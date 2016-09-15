package org.xcolab.portlets.proposals.utils;

import com.ext.portlet.NoSuchContestException;



import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalsClient;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.Proposal2Phase;
import org.xcolab.enums.MemberRole;
import org.xcolab.portlets.proposals.exceptions.ProposalIdOrContestIdInvalidException;
import org.xcolab.portlets.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.wrappers.ContestWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalJudgeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.wrappers.BaseContestPhaseWrapper;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

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
    private static final String MEMBER_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "member";
    private static final String PROPOSALS_PREFERENCES_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "preferences";
    
    private static final String PROPOSAL_2_PHASE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposal2phase";
    private static final String REQUEST_PHASE_ID_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "requestPhaseId";
    private static final String CONTEST_WRAPPED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestWrapped";
    private static final String CONTEST_PHASE_WRAPPED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestPhaseWrapped";
    private static final String CONTEST_TYPE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestType";
    private static final String PROPOSAL_WRAPPED_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposalWrapped";

    public static final String PROPOSAL_ID_PARAM = "proposalId";
    public static final String PLAN_ID_PARAM = "planId";
    public static final String CONTEST_ID_PARAM = "contestId";
    public static final String CONTEST_URL_NAME_PARAM = "contestUrlName";
    public static final String CONTEST_YEAR_PARAM = "contestYear";
    public static final String CONTEST_PHASE_ID_PARAM = "phaseId";
    public static final String VERSION_PARAM = "version";

    public ProposalsContextImpl() {
    }
    
    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getContest(javax.portlet.PortletRequest)
     */
    @Override
    public Contest getContest(PortletRequest request) {
        return getAttribute(request, CONTEST_ATTRIBUTE);
    }
    
    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getContestPhase(javax.portlet.PortletRequest)
     */
    @Override
    public ContestPhase getContestPhase(PortletRequest request) {
        return getAttribute(request, CONTEST_PHASE_ATTRIBUTE);
    }
    
    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getProposal(javax.portlet.PortletRequest)
     */
    @Override
    public Proposal getProposal(PortletRequest request) {
        return getAttribute(request, PROPOSAL_ATTRIBUTE);
    }


    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getPermissions(javax.portlet.PortletRequest)
     */
    @Override
    public ProposalsPermissions getPermissions(PortletRequest request) {
        return getAttribute(request, PERMISSIONS_ATTRIBUTE);
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.ProposalsContext#getDisplayPermissions(javax.portlet.PortletRequest)
     */
    @Override
    public ProposalsDisplayPermissions getDisplayPermissions(PortletRequest request) {
        return getAttribute(request, DISPLAY_PERMISSIONS_ATTRIBUTE);
    }
    
    @Override
    public Proposal2Phase getProposal2Phase(PortletRequest request) {
        return getAttribute(request, PROPOSAL_2_PHASE_ATTRIBUTE);
    }
    
    @Override
    public Long getViewContestPhaseId(PortletRequest request) {
        return getAttribute(request, REQUEST_PHASE_ID_ATTRIBUTE);
    }
    
    @Override
    public ProposalWrapper getProposalWrapped(PortletRequest request) {
        return getAttribute(request, PROPOSAL_WRAPPED_ATTRIBUTE);
    }
    
    @Override
    public ContestWrapper getContestWrapped(PortletRequest request) {
        return getAttribute(request, CONTEST_WRAPPED_ATTRIBUTE);
    }
    
    @Override
    public BaseContestPhaseWrapper getContestPhaseWrapped(PortletRequest request) {
        return getAttribute(request, CONTEST_PHASE_WRAPPED_ATTRIBUTE);
    }

    @Override
    public ContestType getContestType(PortletRequest request) {
        return getAttribute(request, CONTEST_TYPE_ATTRIBUTE);
    }

    @Override
    public ProposalsPreferencesWrapper getProposalsPreferences(PortletRequest request) {
        return getAttribute(request, PROPOSALS_PREFERENCES_ATTRIBUTE);
    }
    
    @Override
    public User getUser(PortletRequest request) {
        return getAttribute(request, USER_ATTRIBUTE);
    }

    @Override
    public Member getMember(PortletRequest request) {
        return getAttribute(request, MEMBER_ATTRIBUTE);
    }

    @Override
    public void invalidateContext(PortletRequest request) {
        request.removeAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
    }
    
    private <T> T getAttribute(PortletRequest request, String attributeName) {
        Object contextInitialized =  request.getAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
        if (contextInitialized == null) {
            init(request);
        }
        return (T) request.getAttribute(attributeName);
    }

    private void init(PortletRequest request) {
        try {
            final String contestUrlName = ParamUtil.getString(request, CONTEST_URL_NAME_PARAM);
            final long contestYear = ParamUtil.getLong(request, CONTEST_YEAR_PARAM);
            final long contestId = ParamUtil.getLong(request, CONTEST_ID_PARAM);
            final long planId = ParamUtil.getLong(request, PLAN_ID_PARAM);
            long proposalId = ParamUtil.getLong(request, PROPOSAL_ID_PARAM);
            final long phaseId = ParamUtil.getLong(request, CONTEST_PHASE_ID_PARAM);
            final int version = ParamUtil.getInteger(request, VERSION_PARAM);

            if (proposalId == 0) {
                proposalId = planId;
            }

            ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
            String currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent();
            User currentUser = null;

            HttpServletRequest httpServletRequest = ((LiferayPortletRequest) request)
                    .getHttpServletRequest();
            String referralUrl = httpServletRequest.getHeader("Referer");
            String userAgent = httpServletRequest.getHeader("User-Agent");

            try {
                currentUser = PortalUtil.getUser(request);
            } catch (PortalException e) {
                //not logged in
            }

            Contest contest = null;
            if (contestId > 0) {
                try {
                    contest = ContestClient.getContest(contestId);
                } catch (ContestNotFoundException e) {
                    handleAccessedInvalidUrlIdInUrl(currentUser, currentUrl, referralUrl,
                            userAgent);
                }
            } else if (StringUtils.isNotBlank(contestUrlName) && contestYear > 0) {
                try {
                    contest = ContestLocalServiceUtil
                            .getByContestUrlNameContestYear(contestUrlName, contestYear);
                } catch (NoSuchContestException e) {
                    handleAccessedInvalidUrlIdInUrl(currentUser, currentUrl, referralUrl,
                            userAgent);
                }
            }

            ContestPhase contestPhase = null;
            Proposal proposal = null;
            Proposal2Phase proposal2Phase = null;

            if (contest != null) {

                    if (phaseId > 0) {
                            contestPhase = ContestClient.getContestPhase(phaseId);
                        if(contestPhase == null) {
                            contestPhase = ContestClient.getActivePhase(contest.getContestPK());
                        }

                    } else {
                        //get the last phase of this contest
                        contestPhase = ContestClient.getActivePhase(contest.getContestPK());
                    }
                if( contestPhase == null) {
                    throw ReferenceResolutionException
                            .toObject(ContestPhase.class, "")
                            .fromObject(Contest.class, contest.getContestPK());
                }


                if (proposalId > 0) {
                    try {
                        proposal2Phase = ProposalsClient
                                .getProposal2PhaseByProposalIdContestPhaseId(proposalId,
                                        contestPhase.getContestPhasePK());
                    } catch (Proposal2PhaseNotFoundException e) {
                        // there is no connection between proposal and selected contest phase, check if phaseId was given by the user, if it was
                        // rethrow the exception, if it wasn't check if there is a connection and any phase for given contest if there is such connection
                        // fetch most recent one
                        // if proposal is being moved ignore missing p2p mapping
                        if (request.getParameter("isMove") == null) {
                            if (phaseId <= 0) {
                                _log.info("Can't find association between proposal " + proposalId
                                        + " and phase " + contestPhase.getContestPhasePK());
                                ContestPhase mostRecentPhaseInRequestedContest = null;
                                ContestPhase mostRecentPhaseInOtherContest = null;
                                for (Long contestPhaseId : Proposal2PhaseLocalServiceUtil
                                        .getContestPhasesForProposal(proposalId)) {

                                    ContestPhase cp = ContestClient
                                            .getContestPhase(contestPhaseId);
                                    boolean isContestPhaseAssociatedWithRequestedContest =
                                            cp.getContestPK() == contest.getContestPK();
                                    if (isContestPhaseAssociatedWithRequestedContest) {
                                        if (mostRecentPhaseInRequestedContest == null
                                                || mostRecentPhaseInRequestedContest.compareTo(cp)
                                                < 0) {
                                            mostRecentPhaseInRequestedContest = cp;
                                        }
                                    } else {
                                        if (mostRecentPhaseInOtherContest == null
                                                || mostRecentPhaseInOtherContest.compareTo(cp)
                                                < 0) {
                                            mostRecentPhaseInOtherContest = cp;
                                        }
                                    }
                                }
                                if (mostRecentPhaseInRequestedContest == null) {

                                    if (mostRecentPhaseInOtherContest == null) {
                                        handleAccessedInvalidUrlIdInUrl(currentUser, currentUrl,
                                                userAgent, referralUrl);
                                    } else {
                                        contestPhase = mostRecentPhaseInOtherContest;
                                        // TODO show user list if several contest are available
                                        try{
                                            contest = ContestClient
                                                    .getContest(contestPhase.getContestPK());
                                        }catch (ContestNotFoundException ignored){
                                            contest = null;
                                        }
                                    }

                                } else {
                                    contestPhase = mostRecentPhaseInRequestedContest;
                                }
                                try {
                                    proposal2Phase = ProposalsClient.
                                            getProposal2PhaseByProposalIdContestPhaseId(proposalId,
                                                    contestPhase.getContestPhasePK());
                                }catch (Proposal2PhaseNotFoundException ignored){
                                    proposal2Phase = null;
                                }
                            } else {
                                _log.warn("Couldn't find alternative association between proposal "
                                        + proposalId + " and phase " + contestPhase
                                        .getContestPhasePK());
                                handleAccessedInvalidUrlIdInUrl(currentUser, currentUrl, userAgent, referralUrl);
                            }
                        }
                    }
                    try {
                        proposal = ProposalsClient.getProposal(proposalId);
                    } catch (ProposalNotFoundException e) {
                        handleAccessedInvalidUrlIdInUrl(currentUser, currentUrl, userAgent, referralUrl);
                    }
                }
            }
            ContestType contestType = null;
            if (contest != null) {
                try {
                    org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(contest.getContestPK());
                    request.setAttribute(CONTEST_WRAPPED_ATTRIBUTE, new ContestWrapper(contestMicro));//contest
                }catch (ContestNotFoundException ignored){

                }
                if (contestPhase != null) {
                    org.xcolab.client.contest.pojo.ContestPhase contestPhaseMicro = ContestClient.getContestPhase(contestPhase.getContestPhasePK());
                    request.setAttribute(CONTEST_PHASE_WRAPPED_ATTRIBUTE,
                            new BaseContestPhaseWrapper(contestPhaseMicro));//contestPhase

                    contestType = ContestClient
                            .getContestType(contest.getContestTypeId());
                    if (proposal != null) {
                        ProposalWrapper proposalWrapper;
                        try {
                            User liferayUser = request.getRemoteUser() != null ? UserLocalServiceUtil
                                    .getUser(Long.parseLong(request.getRemoteUser())) : null;
                            Member member = null;
                            if (liferayUser != null) {
                                try {
                                    member = MembersClient.getMember(liferayUser.getUserId());
                                } catch (MemberNotFoundException e) {
                                    //ignore - we know he user exists
                                }
                            }

                            if (version > 0) {
                                if (member != null && UserLocalServiceUtil
                                        .hasRoleUser(MemberRole.JUDGE.getRoleId(), liferayUser.getUserId())) {
                                    proposalWrapper = new ProposalJudgeWrapper(proposal, version,
                                            contest, contestPhase, proposal2Phase, member);
                                } else {
                                    proposalWrapper = new ProposalWrapper(proposal, version,
                                            contest,
                                            contestPhase, proposal2Phase);
                                }
                            } else {
                                final boolean hasVersionTo =
                                        proposal2Phase != null && proposal2Phase.getVersionTo() > 0;
                                final int localVersion =
                                        hasVersionTo ? proposal2Phase.getVersionTo()
                                                : proposal.getCurrentVersion();

                                if (member != null && UserLocalServiceUtil
                                        .hasRoleUser(MemberRole.JUDGE.getRoleId(), liferayUser.getUserId())) {
                                    proposalWrapper = new ProposalJudgeWrapper(proposal,
                                            localVersion,
                                            contest, contestPhase, proposal2Phase, member);
                                } else {
                                    proposalWrapper = new ProposalWrapper(proposal, localVersion,
                                            contest, contestPhase, proposal2Phase);
                                }
                            }
                            request.setAttribute(PROPOSAL_WRAPPED_ATTRIBUTE, proposalWrapper);
                        } catch (PortalException e) {
                            throw new InternalException("Could not find user " + request.getRemoteUser());
                        }
                    }
                }
            }
            final ProposalsPermissions proposalsPermissions = new ProposalsPermissions(request,
                    proposal, contestPhase);

            request.setAttribute(PROPOSAL_ATTRIBUTE, proposal);
            request.setAttribute(CONTEST_ATTRIBUTE, contest);
            request.setAttribute(CONTEST_PHASE_ATTRIBUTE, contestPhase);
            request.setAttribute(PROPOSAL_2_PHASE_ATTRIBUTE, proposal2Phase);
            request.setAttribute(PERMISSIONS_ATTRIBUTE, proposalsPermissions);
            request.setAttribute(DISPLAY_PERMISSIONS_ATTRIBUTE, new ProposalsDisplayPermissions(
                    proposalsPermissions, proposal, contestPhase));
            ProposalsPreferencesWrapper preferences = new ProposalsPreferencesWrapper(request);
            request.setAttribute(PROPOSALS_PREFERENCES_ATTRIBUTE, preferences);
            request.setAttribute(CONTEST_TYPE_ATTRIBUTE,
                    contestType == null ? preferences.getContestType() : contestType);

            request.setAttribute(USER_ATTRIBUTE, themeDisplay.getUser());
            try {
                request.setAttribute(MEMBER_ATTRIBUTE, MembersClient.getMember(themeDisplay.getUserId()));
            } catch (MemberNotFoundException e) {
                //ignored
            }
            if (phaseId > 0) {
                request.setAttribute(REQUEST_PHASE_ID_ATTRIBUTE, phaseId);
            }

            request.setAttribute(CONTEXT_INITIALIZED_ATTRIBUTE, true);
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new InternalException(e);
        }
    }

    private final static Log _log = LogFactoryUtil.getLog(ProposalsContextImpl.class);


    private void reportInvalidUrlToAdmins(User currentUser, String currentUrl, String referralUrl, String userAgent) {
        String userScreenName = "(not logged in)";
        if (Validator.isNotNull(currentUser)) {
            userScreenName = currentUser.getScreenName();
        }

        String emailMessage = "<p>User <b>" + userScreenName + "</b> could not access URL: <b>" + currentUrl + "</b></p>" +
                "<p>Referral URL: <b>" + referralUrl + "</b></p>" +
                "<p>User agent: <b>" + userAgent + "</b></p>";

        //new EmailToAdminDispatcher("User accessed invalid URL " + currentUrl, emailMessage).sendMessage();
        //TO REANABLE 404 email 
    }

    private void handleAccessedInvalidUrlIdInUrl(User currentUser, String currentUrl,
            String referralUrl, String userAgent)
            throws ProposalIdOrContestIdInvalidException {
        if (Validator.isNotNull(currentUser)) {
            reportInvalidUrlToAdmins(currentUser, currentUrl, referralUrl, userAgent);
        }

        throw new ProposalIdOrContestIdInvalidException();
    }
}
