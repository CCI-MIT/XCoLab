package org.xcolab.client.user.pojo;

import java.sql.Timestamp;

public interface ILoginLog {

    Long getId();

    void setId(Long id);

    Long getUserId();

    void setUserId(Long userId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);

    String getIpAddress();

    void setIpAddress(String ipAddress);

    String getCity();

    void setCity(String city);

    String getCountry();

    void setCountry(String country);

    String getEntryUrl();

    void setEntryUrl(String entryUrl);
}
