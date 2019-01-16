package org.xcolab.view.pages.proposals.requests;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.view.pages.proposals.wrappers.ContestEmailTemplateWrapper;

import java.util.HashMap;
import java.util.Map;

public class ContestEmailTemplateBean {
    private final Map<String, ContestEmailTemplateWrapper> emailTemplates;

    public ContestEmailTemplateBean(String[] templateNames, String proposalName, String contestName) {
        this.emailTemplates = new HashMap<>();
        for (String templateToLoad : templateNames) {
            emailTemplates.put(templateToLoad, new ContestEmailTemplateWrapper(
                    StaticAdminContext.getEmailTemplateClient().getEmailTemplate(templateToLoad),
                    proposalName, contestName
            ));
        }
    }

    public Map<String, ContestEmailTemplateWrapper> getEmailTemplates() {
        return emailTemplates;
    }
}
