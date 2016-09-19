package org.xcolab.portlets.contestmanagement.controller.manager;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.portlets.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.portlets.contestmanagement.entities.ContestMassActions;
import org.xcolab.portlets.contestmanagement.utils.SetRenderParameterUtil;
import org.xcolab.portlets.contestmanagement.wrappers.MassActionConfirmationWrapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ContestManagerConfirmMassActionController {
    private final static Log _log = LogFactoryUtil.getLog(ContestManagerConfirmMassActionController.class);
    static final private String CONFIRM_VIEW_PATH = "manager/massActionConfirmation/";

    @RequestMapping(params = {"action=showMassActionConfirmation"})
    public String showConfirmation(PortletRequest request, Model model) throws PortalException, SystemException {
        Integer massActionId = Math.abs(Integer.parseInt(request.getParameter("massActionId")));
        ContestMassActions contestMassAction = ContestMassActions.values()[massActionId];
        String confirmView = contestMassAction.getMethod().getName();
        List<Integer> contestIds = getContestIdsFromParameter(request.getParameter("contestIds"));
        model.addAttribute("massActionConfirmationWrapper",
                new MassActionConfirmationWrapper(contestIds, massActionId));
        model.addAttribute("massActionId", massActionId);
        return CONFIRM_VIEW_PATH + confirmView;
    }

    @RequestMapping(params = "action=confirmMassActionExecution")
    public void confirmMassActionExecution(ActionRequest request, Model model,
            @ModelAttribute MassActionConfirmationWrapper massActionConfirmationWrapper,
            ActionResponse response) {
        try {
            System.out.println("request: " + request.toString());
            massActionConfirmationWrapper.invokeMassActionForSelectedContests();
            SetRenderParameterUtil.addActionSuccessMessageToSession(request,
                    massActionConfirmationWrapper.getSelectedMassActionTitle());
            SetRenderParameterUtil.setSuccessRenderRedirectManagerTab(response, ContestManagerTabs.OVERVIEW.getName());
        } catch (InvocationTargetException | IllegalAccessException | IOException e) {
            _log.warn("Update contest overview failed with: ", e);
            SetRenderParameterUtil.setExceptionRenderParameter(response, e);
        }
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

}