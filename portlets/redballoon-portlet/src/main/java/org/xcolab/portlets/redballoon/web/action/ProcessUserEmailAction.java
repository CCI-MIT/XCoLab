package org.xcolab.portlets.redballoon.web.action;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.util.mail.MailEngineException;

import org.xcolab.client.balloons.BalloonsClient;
import org.xcolab.client.balloons.exceptions.BalloonUserTrackingNotFound;
import org.xcolab.client.balloons.pojo.BalloonLink;
import org.xcolab.client.balloons.pojo.BalloonText;
import org.xcolab.client.balloons.pojo.BalloonUserTracking;
import org.xcolab.client.emails.EmailClient;
import org.xcolab.portlets.redballoon.utils.BalloonUtils;
import org.xcolab.portlets.redballoon.web.beans.UserEmailBean;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.validation.Valid;

//import com.ext.portlet.model.BalloonUserTracking;

@RequestMapping("view")
@Controller
public class ProcessUserEmailAction {

    private final static String BALLOON_LINK_PATTERN = "/socialnetworkprize2016";

    private final static String FROM_ADDRESS = "no-reply@climatecolab.org";

    private final static String EMAIL_SENT = AddEditBalloonTextAction.class.getName()
            + "EMAIL_SENT";

    private final static String USER_EMAIL = AddEditBalloonTextAction.class.getName()
            + "USER_EMAIL";
    public static final String URLPLACEHOLDER = "URLPLACEHOLDER";

    @RequestMapping(params = "action=sendEmail")
    public void processUserEmail(ActionRequest request, ActionResponse response, Model model,
                                 @Valid UserEmailBean userEmailBean, BindingResult bindingResult) throws IOException, AddressException, MailEngineException {

        if (userEmailBean != null && !bindingResult.hasErrors()) {
            BalloonUserTracking but = BalloonUtils.getBalloonUserTracking(request, response, null, null, null);
            if (StringUtils.isNotBlank(but.getEmail())) {
                // don't change the email address, just ignore the request
            }

            but.setEmail(userEmailBean.getEmail());
            but.setFormFiledDate(new Timestamp(new Date().getTime()));


            BalloonsClient.updateBalloonUserTracking(but);

            // create link to be used by user
            BalloonLink link = new BalloonLink();
            link.setUuid_(UUID.randomUUID().toString());

            link.setBalloonUserUuid(but.getUuid_());
            link.setCreateDate(new Timestamp(new Date().getTime()));
            //TODO: this doesn't look right
            link.setTargetUrl(String.format(BALLOON_LINK_PATTERN, link.getUuid_()));

            BalloonsClient.createBalloonLink(link);
            sendNotificationEmail(request, but, link);
            response.sendRedirect(BALLOON_LINK_PATTERN+"/-/link/" + link.getUuid_());

        }
    }


    private void sendNotificationEmail(PortletRequest request, BalloonUserTracking but, BalloonLink link) throws AddressException,
            MailEngineException {

        PortletSession session = request.getPortletSession();
        if (session.getAttributeMap().containsKey(EMAIL_SENT)) {
            return;
        }

        if (but.getBalloonTextId() <= 0) {
            return;
        }

        try {
            BalloonText text = BalloonsClient.getBalloonText(but.getBalloonTextId());
            String messageSubject = text.getEmailSubjectTemplate();
            String messageBody = text.getEmailTemplate().replaceAll(URLPLACEHOLDER, BalloonUtils.getBalloonUrlForLink(request, link));



            String mailAdr = session.getAttribute(USER_EMAIL) == null ? but.getEmail() : session.getAttribute(USER_EMAIL).toString();

            if (StringUtils.isBlank(mailAdr)) {
                return;
            }

            String[] recipients = new String[]{mailAdr};
            List<String> addressTo = new ArrayList<>();
            Collections.addAll(addressTo, recipients);

            EmailClient.sendEmail(FROM_ADDRESS, addressTo, messageSubject, messageBody, true, FROM_ADDRESS);

            session.setAttribute(EMAIL_SENT, true);

        } catch (BalloonUserTrackingNotFound ignored) {

        }


    }

}
