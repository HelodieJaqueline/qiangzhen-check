package org.jeecg.modules.check.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhangrui
 * @Description:
 * @Date: 2022/9/20 20:12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountDTO {

    private Integer type;

    private Long totalCount;

}
