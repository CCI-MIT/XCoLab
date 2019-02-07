package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.user.IPermissionClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.servlet.flash.InfoPage;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.utils.ContestCreatorUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class CreationController {

    @Autowired
    private IPermissionClient permissionClient;

    @PostMapping("createContest")
    public String createContestController(HttpServletRequest request,
            HttpServletResponse response, UserWrapper member) {
        if (!permissionClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        ContestWrapper contest = ContestCreatorUtil.createNewContest("created contest "
                + DateTime.now().toString("yyyy.MM.dd HH.mm.ss"), member.getId());
        String newContestLink = "/admin/contest/details/contestId/"
                + contest.getId();

        return InfoPage.message("<a href=\"" + newContestLink + "\">Click here to start "
                + "editing!</a>")
                .withTitle("You just created a new contest")
                .flashAndReturnView(request);
    }

    @GetMapping("contestCreated")
    public String createContestController() {
        return "message";
    }
}
