package org.xcolab.client.tracking.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.tracking.pojo.tables.pojos.TrackedVisit;

import java.sql.Timestamp;

@JsonDeserialize(as = TrackedVisit.class)
public interface ITrackedVisit {

    Long getId();

    void setId(Long id);

    String getVisitorUuid();

    void setVisitorUuid(String visitorUuid);

    String getIp();

    void setIp(String ip);

    String getCity();

    void setCity(String city);

    String getCountry();

    void setCountry(String country);

    String getUrl();

    void setUrl(String url);

    String getBrowser();

    void setBrowser(String browser);

    String getHeaders();

    void setHeaders(String headers);

    String getReferer();

    void setReferer(String referer);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
