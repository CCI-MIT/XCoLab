package org.xcolab.client.proposals.pojo.points;

import org.xcolab.util.http.client.enums.ServiceNamespace;

import java.sql.Timestamp;

public class PointsDistributionConfiguration extends AbstractPointsDistributionConfiguration {

    public PointsDistributionConfiguration() {}

    public PointsDistributionConfiguration(PointsDistributionConfiguration value) {
        super(value);
    }

    public PointsDistributionConfiguration(
            Long id_,
            Long proposalid,
            Long pointtypeid,
            Long targetuserid,
            Long targetsubproposalid,
            Long targetplansectiondefinitionid,
            Double percentage,
            Long creator,
            Timestamp createdAt
    ) {
        super(id_, proposalid, pointtypeid, targetuserid, targetsubproposalid,
                targetplansectiondefinitionid, percentage, creator, createdAt);
    }

    public PointsDistributionConfiguration(
            AbstractPointsDistributionConfiguration abstractPointsDistributionConfiguration,
            ServiceNamespace serviceNamespace) {
        super(abstractPointsDistributionConfiguration);
    }
}
