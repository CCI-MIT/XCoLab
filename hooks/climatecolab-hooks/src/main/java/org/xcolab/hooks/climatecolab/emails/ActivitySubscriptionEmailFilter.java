package org.xcolab.hooks.climatecolab.emails;

import org.xcolab.entity.utils.activityEntry.ActivitySubscriptionEmailHelper;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ActivitySubscriptionEmailFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //no init method
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        ActivitySubscriptionEmailHelper.sendEmailNotifications();
    }

    @Override
    public void destroy() {
        //no destroy method
    }
}
