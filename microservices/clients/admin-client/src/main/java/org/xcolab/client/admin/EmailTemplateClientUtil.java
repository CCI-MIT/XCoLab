package org.xcolab.client.admin;

import org.xcolab.client.admin.pojo.IEmailTemplate;

import java.util.List;

public class EmailTemplateClientUtil {

    private static final EmailTemplateClient emailTemplateClient = null;

    public static List<IEmailTemplate> listAllContestEmailTemplates() {
        return emailTemplateClient.listEmailTemplates();
    }

    public static IEmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        return emailTemplateClient.getEmailTemplate(emailTemplateType);
    }

    public static void updateContestEmailTemplate(IEmailTemplate contestEmailTemplate) {
        emailTemplateClient.updateEmailTemplate(contestEmailTemplate);
    }

    public static IEmailTemplate createEmailTemplate(IEmailTemplate template) {
        return emailTemplateClient.createEmailTemplate(template);

    }

    public static EmailTemplateClient getClient() {
        return emailTemplateClient;
    }
}
