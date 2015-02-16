package org.xcolab.portlets.contestmanagement.controller;

import com.ext.portlet.model.ContestWrapper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestTeamBean;
import org.xcolab.portlets.contestmanagement.views.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.wrappers.ContestTeamWrapper;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class ContestDetailsAdminTabController extends ContestDetailsBaseTabController {

    static final private TabEnum tab = ContestDetailsTabs.ADMIN;
    static final private String TAB_VIEW = "details/adminTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=ADMIN")
    public String showAdminTabController(PortletRequest request, PortletResponse response, Model model,
                                        @RequestParam(required = false) Long contestId)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        setPageAttributes(request, model, ContestDetailsTabs.TEAM);
        model.addAttribute("contestAdminBean", new ContestWrapper(getContest()));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestAdmin")
    public void updateTeamTabController(ActionRequest request, Model model,
                                        @ModelAttribute ContestWrapper updateContestAdminBean,
                                        ActionResponse response) {

        if(!tabWrapper.getCanEdit()) {
            setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            updateContestAdminBean.persist();
            setSuccessRenderRedirect(response, tab.getName());
        } catch(Exception e){
            setNotFoundErrorRenderParameter(response);
        }
    }

    @RequestMapping(params = {"action=updateContestAdmin", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

    @RequestMapping
    public String updateTeamTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        return NOT_FOUND_TAB_VIEW;
    }

    @RequestMapping(params = {"action=showNoPermission", "error=true"})
    public String showNoPermissionErrorRenderParameter(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        return NO_PERMISSION_TAB_VIEW;
    }

    @RequestMapping(params = {"action=showNotFound", "error=true"})
    public String showNotFoundErrorRenderParameter(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        return NOT_FOUND_TAB_VIEW;
    }

}