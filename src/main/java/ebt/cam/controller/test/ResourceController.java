package ebt.cam.controller.test;


import java.util.List;
import java.util.Map;

import org.jeecgframework.core.util.DynamicDBUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ebt.cam.entity.test.Message;
import ebt.cam.entity.test.Resource;
import ebt.cam.service.test.ResourceService;




@Controller
@RequestMapping("/resource")
public class ResourceController {

	@Autowired
	private ResourceService service;
	
	/**
	 * 
	 * <p>通过id获取资源
	 * 在这里采用@PathVariable注解，同时返回一个String，让SpringMVC自动寻找最合适的视图解析器
	 * 优点是能够找到html视图解析器，但是缺点是，JavaBean转为XML格式，需要在 org.springframework.oxm.jaxb.Jaxb2Marshaller绑定JavaBean
	 * 所以 这里Resource一定要进行绑定
	 * @param id
	 * @param model
	 * @return 
	 * @return String    
	 */
	@RequestMapping(value="/get/{id}" , method=RequestMethod.GET)
	@ResponseBody
	public List<Map<String, Object>> get(@PathVariable("id") String id , ModelMap model){
		model.put("resource", service.getResource(id));
		//动态数据源测试
		final String sql = "select * from person";
		List<Map<String, Object>> listUsers =  DynamicDBUtil.findList("CAM_DB", sql, null);
		return listUsers;
	}
	
	/**
	 * 
	 * <p>存储resource，另外，关于resource的number参数注入很值得注意，如果URL中携带的参数不能转为
	 * Integer类型，那么是无法继续访问这个方法的！！所以，对于JavaBean最好设置与数据库对应的数据类型
	 * 进行类型约束。
	 * 在这里采用@ResponseBody注解，那么返回的对象将会被对应的视图解析器解析，但是不会有html解析器，但是有一个
	 * 优点：javaBean 如果转为 XML 是不需要在spring-servlet 中 org.springframework.oxm.jaxb.Jaxb2Marshaller 绑定的
	 * @param resource
	 * @param model 
	 * @return void    
	 */
	@RequestMapping(value="/put" , method=RequestMethod.PUT)
	public @ResponseBody Message put(Resource resource , ModelMap model){
		Message message = new Message();
		if(resource.getId()==null || "".equals(resource.getId()) || resource.getName()==null || "".equals(resource.getName())){
			message.setMsg("请输入id 和 用户名");
			message.setResult("faile");
			return message;
		}
		service.insertResource(resource);
		message.setMsg("数据已经存储");
		message.setResult("success");
		return message;
	}
	
	/**
	 * 
	 * <p>通过id更新resource
	 * @param resource
	 * @param model
	 * @return 
	 * @return Message    
	 */
	@RequestMapping(value="/post" , method=RequestMethod.POST)
	public @ResponseBody Message post(Resource resource , ModelMap model){
		Message message = new Message();
		if(resource.getId()==null || "".equals(resource.getId())){
			message.setMsg("id不能为空");
			message.setResult("faile");
			return message;
		}
		service.updateResource(resource);
		message.setMsg("数据已经修改");
		message.setResult("success");
		return message;
	}
	
	/**
	 * 
	 * <p>通过id删除资源
	 * @param id
	 * @param model
	 * @return 
	 * @return Message    
	 */
	@RequestMapping(value="/{id}" , method=RequestMethod.DELETE)
	public @ResponseBody Message delete(@PathVariable("id")String id, ModelMap model){
		Message message = new Message();
		service.deleteResource(id);
		message.setMsg("数据已删除");
		message.setResult("success");
		return message;
	}
	
}

