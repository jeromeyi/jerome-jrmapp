/**
 * JaxRpcHelloWorldService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Feb 20, 2006 (03:32:25 GMT+00:00) WSDL2Java emitter.
 */

package localhost.jrmApp.services.HelloWorld;

public interface JaxRpcHelloWorldService extends javax.xml.rpc.Service {
    public java.lang.String getHelloWorldAddress();

    public localhost.jrmApp.services.HelloWorld.JaxRpcHelloWorld getHelloWorld() throws javax.xml.rpc.ServiceException;

    public localhost.jrmApp.services.HelloWorld.JaxRpcHelloWorld getHelloWorld(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
