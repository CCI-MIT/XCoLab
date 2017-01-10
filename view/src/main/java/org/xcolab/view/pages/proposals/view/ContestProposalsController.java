package org.xcolab.view.pages.proposals.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//-- @RequestMapping("view")
public class ContestProposalsController extends BaseProposalsController {

    @Autowired
    private ProposalsContext proposalsContext;

    @GetMapping("/contests/{contestYear}/{contestUrlName}/phase/{contestPhaseId}")
    public String showContestProposalsWithContestPhaseId(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String contestYear,
            @PathVariable String contestUrlName,
            @PathVariable String contestPhaseId,
            final SortFilterPage sortFilterPage, Model model) {
        return showContestProposalsPage(request, response, contestYear, contestUrlName, contestPhaseId,sortFilterPage, model);
    }
    @GetMapping("/contests/{contestYear}/{contestUrlName}")
    public String showContestProposals(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String contestYear,
            @PathVariable String contestUrlName,
            final SortFilterPage sortFilterPage, Model model) {
        return showContestProposalsPage(request, response, contestYear, contestUrlName,null, sortFilterPage, model);
    }

    private String showContestProposalsPage(HttpServletRequest request, HttpServletResponse response,
            String contestYear,
            String contestUrlName,
            String phaseId,
            final SortFilterPage sortFilterPage, Model model) {

        proposalsContext.addPathVariable("contestYear", contestYear);
        proposalsContext.addPathVariable("contestUrlName", contestUrlName);
        proposalsContext.addPathVariable("phaseId",phaseId);
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

        return "/proposals/contestProposals";
    }

    //-- @ActionMapping(params = "action=redirectOldContestDiscussionUrl")
    public void redirectOldContestDiscussionUrl(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam Long contestId) throws IOException {

        String contestUrl = (proposalsContext.getContest(request)).getContestLinkUrl();
        response.sendRedirect(contestUrl + "/discussion");
    }

    //-- @ActionMapping(params = "action=redirectOldContestProposalsUrl")
    public void redirectOldContestProposalsUrl(HttpServletRequest request, HttpServletResponse response, Model model,
                                       @RequestParam Long contestId, @RequestParam(required = false) Long phaseId) throws IOException {

        String redirectUrl;
        if (phaseId != null && phaseId > 0) {
            redirectUrl = proposalsContext.getContestPhase(request).getContestPhaseLinkUrl();
        } else {
            redirectUrl = proposalsContext.getContest(request).getContestLinkUrl();
        }
        response.sendRedirect(redirectUrl);
    }

    //--  @ActionMapping(params = "action=redirectOldProposalUrl")
    public void redirectOldProposalUrl(
            @RequestParam(value="planId") Long proposalId,
            @RequestParam Long contestId,
            @RequestParam(required = false) Long phaseId,
            @RequestParam(required = false) Long version,
            @RequestParam(required = false) String tab,
            Model model, HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Proposal proposal = ProposalsContextUtil.getClients(request).getProposalClient().getProposal(proposalId);
            Contest contest = ContestClientUtil.getContest(contestId);

            String redirectUrl;
            if (phaseId != null && phaseId > 0) {
                ContestPhase contestPhase = ContestClientUtil.getContestPhase(phaseId);
                redirectUrl = proposal.getProposalLinkUrl(contest, contestPhase.getContestPhasePK());
            } else {
                redirectUrl = proposal.getProposalLinkUrl(contest);
            }
            if (version != null && version > 0) {
                redirectUrl += "/version/" + version;
            }
            if (StringUtils.isNotBlank(tab)) {
                redirectUrl += "/tab/" + tab;
            }
            response.sendRedirect(redirectUrl);
        }catch (ProposalNotFoundException | ContestNotFoundException ignored){

        }
    }

}


