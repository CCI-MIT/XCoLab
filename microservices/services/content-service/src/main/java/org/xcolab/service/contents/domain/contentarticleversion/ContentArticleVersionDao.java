package org.xcolab.service.contents.domain.contentarticleversion;

import org.xcolab.model.tables.pojos.ContentArticleVersion;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ContentArticleVersionDao {

    ContentArticleVersion create(ContentArticleVersion contentArticleVersion);
    int deleteByArticleId(long contentArticleId);

    boolean update(ContentArticleVersion contentArticleVersion);

    ContentArticleVersion get(Long contentArticleId) throws NotFoundException;

    List<ContentArticleVersion> findByGiven(PaginationHelper paginationHelper, Long contentArticleId, Long contentArticleVersion,
        Long folderId, Long ancestorFolderId, String title);

    List<ContentArticleVersion> getByFolderId(Long contentFolderId);
}
