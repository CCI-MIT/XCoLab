package org.xcolab.view.config.spring.filters;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CdnUrlEncodingFilter extends GenericFilterBean {

    private final AntPathMatcher pathMatcher = new AntPathMatcher();
    private final Map<String, String> pathPatternToCdnUrlMap;

    public CdnUrlEncodingFilter(Map<String, String> pathPatternToCdnUrlMap) {
        this.pathPatternToCdnUrlMap = pathPatternToCdnUrlMap;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        if (!(response instanceof HttpServletResponse)) {
            throw new ServletException("CdnUrlEncodingFilter just supports HTTP requests");
        }
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        filterChain.doFilter(request, new CdnUrlEncodingResponseWrapper(httpResponse));
    }

    private class CdnUrlEncodingResponseWrapper extends HttpServletResponseWrapper {

        public CdnUrlEncodingResponseWrapper(HttpServletResponse wrapped) {
            super(wrapped);
        }

        @Override
        public String encodeURL(String url) {
            final String localUrl = super.encodeURL(url);
            for (Map.Entry<String, String> entry : pathPatternToCdnUrlMap.entrySet()) {
                final String pattern = entry.getKey();
                final String domain = entry.getValue();

                if (StringUtils.isNotBlank(localUrl) && pathMatcher.match(pattern, localUrl)) {
                    return domain + localUrl;
                }
            }
            return localUrl;
        }
    }
}
