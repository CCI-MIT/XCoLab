package org.xcolab.util;

import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.mail.Message;


@PropertySource({"classpath:application.properties", "file:${user.home}/.xcolab.application.properties"})
@Component
public class EmailUtil {

    @Autowired
    private Environment env;

    public void sendEmailToRecipient(String from, List<String> toEmails, String subject, String htmlBody,
                                     boolean isHtml, String replyTo) {
        final Email email = new Email();

        email.setFromAddress(null, from);
        email.setSubject(subject);

        if (replyTo != null && !replyTo.isEmpty()) {
            email.setReplyToAddress(null, replyTo);
        }

        for(String emailTo : toEmails) {
            email.addRecipient(null, emailTo, Message.RecipientType.TO);
        }

        if (isHtml) {
            email.setTextHTML(htmlBody);
        } else {
            email.setText(htmlBody);
        }

        String smtpHost = env.getRequiredProperty("mail.smtp.host");
        String smtpPort = env.getRequiredProperty("mail.smtp.port");
        String userName = env.getRequiredProperty("mail.smtp.user");
        String password = env.getRequiredProperty("mail.smtp.pass");
        switch (env.getRequiredProperty("mail.smtp.transport.strategy")) {
            case "TLS":
                new Mailer(smtpHost, Integer.parseInt(smtpPort), userName, password, TransportStrategy.SMTP_TLS).sendMail(email);
            case "SSL":
                new Mailer(smtpHost, Integer.parseInt(smtpPort), userName, password, TransportStrategy.SMTP_SSL).sendMail(email);
            default:
                new Mailer(smtpHost, Integer.parseInt(smtpPort), userName, password).sendMail(email);
        }
    }
}
