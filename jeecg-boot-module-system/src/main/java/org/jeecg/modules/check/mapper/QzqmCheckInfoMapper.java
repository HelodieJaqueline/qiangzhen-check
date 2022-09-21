package org.jeecg.modules.check.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.check.dto.CountDTO;
import org.jeecg.modules.check.dto.PassRateDTO;
import org.jeecg.modules.check.entity.QzqmCheckInfo;

import java.util.Date;
import java.util.List;

/**
 * @Description: qzqm_check_info
 * @Author: jeecg-boot
 * @Date: 2022-09-16
 * @Version: V1.0
 */
public interface QzqmCheckInfoMapper extends BaseMapper<QzqmCheckInfo> {

    List<CountDTO> countByCheckStatus(@Param("start") Date start, @Param("end") Date end);

    List<CountDTO> countByQualifiedStatus(@Param("start") Date start, @Param("end") Date end);

    List<PassRateDTO> countPassRate();

    List<Long> countFailure(@Param("start") Date start, @Param("end") Date end);
}
