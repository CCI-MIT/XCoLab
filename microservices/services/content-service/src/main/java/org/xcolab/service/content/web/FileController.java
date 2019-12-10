package org.xcolab.service.content.web;

import org.xcolab.client.content.pojo.tables.pojos.FileEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.content.IFileClient;
import org.xcolab.client.content.pojo.FileEntryWrapper;
import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.service.content.domain.fileentry.FileEntryDao;
import org.xcolab.service.content.exceptions.NotFoundException;
import org.xcolab.service.content.providers.PersistenceProvider;

import java.io.File;
import java.util.List;
import java.util.Optional;

import java.util.ArrayList;

@RestController
public class FileController implements IFileClient {

    private final PersistenceProvider persistenceProvider;
    private final FileEntryDao fileEntryDao;

    @Autowired
    public FileController(FileEntryDao fileEntryDao, PersistenceProvider persistenceProvider) {
        Assert.notNull(fileEntryDao, "FileEntryDao is required");
        this.fileEntryDao = fileEntryDao;
        this.persistenceProvider = persistenceProvider;
    }

    @Override
    @PostMapping("/fileEntries")
    public IFileEntry createFileEntry(@RequestBody FileEntryWrapper fileEntryWrapper) {
        IFileEntry ret = this.fileEntryDao.create(fileEntryWrapper.getFileEntry());
        persistenceProvider.saveFileToFinalDestination(fileEntryWrapper.getImgArr(), ret,
                fileEntryWrapper.getPath());
        return ret;
    }

    @Override
    @GetMapping("/imageFile")
    public File getImageFile(@RequestParam Long fileEntryId, @RequestParam String filePath,
            @RequestParam String fileExtension) {
        String finalFilePath = persistenceProvider
                .getFilePathFromFinalDestination(fileEntryId, filePath, fileExtension);
        File file = new File(finalFilePath);
        if (file.exists() && !file.isDirectory()) {
            return file;
        }
        return null;
    }

    @Override
    @GetMapping("/fileEntries/{fileEntryId}")
    public Optional<IFileEntry> getFileEntry(@PathVariable Long fileEntryId) {
        if (fileEntryId != null && fileEntryId > 0) {
            try {
                IFileEntry fileEntry = this.fileEntryDao.get(fileEntryId);
                return Optional.of(fileEntry);
            } catch (NotFoundException ignored) {
            }
        }
        return Optional.empty();
    }

    @Override
    @GetMapping("/nonImageFileEntries")
    public List<FileEntry> getNonImageFilesEntry() {
        try {
            return this.fileEntryDao.getNonImageFiles();
        }
        catch(NotFoundException e){
            return new ArrayList<FileEntry>();
        }
    }
}
