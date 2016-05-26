package org.xcolab.client.files.providers;

import java.io.File;

public class AWSPersistenceProvider implements PersistenceProvider {

    @Override
    public boolean saveFileToFinalDestination(File file, Object fileEntry) {
        return false;
    }

    @Override
    public String getFilePathFromFinalDestination(File file, Object fileEntry) {
        return null;
    }
}
