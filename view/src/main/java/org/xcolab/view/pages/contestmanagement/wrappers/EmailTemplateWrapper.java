package org.xcolab.view.pages.contestmanagement.wrappers;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.EmailTemplate;

import java.util.List;

public class EmailTemplateWrapper {

    private EmailTemplate emailTemplate;
    private Boolean createNew = false;

    public EmailTemplateWrapper() {
        emailTemplate = new EmailTemplate();
    }

    public EmailTemplateWrapper(String templateType) {
        initEmailTemplate(templateType);
    }

    private void initEmailTemplate(String templateType) {
        if (templateType != null) {
            emailTemplate = EmailTemplateClientUtil.getContestEmailTemplateByType(templateType);
        } else {
            List<EmailTemplate> contestScheduleList =
                    EmailTemplateClientUtil.listAllContestEmailTemplates();
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
        emailTemplate = EmailTemplateClientUtil.createEmailTemplate(emailTemplate);
    }

    private void persistUpdatedSchedule() {
        EmailTemplateClientUtil.updateContestEmailTemplate(emailTemplate);
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
