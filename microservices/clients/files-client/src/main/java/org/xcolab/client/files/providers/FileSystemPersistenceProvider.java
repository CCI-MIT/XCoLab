package org.xcolab.client.files.providers;

import java.io.File;

public class FileSystemPersistenceProvider implements PersistenceProvider{
    @Override
    public boolean saveFileToFinalDestination(File file, Object fileEntry) {
        // move file to liferay data folder above tomcat
        return false;
    }

    @Override
    public String getFilePathFromFinalDestination(File file, Object fileEntry) {
        return null;
    }
}
