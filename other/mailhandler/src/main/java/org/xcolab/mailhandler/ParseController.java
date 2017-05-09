package org.xcolab.mailhandler;

import org.codemonkey.simplejavamail.MailException;
import org.codemonkey.simplejavamail.Mailer;
import org.codemonkey.simplejavamail.TransportStrategy;
import org.codemonkey.simplejavamail.email.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import org.xcolab.mailhandler.MailProperties.Domain;
import org.xcolab.mailhandler.MailProperties.EmailAddress;
import org.xcolab.mailhandler.MailProperties.Mapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.mail.Message;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ParseController {

    private static final Logger log = LoggerFactory.getLogger(ParseController.class);

    private final MailProperties mailProperties;
    private final Pattern EMAIL_PATTERN = Pattern.compile(
            "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b", Pattern.CASE_INSENSITIVE);

    @Autowired
    public ParseController(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @RequestMapping
    public ResponseEntity<String> handle(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String to, @RequestParam String from, @RequestParam String subject,
            @RequestParam (required = false) String html,
            @RequestParam (required = false) String text,
            @RequestParam (value = "spam_score", required = false) Float spamScore,
            @RequestParam (value = "spam_report", required = false) String spamReport) {


        final String smtpHost = mailProperties.getSmtp().getHost();
        final int smtpPort = mailProperties.getSmtp().getPort();
        final String smtpUsername = mailProperties.getSmtp().getUser();
        final String smtpPassword = mailProperties.getSmtp().getPass();
        final String smtpConnection = mailProperties.getSmtp().getTransport();

        if (html == null && text == null) {
            log.error("Email has not text or html parameter.");
            return ResponseEntity.badRequest().body("Need to specify at least one of html or text");
        }

        List<String> recipients = extractRecipientsFromMappings(to);
        if (recipients.isEmpty()) {
            if (!to.contains("no-reply@")) {
                log.warn("No mappings found for to = {}.", to);
            }
            return ResponseEntity.notFound().build();
        }

        final Collection<MultipartFile> attachments = getAttachments(request);
        try {
            sendEmail(from, subject, html, text, smtpHost, smtpPort, smtpUsername, smtpPassword,
                    smtpConnection, recipients, attachments);
        } catch (MailException e) {
            log.error("Exception while sending mail:", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

        return ResponseEntity.ok("Email sent.");
    }

    private Collection<MultipartFile> getAttachments(HttpServletRequest request) {
        final Map<String, MultipartFile> fileMap;
        if (request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            fileMap = multipartRequest.getFileMap();
        } else {
            fileMap = Collections.emptyMap();
        }
        return fileMap.values();
    }

    private List<String> extractRecipientsFromMappings(@RequestParam String to) {
        List<String> recipients = new ArrayList<>();
        final List<Domain> candidateDomains = mailProperties.getDomains().stream()
                .filter(domain -> to.contains(domain.getDomain()))
                .collect(Collectors.toList());
        for (Domain domain : candidateDomains) {
            for (Mapping mapping : domain.getMappings()) {
                if (to.contains(mapping.getUsername() + "@" + domain.getDomain())) {
                    recipients.addAll(mapping.getRecipients());
                }
            }
        }
        return recipients;
    }

    private void sendEmail(@RequestParam String from, @RequestParam String subject,
            @RequestParam String html, @RequestParam String text, String smtpHost, int smtpPort,
            String smtpUsername, String smtpPassword, String smtpConnection,
            List<String> recipients, Collection<MultipartFile> attachments) {
        final Email email = new Email();
        final EmailAddress sender = mailProperties.getFrom();
        email.setFromAddress(sender.getName(), sender.getEmail());
        email.setReplyToAddress(null, extractEmail(from));
        email.setSubject("forwarded message: " + subject);

        for (String recipient : recipients) {
            email.addRecipient(null, recipient, Message.RecipientType.TO);
        }

        if (html != null && !html.isEmpty()) {
            email.setTextHTML(html);
        } else {
            email.setText(text);
        }
        for (MultipartFile attachment : attachments) {
            try {
                email.addAttachment(attachment.getOriginalFilename(), attachment.getBytes(),
                        attachment.getContentType());
            } catch (IOException e) {
                log.error("Could not forward attachment {} for email {}",
                        attachment.getOriginalFilename(), subject);
            }
        }

        switch (smtpConnection) {
            case "TLS":
                new Mailer(smtpHost, smtpPort, smtpUsername, smtpPassword,
                        TransportStrategy.SMTP_TLS).sendMail(email);
                break;
            case "SSL":
                new Mailer(smtpHost, smtpPort, smtpUsername, smtpPassword,
                        TransportStrategy.SMTP_SSL).sendMail(email);
                break;
            default:
                new Mailer(smtpHost, smtpPort, smtpUsername, smtpPassword).sendMail(email);
        }
    }

    private String extractEmail(String emailField) {
        final List<String> emails = extractEmails(emailField);
        if (emails.size() == 1) {
            return emails.get(0);
        }
        throw new IllegalArgumentException("Field contained more than one email: "+ emailField);
    }

    private List<String> extractEmails(String emailField) {
        List<String> emails = new ArrayList<>();
        Matcher m = EMAIL_PATTERN.matcher(emailField);
        while (m.find()) {
            emails.add(m.group());
        }
        return emails;
    }
}
