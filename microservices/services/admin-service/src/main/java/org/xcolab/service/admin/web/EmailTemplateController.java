package org.xcolab.service.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.service.admin.domain.emailtemplate.EmailTemplateDao;
import org.xcolab.service.admin.exceptions.NotFoundException;

import java.util.List;

@RestController
public class EmailTemplateController {

    @Autowired
    private EmailTemplateDao emailTemplateDao;

    @RequestMapping(value = "/emailTemplates", method = RequestMethod.GET)
    public List<IEmailTemplate> listEmailTemplates() {
        return this.emailTemplateDao.listAllEmailTemplates();
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateType}", method = RequestMethod.GET)
    public IEmailTemplate getEmailTemplates(@PathVariable String emailTemplateType)
            throws NotFoundException {
        final IEmailTemplate emailTemplate = emailTemplateDao
                .getEmailTemplate(emailTemplateType);
        if (emailTemplate == null) {
            throw new NotFoundException();
        }
        return emailTemplate;
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateType}", method = RequestMethod.PUT)
    public boolean updateEmailTemplates(@RequestBody IEmailTemplate contestEmailTemplate,
                                       @PathVariable String emailTemplateType) {
        return this.emailTemplateDao.getEmailTemplate(emailTemplateType) != null
                && emailTemplateDao.updateEmailTemplate(contestEmailTemplate);
    }

    @RequestMapping(value = "/emailTemplates", method = RequestMethod.POST)
    public IEmailTemplate createEmailTemplates(@RequestBody IEmailTemplate contestEmailTemplate) {
        emailTemplateDao.createEmailTemplate(contestEmailTemplate);
        return emailTemplateDao.getEmailTemplate(contestEmailTemplate.getName());
    }

    @RequestMapping(value = "/emailTemplates/{emailTemplateType}", method = RequestMethod.DELETE)
    public Object deleteEmailTemplates(@PathVariable("emailTemplateType") String emailTemplateType) {
        return null;
    }
}
