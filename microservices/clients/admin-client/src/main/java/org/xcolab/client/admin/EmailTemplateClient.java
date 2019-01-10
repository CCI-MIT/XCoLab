package org.xcolab.client.admin;

import org.xcolab.client.admin.exceptions.EmailTemplateNotFoundException;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class EmailTemplateClient {

    private final RestResource<IEmailTemplate, String> emailTemplatesResource = null; //EMAIL_TEMPLATE("emailTemplates")

    public  List<IEmailTemplate> listAllContestEmailTemplates() {
        return emailTemplatesResource.list().execute();
    }

    public IEmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        try {
            return emailTemplatesResource.get(emailTemplateType).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new EmailTemplateNotFoundException(emailTemplateType);
        }
    }

    public  void updateContestEmailTemplate(IEmailTemplate contestEmailTemplate) {
        emailTemplatesResource.update(contestEmailTemplate, contestEmailTemplate.getName())
                .execute();
    }

    public IEmailTemplate createEmailTemplate(IEmailTemplate template) {
        return emailTemplatesResource.create(template).execute();
    }
}
