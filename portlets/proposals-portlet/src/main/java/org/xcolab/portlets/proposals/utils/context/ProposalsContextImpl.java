package org.xcolab.portlets.proposals.utils.context;

import org.springframework.stereotype.Component;

import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.portlets.proposals.exceptions.ProposalIdOrContestIdInvalidException;
import org.xcolab.portlets.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.portlets.proposals.permissions.ProposalsPermissions;
import org.xcolab.portlets.proposals.utils.context.ProposalContextHelper.InvalidAccessException;
import org.xcolab.portlets.proposals.wrappers.ProposalsPreferencesWrapper;
import org.xcolab.util.exceptions.InternalException;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

@Component
public class ProposalsContextImpl implements ProposalsContext {

    private static final String PROPOSALS_ATTRIBUTE_PREFIX = "_proposalsProtlet_";
    private static final String CONTEXT_INITIALIZED_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "contextInitialized";
    private static final String PERMISSIONS_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "permissions";
    private static final String DISPLAY_PERMISSIONS_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "displayPermissions";
    private static final String CONTEST_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contest";
    private static final String PROPOSAL_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposals";
    private static final String CONTEST_PHASE_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "contestPhase";
    private static final String USER_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "user";
    private static final String MEMBER_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "member";
    private static final String PROPOSALS_PREFERENCES_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "preferences";
    private static final String PROPOSAL_2_PHASE_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "proposal2phase";
    private static final String REQUEST_PHASE_ID_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "requestPhaseId";
    private static final String CONTEST_WRAPPED_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "contestWrapped";
    private static final String CONTEST_PHASE_WRAPPED_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "contestPhaseWrapped";
    private static final String CONTEST_TYPE_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contestType";
    private static final String PROPOSAL_WRAPPED_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "proposalWrapped";
    private static final String CLIENTS_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "clients";
    public static final String PROPOSAL_CONTEXT_HELPER = "ProposalContextHelper";

