package org.xcolab.portlets.proposals.utils;

import javax.portlet.PortletRequest;

import org.springframework.stereotype.Component;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;

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
import com.liferay.portal.kernel.util.ParamUtil;

@Component
public class ProposalsContextImpl implements ProposalsContext {
    private static final String PROPOSALS_ATTRIBUTE_PREFIX = "_proposalsProtlet_";
    private static final String CONTEXT_INITIALIZED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contextInitialized";
    private static final String PERMISSIONS_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "permissions";
    private static final String CONTEST_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contest";
    private static final String PROPOSAL_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposals";
    private static final String CONTEST_PHASE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestPhase";
    private static final String PROPOSAL_2_PHASE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposal2phase";
    private static final String REQUEST_PHASE_ID_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "requestPhaseId";
    
    public static final String PROPOSAL_ID_PARAM = "planId";
    public static final String CONTEST_ID_PARAM = "contestId";
    public static final String CONTEST_PHASE_ID_PARAM = "phaseId";
    

    public ProposalsContextImpl() {
    }
    
    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getContest(javax.portlet.PortletRequest)
     */
    @Override
    public Contest getContest(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, CONTEST_ATTRIBUTE, Contest.class);
    }
    
    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getContestPhase(javax.portlet.PortletRequest)
     */
    @Override
    public ContestPhase getContestPhase(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, CONTEST_PHASE_ATTRIBUTE, ContestPhase.class);
    }
    
    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getProposal(javax.portlet.PortletRequest)
     */
    @Override
    public Proposal getProposal(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PROPOSAL_ATTRIBUTE, Proposal.class);        
    }
    

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getPermissions(javax.portlet.PortletRequest)
     */
    @Override
    public ProposalsPermissions getPermissions(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PERMISSIONS_ATTRIBUTE, ProposalsPermissions.class);
    }
    
    @Override
    public Proposal2Phase getProposal2Phase(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PROPOSAL_2_PHASE_ATTRIBUTE, Proposal2Phase.class);        
    }
    
    @Override
    public Long getViewContestPhaseId(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, REQUEST_PHASE_ID_ATTRIBUTE, Long.class);        
    }
    
    private <T> T getAttribute(PortletRequest request, String attributeName, Class<T> clasz) throws PortalException, SystemException {
        Object contextInitialized =  request.getAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
        if (contextInitialized == null) {
            init(request);
        }
        return (T) request.getAttribute(attributeName);
    }
    
    private void init(PortletRequest request) throws PortalException, SystemException {
        final Long proposalId = (Long) ParamUtil.getLong(request, PROPOSAL_ID_PARAM);
        final Long contestId = (Long) ParamUtil.getLong(request, CONTEST_ID_PARAM);
        final Long phaseId = (Long) ParamUtil.getLong(request, CONTEST_PHASE_ID_PARAM);
        
        
        Contest contest = null;
        ContestPhase contestPhase = null;
        Proposal proposal = null;
        Proposal2Phase proposal2Phase = null;
        
        
        
        if (contestId != null && contestId > 0) {
            contest = ContestLocalServiceUtil.getContest(contestId);
            if (phaseId != null && phaseId > 0) {
                contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseId);
            }
            else {
                contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(contest);
            }
            
            if (proposalId != null && proposalId > 0) {
                proposal2Phase = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposalId, contestPhase.getContestPhasePK());
                proposal = ProposalLocalServiceUtil.getProposal(proposalId);
            }
        }
        
        request.setAttribute(PROPOSAL_ATTRIBUTE, proposal);
        request.setAttribute(CONTEST_ATTRIBUTE, contest);
        request.setAttribute(CONTEST_PHASE_ATTRIBUTE, contestPhase);
        request.setAttribute(PROPOSAL_2_PHASE_ATTRIBUTE, proposal2Phase);
        request.setAttribute(PERMISSIONS_ATTRIBUTE, new ProposalsPermissions(request, proposal, contestPhase));
        if (phaseId > 0) {
            request.setAttribute(REQUEST_PHASE_ID_ATTRIBUTE, phaseId);
        }
        
        request.setAttribute(CONTEXT_INITIALIZED_ATTRIBUTE, true);
        
        
        
        
    }
}
