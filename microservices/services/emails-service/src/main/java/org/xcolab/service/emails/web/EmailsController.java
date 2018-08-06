package org.xcolab.service.emails.web;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.OutgoingEmail;
import org.xcolab.service.emails.domain.OutgoingEmailDao;
import org.xcolab.service.emails.pojo.Email;
import org.xcolab.service.emails.util.EmailService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class EmailsController {

    private final EmailService emailService;

    private final OutgoingEmailDao colabEmailDao;

    @Autowired
    public EmailsController(OutgoingEmailDao colabEmailDao, EmailService emailService) {
        this.colabEmailDao = colabEmailDao;
        this.emailService = emailService;
    }

    @PostMapping("/emails/send")
    public String sendEmail(@RequestBody Email email) {

        boolean shouldSendEmail = true;
        if (email.getTo().size() == 1) {
            List<OutgoingEmail> colabEmails = colabEmailDao
                    .findByGiven(email.getSubject(), email.getTo().get(0), email.getReferenceId(),
                            hashEmail(email.getEmailBody()));
            shouldSendEmail = colabEmails.isEmpty();
        }

        createColabEmailFromEmail(email,shouldSendEmail);
        if (shouldSendEmail) {
            emailService.sendEmailToRecipient(email);
        }
        return "Email sent successfully";
    }
    private void createColabEmailFromEmail(Email email, boolean sentStatus){
        for(String recipient: email.getTo()) {
            OutgoingEmail ce = new OutgoingEmail();
            ce.setSentAt(new Timestamp(new Date().getTime()));
            ce.setEmailBody(email.getEmailBody());
            ce.setEmailBodyHash(hashEmail(email.getEmailBody()));
            ce.setReferenceId(email.getReferenceId());
            ce.setEmailTo(recipient);
            ce.setEmailFrom(email.getFrom());
            ce.setEmailSubject(email.getSubject());
            ce.setWasSent(sentStatus);
            colabEmailDao.create(ce);
        }
    }

    private String hashEmail(String emailBody) {

        return DigestUtils.md5Hex(emailBody);
    }
}
