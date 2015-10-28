/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.hooks.climatecolab.massmessaging;

import java.util.HashMap;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import com.ext.portlet.service.MessagingMessageConversionLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

public class MessagingUtils {

    private final static Log _log = LogFactoryUtil.getLog(MessagingUtils.class);
    
    public static void addConversion(Long messageId, MessagingConversionTypes typeName, PortletRequest request, 
            Object extraData, Object extraData2)
    throws SystemException, PortalException {
        addConversion(messageId, typeName, PortalUtil.getHttpServletRequest(request).getRemoteAddr(), extraData, extraData2);
    }
    
    
    public static void addConversion(Long messageId, MessagingConversionTypes typeName, HttpServletRequest request, 
            Object extraData, Object extraData2) throws SystemException, PortalException {
        addConversion(messageId, typeName, request.getRemoteAddr(), extraData, extraData2);
        
    }

    public static void addConversion(Long messageId, MessagingConversionTypes typeName, String clientIP, 
            Object extraData, Object extraData2)
            throws SystemException, PortalException {

        MessagingMessageConversionLocalServiceUtil.addConversion(messageId, typeName.name(), clientIP, extraData, extraData2);
    }

    public static void handleUserRegistered(ActionRequest actionRequest, Long messageId) {

        ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
        Long userId = themeDisplay.getUserId();

        try {
            MessagingUtils.addConversion(messageId, MessagingConversionTypes.USER_REGISTERED, actionRequest, userId, null);
        } catch (SystemException | PortalException e) {
            // no exception should be rethrown
            _log.error("Can't add message conversion details after user registration", e);
        }

    }
    
    public static Map<String, String> getLinkParameters(HttpServletRequest request) {
        String pathInfo = request.getRequestURL().toString();
        
        pathInfo = pathInfo.substring(pathInfo.indexOf("/", 10)+1);
        String[] parameters = pathInfo.split(MessagingConstants.CONVERSION_PARAMETER_DELIMITER);
        
        Map<String, String> parametersMap = new HashMap<>();
        for (int i = 1; i < parameters.length; i += 2) {
            parametersMap.put(parameters[i], parameters[i+1]);
        }
        
        return parametersMap;
    }
    
    
    public static String getPortalURL(PortletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
    
    public static int countConversionsByIP(Long messageId, MessagingConversionTypes typeName) throws SystemException {
        return MessagingMessageConversionLocalServiceUtil.countConversionsByIP(messageId, typeName.name);
    }
    
    public static int countConversionsByRecipient(Long messageId) throws SystemException {
        return MessagingMessageConversionLocalServiceUtil.countConversionsByRecipient(messageId, MessagingConversionTypes.EMAIL_LINK_CLICKED.name());
    }
}
