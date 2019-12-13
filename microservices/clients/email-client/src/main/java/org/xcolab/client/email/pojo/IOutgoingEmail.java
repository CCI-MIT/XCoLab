package org.xcolab.client.email.pojo;

import java.sql.Timestamp;

public interface IOutgoingEmail {

    Long getId();

    void setId(Long id);

    String getEmailSubject();

    void setEmailSubject(String emailSubject);

    String getEmailBody();

    void setEmailBody(String emailBody);

    String getEmailTo();

    void setEmailTo(String emailTo);

    String getEmailFrom();

    void setEmailFrom(String emailFrom);

    Timestamp getSentAt();

    void setSentAt(Timestamp sentAt);

    Long getReferenceId();

    void setReferenceId(Long referenceId);

    String getEmailBodyHash();

    void setEmailBodyHash(String emailBodyHash);

    Boolean isWasSent();

    void setWasSent(Boolean wasSent);




}
