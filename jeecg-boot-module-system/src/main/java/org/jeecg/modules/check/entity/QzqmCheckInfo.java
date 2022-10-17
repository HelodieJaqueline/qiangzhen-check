package org.jeecg.modules.check.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: qzqm_check_info
 * @Author: jeecg-boot
 * @Date:   2022-09-16
 * @Version: V1.0
 */
@Data
@TableName("qzqm_check_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="qzqm_check_info对象", description="qzqm_check_info")
public class QzqmCheckInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键id*/
	@TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "主键id")
    private Long id;
	/**流程卡号*/
	@Excel(name = "流程卡号", width = 15)
    @ApiModelProperty(value = "流程卡号")
    private String workCode;

    /**检测编号*/
    @Excel(name = "检测编号", width = 20)
    @ApiModelProperty(value = "检测编号")
    private Long checkNo;
	/**物料号*/
	@Excel(name = "物料号", width = 15)
    @ApiModelProperty(value = "物料号")
    private String materialCode;
	/**物料名称*/
	@Excel(name = "物料名称", width = 15)
    @ApiModelProperty(value = "物料名称")
    private String materialName;
	/**物料规格*/
	@Excel(name = "物料规格", width = 15)
    @ApiModelProperty(value = "物料规格")
    private String specifications;
	/**图号*/
	@Excel(name = "图号", width = 15)
    @ApiModelProperty(value = "图号")
    private String productDraw;
	/**客户名称*/
	@Excel(name = "客户名称", width = 15)
    @ApiModelProperty(value = "客户名称")
    private String customerName;
	/**要求硬度*/
	@Excel(name = "要求硬度", width = 15)
    @ApiModelProperty(value = "要求硬度")
    private String hardness;
	/**当前工序*/
	@Excel(name = "当前工序", width = 15)
    @ApiModelProperty(value = "当前工序")
    private String process;
    /**当前工序*/
    @Excel(name = "当前工序详情", width = 15)
    @ApiModelProperty(value = "当前工序详情")
    private String processDetail;
	/**材质*/
	@Excel(name = "材质", width = 15)
    @ApiModelProperty(value = "材质")
    private String material;
	/**送检时间*/
	@Excel(name = "送检时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "送检时间")
    private Date deliveryTime;
	/**送检人*/
	@Excel(name = "送检人", width = 15)
    @ApiModelProperty(value = "送检人")
    private String deliveryUserId;
	/**送检人姓名*/
	@Excel(name = "送检人姓名", width = 15)
    @ApiModelProperty(value = "送检人姓名")
    private String deliveryUserName;
	/**送检部门*/
	@Excel(name = "送检部门", width = 15)
    @ApiModelProperty(value = "送检部门")
    private String deliveryDep;
	/**检验员id*/
	@Excel(name = "检验员id", width = 15)
    @ApiModelProperty(value = "检验员id")
    private Integer checkUserId;
	/**检验员名称*/
	@Excel(name = "检验员名称", width = 15)
    @ApiModelProperty(value = "检验员名称")
    private String checkUserName;
	/**检验设备*/
	@Excel(name = "检验设备", width = 15)
    @ApiModelProperty(value = "检验设备")
    @Dict(dicCode = "checkDevice")
    private Integer checkDevice;
	/**检测时间*/
	@Excel(name = "检测时间", width = 15, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "检测时间")
    private Date checkTime;
	/**预估完成所需要的时间*/
	@Excel(name = "预估完成所需要的时间", width = 15)
    @ApiModelProperty(value = "预估完成所需要的时间")
    private Integer evaluateTime;
	/**上传报告地址*/
	@Excel(name = "上传报告地址", width = 15)
    @ApiModelProperty(value = "上传报告地址")
    private String reportUrl;
    /**上传pdf报告地址*/
    @Excel(name = "上传pdf报告地址", width = 15)
    @ApiModelProperty(value = "上传pdf报告地址")
    private String reportPdfUrl;
	/**结束日期*/
	@Excel(name = "结束日期", width = 15, format = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束日期")
    private Date finishedTime;
	/**检测状态(0:待检测，1:检测中，2:检测完成)*/
	@Excel(name = "检测状态(0:待检测，1:检测中，2:检测完成)", width = 15)
    @ApiModelProperty(value = "检测状态(0:待检测，1:检测中，2:检测完成)")
    @Dict(dicCode = "checkStatus")
    private Integer checkStatus;
	/**合格状态(0:未知，1:合格，2:不合格)*/
	@Excel(name = "合格状态(0:未知，1:合格，2:不合格)", width = 15)
    @ApiModelProperty(value = "合格状态(0:未知，1:合格，2:不合格)")
    @Dict(dicCode = "qualifiedStatus")
    private Integer qualifiedStatus;
	/**是否已删除*/
	@Excel(name = "是否已删除", width = 15)
    @ApiModelProperty(value = "是否已删除")
    private Boolean isDeleted;
	/**创建时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
	/**更新时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
}
