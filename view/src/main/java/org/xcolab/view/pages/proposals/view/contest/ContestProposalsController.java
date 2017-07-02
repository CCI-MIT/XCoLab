package org.xcolab.view.pages.proposals.view.contest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.view.util.entity.enums.MemberRole;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.view.pages.proposals.exceptions.ProposalIdOrContestIdInvalidException;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.view.proposal.BaseProposalsController;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.view.pages.proposals.wrappers.ProposalsSortFilterBean;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContestProposalsController extends BaseProposalsController {

    private final ProposalsContext proposalsContext;

    @Autowired
    public ContestProposalsController(ProposalsContext proposalsContext) {
        Assert.notNull(proposalsContext, "ProposalsContext bean is required");
        this.proposalsContext = proposalsContext;
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}/phase/{phaseId}")
    public String showContestProposalsWithContestPhaseId(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String contestYear,
            @PathVariable String contestUrlName,
            @PathVariable String phaseId,
            final SortFilterPage sortFilterPage, Model model, Member loggedInMember) {
        setBasePageAttributes(request, model);
        return showContestProposalsPage(request, response, model,
                sortFilterPage, loggedInMember);
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}")
    public String showContestProposals(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String contestYear,
            @PathVariable String contestUrlName,
            final SortFilterPage sortFilterPage, Model model, Member loggedInMember) {
        setBasePageAttributes(request, model);
        return showContestProposalsPage(request, response, model,
                sortFilterPage, loggedInMember);
    }

    private String showContestProposalsPage(HttpServletRequest request,
            HttpServletResponse response, Model model,
            final SortFilterPage sortFilterPage, Member loggedInMember) {


        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        Contest contest = proposalsContext.getContest(request);

        if (contest == null || contestPhase == null) {
            throw new ProposalIdOrContestIdInvalidException();
        }

        final ClientHelper clients = proposalsContext.getClients(request);
        final ProposalClient proposalClient = clients.getProposalClient();

        final List<Proposal> activeProposals;
        switch (contestPhase.getContestPhaseTypeObject().getStatusEnum()) {
            case OPEN_FOR_SUBMISSION:
            case OPEN_FOR_EDIT:
                activeProposals = proposalClient.getActiveProposalsInContestPhase(
                        contestPhase.getContestPhasePK());
                break;
            default:
                activeProposals = proposalClient.getActiveProposalsInContestPhase(
                        contestPhase.getContestPhasePK(), CacheName.PROPOSAL_LIST_CLOSED);
        }

        List<Proposal> proposals = new ArrayList<>();
        for (Proposal proposal : activeProposals) {

            try {
                final ProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();
                Proposal2Phase p2p = proposalPhaseClient.getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
                Proposal proposalWrapper;

                if (loggedInMember != null && PermissionsClient.memberHasRole(loggedInMember.getUserId(), MemberRole.JUDGE.getRoleId())) {
                    proposalWrapper = new ProposalJudgeWrapper(proposal, p2p.getVersionTo() == -1 ? proposal.getCurrentVersion() : p2p.getVersionTo(), contest, contestPhase, p2p, loggedInMember);

                } else {
                    proposalWrapper = new Proposal(proposal, p2p.getVersionTo() == -1 ? proposal.getCurrentVersion() : p2p.getVersionTo(), contest, contestPhase, p2p);
                }

                proposals.add(proposalWrapper);
            } catch (Proposal2PhaseNotFoundException ignored) {

            }
        }

        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("proposals",
            new ProposalsSortFilterBean(proposals, sortFilterPage));
        model.addAttribute("showCountdown",
                ConfigurationAttributeKey.SHOW_CONTEST_COUNTDOWN.get());
        model.addAttribute("defaultTimeZoneId",
            ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get());
        model.addAttribute("showShareButtons",
            ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get());
        model.addAttribute("showContributorsColumn",
            ConfigurationAttributeKey.CONTESTS_ALLOW_OPEN_PROPOSALS.get());

        boolean showEditLink = false;
        if (loggedInMember != null) {
            showEditLink = PermissionsClient.canAdminAll(loggedInMember.getUserId())
                    || contest.getCanFellow(loggedInMember.getUserId());
        }
        model.addAttribute("showEditLink", showEditLink);

        setSeoTexts(request, contest.getContestShortName(), null, contest.getContestDescription());

        setBasePageAttributes(request, model);
        return "/proposals/contestProposals";
    }

    @GetMapping("/contests/subscribeContest")
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response)
            throws ProposalsAuthorizationException, IOException {

        if (proposalsContext.getPermissions(request).getCanSubscribeContest()) {
            long contestId = proposalsContext.getContest(request).getContestPK();
            long memberId = proposalsContext.getMember(request).getUserId();
            if (ContestClientUtil.isMemberSubscribedToContest(contestId, memberId)) {
                ContestClientUtil.unsubscribeMemberFromContest(contestId, memberId);
            }
            else {
                ContestClientUtil.subscribeMemberToContest(contestId, memberId);

            }
            response.sendRedirect(proposalsContext.getContest(request).getContestLinkUrl());
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to subscribe contest");
        }
    }
}


