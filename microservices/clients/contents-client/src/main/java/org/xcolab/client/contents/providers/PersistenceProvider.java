package org.xcolab.client.contents.providers;

import org.xcolab.client.contents.pojo.FileEntry;

public interface PersistenceProvider {

    boolean saveFileToFinalDestination(byte [] imgBArr, FileEntry fileEntry, String path);

    String getFilePathFromFinalDestination(FileEntry fileEntry, String path);
}
