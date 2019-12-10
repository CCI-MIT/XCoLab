package org.xcolab.service.content.domain.fileentry;

import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.service.content.exceptions.NotFoundException;
import org.xcolab.client.content.pojo.tables.pojos.FileEntry;

import java.util.List;

public interface FileEntryDao {

    IFileEntry create(IFileEntry fileEntry);

    IFileEntry get(Long fileEntryId) throws NotFoundException;

    List<FileEntry> getNonImageFiles() throws NotFoundException;
}
