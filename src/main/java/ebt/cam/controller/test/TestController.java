package ebt.cam.controller.test;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import ebt.cam.entity.test.TestEntity;
import ebt.cam.service.test.TestServiceI;

/**   
 * @Title: Controller
 * @Description: 测试
 * @author zhangdaihao
 * @date 2016-02-29 17:49:03
 * @version V1.0   
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/testController")
public class TestController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TestController.class);

	@Autowired
	private TestServiceI testService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 测试列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "test")
	public ModelAndView list(HttpServletRequest request) {
		return new ModelAndView("ebt/cam/test/testList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(TestEntity test,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TestEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, test, request.getParameterMap());
		this.testService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除测试
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TestEntity test, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		test = systemService.getEntity(TestEntity.class, test.getId());
		message = "测试删除成功";
		testService.delete(test);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加测试
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TestEntity test, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(test.getId())) {
			message = "测试更新成功";
			TestEntity t = testService.get(TestEntity.class, test.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(test, t);
				testService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "测试更新失败";
			}
		} else {
			message = "测试添加成功";
			testService.save(test);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 测试列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TestEntity test, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(test.getId())) {
			test = testService.getEntity(TestEntity.class, test.getId());
			req.setAttribute("testPage", test);
		}
		return new ModelAndView("ebt/cam/test/test");
	}
}
