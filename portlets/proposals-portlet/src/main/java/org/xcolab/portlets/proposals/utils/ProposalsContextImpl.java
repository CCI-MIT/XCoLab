package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.NoSuchContestPhaseException;
import com.ext.portlet.NoSuchProposal2PhaseException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.springframework.stereotype.Component;
import org.xcolab.enums.MemberRole;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.portlets.proposals.exceptions.ProposalIdOrContestIdInvalidException;
import org.xcolab.portlets.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.wrappers.ContestPhaseWrapper;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;

import javax.portlet.PortletRequest;

@Component
public class ProposalsContextImpl implements ProposalsContext {
    private static final String PROPOSALS_ATTRIBUTE_PREFIX = "_proposalsProtlet_";
    private static final String CONTEXT_INITIALIZED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contextInitialized";
    private static final String PERMISSIONS_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "permissions";
    private static final String DISPLAY_PERMISSIONS_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "displayPermissions";
    private static final String CONTEST_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contest";
    private static final String PROPOSAL_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposals";
    private static final String CONTEST_PHASE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestPhase";
    private static final String USER_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "user";
    private static final String PROPOSALS_PREFERENCES_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "preferences";
    
    private static final String PROPOSAL_2_PHASE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposal2phase";
    private static final String REQUEST_PHASE_ID_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "requestPhaseId";
    private static final String CONTEST_WRAPPED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestWrapped";
    private static final String CONTEST_PHASE_WRAPPED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestPhaseWrapped";
    private static final String CONTEST_TYPE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestType";
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
        return getAttribute(request, CONTEST_ATTRIBUTE);
    }
    
    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getContestPhase(javax.portlet.PortletRequest)
     */
    @Override
    public ContestPhase getContestPhase(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, CONTEST_PHASE_ATTRIBUTE);
    }
    
    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getProposal(javax.portlet.PortletRequest)
     */
    @Override
    public Proposal getProposal(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PROPOSAL_ATTRIBUTE);
    }


    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getPermissions(javax.portlet.PortletRequest)
     */
    @Override
    public ProposalsPermissions getPermissions(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PERMISSIONS_ATTRIBUTE);
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getDisplayPermissions(javax.portlet.PortletRequest)
     */
    @Override
    public ProposalsDisplayPermissions getDisplayPermissions(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, DISPLAY_PERMISSIONS_ATTRIBUTE);
    }
    
    @Override
    public Proposal2Phase getProposal2Phase(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PROPOSAL_2_PHASE_ATTRIBUTE);
    }
    
    @Override
    public Long getViewContestPhaseId(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, REQUEST_PHASE_ID_ATTRIBUTE);
    }
    
    @Override
    public ProposalWrapper getProposalWrapped(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PROPOSAL_WRAPPED_ATTRIBUTE);
    }
    
    @Override
    public ContestWrapper getContestWrapped(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, CONTEST_WRAPPED_ATTRIBUTE);
    }
    
    @Override
    public ContestPhaseWrapper getContestPhaseWrapped(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, CONTEST_PHASE_WRAPPED_ATTRIBUTE);
    }

    @Override
    public ContestType getContestType(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, CONTEST_TYPE_ATTRIBUTE);
    }

    @Override
    public ProposalsPreferencesWrapper getProposalsPreferences(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, PROPOSALS_PREFERENCES_ATTRIBUTE);
    }
    
    @Override
    public User getUser(PortletRequest request) throws PortalException, SystemException {
        return getAttribute(request, USER_ATTRIBUTE);
    }
    
    @Override
    public void invalidateContext(PortletRequest request) {
        request.removeAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
    }
    
    private <T> T getAttribute(PortletRequest request, String attributeName) throws PortalException, SystemException {
        Object contextInitialized =  request.getAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
        if (contextInitialized == null) {
            init(request);
        }
        return (T) request.getAttribute(attributeName);
    }

    private void init(PortletRequest request) throws PortalException, SystemException {
        final long proposalId = ParamUtil.getLong(request, PROPOSAL_ID_PARAM);
        final long contestId = ParamUtil.getLong(request, CONTEST_ID_PARAM);
        final long phaseId = ParamUtil.getLong(request, CONTEST_PHASE_ID_PARAM);
        final int version = ParamUtil.getInteger(request, VERSION_PARAM);

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        String currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent();
        User currentUser = null;

        try {
            currentUser = PortalUtil.getUser(request);
        } catch (SystemException | PortalException e) {
            // No user is logged in
        }

        Contest contest = null;
        ContestPhase contestPhase = null;
        Proposal proposal = null;
        Proposal2Phase proposal2Phase = null;
        if (contestId > 0) {
            try {
                contest = ContestLocalServiceUtil.getContest(contestId);
            } catch (NoSuchContestException e) {
                handleAccessedInvalidUrlIdInUrl(currentUser, currentUrl);
            }

            if (phaseId > 0) {
                try {
                    contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(phaseId);
                } catch (NoSuchContestPhaseException e){
                    contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(contest);
                }
            } else {
                //get the last phase of this contest
                contestPhase = ContestLocalServiceUtil.getActiveOrLastPhase(contest);
            }
            
            if (proposalId > 0) {
                try {
                      proposal2Phase = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposalId, contestPhase.getContestPhasePK());
                }
                catch (NoSuchProposal2PhaseException e) {
                    // there is no connection between proposal and selected contest phase, check if phaseId was given by the user, if it was
                    // rethrow the exception, if it wasn't check if there is a connection and any phase for given contest if there is such connection
                    // fetch most recent one
                    // if proposal is being moved ignore missing p2p mapping
                    if (request.getParameter("move")==null){
                        if (phaseId <= 0) {
                            _log.info("Can't find association between proposal " + proposalId + " and phase " + contestPhase.getContestPhasePK());
                            ContestPhase mostRecentPhaseInRequestedContest = null;
                            ContestPhase mostRecentPhaseInOtherContest = null;
                            for (Long contestPhaseId: Proposal2PhaseLocalServiceUtil.getContestPhasesForProposal(proposalId)) {

                                ContestPhase cp = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
                                boolean isContestPhaseAssociatedWithRequestedContest = contest != null && cp.getContestPK() == contest.getContestPK();
                                if (isContestPhaseAssociatedWithRequestedContest) {
                                    if (mostRecentPhaseInRequestedContest == null || mostRecentPhaseInRequestedContest.compareTo(cp) < 0) {
                                        mostRecentPhaseInRequestedContest = cp;
                                    }
                                } else {
                                    if (mostRecentPhaseInOtherContest == null || mostRecentPhaseInOtherContest.compareTo(cp) < 0) {
                                        mostRecentPhaseInOtherContest = cp;
                                    }
                                }
                            }
                            if (mostRecentPhaseInRequestedContest == null) {

                                if(mostRecentPhaseInOtherContest == null){
                                    handleAccessedInvalidUrlIdInUrl(currentUser, currentUrl);
                                } else {
                                    contestPhase = mostRecentPhaseInOtherContest;
                                    // TODO show user list if several contest are available
                                    contest = ContestLocalServiceUtil.getContest(contestPhase.getContestPK());
                                }

                            } else {
                                contestPhase = mostRecentPhaseInRequestedContest;
                            }
                            proposal2Phase = Proposal2PhaseLocalServiceUtil.
                                    getByProposalIdContestPhaseId(proposalId, contestPhase.getContestPhasePK());
                        } else {
                            _log.warn("Couldn't find alternative association between proposal " + proposalId + " and phase " + contestPhase.getContestPhasePK());
                            throw e;
                        }
                    }
                }
                proposal = ProposalLocalServiceUtil.getProposal(proposalId);
            }
        }
        ContestType contestType = null;
        if (contest != null) {
            request.setAttribute(CONTEST_WRAPPED_ATTRIBUTE, new ContestWrapper(contest));
            if (contestPhase != null) {
                request.setAttribute(CONTEST_PHASE_WRAPPED_ATTRIBUTE, new ContestPhaseWrapper(contestPhase));

                if (proposal == null) {
                    contestType = ContestTypeLocalServiceUtil.fetchContestType(contest.getContestTypeId());
                } else {
                    contestType = ContestTypeLocalServiceUtil.getContestTypeFromProposalId(proposal.getProposalId());
                    ProposalWrapper proposalWrapper;
                    User u = request.getRemoteUser() != null ? UserLocalServiceUtil.getUser(Long.parseLong(request.getRemoteUser())) : null;

                    if (version > 0) {
                        if (u != null && UserLocalServiceUtil.hasRoleUser(MemberRole.JUDGES.getRoleId(),u.getUserId())) {
                            proposalWrapper = new ProposalJudgeWrapper(proposal, version, contest, contestPhase, proposal2Phase, u);
                        } else {
                            proposalWrapper = new ProposalWrapper(proposal, version, contest, contestPhase, proposal2Phase);
                        }
                    } else {
                        final boolean hasVersionTo = proposal2Phase != null && proposal2Phase.getVersionTo() > 0;
                        final int localVersion = hasVersionTo ? proposal2Phase.getVersionTo() : proposal.getCurrentVersion();

                        if (u != null && UserLocalServiceUtil.hasRoleUser(MemberRole.JUDGES.getRoleId(),u.getUserId())) {
                            proposalWrapper = new ProposalJudgeWrapper(proposal, localVersion, contest, contestPhase, proposal2Phase, u);
                        } else {
                            proposalWrapper = new ProposalWrapper(proposal, localVersion, contest, contestPhase, proposal2Phase);
                        }
                    }
                    request.setAttribute(PROPOSAL_WRAPPED_ATTRIBUTE, proposalWrapper);
                }
            }
        }
        final ProposalsPermissions proposalsPermissions = new ProposalsPermissions(request, proposal, contestPhase);

        request.setAttribute(PROPOSAL_ATTRIBUTE, proposal);
        request.setAttribute(CONTEST_ATTRIBUTE, contest);
        request.setAttribute(CONTEST_PHASE_ATTRIBUTE, contestPhase);
        request.setAttribute(PROPOSAL_2_PHASE_ATTRIBUTE, proposal2Phase);
        request.setAttribute(PERMISSIONS_ATTRIBUTE, proposalsPermissions);
        request.setAttribute(DISPLAY_PERMISSIONS_ATTRIBUTE, new ProposalsDisplayPermissions(
                proposalsPermissions, proposal, contestPhase));
        ProposalsPreferencesWrapper preferences = new ProposalsPreferencesWrapper(request);
        request.setAttribute(PROPOSALS_PREFERENCES_ATTRIBUTE, preferences);
        request.setAttribute(CONTEST_TYPE_ATTRIBUTE, contestType == null ? preferences.getContestType() : contestType);
        
        request.setAttribute(USER_ATTRIBUTE, themeDisplay.getUser());
        if (phaseId > 0) {
            request.setAttribute(REQUEST_PHASE_ID_ATTRIBUTE, phaseId);
        }
        
        request.setAttribute(CONTEXT_INITIALIZED_ATTRIBUTE, true);
        
    }

    private final static Log _log = LogFactoryUtil.getLog(ProposalsContextImpl.class);


    private void reportInvalidUrlToAdmins(User currentUser, String currentUrl) {
        String userScreenName = "(not logged in)";
        if (Validator.isNotNull(currentUser)) {
            userScreenName = currentUser.getScreenName();
        }

        new EmailToAdminDispatcher("User accessed invalid URL " + currentUrl, "<p>User " + userScreenName + " could not access URL " + currentUrl + "</p>").sendMessage();
    }

    private void handleAccessedInvalidUrlIdInUrl(User currentUser, String currentUrl) throws ProposalIdOrContestIdInvalidException {
        if (Validator.isNotNull(currentUser)) {
            reportInvalidUrlToAdmins(currentUser, currentUrl);
        }

        throw new ProposalIdOrContestIdInvalidException();
    }
}
