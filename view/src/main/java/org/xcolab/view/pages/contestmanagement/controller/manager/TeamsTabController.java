package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.util.html.LabelStringValue;
import org.xcolab.util.html.LabelValue;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestScheduleBean;
import org.xcolab.view.pages.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.PlatformTeamBean;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.view.util.entity.flash.AlertMessage;

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

    private List<PlatformTeam> teams;

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

//    @ModelAttribute("contestPhaseTypesSelectionItems")
//    public List<LabelValue> populateContestPhaseTypesSelectionItems() {
//        return getContestPhaseTypesSelectionItems();
//    }
//
//    private List<LabelValue> getContestPhaseTypesSelectionItems() {
//        List<LabelValue> contestPhaseTypesSelectionItems = new ArrayList<>();
//
//        List<ContestPhaseType> contestPhases = ContestClientUtil.getAllContestPhaseTypes();
//        for (ContestPhaseType contestPhaseType : contestPhases) {
//            contestPhaseTypesSelectionItems
//                    .add(new LabelValue(contestPhaseType.getId_(), contestPhaseType.getName()));
//        }
//
//        return contestPhaseTypesSelectionItems;
//    }
//
//    @ModelAttribute("contestPhaseAutopromoteSelectionItems")
//    public List<LabelStringValue> populateContestPhaseAutopromoteSelectionItems() {
//        return getContestPhaseAutopromoteSelectionItems();
//    }
//
//    private List<LabelStringValue> getContestPhaseAutopromoteSelectionItems() {
//        List<LabelStringValue> contestPhaseAutopromoteSelectionItems = new ArrayList<>();
//        for (ContestPhasePromoteType contestPhasePromoteType : ContestPhasePromoteType.values()) {
//            contestPhaseAutopromoteSelectionItems
//                    .add(new LabelStringValue(contestPhasePromoteType.getValue(),
//                            contestPhasePromoteType.getValue()));
//        }
//        return contestPhaseAutopromoteSelectionItems;
//    }

    @GetMapping("tab/TEAMS")
    public String showTeamTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, Member member,
            @RequestParam(value = "elementId", required = false) Long elementId) {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        if (this.teams == null) {
            this.teams = getMockTeamList();
        }

        Long teamId = -1L;
        PlatformTeam team = null;
        if (elementId == null) {
            team = this.teams.get(0);
        } else {
            team = getTeamWithId(elementId);
        }
        if (!this.teams.isEmpty() && !model.containsAttribute(CONTEST_TEAM_BEAN_ATTRIBUTE_KEY)) {
            model.addAttribute(CONTEST_TEAM_BEAN_ATTRIBUTE_KEY, new PlatformTeamBean(team));
            teamId = team.getId_();
        }
        model.addAttribute("teamId", teamId);
        model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(teamId,
                getAllTeamItems()));
        return TAB_VIEW;
    }

    @PostMapping("tab/TEAMS/{teamId}/removeMember/{memberId}")
    public void removeMember(HttpServletRequest request,
            HttpServletResponse response, Model model, Member member,
            @PathVariable long teamId, @PathVariable long memberId) throws IOException {
        if (!tabWrapper.getCanView()) {
            response.sendRedirect(ContestManagerTabs.TEAMS.getTabUrl(teamId));
            return;
        }

        PlatformTeam team = getTeamWithId(teamId);
        try {
            Member teamMember = MembersClient.getMember(memberId);
            team.getMembers().remove(teamMember);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
        }

        response.sendRedirect(ContestManagerTabs.TEAMS.getTabUrl(teamId));

    }

    @PostMapping("tab/TEAMS/{teamId}/addMember")
    public void addMember(HttpServletRequest request,
            HttpServletResponse response, Model model, Member member,
            @PathVariable long teamId, @RequestParam long userId) throws IOException {
        if (!tabWrapper.getCanView()) {
            return;
        }

        PlatformTeam team = getTeamWithId(teamId);
        try {
            Member teamMember = MembersClient.getMember(userId);
            team.getMembers().add(teamMember);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
        }

    }

    private Long getFirstTeamId() {
        final List<PlatformTeam> teams = this.teams;
//                ContestClientUtil.getAllContestSchedules();
        if (!teams.isEmpty()) {
            return teams.get(0).getId_();
        }
        return -1L;
    }

    private PlatformTeam getTeamWithId(long teamId) {
        PlatformTeam team = null;
        for (PlatformTeam curTeam : teams) {
            if (curTeam.getId_().equals(teamId)) {
                team = curTeam;
                break;
            }
        }
        return team;
    }

    private List<LabelValue> getAllTeamItems() {
        List<LabelValue> teamItems = new ArrayList<>();
        for (PlatformTeam team : teams) {
            teamItems.add(new LabelValue(team.getId_(), team.getName()));
        }
        return teamItems;
    }

    private List<PlatformTeam> getMockTeamList() {
        List<PlatformTeam> teams = new ArrayList<>();
        PlatformTeam team1 = new PlatformTeam();
        PlatformTeam team2 = new PlatformTeam();
        PlatformTeam team3 = new PlatformTeam();
        team1.setName("Team Uno");
        team2.setName("Team Dos");
        team3.setName("Team Tres");
        team1.setMembers(new ArrayList<>());
        team2.setMembers(new ArrayList<>());
        team3.setMembers(new ArrayList<>());
        try {
            Member aleks = MembersClient.getMember(2666734);
            Member schwanzo = MembersClient.getMember(2666735);
            Member schmibo = MembersClient.getMember(2666736);
            team1.getMembers().add(aleks);
            team1.getMembers().add(schwanzo);
            team1.getMembers().add(schmibo);
        } catch (MemberNotFoundException e) {
            e.printStackTrace();
        }
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        return teams;
    }

