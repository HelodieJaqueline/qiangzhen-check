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
import org.jeecg.modules.check.dto.MesWorkStateResponse;
import org.jeecg.modules.check.entity.QzqmCheckInfo;
import org.jeecg.modules.check.service.IQzqmCheckInfoService;
import org.jeecg.modules.check.service.mes.MesWorkService;
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
	
	/**
	 * 分页列表查询
	 *
	 * @param qzqmCheckInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "qzqm_check_info-分页列表查询")
	@ApiOperation(value="qzqm_check_info-分页列表查询", notes="qzqm_check_info-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(QzqmCheckInfo qzqmCheckInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<QzqmCheckInfo> queryWrapper = QueryGenerator.initQueryWrapper(qzqmCheckInfo, req.getParameterMap());
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
		 MesWorkStateResponse.WorkState workState = mesWorkService.queryInfo();
		 return Result.OK(workState);
	 }

	 /**
	  * 分页列表查询
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
			 return Result.error("未找到对应数据");
		 }
		 return Result.OK(list.get(0));
	 }

}
