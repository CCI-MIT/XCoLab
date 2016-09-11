package org.xcolab.client.admin;

import org.xcolab.client.admin.exceptions.EmailTemplateNotFoundException;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class EmailTemplateClient {

    private static final RestService adminService = new RestService("admin-service");
    private static final RestResource<ContestEmailTemplate> emailTemplatesResource =
            new RestResource<>(adminService, "emailTemplates", ContestEmailTemplate.TYPES);

    public static List<ContestEmailTemplate> listAllContestEmailTemplates() {
        return emailTemplatesResource.list().execute();
    }

    public static ContestEmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        try {
            return emailTemplatesResource.get(emailTemplateType).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new EmailTemplateNotFoundException(emailTemplateType);
        }
    }

    public static void updateContestEmailTemplate(ContestEmailTemplate contestEmailTemplate) {
        emailTemplatesResource.update(contestEmailTemplate, contestEmailTemplate.getType_())
                .execute();
    }

    public static ContestEmailTemplate createEmailTemplate(ContestEmailTemplate template) {
        return emailTemplatesResource.create(template).execute();
    }
}
