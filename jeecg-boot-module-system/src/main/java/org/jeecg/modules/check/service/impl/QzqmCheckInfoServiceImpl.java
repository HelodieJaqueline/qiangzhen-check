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

    @Override
    public SummaryVO summary(Integer type) {

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
            return SummaryVO.builder().total(0L).uncheck(0L).checking(0L).totalPassRate("0").build();
        }
        Map<Integer, Long> checkStatusMap = checkStatusList.stream()
                .collect(Collectors.toMap(CountDTO::getType, CountDTO::getTotalCount));

        //待检测数量
        Long unchecked = checkStatusMap.getOrDefault(0, 0L);
        //在检数量
        Long checking = checkStatusMap.getOrDefault(1, 0L);
        //已检数量
        Long checked = checkStatusMap.getOrDefault(2, 0L);
        SummaryVO summaryVO = SummaryVO.builder().uncheck(unchecked).checking(checking).total(checked).build();


        List<CountDTO> qualifiedStatusList = baseMapper.countByQualifiedStatus(start, end);
        if (CollectionUtils.isEmpty(qualifiedStatusList)) {
            summaryVO.setTotalPassRate("0");
            return summaryVO;
        }
        Map<Integer, Long> qualifiedStatusMap = qualifiedStatusList.stream()
                .collect(Collectors.toMap(CountDTO::getType, CountDTO::getTotalCount));
        Long qualifiedCount = qualifiedStatusMap.getOrDefault(1, 0L);
        Long totalQualifiedCount = 0L;
        for (CountDTO countDTO : qualifiedStatusList) {
            totalQualifiedCount += countDTO.getTotalCount();
        }
        BigDecimal qualifiedRate = BigDecimal.valueOf(qualifiedCount * 100)
                .divide(BigDecimal.valueOf(totalQualifiedCount),new MathContext(2, RoundingMode.HALF_UP));
        summaryVO.setTotalPassRate(qualifiedRate.toPlainString());
        return summaryVO;
    }

    @Override
    public List<PassRateDTO> passRate() {
        List<PassRateDTO> list = baseMapper.countPassRate();
        if (CollectionUtils.isNotEmpty(list)) {
            for (PassRateDTO passRateDTO : list) {
                passRateDTO.setRate(passRateDTO.getRate() + "%");
            }
        }
        return list;
    }

    @Override
    public FailureRateDTO failureRate(Integer type, String productDraw) {
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
        List<CountDTO> countDTOS = baseMapper.countByProductDraw(start, end, productDraw);
        if (CollectionUtils.isEmpty(countDTOS)) {
            return null;
        }
        Map<Integer, Long> qualifiedStatusMap = countDTOS.stream()
                .collect(Collectors.toMap(CountDTO::getType, CountDTO::getTotalCount));
        Long pass = qualifiedStatusMap.getOrDefault(1, 0L);
        Long failure = qualifiedStatusMap.getOrDefault(2, 0L);
        Long checked = pass + failure;
        BigDecimal passRate = BigDecimal.valueOf(pass * 100)
                .divide(BigDecimal.valueOf(checked), new MathContext(2, RoundingMode.HALF_UP));

        return FailureRateDTO.builder().pass(pass).failure(failure)
                .checked(checked).passRate(passRate.toPlainString()).build();

    }
}
