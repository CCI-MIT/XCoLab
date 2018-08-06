package org.xcolab.view.pages.proposals.view.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.PointsClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfiguration;
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

    private void initializePercentageModifiers(PointType pointType) {
        this.pointTypePercentageModifiers.put(pointType.getId(), pointType.getPercentageOfTotal());
        for (PointType p : pointType.getChildren()) {
            this.initializePercentageModifiers(p);
        }
    }

    @PostMapping({"c/{proposalUrlString}/{proposalId}/tab/POINTS/savePointAssignments",
            "phase/{phaseId}/{proposalUrlString}/{proposalId}/tab/POINTS/savePointAssignments"})
    public void savePointAssignments(HttpServletRequest request, HttpServletResponse response,
            Model model, ProposalContext proposalContext, Member currentMember,
            @Valid AssignPointsBean assignPointsBean, BindingResult result)
            throws IOException {

        final Proposal proposal = proposalContext.getProposal();
        final Contest contest = proposalContext.getContest();
        final ContestPhase contestPhase = proposalContext.getContestPhase();

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
            PointType contestRootPointType = PointsClientUtil

                    .getPointType(contest.getDefaultParentPointType());

            //calculate the percentage multiplicator for each pointtype
            this.initializePercentageModifiers(new PointType(contestRootPointType));

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
                    PointsDistributionConfiguration pointsDistributionConfiguration =
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
