package org.xcolab.client.files.providers;

import org.xcolab.client.files.pojo.FileEntry;

public class AWSPersistenceProvider implements PersistenceProvider {

    @Override
    public boolean saveFileToFinalDestination(byte [] imgBArr, FileEntry fileEntry, String path) {
        // move file to liferay data folder above tomcat

        // create the file from
        return false;
    }

    @Override
    public String getFilePathFromFinalDestination( FileEntry fileEntry, String path) {
        return null;
    }
}
