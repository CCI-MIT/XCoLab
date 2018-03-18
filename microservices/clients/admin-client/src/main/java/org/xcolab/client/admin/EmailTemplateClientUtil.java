package org.xcolab.client.admin;

import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.util.List;

public class EmailTemplateClientUtil {

    private static final EmailTemplateClient emailTemplateClient = EmailTemplateClient.fromNamespace(
            ServiceNamespace.instance());

    public static List<ContestEmailTemplate> listAllContestEmailTemplates() {
        return emailTemplateClient.listAllContestEmailTemplates();
    }

    public static ContestEmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        return emailTemplateClient.getContestEmailTemplateByType(emailTemplateType);
    }

    public static void updateContestEmailTemplate(ContestEmailTemplate contestEmailTemplate) {
        emailTemplateClient.updateContestEmailTemplate(contestEmailTemplate);
    }

    public static ContestEmailTemplate createEmailTemplate(ContestEmailTemplate template) {
        return emailTemplateClient.createEmailTemplate(template);

    }

    public static EmailTemplateClient getClient() {
        return emailTemplateClient;
    }
}
