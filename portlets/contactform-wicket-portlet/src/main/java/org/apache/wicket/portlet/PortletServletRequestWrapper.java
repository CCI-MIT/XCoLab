/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.portlet;

import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Wraps servlet request object with Portlet specific functionality by overriding the {@link HttpServletRequestWrapper}
 * to return the portal specific translations (e.g. retrieval of the context path, path info, request URI, etc...)
 * <p/>
 * 
 * @author Ate Douma
 * @author <a href="http://sebthom.de/">Sebastian Thomschke</a>
 * @author Peter Pastrnak
 * @author Ronny Pscheidl
 */
@SuppressWarnings("JavaDoc")
public class PortletServletRequestWrapper extends HttpServletRequestWrapper {
    private static final Set<String> HIDDEN_REQUEST_ATTRIBUTES = new HashSet<String>(5);
    static {
        HIDDEN_REQUEST_ATTRIBUTES.add("javax.servlet.error.request_uri");
        HIDDEN_REQUEST_ATTRIBUTES.add("javax.servlet.forward.request_uri");
        HIDDEN_REQUEST_ATTRIBUTES.add("javax.servlet.forward.servlet_path");
        HIDDEN_REQUEST_ATTRIBUTES.add("javax.servlet.forward.context_path");
        HIDDEN_REQUEST_ATTRIBUTES.add("javax.servlet.forward.query_string");
    }

    /**
     * Converts from a filterPath (path with a trailing slash), to a servletPath (path with a leading slash).
     * 
     * @param filterPath
     * @return the filterPath prefixed with a leading slash and with the trailing slash removed
     */
    private static String makeServletPath(final String filterPath) {
        return "/" + filterPath.substring(0, filterPath.length() - 1);
    }

    /**
     * Context path.
     */
    private String contextPath;

    /**
     * Servlet path.
     */
    private final String servletPath;

    /**
     * Path info - the url relative to the context and filter path.
     */
    private String pathInfo;

    /**
     * Request URI.
     */
    private String requestURI;

    /**
     * Query string.
     */
    private String queryString;

    /**
     * HTTP session.
     */
    private HttpSession session;

    /**
     * Public constructor which internally builds the path info from request URI, instead of deriving it.
     * 
     * @param request
     * @param proxiedSession
     * @param filterPath
     */
    public PortletServletRequestWrapper(final HttpServletRequest request,
            final HttpSession proxiedSession, final String filterPath) {
        this(proxiedSession, request, filterPath);

        // Liferay sometimes gives an incorrect requestURI
        final int pathInfoBegin = contextPath.length() + filterPath.length();
        final String pathInfo = pathInfoBegin >= requestURI.length() ? null : requestURI.substring(pathInfoBegin);
        this.pathInfo = pathInfo == null || pathInfo.length() < 2 ? null : pathInfo;
    }

    /**
     * Public constructor called when not running in a portlet environment, which is passed in the path info instead of
     * deriving it. It overrides the generated request URI from the internal constructor.
     * 
     * @param request
     * @param proxiedSession
     * @param filterPath ???
     * @param pathInfo ???
     */
    public PortletServletRequestWrapper(final HttpServletRequest request,
            final HttpSession proxiedSession, final String filterPath, final String pathInfo) {
        this(proxiedSession, request, filterPath);

        this.pathInfo = pathInfo;
        // override requestURI which is setup in the protected constructor
        requestURI = contextPath + servletPath + (pathInfo != null ? pathInfo : "");
    }

    /**
     * Package private constructor which is called from either of the two public
     * constructors - sets up the various portlet specific versions of the
     * context path, servlet path, request URI etc...
     * 
     * @param proxiedSession
     * @param request
     * @param filterPath
     */
    PortletServletRequestWrapper(final HttpSession proxiedSession,
            final HttpServletRequest request, final String filterPath) {
        super(request);

        session = proxiedSession;
        if (proxiedSession == null) {
            session = request.getSession(false);
        }

        servletPath = makeServletPath(filterPath);

        // retrieve the correct contextPath, requestURI and queryString
        if ((contextPath = (String) request.getAttribute("javax.servlet.include.context_path")) != null) {
            // request is an include
            requestURI = (String) request.getAttribute("javax.servlet.include.request_uri");
            queryString = (String) request.getAttribute("javax.servlet.include.query_string");
        } else if ((contextPath = (String) request.getAttribute("javax.servlet.forward.context_path")) != null) {
            // request is a forward
            requestURI = (String) request.getAttribute("javax.servlet.forward.request_uri");
            queryString = (String) request.getAttribute("javax.servlet.include.query_string");
        } else {
            // normal request
            contextPath = request.getContextPath();
            requestURI = request.getRequestURI();
            queryString = request.getQueryString();
        }
    }

    @Override
    public Object getAttribute(final String name) {
        // TODO: check if these can possibly be set/handled
        // nullifying these for now to prevent Wicket ServletWebRequest.getClientUrl() going the wrong route
        if (HIDDEN_REQUEST_ATTRIBUTES.contains(name)) {
            return null;
        }
        return super.getAttribute(name);
    }

    @Override
    public String getContextPath() {
        return contextPath;
    }

    @Override
    public String getPathInfo() {
        return pathInfo;
    }

    @Override
    public String getQueryString() {
        return queryString;
    }

    @Override
    public String getRequestURI() {
        return requestURI;
    }

    @Override
    public String getServletPath() {
        return servletPath;
    }

    @Override
    public HttpSession getSession() {
        return getSession(true);
    }

    @Override
    public HttpSession getSession(final boolean create) {
        return session != null ? session : super.getSession(create);
    }

    @Override
    public String getHeader(String name) {
        String header = super.getHeader(name);
        if (header == null) {
            // GateIn: SimpleMultiValuedPropertyMap does not load values with equalsIgnoreCase like MimeHeaders do
            // in Request
            Enumeration<?> headerNames = this.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement().toString();
                if (headerName.equalsIgnoreCase(name)) {
                    header = super.getHeader(headerName);
                    break;
                }
            }
        }
        return header;
    }

    @Override
    public Enumeration<?> getHeaders(String name) {
        Enumeration<?> headers = super.getHeaders(name);
        if (!headers.hasMoreElements()) {
            // GateIn: SimpleMultiValuedPropertyMap does not load values with equalsIgnoreCase like MimeHeaders do
            // in Request
            Enumeration<?> headerNames = this.getHeaderNames();
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement().toString();
                if (headerName.equalsIgnoreCase(name)) {
                    headers = super.getHeaders(headerName);
                    break;
                }
            }
        }
        return headers;
    }

    @Override
    public void setCharacterEncoding(final String enc) throws UnsupportedEncodingException {
        try {
            super.setCharacterEncoding(enc);
        } catch (final UnsupportedEncodingException uex) {
            /*
             * SUN OpenPortal Portlet Container 2.0_01 BUG which only allows setting an encoding as provided by
             * underlying request and throws UnsupportedEncodingException even when that one == null
             */
            LoggerFactory.getLogger(PortletServletRequestWrapper.class).debug(
                    "Probably vendor specific problem with character encoding, ignoring for now.", uex);
        }
    }
}
