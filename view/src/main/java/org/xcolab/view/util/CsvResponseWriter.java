package org.xcolab.view.util;

import au.com.bytecode.opencsv.CSVWriter;
import org.apache.commons.collections4.ListUtils;
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
            csvWriter.writeNext(cleanRow(headerRow));
        } catch (IOException e) {
            // make sure the streams get closed if we get an exception while writing
            csvWriter.close();
            throw e;
        }
    }

    /**
     * Writes a row of values to the list.
     *
     * The row must have exactly as many columns as defined in the constructor of this class
     * or an exception will be thrown.
     *
     * @param cols A list of columns for this row.
     *
     * @throws IllegalArgumentException if {@code cols.size() != numColumns}
     * @throws NullPointerException if cols is null
     */
    protected void writeRow(List<String> cols) {
        writeRow(cols, null);
    }

    /**
     * Writes a new row with optional extra columns.
     *
     * While the cols parameter has to match the count of headers defined,
     * the extraCols parameters can be of any length (or null).
     * Its values, if present, will simply be appended to the end of the list.
     *
     * @param cols A list of default columns (matching the headers)
     * @param extraCols An optional list of appended columns that don't match any header
     *
     * @throws IllegalArgumentException if {@code cols.size() != numColumns}
     * @throws NullPointerException if cols is null
     */
    protected void writeRow(List<String> cols, List<String> extraCols) {
        checkLength(cols);
        List<String> fullRow = extraCols == null ? cols : ListUtils.union(cols, extraCols);
        csvWriter.writeNext(cleanRow(fullRow));
    }

    private void checkLength(List<String> cols) {
        if (cols.size() != numColumns) {
            throw new IllegalArgumentException(
                    "Illegal column length: " + cols.size() + " - expected " + numColumns);
        }
    }

    private String[] cleanRow(List<String> row) {
        return row.stream()
                .map(this::clean)
                .toArray(String[]::new);
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
