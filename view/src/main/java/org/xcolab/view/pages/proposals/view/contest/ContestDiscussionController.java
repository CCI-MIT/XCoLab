package org.xcolab.view.pages.proposals.view.contest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.view.proposal.BaseProposalsController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class ContestDiscussionController extends BaseProposalsController {

    @GetMapping("/contests/{contestYear}/{contestUrlName}/discussion")
    public String showContestProposals(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext) {

        model.addAttribute("discussionId",  proposalContext.getContest().getDiscussionGroupId());
        setBasePageAttributes(proposalContext, model);
        return "proposals/contestDiscussion";
    }
}
