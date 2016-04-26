package org.xcolab.service.contents.domain.contentarticleversion;

import org.xcolab.model.tables.pojos.ContentArticleVersion;

public interface ContentArticleVersionDao {

    ContentArticleVersion create(ContentArticleVersion contentArticleVersion);

    void update(ContentArticleVersion contentArticleVersion);

    ContentArticleVersion get(Long contentArticleId);

    ContentArticleVersion getByFolderId(Long contentFolderId) ;
}
