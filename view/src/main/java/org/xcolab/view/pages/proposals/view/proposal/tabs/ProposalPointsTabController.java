package org.xcolab.view.pages.proposals.view.proposal.tabs;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.proposals.PointsClient;
import org.xcolab.client.proposals.ProposalClient;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.enums.points.DistributionStrategy;
import org.xcolab.client.proposals.enums.points.PointsTarget;
import org.xcolab.client.proposals.enums.points.ReceiverLimitationStrategy;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.view.errors.AccessDeniedPage;
import org.xcolab.view.errors.ErrorText;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.requests.AssignPointsBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;
import org.xcolab.view.pages.proposals.wrappers.PointsTargetProposalWrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class ProposalPointsTabController extends BaseProposalTabController {

    @GetMapping(value = "c/{proposalUrlString}/{proposalId}", params = "tab=POINTS")
    public String showProposalDetails(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember) {

        final ProposalsPermissions permissions = proposalContext.getPermissions();
        if (!permissions.getCanView()) {
            return new AccessDeniedPage(currentMember).toViewName(response);
        }

        final ClientHelper clients = proposalContext.getClients();
        final PointsClient pointsClient = clients.getPointsClient();
        final ProposalClient proposalClient = clients.getProposalClient();

        Proposal proposal = proposalContext.getProposal();
        Contest contest = proposalContext.getContest();

        final String lang = LocaleContextHolder.getLocale().getLanguage();

        PointType contestParentPointType = pointsClient
                .getPointType(contest.getDefaultParentPointType());

        if (!ConfigurationAttributeKey.POINTS_IS_ACTIVE.get() || contestParentPointType == null) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            return ErrorText.PAGE_NOT_FOUND.flashAndReturnView(request);
        }

        setCommonModelAndPageAttributes(request, model, proposalContext, ProposalTab.POINTS);

        List<Proposal> subProposalsWrapped = ProposalClientUtil
                .getSubproposals(proposal.getId(), false);

        //TODO COLAB-2597: make this flexible
        PointType pointType = pointsClient.getPointType(9L);
        DistributionStrategy distributionStrategy = DistributionStrategy.valueOf(pointType.getDistributionStrategy());
        ReceiverLimitationStrategy receiverLimitationStrategy = ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy());

        List<PointsTarget> targets = receiverLimitationStrategy.getTargets(proposal, pointType, distributionStrategy);
        List<PointsTargetProposalWrapper> regionalPercentages = new ArrayList<>();
        for (PointsTarget target : targets) {
            regionalPercentages.add(new PointsTargetProposalWrapper(target, 93L));
        }

        pointType = pointsClient.getPointType(4L);
        distributionStrategy = DistributionStrategy.valueOf(pointType.getDistributionStrategy());
        receiverLimitationStrategy = ReceiverLimitationStrategy.valueOf(pointType.getReceiverLimitationStrategy());

        targets = receiverLimitationStrategy.getTargets(proposal, pointType, distributionStrategy);
        List<PointsTargetProposalWrapper> basicPercentages = new ArrayList<>();
        for (PointsTarget target : targets) {
            basicPercentages.add(new PointsTargetProposalWrapper(target, 2L));
        }

        final List<Proposal> linkingProposalsWrapped = proposalClient.getLinkingProposals(proposal.getId());

        List<UserWrapper> members = proposalClient.getProposalMembers(proposal.getId());

        //this bean will be filled with the user input
        AssignPointsBean assignPointsBean = new AssignPointsBean(proposal.getId());
        assignPointsBean.addAllAssignments((contestParentPointType), members);

        model.addAttribute("pointsHelpText", ConfigurationAttributeKey.POINTS_HELP_TEXT.get(lang));
        model.addAttribute("assignPointsBean", assignPointsBean);
        model.addAttribute("pointsToDistribute", contest.getPoints());
        model.addAttribute("pointType", (contestParentPointType));
        model.addAttribute("recursionLevel", 0);
        model.addAttribute("percentageOfTotalPoints", contestParentPointType.getPercentageOfParent());
        model.addAttribute("subProposals", subProposalsWrapped);
        model.addAttribute("regionalPercentages", regionalPercentages);
        model.addAttribute("basicPercentages", basicPercentages);
        model.addAttribute("members", members);
        model.addAttribute("totalPoints", proposalClient.getProposalMaterializedPoints(proposal.getId()));
        model.addAttribute("proposal", proposal);
        model.addAttribute("contest", contest);
        model.addAttribute("linkingProposals", linkingProposalsWrapped);

        return "/proposals/proposalPoints";
    }
}
