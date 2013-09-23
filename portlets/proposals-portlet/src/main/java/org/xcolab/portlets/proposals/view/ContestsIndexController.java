package org.xcolab.portlets.proposals.view;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.proposals.utils.ContestsSortFilterBean;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ContestsIndexController {

    private final static String COOKIE_VIEW_TYPE = "cc_contests_viewType";
    private final static String VIEW_TYPE_GRID = "GRID";
    private final static String VIEW_TYPE_LIST = "LIST";
    private final static String VIEW_TYPE_DEFAULT = VIEW_TYPE_GRID;
    
    @RequestMapping
    public String showContestsIndex(PortletRequest request, PortletResponse response, Model model, 
            @RequestParam(required = false) String viewType, 
            @RequestParam(required = false, defaultValue="true") boolean showActiveContests,
            SortFilterPage sortFilterPage) 
                    throws PortalException, SystemException {
        if (viewType == null) {
            // view type wasn't set
            for (Cookie cookie: request.getCookies()) {
                if (cookie.getName().equals(COOKIE_VIEW_TYPE)) {
                    viewType = cookie.getValue();
                } 
            }
        }
        else {
            // we need to change the view type
            if (viewType.equals(VIEW_TYPE_GRID) || viewType.equals(VIEW_TYPE_LIST)) {
                // we should set the cookie but it doesn't work currently https://issues.liferay.com/browse/LPS-25733 it should be handled in the view
                response.addProperty(new Cookie(COOKIE_VIEW_TYPE, viewType));
            }
        }
        if (viewType == null) {
            viewType = VIEW_TYPE_DEFAULT;
        }
        List<ContestWrapper> contests = new ArrayList<ContestWrapper>();
        for (Contest contest: ContestLocalServiceUtil.getContests(0, 1000)) {
            if (contest.getContestActive() == showActiveContests) {
                contests.add(new ContestWrapper(contest));
            }
        }

        model.addAttribute("contests", contests);
        model.addAttribute("contestsSorted", new ContestsSortFilterBean(contests, sortFilterPage));
        model.addAttribute("viewType", viewType);
        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("showActiveContests", showActiveContests);
        
        return "contestsIndex";
    }

}
