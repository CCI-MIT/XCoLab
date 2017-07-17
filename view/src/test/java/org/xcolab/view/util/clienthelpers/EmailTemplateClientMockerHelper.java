package org.xcolab.view.util.clienthelpers;

import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.client.admin.pojo.ContestEmailTemplate;
import org.xcolab.view.util.TestUtil;

import static org.mockito.Matchers.anyString;

public class EmailTemplateClientMockerHelper {
    public static void mockEmailTemplateClient(){
        PowerMockito.mockStatic(EmailTemplateClientUtil.class);
        Mockito.when(EmailTemplateClientUtil.getContestEmailTemplateByType(anyString()))
                .thenAnswer(invocation -> {

                    ContestEmailTemplate contestEmailTemplate = new ContestEmailTemplate();
                    contestEmailTemplate.setFooter(TestUtil.createStringWithLength(10));
                    contestEmailTemplate.setHeader(TestUtil.createStringWithLength(10));
                    contestEmailTemplate.setSubject(TestUtil.createStringWithLength(10));
                    contestEmailTemplate.setType_(TestUtil.createStringWithLength(10));

                    return contestEmailTemplate;
                });
        AdminClientMockerHelper.mockAdminClient();
    }
}
