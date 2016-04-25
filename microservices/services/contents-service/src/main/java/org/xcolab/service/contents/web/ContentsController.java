package org.xcolab.service.contents.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xcolab.model.tables.pojos.ContentArticle;
import org.xcolab.model.tables.pojos.ContentArticleVersion;
import org.xcolab.model.tables.pojos.ContentFolder;
import org.xcolab.service.contents.exceptions.NotFoundException;
import org.xcolab.service.contents.service.contentarticle.ContentArticleService;
import org.xcolab.service.contents.service.contentarticleversion.ContentArticleVersionService;
import org.xcolab.service.contents.service.contentfolder.ContentFolderService;

@RestController
public class ContentsController {

    @Autowired
    private ContentArticleService contentArticleService;

    @Autowired
    private ContentArticleVersionService contentArticleVersionService;

    @Autowired
    private ContentFolderService contentFolderService;

    @RequestMapping(value = "/contentArticles/", method = RequestMethod.PUT)
    public ContentArticle createContentArticle(@RequestBody ContentArticle contentArticle) {

        return this.contentArticleService.create(contentArticle);
    }

    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.GET)
    public ContentArticle getContentArticle(@PathVariable("articleId") Long articleId) throws NotFoundException {
        if (articleId == null || articleId == 0) {
            throw new NotFoundException("No content article with id given");
        } else {
            return this.contentArticleService.get(articleId);//contentArticleService.getMember(memberId);
        }
    }

    @RequestMapping(value = "/contentArticles/{articleId}", method = RequestMethod.POST)
    public String updateContentArticle(@RequestBody ContentArticle contentArticle,
                                       @PathVariable("articleId") Long articleId) throws NotFoundException {

        if (articleId == null || articleId == 0) {
            throw new NotFoundException("No content article with id given");
        } else {
            if (this.contentArticleService.get(articleId) != null) {
                this.contentArticleService.update(contentArticle);// contentArticleService.getMember(memberId);
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
            ContentArticle contentArticle = this.contentArticleService.get(articleId);
            if ( contentArticle!= null) {
                contentArticle.setVisible(false);
                this.contentArticleService.update(contentArticle);// contentArticleService.getMember(memberId);
                return "Content article updated successfully";
            } else {
                throw new NotFoundException("No content article with id given");
            }
        }

    }

    @RequestMapping(value = "/contentArticleVersions/", method = RequestMethod.PUT)
    public ContentArticleVersion createContentArticleVersion(@RequestBody ContentArticleVersion contentArticleVersion) {

        return this.contentArticleVersionService.create(contentArticleVersion);
    }

    @RequestMapping(value = "/contentArticleVersions/{articleVersionId}", method = RequestMethod.GET)
    public ContentArticleVersion getContentArticleVersion(@PathVariable("articleVersionId") Long articleVersionId) throws NotFoundException {
        if (articleVersionId == null || articleVersionId == 0) {
            throw new NotFoundException("No content article version with id given");
        } else {
            return this.contentArticleVersionService.get(articleVersionId);//contentArticleService.getMember(memberId);
        }
    }

    @RequestMapping(value = "/contentArticleVersions/{articleVersionId}", method = RequestMethod.POST)
    public String updateContentArticle(@RequestBody ContentArticleVersion contentArticleVersion,
                                       @PathVariable("articleVersionId") Long articleVersionId) throws NotFoundException {

        if (articleVersionId == null || articleVersionId == 0) {
            throw new NotFoundException("No content article version with id given");
        } else {
            if (this.contentArticleVersionService.get(articleVersionId) != null) {
                this.contentArticleVersionService.update(contentArticleVersion);// contentArticleService.getMember(memberId);
                return "Content article version updated successfully";
            } else {
                throw new NotFoundException("No content article version with id given");
            }
        }

    }

    @RequestMapping(value = "/contentFolders/", method = RequestMethod.PUT)
    public ContentFolder createContentFolder(@RequestBody ContentFolder contentFolder) {

        return this.contentFolderService.create(contentFolder);
    }

    @RequestMapping(value = "/contentFolders/{contentFolderId}", method = RequestMethod.GET)
    public ContentArticleVersion getContentFolder(@PathVariable("contentFolderId") Long contentFolderId) throws NotFoundException {
        if (contentFolderId == null || contentFolderId == 0) {
            throw new NotFoundException("No content folder with id given");
        } else {
            return this.contentArticleVersionService.get(contentFolderId);//contentArticleService.getMember(memberId);
        }
    }

    @RequestMapping(value = "/contentFolders/{contentFolderId}", method = RequestMethod.POST)
    public String updateContentFolder(@RequestBody ContentFolder contentFolder,
                                       @PathVariable("contentFolderId") Long contentFolderId) throws NotFoundException {

        if (contentFolderId == null || contentFolderId == 0) {
            throw new NotFoundException("No content folder with id given");
        } else {
            if (this.contentFolderService.get(contentFolderId) != null) {
                this.contentFolderService.update(contentFolder);// contentArticleService.getMember(memberId);
                return "Content folder updated successfully";
            } else {
                throw new NotFoundException("No content folder with id given");
            }
        }

    }

}
