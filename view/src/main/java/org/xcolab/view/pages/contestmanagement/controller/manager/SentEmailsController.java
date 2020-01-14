package org.xcolab.view.pages.contestmanagement.controller.manager;

import com.google.gson.JsonObject;
import net.sf.json.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.xcolab.client.email.IEmailClient;
import org.xcolab.client.email.pojo.IOutgoingEmail;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.entities.ContestManagerTabs;
import org.xcolab.view.taglibs.xcolab.wrapper.TabWrapper;
import org.xcolab.client.email.pojo.tables.pojos.OutgoingEmail;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class SentEmailsController extends AbstractTabController {

    static final private ContestManagerTabs tab = ContestManagerTabs.SENT_EMAILS;
    static final private String TAB_VIEW = "contestmanagement/manager/sentEmails";

    @Autowired
    private IEmailClient emailClient;


    @ModelAttribute("currentTabWrapped")
    @Override
    public TabWrapper populateCurrentTabWrapped(HttpServletRequest request) {
        tabWrapper = new TabWrapper(tab, request, tabContext);
        return tabWrapper;
    }

    @GetMapping("tab/SENT_EMAILS")
    public String showEmailTabController(HttpServletRequest request, HttpServletResponse response,
            Model model, UserWrapper member, @RequestParam(required = true, defaultValue = "15") int numOfEmails) {

        if (!tabWrapper.getCanView()) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        List<IOutgoingEmail> retrievedEmails = emailClient.getSentEmails(numOfEmails);
        model.addAttribute("retrievedEmails", retrievedEmails);

        JsonObject json = new JsonObject();
        json.addProperty("number", numOfEmails);
        model.addAttribute("numOfEmails", Integer.toString(numOfEmails));

        return TAB_VIEW;
    }

}
