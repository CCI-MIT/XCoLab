package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.entities.LabelStringValue;
import org.xcolab.view.pages.contestmanagement.entities.LabelValue;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleChangeHelper.IllegalScheduleChangeException;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestScheduleBean;
import org.xcolab.view.pages.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class SchedulesTabController extends AbstractTabController {

    private static final ContestManagerTabs tab = ContestManagerTabs.SCHEDULES;
    private static final String TAB_VIEW = "contestmanagement/manager/schedulesTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @ModelAttribute("contestPhaseTypesSelectionItems")
    public List<LabelValue> populateContestPhaseTypesSelectionItems() {
        return getContestPhaseTypesSelectionItems();
    }

    private List<LabelValue> getContestPhaseTypesSelectionItems() {
        List<LabelValue> contestPhaseTypesSelectionItems = new ArrayList<>();

        List<ContestPhaseType> contestPhases = ContestClientUtil.getAllContestPhaseTypes();
        for (ContestPhaseType contestPhaseType : contestPhases) {
            contestPhaseTypesSelectionItems
                    .add(new LabelValue(contestPhaseType.getId_(), contestPhaseType.getName()));
        }

        return contestPhaseTypesSelectionItems;
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
            HttpServletResponse response, Model model,
            @RequestParam(value = "elementId", required = false) Long elementId) {
        if (!tabWrapper.getCanView()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        Long scheduleId = elementId != null ? elementId : getFirstScheduleId();
        model.addAttribute("scheduleId", scheduleId);
        if (scheduleId >= 0 && !model.containsAttribute("contestScheduleWrapper")) {
            model.addAttribute("contestScheduleWrapper", new ContestScheduleBean(scheduleId));
        }
        model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(scheduleId,
                ContestScheduleLifecycleUtil.getAllScheduleTemplateSelectionItems()));
        setPageAttributes(request, model, tab);
        return TAB_VIEW;
    }

    private Long getFirstScheduleId() {
        final List<ContestSchedule> contestSchedules =
                ContestClientUtil.getAllContestSchedules();
        if (!contestSchedules.isEmpty()) {
            return contestSchedules.get(0).getId_();
        }
        return -1L;
    }

    @PostMapping("tab/SCHEDULES")
    public String performAction(HttpServletRequest request, HttpServletResponse response,
            Model model, Action action, @RequestParam(required = false) Long elementId,
            @ModelAttribute ContestScheduleBean contestScheduleBean, BindingResult result) {

        if (!tabWrapper.getCanEdit()) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }

        switch (action) {
            case CREATE:
                return createSchedule(request, response, model);
            case UPDATE:
                return updateSchedule(request, response, model, contestScheduleBean, result);
            case DELETE:
                if (elementId == null) {
                    throw new IllegalArgumentException("Schedule id missing");
                }
                return deleteSchedule(request, response, model, elementId);
            default:
                throw new IllegalArgumentException("Known action");
        }
    }

    private String createSchedule(HttpServletRequest request, HttpServletResponse response,
            Model model) {
        ContestSchedule newContestSchedule = ContestScheduleLifecycleUtil.createNewSchedule();
        AlertMessage.CREATED.flash(request);
        return showScheduleTabController(request, response, model, newContestSchedule.getId_());
    }

    private String updateSchedule(HttpServletRequest request, HttpServletResponse response,
            Model model, @ModelAttribute ContestScheduleBean contestScheduleBean,
            BindingResult result) {
        if (result.hasErrors()) {
            AlertMessage.danger("Error saving your changes!").flash(request);
            return showScheduleTabController(request, response, model,
                    contestScheduleBean.getScheduleId());
        }
        try {
            contestScheduleBean.persist();
            AlertMessage.CHANGES_SAVED.flash(request);
            return showScheduleTabController(request, response, model,
                    contestScheduleBean.getScheduleId());
        } catch (IllegalScheduleChangeException e) {
            return ErrorText.ILLEGAL_SCHEDULE_CHANGE.flashAndReturnView(request);
        }
    }

    private String deleteSchedule(HttpServletRequest request, HttpServletResponse response,
            Model model, Long scheduleId) {
        ContestScheduleLifecycleUtil.deleteContestSchedule(scheduleId);
        AlertMessage.DELETED.flash(request);
        return showScheduleTabController(request, response, model, null);
    }

    enum Action {
        CREATE, UPDATE, DELETE
    }
}