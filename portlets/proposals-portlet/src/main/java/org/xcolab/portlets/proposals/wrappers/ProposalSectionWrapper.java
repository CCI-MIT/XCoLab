package org.xcolab.portlets.proposals.wrappers;

import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.apache.commons.lang.StringUtils;

import com.ext.portlet.PlanSectionTypeKeys;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.xcolab.portlets.proposals.utils.LinkUtils;

public class ProposalSectionWrapper {

    private final static Log _log = LogFactoryUtil.getLog(ProposalSectionWrapper.class);
    private PlanSectionDefinition definition;
    private Proposal proposal;
    private ProposalWrapper wrappedProposal;
    private Integer version;

    public ProposalSectionWrapper(PlanSectionDefinition definition, Proposal proposal, ProposalWrapper wrappedProposal) {
        super();
        this.definition = definition;
        this.proposal = proposal;
        this.wrappedProposal = wrappedProposal;
    }

    public ProposalSectionWrapper(PlanSectionDefinition definition, Proposal proposal, int version, ProposalWrapper wrappedProposal) {
        super();
        this.definition = definition;
        this.proposal = proposal;
        this.version = version;
        this.wrappedProposal = wrappedProposal;
    }


    public String getTitle() {
        return definition.getTitle();
    }

    public String getContent() throws PortalException, SystemException {
        ProposalAttribute attr = getSectionAttribute();

        if (attr == null) return null;
        else return attr.getStringValue().trim();
    }

    public String getContentFormatted() throws SystemException, PortalException, URISyntaxException {
        String content = getContent();
        if (content == null) return null;
        Document d = Jsoup.parse(content.trim());
        for (Element e : d.select("a.utube")) {
            String curURL = e.attr("href");
            List<NameValuePair> params = URLEncodedUtils.parse(curURL.substring(curURL.indexOf("?") + 1), Charset.defaultCharset());
            for (NameValuePair nvp : params) {
                if (nvp.getName().equals("v")) {
                    e.after("<iframe width=\"560\" height=\"315\" src=\"//www.youtube.com/embed/" + nvp.getValue() + "\" frameborder=\"0\" allowfullscreen></iframe><br/>");
                    e.remove();
                }
            }
        }

        // Regex pattern originated from
        // http://stackoverflow.com/questions/1806017/extracting-urls-from-a-text-document-using-java-regular-expressions
        Pattern pattern = Pattern.compile(
                "\\b(((ht|f)tp(s?)\\:\\/\\/|~\\/|\\/)|www.)" +
                        "(((\\w+:\\w+@)?(([-\\w]+\\.)+(com|org|net|gov" +
                        "|mil|biz|info|mobi|name|aero|jobs|museum" +
                        "|travel|[a-z]{2})))|localhost)(:[\\d]{1,5})?" +
                        "(((\\/([-\\w~!$+|.,=]|%[a-f\\d]{2})+)+|\\/)+|\\?|#)?" +
                        "((\\?([-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)" +
                        "(&(?:[-\\w~!$+|.,*:]|%[a-f\\d{2}])+=?" +
                        "([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)*)*" +
                        "(#([-\\w~!$+|.,*:=]|%[a-f\\d]{2})*)?\\b");

        // Scan all <p> tags
        for (Element e : d.select("p")) {
            String newText = "";
            _log.debug("Scanning line: " + e.text());
            // Separate the <p> tags by the space character and process potential URLs
            String[] words = e.text().split(" ");
            for (String word : words) {
                final Matcher matcher = pattern.matcher(word);

                // If a match is found create a new <a> tag
                if (matcher.find()) {
                    final String link = word.substring(matcher.start(), matcher.end());
                    ProposalWrapper wr = LinkUtils.getProposalLinks(link);

                    String elementName;
                    if (wr != null) {
                        elementName = wr.getName();
                    } else {
                        elementName = link;
                    }

                    newText += "<a href=\""+link+"\">"+ elementName +"</a>" + " ";
                } else {
                    newText += word + " ";
                }
            }

            // Rebuild the whole value string of the <p> tag and exchange the old one
            e.after("<p>" + newText + "<p>");
            e.remove();
        }

        return d.select("body").html();
    }

    public PlanSectionTypeKeys getType() {
        if (StringUtils.isBlank(definition.getType())) {
            return PlanSectionTypeKeys.TEXT;
        }
        return PlanSectionTypeKeys.valueOf(definition.getType());
    }

    public Long getSectionDefinitionId() {
        return definition.getId();
    }

    public boolean isLocked() {
        return definition.getLocked();
    }

    public int getCharacterLimit() {
        return definition.getCharacterLimit();
    }

    public String getHelpText() {
        return definition.getHelpText();
    }

    public OntologyTerm getNumericValueAsOntologyTerm() throws SystemException, PortalException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getNumericValue() <= 0) {
            return null;
        }
        return OntologyTermLocalServiceUtil.getOntologyTerm(attr.getNumericValue());
    }

    public ProposalWrapper getNumericValueAsProposal() throws SystemException, PortalException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getNumericValue() <= 0) {
            return null;
        }
        return new ProposalWrapper(ProposalLocalServiceUtil.getProposal(attr.getNumericValue()));
    }

    public ProposalWrapper[] getStringValueAsProposalArray() throws SystemException, PortalException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null || attr.getStringValue() == null || attr.getStringValue().equals("")) {
            return null;
        }

        String props[] = attr.getStringValue().split(",");
        ProposalWrapper[] ret = new ProposalWrapper[props.length];
        for (int i = 0; i < props.length; i++) {
            try {
                ret[i] = new ProposalWrapper(ProposalLocalServiceUtil.getProposal(Long.parseLong(props[i])));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    public long getNumericValue() throws SystemException, PortalException {
        ProposalAttribute attr = getSectionAttribute();
        if (attr == null) {
            return 0;
        }
        return attr.getNumericValue();
    }

    public String getStringValue() throws SystemException, PortalException {
        return getSectionAttribute().getStringValue();
    }

    public List<OntologyTerm> getFocusAreaTerms() throws PortalException, SystemException {
        if (definition.getFocusAreaId() <= 0) return null;

        FocusArea area = FocusAreaLocalServiceUtil.getFocusArea(definition.getFocusAreaId());

        return FocusAreaLocalServiceUtil.getTerms(area);
    }

    private ProposalAttribute getSectionAttribute() throws SystemException, PortalException {
        /*
        try {
            if (version != null && version > 0) {
                return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), version, "SECTION", definition.getId());
            } else {
                return ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), "SECTION", definition.getId());
            }
        } catch (NoSuchProposalAttributeException e) {
            return null;
        } catch (NoSuchProposalException e) {
            return null;
        }
        */
        return this.wrappedProposal.getProposalAttributeUtil().getAttributeOrNull("SECTION", definition.getId());

    }
}
