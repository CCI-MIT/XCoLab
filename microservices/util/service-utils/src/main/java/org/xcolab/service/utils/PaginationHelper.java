package org.xcolab.service.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PaginationHelper {

    private static final int DEFAULT_PAGE_SIZE = 20;

    public static final PaginationHelper EVERYTHING =
            new PaginationHelper(0, Integer.MAX_VALUE, "");

    private final int startRecord;
    private final int limitRecord;
    private final List<SortColumn> sortColumns = new ArrayList<>();

    public PaginationHelper(Integer startRecord, Integer limitRecord, String sort) {
        this.startRecord = startRecord != null ? startRecord : 0;
        this.limitRecord = limitRecord != null ? limitRecord : this.startRecord + DEFAULT_PAGE_SIZE;
        if (this.limitRecord < this.startRecord) {
            throw new IllegalArgumentException("limitRecord can't be smaller than the startRecord");
        }
        if (StringUtils.isNotBlank(sort)) {
            for (String sortString : sort.split(",")) {
                sortColumns.add(new SortColumn(sortString));
            }
        }
    }

    public int getStartRecord() {
        return startRecord;
    }

    public int getLimitRecord() {
        return limitRecord;
    }

    public List<SortColumn> getSortColumns() {
        return sortColumns;
    }

    public boolean containsSortColumn(String columnName) {
        for (SortColumn sortColumn : sortColumns) {
            if (sortColumn.getColumnName().equalsIgnoreCase(columnName)) {
                return true;
            }
        }
        return false;
    }

    public static class SortColumn {

        private final boolean isAscending;
        private final String columnName;

        public SortColumn(String sortString) {
            if (sortString.startsWith("-")) {
                isAscending = false;
                columnName = sortString.substring(1);
            } else {
                isAscending = true;
                columnName = sortString;
            }
        }

        public boolean isAscending() {
            return isAscending;
        }

        public String getColumnName() {
            return columnName;
        }
    }
}
