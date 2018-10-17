package org.xcolab.client.tracking.pojo.tables.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.xcolab.client.tracking.pojo.IBalloonUserTracking;

import java.sql.Timestamp;

@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class BalloonUserTracking implements IBalloonUserTracking {

    private static final long serialVersionUID = 1869101588;

    private String uuid;
    private String email;
    private String parent;
    private String ip;
    private Timestamp createdAt;
    private Timestamp registrationDate;
    private Timestamp formFiledDate;
    private Long userId;
    private Long balloonTextId;
    private String referrer;
    private Double latitude;
    private Double longitude;
    private String city;
    private String country;
    private String extraData;
    private String balloonLinkUuid;
    private String balloonLinkContext;
    private String userAgent;
}
