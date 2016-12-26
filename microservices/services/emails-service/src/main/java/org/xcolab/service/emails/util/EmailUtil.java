package org.xcolab.service.emails.util;

import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.mail.Message;

//"classpath:application.properties",
@PropertySource({"file:${user.home}/.xcolab.application.properties"})
@Component
public class EmailUtil {

    @Autowired
    private Environment env;

    public void sendEmailToRecipient(org.xcolab.service.emails.pojo.Email emailPojo) {
        final Email email = new Email();

        try {
            email.setFromAddress(null, emailPojo.getFrom());
            email.setSubject(emailPojo.getSubject());

            if (emailPojo.getReplyTo() != null && !emailPojo.getReplyTo().isEmpty()) {
                email.setReplyToAddress(null, emailPojo.getReplyTo());
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
        }catch (Exception ignore){

        }
    }
}
