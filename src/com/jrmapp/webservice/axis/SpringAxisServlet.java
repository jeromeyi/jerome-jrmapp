package com.jrmapp.webservice.axis;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.axis.transport.http.AxisServlet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.util.StringUtils;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class SpringAxisServlet extends AxisServlet {

    private static final Log LOG = LogFactory.getLog(SpringAxisServlet.class);

    /**
     * Suffix for WebApplicationContext namespaces. If a SpringAxisServlet is
     * given the name "axis" in a context, the namespace used by this instance will
     * resolve to "axis-servlet".
     */
    public static final String DEFAULT_NAMESPACE_SUFFIX = "-servlet";

    /**
     * Default context class for SpringAxisServlet.
     * 
     * @see org.springframework.web.context.support.XmlWebApplicationContext
     */
    public static final Class DEFAULT_CONTEXT_CLASS = XmlWebApplicationContext.class;

    /** Name of the ServletContext attribute for the WebApplicationContext */
    public static final String SERVLET_CONTEXT_ATTRIBUTE = SpringAxisServlet.class.getName()
            + ".CONTEXT";

    /** Custom WebApplicationContext class */
    private Class contextClass = DEFAULT_CONTEXT_CLASS;

    /** Namespace for this servlet */
    private String namespace;

    /** Explicit context config location */
    private String contextConfigLocation;

    /** WebApplicationContext for the SpringAxisServlet */
    private WebApplicationContext webApplicationContext;

    /**
     * Initialise the WebApplicationContext of the SpringAxisServlet.
     * 
     * @see org.apache.axis.transport.http.AxisServlet#init()
     */
    public void init() throws ServletException {
        super.init();

        long startTime = System.currentTimeMillis();
        if (LOG.isDebugEnabled()) {
            LOG.debug("Servlet '" + getServletName() + "' init");
        }

        setContextClassName(getServletConfig().getInitParameter("contextClassName"));
        setContextConfigLocation(getServletConfig().getInitParameter("contextConfigLocation"));
        setNamespace(getServletConfig().getInitParameter("namespace"));

        try {
            this.webApplicationContext = initWebApplicationContext();
        } catch (BeansException ex) {
            LOG.error("Context initialization failed", ex);
            throw ex;
        }

        if (LOG.isDebugEnabled()) {
            long elapsedTime = System.currentTimeMillis() - startTime;
            LOG.debug("Servlet '"
                    + this.getServletName()
                    + "' init completed in "
                    + elapsedTime
                    + " ms");
        }
    }

    /**
     * Close the WebApplicationContext of the SpringAxisServlet.
     * 
     * @see org.apache.axis.transport.http.AxisServletBase#destroy()
     */
    public void destroy() {
        super.destroy();

        log("Closing WebApplicationContext of servlet '" + getServletName() + "'");
        if (this.webApplicationContext instanceof ConfigurableApplicationContext) {
            ((ConfigurableApplicationContext) this.webApplicationContext).close();
        }
    }

    /**
     * Set a custom context class by name. This class must be of type WebApplicationContext,
     * when using the default SpringAxisServlet implementation, the context class
     * must also implement ConfigurableWebApplicationContext.
     * 
     * @see #createWebApplicationContext
     */
    public void setContextClassName(String contextClassName) throws IllegalArgumentException {
        if (contextClassName != null) {
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                this.contextClass = Class.forName(contextClassName, true, loader);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e.getMessage());
            }
        }
    }

    /**
     * Set a custom context class. This class must be of type WebApplicationContext,
     * when using the default SpringAxisServlet implementation, the context class
     * must also implement ConfigurableWebApplicationContext.
     * 
     * @see #createWebApplicationContext
     */
    public void setContextClass(Class contextClass) {
        this.contextClass = contextClass;
    }

    /**
     * Return the custom context class.
     */
    public Class getContextClass() {
        return contextClass;
    }

    /**
     * Set a custom namespace for the SpringAxisServlet,
     * to be used for building a default context config location.
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Return the namespace for the SpringAxisServlet, falling back to default scheme if
     * no custom namespace was set: e.g. "axis-servlet" for a servlet named "axis".
     */
    public String getNamespace() {
        if (namespace != null) {
            return namespace;
        }
        return getServletName() + DEFAULT_NAMESPACE_SUFFIX;
    }

    /**
     * Set the context config location explicitly, instead of relying on the default
     * location built from the namespace. This location string can consist of
     * multiple locations separated by any number of commas and spaces.
     */
    public void setContextConfigLocation(String contextConfigLocation) {
        this.contextConfigLocation = contextConfigLocation;
    }

    /**
     * Return the explicit context config location, if any.
     */
    public String getContextConfigLocation() {
        return contextConfigLocation;
    }

    /**
     * Initialize and publish the WebApplicationContext for the SpringAxisServlet.
     * Delegates to createWebApplicationContext for actual creation.
     * Can be overridden in subclasses.
     * 
     * @throws org.springframework.beans.BeansException 
     *      if the context couldn't be initialized
     * 
     * @see #createWebApplicationContext
     */
    protected WebApplicationContext initWebApplicationContext() throws BeansException {
        log("Initializing WebApplicationContext for servlet '" + this.getServletName() + "'");

        ServletContext ctx = getServletContext();
        WebApplicationContext parent = WebApplicationContextUtils.getWebApplicationContext(ctx);

        WebApplicationContext wac = createWebApplicationContext(parent);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Using context class '"
                    + wac.getClass().getName()
                    + "' for servlet '"
                    + getServletName()
                    + "'");
        }

        // publish the context as a servlet context attribute
        ctx.setAttribute(SERVLET_CONTEXT_ATTRIBUTE, wac);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Published WebApplicationContext of servlet '"
                    + getServletName()
                    + "' as ServletContext attribute with name ["
                    + SERVLET_CONTEXT_ATTRIBUTE
                    + "]");
        }
        return wac;
    }

    /**
     * Instantiate the WebApplicationContext for the SpringAxisServlet, either a default
     * XmlWebApplicationContext or a custom context class if set. This implementation
     * expects custom contexts to implement ConfigurableWebApplicationContext.
     * Can be overridden in subclasses.
     * 
     * @throws org.springframework.beans.BeansException 
     *      if the context couldn't be initialized
     * 
     * @see #setContextClass
     * 
     * @see org.springframework.web.context.support.XmlWebApplicationContext
     */
    protected WebApplicationContext createWebApplicationContext(WebApplicationContext parent)
        throws BeansException {

        if (LOG.isDebugEnabled()) {
            LOG.debug("Servlet with name '"
                    + getServletName()
                    + "' will try to create custom WebApplicationContext context of class '"
                    + getContextClass().getName()
                    + "'"
                    + " using parent context ["
                    + parent
                    + "]");
        }
        if (!ConfigurableWebApplicationContext.class.isAssignableFrom(getContextClass())) {
            throw new ApplicationContextException(
                "Fatal initialization error in servlet with name '"
                        + getServletName()
                        + "': custom WebApplicationContext class ["
                        + getContextClass().getName()
                        + "] is not of type ConfigurableWebApplicationContext");
        }

        ConfigurableWebApplicationContext wac = createContextInstance();
        wac.setParent(parent);
        wac.setServletContext(getServletContext());
        wac.setNamespace(getNamespace());

        if (this.contextConfigLocation != null) {
            wac.setConfigLocations(StringUtils.tokenizeToStringArray(
                this.contextConfigLocation,
                ConfigurableWebApplicationContext.CONFIG_LOCATION_DELIMITERS,
                true,
                true));
        }
        wac.refresh();
        return wac;
    }

    private ConfigurableWebApplicationContext createContextInstance() {
        return (ConfigurableWebApplicationContext) BeanUtils.instantiateClass(getContextClass());
    }

    /**
     * Return the SpringAxisServlet's WebApplicationContext.
     */
    public final WebApplicationContext getWebApplicationContext() {
        return webApplicationContext;
    }
}