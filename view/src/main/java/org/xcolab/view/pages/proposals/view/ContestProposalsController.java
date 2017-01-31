package org.xcolab.view.pages.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.entity.utils.enums.MemberRole;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalIdOrContestIdInvalidException;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.view.pages.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.view.pages.proposals.wrappers.ProposalsSortFilterBean;
import org.xcolab.view.util.pagination.SortFilterPage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ContestProposalsController extends BaseProposalsController {

    @Autowired
    private ProposalsContext proposalsContext;

    @GetMapping("/contests/{contestYear}/{contestUrlName}/phase/{contestPhaseId}")
    public String showContestProposalsWithContestPhaseId(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String contestYear,
            @PathVariable String contestUrlName,
            @PathVariable String contestPhaseId,
            final SortFilterPage sortFilterPage, Model model) {
        setBasePageAttributes(request, model);
        return showContestProposalsPage(request, response, contestYear, contestUrlName, contestPhaseId,sortFilterPage, model);
    }
    @GetMapping("/contests/{contestYear}/{contestUrlName}")
    public String showContestProposals(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String contestYear,
            @PathVariable String contestUrlName,
            final SortFilterPage sortFilterPage, Model model) {
        setBasePageAttributes(request, model);
        return showContestProposalsPage(request, response, contestYear, contestUrlName,null, sortFilterPage, model);
    }

    private String showContestProposalsPage(HttpServletRequest request, HttpServletResponse response,
            String contestYear,
            String contestUrlName,
            String phaseId,
            final SortFilterPage sortFilterPage, Model model) {


        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        Contest contest = proposalsContext.getContest(request);

        if (contest == null || contestPhase == null) {
            throw new ProposalIdOrContestIdInvalidException();
        }

        Member u = MemberAuthUtil.getMemberOrNull(request);
        List<Proposal> proposals = new ArrayList<>();

        for (Proposal proposal : ProposalsContextUtil.getClients(request).getProposalClient().getActiveProposalsInContestPhase(contestPhase.getContestPhasePK())) {

            try {
                Proposal2Phase p2p = proposalsContext.getClients(request).getProposalPhaseClient().getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
                Proposal proposalWrapper;

                if (u != null && PermissionsClient.memberHasRole(u.getUserId(), MemberRole.JUDGE.getRoleId())) {
                    proposalWrapper = new ProposalJudgeWrapper(proposal, p2p.getVersionTo() == -1 ? proposal.getCurrentVersion() : p2p.getVersionTo(), contest, contestPhase, p2p, u);

                } else {
                    proposalWrapper = new Proposal(proposal, p2p.getVersionTo() == -1 ? proposal.getCurrentVersion() : p2p.getVersionTo(), contest, contestPhase, p2p);
                }

                proposals.add(proposalWrapper);
            }catch ( Proposal2PhaseNotFoundException ignored){

            }
        }


        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("showCountdown",
                ConfigurationAttributeKey.SHOW_CONTEST_COUNTDOWN.get());
        model.addAttribute("proposals", new ProposalsSortFilterBean(proposals, sortFilterPage));
        model.addAttribute("defaultTimeZoneId", ConfigurationAttributeKey.DEFAULT_TIME_ZONE_ID.get());
        model.addAttribute("contestCompleted", proposalsContext.getContestWrapped(request).isContestCompleted(proposalsContext.getContestPhaseWrapped(request)));
        model.addAttribute("showShareButtons", ConfigurationAttributeKey.SHOW_SHARE_BUTTONS.get());
        boolean showAdminLink = false;
        if(u != null) {
            showAdminLink = PermissionsClient.canAdminAll(u.getUserId());
        }
        model.addAttribute("showAdminLink", showAdminLink);

        setSeoTexts(request, contest.getContestShortName(), null, contest.getContestDescription());

        setBasePageAttributes(request, model);
        return "/proposals/contestProposals";
    }


}


