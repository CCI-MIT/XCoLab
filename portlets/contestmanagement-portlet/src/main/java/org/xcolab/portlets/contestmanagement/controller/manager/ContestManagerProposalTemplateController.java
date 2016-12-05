package org.xcolab.portlets.contestmanagement.controller.manager;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.client.contest.PlanTemplateClientUtil;
import org.xcolab.client.contest.pojo.templates.PlanTemplate;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.controller.common.ContestProposalTemplateTabController;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.utils.ProposalTemplateLifecycleUtil;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.portlets.contestmanagement.wrappers.ProposalTemplateWrapper;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestManagerProposalTemplateController extends ContestProposalTemplateTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestManagerProposalTemplateController.class);
    static final private TabEnum tab = ContestManagerTabs.PROPOSALTEMPLATES;

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, PortletRequest request) {
        return getAllVisibleTabsWrapped(request, ContestManagerTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=PROPOSALTEMPLATES")
    public String showProposalTemplatesTabController(PortletRequest request, PortletResponse response, Model model,
            @RequestParam(value = "elementId", required = false) Long elementId)
            throws PortalException, SystemException {

        if (!tabWrapper.getCanView() || request.getRemoteUser() == null) {
            return NO_PERMISSION_TAB_VIEW;
        }

        try {
            Long planTemplateId = elementId != null ? elementId : getFirstPlanTemplateId();
            model.addAttribute("planTemplateId", planTemplateId);
            if (planTemplateId >= 0) {
                ProposalTemplateWrapper proposalTemplateWrapper = new ProposalTemplateWrapper(planTemplateId);
                model.addAttribute("contestProposalTemplateWrapper", proposalTemplateWrapper);
            }
            model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(planTemplateId,
                    ProposalTemplateWrapper.getAllPlanTemplateSelectionItems()));
            model.addAttribute("elementId", planTemplateId);
            return ContestProposalTemplateTabController.TAB_VIEW;
        } catch (SystemException | PortalException e) {
            _log.warn("Exception while rendering CMS proposal template tab", e);
            SetRenderParameterUtil.addActionExceptionMessageToSession(request, e);
        }
        return NOT_FOUND_TAB_VIEW;
    }


    @RequestMapping(params = "action=createPROPOSALTEMPLATES")
    public void createNewProposalTemplateTabController(ActionRequest request, Model model, ActionResponse response) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            PlanTemplate newTemplate = ProposalTemplateLifecycleUtil.create();
            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(), newTemplate.getId_());
        } catch (IOException e) {
            _log.warn("Create proposal template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = "action=deletePROPOSALTEMPLATES")
    public void deleteProposalTemplateTabController(ActionRequest request, Model model,
            @RequestParam(value = "elementId", required = true) Long elementId,
            ActionResponse response) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        try {
            ProposalTemplateLifecycleUtil.delete(elementId);
            SetRenderParameterUtil
                    .setSuccessRenderRedirectManagerTab(response, tab.getName(), getFirstPlanTemplateId());
        } catch (IOException e) {
            _log.warn("Delete proposal template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updatePROPOSALTEMPLATES"})
    public void updateProposalTemplatesTabController(ActionRequest request, Model model, ActionResponse response,
            @ModelAttribute ProposalTemplateWrapper updatedProposalTemplateWrapper, BindingResult result) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(
                    updatedProposalTemplateWrapper.getPlanTemplateId(),
                    ProposalTemplateWrapper.getAllPlanTemplateSelectionItems()));
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestProposalTemplate");
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
    public String reportError(PortletRequest request, Model model) {
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
}
