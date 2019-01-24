package org.xcolab.client.user.pojo;

public interface IStaffMember {

    Long getId();

    void setId(Long id);

    Long getUserId();

    void setUserId(Long userId);

    Long getCategoryId();

    void setCategoryId(Long categoryId);

    String getFirstNames();

    void setFirstNames(String firstNames);

    String getLastName();

    void setLastName(String lastName);

    String getUrl();

    void setUrl(String url);

    String getPhotoUrl();

    void setPhotoUrl(String photoUrl);

    String getRole();

    void setRole(String role);

    String getOrganization();

    void setOrganization(String organization);

    Integer getSortOrder();

    void setSortOrder(Integer sortOrder);
}
