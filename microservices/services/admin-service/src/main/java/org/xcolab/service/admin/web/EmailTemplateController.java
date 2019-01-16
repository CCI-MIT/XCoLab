package org.xcolab.service.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.IEmailTemplateClient;
import org.xcolab.client.admin.exceptions.EmailTemplateNotFoundException;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.service.admin.domain.emailtemplate.EmailTemplateDao;

import java.util.List;

@RestController
public class EmailTemplateController implements IEmailTemplateClient {

    @Autowired
    private EmailTemplateDao emailTemplateDao;

    @Override
    @GetMapping("/emailTemplates")
    public List<IEmailTemplate> listEmailTemplates() {
        return this.emailTemplateDao.listAllEmailTemplates();
    }

    @GetMapping("/emailTemplates/{emailTemplateType}")
    public IEmailTemplate getEmailTemplate(@PathVariable String emailTemplateType) {
        IEmailTemplate emailTemplate = emailTemplateDao.getEmailTemplate(emailTemplateType);
        if (emailTemplate == null) {
            throw new EmailTemplateNotFoundException(emailTemplateType);
        }
        return emailTemplate;
    }

    @Override
    @PutMapping("/emailTemplates")
    public boolean updateEmailTemplate(@RequestBody IEmailTemplate emailTemplate) {
        return this.emailTemplateDao.getEmailTemplate(emailTemplate.getName()) != null
                && emailTemplateDao.updateEmailTemplate(emailTemplate);
    }

    @Override
    @PostMapping("/emailTemplates")
    public IEmailTemplate createEmailTemplate(@RequestBody IEmailTemplate emailTemplate) {
        emailTemplateDao.createEmailTemplate(emailTemplate);
        return emailTemplateDao.getEmailTemplate(emailTemplate.getName());
    }
}
