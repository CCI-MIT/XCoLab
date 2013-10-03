package org.xcolab.portlets.proposals.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

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

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

@Controller
@RequestMapping("view")
public class ContestProposalsController {
    
    @Autowired
    private ProposalsContext proposalsContext;

    @RequestMapping(params = "pageToDisplay=contestProposals")
    public String showContestProposals(RenderRequest request, RenderResponse response, 
            @RequestParam Long contestId, @RequestParam(required = false, value="phaseId") Long contestPhaseId,
            final SortFilterPage sortFilterPage, Model model) 
            throws PortalException, SystemException {
        
        ContestPhase contestPhase = proposalsContext.getContestPhase(request);
        Contest contest = proposalsContext.getContest(request);
        
        List<ProposalWrapper> proposals = new ArrayList<ProposalWrapper>();
        for (Proposal proposal: ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
            proposals.add(new ProposalWrapper(proposal, proposal.getCurrentVersion(), contest, contestPhase)); 
        }
        
        if (sortFilterPage != null && StringUtils.isNotBlank(sortFilterPage.getSortColumn())) {
            try {
                final Comparator<ProposalWrapper> proposalsSorter = ProposalsColumn.valueOf(sortFilterPage.getSortColumn()).getComparator();

                Collections.sort(proposals, new Comparator<ProposalWrapper>() {

                    @Override
                    public int compare(ProposalWrapper o1, ProposalWrapper o2) {
                        int ret = 0;
                        ret = proposalsSorter.compare(o1, o2);
                        return sortFilterPage.isSortAscending() ? ret : -ret;
                    }
                });
            }
            catch (Exception e) {
                // ignore
            }
        }

        

        model.addAttribute("sortFilterPage", sortFilterPage);
        model.addAttribute("proposals", proposals);
        
        return "contestProposals";
    }
}
