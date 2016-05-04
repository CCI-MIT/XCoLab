package org.xcolab.service.contents.service.contentfolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.service.contents.domain.contentFolder.ContentFolderDao;

import java.util.List;

@Service
public class ContentFolderService {
    private final ContentFolderDao contentFolderDao;

    @Autowired
    public ContentFolderService(ContentFolderDao contentFolderDao) {
        this.contentFolderDao = contentFolderDao;
    }

    public ContentFolder create(ContentFolder contentFolder) {
        return this.contentFolderDao.create(contentFolder);
    }

    public void update(ContentFolder contentFolder) {
        this.contentFolderDao.update(contentFolder);
    }

    public ContentFolder get(Long contentFolderId) {
        return this.contentFolderDao.get(contentFolderId);
    }
    public List<ContentFolder> getChildFolders(Long contentFolderId) {
        return this.contentFolderDao.getChildFolders(contentFolderId);
    }


}
