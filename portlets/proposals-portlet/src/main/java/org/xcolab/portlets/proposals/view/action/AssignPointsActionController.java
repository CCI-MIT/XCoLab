package org.xcolab.portlets.proposals.view.action;



import com.ext.portlet.model.PointType;

import com.ext.portlet.service.PointTypeLocalServiceUtil;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.pojo.PointsDistributionConfiguration;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.requests.AssignPointsBean;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.PointTypeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("view")
public class AssignPointsActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    private final Map<Long, Double> pointTypePercentageModifiers = new HashMap<>();

    private void initializePercentageModifiers(PointTypeWrapper pointType) {
        this.pointTypePercentageModifiers.put(pointType.getId(), pointType.getPercentageOfTotal());
        for (PointTypeWrapper p: pointType.getChildren()) {
            this.initializePercentageModifiers(p);
        }
    }

    @RequestMapping(params = {"action=savePointAssignments"})
    public void savePointAssignments(ActionRequest request, Model model,
                                ActionResponse response, @Valid AssignPointsBean assignPointsBean,
                                BindingResult result, PortletRequest portletRequest)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        final User currentUser = proposalsContext.getUser(request);
        final Proposal proposal = proposalsContext.getProposal(request);
        final Contest contest = proposalsContext.getContest(request);
        final ContestPhase contestPhase = proposalsContext.getContestPhase(request);

        if (result.hasErrors()) {
            response.sendRedirect(proposal.getProposalLinkUrl(contest, contestPhase.getContestPhasePK()) + "/tab/POINTS");
            return;
        }

        // Security handling
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (!ProposalTab.POINTS.getCanEdit(permissions, proposalsContext, portletRequest)) {
            response.sendRedirect(proposal.getProposalLinkUrl(contest, contestPhase.getContestPhasePK()) + "/tab/POINTS");
            return;
        }

        //first, delete the existing configuration
        ProposalsClient.deletePointsDistributionConfigurationByProposalId(proposal.getProposalId());

        try {
            PointType contestRootPointType = PointTypeLocalServiceUtil.fetchPointType(contest.getDefaultParentPointType());

            //calculate the percentage multiplicator for each pointtype
            this.initializePercentageModifiers(new PointTypeWrapper(contestRootPointType));

            //custom user assignments
            for (Long pointTypeId : assignPointsBean.getAssignmentsByUserIdByPointTypeId().keySet()) {
                Map<Long, Double> assignments = assignPointsBean.getAssignmentsByUserId(pointTypeId);

                double sum = 0.0;
                for (Map.Entry<Long, Double> entry : assignments.entrySet()) {
                    double percentage = entry.getValue() != null ? entry.getValue() : 0.0;
                    //round and take absolute value
                    percentage = Math.round(Math.abs(percentage)*100)/100.0d;
                    //calculate relative percentage
                    percentage = percentage * (1.0/(this.pointTypePercentageModifiers.get(pointTypeId)*100.0));
                    sum += percentage;
                    //round to four decimals
                    percentage = (double)Math.round(percentage * 10000) / 10000;
                    PointsDistributionConfiguration pointsDistributionConfiguration = new PointsDistributionConfiguration();
                    pointsDistributionConfiguration.setProposalId(proposal.getProposalId());
                    pointsDistributionConfiguration.setPointTypeId(pointTypeId);
                    pointsDistributionConfiguration.setTargetUserId(entry.getKey());
                    pointsDistributionConfiguration.setTargetSubProposalId(null);
                    pointsDistributionConfiguration.setPercentage(percentage);
                    pointsDistributionConfiguration.setCreator(currentUser.getUserId());

                    ProposalsClient.createPointsDistributionConfiguration(pointsDistributionConfiguration);

                }
                //round to two decimals
                sum = Math.round(sum * 100) / 100.0d;
                if (Math.abs(sum - 1.0) > 0.0001) {
                    throw new IllegalArgumentException("Error while adding PointsDistributionConfiguration: The sum of distributed percentages do not sum up to 1: " + sum);
                }
            }
        } catch (SystemException | IllegalArgumentException e) {
            //in case a (validation) error occurs, we simply delete all created configurations.
            //since we do client-side validations, this state will not be reached by regular uses of the UI.
            ProposalsClient.deletePointsDistributionConfigurationByProposalId(proposal.getProposalId());
            throw e;
        }

        response.sendRedirect(proposal.getProposalLinkUrl(contest, contestPhase.getContestPhasePK()) + "/tab/POINTS");
    }
}
