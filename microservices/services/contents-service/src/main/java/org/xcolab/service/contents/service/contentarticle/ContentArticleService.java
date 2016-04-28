package org.xcolab.service.contents.service.contentarticle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xcolab.service.contents.domain.contentarticle.ContentArticleDao;

@Service
public class ContentArticleService {

    private final ContentArticleDao contentArticleDao;

    @Autowired
    public ContentArticleService(ContentArticleDao contentArticleDao) {
        this.contentArticleDao = contentArticleDao;
    }
}
