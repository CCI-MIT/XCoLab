package org.xcolab.client.content.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.content.pojo.tables.pojos.ContentArticleVersion;

import java.sql.Timestamp;

@JsonDeserialize(as = ContentArticleVersion.class)
public interface IContentArticleVersion {

    Long getId();

    void setId(Long id);

    Long getArticleId();

    void setArticleId(Long articleId);

    Long getFolderId();

    void setFolderId(Long folderId);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    String getTitle();

    void setTitle(String title);

    String getContent();

    void setContent(String content);

    String getLang();

    void setLang(String lang);

    default String getLinkUrl() {
        return "/wiki/" + getTitle().replace(" ", "+");
    }
}
