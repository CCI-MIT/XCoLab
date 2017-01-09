package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.util.enums.promotion.ContestPhasePromoteType;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.entities.LabelStringValue;
import org.xcolab.view.pages.contestmanagement.entities.LabelValue;
import org.xcolab.view.pages.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleChangeHelper
        .IllegalScheduleChangeException;
import org.xcolab.view.pages.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestScheduleWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class ContestManagerSchedulesTabController extends ContestManagerBaseTabController {

    static final private TabEnum tab = ContestManagerTabs.SCHEDULES;
    static final private String TAB_VIEW = "manager/schedulesTab";

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

    @RequestMapping(params = "tab=SCHEDULES")
    public String showScheduleTabController(HttpServletRequest request,
            HttpServletResponse response, Model model,
            @RequestParam(value = "elementId", required = false) Long elementId) {
        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        Long scheduleId = elementId != null ? elementId : getFirstScheduleId();
        model.addAttribute("scheduleId", scheduleId);
        if (scheduleId >= 0) {
            model.addAttribute("contestScheduleWrapper", new ContestScheduleWrapper(scheduleId));
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

    @RequestMapping(params = "action=createContestSchedule")
    public void createNewScheduleTabController(HttpServletRequest request, Model model,
            HttpServletResponse response)
            throws IOException {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        ContestSchedule newContestSchedule = ContestScheduleLifecycleUtil.createNewSchedule();
        SetRenderParameterUtil
                .setSuccessRenderRedirectManagerTab(response, tab.getName(),
                        newContestSchedule.getId_());
    }

    @RequestMapping(params = "action=deleteContestSchedule")
    public void deleteScheduleTabController(HttpServletRequest request, Model model,
            @RequestParam(value = "scheduleId") Long scheduleId,
            HttpServletResponse response) throws IOException {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        ContestScheduleLifecycleUtil.deleteContestSchedule(scheduleId);
        SetRenderParameterUtil
                .setSuccessRenderRedirectManagerTab(response, tab.getName(), getFirstScheduleId());
    }

    @RequestMapping(params = "action=updateContestSchedule")
    public void updateScheduleTabController(HttpServletRequest request, Model model,
            @ModelAttribute ContestScheduleWrapper updateContestScheduleWrapper,
            BindingResult result, HttpServletResponse response) throws IOException {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
            //            SetRenderParameterUtil.setErrorRenderParameter(response,
            // "updateContestSchedule");
            return;
        }
        try {
            updateContestScheduleWrapper.persist();
            SetRenderParameterUtil.addActionSuccessMessageToSession(request);
            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(),
                    updateContestScheduleWrapper.getScheduleId());
        } catch (IllegalScheduleChangeException e) {
            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestSchedule");
        }
    }

    @RequestMapping(params = {"action=updateContestSchedule", "error=true"})
    public String reportError(HttpServletRequest request, Model model) {
        return "common/notSupported";
    }
}