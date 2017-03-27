package org.xcolab.service.flagging.utils.filteringprocessor;

import org.xcolab.model.tables.pojos.FilteredEntry;
import org.xcolab.service.flagging.enums.FilteringStatus;

import java.sql.Timestamp;
import java.util.Date;

public abstract class EntryFilteringProcessor {

    public abstract FilteredEntry processEntry(FilteredEntry memberContentEntry);

    public FilteredEntry setSuccessFiltering(FilteredEntry memberContentEntry){
        memberContentEntry.setAnsweredAt(new Timestamp(new Date().getTime()));
        memberContentEntry.setResponseFullText(null);
        memberContentEntry.setStatus(FilteringStatus.APPROVED.getId());
        return memberContentEntry;
    }

    public FilteredEntry setFailedFiltering(FilteredEntry memberContentEntry, String filteredText){
        memberContentEntry.setAnsweredAt(new Timestamp(new Date().getTime()));
        memberContentEntry.setResponseFullText(filteredText);
        memberContentEntry.setStatus(FilteringStatus.REJECTED.getId());
        return memberContentEntry;
    }
}
