package org.xcolab.view.pages.proposals.view.proposal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/legacy/contest")
public class LegacyRedirectController {

    @GetMapping("{contestId}")
    public void redirectOldContestProposalsUrl(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable Long contestId, @RequestParam(required = false) Long phaseId) throws IOException {

        try {
            String redirectUrl;
            if (phaseId != null) {
                redirectUrl = ContestClientUtil.getContest(contestId).getContestLinkUrl(phaseId);
            } else {
                redirectUrl = ContestClientUtil.getContest(contestId).getContestLinkUrl();
            }
            response.sendRedirect(redirectUrl);
        } catch (ContestNotFoundException e) {
            response.sendError(404, "Content has moved but can't be found");
        }
    }

    @GetMapping("{contestId}/discussion")
    public void redirectOldContestDiscussionUrl(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable Long contestId) throws IOException {
        try {
            String contestUrl = ContestClientUtil.getContest(contestId).getContestLinkUrl();
            response.sendRedirect(contestUrl + "/discussion");
        } catch (ContestNotFoundException e) {
            response.sendError(404, "Content has moved but can't be found");
        }
    }

    @GetMapping("{contestId}/proposal/{proposalId}")
    public void redirectOldProposalUrl(HttpServletRequest request, HttpServletResponse response,
            Model model, @PathVariable long proposalId, @PathVariable long contestId,
            @RequestParam(required = false) Long phaseId)
            throws IOException {

        try {
            Contest contest = ContestClientUtil.getContest(contestId);
            Proposal proposal = ProposalClientUtil.getProposal(proposalId);

            String redirectUrl;
            if (phaseId != null) {
                redirectUrl = proposal.getProposalLinkUrl(contest, phaseId);
            } else {
                redirectUrl = proposal.getProposalLinkUrl(contest);
            }
            response.sendRedirect(redirectUrl);
        } catch (ProposalNotFoundException | ContestNotFoundException e) {
            response.sendError(404, "Content has moved but can't be found");
        }
    }
}
