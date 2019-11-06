package org.xcolab.service.content.domain.fileentry;

import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.service.content.exceptions.NotFoundException;

public interface FileEntryDao {

    IFileEntry create(IFileEntry fileEntry);

    IFileEntry get(Long fileEntryId) throws NotFoundException;

    Boolean getNonImageFiles() throws NotFoundException;
}
