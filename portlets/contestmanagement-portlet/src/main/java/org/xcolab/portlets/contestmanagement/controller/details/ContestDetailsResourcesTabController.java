package org.xcolab.portlets.contestmanagement.controller.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestResourcesBean;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.WikiPageWrapper;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.text.ParseException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestDetailsResourcesTabController extends ContestDetailsBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestDetailsResourcesTabController.class);
    static final private TabEnum tab = ContestDetailsTabs.RESOURCES;
    static final private String TAB_VIEW = "details/resourcesTab";

    @Autowired
    private Validator validator;
    private WikiPageWrapper wikiPageWrapper;

    @InitBinder("contestResourcesBean")
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=RESOURCES")
    public String showResourcesTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {

        if (!tabWrapper.getCanView() || request.getRemoteUser() == null) {
            return NO_PERMISSION_TAB_VIEW;
        }

        Long userLoggedInId = Long.parseLong(request.getRemoteUser());
        wikiPageWrapper = new WikiPageWrapper(getContest(), userLoggedInId);
        setPageAttributes(request, model, tab);
        model.addAttribute("contestResourcesBean", wikiPageWrapper.getContestResourcesBean());
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestResources")
    public void updateResourcesTabController(ActionRequest request, Model model, ActionResponse response,
            @ModelAttribute ContestResourcesBean updatedContestResourcesBean, BindingResult result) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestResources");
            return;
        }

        try {
            wikiPageWrapper.updateWikiPage(updatedContestResourcesBean);
            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (SystemException | ParseException | IOException e) {
            _log.warn("Update contest resources failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestResources", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

}