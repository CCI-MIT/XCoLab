package org.xcolab.service.email.util;

import org.codemonkey.simplejavamail.MailException;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import org.xcolab.client.email.pojo.IEmail;

import javax.mail.Message;

@PropertySource({"file:${user.home}/.xcolab.application.properties"})
@Component
public class EmailService {

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    private final Environment env;

    @Autowired
    public EmailService(Environment env) {
        this.env = env;
    }

    public void sendEmailToRecipient(IEmail emailPojo) {
        final Email email = new Email();

        try {
            email.setFromAddress(emailPojo.getFromName(), emailPojo.getFrom());
            email.setSubject(emailPojo.getSubject());

            if (emailPojo.getReplyTo() != null && !emailPojo.getReplyTo().isEmpty()) {
                email.setReplyToAddress(emailPojo.getReplyToName(), emailPojo.getReplyTo());
            }

            for (String emailTo : emailPojo.getTo()) {
                email.addRecipient(null, emailTo, Message.RecipientType.TO);
            }

            if (emailPojo.isHtml()) {
                email.setTextHTML(emailPojo.getEmailBody());
            } else {
                email.setText(emailPojo.getEmailBody());
            }

            String smtpHost = env.getRequiredProperty("mail.smtp.host");
            String smtpPort = env.getRequiredProperty("mail.smtp.port");
            String userName = env.getRequiredProperty("mail.smtp.user");
            String password = env.getRequiredProperty("mail.smtp.pass");
            switch (env.getRequiredProperty("mail.smtp.transport.strategy")) {
                case "TLS":
                    new Mailer(smtpHost, Integer.parseInt(smtpPort), userName, password,
                            TransportStrategy.SMTP_TLS).sendMail(email);
                    break;
                case "SSL":
                    new Mailer(smtpHost, Integer.parseInt(smtpPort), userName, password,
                            TransportStrategy.SMTP_SSL).sendMail(email);
                    break;
                default:
                    new Mailer(smtpHost, Integer.parseInt(smtpPort), userName, password)
                            .sendMail(email);
            }
        } catch (MailException e) {
            log.error("Failed to send email {}", emailPojo.getSubject(), e);
        }
    }
}
