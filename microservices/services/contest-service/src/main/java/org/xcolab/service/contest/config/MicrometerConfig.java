package org.xcolab.service.contest.config;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import org.springframework.stereotype.Component;

import org.xcolab.commons.monitoring.Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        // TODO: Implement tags for individual arguments

        //"endpoint",req.getRequestURI(), "function", function, "method", req.getMethod()

        //parameters.add(new Parameter(gson.toJson("endpoint"),gson.toJson(req.getRequestURI()) ));
        //parameters.add(new Parameter(gson.toJson("function"), gson.toJson(function)));
        //parameters.add(new Parameter(gson.toJson("method"), gson.toJson(req.getMethod())));


        while(enumeration.hasMoreElements()){
            String parameterName = (String) enumeration.nextElement();
            parameters.add( new Parameter(parameterName, request.getParameter(parameterName)));
        }

        ArrayList<Tag> tags = new ArrayList<>();

        String args = "";

        for (Parameter parameter: parameters) {
            args +=   parameter.getKey() + " : " +  parameter.getValue() +  " | ";// + " : " + parameter.getValue() + " | ";
        }

        tags.addAll(parameters);

        Metrics.counter("contest-service","endpoint",req.getRequestURI(), "function", function, "method", req.getMethod(), "Arguments", args).increment();
        //Metrics.counter("contest-service",tags).increment();

    }


    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }


    @Override
    public void destroy() {

    }
}
