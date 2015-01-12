package org.xcolab.portlets.contactform;

import com.liferay.portal.model.User;
import org.springframework.stereotype.Component;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
@Component
public class ContactPreferences implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String recipients;
    private String messageFormat;
    private String messageSubject;
    private String expandLinkText;

    private final static String RECIPIENTS_PREF = "RECIPIENTS";
    private final static String MESSAGE_FORMAT_PREF = "MESSAGE_FORMAT";
    private final static String MESSAGE_SUBJECT_PREF = "MESSAGE_SUBJECT";
    private final static String EXPAND_LINK_TEXT_PREF = "EXPAND_LINK_TEXT";
    
    private final static String defaultRecipients = "pdeboer@mit.edu,lfi@mit.edu,knauert@mit.edu";
    private final static String defaultMessageFormat = "USER_NAME (USER_EMAIL) has sent message using contact form\nUSER_MESSAGE";
    private final static String defaultMessageSubject = "[CoLab] USER_NAME sent a message using contact form";
    private final static String defaultExpandLinkText = "Send feedback message";    
    
    

    public List<User> getMessageRecipients() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ContactPreferences() {
        messageFormat = defaultMessageFormat;
        messageSubject = defaultMessageSubject;
        expandLinkText = defaultExpandLinkText;
        recipients = defaultRecipients;
    }
    public ContactPreferences(PortletRequest request) {
        PortletPreferences prefs = request.getPreferences();

        messageFormat = prefs.getValue(MESSAGE_FORMAT_PREF, String.valueOf(defaultMessageFormat));
        messageSubject = prefs.getValue(MESSAGE_SUBJECT_PREF, String.valueOf(defaultMessageSubject));
        expandLinkText = prefs.getValue(EXPAND_LINK_TEXT_PREF, String.valueOf(defaultExpandLinkText));
        recipients = prefs.getValue(RECIPIENTS_PREF, String.valueOf(defaultRecipients));
    }

    public String store(PortletPreferences prefs) throws ReadOnlyException, ValidatorException, IOException {

        prefs.setValue(MESSAGE_FORMAT_PREF, messageFormat);
        prefs.setValue(MESSAGE_SUBJECT_PREF, messageSubject);
        prefs.setValue(EXPAND_LINK_TEXT_PREF, expandLinkText);
        prefs.setValue(RECIPIENTS_PREF, recipients);
        prefs.store();

        return null;
    }


    public String getRecipients() {
        return recipients;
    }
    
    public String[] getRecipientsArray() {
        return recipients.split(",");
    }


    public void setRecipients(String recipients) {
        this.recipients = recipients;
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


    public static String getRecipientsPref() {
        return RECIPIENTS_PREF;
    }


    public static String getMessageFormatPref() {
        return MESSAGE_FORMAT_PREF;
    }


    public static String getMessageSubjectPref() {
        return MESSAGE_SUBJECT_PREF;
    }
}
