package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.admin.pojo.tables.pojos.EmailTemplate;

import java.util.List;

public class EmailTemplateWrapper {

    private IEmailTemplate emailTemplate;
    private Boolean createNew = false;

    public EmailTemplateWrapper() {
        emailTemplate = new EmailTemplate();
    }

    public EmailTemplateWrapper(String templateType) {
        initEmailTemplate(templateType);
    }

    private void initEmailTemplate(String templateType) {
        if (templateType != null) {
            emailTemplate =
                    StaticAdminContext.getEmailTemplateClient().getEmailTemplate(templateType);
        } else {
            List<IEmailTemplate> contestScheduleList =
                    StaticAdminContext.getEmailTemplateClient().listEmailTemplates();
            emailTemplate = contestScheduleList.get(0);
        }
    }

    public Boolean getCreateNew() {
        return createNew;
    }

    public void setCreateNew(Boolean createNew) {
        this.createNew = createNew;
    }

    public void persist() {
        if (createNew) {
            createTemplateFromExisting();
        } else {
            persistUpdatedSchedule();
        }
    }

    private void createTemplateFromExisting() {
        emailTemplate =
                StaticAdminContext.getEmailTemplateClient().createEmailTemplate(emailTemplate);
    }

    private void persistUpdatedSchedule() {
        StaticAdminContext.getEmailTemplateClient().updateEmailTemplate(emailTemplate);
    }

    public String getType() {
        return emailTemplate.getName();
    }

    public void setType(String type) {
        emailTemplate.setName(type);
    }

    public String getSubject() {
        return emailTemplate.getSubject();
    }

    public void setSubject(String subject) {
        emailTemplate.setSubject(subject);
    }

    public String getContent() {
        return emailTemplate.getHeader();
    }

    public void setContent(String content) {
        emailTemplate.setHeader(content);
    }

    public String getFooter() {
        return emailTemplate.getFooter();
    }

    public void setFooter(String footer) {
        emailTemplate.setFooter(footer);
    }
}
