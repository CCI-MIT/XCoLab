package org.xcolab.view.notification;

import org.json.JSONObject;
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
import org.xcolab.util.http.exceptions.EntityNotFoundException;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.feedback.ContactBean;
import org.xcolab.view.pages.proposals.impact.ProposalImpactSeries;
import org.xcolab.view.pages.proposals.impact.ProposalImpactUtil;
import org.xcolab.view.util.entity.flash.AlertMessage;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zeeshan on 30.03.17.
 */
@Controller
public class NotificationController {



    @RequestMapping(value = "/notificationMessage", method = RequestMethod.GET)
    public String showNotification(HttpServletRequest request,
            HttpServletResponse response) throws IOException
    {

        try {
            List<Notification> list = AdminClient.getNotifications("dummy time");
            System.out.println("Printing CoLab1234: "+ list.get(0).getNotificationText());

            JSONObject responseJSON = new JSONObject();



            Notification chosenNoti = list.get(0);

            responseJSON.put("notificationId", chosenNoti.getNotificationId());
            responseJSON.put("notificationText", chosenNoti.getNotificationText());
            responseJSON.put("success", true);
            response.getOutputStream().write(responseJSON.toString().getBytes());

            AlertMessage.success("We hope you come back soon!").flash(request);

            return null;

        } catch (EntityNotFoundException e) {
            JSONObject responseJSON = new JSONObject();
            responseJSON.put("success", false);
            response.getOutputStream().write(responseJSON.toString().getBytes());

            e.printStackTrace();
            return null;
        }
    }


}
