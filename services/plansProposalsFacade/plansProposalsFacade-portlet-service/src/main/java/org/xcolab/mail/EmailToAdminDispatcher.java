package org.xcolab.mail;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.mail.MailEngine;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by Mente on 29/07/15.
 */
public class EmailToAdminDispatcher {

    private static final Log _log = LogFactoryUtil.getLog(EmailToAdminDispatcher.class);

    private static final String[] adminEmailRecipients = new String[] {"pdeboer@mit.edu", "knauert@mit.edu", "mail@klemensmang.com", "jobachhu@mit.edu"};
    private static final String fromEmailAddress = "no-reply@climatecolab.org";
    private static final String fromEmailName = "MIT Climate CoLab";

    private String subject;
    private String body;

    public EmailToAdminDispatcher(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public void sendMessage() {
        try {
            InternetAddress fromAddress = new InternetAddress(fromEmailAddress, fromEmailName);
            MailEngine.send(fromAddress, getAllAdminRecipients(), subject, body, true);
        } catch (Exception e) {
            _log.error("Could not send error message", e);
        }
    }

    private InternetAddress[] getAllAdminRecipients() throws AddressException {
        InternetAddress[] addressTo = new InternetAddress[adminEmailRecipients.length];
        for (int i = 0; i < adminEmailRecipients.length; i++) {
            addressTo[i] = new InternetAddress(adminEmailRecipients[i]);
        }

        return addressTo;
    }


}
