package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.templates.PlanSectionDefinition;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalTeamMember;
import org.xcolab.commons.CsvResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

public class ProposalCsvWriter extends CsvResponseWriter {

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Contest id",
            "Contest name",
            "Proposal id",
            "Proposal link",
            "Proposal name",
            "Proposal team name",
            "Proposal team size",
            "Proposal team members (IDs)",
            "Proposal team members (names)",
            "Proposal author (ID)",
            "Proposal author (name)",
            "Proposal comments",
            "Proposal supports",
            "Proposal votes",
            "Proposal create date",
            "Proposal update date",
            "Proposal pitch"
    );

    public ProposalCsvWriter(HttpServletResponse response) throws IOException {
        super("proposalReport", COLUMN_NAMES, response);
    }

    public void writeProposalsInContest(Contest contest) {
        final String colabUrl = PlatformAttributeKey.COLAB_URL.get();

        List<Proposal> proposals = ProposalClientUtil.listProposals(contest.getContestPK());

        for (Proposal proposal : proposals) {
            List<String> row = new ArrayList<>();
            addValue(row, contest.getContestPK());
            addValue(row, contest.getContestShortName());

            addValue(row, proposal.getProposalId());
            final String proposalUrl = colabUrl + proposal.getProposalLinkUrl(contest);
            addValue(row, proposalUrl);
            addValue(row, proposal.getName());
            addValue(row, proposal.getTeam());
            addValue(row, proposal.getMembers().size());
            addValue(row, proposal.getMembers().stream()
                    .map(ProposalTeamMember::getUserId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));
            addValue(row, proposal.getMembers().stream()
                    .map(ProposalTeamMember::getFullName)
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));
            addValue(row, proposal.getAuthorId());
            addValue(row, proposal.getAuthorName());
            addValue(row, proposal.getCommentsCount());
            addValue(row, proposal.getSupportersCount());
            addValue(row, proposal.getVotesCount());
            addValue(row, proposal.getCreatedAt());
            addValue(row, proposal.getUpdatedDate());
            addValue(row, proposal.getPitch());

            List<String> sectionContent = new ArrayList<>();
            for (PlanSectionDefinition section:  proposal.getSections()) {
                addValue(sectionContent, "<h1>" + section.getTitle() + "</h1>" + section.getContent());
            }

            writeRow(row, sectionContent);
        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
