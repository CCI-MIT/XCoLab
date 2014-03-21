package org.xcolab.portlets.contests;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ext.portlet.model.Contest;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;


@Controller
@RequestMapping("view")
public class ContestsController {
    
    public ContestsController() {
    }

    @RequestMapping
    public String showContests(PortletRequest request, PortletResponse response, Model model) throws SystemException, PortalException {
    	
        List<ContestWrapper> ret = new ArrayList<>();
        
        for (Contest contest: ContestLocalServiceUtil.findByActiveFlagText(true, "Open")) {
            ret.add(new ContestWrapper(contest));
        }
        
        model.addAttribute("contests", ret);
    	return "showContests";
    }



}