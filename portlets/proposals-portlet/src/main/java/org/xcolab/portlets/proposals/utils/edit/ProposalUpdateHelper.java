package org.xcolab.portlets.proposals.utils.edit;

import com.ext.portlet.PlanSectionTypeKeys;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalReferenceLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.StringUtils;
import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.utils.LinkUtils;

import javax.portlet.ActionRequest;
import javax.validation.Valid;

public class ProposalUpdateHelper {

    public final static String PROPOSAL_ANALYTICS_KEY = "CONTEST_ENTRY_UPDATE";
    public final static String PROPOSAL_ANALYTICS_CATEGORY = "User";
    public final static String PROPOSAL_ANALYTICS_ACTION = "Contest entry update";
    public final static String PROPOSAL_ANALYTICS_LABEL = "";

    private final UpdateProposalDetailsBean updateProposalSectionsBean;
    private final ActionRequest request;
    private final ThemeDisplay themeDisplay;
    private final ProposalWrapper proposalWrapper;
    private final Proposal2Phase p2p;
    private final long userId;

    public ProposalUpdateHelper(@Valid UpdateProposalDetailsBean updateProposalSectionsBean, ActionRequest request,
            ThemeDisplay themeDisplay, ProposalWrapper proposalWrapper, Proposal2Phase p2p, long userId) {
        this.updateProposalSectionsBean = updateProposalSectionsBean;
        this.request = request;
        this.themeDisplay = themeDisplay;
        this.proposalWrapper = proposalWrapper;
        this.p2p = p2p;
        this.userId = userId;
    }

    public void updateProposal() throws PortalException, SystemException {
        boolean filledAll = updateBasicFields();

        boolean updateProposalReferences = false;
        for (ProposalSectionWrapper section : proposalWrapper.getSections()) {
            String newSectionValue =
                    updateProposalSectionsBean.getSectionsContent().get(section.getSectionDefinitionId());
            switch (section.getType()) {
                case TEXT:
                case PROPOSAL_LIST_TEXT_REFERENCE:
                case DROPDOWN_MENU:
                    if (newSectionValue != null && !newSectionValue.trim().equals(section.getContent())) {
                        ProposalAttributeLocalServiceUtil
                                .setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                        HtmlUtil.cleanSome(newSectionValue, LinkUtils.getBaseUri(request)));
                        if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
                            updateProposalReferences = true;
                        }
                    } else {
                        filledAll = false;
                    }
                    break;
                case ONTOLOGY_REFERENCE:
                    if (StringUtils.isNumeric(newSectionValue)) {
                        long newNumericVal = Long.parseLong(newSectionValue);
                        if (newNumericVal != section.getNumericValue()) {
                            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(),
                                    proposalWrapper.getProposalId(), ProposalAttributeKeys.SECTION,
                                    section.getSectionDefinitionId(), newNumericVal);
                        }
                    } else {
                        filledAll = false;
                    }
                    break;
                case PROPOSAL_REFERENCE:
                    if (StringUtils.isNumeric(newSectionValue) && StringUtils.isNotBlank(newSectionValue)) {
                        final long newNumericValue = Long.parseLong(newSectionValue);
                        if (section.getNumericValue() != newNumericValue) {
                            ProposalAttributeLocalServiceUtil
                                    .setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                                            ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                            newNumericValue);
                            updateProposalReferences = true;
                        }
                    } else if (StringUtils.isBlank(newSectionValue)) {
                        ProposalAttributeLocalServiceUtil
                                .setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), 0L);
                    }
                    break;
                case PROPOSAL_LIST_REFERENCE:
                    StringBuilder cleanedReferences = new StringBuilder();
                    if (StringUtils.isNotBlank(newSectionValue)) {
                        String[] referencedProposals = newSectionValue.split(",");
                        for (String referencedProposal : referencedProposals) {
                            if (StringUtils.isNotBlank(referencedProposal) && StringUtils
                                    .isNumeric(referencedProposal)) {
                                cleanedReferences.append(referencedProposal).append(",");
                            }
                        }
                    }
                    if (!section.getStringValue().equals(cleanedReferences.toString())) {
                        ProposalAttributeLocalServiceUtil
                                .setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                        cleanedReferences.toString());
                        updateProposalReferences = true;
                    }
                    break;
                default:
            }
        }

        if (p2p != null && p2p.getVersionTo() != -1) {
            // we are in a completed phase - need to adjust the end version
            try {
                final Proposal updatedProposal = ProposalsClient.getProposal(proposalWrapper.getProposalId());
                p2p.setVersionTo(updatedProposal.getCurrentVersion());
                Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);
            }catch (ProposalNotFoundException ignored){

            }
        }

        if (updateProposalReferences) {
            ProposalReferenceLocalServiceUtil.populateTableWithProposal(proposalWrapper.getWrapped());
        }

        doAnalytics(request, filledAll);
    }

    private boolean updateBasicFields() throws PortalException, SystemException {
        boolean filledAll = true;

        if (!StringUtils.equals(updateProposalSectionsBean.getName(), proposalWrapper.getName())) {
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.NAME, HtmlUtil.cleanMost(updateProposalSectionsBean.getName()));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getPitch(), proposalWrapper.getPitch())) {
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.PITCH, HtmlUtil.cleanSome(updateProposalSectionsBean.getPitch(),
                            LinkUtils.getBaseUri(request)));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getDescription(), proposalWrapper.getDescription())) {
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.DESCRIPTION, HtmlUtil.cleanSome(updateProposalSectionsBean.getDescription(),
                            LinkUtils.getBaseUri(request)));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getTeam(), proposalWrapper.getTeam())) {
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.TEAM, HtmlUtil.cleanMost(updateProposalSectionsBean.getTeam()));
        } else {
            filledAll = false;
        }

        if (updateProposalSectionsBean.getImageId() > 0
                && updateProposalSectionsBean.getImageId() != proposalWrapper.getImageId()) {
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.IMAGE_ID, updateProposalSectionsBean.getImageId());
        } else {
            filledAll = false;
        }
        return filledAll;
    }

    public void doAnalytics(ActionRequest request, boolean filledAll) throws SystemException {
        int analyticsValue;

        if (filledAll) {
            analyticsValue = 3;
        } else {
            analyticsValue = 2;
        }

        AnalyticsUtil.publishEvent(request, userId, PROPOSAL_ANALYTICS_KEY + analyticsValue,
                PROPOSAL_ANALYTICS_CATEGORY, PROPOSAL_ANALYTICS_ACTION, PROPOSAL_ANALYTICS_LABEL, analyticsValue);
    }
}
