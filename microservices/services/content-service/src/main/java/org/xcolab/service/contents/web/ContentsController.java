package org.xcolab.service.contents.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ContentArticle;
import org.xcolab.model.tables.pojos.ContentArticleVersion;
import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.model.tables.pojos.ContentPage;
import org.xcolab.service.contents.domain.contentFolder.ContentFolderDao;
import org.xcolab.service.contents.domain.contentarticle.ContentArticleDao;
import org.xcolab.service.contents.domain.contentarticleversion.ContentArticleVersionDao;
import org.xcolab.service.contents.domain.page.ContentPageDao;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.contents.service.contentarticle.ContentArticleService;
import org.xcolab.service.utils.PaginationHelper;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class ContentsController {

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

    @RequestMapping(value = "/contentArticles", method = RequestMethod.POST)
    public ContentArticle createContentArticle(@RequestBody ContentArticle contentArticle) {
        java.util.Date date = new java.util.Date();
        contentArticle.setCreatedAt(new Timestamp(date.getTime()));
        return this.contentArticleDao.create(contentArticle);
    }

    @RequestMapping(value = "/contentArticles", method = RequestMethod.GET)
    public List<? extends ContentArticle> getContentArticles(@RequestParam(required = false) Long folderId) {
        if (folderId != null) {
            return contentArticleDao.getArticlesInFolder(folderId);
        }
        return contentArticleDao.getArticles();
    }

    @GetMapping("/contentArticleVersions")
    public List<ContentArticleVersion> getContentArticles(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long folderId,
            @RequestParam(required = false) Long contentArticleId,
            @RequestParam(required = false) Long contentArticleVersion,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String lang,
            @RequestParam(required = false) String sort) {
        final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                sort);
        return contentArticleVersionDao.findByGiven(paginationHelper, contentArticleId,
                contentArticleVersion, folderId, null, title, lang);
    }


    @RequestMapping(value = "/contentFolders", method = RequestMethod.GET)
    public List<ContentFolder> getContentFolders(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) String sort,
            @RequestParam(required = false) Long parentFolderId) {
        final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                sort);
        return contentFolderDao.findByGiven(paginationHelper, parentFolderId);
    }

    @GetMapping("/contentFolders/{folderId}/descendantFolders")
    public List<ContentFolder> listDescendantFolders(@PathVariable long folderId) {
        return contentFolderDao.findByAncestorFolderId(folderId);
    }

    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.GET)
    public ContentArticle getContentArticle(@PathVariable("articleId") Long articleId)
            throws NotFoundException {
        if (articleId == null || articleId == 0) {
            throw new NotFoundException("No content article with id given");
        } else {
            return this.contentArticleDao.get(articleId);
        }
    }

    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.PUT)
    public boolean updateContentArticle(@RequestBody ContentArticle contentArticle,
            @PathVariable("articleId") Long articleId) throws NotFoundException {

        if (articleId == null || articleId == 0 || contentArticleDao.get(articleId) == null) {
            throw new NotFoundException("No content article with id " + articleId);
        } else {
            return contentArticleDao.update(contentArticle);
        }
    }

    @PostMapping("/contentArticleVersions")
    public ContentArticleVersion createContentArticleVersion(
            @RequestBody ContentArticleVersion contentArticleVersion) {
        java.util.Date date = new java.util.Date();
        contentArticleVersion.setCreatedAt(new Timestamp(date.getTime()));

        ContentArticle contentArticle;
        if (contentArticleVersion.getContentArticleId() == null
                || contentArticleVersion.getContentArticleId() == 0L) {
            contentArticle = new ContentArticle();
            contentArticle.setAuthorId(contentArticleVersion.getAuthorId());
            contentArticle.setVisible(true);
            contentArticle.setCreatedAt(contentArticleVersion.getCreatedAt());
            contentArticle = this.contentArticleDao.create(contentArticle);
        } else {
            try {
                contentArticle = this.contentArticleDao
                        .get(contentArticleVersion.getContentArticleId());
            } catch (NotFoundException e) {
                throw new IllegalArgumentException(
                        "ContentArticle " + contentArticleVersion.getContentArticleId()
                                + " does not exist");
            }
        }
        contentArticleVersion.setContentArticleId(contentArticle.getContentArticleId());
        contentArticleVersion = this.contentArticleVersionDao.create(contentArticleVersion);

        contentArticle.setMaxVersionId(contentArticleVersion.getContentArticleVersionId());
        contentArticle.setFolderId(contentArticleVersion.getFolderId());
        this.contentArticleDao.update(contentArticle);

        return contentArticleVersion;
    }

    @GetMapping("/contentArticleVersions/{articleVersionId}")
    public ContentArticleVersion getContentArticleVersion(@PathVariable long articleVersionId)
            throws NotFoundException {
        if (articleVersionId == 0) {
            throw new NotFoundException("No content article version with id given");
        }
        return this.contentArticleVersionDao.get(articleVersionId);
    }

    @PutMapping("/contentArticleVersions/{articleVersionId}")
    public boolean updateContentArticleVersion(
            @RequestBody ContentArticleVersion contentArticleVersion,
            @PathVariable("articleVersionId") Long articleVersionId) throws NotFoundException {

        if (articleVersionId == null || articleVersionId == 0
                || contentArticleVersionDao.get(articleVersionId) == null) {
            throw new NotFoundException("No content article version with id "
                    + articleVersionId + " exists");
        } else {
            return contentArticleVersionDao.update(contentArticleVersion);
        }
    }

    @PostMapping("/contentFolders")
    public ContentFolder createContentFolder(@RequestBody ContentFolder contentFolder) {
        return this.contentFolderDao.create(contentFolder);
    }

    @GetMapping( "/contentFolders/{contentFolderId}")
    public ContentFolder getContentFolder(@PathVariable("contentFolderId") Long contentFolderId)
            throws NotFoundException {
        if (contentFolderId == null || contentFolderId == 0) {
            throw new NotFoundException("No content folder with id given");
        } else {
            return this.contentFolderDao.get(contentFolderId);
        }
    }

    @GetMapping(value = "/contentFolders/{contentFolderId}/contentArticleVersions")
    public List<ContentArticleVersion> getContentFolderArticleVersions(
            @PathVariable("contentFolderId") Long contentFolderId) throws NotFoundException {
        if (contentFolderId == 0) {
            contentFolderId = null;
        }
        return this.contentArticleVersionDao.getByFolderId(contentFolderId);

    }

    @RequestMapping(value = "/contentFolders/{contentFolderId}", method = RequestMethod.PUT)
    public boolean updateContentFolder(@RequestBody ContentFolder contentFolder,
            @PathVariable("contentFolderId") Long contentFolderId) throws NotFoundException {

        if (contentFolderId == null || contentFolderId == 0
                || contentFolderDao.get(contentFolderId) == null) {
            throw new NotFoundException("No content folder with id given");
        } else {
                return contentFolderDao.update(contentFolder);
        }
    }

    @RequestMapping(value = "/contentArticles/{contentArticleId}", method = RequestMethod.DELETE)
    public int deleteArticle(@PathVariable long contentArticleId) {
        return contentArticleService.delete(contentArticleId);
    }

    @GetMapping("/contentPages")
    public List<ContentPage> listContentPages(@RequestParam(required = false) String title) {
        return contentPageDao.list(title);
    }

    @GetMapping("/contentPages/{pageId}")
    public ContentPage getContentPage(@PathVariable long pageId) throws NotFoundException {
        return contentPageDao.get(pageId).orElseThrow(NotFoundException::new);
    }

    @GetMapping("/contentPages/getByContentArticleId")
    public ContentPage getContentPageByContentArticleId(@RequestParam long contentArticleId) throws NotFoundException {
        return contentPageDao.getByContentArticleId(contentArticleId).orElseThrow(NotFoundException::new);
    }

    @PostMapping("/contentPages")
    public ContentPage createContentPage(@RequestBody ContentPage page) {
        return contentPageDao.create(page);
    }

    @PutMapping("/contentPages/{pageId}")
    public boolean updateContentPage(@PathVariable long pageId, @RequestBody ContentPage page) {
        return pageId == page.getPageId() && contentPageDao.update(page);
    }
}
