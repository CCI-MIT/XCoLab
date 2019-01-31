package org.xcolab.view.pages.contestmanagement.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activity.StaticActivityContext;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.pojo.IProposalVote;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.client.tracking.StaticTrackingContext;
import org.xcolab.client.tracking.pojo.ILocation;
import org.xcolab.commons.CsvResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

public class VoteCsvWriter extends CsvResponseWriter {

    private static final Logger log = LoggerFactory.getLogger(VoteCsvWriter.class);

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Proposal id",
            "Contest name",
            "Proposal link",
            "ProposalName",
            "Member ID",
            "screenName",
            "firstName",
            "lastName",
            "Activity",
            "Login Ip",
            "Country of login",
            "Region of login ",
            "City of login (inaccurate)",
            "Registration date",
            "Social media login",
            "Email address",
            "Email is verified",
            "Email bounced",
            "Vote date",
            "Voter IP",
            "Voter country",
            "Voter region",
            "Voter city (inaccurate)",
            "Voter browser",
            "confirmationEmailSendDate",
            "initialValidationResult",
            "lastValidationResult",
            "Vote is valid (generated)",
            "Vote is valid (manual override)",
            "manualValidationResult");

    public VoteCsvWriter(HttpServletResponse response) throws IOException {
        super("votingReport", COLUMN_NAMES, response);
    }

    public void writeVotes(List<IProposalVote> proposalVotes) {
        final String colabUrl = PlatformAttributeKey.COLAB_URL.get();

        //local caches, since many votes will likely be in the same contest
        Map<Long, ContestWrapper> contests  = new HashMap<>();
        Map<Long, ContestPhaseWrapper> phases  = new HashMap<>();
        Map<Long, ProposalWrapper> proposals  = new HashMap<>();

        for (IProposalVote vote : proposalVotes) {
            ContestPhaseWrapper contestPhase = phases.computeIfAbsent(vote.getContestPhaseId(),
                    StaticContestContext.getContestClient()::getContestPhase);
            ContestWrapper contest = contests.computeIfAbsent(contestPhase.getContestId(),
                    StaticContestContext.getContestClient()::getContest);

            UserWrapper member = getMemberOrNull(vote);
            ProposalWrapper proposal = getProposalOrNull(proposals, vote);

            List<String> row = new ArrayList<>();
            addValue(row, vote.getProposalId());
            addValue(row, contest.getTitle());
            if (proposal != null) {
                final String proposalUrl =
                        colabUrl + proposal.getProposalLinkUrl(contest, vote.getContestPhaseId());
                addValue(row, proposalUrl);
                addValue(row, proposal.getName());
            } else {
                addValue(row, "Proposal not found");
                addValue(row, "Proposal not found");
            }
            addValue(row, vote.getUserId());
            addValue(row, member != null ? member.getScreenName() : "Member not found");
            addValue(row, member != null ? member.getFirstName() : "Member not found");
            addValue(row, member != null ? member.getLastName() : "Member not found");
            addValue(row, member != null ? StaticActivityContext.getActivityClient()
                    .countActivities(member.getId(), null) : "Member not found");
            addValue(row, member != null ? member.getLoginIp() : "Member not found");
            addLocationForIp(row, member != null ? member.getLoginIp() : null);

            addValue(row, member != null ? member.getCreatedAt() : "Member not found");
            addValue(row, member != null ? member.hasLinkedSocialAccount() : "Member not found");
            addValue(row, member != null ? member.getEmailAddress() : "Member not found");
            addValue(row, member != null ? member.isIsEmailConfirmed() : "Member not found");
            addValue(row, member != null ? member.isIsEmailBounced() : "Member not found");
            addValue(row, vote.getCreatedAt());
            addValue(row, vote.getVoterIp());
            addLocationForIp(row, vote.getVoterIp());
            addValue(row, vote.getVoterUserAgent());
            addValue(row, vote.getConfirmationEmailSendDate() != null ? vote.getConfirmationEmailSendDate() : "");
            addValue(row, vote.getInitialValidationResult());
            addValue(row, vote.getLastValidationResult());
            addValue(row, vote.isIsValid());
            addValue(row, vote.isIsValidOverride() != null ? vote.isIsValidOverride() : "");
            addValue(row, vote.getManualValidationResult() != null ? vote.getManualValidationResult() : "");
            writeRow(row);
        }
    }

    private void addLocationForIp(List<String> row, String ipAddress) {
        ILocation loginLocation = null;
        if (StringUtils.isNotEmpty(ipAddress)) {
            loginLocation = StaticTrackingContext.getTrackingClient().getLocationForIp(ipAddress);
        }
        if (loginLocation != null) {
            addValue(row, loginLocation.getCountryNameInEnglish());
            addValue(row, loginLocation.getRegion());
            addValue(row, loginLocation.getCity());
        } else {
            addValue(row, "unknown");
            addValue(row, "unknown");
            addValue(row, "unknown");
        }
    }

    private ProposalWrapper getProposalOrNull(Map<Long, ProposalWrapper> proposals, IProposalVote vote) {
        try {
            return proposals.computeIfAbsent(vote.getProposalId(),
                    StaticProposalContext.getProposalClient()::getProposal);
        } catch (ProposalNotFoundException e) {
            log.warn("Proposal {} not found when generating report", vote.getProposalId());
            return null;
        }
    }

    private UserWrapper getMemberOrNull(IProposalVote vote) {
        try {
            return StaticUserContext.getUserClient().getMember(vote.getUserId());
        } catch (MemberNotFoundException e) {
            log.warn("Member {} not found when generating report", vote.getUserId());
            return null;
        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
