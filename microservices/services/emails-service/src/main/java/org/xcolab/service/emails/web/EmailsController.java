package org.xcolab.service.emails.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.service.emails.pojo.Email;
import org.xcolab.service.emails.util.EmailUtil;

@RestController
public class EmailsController {

    @Autowired
    private EmailUtil emailUtil;

    @RequestMapping(value = "/emails/send", method = RequestMethod.POST)
    public String sendEmail(@RequestBody Email email) {

        emailUtil.sendEmailToRecipient(email);

        return "Email sent successfully";
    }
}
