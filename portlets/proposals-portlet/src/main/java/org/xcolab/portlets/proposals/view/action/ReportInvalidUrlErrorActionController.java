package org.xcolab.portlets.proposals.view.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.entity.utils.email.EmailToAdminDispatcher;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.wrapper.SimpleExceptionErrorReportWrapper;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

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
                     ActionResponse response) throws IOException {

        String stepsToReproduce = request.getParameter("stepsToReproduce");
        String userEmailAddress = request.getParameter("userEmailAddress");
        String url = request.getParameter("url");
        SimpleExceptionErrorReportWrapper simpleExceptionErrorReportWrapper = new SimpleExceptionErrorReportWrapper(stepsToReproduce, userEmailAddress);

        if (simpleExceptionErrorReportWrapper.isWrapperFilledOut()
                && isUrlValid(url)) {

            Member user = null;
            try {
                user = MembersClient.getMember(MemberAuthUtil.getMemberId(request));
            } catch(Exception exception) {
                // User not logged in
            }

            new EmailToAdminDispatcher(String.format(EMAIL_SUBJECT_FORMAT_STRING, url),
                    getMessageBody(url, simpleExceptionErrorReportWrapper, user)).sendMessage();
        }

        response.sendRedirect("/contests");
    }

    private boolean isUrlValid(String url) {
        return (url!= null) && url.matches(URL_REGEX);
    }

    private String getMessageBody(String url, SimpleExceptionErrorReportWrapper simpleExceptionErrorReportWrapper, Member user) {
        String userScreenName = USER_SCREEN_NAME_PLACEHOLDER;
        String emailAddress = EMAIL_ADDRESS_PLACEHOLDER;
        if ((simpleExceptionErrorReportWrapper.getUserEmailAddress()!=null)) {
            emailAddress = simpleExceptionErrorReportWrapper.getUserEmailAddress();
        }

        if ((user!=null)) {
            userScreenName = user.getScreenName();
            if ((emailAddress == null) || emailAddress.equals(EMAIL_ADDRESS_PLACEHOLDER)) {
                emailAddress = user.getEmailAddress();
            }
        }

        return String.format(MESSAGE_BODY_FORMAT_STRING, url, userScreenName, simpleExceptionErrorReportWrapper.getStepsToReproduce(), emailAddress);
    }
}
