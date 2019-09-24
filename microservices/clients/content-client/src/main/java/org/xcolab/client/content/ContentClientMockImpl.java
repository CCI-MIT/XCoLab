package org.xcolab.client.content;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.content.pojo.IContentFolder;
import org.xcolab.client.content.pojo.IContentPage;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class ContentClientMockImpl implements IContentClient {

    @Override
    public List<? extends IContentArticle> getContentArticles(Long folderId) {
        return Collections.emptyList();
    }

    @Override
    public List<IContentArticleVersion> getContentArticleVersions(Integer startRecord,
            Integer limitRecord, Long folderId, Long contentArticleId, Long contentArticleVersion,
            String title, String lang) {
        return Collections.emptyList();
    }

    @Override
    public List<IContentFolder> getContentFolders() {
        return Collections.emptyList();
    }

    @Override
    public List<IContentFolder> getContentFolders(Long parentFolderId) {
        return Collections.emptyList();
    }

    @Override
    public IContentArticle getContentArticle(Long articleId) throws ContentNotFoundException {
        return null;
    }

    @Override
    public IContentArticle createContentArticle(IContentArticle contentArticle) {
        return null;
    }

    @Override
    public void deleteContentArticle(Long contentArticleId) {
        // Do nothing
    }

    @Override
    public boolean updateContentArticle(IContentArticle contentArticle)
            throws ContentNotFoundException {
        return false;
    }

    @Override
    public IContentArticleVersion getContentArticleVersion(Long articleVersionId)
            throws ContentNotFoundException {
        return null;
    }

    @Override
    public IContentArticleVersion createContentArticleVersion(
            IContentArticleVersion contentArticleVersion) {
        return null;
    }

    @Override
    public boolean updateContentArticleVersion(IContentArticleVersion contentArticleVersion)
            throws ContentNotFoundException {
        return false;
    }

    @Override
    public IContentFolder getContentFolder(Long contentFolderId) throws ContentNotFoundException {
        return null;
    }

    @Override
    public IContentFolder createContentFolder(IContentFolder contentFolder) {
        return null;
    }

    @Override
    public boolean updateContentFolder(IContentFolder IContentFolder)
            throws ContentNotFoundException {
        return false;
    }

    @Override
    public IContentPage createContentPage(IContentPage page) {
        return null;
    }

    @Override
    public Boolean updateContentPage(IContentPage page) {
        return null;
    }

    @Override
    public List<IContentArticleVersion> getContentFolderArticleVersions(Long contentFolderId) {
        return Collections.emptyList();
    }

    @Override
    public IContentPage getContentPage(Long pageId) throws ContentNotFoundException {
        return null;
    }

    @Override
    public IContentPage getContentPageByContentArticleId(Long contentArticleId)
            throws ContentNotFoundException {
        return null;
    }

    @Override
    public List<IContentPage> getContentPages(String title) {
        return Collections.emptyList();
    }
}
