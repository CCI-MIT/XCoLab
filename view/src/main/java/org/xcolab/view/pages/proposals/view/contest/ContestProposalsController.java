package org.xcolab.view.pages.proposals.view.contest;

import io.micrometer.core.instrument.Metrics;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.enums.ContestStatus;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IProposalClient;
import org.xcolab.client.contest.proposals.IProposalPhaseClient;
import org.xcolab.client.contest.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.view.pages.proposals.permissions.ContestPermissions;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.view.proposal.BaseProposalsController;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.view.pages.proposals.wrappers.SortedProposalList;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

@Controller
public class ContestProposalsController extends BaseProposalsController {

    @GetMapping("/contests/{contestYear}/{contestUrlName}/phase/{phaseId}")
    public String showContestProposalsWithContestPhaseId(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper loggedInMember,
            ProposalContext proposalContext, @PathVariable String contestYear,
            @PathVariable String contestUrlName, @PathVariable String phaseId,
            final SortFilterPage sortFilterPage) {
        setBasePageAttributes(proposalContext, model);
        return showContestProposalsPage(response, model, proposalContext,
                sortFilterPage, loggedInMember);
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}/judgeFilter/{judgeId}")
    public String showContestProposalsWithJudgeFilter(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper loggedInMember,
            ProposalContext proposalContext, @PathVariable String contestYear,
            @PathVariable String contestUrlName, @PathVariable Long judgeId,
            final SortFilterPage sortFilterPage) {
        model.addAttribute("judgeId", judgeId);
        setBasePageAttributes(proposalContext, model);
        return showContestProposalsPage(response, model, proposalContext,
                sortFilterPage, loggedInMember);
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}")
    public String showContestProposals(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper loggedInMember, ProposalContext proposalContext,
            @PathVariable String contestYear, @PathVariable String contestUrlName,
            final SortFilterPage sortFilterPage) {
        setBasePageAttributes(proposalContext, model);

        String name = "contest." + contestYear +"." + contestUrlName;


        Metrics.counter("xcolab-view","endpoint","/contests/"+  contestYear +"/" + contestUrlName, "function", "/contests").increment();

        long startTime = System.nanoTime();


        String porposalsPage = showContestProposalsPage(response, model, proposalContext, sortFilterPage,
                loggedInMember);

        long endTime = System.nanoTime();

        long duration = (endTime - startTime);

        Metrics.timer("xcolab-view_timer","endpoint","/contests/"+  contestYear +"/" + contestUrlName, "function", "/contests").record(duration, NANOSECONDS);;

        return porposalsPage;
    }

    private List<ProposalWrapper> getProposals(ProposalContext proposalContext, UserWrapper loggedInMember) {
        final ClientHelper clients = proposalContext.getClients();
        final IProposalClient proposalClient = clients.getProposalClient();

        ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();
        ContestWrapper contest = proposalContext.getContest();

        final List<ProposalWrapper> activeProposals;
        final ContestStatus phaseStatus = contestPhase.getStatus();
        switch (phaseStatus) {
            case OPEN_FOR_SUBMISSION:
            case OPEN_FOR_EDIT:
                activeProposals = proposalClient.getActiveProposalsInContestPhase(
                        contestPhase.getId());
                break;
            default:
                activeProposals = proposalClient.getActiveProposalsInContestPhase(contestPhase.getId());
        }

        List<ProposalWrapper> proposals = new ArrayList<>();
        for (ProposalWrapper proposal : activeProposals) {

            try {
                final IProposalPhaseClient proposalPhaseClient = clients.getProposalPhaseClient();
                IProposal2Phase p2p = proposalPhaseClient.getProposal2PhaseByProposalIdContestPhaseId(proposal.getId(), contestPhase.getId());
                ProposalWrapper proposalWrapper;

                if (loggedInMember != null && StaticUserContext.getPermissionClient()
                        .canJudge(loggedInMember.getId(), contest.getId())) {
                    proposalWrapper = new ProposalJudgeWrapper(proposal, p2p.getVersionTo() == -1 ? proposal.getCurrentVersion() : p2p.getVersionTo(), contest, contestPhase, p2p, loggedInMember);
                } else {
                    proposalWrapper = new ProposalWrapper(proposal, p2p.getVersionTo() == -1 ? proposal.getCurrentVersion() : p2p.getVersionTo(), contest, contestPhase, p2p);
                }

                proposals.add(proposalWrapper);
            } catch (Proposal2PhaseNotFoundException ignored) {

            }
        }

        return proposals;
    }

