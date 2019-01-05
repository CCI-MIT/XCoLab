package org.xcolab.service.content.providers;

import org.xcolab.client.content.pojo.IFileEntry;

public interface PersistenceProvider {

    boolean saveFileToFinalDestination(byte[] imgBArr, IFileEntry fileEntry, String path);

    String getFilePathFromFinalDestination(Long fileEntryId, String filePath, String fileExtension);
}
