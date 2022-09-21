package org.jeecg.modules.check.vo;

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
public class SummaryVO {

    private String backgroundColor;

    private String prefixText;

    private String value;

    private String suffixText;

}
