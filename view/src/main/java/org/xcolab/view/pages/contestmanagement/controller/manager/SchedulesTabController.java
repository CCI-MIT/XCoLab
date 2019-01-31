package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.pojo.IContestSchedule;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.html.LabelStringValue;
import org.xcolab.commons.html.LabelValue;
import org.xcolab.commons.servlet.flash.AlertMessage;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestScheduleBean;
import org.xcolab.view.pages.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class SchedulesTabController extends AbstractTabController {

    private static final ContestManagerTabs tab = ContestManagerTabs.SCHEDULES;
    private static final String TAB_VIEW = "contestmanagement/manager/schedulesTab";

    private static final String CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY = "contestScheduleBean";
    private static final String SCHEDULE_CHANGE_ERROR_MESSAGE =
            "This schedule is used in at least one contest that has already started. "
                    + "Please make sure you only change future phases.";
    private static final String SCHEDULE_CHANGE_INVALID_MESSAGE =
            "This schedule is invalid.";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @ModelAttribute("contestPhaseTypesSelectionItems")
    public List<LabelValue> populateContestPhaseTypesSelectionItems() {
        return contestClient.getAllContestPhaseTypes().stream()
                .filter(phaseType -> !phaseType.isIsDeprecated())
                .map(phaseType -> new LabelValue(phaseType.getId(), phaseType.getName()))
                .collect(Collectors.toList());
    }

    @ModelAttribute("contestPhaseTypesSelectionItemsDeprecated")
    public List<LabelValue> populateContestPhaseTypesSelectionItemsDeprecated() {
        return contestClient.getAllContestPhaseTypes().stream()
                .filter(phaseType -> phaseType.isIsDeprecated())
                .map(phaseType -> new LabelValue(phaseType.getId(), phaseType.getName() + " (Deprecated)"))
                .collect(Collectors.toList());
    }

    @ModelAttribute("contestPhaseAutopromoteSelectionItems")
    public List<LabelStringValue> populateContestPhaseAutopromoteSelectionItems() {
        return getContestPhaseAutopromoteSelectionItems();
    }

    private List<LabelStringValue> getContestPhaseAutopromoteSelectionItems() {
        List<LabelStringValue> contestPhaseAutopromoteSelectionItems = new ArrayList<>();
        for (ContestPhasePromoteType contestPhasePromoteType : ContestPhasePromoteType.values()) {
            contestPhaseAutopromoteSelectionItems
                    .add(new LabelStringValue(contestPhasePromoteType.getValue(),
                            contestPhasePromoteType.getValue()));
        }
        return contestPhaseAutopromoteSelectionItems;
    }

    @GetMapping("tab/SCHEDULES")
    public String showScheduleTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @RequestParam(value = "elementId", required = false) Long elementId) {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        Long scheduleId = elementId != null ? elementId : getFirstScheduleId();
        if (scheduleId >= 0 && !model.containsAttribute(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY)) {
            model.addAttribute(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY, new ContestScheduleBean(scheduleId));
        }
        model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(scheduleId,
                ContestScheduleLifecycleUtil.getAllScheduleTemplateSelectionItems()));
        return TAB_VIEW;
    }

    private Long getFirstScheduleId() {
        final List<IContestSchedule> contestSchedules =
                contestClient.getAllContestSchedules();
        if (!contestSchedules.isEmpty()) {
            return contestSchedules.get(0).getId();
        }
        return -1L;
    }

    @PostMapping("tab/SCHEDULES")
    public String performAction(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, Action action,
            @RequestParam(required = false) Long elementId,
            @ModelAttribute ContestScheduleBean contestScheduleBean, BindingResult result) {

        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        switch (action) {
            case CREATE:
                return createSchedule(request, response, model, member);
            case UPDATE:
                return updateSchedule(request, response, model, member,
                        contestScheduleBean, result);
            case DELETE:
                if (elementId == null) {
                    throw new IllegalArgumentException("Schedule id missing");
                }
                return deleteSchedule(request, response, model, member, elementId);
            default:
                throw new IllegalArgumentException("unknown action");
        }
    }

    private String createSchedule(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member) {
        IContestSchedule newContestSchedule = ContestScheduleLifecycleUtil.createNewSchedule();

        AlertMessage.CREATED.flash(request);
        model.asMap().remove(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY);
        return showScheduleTabController(request, response, model, member, newContestSchedule.getId());
    }

    private String updateSchedule(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, ContestScheduleBean contestScheduleBean, BindingResult result) {

        contestScheduleBean.setPhaseEndDates();

        if (!contestScheduleBean.isValidSchedule()) {
            result.reject(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY, SCHEDULE_CHANGE_INVALID_MESSAGE);
        }

        if (!contestScheduleBean.areContestsCompatibleWithSchedule()) {
            result.reject(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY, SCHEDULE_CHANGE_ERROR_MESSAGE);
        }

        if (result.hasErrors()) {
            AlertMessage.NOT_SAVED.flash(request);
            return showScheduleTabController(request, response, model, member,
                    contestScheduleBean.getScheduleId());
        }

        contestScheduleBean.persist();

        AlertMessage.CHANGES_SAVED.flash(request);
        model.asMap().remove(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY);
        return showScheduleTabController(request, response, model, member,
                contestScheduleBean.getScheduleId());
    }

    private String deleteSchedule(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, Long scheduleId) {

        ContestScheduleLifecycleUtil.deleteContestSchedule(scheduleId);

        AlertMessage.DELETED.flash(request);
        model.asMap().remove(CONTEST_SCHEDULE_BEAN_ATTRIBUTE_KEY);
        return showScheduleTabController(request, response, model, member, null);
    }

    private enum Action {
        CREATE, UPDATE, DELETE
    }
}
