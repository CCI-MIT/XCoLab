package org.xcolab.service.moderation.config;

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
        String function = "";
        if(req.getRequestURI().length() > 1){
            function = req.getRequestURI().split("/")[1];
        }


        Enumeration enumeration = request.getParameterNames();

        ArrayList<Parameter> parameters = new ArrayList<>();

        while(enumeration.hasMoreElements()){
            String parameterName = (String) enumeration.nextElement();
            parameters.add( new Parameter(parameterName, request.getParameter(parameterName)));
        }

        if(StringUtils.compare(function, "contests") == 0){
            String fml = function;
        }

        ArrayList<Tag> tags = new ArrayList<>();

        String args = "";

        for (Parameter parameter: parameters) {
            args +=   parameter.getKey() + " : " +  parameter.getValue() +  " | ";
        }

        tags.addAll(parameters);


        Metrics.counter("moderation-service","endpoint",req.getRequestURI(), "function", function, "method", req.getMethod(), "Arguments", args).increment();
        //Metrics.counter("contest-service",tags).increment();

    }

    @Override
    public void destroy() {

    }
}
