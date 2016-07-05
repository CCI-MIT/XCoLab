package org.xcolab.client.emails;

import org.xcolab.client.emails.pojo.Email;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestService;

import java.util.ArrayList;
import java.util.List;

public final class EmailClient {

    private static final RestService emailService = new RestService("emails-service");

    private EmailClient() {
    }

    public static void sendEmail(String from, String to, String subject, String emailBody, Boolean isHtml, String replyTo) {

        List<String> toAdd = new ArrayList<>();
        toAdd.add(to);
        sendEmail(from, toAdd, subject, emailBody, isHtml, replyTo);
    }

    public static void sendEmail(String from, List<String> to, String subject, String emailBody, Boolean isHtml, String replyTo) {

        final UriBuilder uriBuilder = emailService.getBaseUrl().path("/sendEmail");

        Email email = new Email();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setEmailBody(emailBody);
        email.setHtml(isHtml);
        email.setReplyTo(((replyTo == null ? ("") : (replyTo))));

        RequestUtils.post(uriBuilder, email, String.class);
    }

}
