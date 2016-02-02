package org.xcolab.mail;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.util.mail.MailEngine;
import com.liferay.util.mail.MailEngineException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mente on 29/07/15.
 */
public class EmailToAdminDispatcher {

    private static final Log _log = LogFactoryUtil.getLog(EmailToAdminDispatcher.class);

    public final static int VERBOSITY_ERROR = 1;
    public final static int VERBOSITY_DEBUG = 2;

    private static final Recipient[] ADMIN_EMAIL_RECIPIENTS = {
            new Recipient("pdeboer@mit.edu", VERBOSITY_ERROR),
            new Recipient("knauert@mit.edu", VERBOSITY_ERROR),
            new Recipient("mail@klemensmang.com", VERBOSITY_ERROR),
            new Recipient("jobachhu@mit.edu", VERBOSITY_DEBUG),
            new Recipient("rabanser@mit.edu", VERBOSITY_ERROR),
            new Recipient("carlosbp@mit.edu", VERBOSITY_ERROR)
    };
    private static final String FROM_EMAIL_ADDRESS = "no-reply@climatecolab.org";
    private static final String FROM_EMAIL_NAME = "MIT Climate CoLab";

    private final String subject;
    private final String body;
    private final int messageVerbosity;

    public EmailToAdminDispatcher(String subject, String body) {
        this(subject, body, VERBOSITY_ERROR);
    }

    public EmailToAdminDispatcher(String subject, String body, int verbosity) {
        this.subject = subject;
        this.body = body;
        this.messageVerbosity = verbosity;
    }

    public void sendMessage() {
        try {
            InternetAddress fromAddress = new InternetAddress(FROM_EMAIL_ADDRESS, FROM_EMAIL_NAME);
            MailEngine.send(fromAddress, getRecipientAddresses(), subject, body, true);
        } catch (MailEngineException | AddressException | UnsupportedEncodingException e) {
            _log.error("Could not send error message", e);
        }
    }

    private InternetAddress[] getRecipientAddresses() throws AddressException {
        List<InternetAddress> addressTo = new ArrayList<>();
        for (Recipient recipient : ADMIN_EMAIL_RECIPIENTS) {
            if (recipient.verbosity >= messageVerbosity) {
                addressTo.add(new InternetAddress(recipient.email));
            }
        }
        return addressTo.toArray(new InternetAddress[addressTo.size()]);
    }

    private static class Recipient {
        private final String email;
        private final int verbosity;

        Recipient(String email, int verbosity) {

            this.email = email;
            this.verbosity = verbosity;
        }
    }
}
