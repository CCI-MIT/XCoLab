package org.xcolab.client.members.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class LoginLog implements Serializable {

    private static final long serialVersionUID = -1357748274;

    public static final TypeProvider<LoginLog> TYPES =
            new TypeProvider<>(LoginLog.class, new ParameterizedTypeReference<List<LoginLog>>() {});

    private Long pk;
    private Long userId;
    private Timestamp createDate;
    private String ipAddress;
    private String city;
    private String country;
    private String entryUrl;

    public LoginLog() {
    }

    public LoginLog(LoginLog value) {
        this.pk = value.pk;
        this.userId = value.userId;
        this.createDate = value.createDate;
        this.ipAddress = value.ipAddress;
        this.city = value.city;
        this.country = value.country;
        this.entryUrl = value.entryUrl;
    }

    public LoginLog(Long pk, Long userId, Timestamp createDate, String ipAddress,
            String city, String country, String entryUrl) {
        this.pk = pk;
        this.userId = userId;
        this.createDate = createDate;
        this.ipAddress = ipAddress;
        this.city = city;
        this.country = country;
        this.entryUrl = entryUrl;
    }

    public Long getPk() {
        return this.pk;
    }

    public void setPk(Long pk) {
        this.pk = pk;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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
        if (pk == null) {
            if (other.pk != null) {
                return false;
            }
        } else if (!pk.equals(other.pk)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }
        if (createDate == null) {
            if (other.createDate != null) {
                return false;
            }
        } else if (!createDate.equals(other.createDate)) {
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
        result = prime * result + ((pk == null) ? 0 : pk.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        result = prime * result + ((ipAddress == null) ? 0 : ipAddress.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((entryUrl == null) ? 0 : entryUrl.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "LoginLog (" + pk +
                ", " + userId +
                ", " + createDate +
                ", " + ipAddress +
                ", " + city +
                ", " + country +
                ", " + entryUrl +
                ")";
    }
}
