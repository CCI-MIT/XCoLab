package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassAction;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassActions;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.entities.massactions.OrderMassAction;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;
import org.xcolab.view.pages.contestmanagement.wrappers.MassActionConfirmationWrapper;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.view.util.entity.EntityIdListUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest")
public class OverviewTabController extends AbstractTabController {

    static final private ContestManagerTabs tab = ContestManagerTabs.OVERVIEW;
    static final private String TAB_VIEW = "contestmanagement/manager/overviewTab";

    static final private String CONFIRM_VIEW_PATH =
            "contestmanagement/manager/massActionConfirmation/confirmMassAction";

    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }


    @ModelAttribute("massActionsItems")
    public Map<String, String> populateMassActionsItems(HttpServletRequest request) {
        Map<String, String> contestMassActionItems = new LinkedHashMap<>();

        for (ContestMassActions contestMassAction : ContestMassActions.values()) {
            contestMassActionItems
                    .put(contestMassAction.name(), contestMassAction.getAction().getDisplayName());
        }

        return contestMassActionItems;
    }

    @GetMapping({"", "manager"})
    public String showAdminTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member) {
        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }
        model.addAttribute("contestOverviewWrapper", new ContestOverviewWrapper(member));
        return TAB_VIEW;
    }

    @PostMapping("manager/update")
    public String updateContestOverviewTabController(HttpServletRequest request,
            HttpServletResponse response, Model model, UserWrapper member,
            @ModelAttribute ContestOverviewWrapper updateContestOverviewWrapper)
            throws IOException, InvocationTargetException, IllegalAccessException {
        if (!tabWrapper.getCanEdit()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        try {
            executeMassAction(response, updateContestOverviewWrapper);
            AlertMessage.CHANGES_SAVED.flash(request);
            return "redirect:/admin/contest/manager";
        } catch (MassActionRequiresConfirmationException e) {
            return showConfirmationView(model, updateContestOverviewWrapper);
        }
    }

    @PostMapping("manager/updateOrder")
    public void updateContestOrder(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member,
            @ModelAttribute ContestOverviewWrapper updateContestOverviewWrapper)
            throws IOException, InvocationTargetException, IllegalAccessException {
        if (!tabWrapper.getCanEdit()) {
            response.sendError(403);
        }
        List<ContestWrapper> contests =
                new ArrayList<>(updateContestOverviewWrapper.getContests().values());
        OrderMassAction orderMassAction = (OrderMassAction) ContestMassActions.ORDER.getAction();
        orderMassAction.execute(contests);
    }

    @PostMapping("api/massAction")
    public void getExportController(HttpServletRequest request, Model model,
            @ModelAttribute ContestOverviewWrapper updateContestOverviewWrapper,
            HttpServletResponse response) throws InvocationTargetException, IllegalAccessException {
        if (!tabWrapper.getCanEdit()) {
            return;
        }
        try {
            executeMassAction(response, updateContestOverviewWrapper);
        } catch (MassActionRequiresConfirmationException | IOException ignored) {
        }
    }

    private String showConfirmationView(Model model,
            ContestOverviewWrapper contestOverviewWrapper) {
        List<Long> contestIds = contestOverviewWrapper.getSelectedContestIds();
        ContestMassActions selectedMassActionWrapper = contestOverviewWrapper.getSelectedMassAction();
        model.addAttribute("massActionConfirmationWrapper",
                new MassActionConfirmationWrapper(contestIds, selectedMassActionWrapper));

        return CONFIRM_VIEW_PATH;
    }

    private void executeMassAction(HttpServletResponse response,
            ContestOverviewWrapper contestOverviewWrapper)
            throws MassActionRequiresConfirmationException, IOException {
        contestOverviewWrapper.setuserId(MemberAuthUtil.getUserId());

        ContestMassAction action = contestOverviewWrapper.getSelectedMassAction().getAction();
        List<Long> contestIds = contestOverviewWrapper.getSelectedContestIds();
        List<ContestWrapper> contests = EntityIdListUtil.CONTESTS.fromIdList(contestIds);

        action.execute(contests, false, contestOverviewWrapper, response);
    }

}
