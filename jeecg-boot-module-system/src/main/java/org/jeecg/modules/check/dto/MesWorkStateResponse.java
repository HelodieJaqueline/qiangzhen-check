package org.jeecg.modules.check.dto;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: zhangrui
 * @Description:
 * @Date: 2022/9/16 15:24
 */
@NoArgsConstructor
@Data
public class MesWorkStateResponse {


    @JSONField(name = "success")
    private Boolean success;
    @JSONField(name = "content")
    private List<WorkState> workStates;
    @JSONField(name = "msg")
    private String msg;
    @JSONField(name = "count")
    private Integer count;
    @JSONField(name = "total")
    private Integer total;

    @NoArgsConstructor
    @Data
    public static class WorkState {

        /**
         * 物料号
         */
        @JSONField(name = "materialCode")
        private String materialCode;

        /**
         * 物料名称
         */
        @JSONField(name = "materialName")
        private String materialName;

        /**
         * 零件图号
         */
        @JSONField(name = "productDraw")
        private String productDraw;

        /**
         * 物料规格
         */
        @JSONField(name = "desc1")
        private String desc1;

        /**
         *
         */
        @JSONField(name = "desc2")
        private String desc2;

        /**
         * 材质
         */
        @JSONField(name = "desc3")
        private String desc3;
        @JSONField(name = "desc4")
        private String desc4;
        @JSONField(name = "oldMachName")
        private Object oldMachName;
        @JSONField(name = "newMachName")
        private String newMachName;
        @JSONField(name = "newProcessName")
        private String newProcessName;
        @JSONField(name = "newProcessRout")
        private Integer newProcessRout;
        @JSONField(name = "palletCode")
        private String palletCode;
        @JSONField(name = "nowValue")
        private Double nowValue;
        @JSONField(name = "palletWeight")
        private Integer palletWeight;
        @JSONField(name = "stateName")
        private String stateName;
        @JSONField(name = "userID")
        private Integer userID;
        @JSONField(name = "userName")
        private String userName;
        @JSONField(name = "receiptID")
        private Integer receiptID;
        @JSONField(name = "receiptName")
        private String receiptName;
        @JSONField(name = "newStationID")
        private Integer newStationID;
        @JSONField(name = "newStationName")
        private String newStationName;
        @JSONField(name = "lotNo")
        private Object lotNo;
        @JSONField(name = "shiftName")
        private String shiftName;
        @JSONField(name = "firstProcessRout")
        private Integer firstProcessRout;
        @JSONField(name = "pStationID")
        private Integer pStationID;
        @JSONField(name = "pStationName")
        private String pStationName;
        @JSONField(name = "id")
        private Integer id;
        @JSONField(name = "pBillID")
        private String pBillID;
        @JSONField(name = "parentWorkID")
        private Integer parentWorkID;

        /**
         * 流程卡号
         */
        @JSONField(name = "workCode")
        private String workCode;
        @JSONField(name = "workName")
        private String workName;
        @JSONField(name = "partBomID")
        private Integer partBomID;
        @JSONField(name = "batchID")
        private Object batchID;
        @JSONField(name = "okValue")
        private Integer okValue;
        @JSONField(name = "ngValue")
        private Integer ngValue;
        @JSONField(name = "orderValue")
        private Integer orderValue;
        @JSONField(name = "orderDT")
        private String orderDT;
        @JSONField(name = "shipDT")
        private String shipDT;
        @JSONField(name = "endDT")
        private String endDT;
        @JSONField(name = "isEnabled")
        private Boolean isEnabled;
        @JSONField(name = "stationStateID")
        private Integer stationStateID;
        @JSONField(name = "oldMachID")
        private Integer oldMachID;
        @JSONField(name = "newMachID")
        private Integer newMachID;
        @JSONField(name = "newWorkProcessID")
        private Integer newWorkProcessID;
        @JSONField(name = "palletID")
        private Integer palletID;
        @JSONField(name = "partStateID")
        private Integer partStateID;
        @JSONField(name = "doneWeight")
        private Double doneWeight;
        @JSONField(name = "doneNum")
        private Integer doneNum;
        @JSONField(name = "loginID")
        private Integer loginID;
        @JSONField(name = "checkIn")
        private Boolean checkIn;
        @JSONField(name = "updateDT")
        private String updateDT;
        @JSONField(name = "loadDT")
        private String loadDT;
    }
}
