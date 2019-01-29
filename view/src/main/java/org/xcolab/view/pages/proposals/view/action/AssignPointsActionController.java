package org.xcolab.view.pages.proposals.view.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.IPointsClient;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.view.pages.proposals.requests.AssignPointsBean;
import org.xcolab.view.pages.proposals.tabs.ProposalTab;
import org.xcolab.view.pages.proposals.utils.context.ProposalContext;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/contests/{contestYear}/{contestUrlName}")
public class AssignPointsActionController {

    private final Map<Long, Double> pointTypePercentageModifiers = new HashMap<>();

    @Autowired
    private IPointsClient pointsClient;

    private void initializePercentageModifiers(PointTypeWrapper pointType) {
        this.pointTypePercentageModifiers.put(pointType.getId(), pointType.getPercentageOfTotal());
        for (PointTypeWrapper p : pointType.getChildren()) {
            this.initializePercentageModifiers(p);
        }
    }

    @PostMapping({"c/{proposalUrlString}/{proposalId}/tab/POINTS/savePointAssignments",
            "phase/{phaseId}/{proposalUrlString}/{proposalId}/tab/POINTS/savePointAssignments"})
    public void savePointAssignments(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, UserWrapper currentMember,
            @Valid AssignPointsBean assignPointsBean, BindingResult result)
            throws IOException {

        final ProposalWrapper proposal = proposalContext.getProposal();
        final ContestWrapper contest = proposalContext.getContest();
        final ContestPhaseWrapper contestPhase = proposalContext.getContestPhase();

        final String redirectUrl =
                proposal.getProposalLinkUrl(contest, contestPhase.getId())
                        + "/tab/POINTS";
        if (result.hasErrors()) {
            response.sendRedirect(redirectUrl);
            return;
        }

        // Security handling
        if (!ProposalTab.POINTS.getCanEdit(proposalContext)) {
            response.sendRedirect(redirectUrl);
            return;
        }

        //first, delete the existing configuration
        proposalContext.getClients().getPointsClient()
                .deletePointsDistributionConfigurationByProposalId(proposal.getId());

        try {
            PointTypeWrapper contestRootPointType = pointsClient
                    .getPointType(contest.getDefaultParentPointType());

            //calculate the percentage multiplicator for each pointtype
            this.initializePercentageModifiers(new PointTypeWrapper(contestRootPointType));

            //custom user assignments
            for (Long pointTypeId : assignPointsBean.getAssignmentsByUserIdByPointTypeId()
                    .keySet()) {
                Map<Long, Double> assignments =
                        assignPointsBean.getAssignmentsByUserId(pointTypeId);

                double sum = 0.0;
                for (Map.Entry<Long, Double> entry : assignments.entrySet()) {
                    double percentage = entry.getValue() != null ? entry.getValue() : 0.0;
                    //round and take absolute value
                    percentage = Math.round(Math.abs(percentage) * 100) / 100.0d;
                    //calculate relative percentage
                    percentage =
                            percentage * (1.0 / (this.pointTypePercentageModifiers.get(pointTypeId)
                                    * 100.0));
                    sum += percentage;
                    //round to four decimals
                    percentage = (double) Math.round(percentage * 10000) / 10000;
                    IPointsDistributionConfiguration pointsDistributionConfiguration =
                            new PointsDistributionConfiguration();
                    pointsDistributionConfiguration.setProposalId(proposal.getId());
                    pointsDistributionConfiguration.setPointTypeId(pointTypeId);
                    pointsDistributionConfiguration.setTargetUserId(entry.getKey());
                    pointsDistributionConfiguration.setTargetSubProposalId(null);
                    pointsDistributionConfiguration.setPercentage(percentage);
                    pointsDistributionConfiguration.setAuthorUserId(currentMember.getId());

                    proposalContext.getClients().getPointsClient()
                            .createPointsDistributionConfiguration(pointsDistributionConfiguration);

                }
                //round to two decimals
                sum = Math.round(sum * 100) / 100.0d;
                if (Math.abs(sum - 1.0) > 0.0001) {
                    throw new IllegalArgumentException(
                            "Error while adding PointsDistributionConfiguration: The sum of "
                                    + "distributed percentages do not sum up to 1: "
                                    + sum);
                }
            }
        } catch (IllegalArgumentException e) {
            //in case a (validation) error occurs, we simply delete all created configurations.
            //since we do client-side validations, this state will not be reached by regular uses
            // of the UI.
            proposalContext.getClients().getPointsClient()
                    .deletePointsDistributionConfigurationByProposalId(proposal.getId());
            throw e;
        }

        response.sendRedirect(redirectUrl);
    }
}
