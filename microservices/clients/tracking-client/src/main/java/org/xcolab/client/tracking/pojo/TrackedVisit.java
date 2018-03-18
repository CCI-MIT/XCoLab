package org.xcolab.client.tracking.pojo;

import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.commons.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class TrackedVisit implements Serializable {

    public static final TypeProvider<TrackedVisit> TYPES = new TypeProvider<>(TrackedVisit.class,
            new ParameterizedTypeReference<List<TrackedVisit>>() {});

    private static final long serialVersionUID = 1556490672;

    private Long id_;
    private String uuid_;
    private String ip;
    private String city;
    private String country;
    private String url;
    private String browser;
    private String headers;
    private String referer;
    private Timestamp createDate;

    public TrackedVisit() {
    }

    public TrackedVisit(TrackedVisit value) {
        this.id_ = value.id_;
        this.uuid_ = value.uuid_;
        this.ip = value.ip;
        this.city = value.city;
        this.country = value.country;
        this.url = value.url;
        this.browser = value.browser;
        this.headers = value.headers;
        this.referer = value.referer;
        this.createDate = value.createDate;
    }

    public TrackedVisit(Long id_, String uuid_, String ip, String city, String country,
            String url, String browser, String headers, String referer, Timestamp createDate) {
        this.id_ = id_;
        this.uuid_ = uuid_;
        this.ip = ip;
        this.city = city;
        this.country = country;
        this.url = url;
        this.browser = browser;
        this.headers = headers;
        this.referer = referer;
        this.createDate = createDate;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getUuid_() {
        return this.uuid_;
    }

    public void setUuid_(String uuid_) {
        this.uuid_ = uuid_;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getHeaders() {
        return this.headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getReferer() {
        return this.referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public Timestamp getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
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
        final TrackedVisit other = (TrackedVisit) obj;
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
            return false;
        }
        if (uuid_ == null) {
            if (other.uuid_ != null) {
                return false;
            }
        } else if (!uuid_.equals(other.uuid_)) {
            return false;
        }
        if (ip == null) {
            if (other.ip != null) {
                return false;
            }
        } else if (!ip.equals(other.ip)) {
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
        if (url == null) {
            if (other.url != null) {
                return false;
            }
        } else if (!url.equals(other.url)) {
            return false;
        }
        if (browser == null) {
            if (other.browser != null) {
                return false;
            }
        } else if (!browser.equals(other.browser)) {
            return false;
        }
        if (headers == null) {
            if (other.headers != null) {
                return false;
            }
        } else if (!headers.equals(other.headers)) {
            return false;
        }
        if (referer == null) {
            if (other.referer != null) {
                return false;
            }
        } else if (!referer.equals(other.referer)) {
            return false;
        }
        if (createDate == null) {
            if (other.createDate != null) {
                return false;
            }
        } else if (!createDate.equals(other.createDate)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((uuid_ == null) ? 0 : uuid_.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + ((url == null) ? 0 : url.hashCode());
        result = prime * result + ((browser == null) ? 0 : browser.hashCode());
        result = prime * result + ((headers == null) ? 0 : headers.hashCode());
        result = prime * result + ((referer == null) ? 0 : referer.hashCode());
        result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
        return result;
    }

    @Override
    public String toString() {

        return "TrackedVisit (" + id_ +
                ", " + uuid_ +
                ", " + ip +
                ", " + city +
                ", " + country +
                ", " + url +
                ", " + browser +
                ", " + headers +
                ", " + referer +
                ", " + createDate +
                ")";
    }
}
