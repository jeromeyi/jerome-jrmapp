package com.jrmapp.webservice.axis;

import org.apache.axis.AxisFault;
import org.apache.axis.MessageContext;
import org.apache.axis.handlers.soap.SOAPService;
import org.apache.axis.providers.java.RPCProvider;

public class SpringBeanRPCProvider extends RPCProvider {

    private final SpringBeanProvider provider = new SpringBeanProvider();

    /**
     * @see org.apache.axis.providers.java.JavaProvider#makeNewServiceObject(org.apache.axis.MessageContext, java.lang.String)
     */
    protected Object makeNewServiceObject(MessageContext msgContext, String clsName)
        throws Exception {

        return provider.getBean(msgContext, clsName);
    }

    /**
     * @see org.apache.axis.providers.java.JavaProvider#getServiceClass(java.lang.String, org.apache.axis.handlers.soap.SOAPService, org.apache.axis.MessageContext)
     */
    protected Class getServiceClass(String clsName, SOAPService service, MessageContext msgContext)
        throws AxisFault {

        return provider.getBeanClass(msgContext, clsName);
    }

    /**
     * @see org.apache.axis.providers.java.JavaProvider#getServiceClassNameOptionName()
     */
    protected String getServiceClassNameOptionName() {
        return SpringBeanProvider.BEAN_OPTION_NAME;
    }
}
