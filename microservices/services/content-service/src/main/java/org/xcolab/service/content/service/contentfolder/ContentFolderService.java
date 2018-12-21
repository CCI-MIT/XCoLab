package org.xcolab.service.content.service.contentfolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.content.domain.contentfolder.ContentFolderDao;

@Service
public class ContentFolderService {

    private final ContentFolderDao contentFolderDao;

    @Autowired
    public ContentFolderService(ContentFolderDao contentFolderDao) {
        this.contentFolderDao = contentFolderDao;
    }
}
