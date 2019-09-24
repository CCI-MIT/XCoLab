package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassAction;
import org.xcolab.view.pages.contestmanagement.entities.ContestMassActions;
import org.xcolab.view.pages.contestmanagement.entities.MassActionRequiresConfirmationException;
import org.xcolab.view.pages.contestmanagement.wrappers.MassActionConfirmationWrapper;
import org.xcolab.view.util.entity.EntityIdListUtil;
import org.xcolab.commons.servlet.flash.AlertMessage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class ConfirmMassActionController {

    @PostMapping("confirmMassAction")
    public String confirmMassActionExecution(HttpServletRequest request, Model model, UserWrapper member,
            @ModelAttribute MassActionConfirmationWrapper massActionConfirmationWrapper,
            HttpServletResponse response)
            throws IOException, InvocationTargetException, IllegalAccessException {
        massActionConfirmationWrapper.setuserId(member.getId());

        List<Long> contestIds = massActionConfirmationWrapper.getSelectedContestIds();
        List<ContestWrapper> contests = EntityIdListUtil.CONTESTS.fromIdList(contestIds);

        ContestMassActions actionWrapper = massActionConfirmationWrapper.getSelectedMassAction();
        ContestMassAction action = actionWrapper.getAction();

        try {
            action.execute(contests, true, massActionConfirmationWrapper, response);
        } catch (MassActionRequiresConfirmationException e) {
            throw new IllegalStateException(
                    "An unexpected MassActionRequiresConfirmationException was encountered: \n" + e
                            .toString());
        }

        AlertMessage.success("Mass action successful").flash(request);
        return "redirect:/admin/contest";
    }

}
