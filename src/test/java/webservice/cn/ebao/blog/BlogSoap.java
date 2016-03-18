package webservice.cn.ebao.blog;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.0.8
 * 2016-03-17T19:10:34.725+08:00
 * Generated source version: 3.0.8
 * 
 */
@WebService(targetNamespace = "http://www.e-bao.cn/", name = "blogSoap")
@XmlSeeAlso({ObjectFactory.class})
public interface BlogSoap {

    @WebResult(name = "GetBlogResult", targetNamespace = "http://www.e-bao.cn/")
    @RequestWrapper(localName = "GetBlog", targetNamespace = "http://www.e-bao.cn/", className = "cn.e_bao.GetBlog")
    @WebMethod(operationName = "GetBlog", action = "http://www.e-bao.cn/GetBlog")
    @ResponseWrapper(localName = "GetBlogResponse", targetNamespace = "http://www.e-bao.cn/", className = "cn.e_bao.GetBlogResponse")
    public java.lang.String getBlog(
        @WebParam(name = "flag", targetNamespace = "http://www.e-bao.cn/")
        java.lang.String flag
    );

    @WebResult(name = "HelloWorldResult", targetNamespace = "http://www.e-bao.cn/")
    @RequestWrapper(localName = "HelloWorld", targetNamespace = "http://www.e-bao.cn/", className = "cn.e_bao.HelloWorld")
    @WebMethod(operationName = "HelloWorld", action = "http://www.e-bao.cn/HelloWorld")
    @ResponseWrapper(localName = "HelloWorldResponse", targetNamespace = "http://www.e-bao.cn/", className = "cn.e_bao.HelloWorldResponse")
    public java.lang.String helloWorld();
}
