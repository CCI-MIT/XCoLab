package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestPhaseRibbonTypeLocalServiceUtil;
import com.ext.portlet.service.PointTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.PointTypeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import javax.portlet.PortletRequest;
import java.util.ArrayList;

@Controller
@RequestMapping("view")
public class ProposalPointsTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;

    @Autowired
    private ProposalsContext context;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_POINTS"})
    public String showProposalDetails(Model model, PortletRequest request) 
            throws PortalException, SystemException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.POINTS);

        Proposal proposal = proposalsContext.getProposal(request);
        Contest contest = proposalsContext.getContest(request);

        PointType contestParentPointType = PointTypeLocalServiceUtil.fetchPointType(contest.getDefaultParentPointType());
        PointTypeWrapper parentPointType = new PointTypeWrapper(contestParentPointType);

        model.addAttribute("pointsToDistribute", contest.getPoints());
        model.addAttribute("pointType", parentPointType);
        model.addAttribute("percentageOfTotalPoints", parentPointType.getPercentageOfParent());

        return "proposalPoints";
    }
    
    

}
