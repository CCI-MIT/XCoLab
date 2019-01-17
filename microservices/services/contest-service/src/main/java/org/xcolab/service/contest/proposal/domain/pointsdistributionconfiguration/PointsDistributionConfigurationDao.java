package org.xcolab.service.contest.proposal.domain.pointsdistributionconfiguration;

import org.xcolab.model.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface PointsDistributionConfigurationDao {
    int deleteByProposalId(Long proposalId);
    List<PointsDistributionConfiguration> findByGiven(Long proposalId, Long pointTypeId);
    PointsDistributionConfiguration create(PointsDistributionConfiguration pointsDistributionConfiguration);
    PointsDistributionConfiguration get(Long id) throws NotFoundException;
    PointsDistributionConfiguration getByProposalTemplateSectionDefinitionId(Long targetProposalTemplateSectionDefinitionId) throws NotFoundException;
    boolean update(PointsDistributionConfiguration pointsDistributionConfiguration);
    int delete(Long id);
}
