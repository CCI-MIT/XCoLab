package org.xcolab.service.content.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.client.content.IContentClient;
import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.content.pojo.IContentFolder;
import org.xcolab.client.content.pojo.IContentPage;
import org.xcolab.model.tables.pojos.ContentArticleImpl;
import org.xcolab.service.content.domain.contentarticle.ContentArticleDao;
import org.xcolab.service.content.domain.contentarticleversion.ContentArticleVersionDao;
import org.xcolab.service.content.domain.contentfolder.ContentFolderDao;
import org.xcolab.service.content.domain.page.ContentPageDao;
import org.xcolab.service.content.exceptions.NotFoundException;
import org.xcolab.service.content.service.contentarticle.ContentArticleService;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
public class ContentController implements IContentClient {

    @Autowired
    private ContentArticleService contentArticleService;

    @Autowired
    private ContentArticleDao contentArticleDao;

    @Autowired
    private ContentFolderDao contentFolderDao;

    @Autowired
    private ContentArticleVersionDao contentArticleVersionDao;

    @Autowired
    private ContentPageDao contentPageDao;

    @Override
    @PostMapping("/contentArticles")
    public IContentArticle createContentArticle(@RequestBody IContentArticle contentArticle) {
        Date date = new Date();
        contentArticle.setCreatedAt(new Timestamp(date.getTime()));

        //TODO: is this still relevant?
        //TODO COLAB-2589: fine-grained cache control
        //ServiceRequestUtils.clearCache(CacheName.CONTENT);

        return this.contentArticleDao.create(contentArticle);
    }

    @Override
    @GetMapping("/contentArticles")
    public List<? extends IContentArticle> getContentArticles(
            @RequestParam(required = false) Long folderId) {
        if (folderId != null) {
            return contentArticleDao.getArticlesInFolder(folderId);
        }
        return contentArticleDao.getArticles();
    }

    @Override
    @GetMapping("/contentArticleVersions")
    public List<IContentArticleVersion> getContentArticleVersions(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long folderId,
            @RequestParam(required = false) Long contentArticleId,
            @RequestParam(required = false) Long contentArticleVersion,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String lang) {
        //TODO: sort parameter:
        //                .optionalQueryParam("sort","-contentArticleVersion")
        PaginationHelper paginationHelper =
                new PaginationHelper(startRecord, limitRecord, "-contentArticleVersion");
        return contentArticleVersionDao.findByGiven(paginationHelper, contentArticleId,
                contentArticleVersion, folderId, null, title, lang);
    }

    @Override
    @GetMapping("/contentFolders")
    public List<IContentFolder> getContentFolders() {
        final PaginationHelper paginationHelper = new PaginationHelper(null, null, null);
        return contentFolderDao.findByGiven(paginationHelper, null);
    }

    @Override
    @GetMapping("/contentFolders/{folderId}/descendantFolders")
    public List<IContentFolder> getContentFolders(@PathVariable Long folderId) {
        return contentFolderDao.findByAncestorFolderId(folderId);
    }

    @Override
    @GetMapping("/contentArticles/{articleId}")
    public IContentArticle getContentArticle(@PathVariable Long articleId)
            throws ContentNotFoundException {
        if (articleId == null || articleId == 0) {
            throw new ContentNotFoundException("No content article with id " + articleId);
        } else {
            try {
                return this.contentArticleDao.get(articleId);
            } catch (NotFoundException e) {
                throw new ContentNotFoundException("No content article with id " + articleId);
            }
        }
    }

    @Override
    @PutMapping("/contentArticles")
    public boolean updateContentArticle(@RequestBody IContentArticle contentArticle)
            throws ContentNotFoundException {
        Long articleId = contentArticle.getId();
        if (articleId == null || articleId == 0) {
            throw new ContentNotFoundException("No content article with id " + articleId);
        }
        try {
            if (contentArticleDao.get(articleId) == null) {
                throw new ContentNotFoundException("No content article with id " + articleId);
            }
        } catch (NotFoundException e) {
            throw new ContentNotFoundException("No content article with id " + articleId);
        }
        return contentArticleDao.update(contentArticle);
    }

    @Override
    @PostMapping("/contentArticleVersions")
    public IContentArticleVersion createContentArticleVersion(
            @RequestBody IContentArticleVersion contentArticleVersion) {
        Date date = new Date();
        contentArticleVersion.setCreatedAt(new Timestamp(date.getTime()));

        IContentArticle contentArticle;
        if (contentArticleVersion.getArticleId() == null
                || contentArticleVersion.getArticleId() == 0L) {
            contentArticle = new ContentArticleImpl();
            contentArticle.setAuthorUserId(contentArticleVersion.getAuthorUserId());
            contentArticle.setVisible(true);
            contentArticle.setCreatedAt(contentArticleVersion.getCreatedAt());
            contentArticle = this.contentArticleDao.create(contentArticle);
        } else {
            try {
                contentArticle = this.contentArticleDao
                        .get(contentArticleVersion.getArticleId());
            } catch (NotFoundException e) {
                throw new IllegalArgumentException(
                        "ContentArticle " + contentArticleVersion.getArticleId()
                                + " does not exist");
            }
        }
        contentArticleVersion.setArticleId(contentArticle.getId());
        contentArticleVersion = this.contentArticleVersionDao.create(contentArticleVersion);

        contentArticle.setMaxVersionId(contentArticleVersion.getId());
        contentArticle.setFolderId(contentArticleVersion.getFolderId());
        this.contentArticleDao.update(contentArticle);

        return contentArticleVersion;

        // Caching?
        //        //TODO COLAB-2589: fine-grained cache control
        //        ServiceRequestUtils.clearCache(CacheName.CONTENT);
    }

