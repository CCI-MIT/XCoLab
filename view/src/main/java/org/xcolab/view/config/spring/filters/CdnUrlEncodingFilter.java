package org.xcolab.view.config.spring.filters;

import org.apache.commons.lang3.StringUtils;
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

    private final Map<String, String> prefixToCdnUrlMap;

    public CdnUrlEncodingFilter(Map<String, String> prefixToCdnUrlMap) {
        this.prefixToCdnUrlMap = prefixToCdnUrlMap;
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
            //TODO COLAB-2446: use pathPatterns rather than prefixes
            for (Map.Entry<String, String> entry : prefixToCdnUrlMap.entrySet()) {
                if (StringUtils.isNotBlank(localUrl) && localUrl.startsWith(entry.getKey())) {
                    final String finalUrl = entry.getValue() + localUrl;
                    return super.encodeURL(finalUrl);
                }
            }
            return localUrl;
        }

    }

}
