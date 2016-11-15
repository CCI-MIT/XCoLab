package org.xcolab.portlets.contestmanagement.wrappers;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;

import java.util.List;

public class EmailTemplateWrapper {

    private final static Log _log = LogFactoryUtil.getLog(EmailTemplateWrapper.class);

    private ContestEmailTemplate emailTemplate;
    private Boolean createNew = false;

    public EmailTemplateWrapper() {
        emailTemplate = new ContestEmailTemplate();
    }

    public EmailTemplateWrapper(String templateType) {
        initEmailTemplate(templateType);
    }

    private void initEmailTemplate(String templateType) {
        if (templateType != null) {
            emailTemplate = EmailTemplateClientUtil.getContestEmailTemplateByType(templateType);
        } else {
            List<ContestEmailTemplate> contestScheduleList =
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
        return emailTemplate.getType_();
    }

    public void setType(String type) {
        emailTemplate.setType_(type);
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

    private static void removeEmailTemplate(String templateType) {
        //TODO
    }
}
