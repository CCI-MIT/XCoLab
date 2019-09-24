package org.xcolab.view.pages.proposals.utils.voting;

import org.joda.time.DateTime;

import org.xcolab.client.admin.attributes.configuration.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.InternalException;
import org.xcolab.entity.utils.notifications.proposal.ProposalVoteValidityConfirmation;
import org.xcolab.view.pages.proposals.utils.context.ClientHelper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class VoteValidator {

    private static final int VOTE_RECENCY_THRESHOLD_HOURS = 12;
    private static final int SHARED_IP_VOTE_SUSPICION_THRESHOLD = 7;
    private static final int SHARED_IP_CONFIRMATION_THRESHOLD = 3;

    private final UserWrapper member;
    private final ProposalWrapper proposal;
    private final ContestWrapper contest;
    private final String remoteIp;
    private final ClientHelper clients;

    public VoteValidator(UserWrapper member, ProposalWrapper proposal, ContestWrapper contest,
            String remoteIp, ClientHelper clients) {
        this.member = member;
        this.proposal = proposal;
        this.contest = contest;
        this.remoteIp = remoteIp;
        this.clients = clients;
    }

    public ValidationResult validate() {
        IProposalVote vote = getVote(member);
        if (vote == null) {
            throw new InternalException("Could not retrieve vote");
        }
        final ValidationResult validationResult = getValidationResult(vote);
        if (validationResult != ValidationResult.VALID) {
            vote.setIsValid(false);
        }
        vote.setInitialValidationResult(validationResult.name());
        vote.setLastValidationResult(validationResult.name());
        clients.getProposalMemberRatingClient().updateProposalVote(vote);
        return validationResult;
    }

    private ValidationResult getValidationResult(IProposalVote vote) {
        if (member.isVerifiedAccount() || isEmailWhitelisted()) {
            return ValidationResult.VALID;
        }

        if (member.isIsEmailBounced()) {
            return ValidationResult.INVALID_BOUNCED_EMAIL;
        }

        if (isEmailBlacklisted()) {
            return ValidationResult.INVALID_BLACKLISTED;
        }

        if (remoteIp == null) {
            return ValidationResult.VALID;
        }

        List<UserWrapper> usersWithSharedIP = StaticUserContext.getUserClient().getUserByIp(remoteIp);
        usersWithSharedIP.remove(member);

        for (UserWrapper otherMember : usersWithSharedIP) {
            if (member.hasSameName(otherMember)) {
                return ValidationResult.INVALID_DUPLICATE;
            }
        }

        List<IProposalVote> recentVotesFromSharedIp = new ArrayList<>();
        for (UserWrapper otherMember : usersWithSharedIP) {
            final IProposalVote otherVote = getVote(otherMember);
            if (otherVote != null) {
                if (isRecentVote(otherVote)) {
                    recentVotesFromSharedIp.add(otherVote);
                }
            }
        }

        if (recentVotesFromSharedIp.size() > SHARED_IP_VOTE_SUSPICION_THRESHOLD) {

            if (countConfirmedVotes(recentVotesFromSharedIp) >= SHARED_IP_CONFIRMATION_THRESHOLD) {
                return ValidationResult.VALID;
            }

            recentVotesFromSharedIp.stream()
                    .filter(IProposalVote::isIsValid)
                    .forEach(this::sendConfirmationEmail);

            if (vote.isIsValid()) {
                sendConfirmationEmail(vote);
                return ValidationResult.AWAITING_RESPONSE;
            }
        }
        return ValidationResult.VALID;
    }

    private long countConfirmedVotes(List<IProposalVote> recentVotesFromSharedIp) {
        return recentVotesFromSharedIp.stream()
                .filter(IProposalVote::isIsValid)
                .filter(otherVote -> otherVote.getConfirmationEmailSendDate() != null)
                .count();
    }

    private void sendConfirmationEmail(IProposalVote vote) {
        String confirmationToken = generateAndSetConfirmationToken(vote);
        vote.setIsValid(false);
        clients.getProposalMemberRatingClient().updateProposalVote(vote);

        UserWrapper member = StaticUserContext.getUserClient().getMemberUnchecked(vote.getUserId());
        new ProposalVoteValidityConfirmation(proposal, contest, member, confirmationToken)
                .sendEmailNotification();
    }

    private IProposalVote getVote(UserWrapper votingMember) {
        return clients.getProposalMemberRatingClient()
                .getProposalVoteByProposalIdUserId(proposal.getId(),
                        votingMember.getId());
    }

    private boolean isRecentVote(IProposalVote otherVote) {
        final DateTime otherVoteTime = new DateTime(otherVote.getCreatedAt());
        return otherVoteTime.plusHours(VOTE_RECENCY_THRESHOLD_HOURS).isAfterNow();
    }

    private boolean isEmailWhitelisted() {
        final List<Pattern> emailWhitelist =
                ConfigurationAttributeKey.VOTING_EMAIL_VERIFICATION_WHITELIST.get();
        return emailWhitelist.stream()
                .anyMatch(pattern -> pattern.matcher(member.getEmailAddress()).find());
    }

    private boolean isEmailBlacklisted() {
        final List<Pattern> emailBlacklist =
                ConfigurationAttributeKey.VOTING_EMAIL_VERIFICATION_BLACKLIST.get();
        return emailBlacklist.stream()
                .anyMatch(pattern -> pattern.matcher(member.getEmailAddress()).find());
    }

    private String generateAndSetConfirmationToken(IProposalVote vote) {
        String confirmationToken = UUID.randomUUID().toString();
        vote.setConfirmationToken(confirmationToken);
        vote.setConfirmationEmailSendDate(new Timestamp(new Date().getTime()));
        return confirmationToken;
    }

    public enum ValidationResult {
        VALID,
        INVALID_BLACKLISTED,
        INVALID_BOUNCED_EMAIL,
        INVALID_DUPLICATE,
        AWAITING_RESPONSE
    }
}
