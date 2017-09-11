package org.xcolab.view.pages.contestmanagement.entities.massactions;

import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.view.pages.contestmanagement.utils.ContestMassActionMethods;
import org.xcolab.view.pages.contestmanagement.utils.CsvExportHelper;
import org.xcolab.view.pages.contestmanagement.wrappers.ContestOverviewWrapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReportPeopleInCurrentPhaseMassAction extends ContestMassActionAdapter {

    private static final List<String> CSV_EXPORT_HEADER =
            Arrays.asList("Contest", "Proposal Title", "Proposal Link", "Username", "First Name",
                    "Last Name", "Email Address", "Role", "Last phase");

    public ReportPeopleInCurrentPhaseMassAction() {
        super("Generate report of contributors in active phase");
    }

    @Override
    public void execute(List<Contest> contests, boolean actionConfirmed,
            MassActionDataWrapper dataWrapper, HttpServletResponse response) throws IOException {
        CsvExportHelper csvExportHelper = new CsvExportHelper();
        csvExportHelper.addRowToExportData(CSV_EXPORT_HEADER);

        // TODO: Fix intermediate solution.
        for (Contest contest : contests) {
            Long contestId = contest.getContestPK();
            if (!contest.getIsSharedContestInForeignColab()) {
                List<Proposal> proposalsInActiveContestPhase =
                        ContestMassActionMethods.getProposalsInActiveContestPhase(contestId);
                ContestPhase activeContestPhase = ContestClientUtil.getActivePhase(contestId);
                csvExportHelper
                        .addProposalAndAuthorDetailsToExportData(proposalsInActiveContestPhase,
                                activeContestPhase);
            }
        }

        String exportFileName = "reportOfPeopleInCurrentPhase";
        csvExportHelper.initiateDownload(exportFileName, response);
    }
}
