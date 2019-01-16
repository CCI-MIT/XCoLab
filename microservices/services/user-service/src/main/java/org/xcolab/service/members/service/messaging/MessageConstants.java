package org.xcolab.service.members.service.messaging;

public class MessageConstants {
    public static final String EMAIL_MESSAGE_SUBJECT = "[<colab-name/>] Message from $USER";
    public static final String EMAIL_MESSAGE_TEMPLATE = "The <colab-name/> user <b>$USER</b> has sent you " +
            "the following message:\n<br /><br />" +
            "<b>Subject: $SUBJECT</b>\n" +
            "<br /><br />$MESSAGE<br /><br /> \n" +
            "--------------\n"+
            "<br /><br />Please do not reply to this email. "
            + "If you no longer wish to receive email notifications when you receive messages, "
            + "you can update your message settings <a href='$PROFILE_URL'>here</a> (login required).<br/><br />"
            + "You can view and respond to this message <a href='$URL'>here</a>.<br /><br />" +
            "(If the above link does not work, please paste the following link directly into your browser: $URL )";

    public static final String EMAIL_MESSAGE_VAR_USER = "$USER";
    public static final String EMAIL_MESSAGE_VAR_MESSAGE = "$MESSAGE";
    public static final String EMAIL_MESSAGE_VAR_URL = "$URL";
    public static final String EMAIL_MESSAGE_VAR_PROFILE_URL = "$PROFILE_URL";

    public static final String EMAIL_MESSAGE_URL_TEMPLATE = "/messaging/message/";


    public static final String EMAIL_MESSAGE_VAR_SUBJECT = "$SUBJECT" ;
}
