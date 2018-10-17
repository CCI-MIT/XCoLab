package org.xcolab.client.tracking.pojo.tables.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.xcolab.client.tracking.pojo.ITrackedVisitor;

import java.sql.Timestamp;

@Data
@ToString(includeFieldNames = true)
@NoArgsConstructor
@AllArgsConstructor
public class TrackedVisitor implements ITrackedVisitor {

    private static final long serialVersionUID = -1101057440;

    private String uuid;
    private Long userId;
    private Timestamp createdAt;
}
