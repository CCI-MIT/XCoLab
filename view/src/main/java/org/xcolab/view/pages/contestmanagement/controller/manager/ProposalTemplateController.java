package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.controller.AbstractProposalTemplateTabController;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.utils.ProposalTemplateLifecycleUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.ProposalTemplateWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class ProposalTemplateController extends AbstractProposalTemplateTabController {

    private static final ContestManagerTabs tab = ContestManagerTabs.PROPOSAL_TEMPLATES;

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, HttpServletRequest request) {
        return getAllVisibleTabsWrapped(request, ContestManagerTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @GetMapping("tab/PROPOSAL_TEMPLATES")
    public String showProposalTemplatesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @RequestParam(required = false) Long elementId) {

        if (!tabWrapper.getCanView()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        Long planTemplateId = elementId != null ? elementId : getFirstPlanTemplateId();
        model.addAttribute("planTemplateId", planTemplateId);
        if (planTemplateId >= 0) {
            ProposalTemplateWrapper proposalTemplateWrapper =
                    new ProposalTemplateWrapper(planTemplateId);
            model.addAttribute("contestProposalTemplateWrapper", proposalTemplateWrapper);
        }
        model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(planTemplateId,
                ProposalTemplateWrapper.getAllPlanTemplateSelectionItems()));
        model.addAttribute("elementId", planTemplateId);
        return TAB_VIEW;
    }

    private Long getFirstPlanTemplateId() {

        final List<PlanTemplate> planTemplates = PlanTemplateClientUtil
                .getPlanTemplates();
        if (!planTemplates.isEmpty()) {
            return planTemplates.get(0).getId_();
        }
        return -1L;

    }

    @PostMapping("tab/PROPOSAL_TEMPLATES/create")
    public String createNewProposalTemplateTabController(HttpServletRequest request,
            HttpServletResponse response, Model model) throws IOException {

        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        PlanTemplate newTemplate = ProposalTemplateLifecycleUtil.create();
        return "redirect:" + tab.getTabUrl(newTemplate.getId_());
    }

    @PostMapping("tab/PROPOSAL_TEMPLATES/delete/{elementId}")
    public String deleteProposalTemplateTabController(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @PathVariable Long elementId) throws IOException {

        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }
        ProposalTemplateLifecycleUtil.delete(elementId);
        AlertMessage.DELETED.flash(request);
        return "redirect:" + tab.getTabUrl();
    }

    @PostMapping("tab/PROPOSAL_TEMPLATES/update")
    public String updateProposalTemplatesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @ModelAttribute ProposalTemplateWrapper updatedProposalTemplateWrapper,
            BindingResult result) throws IOException {

        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        if (result.hasErrors()) {
            model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(
                    updatedProposalTemplateWrapper.getPlanTemplateId(),
                    ProposalTemplateWrapper.getAllPlanTemplateSelectionItems()));
            //TODO: process errors
            AlertMessage.danger("Failed to update element").flash(request);
            return "redirect:" + tab.getTabUrl(updatedProposalTemplateWrapper.getPlanTemplateId());
        }

        updatedProposalTemplateWrapper.setUpdateExistingTemplate(true);
        updatedProposalTemplateWrapper.persist();
        AlertMessage.CHANGES_SAVED.flash(request);
        return "redirect:" + tab.getTabUrl(updatedProposalTemplateWrapper.getPlanTemplateId());
    }
}
