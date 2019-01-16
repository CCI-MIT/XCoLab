package org.xcolab.client.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.email.pojo.IEmail;

import java.util.List;

@Component
@Profile("test")
public class EmailClientMockImpl implements IEmailClient {

    @Override
    public void sendEmail(IEmail email) {
        // do nothing
    }

    @Override
    public void sendEmail(String from, String fromName, String to, String subject, String emailBody,
            Boolean isHtml, String replyTo, String replyToName, Long referenceId) {
        // do nothing
    }

    @Override
    public void sendEmail(String from, String fromName, List<String> to, String subject,
            String emailBody, Boolean isHtml, String replyTo, String replyToName,
            Long referenceId) {
        // do nothing
    }
}
