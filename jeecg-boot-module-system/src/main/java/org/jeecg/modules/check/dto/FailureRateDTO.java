package org.jeecg.modules.check.dto;

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
public class FailureRateDTO {

    private String name;

    private Long value;
}
