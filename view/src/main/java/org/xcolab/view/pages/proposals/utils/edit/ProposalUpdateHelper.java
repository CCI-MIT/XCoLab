package org.xcolab.view.pages.proposals.utils.edit;

import org.apache.commons.lang3.StringUtils;

import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.entity.utils.LinkUtils;
import org.xcolab.view.util.entity.analytics.AnalyticsUtil;
import org.xcolab.util.enums.proposal.PlanSectionTypeKeys;
import org.xcolab.util.html.HtmlUtil;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextImpl;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import javax.servlet.http.HttpServletRequest;

public class ProposalUpdateHelper {

    public final static String PROPOSAL_ANALYTICS_KEY = "CONTEST_ENTRY_UPDATE";
    public final static String PROPOSAL_ANALYTICS_CATEGORY = "User";
    public final static String PROPOSAL_ANALYTICS_ACTION = "Contest entry update";
    public final static String PROPOSAL_ANALYTICS_LABEL = "";

    private final UpdateProposalDetailsBean updateProposalSectionsBean;
    private final HttpServletRequest request;
    private final Proposal proposalWrapper;
    private final Proposal2Phase p2p;
    private final long memberId;

    private final ProposalsContext proposalsContext = new ProposalsContextImpl();

    public ProposalUpdateHelper(UpdateProposalDetailsBean updateProposalSectionsBean,
            HttpServletRequest request, Proposal proposalWrapper, Proposal2Phase p2p, long memberId) {
        this.updateProposalSectionsBean = updateProposalSectionsBean;
        this.request = request;
        this.proposalWrapper = proposalWrapper;
        this.p2p = p2p;
        this.memberId = memberId;
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
                        ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                                .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
                                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                        HtmlUtil.cleanSome(newSectionValue, LinkUtils.getBaseUri(request)));
                        if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
                            updateProposalReferences = true;
                        }
                    } else {
                        filledAll = false;
                    }
                    break;
                case CHECKBOX_OPTION:
                    if (newSectionValue != null && !newSectionValue.trim().equals(section.getContent())) {
                        String optionValues = newSectionValue.replace(",",";");

                        ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                                .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
                                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                        optionValues);
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
                            ProposalsContextUtil.getClients(request).getProposalAttributeClient().setProposalAttribute(
                                    memberId,
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
                            ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                                    .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
                                            ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                            newNumericValue);
                            updateProposalReferences = true;
                        }
                    } else if (StringUtils.isBlank(newSectionValue)) {
                        ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                                .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
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
                        ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                                .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
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
            } catch (ProposalNotFoundException ignored) {

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
            ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                    .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.NAME, 0L, HtmlUtil.cleanMost(updateProposalSectionsBean.getName()));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getPitch(), proposalWrapper.getPitch())) {
            ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                    .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.PITCH, 0L, HtmlUtil.cleanSome(updateProposalSectionsBean.getPitch(),
                            LinkUtils.getBaseUri(request)));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getDescription(), proposalWrapper.getDescription())) {
            ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                    .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.DESCRIPTION, 0L, HtmlUtil.cleanSome(updateProposalSectionsBean.getDescription(),
                            LinkUtils.getBaseUri(request)));
        } else {
            filledAll = false;
        }

        if (!StringUtils.equals(updateProposalSectionsBean.getTeam(), proposalWrapper.getTeam())) {
            ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                    .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.TEAM, 0L, HtmlUtil.cleanMost(updateProposalSectionsBean.getTeam()));
        } else {
            filledAll = false;
        }

        if (updateProposalSectionsBean.getImageId() > 0
                && updateProposalSectionsBean.getImageId() != proposalWrapper.getImageId()) {
            ProposalsContextUtil.getClients(request).getProposalAttributeClient()
                    .setProposalAttribute(memberId, proposalWrapper.getProposalId(),
                    ProposalAttributeKeys.IMAGE_ID, 0L,updateProposalSectionsBean.getImageId());
        } else {
            filledAll = false;
        }
        return filledAll;
    }

    private void doAnalytics(HttpServletRequest request, boolean filledAll) {
        int analyticsValue;

        if (filledAll) {
            analyticsValue = 3;
        } else {
            analyticsValue = 2;
        }

        AnalyticsUtil.publishEvent(request, memberId, PROPOSAL_ANALYTICS_KEY + analyticsValue,
                PROPOSAL_ANALYTICS_CATEGORY, PROPOSAL_ANALYTICS_ACTION, PROPOSAL_ANALYTICS_LABEL, analyticsValue);
    }
}
