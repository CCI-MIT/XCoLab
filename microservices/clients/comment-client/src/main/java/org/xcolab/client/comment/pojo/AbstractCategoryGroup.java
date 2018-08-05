package org.xcolab.client.comment.pojo;

import java.io.Serializable;

abstract class AbstractCategoryGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String description;
    private String url;
    private Boolean isQuiet;

    AbstractCategoryGroup() {
    }

    AbstractCategoryGroup(Long id, String description, String url, Boolean isQuiet) {
        this.id = id;
        this.description = description;
        this.url = url;
        this.isQuiet = isQuiet;
    }

    AbstractCategoryGroup(AbstractCategoryGroup categoryGroup) {
        this.id = categoryGroup.id;
        this.description = categoryGroup.description;
        this.url = categoryGroup.url;
        this.isQuiet = categoryGroup.isQuiet;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
                " (" + id +
                ", " + description +
                ", " + url +
                ", " + isQuiet +
                ")";
    }
}