    private String showContestProposalsPage(HttpServletResponse response, Model model, ProposalContext proposalContext,
            final SortFilterPage sortFilterPage, UserWrapper loggedInMember) {

        ContestWrapper contest = proposalContext.getContest();
        ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();

        final ContestType contestType = contest.getContestType();
        if (contestType.isRestrictedAccess() && !new ContestPermissions(loggedInMember)
                .getCanAccessContest(contest)) {
            return new AccessDeniedPage(loggedInMember).toViewName(response);
        }

        List<ProposalWrapper> proposals = getProposals(proposalContext, loggedInMember);

        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("proposals", new SortedProposalList(proposals, sortFilterPage,
                contestPhase));
        model.addAttribute("showCountdown",
                ConfigurationAttributeKey.SHOW_CONTEST_COUNTDOWN.get());
        model.addAttribute("defaultTimeZoneId",
            ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get());
        model.addAttribute("showContributorsColumn",
            ConfigurationAttributeKey.CONTESTS_ALLOW_OPEN_PROPOSALS.get());

        model.addAttribute("proposalVoteInContestPage", ConfigurationAttributeKey.PROPOSALS_VOTE_IN_CONTEST_PAGE.get());

        boolean showEditLink = proposalContext.getPermissions().getCanEditContest();
        boolean showDownloadLink = proposalContext.getPermissions().getCanDownload();
        model.addAttribute("showEditLink", showEditLink);
        model.addAttribute("showDownloadLink", showDownloadLink);

        Long proposalCreationMaxPerAuthor = ConfigurationAttributeKey.PROPOSALS_MAX_PER_AUTHOR_IN_CONTEST.get();
        model.addAttribute("proposalCreationMaxPerAuthor",proposalCreationMaxPerAuthor);
        if(proposalCreationMaxPerAuthor != 0 ) {
            int totalProposalsByAuthor = 0;
            for(ProposalWrapper p: proposals){
                if(p.getAuthor().getId() == loggedInMember.getId()) {
                    totalProposalsByAuthor = totalProposalsByAuthor + 1;
                }
            }

            model.addAttribute("hasAuthorReachedLimit", (totalProposalsByAuthor>= proposalCreationMaxPerAuthor));

        }


        setBasePageAttributes(proposalContext, model);

        return "/proposals/contestProposals";
    }

    @PostMapping("/contests/subscribeContest")
    public void handleAction(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper currentMember, ProposalContext proposalContext)
            throws ProposalsAuthorizationException, IOException {

        if (proposalContext.getPermissions().getCanSubscribeContest()) {
            long contestId = proposalContext.getContest().getId();
            long userId = currentMember.getId();
            if (contestClient.isMemberSubscribedToContest(contestId, userId)) {
                contestClient.unsubscribeMemberFromContest(contestId, userId);
            }
            else {
                contestClient.subscribeMemberToContest(contestId, userId);
            }
            response.sendRedirect(proposalContext.getContest().getContestLinkUrl());
        } else {
            throw new ProposalsAuthorizationException("User isn't allowed to subscribe contest");
        }
    }

    @PostMapping("/contests/{contestYear}/{contestUrlName}/assignAllJudges")
    public String assignAllJudges(HttpServletRequest request, HttpServletResponse response,
            UserWrapper currentMember, ProposalContext proposalContext)
            throws ProposalsAuthorizationException, IOException {

        if (proposalContext.getPermissions().getCanFellowActions()) {

            final ContestWrapper contest = proposalContext.getContest();
            final IProposalClient proposalClient = proposalContext.getClients().getProposalClient();
            long contestPhaseId = proposalContext.getContestPhase().getId();

            List<Long> selectedJudges = new ArrayList<>();
            for (UserWrapper judge : contest.getContestJudges()) {
                selectedJudges.add(judge.getId());
            }

            for (ProposalWrapper proposal : proposalClient.getProposalsInContest(contest.getId())) {
                proposalContext.getClients().getProposalPhaseClient().persistSelectedJudgesAttribute(
                        proposal.getId(),
                        contestPhaseId,
                        selectedJudges);
            }

            AlertMessage.success("All judges were assigned to every proposal.").flash(request);
            return "redirect:" + contest.getContestLinkUrl();
        } else {
            throw new ProposalsAuthorizationException("User isn't allowed to assign all judges");
        }
    }

    @PostMapping("/contests/{contestYear}/{contestUrlName}/removeUnfinishedJudges")
    public String removeUnfinishedJudges(HttpServletRequest request, HttpServletResponse response,
            UserWrapper currentMember, ProposalContext proposalContext)
            throws ProposalsAuthorizationException, IOException {

        if (proposalContext.getPermissions().getCanFellowActions()) {

            final ContestWrapper contest = proposalContext.getContest();
            final IProposalClient proposalClient = proposalContext.getClients().getProposalClient();
            long contestPhaseId = proposalContext.getContestPhase().getId();

            for (ProposalWrapper proposal : proposalClient.getProposalsInContest(contest.getId())) {
                List<Long> newSelectedJudges = proposal.getSelectedJudges().stream()
                        .filter(proposal::getIsReviewFinishedForJudge)
                        .collect(Collectors.toList());

                proposalContext.getClients().getProposalPhaseClient().persistSelectedJudgesAttribute(
                        proposal.getId(),
                        contestPhaseId,
                        newSelectedJudges);
            }

            AlertMessage.success("All judges who did not complete their reviews were removed.").flash(request);
            return "redirect:" + contest.getContestLinkUrl();
        } else {
            throw new ProposalsAuthorizationException("User isn't allowed to remove unfinished judges");
        }
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}/downloadContestProposalsList")
    public void downloadContestProposalsList(HttpServletRequest request, HttpServletResponse response,
            UserWrapper loggedInMember, ProposalContext proposalContext)
            throws IOException {

        try (ContestProposalsCsvWriter csvWriter = new ContestProposalsCsvWriter(response)) {
            List<ProposalWrapper> contestProposalsList = getProposals(proposalContext, loggedInMember);
            csvWriter.writeProposals(contestProposalsList);
        }
    }
}


