package org.xcolab.view.pages.contestmanagement.controller.manager;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.view.util.entity.flash.InfoMessage;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.contestmanagement.utils.ContestCreatorUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/contest/manager")
public class CreationController {

    @GetMapping("createContest")
    public String createContestController(HttpServletRequest request,
            HttpServletResponse response) {
        long memberId = MemberAuthUtil.getMemberId(request);

        if (PermissionsClient.canAdminAll(memberId)) {
            Contest contest = ContestCreatorUtil.createNewContest("created contest "
                    + DateTime.now().toString("yyyy.MM.dd HH.mm.ss"));
            String newContestLink = "/admin/contest/details/contestId/"
                    + contest.getContestPK();

            return InfoMessage.message("<a href=\"" + newContestLink + "\">Click here to start "
                    + "editing!</a>")
                    .withTitle("You just created a new contest")
                    .flashAndReturnView(request);
        }
        return ErrorText.ACCESS_DENIED.flashAndReturnView(request);
    }
}
