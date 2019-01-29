package org.xcolab.view.pages.contestmanagement.utils;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.StaticContestContext;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.IContestPhaseType;
import org.xcolab.client.contest.pojo.IProposal2Phase;
import org.xcolab.client.contest.pojo.wrapper.ContestPhaseWrapper;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalTeamMemberWrapper;
import org.xcolab.client.contest.pojo.wrapper.ProposalWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

public class CsvExportHelper {

    private static final String URL_DOMAIN = PlatformAttributeKey.COLAB_URL.get();
    private final List<String[]> records = new ArrayList<>();

    public CsvExportHelper() {
    }

    public void addRowToExportData(List<String> rowData) {
        String[] rowDataArr = new String[rowData.size()];
        rowDataArr = rowData.toArray(rowDataArr);
        addRowToExportData(rowDataArr);
    }

    private void addRowToExportData(String[] rowData) {
        records.add(rowData);
    }

    public void addProposalAndAuthorDetailsToExportData(
            List<ProposalWrapper> proposals, ContestPhaseWrapper contestPhase) {
        for (ProposalWrapper proposal : proposals) {
            List<String[]> proposalAndAuthorDetailsRows =
                    generateProposalAndAuthorDetailsRows(proposal, contestPhase);
            if (!CollectionUtils.isEmpty(proposalAndAuthorDetailsRows)) {
                records.addAll(proposalAndAuthorDetailsRows);
            }
        }
    }

    private List<String[]> generateProposalAndAuthorDetailsRows(ProposalWrapper proposal,
            ContestPhaseWrapper contestPhase) {

        try {
            IProposal2Phase proposal2Phase = StaticProposalContext.getProposalPhaseClient()
                    .getProposal2PhaseByProposalIdContestPhaseId(proposal.getId(),
                            contestPhase.getId());
            ProposalWrapper proposalWrapper =
                    getProposalWithLatestVersionInContestPhase(proposal2Phase, proposal);
            Long contestId = contestPhase.getContestId();

            ContestWrapper contest = StaticContestContext.getContestClient().getContest(contestId);

            String contestTitle = normalizeApostrophes(contest.getTitle());
            String proposalTitle = normalizeApostrophes(proposalWrapper.getName());
            String proposalLink = URL_DOMAIN + proposalWrapper.getProposalUrl();
            String lastPhaseTitle = getContestPhaseTitle(contestPhase);

            List<ProposalTeamMemberWrapper> proposalTeam = proposalWrapper.getMembers();
            List<String[]> proposalExportData = new ArrayList<>();
            for (ProposalTeamMemberWrapper teamMemberWrapper : proposalTeam) {
                String[] csvRow =
                        generateProposalAndUserDetailsRow(contestTitle, proposalTitle, proposalLink,
                                teamMemberWrapper,
                                lastPhaseTitle);
                proposalExportData.add(csvRow);
            }
            return proposalExportData;
        } catch (ContestNotFoundException | Proposal2PhaseNotFoundException ignored) {

        }
        return null;
    }

    private static ProposalWrapper getProposalWithLatestVersionInContestPhase(
            IProposal2Phase proposal2Phase, ProposalWrapper proposal) {
        if (proposal2Phase.getVersionTo() == -1 || proposal2Phase.getVersionFrom() == 0) {
            return (proposal);
        }
        return new ProposalWrapper(proposal, proposal2Phase.getVersionTo());
    }

    private static String normalizeApostrophes(String stringToBeCleaned) {
        return stringToBeCleaned.replace("`", "'").replace("’", "'");
    }

    private static String getContestPhaseTitle(ContestPhaseWrapper contestPhase) {
        Long contestPhaseTypeId = contestPhase.getContestPhaseTypeId();
        IContestPhaseType contestPhaseType =
                StaticContestContext.getContestClient().getContestPhaseType(contestPhaseTypeId);
        return contestPhaseType.getName();
    }

    private String[] generateProposalAndUserDetailsRow(String contestTitle, String proposalTitle,
            String proposalLink, ProposalTeamMemberWrapper teamMember,
            String lastPhaseTitle) {
        UserWrapper member = StaticUserContext.getUserClient().getMemberUnchecked(teamMember.getUserId());
        String username = member.getScreenName();
        String firstName = member.getFullName();
        String lastName = member.getLastName();
        String emailAddress = member.getEmailAddress();
        String role = teamMember.getMemberTypeForDisplay();

        return new String[]{contestTitle, proposalTitle, proposalLink, username, firstName,
                lastName, emailAddress, role, lastPhaseTitle};

    }

    public void initiateDownload(String downloadFileName, HttpServletResponse response)
            throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String csvPayload = getCSVData();
        outputStream.write(csvPayload.getBytes());

        response.setContentType("application/csv");
        response.addHeader(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + downloadFileName + ".csv");
        response.setContentLength(outputStream.size());
        OutputStream out = response.getOutputStream();
        outputStream.writeTo(out);
        out.flush();
        out.close();
    }

    private String getCSVData() throws IOException {
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.DEFAULT_QUOTE_CHARACTER);
        csvWriter.writeAll(records);
        csvWriter.close();
        String separatorIndicationForExcel =
                "sep=" + CSVWriter.DEFAULT_SEPARATOR + CSVWriter.DEFAULT_LINE_END;
        return separatorIndicationForExcel + writer.toString();
    }

}
