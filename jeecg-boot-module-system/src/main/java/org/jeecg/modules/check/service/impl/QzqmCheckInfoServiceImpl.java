package org.jeecg.modules.check.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.modules.check.dto.CountDTO;
import org.jeecg.modules.check.dto.FailureRateDTO;
import org.jeecg.modules.check.dto.PassRateDTO;
import org.jeecg.modules.check.entity.QzqmCheckInfo;
import org.jeecg.modules.check.mapper.QzqmCheckInfoMapper;
import org.jeecg.modules.check.service.IQzqmCheckInfoService;
import org.jeecg.modules.check.vo.SummaryVO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Description: qzqm_check_info
 * @Author: jeecg-boot
 * @Date:   2022-09-16
 * @Version: V1.0
 */
@Service
public class QzqmCheckInfoServiceImpl extends ServiceImpl<QzqmCheckInfoMapper, QzqmCheckInfo> implements IQzqmCheckInfoService {

    private static final String BACKGROUND_COLOR = "#4992FF";

    @Override
    public List<SummaryVO> summary(Integer type) {

        //根据日期查询
        Date now = new Date();
        Date start, end;
        if (type.equals(1)) {
            start = DateUtil.beginOfDay(now);
            end = DateUtil.endOfDay(now);
        } else if (type.equals(2)) {
            start = DateUtil.beginOfMonth(now);
            end = DateUtil.endOfMonth(now);
        } else if (type.equals(3)) {
            start = DateUtil.beginOfYear(now);
            end = DateUtil.endOfYear(now);
        } else {
            return null;
        }

        List<CountDTO> checkStatusList = baseMapper.countByCheckStatus(start, end);
        if (CollectionUtils.isEmpty(checkStatusList)) {
            // TODO:  
            return null;
        }
        Map<Integer, Long> checkStatusMap = checkStatusList.stream()
                .collect(Collectors.toMap(CountDTO::getType, CountDTO::getTotalCount));


        List<CountDTO> qualifiedStatusList = baseMapper.countByQualifiedStatus(start, end);

        Map<Integer, Long> qualifiedStatusMap = qualifiedStatusList.stream()
                .collect(Collectors.toMap(CountDTO::getType, CountDTO::getTotalCount));

        Long qualifiedCount = qualifiedStatusMap.getOrDefault(1, 0L);
        Long totalQualifiedCount = 0L;
        for (CountDTO countDTO : qualifiedStatusList) {
            totalQualifiedCount += countDTO.getTotalCount();
        }
        BigDecimal qualifiedRate = BigDecimal.valueOf(qualifiedCount * 100)
                .divide(BigDecimal.valueOf(totalQualifiedCount),new MathContext(2, RoundingMode.HALF_UP));


        //待检测数量
        Long unchecked = checkStatusMap.getOrDefault(0, 0L);
        //在检数量
        Long checking = checkStatusMap.getOrDefault(1, 0L);
        //已检数量
        Long checked = checkStatusMap.getOrDefault(2, 0L);
        //总量
        Long total = unchecked + checking + checked;

        SummaryVO totalSummary = SummaryVO.builder()
                .prefixText("总检测数量").value(String.valueOf(total)).backgroundColor(BACKGROUND_COLOR).build();
        SummaryVO uncheckedSummary = SummaryVO.builder().prefixText("待检测数量").value(String.valueOf(unchecked)).backgroundColor(BACKGROUND_COLOR).build();
        //总合格率
        SummaryVO totalQualifiedSummary = SummaryVO.builder().prefixText("总合格率").value(qualifiedRate.toPlainString()).backgroundColor(BACKGROUND_COLOR).suffixText("%").build();

        SummaryVO checkingSummary = SummaryVO.builder().prefixText("在检数量").backgroundColor(BACKGROUND_COLOR).value(String.valueOf(checking)).build();
        List<SummaryVO> result = new LinkedList<>();
        result.add(totalSummary);
        result.add(uncheckedSummary);
        result.add(totalQualifiedSummary);
        result.add(checkingSummary);
        return result;
    }

    @Override
    public List<PassRateDTO> passRate() {
        return baseMapper.countPassRate();
    }

    @Override
    public List<FailureRateDTO> failureRate(Integer type, String productDraw) {
        Date now = new Date();
        Date start, end;
        if (type.equals(1)) {
            start = DateUtil.beginOfDay(now);
            end = DateUtil.endOfDay(now);
        } else if (type.equals(2)) {
            start = DateUtil.beginOfMonth(now);
            end = DateUtil.endOfMonth(now);
        } else if (type.equals(3)) {
            start = DateUtil.beginOfYear(now);
            end = DateUtil.endOfYear(now);
        } else {
            return null;
        }
        List<CountDTO> qualifiedStatusList = baseMapper.countByQualifiedStatus(start, end);
        Map<Integer, Long> qualifiedMap = qualifiedStatusList.stream()
                .collect(Collectors.toMap(CountDTO::getType, CountDTO::getTotalCount));

        Long unknown = qualifiedMap.getOrDefault(0,0L);
        Long pass = qualifiedMap.getOrDefault(1,0L);
        Long failure = qualifiedMap.getOrDefault(2,0L);
        List<FailureRateDTO> result = new ArrayList<>(3);
        result.add(FailureRateDTO.builder().name("未检测").value(unknown).build());
        result.add(FailureRateDTO.builder().name("合格").value(pass).build());
        result.add(FailureRateDTO.builder().name("不合格").value(failure).build());
        return result;
    }
}
