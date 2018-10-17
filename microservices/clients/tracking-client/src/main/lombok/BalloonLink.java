package org.xcolab.client.tracking.pojo.tables.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.xcolab.client.tracking.pojo.IBalloonLink;

import java.sql.Timestamp;

@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class BalloonLink implements IBalloonLink {

    private static final long serialVersionUID = 1605019719;

    private String uuid;
    private String targetUrl;
    private Integer visits;
    private String balloonUserUuid;
    private Timestamp createdAt;
}
