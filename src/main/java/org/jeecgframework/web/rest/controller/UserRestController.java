package org.jeecgframework.web.rest.controller;

import java.net.URI;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.UserService;

import org.jeecgframework.core.beanvalidator.BeanValidators;
import org.jeecgframework.core.constant.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * TSUser的Restful API的Controller. 请求路径 rest/user
 * 
 * @author liuht
 */
@Controller
@RequestMapping(value = "/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Autowired
	private Validator validator;

<<<<<<< .mine
	//get User 列表     路径  /rest/user
||||||| .r83
=======
	/**
	 * 访问地址：http://localhost:8080/jeecg/rest/user
	 * @return
	 */
>>>>>>> .r85
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<TSUser> list() {
		List<TSUser> listUsers = userService.getList(TSUser.class);
		return listUsers;
	}

<<<<<<< .mine
	//get User 列表     路径  /rest/user/id  
||||||| .r83
=======
	/**
	 * 访问地址：http://localhost:8080/jeecg/rest/user/{id}
	 * @param id
	 * @return
	 */
>>>>>>> .r85
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> get(@PathVariable("id") String id) {
		TSUser user = userService.get(TSUser.class, id);
		if (user == null) {
			return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Object>(user, HttpStatus.OK);
	}

	//post 保存用户  
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> create(@RequestBody TSUser user, UriComponentsBuilder uriBuilder) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TSUser>> failures = validator.validate(user);
		if (!failures.isEmpty()) {
			return new ResponseEntity<Object>(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}
		// 保存用户
		userService.save(user);
		// 按照Restful风格约定，创建指向新任务的url, 也可以直接返回id或对象.
		String id = user.getId();
		URI uri = uriBuilder.path("/rest/user/" + id).build().toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uri);
		return new ResponseEntity(headers, HttpStatus.CREATED);
	}

	//更新用户
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> update(@RequestBody TSUser user) {
		// 调用JSR303 Bean Validator进行校验，如果出错返回含400错误码及json格式的错误信息.
		Set<ConstraintViolation<TSUser>> failures = validator.validate(user);
		if (!failures.isEmpty()) {
			return new ResponseEntity<Object>(BeanValidators.extractPropertyAndMessage(failures), HttpStatus.BAD_REQUEST);
		}
		// 保存
		userService.saveOrUpdate(user);
		// 按Restful约定，返回204状态码, 无内容. 也可以返回200状态码.
		return new ResponseEntity<Object>(HttpStatus.NO_CONTENT);
	}

	//删除用户
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)

	public void delete(@PathVariable("id") String id) {
		//userService.deleteEntityById(TSUser.class, id);	
		TSUser user = userService.get(TSUser.class, id);
		List<TSRoleUser> roleUser = userService.findByProperty(TSRoleUser.class, "TSUser.id", id);
		if (!user.getStatus().equals(Globals.User_ADMIN)) {
			if (roleUser.size()>0) {
				// 删除用户时先删除用户和角色关系表
				// 同步删除用户角色关联表
				List<TSRoleUser> roleUserList = userService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
				if (roleUserList.size() >= 1) {
					for (TSRoleUser tRoleUser : roleUserList) {
						userService.delete(tRoleUser);
					}
				}
				userService.executeSql("delete from t_s_user_org where user_id=?", id); // 删除 用户-机构 数据
                userService.delete(user);				
			} else {
				userService.delete(user);
			}
		} 
	}
}
