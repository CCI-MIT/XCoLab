package org.xcolab.client.admin;

import org.xcolab.client.admin.exceptions.EmailTemplateNotFoundException;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EmailTemplateClient {

    private final RestService adminService;

    private static final Map<RestService, EmailTemplateClient> instances = new HashMap<>();
    private final RestResource<ContestEmailTemplate, String> emailTemplatesResource;

    private EmailTemplateClient(RestService restService){
        this.adminService = restService;
        emailTemplatesResource = new RestResource1<>(adminService, "emailTemplates", ContestEmailTemplate.TYPES);
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

    public static EmailTemplateClient fromService(RestService contestService) {
        EmailTemplateClient client = instances.get(contestService);
        if (client == null) {
            client = new EmailTemplateClient(contestService);
            instances.put(contestService, client);
        }
        return client;
    }

}
