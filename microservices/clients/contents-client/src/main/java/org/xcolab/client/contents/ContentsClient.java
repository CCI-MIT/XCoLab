package org.xcolab.client.contents;

import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class ContentsClient {

    private static final RestService contentService = new RestService("contents-service");

    private static final RestResource<ContentArticle, Long> contentArticleResource =
            new RestResource1<>(contentService, "contentArticles", ContentArticle.TYPES);
    private static final RestResource<ContentArticleVersion, Long> contentArticleVersionResource =
            new RestResource1<>(contentService, "contentArticleVersions",
                    ContentArticleVersion.TYPES);
    private static final RestResource1<ContentFolder, Long> contentFolderResource =
            new RestResource1<>(contentService, "contentFolders", ContentFolder.TYPES);

    private ContentsClient() {
    }

    public static List<ContentArticle> getContentArticles(Long folderId) {
        return contentArticleResource.list()
                .optionalQueryParam("folderId", folderId)
                .execute();
    }

    public static ContentArticleVersion getLatestContentArticleVersion(long folderId, String title)
            throws ContentNotFoundException {
        final ContentArticleVersion contentArticleVersion = contentArticleVersionResource.list()
                .queryParam("folderId", folderId)
                .queryParam("title", title)
                .queryParam("sort", "-contentArticleVersion")
                .withCache(CacheKeys.withClass(ContentArticleVersion.class)
                        .withParameter("folderId", folderId)
                        .withParameter("title", title).asSingletonList("latest"),
                        CacheRetention.REQUEST)
                .executeWithResult().getFirstIfExists();
        if (contentArticleVersion == null) {
            throw new ContentNotFoundException("No ContentArticleVersion with title " + title
                    + " found in folder " + folderId);
        }
        return contentArticleVersion;
    }

    public static ContentArticleVersion getLatestContentArticleVersion(long articleId)
            throws ContentNotFoundException {
        final ContentArticleVersion contentArticleVersion = contentArticleVersionResource.list()
                .queryParam("contentArticleId", articleId)
                .queryParam("sort", "-contentArticleVersion")
                .withCache(CacheKeys.withClass(ContentArticleVersion.class)
                        .withParameter("articleId", articleId).asSingletonList("latest"),
                        CacheRetention.REQUEST)
                .executeWithResult().getFirstIfExists();
        if (contentArticleVersion == null) {
            throw new ContentNotFoundException(
                    "No ContentArticleVersion for contentArticleID " + articleId);
        }
        return contentArticleVersion;
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
            return contentArticleResource.get(contentArticleId).executeChecked();
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
            return contentArticleVersionResource.get(contentArticleVersionId).executeChecked();
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
            return contentFolderResource.get(contentFolderId).executeChecked();
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
                .getSubRestResource(folderId, "contentArticleVersions", ContentArticleVersion.TYPES)
                .list()
                .execute();
    }
}
