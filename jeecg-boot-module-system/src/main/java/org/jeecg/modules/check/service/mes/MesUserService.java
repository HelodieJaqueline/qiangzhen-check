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
        return ".AspNetCore.Cookies=CfDJ8FAok77Wx-VAgWHfwSImD2QY8h-d1RCYIfK8xJsv5UaE_ihDyvxOaVyW5ZkR2yvxmL646lz0s-9mUksYdQEqx8oejvwli5brA3VUdvWLHBlV0MtR5xNEzL6XiAthdhtFLkts9sq3uKC79NZYp21mlpe58p9ZpOvoRwHTFcQ_pf-eDVip9wK30oXPzCKvcF2gnt7S7K2m3GSIFGsmRQYYF5kyCXXg71hwGCsjEAc9v1A5VSg9yLWJBKhINerLTM0JtYwMU-yLbfsOYPGOZMGaTuVdJtZeIJAYXkxRsOtV4OvQhWB-2-7IoLN4oEnTOupM5W66A5zjneEEmMXYv9qzXx0Leck7UeNgLjckc54rtOQdzQcW5cp-wWA7uPI6VqIbAV-WdqcOMUgdg4jvejW6nJQDXDksN5YPsVdHfHsrtDLwsVle5CPqFCpLMdeOXowIvyDktQqWUl7QEWZ6ulUGajA";
/*        Map<String, Object> param = new HashMap<>();
        param.put("username", "admin");
        param.put("password", "maple");
        HttpResponse response = HttpRequest.post("http://172.16.10.2:5000/Account/MESCheckLogin")
                .contentType(ContentType.FORM_URLENCODED.getValue())
                .form(param).execute();
        return response == null ? null : response.getCookieStr();*/
    }

    public Map<String, Object> getCookie() {
        Map<String, Object> param = new HashMap<>();
        param.put("strUserCode", "admin");
        param.put("strPassword", "maple");
        HttpResponse response = HttpRequest.post("http://qzqms.check.com/Account/MESCheckLogin")
                .contentType(ContentType.FORM_URLENCODED.getValue())
                .form(param).execute();
        String cookieStr = response.getCookieStr();
        List<HttpCookie> cookies = response.getCookies();
        HashMap<String, Object> result = new HashMap<>();
        result.put("cookieStr", cookieStr);
        result.put("cookies", cookies);
        return result;
    }

}
