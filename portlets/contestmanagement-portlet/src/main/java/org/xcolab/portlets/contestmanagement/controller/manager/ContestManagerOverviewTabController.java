package org.xcolab.portlets.contestmanagement.controller.manager;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagementTabs;
import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.wrappers.ContestOverviewWrapper;
import org.xcolab.wrapper.ContestWrapper;
import org.xcolab.wrapper.TabWrapper;

import javax.portlet.*;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class ContestManagerOverviewTabController extends ContestManagerBaseTabController {

    static final private TabEnum tab = ContestManagementTabs.OVERVIEW;
    static final private String TAB_VIEW = "manager/overviewTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(PortletRequest request) throws PortalException, SystemException {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getPortletSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @ModelAttribute("massActionsItems")
    public List<LabelValue> populateMassActionsItems(PortletRequest request) throws PortalException, SystemException {
        List<LabelValue> contestMassActionItems= new ArrayList<>();

        for(ContestMassActions contestMassAction : ContestMassActions.values()){
            contestMassActionItems.add(new LabelValue(new Long(contestMassAction.ordinal()), contestMassAction.getActionDisplayName()));
            if(contestMassAction.getHasReverseAction()) {
                contestMassActionItems.add(new LabelValue(new Long(-contestMassAction.ordinal()), contestMassAction.getReverseActionDisplayName()));
            }
        }

        return contestMassActionItems;
    }

    @RequestMapping(params = "tab=OVERVIEW")
    public String showAdminTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {

        if(!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }
        setPageAttributes(request, model, tab);
        model.addAttribute("contestOverviewWrapper", new ContestOverviewWrapper(request));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestOverview")
    public void updateTeamTabController(ActionRequest request, Model model,
                                        @ModelAttribute ContestOverviewWrapper updateContestOverviewWrapper,
                                        ActionResponse response) {

        if(!tabWrapper.getCanEdit()) {
            setNoPermissionErrorRenderParameter(response);
            return;
        }

        try {
            updateContestOverviewWrapper.persistOrder();
            updateContestOverviewWrapper.executeMassActionIfSelected(request);
            PortletSession session = request.getPortletSession();
            String massActionTitle = updateContestOverviewWrapper.getSelectedMassActionTitle();
            session.setAttribute("massActionSuccess" , massActionTitle, PortletSession.APPLICATION_SCOPE);
            setSuccessRenderRedirect(response, tab.getName());
        } catch(Exception e){
            setNotFoundErrorRenderParameter(response);
        }
    }

    @RequestMapping(params = {"action=updateContestOverview", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

}