/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.portlets.massmessaging;

import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ext.portlet.model.MessagingRedirectLink;
import com.ext.portlet.service.MessagingRedirectLinkLocalServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class MessagingServlet extends HttpServlet {

    /**
     * 
     */
    private static final long serialVersionUID = -1937254429148377314L;

    private final static byte[] BLANK_GIF_RESPONSE = { 71, 73, 70, 56, 57, 97, 1, 0, 1, 0, -128, 0, 0, -1, -1, -1, -1,
            -1, -1, 33, -7, 4, 1, 10, 0, 1, 0, 44, 0, 0, 0, 0, 1, 0, 1, 0, 0, 2, 2, 76, 1, 0, 59 };

    private final static Log _log = LogFactoryUtil.getLog(MessagingServlet.class);

    private static final String DEFAULT_REDIRECT = "/web/guest/";

    private static final String REFERER_HEADER = "referer";

    private static final String CONTENT_TYPE_GIF = "image/gif";

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) {

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
                if (referer == null || referer.indexOf(request.getServerName()) == -1) {
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
                }
                catch (Exception e) {
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
}
