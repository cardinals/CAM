package ebt.cam.service.test;

import org.springframework.stereotype.Repository;

import ebt.cam.entity.test.Resource;


@Repository
public class ResourceService {

	/**
	 * 
	 * 根据id查询 resource
	 * @param id
	 * @return 
	 * @return Resource    
	 * author: Heweipo
	 */
	public Resource getResource(String id){
//		StringUtils.
		return ResourceRepository.getResourceById(id)==null?new Resource():ResourceRepository.getResourceById(id);
	}

	/**
	 * 
	 * <p>resource存储
	 * @param resource 
	 * @return void    
	 * author: Heweipo
	 */
	public void insertResource(Resource resource){
		ResourceRepository.insertResource(resource);
	}
	
	/**
	 * 
	 * <p>更新 resource
	 * @param resource 
	 * @return void    
	 * author: Heweipo
	 */
	public void updateResource(Resource resource){
		Resource tem = new Resource();
		if(resource.getId()==null || "".equals(resource.getId())){
			return;
		}
		tem.setId(resource.getId());
		
		if(resource.getName()!=null || !"".equals(resource.getName())){
			tem.setName(resource.getName());
		}
		
		if(resource.getNumber()!=null || !"".equals(resource.getNumber())){
			tem.setNumber(resource.getNumber());
		}
			
		ResourceRepository.updateResource(tem);
	}
	
	/**
	 * 
	 * <p>删除id对应的resource
	 * @param id 
	 * @return void    
	 * author: Heweipo
	 */
	public void deleteResource(String id ){
		ResourceRepository.deleteResource(id);
	}
	
}

