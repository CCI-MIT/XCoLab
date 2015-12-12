package org.xcolab.portlets.proposals.view;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.PointType;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.PointTypeLocalServiceUtil;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;
import com.ext.portlet.service.PointsLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.points.DistributionStrategy;
import org.xcolab.points.PointsTarget;
import org.xcolab.points.ReceiverLimitationStrategy;
import org.xcolab.portlets.proposals.requests.AssignPointsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.PointTypeWrapper;
import org.xcolab.portlets.proposals.wrappers.PointsTargetProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import javax.portlet.PortletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("view")
public class ProposalPointsTabController extends BaseProposalTabController {

    private static final Log _log = LogFactoryUtil.getLog(ProposalPointsTabController.class);

    @Autowired
    private ProposalsContext proposalsContext;

    private AssignPointsBean assignPointsBean;
    private List<User> members;
    private Proposal proposal;
    
    @RequestMapping(params = {"pageToDisplay=proposalDetails_POINTS"})
    public String showProposalDetails(Model model, PortletRequest request) 
            throws PortalException, SystemException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.POINTS);
        proposal = proposalsContext.getProposal(request);
        Contest contest = proposalsContext.getContest(request);

        PointType contestParentPointType = PointTypeLocalServiceUtil.fetchPointType(contest.getDefaultParentPointType());

        if (contestParentPointType == null) {
            //there is no point scheme set for this contest, forward to description tab
            return "";
        }

        PointTypeWrapper parentPointType = new PointTypeWrapper(contestParentPointType);

        List<Proposal> subProposals = ProposalLocalServiceUtil.getSubproposals(proposal.getProposalId(), false);
        List<ProposalWrapper> subProposalsWrapped = new ArrayList<>();
        for (Proposal p: subProposals) {
            subProposalsWrapped.add(new ProposalWrapper(p));
        }
        //TODO: make this flexible
        PointType pointType = PointTypeLocalServiceUtil.getPointType(9L);
        DistributionStrategy distributionStrategy = DistributionStrategy.valueOf(pointType.getDistributionStrategy());
        ReceiverLimitationStrategy receiverLimitationStrategy = ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy());

        List<PointsTarget> targets = receiverLimitationStrategy.getTargets(proposal, pointType, distributionStrategy);
        List<PointsTargetProposalWrapper> regionalPercentages = new ArrayList<>();
        for (PointsTarget target : targets) {
            regionalPercentages.add(new PointsTargetProposalWrapper(target, 93));
        }

        pointType = PointTypeLocalServiceUtil.getPointType(4L);
        distributionStrategy = DistributionStrategy.valueOf(pointType.getDistributionStrategy());
        receiverLimitationStrategy = ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy());

        targets = receiverLimitationStrategy.getTargets(proposal, pointType, distributionStrategy);
        List<PointsTargetProposalWrapper> basicPercentages = new ArrayList<>();
        for (PointsTarget target : targets) {
            basicPercentages.add(new PointsTargetProposalWrapper(target, 2));
        }



        List<ProposalWrapper> linkingProposalsWrapped = new ArrayList<>();
        final List<Proposal> linkingProposals = PointsLocalServiceUtil.getLinkingProposals(proposal.getProposalId());
        for (Proposal p : linkingProposals) {
            linkingProposalsWrapped.add(new ProposalWrapper(p));
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
        model.addAttribute("regionalPercentages", regionalPercentages);
        model.addAttribute("basicPercentages", basicPercentages);
        model.addAttribute("members", members);
        model.addAttribute("totalPoints", PointsLocalServiceUtil.getProposalMaterializedPoints(proposal.getProposalId()));
        model.addAttribute("proposal", proposal);
        model.addAttribute("contest", contest);
        model.addAttribute("linkingProposals", linkingProposalsWrapped);
        return "proposalPoints";
    }

    private void initializeAssignPointsBean(PointTypeWrapper pointType) throws SystemException {
        if (DistributionStrategy.USER_DEFINED.name().equals(pointType.getDistributionStrategy())) {
            List<String> userStrategies = new ArrayList<>();
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
