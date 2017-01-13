package org.xcolab.view.pages.contestmanagement.controller.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.email.EmailToAdminDispatcher;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.taglibs.xcolab.wrapper.SimpleExceptionErrorReportWrapper;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest")
public class ExceptionController {

    private static final String EXCEPTION_VIEW = "common/showException";
    private static final String EMAIL_SUBJECT_FORMAT_STRING = "User encountered exception in CMS";
    private static final String EMAIL_ADDRESS_PLACEHOLDER = "(email address not specified)";
    private static final String USER_SCREEN_NAME_PLACEHOLDER = "(name not specified)";
    private static final String MESSAGE_BODY_FORMAT_STRING =
            "<p>Contest manager exception: %s for user <strong>%s</strong></p>" +
                    "<br/>" +
                    "<p>The user provided the following steps to reproduce the problem: <br/>%s</p>"
                    +
                    "<p><strong>Please notify the user once we have a fix for the "
                    + "bug:</strong><br/>%s </br>"
                    +
                    "<strong>Stacktrace:</strong>" +
                    "%s</p>";

    @RequestMapping(params = {"action=showException", "error=true"})
    public String showException(HttpServletRequest request, Model model) {
        String exceptionMessage = request.getParameter("exceptionMessage");
        String exceptionStacktrace = request.getParameter("exceptionStacktrace");
        model.addAttribute("exceptionMessage", exceptionMessage);
        model.addAttribute("exceptionStacktrace", exceptionStacktrace);
        return EXCEPTION_VIEW;
    }

    @RequestMapping(params = {"action=reportException"})
    public void reportException(HttpServletRequest request, Model model,
            HttpServletResponse response) throws IOException {

        Member member = MemberAuthUtil.getMemberOrNull(request);
        String stepsToReproduce = request.getParameter("stepsToReproduce");
        String exceptionMessage = request.getParameter("exceptionMessage");
        String exceptionStacktrace = request.getParameter("exceptionStacktrace");
        String userEmailAddress = member != null ? member.getEmailAddress() : "";
        SimpleExceptionErrorReportWrapper invalidUrlErrorWrapper =
                new SimpleExceptionErrorReportWrapper(stepsToReproduce, userEmailAddress,
                        exceptionMessage,
                        exceptionStacktrace);

        if (invalidUrlErrorWrapper.isExceptionMessageAvailable()) {
            new EmailToAdminDispatcher(EMAIL_SUBJECT_FORMAT_STRING,
                    getMessageBody(invalidUrlErrorWrapper, member)).sendMessage();
        }

        response.sendRedirect("/web/guest/cms/-/contestmanagement/manager");
    }

    private String getMessageBody(
            SimpleExceptionErrorReportWrapper simpleExceptionErrorReportWrapper, Member member) {
        String emailAddress = EMAIL_ADDRESS_PLACEHOLDER;
        if (StringUtils.isNotBlank(simpleExceptionErrorReportWrapper.getUserEmailAddress())) {
            emailAddress = simpleExceptionErrorReportWrapper.getUserEmailAddress();
        }

        String userScreenName = USER_SCREEN_NAME_PLACEHOLDER;
        if (member != null) {
            userScreenName = member.getScreenName();
            if (StringUtils.isBlank(emailAddress) || emailAddress
                    .equals(EMAIL_ADDRESS_PLACEHOLDER)) {
                emailAddress = member.getEmailAddress();
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