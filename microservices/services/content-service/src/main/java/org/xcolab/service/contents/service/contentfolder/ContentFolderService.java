package org.xcolab.service.contents.service.contentfolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.contents.domain.contentFolder.ContentFolderDao;

@Service
public class ContentFolderService {

    private final ContentFolderDao contentFolderDao;

    @Autowired
    public ContentFolderService(ContentFolderDao contentFolderDao) {
        this.contentFolderDao = contentFolderDao;
    }
}
