package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.points.DistributionStrategy;
import org.xcolab.points.ReceiverLimitationStrategy;
import org.xcolab.portlets.proposals.requests.AssignPointsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.PointTypeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class ProposalPointsTabController extends BaseProposalTabController {
    @Autowired
    private ProposalsContext proposalsContext;

    @Autowired
    private ProposalsContext context;

    private AssignPointsBean assignPointsBean;
    private List<User> members;
    private Proposal proposal;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_POINTS"})
    public String showProposalDetails(Model model, PortletRequest request) 
            throws PortalException, SystemException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.POINTS);
        try {
            proposal = proposalsContext.getProposal(request);
            Contest contest = proposalsContext.getContest(request);

            PointType contestParentPointType = PointTypeLocalServiceUtil.fetchPointType(contest.getDefaultParentPointType());

            if (contestParentPointType == null) {
                //there is no point scheme set for this contest, forward to description tab
                return "";
            }

            PointTypeWrapper parentPointType = new PointTypeWrapper(contestParentPointType);

            List<Proposal> subProposals = ProposalLocalServiceUtil.getSubproposals(proposal.getProposalId(), false);
            List<ProposalWrapper> subProposalsWrapped = new ArrayList<ProposalWrapper>();
            for (Proposal p: subProposals) {
                subProposalsWrapped.add(new ProposalWrapper(p));
            }

            members = ProposalLocalServiceUtil.getMembers(proposal.getProposalId());

            //this bean will be filled with the user input
            assignPointsBean = new AssignPointsBean();
            this.initializeAssignPointsBean(parentPointType);
            assignPointsBean.setupUsers(members);
            model.addAttribute("assignPointsBean", assignPointsBean);
            model.addAttribute("pointsToDistribute", contest.getPoints());
            model.addAttribute("pointType", parentPointType);
            model.addAttribute("recursionLevel", 0);
            model.addAttribute("percentageOfTotalPoints", parentPointType.getPercentageOfParent());
            model.addAttribute("subProposals", subProposalsWrapped);
            model.addAttribute("members", members);
            model.addAttribute("proposal", proposal);
            model.addAttribute("contest", contest);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return "proposalPoints";
    }

    private void initializeAssignPointsBean(PointTypeWrapper pointType) throws SystemException {
        if (DistributionStrategy.USER_DEFINED.name().equals(pointType.getDistributionStrategy())) {
            List<String> userStrategies = new ArrayList<String>();
            userStrategies.add(ReceiverLimitationStrategy.ANY_TEAM_MEMBER.name());
            userStrategies.add(ReceiverLimitationStrategy.ANY_NON_TEAM_MEMBER.name());
            userStrategies.add(ReceiverLimitationStrategy.ANY_USER.name());
            List<PointsDistributionConfiguration> existingConfiguration = PointsDistributionConfigurationLocalServiceUtil.findByProposalPointType(proposal, pointType.getPointType());
            //Team members
            if (userStrategies.contains(pointType.getReceiverLimitationStrategy())) {
                List<User> presetUsers = null;
                if (ReceiverLimitationStrategy.ANY_TEAM_MEMBER.name().equals(pointType.getReceiverLimitationStrategy())) {
                    presetUsers = members;
                }

                assignPointsBean.addAssignment(
                        pointType,
                        presetUsers,
                        existingConfiguration
                );
            }
            //TODO: subProposals
        }
        //follow down the pointType tree
        for (PointTypeWrapper p: pointType.getChildren()) {
            this.initializeAssignPointsBean(p);
        }
    }
    
    

}
