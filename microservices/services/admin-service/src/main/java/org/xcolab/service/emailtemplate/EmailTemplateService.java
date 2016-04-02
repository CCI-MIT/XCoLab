package org.xcolab.service.emailtemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.domain.emailtemplate.EmailTemplateDao;
import org.xcolab.model.tables.pojos.ContestEmailTemplate;

import java.util.List;

@Service
public class EmailTemplateService {

    private final EmailTemplateDao emailTemplateDao;

    @Autowired
    public EmailTemplateService(EmailTemplateDao emailTemplateDao) {
        this.emailTemplateDao = emailTemplateDao;
    }

    public List<ContestEmailTemplate> listAllEmailTemplate() {
        return this.emailTemplateDao.listAllEmailTemplates();
    }

    public ContestEmailTemplate getEmailTemplate(String emailTemplateId) {
        return this.emailTemplateDao.getEmailTemplate(emailTemplateId);
    }
}
