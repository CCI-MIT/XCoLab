package org.xcolab.view.pages.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalPhaseClient;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.entity.utils.enums.MemberRole;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.view.pages.proposals.exceptions.ProposalIdOrContestIdInvalidException;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.view.pages.proposals.wrappers.ProposalsSortFilterBean;
import org.xcolab.view.util.pagination.SortFilterPage;

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
        return showContestProposalsPage(request, response, contestYear, contestUrlName,
                phaseId, sortFilterPage, model, loggedInMember);
    }

    @GetMapping("/contests/{contestYear}/{contestUrlName}")
    public String showContestProposals(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String contestYear,
            @PathVariable String contestUrlName,
            final SortFilterPage sortFilterPage, Model model, Member loggedInMember) {
        setBasePageAttributes(request, model);
        return showContestProposalsPage(request, response, contestYear, contestUrlName,
                null, sortFilterPage, model, loggedInMember);
    }

    private String showContestProposalsPage(HttpServletRequest request, HttpServletResponse response,
            String contestYear,
            String contestUrlName,
            String phaseId,
            final SortFilterPage sortFilterPage, Model model, Member loggedInMember) {


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
        model.addAttribute("showCountdown",
                ConfigurationAttributeKey.SHOW_CONTEST_COUNTDOWN.get());
        model.addAttribute("proposals", new ProposalsSortFilterBean(proposals, sortFilterPage));
        model.addAttribute("defaultTimeZoneId", ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get());
        model.addAttribute("showShareButtons", ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get());
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
}


