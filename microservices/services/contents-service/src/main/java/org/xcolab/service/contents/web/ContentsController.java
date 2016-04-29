package org.xcolab.service.contents.web;

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
import org.xcolab.service.contents.domain.contentFolder.ContentFolderDao;
import org.xcolab.service.contents.domain.contentarticle.ContentArticleDao;
import org.xcolab.service.contents.domain.contentarticleversion.ContentArticleVersionDao;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.contents.service.contentarticle.ContentArticleService;
import org.xcolab.service.contents.service.contentarticleversion.ContentArticleVersionService;
import org.xcolab.service.contents.service.contentfolder.ContentFolderService;

import java.util.List;

@RestController
public class ContentsController {

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

        return this.contentArticleDao.create(contentArticle);
    }

    @RequestMapping(value = "/contentArticles", method = RequestMethod.GET)
    public List<ContentArticle> getContentArticles(@RequestParam(required = false) Long folderId) {
        if (folderId != null) {
            return contentArticleDao.getArticlesInFolder(folderId);
        }
        return contentArticleDao.getArticles();
    }

    @RequestMapping(value = "/contentFolders", method = RequestMethod.GET)
    public List<ContentFolder> getContentFolders(@RequestParam(required = false) Long parentFolderId) {
        if (parentFolderId != null) {
            return contentFolderDao.getFoldersInFolder(parentFolderId);
        }
        return contentFolderDao.getFolders();
    }

    @RequestMapping(value = "/contentArticleVersions", method = RequestMethod.GET)
    public List<ContentArticleVersion> getContentArticleVersions(@RequestParam(required = false) Long articleId) {
        if (articleId != null) {
            return contentArticleVersionDao.getVersionsForArticle(articleId);
        }
        return contentArticleVersionDao.getVersions();
    }

    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.GET)
    public ContentArticle getContentArticle(@PathVariable("articleId") Long articleId) throws NotFoundException {
        if (articleId == null || articleId == 0) {
            throw new NotFoundException("No content article with id given");
        } else {
            return this.contentArticleDao.get(articleId);
        }
    }

    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.PUT)
    public String updateContentArticle(@RequestBody ContentArticle contentArticle,
                                       @PathVariable("articleId") Long articleId) throws NotFoundException {

        if (articleId == null || articleId == 0) {
            throw new NotFoundException("No content article with id given");
        } else {
            if (this.contentArticleDao.get(articleId) != null) {
                this.contentArticleDao.update(contentArticle);
                return "Content article updated successfully";
            } else {
                throw new NotFoundException("No content article with id given");
            }
        }

    }

    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.DELETE)
    public String deleteContentArticle(@PathVariable("articleId") Long articleId) throws NotFoundException {

        if (articleId == null || articleId == 0) {
            throw new NotFoundException("No content article with id given");
        } else {
            ContentArticle contentArticle = this.contentArticleDao.get(articleId);
            if ( contentArticle!= null) {
                contentArticle.setVisible(false);
                this.contentArticleDao.update(contentArticle);
                return "Content article updated successfully";
            } else {
                throw new NotFoundException("No content article with id given");
            }
        }

    }

    @RequestMapping(value = "/contentArticleVersions/", method = RequestMethod.POST)
    public ContentArticleVersion createContentArticleVersion(@RequestBody ContentArticleVersion contentArticleVersion) {
        return this.contentArticleVersionDao.create(contentArticleVersion);
    }

    @RequestMapping(value = "/contentArticleVersions/{articleVersionId}", method = RequestMethod.GET)
    public ContentArticleVersion getContentArticleVersion(@PathVariable("articleVersionId") Long articleVersionId) throws NotFoundException {
        if (articleVersionId == null || articleVersionId == 0) {
            throw new NotFoundException("No content article version with id given");
        } else {
            return this.contentArticleVersionDao.get(articleVersionId);
        }
    }

    @RequestMapping(value = "/contentArticleVersions/{articleVersionId}", method = RequestMethod.PUT)
    public String updateContentArticle(@RequestBody ContentArticleVersion contentArticleVersion,
                                       @PathVariable("articleVersionId") Long articleVersionId) throws NotFoundException {

        if (articleVersionId == null || articleVersionId == 0) {
            throw new NotFoundException("No content article version with id given");
        } else {
            if (this.contentArticleVersionDao.get(articleVersionId) != null) {
                this.contentArticleVersionDao.update(contentArticleVersion);
                return "Content article version updated successfully";
            } else {
                throw new NotFoundException("No content article version with id given");
            }
        }

    }

    @RequestMapping(value = "/contentFolders/", method = RequestMethod.POST)
    public ContentFolder createContentFolder(@RequestBody ContentFolder contentFolder) {

        return this.contentFolderDao.create(contentFolder);
    }

    @RequestMapping(value = "/contentFolders/{contentFolderId}", method = RequestMethod.GET)
    public ContentFolder getContentFolder(@PathVariable("contentFolderId") Long contentFolderId) throws NotFoundException {
        if (contentFolderId == null || contentFolderId == 0) {
            throw new NotFoundException("No content folder with id given");
        } else {
            return this.contentFolderDao.get(contentFolderId);
        }
    }

    @RequestMapping(value = "/contentFolders/{contentFolderId}/contentArticles/", method = RequestMethod.GET)
    public ContentArticleVersion getContentFolderArticles(@PathVariable("contentFolderId") Long contentFolderId) throws NotFoundException {
        if (contentFolderId == null || contentFolderId == 0) {
            throw new NotFoundException("No content folder with id given");
        } else {
            return this.contentArticleVersionDao.getByFolderId(contentFolderId);
        }
    }

    @RequestMapping(value = "/contentFolders/{contentFolderId}", method = RequestMethod.PUT)
    public String updateContentFolder(@RequestBody ContentFolder contentFolder,
                                       @PathVariable("contentFolderId") Long contentFolderId) throws NotFoundException {

        if (contentFolderId == null || contentFolderId == 0) {
            throw new NotFoundException("No content folder with id given");
        } else {
            if (this.contentFolderDao.get(contentFolderId) != null) {
                this.contentFolderDao.update(contentFolder);
                return "Content folder updated successfully";
            } else {
                throw new NotFoundException("No content folder with id given");
            }
        }

    }

}
