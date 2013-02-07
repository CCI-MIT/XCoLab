package org.xcolab.hooks.climatecolab.landingpage;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ext.portlet.model.LandingPage;
import com.ext.portlet.service.LandingPageLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class LandingpageFilter implements Filter {

    private static final long serialVersionUID = 3534776054953153891L;
    
    private static final Pattern landingPageUrlPattern = Pattern.compile("/landingpage/(\\d*)/.*"); 
    private static final Log _log =  LogFactoryUtil.getLog(LandingpageFilter.class);
    
    public void init(FilterConfig filterConfig) throws ServletException {
        
    }

    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException,
            ServletException {
        try {

            if (!(arg0 instanceof HttpServletRequest) || ! (arg1 instanceof HttpServletResponse)) 
                return;
            
            HttpServletRequest request = (HttpServletRequest) arg0;
            HttpServletResponse response = (HttpServletResponse) arg1;
            
            Matcher m = landingPageUrlPattern.matcher(request.getRequestURI());
            if (m.find()) {
                Long landingPageId = Long.parseLong(m.group(1));
                LandingPage lp = LandingPageLocalServiceUtil.getLandingPage(landingPageId);
            
                request.getSession().setAttribute("user_from_landing_page", "/landingpage/" + lp.getId() + "/" + lp.getBaseUrl());
                response.sendRedirect(lp.getTargetUrl());
            }
            else {
                _log.error("Invalid landing page requested, should be in format /landingpage/LANDINGPAGE_ID/name");
            }
        } catch (Exception e) {
            _log.error("Can't process landing page request" , e);
        }
        
    }

    public void destroy() {
        
    }
    

}
