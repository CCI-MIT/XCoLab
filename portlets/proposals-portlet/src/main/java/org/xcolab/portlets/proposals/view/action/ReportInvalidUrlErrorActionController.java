package org.xcolab.portlets.proposals.view.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.portlets.proposals.wrappers.InvalidUrlErrorWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

/**
 * Created by Klemens Mang on 29/07/15.
 */
@RequestMapping("view")
@Controller
public class ReportInvalidUrlErrorActionController {

    private static final String EMAIL_SUBJECT = "User accessed invalid URL";

    private static final String EMAIL_ADDRESS_PLACEHOLDER = "(email address not specified)";
    private static final String USER_SCREEN_NAME_PLACEHOLDER = "(name not specified)";


    private static final String MESSAGE_BODY_FORMAT_STRING = "<p>URL %s could not be accessed by user <strong>%s</strong></p>" +
            "<br/>" +
            "<p><strong>Please notify the user once we have a fix for the bug:</strong><br/>%s</p>";

    @RequestMapping(params = {"error=invalidUrl", "pageToDisplay=contestProposals"})
    public void show(ActionRequest request, Model model,
                     ActionResponse response) throws IOException, SystemException, PortalException {

        String stepsToReproduce = request.getParameter("stepsToReproduce");
        String userEmailAddress = request.getParameter("userEmailAddress");
        String url = request.getParameter("url");
        InvalidUrlErrorWrapper invalidUrlErrorWrapper = new InvalidUrlErrorWrapper(stepsToReproduce, userEmailAddress);

        if (invalidUrlErrorWrapper.isWrapperFilledOut()) {
            User user = null;
            try {
                user = PortalUtil.getUser(request);
            } catch(Exception exception) {
                // User not logged in
            }

            new EmailToAdminDispatcher(EMAIL_SUBJECT, getMessageBody(url, invalidUrlErrorWrapper, user)).sendMessage();
        }

        response.sendRedirect("/web/guest/plans");
    }

    private String getMessageBody(String url, InvalidUrlErrorWrapper invalidUrlErrorWrapper, User user) {
        String userScreenName = USER_SCREEN_NAME_PLACEHOLDER;
        String emailAddress = EMAIL_ADDRESS_PLACEHOLDER;
        if (Validator.isNotNull(invalidUrlErrorWrapper.getUserEmailAddress())) {
            emailAddress = invalidUrlErrorWrapper.getUserEmailAddress();
        }

        if (Validator.isNotNull(user)) {
            userScreenName = user.getScreenName();
            if (Validator.isNull(emailAddress) || emailAddress.equals(EMAIL_ADDRESS_PLACEHOLDER)) {
                emailAddress = user.getEmailAddress();
            }
        }

        return String.format(MESSAGE_BODY_FORMAT_STRING, url, userScreenName, emailAddress);
    }
}
