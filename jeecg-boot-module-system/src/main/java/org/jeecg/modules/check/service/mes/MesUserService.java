package org.jeecg.modules.check.service.mes;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.modules.check.dto.MesUserInfoResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangrui
 * @Description:
 * @Date: 2022/9/16 14:38
 */
@Service
@Slf4j
public class MesUserService {


    public MesUserInfoResponse getUserInfoByCode(String code) {
        // TODO:  查询用户信息
        Map<String, Object> params = new HashMap<>();
        params.put("strUserCode", code);
        //params.put("strUserName", code);
        HttpResponse response = HttpRequest.get("http://172.16.10.2:5000/TSetting/QueryUserByUserCode")
                .form(params)
                .header("Cookie", getUserCookie())
                .execute();
        String cookieStr = response.getCookieStr();

        return JSON.parseObject(response.body(), MesUserInfoResponse.class);
    }

    public String getUserCookie() {
        Map<String, Object> param = new HashMap<>();
        param.put("username", "admin");
        param.put("password", "maple");
        HttpResponse response = HttpRequest.post("http://172.16.10.2:5000/Account/MESCheckLogin")
                .contentType(ContentType.FORM_URLENCODED.getValue())
                .form(param).execute();
        return response == null ? null : response.getCookieStr();
    }

}
