package org.xcolab.portlets.contests;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Controller
@RequestMapping("view")
public class ContestsController {
    
    public ContestsController() {
    }

    @RequestMapping
    public String showContests(PortletRequest request, PortletResponse response, Model model) throws SystemException, PortalException {

        ContestPreferences contestPreferences = new ContestPreferences(request);

        List<ContestWrapper> ret = new ArrayList<>();
        List<Contest> contests;
        if (contestPreferences.getSelectedContests().length == 0) {
             contests = ContestLocalServiceUtil.findByActiveFeatured(true,true);
        } else {
            contests = new ArrayList<>();
            for (Long contestId : contestPreferences.getSelectedContests()) {
                contests.add(ContestLocalServiceUtil.getContest(contestId));
            }
        }

        Collections.shuffle(contests);
        for (Contest contest: contests) {
            if(ret.size() >= contestPreferences.getFeedSize()) {
            	break;
            }
            if (contest.getContestPrivate()) {
            	continue;
            }
            ret.add(new ContestWrapper(contest));
        }
        
        model.addAttribute("contests", ret);
        model.addAttribute("title", contestPreferences.getTitle());
    	return "showContests";
    }
}