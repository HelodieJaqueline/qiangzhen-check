package org.jeecg.modules.check.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.check.dto.FailureRateDTO;
import org.jeecg.modules.check.dto.MesUserInfoResponse;
import org.jeecg.modules.check.dto.MesWorkStateResponse;
import org.jeecg.modules.check.dto.PassRateDTO;
import org.jeecg.modules.check.entity.QzqmCheckInfo;
import org.jeecg.modules.check.service.IQzqmCheckInfoService;
import org.jeecg.modules.check.service.mes.MesUserService;
import org.jeecg.modules.check.service.mes.MesWorkService;
import org.jeecg.modules.check.vo.SummaryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description: qzqm_check_info
 * @Author: jeecg-boot
 * @Date:   2022-09-16
 * @Version: V1.0
 */
@Api(tags="qzqm_check_info")
@RestController
@RequestMapping("/check/qzqmCheckInfo")
@Slf4j
public class QzqmCheckInfoController extends JeecgController<QzqmCheckInfo, IQzqmCheckInfoService> {
	@Autowired
	private IQzqmCheckInfoService qzqmCheckInfoService;

	@Autowired
	private MesWorkService mesWorkService;

	@Autowired
	private MesUserService mesUserService;

	/**
	 * 已检列表
	 *
	 * @param qzqmCheckInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-已检列表")
	@ApiOperation(value="qzqm_check_info-已检列表", notes="qzqm_check_info-已检列表")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(QzqmCheckInfo qzqmCheckInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//已检列表
		qzqmCheckInfo.setCheckStatus(2);
		Map<String, String[]> parameterMap = req.getParameterMap();
		QueryWrapper<QzqmCheckInfo> queryWrapper = QueryGenerator.initQueryWrapper(qzqmCheckInfo, parameterMap);
		String[] times = parameterMap.get("createTimeRange[]");
		if (times != null && times.length == 2) {
			queryWrapper.between("delivery_Time", times[0].replace("\"", ""),times[1].replace("\"", ""));
		}
		queryWrapper.orderByDesc("delivery_Time");
		Page<QzqmCheckInfo> page = new Page<QzqmCheckInfo>(pageNo, pageSize);
		IPage<QzqmCheckInfo> pageList = qzqmCheckInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 送检列表
	 *
	 * @param qzqmCheckInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-送检列表")
	@ApiOperation(value="qzqm_check_info-送检列表", notes="qzqm_check_info-送检列表")
	@GetMapping(value = "/uncheckedList")
	public Result<?> uncheckedList(QzqmCheckInfo qzqmCheckInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		qzqmCheckInfo.setCheckStatus(0);
		Map<String, String[]> parameterMap = req.getParameterMap();
		QueryWrapper<QzqmCheckInfo> queryWrapper = QueryGenerator.initQueryWrapper(qzqmCheckInfo, parameterMap);
		String[] times = parameterMap.get("createTimeRange[]");
		if (times != null && times.length == 2) {
			queryWrapper.between("delivery_Time", times[0].replace("\"", ""),times[1].replace("\"", ""));
		}
		queryWrapper.orderByDesc("delivery_Time");
		Page<QzqmCheckInfo> page = new Page<QzqmCheckInfo>(pageNo, pageSize);
		IPage<QzqmCheckInfo> pageList = qzqmCheckInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 * 在检列表
	 *
	 * @param qzqmCheckInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-在检列表")
	@ApiOperation(value="qzqm_check_info-在检列表", notes="qzqm_check_info-在检列表")
	@GetMapping(value = "/checkingList")
	public Result<?> checkingList(QzqmCheckInfo qzqmCheckInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		qzqmCheckInfo.setCheckStatus(1);
		Map<String, String[]> parameterMap = req.getParameterMap();
		QueryWrapper<QzqmCheckInfo> queryWrapper = QueryGenerator.initQueryWrapper(qzqmCheckInfo, parameterMap);
		String[] times = parameterMap.get("createTimeRange[]");
		if (times != null && times.length == 2) {
			queryWrapper.between("delivery_Time", times[0].replace("\"", ""),times[1].replace("\"", ""));
		}
		queryWrapper.orderByDesc("delivery_Time");
		Page<QzqmCheckInfo> page = new Page<QzqmCheckInfo>(pageNo, pageSize);
		IPage<QzqmCheckInfo> pageList = qzqmCheckInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param qzqmCheckInfo
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-添加")
	@ApiOperation(value="qzqm_check_info-添加", notes="qzqm_check_info-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody QzqmCheckInfo qzqmCheckInfo) {
		qzqmCheckInfoService.save(qzqmCheckInfo);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param qzqmCheckInfo
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-编辑")
	@ApiOperation(value="qzqm_check_info-编辑", notes="qzqm_check_info-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody QzqmCheckInfo qzqmCheckInfo) {
		qzqmCheckInfoService.updateById(qzqmCheckInfo);
		return Result.OK("编辑成功!");
	}

	/**
	 *  checking
	 *
	 * @param qzqmCheckInfo
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-checking")
	@ApiOperation(value="qzqm_check_info-checking", notes="qzqm_check_info-checking")
	@PutMapping(value = "/checking")
	public Result<?> checking(@RequestBody QzqmCheckInfo qzqmCheckInfo) {
		qzqmCheckInfo.setCheckStatus(1);
		qzqmCheckInfoService.updateById(qzqmCheckInfo);
		return Result.OK("编辑成功!");
	}

	/**
	 *  checking
	 *
	 * @param qzqmCheckInfo
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-checked")
	@ApiOperation(value="qzqm_check_info-checking", notes="qzqm_check_info-checked")
	@PutMapping(value = "/checked")
	public Result<?> checked(@RequestBody QzqmCheckInfo qzqmCheckInfo) {
		qzqmCheckInfo.setCheckStatus(2);
		qzqmCheckInfoService.updateById(qzqmCheckInfo);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-通过id删除")
	@ApiOperation(value="qzqm_check_info-通过id删除", notes="qzqm_check_info-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qzqmCheckInfoService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-批量删除")
	@ApiOperation(value="qzqm_check_info-批量删除", notes="qzqm_check_info-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qzqmCheckInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-通过id查询")
	@ApiOperation(value="qzqm_check_info-通过id查询", notes="qzqm_check_info-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		QzqmCheckInfo qzqmCheckInfo = qzqmCheckInfoService.getById(id);
		if(qzqmCheckInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(qzqmCheckInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param qzqmCheckInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, QzqmCheckInfo qzqmCheckInfo) {
        return super.exportXls(request, qzqmCheckInfo, QzqmCheckInfo.class, "qzqm_check_info");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, QzqmCheckInfo.class);
    }

	 /**
	  * 分页列表查询
	  *
	  * @param qzqmCheckInfo
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "qzqm_check_info-查询工单")
	 @ApiOperation(value="qzqm_check_info-查询工单", notes="qzqm_check_info-查询工单")
	 @GetMapping(value = "/queryWorkState")
	 public Result<?> queryWorkState(QzqmCheckInfo qzqmCheckInfo,
									HttpServletRequest req) {
		 return mesWorkService.queryInfo(qzqmCheckInfo.getWorkCode());
	 }

	 /**
	  * 查询mes工单
	  *
	  * @return
	  */
	 @AutoLog(value = "qzqm_check_info-查询工单")
	 @ApiOperation(value="qzqm_check_info-查询工单", notes="qzqm_check_info-查询工单")
	 @GetMapping(value = "/queryByWorkCode")
	 public Result<?> queryByWorkCode(String workCode) {
		 List<QzqmCheckInfo> list = qzqmCheckInfoService.list(new LambdaQueryWrapper<QzqmCheckInfo>().eq(QzqmCheckInfo::getWorkCode, workCode)
				 .eq(QzqmCheckInfo::getIsDeleted, false));
		 if(CollectionUtils.isEmpty(list)) {
			 return Result.error("未找到流程卡号" + workCode + "对应的工单数据");
		 }
		 return Result.OK(list.get(0));
	 }

