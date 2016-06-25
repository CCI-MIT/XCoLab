package org.xcolab.client.comment.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.ThreadSortColumn;
import org.xcolab.client.comment.exceptions.CategoryGroupNotFoundException;
import org.xcolab.client.comment.exceptions.KeyReferenceException;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Category implements Serializable {
    public static final TypeProvider<Category> TYPES =
            new TypeProvider<>(Category.class,
                    new ParameterizedTypeReference<List<Category>>() {
                    });

    private static final long serialVersionUID = -753738209;

    private Long categoryid;
    private Long groupid;
    private Long authorid;
    private String name;
    private String description;
    private Timestamp createdate;
    private Timestamp deleteddate;
    private Integer sort;
    private Boolean isquiet;

    public Category() {
    }

    public Category(Long categoryid, Long groupid, Long authorid, String name, String description,
            Timestamp createdate, Timestamp deleteddate, Integer sort, Boolean isquiet) {
        this.categoryid = categoryid;
        this.groupid = groupid;
        this.authorid = authorid;
        this.name = name;
        this.description = description;
        this.createdate = createdate;
        this.deleteddate = deleteddate;
        this.sort = sort;
        this.isquiet = isquiet;
    }

    public Long getCategoryId() {
        return this.categoryid;
    }

    public void setCategoryId(Long categoryid) {
        this.categoryid = categoryid;
    }

    public Long getGroupId() {
        return this.groupid;
    }

    public void setGroupId(Long groupid) {
        this.groupid = groupid;
    }

    public Long getAuthorId() {
        return this.authorid;
    }

    public void setAuthorId(Long authorid) {
        this.authorid = authorid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return this.createdate;
    }

    public void setCreateDate(Timestamp createdate) {
        this.createdate = createdate;
    }

    public Timestamp getDeletedDate() {
        return this.deleteddate;
    }

    public void setDeletedDate(Timestamp deleteddate) {
        this.deleteddate = deleteddate;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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
                categoryid, null, sortColumn, ascending);
    }

    @JsonIgnore
    public CategoryGroup getCategoryGroup() {
        if (groupid != null && groupid > 0) {
            try {
                return CommentClient.getCategoryGroup(groupid);
            } catch (CategoryGroupNotFoundException e) {
                throw new KeyReferenceException(e);
            }
        }
        return null;
    }

    @JsonIgnore
    public String getLinkUrl() {
        final CategoryGroup categoryGroup = getCategoryGroup();
        if (categoryGroup != null) {
            return categoryGroup.getLinkUrl() + "/-/discussion/category/" + categoryid;
        }
        return "";
    }

    @Override
    public String toString() {

        return "Category (" + categoryid +
                ", " + groupid +
                ", " + authorid +
                ", " + name +
                ", " + description +
                ", " + createdate +
                ", " + deleteddate +
                ", " + sort +
                ", " + isquiet +
                ")";
    }
}
