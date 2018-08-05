package org.xcolab.service.admin.domain.emailtemplate;

import org.xcolab.model.tables.pojos.EmailTemplate;

import java.util.List;

public interface EmailTemplateDao {

    List<EmailTemplate> listAllEmailTemplates();

    EmailTemplate getEmailTemplate(String emailTemplateId);

    boolean updateEmailTemplate(EmailTemplate contestEmailTemplate);

    void createEmailTemplate(EmailTemplate contestEmailTemplate);
}
