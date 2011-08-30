package com.jrmapp.jws.client.axis2;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.jrmapp.pojo.HouseType;
import com.jrmapp.pojo.User;

public class TestSpringClinet{
private final static String TARGET_NAMESPACE = "http://service.jrmapp.com";
private final static String  PREFIX = "hw";
private final static String  USER_SERVICE_URL =
    "http://localhost/jrmApp/services/jwsUserService";
private final static String  HOUSETYPE_SERVICE_URL =
    "http://localhost/jrmApp/services/jwsHouseTypeService";


public static void main(String[] args) throws AxisFault {
   
    RPCServiceClient sender = initRpcClient(USER_SERVICE_URL);
    findByIdObject(sender);
/*	RPCServiceClient sender = initRpcClient(HOUSETYPE_SERVICE_URL);
	findHouseTypeById(sender);*/

}


public static RPCServiceClient initRpcClient(String serviceUrl) throws AxisFault{
      RPCServiceClient sender = new RPCServiceClient();
     
      Options options = sender.getOptions();         
     
      EndpointReference targetEPR = new EndpointReference(serviceUrl);
      options.setTo(targetEPR);
     
      return sender;
}


public static void findById(RPCServiceClient sender) throws AxisFault{
      // There are serveral import files. Choose "javax.xml.namespace.QName" to import
      QName qname1 = new QName(TARGET_NAMESPACE, "findByIdPlain");
      Object[] args1 = new Object[] {1 };  // 这是针对方法参数的
      Class[] types1 = new Class[] { String.class };  //这是针对返值类型的
      Object[] response1 = sender.invokeBlocking(qname1, args1, types1);

      String u1 = (String) response1[0];
      if (u1 == null) {
          System.out.println("u1 was null");
          return;
      }
    
      System.out.println(u1);
}


public static void findAllString(RPCServiceClient sender) throws AxisFault{
      QName qname2 = new QName(TARGET_NAMESPACE, "findAllString");
      Object[] args2 = new Object[] {};  // 这是针对方法参数的
      Class[] types2 = new Class[] { String[].class }; 
                        //这是针对返值类型的,因为返值是String数组,所以这里是String[].class
      Object[] response2 = sender.invokeBlocking(qname2, args2, types2);

      String[] u2 = (String[]) response2[0];
                       // 即使返值是数组,也是response2数组的元素,"数组元素还是数组"
      for(String temp:u2)
          System.out.println(temp);
}


public static void findAllList(RPCServiceClient sender) throws AxisFault{
      QName qname3 = new QName(TARGET_NAMESPACE, "findAllList");
      Object[] args3 = new Object[] {};  // 这是针对方法参数的
      Class[] types3 = new Class[] { String[].class }; 
               //这是针对返值类型的,因为返值是List<String>,这里当做String[].class处理
      Object[] response3 = sender.invokeBlocking(qname3, args3, types3);

      String[] u3 = (String[]) response3[0];
      for(String temp:u3)
          System.out.println(temp);
}   


public static void findByIdObject(RPCServiceClient serviceClient) throws AxisFault{
      // There are serveral import files. Choose "javax.xml.namespace.QName" to import
      //QName qname1 = new QName("http://test.machome.com", "findById","ns1");
      QName qname1 = new QName(TARGET_NAMESPACE, "getUserForJWS");
      Object[] args1 = new Object[] {1 };  // 这是针对方法参数的
      Class[] types1 = new Class[] { User.class };  //这是针对返值类型的
      Object[] response1 = serviceClient.invokeBlocking(qname1, args1, types1);

      User u1 = (User) response1[0];
      if (u1 == null) {
          System.out.println("u1 was null");
          return;
      }
    
      System.out.println(u1.getId()+":"+u1.getName()+":"+u1.getName());
}

public static void findHouseTypeById(RPCServiceClient serviceClient) throws AxisFault{
    // There are serveral import files. Choose "javax.xml.namespace.QName" to import
    //QName qname1 = new QName("http://test.machome.com", "findById","ns1");
    QName qname1 = new QName(TARGET_NAMESPACE, "getHouseTypeForJWS");
    Object[] args1 = new Object[] {1 };  // 这是针对方法参数的
    Class[] types1 = new Class[] { HouseType.class };  //这是针对返值类型的
    Object[] response1 = serviceClient.invokeBlocking(qname1, args1, types1);

    HouseType ht = (HouseType) response1[0];
    if (ht == null) {
        System.out.println("ht was null");
        return;
    }
  
    System.out.println(ht.getHousetypename());
}

/*public static void findAllObject(RPCServiceClient serviceClient) throws AxisFault{
      QName qname3 = new QName(TARGET_NAMESPACE, "findAllObject");
      Object[] args3 = new Object[] {};  // 这是针对方法参数的
      Class[] types3 = new Class[] { Student1[].class }; 

      Object[] response3 = serviceClient.invokeBlocking(qname3, args3, types3);
      Student1[] u3 = (Student1[]) response3[0];

      for(Student1 temp:u3)
          System.out.println(temp.getId()+":"+temp.getName()+":"+temp.getAge());
}  */ 

} 