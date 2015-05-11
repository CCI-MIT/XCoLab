package org.xcolab.portlets.contestmanagement.controller.manager;

import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.ContestScheduleWrapper;
import org.xcolab.portlets.contestmanagement.wrappers.ElementSelectIdWrapper;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class ContestManagerSchedulesTabController extends ContestManagerBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestManagerSchedulesTabController.class);
    static final private TabEnum tab = ContestManagerTabs.SCHEDULES;
    static final private String TAB_VIEW = "manager/schedulesTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @ModelAttribute("contestPhaseTypesSelectionItems")
    public List<LabelValue> populateContestPhaseTypesSelectionItems(){
        return getContestPhaseTypesSelectionItems();
    }

    @RequestMapping(params = "tab=SCHEDULES")
    public String showScheduleTabController(PortletRequest request, PortletResponse response, Model model,
                                         @RequestParam(value = "elementId", required = false) Long elementId)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }

        try{
            Long scheduleId = elementId != null ? elementId : getFirstScheduleId();
            model.addAttribute("contestScheduleWrapper", new ContestScheduleWrapper(scheduleId));
            model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(scheduleId));
        } catch (Exception e){
            _log.warn("Show contest schedule failed with: ", e);
            _log.warn(e);
            return NOT_FOUND_TAB_VIEW;
        }

        setPageAttributes(request, model, tab);
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestSchedule")
    public void updateScheduleTabController(ActionRequest request, Model model,
                                        @ModelAttribute ContestScheduleWrapper updateContestScheduleWrapper,
                                        ActionResponse response) {

        if(!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            updateContestScheduleWrapper.persist();
            SetRenderParameterUtil.addActionSuccessMessageToSession(request);
            setSuccessRenderRedirect(response, tab.getName(), updateContestScheduleWrapper.getScheduleId());
        } catch(Exception e){
            _log.warn("Update contest schedule failed with: ", e);
            _log.warn(e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @RequestMapping(params = {"action=updateContestSchedule", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

    private Long getFirstScheduleId()throws Exception{
        return ContestScheduleLocalServiceUtil.getContestSchedules(0,Integer.MAX_VALUE).get(0).getId();
    }

    private List<LabelValue> getContestPhaseTypesSelectionItems(){
        List<LabelValue> contestPhaseTypesSelectionItems = new ArrayList<>();
        try {

            List<ContestPhaseType> contestPhases = ContestPhaseTypeLocalServiceUtil.getContestPhaseTypes(0, Integer.MAX_VALUE);
            for(ContestPhaseType contestPhaseType : contestPhases){
                contestPhaseTypesSelectionItems.add(new LabelValue(contestPhaseType.getId(), contestPhaseType.getName()));
            }
        } catch (Exception e){
            _log.warn("Could not get contest phase types selection items: " + e);
        }
        return contestPhaseTypesSelectionItems;
    }
}