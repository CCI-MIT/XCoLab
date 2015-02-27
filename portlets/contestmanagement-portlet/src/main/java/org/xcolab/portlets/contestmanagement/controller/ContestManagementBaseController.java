package org.xcolab.portlets.contestmanagement.controller;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestSchedule;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestScheduleLocalService;
import com.ext.portlet.service.ContestScheduleLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.permission.PortletPermission;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.xcolab.enums.ContestTier;
import org.xcolab.portlets.contestmanagement.beans.ContestDescriptionBean;
import org.xcolab.portlets.contestmanagement.wrappers.ContestScheduleWrapper;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Thomas on 2/19/2015.
 */
@Controller
@RequestMapping("view")
public class ContestManagementBaseController {

    private static final String[] BASIC_CONTESTS_2015_SHORT_NAMES = new String[]{"Adaptation","Buildings","Energy Supply",
            "Geoengineering","Industry","Land Use: Agriculture, Forestry, Livestock","Shifting Attitudes & Behavior",
            "Transportation","US Carbon Price","Waste Management","OIP: Latin America Energy","Somerville: Atypical","Rural resilience"};

    // TODO: to be done when Laur is ready
    private static final String[] REGION_CONTESTS_2015_SHORT_NAMES = new String[]{};

    @RequestMapping(params = "createContest=true")
    public String createContestController(PortletRequest request, Model model, PortletResponse response) {
        String view = "notFound";
        try {
            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            PermissionChecker portletPermissionChecker = themeDisplay.getPermissionChecker();
            User currentUser = themeDisplay.getUser();

            if(!currentUser.isDefaultUser() && portletPermissionChecker.isOmniadmin()) {
                Contest contest = createNewContest("created contest");
                String newContestLink = "/web/guest/cms/-/contestmanagement/contestId/" + contest.getContestPK() + "/tab/DESCRIPTION";
                model.addAttribute("newContestLink", newContestLink);
                view = "newContestCreated";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @RequestMapping(params = "create2015Contests=true")
    public String create2015BasicContestsController(PortletRequest request, Model model, PortletResponse response) {
        String view = "notFound";

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        PermissionChecker portletPermissionChecker = themeDisplay.getPermissionChecker();
        User currentUser = themeDisplay.getUser();

        if(!currentUser.isDefaultUser() && portletPermissionChecker.isOmniadmin()) {

            Map<String, String> contestEditMap = create2015BasicContests(themeDisplay);
            model.addAttribute("contestEditLinks", contestEditMap);
            view = "creationDone";
        }

        return view;
    }

    private Map<String, String> create2015BasicContests(ThemeDisplay themeDisplay) {
        Map<String, String> contestEditMap = new HashMap<>(BASIC_CONTESTS_2015_SHORT_NAMES.length);
        for (String contestShortName : BASIC_CONTESTS_2015_SHORT_NAMES) {
            try {
                Contest newContest = createNewContest(contestShortName);
                newContest.setContestTier(ContestTier.BASIC.getTierType());

                // Look for the right contestSchedule
                for (ContestSchedule schedule : ContestScheduleLocalServiceUtil.getContestSchedules(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
                    if (schedule.getName().equals(ContestScheduleWrapper.CONTEST_SCHEDULE_2015_SECTOR_LABEL)) {
                        newContest.setContestScheduleId(schedule.getBaseScheduleId());
                        break;
                    }
                }

                newContest.persist();

                String absoluteURL = themeDisplay.getPortalURL() + getContestEditLink(newContest);
                contestEditMap.put(newContest.getContestShortName(), absoluteURL);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return contestEditMap;
    }

    private Contest createNewContest(String contestShortName) throws SystemException, PortalException {
        Contest contest = ContestLocalServiceUtil.createNewContest(10144L, contestShortName);
        contest.setContestPrivate(true);
        contest.persist();
        // TODO for now there is always a template preselected
        contest.setPlanTemplateId(102L);
        return contest;
    }

    private String getContestEditLink(Contest contest) {
        return "/web/guest/cms/-/contestmanagement/contestId/" + contest.getContestPK() + "/tab/DESCRIPTION";
    }
}
