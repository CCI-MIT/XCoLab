package org.xcolab.view.pages.proposals.utils.context;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.contest.ContestClient;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;
import org.xcolab.util.exceptions.InternalException;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.exceptions.ProposalIdOrContestIdInvalidException;
import org.xcolab.view.pages.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.utils.context.ProposalContextHelper.InvalidAccessException;
import org.xcolab.view.pages.proposals.wrappers.ProposalsPreferencesWrapper;

import javax.servlet.http.HttpServletRequest;

@Component
public class ProposalsContextImpl implements ProposalsContext {

    private static final String PROPOSALS_ATTRIBUTE_PREFIX = "_proposalsContext_";
    private static final String CONTEXT_INITIALIZED_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "contextInitialized";
    private static final String PERMISSIONS_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "permissions";
    private static final String DISPLAY_PERMISSIONS_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "displayPermissions";
    private static final String CONTEST_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "contest";
    private static final String PROPOSAL_ATTRIBUTE = PROPOSALS_ATTRIBUTE_PREFIX + "proposals";
    private static final String CONTEST_PHASE_ATTRIBUTE =
            PROPOSALS_ATTRIBUTE_PREFIX + "contestPhase";
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
    public static final String PROPOSAL_CONTEST_HELPER = "ProposalContextHelper";

