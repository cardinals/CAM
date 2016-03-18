package webservice.client;

import java.util.HashMap;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import webservice.cn.ebao.blog.BlogSoap;


public class WebServiceTestClient {
	public static void main1(String[] args) {
		String address = "http://blog.e-baotong.cn/ws/blog.asmx"; //此处最好用系统参数
		JaxWsProxyFactoryBean bean = new JaxWsProxyFactoryBean();
		bean.setAddress(address);
		bean.setServiceClass(BlogSoap.class);
		BlogSoap ws = (BlogSoap) bean.create();
		System.out.println(ws.helloWorld());
	}
	
	public static void main(String[] args) {
		String url = "http://uacserver.e-bao.cn:8086/UACService.asmx"; 
		String xmlns = "http://www.songlink.uac.cn/"; 
		String method= "GetUserKey";
		
		String user = "18363920690";
		String pwd = "12345678";
		
		String result = "";

		SoapObject soapobj = new SoapObject(
				xmlns, "", "");
		soapobj.setWsURL(url);//
		soapobj.setSoapAction(xmlns);

		soapobj.setMethod(method);
		HashMap<String, String> pams = new HashMap<String, String>();

		pams.put("user", user);
		pams.put("password", pwd);
		soapobj.setParams(pams);
		try {
			result = soapobj.sendRequest();
			System.out.println(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
