package org.xcolab.service.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.EmailTemplate;
import org.xcolab.service.admin.domain.emailtemplate.EmailTemplateDao;
import org.xcolab.service.admin.exceptions.NotFoundException;
import org.xcolab.service.admin.service.EmailTemplateService;

import java.util.List;

@RestController
public class EmailTemplateController {

    @Autowired
    private EmailTemplateService emailTemplateService;

    @Autowired
    private EmailTemplateDao emailTemplateDao;

    @RequestMapping(value = "/emailTemplates", method = RequestMethod.GET)
    public List<EmailTemplate> listEmailTemplates() {

        return this.emailTemplateDao.listAllEmailTemplates();
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateType}", method = RequestMethod.GET)
    public EmailTemplate getEmailTemplates(@PathVariable String emailTemplateType)
            throws NotFoundException {
        final EmailTemplate emailTemplate = emailTemplateDao
                .getEmailTemplate(emailTemplateType);
        if (emailTemplate == null) {
            throw new NotFoundException();
        }
        return emailTemplate;
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateType}", method = RequestMethod.PUT)
    public boolean updateEmailTemplates(@RequestBody EmailTemplate contestEmailTemplate,
                                       @PathVariable String emailTemplateType) {
        return this.emailTemplateDao.getEmailTemplate(emailTemplateType) != null
                && emailTemplateDao.updateEmailTemplate(contestEmailTemplate);
    }

    @RequestMapping(value = "/emailTemplates", method = RequestMethod.POST)
    public EmailTemplate createEmailTemplates(@RequestBody EmailTemplate contestEmailTemplate) {
        emailTemplateDao.createEmailTemplate(contestEmailTemplate);
        return emailTemplateDao.getEmailTemplate(contestEmailTemplate.getName());
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateType}", method = RequestMethod.DELETE)
    public Object deleteEmailTemplates(@PathVariable("emailTemplateType") String emailTemplateType) {
        return null;
    }
}
