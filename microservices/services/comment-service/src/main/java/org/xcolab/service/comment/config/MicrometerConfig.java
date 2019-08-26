package org.xcolab.service.comment.config;

import org.springframework.stereotype.Component;

import org.xcolab.util.metrics.MicrometerUtil;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

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

        MicrometerUtil.ProcessRequest("comment-service", request, response, chain);

    }

    @Override
    public void destroy() {

    }
}
