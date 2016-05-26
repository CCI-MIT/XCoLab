package org.xcolab.client.files.providers;

import org.apache.commons.io.FileUtils;
import org.xcolab.client.files.pojo.FileEntry;

import java.io.File;
import java.io.IOException;

public class FileSystemPersistenceProvider implements PersistenceProvider {

    private final String LOCAL_FOLDER_NAME = "fileEntriesDataFolder";

    private final int LOCAL_FOLDER_MAX_AMOUNT_OF_FILES = 300;

    @Override
    public boolean saveFileToFinalDestination(byte[] imgBArr, FileEntry fileEntry, String path) {

        int shardingFolder = (fileEntry.getFileEntryId()).intValue() / LOCAL_FOLDER_MAX_AMOUNT_OF_FILES;
        String finalPath = path + "../../../data/" + LOCAL_FOLDER_NAME + File.separator + shardingFolder + File.separator;
        File folder = new File(finalPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try {
            FileUtils.writeByteArrayToFile(new File(finalPath + fileEntry.getFileEntryId() + "." + fileEntry.getFileEntryExtension()), imgBArr);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getFilePathFromFinalDestination(FileEntry fileEntry, String path) {

        int shardingFolder = (fileEntry.getFileEntryId()).intValue() / LOCAL_FOLDER_MAX_AMOUNT_OF_FILES;
        String finalPath = path + "../../../data/" + LOCAL_FOLDER_NAME + File.separator + shardingFolder + File.separator;
        return finalPath + fileEntry.getFileEntryId() + "." + fileEntry.getFileEntryExtension();
    }
}
