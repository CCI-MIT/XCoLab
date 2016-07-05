package org.xcolab.client.filtering.exceptions;

public class FilteredEntryNotFoundException extends Exception {
    public FilteredEntryNotFoundException(String uuid) {
        super("FilteredEntry with uuid " + uuid + " not found.");
    }
}

