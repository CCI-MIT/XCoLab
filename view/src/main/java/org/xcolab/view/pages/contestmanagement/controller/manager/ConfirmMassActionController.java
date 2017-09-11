package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassActions;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.entities.massactions.ContestMassAction;
import org.xcolab.view.pages.contestmanagement.wrappers.MassActionConfirmationWrapper;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.xcolab.view.pages.contestmanagement.utils.MassActionUtil.getContests;

@Controller
@RequestMapping("/admin/contest/manager")
public class ConfirmMassActionController {

    @PostMapping("confirmMassAction")
    public String confirmMassActionExecution(HttpServletRequest request, Model model,
            @ModelAttribute MassActionConfirmationWrapper massActionConfirmationWrapper,
            HttpServletResponse response)
            throws IOException, InvocationTargetException, IllegalAccessException {
        List<Long> contestIds = massActionConfirmationWrapper.getSelectedContestIds();
        List<Contest> contests = getContests(contestIds);

        int massActionIndex = massActionConfirmationWrapper.getMassActionId();
        ContestMassActions actionWrapper = ContestMassActions.values()[massActionIndex];
        ContestMassAction action = actionWrapper.getAction();

        if (actionWrapper != ContestMassActions.DELETE
                && actionWrapper != ContestMassActions.DELETE_WITH_PHASES) {
            throw new IllegalArgumentException(
                    "No action defined for mass action id: " + massActionIndex);
        }

        try {
            action.execute(contests, true, null, null);
        } catch (MassActionRequiresConfirmationException e) {
            throw new IllegalStateException(
                    "An unexpected MassActionRequiresConfirmationException was encountered: \n" + e
                            .toString());
        }

        AlertMessage.success("Mass action successful").flash(request);
        return "redirect:/admin/contest";
    }

}
