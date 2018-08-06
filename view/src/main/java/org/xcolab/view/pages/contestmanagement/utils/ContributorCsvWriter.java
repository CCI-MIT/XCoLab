package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalClientUtil;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalTeamMember;
import org.xcolab.client.proposals.pojo.proposals.ProposalRibbon;
import org.xcolab.commons.CsvResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import javax.servlet.http.HttpServletResponse;

public class ContributorCsvWriter extends CsvResponseWriter {

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Contest id",
            "Contest name",
            "Proposal id",
            "Proposal link",
            "Proposal name",
            "Ribbon type",
            "Ribbon name",
            "Member ID",
            "Member screen name",
            "Member first name",
            "Member last name",
            "Member email",
            "Member role"
    );
    private Predicate<Proposal> filterPredicate = proposal -> true;

    public ContributorCsvWriter(HttpServletResponse response) throws IOException {
        super("contributorReport", COLUMN_NAMES, response);
    }

    public void addFilter(Predicate<Proposal> filterPredicate) {
        this.filterPredicate = this.filterPredicate.and(filterPredicate);
    }

    public void writeProposalsInContest(Contest contest) {
        final String colabUrl = PlatformAttributeKey.COLAB_URL.get();

        List<Proposal> proposals = ProposalClientUtil.listProposals(contest.getId());

        for (Proposal proposal : proposals) {
            if (!filterPredicate.test(proposal)) {
                continue;
            }
            final ProposalRibbon ribbonWrapper = proposal.getRibbonWrapper();
            for (ProposalTeamMember teamMember : proposal.getMembers()) {
                List<String> row = new ArrayList<>();
                addValue(row, contest.getId());
                addValue(row, contest.getContestTitle());
                addValue(row, proposal.getId());
                final String proposalUrl = colabUrl + proposal.getProposalLinkUrl(contest);
                addValue(row, proposalUrl);
                addValue(row, proposal.getName());
                addValue(row, ribbonWrapper.getRibbon());
                addValue(row, ribbonWrapper.getRibbonTitle());

                final Member member = teamMember.getMember();
                addValue(row, member.getUserId());
                addValue(row, member.getScreenName());
                addValue(row, member.getFirstName());
                addValue(row, member.getLastName());
                addValue(row, member.getEmailAddress());
                addValue(row, teamMember.getMemberType().getDescription());

                writeRow(row);
            }
        }
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
