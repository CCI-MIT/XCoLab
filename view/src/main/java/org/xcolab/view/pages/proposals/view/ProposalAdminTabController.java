package org.xcolab.view.pages.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContext;
import org.xcolab.view.pages.proposals.wrappers.ProposalTab;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProposalAdminTabController extends BaseProposalTabController {

    @Autowired
    private ProposalsContext context;


    @GetMapping({"/contests/{contestYear}/{contestUrlName}/phase/{phaseId}/{proposalUrlString}/{proposalId}/tab/ADMIN",
                 "/contests/{contestYear}/{contestUrlName}/c/{proposalUrlString}/{proposalId}/tab/ADMIN"})
    public String showProposalDetails(Model model, HttpServletRequest request) {

        ContestClient contestClient = context.getClients(request).getContestClient();
        setCommonModelAndPageAttributes(request, model, ProposalTab.ADMIN);
        model.addAttribute("availableRibbons", contestClient.getAllContestPhaseRibbonType());
        
        return "proposals/proposalAdmin";
    }
}
