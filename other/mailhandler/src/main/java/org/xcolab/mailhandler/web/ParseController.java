package org.xcolab.mailhandler.web;

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

import org.xcolab.mailhandler.config.MailProperties;
import org.xcolab.mailhandler.config.MailProperties.Domain;
import org.xcolab.mailhandler.config.MailProperties.Mapping;
import org.xcolab.mailhandler.config.MailProperties.SpamSettings;
import org.xcolab.mailhandler.pojo.MatchedMapping;
import org.xcolab.mailhandler.util.SpamReportHelper;

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
    private final String smtpHost;
    private final int smtpPort;
    private final String smtpUsername;
    private final String smtpPassword;
    private final String smtpConnection;
    private final SpamSettings spamSettings;

    @Autowired
    public ParseController(MailProperties mailProperties) {
        this.mailProperties = mailProperties;
        smtpHost = mailProperties.getSmtp().getHost();
        smtpPort = mailProperties.getSmtp().getPort();
        smtpUsername = mailProperties.getSmtp().getUser();
        smtpPassword = mailProperties.getSmtp().getPass();
        smtpConnection = mailProperties.getSmtp().getTransport();
        spamSettings = mailProperties.getSpam();
    }

    @RequestMapping
    public ResponseEntity<String> handle(HttpServletRequest request, HttpServletResponse response,
            @RequestParam String to, @RequestParam String from, @RequestParam String subject,
            @RequestParam (required = false) String html,
            @RequestParam (required = false) String text,
            @RequestParam (value = "spam_score", required = false) Float spamScore,
            @RequestParam (value = "spam_report", required = false) String spamReport) {

        if (html == null && text == null) {
            log.error("Email has neither text nor html parameter.");
            return ResponseEntity.badRequest().body("Need to specify at least one of html or text");
        }

        SpamReportHelper spamReportHelper = new SpamReportHelper(spamScore, spamReport, spamSettings);

        if (spamReportHelper.shouldFilterMessage()) {
            return ResponseEntity.ok("Message processed successfully; marked as spam.");
        }

        List<MatchedMapping> matchedMappings = extractMappingsFromToField(to);
        if (matchedMappings.isEmpty()) {
            if (!to.contains("no-reply@")) {
                log.warn("No mappings found for to = {}.", to);
            }
            return ResponseEntity.notFound().build();
        }

        final Collection<MultipartFile> attachments = getAttachments(request);
        try {
            for (MatchedMapping matchedMapping : matchedMappings) {
                sendEmail(matchedMapping.getEmailAddress(), from,
                        spamReportHelper.shouldShowSpamWarning()
                                ? spamReportHelper.getWarning() + subject : subject,
                        spamReportHelper.shouldShowSpamReport()
                                ? html + spamReportHelper.formatSpamReport(true) : html,
                        spamReportHelper.shouldShowSpamReport()
                                ? text + spamReportHelper.formatSpamReport(false) : text,
                        matchedMapping.getMapping().getRecipients(), attachments);
            }
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

    private List<MatchedMapping> extractMappingsFromToField(String to) {
        List<MatchedMapping> matchedMappings = new ArrayList<>();
        final List<Domain> candidateDomains = mailProperties.getDomains().stream()
                .filter(domain -> to.contains(domain.getDomain()))
                .collect(Collectors.toList());
        for (Domain domain : candidateDomains) {
            for (Mapping mapping : domain.getMappings()) {
                if (to.contains(mapping.getUsername() + "@" + domain.getDomain())) {
                    matchedMappings.add(new MatchedMapping(domain, mapping));
                }
            }
        }
        return matchedMappings;
    }

    private void sendEmail(String fromAddress, String originalSender, String subject, String html,
            String text, List<String> recipients, Collection<MultipartFile> attachments) {
        final Email email = new Email();
        email.setFromAddress(null, fromAddress);
        email.setReplyToAddress(null, extractEmail(originalSender));
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
