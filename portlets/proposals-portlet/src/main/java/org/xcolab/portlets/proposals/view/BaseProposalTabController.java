package org.xcolab.portlets.proposals.view;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.xcolab.portlets.proposals.wrappers.ContestPhaseWrapper;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTabWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalTab;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;

public class BaseProposalTabController {
    
    
    protected void findEntitiesAndPopulateModel(
            @RequestParam(value="planId") Long proposalId, 
            @RequestParam Long contestId, 
            @RequestParam(required = false) Long phaseId, 
            Model model) 
    throws PortalException, SystemException {
        
        // populate available tabs
        
        List<ProposalTabWrapper> tabs = new ArrayList<ProposalTabWrapper>();
        
        int i=0; 
        for (ProposalTab tab: ProposalTab.values()) {
            tabs.add(new ProposalTabWrapper(tab, i++));
        }
        
        model.addAttribute("tabs", tabs);
        
        
    }
    
    public class ProposalPhaseContest {
        Proposal proposal;
        Contest contest;
        ContestPhase phase;
        
        public ProposalPhaseContest(Proposal proposal, Contest contest, ContestPhase phase) {
            super();
            this.proposal = proposal;
            this.contest = contest;
            this.phase = phase;
        }
        
        
    }

}
