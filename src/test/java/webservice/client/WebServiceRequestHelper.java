//package webservice.client;
//
//import java.io.IOException;
//import java.net.UnknownHostException;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.apache.http.HttpException;
//import org.apache.http.conn.ConnectTimeoutException;
//
//
///***
// * 请求WS帮助类
// * @author ZhanWu.Li
// *
// */
//public class WebServiceRequestHelper {
//	
//	/**
//	 * 发送请求（不需要获取最新的验证码）
//	 * @param params
//	 * @param wsdl
//	 * @param soapAction
//	 * @param method
//	 * @param xmlnS
//	 * @return 返回相应json
//	 * @throws HttpException
//	 * @throws EBTSoapHeaderException
//	 * @throws IOException
//	 * @throws Exception
//	 */
//	public static String sendRequest(Map params,String wsdl,String soapAction,String method,String  xmlnS) throws EBTSoapHeaderException, ConnectTimeoutException ,Exception{
//		//EbtUserInfo user = EBTUserInfoHelper.getUserInfo();
//		
//		return  sendRequest(params,"".userAccount,"",wsdl,soapAction,method,xmlnS,false);
//	}
//	/**
//	 * 发送请求（不需要头部验证）
//	 * @param params
//	 * @param wsdl
//	 * @param soapAction
//	 * @param method
//	 * @param xmlnS
//	 * @return
//	 * @throws EBTSoapHeaderException
//	 * @throws ConnectTimeoutException
//	 * @throws Exception
//	 */
//	public static String sendRequestNotNeedAddOTherParams(Map params,String wsdl,String soapAction,String method,String  xmlnS) throws EBTSoapHeaderException, ConnectTimeoutException,Exception{
//		/*EbtUserInfo user = EBTUserInfoHelper.getUserInfo();
//	
//		return  sendRequest(params,user.userAccount,user.userPassword,wsdl,soapAction,method,xmlnS,false);*/
//		EbtUserInfo user = EBTUserInfoHelper.getUserInfo();
//		if(user.userPrivateKey==null||user.userPrivateKey.length()==0){
//			String keyJson = ProductDownloader.downloadSoapHeaderKey(user.userAccount, user.userPassword);
//			String public_key = EBTUserInfoHelper.getUserkeyValue(keyJson);
//			if (public_key != null
//					&& (user.userPrivateKey == null || !user.userPrivateKey
//							.equals(public_key))) {
//				user.userPrivateKey = public_key;
//				EBTUserInfoHelper.saveUserKEY(user);
//			}
//		}
//		if(user.userPrivateKey==null||user.userPrivateKey.isEmpty()||user.userAccount==null||user.userPrivateKey.isEmpty()){
//			
//			throw  new EBTSoapHeaderException(DownloaderConstants.STR_DOWNLOAD_SOAPHEADER_ERROR);
//			
//			
//		}
//
//		SoapObject soapobjet= new SoapObject(xmlnS,user.userAccount,user.userPrivateKey);
//		soapobjet.setMethod(method);
//		soapobjet.setParams(params);
//		soapobjet.setWsURL(wsdl);
//		soapobjet.setSoapAction(soapAction);
//		return  soapobjet.sendRequest();
//	}
//	/**
//	 * 发送请求 （isNeedGetNewKey是否需要获取最新的公钥）
//	 * @param params
//	 * @param userAccount
//	 * @param pwd
//	 * @param wsdl
//	 * @param soapAction
//	 * @param method
//	 * @param xmlnS
//	 * @return
//	 * @throws HttpException
//	 * @throws EBTSoapHeaderException
//	 * @throws IOException
//	 * @throws Exception
//	 */
//	public static String sendRequest(Map params,String userAccount,String pwd,String wsdl,String soapAction,String method,String  xmlnS,boolean isNeedGetNewKey) throws ConnectTimeoutException,UnknownHostException,Exception{
//		EbtUserInfo user = EBTUserInfoHelper.getUserInfo();
//		
//	
//		
//			if(user.userPrivateKey==null||user.userPrivateKey.length()==0){
//				String keyJson = ProductDownloader.downloadSoapHeaderKey(userAccount, pwd);
//				String public_key = EBTUserInfoHelper.getUserkeyValue(keyJson);
//				if (public_key != null
//						&& (user.userPrivateKey == null || !user.userPrivateKey
//								.equals(public_key))) {
//					user.userPrivateKey = public_key;
//					EBTUserInfoHelper.saveUserKEY(user);
//				}
//			}
//		
//		if(user.userPrivateKey==null||user.userPrivateKey.isEmpty()||user.userAccount==null||user.userPrivateKey.isEmpty()){
//		
//			throw  new EBTSoapHeaderException(DownloaderConstants.STR_DOWNLOAD_SOAPHEADER_ERROR);
//			
//			
//		}
//		params.put("strUserId", user.userId);
//		params.put("strEquipment", EBTGetAdviceInfo.getDeviceInfo());
//		SoapObject soapobjet= new SoapObject(xmlnS,userAccount,user.userPrivateKey);
//		soapobjet.setMethod(method);
//		soapobjet.setParams(params);
//		soapobjet.setWsURL(wsdl);
//		soapobjet.setSoapAction(soapAction);
//		return  soapobjet.sendRequest();
//	}
//	/**
//	 * 发送请求（没有用户信息时候发送请求）
//	 * @param params
//	 * @param userAccount
//	 * @param pwd
//	 * @param wsdl
//	 * @param soapAction
//	 * @param method
//	 * @param xmlnS
//	 * @return
//	 * @throws HttpException
//	 * @throws EBTSoapHeaderException
//	 * @throws IOException
//	 * @throws Exception
//	 */
//	public static String sendRequestByNoAccount(Map params,String wsdl,String soapAction,String method,String  xmlnS) throws EBTSoapHeaderException, ConnectTimeoutException,Exception{
//		
//		
//		String account = EBTGetAdviceInfo.getAppVersionName();
//		String key = EBTGetAdviceInfo.getApkVersionKey();
//		String keyJson = ProductDownloader.downloadSoapHeaderKey(account,key);
//		String public_key=EBTUserInfoHelper.getUserkeyValue(keyJson);
//		params.put("strUserId", "");
//		params.put("strEquipment", EBTGetAdviceInfo.getDeviceInfo());
//	
//		SoapObject soapobjet= new SoapObject(xmlnS,account,public_key);
//		soapobjet.setMethod(method);
//		soapobjet.setParams(params);
//		soapobjet.setWsURL(wsdl);
//		soapobjet.setSoapAction(soapAction);
//	
//		
//		return  soapobjet.sendRequest();
//	}
//
//}
