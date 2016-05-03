package org.xcolab.service.contents.domain.contentarticle;

import org.xcolab.model.tables.pojos.ContentArticle;

import java.util.List;

public interface ContentArticleDao {
    ContentArticle get(Long contentArticleId);

    List<ContentArticle> getArticles();
    List<ContentArticle> getArticlesInFolder(long folderId);

    void update(ContentArticle contentArticle);

    ContentArticle create(ContentArticle contentArticle);
}
