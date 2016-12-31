package org.xcolab.portlets.proposals.view;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.entity.utils.enums.MemberRole;
import org.xcolab.portlets.proposals.exceptions.ProposalIdOrContestIdInvalidException;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsSortFilterBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

@Controller
@RequestMapping("view")
public class ContestProposalsController extends BaseProposalsController {

    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=contestProposals")
    public String showContestProposals(RenderRequest request, RenderResponse response,
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

        return "contestProposals";
    }

    @ActionMapping(params = "action=redirectOldContestDiscussionUrl")
    public void redirectOldContestDiscussionUrl(ActionRequest request, ActionResponse response, Model model,
            @RequestParam Long contestId) throws IOException {

        String contestUrl = (proposalsContext.getContest(request)).getContestLinkUrl();
        response.sendRedirect(contestUrl + "/discussion");
    }

    @ActionMapping(params = "action=redirectOldContestProposalsUrl")
    public void redirectOldContestProposalsUrl(ActionRequest request, ActionResponse response, Model model,
                                       @RequestParam Long contestId, @RequestParam(required = false) Long phaseId) throws IOException {

        String redirectUrl;
        if (phaseId != null && phaseId > 0) {
            redirectUrl = proposalsContext.getContestPhase(request).getContestPhaseLinkUrl();
        } else {
            redirectUrl = proposalsContext.getContest(request).getContestLinkUrl();
        }
        response.sendRedirect(redirectUrl);
    }

    @ActionMapping(params = "action=redirectOldProposalUrl")
    public void redirectOldProposalUrl(
            @RequestParam(value="planId") Long proposalId,
            @RequestParam Long contestId,
            @RequestParam(required = false) Long phaseId,
            @RequestParam(required = false) Long version,
            @RequestParam(required = false) String tab,
            Model model, ActionRequest request, ActionResponse response)
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


