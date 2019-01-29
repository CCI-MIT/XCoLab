package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.proposals.IProposalAttributeClient;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.proposals.impact.adaptation.AdaptationCategory;
import org.xcolab.view.pages.proposals.impact.adaptation.AdaptationImpactBean;
import org.xcolab.view.pages.proposals.impact.adaptation.AdaptationService;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalAdaptationImpactTabController extends BaseProposalTabController {

    private final AdaptationService adaptationService;

    @Autowired
    public ProposalAdaptationImpactTabController(AdaptationService adaptationService) {
        this.adaptationService = adaptationService;
    }

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADAPTATION_IMPACT")
    public String show(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember) {

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanView()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }

        final ClientHelper clients = proposalContext.getClients();
        final IProposalAttributeClient attributeClient = clients.getProposalAttributeClient();
        final Long proposalId = proposalContext.getProposal().getId();

        setCommonModelAndPageAttributes(request, model, proposalContext,
                ProposalTab.ADAPTATION_IMPACT);

        model.addAttribute("categories", Arrays.asList(AdaptationCategory.values()));

        if (!model.containsAttribute("adaptationImpactBean")) {
            model.addAttribute("adaptationImpactBean",
                    adaptationService.getAdaptationImpactBean(attributeClient, proposalId));
        }

        return "/proposals/adaptationImpact";
    }

    @PostMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=ADAPTATION_IMPACT")
    public String save(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember,
            @PathVariable long proposalId,
            @ModelAttribute AdaptationImpactBean adaptationImpactBean,
            BindingResult bindingResult) {

        if (!proposalContext.getPermissions().getCanEditBasicImpact()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }

        final ClientHelper clients = proposalContext.getClients();
        final IProposalAttributeClient attributeClient = clients.getProposalAttributeClient();

        // make sure the proposal and author ids are set correctly
        adaptationImpactBean.setProposalId(proposalId);
        adaptationImpactBean.setAuthorUserId(currentMember.getId());

        adaptationService.save(adaptationImpactBean,
                attributeClient);

        AlertMessage.CHANGES_SAVED.flash(request);
        return show(request, response, model, proposalContext, currentMember);
    }
}
