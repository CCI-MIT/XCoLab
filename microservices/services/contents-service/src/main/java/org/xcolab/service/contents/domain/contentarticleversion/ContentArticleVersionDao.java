package org.xcolab.service.contents.domain.contentarticleversion;

import org.xcolab.model.tables.pojos.ContentArticleVersion;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ContentArticleVersionDao {

    ContentArticleVersion create(ContentArticleVersion contentArticleVersion);

    void update(ContentArticleVersion contentArticleVersion);

    ContentArticleVersion get(Long contentArticleId) throws NotFoundException;

    List<ContentArticleVersion> findByGiven(PaginationHelper paginationHelper, Long contentArticleId, Long contentArticleVersion,
            Long folderId, String title);

    List<ContentArticleVersion> getByFolderId(Long contentFolderId);
}
