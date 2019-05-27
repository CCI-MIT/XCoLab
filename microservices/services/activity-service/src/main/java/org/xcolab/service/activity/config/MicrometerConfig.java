package org.xcolab.service.activity.config;

import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Component;

import java.io.IOException;

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
        String function = "";
        if(req.getRequestURI().length() > 1){
            function = req.getRequestURI().split("/")[1];
        }

        Metrics.counter("activity-service","endpoint",req.getRequestURI(), "function", function).increment();

    }

    @Override
    public void destroy() {

    }
}
