package org.xcolab.portlets.proposals.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.points.DistributionStrategy;
import org.xcolab.points.PointsTarget;
import org.xcolab.points.ReceiverLimitationStrategy;
import org.xcolab.portlets.proposals.requests.AssignPointsBean;
import org.xcolab.portlets.proposals.utils.context.ProposalsContext;
import org.xcolab.portlets.proposals.utils.context.ProposalsContextUtil;
import org.xcolab.portlets.proposals.wrappers.PointsTargetProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;

@Controller
@RequestMapping("view")
public class ProposalPointsTabController extends BaseProposalTabController {

    private final ProposalsContext proposalsContext;

    @Autowired
    public ProposalPointsTabController(ProposalsContext proposalsContext) {
        this.proposalsContext = proposalsContext;
    }

    @RequestMapping(params = {"pageToDisplay=proposalDetails_POINTS"})
    public String showProposalDetails(Model model, PortletRequest request) 
            throws PortalException, SystemException {

        setCommonModelAndPageAttributes(request, model, ProposalTab.POINTS);
        Proposal proposal = proposalsContext.getProposal(request);
        Contest contest = proposalsContext.getContest(request);

        PointType contestParentPointType = ProposalsContextUtil.getClients(request).getPointsClient()
                .getPointType(contest.getDefaultParentPointType());

        if (contestParentPointType == null) {
            //there is no point scheme set for this contest, forward to description tab
            return "";
        }

        PointType parentPointType = new PointType(contestParentPointType);

        List<Proposal> subProposals = ProposalClientUtil
                .getSubproposals(proposal.getProposalId(), false);
        List<Proposal> subProposalsWrapped = new ArrayList<>();
        for (Proposal p: subProposals) {
            subProposalsWrapped.add(new Proposal(p));
        }
        //TODO: make this flexible
        PointType pointType = ProposalsContextUtil.getClients(request).getPointsClient().getPointType(9L);
        DistributionStrategy distributionStrategy = DistributionStrategy.valueOf(pointType.getDistributionStrategy());
        ReceiverLimitationStrategy receiverLimitationStrategy = ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy());

        List<PointsTarget> targets = receiverLimitationStrategy.getTargets(proposal, pointType, distributionStrategy);
        List<PointsTargetProposalWrapper> regionalPercentages = new ArrayList<>();
        for (PointsTarget target : targets) {
            regionalPercentages.add(new PointsTargetProposalWrapper(target, 93));
        }

        pointType = ProposalsContextUtil.getClients(request).getPointsClient().getPointType(4L);
        distributionStrategy = DistributionStrategy.valueOf(pointType.getDistributionStrategy());
        receiverLimitationStrategy = ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy());

        targets = receiverLimitationStrategy.getTargets(proposal, pointType, distributionStrategy);
        List<PointsTargetProposalWrapper> basicPercentages = new ArrayList<>();
        for (PointsTarget target : targets) {
            basicPercentages.add(new PointsTargetProposalWrapper(target, 2));
        }

        List<Proposal> linkingProposalsWrapped = new ArrayList<>();
        final List<Proposal> linkingProposals = ProposalsContextUtil.getClients(request).getProposalClient().getLinkingProposals(proposal.getProposalId());
        for (Proposal p : linkingProposals) {
            linkingProposalsWrapped.add(new Proposal(p));
        }

        List<Member> members = ProposalsContextUtil.getClients(request).getProposalClient().getProposalMembers(proposal.getProposalId());

        //this bean will be filled with the user input
        AssignPointsBean assignPointsBean = new AssignPointsBean(proposal.getProposalId());
        assignPointsBean.addAllAssignments(parentPointType, members);

        model.addAttribute("assignPointsBean", assignPointsBean);
        model.addAttribute("pointsToDistribute", contest.getPoints());
        model.addAttribute("pointType", parentPointType);
        model.addAttribute("recursionLevel", 0);
        model.addAttribute("percentageOfTotalPoints", parentPointType.getPercentageOfParent());
        model.addAttribute("subProposals", subProposalsWrapped);
        model.addAttribute("regionalPercentages", regionalPercentages);
        model.addAttribute("basicPercentages", basicPercentages);
        model.addAttribute("members", members);
        model.addAttribute("totalPoints", ProposalsContextUtil.getClients(request).getProposalClient().getProposalMaterializedPoints(proposal.getProposalId()));
        model.addAttribute("proposal", proposal);
        model.addAttribute("contest", contest);
        model.addAttribute("linkingProposals", linkingProposalsWrapped);
        return "proposalPoints";
    }
}
