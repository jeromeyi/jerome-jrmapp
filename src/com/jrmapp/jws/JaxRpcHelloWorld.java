package com.jrmapp.jws;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.springframework.remoting.jaxrpc.ServletEndpointSupport;

public class JaxRpcHelloWorld extends ServletEndpointSupport implements IHelloWorld{
    private IHelloWorld helloWorld;
    
    protected void onInit() throws ServiceException {
        //在Spring容器中获取Bean的实例
          helloWorld = (IHelloWorld) getApplicationContext().getBean(
                "helloWorld");
        }
      
    public String getMessage(String name) throws RemoteException {
        //执行Bean中的相同的方法
          return helloWorld.getMessage(name);
        }
    }
