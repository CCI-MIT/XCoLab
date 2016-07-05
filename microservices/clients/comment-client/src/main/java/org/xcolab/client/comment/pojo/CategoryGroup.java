package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadSortColumn;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class CategoryGroup implements Serializable {
    public static final TypeProvider<CategoryGroup> TYPES =
            new TypeProvider<>(CategoryGroup.class,
                    new ParameterizedTypeReference<List<CategoryGroup>>() {
                    });

    private static final long serialVersionUID = -1843163875;

    private Long groupid;
    private String description;
    private String url;
    private Boolean isquiet;

    public CategoryGroup() {
    }

    public CategoryGroup(Long groupid, String description, String url, Boolean isquiet) {
        this.groupid = groupid;
        this.description = description;
        this.url = url;
        this.isquiet = isquiet;
    }

    public Long getGroupId() {
        return this.groupid;
    }

    public void setGroupId(Long groupid) {
        this.groupid = groupid;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsQuiet() {
        return this.isquiet;
    }

    public void setIsQuiet(Boolean isquiet) {
        this.isquiet = isquiet;
    }

    @JsonIgnore
    public List<CommentThread> getThreads(ThreadSortColumn sortColumn, boolean ascending) {
        return CommentClient.listThreads(0, Integer.MAX_VALUE,
                null, groupid, sortColumn, ascending);
    }

    @JsonIgnore
    public List<Category> getCategories() {
        return CommentClient.listCategories(0, Integer.MAX_VALUE, groupid);
    }

    @JsonIgnore
    public String getLinkUrl() {
        return getUrl();
    }

    @Override
    public String toString() {

        return "CategoryGroup (" + groupid +
                ", " + description +
                ", " + url +
                ", " + isquiet +
                ")";
    }
}
