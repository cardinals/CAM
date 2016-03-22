package org.jeecgframework.core.util;

import java.util.HashMap;

public class WebServiceUtil {
	 /**
	  * 
	  * @param url服务地址
	  * @param xmlns命名空间
	  * @param method方法名
	  * @param pams参数集合
	  * @param SoapHeaderUser
	  * @param SoapHeaderSign
	  */
	public static String SoapWebservice(String url,String xmlns,String method,HashMap<String, String> pams,String SoapHeaderUser,String SoapHeaderSign) {
		String result = "";
		SoapObject soapobj = new SoapObject(
				xmlns, "", "");
		soapobj.setWsURL(url);//
		soapobj.setSoapAction(xmlns);
		soapobj.setMethod(method);		
		soapobj.setParams(pams);
		try {
			result = soapobj.sendRequest();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
