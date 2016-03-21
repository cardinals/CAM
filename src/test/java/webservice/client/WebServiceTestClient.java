package webservice.client;

import java.util.HashMap;

public class WebServiceTestClient {
	public static void main(String[] args) {
		String url = "http://localhost:54918/WebService/CAMServices.asmx"; 
		String xmlns = "http://www.e-bao.cn/"; 
		String method= "CompanyVerify";
				
		String result = "";

		SoapObject soapobj = new SoapObject(
				xmlns, "", "");
		soapobj.setWsURL(url);//
		soapobj.setSoapAction(xmlns);

		soapobj.setMethod(method);
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
		
		soapobj.setParams(pams);
		try {
			result = soapobj.sendRequest();
			System.out.println(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
