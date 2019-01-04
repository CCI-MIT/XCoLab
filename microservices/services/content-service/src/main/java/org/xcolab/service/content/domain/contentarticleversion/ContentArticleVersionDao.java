package org.xcolab.service.content.domain.contentarticleversion;

import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.service.content.exceptions.NotFoundException;
import org.xcolab.service.utils.PaginationHelper;

import java.util.List;

public interface ContentArticleVersionDao {

    IContentArticleVersion create(IContentArticleVersion contentArticleVersion);

    int deleteByArticleId(long contentArticleId);

    boolean update(IContentArticleVersion contentArticleVersion);

    IContentArticleVersion get(Long contentArticleId) throws NotFoundException;

    List<IContentArticleVersion> findByGiven(PaginationHelper paginationHelper,
            Long contentArticleId, Long contentArticleVersion,
            Long folderId, Long ancestorFolderId, String title, String lang);

    List<IContentArticleVersion> getByFolderId(Long contentFolderId);

    IContentArticleVersion getLatestVersionByArticleIdAndLanguage(Long articleId, String language)
            throws NotFoundException;
}
