package org.xcolab.hooks.climatecolab.captcha;

import java.io.IOException;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.liferay.portal.kernel.captcha.CaptchaUtil;
import com.liferay.portal.kernel.util.JavaConstants;

public class CaptchaFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        if (request instanceof HttpServletRequest) {
            CaptchaUtil.serveImage((HttpServletRequest) request, (HttpServletResponse) response);
            HttpSession session = ((HttpServletRequest) request).getSession();
        }
        else {
        }
        chain.doFilter(request, response);

    }

    public void destroy() {

    }

}
