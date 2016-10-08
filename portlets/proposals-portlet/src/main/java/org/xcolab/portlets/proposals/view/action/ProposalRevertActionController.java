package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.PlanSectionTypeKeys;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;

import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.proposals.Proposal2PhaseClient;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;


@Controller
@RequestMapping("view")
public class ProposalRevertActionController {

    
    @Autowired
    private ProposalsContext proposalsContext;



    @RequestMapping(params = "action=proposalRevert")
    public void showProposalRevert(ActionRequest request, Model model,
                                    ActionResponse response)

    throws PortalException, SystemException, ProposalsAuthorizationException, IOException {

        if (proposalsContext.getProposal(request) != null && !proposalsContext.getPermissions(request).getCanEdit()) {
            throw new ProposalsAuthorizationException("User is not allowed to edit proposal, user: " +
                    proposalsContext.getUser(request).getUserId() + ", proposal: " + proposalsContext.getProposal(request).getProposalId());
        }

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();

        if (proposalsContext.getProposal(request) != null) {
            ProposalWrapper oldProposalVersionToBeBecomeCurrent = proposalsContext.getProposalWrapped(request);
            updateProposalSpecialAttributes(userId, oldProposalVersionToBeBecomeCurrent);

            updateProposalAttributes(request, userId, oldProposalVersionToBeBecomeCurrent);

            proposalsContext.invalidateContext(request);

            request.setAttribute("ACTION_REDIRECTING", true);
            response.sendRedirect(ProposalLocalServiceUtil.getProposalLinkUrl(oldProposalVersionToBeBecomeCurrent.getProposalId()));
        }
    }

    private void updateProposalAttributes(ActionRequest request, long userId, ProposalWrapper oldProposalVersionToBeBecomeCurrent) throws PortalException, SystemException {
        boolean updateProposalReferences = false;
        for (ProposalSectionWrapper section: oldProposalVersionToBeBecomeCurrent.getSections()) {
            String newSectionValue = section.getStringValue();
            if (section.getType() == PlanSectionTypeKeys.TEXT
                    || section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE
                    || section.getType() == PlanSectionTypeKeys.DROPDOWN_MENU) {

                ProposalAttributeLocalServiceUtil.setAttribute(userId,
                        oldProposalVersionToBeBecomeCurrent.getProposalId(),
                        ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), newSectionValue);

                if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
                    updateProposalReferences = true;
                }
            }
            if (section.getType() == PlanSectionTypeKeys.ONTOLOGY_REFERENCE) {
                if (StringUtils.isNumeric(newSectionValue)) {
                    long newNumericVal = Long.parseLong(newSectionValue);
                    if (newNumericVal != section.getNumericValue()) {
                        ProposalAttributeLocalServiceUtil.setAttribute(userId,
                                oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), newNumericVal);
                    }
                }
            }
            if (section.getType() == PlanSectionTypeKeys.PROPOSAL_REFERENCE) {
                if (StringUtils.isNumeric(newSectionValue) && StringUtils.isNotBlank(newSectionValue)) {
                    final long newNumericValue = Long.parseLong(newSectionValue);
                    if (section.getNumericValue() != newNumericValue) {
                        ProposalAttributeLocalServiceUtil.setAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), newNumericValue);
                        updateProposalReferences = true;
                    }
                } else if (StringUtils.isBlank(newSectionValue)) {
                    ProposalAttributeLocalServiceUtil.setAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), 0L);
                }
            }
            if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_REFERENCE) {
                StringBuilder cleanedReferences = new StringBuilder();
                if (StringUtils.isNotBlank(newSectionValue)){
                    String[]referencedProposals = newSectionValue.split(",");
                    for (String referencedProposal : referencedProposals) {
                        if (StringUtils.isNotBlank(referencedProposal) && StringUtils.isNumeric(referencedProposal)) {
                            cleanedReferences.append(referencedProposal).append(",");
                        }
                    }
                }
                if (!section.getStringValue().equals(cleanedReferences.toString())) {
                    ProposalAttributeLocalServiceUtil.setAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), cleanedReferences.toString());
                    updateProposalReferences = true;
                }
            }
        }

        //this code was on the proposal add/update controller, if the user could edit and save , he might just want to revert
        // and leave it like that , so this code must be executed as well.
        final Proposal2Phase p2p = proposalsContext.getProposal2Phase(request);
        try {
            if (p2p != null && p2p.getVersionTo() != -1) {
                // we are in a completed phase - need to adjust the end version
                final Proposal updatedProposal = ProposalsClient.getProposal(oldProposalVersionToBeBecomeCurrent.getProposalId());
                p2p.setVersionTo(updatedProposal.getCurrentVersion());
                Proposal2PhaseClient.updateProposal2Phase(p2p);
            }
            // extra check to reset dependencies from the old versions
            if (updateProposalReferences) {
                ProposalsClient.populateTableWithProposal(oldProposalVersionToBeBecomeCurrent.getWrapped().getProposalId());
            }
        }catch (ProposalNotFoundException ignored){

        }
    }

    private void updateProposalSpecialAttributes(long userId, ProposalWrapper oldProposalVersionToBeBecomeCurrent) throws PortalException, SystemException {
        ProposalAttributeLocalServiceUtil.setAttribute(userId,  oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.NAME, oldProposalVersionToBeBecomeCurrent.getName());
        ProposalAttributeLocalServiceUtil.setAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.PITCH, oldProposalVersionToBeBecomeCurrent.getPitch());
        ProposalAttributeLocalServiceUtil.setAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.DESCRIPTION, oldProposalVersionToBeBecomeCurrent.getDescription());
        ProposalAttributeLocalServiceUtil.setAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.TEAM, oldProposalVersionToBeBecomeCurrent.getTeam());
        ProposalAttributeLocalServiceUtil.setAttribute(userId, oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.IMAGE_ID, oldProposalVersionToBeBecomeCurrent.getImageId());
    }
}
