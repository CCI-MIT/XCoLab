package org.xcolab.client.admin;

import org.xcolab.client.admin.pojo.IEmailTemplate;

import java.util.List;

public class EmailTemplateClientUtil {

    private static final EmailTemplateClient emailTemplateClient = new EmailTemplateClient();

    public static List<IEmailTemplate> listAllContestEmailTemplates() {
        return emailTemplateClient.listAllContestEmailTemplates();
    }

    public static IEmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        return emailTemplateClient.getContestEmailTemplateByType(emailTemplateType);
    }

    public static void updateContestEmailTemplate(IEmailTemplate contestEmailTemplate) {
        emailTemplateClient.updateContestEmailTemplate(contestEmailTemplate);
    }

    public static IEmailTemplate createEmailTemplate(IEmailTemplate template) {
        return emailTemplateClient.createEmailTemplate(template);

    }

    public static EmailTemplateClient getClient() {
        return emailTemplateClient;
    }
}
