package org.xcolab.view.pages.contestmanagement.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.contest.proposals.ProposalClientUtil;
import org.xcolab.client.contest.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.contest.pojo.Proposal;
import org.xcolab.client.contest.pojo.ProposalVote;
import org.xcolab.client.tracking.ITrackingClient;
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

    private ITrackingClient trackingClient;

    public VoteCsvWriter(HttpServletResponse response, ITrackingClient trackingClient) throws IOException {
        super("votingReport", COLUMN_NAMES, response);
        this.trackingClient = trackingClient;
    }

    public void writeVotes(List<ProposalVote> proposalVotes) {
        final String colabUrl = PlatformAttributeKey.COLAB_URL.get();

        //local caches, since many votes will likely be in the same contest
        Map<Long, Contest> contests  = new HashMap<>();
        Map<Long, ContestPhase> phases  = new HashMap<>();
        Map<Long, Proposal> proposals  = new HashMap<>();

        for (ProposalVote vote : proposalVotes) {
            ContestPhase contestPhase = phases.computeIfAbsent(vote.getContestPhaseId(),
                    ContestClientUtil::getContestPhase);
            Contest contest = contests.computeIfAbsent(contestPhase.getContestId(),
                    ContestClientUtil::getContest);

            Member member = getMemberOrNull(vote);
            Proposal proposal = getProposalOrNull(proposals, vote);

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
            addValue(row, member != null ? ActivitiesClientUtil.countActivities(
                    member.getId(),null) : "Member not found");
            addValue(row, member != null ? member.getLoginIP() : "Member not found");
            addLocationForIp(row, member != null ? member.getLoginIP() : null);

            addValue(row, member != null ? member.getCreatedAt() : "Member not found");
            addValue(row, member != null ? member.hasLinkedSocialAccount() : "Member not found");
            addValue(row, member != null ? member.getEmailAddress() : "Member not found");
            addValue(row, member != null ? member.getIsEmailConfirmed() : "Member not found");
            addValue(row, member != null ? member.getIsEmailBounced() : "Member not found");
            addValue(row, vote.getCreatedAt());
            addValue(row, vote.getVoterIp());
            addLocationForIp(row, vote.getVoterIp());
            addValue(row, vote.getVoterUserAgent());
            addValue(row, vote.getConfirmationEmailSendDate() != null ? vote.getConfirmationEmailSendDate() : "");
            addValue(row, vote.getInitialValidationResult());
            addValue(row, vote.getLastValidationResult());
            addValue(row, vote.getIsValid());
            addValue(row, vote.getIsValidOverride() != null ? vote.getIsValidOverride() : "");
            addValue(row, vote.getManualValidationResult() != null ? vote.getManualValidationResult() : "");
            writeRow(row);
        }
    }

    private void addLocationForIp(List<String> row, String ipAddress) {
        ILocation loginLocation = null;
        if (StringUtils.isNotEmpty(ipAddress)) {
            loginLocation = trackingClient.getLocationForIp(ipAddress);
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

    private Proposal getProposalOrNull(Map<Long, Proposal> proposals, ProposalVote vote) {
        try {
            return proposals.computeIfAbsent(vote.getProposalId(),
                    ProposalClientUtil::getProposal);
        } catch (ProposalNotFoundException e) {
            log.warn("Proposal {} not found when generating report", vote.getProposalId());
            return null;
        }
    }

    private Member getMemberOrNull(ProposalVote vote) {
        try {
            return MembersClient.getMember(vote.getUserId());
        } catch (MemberNotFoundException e) {
            log.warn("Member {} not found when generating report", vote.getUserId());
            return null;
        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
