package org.xcolab.portlets.proposals.utils.edit;

import org.apache.commons.lang.StringUtils;


import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextImpl;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;

import org.xcolab.util.enums.proposal.PlanSectionTypeKeys;
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
    private final Proposal proposalWrapper;
    private final Proposal2Phase p2p;
    private final long userId;

    private final ProposalsContext proposalsContext = new ProposalsContextImpl();

    public ProposalUpdateHelper(@Valid UpdateProposalDetailsBean updateProposalSectionsBean, ActionRequest request,
            ThemeDisplay themeDisplay, Proposal proposalWrapper, Proposal2Phase p2p, long userId) {
        this.updateProposalSectionsBean = updateProposalSectionsBean;
        this.request = request;
        this.themeDisplay = themeDisplay;
        this.proposalWrapper = proposalWrapper;
        this.p2p = p2p;
        this.userId = userId;
    }

    public void updateProposal() {
        boolean filledAll = updateBasicFields();

        boolean updateProposalReferences = false;
        for (PlanSectionDefinition section : proposalWrapper.getSections()) {
            String newSectionValue =
                    updateProposalSectionsBean.getSectionsContent().get(section.getSectionDefinitionId());
            switch (section.getType()) {
                case TEXT:
                case PROPOSAL_LIST_TEXT_REFERENCE:
                case DROPDOWN_MENU:
                    if (newSectionValue != null && !newSectionValue.trim().equals(section.getContent())) {
                        ProposalAttributeClientUtil
                                .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
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
                            ProposalsContextUtil.getClients(request).getProposalAttributeClient().setProposalAttribute(themeDisplay.getUserId(),
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
                            ProposalAttributeClientUtil
                                    .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                                            ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                            newNumericValue);
                            updateProposalReferences = true;
                        }
                    } else if (StringUtils.isBlank(newSectionValue)) {
                        ProposalAttributeClientUtil
                                .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
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
                        ProposalAttributeClientUtil
                                .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
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
                final Proposal updatedProposal = ProposalsContextUtil.getClients(request).getProposalClient().getProposal(proposalWrapper.getProposalId());
                p2p.setVersionTo(updatedProposal.getCurrentVersion());
                proposalsContext.getClients(request).getProposalPhaseClient().updateProposal2Phase(p2p);
            }catch (ProposalNotFoundException ignored){

            }
        }

        if (updateProposalReferences) {
            ProposalsContextUtil.getClients(request).getProposalClient().populateTableWithProposal(proposalWrapper.getWrapped().getProposalId());
        }

        doAnalytics(request, filledAll);
    }

    private boolean updateBasicFields() {
        boolean filledAll = true;

        if (!StringUtils.equals(updateProposalSectionsBean.getName(), proposalWrapper.getName())) {
            ProposalAttributeClientUtil
                    .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.NAME, 0L, HtmlUtil.cleanMost(updateProposalSectionsBean.getName()));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getPitch(), proposalWrapper.getPitch())) {
            ProposalAttributeClientUtil
                    .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.PITCH, 0L, HtmlUtil.cleanSome(updateProposalSectionsBean.getPitch(),
                            LinkUtils.getBaseUri(request)));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getDescription(), proposalWrapper.getDescription())) {
            ProposalAttributeClientUtil
                    .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.DESCRIPTION, 0L, HtmlUtil.cleanSome(updateProposalSectionsBean.getDescription(),
                            LinkUtils.getBaseUri(request)));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getTeam(), proposalWrapper.getTeam())) {
            ProposalAttributeClientUtil
                    .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.TEAM, 0L, HtmlUtil.cleanMost(updateProposalSectionsBean.getTeam()));
        } else {
            filledAll = false;
        }

        if (updateProposalSectionsBean.getImageId() > 0
                && updateProposalSectionsBean.getImageId() != proposalWrapper.getImageId()) {
            ProposalAttributeClientUtil
                    .setProposalAttribute(themeDisplay.getUserId(), proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.IMAGE_ID, 0L,updateProposalSectionsBean.getImageId());
        } else {
            filledAll = false;
        }
        return filledAll;
    }

    public void doAnalytics(ActionRequest request, boolean filledAll) {
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
