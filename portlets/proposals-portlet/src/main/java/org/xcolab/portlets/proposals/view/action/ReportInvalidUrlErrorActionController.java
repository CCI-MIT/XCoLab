package org.xcolab.portlets.proposals.view.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.wrapper.SimpleExceptionErrorReportWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

/**
 * Created by Klemens Mang on 29/07/15.
 */
@RequestMapping("view")
@Controller
public class ReportInvalidUrlErrorActionController {

    private static final String EMAIL_SUBJECT_FORMAT_STRING = "User accessed invalid URL %s";

    private static final String EMAIL_ADDRESS_PLACEHOLDER = "(email address not specified)";
    private static final String USER_SCREEN_NAME_PLACEHOLDER = "(name not specified)";

    private static final String URL_REGEX = "([A-Za-z]{4}:(?:\\/\\/)?)climatecolab\\.org\\/(.)*\\/plans\\/(.)*";


    private static final String MESSAGE_BODY_FORMAT_STRING = "<p>URL %s could not be accessed by user <strong>%s</strong></p>" +
            "<br/>" +
            "<p>The user provided the following steps to reproduce the problem: <br/>%s</p>" +
            "<p><strong>Please notify the user once we have a fix for the bug:</strong><br/>%s</p>";

    @RequestMapping(params = {"error=invalidUrl", "pageToDisplay=contestProposals"})
    public void show(ActionRequest request, Model model,
                     ActionResponse response) throws IOException, SystemException, PortalException {

        String stepsToReproduce = request.getParameter("stepsToReproduce");
        String userEmailAddress = request.getParameter("userEmailAddress");
        String url = request.getParameter("url");
        SimpleExceptionErrorReportWrapper simpleExceptionErrorReportWrapper = new SimpleExceptionErrorReportWrapper(stepsToReproduce, userEmailAddress);

        if (simpleExceptionErrorReportWrapper.isWrapperFilledOut()
                && isUrlValid(url)) {

            User user = null;
            try {
                user = PortalUtil.getUser(request);
            } catch(Exception exception) {
                // User not logged in
            }

            new EmailToAdminDispatcher(String.format(EMAIL_SUBJECT_FORMAT_STRING, url),
                    getMessageBody(url, simpleExceptionErrorReportWrapper, user)).sendMessage();
        }

        response.sendRedirect("/web/guest/plans");
    }

    private boolean isUrlValid(String url) {
        return Validator.isNotNull(url) && url.matches(URL_REGEX);
    }

    private String getMessageBody(String url, SimpleExceptionErrorReportWrapper simpleExceptionErrorReportWrapper, User user) {
        String userScreenName = USER_SCREEN_NAME_PLACEHOLDER;
        String emailAddress = EMAIL_ADDRESS_PLACEHOLDER;
        if (Validator.isNotNull(simpleExceptionErrorReportWrapper.getUserEmailAddress())) {
            emailAddress = simpleExceptionErrorReportWrapper.getUserEmailAddress();
        }

        if (Validator.isNotNull(user)) {
            userScreenName = user.getScreenName();
            if (Validator.isNull(emailAddress) || emailAddress.equals(EMAIL_ADDRESS_PLACEHOLDER)) {
                emailAddress = user.getEmailAddress();
            }
        }

        return String.format(MESSAGE_BODY_FORMAT_STRING, url, userScreenName, simpleExceptionErrorReportWrapper.getStepsToReproduce(), emailAddress);
    }
}
