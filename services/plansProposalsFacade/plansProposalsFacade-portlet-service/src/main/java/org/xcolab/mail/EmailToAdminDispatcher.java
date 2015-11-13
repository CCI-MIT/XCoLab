package org.xcolab.mail;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;

/**
 * Created by Mente on 29/07/15.
 */
public class EmailToAdminDispatcher {

    private static final Log _log = LogFactoryUtil.getLog(EmailToAdminDispatcher.class);

    private static final String[] ADMIN_EMAIL_RECIPIENTS = {"pdeboer@mit.edu", "knauert@mit.edu", "mail@klemensmang.com", "jobachhu@mit.edu"};
    private static final String FROM_EMAIL_ADDRESS = "no-reply@climatecolab.org";
    private static final String FROM_EMAIL_NAME = "MIT Climate CoLab";

    private final String subject;
    private final String body;

    public EmailToAdminDispatcher(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public void sendMessage() {
        try {
            InternetAddress fromAddress = new InternetAddress(FROM_EMAIL_ADDRESS, FROM_EMAIL_NAME);
            MailEngine.send(fromAddress, getAllAdminRecipients(), subject, body, true);
        } catch (MailEngineException | AddressException | UnsupportedEncodingException e) {
            _log.error("Could not send error message", e);
        }
    }

    private InternetAddress[] getAllAdminRecipients() throws AddressException {
        InternetAddress[] addressTo = new InternetAddress[ADMIN_EMAIL_RECIPIENTS.length];
        for (int i = 0; i < ADMIN_EMAIL_RECIPIENTS.length; i++) {
            addressTo[i] = new InternetAddress(ADMIN_EMAIL_RECIPIENTS[i]);
        }

        return addressTo;
    }
}
