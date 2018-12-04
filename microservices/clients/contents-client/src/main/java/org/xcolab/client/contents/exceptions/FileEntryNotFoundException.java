package org.xcolab.client.contents.exceptions;

public class FileEntryNotFoundException extends Exception {
    public FileEntryNotFoundException(long fileEntryId) {
        super("FileEntry " + fileEntryId + " does not exist");
    }
}

