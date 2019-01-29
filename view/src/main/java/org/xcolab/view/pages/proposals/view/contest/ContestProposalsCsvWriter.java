package org.xcolab.view.pages.proposals.view.contest;

import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.commons.CsvResponseWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletResponse;

public class ContestProposalsCsvWriter extends CsvResponseWriter {

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Name",
            "Author Name",
            "Author Full Name",
            "Team",
            "Description",
            "Clean Pitch",
            "Pitch",
            "Proposal Review",
            "Rating Comment",
            "Fellow Action Comment",
            "Impact Comment Author",
            "Impact Comment Iaf",
            "All Judges finished Reviews",
            "Vote Count",
            "Supporter Count",
            "Absolute Proposal URL"
    );

    private static final Function<ProposalWrapper, List<String>> COLUMN_EXTRACTION_FUNCTION
            = (proposal -> Arrays.asList(
            proposal.getName(),
            proposal.getAuthorName(),
            proposal.getAuthor().getFullName(),
            proposal.getTeam(),
            proposal.getDescription(),
            proposal.getCleanPitch(),
            proposal.getPitch(),
            proposal.getProposalReview(),
            proposal.getRatingComment(),
            proposal.getFellowActionComment(),
            proposal.getImpactCommentAuthor(),
            proposal.getImpactCommentIaf(),
            String.valueOf(proposal.getAllJudgesReviewFinished()),
            String.valueOf(proposal.getVotesCount()),
            String.valueOf(proposal.getSupportersCount()),
            proposal.getAbsoluteProposalUrl()
    ));

    public ContestProposalsCsvWriter(HttpServletResponse response) throws IOException {
        super("contestProposalsList", COLUMN_NAMES, response);
    }

    public void writeProposals(List<ProposalWrapper> proposals) {
        proposals.stream()
                .map(COLUMN_EXTRACTION_FUNCTION)
                .forEach(this::writeRow);
    }
}
