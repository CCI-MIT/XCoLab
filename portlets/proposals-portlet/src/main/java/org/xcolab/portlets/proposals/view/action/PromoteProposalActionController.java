package org.xcolab.portlets.proposals.view.action;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestPhaseLocalService;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.exceptions.ProposalsAuthorizationException;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ContestPhaseWrapper;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.io.IOException;

@Controller
@RequestMapping("view")
public class PromoteProposalActionController {

    @Autowired
    private ProposalsContext proposalsContext;
    
    @RequestMapping(params = {"action=promoteProposal"})
    public void handleAction(ActionRequest request, Model model, ActionResponse response,
                             @RequestParam Long contestId,
                             @RequestParam Long contestPhaseId,
                             @RequestParam Long planId
                             )
                    throws Exception {

        ProposalsPermissions proposalsPermissions = proposalsContext.getPermissions(request);
        ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
        if(proposalsPermissions.getCanPromoteProposalToNextPhase(contestPhase)){
            Contest latestProposalContest = ProposalLocalServiceUtil.getLatestProposalContest(planId);
            ContestPhase currentProposalContestPhase = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
            ContestPhase activePhaseForContest = ContestPhaseLocalServiceUtil.getActivePhaseForContest(latestProposalContest);

            ContestPhaseLocalServiceUtil.promoteProposal(planId,
                    activePhaseForContest.getContestPhasePK(),
                    currentProposalContestPhase.getContestPhasePK());

            response.sendRedirect("/web/guest/plans/-/plans/contestId/"+contestId+"/phaseId/"+activePhaseForContest.getContestPhasePK()+"/planId/"+planId);
        } else {
            response.sendRedirect("/web/guest/plans/-/plans/contestId/"+contestId+"/phaseId/"+contestPhaseId+"/planId/"+planId+"/tab/ADMIN");
        }
        /*
        if (proposalsContext.getPermissions(request).getCanAdminAll()) {

            Contest latestProposalContest = ProposalLocalServiceUtil.getLatestProposalContest(planId);

            boolean onlyPromoteIfThisContestIsTheLatestContestOfProposal =
                    (contestId == latestProposalContest.getContestPK());
            if(onlyPromoteIfThisContestIsTheLatestContestOfProposal) {

                ContestPhase currentProposalContestPhase = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
                boolean onlyPromoteIfCurrentContestPhaseIsNotJudged =
                        currentProposalContestPhase.getFellowScreeningActive();
                if(!onlyPromoteIfCurrentContestPhaseIsNotJudged) {

                    ContestPhase activePhaseForContest = ContestPhaseLocalServiceUtil.getActivePhaseForContest(latestProposalContest);
                    boolean onlyPromoteIfThisIsNotTheLatestContestPhaseInContest =
                            currentProposalContestPhase.equals(activePhaseForContest);
                    if (!onlyPromoteIfThisIsNotTheLatestContestPhaseInContest) {
                        ContestPhaseLocalServiceUtil.promoteProposal(planId,
                                activePhaseForContest.getContestPhasePK(),
                                currentProposalContestPhase.getContestPhasePK());

                        response.sendRedirect("/web/guest/plans/-/plans/contestId/"+contestId+"/phaseId/"+activePhaseForContest.getContestPhasePK()+"/planId/"+planId);
                    }
                }
            }
        }
        else {
            throw new ProposalsAuthorizationException("User isn't allowed to promote proposal ");
        }*/
    }

}
