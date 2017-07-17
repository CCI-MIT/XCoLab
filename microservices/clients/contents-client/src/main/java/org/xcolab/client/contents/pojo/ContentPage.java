package org.xcolab.client.contents.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.text.WordUtils;
import org.springframework.core.ParameterizedTypeReference;

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

    private Long pageId;
    private String title;
    private String metaDescription;
    private Long menuArticleId;
    private Long contentArticleId;
    private Timestamp createDate;
    private Timestamp modifiedDate;

    public ContentPage() {
    }

    public ContentPage(ContentPage value) {
        this.pageId = value.pageId;
        this.title = value.title;
        this.menuArticleId = value.menuArticleId;
        this.contentArticleId = value.contentArticleId;
        this.createDate = value.createDate;
        this.modifiedDate = value.modifiedDate;
    }

    public Long getPageId() {
        return this.pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
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

    public Timestamp getCreatedDate() {
        return this.createDate;
    }

    public void setCreatedDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getModifiedDate() {
        return this.modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
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
        return Objects.equals(getPageId(), that.getPageId())
                && Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(getMetaDescription(), that.getMetaDescription())
                && Objects.equals(getMenuArticleId(), that.getMenuArticleId())
                && Objects.equals(getContentArticleId(), that.getContentArticleId())
                && Objects.equals(getCreatedDate(), that.getCreatedDate())
                && Objects.equals(getModifiedDate(), that.getModifiedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPageId(), getTitle(), getMetaDescription(), getMenuArticleId(),
                getContentArticleId(), createDate, getModifiedDate());
    }

    @Override
    public String toString() {

        return "ContentPage (" + pageId +
                ", " + title +
                ", " + menuArticleId +
                ", " + contentArticleId +
                ", " + createDate +
                ", " + modifiedDate +
                ")";
    }
}
