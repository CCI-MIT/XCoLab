package org.xcolab.service.contest.utils.email;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.emails.EmailClient;

import java.util.ArrayList;
import java.util.List;

public class EmailToAdminDispatcher {

    private final static int VERBOSITY_ERROR = 1;
    private final static int VERBOSITY_DEBUG = 2;

    private static final Recipient[] ADMIN_EMAIL_RECIPIENTS = {
            new Recipient("jobachhu@mit.edu", VERBOSITY_DEBUG),
            new Recipient("carlosbp@mit.edu", VERBOSITY_ERROR),
            new Recipient("umov@mit.edu", VERBOSITY_ERROR),
            new Recipient("cwoebker@mit.edu", VERBOSITY_ERROR),
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
        String fromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
        String fromName = ConfigurationAttributeKey.COLAB_NAME.get();
        EmailClient.sendEmail(fromEmail,fromName, getRecipientAddresses(), subject, body, true,
                fromEmail,fromName,null);
    }

    private List<String> getRecipientAddresses() {
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
