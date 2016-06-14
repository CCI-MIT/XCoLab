package org.xcolab.service.filtering.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.FilteredEntry;
import org.xcolab.service.filtering.domain.filteredentry.FilteredEntryDao;
import org.xcolab.service.filtering.enums.FilteringStatus;
import org.xcolab.service.filtering.exceptions.NotFoundException;
import org.xcolab.service.filtering.utils.filteringprocessor.EntryFilteringProcessor;
import org.xcolab.service.filtering.utils.filteringprocessor.XColabFilteringProcessor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class FilteringController {

    @Autowired
    private FilteredEntryDao filteredEntryDao;

    private EntryFilteringProcessor processor = new XColabFilteringProcessor();

    @RequestMapping(value = "/filteredEntries", method = RequestMethod.POST)
    public FilteredEntry filterEntry(@RequestBody FilteredEntry filteredEntry) {

        filteredEntry.setStatus(FilteringStatus.CREATED.getId());
        filteredEntry.setCreatedAt(new Timestamp(new Date().getTime()));
        filteredEntry.setUuid(UUID.randomUUID().toString());
        FilteredEntry aux = filteredEntryDao.create(filteredEntry);
        FilteredEntry afterProcess = processor.processEntry(aux);
        if (afterProcess != null) {
            filteredEntryDao.update(afterProcess);
        }
        return aux;

    }

    @RequestMapping(value = "/filteredEntries/{uuid}", method = RequestMethod.GET)
    public FilteredEntry getByUUID(@PathVariable String uuid) throws NotFoundException {
        return filteredEntryDao.getByUuid(uuid);
    }

    @RequestMapping(value = "/filteredEntries/{filterId}", method = RequestMethod.GET)
    public FilteredEntry checkStatus(@PathVariable Long filterId) throws NotFoundException {
        return filteredEntryDao.get(filterId);
    }

    @RequestMapping(value = "/filteredEntries/processCreatedEntries", method = RequestMethod.GET)
    public void processCreatedEntries() {
        List<FilteredEntry> entriesToProcess = filteredEntryDao.getByStatus(FilteringStatus.CREATED.getId());
        if (entriesToProcess != null) {
            for (FilteredEntry entry : entriesToProcess) {
                FilteredEntry afterProcess = processor.processEntry(entry);
                if (afterProcess != null) {
                    filteredEntryDao.update(afterProcess);
                }
            }
        }
        return;
    }
}
