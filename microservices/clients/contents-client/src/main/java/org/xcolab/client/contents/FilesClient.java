package org.xcolab.client.contents;

import org.xcolab.client.contents.pojo.FileEntry;
import org.xcolab.client.contents.providers.FileSystemPersistenceProvider;
import org.xcolab.client.contents.providers.PersistenceProvider;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.Optional;

public final class FilesClient {

    private static final RestResource<FileEntry, Long> fileEntryResource = new RestResource1<>(
            FilesResource.FILE_ENTRY, FileEntry.TYPES);

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

    public static Optional<FileEntry> getFileEntry(Long fileEntryId) {
        if (fileEntryId != null && fileEntryId > 0) {
            try {
                return Optional.of(fileEntryResource.get(fileEntryId)
                        .withCache(CacheKeys.of(FileEntry.class, fileEntryId), CacheName.MISC_LONG)
                        .executeChecked());
            } catch (EntityNotFoundException ignored) {
            }
        }
        return Optional.empty();
    }
}
