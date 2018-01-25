package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.util.CsvResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class ProposalCsvWriter extends CsvResponseWriter {

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Proposal id",
            "Contest id",
            "Proposal link",
            "Proposal name",
            "Contest name"
            );

    public ProposalCsvWriter(HttpServletResponse response) throws IOException {
        super("proposalReport", COLUMN_NAMES, response);
    }

    public void writeProposalsInContest(Contest contest) {
        final String colabUrl = PlatformAttributeKey.COLAB_URL.get();

        List<Proposal> proposals = ProposalClientUtil.listProposals(contest.getContestPK());

        for (Proposal proposal : proposals) {
            List<String> row = new ArrayList<>();
            addValue(row, proposal.getProposalId());
            addValue(row, contest.getContestPK());
            final String proposalUrl = colabUrl + proposal.getProposalLinkUrl(contest);
            addValue(row, proposalUrl);
            addValue(row, proposal.getName());
            addValue(row, contest.getContestShortName());

            writeRow(row);
        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
