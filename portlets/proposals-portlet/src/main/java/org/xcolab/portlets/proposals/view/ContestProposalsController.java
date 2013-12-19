package org.xcolab.portlets.proposals.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.commons.beans.SortFilterPage;
import org.xcolab.portlets.proposals.utils.ProposalsColumn;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsSortFilterBean;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ContestProposalsController extends BaseProposalsController {
    
    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=contestProposals")
    public String showContestProposals(RenderRequest request, RenderResponse response,
            final SortFilterPage sortFilterPage, Model model) 
            throws PortalException, SystemException {
        
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        Contest contest = proposalsContext.getContest(request);

        User u = UserLocalServiceUtil.getUser(Long.parseLong(request.getRemoteUser()));
        List<ProposalWrapper> proposals = new ArrayList<ProposalWrapper>();
        for (Proposal proposal: ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
            Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());

            ProposalWrapper proposalWrapper = new ProposalWrapper(proposal, p2p.getVersionTo() == -1 ? proposal.getCurrentVersion() : p2p.getVersionTo(), contest, contestPhase, p2p, u);
            if(proposalWrapper.getVisible())
                proposals.add(proposalWrapper);
        }

        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("proposals", new ProposalsSortFilterBean(proposals, sortFilterPage));
        
        setSeoTexts(request, contest.getContestShortName(), null, contest.getContestDescription());
        
        return "contestProposals";
    }

}


