package org.xcolab.view.pages.proposals.utils.voting;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalMemberRatingClient;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.entity.utils.notifications.proposal.ProposalVoteValidityConfirmation;
import org.xcolab.util.exceptions.InternalException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class VoteValidator {

    private static final int VOTE_RECENCY_THRESHOLD_HOURS = 12;
    private static final int NAME_SIMILARITY_LEVENSHTEIN_THRESHOLD = 3;

    private final Member member;
    private final Proposal proposal;
    private final Contest contest;
    private final String remoteIp;
    private final ProposalMemberRatingClient proposalMemberRatingClient;

    public VoteValidator(Member member, Proposal proposal, Contest contest,
            String remoteIp, ProposalMemberRatingClient proposalMemberRatingClient) {
        this.member = member;
        this.proposal = proposal;
        this.contest = contest;
        this.remoteIp = remoteIp;
        this.proposalMemberRatingClient = proposalMemberRatingClient;
    }

    public ValidationResult validate() {
        if (member.isVerifiedAccount() || isEmailWhitelisted()) {
            return ValidationResult.VALID;
        }

        if (isEmailBlacklisted()) {
            return ValidationResult.INVALID_BLACKLISTED;
        }

        List<Member> usersWithSharedIP = MembersClient.findMembersByIp(remoteIp);
        usersWithSharedIP.remove(member);
        if (usersWithSharedIP.isEmpty()) {
            return ValidationResult.VALID;
        }

        final ProposalVote vote = getVote(member);
        if (vote == null) {
            throw new InternalException("Could not retrieve vote");
        }

        ValidationResult result = ValidationResult.VALID;
        for (Member otherMember : usersWithSharedIP) {
            if (isNameSimilar(otherMember)) {
                vote.setIsValid(false);
                result = ValidationResult.INVALID_DUPLICATE;
                break;
            }
        }

        int recentVotesFromSharedIP = 0;
        for (Member otherMember : usersWithSharedIP) {
            final ProposalVote otherVote = getVote(otherMember);
            if (otherVote != null) {
                if (isRecentVote(otherVote)) {
                    recentVotesFromSharedIP++;
                }
            }

        }
        if (vote.getIsValid() && recentVotesFromSharedIP > 7) {
            vote.setIsValid(false);
            result = ValidationResult.AWAITING_RESPONSE;
            sendConfirmationMail(vote);
        }
        proposalMemberRatingClient.updateProposalVote(vote);
        return result;
    }

    private ProposalVote getVote(Member otherUser) {
        return proposalMemberRatingClient
                .getProposalVoteByProposalIdUserId(proposal.getProposalId(),
                        otherUser.getUserId());
    }

    private boolean isRecentVote(ProposalVote otherVote) {
        final DateTime otherVoteTime = new DateTime(otherVote.getCreateDate());
        return otherVoteTime.plusHours(VOTE_RECENCY_THRESHOLD_HOURS).isAfterNow();
    }

    private boolean isNameSimilar(Member otherUser) {
        return StringUtils.getLevenshteinDistance(member.getFirstName(),
                otherUser.getFirstName()) < NAME_SIMILARITY_LEVENSHTEIN_THRESHOLD
                && StringUtils.getLevenshteinDistance(member.getLastName(),
                otherUser.getLastName()) < NAME_SIMILARITY_LEVENSHTEIN_THRESHOLD;
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

    private void sendConfirmationMail(ProposalVote vote) {
        String confirmationToken = UUID.randomUUID().toString();
        vote.setConfirmationToken(confirmationToken);
        vote.setConfirmationEmailSendDate(new Timestamp(new Date().getTime()));
        new ProposalVoteValidityConfirmation(proposal, contest, member, confirmationToken)
                .sendEmailNotification();
    }

    public enum ValidationResult {
        VALID,
        INVALID_BLACKLISTED,
        INVALID_DUPLICATE,
        AWAITING_RESPONSE
    }
}
