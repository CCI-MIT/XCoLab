package org.xcolab.service.contents.domain.contentarticle;

import org.xcolab.model.tables.pojos.ContentArticle;

public interface ContentArticleDao {
    ContentArticle get(Long contentArticleId);

    void update(ContentArticle contentArticle);

    ContentArticle create(ContentArticle contentArticle);
}
