package org.xcolab.client.admin;

import org.xcolab.client.admin.exceptions.EmailTemplateNotFoundException;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EmailTemplateClient {

    private static final Map<ServiceNamespace, EmailTemplateClient> instances = new HashMap<>();
    private final RestResource<ContestEmailTemplate, String> emailTemplatesResource;

    private EmailTemplateClient(ServiceNamespace serviceNamespace) {
        emailTemplatesResource = new RestResource1<>(
                AdminResource.EMAIL_TEMPLATE, ContestEmailTemplate.TYPES, serviceNamespace);
    }

    public  List<ContestEmailTemplate> listAllContestEmailTemplates() {
        return emailTemplatesResource.list().execute();
    }

    public  ContestEmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        try {
            return emailTemplatesResource.get(emailTemplateType).executeChecked();
        } catch (EntityNotFoundException e) {
            throw new EmailTemplateNotFoundException(emailTemplateType);
        }
    }

    public  void updateContestEmailTemplate(ContestEmailTemplate contestEmailTemplate) {
        emailTemplatesResource.update(contestEmailTemplate, contestEmailTemplate.getType_())
                .execute();
    }

    public  ContestEmailTemplate createEmailTemplate(ContestEmailTemplate template) {
        return emailTemplatesResource.create(template).execute();
    }

    public static EmailTemplateClient fromNamespace(ServiceNamespace serviceNamespace) {
        return instances.computeIfAbsent(serviceNamespace, EmailTemplateClient::new);
    }
}
