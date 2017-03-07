package org.xcolab.view.pages.feedback;

import org.json.JSONObject;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ConfigurationAttribute;

import java.io.IOException;
import java.io.Serializable;

public class ContactPreferences implements Serializable {

    private static final long serialVersionUID = 1L;
    private final static String RECIPIENTS_PREF = "RECIPIENTS";
    private final static String MESSAGE_FORMAT_PREF = "MESSAGE_FORMAT";
    private final static String MESSAGE_SUBJECT_PREF = "MESSAGE_SUBJECT";
    private final static String EXPAND_LINK_TEXT_PREF = "EXPAND_LINK_TEXT";
    private final static String defaultRecipients = "pdeboer@mit.edu,lfi@mit.edu";
    private final static String defaultMessageFormat =
            "USER_NAME (USER_EMAIL) has sent message using contact form\nUSER_MESSAGE";
    private final static String defaultMessageSubject =
            "[CoLab] USER_NAME sent a message using contact form";
    private final static String defaultExpandLinkText = "Send feedback message";
    private String recipients;
    private String messageFormat;
    private String messageSubject;
    private String expandLinkText;

    public ContactPreferences() {
        JSONObject prefs =
                new JSONObject(ConfigurationAttributeKey.PORTLET_CONTACT_FORM_PREFERENCES.get());

        if (prefs.has(MESSAGE_FORMAT_PREF)) {
            messageFormat = prefs.getString(MESSAGE_FORMAT_PREF);
        } else {
            messageFormat = defaultMessageFormat;
        }

        if (prefs.has(MESSAGE_SUBJECT_PREF)) {
            messageSubject = prefs.getString(MESSAGE_SUBJECT_PREF);
        } else {
            messageSubject = defaultMessageSubject;
        }

        if (prefs.has(EXPAND_LINK_TEXT_PREF)) {
            expandLinkText = prefs.getString(EXPAND_LINK_TEXT_PREF);
        } else {
            expandLinkText = defaultExpandLinkText;
        }

        if (prefs.has(RECIPIENTS_PREF)) {
            recipients = prefs.getString(RECIPIENTS_PREF);
        } else {
            recipients = defaultRecipients;
        }
    }

    public static String getRecipientsPref() {
        return RECIPIENTS_PREF;
    }

    public static String getMessageFormatPref() {
        return MESSAGE_FORMAT_PREF;
    }

    public static String getMessageSubjectPref() {
        return MESSAGE_SUBJECT_PREF;
    }

    public void save() throws IOException {
        JSONObject prefs = new JSONObject();
        prefs.put(MESSAGE_FORMAT_PREF, messageFormat);
        prefs.put(MESSAGE_SUBJECT_PREF, messageSubject);
        prefs.put(EXPAND_LINK_TEXT_PREF, expandLinkText);
        prefs.put(RECIPIENTS_PREF, recipients);
        ConfigurationAttribute configurationAttribute = new ConfigurationAttribute();
        configurationAttribute
                .setName(ConfigurationAttributeKey.PORTLET_CONTACT_FORM_PREFERENCES.name());
        configurationAttribute.setStringValue(prefs.toString());
        AdminClient.updateConfigurationAttribute(configurationAttribute);
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String[] getRecipientsArray() {
        return recipients.split(",");
    }

    public String getMessageFormat() {
        return messageFormat;
    }

    public void setMessageFormat(String messageFormat) {
        this.messageFormat = messageFormat;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getExpandLinkText() {
        return expandLinkText;
    }

    public void setExpandLinkText(String expandLinkText) {
        this.expandLinkText = expandLinkText;
    }
}
