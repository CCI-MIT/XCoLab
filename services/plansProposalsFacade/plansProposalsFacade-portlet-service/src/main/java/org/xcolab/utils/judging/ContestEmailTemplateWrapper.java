package org.xcolab.utils.judging;

import com.ext.portlet.model.ContestEmailTemplate;
import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.model.ProposalRatingValue;
import com.ext.portlet.service.ProposalRatingValueLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * Created by Manuel Thurner
 */
public class ContestEmailTemplateWrapper {
    private ContestEmailTemplate template;
    private String proposalName;
    private String contestName;


    public ContestEmailTemplateWrapper(ContestEmailTemplate template, String proposalName, String contestName) {
        this.template = template;
        this.proposalName = proposalName;
        this.contestName = contestName;

    }

    public String getHeader() {
        return this.replaceVariables(this.template.getHeader());
    }

    public String getFooter() {
        return this.replaceVariables(this.template.getFooter());
    }

    public String getSubject() {
        return this.replaceVariables(this.template.getSubject());
    }

    public String getCompleteMessage(String body) {
        return this.getHeader()+"\n"+body+"\n\n"+this.getFooter();
    }

    private String replaceVariables(String subject) {
    	System.out.println("Subject: " + subject + "\t" + this.contestName + "\t" + this.proposalName); 
        String result = subject.replaceAll("<contest-title/>", this.contestName)
                .replaceAll("<proposal-title/>", this.proposalName);
        return result;
    }
}
