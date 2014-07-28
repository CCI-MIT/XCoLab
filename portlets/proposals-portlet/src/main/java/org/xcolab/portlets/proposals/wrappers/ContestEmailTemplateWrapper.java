package org.xcolab.portlets.proposals.wrappers;

import com.ext.portlet.model.ContestEmailTemplate;

import java.util.regex.Matcher;

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
        return this.template == null ? "" : this.replaceVariables(this.template.getHeader());
    }

    public String getFooter() {
        return this.template == null ? "" :  this.replaceVariables(this.template.getFooter());
    }

    public String getSubject() {
        return this.template == null ? "" :  this.replaceVariables(this.template.getSubject());
    }

    public String getCompleteMessage(String body) {
        return this.getHeader()+body+this.getFooter();
    }

    private String replaceVariables(String subject) {
        String result = subject.replaceAll("<contest-title/>", Matcher.quoteReplacement(this.contestName))
            .replaceAll("<proposal-title/>", Matcher.quoteReplacement(this.proposalName))
            .replaceAll("\n", "\n<br/>");
        return result;
    }
}
