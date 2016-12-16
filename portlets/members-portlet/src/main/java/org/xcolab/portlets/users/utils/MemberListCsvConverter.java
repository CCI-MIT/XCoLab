package org.xcolab.portlets.users.utils;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.http.HttpHeaders;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.portlet.ResourceResponse;

//TODO: combine this and CsvExportHelper in CMS
public class MemberListCsvConverter {

    public MemberListCsvConverter() {
    }

    private List<String[]> getMemberList() {
        List<Member> memberList = MembersClient
                .listMembers(null, null, null,
                        null, true, 0, Integer.MAX_VALUE);
        List<String[]> rows = new ArrayList<>(memberList.size());

        for (Member member : memberList) {
            rows.add(new String[]{
                    clean(member.getScreenName()),
                    clean(member.getFirstName()),
                    clean(member.getLastName()),
                    clean(member.getEmailAddress()),
                    clean(member.getCountry())
            });
        }

        return rows;
    }

    private String clean(String string) {
        if (string != null) {
            return replaceNonAsciiCharacters(deAccent(string));
        }
        return "";
    }

    private String deAccent(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    private String replaceNonAsciiCharacters(String str) {
        return str.replaceAll("[^\\x00-\\x7F]", "");
    }

    private String getCSVData() throws IOException {
        StringWriter writer = new StringWriter();
        CSVWriter csvWriter = new CSVWriter(writer, CSVWriter.DEFAULT_SEPARATOR,
                CSVWriter.DEFAULT_QUOTE_CHARACTER);
        csvWriter.writeAll(getMemberList());
        csvWriter.close();
        String separatorIndicationForExcel =
                "sep=" + CSVWriter.DEFAULT_SEPARATOR + CSVWriter.DEFAULT_LINE_END;
        return separatorIndicationForExcel + writer.toString();
    }

    public void initiateDownload(String downloadFileName, ResourceResponse response)
            throws IOException {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        String csvPayload = getCSVData();
        outputStream.write(csvPayload.getBytes());

        response.setContentType("application/csv");
        response.addProperty(HttpHeaders.CACHE_CONTROL, "max-age=3600, must-revalidate");
        response.setProperty(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + downloadFileName + ".csv");
        response.setContentLength(outputStream.size());
        OutputStream out = response.getPortletOutputStream();
        outputStream.writeTo(out);
        out.flush();
        out.close();
    }

}
