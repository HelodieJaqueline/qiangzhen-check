package org.jeecg.modules.check.dto;

import lombok.AllArgsConstructor;
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
public class PassRateDTO {

    private String productDraw;

    private String materialName;

    private String rate;
}
