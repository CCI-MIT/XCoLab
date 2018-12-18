package org.xcolab.service.content.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.content.IFileClient;
import org.xcolab.client.content.pojo.IFileEntry;
import org.xcolab.service.content.domain.fileentry.FileEntryDao;
import org.xcolab.service.content.exceptions.NotFoundException;
import org.xcolab.service.content.providers.FileSystemPersistenceProvider;
import org.xcolab.service.content.providers.PersistenceProvider;

import java.io.File;
import java.util.Optional;

@RestController
public class FileController implements IFileClient {

    private static final PersistenceProvider persistenceProvider =
            new FileSystemPersistenceProvider();

    private final FileEntryDao fileEntryDao;

    @Autowired
    public FileController(FileEntryDao fileEntryDao) {
        Assert.notNull(fileEntryDao, "FileEntryDao is required");
        this.fileEntryDao = fileEntryDao;
    }

    @Override
    @PostMapping("/fileEntries")
    public IFileEntry createFileEntry(@RequestBody IFileEntry fileEntry,
            @RequestParam byte[] imgBArr,
            @RequestParam String path) {
        IFileEntry ret = this.fileEntryDao.create(fileEntry);
        persistenceProvider.saveFileToFinalDestination(imgBArr, ret, path);
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
                return Optional.of(this.fileEntryDao.get(fileEntryId));
            } catch (NotFoundException ignored) {
            }
        }
        return Optional.empty();
    }
}
