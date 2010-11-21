package com.jrmapp.jws.client;

import javax.xml.namespace.QName;

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
         call1.getMessageContext().setUsername("user3");//axis中的用户名。
         call1.getMessageContext().setPassword("pass3");//密码
         call1.setTargetEndpointAddress(url1);       
         call1.setOperationName(new QName(url1, "getUserName"));
         String ret3 = (String) call1.invoke(new Object[] { 1L });
         System.out.println("return value is " + ret3);
       } catch (Exception e) {
         e.printStackTrace();
       }
   }
}

