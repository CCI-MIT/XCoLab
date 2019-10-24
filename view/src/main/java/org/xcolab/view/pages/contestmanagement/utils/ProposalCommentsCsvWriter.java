package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.pojo.IComment;
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

public class ProposalCommentsCsvWriter extends CsvResponseWriter {

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Contest id",
            "Contest name",
            "Proposal id",
            "Proposal link",
            "Proposal name",
            "Proposal author (ID)",
            "Proposal author (name)",
            "Comment author (ID)",
            "Comment author (name)",
            "Comment",
            "Comment create date"
    );
    public ProposalCommentsCsvWriter(HttpServletResponse response) throws IOException {
        super("proposalCommentReport", COLUMN_NAMES, response);
    }
    public void writeProposalCommentsInContest(ContestWrapper contest) {
        final String colabUrl = PlatformAttributeKey.COLAB_URL.get();

        List<ProposalWrapper> proposals = StaticProposalContext.getProposalClient()
                .listProposals(contest.getId());

        for (ProposalWrapper proposal : proposals) {
            proposal = new ProposalWrapper(proposal);
            List<String> row = new ArrayList<>();

            addValue(row, contest.getId());
            addValue(row, contest.getTitle());

            addValue(row, proposal.getId());
            final String proposalUrl = colabUrl + proposal.getProposalLinkUrl(contest);
            addValue(row, proposalUrl);
            addValue(row, proposal.getName());
            addValue(row, proposal.getAuthorUserId());
            addValue(row, proposal.getAuthorName());
            List<IComment> comments = StaticCommentContext.getCommentClient().listComments(
                     0, Integer.MAX_VALUE, proposal.getDiscussionId());

            List<String> commentRow;
            for(IComment comm: comments){

                commentRow = new ArrayList<>();
                commentRow.addAll(row);
                addValue(commentRow,comm.getAuthorUserId());
                addValue(commentRow, comm.getAuthor().getDisplayName());
                addValue(commentRow, comm.getContentPlain());
                addValue(commentRow, comm.getCreatedAt());
                writeRow(commentRow);
            }


        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
