package org.xcolab.client.admin;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.admin.exceptions.EmailTemplateNotFoundException;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class EmailTemplateClient {

    private static final RestService adminService = new RestService("admin-service");
    private static final RestResource<ContestEmailTemplate> emailTemplatesResource =
            new RestResource<>(adminService, "emailTemplates", ContestEmailTemplate.class,
            new ParameterizedTypeReference<List<ContestEmailTemplate>>() {
            });

    public static List<ContestEmailTemplate> listAllContestEmailTemplates() {
        return emailTemplatesResource.list().execute();
    }

    public static ContestEmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        try {
            return emailTemplatesResource.get(emailTemplateType).execute();
        } catch (EntityNotFoundException e) {
            throw new EmailTemplateNotFoundException(emailTemplateType);
        }
    }

    public static void updateContestEmailTemplate(ContestEmailTemplate contestEmailTemplate) {
        final UriBuilder uriBuilder = emailTemplatesResource.getResourceUrl(
                contestEmailTemplate.getType_());
        RequestUtils.put(uriBuilder, contestEmailTemplate);
    }

    public static ContestEmailTemplate createEmailTemplate(ContestEmailTemplate template) {
        final UriBuilder uriBuilder = emailTemplatesResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, template, ContestEmailTemplate.class);
    }
}
