package org.xcolab.service.contents.domain.contentFolder;

import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ContentFolderDao {
    ContentFolder create(ContentFolder contentFolder);

    boolean update(ContentFolder contentFolder);

    ContentFolder get(Long contentFolderId);

    List<ContentFolder> findByGiven(PaginationHelper paginationHelper, Long parentFolderId);
}
