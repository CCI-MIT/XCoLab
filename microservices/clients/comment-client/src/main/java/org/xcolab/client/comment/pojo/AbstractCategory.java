package org.xcolab.client.comment.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

abstract class AbstractCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long groupId;
    private Long authorUserId;
    private String name;
    private String description;
    private Timestamp createdAt;
    private Timestamp deletedAt;
    private Integer sort;
    private Boolean isQuiet;

    AbstractCategory() {
    }

    AbstractCategory(Long id, Long groupId, Long authorUserId, String name, String description,
            Timestamp createdAt, Timestamp deletedAt, Integer sort, Boolean isQuiet) {
        this.id = id;
        this.groupId = groupId;
        this.authorUserId = authorUserId;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
        this.sort = sort;
        this.isQuiet = isQuiet;
    }

    AbstractCategory(AbstractCategory category) {
        this.id = category.id;
        this.groupId = category.groupId;
        this.authorUserId = category.authorUserId;
        this.name = category.name;
        this.description = category.description;
        this.createdAt = category.createdAt;
        this.deletedAt = category.deletedAt;
        this.sort = category.sort;
        this.isQuiet = category.isQuiet;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getAuthorUserId() {
        return this.authorUserId;
    }

    public void setAuthorUserId(Long authorUserId) {
        this.authorUserId = authorUserId;
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

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
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
                " (" + id +
                ", " + groupId +
                ", " + authorUserId +
                ", " + name +
                ", " + description +
                ", " + createdAt +
                ", " + deletedAt +
                ", " + sort +
                ", " + isQuiet +
                ")";
    }
}
