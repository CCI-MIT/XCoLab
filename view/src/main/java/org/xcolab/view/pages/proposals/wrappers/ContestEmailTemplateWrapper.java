package org.xcolab.view.pages.proposals.wrappers;


import org.xcolab.client.admin.pojo.IEmailTemplate;

import java.util.regex.Matcher;

public class ContestEmailTemplateWrapper {
    private final IEmailTemplate template;
    private final String proposalName;
    private final String contestName;


    public ContestEmailTemplateWrapper(IEmailTemplate template, String proposalName, String contestName) {
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
        return subject.replaceAll("<contest-title/>", Matcher.quoteReplacement(this.contestName))
            .replaceAll("<proposal-title/>", Matcher.quoteReplacement(this.proposalName))
            .replaceAll("\n", "\n<br/>");
    }
}
