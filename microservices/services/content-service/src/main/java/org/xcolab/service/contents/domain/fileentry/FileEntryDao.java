package org.xcolab.service.contents.domain.fileentry;

import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.service.contents.exceptions.NotFoundException;

public interface FileEntryDao {

    IFileEntry create(IFileEntry IFileEntry);

    IFileEntry get(Long fileEntryid) throws NotFoundException;
}
