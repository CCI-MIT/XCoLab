package org.xcolab.service.contents.service.contentarticleversion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.xcolab.service.contents.domain.contentarticleversion.ContentArticleVersionDao;

@Service
public class ContentArticleVersionService {

    private final ContentArticleVersionDao contentArticleVersionDao;

    @Autowired
    public ContentArticleVersionService(ContentArticleVersionDao contentArticleVersionDao) {
        this.contentArticleVersionDao = contentArticleVersionDao;
    }
}
