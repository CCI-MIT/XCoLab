package org.xcolab.client.comment.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

abstract class AbstractCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long categoryId;
    private Long groupId;
    private Long authorId;
    private String name;
    private String description;
    private Timestamp createDate;
    private Timestamp deletedDate;
    private Integer sort;
    private Boolean isQuiet;

    AbstractCategory() {
    }

    AbstractCategory(Long categoryId, Long groupId, Long authorId, String name, String description,
            Timestamp createDate, Timestamp deletedDate, Integer sort, Boolean isQuiet) {
        this.categoryId = categoryId;
        this.groupId = groupId;
        this.authorId = authorId;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.deletedDate = deletedDate;
        this.sort = sort;
        this.isQuiet = isQuiet;
    }

    AbstractCategory(AbstractCategory category) {
        this.categoryId = category.categoryId;
        this.groupId = category.groupId;
        this.authorId = category.authorId;
        this.name = category.name;
        this.description = category.description;
        this.createDate = category.createDate;
        this.deletedDate = category.deletedDate;
        this.sort = category.sort;
        this.isQuiet = category.isQuiet;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
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
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Timestamp getDeletedDate() {
        return this.deletedDate;
    }

    public void setDeletedDate(Timestamp deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsQuiet() {
        return this.isQuiet;
    }

    public void setIsQuiet(Boolean isQuiet) {
        this.isQuiet = isQuiet;
    }

    @Override
    public String toString() {

        return  getClass().getSimpleName() +
                " (" + categoryId +
                ", " + groupId +
                ", " + authorId +
                ", " + name +
                ", " + description +
                ", " + createDate +
                ", " + deletedDate +
                ", " + sort +
                ", " + isQuiet +
                ")";
    }
}
