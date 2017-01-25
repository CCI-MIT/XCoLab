package org.xcolab.view.pages.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller

public class ContestDiscussionController extends BaseProposalsController {
    
    @Autowired
    private ProposalsContext proposalsContext;


    @GetMapping("/contests/{contestYear}/{contestUrlName}/discussion")
    public String showContestProposals(HttpServletRequest request, HttpServletResponse response, Model model) {

        model.addAttribute("discussionId",  proposalsContext.getContest(request).getDiscussionGroupId());
        setBasePageAttributes(request, model);
        return "proposals/contestDiscussion";
    }
}
