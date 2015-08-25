/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.messaging;

/**
 * @author jintrone
 * @date 01/19/2010
 * @version 1.0
 */
public class MessageConstants {
    public static final String MESSAGE_ID = "MessageId";
    public static final String MOVE_MESSAGE_IDS = "MoveMessagesIds";
    public static final String FORWARD_VIEW_MESSAGES = "portlet.ext.messaging.view" ;
    public static final String FORWARD_VIEW_MESSAGE_PREFERENCES = "portlet.ext.messaging.view_preferences";

    public static final String ACTION_VIEW_MESSAGES = "/ext/messaging/view_messages" ;
    public static final String ACTION_MOVE_MESSAGES = "/ext/messaging/move_messages";
    public static final String ACTION_SEND_MESSAGE = "/ext/messaging/compose_messages";
    public static final String ACTION_UPDATE_PREFERENCES = "/ext/messaging/update_preferences";
    public static final String ACTION_VIEW_PREFERENCES = "/ext/messaging/view_preferences";


    public static final String PAGER_START = "pagerStart";
    public static int PAGER_MAX_NUMBER = 10;
    public static String INBOX = "Inbox";
    public static String SENT = "Sent";
    public static String ARCHIVED = "Archived";
    public static String MESSAGE_TYPE = "MessageType";

    public static String MESSAGE_COUNT = "MessageCount";

    public static final String MESSAGES = "Messages";

     public static final String REDIRECT = "Redirect";
    public static final String COMPOSE_SUBJECT = "ComposeSubject";
    public static final String COMPOSE_CONTENT = "ComponseContent";
    public static final String COMPOSE_RECIPIENTS = "ComposeRecipients";
    public static final String COMPOSE_REPLY_TO="ComposeReplyTo";

    public static final String MESSAGE = "Message";


    public static final String EMAIL_MESSAGE_SUBJECT = "[Climate CoLab] Message from $USER";
    public static final String EMAIL_MESSAGE_TEMPLATE = "The Climate CoLab user <b>$USER</b> has sent you " +
            "the following message:\n<br /><br />" +
            "<b>Subject: $SUBJECT</b>\n" +
            "<br /><br />$MESSAGE<br /><br /> \n" +
            "--------------<br />\n"+
            "<br /><br />Please do not reply to this email. You can view and respond to this message <a href='$URL'>here</a>.<br /><br />" +
            "(If the above link does not work, please paste the following link directly into your browser: $URL )";

    public static final String EMAIL_MESSAGE_VAR_USER = "$USER";
    public static final String EMAIL_MESSAGE_VAR_MESSAGE = "$MESSAGE";
    public static final String EMAIL_MESSAGE_VAR_URL = "$URL";

    public static final String EMAIL_MESSAGE_URL_TEMPLATE = "/web/guest/messaging?p_p_id=messaging_WAR_messagingportlet_INSTANCE_3LAh&messageId=";


    public static final String EMAIL_MESSAGE_VAR_SUBJECT = "$SUBJECT" ;
    public static final String MESSAGE_PREFERENCES = "MessagePreferences";

    public static final String MESSAGING_PREF_COPY_ON_RECEIPT = "MessagePrefCopyOnReceipt";
}
