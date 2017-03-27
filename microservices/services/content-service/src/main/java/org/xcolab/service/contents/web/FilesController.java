package org.xcolab.service.contents.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.FileEntry;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.contents.domain.fileentry.FileEntryDao;

@RestController
public class FilesController {

    private final FileEntryDao fileEntryDao;

    @Autowired
    public FilesController(FileEntryDao fileEntryDao) {
        Assert.notNull(fileEntryDao);
        this.fileEntryDao = fileEntryDao;
    }

    @RequestMapping(value = "/fileEntries", method = RequestMethod.POST)
    public FileEntry createFileEntry(@RequestBody FileEntry fileEntry) {
        return this.fileEntryDao.create(fileEntry);
    }
    @RequestMapping(value = "/fileEntries/{fileEntryId}", method = RequestMethod.GET)
    public FileEntry getContentArticle(@PathVariable long fileEntryId)
            throws NotFoundException {
        return this.fileEntryDao.get(fileEntryId);
    }
}
