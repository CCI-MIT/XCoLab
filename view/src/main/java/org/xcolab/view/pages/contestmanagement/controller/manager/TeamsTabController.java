package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.PlatformTeamsClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.PlatformTeam;
import org.xcolab.util.html.LabelValue;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.PlatformTeamBean;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class TeamsTabController extends AbstractTabController {

    private static final ContestManagerTabs tab = ContestManagerTabs.TEAMS;
    private static final String TAB_VIEW = "contestmanagement/manager/teamsTab";

    private static final String CONTEST_TEAM_BEAN_ATTRIBUTE_KEY = "teamBean";

    private static final String NEW_TEAM_NAME = "New team";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    private List<org.xcolab.client.members.pojo.PlatformTeam> getTeams() {
        return PlatformTeamsClient.listAllPlatformTeams();
    }

    @GetMapping("tab/TEAMS")
    public String showTeamTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, Member member,
            @RequestParam(value = "elementId", required = false) Long elementId)
            throws EntityNotFoundException {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        List<PlatformTeam> teams = getTeams();

        PlatformTeam team = null;
        if (elementId != null) {
            team = PlatformTeamsClient.getPlatformTeam(elementId);
        } else if (!teams.isEmpty()) {
            team = teams.get(0);
        }

        Long teamId = -1L;
        if (team != null && !model.containsAttribute(CONTEST_TEAM_BEAN_ATTRIBUTE_KEY)) {
            List<Member> members = PlatformTeamsClient.getTeamMembers(team);
            model.addAttribute(CONTEST_TEAM_BEAN_ATTRIBUTE_KEY,
                    new PlatformTeamBean(team, members));
            teamId = team.getId_();
        }

        model.addAttribute("teamId", teamId);
        model.addAttribute("elementSelectIdWrapper",
                new ElementSelectIdWrapper(teamId, getTeamItems(teams)));
        return TAB_VIEW;
    }

    @PostMapping("tab/TEAMS")
    public void updateTeam(HttpServletRequest request, HttpServletResponse response,
            @ModelAttribute PlatformTeamBean teamBean) throws IOException, EntityNotFoundException {

        if (!tabWrapper.getCanEdit()) {
            response.sendRedirect(ContestManagerTabs.TEAMS.getTabUrl());
        }

        PlatformTeam team;
        if (teamBean != null && teamBean.getTeamId() != null) {
            team = PlatformTeamsClient.getPlatformTeam(teamBean.getTeamId());
            team.setName(teamBean.getName());
            PlatformTeamsClient.updatePlatformTeam(team);
        } else {
            team = addNewTeam();
        }

        response.sendRedirect(ContestManagerTabs.TEAMS.getTabUrl(team.getId_()));
    }

    @PostMapping("tab/TEAMS/{teamId}/delete")
    public void deleteTeam(HttpServletRequest request, HttpServletResponse response,
            @PathVariable long teamId) throws IOException {

        if (!tabWrapper.getCanEdit()) {
            response.sendRedirect(ContestManagerTabs.TEAMS.getTabUrl());
            return;
        }

        deleteTeam(teamId);

        response.sendRedirect(ContestManagerTabs.TEAMS.getTabUrl());
    }

    @PostMapping("tab/TEAMS/{teamId}/removeMember/{memberId}")
    public void removeMember(HttpServletRequest request, HttpServletResponse response, Model model,
            Member member, @PathVariable long teamId, @PathVariable long memberId)
            throws IOException {

        if (!tabWrapper.getCanEdit()) {
            response.sendRedirect(ContestManagerTabs.TEAMS.getTabUrl(teamId));
            return;
        }

        removeMember(teamId, memberId);

        response.sendRedirect(ContestManagerTabs.TEAMS.getTabUrl(teamId));
    }

    @PostMapping("tab/TEAMS/{teamId}/addMember")
    public void addMember(HttpServletRequest request, HttpServletResponse response, Model model,
            Member member, @PathVariable long teamId, @RequestParam long userId)
            throws IOException {

        if (!tabWrapper.getCanEdit()) {
            return;
        }

        addMember(teamId, userId);
    }

    private List<LabelValue> getTeamItems(List<PlatformTeam> teams) {
        List<LabelValue> teamItems = new ArrayList<>();
        for (PlatformTeam team : teams) {
            teamItems.add(new LabelValue(team.getId_(), team.getName()));
        }
        return teamItems;
    }

    private void addMember(Long teamId, Long memberId) {
        try {
            PlatformTeam team = PlatformTeamsClient.getPlatformTeam(teamId);
            Member member = MembersClient.getMember(memberId);
            PlatformTeamsClient.addMember(team, member);
        } catch (EntityNotFoundException | MemberNotFoundException e) {
            throw new IllegalArgumentException("Invalid teamId or memberId.");
        }
    }

    private void removeMember(Long teamId, Long memberId) {
        try {
            PlatformTeam team = PlatformTeamsClient.getPlatformTeam(teamId);
            Member member = MembersClient.getMember(memberId);
            PlatformTeamsClient.removeMember(team, member);
        } catch (EntityNotFoundException | MemberNotFoundException e) {
            throw new IllegalArgumentException("Invalid teamId or memberId.");
        }
    }

    private void deleteTeam(Long teamId) {
        try {
            PlatformTeam team = PlatformTeamsClient.getPlatformTeam(teamId);
            team.setId_(teamId);
            PlatformTeamsClient.deletePlatformTeam(team);
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Invalid teamId.");
        }
    }

    private org.xcolab.client.members.pojo.PlatformTeam addNewTeam() {
        return PlatformTeamsClient.createPlatformTeam(NEW_TEAM_NAME);
    }
}
