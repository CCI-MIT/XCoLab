package org.xcolab.service.content.providers;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Repository;

import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.commons.exceptions.InternalException;

import java.io.File;
import java.io.IOException;

@Repository
public class FileSystemPersistenceProvider implements PersistenceProvider {

    private static final String LOCAL_FOLDER_NAME = "fileEntriesDataFolder";

    private static final int LOCAL_FOLDER_MAX_AMOUNT_OF_FILES = 300;

    @Override
    public boolean saveFileToFinalDestination(byte[] imgBArr, IFileEntry fileEntry, String path) {
        int shardingFolder = (fileEntry.getId()).intValue() / LOCAL_FOLDER_MAX_AMOUNT_OF_FILES;
        String finalPath = path + "data/" + LOCAL_FOLDER_NAME + File.separator + shardingFolder
                + File.separator;
        File folder = new File(finalPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            FileUtils.writeByteArrayToFile(
                    new File(finalPath + fileEntry.getId() + "." + fileEntry.getFileExtension()),
                    imgBArr);
            return true;
        } catch (IOException e) {
            throw new InternalException(e);
        }
    }

    @Override
    public String getFilePathFromFinalDestination(Long fileEntryId, String filePath,
            String fileExtension) {
        int shardingFolder = (fileEntryId).intValue() / LOCAL_FOLDER_MAX_AMOUNT_OF_FILES;
        String finalPath = filePath + "data/" + LOCAL_FOLDER_NAME + File.separator + shardingFolder
                + File.separator;
        return finalPath + fileEntryId + "." + fileExtension;
    }
}
