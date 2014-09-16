package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.model.*;
import com.ext.portlet.service.*;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.requests.*;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.PointTypeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("view")
public class AssignPointsActionController {

    @Autowired
    private ProposalsContext proposalsContext;

    private Map<Long, Double> pointTypePercentageModifiers = new HashMap<Long, Double>();

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
        if (result.hasErrors()) {
            return;
        }

        // Security handling
        ProposalsPermissions permissions = proposalsContext.getPermissions(request);
        if (!ProposalTab.POINTS.getCanEdit(permissions, proposalsContext, portletRequest)) {
            return;
        }


        Proposal proposal = proposalsContext.getProposal(request);
        User currentUser = proposalsContext.getUser(request);
        Contest contest = proposalsContext.getContest(request);
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

        //first, delete the existing configuration
        PointsDistributionConfigurationLocalServiceUtil.removeByProposalId(proposal.getProposalId());

        try {
            PointType contestRootPointType = PointTypeLocalServiceUtil.fetchPointType(contest.getDefaultParentPointType());

            //calculate the percentage multiplicator for each pointtype
            this.initializePercentageModifiers(new PointTypeWrapper(contestRootPointType));

            //custom user assignments
            for (Long pointTypeId : assignPointsBean.getAssignments().keySet()) {
                Map<Long, Double> assignments = assignPointsBean.get(pointTypeId);

                double sum = 0.0;
                for (Long targetUserId : assignments.keySet()) {
                    double percentage = new Double(assignments.get(targetUserId) != null ? assignments.get(targetUserId) : 0.0);
                    //round and take absolute value
                    percentage = Math.round(Math.abs(percentage)*100)/100.0d;
                    //calculate relative percentage
                    percentage = percentage * (1.0/(this.pointTypePercentageModifiers.get(pointTypeId)*100.0));
                    //round to two decimals
                    sum += percentage;
                    PointsDistributionConfigurationLocalServiceUtil.addDistributionConfiguration(
                            proposal.getProposalId(),
                            pointTypeId,
                            targetUserId,
                            null,
                            percentage,
                            currentUser.getUserId()
                    );
                }
                //round to two decimals
                sum = Math.round(sum*100)/100.0d;
                if (sum != 1.0) {
                    throw new IllegalArgumentException("Error while adding PointsDistributionConfiguration: The sum of distributed percentages do not sum up to 1: " + sum);
                }
            }
        } catch (Exception e) {
            //in case a (validation) error occurs, we simply delete all created configurations.
            //since we do client-side validations, this state will not be reached by regular uses of the UI.
            PointsDistributionConfigurationLocalServiceUtil.removeByProposalId(proposal.getProposalId());
            throw e;
        }
        response.sendRedirect("/web/guest/plans/-/plans/contestId/"+contest.getContestPK()+"/phaseId/"+contestPhaseId+"/planId/"+proposal.getProposalId()+"/tab/POINTS");
    }




}
