package org.xcolab.client.comment.pojo;

import java.io.Serializable;

public class CategoryGroup implements Serializable {

    private static final long serialVersionUID = -1843163875;

    private Long    groupid;
    private String  description;
    private String  url;
    private Boolean isquiet;

    public CategoryGroup() {}

    public CategoryGroup(CategoryGroup value) {
        this.groupid = value.groupid;
        this.description = value.description;
        this.url = value.url;
        this.isquiet = value.isquiet;
    }

    public CategoryGroup(
        Long    groupid,
        String  description,
        String  url,
        Boolean isquiet
    ) {
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

    @Override
    public String toString() {

        return "CategoryGroup (" + groupid +
                ", " + description +
                ", " + url +
                ", " + isquiet +
                ")";
    }
}
