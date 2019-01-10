package org.xcolab.client.email;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.xcolab.client.email.pojo.Email;

import java.util.ArrayList;
import java.util.List;

@FeignClient("xcolab-email-service")
public interface IEmailClient {

    @PostMapping("/emails/send")
    void sendEmail(@RequestBody Email email);


    default void sendEmail(String from,String fromName, String to, String subject, String emailBody, Boolean isHtml, String replyTo, String replyToName, Long referenceId) {
        List<String> toAdd = new ArrayList<>();
        toAdd.add(to);
        sendEmail(from, fromName, toAdd, subject, emailBody, isHtml, replyTo, replyToName, referenceId);
    }

    default void sendEmail(String from, String fromName, List<String> to, String subject, String emailBody, Boolean isHtml, String replyTo, String replyToName, Long referenceId) {
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
    }

}
