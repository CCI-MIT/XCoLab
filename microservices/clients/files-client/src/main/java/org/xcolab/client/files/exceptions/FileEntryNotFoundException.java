package org.xcolab.client.files.exceptions;

public class FileEntryNotFoundException extends Exception {
    public FileEntryNotFoundException(long fileEntryId) {
        super("FileEntry " + fileEntryId + " does not exist");
    }
}

