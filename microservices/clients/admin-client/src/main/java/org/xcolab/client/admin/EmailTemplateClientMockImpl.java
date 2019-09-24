package org.xcolab.client.admin;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.pojo.IEmailTemplate;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class EmailTemplateClientMockImpl implements IEmailTemplateClient {

    @Override
    public List<IEmailTemplate> listEmailTemplates() {
        return Collections.emptyList();
    }

    @Override
    public IEmailTemplate getEmailTemplate(String emailTemplateType) {
        return null;
    }

    @Override
    public boolean updateEmailTemplate(IEmailTemplate emailTemplate) {
        return false;
    }

    @Override
    public IEmailTemplate createEmailTemplate(IEmailTemplate emailTemplate) {
        return null;
    }
}
