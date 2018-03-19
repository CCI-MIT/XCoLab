package org.xcolab.commons;

public class SortColumn {

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
