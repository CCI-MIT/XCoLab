package org.xcolab.service.members.service.messaging;

public class MessageConstants {
    public static final String EMAIL_MESSAGE_SUBJECT = "[<colab-name/>] Message from $USER";
    public static final String EMAIL_MESSAGE_TEMPLATE = "The <colab-name/> user <b>$USER</b> has sent you " +
            "the following message:\n<br /><br />" +
            "<b>Subject: $SUBJECT</b>\n" +
            "<br /><br />$MESSAGE<br /><br /> \n" +
            "--------------<br />\n"+
            "<br /><br />Please do not reply to this email. You can view and respond to this message <a href='$URL'>here</a>.<br /><br />" +
            "(If the above link does not work, please paste the following link directly into your browser: $URL )";

    public static final String EMAIL_MESSAGE_VAR_USER = "$USER";
    public static final String EMAIL_MESSAGE_VAR_MESSAGE = "$MESSAGE";
    public static final String EMAIL_MESSAGE_VAR_URL = "$URL";

    public static final String EMAIL_MESSAGE_URL_TEMPLATE = "/web/guest/messaging/-/messaging/message/";


    public static final String EMAIL_MESSAGE_VAR_SUBJECT = "$SUBJECT" ;
}
