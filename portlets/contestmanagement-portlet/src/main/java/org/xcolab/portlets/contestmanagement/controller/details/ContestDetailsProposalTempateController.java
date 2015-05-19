package org.xcolab.portlets.contestmanagement.controller.details;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.controller.common.ContestProposalTemplateTabController;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.ContestProposalTemplateWrapper;
import org.xcolab.wrapper.ContestWrapper;
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
public class ContestDetailsProposalTempateController extends ContestProposalTemplateTabController {

    private Contest contest;
    private ContestWrapper contestWrapper;
    private final static Log _log = LogFactoryUtil.getLog(ContestDetailsProposalTempateController.class);
    static final private TabEnum tab = ContestDetailsTabs.PROPOSALTEMPLATE;

    @ModelAttribute("tabs")
    @Override
    public List<TabWrapper> populateTabs(Model model, PortletRequest request) throws PortalException, SystemException {
        return getAllVisibleTabsWrapped(model, request, ContestDetailsTabs.values());
    }

    @ModelAttribute("contestWrapper")
    public ContestWrapper populateContestWrapper(Model model, PortletRequest request){
        try {
            Long contestId = getContestIdFromRequest(request);
            if (contestId != null) {
                contest = ContestLocalServiceUtil.getContest(contestId);
                contestWrapper = new ContestWrapper(contest);
                return contestWrapper;
            }
            throw new Exception("No contest id provided.Severe.");
        } catch (Exception e){
        }
        return null;
    }
    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException{
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }


    @RequestMapping(params = "tab=PROPOSALTEMPLATE")
    public String showProposalTemplateTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView() || request.getRemoteUser() == null) {
            return NO_PERMISSION_TAB_VIEW;
        }

        try {
            ContestProposalTemplateWrapper contestProposalTemplateWrapper = new ContestProposalTemplateWrapper(contest);
            model.addAttribute("contestProposalTemplateWrapper", contestProposalTemplateWrapper);
            return ContestProposalTemplateTabController.TAB_VIEW;
        } catch (Exception e){
            _log.warn("Could not create proposal template wrapper: ", e);
        }
        return NOT_FOUND_TAB_VIEW;
    }


    @RequestMapping(params = "action=updateContestProposalTemplate")
    public void updateProposalTemplateTabController(ActionRequest request, Model model, ActionResponse response,
                                                    @ModelAttribute ContestProposalTemplateWrapper updatedContestProposalTemplateWrapper, BindingResult result) {

        if(!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestProposalTemplate");
            return;
        }

        try{
            updatedContestProposalTemplateWrapper.init(contest);
            updatedContestProposalTemplateWrapper.updateNewProposalTemplateSections();
            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, contest.getContestPK(), tab.getName());
        } catch(Exception e){
            _log.warn("Update proposal template failed with: ", e);
            _log.warn(e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestProposalTemplate", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }
}
