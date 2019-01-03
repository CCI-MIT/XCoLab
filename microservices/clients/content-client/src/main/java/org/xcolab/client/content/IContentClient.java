package org.xcolab.client.content;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.content.exceptions.ContentNotFoundException;
import org.xcolab.client.content.pojo.IContentArticle;
import org.xcolab.client.content.pojo.IContentArticleVersion;
import org.xcolab.client.content.pojo.IContentFolder;
import org.xcolab.client.content.pojo.IContentPage;

import java.util.List;

@FeignClient("xcolab-content-service")
public interface IContentClient {

    @GetMapping("/contentArticles")
    List<? extends IContentArticle> getContentArticles(
            @RequestParam(value = "folderId", required = false) Long folderId);

    default IContentArticleVersion getLatestContentArticleVersion(long folderId, String title)
            throws ContentNotFoundException {
        List<IContentArticleVersion> contentArticleVersions =
                getContentArticleVersions(null, null, folderId, null, null, title, null);

        if (contentArticleVersions.isEmpty()) {
            throw new ContentNotFoundException("No ContentArticleVersion with title " + title
                    + " found in folder " + folderId);
        }
        return contentArticleVersions.get(0);
    }

    default IContentArticleVersion getLatestContentArticleVersion(long contentArticleId)
            throws ContentNotFoundException {
        List<IContentArticleVersion> contentArticleVersions =
                getContentArticleVersions(null, null, null, contentArticleId, null, null, null);

        if (contentArticleVersions.isEmpty()) {
            throw new ContentNotFoundException(
                    "No ContentArticleVersion for contentArticleID " + contentArticleId);
        }
        return contentArticleVersions.get(0);
    }

    @GetMapping("/contentArticleVersions")
    List<IContentArticleVersion> getContentArticleVersions(
            @RequestParam(value = "startRecord", required = false) Integer startRecord,
            @RequestParam(value = "limitRecord", required = false) Integer limitRecord,
            @RequestParam(value = "folderId", required = false) Long folderId,
            @RequestParam(value = "contentArticleId", required = false) Long contentArticleId,
            @RequestParam(value = "contentArticleVersion", required = false)
                    Long contentArticleVersion,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "lang", required = false) String lang);

    @GetMapping("/contentFolders")
    List<IContentFolder> getContentFolders();

    @GetMapping("/contentFolders/{parentFolderId}/descendantFolders")
    List<IContentFolder> getContentFolders(@PathVariable("parentFolderId") Long parentFolderId);

    @GetMapping("/contentArticles/{articleId}")
    IContentArticle getContentArticle(@PathVariable("articleId") Long articleId)
            throws ContentNotFoundException;

    @PostMapping("/contentArticles")
    IContentArticle createContentArticle(@RequestBody IContentArticle contentArticle);

    @DeleteMapping("/contentArticles/{contentArticleId}")
    void deleteContentArticle(@PathVariable("contentArticleId") Long contentArticleId);

    @PutMapping("/contentArticles")
    boolean updateContentArticle(@RequestBody IContentArticle contentArticle)
            throws ContentNotFoundException;

    @GetMapping("/contentArticleVersions/{articleVersionId}")
    IContentArticleVersion getContentArticleVersion(
            @PathVariable("articleVersionId") Long articleVersionId)
            throws ContentNotFoundException;

    @PostMapping("/contentArticleVersions")
    IContentArticleVersion createContentArticleVersion(
            @RequestBody IContentArticleVersion contentArticleVersion);

    @PutMapping("/contentArticleVersions")
    boolean updateContentArticleVersion(@RequestBody IContentArticleVersion contentArticleVersion)
            throws ContentNotFoundException;

    @GetMapping("/contentFolders/{contentFolderId}")
    IContentFolder getContentFolder(@PathVariable("contentFolderId") Long contentFolderId)
            throws ContentNotFoundException;

    @PostMapping("/contentFolders")
    IContentFolder createContentFolder(@RequestBody IContentFolder contentFolder);

    @PutMapping("/contentFolders/{contentFolderId}")
    boolean updateContentFolder(@RequestBody IContentFolder IContentFolder)
            throws ContentNotFoundException;

    @PostMapping("/contentPages")
    IContentPage createContentPage(@RequestBody IContentPage page);

    @PutMapping("/contentPages")
    Boolean updateContentPage(@RequestBody IContentPage page);

    @GetMapping("/contentFolders/{contentFolderId}/contentArticleVersions")
    List<IContentArticleVersion> getContentFolderArticleVersions(
            @PathVariable("contentFolderId") Long contentFolderId);

    default IContentPage getContentPage(String title) throws ContentNotFoundException {
        List<IContentPage> contentPages = getContentPages(title);
        if (contentPages.isEmpty()) {
            throw new ContentNotFoundException("Content page does not exist: " + title);
        }
        return contentPages.get(0);
    }

    @GetMapping("/contentPages/{pageId}")
    IContentPage getContentPage(@PathVariable("pageId") Long pageId)
            throws ContentNotFoundException;

    @GetMapping("/contentPages/getByContentArticleId")
    IContentPage getContentPageByContentArticleId(
            @RequestParam("contentArticleId") Long contentArticleId)
            throws ContentNotFoundException;

    default IContentArticleVersion getLatestVersionByArticleIdAndLanguage(long contentArticleId,
            String language) throws ContentNotFoundException {
        List<IContentArticleVersion> contentArticleVersions =
                getContentArticleVersions(null, null, null, contentArticleId, null, null, language);

        if (contentArticleVersions.isEmpty()) {
            throw new ContentNotFoundException(
                    "No ContentArticleVersion for contentArticleID " + contentArticleId);
        }
        return contentArticleVersions.get(0);
    }

    @GetMapping("/contentPages")
    List<IContentPage> getContentPages(
            @RequestParam(value = "title", required = false) String title);
}