    public ProposalsContextImpl() {
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getContest(javax.portlet
     * .PortletRequest)
     */
    @Override
    public Contest getContest(PortletRequest request) {
        return getAttribute(request, CONTEST_ATTRIBUTE);
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getContestPhase(javax
     * .portlet.PortletRequest)
     */
    @Override
    public ContestPhase getContestPhase(PortletRequest request) {
        return getAttribute(request, CONTEST_PHASE_ATTRIBUTE);
    }


    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getProposal(javax
     * .portlet.PortletRequest)
     */
    @Override
    public Proposal getProposal(PortletRequest request) {
        return getAttribute(request, PROPOSAL_ATTRIBUTE);
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getPermissions(javax
     * .portlet.PortletRequest)
     */
    @Override
    public ProposalsPermissions getPermissions(PortletRequest request) {
        return getAttribute(request, PERMISSIONS_ATTRIBUTE);
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getDisplayPermissions
     * (javax.portlet.PortletRequest)
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
    public Proposal getProposalWrapped(PortletRequest request) {
        return getAttribute(request, PROPOSAL_WRAPPED_ATTRIBUTE);
    }

    @Override
    public Contest getContestWrapped(PortletRequest request) {
        return getAttribute(request, CONTEST_WRAPPED_ATTRIBUTE);
    }

    @Override
    public ContestPhase getContestPhaseWrapped(PortletRequest request) {
        return getAttribute(request, CONTEST_PHASE_WRAPPED_ATTRIBUTE);
    }

    @Override
    public ContestType getContestType(PortletRequest request) {
        return getAttribute(request, CONTEST_TYPE_ATTRIBUTE);
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
    public long getMemberId(PortletRequest request) {
        Member member = getMember(request);
        if (member == null) {
            return 0;
        }
        return member.getId_();
    }

    @Override
    public void invalidateContext(PortletRequest request) {
        request.removeAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
    }

    @Override
    public ProposalsPreferencesWrapper getProposalsPreferences(PortletRequest request) {
        return getAttribute(request, PROPOSALS_PREFERENCES_ATTRIBUTE);
    }

    @Override
    public ClientHelper getClients(PortletRequest request) {
        return getAttribute(request, CLIENTS_ATTRIBUTE);
    }

    private <T> T getAttribute(PortletRequest request, String attributeName) {
        Object contextInitialized = request.getAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
        if (contextInitialized == null) {
            init(request);
        }
        //noinspection unchecked
        return (T) request.getAttribute(attributeName);
    }

    private void init(PortletRequest request) {
        ProposalContextHelper contextHelper = new ProposalContextHelper(request);

        final Member member = contextHelper.getMember();
        final ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        final String currentUrl = themeDisplay.getPortalURL() + themeDisplay.getURLCurrent();

        final HttpServletRequest httpServletRequest
                = ((LiferayPortletRequest) request).getHttpServletRequest();
        final String referralUrl = httpServletRequest.getHeader("Referer");
        final String userAgent = httpServletRequest.getHeader("User-Agent");

        try {
            Contest contest = contextHelper.getContest();

            ContestPhase contestPhase = null;
            Proposal proposal = null;
            Proposal2Phase proposal2Phase = null;
            ContestType contestType = null;

            if (contest != null) {
                contestPhase = contextHelper.getContestPhase(contest);
                if (contextHelper.getGivenProposalId() > 0) {
                    proposal2Phase = contextHelper.getProposal2Phase(contestPhase);
                    if (proposal2Phase == null && request.getParameter("isMove") == null) {
                        if (contextHelper.getGivenPhaseId() > 0) {
                            throw new InvalidAccessException();
                        }
                        //TODO: maybe this could be an InvalidAccessException?
                        throw new InternalException(String.format(
                                "Proposal %d has no phase association with phase %d in contest %d",
                                contextHelper.getGivenProposalId(),
                                contestPhase.getContestPhasePK(),
                                contest.getContestPK()));
                    }
                    proposal = contextHelper.getProposal();
                }

                request.setAttribute(PROPOSAL_CONTEXT_HELPER, contextHelper);
                request.setAttribute(CONTEST_WRAPPED_ATTRIBUTE, contest);
                request.setAttribute(CONTEST_PHASE_WRAPPED_ATTRIBUTE, contestPhase);

                contestType = contextHelper.getClientHelper().getContestClient().getContestType(contest.getContestTypeId());
                if (proposal != null) {
                    Proposal proposalWrapper = contextHelper.getProposalWrapper(
                            proposal, proposal2Phase, contestPhase, contest, member);
                    request.setAttribute(PROPOSAL_WRAPPED_ATTRIBUTE, proposalWrapper);
                }
            }
            if(request.getAttribute(PERMISSIONS_ATTRIBUTE)==null) {
                final ProposalsPermissions proposalsPermissions = new ProposalsPermissions(request,
                        proposal, contestPhase);
                request.setAttribute(PERMISSIONS_ATTRIBUTE, proposalsPermissions);

                request.setAttribute(DISPLAY_PERMISSIONS_ATTRIBUTE, new ProposalsDisplayPermissions(
                        proposalsPermissions, proposal, contestPhase, request));
            }

            request.setAttribute(PROPOSAL_ATTRIBUTE, proposal);
            request.setAttribute(CONTEST_ATTRIBUTE, contest);
            request.setAttribute(CONTEST_PHASE_ATTRIBUTE, contestPhase);
            request.setAttribute(PROPOSAL_2_PHASE_ATTRIBUTE, proposal2Phase);

            ProposalsPreferencesWrapper preferences = new ProposalsPreferencesWrapper(request);
            request.setAttribute(PROPOSALS_PREFERENCES_ATTRIBUTE, preferences);
            request.setAttribute(CONTEST_TYPE_ATTRIBUTE,
                    contestType == null ? preferences.getContestType() : contestType);

            request.setAttribute(USER_ATTRIBUTE, themeDisplay.getUser());
            request.setAttribute(MEMBER_ATTRIBUTE, member);
            long phaseId = contextHelper.getGivenPhaseId();
            if (phaseId > 0) {
                request.setAttribute(REQUEST_PHASE_ID_ATTRIBUTE, phaseId);
            }
            request.setAttribute(CLIENTS_ATTRIBUTE, contextHelper.getClientHelper());

            request.setAttribute(CONTEXT_INITIALIZED_ATTRIBUTE, true);
        } catch (InvalidAccessException e) {
            handleAccessedInvalidUrlIdInUrl(member, currentUrl, referralUrl, userAgent);
        }
    }

    //TODO: report to table rather than email
    @SuppressWarnings("UnusedParameters")
    private void handleAccessedInvalidUrlIdInUrl(Member member, String currentUrl,
            String referralUrl, String userAgent)
            throws ProposalIdOrContestIdInvalidException {
//        if (member != null) {
//            reportInvalidUrlToAdmins(member, currentUrl, referralUrl, userAgent);
//        }

        throw new ProposalIdOrContestIdInvalidException();
    }

//    private void reportInvalidUrlToAdmins(User currentUser, String currentUrl, String referralUrl,
//            String userAgent) {
//        String userScreenName = "(not logged in)";
//        if (Validator.isNotNull(currentUser)) {
//            userScreenName = currentUser.getScreenName();
//        }

//        String emailMessage =
//                "<p>User <b>" + userScreenName + "</b> could not access URL: <b>" + currentUrl
//                        + "</b></p>" +
//                        "<p>Referral URL: <b>" + referralUrl + "</b></p>" +
//                        "<p>User agent: <b>" + userAgent + "</b></p>";

        //new EmailToAdminDispatcher("User accessed invalid URL " + currentUrl, emailMessage).sendMessage();

//    }
}
