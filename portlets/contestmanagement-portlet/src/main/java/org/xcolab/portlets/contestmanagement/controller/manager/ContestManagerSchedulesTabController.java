package org.xcolab.portlets.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.ContestPhaseType;
import org.xcolab.client.contest.pojo.ContestSchedule;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.entities.LabelStringValue;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.utils.schedule.ContestScheduleLifecycleUtil;
import org.xcolab.portlets.contestmanagement.wrappers.ContestScheduleWrapper;
import org.xcolab.portlets.contestmanagement.wrappers.ElementSelectIdWrapper;
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
public class ContestManagerSchedulesTabController extends ContestManagerBaseTabController {

    static final private TabEnum tab = ContestManagerTabs.SCHEDULES;
    static final private String TAB_VIEW = "manager/schedulesTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @ModelAttribute("contestPhaseTypesSelectionItems")
    public List<LabelValue> populateContestPhaseTypesSelectionItems() {
        return getContestPhaseTypesSelectionItems();
    }

    @ModelAttribute("contestPhaseAutopromoteSelectionItems")
    public List<LabelStringValue> populateContestPhaseAutopromoteSelectionItems() {
        return getContestPhaseAutopromoteSelectionItems();
    }

    @RequestMapping(params = "tab=SCHEDULES")
    public String showScheduleTabController(PortletRequest request, PortletResponse response, Model model,
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

    @RequestMapping(params = "action=createContestSchedule")
    public void createNewScheduleTabController(ActionRequest request, Model model, ActionResponse response)
            throws IOException {

        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        ContestSchedule newContestSchedule = ContestCreatorUtil.createNewSchedule();
        SetRenderParameterUtil
                .setSuccessRenderRedirectManagerTab(response, tab.getName(), newContestSchedule.getId_());
    }

    @RequestMapping(params = "action=deleteContestSchedule")
    public void deleteScheduleTabController(ActionRequest request, Model model,
            @RequestParam(value = "scheduleId") Long scheduleId,
            ActionResponse response) throws IOException {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        ContestScheduleLifecycleUtil.deleteContestSchedule(scheduleId);
        SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(), getFirstScheduleId());
    }

    @RequestMapping(params = "action=updateContestSchedule")
    public void updateScheduleTabController(ActionRequest request, Model model,
            @ModelAttribute ContestScheduleWrapper updateContestScheduleWrapper,
            BindingResult result, ActionResponse response) throws IOException {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        if (result.hasErrors()) {
//            SetRenderParameterUtil.setErrorRenderParameter(response, "updateContestSchedule");
            return;
        }

        updateContestScheduleWrapper.persist();
        SetRenderParameterUtil.addActionSuccessMessageToSession(request);
        SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName(),
                updateContestScheduleWrapper.getScheduleId());
    }

    @RequestMapping(params = {"action=updateContestSchedule", "error=true"})
    public String reportError(PortletRequest request, Model model) {
        return TAB_VIEW;
    }

    private Long getFirstScheduleId() {
            final List<ContestSchedule> contestSchedules =
                    ContestClient.getAllContestSchedules();
            if (!contestSchedules.isEmpty()) {
                return contestSchedules.get(0).getId_();
            }
            return -1L;
    }

    private List<LabelValue> getContestPhaseTypesSelectionItems() {
        List<LabelValue> contestPhaseTypesSelectionItems = new ArrayList<>();

        List<ContestPhaseType> contestPhases = ContestClient.getAllContestPhaseTypes();
        for (ContestPhaseType contestPhaseType : contestPhases) {
            contestPhaseTypesSelectionItems
                    .add(new LabelValue(contestPhaseType.getId_(), contestPhaseType.getName()));
        }

        return contestPhaseTypesSelectionItems;
    }

    private List<LabelStringValue> getContestPhaseAutopromoteSelectionItems() {
        List<LabelStringValue> contestPhaseAutopromoteSelectionItems = new ArrayList<>();
        for (ContestPhasePromoteType contestPhasePromoteType : ContestPhasePromoteType.values()) {
            contestPhaseAutopromoteSelectionItems
                    .add(new LabelStringValue(contestPhasePromoteType.getValue(), contestPhasePromoteType.getValue()));
        }
        return contestPhaseAutopromoteSelectionItems;
    }
}