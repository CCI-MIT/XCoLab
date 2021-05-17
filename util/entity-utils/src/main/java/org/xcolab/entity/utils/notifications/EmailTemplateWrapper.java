package org.xcolab.entity.utils.notifications;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;

import org.xcolab.client.admin.pojo.IEmailTemplate;

public class EmailTemplateWrapper {
    private static final String PROPOSAL_TITLE_PLACEHOLDER = "proposal-title";
    private static final String CONTEST_TITLE_PLACEHOLDER = "contest-title";

    private final IEmailTemplate template;
    private final String proposalName;
    private final String contestName;

    public EmailTemplateWrapper(IEmailTemplate template, String proposalName, String contestName) {
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

    protected String replaceVariables(String inputString) {
        Document doc = Jsoup.parse(inputString, "", Parser.xmlParser());
        for (Element tag : doc.select("*")) {
            Node resolvedNode = resolvePlaceholderTag(tag);
            if (resolvedNode == null) {
                //don't replace tags that aren't recognized, they are probably html tags (e.g. <br />)
                continue;
            }
            tag.replaceWith(resolvedNode);
        }
        return doc.html();
    }

    protected Node resolvePlaceholderTag(Element tag) {
        switch (tag.nodeName()) {
            case PROPOSAL_TITLE_PLACEHOLDER:
                return new TextNode(this.proposalName);
            case CONTEST_TITLE_PLACEHOLDER:
                return new TextNode(this.contestName);
            //missing default case
            default:
                // add default case
                break;
        }
        return null;
    }

    public String getCompleteMessage(String body) {
        return this.getHeader() + "\n" + body + "\n\n" + this.getFooter();
    }

    protected Node parseXmlNode(String xml) {
        return Parser.parseXmlFragment(xml, "").get(0);
    }
}
