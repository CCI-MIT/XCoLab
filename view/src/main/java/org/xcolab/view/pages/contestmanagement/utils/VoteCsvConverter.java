package org.xcolab.view.pages.contestmanagement.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.enums.PlatformAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.exceptions.MemberNotFoundException;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.exceptions.ProposalNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.client.tracking.TrackingClient;
import org.xcolab.client.tracking.pojo.Location;
import org.xcolab.view.util.CsvConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteCsvConverter extends CsvConverter {

    private static final Logger log = LoggerFactory.getLogger(VoteCsvConverter.class);

    private static final int NUM_COLUMNS = 19;
    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Proposal id",
            "Contest name",
            "Proposal link",
            "ProposalName",
            "Member ID",
            "screenName",
            "firstName",
            "lastName",
            "Login Ip",
            "Country of login",
            "Region of login ",
            "City of login (inaccurate)",
            "Registration date",
            "Social media login",
            "Email address",
            "Email is verified",
            "Email bounced",
            "vote_is_valid",
            "confirmationEmailSendDate");

    public VoteCsvConverter() {
        super(NUM_COLUMNS);
        addRow(COLUMN_NAMES);
    }

    public void addVotes(List<ProposalVote> proposalVotes) {
        final String colabUrl = PlatformAttributeKey.PLATFORM_COLAB_URL.get();

        //local caches, since many votes will likely be in the same contest
        Map<Long, Contest> contests  = new HashMap<>();
        Map<Long, ContestPhase> phases  = new HashMap<>();
        Map<Long, Proposal> proposals  = new HashMap<>();

        for (ProposalVote vote : proposalVotes) {
            ContestPhase contestPhase = phases.computeIfAbsent(vote.getContestPhaseId(),
                    ContestClientUtil::getContestPhase);
            Contest contest = contests.computeIfAbsent(contestPhase.getContestPK(),
                    ContestClientUtil::getContest);

            Member member = getMemberOrNull(vote);
            Proposal proposal = getProposalOrNull(proposals, vote);

            List<String> row = new ArrayList<>();
            addValue(row, vote.getProposalId());
            addValue(row, contest.getContestShortName());
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
            addValue(row, member != null ? member.getLoginIP() : "Member not found");

            Location loginLocation = null;
            if (member != null && StringUtils.isNotEmpty(member.getLoginIP())) {
                loginLocation = TrackingClient.getLocationForIp(member.getLoginIP());
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
            addValue(row, member != null ? member.getCreateDate() : "Member not found");
            addValue(row, member != null ? member.hasLinkedSocialAccount() : "Member not found");
            addValue(row, member != null ? member.getEmailAddress() : "Member not found");
            addValue(row, member != null ? member.getIsEmailConfirmed() : "Member not found");
            //TODO: add bounced emails
            addValue(row, "unknown");
            addValue(row, vote.getIsValid());
            addValue(row, vote.getConfirmationEmailSendDate());
            addRow(row);
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
