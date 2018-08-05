package org.xcolab.view.pages.proposals.view.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalAttributeClientUtil;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.enums.proposal.PlanSectionTypeKeys;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalRevertActionController {

    @PostMapping("/c/{proposalUrlString}/{proposalId}/proposalRevert")
    public void showProposalRevert(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member currentMember)
            throws ProposalsAuthorizationException, IOException {

        if (proposalContext.getProposal() != null && !proposalContext.getPermissions()
                .getCanEdit()) {
            throw new ProposalsAuthorizationException(
                    "User is not allowed to edit proposal, user: " + currentMember.getUserId()
                            + ", proposal: " + proposalContext.getProposal().getProposalId());
        }

        long userId = MemberAuthUtil.getuserId(request);

        if (proposalContext.getProposal() != null) {
            Proposal oldProposalVersionToBeBecomeCurrent = proposalContext.getProposal();

            Integer version = updateProposalSpecialAttributes(userId, oldProposalVersionToBeBecomeCurrent);

            updateProposalAttributes(proposalContext, userId,
                    oldProposalVersionToBeBecomeCurrent, version);

            response.sendRedirect(oldProposalVersionToBeBecomeCurrent.getProposalUrl());
        }
    }

    private void updateProposalAttributes(ProposalContext proposalContext, long userId,
            Proposal oldProposalVersionToBeBecomeCurrent, Integer version) {
        boolean updateProposalReferences = false;
        for (PlanSectionDefinition section : oldProposalVersionToBeBecomeCurrent.getSections()) {
            String newSectionValue = section.getStringValue();
            if (section.getType() == PlanSectionTypeKeys.TEXT
                    || section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE
                    || section.getType() == PlanSectionTypeKeys.DROPDOWN_MENU
                    || section.getType() == PlanSectionTypeKeys.CHECKBOX_OPTION) {

                version = ProposalAttributeClientUtil.setProposalAttribute(userId,
                        oldProposalVersionToBeBecomeCurrent.getProposalId(),
                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                        newSectionValue, version).getVersion();

                if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
                    updateProposalReferences = true;
                }
            }
            if (section.getType() == PlanSectionTypeKeys.ONTOLOGY_REFERENCE) {
                if (StringUtils.isNumeric(newSectionValue)) {
                    long newNumericVal = Long.parseLong(newSectionValue);
                    if (newNumericVal != section.getNumericValue()) {
                        version = ProposalAttributeClientUtil.setProposalAttribute(userId,
                                oldProposalVersionToBeBecomeCurrent.getProposalId(),
                                ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                newNumericVal, version).getVersion();
                    }
                }
            }
            if (section.getType() == PlanSectionTypeKeys.PROPOSAL_REFERENCE) {
                if (StringUtils.isNumeric(newSectionValue) && StringUtils
                        .isNotBlank(newSectionValue)) {
                    final long newNumericValue = Long.parseLong(newSectionValue);
                    if (section.getNumericValue() != newNumericValue) {
                        version = ProposalAttributeClientUtil.setProposalAttribute(userId,
                                oldProposalVersionToBeBecomeCurrent.getProposalId(),
                                ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                newNumericValue, version).getVersion();
                        updateProposalReferences = true;
                    }
                } else if (StringUtils.isBlank(newSectionValue)) {
                    version = ProposalAttributeClientUtil.setProposalAttribute(userId,
                            oldProposalVersionToBeBecomeCurrent.getProposalId(),
                            ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), 0L,
                            version).getVersion();
                }
            }
            if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_REFERENCE) {
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
                    version = ProposalAttributeClientUtil.setProposalAttribute(userId,
                            oldProposalVersionToBeBecomeCurrent.getProposalId(),
                            ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                            cleanedReferences.toString(), version).getVersion();
                    updateProposalReferences = true;
                }
            }
        }

        //this code was on the proposal add/update controller, if the user could edit and save ,
        // he might just want to revert
        // and leave it like that , so this code must be executed as well.
        final Proposal2Phase p2p = proposalContext.getProposal2Phase();
        try {
            if (p2p != null && p2p.getVersionTo() != -1) {
                // we are in a completed phase - need to adjust the end version
                final Proposal updatedProposal = proposalContext.getClients().getProposalClient()
                        .getProposal(oldProposalVersionToBeBecomeCurrent.getProposalId());
                p2p.setVersionTo(updatedProposal.getCurrentVersion());
                proposalContext.getClients().getProposalPhaseClient().updateProposal2Phase(p2p);
            }
            // extra check to reset dependencies from the old versions
            if (updateProposalReferences) {
                ProposalClientUtil.populateTableWithProposal(
                        oldProposalVersionToBeBecomeCurrent.getWrapped().getProposalId());
            }
        } catch (ProposalNotFoundException ignored) {

        }
    }

    private Integer updateProposalSpecialAttributes(long userId,
            Proposal oldProposalVersionToBeBecomeCurrent) {
        Integer version = null;
        version = ProposalAttributeClientUtil
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(),
                        ProposalAttributeKeys.NAME, 0L,
                        oldProposalVersionToBeBecomeCurrent.getName(), version).getVersion();
        version = ProposalAttributeClientUtil
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(),
                        ProposalAttributeKeys.PITCH, 0L,
                        oldProposalVersionToBeBecomeCurrent.getPitch(), version).getVersion();
        version = ProposalAttributeClientUtil
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(),
                        ProposalAttributeKeys.DESCRIPTION, 0L,
                        oldProposalVersionToBeBecomeCurrent.getDescription(), version).getVersion();
        version = ProposalAttributeClientUtil
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(),
                        ProposalAttributeKeys.TEAM, 0L,
                        oldProposalVersionToBeBecomeCurrent.getTeam(), version).getVersion();
        version = ProposalAttributeClientUtil
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(),
                        ProposalAttributeKeys.IMAGE_ID, 0L,
                        oldProposalVersionToBeBecomeCurrent.getImageId(), version).getVersion();
        return version;
    }
}
