package org.xcolab.portlets.proposals.wrappers;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.NoSuchProposalException;
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
import org.jsoup.nodes.TextNode;

public class ProposalSectionWrapper {

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
        if(content == null) return null;
        Document d = Jsoup.parse(content.trim());
        for (Element e : d.select("a.utube")) {
            String curURL = e.attr("href");
            List<NameValuePair> params = URLEncodedUtils.parse(curURL.substring(curURL.indexOf("?")+1), Charset.defaultCharset());
            for(NameValuePair nvp : params) {
                if(nvp.getName().equals("v")) {
                    e.after("<iframe width=\"560\" height=\"315\" src=\"//www.youtube.com/embed/" + nvp.getValue() + "\" frameborder=\"0\" allowfullscreen></iframe><br/>");
                    e.remove();
                }
            }
        }

        for (Element e : d.select("a")) {
            String href = e.attr("href");
            if (href.contains("/web/guest/plans/-/plans/contestId/")) {
                final String planId = "/planId/";
                int beginIndex = href.indexOf(planId);
                if (beginIndex > -1) {
                    String proposalIdSuffix = href.substring(beginIndex+planId.length(), href.length());
                    int slashIndex = proposalIdSuffix.indexOf("/");
                    String idStr = slashIndex > -1 ? proposalIdSuffix.substring(0, slashIndex) : proposalIdSuffix;

                    try {
                        Long id = Long.parseLong(idStr);

                        ProposalWrapper proposalWrapper = new ProposalWrapper(ProposalLocalServiceUtil.getProposal(id));
                        e.text(proposalWrapper.getName());
                    } catch (Throwable ex) {

                    }
                }
            }
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
