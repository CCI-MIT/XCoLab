package org.xcolab.mail;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.emails.EmailClient;
import org.xcolab.utils.TemplateReplacementUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by Mente on 29/07/15.
 */
public class EmailToAdminDispatcher {

    private static final Log _log = LogFactoryUtil.getLog(EmailToAdminDispatcher.class);

    public final static int VERBOSITY_ERROR = 1;
    public final static int VERBOSITY_DEBUG = 2;

    private static final Recipient[] ADMIN_EMAIL_RECIPIENTS = {
            new Recipient("pdeboer@mit.edu", VERBOSITY_ERROR),
            new Recipient("jobachhu@mit.edu", VERBOSITY_DEBUG),
            new Recipient("rabanser@mit.edu", VERBOSITY_ERROR),
            new Recipient("carlosbp@mit.edu", VERBOSITY_ERROR)
    };

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
            InternetAddress fromAddress = TemplateReplacementUtil.getAdminFromEmailAddress();
            //MailEngine.send(fromAddress, getRecipientAddresses(), subject, body, true);
            EmailClient.sendEmail(fromAddress.getAddress(), getRecipientAddresses(), subject, body, true, fromAddress.getAddress());
        } catch (AddressException | UnsupportedEncodingException | SystemException e) {
            _log.error("Could not send error message", e);
        }
    }

    private List<String> getRecipientAddresses() throws AddressException {
        List<String> addressTo = new ArrayList<>();
        for (Recipient recipient : ADMIN_EMAIL_RECIPIENTS) {
            if (recipient.verbosity >= messageVerbosity) {
                addressTo.add((recipient.email));
            }
        }
        return addressTo;
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
