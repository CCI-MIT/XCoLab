package org.xcolab.view.pages.contestmanagement.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
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
import org.xcolab.view.util.CsvConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteCsvConverter extends CsvConverter {

    private static final Logger log = LoggerFactory.getLogger(VoteCsvConverter.class);

    private static final int NUM_COLUMNS = 16;
    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Proposal id",
            "Contest name",
            "Proposal link",
            "ProposalName",
            "Member ID",
            "screenName",
            "firstName",
            "lastName",
            "loginIp",
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
        final String colabUrl = ConfigurationAttributeKey.COLAB_URL.get();

        //local caches, since many votes will likely be in the same contest
        Map<Long, Contest> contests  = new HashMap<>();
        Map<Long, ContestPhase> phases  = new HashMap<>();
        Map<Long, Proposal> proposals  = new HashMap<>();

        for (ProposalVote vote : proposalVotes) {
            ContestPhase contestPhase = phases.computeIfAbsent(vote.getContestPhaseId(),
                    ContestClientUtil::getContestPhase);
            Contest contest = contests.computeIfAbsent(contestPhase.getContestPK(),
                    ContestClientUtil::getContest);

            Member member;
            try {
                member = MembersClient.getMember(vote.getUserId());
            } catch (MemberNotFoundException e) {
                log.warn("Member {} not found when generating report", vote.getUserId());
                member = null;
            }
            Proposal proposal;
            try {
                proposal = proposals.computeIfAbsent(vote.getProposalId(),
                        ProposalClientUtil::getProposal);
            } catch (ProposalNotFoundException e) {
                log.warn("Proposal {} not found when generating report", vote.getProposalId());
                proposal = null;
            }

            List<String> row = new ArrayList<>();
            addValue(row, vote.getProposalId());
            addValue(row, contest.getContestShortName());
            if (proposal != null) {
                addValue(row,
                        colabUrl + proposal.getProposalLinkUrl(contest, vote.getContestPhaseId()));
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

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
