package org.xcolab.view.pages.proposals.view.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextUtil;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//-- @RequestMapping("view")
public class PromoteProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    //-- @RequestMapping(params = {"action=promoteProposal"})
    @PostMapping("/contests/{contestYear}/{contestUrlName}/phase/{phaseId}/{proposalUrlString}/{proposalId}/tab/ADMIN/promoteProposal")
    public void handleAction(HttpServletRequest request, Model model, HttpServletResponse response,
                             @RequestParam Long contestId,
                             @RequestParam Long contestPhaseId,
                             @RequestParam Long proposalId ) throws IOException {

        ProposalsPermissions proposalsPermissions = proposalsContext.getPermissions(request);
        ContestPhase contestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
        if (proposalsPermissions.getCanPromoteProposalToNextPhase(contestPhase)) {
            try {
                Contest latestProposalContest = ProposalsContextUtil.getClients(request).getProposalClient().getLatestContestInProposal(proposalId);
                ContestPhase currentProposalContestPhase = ContestClientUtil.getContestPhase(contestPhaseId);
                ContestPhase activePhaseForContest = ContestClientUtil.getActivePhase(latestProposalContest.getContestPK());

                proposalsContext.getClients(request).getProposalPhaseClient().promoteProposal(proposalId,
                        activePhaseForContest.getContestPhasePK(),
                        currentProposalContestPhase.getContestPhasePK());

                response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request),
                        contestPhase.getContestPhasePK()));
            }catch (ContestNotFoundException ignored){

            }
        } else {
            response.sendRedirect(proposalsContext.getProposal(request).getProposalLinkUrl(proposalsContext.getContest(request),
                    contestPhase.getContestPhasePK()) + "/tab/ADMIN");
        }
    }
}
