package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.pages.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.commons.servlet.flash.InfoPage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class CreationController {

    @PostMapping("createContest")
    public String createContestController(HttpServletRequest request,
            HttpServletResponse response, Member member) {
        if (!PermissionsClient.canAdminAll(member)) {
            return new AccessDeniedPage(member).toViewName(response);
        }

        Contest contest = ContestCreatorUtil.createNewContest("created contest "
                + DateTime.now().toString("yyyy.MM.dd HH.mm.ss"));
        String newContestLink = "/admin/contest/details/contestId/"
                + contest.getContestPK();

        return InfoPage.message("<a href=\"" + newContestLink + "\">Click here to start "
                + "editing!</a>")
                .withTitle("You just created a new contest")
                .flashAndReturnView(request);
    }
}
