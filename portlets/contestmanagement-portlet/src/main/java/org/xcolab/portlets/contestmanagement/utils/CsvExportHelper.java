package org.xcolab.portlets.contestmanagement.utils;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.util.CollectionUtils;

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;

import org.xcolab.client.admin.enums.ConfigurationAttributeKey;
import org.xcolab.client.contest.ContestClientUtil;
import org.xcolab.client.contest.exceptions.ContestNotFoundException;
import org.xcolab.client.contest.pojo.Contest;
import org.xcolab.client.contest.pojo.phases.ContestPhase;
import org.xcolab.client.contest.pojo.phases.ContestPhaseType;
import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.ProposalPhaseClientUtil;
import org.xcolab.client.proposals.exceptions.Proposal2PhaseNotFoundException;
import org.xcolab.client.proposals.pojo.Proposal;
import org.xcolab.client.proposals.pojo.ProposalTeamMember;
import org.xcolab.client.proposals.pojo.phases.Proposal2Phase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;

public class CsvExportHelper {

    private final static String URL_DOMAIN = ConfigurationAttributeKey.COLAB_URL.get();
    private final List<String[]> records = new ArrayList<>();

    public CsvExportHelper() {
    }

    private String getCSVData() throws IOException {
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR, CSVWriter.DEFAULT_QUOTE_CHARACTER);
        csvWriter.writeAll(records);
        csvWriter.close();
        String separatorIndicationForExcel = "sep=" + CSVWriter.DEFAULT_SEPARATOR + CSVWriter.DEFAULT_LINE_END;
        return separatorIndicationForExcel + writer.toString();
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
            List<Proposal> proposals, ContestPhase contestPhase) {
        for (Proposal proposal : proposals) {
            List<String[]> proposalAndAuthorDetailsRows =
                    generateProposalAndAuthorDetailsRows(proposal, contestPhase);
            if (!CollectionUtils.isEmpty(proposalAndAuthorDetailsRows)) {
                records.addAll(proposalAndAuthorDetailsRows);
            }
        }
    }

    private List<String[]> generateProposalAndAuthorDetailsRows(Proposal proposal, ContestPhase contestPhase) {

        try {
            Proposal2Phase proposal2Phase = ProposalPhaseClientUtil
                    .getProposal2PhaseByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
            Proposal proposalWrapper = getProposalWithLatestVersionInContestPhase(proposal2Phase, proposal);
            Long contestId = contestPhase.getContestPK();

            Contest contest = ContestClientUtil.getContest(contestId);

            String contestTitle = normalizeApostrophes(contest.getContestShortName());
            String proposalTitle = normalizeApostrophes(proposalWrapper.getName());
            String proposalLink = URL_DOMAIN + proposalWrapper.getProposalUrl();
            String lastPhaseTitle = getContestPhaseTitle(contestPhase);

            List<ProposalTeamMember> proposalTeam = proposalWrapper.getMembers();
            List<String[]> proposalExportData = new ArrayList<>();
            for (ProposalTeamMember teamMemberWrapper : proposalTeam) {
                String[] csvRow =
                        generateProposalAndUserDetailsRow(contestTitle, proposalTitle, proposalLink, teamMemberWrapper,
                                lastPhaseTitle);
                proposalExportData.add(csvRow);
            }
            return proposalExportData;
        } catch (ContestNotFoundException| Proposal2PhaseNotFoundException ignored) {

        }
        return null;
    }

    private static Proposal getProposalWithLatestVersionInContestPhase(
            Proposal2Phase proposal2Phase, Proposal proposal) {
        if (proposal2Phase.getVersionTo() == -1 || proposal2Phase.getVersionFrom() == 0) {
            return (proposal);
        }
        return new Proposal(proposal, proposal2Phase.getVersionTo());
    }

    private static String normalizeApostrophes(String stringToBeCleaned) {
        return stringToBeCleaned.replace("`", "'").replace("’", "'");
    }

    private static String getContestPhaseTitle(ContestPhase contestPhase) {
        Long contestPhaseTypeId = contestPhase.getContestPhaseType();
        ContestPhaseType contestPhaseType = ContestClientUtil.getContestPhaseType(contestPhaseTypeId);
        return contestPhaseType.getName();
    }

    private String[] generateProposalAndUserDetailsRow(String contestTitle, String proposalTitle,
                                                       String proposalLink, ProposalTeamMember teamMember,
                                                       String lastPhaseTitle) {
        Member member = MembersClient.getMemberUnchecked(teamMember.getUserId());
        String username = member.getScreenName();
        String firstName = member.getFullName();
        String lastName = member.getLastName();
        String emailAddress = member.getEmailAddress();
        String role = teamMember.getMemberType();

        return new String[]{contestTitle, proposalTitle, proposalLink, username, firstName,
                lastName, emailAddress, role, lastPhaseTitle};

    }

    public void initiateDownload(String downloadFileName, PortletRequest request,
                                 ResourceResponse response) throws IOException {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(themeDisplay.getPortalURL());

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String csvPayload = getCSVData();
        outputStream.write(csvPayload.getBytes());

        response.setContentType("application/csv");
        response.addProperty(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
        response.setProperty(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFileName + ".csv");
        response.setContentLength(outputStream.size());
        OutputStream out = response.getPortletOutputStream();
        outputStream.writeTo(out);
        out.flush();
        out.close();
    }

}
