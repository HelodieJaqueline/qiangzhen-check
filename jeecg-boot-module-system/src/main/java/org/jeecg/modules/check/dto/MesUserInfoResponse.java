package org.jeecg.modules.check.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: zhangrui
 * @Description:
 * @Date: 2022/9/16 14:50
 */
@NoArgsConstructor
@Data
public class MesUserInfoResponse {


    @JSONField(name = "success")
    private Boolean success;
    @JSONField(name = "content")
    private List<MesUser> mesUsers;
    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "count")
    private Integer count;
    @JSONField(name = "total")
    private Integer total;

    @NoArgsConstructor
    @Data
    public static class MesUser {
        @JSONField(name = "dptCode")
        private String dptCode;
        @JSONField(name = "dptName")
        private String dptName;
        @JSONField(name = "shiftCode")
        private String shiftCode;
        @JSONField(name = "shiftName")
        private String shiftName;
        @JSONField(name = "beginTime")
        private String beginTime;
        @JSONField(name = "endTime")
        private String endTime;
        @JSONField(name = "roleID")
        private Integer roleID;
        /**
         * 用户code
         */
        @JSONField(name = "userCode")
        private String userCode;

        /**
         * 用户名
         */
        @JSONField(name = "userName")
        private String userName;
        @JSONField(name = "password")
        private String password;
        @JSONField(name = "departMentID")
        private Integer departMentID;
        @JSONField(name = "shiftID")
        private Integer shiftID;
        @JSONField(name = "telephone")
        private Object telephone;
        @JSONField(name = "trainLevel")
        private Object trainLevel;
        @JSONField(name = "id")
        private Integer id;
        @JSONField(name = "isEnabled")
        private Boolean isEnabled;
        @JSONField(name = "updateDT")
        private String updateDT;
    }
}
