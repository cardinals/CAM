package org.jeecgframework.web.system.pojo;

/**
 * 类 名 称： CorpCompanyEntity
 * 类 描 述： 合作公司验证接口对象类
 * 
 */

public class CorpCompanyEntity implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1740030675008151234L;
	private String result;
	
	private String AdminID;
	
	private String CorpCompanyID;
	
	private String CorpCompanyName;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getAdminID() {
		return AdminID;
	}
	public void setAdminID(String adminID) {
		AdminID = adminID;
	}
	public String getCorpCompanyID() {
		return CorpCompanyID;
	}
	public void setCorpCompanyID(String corpCompanyID) {
		CorpCompanyID = corpCompanyID;
	}
	public String getCorpCompanyName() {
		return CorpCompanyName;
	}
	public void setCorpCompanyName(String corpCompanyName) {
		CorpCompanyName = corpCompanyName;
	}
	
}
