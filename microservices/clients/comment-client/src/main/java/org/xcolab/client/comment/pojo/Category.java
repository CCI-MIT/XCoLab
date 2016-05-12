package org.xcolab.client.comment.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Category implements Serializable {

    private static final long serialVersionUID = -753738209;

    private Long      categoryid;
    private Long      groupid;
    private Long      authorid;
    private String    name;
    private String    description;
    private Timestamp createdate;
    private Timestamp deleteddate;
    private Integer   sort;
    private Boolean   isquiet;

    public Category() {}

    public Category(Category value) {
        this.categoryid = value.categoryid;
        this.groupid = value.groupid;
        this.authorid = value.authorid;
        this.name = value.name;
        this.description = value.description;
        this.createdate = value.createdate;
        this.deleteddate = value.deleteddate;
        this.sort = value.sort;
        this.isquiet = value.isquiet;
    }

    public Category(
        Long      categoryid,
        Long      groupid,
        Long      authorid,
        String    name,
        String    description,
        Timestamp createdate,
        Timestamp deleteddate,
        Integer   sort,
        Boolean   isquiet
    ) {
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
