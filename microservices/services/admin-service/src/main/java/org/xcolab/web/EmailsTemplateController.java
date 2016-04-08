package org.xcolab.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value = "/emailTemplates/{emailTemplateType}", method = RequestMethod.GET)
    public ContestEmailTemplate getEmailTemplates(@PathVariable("emailTemplateType") String emailTemplateType) {
        return this.emailTemplateService.getEmailTemplate(emailTemplateType);
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateType}", method = RequestMethod.POST)
    public String updateEmailTemplates(@RequestBody ContestEmailTemplate contestEmailTemplate,
                                       @PathVariable("emailTemplateType") String emailTemplateType) {
        if (this.emailTemplateService.getEmailTemplate(emailTemplateType) != null) {
            this.emailTemplateService.updateEmailTemplate(contestEmailTemplate);
            return "Email template updated successfully";
        } else
            return "Email template not found";
    }

    @RequestMapping(value = "/emailTemplates/", method = RequestMethod.PUT)
    public ContestEmailTemplate createEmailTemplates(@RequestBody ContestEmailTemplate contestEmailTemplate) {
        this.emailTemplateService.createEmailTemplate(contestEmailTemplate);
        return this.emailTemplateService.getEmailTemplate(contestEmailTemplate.getType_());
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateType}", method = RequestMethod.DELETE)
    public Object deleteEmailTemplates(@PathVariable("emailTemplateType") String emailTemplateType) {
        return null;
    }
}
