package org.xcolab.client.user.pojo;

import java.sql.Timestamp;

public interface IAnalyticsUserEvent {

    Long getUserId();

    void setUserId(Long userId);

    String getIdString();

    void setIdString(String idString);

    String getCategory();

    void setCategory(String category);

    String getAction();

    void setAction(String action);

    String getLabel();

    void setLabel(String label);

    Integer getValue();

    void setValue(Integer value);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
