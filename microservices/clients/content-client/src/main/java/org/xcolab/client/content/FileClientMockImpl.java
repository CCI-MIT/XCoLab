package org.xcolab.client.content;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.content.pojo.FileEntryWrapper;
import org.xcolab.client.content.pojo.IFileEntry;

import java.io.File;
import java.util.Optional;

@Component
@Profile("test")
public class FileClientMockImpl implements IFileClient {

    @Override
    public IFileEntry createFileEntry(FileEntryWrapper fileEntryWrapper) {
        return null;
    }

    @Override
    public File getImageFile(Long fileEntryId, String filePath, String fileExtension) {
        return null;
    }

    @Override
    public Optional<IFileEntry> getFileEntry(Long fileEntryId) {
        return Optional.empty();
    }
}
