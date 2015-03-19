package org.xcolab.portlets.contestmanagement.controller;

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
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
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
public class ContestDetailsTeamTabController extends ContestDetailsBaseTabController {

    static final private TabEnum tab = ContestDetailsTabs.TEAM;
    static final private String TAB_VIEW = "details/teamTab";

    @ModelAttribute("usersList")
    public List<User> populateUsers() throws SystemException {
        return UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE);
    }

    @ModelAttribute("userNames")
    public List<String> populateUserNames() throws SystemException {
        ArrayList<String> userNamesList = new ArrayList<>();
        for (User user : UserLocalServiceUtil.getUsers(0, Integer.MAX_VALUE)) {
            userNamesList.add(user.getScreenName());
        }
        return userNamesList;
    }

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @RequestMapping(params = "tab=TEAM")
    public String showTeamTabController(PortletRequest request, PortletResponse response, Model model,
                                        @RequestParam(required = false) Long contestId)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        setPageAttributes(request, model, ContestDetailsTabs.TEAM);
        model.addAttribute("contestTeamBean", new ContestTeamBean(getContest()));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestTeam")
    public void updateTeamTabController(ActionRequest request, Model model, ActionResponse response) {

        if(!tabWrapper.getCanEdit()) {
            setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            ContestTeamBean contestTeamBeam = new ContestTeamBean(request, getContest());
            ContestTeamWrapper contestTeamWrapper = new ContestTeamWrapper(contestTeamBeam);
            contestTeamWrapper.updateContestTeamMembers();
            setSuccessRenderRedirect(response, tab.getName());
        } catch(Exception e){
            setNotFoundErrorRenderParameter(response);
        }
    }

    @RequestMapping(params = {"action=updateContestTeam", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

}