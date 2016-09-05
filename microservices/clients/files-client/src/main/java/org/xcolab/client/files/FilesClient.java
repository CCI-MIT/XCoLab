package org.xcolab.client.files;

import org.xcolab.client.files.exceptions.FileEntryNotFoundException;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.files.providers.FileSystemPersistenceProvider;
import org.xcolab.client.files.providers.PersistenceProvider;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CachingStrategy;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public final class FilesClient {

    private static final RestService fileService = new RestService("files-service");
    private static final RestResource<FileEntry> fileEntryResource = new RestResource<>(fileService,
            "fileEntries", FileEntry.TYPES);

    private static final PersistenceProvider persistenceProvider =
            new FileSystemPersistenceProvider();

    public static FileEntry createFileEntry(
            FileEntry fileEntry, byte[] imgBArr, String path) {
        FileEntry ret = fileEntryResource.create(fileEntry).execute();

        persistenceProvider.saveFileToFinalDestination(imgBArr, ret, path);
        return ret;
    }

    public static String getFilePathFromFinalDestination(FileEntry fe, String path) {
        return persistenceProvider.getFilePathFromFinalDestination(fe, path);
    }

    public static FileEntry getFileEntry(Long fileEntryId) throws FileEntryNotFoundException {
        try {
            return fileEntryResource.get(fileEntryId)
                    .withCache(CacheKeys.of(FileEntry.class, fileEntryId), CachingStrategy.LONG)
                    .executeChecked();
        } catch (EntityNotFoundException e) {
            throw new FileEntryNotFoundException(fileEntryId);
        }
    }
}
