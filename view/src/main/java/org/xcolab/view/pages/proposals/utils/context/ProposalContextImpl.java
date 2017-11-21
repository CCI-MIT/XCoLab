package org.xcolab.view.pages.proposals.utils.context;

import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

import org.xcolab.client.admin.ContestTypeClient;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.Contest;
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

public class ProposalContextImpl implements ProposalContext {

    private final LocaleResolver localeResolver;
    private final HttpServletRequest request;

    private Contest contest;
    private ContestType contestType;
    private ContestPhase contestPhase;
    private Proposal2Phase proposal2Phase;
    private Proposal proposal;
    private ProposalsPermissions permissions;
    private ProposalsDisplayPermissions displayPermissions;
    private ProposalsPreferencesWrapper preferences;
    private ClientHelper clientHelper;

    public ProposalContextImpl(HttpServletRequest request, LocaleResolver localeResolver) {
        Assert.notNull(localeResolver, "LocaleResolver is required");
        this.localeResolver = localeResolver;
        this.request = request;
        init();
    }

    private void init() {
        ProposalContextHelper contextHelper = new ProposalContextHelper(request);
        final String language = localeResolver.resolveLocale(request).getLanguage();

        final Member member = contextHelper.getMember();

        final String currentUrl = request.getRequestURL().toString();
        final String referrerUrl = request.getHeader(HttpHeaders.REFERER);
        final String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

        try {
            contest = contextHelper.getContest();
            proposal = contextHelper.getProposal(contest);
            clientHelper = contextHelper.getClientHelper();

            if (contest != null) {
                contestType = ContestTypeClient.getContestType(contest.getContestTypeId(), language);
                contestPhase = contextHelper.getContestPhase(contest, proposal);
                if (proposal != null) {
                    proposal2Phase = contextHelper.getProposal2Phase(contestPhase);
                    final boolean isMove = request.getParameter("moveType") != null;
                    if (proposal2Phase == null && !isMove) {
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
            }
            permissions = new ProposalsPermissions(clientHelper, member, proposal, contest,
                    contestPhase);
            displayPermissions =
                    new ProposalsDisplayPermissions(permissions, proposal, contestPhase,
                            clientHelper, MemberAuthUtil.getMemberId(request));

            preferences = new ProposalsPreferencesWrapper(request.getParameter("preferenceId"),localeResolver.resolveLocale(request).getLanguage());

            if (contestType == null) {
                contestType = preferences.getContestType(language);
            }

        } catch (InvalidAccessException e) {
            handleAccessedInvalidUrlIdInUrl(member, currentUrl, referrerUrl, userAgent);
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

    @Override
    public Contest getContest() {
        return contest;
    }

    @Override
    public ContestType getContestType() {
        return contestType;
    }

    @Override
    public ContestPhase getContestPhase() {
        return contestPhase;
    }

    @Override
    public Proposal2Phase getProposal2Phase() {
        return proposal2Phase;
    }

    @Override
    public Proposal getProposal() {
        return proposal;
    }

    @Override
    public ProposalsPreferencesWrapper getPreferences() {
        return preferences;
    }

    @Override
    public ProposalsPermissions getPermissions() {
        return permissions;
    }

    @Override
    public ProposalsDisplayPermissions getDisplayPermissions() {
        return displayPermissions;
    }

    @Override
    public ClientHelper getClients() {
        return clientHelper;
    }
}
