package org.xcolab.portlets.contestmanagement.controller;

import com.ext.portlet.model.Contest;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.contestmanagement.utils.ContestCreatorUtil;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.Map;

/**
 * Created by Thomas on 2/19/2015.
 */
@Controller
@RequestMapping("view")
public class ContestManagementBaseController {

    @RequestMapping(params = "createContest=true")
    public String createContestController(PortletRequest request, Model model, PortletResponse response) {
        String view = "common/notFound";
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            PermissionChecker portletPermissionChecker = themeDisplay.getPermissionChecker();
            User currentUser = themeDisplay.getUser();

            if(!currentUser.isDefaultUser() && portletPermissionChecker.isOmniadmin()) {
                Contest contest = ContestCreatorUtil.createNewContest("created contest");
                String newContestLink = "/web/guest/cms/-/contestmanagement/contestId/" + contest.getContestPK() + "/tab/DESCRIPTION";
                model.addAttribute("newContestLink", newContestLink);
                view = "common/newContestCreated";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @RequestMapping(params = "create2015Tier1Contests=true")
    public String create2015Tier1ContestsController(PortletRequest request, Model model, PortletResponse response) {
        String view = "common/notFound";

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker portletPermissionChecker = themeDisplay.getPermissionChecker();
        User currentUser = themeDisplay.getUser();

        if(!currentUser.isDefaultUser() && portletPermissionChecker.isOmniadmin()) {

            Map<String, String> contestEditMap = ContestCreatorUtil.create2015BasicContests(themeDisplay.getPortalURL());
            model.addAttribute("contestEditLinks", contestEditMap);
            model.addAttribute("success", (contestEditMap != null));
            view = "common/creationDone";
        }

        return view;
    }

    @RequestMapping(params = "create2015Tier2And3Contests=true")
    public String create2015Tier2And3ContestsController(PortletRequest request, Model model, PortletResponse response) {
        String view = "common/notFound";

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker portletPermissionChecker = themeDisplay.getPermissionChecker();
        User currentUser = themeDisplay.getUser();

        if(!currentUser.isDefaultUser() && portletPermissionChecker.isOmniadmin()) {

            Map<String, String> contestEditMap = ContestCreatorUtil.create2015Tier2And3Contests(themeDisplay.getPortalURL());
            model.addAttribute("contestEditLinks", contestEditMap);
            model.addAttribute("success", (contestEditMap != null));
            view = "common/creationDone";
        }

        return view;
    }
}
