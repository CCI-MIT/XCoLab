package org.xcolab.portlets.search.utils;

/**
 * The DataSource class is a utility class used by the data table paginator
 * and commandSortHeader.
 */
public abstract class DataSource {

    // Sortable Headers
    protected String sortColumnName;
    protected boolean sortAscending;

    // DataModel bound to dataTable
    protected PagedListDataModel onePageDataModel;
    // bound to rows attribute in dataTable
    protected int pageSize = 5;

    protected DataSource(String defaultSortColumn) {
        sortColumnName = defaultSortColumn;
        sortAscending = isDefaultAscending(defaultSortColumn);
    }

    /**
     * Is the default sortColumnName direction for the given column "sortAscending" ?
     */
    protected abstract boolean isDefaultAscending(String sortColumn);

    protected DataSource() {
    }

    /**
     * Gets the sortColumnName column.
     *
     * @return column to sortColumnName
     */
    public String getSortColumnName() {
        return sortColumnName;
    }

    /**
     * Sets the sortColumnName column.
     *
     * @param sortColumnName column to sortColumnName
     */
    public void setSortColumnName(String sortColumnName) {
        if (!sortColumnName.equals(this.sortColumnName)) {
            onePageDataModel.setDirtyData();
            this.sortColumnName = sortColumnName;
        }
    }

    /**
     * Is the sortColumnName sortAscending?
     *
     * @return true if the sortAscending sortColumnName otherwise false
     */
    public boolean isSortAscending() {
        return sortAscending;
    }

    /**
     * Sets sortColumnName type.
     *
     * @param sortAscending true for sortAscending sortColumnName, false for descending sortColumnName.
     */
    public void setSortAscending(boolean sortAscending) {
        if (sortAscending != (this.sortAscending)) {
            onePageDataModel.setDirtyData();
            this.sortAscending = sortAscending;
        }
    }

    public PagedListDataModel getOnePageDataModel() {
        return onePageDataModel;
    }

    public int getPageSize() {
        return pageSize;
    }
}