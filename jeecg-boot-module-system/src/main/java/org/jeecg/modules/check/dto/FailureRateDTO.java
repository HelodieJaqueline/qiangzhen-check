package org.jeecg.modules.check.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhangrui
 * @Description:
 * @Date: 2022/9/20 23:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("故障率返回对象")
public class FailureRateDTO {

    @ApiModelProperty(value = "检测数量")
    private Long checked;

    @ApiModelProperty(value = "合格数量")
    private Long pass;

    @ApiModelProperty(value = "故障数量")
    private Long failure;

    @ApiModelProperty(value = "合格率")
    private String passRate;


}
