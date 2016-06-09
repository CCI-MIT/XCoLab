package org.xcolab.service.filtering.utils.filteringprocessor;

import org.xcolab.model.tables.pojos.FilteredEntry;

public interface EntryFilteringProcessor {

    FilteredEntry processEntry(FilteredEntry memberContentEntry);
}
