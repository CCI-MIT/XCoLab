package org.xcolab.view.pages.contestmanagement.controller.details;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.beans.ContestTeamBean;
import org.xcolab.view.pages.contestmanagement.entities.ContestDetailsTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestTeamWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/admin/contest/details/contestId/{contestId}/tab/TEAM")
public class TeamTabController extends AbstractTabController {

    private static final ContestDetailsTabs tab = ContestDetailsTabs.TEAM;
    private static final String TAB_VIEW = "contestmanagement/details/teamTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @GetMapping
    public String showTeamTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @PathVariable long contestId) {

        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        model.addAttribute("contestTeamBean", new ContestTeamBean(contestClient.getContest(contestId)));
        return TAB_VIEW;
    }

    @PostMapping("update")
    public String updateTeamTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @PathVariable long contestId) {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        ContestTeamBean contestTeamBeam = new ContestTeamBean(request, contestClient.getContest(contestId));
        ContestTeamWrapper contestTeamWrapper = new ContestTeamWrapper(contestTeamBeam);
        contestTeamWrapper.updateContestTeamMembers();
        return "redirect:" + tab.getTabUrl(contestId);
    }
}
