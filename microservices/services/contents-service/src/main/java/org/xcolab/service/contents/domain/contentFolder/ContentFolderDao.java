package org.xcolab.service.contents.domain.contentFolder;

import org.xcolab.model.tables.pojos.ContentFolder;

public interface ContentFolderDao {
    ContentFolder create(ContentFolder contentFolder);

    void update(ContentFolder contentFolder);

    ContentFolder get(Long contentFolderId);
}
