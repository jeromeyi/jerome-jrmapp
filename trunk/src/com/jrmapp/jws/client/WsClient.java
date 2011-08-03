package com.jrmapp.jws.client;

import javax.xml.namespace.QName;

import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;

import com.jrmapp.pojo.User;

/**
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：2011-8-3 下午03:13:53
 * @类说明
 */
public class WsClient {
	private RPCServiceClient serviceClient;
	private Options options;
	private EndpointReference targetEPR;

	public WsClient(String endpoint) throws AxisFault {
		serviceClient = new RPCServiceClient();
		options = serviceClient.getOptions();
		targetEPR = new EndpointReference(endpoint);
		options.setTo(targetEPR);
	}

	public Object[] invokeOp(String targetNamespace, String opName,
			Object[] opArgs, Class<?>[] opReturnType) throws AxisFault,
			ClassNotFoundException {
		// 设定操作的名称
		QName opQName = new QName(targetNamespace, opName);
		// 设定返回值

		// Class<?>[] opReturn = new Class[] { opReturnType };

		// 操作需要传入的参数已经在参数中给定，这里直接传入方法中调用
		return serviceClient.invokeBlocking(opQName, opArgs, opReturnType);
	}

	/**
	 * @param args
	 * @throws AxisFault
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws AxisFault,
			ClassNotFoundException {
		// TODO Auto-generated method stub
		final String endPointReference = "http://localhost/jrmApp/services/jwsUserService";
		final String targetNamespace = "http://service.jrmapp.com";
		WsClient client = new WsClient(endPointReference);

		String opName = "getUser";
		Object[] opArgs = new Object[] { 1L };
		//Class<?>[] opReturnType = new Class[] { String[].class };
		Class<?>[] opReturnType = new Class[] { User.class };
		Object[] response = client.invokeOp(targetNamespace, opName, opArgs,
				opReturnType);
		System.out.println(((String[]) response[0])[0]);
	}
}
