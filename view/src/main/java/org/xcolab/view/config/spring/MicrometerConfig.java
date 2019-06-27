package org.xcolab.view.config.spring;

import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Component;

import org.xcolab.util.metrics.MicrometerUtil;

import java.io.IOException;
import java.util.Enumeration;

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
            FilterChain chain) throws IOException, ServletException {

        MicrometerUtil.ProcessRequest("colab-view", request, response, chain);

    }

    @Override
    public void destroy() {

    }
}
