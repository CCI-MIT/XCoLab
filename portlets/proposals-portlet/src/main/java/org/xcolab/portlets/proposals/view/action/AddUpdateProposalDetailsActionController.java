package org.xcolab.portlets.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.activities.enums.ActivityProvidersType;
import org.xcolab.client.activities.helper.ActivityEntryHelper;
import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.filtering.FilteringClient;
import org.xcolab.client.filtering.exceptions.FilteredEntryNotFoundException;
import org.xcolab.client.filtering.pojo.FilteredEntry;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.liferay.SharedColabUtil;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.portlets.proposals.utils.edit.ProposalCreationUtil;
import org.xcolab.portlets.proposals.utils.edit.ProposalMoveUtil;
import org.xcolab.portlets.proposals.utils.edit.ProposalUpdateHelper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("view")
public class AddUpdateProposalDetailsActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = {"action=updateProposalDetails"})
    public void show(ActionRequest request, Model model,
            ActionResponse response, @Valid UpdateProposalDetailsBean updateProposalSectionsBean, BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {

        final Proposal proposal = proposalsContext.getProposal(request);
        if (proposal != null && !proposalsContext.getPermissions(request).getCanEdit()) {
            throw new ProposalsAuthorizationException("User is not allowed to edit proposal, user: " +
                    proposalsContext.getMember(request).getUserId() + ", proposal: " + proposal.getProposalId());
        }
        final Contest contest = proposalsContext.getContest(request);
        if (proposal == null && !proposalsContext.getPermissions(request).getCanCreate()) {
            throw new ProposalsAuthorizationException("User is not allowed to create proposal, user: " +
                    proposalsContext.getMember(request).getUserId() + ", contest: " + contest
                    .getContestPK());
        }

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        long userId = themeDisplay.getUserId();

        if (result.hasErrors()) {
            response.setRenderParameter("error", "true");
            response.setRenderParameter("action", "updateProposalDetails");
            response.setRenderParameter("edit", "true");
            request.setAttribute("ACTION_ERROR", true);
            return;
        }
        Proposal proposalWrapper;
        boolean createNew = false;
        final ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        if (proposal != null) {
            proposalWrapper = proposalsContext.getProposalWrapped(request);
            if (updateProposalSectionsBean.getIsMove() && updateProposalSectionsBean.getMoveToContestId() > 0) {
                ProposalMoveUtil.moveProposal(updateProposalSectionsBean,
                        proposalWrapper, contestPhase, contest, themeDisplay, request);
            }
        } else {
            createNew = true;
            proposalWrapper = ProposalCreationUtil
                    .createProposal(userId, updateProposalSectionsBean, contest, themeDisplay, contestPhase);
        }

        final Proposal2Phase p2p = proposalsContext.getProposal2Phase(request);
        ProposalUpdateHelper proposalUpdateHelper = new ProposalUpdateHelper(updateProposalSectionsBean, request,
                themeDisplay, proposalWrapper, p2p, userId);
        proposalUpdateHelper.updateProposal();

        if (createNew) {
            ProposalCreationUtil.sendAuthorNotification(themeDisplay, proposalWrapper, contestPhase,
                    request);

            ActivityEntryHelper.createActivityEntry(userId,proposalWrapper.getProposalId(),null,
                    ActivityProvidersType.ProposalCreatedActivityEntry.getType());

        }else{
            ActivityEntryHelper.createActivityEntry(userId,proposalWrapper.getProposalId(),null,
                    ActivityProvidersType.ProposalAttributeUpdateActivityEntry.getType());
        }
        SharedColabUtil.checkTriggerForAutoUserCreationInContest(contest.getContestPK(), userId);
        
        if(ConfigurationAttributeKey.FILTER_PROFANITY.get()){
            try {
                FilteredEntry filteredEntry = FilteringClient.getFilteredEntryByUuid(updateProposalSectionsBean.getUuid());
                filteredEntry.setSourceId(proposalWrapper.getProposalId());
                filteredEntry.setAuthorId(userId);
                FilteringClient.updateFilteredEntry(filteredEntry);
            } catch (FilteredEntryNotFoundException ignored) {
            }
        }

        proposalsContext.invalidateContext(request);

        request.setAttribute("ACTION_REDIRECTING", true);
        response.sendRedirect(proposalWrapper.getProposalUrl());
    }

    @RequestMapping(params = {"action=updateProposalDetails", "error=true"})
    public String reportError(PortletRequest request, Model model,
            @ModelAttribute("updateProposalSectionsBean") @Valid UpdateProposalDetailsBean updateProposalSectionsBean,
            BindingResult result) throws PortalException, SystemException {
        Proposal proposalWrapped = proposalsContext.getProposalWrapped(request);

        Proposal proposal = new Proposal();
        proposal.setAuthorId(proposalsContext.getMember(request).getUserId());
        proposal = ProposalsContextUtil.getClients(request).getProposalClient().createProposal(proposal);

        if (proposalWrapped == null) {
            proposalWrapped = new Proposal(proposal, 0, proposalsContext.getContest(request),
                    proposalsContext.getContestPhase(request), null);
            model.addAttribute("proposal", proposalWrapped);
        }
        model.addAttribute("mustFilterContent",ConfigurationAttributeKey.FILTER_PROFANITY.get());
        model.addAttribute("updateProposalSectionsBean",updateProposalSectionsBean);

        request.setAttribute("imageUploadServiceUrl",
                ConfigurationAttributeKey.IMAGE_UPLOAD_EXTERNAL_SERVICE_URL.get());
        request.setAttribute("imageUploadHelpText",
                ConfigurationAttributeKey.IMAGE_UPLOAD_HELP_TEXT.get());
        return "proposalDetails_edit";
    }
}
