package org.xcolab.client.admin;

import org.xcolab.client.admin.exceptions.EmailTemplateNotFoundException;
import org.xcolab.client.admin.pojo.EmailTemplate;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EmailTemplateClient {

    private static final Map<ServiceNamespace, EmailTemplateClient> instances = new HashMap<>();
    private final RestResource<EmailTemplate, String> emailTemplatesResource;

    private EmailTemplateClient(ServiceNamespace serviceNamespace) {
        emailTemplatesResource = new RestResource1<>(
                AdminResource.EMAIL_TEMPLATE, EmailTemplate.TYPES, serviceNamespace);
    }

    public  List<EmailTemplate> listAllContestEmailTemplates() {
        return emailTemplatesResource.list().execute();
    }

    public EmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        try {
            return emailTemplatesResource.get(emailTemplateType).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new EmailTemplateNotFoundException(emailTemplateType);
        }
    }

    public  void updateContestEmailTemplate(EmailTemplate contestEmailTemplate) {
        emailTemplatesResource.update(contestEmailTemplate, contestEmailTemplate.getName())
                .execute();
    }

    public EmailTemplate createEmailTemplate(EmailTemplate template) {
        return emailTemplatesResource.create(template).execute();
    }

    public static EmailTemplateClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, EmailTemplateClient::new);
    }
}
