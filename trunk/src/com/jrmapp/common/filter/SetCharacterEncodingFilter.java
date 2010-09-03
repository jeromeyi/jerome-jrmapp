package com.jrmapp.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.jrmapp.constants.AppConstants;

/**
 * 
 * @author 谢毅(Jerome) E-mail:xieyi@kebao.cn
 * @version 创建时间：Jul 28, 2010 1:38:15 PM
 * @类说明
 */
public class SetCharacterEncodingFilter implements Filter {

  
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
    FilterChain chain)throws IOException, ServletException {

    request.setCharacterEncoding(AppConstants.CHARTSET_UTF8);
    response.setCharacterEncoding(AppConstants.CHARTSET_UTF8);

    //transfer to next filter
    chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
