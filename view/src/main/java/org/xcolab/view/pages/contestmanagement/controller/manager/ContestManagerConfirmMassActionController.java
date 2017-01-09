package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassActions;
import org.xcolab.view.pages.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.view.pages.contestmanagement.wrappers.MassActionConfirmationWrapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("view")
public class ContestManagerConfirmMassActionController {

    private final static Logger _log =
            LoggerFactory.getLogger(ContestManagerConfirmMassActionController.class);
    static final private String CONFIRM_VIEW_PATH = "manager/massActionConfirmation/";

    @RequestMapping(params = {"action=showMassActionConfirmation"})
    public String showConfirmation(HttpServletRequest request, Model model) {
        Integer massActionId = Math.abs(Integer.parseInt(request.getParameter("massActionId")));
        ContestMassActions contestMassAction = ContestMassActions.values()[massActionId];
        String confirmView = contestMassAction.getMethod().getName();
        List<Integer> contestIds = getContestIdsFromParameter(request.getParameter("contestIds"));
        model.addAttribute("massActionConfirmationWrapper",
                new MassActionConfirmationWrapper(contestIds, massActionId));
        model.addAttribute("massActionId", massActionId);
        return CONFIRM_VIEW_PATH + confirmView;
    }

    private static List<Integer> getContestIdsFromParameter(String param) {
        List<Integer> contestIds = new ArrayList<>();
        String cleanedIds = param.replaceAll("\\[", "").replaceAll("\\]", "");
        String[] stringIds = cleanedIds.split(",");
        for (String stringId : stringIds) {
            contestIds.add(Integer.parseInt(stringId.trim()));
        }
        return contestIds;
    }

    @RequestMapping(params = "action=confirmMassActionExecution")
    public void confirmMassActionExecution(HttpServletRequest request, Model model,
            @ModelAttribute MassActionConfirmationWrapper massActionConfirmationWrapper,
            HttpServletResponse response) {
        try {
            massActionConfirmationWrapper.invokeMassActionForSelectedContests();
            SetRenderParameterUtil.addActionSuccessMessageToSession(request,
                    massActionConfirmationWrapper.getSelectedMassActionTitle());
            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response,
                    ContestManagerTabs.OVERVIEW.getName());
        } catch (InvocationTargetException | IllegalAccessException | IOException e) {
            _log.warn("Update contest overview failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
    }

}