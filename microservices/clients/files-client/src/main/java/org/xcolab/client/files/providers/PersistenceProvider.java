package org.xcolab.client.files.providers;

import org.xcolab.client.files.pojo.FileEntry;

public interface PersistenceProvider {

    boolean saveFileToFinalDestination(byte [] imgBArr, FileEntry fileEntry, String path);

    String getFilePathFromFinalDestination(FileEntry fileEntry, String path);
}
