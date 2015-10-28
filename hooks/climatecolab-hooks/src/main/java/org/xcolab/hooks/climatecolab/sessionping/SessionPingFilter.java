package org.xcolab.hooks.climatecolab.sessionping;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SessionPingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        
        // just extend the session if we have httpservletrequest with an active session
        if (request instanceof HttpServletRequest) {
            ((HttpServletRequest) request).getSession(false);
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            httpResponse.getWriter().close();        
        }
        else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        
    }
    

}
