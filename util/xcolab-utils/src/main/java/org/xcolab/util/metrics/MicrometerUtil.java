package org.xcolab.util.metrics;

import io.micrometer.core.instrument.Metrics;

import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MicrometerUtil {

    public static void ProcessRequest(String serviceName, ServletRequest request,
            ServletResponse response,
            FilterChain chain){

        HttpServletRequest req = (HttpServletRequest) request;

        Enumeration enumeration = request.getParameterNames();
        String args = "";

        String function =
                (req.getRequestURI().length() > 1) ? req.getRequestURI().split("/")[1] : "";

        while (enumeration.hasMoreElements()) {
            String parameterName = (String) enumeration.nextElement();
            args += parameterName + " : " + request.getParameter(parameterName) + " | ";
        }

        Metrics.timer(serviceName, "endpoint", req.getRequestURI(), "function", function,
                "method", req.getMethod(), "arguments", args).record(() ->
        {
            try {
                chain.doFilter(request, response);
            }
            catch (Exception s) {
                System.err.println("Error occured when processing request");
            }
        });

    }
}
