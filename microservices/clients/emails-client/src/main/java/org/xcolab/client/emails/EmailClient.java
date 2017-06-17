package org.xcolab.client.emails;

import org.xcolab.client.emails.pojo.Email;
import org.xcolab.util.clients.CoLabService;
import org.xcolab.util.http.ServiceRequestUtils;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.client.ServiceResource;
import org.xcolab.util.http.client.ServiceResource1;

import java.util.ArrayList;
import java.util.List;

public final class EmailClient {
    private static final RestService emailService = new RestService(CoLabService.EMAIL,
            ServiceRequestUtils.getNamespace());
    private static final ServiceResource emailResource = new ServiceResource1(emailService, "emails");

    private EmailClient() {
    }

    public static void sendEmail(String from, String to, String subject, String emailBody, Boolean isHtml, String replyTo, Long referenceId) {
        List<String> toAdd = new ArrayList<>();
        toAdd.add(to);
        sendEmail(from, toAdd, subject, emailBody, isHtml, replyTo,referenceId);
    }

    public static void sendEmail(String from, List<String> to, String subject, String emailBody, Boolean isHtml, String replyTo, Long referenceId) {
        Email email = new Email();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setEmailBody(emailBody);
        email.setHtml(isHtml);
        email.setReplyTo(((replyTo == null ? ("") : (replyTo))));
        email.setReferenceId(referenceId);

        emailResource.service("send", String.class).post(email);
    }
}