    public ProposalsContextImpl() {
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getContest(javax.portlet
     * .HttpServletRequest)
     */
    @Override
    public Contest getContest(HttpServletRequest request) {
        return getAttribute(request, CONTEST_ATTRIBUTE);
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getContestPhase(javax
     * .portlet.HttpServletRequest)
     */
    @Override
    public ContestPhase getContestPhase(HttpServletRequest request) {
        return getAttribute(request, CONTEST_PHASE_ATTRIBUTE);
    }


    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getProposal(javax
     * .portlet.HttpServletRequest)
     */
    @Override
    public Proposal getProposal(HttpServletRequest request) {
        return getAttribute(request, PROPOSAL_ATTRIBUTE);
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getPermissions(javax
     * .portlet.HttpServletRequest)
     */
    @Override
    public ProposalsPermissions getPermissions(HttpServletRequest request) {
        return getAttribute(request, PERMISSIONS_ATTRIBUTE);
    }

    /* (non-Javadoc)
     * @see org.xcolab.portlets.proposals.utils.context.ProposalsContext#getDisplayPermissions
     * (javax.portlet.HttpServletRequest)
     */
    @Override
    public ProposalsDisplayPermissions getDisplayPermissions(HttpServletRequest request) {
        return getAttribute(request, DISPLAY_PERMISSIONS_ATTRIBUTE);
    }

    @Override
    public Proposal2Phase getProposal2Phase(HttpServletRequest request) {
        return getAttribute(request, PROPOSAL_2_PHASE_ATTRIBUTE);
    }

    @Override
    public Long getViewContestPhaseId(HttpServletRequest request) {
        return getAttribute(request, REQUEST_PHASE_ID_ATTRIBUTE);
    }

    @Override
    public Proposal getProposalWrapped(HttpServletRequest request) {
        return getAttribute(request, PROPOSAL_WRAPPED_ATTRIBUTE);
    }

    @Override
    public Contest getContestWrapped(HttpServletRequest request) {
        return getAttribute(request, CONTEST_WRAPPED_ATTRIBUTE);
    }

    @Override
    public ContestPhase getContestPhaseWrapped(HttpServletRequest request) {
        return getAttribute(request, CONTEST_PHASE_WRAPPED_ATTRIBUTE);
    }

    @Override
    public ContestType getContestType(HttpServletRequest request) {
        return getAttribute(request, CONTEST_TYPE_ATTRIBUTE);
    }



    @Override
    public Member getMember(HttpServletRequest request) {
        return getAttribute(request, MEMBER_ATTRIBUTE);
    }

    @Override
    public long getMemberId(HttpServletRequest request) {
        Member member = getMember(request);
        if (member == null) {
            return 0;
        }
        return member.getId_();
    }

    @Override
    public void invalidateContext(HttpServletRequest request) {
        request.removeAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
    }

    @Override
    public ProposalsPreferencesWrapper getProposalsPreferences(HttpServletRequest request) {
        return getAttribute(request, PROPOSALS_PREFERENCES_ATTRIBUTE);
    }

    @Override
    public ClientHelper getClients(HttpServletRequest request) {
        return getAttribute(request, CLIENTS_ATTRIBUTE);
    }

    private <T> T getAttribute(HttpServletRequest request, String attributeName) {
        Object contextInitialized = request.getAttribute(CONTEXT_INITIALIZED_ATTRIBUTE);
        if (contextInitialized == null) {
            init(request);
        }
        //noinspection unchecked
        return (T) request.getAttribute(attributeName);
    }

    private void init(HttpServletRequest request) {
        ProposalContextHelper contextHelper = new ProposalContextHelper(request);

        final Member member = contextHelper.getMember();

        final String currentUrl = request.getRequestURL().toString();


        final String referralUrl = request.getHeader(HttpHeaders.REFERER);
        final String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

        try {
            Contest contest = contextHelper.getContest();
            Proposal proposal = contextHelper.getProposal();

            ContestType contestType = null;
            ContestPhase contestPhase = null;
            Proposal2Phase proposal2Phase = null;

            final ClientHelper clientHelper = contextHelper.getClientHelper();
            final ContestClient contestClient = clientHelper.getContestClient();

            if (contest != null) {
                contestType = ContestTypeClient.getContestType(contest.getContestTypeId());
                contestPhase = contextHelper.getContestPhase(contest, proposal);
                if (proposal != null) {
                    proposal2Phase = contextHelper.getProposal2Phase(contestPhase);
                    if (proposal2Phase == null && request.getParameter("isMove") == null) {
                        if (contextHelper.getGivenPhaseId() > 0) {
                            throw new InvalidAccessException();
                        }
                        throw new InternalException(String.format(
                                "Proposal %d has no phase association with phase %d in contest %d",
                                contextHelper.getGivenProposalId(),
                                contestPhase.getContestPhasePK(),
                                contest.getContestPK()));
                    }
                }

                request.setAttribute(PROPOSAL_CONTEST_HELPER, contextHelper);
                request.setAttribute(CONTEST_WRAPPED_ATTRIBUTE, contest);
                request.setAttribute(CONTEST_PHASE_WRAPPED_ATTRIBUTE, contestPhase);

                if (proposal != null) {
                    Proposal proposalWrapper = contextHelper.getProposalWrapper(
                            proposal, proposal2Phase, contestPhase, contest, member);
                    request.setAttribute(PROPOSAL_WRAPPED_ATTRIBUTE, proposalWrapper);
                }
            }
            if (request.getAttribute(PERMISSIONS_ATTRIBUTE) == null) {
                final ProposalsPermissions proposalsPermissions = new ProposalsPermissions(request,
                        proposal, contestPhase);
                request.setAttribute(PERMISSIONS_ATTRIBUTE, proposalsPermissions);

                request.setAttribute(DISPLAY_PERMISSIONS_ATTRIBUTE, new ProposalsDisplayPermissions(
                        proposalsPermissions, proposal, contestPhase, clientHelper,
                        MemberAuthUtil.getMemberId(request)));
            }

            request.setAttribute(PROPOSAL_ATTRIBUTE, proposal);
            request.setAttribute(CONTEST_ATTRIBUTE, contest);
            request.setAttribute(CONTEST_PHASE_ATTRIBUTE, contestPhase);
            request.setAttribute(PROPOSAL_2_PHASE_ATTRIBUTE, proposal2Phase);

            ProposalsPreferencesWrapper preferences = new ProposalsPreferencesWrapper(request.getParameter("preferenceId"));
            request.setAttribute(PROPOSALS_PREFERENCES_ATTRIBUTE, preferences);
            request.setAttribute(CONTEST_TYPE_ATTRIBUTE,
                    contestType == null ? preferences.getContestType() : contestType);

            request.setAttribute(MEMBER_ATTRIBUTE, member);
            long phaseId = contextHelper.getGivenPhaseId();
            if (phaseId > 0) {
                request.setAttribute(REQUEST_PHASE_ID_ATTRIBUTE, phaseId);
            }
            request.setAttribute(CLIENTS_ATTRIBUTE, clientHelper);

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
