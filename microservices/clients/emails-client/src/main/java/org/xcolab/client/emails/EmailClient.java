package org.xcolab.client.emails;

import org.xcolab.client.emails.pojo.Email;
import org.xcolab.commons.http.client.ServiceResource;
import org.xcolab.commons.http.client.ServiceResource1;

import java.util.ArrayList;
import java.util.List;

public final class EmailClient {

    private static final ServiceResource emailResource =
            new ServiceResource1(EmailResource.EMAIL);

    private EmailClient() {
    }

    public static void sendEmail(String from,String fromName, String to, String subject, String emailBody, Boolean isHtml, String replyTo, String replyToName, Long referenceId) {
        List<String> toAdd = new ArrayList<>();
        toAdd.add(to);
        sendEmail(from, fromName, toAdd, subject, emailBody, isHtml, replyTo, replyToName, referenceId);
    }

    public static void sendEmail(String from, String fromName, List<String> to, String subject, String emailBody, Boolean isHtml, String replyTo, String replyToName, Long referenceId) {
        Email email = new Email();
        email.setFrom(from);
        email.setFromName(fromName);
        email.setTo(to);
        email.setSubject(subject);
        email.setEmailBody(emailBody);
        email.setHtml(isHtml);
        email.setReplyTo(((replyTo == null ? ("") : (replyTo))));
        email.setReplyToName(((replyToName == null ? ("") : (replyToName))));
        email.setReferenceId(referenceId);

        emailResource.service("send", String.class).post(email);
    }
}
