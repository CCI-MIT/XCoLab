package org.xcolab.client.files;

import org.xcolab.client.files.exceptions.FileEntryNotFoundException;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.files.providers.FileSystemPersistenceProvider;
import org.xcolab.client.files.providers.PersistenceProvider;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public final class FilesClient {

    private static final RestService fileService = new RestService("files-service");
    private static final RestResource fileEntryResource = new RestResource(fileService,
            "fileEntries");

    private static final PersistenceProvider persistenceProvider = new FileSystemPersistenceProvider();

    public static FileEntry createFileEntry(
            FileEntry fileEntry, byte[] imgBArr, String path) {
        UriBuilder uriBuilder = fileEntryResource.getResourceUrl();
        FileEntry ret = RequestUtils.post(uriBuilder, fileEntry, FileEntry.class);

        persistenceProvider.saveFileToFinalDestination(imgBArr, ret, path);

        return ret;

    }

    public static String getFilePathFromFinalDestination(FileEntry fe, String path) {
        return persistenceProvider.getFilePathFromFinalDestination(fe, path);
    }

    public static FileEntry getFileEntry(Long fileEntryId) throws FileEntryNotFoundException {
        UriBuilder uriBuilder = fileEntryResource.getResourceUrl(fileEntryId);

        try {
            return RequestUtils.get(uriBuilder, FileEntry.class);
        } catch (EntityNotFoundException e) {
            throw new FileEntryNotFoundException(
                    "FileEntry " + fileEntryId + " does not exist");
        }
    }
}
