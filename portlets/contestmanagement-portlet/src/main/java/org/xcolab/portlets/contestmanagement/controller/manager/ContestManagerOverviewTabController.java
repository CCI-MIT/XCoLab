package org.xcolab.portlets.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import org.xcolab.interfaces.TabEnum;
import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;
import org.xcolab.portlets.contestmanagement.entities.LabelValue;
import org.xcolab.portlets.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.ContestOverviewWrapper;
import org.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

@Controller
@RequestMapping("view")
public class ContestManagerOverviewTabController extends ContestManagerBaseTabController {

    private final static Log _log = LogFactoryUtil.getLog(ContestManagerOverviewTabController.class);
    static final private TabEnum tab = ContestManagerTabs.OVERVIEW;
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
        List<LabelValue> contestMassActionItems = new ArrayList<>();

        for (ContestMassActions contestMassAction : ContestMassActions.values()) {
            contestMassActionItems
                    .add(new LabelValue((long) contestMassAction.ordinal(), contestMassAction.getActionDisplayName()));
            if (contestMassAction.getHasReverseAction()) {
                contestMassActionItems.add(new LabelValue((long) -contestMassAction.ordinal(),
                        contestMassAction.getReverseActionDisplayName()));
            }
        }

        return contestMassActionItems;
    }

    @RequestMapping(params = "tab=OVERVIEW")
    public String showAdminTabController(PortletRequest request, PortletResponse response, Model model)
            throws PortalException, SystemException {
        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }
        setPageAttributes(request, model, tab);
        model.addAttribute("contestOverviewWrapper", new ContestOverviewWrapper(request));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestOverview")
    public void updateContestOverviewTabController(ActionRequest request, Model model,
            @ModelAttribute ContestOverviewWrapper updateContestOverviewWrapper,
            ActionResponse response) throws IOException {
        if (!tabWrapper.getCanEdit()) {
            SetRenderParameterUtil.setNoPermissionErrorRenderParameter(response);
            return;
        }
        try {
            try {
                updateContestOverviewWrapper.executeMassAction(request, response);
                String massActionTitle = updateContestOverviewWrapper.getSelectedMassActionTitle();
                SetRenderParameterUtil.addActionSuccessMessageToSession(request, massActionTitle);
                SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, tab.getName());
            } catch (InvocationTargetException e) {
                Boolean massActionRequiresConfirmation =
                        e.getTargetException() instanceof MassActionRequiresConfirmationException;
                if (massActionRequiresConfirmation) {
                    SetRenderParameterUtil.setConfirmMassActionRenderRedirect(response, updateContestOverviewWrapper);
                } else {
                    throw e;
                }
            }
        } catch (InvocationTargetException | IllegalAccessException | IOException e) {
            _log.warn("Update contest overview failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

    @ResourceMapping("getExport")
    public void getExportController(ResourceRequest request, Model model,
            @ModelAttribute ContestOverviewWrapper updateContestOverviewWrapper,
            ResourceResponse response) {
        if (!tabWrapper.getCanEdit()) {
            return;
        }
        try {
            updateContestOverviewWrapper.executeMassAction(request, response);
        } catch (InvocationTargetException | IllegalAccessException e) {
            _log.warn("Export failed with: ", e);
            SetRenderParameterUtil.addActionExceptionMessageToSession(request, e);
        }
    }

    @RequestMapping(params = {"action=updateContestOverview", "error=true"})
    public String reportError(PortletRequest request, Model model) throws PortalException, SystemException {
        return TAB_VIEW;
    }

}