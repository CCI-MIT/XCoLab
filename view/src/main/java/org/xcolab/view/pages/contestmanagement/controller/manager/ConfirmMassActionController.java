package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.entity.utils.flash.AlertMessage;
import org.xcolab.view.pages.contestmanagement.wrappers.MassActionConfirmationWrapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class ConfirmMassActionController {

    @PostMapping("confirmMassAction")
    public String confirmMassActionExecution(HttpServletRequest request, Model model,
            @ModelAttribute MassActionConfirmationWrapper massActionConfirmationWrapper,
            HttpServletResponse response)
            throws IOException, InvocationTargetException, IllegalAccessException {
        massActionConfirmationWrapper.invokeMassActionForSelectedContests();
        AlertMessage.success("Mass action successful").flash(request);
        return "redirect:/admin/contest";
    }

}