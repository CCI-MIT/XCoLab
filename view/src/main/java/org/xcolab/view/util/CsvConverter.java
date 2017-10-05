package org.xcolab.view.util;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.http.HttpHeaders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

/**
 * Converter to invert a List into a CSV file.
 */
public class CsvConverter {

    private final int numColumns;
    private final List<String[]> rows;

    public CsvConverter(int numColumns, int numRows) {
        this.numColumns = numColumns;
        this.rows = new ArrayList<>(numRows);
    }

    public CsvConverter(int numColumns) {
        this.numColumns = numColumns;
        this.rows = new ArrayList<>();
    }

    protected void addRow(List<String> cols) {
        checkLength(cols);
        final String[] colArray = cols.stream().map(this::clean).collect(Collectors.toList())
                .toArray(new String[numColumns]);
        rows.add(colArray);
    }

    private void checkLength(List<String> cols) {
        if (cols.size() != numColumns) {
            throw new IllegalArgumentException(
                    "Illegal column length: " + cols.size() + " - expected " + numColumns);
        }
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
        csvWriter.writeAll(rows);
        csvWriter.close();
        String separatorIndicationForExcel =
                "sep=" + CSVWriter.DEFAULT_SEPARATOR + CSVWriter.DEFAULT_LINE_END;
        return separatorIndicationForExcel + writer.toString();
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

}
