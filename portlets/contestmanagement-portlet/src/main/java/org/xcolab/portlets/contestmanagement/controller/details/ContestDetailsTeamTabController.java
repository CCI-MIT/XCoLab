package org.xcolab.portlets.contestmanagement.controller.details;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestTeamBean;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.ContestTeamWrapper;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestDetailsTeamTabController extends ContestDetailsBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestDetailsTeamTabController.class);
    private static final TabEnum tab = ContestDetailsTabs.TEAM;
    private static final String TAB_VIEW = "details/teamTab";

    @ModelAttribute("usersList")
    public List<User> populateUsers() {
        try {
            return UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    @ModelAttribute("userNames")
    public List<String> populateUserNames() {
        try {
            ArrayList<String> userNamesList = new ArrayList<>();
            for (User user : UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE)) {
                userNamesList.add(user.getScreenName());
            }
            return userNamesList;
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        }
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=TEAM")
    public String showTeamTabController(PortletRequest request, PortletResponse response, Model model,
            @RequestParam(required = false) Long contestId)
            throws PortalException, SystemException {

        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        setPageAttributes(request, model, ContestDetailsTabs.TEAM);
        model.addAttribute("contestTeamBean", new ContestTeamBean(getContest()));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestTeam")
    public void updateTeamTabController(ActionRequest request, Model model, ActionResponse response) {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            ContestTeamBean contestTeamBeam = new ContestTeamBean(request, getContest());
            ContestTeamWrapper contestTeamWrapper = new ContestTeamWrapper(contestTeamBeam);
            contestTeamWrapper.updateContestTeamMembers();
            SetRenderParameterUtil.setSuccessRenderRedirectDetailsTab(response, getContestPK(), tab.getName());
        } catch (SystemException | IOException | PortalException e) {
            _log.warn("Update contest team failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestTeam", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

}