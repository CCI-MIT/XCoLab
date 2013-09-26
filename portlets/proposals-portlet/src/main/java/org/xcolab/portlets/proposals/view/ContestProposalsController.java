package org.xcolab.portlets.proposals.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.wrappers.ContestPhaseWrapper;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ContestProposalsController {

    @RequestMapping(params = "pageToDisplay=contestProposals")
    public String showContestProposals(@RequestParam Long contestId, 
            @RequestParam(required = false, value="phaseId") Long contestPhaseId, Model model) 
            throws PortalException, SystemException {
        System.out.println(contestId);
        
        Contest contest = ContestLocalServiceUtil.getContest(contestId);
        ContestPhase contestPhase = null;
        if (contestPhaseId != null) {
            contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
            if (contestPhase.getContestPK() != contestId) {
                return "error";
            }
        }
        else {
            contestPhase = ContestLocalServiceUtil.getActivePhase(contest);
            if (contestPhase == null) {
                contestPhase = ContestLocalServiceUtil.getPhases(contest).get(0);
            }
        }
        
        
        
        //List<Proposal> proposals = ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getContestPhasePK());
        List<ProposalWrapper> proposals = new ArrayList<ProposalWrapper>();
        for (Proposal proposal: ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
            proposals.add(new ProposalWrapper(proposal));
        }
        
       
        System.out.println(proposals.size());
        model.addAttribute("proposals", proposals);
        model.addAttribute("contestPhase", new ContestPhaseWrapper(contestPhase));
        model.addAttribute("contest", new ContestWrapper(contest));
        
        return "contestProposals";
    }
}
