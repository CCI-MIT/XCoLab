package org.xcolab.view.pages.feedback;

import org.json.JSONObject;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.commons.i18n.I18nUtils;
import org.xcolab.view.widgets.WidgetPreference;

import java.io.Serializable;

public class ContactPreferences extends WidgetPreference implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String RECIPIENTS_PREF = "RECIPIENTS";
    private static final String MESSAGE_FORMAT_PREF = "MESSAGE_FORMAT";
    private static final String MESSAGE_SUBJECT_PREF = "MESSAGE_SUBJECT";
    private static final String EXPAND_LINK_TEXT_PREF = "EXPAND_LINK_TEXT";
    private static final String defaultRecipients = "pdeboer@mit.edu,lfi@mit.edu";
    private static final String defaultMessageFormat =
            "USER_NAME (USER_EMAIL) has sent message using contact form\nUSER_MESSAGE";
    private static final String defaultMessageSubject =
            "[CoLab] USER_NAME sent a message using contact form";
    private static final String defaultExpandLinkText = "Send feedback message";
    private String recipients;
    private String messageFormat;
    private String messageSubject;
    private String expandLinkText;

    @Override
    public AttributeGetter<String> getConfigurationAttribute() {
        return ConfigurationAttributeKey.PORTLET_CONTACT_FORM_PREFERENCES;
    }


    public ContactPreferences() {
        this(null, I18nUtils.DEFAULT_LANGUAGE);
    }

    public ContactPreferences(String preferenceId, String language) {
        super(preferenceId, language);
        if (jsonPreferences.has(MESSAGE_FORMAT_PREF)) {
            messageFormat = jsonPreferences.getString(MESSAGE_FORMAT_PREF);
        } else {
            messageFormat = defaultMessageFormat;
        }

        if (jsonPreferences.has(MESSAGE_SUBJECT_PREF)) {
            messageSubject = jsonPreferences.getString(MESSAGE_SUBJECT_PREF);
        } else {
            messageSubject = defaultMessageSubject;
        }

        if (jsonPreferences.has(EXPAND_LINK_TEXT_PREF)) {
            expandLinkText = jsonPreferences.getString(EXPAND_LINK_TEXT_PREF);
        } else {
            expandLinkText = defaultExpandLinkText;
        }

        if (jsonPreferences.has(RECIPIENTS_PREF)) {
            recipients = jsonPreferences.getString(RECIPIENTS_PREF);
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

    @Override
    public void savePreferences() {
        JSONObject prefs = new JSONObject();
        prefs.put(MESSAGE_FORMAT_PREF, messageFormat);
        prefs.put(MESSAGE_SUBJECT_PREF, messageSubject);
        prefs.put(EXPAND_LINK_TEXT_PREF, expandLinkText);
        prefs.put(RECIPIENTS_PREF, recipients);
        savePreferencesInternal(prefs, null);
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

}
