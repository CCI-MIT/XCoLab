package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.view.pages.proposals.requests.RequestMembershipBean;
import org.xcolab.view.pages.proposals.requests.RequestMembershipInviteBean;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalTab;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalTeamTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext proposalsContext;

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=TEAM")
    public String show(Model model, HttpServletRequest request) {

        setCommonModelAndPageAttributes(request, model, ProposalTab.TEAM);

        model.addAttribute("requestMembershipBean", new RequestMembershipBean());
        model.addAttribute("requestMembershipInviteBean", new RequestMembershipInviteBean());

        return "/proposals/proposalTeam";
    }
}