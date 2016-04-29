package org.xcolab.service.contents.service.contentarticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.model.tables.pojos.ContentArticle;
import org.xcolab.service.contents.domain.contentarticle.ContentArticleDao;

@Service
public class ContentArticleService {

    private final ContentArticleDao contentArticleDao;

    @Autowired
    public ContentArticleService(ContentArticleDao contentArticleDao) {
        this.contentArticleDao = contentArticleDao;
    }

    public ContentArticle create(ContentArticle contentArticle) {
        return this.contentArticleDao.create(contentArticle);
    }
    public void update(ContentArticle contentArticle) {
        this.contentArticleDao.update(contentArticle);
    }
    public ContentArticle get(Long contentArticleId) {
        return this.contentArticleDao.get(contentArticleId);
    }
}
