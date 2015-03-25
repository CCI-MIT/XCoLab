package org.xcolab.portlets.contestmanagement.controller.manager;

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
import org.xcolab.portlets.contestmanagement.entities.ContestManagementTabs;
import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.wrappers.ContestOverviewWrapper;
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
    static final private TabEnum tab = ContestManagementTabs.SCHEDULES;
    static final private String TAB_VIEW = "manager/schedulesTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @ModelAttribute("scheduleTemplateSelectionItems")
    public List<LabelValue> populateScheduleTemplateSelectionItems(){
        return ContestScheduleWrapper.getScheduleTemplateSelectionItems();
    }

    @RequestMapping(params = "tab=SCHEDULES")
    public String showAdminTabController(PortletRequest request, PortletResponse response, Model model,
                                         @RequestParam(value = "elementId", required = false) Long elementId)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }
        setPageAttributes(request, model, tab);
        model.addAttribute("contestScheduleWrapper", new ContestScheduleWrapper(elementId));
        model.addAttribute("elementSelectIdWrapper", new ElementSelectIdWrapper(elementId));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestSchedule")
    public void updateTeamTabController(ActionRequest request, Model model,
                                        @ModelAttribute ContestScheduleWrapper updateContestScheduleWrapper,
                                        ActionResponse response) {

        if(!tabWrapper.getCanEdit()) {
            setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            updateContestScheduleWrapper.persist();
            addActionSuccessMessageToSession(request);
            setSuccessRenderRedirect(response, tab.getName());
        } catch(Exception e){
            setNotFoundErrorRenderParameter(response);
        }
    }

    @RequestMapping(params = {"action=updateContestSchedule", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

}