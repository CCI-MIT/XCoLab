package org.xcolab.client.user.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class LoginLog implements Serializable {

    private static final long serialVersionUID = -1357748274;

    public static final TypeProvider<LoginLog> TYPES =
            new TypeProvider<>(LoginLog.class, new ParameterizedTypeReference<List<LoginLog>>() {});

    private Long id;
    private Long userId;
    private Timestamp createdAt;
    private String ipAddress;
    private String city;
    private String country;
    private String entryUrl;

    public LoginLog() {
    }

    public LoginLog(LoginLog value) {
        this.id = value.id;
        this.userId = value.userId;
        this.createdAt = value.createdAt;
        this.ipAddress = value.ipAddress;
        this.city = value.city;
        this.country = value.country;
        this.entryUrl = value.entryUrl;
    }

    public LoginLog(Long id, Long userId, Timestamp createdAt, String ipAddress,
            String city, String country, String entryUrl) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.ipAddress = ipAddress;
        this.city = city;
        this.country = country;
        this.entryUrl = entryUrl;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEntryUrl() {
        return this.entryUrl;
    }

    public void setEntryUrl(String entryUrl) {
        this.entryUrl = entryUrl;
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
        final LoginLog other = (LoginLog) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        if (createdAt == null) {
            if (other.createdAt != null) {
                return false;
            }
        } else if (!createdAt.equals(other.createdAt)) {
            return false;
        }
        if (ipAddress == null) {
            if (other.ipAddress != null) {
                return false;
            }
        } else if (!ipAddress.equals(other.ipAddress)) {
            return false;
        }
        if (city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!city.equals(other.city)) {
            return false;
        }
        if (country == null) {
            if (other.country != null) {
                return false;
            }
        } else if (!country.equals(other.country)) {
            return false;
        }
        if (entryUrl == null) {
            if (other.entryUrl != null) {
                return false;
            }
        } else if (!entryUrl.equals(other.entryUrl)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((createdAt == null) ? 0 : createdAt.hashCode());
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((entryUrl == null) ? 0 : entryUrl.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "LoginLog (" + id +
                ", " + userId +
                ", " + createdAt +
                ", " + ipAddress +
                ", " + city +
                ", " + country +
                ", " + entryUrl +
                ")";
    }
}
