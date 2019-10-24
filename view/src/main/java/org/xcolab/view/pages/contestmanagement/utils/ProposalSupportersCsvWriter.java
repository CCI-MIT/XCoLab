package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.comment.StaticCommentContext;
import org.xcolab.client.comment.pojo.IComment;
import org.xcolab.client.contest.pojo.IProposalSupporter;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.exceptions.MemberNotFoundException;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.CsvResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class ProposalSupportersCsvWriter  extends CsvResponseWriter {

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Contest id",
            "Contest name",
            "Proposal id",
            "Proposal link",
            "Proposal name",
            "Proposal author (ID)",
            "Proposal author (name)",
            "Supporter author (ID)",
            "Supporter author (name)",
            "Supporter create date"
    );
    public ProposalSupportersCsvWriter(HttpServletResponse response) throws IOException {
        super("proposalSupportReport", COLUMN_NAMES, response);
    }
    public void writeProposalSupportersInContest(ContestWrapper contest) {
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
            List<IProposalSupporter> supporters =
                    StaticProposalContext.getProposalMemberRatingClient()
                            .getProposalSupporters(proposal.getId());

            List<String> supporterRow;
            for(IProposalSupporter comm: supporters){

                supporterRow = new ArrayList<>();
                supporterRow.addAll(row);
                addValue(supporterRow,comm.getUserId());
                try{
                    UserWrapper user  = StaticUserContext.getUserClient().getUser(comm.getUserId());
                    addValue(supporterRow, user.getDisplayName());
                }catch (MemberNotFoundException e){

                }
                addValue(supporterRow, comm.getCreatedAt());
                writeRow(supporterRow);
            }


        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
