package org.xcolab.service.files.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.FileEntry;
import org.xcolab.service.files.domain.fileEntry.FileEntryDao;

@RestController
public class FilesController {
    @Autowired
    private FileEntryDao fileEntryDao;

    @RequestMapping(value = "/balloonLinks/", method = RequestMethod.POST)
    public FileEntry createFileEntry(@RequestBody FileEntry fileEntry) {

        return this.fileEntryDao.create(fileEntry);
    }
}
