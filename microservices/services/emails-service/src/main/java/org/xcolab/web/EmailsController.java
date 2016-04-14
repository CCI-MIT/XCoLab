package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.util.EmailUtil;

import java.util.List;

@RestController
public class EmailsController {

    @Autowired
    private EmailUtil emailUtil;

    @RequestMapping(value = "/sendEmail", method = RequestMethod.PUT)
    public String sendEmail(@RequestParam String from,
                            @RequestParam("to[]") List<String> to,
                            @RequestParam String subject,
                            @RequestParam String emailBody,
                            @RequestParam Boolean isHtml,
                            @RequestParam(required = false, defaultValue = "") String replyTo) {


        emailUtil.sendEmailToRecipient(from, to, subject, emailBody, isHtml, replyTo);

        return "Email sent successfully";
    }
}/**/
