package webservice.client;

import java.util.HashMap;

import org.jeecgframework.core.util.WebServiceUtil;

import net.sf.json.JSONObject;

public class WebServiceTestClient {
	public static void main(String[] args) {
		String url = "http://localhost:54918/WebService/CAMServices.asmx"; 
		String xmlns = "http://www.e-bao.cn/"; 
		String method= "CompanyVerify";
				
		String result = "";

		HashMap<String, String> pams = new HashMap<String, String>();

		String CompanyCode = "abc1201";
		String UserName = "admin";
		String Password = "123456";
		String SoapHeaderUser = "CASClient";
		String SoapHeaderSign = "adbb683698744f8643d144091a71e040";
		
		pams.put("CompanyCode", CompanyCode);
		pams.put("UserName", UserName);
		pams.put("Password", Password);
		pams.put("SoapHeaderUser", SoapHeaderUser);
		pams.put("SoapHeaderSign", SoapHeaderSign);
		
		result = WebServiceUtil.SoapWebservice(url, xmlns, method, pams, "", "");
		JSONObject jsonObject = JSONObject.fromObject(result);
		
		System.out.println(jsonObject.get("CorpCompanyName").toString());		
	}
	
}