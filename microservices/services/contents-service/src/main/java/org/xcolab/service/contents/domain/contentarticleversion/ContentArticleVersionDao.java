package org.xcolab.service.contents.domain.contentarticleversion;

import org.xcolab.model.tables.pojos.ContentArticleVersion;

import java.util.List;

public interface ContentArticleVersionDao {

    ContentArticleVersion create(ContentArticleVersion contentArticleVersion);

    void update(ContentArticleVersion contentArticleVersion);

    ContentArticleVersion get(Long contentArticleId);

    List<ContentArticleVersion> getByFolderId(Long contentFolderId);

    ContentArticleVersion getLatestVersionByContentArticleId(Long contentArticleVersionId);
}
