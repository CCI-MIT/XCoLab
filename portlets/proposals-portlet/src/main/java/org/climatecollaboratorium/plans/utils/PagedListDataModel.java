package org.climatecollaboratorium.plans.utils;

import javax.faces.model.DataModel;

public abstract class PagedListDataModel extends DataModel {
    int pageSize;
    int rowIndex;
    DataPage page;
    // Triggers a fetch from the database
    protected boolean dirtyData = false;

    /*
     * Create a datamodel that pages through the data showing the specified
     * number of rows on each page.
     */
    public PagedListDataModel(int pageSize) {
        super();
        this.pageSize = pageSize;
        this.rowIndex = -1;
        this.page = null;
    }

    /**
     * Not used in this class; data is fetched via a callback to the
     * fetchData method rather than by explicitly assigning a list.
     */
    @Override
    public void setWrappedData(Object o) {
        throw new UnsupportedOperationException("setWrappedData");
    }

    @Override
    public int getRowIndex() {
        return rowIndex;
    }

    /**
     * Specify what the "current row" within the dataset is. Note that
     * the UIData component will repeatedly call this method followed
     * by getRowData to obtain the objects to render in the table.
     */
    @Override
    public void setRowIndex(int index) {
        rowIndex = index;
    }

    /**
     * Return the total number of rows of data available (not just the
     * number of rows in the current page!).
     */
    @Override
    public int getRowCount() {
        return getPage().getDatasetSize();
    }

    /**
     * Return a DataPage object; if one is not currently available then
     * fetch one. Note that this doesn't ensure that the datapage
     * returned includes the current rowIndex row; see getRowData.
     */
    private DataPage getPage() {
        if (page != null)
            return page;

        int rowIndex = getRowIndex();
        int startRow = rowIndex;
        if (rowIndex == -1) {
            // even when no row is selected, we still need a page
            // object so that we know the amount of data available.
           startRow = 0;
        }

        // invoke method on enclosing class
        page = fetchPage(startRow, pageSize);
        return page;
    }

    /**
     * Return the object corresponding to the current rowIndex.
     * If the DataPage object currently cached doesn't include that
     * index or the data is marked as dirty, then fetchPage is called
     * to retrieve the appropriate page.
     */
    @Override
    public Object getRowData(){
        if (rowIndex < 0) {
            throw new IllegalArgumentException(
                "Invalid rowIndex for PagedListDataModel; not within page");
        }

        // ensure page exists; if rowIndex is beyond dataset size, then
        // we should still get back a DataPage object with the dataset size
        // in it...
        if (page == null) {
            page = fetchPage(rowIndex, pageSize);
        }

        // Check if rowIndex is equal to startRow,
        // useful for dynamic sorting on pages
        if (dirtyData || (dirtyData && rowIndex == page.getStartRow())){
            page = fetchPage(rowIndex, pageSize);
        }

        int datasetSize = page.getDatasetSize();
        int startRow = page.getStartRow();
        int nRows = page.getData().size();
        int endRow = startRow + nRows;

        if (rowIndex >= datasetSize) {
            throw new IllegalArgumentException("Invalid rowIndex");
        }

        if (rowIndex < startRow) {
            page = fetchPage(rowIndex, pageSize);
            startRow = page.getStartRow();
        } else if (rowIndex >= endRow) {
            page = fetchPage(rowIndex, pageSize);
            startRow = page.getStartRow();
        }
        return page.getData().get(rowIndex - startRow);
    }

    @Override
    public Object getWrappedData() {
        return page.getData();
    }

    /**
     * Return true if the rowIndex value is currently set to a
     * value that matches some element in the dataset. Note that
     * it may match a row that is not in the currently cached
     * DataPage; if so then when getRowData is called the
     * required DataPage will be fetched by calling fetchData.
     */
    @Override
    public boolean isRowAvailable() {
        DataPage page = getPage();
        if (page == null)
            return false;

        int rowIndex = getRowIndex();
        if (rowIndex < 0) {
            return false;
        } else if (rowIndex >= page.getDatasetSize()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method which must be implemented in cooperation with the
     * managed bean class to fetch data on demand.
     * @throws SystemException
     * @throws PortalException
     */
    public abstract DataPage fetchPage(int startRow, int pageSize);

    public boolean isDirtyData() {
        return dirtyData;
    }

    public void setDirtyData(boolean dirtyData) {
        this.dirtyData = dirtyData;
    }

    public void setDirtyData() {
        dirtyData = true;
    }

}