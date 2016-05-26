package org.xcolab.client.files.providers;

import java.io.File;

public interface PersistenceProvider {

    boolean saveFileToFinalDestination(File file, Object fileEntry);

    String getFilePathFromFinalDestination(File file, Object fileEntry);
}
