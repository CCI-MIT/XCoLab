package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.evaluation.members.ProposalVote;
import org.xcolab.view.util.CsvConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VoteCsvConverter extends CsvConverter {

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
            List<String> row = new ArrayList<>();
            Member member = MembersClient.getMemberUnchecked(vote.getUserId());
            ContestPhase contestPhase = phases.computeIfAbsent(vote.getContestPhaseId(),
                    ContestClientUtil::getContestPhase);
            Contest contest = contests.computeIfAbsent(contestPhase.getContestPK(),
                    ContestClientUtil::getContest);
            Proposal proposal = proposals.computeIfAbsent(vote.getProposalId(),
                    ProposalClientUtil::getProposal);

            addValue(row, vote.getProposalId());
            addValue(row, contest.getContestShortName());
            addValue(row, colabUrl + proposal.getProposalLinkUrl(contest, vote.getContestPhaseId()));
            addValue(row, proposal.getName());
            addValue(row, member.getUserId());
            addValue(row, member.getScreenName());
            addValue(row, member.getFirstName());
            addValue(row, member.getLastName());
            addValue(row, member.getLoginIP());
            addValue(row, member.getCreateDate());
            addValue(row, member.hasLinkedSocialAccount());
            addValue(row, member.getEmailAddress());
            addValue(row, member.getIsEmailConfirmed());
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
