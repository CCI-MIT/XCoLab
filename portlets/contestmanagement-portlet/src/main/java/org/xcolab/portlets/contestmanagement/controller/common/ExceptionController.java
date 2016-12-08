package org.xcolab.portlets.contestmanagement.controller.common;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.entity.utils.email.EmailToAdminDispatcher;
import org.xcolab.wrapper.SimpleExceptionErrorReportWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import java.io.IOException;

/**
 * Created by Thomas on 8/4/2015.
 */

@Controller
@RequestMapping("view")
public class ExceptionController {

    private final static Log _log = LogFactoryUtil.getLog(ExceptionController.class);
    public static final String EXCEPTION_VIEW = "common/showException";
    private static final String EMAIL_SUBJECT_FORMAT_STRING = "User encountered exception in CMS";
    private static final String EMAIL_ADDRESS_PLACEHOLDER = "(email address not specified)";
    private static final String USER_SCREEN_NAME_PLACEHOLDER = "(name not specified)";
    private static final String MESSAGE_BODY_FORMAT_STRING =
            "<p>Contest manager exception: %s for user <strong>%s</strong></p>" +
                    "<br/>" +
                    "<p>The user provided the following steps to reproduce the problem: <br/>%s</p>" +
                    "<p><strong>Please notify the user once we have a fix for the bug:</strong><br/>%s </br>" +
                    "<strong>Stacktrace:</strong>" +
                    "%s</p>";

    @RequestMapping(params = {"action=showException", "error=true"})
    public String showException(PortletRequest request, Model model) throws PortalException, SystemException {
        String exceptionMessage = request.getParameter("exceptionMessage");
        String exceptionStacktrace = request.getParameter("exceptionStacktrace");
        model.addAttribute("exceptionMessage", exceptionMessage);
        model.addAttribute("exceptionStacktrace", exceptionStacktrace);
        return EXCEPTION_VIEW;
    }

    @RequestMapping(params = {"action=reportException"})
    public void reportException(ActionRequest request, Model model,
            ActionResponse response) throws IOException, SystemException, PortalException {

        User user = PortalUtil.getUser(request);
        String stepsToReproduce = request.getParameter("stepsToReproduce");
        String exceptionMessage = request.getParameter("exceptionMessage");
        String exceptionStacktrace = request.getParameter("exceptionStacktrace");
        String userEmailAddress = user.getEmailAddress();
        SimpleExceptionErrorReportWrapper invalidUrlErrorWrapper =
                new SimpleExceptionErrorReportWrapper(stepsToReproduce, userEmailAddress, exceptionMessage,
                        exceptionStacktrace);

        if (invalidUrlErrorWrapper.isExceptionMessageAvailable()) {
            new EmailToAdminDispatcher(EMAIL_SUBJECT_FORMAT_STRING,
                    getMessageBody(invalidUrlErrorWrapper, user)).sendMessage();
        }

        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager");
    }

    private String getMessageBody(SimpleExceptionErrorReportWrapper simpleExceptionErrorReportWrapper, User user) {
        String emailAddress = EMAIL_ADDRESS_PLACEHOLDER;
        if (Validator.isNotNull(simpleExceptionErrorReportWrapper.getUserEmailAddress())) {
            emailAddress = simpleExceptionErrorReportWrapper.getUserEmailAddress();
        }

        String userScreenName = USER_SCREEN_NAME_PLACEHOLDER;
        if (Validator.isNotNull(user)) {
            userScreenName = user.getScreenName();
            if (Validator.isNull(emailAddress) || emailAddress.equals(EMAIL_ADDRESS_PLACEHOLDER)) {
                emailAddress = user.getEmailAddress();
            }
        }

        return String.format(MESSAGE_BODY_FORMAT_STRING,
                simpleExceptionErrorReportWrapper.getExceptionMessage(),
                userScreenName,
                simpleExceptionErrorReportWrapper.getStepsToReproduce(),
                emailAddress,
                simpleExceptionErrorReportWrapper);
    }

}