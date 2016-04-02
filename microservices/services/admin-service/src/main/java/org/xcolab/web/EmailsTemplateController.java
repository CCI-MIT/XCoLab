package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ContestEmailTemplate;
import org.xcolab.service.emailtemplate.EmailTemplateService;

import java.util.List;

@RestController
public class EmailsTemplateController {

    @Autowired
    private EmailTemplateService emailTemplateService;

    @RequestMapping("/emailTemplates")
    public List<ContestEmailTemplate> listEmailTemplates() {

        return this.emailTemplateService.listAllEmailTemplate();
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateId}", method = RequestMethod.GET)
    public ContestEmailTemplate getEmailTemplates(@PathVariable("emailTemplateId") String emailTemplateId) {
        return this.emailTemplateService.getEmailTemplate(emailTemplateId);
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateId}", method = RequestMethod.POST)
    public Object updateEmailTemplates(@PathVariable("emailTemplateId") String emailTemplateId) {
        return null;
    }

    @RequestMapping(value = "/emailTemplates/", method = RequestMethod.PUT)
    public Object createEmailTemplates() {
        return null;
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateId}", method = RequestMethod.DELETE)
    public Object deleteEmailTemplates(@PathVariable("emailTemplateId") String emailTemplateId) {
        return null;
    }
}
