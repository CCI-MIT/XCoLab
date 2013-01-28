package org.xcolab.portlets.members.utils;

import java.util.List;

/**
 * A simple class that represents a "page" of data out of a longer set, ie a
 * list of objects together with info to indicate the starting row and the full
 * size of the dataset. Business methods can return instances of this type when
 * returning subsets of available data.
 */
public class DataPage {

    private int datasetSize;
    private int startRow;
    private List data;

    /**
     * Create an object representing a sublist of a dataset.
     * 
     * @param datasetSize
     *            is the total number of matching rows available.
     * 
     * @param startRow
     *            is the index within the complete dataset of the first element
     *            in the data list.
     * 
     * @param data
     *            is a list of consecutive objects from the dataset.
     */
    public DataPage(int datasetSize, int startRow, List data) {
        this.datasetSize = datasetSize;
        this.startRow = startRow;
        this.data = data;
    }

    /**
     * Return the number of items in the full dataset.
     */
    public int getDatasetSize() {
        return datasetSize;
    }

    /**
     * Return the offset within the full dataset of the first element in the
     * list held by this object.
     */
    public int getStartRow() {
        return startRow;
    }

    /**
     * Return the list of objects held by this object, which is a continuous
     * subset of the full dataset.
     */
    public List<?> getData() {
        return data;
    }
}