package org.xcolab.portlets.contestmanagement.controller;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.entity.utils.members.MemberAuthUtil;
import org.xcolab.portlets.contestmanagement.utils.ContestCreatorUtil;
import org.xcolab.util.exceptions.InternalException;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

@Controller
@RequestMapping("view")
public class ContestManagementBaseController {

    @RequestMapping(params = "createContest=true")
    public String createContestController(PortletRequest request, PortletResponse response,
            Model model) {
        long memberId = MemberAuthUtil.getMemberId(request);

        if (PermissionsClient.canAdminAll(memberId)) {
            Contest contest = ContestCreatorUtil.createNewContest("created contest "
                    + DateTime.now().toString("yyyy.MM.dd HH.mm.ss"));
            String newContestLink = "/web/guest/cms/-/contestmanagement/contestId/"
                    + contest.getContestPK() + "/tab/DESCRIPTION";
            model.addAttribute("newContestLink", newContestLink);
            return "common/newContestCreated";
        }
        throw new InternalException("User not authorized to create contest");
    }
}
