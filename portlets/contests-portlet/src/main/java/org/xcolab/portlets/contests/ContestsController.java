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
    private static final int NUM_CONTESTS = 5;
    
    public ContestsController() {
    }

    @RequestMapping
    public String showContests(PortletRequest request, PortletResponse response, Model model) throws SystemException, PortalException {
    	
        List<ContestWrapper> ret = new ArrayList<>();
        List<Contest> contests = ContestLocalServiceUtil.findByActiveFeatured(true,true);
        Collections.shuffle(contests);
        for (Contest contest: contests) {
            if(ret.size() >= NUM_CONTESTS) {
            	break;
            }
            if (contest.getContestPrivate()) {
            	continue;
            }
            ret.add(new ContestWrapper(contest));
        }
        
        model.addAttribute("contests", ret);
    	return "showContests";
    }



}