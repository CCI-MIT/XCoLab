package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberCategory implements Serializable {

    private static final long serialVersionUID = -2052172539;

    private Long roleid;
    private String displayname;
    private String categoryname;
    private Long sortorder;
    private Boolean showinlist;
    private String imagename;
    private String description;

    public MemberCategory() {
    }

    public MemberCategory(MemberCategory value) {
        this.roleid = value.roleid;
        this.displayname = value.displayname;
        this.categoryname = value.categoryname;
        this.sortorder = value.sortorder;
        this.showinlist = value.showinlist;
        this.imagename = value.imagename;
    }

    public MemberCategory(
            Long roleid,
            String displayname,
            String categoryname,
            Long sortorder,
            Boolean showinlist,
            String imagename
    ) {
        this.roleid = roleid;
        this.displayname = displayname;
        this.categoryname = categoryname;
        this.sortorder = sortorder;
        this.showinlist = showinlist;
        this.imagename = imagename;
    }

    public Long getRoleId() {
        return this.roleid;
    }

    public void setRoleId(Long roleid) {
        this.roleid = roleid;
    }

    public String getDisplayName() {
        return this.displayname;
    }

    public void setDisplayName(String displayname) {
        this.displayname = displayname;
    }

    public String getCategoryName() {
        return this.categoryname;
    }

    public void setCategoryName(String categoryname) {
        this.categoryname = categoryname;
    }

    public Long getSortOrder() {
        return this.sortorder;
    }

    public void setSortOrder(Long sortorder) {
        this.sortorder = sortorder;
    }

    public Boolean getShowInList() {
        return this.showinlist;
    }

    public void setShowInList(Boolean showinlist) {
        this.showinlist = showinlist;
    }

    public String getImageName() {
        return this.imagename;
    }

    public void setImageName(String imagename) {
        this.imagename = imagename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MemberCategory (");

        sb.append(roleid);
        sb.append(", ").append(displayname);
        sb.append(", ").append(categoryname);
        sb.append(", ").append(sortorder);
        sb.append(", ").append(showinlist);
        sb.append(", ").append(imagename);

        sb.append(")");
        return sb.toString();
    }
}
