package org.xcolab.service.content.domain.contentarticle;

import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.service.content.exceptions.NotFoundException;

import java.util.List;

public interface ContentArticleDao {

    IContentArticle get(Long contentArticleId) throws NotFoundException;

    List<? extends IContentArticle> getArticles();

    List<? extends IContentArticle> getArticlesInFolder(long folderId);

    boolean update(IContentArticle contentArticle);

    IContentArticle create(IContentArticle contentArticle);

    int delete(long contentArticleId);
}
