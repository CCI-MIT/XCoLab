package org.xcolab.client.proposals.pojo.points;

import org.xcolab.util.http.client.RestService;

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
            Timestamp createdate
    ) {
        super(id_, proposalid, pointtypeid, targetuserid, targetsubproposalid,
                targetplansectiondefinitionid, percentage, creator, createdate);
    }

    public PointsDistributionConfiguration(
            AbstractPointsDistributionConfiguration abstractPointsDistributionConfiguration,
            RestService restService) {
        super(abstractPointsDistributionConfiguration);
    }
}
