package org.xcolab.client.user.pojo;

public interface IMemberCategory {

    Long getRoleId();

    void setRoleId(Long roleId);

    String getDisplayName();

    void setDisplayName(String displayName);

    String getCategoryName();

    void setCategoryName(String categoryName);

    Long getSortOrder();

    void setSortOrder(Long sortOrder);

    Boolean isShowInList();

    void setShowInList(Boolean showInList);

    String getImageName();

    void setImageName(String imageName);

    String getDescription();

    void setDescription(String description);
}