//    @PostMapping("tab/TEAMS")
//    public String performAction(HttpServletRequest request, HttpServletResponse response,
//            Model model, Member member, Action action,
//            @RequestParam(required = false) Long elementId,
//            @ModelAttribute ContestScheduleBean contestScheduleBean, BindingResult result) {
//
//        if (!tabWrapper.getCanEdit()) {
//            return new AccessDeniedPage(member).toViewName(response);
//        }
//
//        switch (action) {
//            case CREATE:
//                return createSchedule(request, response, model, member);
//            case UPDATE:
//                return updateSchedule(request, response, model, member,
//                        contestScheduleBean, result);
//            case DELETE:
//                if (elementId == null) {
//                    throw new IllegalArgumentException("Schedule id missing");
//                }
//                return deleteSchedule(request, response, model, member, elementId);
//            default:
//                throw new IllegalArgumentException("unknown action");
//        }
//    }

//    private String createSchedule(HttpServletRequest request, HttpServletResponse response,
//            Model model, Member member) {
//        ContestSchedule newContestSchedule = ContestScheduleLifecycleUtil.createNewSchedule();
//
//        AlertMessage.CREATED.flash(request);
//        model.asMap().remove(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY);
//        return showTeamTabController(request, response, model, member, newContestSchedule.getId_());
//    }
//
//    private String updateSchedule(HttpServletRequest request, HttpServletResponse response,
//            Model model, Member member, ContestScheduleBean contestScheduleBean, BindingResult result) {
//
//        if (!contestScheduleBean.areContestsCompatibleWithSchedule()) {
//            result.reject(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY, SCHEDULE_CHANGE_ERROR_MESSAGE);
//        }
//
//        if (!contestScheduleBean.isValidSchedule()) {
//            result.reject(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY, SCHEDULE_CHANGE_INVALID_MESSAGE);
//        }
//
//        if (result.hasErrors()) {
//            AlertMessage.NOT_SAVED.flash(request);
//            return showTeamTabController(request, response, model, member,
//                    contestScheduleBean.getScheduleId());
//        }
//
//        contestScheduleBean.persist();
//
//        AlertMessage.CHANGES_SAVED.flash(request);
//        model.asMap().remove(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY);
//        return showTeamTabController(request, response, model, member,
//                contestScheduleBean.getScheduleId());
//    }
//
//    private String deleteSchedule(HttpServletRequest request, HttpServletResponse response,
//            Model model, Member member, Long scheduleId) {
//
//        ContestScheduleLifecycleUtil.deleteContestSchedule(scheduleId);
//
//        AlertMessage.DELETED.flash(request);
//        model.asMap().remove(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY);
//        return showTeamTabController(request, response, model, member, null);
//    }
//
//    private enum Action {
//        CREATE, UPDATE, DELETE
//    }
}
