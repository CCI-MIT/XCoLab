package org.xcolab.portlets.contestmanagement.controller.manager;

import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.controller.common.ContestProposalTemplateTabController;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.ContestProposalTemplateWrapper;
import org.xcolab.portlets.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.List;

/**
 * Created by Thomas on 5/15/2015.
 */
@Controller
@RequestMapping("view")
public class ContestManagerProposalTempateController extends ContestProposalTemplateTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestManagerProposalTempateController.class);
    static final private TabEnum tab = ContestManagerTabs.PROPOSALTEMPLATES;

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, PortletRequest request) throws PortalException, SystemException {
        return getAllVisibleTabsWrapped(model, request, ContestManagerTabs.values());
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException{
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }


    @RequestMapping(params = "tab=PROPOSALTEMPLATES")
    public String showProposalTemplatesTabController(PortletRequest request, PortletResponse response, Model model,
                                                    @RequestParam(value = "elementId", required = false) Long elementId)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView() || request.getRemoteUser() == null) {
            return NO_PERMISSION_TAB_VIEW;
        }

        try {
            Long planTemplateId = elementId != null ? elementId : getFirstPlanTemplateId();
            ContestProposalTemplateWrapper contestProposalTemplateWrapper = new ContestProposalTemplateWrapper(planTemplateId);
            model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(planTemplateId, ContestProposalTemplateWrapper.getAllPlanTemplateSelectionItems()));
            model.addAttribute("contestProposalTemplateWrapper", contestProposalTemplateWrapper);
            model.addAttribute("elementId", planTemplateId);
            return ContestProposalTemplateTabController.TAB_VIEW;
        } catch (Exception e){
            _log.warn("Could not create proposal template wrapper: ", e);
        }
        return NOT_FOUND_TAB_VIEW;
    }


    @RequestMapping(params = "action=createPROPOSALTEMPLATES")
    public void createNewProposalTemplateTabController(ActionRequest request, Model model, ActionResponse response) {

        if(!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            PlanTemplate newTemplate = ContestProposalTemplateWrapper.createNewTemplate();
            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(), newTemplate.getId());
        } catch(Exception e){
            _log.warn("Create proposal template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = "action=deletePROPOSALTEMPLATES")
    public void deleteProposalTemplateTabController(ActionRequest request, Model model,
                                            @RequestParam(value = "elementId", required = true) Long elementId,
                                            ActionResponse response) {

        if(!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        try {
            ContestProposalTemplateWrapper.deleteTemplate(elementId);
            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(), getFirstPlanTemplateId());
        } catch(Exception e){
            _log.warn("Delete proposal template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updatePROPOSALTEMPLATES", "manager=true"})
    public void updateProposalTemplatesTabController(ActionRequest request, Model model, ActionResponse response,
                                                    @ModelAttribute ContestProposalTemplateWrapper updatedContestProposalTemplateWrapper, BindingResult result) {

        if(!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(
                    updatedContestProposalTemplateWrapper.getPlanTemplateId(),
                    ContestProposalTemplateWrapper.getAllPlanTemplateSelectionItems()));
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestProposalTemplate");
            return;
        }

        try{
            updatedContestProposalTemplateWrapper.setUpdateExistingTemplate(true);
            updatedContestProposalTemplateWrapper.persist();
            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(), updatedContestProposalTemplateWrapper.getPlanTemplateId());
        } catch(Exception e){
            _log.warn("Update proposal template failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updatePROPOSALTEMPLATES", "error=true", "manager=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }


    private Long getFirstPlanTemplateId()throws Exception{
        return PlanTemplateLocalServiceUtil.getPlanTemplates(0, Integer.MAX_VALUE).get(0).getId();
    }
}
