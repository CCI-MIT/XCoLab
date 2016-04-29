package org.xcolab.client.contents;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.contents.exceptions.ContentNotFoundException;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;

import java.util.List;

public final class ContentsClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/contents-service";

    private static final int MEMCACHED_TIMEOUT = 3;

    private static final RestTemplate restTemplate = new RestTemplate();

    private ContentsClient() {
    }

    public static List<ContentArticle> getContentArticles(Long folderId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticles")
                .queryParam("folderId", folderId);
        ResponseEntity<List<ContentArticle>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ContentArticle>>() {
                });

        return response.getBody();
    }

    public static ContentArticleVersion getContentArticleVersion(long folderId, String title)
            throws ContentNotFoundException {
        final List<ContentArticleVersion> contentArticleVersions = getContentArticleVersions(null,
                null, folderId, null, null, title);
        if (contentArticleVersions.isEmpty()) {
            throw new ContentNotFoundException("No ContentArticleVersion with title " + title
                    + " found in folder " + folderId);
        }
        return contentArticleVersions.get(0);
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

        ResponseEntity<List<ContentArticleVersion>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ContentArticleVersion>>() {
                });

        return response.getBody();
    }

    public static List<ContentFolder> getContentFolders() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders");
        ResponseEntity<List<ContentFolder>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ContentFolder>>() {
                });

        return response.getBody();
    }

    public static List<ContentFolder> getContentFolders(Long parentFolderId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders")
                .queryParam("parentFolderId", parentFolderId);
        ResponseEntity<List<ContentFolder>> response = restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.GET, null, new ParameterizedTypeReference<List<ContentFolder>>() {
                });

        return response.getBody();
    }

    public static ContentArticle getContentArticle(Long contentArticleId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticles/" + contentArticleId + "");

        return restTemplate.getForObject(uriBuilder.build().toString(), ContentArticle.class);
    }
    public static ContentArticle createContentArticle(ContentArticle contentArticle){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticles/");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContentArticle> entity = new HttpEntity<>(contentArticle, headers);

        return restTemplate.postForObject(uriBuilder.build().toString(),  entity,
                ContentArticle.class);
    }

    public static void updateContentArticle(ContentArticle contentArticle){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticles/"+ contentArticle.getContentArticleId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContentArticle> entity = new HttpEntity<>(contentArticle, headers);

        restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.PUT, entity, String.class);
    }

    public static ContentArticleVersion getContentArticleVersion(Long contentArticleVersionId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions/" + contentArticleVersionId + "");

        return restTemplate.getForObject(uriBuilder.build().toString(), ContentArticleVersion.class);
    }

    public static ContentArticleVersion createContentArticleVersion(ContentArticleVersion contentArticleVersion){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions/");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContentArticleVersion> entity = new HttpEntity<>(contentArticleVersion, headers);

        return restTemplate.postForObject(uriBuilder.build().toString(),  entity,
                ContentArticleVersion.class);
    }
    public static void updateContentArticleVersion(ContentArticleVersion contentArticleVersion){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions/"+ contentArticleVersion.getContentArticleVersionId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContentArticleVersion> entity = new HttpEntity<>(contentArticleVersion, headers);

        restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.PUT, entity, String.class);
    }

    public static ContentFolder getContentFolder(Long contentFolderId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders/" + contentFolderId + "");

        return restTemplate.getForObject(uriBuilder.build().toString(), ContentFolder.class);
    }
    public static ContentFolder createContentFolder(ContentFolder contentFolder){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders/");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContentFolder> entity = new HttpEntity<>(contentFolder, headers);

        return restTemplate.postForObject(uriBuilder.build().toString(),  entity,
                ContentFolder.class);
    }
    public static void updateContentFolder(ContentFolder contentFolder){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders/"+ contentFolder.getContentFolderId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContentFolder> entity = new HttpEntity<>(contentFolder, headers);

        restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.PUT, entity, String.class);
    }
}
