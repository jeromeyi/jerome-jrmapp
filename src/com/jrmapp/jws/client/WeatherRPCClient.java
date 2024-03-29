package com.jrmapp.jws.client;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.jrmapp.pojo.User;

public class WeatherRPCClient {
	public static void main(String[] args1) throws AxisFault {  
        
        EndpointReference targetEPR = new EndpointReference("http://localhost/jrmApp/services/jwsUserService");  
        RPCServiceClient serviceClient = new RPCServiceClient();  
        Options options = serviceClient.getOptions();  
        options.setTo(targetEPR);  
  
        QName opGetWeather = new QName("http://service.jrmapp.com", "getUserName");  
        Object[] opGetWeatherArgs = new Object[] { 1L};  
        Class[] returnTypes = new Class[] { String.class };  
       Object[] response = serviceClient.invokeBlocking(opGetWeather,opGetWeatherArgs, returnTypes);  
  System.out.println(response[0]);

  

  QName opGetUser = new QName("http://service.jrmapp.com", "getUser");  
  Object[] opGetUserArgs = new Object[] { 1L};  
  Class[] returnUserTypes = new Class[] { User.class };  
 Object[] responseUser = serviceClient.invokeBlocking(opGetUser,opGetUserArgs, returnUserTypes);  
      User result = (User) responseUser[0];  
  if (result == null) {  
      System.out.println("user didn't initialize!");  
  }else{  
      System.out.println();  
      System.out.println("Id               : " + result.getId());  
      System.out.println("Name                  : " + result.getName());  
  }  
  
    }  
}
