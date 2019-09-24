package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.IPlatformTeamClient;
import org.xcolab.client.user.IUserClient;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.PlatformTeamWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.LabelValue;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.PlatformTeamBean;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

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

    @Autowired
    private IUserClient userClient;

    @Autowired
    private IPlatformTeamClient platformTeamClient;

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    private List<PlatformTeamWrapper> getTeams() {
        return platformTeamClient.listAllPlatformTeams();
    }

    @GetMapping("tab/TEAMS")
    public String showTeamTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member,
            @RequestParam(value = "elementId", required = false) Long elementId)
            throws EntityNotFoundException {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        List<PlatformTeamWrapper> teams = getTeams();

        PlatformTeamWrapper team = null;
        if (elementId != null) {
            team = platformTeamClient.getPlatformTeam(elementId);
        } else if (!teams.isEmpty()) {
            team = teams.get(0);
        }

        Long teamId = -1L;
        if (team != null && !model.containsAttribute(CONTEST_TEAM_BEAN_ATTRIBUTE_KEY)) {
            List<UserWrapper> members = platformTeamClient.listTeamUsers(team.getId());
            model.addAttribute(CONTEST_TEAM_BEAN_ATTRIBUTE_KEY,
                    new PlatformTeamBean(team, members));
            teamId = team.getId();
        }

        model.addAttribute("teamId", teamId);
        model.addAttribute("elementSelectIdWrapper",
                new ElementSelectIdWrapper(teamId, getTeamItems(teams)));
        return TAB_VIEW;
    }

    @PostMapping("tab/TEAMS")
    public String updateTeam(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, @ModelAttribute PlatformTeamBean teamBean)
            throws EntityNotFoundException {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        PlatformTeamWrapper team;
        if (teamBean != null && teamBean.getTeamId() != null) {
            team = updateTeamName(teamBean.getTeamId(), teamBean.getName());
        } else {
            team = addNewTeam();
        }

        return "redirect:" + ContestManagerTabs.TEAMS.getTabUrl(team.getId());
    }

    @PostMapping("tab/TEAMS/{teamId}/delete")
    public String deleteTeam(HttpServletRequest request, HttpServletResponse response,
            UserWrapper member, @PathVariable long teamId) {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        deleteTeam(teamId);

        return "redirect:" + ContestManagerTabs.TEAMS.getTabUrl();
    }

    @PostMapping("tab/TEAMS/{teamId}/removeMember/{userId}")
    public String removeMember(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @PathVariable long teamId, @PathVariable long userId) {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        removeMember(teamId, userId);

        return "redirect:" + ContestManagerTabs.TEAMS.getTabUrl(teamId);
    }

    @PostMapping("tab/TEAMS/{teamId}/addMember")
    public void addMember(HttpServletRequest request, HttpServletResponse response, Model model,
            UserWrapper member, @PathVariable long teamId, @RequestParam long userId,
            @RequestParam(required = false) String teamName) throws EntityNotFoundException {

        if (!tabWrapper.getCanEdit()) {
            return;
        }

        addMember(teamId, userId);

        if (teamName != null) {
            updateTeamName(teamId, teamName);
        }
    }

    private PlatformTeamWrapper updateTeamName(Long teamId, String teamName)
            throws EntityNotFoundException {
        PlatformTeamWrapper team = platformTeamClient.getPlatformTeam(teamId);
        team.setName(teamName);
        platformTeamClient.updatePlatformTeam(team,team.getId());
        return team;

    }

    private List<LabelValue> getTeamItems(List<PlatformTeamWrapper> teams) {
        List<LabelValue> teamItems = new ArrayList<>();
        for (PlatformTeamWrapper team : teams) {
            teamItems.add(new LabelValue(team.getId(), team.getName()));
        }
        return teamItems;
    }

    private void addMember(Long teamId, Long userId) {
        try {
            PlatformTeamWrapper team = platformTeamClient.getPlatformTeam(teamId);
            UserWrapper member = StaticUserContext.getUserClient().getMember(userId);
            platformTeamClient.addUser(team.getId(), member.getId());
        } catch (EntityNotFoundException  e) {
            throw new IllegalArgumentException("Invalid teamId or userId.");
        }
    }

    private void removeMember(Long teamId, Long userId) {
        try {
            PlatformTeamWrapper team = platformTeamClient.getPlatformTeam(teamId);
            UserWrapper member = StaticUserContext.getUserClient().getMember(userId);
            platformTeamClient.removeUser(team.getId(), member.getId());
        } catch (EntityNotFoundException  e) {
            throw new IllegalArgumentException("Invalid teamId or userId.");
        }
    }

    private void deleteTeam(Long teamId) {
        try {
            PlatformTeamWrapper team = platformTeamClient.getPlatformTeam(teamId);
            team.setId(teamId);
            platformTeamClient.deletePlatformTeam(team.getId());
        } catch (EntityNotFoundException e) {
            throw new IllegalArgumentException("Invalid teamId.");
        }
    }

    private PlatformTeamWrapper addNewTeam() {
        return platformTeamClient.createPlatformTeam(NEW_TEAM_NAME);
    }
}
