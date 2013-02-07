/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package org.xcolab.hooks.climatecolab.massmessaging;

public class MessagingConstants {
    
    private MessagingConstants() {
        // empty, this is a utility class
    }
    
    public final static String VIEW_FORWARD = "portlet.ext.mass_messaging.view";
    public static final String EDIT_MESSAGE_FORWARD = "portlet.ext.mass_messaging.edit_message";
    public static final String VIEW_MESSAGE_FORWARD = "portlet.ext.mass_messaging.view_message";
    public static final String MANAGE_IGNORED_RECIPIENTS_FORWARD = "portlet.ext.mass_messaging.manage_ignored_recipients";
    public static final String RECIPIENT_DELIMITER = "\\|";
    public static final String USER_FROM_EMAIL_FLAG = MessagingConstants.class.getName() + ".MESSAGING_USER_FROM_EMAIL";
    public static final String PARAMETER_REDIRECT_ID = "redirectId";
    public static final String PARAMETER_ACTION = "action";
    public static final String ACTION_REDIRECT = "redirect";
    public static final String PARAMETER_MESSAGE_ID = "messageId";
    public static final String CONVERTION_PATH = "convert";
    public static final String CONVERSION_PARAMETER_DELIMITER = "/";
    public static final String ACTION_IMAGE = "image";
    public static final String VIEW_MESSAGE_DETAILS = "/group/control_panel/manage?p_p_id=massmessagingportlet_WAR_massmessagingportlet&p_p_lifecycle=0&p_p_state=maximized&p_p_mode=view&doAsGroupId=10136&refererPlid=16101&_massmessagingportlet_WAR_massmessagingportlet_jspPage=%2Fview_message.jsp&_massmessagingportlet_WAR_massmessagingportlet_messageId=";
    public static final String PARAMETER_RECIPIENT_ID = "recipientId";
    public static final String RECIPIENT_ID_PLACEHOLDER = "RECIPIENT_ID_PLACEHOLDER";
}
