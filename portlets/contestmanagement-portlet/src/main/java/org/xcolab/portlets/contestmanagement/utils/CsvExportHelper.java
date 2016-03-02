package org.xcolab.portlets.contestmanagement.utils;

import au.com.bytecode.opencsv.CSVWriter;
import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestPhaseType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import org.xcolab.wrappers.BaseProposalTeamMemberWrapper;
import org.xcolab.wrappers.BaseProposalWrapper;

import javax.portlet.PortletRequest;
import javax.portlet.ResourceResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 3/25/2015.
 */
public class CsvExportHelper {

    private final static Log _log = LogFactoryUtil.getLog(CsvExportHelper.class);
    private final static String URL_DOMAIN = "http://www.climatecolab.org";
    private final List<String[]> records;


    public CsvExportHelper() {
        records = new ArrayList<>();
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

    public void addProposalAndAuthorDetailsToExportData(List<Proposal> proposals, ContestPhase contestPhase)
            throws Exception {

        for (Proposal proposal : proposals) {
            try {
                List<String[]> proposalAndAuthorDetailsRows =
                        generateProposalAndAuthorDetailsRows(proposal, contestPhase);
                records.addAll(proposalAndAuthorDetailsRows);
            } catch (SystemException | PortalException e) {
                _log.warn("Failed to export data for csv: ", e);
            }
        }

    }

    private List<String[]> generateProposalAndAuthorDetailsRows(Proposal proposal, ContestPhase contestPhase)
            throws PortalException, SystemException {
        List<String[]> proposalExportData = new ArrayList<>();
        Proposal2Phase proposal2Phase = Proposal2PhaseLocalServiceUtil
                .getByProposalIdContestPhaseId(proposal.getProposalId(), contestPhase.getContestPhasePK());
        BaseProposalWrapper proposalWrapper = getProposalWithLatestVersionInContestPhase(proposal2Phase, proposal);
        Long contestId = contestPhase.getContestPK();
        Contest contest = ContestLocalServiceUtil.getContest(contestId);
        String contestTitle = normalizeApostrophes(contest.getContestShortName());
        String proposalTitle = normalizeApostrophes(proposalWrapper.getName());
        String proposalLink = URL_DOMAIN + proposalWrapper.getProposalUrl();
        String lastPhaseTitle = getContestPhaseTitle(contestPhase);

        List<BaseProposalTeamMemberWrapper> proposalTeam = proposalWrapper.getMembers();
        for (BaseProposalTeamMemberWrapper teamMemberWrapper : proposalTeam) {
            String[] csvRow =
                    generateProposalAndUserDetailsRow(contestTitle, proposalTitle, proposalLink, teamMemberWrapper,
                            lastPhaseTitle);
            proposalExportData.add(csvRow);
        }
        return proposalExportData;
    }

    private static BaseProposalWrapper getProposalWithLatestVersionInContestPhase(Proposal2Phase proposal2Phase,
            Proposal proposal) throws NoSuchContestException {
        if (proposal2Phase.getVersionTo() == -1 || proposal2Phase.getVersionFrom() == 0) {
            return new BaseProposalWrapper(proposal);
        }
        return new BaseProposalWrapper(proposal, proposal2Phase.getVersionTo());
    }

    private static String normalizeApostrophes(String stringToBeCleaned) {
        return stringToBeCleaned.replace("`", "'").replace("’", "'");
    }

    private static String getContestPhaseTitle(ContestPhase contestPhase) throws PortalException, SystemException {
        Long contestPhaseTypeId = contestPhase.getContestPhaseType();
        ContestPhaseType contestPhaseType = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(contestPhaseTypeId);
        return contestPhaseType.getName();
    }

    private String[] generateProposalAndUserDetailsRow(String contestTitle, String proposalTitle,
            String proposalLink, BaseProposalTeamMemberWrapper member,
            String lastPhaseTitle) throws PortalException, SystemException {
        User user = UserLocalServiceUtil.getUser(member.getUserId());
        String username = user.getScreenName();
        String firstName = user.getFullName();
        String lastName = user.getLastName();
        String emailAddress = user.getEmailAddress();
        String role = member.getMemberType();

        return new String[]{contestTitle
                , proposalTitle
                , proposalLink
                , username
                , firstName
                , lastName
                , emailAddress
                , role
                , lastPhaseTitle};

    }

    public void initiateDownload(String downloadFileName, PortletRequest request, ResourceResponse response)
            throws Exception {

        ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setPortalURL(themeDisplay.getPortalURL());

        try {
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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
