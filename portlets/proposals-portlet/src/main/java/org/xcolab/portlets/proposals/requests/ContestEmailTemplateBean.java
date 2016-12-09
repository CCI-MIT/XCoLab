package org.xcolab.portlets.proposals.requests;

import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.portlets.proposals.wrappers.ContestEmailTemplateWrapper;

import java.util.HashMap;
import java.util.Map;

public class ContestEmailTemplateBean {
    private final Map<String, ContestEmailTemplateWrapper> emailTemplates;

    public ContestEmailTemplateBean(String[] templateNames, String proposalName, String contestName) {
        this.emailTemplates = new HashMap<>();
        for (String templateToLoad : templateNames) {
            emailTemplates.put(templateToLoad, new ContestEmailTemplateWrapper(
                            EmailTemplateClientUtil.getContestEmailTemplateByType(templateToLoad),
                    proposalName, contestName
            ));
        }
    }

    public Map<String, ContestEmailTemplateWrapper> getEmailTemplates() {
        return emailTemplates;
    }
}
