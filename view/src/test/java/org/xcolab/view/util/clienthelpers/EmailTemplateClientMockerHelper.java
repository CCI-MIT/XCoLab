package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.pojo.IEmailTemplate;
import org.xcolab.client.admin.pojo.tables.pojos.EmailTemplate;
import org.xcolab.view.util.TestUtil;

import static org.mockito.Matchers.anyString;

public class EmailTemplateClientMockerHelper {

    public static void mockEmailTemplateClient(AdminClient adminClient,
            EmailTemplateClient emailTemplateClient) {
        Mockito.when(emailTemplateClient.getEmailTemplate(anyString()))
                .thenAnswer(invocation -> {
                    IEmailTemplate contestEmailTemplate = new EmailTemplate();
                    contestEmailTemplate.setFooter(TestUtil.createStringWithLength(10));
                    contestEmailTemplate.setHeader(TestUtil.createStringWithLength(10));
                    contestEmailTemplate.setSubject(TestUtil.createStringWithLength(10));
                    contestEmailTemplate.setName(TestUtil.createStringWithLength(10));

                    return contestEmailTemplate;
                });
        AdminClientMockerHelper.mockAdminClient(adminClient);
    }
}
