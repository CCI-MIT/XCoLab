package org.xcolab.service.contents.web;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.xcolab.model.tables.pojos.ContentArticle;
import org.xcolab.model.tables.pojos.ContentArticleVersion;
import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.model.tables.pojos.Wikipage;
import org.xcolab.service.contents.domain.contentFolder.ContentFolderDao;
import org.xcolab.service.contents.domain.contentarticle.ContentArticleDao;
import org.xcolab.service.contents.domain.contentarticleversion.ContentArticleVersionDao;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.contents.service.WikipageMigrationHelper;
import org.xcolab.service.contents.service.contentarticle.ContentArticleService;
import org.xcolab.service.contents.service.contentarticleversion.ContentArticleVersionService;
import org.xcolab.service.contents.service.contentfolder.ContentFolderService;
import org.xcolab.service.utils.PaginationHelper;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;

@RestController
public class ContentsController {

    @Autowired
    private WikipageMigrationHelper wikipageMigrationHelper;

    @Autowired
    private ContentArticleService contentArticleService;

    @Autowired
    private ContentArticleVersionService contentArticleVersionService;

    @Autowired
    private ContentFolderService contentFolderService;

    @Autowired
    private ContentArticleDao contentArticleDao;

    @Autowired
    private ContentFolderDao contentFolderDao;

    @Autowired
    private ContentArticleVersionDao contentArticleVersionDao;

    @RequestMapping(value = "/contentArticles/", method = RequestMethod.POST)
    public ContentArticle createContentArticle(@RequestBody ContentArticle contentArticle) {
        java.util.Date date = new java.util.Date();
        contentArticle.setCreateDate(new Timestamp(date.getTime()));
        return this.contentArticleDao.create(contentArticle);
    }

    @RequestMapping(value = "/contentArticles", method = RequestMethod.GET)
    public List<ContentArticle> getContentArticles(@RequestParam(required = false) Long folderId) {
        if (folderId != null) {
            return contentArticleDao.getArticlesInFolder(folderId);
        }
        return contentArticleDao.getArticles();
    }

    @RequestMapping(value = "/contentArticleVersions", method = RequestMethod.GET)
    public List<ContentArticleVersion> getContentArticles(
            @RequestParam(required = false) Integer startRecord,
            @RequestParam(required = false) Integer limitRecord,
            @RequestParam(required = false) Long folderId,
            @RequestParam(required = false) Long contentArticleId,
            @RequestParam(required = false) Long contentArticleVersion,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String sort) {
        final PaginationHelper paginationHelper = new PaginationHelper(startRecord, limitRecord,
                sort);
        return contentArticleVersionDao.findByGiven(paginationHelper, contentArticleId, contentArticleVersion,
                folderId, title);
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

    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.DELETE)
    public String deleteContentArticle(@PathVariable("articleId") Long articleId)
            throws NotFoundException {

        if (articleId == null || articleId == 0) {
            throw new NotFoundException("No content article with id given");
        } else {
            ContentArticle contentArticle = this.contentArticleDao.get(articleId);
            if (contentArticle != null) {
                contentArticle.setVisible(false);
                this.contentArticleDao.update(contentArticle);
                return "Content article updated successfully";
            } else {
                throw new NotFoundException("No content article with id given");
            }
        }
    }

