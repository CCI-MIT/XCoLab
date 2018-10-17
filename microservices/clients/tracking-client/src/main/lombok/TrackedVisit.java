package org.xcolab.client.tracking.pojo.tables.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.xcolab.client.tracking.pojo.ITrackedVisit;

import java.sql.Timestamp;

@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class TrackedVisit implements ITrackedVisit {

    private static final long serialVersionUID = -984486387;

    private Long id;
    private String visitorUuid;
    private String ip;
    private String city;
    private String country;
    private String url;
    private String browser;
    private String headers;
    private String referer;
    private Timestamp createdAt;
}
