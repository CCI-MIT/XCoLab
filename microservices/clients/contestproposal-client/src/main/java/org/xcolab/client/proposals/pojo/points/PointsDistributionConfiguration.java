package org.xcolab.client.proposals.pojo.points;

import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.sql.Timestamp;

public class PointsDistributionConfiguration extends AbstractPointsDistributionConfiguration {

    public PointsDistributionConfiguration() {}

    public PointsDistributionConfiguration(PointsDistributionConfiguration value) {
        super(value);
    }

    public PointsDistributionConfiguration(
            Long id,
            Long proposalid,
            Long pointtypeid,
            Long targetuserid,
            Long targetsubproposalid,
            Long targetplansectiondefinitionid,
            Double percentage,
            Long authorUserId,
            Timestamp createdAt
    ) {
        super(id, proposalid, pointtypeid, targetuserid, targetsubproposalid,
                targetplansectiondefinitionid, percentage, authorUserId, createdAt);
    }

    public PointsDistributionConfiguration(
            AbstractPointsDistributionConfiguration abstractPointsDistributionConfiguration,
            ServiceNamespace serviceNamespace) {
        super(abstractPointsDistributionConfiguration);
    }
}
