package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.xcolab.client.contest.pojo.tables.pojos.PointsDistributionConfiguration;

import java.sql.Timestamp;

@JsonDeserialize(as = PointsDistributionConfiguration.class)
public interface IPointsDistributionConfiguration {

    Long getId();

    void setId(Long id);

    Long getProposalId();

    void setProposalId(Long proposalId);

    Long getPointTypeId();

    void setPointTypeId(Long pointTypeId);

    Long getTargetUserId();

    void setTargetUserId(Long targetUserId);

    Long getTargetSubProposalId();

    void setTargetSubProposalId(Long targetSubProposalId);

    Long getTargetProposalTemplateSectionDefinitionId();

    void setTargetProposalTemplateSectionDefinitionId(
            Long targetProposalTemplateSectionDefinitionId);

    Double getPercentage();

    void setPercentage(Double percentage);

    Long getAuthorUserId();

    void setAuthorUserId(Long authorUserId);

    Timestamp getCreatedAt();

    void setCreatedAt(Timestamp createdAt);
}
