package org.xcolab.portlets.proposals.view.action;


import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.service.*;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.mail.MailEngineException;
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
import org.xcolab.portlets.proposals.wrappers.ProposalRatingWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import javax.mail.internet.AddressException;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
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

    @RequestMapping(params = {"action=savePointAssignments"})
    public void savePointAssignments(ActionRequest request, Model model,
                                ActionResponse response, @Valid AssignPointsBean assignPointsBean,
                                BindingResult result)
            throws PortalException, SystemException, ProposalsAuthorizationException, IOException {
        if (result.hasErrors()) {
            return;
        }

        Proposal proposal = proposalsContext.getProposal(request);
        User currentUser = proposalsContext.getUser(request);
        long contestId = proposalsContext.getContest(request).getContestPK();
        long contestPhaseId = proposalsContext.getContestPhase(request).getContestPhasePK();

        //first, delete the existing configuration
        PointsDistributionConfigurationLocalServiceUtil.removeByProposalId(proposal.getProposalId());

        try {
            //custom user assignments
            for (Long pointTypeId : assignPointsBean.getAssignments().keySet()) {
                Map<Long, Integer> assignments = assignPointsBean.get(pointTypeId);

                double sum = 0.0;
                for (Long targetUserId : assignments.keySet()) {
                    double percentage = new Double(assignments.get(targetUserId) != null ? assignments.get(targetUserId) : 0.0) / 100.0;
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
        response.sendRedirect("/web/guest/plans/-/plans/contestId/"+contestId+"/phaseId/"+contestPhaseId+"/planId/"+proposal.getProposalId()+"/tab/POINTS");
    }




}
