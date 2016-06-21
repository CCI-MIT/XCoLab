package org.xcolab.client.files;

import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.files.exceptions.FileEntryNotFoundException;
import org.xcolab.client.files.pojo.FileEntry;
import org.xcolab.client.files.providers.FileSystemPersistenceProvider;
import org.xcolab.client.files.providers.PersistenceProvider;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

public final class FilesClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:" + RequestUtils.getServicesPort() + "/files-service";

    private static final PersistenceProvider persistenceProvider = new FileSystemPersistenceProvider();

    public static FileEntry createFileEntry(
            FileEntry fileEntry, byte[] imgBArr, String path) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/fileEntries/");
        FileEntry ret = RequestUtils.post(uriBuilder, fileEntry, FileEntry.class);

        persistenceProvider.saveFileToFinalDestination(imgBArr, ret, path);

        return ret;

    }

    public static String getFilePathFromFinalDestination(FileEntry fe, String path) {
        return persistenceProvider.getFilePathFromFinalDestination(fe, path);
    }

    public static FileEntry getFileEntry(Long fileEntryId) throws FileEntryNotFoundException {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/fileEntries/" + fileEntryId + "");

        try {
            return RequestUtils.get(uriBuilder, FileEntry.class);
        } catch (EntityNotFoundException e) {
            throw new FileEntryNotFoundException(
                    "FileEntry " + fileEntryId + " does not exist");
        }
    }
}
