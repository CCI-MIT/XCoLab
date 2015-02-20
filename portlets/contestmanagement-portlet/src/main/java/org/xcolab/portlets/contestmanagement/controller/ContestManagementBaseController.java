package org.xcolab.portlets.contestmanagement.controller;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.PortletPermission;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

/**
 * Created by Thomas on 2/19/2015.
 */
@Controller
@RequestMapping("view")
public class ContestManagementBaseController {


    @RequestMapping(params = "createContest=true")
    public String createContestController(PortletRequest request, Model model, PortletResponse response) {
        String view = "notFound";
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            PermissionChecker portletPermissionChecker = themeDisplay.getPermissionChecker();
            User currentUser = themeDisplay.getUser();

            if(!currentUser.isDefaultUser() && portletPermissionChecker.isOmniadmin()) {
                Contest contest = ContestLocalServiceUtil.createNewContest(10144L, "created contest");
                contest.setContestActive(false);
                contest.persist();
                // TODO for now there is always a template preselected
                contest.setPlanTemplateId(102L);
                String newContestLink = "/web/guest/cms/-/contestmanagement/contestId/" + contest.getContestPK() + "/tab/DESCRIPTION";
                model.addAttribute("newContestLink", newContestLink);
                view = "newContestCreated";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}
