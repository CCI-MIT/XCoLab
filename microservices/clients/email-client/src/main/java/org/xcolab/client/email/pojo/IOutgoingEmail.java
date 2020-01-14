package org.xcolab.client.email.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.xcolab.client.email.pojo.tables.pojos.OutgoingEmail;
import java.sql.Timestamp;

@JsonDeserialize(as = OutgoingEmail.class)
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
