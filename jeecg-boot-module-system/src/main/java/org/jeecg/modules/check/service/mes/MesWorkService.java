package org.jeecg.modules.check.service.mes;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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


    public MesWorkStateResponse.WorkState queryInfo(String workCode) {
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
        return CollectionUtils.isEmpty(workStates) ? null : workStates.get(0);
    }

    public MesWorkStateResponse.WorkState queryInfo(){
        MesWorkStateResponse.WorkState workState = new MesWorkStateResponse.WorkState();
        workState.setMaterialName("测试物料号");
        workState.setProductDraw("10.11.2.3");
        return workState;
    }

}
