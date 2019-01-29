package org.xcolab.view.pages.proposals.utils.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;
import org.springframework.web.servlet.LocaleResolver;

import org.xcolab.client.admin.StaticAdminContext;
import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.admin.pojo.ContestType;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.view.auth.MemberAuthUtil;
import org.xcolab.view.pages.proposals.exceptions.InvalidAccessException;
import org.xcolab.view.pages.proposals.exceptions.InvalidContestUrlException;
import org.xcolab.view.pages.proposals.exceptions.InvalidProposalUrlException;
import org.xcolab.view.pages.proposals.permissions.ProposalsDisplayPermissions;
import org.xcolab.view.pages.proposals.permissions.ProposalsPermissions;
import org.xcolab.view.pages.proposals.wrappers.ProposalsPreferencesWrapper;

import javax.servlet.http.HttpServletRequest;

public class ProposalContextImpl implements ProposalContext {

    private static final Logger log = LoggerFactory.getLogger(ProposalContextImpl.class);

    private final LocaleResolver localeResolver;
    private final HttpServletRequest request;

    private ContestWrapper contest;
    private ContestType contestType;
    private ContestPhaseWrapper contestPhase;
    private IProposal2Phase proposal2Phase;
    private ProposalWrapper proposal;
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

        final UserWrapper member = contextHelper.getMember();

        final String currentUrl = request.getRequestURL().toString();
        final String referrerUrl = request.getHeader(HttpHeaders.REFERER);
        final String userAgent = request.getHeader(HttpHeaders.USER_AGENT);

        try {
            contest = contextHelper.getContest();
            proposal = contextHelper.getProposal(contest);
            clientHelper = contextHelper.getClientHelper();

            if (contest != null) {
                contestType = StaticAdminContext.getContestTypeClient().getContestType(contest.getContestTypeId(), language);
                contestPhase = contextHelper.getContestPhase(contest, proposal);
                if (proposal != null) {
                    proposal2Phase = contextHelper.getProposal2Phase(contestPhase);
                    final boolean isMove = request.getParameter("moveType") != null;
                    if (proposal2Phase == null && !isMove) {
                        if (contextHelper.getGivenPhaseId() > 0) {
                            throw new InvalidProposalUrlException(contest, contestPhase,
                                    proposal.getId());
                        }
                        throw new InternalException(String.format(
                                "Proposal %d has no phase association with phase %d in contest %d",
                                contextHelper.getGivenProposalId(),
                                contestPhase.getId(),
                                contest.getId()));
                    }
                }
            }
            permissions = new ProposalsPermissions(clientHelper, member, proposal, contest,
                    contestPhase);
            displayPermissions =
                    new ProposalsDisplayPermissions(permissions, proposal, contestPhase,
                            clientHelper, MemberAuthUtil.getUserId());

            preferences = new ProposalsPreferencesWrapper(request.getParameter("preferenceId"),
                    localeResolver.resolveLocale(request).getLanguage());

            if (contestType == null) {
                contestType = new ContestType(ConfigurationAttributeKey.DEFAULT_CONTEST_TYPE_ID.get(), language);
            }
        } catch (InvalidProposalUrlException | InvalidContestUrlException e) {
            reportAccessOfInvalidUrl(member, currentUrl, referrerUrl, userAgent, e);
            throw e;
        }
    }

    //TODO COLAB-2624: report to table rather than email (or external service)
    @SuppressWarnings("UnusedParameters")
    private void reportAccessOfInvalidUrl(UserWrapper member, String currentUrl,
            String referralUrl, String userAgent, InvalidAccessException exception) {

        log.warn("Invalid URL {} accessed from {} by {}: {}", currentUrl, referralUrl,
                member != null ?member.getScreenName() : "[no user logged in]",
                exception.getMessage());

        //        if (member != null) {
        //            reportInvalidUrlToAdmins(member, currentUrl, referralUrl, userAgent);
        //        }
    }

    //    private void reportInvalidUrlToAdmins(User currentUser, String currentUrl, String referralUrl,
    //            String userAgent) {
    //        String userScreenName = "(not logged in)";
    //        if (Validator.isNotNull(currentUser)) {
    //            userScreenName = currentUser.getDisplayName();
    //        }

    //        String emailMessage =
    //                "<p>User <b>" + userScreenName + "</b> could not access URL: <b>" + currentUrl
    //                        + "</b></p>" +
    //                        "<p>Referral URL: <b>" + referralUrl + "</b></p>" +
    //                        "<p>User agent: <b>" + userAgent + "</b></p>";

    //new EmailToAdminDispatcher("User accessed invalid URL " + currentUrl, emailMessage).sendMessage();

    //    }

    @Override
    public ContestWrapper getContest() {
        return contest;
    }

    @Override
    public ContestType getContestType() {
        return contestType;
    }

    @Override
    public ContestPhaseWrapper getContestPhase() {
        return contestPhase;
    }

    @Override
    public IProposal2Phase getProposal2Phase() {
        return proposal2Phase;
    }

    @Override
    public ProposalWrapper getProposal() {
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
