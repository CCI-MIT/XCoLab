package org.xcolab.client.email.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.email.pojo.tables.pojos.Email;

import java.util.List;

@JsonDeserialize(as = Email.class)
public interface IEmail {

    String getReplyTo();

    void setReplyTo(String replyTo);

    String getFrom();

    void setFrom(String from);

    List<String> getTo();

    void setTo(List<String> to);

    String getSubject();

    void setSubject(String subject);

    String getEmailBody();

    void setEmailBody(String emailBody);

    Boolean isHtml();

    void setHtml(Boolean html);

    Long getReferenceId();

    void setReferenceId(Long referenceId);

    String getFromName();

    void setFromName(String fromName);

    String getReplyToName();

    void setReplyToName(String replyToName);
}
