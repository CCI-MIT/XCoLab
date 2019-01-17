package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class PointsDistributionConfiguration extends AbstractPointsDistributionConfiguration
        implements Serializable {

    public static final TypeProvider<PointsDistributionConfiguration> TYPES =
            new TypeProvider<>(PointsDistributionConfiguration.class,
                    new ParameterizedTypeReference<List<PointsDistributionConfiguration>>() {});

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
            AbstractPointsDistributionConfiguration abstractPointsDistributionConfiguration) {
        super(abstractPointsDistributionConfiguration);
    }
}
