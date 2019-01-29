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

import org.xcolab.client.contest.pojo.IProposalTemplate;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.controller.AbstractProposalTemplateTabController;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.utils.ProposalTemplateLifecycleUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.ProposalTemplateWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

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
        return tabWrapper;
    }

    @GetMapping("tab/PROPOSAL_TEMPLATES")
    public String showProposalTemplatesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @RequestParam(required = false) Long elementId) {

        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        Long proposalTemplateId = elementId != null ? elementId : getFirstProposalTemplateId();
        if (proposalTemplateId >= 0) {
            ProposalTemplateWrapper proposalTemplateWrapper =
                    new ProposalTemplateWrapper(proposalTemplateId);
            model.addAttribute("contestProposalTemplateWrapper", proposalTemplateWrapper);
        }
        model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(proposalTemplateId,
                ProposalTemplateWrapper.getAllProposalTemplateSelectionItems()));
        model.addAttribute("elementId", proposalTemplateId);
        return TAB_VIEW;
    }

    private Long getFirstProposalTemplateId() {

        final List<IProposalTemplate> proposalTemplates = proposalTemplateClient
                .getProposalTemplates();
        if (!proposalTemplates.isEmpty()) {
            return proposalTemplates.get(0).getId();
        }
        return -1L;

    }

    @PostMapping("tab/PROPOSAL_TEMPLATES/create")
    public String createNewProposalTemplateTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member)
            throws IOException {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        IProposalTemplate newTemplate = ProposalTemplateLifecycleUtil.create();
        return "redirect:" + tab.getTabUrl(newTemplate.getId());
    }

    @PostMapping("tab/PROPOSAL_TEMPLATES/delete/{elementId}")
    public String deleteProposalTemplateTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @PathVariable Long elementId) throws IOException {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        ProposalTemplateLifecycleUtil.delete(elementId);
        AlertMessage.DELETED.flash(request);
        return "redirect:" + tab.getTabUrl();
    }

    @PostMapping("tab/PROPOSAL_TEMPLATES/update")
    public String updateProposalTemplatesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @ModelAttribute ProposalTemplateWrapper updatedProposalTemplateWrapper,
            BindingResult result) throws IOException {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (result.hasErrors()) {
            model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(
                    updatedProposalTemplateWrapper.getProposalTemplateId(),
                    ProposalTemplateWrapper.getAllProposalTemplateSelectionItems()));
            AlertMessage.danger("Failed to update element").flash(request);
            return "redirect:" + tab.getTabUrl(updatedProposalTemplateWrapper.getProposalTemplateId());
        }

        updatedProposalTemplateWrapper.setUpdateExistingTemplate(true);
        updatedProposalTemplateWrapper.persist();
        AlertMessage.CHANGES_SAVED.flash(request);
        return "redirect:" + tab.getTabUrl(updatedProposalTemplateWrapper.getProposalTemplateId());
    }
}
