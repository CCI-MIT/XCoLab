package org.xcolab.client.admin;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.admin.exceptions.EmailTemplateNotFoundException;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.util.RequestUtils;
import org.xcolab.util.exceptions.EntityNotFoundException;

import java.util.List;

public final class EmailTemplateClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/admin-service";

    public static List<ContestEmailTemplate> listAllContestEmailTemplates() {

        UriComponentsBuilder uriBuilder =
                UriComponentsBuilder.fromHttpUrl("http://" + EUREKA_APPLICATION_ID + "/emailTemplates");

        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ContestEmailTemplate>>() {
                });
    }

    public static ContestEmailTemplate getContestEmailTemplateByType(String emailTemplateType) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/emailTemplates/" + emailTemplateType + "");
        try {
            return RequestUtils.get(uriBuilder, ContestEmailTemplate.class, "_emailTemplate_contest_" + emailTemplateType);
        } catch (EntityNotFoundException e) {
            throw new EmailTemplateNotFoundException(emailTemplateType);
        }
    }

    public static void updateContestEmailTemplate(ContestEmailTemplate contestEmailTemplate) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/emailTemplates/" + contestEmailTemplate.getType_() + "");
        RequestUtils.put(uriBuilder, contestEmailTemplate);
    }

    public static ContestEmailTemplate createEmailTemplate(ContestEmailTemplate template) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/emailTemplates");
        return RequestUtils.post(uriBuilder, template, ContestEmailTemplate.class);
    }
}
