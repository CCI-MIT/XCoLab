package org.xcolab.client.comment.pojo;

import java.io.Serializable;

abstract class AbstractCategoryGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long groupId;
    private String description;
    private String url;
    private Boolean isQuiet;

    AbstractCategoryGroup() {
    }

    AbstractCategoryGroup(Long groupId, String description, String url, Boolean isQuiet) {
        this.groupId = groupId;
        this.description = description;
        this.url = url;
        this.isQuiet = isQuiet;
    }

    AbstractCategoryGroup(AbstractCategoryGroup categoryGroup) {
        this.groupId = categoryGroup.groupId;
        this.description = categoryGroup.description;
        this.url = categoryGroup.url;
        this.isQuiet = categoryGroup.isQuiet;
    }

    public Long getGroupId() {
        return this.groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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
        return this.isQuiet;
    }

    public void setIsQuiet(Boolean isQuiet) {
        this.isQuiet = isQuiet;
    }

    @Override
    public String toString() {

        return getClass().getSimpleName() +
                " (" + groupId +
                ", " + description +
                ", " + url +
                ", " + isQuiet +
                ")";
    }
}
