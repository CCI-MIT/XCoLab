package org.xcolab.service.content.service.contentarticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.content.domain.contentarticle.ContentArticleDao;
import org.xcolab.service.content.domain.contentarticleversion.ContentArticleVersionDao;

@Service
public class ContentArticleService {

    private final ContentArticleDao contentArticleDao;

    private final ContentArticleVersionDao contentArticleVersionDao;

    @Autowired
    public ContentArticleService(ContentArticleDao contentArticleDao,
            ContentArticleVersionDao contentArticleVersionDao) {
        this.contentArticleDao = contentArticleDao;
        this.contentArticleVersionDao = contentArticleVersionDao;
    }

    public int delete(long contentArticleId) {
        final int articlesDeleted = contentArticleDao.delete(contentArticleId);
        final int versionsDeleted = contentArticleVersionDao.deleteByArticleId(contentArticleId);
        return articlesDeleted + versionsDeleted;
    }
}
