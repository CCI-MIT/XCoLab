package org.xcolab.service.contents.domain.fileentry;

import org.xcolab.model.tables.pojos.FileEntry;
import org.xcolab.service.contents.exceptions.NotFoundException;

public interface FileEntryDao {

    FileEntry create(FileEntry fileEntry);

    FileEntry get(Long fileEntryid) throws NotFoundException;

}
