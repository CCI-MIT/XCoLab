package org.xcolab.service.contents.domain.contentarticleversion;

import org.xcolab.model.tables.pojos.ContentArticleVersion;

import java.util.List;

public interface ContentArticleVersionDao {

    ContentArticleVersion create(ContentArticleVersion contentArticleVersion);

    void update(ContentArticleVersion contentArticleVersion);

    ContentArticleVersion get(Long contentArticleId);

    List<ContentArticleVersion> getVersions();
    List<ContentArticleVersion> getVersionsForArticle(long articleId);

    List<ContentArticleVersion> findByGiven(Long contentArticleId, Long contentArticleVersion,
            Long folderId, String title, int startRecord, int limitRecord);

    List<ContentArticleVersion> getByFolderId(Long contentFolderId);

    ContentArticleVersion getLatestVersionByContentArticleId(Long contentArticleVersionId);
}
