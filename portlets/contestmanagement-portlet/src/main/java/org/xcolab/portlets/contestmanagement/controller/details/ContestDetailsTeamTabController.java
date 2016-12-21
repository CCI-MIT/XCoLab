package org.xcolab.portlets.contestmanagement.controller.details;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.beans.ContestTeamBean;
import org.xcolab.portlets.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.ContestTeamWrapper;
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

    private static final Logger _log = LoggerFactory.getLogger(ContestDetailsTeamTabController.class);

    private static final TabEnum tab = ContestDetailsTabs.TEAM;
    private static final String TAB_VIEW = "details/teamTab";

    @ModelAttribute("usersList")
    public List<Member> populateUsers() {
            return MembersClient.listAllMembers();
    }

    @ModelAttribute("userNames")
    public List<String> populateUserNames() {
            ArrayList<String> userNamesList = new ArrayList<>();
            for (Member user : MembersClient.listAllMembers()) {
                userNamesList.add(user.getScreenName());
            }
            return userNamesList;

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
            @RequestParam(required = false) Long contestId) {

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
        } catch (IOException e) {
            _log.warn("Update contest team failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestTeam", "error=true"})
    public String reportError(PortletRequest request, Model model) {
        return TAB_VIEW;
    }

}