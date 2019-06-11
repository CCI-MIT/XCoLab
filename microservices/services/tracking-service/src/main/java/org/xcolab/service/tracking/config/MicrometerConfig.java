package org.xcolab.service.tracking.config;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import org.xcolab.commons.monitoring.Parameter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

@Component
public class MicrometerConfig implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException
    {
        HttpServletRequest req = (HttpServletRequest) request;

        chain.doFilter(request, response);

        Enumeration enumeration = request.getParameterNames();
        String args = "";

        String function = (req.getRequestURI().length() > 1) ? req.getRequestURI().split("/")[1] : "";

        while(enumeration.hasMoreElements()){
            String parameterName = (String) enumeration.nextElement();
            args +=   parameterName + " : " +  request.getParameter(parameterName) +  " | ";
        }

        Metrics.counter("tracking-service","endpoint",req.getRequestURI(), "function", function, "method", req.getMethod(), "arguments", args).increment();

    }

    @Override
    public void destroy() {

    }
}
