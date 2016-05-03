package org.xcolab.service.contents.domain.contentFolder;

import org.xcolab.model.tables.pojos.ContentFolder;

import java.util.List;

public interface ContentFolderDao {
    ContentFolder create(ContentFolder contentFolder);

    void update(ContentFolder contentFolder);

    ContentFolder get(Long contentFolderId);

    List<ContentFolder> getFolders();
    List<ContentFolder> getChildFolders(Long contentFolderId);
}
