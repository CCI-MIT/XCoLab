package org.xcolab.view.pages.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
import org.xcolab.util.http.client.RestService;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.loginregister.SharedColabUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.edit.ProposalCreationUtil;
import org.xcolab.view.pages.proposals.utils.edit.ProposalMoveUtil;
import org.xcolab.view.pages.proposals.utils.edit.ProposalUpdateHelper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
public class AddUpdateProposalDetailsActionController {

    private final ProposalsContext proposalsContext;

    @Autowired
    public AddUpdateProposalDetailsActionController(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }


    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/updateProposalDetails")
    public void show(HttpServletRequest request, Model model,
            @PathVariable String contestYear,
            @PathVariable String contestUrlName,
            @PathVariable String proposalUrlString,
            @PathVariable String proposalId,
            HttpServletResponse response, @Valid UpdateProposalDetailsBean updateProposalSectionsBean, BindingResult result)
            throws ProposalsAuthorizationException, IOException {

        long memberId = MemberAuthUtil.getMemberId(request);
        final Proposal proposal = proposalsContext.getProposal(request);
        if (proposal != null && !proposalsContext.getPermissions(request).getCanEdit()) {
            throw new ProposalsAuthorizationException("Member is not allowed to edit proposal, user: " +
                    memberId + ", proposal: " + proposal.getProposalId());
        }
        final Contest contest = proposalsContext.getContest(request);
        if (proposal == null && !proposalsContext.getPermissions(request).getCanCreate()) {
            throw new ProposalsAuthorizationException("Member is not allowed to create proposal, user: " +
                    memberId + ", contest: " + contest
                    .getContestPK());
        }

        if (result.hasErrors()) {
            //--     response.setRenderParameter("error", "true");
            //-- response.setRenderParameter("action", "updateProposalDetails");
            //-- response.setRenderParameter("edit", "true");
            //-- request.setAttribute("ACTION_ERROR", true);
            //return reportError(request,model,updateProposalSectionsBean,result);
            response.sendRedirect(request.getRequestURL()+ "_error");
            return;
        }
        Proposal proposalWrapper;
        boolean createNew = false;
        final ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        if (proposal != null) {
            proposalWrapper = proposalsContext.getProposalWrapped(request);
            if (updateProposalSectionsBean.getIsMove() && updateProposalSectionsBean.getMoveToContestId() > 0) {
                ProposalMoveUtil.moveProposal(updateProposalSectionsBean,
                        proposalWrapper, contestPhase, contest, memberId, request);
            }
        } else {
            createNew = true;
            proposalWrapper = ProposalCreationUtil
                    .createProposal(memberId, updateProposalSectionsBean, contest, contestPhase);
        }

        final Proposal2Phase p2p = proposalsContext.getProposal2Phase(request);
        ProposalUpdateHelper proposalUpdateHelper = new ProposalUpdateHelper(
                updateProposalSectionsBean, request, proposalWrapper, p2p, memberId);
        proposalUpdateHelper.updateProposal();

        if (createNew) {
            ProposalCreationUtil.sendAuthorNotification(ConfigurationAttributeKey.COLAB_URL.get(),
                    proposalWrapper, contestPhase, request);

            ActivityEntryHelper.createActivityEntry(proposalsContext.getClients(request).getActivitiesClient(), memberId, proposalWrapper.getProposalId(), null,
                    ActivityProvidersType.ProposalCreatedActivityEntry.getType());

        } else {
            ActivityEntryHelper.createActivityEntry(proposalsContext.getClients(request).getActivitiesClient(), memberId, proposalWrapper.getProposalId(), null,
                    ActivityProvidersType.ProposalAttributeUpdateActivityEntry.getType());
        }
        SharedColabUtil.checkTriggerForAutoUserCreationInContest(contest.getContestPK(), memberId);
        
        if (ConfigurationAttributeKey.FILTER_PROFANITY.get()) {
            try {
                FilteredEntry filteredEntry = FilteringClient.getFilteredEntryByUuid(updateProposalSectionsBean.getUuid());
                filteredEntry.setSourceId(proposalWrapper.getProposalId());
                filteredEntry.setAuthorId(memberId);
                FilteringClient.updateFilteredEntry(filteredEntry);
            } catch (FilteredEntryNotFoundException ignored) {
            }
        }

        proposalsContext.invalidateContext(request);

        request.setAttribute("ACTION_REDIRECTING", true);
        response.sendRedirect(proposalWrapper.getProposalUrl());
        return;
    }


    @RequestMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/updateProposalDetails_error")
    public String reportError(HttpServletRequest request, Model model,
            @ModelAttribute @Valid UpdateProposalDetailsBean updateProposalSectionsBean,
            BindingResult result) {

        Proposal proposal = proposalsContext.getProposalWrapped(request);
        if (proposal == null) {
            final Contest contest = proposalsContext.getContest(request);
            final RestService proposalService =
                    proposalsContext.getClients(request).getProposalService();
            proposal = new Proposal(new Proposal(proposalService), 0, contest,
                    proposalsContext.getContestPhase(request), null);
            proposal.setAuthorId(proposalsContext.getMember(request).getUserId());
            model.addAttribute("proposal", proposal);
        }
        model.addAttribute("mustFilterContent",ConfigurationAttributeKey.FILTER_PROFANITY.get());
        model.addAttribute("updateProposalSectionsBean", updateProposalSectionsBean);

        request.setAttribute("imageUploadServiceUrl",
                ConfigurationAttributeKey.IMAGE_UPLOAD_EXTERNAL_SERVICE_URL.get());
        request.setAttribute("imageUploadHelpText",
                ConfigurationAttributeKey.IMAGE_UPLOAD_HELP_TEXT.get());
        return "proposals/proposalDetails_edit";
    }
}
