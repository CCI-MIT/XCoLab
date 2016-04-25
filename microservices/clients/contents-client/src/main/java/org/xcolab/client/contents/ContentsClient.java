package org.xcolab.client.contents;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.xcolab.client.contents.pojo.ContentArticle;
import org.xcolab.client.contents.pojo.ContentArticleVersion;
import org.xcolab.client.contents.pojo.ContentFolder;

public final class ContentsClient {

    private static final String EUREKA_APPLICATION_ID = "localhost:8080/contents-service";

    private static final int MEMCACHED_TIMEOUT = 3;

    static RestTemplate restTemplate = new RestTemplate();

    private ContentsClient() {

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

        HttpEntity<ContentArticle> entity = new HttpEntity(contentArticle, headers);

        ContentArticle ret = restTemplate.postForObject(uriBuilder.build().toString(),  entity,
                ContentArticle.class);

        return ret;
    }

    public static void updateContentArticle(ContentArticle contentArticle){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticles/"+ contentArticle.getContentArticleId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContentArticle> entity = new HttpEntity(contentArticle, headers);

        restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.PUT, entity,
                String.class);
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

        HttpEntity<ContentArticle> entity = new HttpEntity(contentArticleVersion, headers);

        ContentArticleVersion ret = restTemplate.postForObject(uriBuilder.build().toString(),  entity,
                ContentArticleVersion.class);

        return ret;
    }
    public static void updateContentArticleVersion(ContentArticleVersion contentArticleVersion){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentArticleVersions/"+ contentArticleVersion.getContentArticleVersionId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContentArticleVersion> entity = new HttpEntity(contentArticleVersion, headers);

        restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.PUT, entity,
                String.class);
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

        HttpEntity<ContentArticle> entity = new HttpEntity(contentFolder, headers);

        ContentFolder ret = restTemplate.postForObject(uriBuilder.build().toString(),  entity,
                ContentFolder.class);

        return ret;
    }
    public static void updateContentFolder(ContentFolder contentFolder){

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl("http://" +
                EUREKA_APPLICATION_ID + "/contentFolders/"+ contentFolder.getContentFolderId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ContentArticleVersion> entity = new HttpEntity(contentFolder, headers);

        restTemplate.exchange(uriBuilder.build().toString(),
                HttpMethod.PUT, entity,
                String.class);
    }
}
