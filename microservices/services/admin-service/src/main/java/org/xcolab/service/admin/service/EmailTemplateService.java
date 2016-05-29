package org.xcolab.service.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.admin.domain.emailtemplate.EmailTemplateDao;

@Service
public class EmailTemplateService {

    private final EmailTemplateDao emailTemplateDao;

    @Autowired
    public EmailTemplateService(EmailTemplateDao emailTemplateDao) {
        this.emailTemplateDao = emailTemplateDao;
    }
}
