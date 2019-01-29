package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMemberWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTemplateSectionDefinitionWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
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

    public void writeProposalsInContest(ContestWrapper contest) {
        final String colabUrl = PlatformAttributeKey.COLAB_URL.get();

        List<ProposalWrapper> proposals = StaticProposalContext.getProposalClient()
                .listProposals(contest.getId());

        for (ProposalWrapper proposal : proposals) {
            List<String> row = new ArrayList<>();
            addValue(row, contest.getId());
            addValue(row, contest.getTitle());

            addValue(row, proposal.getId());
            final String proposalUrl = colabUrl + proposal.getProposalLinkUrl(contest);
            addValue(row, proposalUrl);
            addValue(row, proposal.getName());
            addValue(row, proposal.getTeam());
            addValue(row, proposal.getMembers().size());
            addValue(row, proposal.getMembers().stream()
                    .map(ProposalTeamMemberWrapper::getUserId)
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));
            addValue(row, proposal.getMembers().stream()
                    .map(ProposalTeamMemberWrapper::getFullName)
                    .map(String::valueOf)
                    .collect(Collectors.joining(",")));
            addValue(row, proposal.getAuthorUserId());
            addValue(row, proposal.getAuthorName());
            addValue(row, proposal.getCommentsCount());
            addValue(row, proposal.getSupportersCount());
            addValue(row, proposal.getVotesCount());
            addValue(row, proposal.getCreatedAt());
            addValue(row, proposal.getUpdatedAt());
            addValue(row, proposal.getPitch());

            List<String> sectionContent = new ArrayList<>();
            for (ProposalTemplateSectionDefinitionWrapper section:  proposal.getSections()) {
                addValue(sectionContent, "<h1>" + section.getTitle() + "</h1>" + section.getContent());
            }

            writeRow(row, sectionContent);
        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
