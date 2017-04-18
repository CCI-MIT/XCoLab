package org.xcolab.view.notification;

import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.admin.AdminClient;
import org.xcolab.client.admin.pojo.Notification;
import org.xcolab.client.contents.ContentsClient;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentPage;
import org.xcolab.client.contest.OntologyClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ontology.FocusArea;
import org.xcolab.client.contest.pojo.ontology.OntologyTerm;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.feedback.ContactBean;
import org.xcolab.view.pages.proposals.impact.ProposalImpactSeries;
import org.xcolab.view.pages.proposals.impact.ProposalImpactUtil;
import org.xcolab.view.pages.proposals.utils.context.ProposalContextHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalsContextImpl;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.Console;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zeeshan on 30.03.17.
 */
@Controller
public class NotificationController {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")

    public static int notificationCounter = 0;

    @RequestMapping(value = "/notificationMessageDelete", method = RequestMethod.POST)
    public String saveNotification(HttpServletRequest request,
            HttpServletResponse response, @RequestParam String notificationId)
            throws IOException, ParseException {

        long memberId = MemberAuthUtil.getMemberId(request);
        if (!PermissionsClient.canAdminAll(memberId)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }


        try {
            AdminClient.deleteNotifications(notificationId);

            AlertMessage.success("Notification Deleted Successfully!").flash(request);
            response.sendRedirect("/admin/contest/manager/tab/ADMIN");

            return null;

        } catch (Exception e) {
            AlertMessage.danger("Sorry. Notification cannot be Deleted Successfully!")
                    .flash(request);
            response.sendRedirect("/admin/contest/manager/tab/ADMIN");

            e.printStackTrace();
            return null;
        }
    }


    @RequestMapping(value = "/notificationMessage", method = RequestMethod.GET)
    public String showNotification(HttpServletRequest request,
            HttpServletResponse response) throws IOException {

        try {
            List<Notification> list = AdminClient.getNotifications();

            if (list != null && !list.isEmpty()) {
                JSONObject responseJSON = new JSONObject();


                Notification chosenNoti = list.get(0);

                responseJSON.put("notificationId", chosenNoti.getNotificationId());
                responseJSON.put("notificationText", chosenNoti.getNotificationText());
                responseJSON.put("success", true);
                response.getOutputStream().write(responseJSON.toString().getBytes());
            }
            return null;

        } catch (EntityNotFoundException e) {
            JSONObject responseJSON = new JSONObject();
            responseJSON.put("success", false);
            response.getOutputStream().write(responseJSON.toString().getBytes());

            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/notificationMessageGET", method = RequestMethod.POST)
    public String saveNotification(HttpServletRequest request,
            HttpServletResponse response, @RequestParam String notificationText,
            @RequestParam String expiretime)
            throws IOException, ParseException {

        long memberId = MemberAuthUtil.getMemberId(request);
        if (!PermissionsClient.canAdminAll(memberId)) {
            return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
        }


        try {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            expiretime = expiretime.replace("T", " ");

            if (expiretime.length() < "yyyy-MM-dd hh:mm:ss".length()) {
                expiretime = expiretime + ":00";
            }


            Date beginDate = new Date();
            Date endDate = formatter.parse(expiretime);

            if (endDate.before(new Date())) {
                AlertMessage
                        .danger("Expiry date cannot be in the past. Notification will not be "
                                + "saved!")
                        .flash(request);
                response.sendRedirect("/admin/contest/manager/tab/ADMIN");
                return null;
            }

            List<Notification> list = AdminClient.getNotifications();
            Date now = new Date();
            Notification newNotification = new Notification();
            newNotification.setNotificationId(++notificationCounter);
            newNotification.setNotificationText(notificationText);
            newNotification.setBeginTime(beginDate);
            newNotification.setEndTime(endDate);

            AdminClient.setNotifications(newNotification);
            list = AdminClient.getNotifications();

            AlertMessage.success("Notification Created Successfully!").flash(request);
            response.sendRedirect("/admin/contest/manager/tab/ADMIN");

            return null;

        } catch (EntityNotFoundException e) {

            AlertMessage.danger("Sorry. Notification cannot be created at the moment!")
                    .flash(request);
            response.sendRedirect("/admin/contest/manager/tab/ADMIN");

            e.printStackTrace();
            return null;
        }
    }

}
