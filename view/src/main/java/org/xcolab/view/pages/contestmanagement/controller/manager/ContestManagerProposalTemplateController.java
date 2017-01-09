package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.view.pages.contestmanagement.controller.common
        .ContestProposalTemplateTabController;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.utils.ProposalTemplateLifecycleUtil;
import org.xcolab.view.pages.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.ProposalTemplateWrapper;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class ContestManagerProposalTemplateController extends ContestProposalTemplateTabController {

    private final static Logger _log =
            LoggerFactory.getLogger(ContestManagerProposalTemplateController.class);
    static final private TabEnum tab = ContestManagerTabs.PROPOSALTEMPLATES;

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

    @RequestMapping(params = "tab=PROPOSALTEMPLATES")
    public String showProposalTemplatesTabController(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @RequestParam(value = "elementId", required = false) Long elementId) {

        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
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
        return ContestProposalTemplateTabController.TAB_VIEW;
    }

    private Long getFirstPlanTemplateId() {

        final List<PlanTemplate> planTemplates = PlanTemplateClientUtil
                .getPlanTemplates();
        if (!planTemplates.isEmpty()) {
            return planTemplates.get(0).getId_();
        }
        return -1L;

    }

    @RequestMapping(params = "action=createPROPOSALTEMPLATES")
    public void createNewProposalTemplateTabController(HttpServletRequest request, Model model,
            HttpServletResponse response) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            PlanTemplate newTemplate = ProposalTemplateLifecycleUtil.create();
            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(),
                    newTemplate.getId_());
        } catch (IOException e) {
            _log.warn("Create proposal template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = "action=deletePROPOSALTEMPLATES")
    public void deleteProposalTemplateTabController(HttpServletRequest request, Model model,
            @RequestParam(value = "elementId") Long elementId,
            HttpServletResponse response) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        try {
            ProposalTemplateLifecycleUtil.delete(elementId);
            SetRenderParameterUtil
                    .setSuccessRenderRedirectManagerTab(response, tab.getName(),
                            getFirstPlanTemplateId());
        } catch (IOException e) {
            _log.warn("Delete proposal template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updatePROPOSALTEMPLATES"})
    public void updateProposalTemplatesTabController(HttpServletRequest request, Model model,
            HttpServletResponse response,
            @ModelAttribute ProposalTemplateWrapper updatedProposalTemplateWrapper,
            BindingResult result) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(
                    updatedProposalTemplateWrapper.getPlanTemplateId(),
                    ProposalTemplateWrapper.getAllPlanTemplateSelectionItems()));
            SetRenderParameterUtil
                    .setErrorRenderParameter(response, "updateContestProposalTemplate");
            return;
        }

        try {
            updatedProposalTemplateWrapper.setUpdateExistingTemplate(true);
            updatedProposalTemplateWrapper.persist();
            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(),
                    updatedProposalTemplateWrapper.getPlanTemplateId());
        } catch (IOException e) {
            _log.warn("Update proposal template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updatePROPOSALTEMPLATES", "error=true"})
    public String reportError(HttpServletRequest request, Model model) {
        return TAB_VIEW;
    }
}