    @Override
    @GetMapping("/contentArticleVersions/{articleVersionId}")
    public IContentArticleVersion getContentArticleVersion(@PathVariable Long articleVersionId)
            throws ContentNotFoundException {
        if (articleVersionId == 0) {
            throw new ContentNotFoundException("No content article version with id=0 given");
        }
        try {
            return this.contentArticleVersionDao.get(articleVersionId);
        } catch (NotFoundException e) {
            throw new ContentNotFoundException("No content article version with id "
                    + articleVersionId + " exists");
        }
    }

    @Override
    @PutMapping("/contentArticleVersions")
    public boolean updateContentArticleVersion(
            @RequestBody IContentArticleVersion contentArticleVersion)
            throws ContentNotFoundException {
        long articleVersionId = contentArticleVersion.getId();
        if (articleVersionId == 0) {
            throw new ContentNotFoundException("No content article version with id=0 exists");
        }
        try {
            if (contentArticleVersionDao.get(articleVersionId) == null) {
                throw new ContentNotFoundException("No content article version with id "
                        + articleVersionId + " exists");
            }
        } catch (NotFoundException e) {
            throw new ContentNotFoundException("No content article version with id "
                    + articleVersionId + " exists");
        }

        return contentArticleVersionDao.update(contentArticleVersion);
        // What about caches?
        //        //TODO COLAB-2589: fine-grained cache control
        //        ServiceRequestUtils.clearCache(CacheName.CONTENT);
    }

    @Override
    @PostMapping("/contentFolders")
    public IContentFolder createContentFolder(@RequestBody IContentFolder contentFolder) {
        return this.contentFolderDao.create(contentFolder);

        //TODO COLAB-2589: fine-grained cache control
        //        ServiceRequestUtils.clearCache(CacheName.CONTENT);
    }

    @Override
    @GetMapping("/contentFolders/{contentFolderId}")
    public IContentFolder getContentFolder(@PathVariable Long contentFolderId)
            throws ContentNotFoundException {
        if (contentFolderId == null || contentFolderId == 0) {
            throw new ContentNotFoundException(
                    "No content folder with content folder id " + contentFolderId);
        } else {
            return this.contentFolderDao.get(contentFolderId);
        }
    }

    @Override
    @GetMapping("/contentFolders/{contentFolderId}/contentArticleVersions")
    public List<IContentArticleVersion> getContentFolderArticleVersions(
            @PathVariable Long contentFolderId) {
        if (contentFolderId == 0) {
            contentFolderId = null;
        }
        return this.contentArticleVersionDao.getByFolderId(contentFolderId);
    }

    @Override
    @PutMapping("/contentFolders/{contentFolderId}")
    public boolean updateContentFolder(@RequestBody IContentFolder contentFolder)
            throws ContentNotFoundException {
        if (contentFolderDao.get(contentFolder.getId()) == null) {
            throw new ContentNotFoundException(
                    "No content folder with content folder id " + contentFolder.getId());
        } else {
            return contentFolderDao.update(contentFolder);
        }
        //        //TODO COLAB-2589: fine-grained cache control
        //        ServiceRequestUtils.clearCache(CacheName.CONTENT);
    }

    @Override
    @DeleteMapping("/contentArticles/{contentArticleId}")
    public void deleteContentArticle(@PathVariable Long contentArticleId) {
        contentArticleService.delete(contentArticleId);
    }

    @Override
    @GetMapping("/contentPages")
    public List<IContentPage> getContentPages(@RequestParam(required = false) String title) {
        return contentPageDao.list(title);
    }

    @Override
    @GetMapping("/contentPages/{pageId}")
    public IContentPage getContentPage(@PathVariable Long pageId) throws ContentNotFoundException {
        return contentPageDao.get(pageId).orElseThrow(
                () -> new ContentNotFoundException("Content page does not exist: " + pageId));
    }

    @Override
    @GetMapping("/contentPages/getByContentArticleId")
    public IContentPage getContentPageByContentArticleId(@RequestParam Long contentArticleId)
            throws ContentNotFoundException {
        return contentPageDao.getByContentArticleId(contentArticleId).orElseThrow(
                () -> new ContentNotFoundException(
                        "Content page does not exist for content article id " + contentArticleId));
    }

    @Override
    @PostMapping("/contentPages")
    public IContentPage createContentPage(@RequestBody IContentPage page) {
        return contentPageDao.create(page);
    }

    @Override
    @PutMapping("/contentPages")
    public Boolean updateContentPage(@RequestBody IContentPage page) {
        return contentPageDao.update(page);
    }
}
