package org.xcolab.hooks.climatecolab.massmessaging;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ext.portlet.model.MessagingRedirectLink;
import com.ext.portlet.service.MessagingRedirectLinkLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class MassMessagingFilter implements Filter {
    private final static byte[] BLANK_GIF_RESPONSE = { 71, 73, 70, 56, 57, 97, 1, 0, 1, 0, -128, 0, 0, -1, -1, -1, -1,
            -1, -1, 33, -7, 4, 1, 10, 0, 1, 0, 44, 0, 0, 0, 0, 1, 0, 1, 0, 0, 2, 2, 76, 1, 0, 59 };

    private static final String DEFAULT_REDIRECT = "/web/guest/";

    private static final String REFERER_HEADER = "referer";

    private static final String CONTENT_TYPE_GIF = "image/gif";
    
    private final static Log _log = LogFactoryUtil.getLog(MassMessagingFilter.class);
    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException,
            ServletException {
        
        if (!(arg0 instanceof HttpServletRequest) || ! (arg1 instanceof HttpServletResponse)) {
            return;
        }
        
        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;
        

        try {
            Map<String, String> linkParameters = MessagingUtils.getLinkParameters(request);
            Long messageId = Long.parseLong(linkParameters.get(MessagingConstants.PARAMETER_MESSAGE_ID));
            String action = linkParameters.get(MessagingConstants.PARAMETER_ACTION);
            
            if (action == null) {
                throw new Exception("Action parameter can't be null.");
            }
            
            if (action.equals(MessagingConstants.ACTION_IMAGE)) {
                response.setContentType(CONTENT_TYPE_GIF);
                
                String referer = request.getHeader(REFERER_HEADER);
                if (referer == null || !referer.contains(request.getServerName())) {
                    // add conversion only when request doesn't come from portal
                    MessagingUtils.addConversion(messageId, MessagingConversionTypes.EMAIL_OPENED, request, null, null);
                }
                response.getOutputStream().write(BLANK_GIF_RESPONSE);
            } else if (action.equals(MessagingConstants.ACTION_REDIRECT)) {
                String redirectURL = DEFAULT_REDIRECT;
                
                try {
                    Long redirectId = Long.parseLong(linkParameters.get(MessagingConstants.PARAMETER_REDIRECT_ID));
                    Long recipientId = Long.parseLong(linkParameters.get(MessagingConstants.PARAMETER_RECIPIENT_ID));
                    MessagingRedirectLink redirectLink = 
                        MessagingRedirectLinkLocalServiceUtil.getMessagingRedirectLink(redirectId);
                    request.getSession().setAttribute(MessagingConstants.USER_FROM_EMAIL_FLAG, messageId);
                    MessagingUtils.addConversion(messageId, MessagingConversionTypes.EMAIL_LINK_CLICKED, request,
                            redirectId, recipientId);
                    redirectURL = redirectLink.getLink();
                } catch (NumberFormatException | PortalException | SystemException e) {
                    // log the exception and continue processing
                    _log.error("There was an exception when accessing redirect data", e);
                }
                
                if (redirectURL == null || redirectURL.trim().equals("")) {
                    redirectURL = DEFAULT_REDIRECT;
                }

                response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
                response.setHeader("Location", redirectURL);
            }

        } catch (Exception e) {
            _log.error("Ther was an error when processing conversion, path: " + request.getPathInfo(), e);
        }
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

}
