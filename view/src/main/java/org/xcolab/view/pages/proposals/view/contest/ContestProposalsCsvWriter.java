package org.xcolab.view.pages.proposals.view.contest;

import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.commons.CsvResponseWriter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.servlet.http.HttpServletResponse;

public class ContestProposalsCsvWriter extends CsvResponseWriter {

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Author name", "Description", "Name", "Proposal review", "Rating comment", "Team"
    );
    private static final Function<Proposal, List<String>> COLUMN_EXTRACTION_FUNCTION
            = (proposal -> Arrays.asList(
            proposal.getAuthorName(),
            proposal.getDescription(),
            proposal.getName(),
            proposal.getProposalReview(),
            proposal.getRatingComment(),
            proposal.getTeam()
    ));

    public ContestProposalsCsvWriter(HttpServletResponse response) throws IOException {
        super("proposalList", COLUMN_NAMES, response);
    }

    public void writeMembers(List<Proposal> proposals) {
        proposals.stream()
                .map(COLUMN_EXTRACTION_FUNCTION)
                .forEach(this::writeRow);
    }
}
