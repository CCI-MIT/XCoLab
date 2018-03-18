package org.xcolab.service.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;

import org.xcolab.commons.SortColumn;

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

        Validate.isTrue(this.startRecord >= 0, "startRecord is %d, must be zero or positive", this.startRecord);
        Validate.isTrue(this.limitRecord > 0, "limitRecord is %d, must be positive", this.limitRecord);
        Validate.isTrue(this.limitRecord >= this.startRecord,
                "limitRecord (%d) has to be greater than or equal to startRecord (%d)",
                this.limitRecord, this.startRecord);

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

    public int getCount() {
        final int difference = limitRecord - startRecord;
        if (difference <  Integer.MAX_VALUE) {
            return difference + 1;
        } else {
            return Integer.MAX_VALUE;
        }
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

}
