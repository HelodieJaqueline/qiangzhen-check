package org.jeecg.modules.check.service.mes;

import cn.hutool.http.ContentType;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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


    public MesUserInfoResponse.MesUser getUserInfoByCode(String code) {
        // TODO:  查询用户信息
        Map<String, Object> params = new HashMap<>();
        params.put("strUserCode", code);
        //params.put("strUserName", code);
        HttpResponse response = HttpRequest.get("http://172.16.10.2:5000/TSetting/QueryUserByUserCode")
                .form(params)
                .header("Cookie", getUserCookie())
                .execute();
        MesUserInfoResponse mesUserInfoResponse = JSON.parseObject(response.body(), MesUserInfoResponse.class);
        if (null != mesUserInfoResponse && CollectionUtils.isNotEmpty(mesUserInfoResponse.getMesUsers())) {
            return mesUserInfoResponse.getMesUsers().get(0);
        }
        return null;
    }

    public String getUserCookie() {
        return ".AspNetCore.Cookies=CfDJ8FAok77Wx-VAgWHfwSImD2RkDSL-j6uA5Cr9fiXCddp6ELTHaZ-yUNfcqRLFpHzMO5TdzwwPPcUjci_Ag09L7xIog9dpu2k23milhb2jWwtNfprXEbo0O-tqAMjZOt2ZG8io6caFDUTK2YqcYi9rZ_OFkJ4PR-zGFJncrcUpj6SAuOOuTc1A4oRjGM-f4RiBjn0StHB9u4it1Is4Oxm2h0qOHGlvyS5gvQl33UYOy6HomMMq7iY3W24hDFNXkzqa0rII910sr-UsPgZAIQz6IhAZHGVhNKYbtyps06cfG6DYLxugeOWMVQUPSrLmifqKi2KJywBFChxbngsTaf2kgR7-baIkOgrRNx60n-_1or0rN93BDPfaBJADrhvKNczAniHflWUqfaaniI_x0Klu-o1CyaVNnkM2zC289B4mNoixX_O-DZLF8hPjl9H7lgqSSav48VYFFCdvYAPvowTzIOU";
/*        Map<String, Object> param = new HashMap<>();
        param.put("username", "admin");
        param.put("password", "maple");
        HttpResponse response = HttpRequest.post("http://172.16.10.2:5000/Account/MESCheckLogin")
                .contentType(ContentType.FORM_URLENCODED.getValue())
                .form(param).execute();
        return response == null ? null : response.getCookieStr();*/
    }

}
