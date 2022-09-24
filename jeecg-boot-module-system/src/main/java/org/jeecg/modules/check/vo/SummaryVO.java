package org.jeecg.modules.check.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhangrui
 * @Description:
 * @Date: 2022/9/20 20:05
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value="数据总览")
public class SummaryVO {

    @ApiModelProperty(value = "总检测数量")
    private Long total;

    @ApiModelProperty(value = "待检测数量")
    private Long uncheck;

    @ApiModelProperty(value = "总合格率")
    private String totalPassRate;

    @ApiModelProperty(value = "在检数量")
    private Long checking;

}
