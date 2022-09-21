package org.jeecg.util;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @Author: zhangrui
 * @Description:
 * @Date: 2022/9/21 10:11
 */
public class DateUtils {

    public static LocalDateTime getStartOfDay(LocalDate today){
        return LocalDateTime.of(today == null ? LocalDate.now() : today, LocalTime.MAX);
    }

    public static LocalDateTime getEndOfDay(LocalDate today){
        return LocalDateTime.of(today == null ? LocalDate.now() : today, LocalTime.MAX);
    }
}
