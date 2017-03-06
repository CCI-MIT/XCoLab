package org.xcolab.view.pages.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.client.activities.ActivitiesClient;
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
import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.loginregister.SharedColabUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.UpdateProposalDetailsBean;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
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

    @PostMapping("/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}")
    public String show(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable String contestYear, @PathVariable String contestUrlName,
            @PathVariable String proposalUrlString, @PathVariable String proposalId,
            @Valid UpdateProposalDetailsBean updateProposalSectionsBean, BindingResult result)
            throws ProposalsAuthorizationException, IOException {

        long memberId = MemberAuthUtil.getMemberId(request);
        Proposal proposal = proposalsContext.getProposal(request);
        final ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (proposal != null && !permissions.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
        final Contest contest = proposalsContext.getContest(request);
        if (proposal == null && !permissions.getCanCreate()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        final ClientHelper clients = proposalsContext.getClients(request);
        if (result.hasErrors()) {
            AlertMessage.warning(
                    "Please make sure you have entered a title and check the character limits.")
                    .flash(request);
            final String redirectUrl = request.getHeader(HttpHeaders.REFERER);
            return "redirect:" + redirectUrl;
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

        final ActivitiesClient activitiesClient = clients.getActivitiesClient();
        if (createNew) {
            ProposalCreationUtil.sendAuthorNotification(ConfigurationAttributeKey.COLAB_URL.get(),
                    proposalWrapper, contestPhase, request);

            ActivityEntryHelper.createActivityEntry(activitiesClient, memberId, proposalWrapper.getProposalId(), null,
                    ActivityProvidersType.ProposalCreatedActivityEntry.getType());

        } else {
            ActivityEntryHelper.createActivityEntry(activitiesClient, memberId, proposalWrapper.getProposalId(), null,
                    ActivityProvidersType.ProposalAttributeUpdateActivityEntry.getType());
        }
        SharedColabUtil.checkTriggerForAutoUserCreationInContest(contest.getContestPK(), memberId);
        
        if (ConfigurationAttributeKey.FILTER_PROFANITY.get()) {
            try {
                FilteredEntry filteredEntry = FilteringClient
                        .getFilteredEntryByUuid(updateProposalSectionsBean.getUuid());
                filteredEntry.setSourceId(proposalWrapper.getProposalId());
                filteredEntry.setAuthorId(memberId);
                FilteringClient.updateFilteredEntry(filteredEntry);
            } catch (FilteredEntryNotFoundException ignored) {
            }
        }

        proposalsContext.invalidateContext(request);
        return "redirect:" + proposalWrapper.getProposalUrl();
    }
}
