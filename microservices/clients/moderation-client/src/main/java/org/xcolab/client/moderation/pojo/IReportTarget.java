package org.xcolab.client.moderation.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.xcolab.client.moderation.pojo.tables.pojos.ReportTarget;

import java.io.Serializable;

@JsonDeserialize(as = ReportTarget.class)
public interface IReportTarget extends Serializable {

    Long getId();

    void setId(Long id);

    String getType();

    void setType(String type);

    String getReason();

    void setReason(String reason);

    Integer getNotificationThreshold();

    void setNotificationThreshold(Integer notificationThreshold);

    Integer getScreeningThreshold();

    void setScreeningThreshold(Integer screeningThreshold);
}
