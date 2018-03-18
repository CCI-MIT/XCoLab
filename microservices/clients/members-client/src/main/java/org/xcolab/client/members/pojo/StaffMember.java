package org.xcolab.client.members.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StaffMember implements Serializable {

    private static final long serialVersionUID = 533221781;

    public static final TypeProvider<StaffMember> TYPES =
            new TypeProvider<>(StaffMember.class,
                    new ParameterizedTypeReference<List<StaffMember>>() {
                    });

    private Long id_;
    private Long userId;
    private Long categoryId;
    private String firstNames;
    private String lastName;
    private String url;
    private String photoUrl;
    private String role;
    private String organization;
    private Integer sort;

    public StaffMember() {
    }

    public StaffMember(StaffMember value) {
        this.id_ = value.id_;
        this.userId = value.userId;
        this.categoryId = value.categoryId;
        this.firstNames = value.firstNames;
        this.lastName = value.lastName;
        this.url = value.url;
        this.photoUrl = value.photoUrl;
        this.role = value.role;
        this.organization = value.organization;
        this.sort = value.sort;
    }

    public StaffMember(Long id_, Long userId, Long categoryId, String firstNames,
            String lastName, String url, String photoUrl, String role, String organization,
            Integer sort) {
        this.id_ = id_;
        this.userId = userId;
        this.categoryId = categoryId;
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.url = url;
        this.photoUrl = photoUrl;
        this.role = role;
        this.organization = organization;
        this.sort = sort;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getFirstNames() {
        return this.firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getSort() {
        return this.sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StaffMember other = (StaffMember) obj;
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        if (categoryId == null) {
            if (other.categoryId != null) {
                return false;
            }
        } else if (!categoryId.equals(other.categoryId)) {
            return false;
        }
        if (firstNames == null) {
            if (other.firstNames != null) {
                return false;
            }
        } else if (!firstNames.equals(other.firstNames)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (url == null) {
            if (other.url != null) {
                return false;
            }
        } else if (!url.equals(other.url)) {
            return false;
        }
        if (photoUrl == null) {
            if (other.photoUrl != null) {
                return false;
            }
        } else if (!photoUrl.equals(other.photoUrl)) {
            return false;
        }
        if (role == null) {
            if (other.role != null) {
                return false;
            }
        } else if (!role.equals(other.role)) {
            return false;
        }
        if (organization == null) {
            if (other.organization != null) {
                return false;
            }
        } else if (!organization.equals(other.organization)) {
            return false;
        }
        if (sort == null) {
            if (other.sort != null) {
                return false;
            }
        } else if (!sort.equals(other.sort)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
        result = prime * result + ((firstNames == null) ? 0 : firstNames.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((photoUrl == null) ? 0 : photoUrl.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        result = prime * result + ((organization == null) ? 0 : organization.hashCode());
        result = prime * result + ((sort == null) ? 0 : sort.hashCode());
        return result;
    }

    @Override
    public String toString() {

        return "StaffMember (" + id_ +
                ", " + userId +
                ", " + categoryId +
                ", " + firstNames +
                ", " + lastName +
                ", " + url +
                ", " + photoUrl +
                ", " + role +
                ", " + organization +
                ", " + sort +
                ")";
    }
}
