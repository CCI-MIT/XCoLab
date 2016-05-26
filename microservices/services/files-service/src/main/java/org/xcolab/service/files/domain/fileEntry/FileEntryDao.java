package org.xcolab.service.files.domain.fileEntry;

import org.xcolab.model.tables.pojos.FileEntry;
import org.xcolab.service.files.exceptions.NotFoundException;

public interface FileEntryDao {
    FileEntry create(FileEntry fileEntry);
    FileEntry get(Long fileEntryid) throws NotFoundException;
}
