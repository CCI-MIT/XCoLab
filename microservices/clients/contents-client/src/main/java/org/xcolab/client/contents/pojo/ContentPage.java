package org.xcolab.client.contents.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.text.WordUtils;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contents.ContentsClient;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContentPage implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final TypeProvider<ContentPage> TYPES = new TypeProvider<>(ContentPage.class,
                    new ParameterizedTypeReference<List<ContentPage>>() {});

    private Long id;
    private String title;
    private String metaDescription;
    private Long menuArticleId;
    private Long contentArticleId;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public ContentPage() {
    }

    public ContentPage(ContentPage value) {
        this.id = value.id;
        this.title = value.title;
        this.metaDescription = value.metaDescription;
        this.menuArticleId = value.menuArticleId;
        this.contentArticleId = value.contentArticleId;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public static ContentPage forId(long pageId) {
        return ContentsClient.getContentPage(pageId);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public Long getMenuArticleId() {
        return this.menuArticleId;
    }

    public void setMenuArticleId(Long menuArticleId) {
        this.menuArticleId = menuArticleId;
    }

    public Long getContentArticleId() {
        return this.contentArticleId;
    }

    public void setContentArticleId(Long contentArticleId) {
        this.contentArticleId = contentArticleId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getupdatedAt() {
        return this.updatedAt;
    }

    public void setupdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @JsonIgnore
    public String getFormattedTitle() {
        return WordUtils.capitalize(title.replace('-', ' '));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContentPage)) {
            return false;
        }
        ContentPage that = (ContentPage) o;
        return Objects.equals(getId(), that.getId())
                && Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getMetaDescription(), that.getMetaDescription())
                && Objects.equals(getMenuArticleId(), that.getMenuArticleId())
                && Objects.equals(getContentArticleId(), that.getContentArticleId())
                && Objects.equals(getCreatedAt(), that.getCreatedAt())
                && Objects.equals(getupdatedAt(), that.getupdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getMetaDescription(), getMenuArticleId(),
                getContentArticleId(), createdAt, getupdatedAt());
    }

    @Override
    public String toString() {

        return "ContentPage (" + id +
                ", " + title +
                ", " + menuArticleId +
                ", " + contentArticleId +
                ", " + createdAt +
                ", " + updatedAt +
                ")";
    }
}
