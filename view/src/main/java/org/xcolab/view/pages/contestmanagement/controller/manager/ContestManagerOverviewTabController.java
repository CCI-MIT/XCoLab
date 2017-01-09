package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassActions;
import org.xcolab.view.pages.contestmanagement.entities.LabelValue;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;
import org.xcolab.view.taglibs.xcolab.interfaces.TabEnum;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class ContestManagerOverviewTabController extends ContestManagerBaseTabController {

    private final static Logger _log =
            LoggerFactory.getLogger(ContestManagerOverviewTabController.class);
    static final private TabEnum tab = ContestManagerTabs.OVERVIEW;
    static final private String TAB_VIEW = "manager/overviewTab";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        request.getSession().setAttribute("tabWrapper", tabWrapper);
        return tabWrapper;
    }

    @ModelAttribute("massActionsItems")
    public List<LabelValue> populateMassActionsItems(HttpServletRequest request) {
        List<LabelValue> contestMassActionItems = new ArrayList<>();

        for (ContestMassActions contestMassAction : ContestMassActions.values()) {
            contestMassActionItems
                    .add(new LabelValue((long) contestMassAction.ordinal(),
                            contestMassAction.getActionDisplayName()));
            if (contestMassAction.getHasReverseAction()) {
                contestMassActionItems.add(new LabelValue((long) -contestMassAction.ordinal(),
                        contestMassAction.getReverseActionDisplayName()));
            }
        }

        return contestMassActionItems;
    }

    @RequestMapping(params = "tab=OVERVIEW")
    public String showAdminTabController(HttpServletRequest request, HttpServletResponse response,
            Model model) {
        if (!tabWrapper.getCanView()) {
            return NO_PERMISSION_TAB_VIEW;
        }
        setPageAttributes(request, model, tab);
        model.addAttribute("contestOverviewWrapper", new ContestOverviewWrapper(request));
        return TAB_VIEW;
    }

    @RequestMapping(params = "action=updateContestOverview")
    public void updateContestOverviewTabController(HttpServletRequest request, Model model,
            @ModelAttribute ContestOverviewWrapper updateContestOverviewWrapper,
            HttpServletResponse response) throws IOException {
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
                    SetRenderParameterUtil.setConfirmMassActionRenderRedirect(response,
                            updateContestOverviewWrapper);
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
    public void getExportController(HttpServletRequest request, Model model,
            @ModelAttribute ContestOverviewWrapper updateContestOverviewWrapper,
            HttpServletResponse response) {
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
    public String reportError(HttpServletRequest request, Model model) {
        return TAB_VIEW;
    }

}