package org.xcolab.client.contents.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentArticleVersion implements Serializable {

    private static final long serialVersionUID = -110947002;

    public static final TypeProvider<ContentArticleVersion> TYPES =
            new TypeProvider<>(ContentArticleVersion.class,
                    new ParameterizedTypeReference<List<ContentArticleVersion>>() {
                    });

    private Long contentArticleVersionId;
    private Long contentArticleId;
    private Long folderId;
    private Long authorId;
    private Timestamp createDate;
    private String title;
    private String content;
    private String    lang;

    public ContentArticleVersion() {
    }

    public ContentArticleVersion(Long contentArticleVersionId, Long contentArticleId,
            Long folderId, Long authorId, Timestamp createDate, String title, String content,String lang) {
        this.contentArticleVersionId = contentArticleVersionId;
        this.contentArticleId = contentArticleId;
        this.folderId = folderId;
        this.authorId = authorId;
        this.createDate = createDate;
        this.title = title;
        this.content = content;
        this.lang = lang;
    }

    public Long getContentArticleVersionId() {
        return this.contentArticleVersionId;
    }

    public void setContentArticleVersionId(Long contentArticleVersionId) {
        this.contentArticleVersionId = contentArticleVersionId;
    }

    public Long getContentArticleId() {
        return this.contentArticleId;
    }

    public void setContentArticleId(Long contentArticleId) {
        this.contentArticleId = contentArticleId;
    }

    public Long getFolderId() {
        return this.folderId;
    }

    public void setFolderId(Long folderId) {
        this.folderId = folderId;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLang() {
        return this.lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @JsonIgnore
    public String getLinkUrl() {
        return "/wiki/" + title.replace(" ", "+");
    }

    @Override
    public String toString() {
        return "ContentArticleVersion (" + contentArticleVersionId +
                ", " + contentArticleId +
                ", " + folderId +
                ", " + authorId +
                ", " + createDate +
                ", " + title +
                ", " + content +
                ")";
    }
}
