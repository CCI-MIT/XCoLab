package org.xcolab.client.email;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import org.xcolab.client.email.pojo.IEmail;
import org.xcolab.client.email.pojo.IOutgoingEmail;

import java.util.ArrayList;
import java.util.List;

@Component
@Profile("test")
public class EmailClientMockImpl implements IEmailClient {

    @Override
    public List<IOutgoingEmail> getSentEmails(@PathVariable int numOfEmails) {
        return new ArrayList<IOutgoingEmail>();
    }

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
