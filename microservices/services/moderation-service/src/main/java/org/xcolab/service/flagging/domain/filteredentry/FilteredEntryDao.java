package org.xcolab.service.flagging.domain.filteredentry;

import org.xcolab.model.tables.pojos.FilteredEntry;
import org.xcolab.service.flagging.exceptions.NotFoundException;

import java.util.List;

public interface FilteredEntryDao {

    FilteredEntry create(FilteredEntry memberContentEntry);

    boolean update(FilteredEntry filteredEntry);

    FilteredEntry get(Long memberContentEntryId) throws NotFoundException;

    List<FilteredEntry> getByStatus(Integer statusId);

    FilteredEntry getByAuthorAndSourceAndSourceId(Long authorId, Long sourceId, Long source);

    FilteredEntry getByUuid(String uuid) throws NotFoundException;

}
