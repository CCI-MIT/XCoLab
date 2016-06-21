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
    private static final RestResource contentArticleResource = new RestResource(contentService,
            "contentArticle");
    private static final RestResource contentArticleVersionResource = new RestResource(contentService,
            "contentArticleVersion");
    private static final RestResource contentFolderResource = new RestResource(contentService,
            "contentFolder");

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
        final UriBuilder uriBuilder = contentArticleVersionResource.getResourceUrl()
                .optionalQueryParam("startRecord", startRecord)
                .optionalQueryParam("limitRecord", limitRecord)
                .optionalQueryParam("contentArticleId", contentArticleId)
                .optionalQueryParam("folderId", folderId)
                .optionalQueryParam("contentArticleVersion", contentArticleVersion)
                .optionalQueryParam("title", title);
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ContentArticleVersion>>() {
                });
    }

    public static List<ContentFolder> getContentFolders() {
        final UriBuilder uriBuilder = contentFolderResource.getResourceUrl();
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ContentFolder>>() {
                });
    }

    public static List<ContentFolder> getContentFolders(Long parentFolderId) {
        final UriBuilder uriBuilder = contentFolderResource.getResourceUrl()
                .queryParam("parentFolderId", parentFolderId);
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ContentFolder>>() {
                });
    }

    public static ContentArticle getContentArticle(Long contentArticleId)
            throws ContentNotFoundException {
        final UriBuilder uriBuilder = contentArticleResource.getResourceUrl(contentArticleId);

        try {
            return RequestUtils.get(uriBuilder, ContentArticle.class);
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "ContentArticle " + contentArticleId + " does not exist");
        }
    }

    public static ContentArticle createContentArticle(ContentArticle contentArticle) {
        final UriBuilder uriBuilder = contentArticleResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, contentArticle, ContentArticle.class);
    }

    public static void updateContentArticle(ContentArticle contentArticle) {
        final UriBuilder uriBuilder = contentArticleResource.getResourceUrl(
                contentArticle.getContentArticleId());
        RequestUtils.put(uriBuilder, contentArticle);
    }

    public static ContentArticleVersion getContentArticleVersion(Long contentArticleVersionId)
            throws ContentNotFoundException {
        final UriBuilder uriBuilder = contentArticleVersionResource.getResourceUrl(
                contentArticleVersionId);

        try {
            return RequestUtils.get(uriBuilder, ContentArticleVersion.class);
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "ContentArticleVersion " + contentArticleVersionId + " does not exist");
        }
    }

    public static ContentArticleVersion createContentArticleVersion(
            ContentArticleVersion contentArticleVersion) {
        final UriBuilder uriBuilder = contentArticleVersionResource.getResourceUrl(
                contentArticleVersion.getContentArticleVersionId());
        return RequestUtils.post(uriBuilder, contentArticleVersion, ContentArticleVersion.class);
    }

    public static void updateContentArticleVersion(ContentArticleVersion contentArticleVersion) {
        final UriBuilder uriBuilder = contentArticleVersionResource.getResourceUrl(
                contentArticleVersion.getContentArticleVersionId());
        RequestUtils.put(uriBuilder, contentArticleVersion);
    }

    public static ContentFolder getContentFolder(long contentFolderId)
            throws ContentNotFoundException {
        final UriBuilder uriBuilder = contentFolderResource.getResourceUrl(
                contentFolderId);

        try {
            return RequestUtils.get(uriBuilder, ContentFolder.class);
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "ContentFolder " + contentFolderId + " does not exist");
        }
    }

    public static ContentFolder createContentFolder(ContentFolder contentFolder) {
        final UriBuilder uriBuilder = contentFolderResource.getResourceUrl();
        return RequestUtils.post(uriBuilder, contentFolder, ContentFolder.class);
    }

    public static void updateContentFolder(ContentFolder contentFolder) {
        final UriBuilder uriBuilder = contentFolderResource.getResourceUrl(
                contentFolder.getContentFolderId());
        RequestUtils.put(uriBuilder, contentFolder);
    }

    public static List<ContentArticleVersion> getChildArticleVersions(long folderId) {
        final UriBuilder uriBuilder = contentArticleVersionResource
                .getSubResource(folderId, "contentArticlesVersions").getResourceUrl();

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<ContentArticleVersion>>() {
        });
    }
}
