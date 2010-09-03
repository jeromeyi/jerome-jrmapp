/**
 * 
 */
package com.jrmapp.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jrmapp.common.util.GenericBean;
import com.jrmapp.common.util.UserSession;



/**
 * 
 * @author 谢毅(Jerome) E-mail:xieyilucky@gmail.com
 * @version 创建时间：Jul 28, 2010 1:53:51 PM
 * @类说明
 */
public class URLLimitFilter implements Filter {
	private static final Log log = LogFactory.getLog(URLLimitFilter.class);
	private String loginURL;
	private String[] urllimit;
	private String[] urllimitAction;


	public void init(FilterConfig config) throws ServletException {
		loginURL = config.getInitParameter("LOGIN.URL");
		urllimit = config.getInitParameter("url-pattern-protected").split(";");
		urllimitAction = config.getInitParameter(
				"url-pattern-protected-action").split(";");
	}

	
	public void doFilter(ServletRequest requestLocal,
			ServletResponse responseLocal, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) requestLocal;
		HttpServletResponse response = (HttpServletResponse) responseLocal;
	
		UserSession userSession = GenericBean.getUserSession(request);

		StringBuffer requestURL = request.getRequestURL();
		if (requestURL != null&&(requestURL.indexOf("loginon.do")>=0||requestURL.indexOf("login.do")>=0||requestURL.indexOf("login.html")>=0)) {
			chain.doFilter(request, response);
			return;
		}
		boolean isLimitPage = false;
		boolean isLimitAction = false;

		for (int i = 0; i < urllimit.length; i++) {
			if (requestURL.indexOf(urllimit[i]) >= 0) {
				isLimitPage = true;
				break;
			}
		}

		for (int i = 0; i < urllimitAction.length; i++) {
			if (requestURL.indexOf(urllimitAction[i]) >= 0) {
				isLimitAction = true;
				break;
			}
		}

		if (!isLimitPage && !isLimitAction) {
			chain.doFilter(request, response);
			return;
		}
        
		if (userSession==null){
			response.sendRedirect( loginURL);
			return;
			
		}
		/*
		else{
			request.getSession().setAttribute(AppConstants.INTMBSESSION_KEY,userSession);
		}
		*/
		
		chain.doFilter(request, response);
	}

	/**
	 * 
	 */
	public void destroy() {
	}
}
