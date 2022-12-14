package org.jeecg.modules.check.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.math.MathUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math3.util.MathUtils;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
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
	 * ????????????
	 *
	 * @param qzqmCheckInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-????????????")
	@ApiOperation(value="qzqm_check_info-????????????", notes="qzqm_check_info-????????????")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(QzqmCheckInfo qzqmCheckInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		//????????????
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
	 * ????????????
	 *
	 * @param qzqmCheckInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-????????????")
	@ApiOperation(value="qzqm_check_info-????????????", notes="qzqm_check_info-????????????")
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
	 * ????????????
	 *
	 * @param qzqmCheckInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-????????????")
	@ApiOperation(value="qzqm_check_info-????????????", notes="qzqm_check_info-????????????")
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
	 *   ??????
	 *
	 * @param qzqmCheckInfo
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-??????")
	@ApiOperation(value="qzqm_check_info-??????", notes="qzqm_check_info-??????")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody QzqmCheckInfo qzqmCheckInfo) {
		qzqmCheckInfoService.save(qzqmCheckInfo);
		return Result.OK("???????????????");
	}

	/**
	 *  ??????
	 *
	 * @param qzqmCheckInfo
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-??????")
	@ApiOperation(value="qzqm_check_info-??????", notes="qzqm_check_info-??????")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody QzqmCheckInfo qzqmCheckInfo) {
		qzqmCheckInfoService.updateById(qzqmCheckInfo);
		return Result.OK("????????????!");
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
		qzqmCheckInfo.setCheckTime(new Date());
		qzqmCheckInfoService.updateById(qzqmCheckInfo);
		return Result.OK("????????????!");
	}

	/**
	 *  checked
	 *
	 * @param qzqmCheckInfo
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-checked")
	@ApiOperation(value="qzqm_check_info-checking", notes="qzqm_check_info-checked")
	@PutMapping(value = "/checked")
	public Result<?> checked(@RequestBody QzqmCheckInfo qzqmCheckInfo) {
		qzqmCheckInfo.setCheckStatus(2);
		Date fishedTime = new Date();
		qzqmCheckInfo.setFinishedTime(fishedTime);
		long difference = fishedTime.getTime() - qzqmCheckInfo.getDeliveryTime().getTime();
		Double ceil = Math.ceil(difference / (1000.00 * 60 * 60));
		int intValue = ceil.intValue();
		qzqmCheckInfo.setEvaluateTime(intValue);
		qzqmCheckInfoService.updateById(qzqmCheckInfo);
		return Result.OK("????????????!");
	}

	/**
	 *   ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-??????id??????")
	@ApiOperation(value="qzqm_check_info-??????id??????", notes="qzqm_check_info-??????id??????")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		qzqmCheckInfoService.removeById(id);
		return Result.OK("????????????!");
	}

	/**
	 *  ????????????
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-????????????")
	@ApiOperation(value="qzqm_check_info-????????????", notes="qzqm_check_info-????????????")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.qzqmCheckInfoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("??????????????????!");
	}

	/**
	 * ??????id??????
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-??????id??????")
	@ApiOperation(value="qzqm_check_info-??????id??????", notes="qzqm_check_info-??????id??????")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		QzqmCheckInfo qzqmCheckInfo = qzqmCheckInfoService.getById(id);
		if(qzqmCheckInfo==null) {
			return Result.error("?????????????????????");
		}
		return Result.OK(qzqmCheckInfo);
	}

    /**
    * ??????excel
    *
    * @param request
    * @param qzqmCheckInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, QzqmCheckInfo qzqmCheckInfo) {
        return super.exportXls(request, qzqmCheckInfo, QzqmCheckInfo.class, "qzqm_check_info");
    }

    /**
      * ??????excel????????????
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
	  * ??????????????????
	  *
	  * @param qzqmCheckInfo
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "qzqm_check_info-????????????")
	 @ApiOperation(value="qzqm_check_info-????????????", notes="qzqm_check_info-????????????")
	 @GetMapping(value = "/queryWorkState")
	 public Result<?> queryWorkState(QzqmCheckInfo qzqmCheckInfo,
									HttpServletRequest req) {
		 return mesWorkService.queryInfo(qzqmCheckInfo.getWorkCode());
	 }

	 /**
	  * ??????mes??????
	  *
	  * @return
	  */
	 @AutoLog(value = "qzqm_check_info-????????????")
	 @ApiOperation(value="qzqm_check_info-????????????", notes="qzqm_check_info-????????????")
	 @GetMapping(value = "/queryByWorkCode")
	 public Result<?> queryByWorkCode(String workCode) {
		 List<QzqmCheckInfo> list = qzqmCheckInfoService.list(new LambdaQueryWrapper<QzqmCheckInfo>().eq(QzqmCheckInfo::getWorkCode, workCode)
				 .eq(QzqmCheckInfo::getIsDeleted, false));
		 if(CollectionUtils.isEmpty(list)) {
			 return Result.error("?????????????????????" + workCode + "?????????????????????");
		 }
		 return Result.OK(list.get(0));
	 }

	/**
	 * ??????mes??????
	 *
	 * @param userId
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-????????????")
	@ApiOperation(value="qzqm_check_info-????????????", notes="qzqm_check_info-????????????")
	@GetMapping(value = "/queryMesUser")
	public Result<?> queryMesUser(String userId) {
		MesUserInfoResponse.MesUser mesUser = mesUserService.getUserInfoByCode(userId);
		if (null == mesUser) {
			return Result.error("?????????" + userId + "?????????????????????");
		}
		return Result.OK(mesUser);
	}

	@AutoLog(value = "qzqm_check_info-?????????")
	@ApiOperation(value="qzqm_check_info-?????????", notes="qzqm_check_info-?????????")
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

	@AutoLog(value = "qzqm_check_info-????????????")
	@ApiOperation(value = "qzqm_check_info-????????????", notes = "qzqm_check_info-????????????")
	@GetMapping(value = "/summary")
	public SummaryVO summary(@RequestParam(required = false, defaultValue = "1") Integer type) {
		return qzqmCheckInfoService.summary(type);
	}

	@AutoLog(value = "qzqm_check_info-??????????????????")
	@ApiOperation(value = "qzqm_check_info-??????????????????", notes = "qzqm_check_info-??????????????????")
	@GetMapping(value = "/passRate")
	public List<PassRateDTO> passRate() {
		return qzqmCheckInfoService.passRate();
	}

	@AutoLog(value = "qzqm_check_info-???????????????")
	@ApiOperation(value = "qzqm_check_info-???????????????", notes = "qzqm_check_info-???????????????")
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
