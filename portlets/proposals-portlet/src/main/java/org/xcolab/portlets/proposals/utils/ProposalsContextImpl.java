package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.NoSuchProposal2PhaseException;
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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.springframework.stereotype.Component;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.wrappers.*;

import javax.portlet.PortletRequest;

@Component
public class ProposalsContextImpl implements ProposalsContext {
    private static final String PROPOSALS_ATTRIBUTE_PREFIX = "_proposalsProtlet_";
    private static final String CONTEXT_INITIALIZED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contextInitialized";
    private static final String PERMISSIONS_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "permissions";
    private static final String CONTEST_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contest";
    private static final String PROPOSAL_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposals";
    private static final String CONTEST_PHASE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestPhase";
    private static final String USER_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "user";
    private static final String PROPOSALS_PREFERENCES_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "preferences";
    
    private static final String PROPOSAL_2_PHASE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposal2phase";
    private static final String REQUEST_PHASE_ID_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "requestPhaseId";
    private static final String CONTEST_WRAPPED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestWrapped";
    private static final String CONTEST_PHASE_WRAPPED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestPhaseWrapped";
    private static final String PROPOSAL_WRAPPED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposalWrapped";
    
    public static final String PROPOSAL_ID_PARAM = "planId";
    public static final String CONTEST_ID_PARAM = "contestId";
    public static final String CONTEST_PHASE_ID_PARAM = "phaseId";
    public static final String VERSION_PARAM = "version";

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
    
    @Override
    public ProposalWrapper getProposalWrapped(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PROPOSAL_WRAPPED_ATTRIBUTE, ProposalWrapper.class);
    }
    
    @Override
    public ContestWrapper getContestWrapped(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, CONTEST_WRAPPED_ATTRIBUTE, ContestWrapper.class);
    }
    
    @Override
    public ContestPhaseWrapper getContestPhaseWrapped(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, CONTEST_PHASE_WRAPPED_ATTRIBUTE, ContestPhaseWrapper.class);
    }

    @Override
    public ProposalsPreferencesWrapper getProposalsPreferences(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PROPOSALS_PREFERENCES_ATTRIBUTE, ProposalsPreferencesWrapper.class);
    }
    
    @Override
    public User getUser(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, USER_ATTRIBUTE, User.class);
    }
    
    @Override
    public void invalidateContext(PortletRequest request) {
        request.removeAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
    }
    
    private <T> T getAttribute(PortletRequest request, String attributeName, Class<T> clazz) throws PortalException, SystemException {
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
        final Integer version = (Integer) ParamUtil.getInteger(request, VERSION_PARAM);
        
        Contest contest = null;
        ContestPhase contestPhase = null;
        Proposal proposal = null;
        Proposal2Phase proposal2Phase = null;

        if (contestId != null && contestId > 0) {
            contest = ContestLocalServiceUtil.getContest(contestId);

            if (phaseId != null && phaseId > 0) {
                contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseId);
            } else {
                //get the last phase of this contest
                contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(contest);
            }
            
            if (proposalId != null && proposalId > 0) {
                try {
                    proposal2Phase = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposalId, contestPhase.getContestPhasePK());
                }
                catch (NoSuchProposal2PhaseException e) {
                    // there is no connection between proposal and selected contest phase, check if phaseId was given by the user, if it was
                    // rethrow the exception, if it wasn't check if there is a connection and any phase for given contest if there is such connection
                    // fetch most recent one
                    // if proposal is beeing moved ignore missing p2p mapping
                    if (request.getParameter("move")==null){
                        ContestPhase mostRecentPhase = null;
                        if (phaseId == null || phaseId <= 0) {
                            _log.info("Can't find association between proposal " + proposalId + " and phase " + contestPhase.getContestPhasePK());
                            for (Long contestPhaseId: Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(proposalId)) {
                                ContestPhase cp = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
                                if (cp.getContestPK() == contest.getContestPK()) {
                                    // we have a candidate
                                    if (mostRecentPhase == null || mostRecentPhase.compareTo(cp) < 0) {
                                        mostRecentPhase = cp;
                                    }
                                }
                            }
                            if (mostRecentPhase == null) {
                                throw e;
                            }
                            proposal2Phase = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposalId, mostRecentPhase.getContestPhasePK());
                            contestPhase = mostRecentPhase;
                        } else {
                            throw e;
                        }
                    }
                }
                proposal = ProposalLocalServiceUtil.getProposal(proposalId);
            }
        }
        
        if (contest != null) {
            request.setAttribute(CONTEST_WRAPPED_ATTRIBUTE, new ContestWrapper(contest));
            
            if (contestPhase != null) {
                request.setAttribute(CONTEST_PHASE_WRAPPED_ATTRIBUTE, new ContestPhaseWrapper(contestPhase));
                
                if (proposal != null) {
                    ProposalWrapper proposalWrapper = null;
                    User u = request.getRemoteUser() != null ? UserLocalServiceUtil.getUser(Long.parseLong(request.getRemoteUser())) : null;

                    if (version != null && version > 0) {
                        if (u != null && UserLocalServiceUtil.hasRoleUser(MemberRole.JUDGES.getRoleId(),u.getUserId())) proposalWrapper = new ProposalJudgeWrapper(proposal, version, contest, contestPhase, proposal2Phase, u);
                        else proposalWrapper = new ProposalWrapper(proposal, version, contest, contestPhase, proposal2Phase);
                    }
                    else {
                        if (u != null && UserLocalServiceUtil.hasRoleUser(MemberRole.JUDGES.getRoleId(),u.getUserId())) proposalWrapper = new ProposalJudgeWrapper(proposal, proposal2Phase != null && proposal2Phase.getVersionTo() > 0 ?
                                        proposal2Phase.getVersionTo() : proposal.getCurrentVersion(), contest, contestPhase, proposal2Phase, u);
                        else proposalWrapper = new ProposalWrapper(proposal, proposal2Phase != null && proposal2Phase.getVersionTo() > 0 ?
                                proposal2Phase.getVersionTo() : proposal.getCurrentVersion(), contest, contestPhase, proposal2Phase);
                    }
                    request.setAttribute(PROPOSAL_WRAPPED_ATTRIBUTE, proposalWrapper);
                }
            }
        }
        

        request.setAttribute(PROPOSAL_ATTRIBUTE, proposal);
        request.setAttribute(CONTEST_ATTRIBUTE, contest);
        request.setAttribute(CONTEST_PHASE_ATTRIBUTE, contestPhase);
        request.setAttribute(PROPOSAL_2_PHASE_ATTRIBUTE, proposal2Phase);
        request.setAttribute(PERMISSIONS_ATTRIBUTE, new ProposalsPermissions(request, proposal, contestPhase));
        request.setAttribute(PROPOSALS_PREFERENCES_ATTRIBUTE, new ProposalsPreferencesWrapper(request));
        
        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        request.setAttribute(USER_ATTRIBUTE, themeDisplay.getUser());
        if (phaseId > 0) {
            request.setAttribute(REQUEST_PHASE_ID_ATTRIBUTE, phaseId);
        }
        
        request.setAttribute(CONTEXT_INITIALIZED_ATTRIBUTE, true);
        
        
        
        
    }
    
    private final static Log _log = LogFactoryUtil.getLog(ProposalsContextImpl.class);
}
