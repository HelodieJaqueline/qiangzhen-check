package org.jeecg.modules.check.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.check.dto.FailureRateDTO;
import org.jeecg.modules.check.dto.PassRateDTO;
import org.jeecg.modules.check.entity.QzqmCheckInfo;
import org.jeecg.modules.check.vo.SummaryVO;

import java.util.List;

/**
 * @Description: qzqm_check_info
 * @Author: jeecg-boot
 * @Date:   2022-09-16
 * @Version: V1.0
 */
public interface IQzqmCheckInfoService extends IService<QzqmCheckInfo> {

    SummaryVO summary(Integer type);

    List<PassRateDTO> passRate();

    FailureRateDTO failureRate(Integer type, String productDraw);
}
