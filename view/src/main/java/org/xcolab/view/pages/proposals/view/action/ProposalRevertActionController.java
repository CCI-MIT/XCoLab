package org.xcolab.view.pages.proposals.view.action;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.enums.ProposalAttributeKeys;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.util.enums.proposal.ProposalTemplateSectionType;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalRevertActionController {

    @Autowired
    private IProposalAttributeClient proposalAttributeClient;

    @Autowired
    private IProposalClient proposalClient;
    
    @PostMapping("/c/{proposalUrlString}/{proposalId}/proposalRevert")
    public void showProposalRevert(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember)
            throws ProposalsAuthorizationException, IOException {

        if (proposalContext.getProposal() != null && !proposalContext.getPermissions()
                .getCanEdit()) {
            throw new ProposalsAuthorizationException(
                    "User is not allowed to edit proposal, user: " + currentMember.getId()
                            + ", proposal: " + proposalContext.getProposal().getId());
        }

        long userId = MemberAuthUtil.getUserId();

        if (proposalContext.getProposal() != null) {
            ProposalWrapper oldProposalVersionToBeBecomeCurrent = proposalContext.getProposal();

            Integer version = updateProposalSpecialAttributes(userId, oldProposalVersionToBeBecomeCurrent);

            updateProposalAttributes(proposalContext, userId,
                    oldProposalVersionToBeBecomeCurrent, version);

            response.sendRedirect(oldProposalVersionToBeBecomeCurrent.getProposalUrl());
        }
    }

    private void updateProposalAttributes(ProposalContext proposalContext, long userId,
            ProposalWrapper oldProposalVersionToBeBecomeCurrent, Integer version) {
        boolean updateProposalReferences = false;
        for (ProposalTemplateSectionDefinitionWrapper section : oldProposalVersionToBeBecomeCurrent.getSections()) {
            String newSectionValue = section.getStringValue();
            final ProposalTemplateSectionType sectionType = section.getTypeEnum();
            if (sectionType == ProposalTemplateSectionType.TEXT
                    || sectionType == ProposalTemplateSectionType.PROPOSAL_LIST_TEXT_REFERENCE
                    || sectionType == ProposalTemplateSectionType.DROPDOWN_MENU
                    || sectionType == ProposalTemplateSectionType.CHECKBOX_OPTION) {

                version = proposalAttributeClient.setProposalAttribute(userId,
                        oldProposalVersionToBeBecomeCurrent.getId(),
                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                        newSectionValue, version).getVersion();

                if (sectionType == ProposalTemplateSectionType.PROPOSAL_LIST_TEXT_REFERENCE) {
                    updateProposalReferences = true;
                }
            }
            if (sectionType == ProposalTemplateSectionType.ONTOLOGY_REFERENCE) {
                if (StringUtils.isNumeric(newSectionValue)) {
                    long newNumericVal = Long.parseLong(newSectionValue);
                    if (newNumericVal != section.getNumericValue()) {
                        version = proposalAttributeClient.setProposalAttribute(userId,
                                oldProposalVersionToBeBecomeCurrent.getId(),
                                ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                newNumericVal, version).getVersion();
                    }
                }
            }
            if (sectionType == ProposalTemplateSectionType.PROPOSAL_REFERENCE) {
                if (StringUtils.isNumeric(newSectionValue) && StringUtils
                        .isNotBlank(newSectionValue)) {
                    final long newNumericValue = Long.parseLong(newSectionValue);
                    if (section.getNumericValue() != newNumericValue) {
                        version = proposalAttributeClient.setProposalAttribute(userId,
                                oldProposalVersionToBeBecomeCurrent.getId(),
                                ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                                newNumericValue, version).getVersion();
                        updateProposalReferences = true;
                    }
                } else if (StringUtils.isBlank(newSectionValue)) {
                    version = proposalAttributeClient.setProposalAttribute(userId,
                            oldProposalVersionToBeBecomeCurrent.getId(),
                            ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), 0L,
                            version).getVersion();
                }
            }
            if (sectionType == ProposalTemplateSectionType.PROPOSAL_LIST_REFERENCE) {
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
                    version = proposalAttributeClient.setProposalAttribute(userId,
                            oldProposalVersionToBeBecomeCurrent.getId(),
                            ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(),
                            cleanedReferences.toString(), version).getVersion();
                    updateProposalReferences = true;
                }
            }
        }

        //this code was on the proposal add/update controller, if the user could edit and save ,
        // he might just want to revert
        // and leave it like that , so this code must be executed as well.
        final IProposal2Phase p2p = proposalContext.getProposal2Phase();
        try {
            if (p2p != null && p2p.getVersionTo() != -1) {
                // we are in a completed phase - need to adjust the end version
                final ProposalWrapper updatedProposal = proposalContext.getClients().getProposalClient()
                        .getProposal(oldProposalVersionToBeBecomeCurrent.getId());
                p2p.setVersionTo(updatedProposal.getCurrentVersion());
                proposalContext.getClients().getProposalPhaseClient().updateProposal2Phase(p2p);
            }
            // extra check to reset dependencies from the old versions
            if (updateProposalReferences) {
                proposalClient.populateTableWithProposal(
                        oldProposalVersionToBeBecomeCurrent.getId());
            }
        } catch (ProposalNotFoundException ignored) {
        }
    }

    private Integer updateProposalSpecialAttributes(long userId,
            ProposalWrapper oldProposalVersionToBeBecomeCurrent) {
        Integer version = null;
        version = proposalAttributeClient
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getId(),
                        ProposalAttributeKeys.NAME, 0L,
                        oldProposalVersionToBeBecomeCurrent.getName(), version).getVersion();
        version = proposalAttributeClient
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getId(),
                        ProposalAttributeKeys.PITCH, 0L,
                        oldProposalVersionToBeBecomeCurrent.getPitch(), version).getVersion();
        version = proposalAttributeClient
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getId(),
                        ProposalAttributeKeys.DESCRIPTION, 0L,
                        oldProposalVersionToBeBecomeCurrent.getDescription(), version).getVersion();
        version = proposalAttributeClient
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getId(),
                        ProposalAttributeKeys.TEAM, 0L,
                        oldProposalVersionToBeBecomeCurrent.getTeam(), version).getVersion();
        version = proposalAttributeClient
                .setProposalAttribute(userId, oldProposalVersionToBeBecomeCurrent.getId(),
                        ProposalAttributeKeys.IMAGE_ID, 0L,
                        oldProposalVersionToBeBecomeCurrent.getImageId(), version).getVersion();
        return version;
    }
}