    @RequestMapping(value = "/contentArticleVersions/", method = RequestMethod.POST)
    public ContentArticleVersion createContentArticleVersion(
            @RequestBody ContentArticleVersion contentArticleVersion) {
        java.util.Date date = new java.util.Date();
        contentArticleVersion.setCreateDate(new Timestamp(date.getTime()));

        ContentArticle contentArticle;
        if (contentArticleVersion.getContentArticleId() == null
                || contentArticleVersion.getContentArticleId() == 0L) {
            contentArticle = new ContentArticle();
            contentArticle.setAuthorId(contentArticleVersion.getAuthorId());
            contentArticle.setVisible(true);
            contentArticle.setCreateDate(contentArticleVersion.getCreateDate());
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

    @RequestMapping(value = "/contentArticleVersions/{articleVersionId}", method = RequestMethod.GET)
    public ContentArticleVersion getContentArticleVersion(
            @PathVariable("articleVersionId") Long articleVersionId) throws NotFoundException {
        if (articleVersionId == null || articleVersionId == 0) {
            throw new NotFoundException("No content article version with id given");
        } else {
            return this.contentArticleVersionDao.get(articleVersionId);
        }
    }

    @RequestMapping(value = "/contentArticleVersions/{articleVersionId}", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/contentFolders/", method = RequestMethod.POST)
    public ContentFolder createContentFolder(@RequestBody ContentFolder contentFolder) {
        return this.contentFolderDao.create(contentFolder);
    }

    @RequestMapping(value = "/contentFolders/{contentFolderId}", method = RequestMethod.GET)
    public ContentFolder getContentFolder(@PathVariable("contentFolderId") Long contentFolderId)
            throws NotFoundException {
        if (contentFolderId == null || contentFolderId == 0) {
            throw new NotFoundException("No content folder with id given");
        } else {
            return this.contentFolderDao.get(contentFolderId);
        }
    }

    @RequestMapping(value = "/contentFolders/{contentFolderId}/contentArticlesVersions/", method = RequestMethod.GET)
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

    //TODO: temporary endpoint for migration
    @RequestMapping(value = "/migration/creolePages", method = RequestMethod.GET)
    public List<Wikipage> getCreolePages() {
        return wikipageMigrationHelper.getCreolePages();
    }

    @RequestMapping(value = "/migration/getClimateCoLabContent", method = RequestMethod.GET)
    public List<Wikipage> getClimateCoLabContent(
            @RequestParam String sessionId,
            @RequestParam String lrSessionState
    ) throws IOException {
        final List<Wikipage> pages = wikipageMigrationHelper.getAllPages();
        for (Wikipage page : pages) {
            final String url = "http://climatecolab.org:18081/resources/-/wiki/Main/" + URLEncoder.encode(page.getTitle(), "UTF-8");
            try {
                Document doc = Jsoup.connect(url).get();
                String html = doc.select(".wiki-body").html();
                boolean isRestricted = false;
                if (StringUtils.isNotBlank(html)) {
                    page.setContent(html);
                } else {
                        doc = Jsoup.connect(url)
                                .cookie("JSESSIONID", sessionId)
                                .cookie("LFR_SESSION_STATE_2057710", lrSessionState)
                                .get();
                        html = doc.select(".wiki-body").html();
                        if (StringUtils.isNotBlank(html)) {
                            page.setContent(html);
                            isRestricted = true;
                            System.out.println("Found content by using cookies: " + url);
                        } else {
                            System.out.println("No content on url: " + url);
                        }
                }
                //insert into content article
                if (StringUtils.isNotBlank(html)) {

                    ContentArticle contentArticle = new ContentArticle();
                    contentArticle.setViewRoleGroupId(isRestricted ? 3L : null);
                    contentArticle.setEditRoleGroupId(2L);
                    contentArticle.setFolderId(3L);
                    contentArticle.setAuthorId(page.getUserId());
                    contentArticle.setVisible(true);
                    contentArticle.setCreateDate(page.getCreateDate());
                    contentArticle = contentArticleDao.create(contentArticle);

                    ContentArticleVersion contentArticleVersion = new ContentArticleVersion();
                    contentArticleVersion.setCreateDate(page.getCreateDate());
                    contentArticleVersion.setTitle(page.getTitle());
                    contentArticleVersion.setContent(html);
                    contentArticleVersion.setFolderId(3L);
                    contentArticleVersion.setAuthorId(page.getUserId());
                    contentArticleVersion.setContentArticleId(contentArticle.getContentArticleId());
                    contentArticleVersion = contentArticleVersionDao.create(contentArticleVersion);

                    contentArticle.setMaxVersionId(contentArticleVersion.getContentArticleVersionId());
                    contentArticleDao.update(contentArticle);
                }
            } catch (HttpStatusException e) {
                System.out.println("Could not get url: " + url + " (modifiedDate = " + page.getModifiedDate() + ")");
            }
        }
        return pages;
    }

    @RequestMapping(value = "/contentArticles/{contentArticleId}", method = RequestMethod.DELETE)
    public int deleteArticle(@PathVariable long contentArticleId) {
        return contentArticleService.delete(contentArticleId);
    }
}
