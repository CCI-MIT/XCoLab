package org.xcolab.utils.judging;

import com.ext.portlet.model.ContestEmailTemplate;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Parser;

public class EmailTemplateWrapper {
    private static final String PROPOSAL_TITLE_PLACEHOLDER = "proposal-title";
    private static final String CONTEST_TITLE_PLACEHOLDER = "contest-title";

    private final ContestEmailTemplate template;
    private final String proposalName;
    private final String contestName;

    public EmailTemplateWrapper(ContestEmailTemplate template, String proposalName, String contestName) {
        this.template = template;
        this.proposalName = proposalName;
        this.contestName = contestName;
    }

    public String getHeader() throws SystemException, PortalException {
        return this.replaceVariables(this.template.getHeader());
    }

    public String getFooter() throws SystemException, PortalException {
        return this.replaceVariables(this.template.getFooter());
    }

    public String getSubject() throws SystemException, PortalException {
        return this.replaceVariables(this.template.getSubject());
    }

    protected String replaceVariables(String inputString) throws SystemException, PortalException {
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

    protected Node resolvePlaceholderTag(Element tag) throws SystemException, PortalException {
        switch (tag.nodeName()) {
            case PROPOSAL_TITLE_PLACEHOLDER:
                return new TextNode(this.proposalName, "");
            case CONTEST_TITLE_PLACEHOLDER:
                return new TextNode(this.contestName, "");
        }
        return null;
    }

    public String getCompleteMessage(String body) throws SystemException, PortalException {
        return this.getHeader() + "\n" + body + "\n\n" + this.getFooter();
    }

    protected Node parseXmlNode(String xml) {
        return Parser.parseXmlFragment(xml, "").get(0);
    }
}
