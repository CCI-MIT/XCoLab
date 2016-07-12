package org.xcolab.client.contents;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.UriBuilder;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class ContentsClient {

    private static final RestService contentService = new RestService("contents-service");

    private static final RestResource<ContentArticle> contentArticleResource =
            new RestResource<>(contentService, "contentArticles", ContentArticle.TYPES);
    private static final RestResource<ContentArticleVersion> contentArticleVersionResource =
            new RestResource<>(contentService, "contentArticleVersions",
                    ContentArticleVersion.TYPES);
    private static final RestResource<ContentFolder> contentFolderResource =
            new RestResource<>(contentService, "contentFolders", ContentFolder.TYPES);

    private ContentsClient() {
    }

    public static List<ContentArticle> getContentArticles(Long folderId) {
        final UriBuilder uriBuilder = contentArticleResource.getResourceUrl()
                .optionalQueryParam("folderId", folderId);
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ContentArticle>>() {
                });
    }

    public static ContentArticleVersion getLatestContentArticleVersion(long folderId, String title)
            throws ContentNotFoundException {
        //TODO: port to new methods
        final UriBuilder uriBuilder = contentArticleVersionResource.getResourceUrl()
                    .queryParam("folderId", folderId)
                    .queryParam("title", title)
                    .queryParam("sort", "-contentArticleVersion");
        try {
            return RequestUtils.getFirstFromList(uriBuilder,
                    new ParameterizedTypeReference<List<ContentArticleVersion>>() {
                    }, "_latest_folderId_" + folderId + "_title_" + title);
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException("No ContentArticleVersion with title " + title
                    + " found in folder " + folderId);
        }
    }

    public static ContentArticleVersion getLatestContentArticleVersion(long articleId)
            throws ContentNotFoundException {
        final UriBuilder uriBuilder = contentArticleVersionResource.getResourceUrl()
                    .queryParam("contentArticleId", articleId)
                    .queryParam("sort", "-contentArticleVersion");
        try {
            return RequestUtils.getFirstFromList(uriBuilder,
                    new ParameterizedTypeReference<List<ContentArticleVersion>>() {
                    }, "_latest_articleId_" + articleId);
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "No ContentArticleVersion for contentArticleID " + articleId);
        }
    }

    public static List<ContentArticleVersion> getContentArticleVersions(Integer startRecord,
            Integer limitRecord, Long folderId, Long contentArticleId,
            Long contentArticleVersion, String title) {
        return contentArticleVersionResource.list()
                .optionalQueryParam("startRecord", startRecord)
                .optionalQueryParam("limitRecord", limitRecord)
                .optionalQueryParam("contentArticleId", contentArticleId)
                .optionalQueryParam("folderId", folderId)
                .optionalQueryParam("contentArticleVersion", contentArticleVersion)
                .optionalQueryParam("title", title)
                .execute();
    }

    public static List<ContentFolder> getContentFolders() {
        return contentFolderResource.list().execute();
    }

    public static List<ContentFolder> getContentFolders(Long parentFolderId) {
        return contentFolderResource.list()
                .queryParam("parentFolderId", parentFolderId)
                .execute();
    }

    public static ContentArticle getContentArticle(Long contentArticleId)
            throws ContentNotFoundException {
        try {
            return contentArticleResource.get(contentArticleId).execute();
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "ContentArticle " + contentArticleId + " does not exist");
        }
    }

    public static ContentArticle createContentArticle(ContentArticle contentArticle) {
        return contentArticleResource.create(contentArticle).execute();
    }

    public static boolean updateContentArticle(ContentArticle contentArticle) {
        return contentArticleResource.update(contentArticle, contentArticle.getContentArticleId())
                .execute();
    }

    public static ContentArticleVersion getContentArticleVersion(Long contentArticleVersionId)
            throws ContentNotFoundException {
        try {
            return contentArticleVersionResource.get(contentArticleVersionId).execute();
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "ContentArticleVersion " + contentArticleVersionId + " does not exist");
        }
    }

    public static ContentArticleVersion createContentArticleVersion(
            ContentArticleVersion contentArticleVersion) {
        return contentArticleVersionResource.create(contentArticleVersion).execute();
    }

    public static boolean updateContentArticleVersion(ContentArticleVersion contentArticleVersion) {
        return contentArticleVersionResource
                .update(contentArticleVersion, contentArticleVersion.getContentArticleVersionId())
                .execute();
    }

    public static ContentFolder getContentFolder(long contentFolderId)
            throws ContentNotFoundException {
        try {
            return contentFolderResource.get(contentFolderId).execute();
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "ContentFolder " + contentFolderId + " does not exist");
        }
    }

    public static ContentFolder createContentFolder(ContentFolder contentFolder) {
        return contentFolderResource.create(contentFolder).execute();
    }

    public static boolean updateContentFolder(ContentFolder contentFolder) {
        return contentFolderResource.update(contentFolder, contentFolder.getContentFolderId())
                .execute();
    }

    public static List<ContentArticleVersion> getChildArticleVersions(long folderId) {
        return contentFolderResource
                .getSubResource(folderId, "contentArticleVersions", ContentArticleVersion.TYPES)
                .list()
                .execute();
    }
}
