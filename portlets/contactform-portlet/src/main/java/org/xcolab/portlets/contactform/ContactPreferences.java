package org.xcolab.portlets.contactform;

import java.io.IOException;

import java.io.Serializable;
import java.util.List;

import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;

import org.springframework.stereotype.Component;

import com.liferay.portal.model.User;
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
    
    private final static String defaultRecipients = "rjl@mit.edu,janusz.parfieniuk@gmail.com,pdeboer@mit.edu,lfi@mit.edu,thurner@mit.edu";
    private final static String defaultMessageFormat = "USER_NAME (USER_EMAIL) has sent message using contact form\nUSER_MESSAGE";
    private final static String defaultMessageSubject = "[CoLab] USER_NAME sent a message using contact form";
    private final static String defaultExpandLinkText = "Send feedback message";    
    
    

    public List<User> getMessageRecipients() {
        // TODO Auto-generated method stub
        return null;
    }
    
    public ContactPreferences() {
        //PortletPreferences prefs = Helper.getPortletPrefs();
        
        messageFormat = defaultMessageFormat;
        messageSubject = defaultMessageSubject;
        expandLinkText = defaultExpandLinkText;
        recipients = defaultRecipients;
        
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
    
    public String submit() throws ReadOnlyException, ValidatorException, IOException {
        /*
        PortletPreferences prefs = Helper.getPortletPrefs();
        prefs.setValue(MESSAGE_FORMAT_PREF, messageFormat);
        prefs.setValue(MESSAGE_SUBJECT_PREF, me
        FacesMessage fm = new FacesMessage();
        fm.setSummary("Settings saved successfully");
        fm.setSeverity(FacesMessage.SEVERITY_INFO);

        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, fm);ssageSubject);
        prefs.setValue(EXPAND_LINK_TEXT_PREF, expandLinkText);
        prefs.setValue(RECIPIENTS_PREF, recipients);

        prefs.store();
            
        */
        
        return null;
    }
    

}
