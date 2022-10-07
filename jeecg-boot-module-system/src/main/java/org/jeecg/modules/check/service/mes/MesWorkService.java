package org.jeecg.modules.check.service.mes;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.check.dto.MesWorkStateResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhangrui
 * @Description: mes工单相关接口
 * @Date: 2022/9/15 22:23
 */
@Service
@Slf4j
public class MesWorkService {


    public Result<?> queryInfo(String workCode) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime startTime = now.minusDays(180);
        LocalDateTime endOfToday = DateUtil.toLocalDateTime(DateUtil.endOfDay(new Date()));

        HashMap<String, Object> map = new HashMap<>();
        map.put("strWorkCodeS", workCode);
        map.put("strWorkCodeE", workCode);
        map.put("dtStart", DateUtil.format(startTime,"yyyy/MM/dd HH:mm:ss"));
        map.put("dtEnd", DateUtil.format(endOfToday,"yyyy/MM/dd HH:mm:ss"));
        HttpResponse response = HttpRequest.post("http://172.16.10.2:5000/WeightTrace/QueryWorkCodeState")
                .contentType(ContentType.FORM_URLENCODED.getValue()).form(map).execute();
        String body = response.body();
        log.info("接口返回:{}", body);
        MesWorkStateResponse mesWorkStateResponse = JSON.parseObject(body, MesWorkStateResponse.class);
        List<MesWorkStateResponse.WorkState> workStates = mesWorkStateResponse.getWorkStates();
        if (CollectionUtils.isEmpty(workStates)) {
            return Result.error("未查询到流程卡号对应的工单西信息");
        }
        MesWorkStateResponse.WorkState workState = workStates.get(0);
        if (StringUtils.isBlank(workState.getWorkCode())) {
            return Result.error("未查询到流程卡号信息");
        }
        if (StringUtils.isBlank(workState.getMaterialCode())) {
            return Result.error("未查询到物料号信息");
        }
        if (StringUtils.isBlank(workState.getMaterialName())) {
            return Result.error("未查询到物料名称信息");
        }
        if (StringUtils.isBlank(workState.getProductDraw())) {
            return Result.error("未查询到图号信息");
        }
        if (StringUtils.isBlank(workState.getDesc2())) {
            return Result.error("未查询到硬度信息");
        }
        if (StringUtils.isBlank(workState.getDesc3())) {
            return Result.error("未查询到材质信息");
        }
        if (StringUtils.isBlank(workState.getPStationName())) {
            return Result.error("未查询到材质信息");
        }
        return Result.OK(workState);
    }

    public MesWorkStateResponse.WorkState queryInfo(){
        MesWorkStateResponse.WorkState workState = new MesWorkStateResponse.WorkState();
        workState.setMaterialName("测试物料号");
        workState.setProductDraw("10.11.2.3");
        return workState;
    }

}
