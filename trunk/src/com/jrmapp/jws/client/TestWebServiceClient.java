package com.jrmapp.jws.client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class TestWebServiceClient {
	 /**
     * @param args
     */
   public static void main(String[] args) {
       // TODO Auto-generated method stub
       try {
/*         String wsdlUrl = "http://localhost/jrmApp/services/HelloWorld?wsdl";
         String nameSpaceUri = "http://localhost/jrmApp/services/HelloWorld";
         // 创建调用对象
         Service service = new Service();
         Call call = null;
         call = (Call) service.createCall();
         // 调用sayHello
         System.out.println(">>>getMessage");
         call.setOperationName(new QName(nameSpaceUri, "getMessage"));
         call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));
         String ret = (String) call.invoke(new Object[] { "ABC" });
         System.out.println("return value is " + ret);*/
    	 String url="http://localhost/jrmApp/services/HelloWorld";
    	 Service service = new Service();
         Call call = null;
         call = (Call) service.createCall();
         call.setTargetEndpointAddress(url);
         call.setOperationName(new QName(url, "getMessage"));
         String ret = (String) call.invoke(new Object[] { "ABC" });
         System.out.println("return value is " + ret);
         
         
         String url1="http://localhost/jrmApp/services/userServiceRpc";
         Service service1 = new Service();
         Call call1 = null;
         call1 = (Call) service1.createCall();
         call1.getMessageContext().setUsername("user1");//axis中的用户名。
         call1.getMessageContext().setPassword("pass1");//密码
         call1.setTargetEndpointAddress(url1);       
         call1.setOperationName(new QName(url1, "getUserName"));
         String ret3 = (String) call1.invoke(new Object[] { 1L });
         System.out.println("return value is " + ret3);
       } catch (Exception e) {
         e.printStackTrace();
       }
   }
   
   
   public void test() throws ServiceException, MalformedURLException, RemoteException{
	   String wsdlUrl = "http://127.0.0.1:8080/WS1.4/services/axisJWS?wsdl";   
       String soapActionURI = "http://127.0.0.1:8080/WS1.4/services/axisJWS?wsdl";   
       // 创建调用对象   
       Service service = new Service();   
       Call call = (Call) service.createCall();   
       // 调用getUserInfo   
       System.out.println(">>>调用开始: ");   
       //xmlns   
       call.setOperationName(new QName("http://127.0.0.1:8080/WS1.4/services/axisJWS?wsdl", "method"));   
       call.setTargetEndpointAddress(new java.net.URL(wsdlUrl));   
          
       //parameter & return   
       call.addParameter("userAuth", org.apache.axis.encoding.XMLType.XSD_STRING,   
               javax.xml.rpc.ParameterMode.IN);   
       call.addParameter("xml", org.apache.axis.encoding.XMLType.XSD_STRING,   
               javax.xml.rpc.ParameterMode.IN);   
       call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);   
          
       //set soap action   
       call.setUseSOAPAction(true);   
       call.setSOAPActionURI(soapActionURI);   
       //call   
       String ret = (String) call.invoke(new Object[] { "参数一" , "参数二" });   
       System.out.println("返回值: " + ret);   
   }
}

