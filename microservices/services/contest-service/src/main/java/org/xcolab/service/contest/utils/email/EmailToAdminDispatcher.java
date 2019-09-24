package org.xcolab.service.contest.utils.email;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.email.IEmailClient;

import java.util.ArrayList;
import java.util.List;

public class EmailToAdminDispatcher {

    private static final int VERBOSITY_ERROR = 1;
    private static final int VERBOSITY_DEBUG = 2;

    private static final Recipient[] ADMIN_EMAIL_RECIPIENTS = {
            new Recipient("jobachhu@mit.edu", VERBOSITY_DEBUG),
            new Recipient("carlosbp@mit.edu", VERBOSITY_ERROR),
    };

    private final String subject;
    private final String body;
    private final int messageVerbosity;
    private final IEmailClient emailClient;



    public EmailToAdminDispatcher(String subject, String body, int verbosity,IEmailClient emailClient) {
        this.subject = subject;
        this.body = body;
        this.messageVerbosity = verbosity;
        this.emailClient = emailClient;
    }

    public void sendMessage() {
        String fromEmail = ConfigurationAttributeKey.ADMIN_FROM_EMAIL.get();
        String fromName = ConfigurationAttributeKey.COLAB_NAME.get();
        emailClient.sendEmail(fromEmail,fromName, getRecipientAddresses(), subject, body, true,
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
