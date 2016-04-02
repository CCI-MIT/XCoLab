package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.EmailUtil;

import java.util.List;

@RestController
public class EmailsController {

    @Autowired
    private EmailUtil emailUtil;

    @RequestMapping(value = "/sendEmail", method = RequestMethod.PUT)
    public String listEmailTemplates(@RequestParam String senderName,
                                     @RequestParam String senderEmail,
                                     @RequestParam String subject,
                                     @RequestParam String emailBody,
                                     @RequestParam(required = false, defaultValue = "") String emailTxt,
                                     @RequestParam List<String> recipients) {

        for (String recipient : recipients) {
            emailUtil.sendEmailToRecipient(senderName, senderEmail, subject, emailBody, emailTxt, recipient);
        }
        return "Email sent successfully";
    }
}