	/**
	 * 查询mes员工
	 *
	 * @param userId
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-查询员工")
	@ApiOperation(value="qzqm_check_info-查询员工", notes="qzqm_check_info-查询员工")
	@GetMapping(value = "/queryMesUser")
	public Result<?> queryMesUser(String userId) {
		MesUserInfoResponse.MesUser mesUser = mesUserService.getUserInfoByCode(userId);
		if (null == mesUser) {
			return Result.error("未找到" + userId + "对应的员工信息");
		}
		return Result.OK(mesUser);
	}

	@AutoLog(value = "qzqm_check_info-轮播表")
	@ApiOperation(value="qzqm_check_info-轮播表", notes="qzqm_check_info-轮播表")
	@GetMapping(value = "/tableScroll")
	public Result<?> tableScroll(QzqmCheckInfo qzqmCheckInfo,
								   @RequestParam(name="pageNo", defaultValue="1",required = false) Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10",required = false) Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<QzqmCheckInfo> queryWrapper = QueryGenerator.initQueryWrapper(qzqmCheckInfo, req.getParameterMap());
		queryWrapper.orderByDesc("delivery_Time");
		Page<QzqmCheckInfo> page = new Page<QzqmCheckInfo>(pageNo, pageSize);
		IPage<QzqmCheckInfo> pageList = qzqmCheckInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	@AutoLog(value = "qzqm_check_info-数据总览")
	@ApiOperation(value = "qzqm_check_info-数据总览", notes = "qzqm_check_info-数据总览")
	@GetMapping(value = "/summary")
	public SummaryVO summary(@RequestParam(required = false, defaultValue = "1") Integer type) {
		return qzqmCheckInfoService.summary(type);
	}

	@AutoLog(value = "qzqm_check_info-合格率排行榜")
	@ApiOperation(value = "qzqm_check_info-合格率排行榜", notes = "qzqm_check_info-合格率排行榜")
	@GetMapping(value = "/passRate")
	public List<PassRateDTO> passRate() {
		return qzqmCheckInfoService.passRate();
	}

	@AutoLog(value = "qzqm_check_info-零件故障率")
	@ApiOperation(value = "qzqm_check_info-零件故障率", notes = "qzqm_check_info-零件故障率")
	@GetMapping(value = "/failureRate")
	public FailureRateDTO failureRate(@RequestParam(required = false, defaultValue = "1") Integer type,
											String productDraw) {
		return qzqmCheckInfoService.failureRate(type, productDraw);
	}

	@AutoLog(value = "qzqm_check_info-getCookie")
	@ApiOperation(value="qzqm_check_info-getCookie", notes="qzqm_check_info-getCookie")
	@GetMapping(value = "/getCookie")
	public Result<?> getCookie(String userId) {
		return Result.OK(mesUserService.getUserCookie());
	}

}
