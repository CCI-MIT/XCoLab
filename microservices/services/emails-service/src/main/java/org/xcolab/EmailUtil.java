package org.xcolab;

import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.mail.Message;


@PropertySource({"classpath:application.properties", "file:${user.home}/.xcolab.application.properties"})

public class EmailUtil {

    @Autowired
    private Environment env;

    public void sendEmailToRecipient(String senderName, String senderEmail, String subject, String htmlBody,
                                     String txtBody, String toEmail) {
        final Email email = new Email();

        email.setFromAddress(senderName, senderEmail);
        email.setSubject(subject);
        email.addRecipient(null, toEmail, Message.RecipientType.TO);
        if (txtBody != null && !txtBody.isEmpty())
            email.setText(txtBody);
        email.setTextHTML(htmlBody);
        String smtpHost = env.getRequiredProperty("mail.smtp.host");
        String smtpPort = env.getRequiredProperty("mail.smtp.port");
        String userName = env.getRequiredProperty("mail.smtp.user");
        String password = env.getRequiredProperty("mail.smtp.pass");
        switch (env.getRequiredProperty("mail.smtp.transport.strategy")) {
            case "SSL":
                new Mailer(smtpHost, Integer.parseInt(smtpPort), userName, password, TransportStrategy.SMTP_TLS).sendMail(email);
            case "TSL":
                new Mailer(smtpHost, Integer.parseInt(smtpPort), userName, password, TransportStrategy.SMTP_SSL).sendMail(email);
            default:
                new Mailer(smtpHost, Integer.parseInt(smtpPort), userName, password).sendMail(email);
        }
    }
}
