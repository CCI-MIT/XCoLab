package org.xcolab.portlets.proposals.view.interceptors;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.ProposalsContext;
import org.xcolab.portlets.proposals.wrappers.ContestPhaseWrapper;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;

public class PopulateContextInterceptor extends HandlerInterceptorAdapter {
    
    private static final String MODEL_ATTRIBUTE_PROPOSALS_PERMISSIONS = "proposalsPermissions";
    private static final String MODEL_ATTRIBUTE_CONTEST_PHASE = "contestPhase";
    private static final String MODEL_ATTRIBUTE_PROPOSAL = "proposal";
    private static final String MODEL_ATTRIBUTE_CONTEST = "contest";
    @Autowired
    private ProposalsContext proposalsContext;

    
    @Override
    public void postHandleRender(RenderRequest request, RenderResponse response, Object handler,
            org.springframework.web.portlet.ModelAndView modelAndView) throws Exception {
        super.postHandleRender(request, response, handler, modelAndView);
        
        if (modelAndView != null) {
            Contest contest = proposalsContext.getContest(request);
            ContestPhase contestPhase = proposalsContext.getContestPhase(request);
            Proposal proposal = proposalsContext.getProposal(request);
            ProposalsPermissions permissions = proposalsContext.getPermissions(request);
            Proposal2Phase proposal2Phase = proposalsContext.getProposal2Phase(request);
            
            
            if (contest != null) {
                modelAndView.addObject(MODEL_ATTRIBUTE_CONTEST, new ContestWrapper(contest));
                
                if (contestPhase != null) {
                    modelAndView.addObject(MODEL_ATTRIBUTE_CONTEST_PHASE, new ContestPhaseWrapper(contestPhase));
                    
                    if (proposal != null) {
                        modelAndView.addObject(MODEL_ATTRIBUTE_PROPOSAL, 
                                new ProposalWrapper(proposal, proposal2Phase != null && proposal2Phase.getVersionTo() > 0 ? 
                                        proposal2Phase.getVersionTo() : proposal.getCurrentVersion()));
                   
                    }
                }
            }
            
            modelAndView.addObject(MODEL_ATTRIBUTE_PROPOSALS_PERMISSIONS, permissions);
            
            
        }
    }
}
