package org.jeecg.modules.check.service.mes;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.modules.check.dto.MesUserInfoResponse;
import org.springframework.stereotype.Service;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangrui
 * @Description:
 * @Date: 2022/9/16 14:38
 */
@Service
@Slf4j
public class MesUserService {


    public MesUserInfoResponse.MesUser getUserInfoByCode(String code) {
        // TODO:  查询用户信息
        Map<String, Object> params = new HashMap<>();
        params.put("strUserCode", code);
        //params.put("strUserName", code);
        HttpResponse response = HttpRequest.get("http://172.16.10.2:5000/TSetting/QueryUserByUserCode")
                .form(params)
                .header("Cookie", getUserCookie())
                .execute();
        log.info("getUserInfoByCode from MES response:{}", response);
        MesUserInfoResponse mesUserInfoResponse = JSON.parseObject(response.body(), MesUserInfoResponse.class);
        if (null != mesUserInfoResponse && CollectionUtils.isNotEmpty(mesUserInfoResponse.getMesUsers())) {
            return mesUserInfoResponse.getMesUsers().get(0);
        }
        return null;
    }

    public String getUserCookie() {
        Map<String, Object> param = new HashMap<>();
        param.put("strUserCode", "admin");
        param.put("strPassword", "maple");
        HttpResponse response = HttpRequest.post("http://qzqms.check.com/Account/MESCheckLogin")
                .contentType(ContentType.FORM_URLENCODED.getValue())
                .form(param).execute();
        log.info("MESCheckLogin return Cookie:{}",response.getCookieStr());
        return response.getCookieStr();
    }

}
