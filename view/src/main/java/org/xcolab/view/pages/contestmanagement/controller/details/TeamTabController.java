package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.beans.ContestTeamBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestTeamWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}/tab/TEAM")
public class TeamTabController extends AbstractTabController {

    private static final ContestDetailsTabs tab = ContestDetailsTabs.TEAM;
    private static final String TAB_VIEW = "contestmanagement/details/teamTab";

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
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @GetMapping
    public String showTeamTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, @RequestParam(required = false) Long contestId) {

        if (!tabWrapper.getCanView()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        setPageAttributes(request, model, ContestDetailsTabs.TEAM);
        model.addAttribute("contestTeamBean", new ContestTeamBean(getContest()));
        return TAB_VIEW;
    }

    @PostMapping("update")
    public String updateTeamTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, @PathVariable long contestId) {

        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        ContestTeamBean contestTeamBeam = new ContestTeamBean(request, getContest());
        ContestTeamWrapper contestTeamWrapper = new ContestTeamWrapper(contestTeamBeam);
        contestTeamWrapper.updateContestTeamMembers();
        return "redirect:" + tab.getTabUrl(contestId);
    }
}