package org.xcolab.service.files.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.FileEntry;
import org.xcolab.service.files.domain.fileEntry.FileEntryDao;
import org.xcolab.service.files.exceptions.NotFoundException;

@RestController
public class FilesController {
    @Autowired
    private FileEntryDao fileEntryDao;

    @RequestMapping(value = "/fileEntries", method = RequestMethod.POST)
    public FileEntry createFileEntry(@RequestBody FileEntry fileEntry) {

        return this.fileEntryDao.create(fileEntry);
    }
    @RequestMapping(value = "/fileEntries/{fileEntryId}", method = RequestMethod.GET)
    public FileEntry getContentArticle(@PathVariable("fileEntryId") Long fileEntryId)
            throws NotFoundException {
        if (fileEntryId == null || fileEntryId == 0) {
            throw new NotFoundException("No content article with id given");
        } else {
            return this.fileEntryDao.get(fileEntryId);
        }
    }
}
