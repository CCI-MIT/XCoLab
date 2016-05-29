package org.xcolab.portlets.contestmanagement.controller;

import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.members.PermissionsClient;
import org.xcolab.portlets.contestmanagement.utils.ContestCreatorUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * Created by Thomas on 2/19/2015.
 */
@Controller
@RequestMapping("view")
public class ContestManagementBaseController {

    @RequestMapping(params = "createContest=true")
    public String createContestController(PortletRequest request, Model model, PortletResponse response)
            throws PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        User currentUser = themeDisplay.getUser();

        if (!currentUser.isDefaultUser() && PermissionsClient.canAdminAll(currentUser.getUserId())) {
            Contest contest = ContestCreatorUtil.createNewContest("created contest "
                    + DateTime.now().toString("yyyy.MM.dd HH.mm.ss"));
            String newContestLink = "/web/guest/cms/-/contestmanagement/contestId/"
                    + contest.getContestPK() + "/tab/DESCRIPTION";
            model.addAttribute("newContestLink", newContestLink);
            return "common/newContestCreated";
        }
        throw new PortalException("User not authorized to create contest");
    }
}
