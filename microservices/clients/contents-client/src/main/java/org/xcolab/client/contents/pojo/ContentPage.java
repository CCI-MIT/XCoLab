package org.xcolab.client.contents.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.apache.commons.lang3.text.WordUtils;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class ContentPage implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final TypeProvider<ContentPage> TYPES = new TypeProvider<>(ContentPage.class,
                    new ParameterizedTypeReference<List<ContentPage>>() {});

    private Long pageId;
    private String title;
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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pageId == null) ? 0 : pageId.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((menuArticleId == null) ? 0 : menuArticleId.hashCode());
        result = prime * result + ((contentArticleId == null) ? 0 : contentArticleId.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((modifiedDate == null) ? 0 : modifiedDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContentPage other = (ContentPage) obj;
        if (pageId == null) {
            if (other.pageId != null) {
                return false;
            }
        } else if (!pageId.equals(other.pageId)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        } else if (!title.equals(other.title)) {
            return false;
        }
        if (menuArticleId == null) {
            if (other.menuArticleId != null) {
                return false;
            }
        } else if (!menuArticleId.equals(other.menuArticleId)) {
            return false;
        }
        if (contentArticleId == null) {
            if (other.contentArticleId != null) {
                return false;
            }
        } else if (!contentArticleId.equals(other.contentArticleId)) {
            return false;
        }
        if (createDate == null) {
            if (other.createDate != null) {
                return false;
            }
        } else if (!createDate.equals(other.createDate)) {
            return false;
        }
        if (modifiedDate == null) {
            if (other.modifiedDate != null) {
                return false;
            }
        } else if (!modifiedDate.equals(other.modifiedDate)) {
            return false;
        }
        return true;
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
