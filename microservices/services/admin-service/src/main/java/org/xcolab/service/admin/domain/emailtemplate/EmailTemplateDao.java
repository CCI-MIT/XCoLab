package org.xcolab.service.admin.domain.emailtemplate;

import org.xcolab.client.admin.pojo.IEmailTemplate;

import java.util.List;

public interface EmailTemplateDao {

    List<IEmailTemplate> listAllEmailTemplates();

    IEmailTemplate getEmailTemplate(String emailTemplateId);

    boolean updateEmailTemplate(IEmailTemplate contestEmailTemplate);

    void createEmailTemplate(IEmailTemplate contestEmailTemplate);
}
