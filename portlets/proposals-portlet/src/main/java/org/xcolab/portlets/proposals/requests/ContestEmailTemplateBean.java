package org.xcolab.portlets.proposals.requests;

import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.admin.EmailTemplateClient;
import org.xcolab.client.admin.EmailTemplateClientUtil;
import org.xcolab.portlets.proposals.wrappers.ContestEmailTemplateWrapper;

import java.util.HashMap;
import java.util.Map;

public class ContestEmailTemplateBean {
    protected Map<String, ContestEmailTemplateWrapper> emailTemplates;

    public ContestEmailTemplateBean(String[] templateNames, String proposalName, String contestName) throws SystemException {
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
