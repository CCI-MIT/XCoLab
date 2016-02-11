package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.NoSuchProposal2PhaseException;
import com.ext.portlet.PlanSectionTypeKeys;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.analytics.AnalyticsUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalSectionWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.utils.HtmlUtil;
import org.xcolab.utils.emailnotification.ProposalCreationNotification;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;



@Controller
@RequestMapping("view")
public class ProposalRevertActionController {


    @Autowired
    private ProposalsContext proposalsContext;



    @RequestMapping(params = "action=proposalRevert")
    public void showProposalRevert(ActionRequest request, Model model,
                                    ActionResponse response)

    throws PortalException, SystemException, ProposalsAuthorizationException, IOException {

        //The current version of the proposal is automatically loaded in the proposalContext

        //validate users that have permission to revert proposal

        if (proposalsContext.getProposal(request) != null && ! proposalsContext.getPermissions(request).getCanEdit()) {
            throw new ProposalsAuthorizationException("User is not allowed to edit proposal, user: " +
                    proposalsContext.getUser(request).getUserId() + ", proposal: " + proposalsContext.getProposal(request).getProposalId());
        }
        if (proposalsContext.getProposal(request) == null && ! proposalsContext.getPermissions(request).getCanCreate()) {
            throw new ProposalsAuthorizationException("User is not allowed to create proposal, user: " +
                    proposalsContext.getUser(request).getUserId() + ", contest: " + proposalsContext.getContest(request).getContestPK());
        }

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();




        ProposalWrapper oldProposalVersionToBeBecomeCurrent;
        if (proposalsContext.getProposal(request) != null) {
            oldProposalVersionToBeBecomeCurrent = proposalsContext.getProposalWrapped(request);



            //updating the name
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.NAME, oldProposalVersionToBeBecomeCurrent.getName());

            //updating the pitch
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.PITCH, oldProposalVersionToBeBecomeCurrent.getPitch());

            //updating the description
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.DESCRIPTION, oldProposalVersionToBeBecomeCurrent.getDescription());

            //updating the team
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.TEAM, oldProposalVersionToBeBecomeCurrent.getTeam());

            //updating the image_id
            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.IMAGE_ID, oldProposalVersionToBeBecomeCurrent.getImageId());


            boolean updateProposalReferences = false;

            //updating all other sections
            for (ProposalSectionWrapper section: oldProposalVersionToBeBecomeCurrent.getSections()) {
                String newSectionValue = section.getStringValue();
                if (section.getType() == PlanSectionTypeKeys.TEXT || section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
                        ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), newSectionValue);
                        if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE) {
                            updateProposalReferences = true;
                        }

                }
                if (section.getType() == PlanSectionTypeKeys.ONTOLOGY_REFERENCE) {
                    // value
                    if (StringUtils.isNumeric(newSectionValue)) {
                        long newNumericVal = Long.parseLong(newSectionValue);
                        if (newNumericVal != section.getNumericValue()) {
                            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(),
                                    oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), newNumericVal);
                        }
                    }
                }
                if (section.getType() == PlanSectionTypeKeys.PROPOSAL_REFERENCE) {
                    if (StringUtils.isNumeric(newSectionValue) && StringUtils.isNotBlank(newSectionValue)) {
                        final long newNumericValue = Long.parseLong(newSectionValue);
                        if (section.getNumericValue() != newNumericValue) {
                            ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), newNumericValue);
                            updateProposalReferences = true;
                        }
                    } else if (StringUtils.isBlank(newSectionValue)) {
                        ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), 0L);
                    }
                }
                if (section.getType() == PlanSectionTypeKeys.PROPOSAL_LIST_REFERENCE) {
                    // check if all parts are numeric
                    StringBuilder cleanedReferences = new StringBuilder();
                    if (StringUtils.isNotBlank(newSectionValue)){
                        String[]referencedProposals = newSectionValue.split(",");
                        for (String referencedProposal : referencedProposals) {
                            if (StringUtils.isNotBlank(referencedProposal) && StringUtils.isNumeric(referencedProposal)) {
                                cleanedReferences.append(referencedProposal).append(",");
                            }
                        }
                        //if (cleanedReferences.substring(cleanedReferences.length()-2,cleanedReferences.length()-1).equalsIgnoreCase(",")) cleanedReferences = cleanedReferences.substring(0, cleanedReferences.length() - 2);
                    }
                    if (!section.getStringValue().equals(cleanedReferences.toString())) {
                        ProposalAttributeLocalServiceUtil.setAttribute(themeDisplay.getUserId(), oldProposalVersionToBeBecomeCurrent.getProposalId(), ProposalAttributeKeys.SECTION, section.getSectionDefinitionId(), cleanedReferences.toString());
                        updateProposalReferences = true;
                    }
                }
            }

            //this code was on the proposal add/update controller, if the user could edit and save , he might just want to revert
            // and leave it like that , so this code must be executed as well.
            final Proposal2Phase p2p = proposalsContext.getProposal2Phase(request);
            if (p2p != null && p2p.getVersionTo() != -1) {
                // we are in a completed phase - need to adjust the end version
                final Proposal updatedProposal = ProposalLocalServiceUtil.fetchProposal(oldProposalVersionToBeBecomeCurrent.getProposalId());
                p2p.setVersionTo(updatedProposal.getCurrentVersion());
                Proposal2PhaseLocalServiceUtil.updateProposal2Phase(p2p);
            }
            // extra check to reset dependencies from the old versions
            if (updateProposalReferences) {
                ProposalReferenceLocalServiceUtil.populateTableWithProposal(oldProposalVersionToBeBecomeCurrent.getWrapped());
            }


            proposalsContext.invalidateContext(request);

            request.setAttribute("ACTION_REDIRECTING", true);
            response.sendRedirect("/web/guest/plans/-/plans/contestId/" + proposalsContext.getContest(request).getContestPK() + "/planId/" + oldProposalVersionToBeBecomeCurrent.getProposalId());

        }


    }
}
