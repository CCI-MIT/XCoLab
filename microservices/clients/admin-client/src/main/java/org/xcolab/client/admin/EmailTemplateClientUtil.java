package org.xcolab.client.admin;

import org.xcolab.client.admin.pojo.EmailTemplate;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public class EmailTemplateClientUtil {

    private static final EmailTemplateClient emailTemplateClient = EmailTemplateClient.fromNamespace(
            ServiceNamespace.instance());

    public static List<EmailTemplate> listAllContestEmailTemplates() {
        return emailTemplateClient.listAllContestEmailTemplates();
    }

    public static EmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        return emailTemplateClient.getContestEmailTemplateByType(emailTemplateType);
    }

    public static void updateContestEmailTemplate(EmailTemplate contestEmailTemplate) {
        emailTemplateClient.updateContestEmailTemplate(contestEmailTemplate);
    }

    public static EmailTemplate createEmailTemplate(EmailTemplate template) {
        return emailTemplateClient.createEmailTemplate(template);

    }

    public static EmailTemplateClient getClient() {
        return emailTemplateClient;
    }
}
