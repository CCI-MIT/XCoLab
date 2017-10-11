package org.xcolab.view.util;

import au.com.bytecode.opencsv.CSVWriter;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.util.Assert;

import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.Normalizer;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

/**
 * Generic class to write data to a response as CSV file.
 *
 * Sub-classes provide mechanisms to convert objects to row entries.
 */
public abstract class CsvResponseWriter implements Closeable {

    private static final String EXCEL_SEPARATOR_META_DATA =
            "sep=" + CSVWriter.DEFAULT_SEPARATOR + CSVWriter.DEFAULT_LINE_END;

    private final int numColumns;

    private final CSVWriter csvWriter;

    public CsvResponseWriter(String fileName, List<String> headerRow,
            HttpServletResponse response) throws IOException {
        Assert.notEmpty(headerRow, "The CSV converter must have at least one entry");
        this.numColumns = headerRow.size();

        writeResponseMetaData(fileName, response);

        Writer outWriter = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
        csvWriter = new CSVWriter(outWriter);

        writeCsvHeader(headerRow, outWriter);
    }

    private void writeResponseMetaData(String downloadFileName, HttpServletResponse response) {
        response.setContentType("application/csv");
        response.addHeader(HttpHeaders.CACHE_CONTROL, CacheControl.maxAge(1, TimeUnit.HOURS)
                .mustRevalidate().getHeaderValue());
        response.addHeader(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=" + downloadFileName + ".csv");
    }

    private void writeCsvHeader(List<String> headerRow, Writer outWriter) throws IOException {
        try {
            // Excel needs this as different regional versions except different separators
            outWriter.append(EXCEL_SEPARATOR_META_DATA);
            csvWriter.writeNext(headerRow.toArray(new String[headerRow.size()]));
        } catch (IOException e) {
            // make sure the streams get closed if we get an exception while writing
            csvWriter.close();
            throw e;
        }
    }

    protected void writeRow(List<String> cols) {
        checkLength(cols);
        final String[] colArray = cols.stream()
                .map(this::clean)
                .collect(Collectors.toList())
                .toArray(new String[numColumns]);
        csvWriter.writeNext(colArray);
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

    @Override
    public void close() throws IOException {
        csvWriter.close();
    }
}
