package org.xcolab.client.contents;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.util.UriComponentsBuilder;

import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;
import org.xcolab.util.http.RequestUtils;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class ContentsClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:"+RequestUtils.getServicesPort()+"/contents-service";

    private ContentsClient() {
    }

    public static List<ContentArticle> getContentArticles(Long folderId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticles");
        if (folderId != null) {
            uriBuilder.queryParam("folderId", folderId);
        }
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ContentArticle>>() {
                });
    }

    public static ContentArticleVersion getLatestContentArticleVersion(long folderId, String title)
            throws ContentNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions")
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
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions")
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
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions");

        if (startRecord != null) {
            uriBuilder.queryParam("startRecord", startRecord);
        }
        if (limitRecord != null) {
            uriBuilder.queryParam("limitRecord", limitRecord);
        }
        if (contentArticleId != null) {
            uriBuilder.queryParam("contentArticleId", contentArticleId);
        }
        if (folderId != null) {
            uriBuilder.queryParam("folderId", folderId);
        }
        if (contentArticleVersion != null) {
            uriBuilder.queryParam("contentArticleVersion", contentArticleVersion);
        }
        if (title != null) {
            uriBuilder.queryParam("title", title);
        }
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ContentArticleVersion>>() {
                });
    }

    public static List<ContentFolder> getContentFolders() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders");
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ContentFolder>>() {
                });
    }

    public static List<ContentFolder> getContentFolders(Long parentFolderId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders")
                .queryParam("parentFolderId", parentFolderId);
        return RequestUtils.getList(uriBuilder,
                new ParameterizedTypeReference<List<ContentFolder>>() {
                });
    }

    public static ContentArticle getContentArticle(Long contentArticleId)
            throws ContentNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticles/" + contentArticleId + "");

        try {
            return RequestUtils.get(uriBuilder, ContentArticle.class);
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "ContentArticle " + contentArticleId + " does not exist");
        }
    }

    public static ContentArticle createContentArticle(ContentArticle contentArticle) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticles/");
        return RequestUtils.post(uriBuilder, contentArticle, ContentArticle.class);
    }

    public static void updateContentArticle(ContentArticle contentArticle) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticles/" + contentArticle.getContentArticleId());

        RequestUtils.put(uriBuilder, contentArticle);
    }

    public static ContentArticleVersion getContentArticleVersion(Long contentArticleVersionId)
            throws ContentNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions/" + contentArticleVersionId + "");

        try {
            return RequestUtils.get(uriBuilder, ContentArticleVersion.class);
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "ContentArticleVersion " + contentArticleVersionId + " does not exist");
        }
    }

    public static ContentArticleVersion createContentArticleVersion(
            ContentArticleVersion contentArticleVersion) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions/");
        return RequestUtils.post(uriBuilder, contentArticleVersion, ContentArticleVersion.class);
    }

    public static void updateContentArticleVersion(ContentArticleVersion contentArticleVersion) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions/" + contentArticleVersion
                .getContentArticleVersionId());
        RequestUtils.put(uriBuilder, contentArticleVersion);
    }

    public static ContentFolder getContentFolder(Long contentFolderId)
            throws ContentNotFoundException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders/" + contentFolderId + "");

        try {
            return RequestUtils.get(uriBuilder, ContentFolder.class);
        } catch (EntityNotFoundException e) {
            throw new ContentNotFoundException(
                    "ContentFolder " + contentFolderId + " does not exist");
        }
    }

    public static ContentFolder createContentFolder(ContentFolder contentFolder) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders/");
        return RequestUtils.post(uriBuilder, contentFolder, ContentFolder.class);
    }

    public static void updateContentFolder(ContentFolder contentFolder) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders/" + contentFolder.getContentFolderId());
        RequestUtils.put(uriBuilder, contentFolder);
    }

    public static List<ContentArticleVersion> getChildArticleVersions(Long folderId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders/" + folderId
                + "/contentArticlesVersions/");

        return RequestUtils.getList(uriBuilder, new ParameterizedTypeReference<List<ContentArticleVersion>>() {
        });
    }
}
