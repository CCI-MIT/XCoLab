package org.xcolab.client.emails;

import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.emails.pojo.Email;
import org.xcolab.util.http.RequestUtils;

import java.util.ArrayList;
import java.util.List;

public final class EmailClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:"+RequestUtils.getServicesPort()+"/emails-service";

    private EmailClient() {
    }

    public static void sendEmail(String from, String to, String subject, String emailBody, Boolean isHtml, String replyTo) {

        List<String> toAdd = new ArrayList<>();
        toAdd.add(to);
        sendEmail(from, toAdd, subject, emailBody, isHtml, replyTo);
    }

    public static void sendEmail(String from, List<String> to, String subject, String emailBody, Boolean isHtml, String replyTo) {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/sendEmail");

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
