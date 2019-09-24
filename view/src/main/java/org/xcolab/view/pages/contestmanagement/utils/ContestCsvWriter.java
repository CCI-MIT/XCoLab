package org.xcolab.view.pages.contestmanagement.utils;

import org.xcolab.client.admin.attributes.platform.PlatformAttributeKey;
import org.xcolab.client.contest.pojo.wrapper.ContestWrapper;
import org.xcolab.client.contest.pojo.wrapper.OntologyTermWrapper;
import org.xcolab.commons.CsvResponseWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

public class ContestCsvWriter extends CsvResponseWriter {

    private static final List<String> COLUMN_NAMES = Arrays.asList(
            "Contest id",
            "Contest link",
            "Contest name",
            "Contest description",
            "What terms (ids)",
            "What terms",
            "Where terms (ids)",
            "Where terms",
            "Who terms (ids)",
            "Who terms",
            "How terms (ids)",
            "How terms"
    );

    public ContestCsvWriter(HttpServletResponse response) throws IOException {
        super("contestReport", COLUMN_NAMES, response);
    }

    public void writeContests(Collection<ContestWrapper> contests) {
        final String colabUrl = PlatformAttributeKey.COLAB_URL.get();

        for (ContestWrapper contest : contests) {

            List<String> row = new ArrayList<>();

            addValue(row, contest.getId());
            addValue(row, colabUrl + contest.getContestUrl());
            addValue(row, contest.getTitle());
            addValue(row, contest.getQuestion());

            addValue(row, getIdString(contest.getWhat()));
            addValue(row, getNames(contest.getWhat()));

            addValue(row, getIdString(contest.getWhere()));
            addValue(row, getNames(contest.getWhere()));

            addValue(row, getIdString(contest.getWho()));
            addValue(row, getNames(contest.getWho()));

            addValue(row, getIdString(contest.getHow()));
            addValue(row, getNames(contest.getHow()));

            writeRow(row);
        }
    }

    private String getNames(Collection<OntologyTermWrapper> terms) {
        if (terms == null) {
            return "";
        }
        return terms.stream()
                .map(OntologyTermWrapper::getName)
                .collect(Collectors.joining("; "));
    }

    private String getIdString(Collection<OntologyTermWrapper> terms) {
        if (terms == null) {
            return "";
        }
        return terms.stream()
                .map(OntologyTermWrapper::getId)
                .map(String::valueOf)
                .collect(Collectors.joining("; "));
    }

    private void addValue(List<String> list, Object value) {
        list.add(String.valueOf(value));
    }
}
