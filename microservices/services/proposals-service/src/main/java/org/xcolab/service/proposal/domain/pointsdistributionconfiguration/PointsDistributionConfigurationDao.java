package org.xcolab.service.proposal.domain.pointsdistributionconfiguration;

import org.xcolab.model.tables.pojos.PointsDistributionConfiguration;
import org.xcolab.service.proposal.exceptions.NotFoundException;

import java.util.List;

public interface PointsDistributionConfigurationDao {
    int deleteByProposalId(Long proposalId);
    List<PointsDistributionConfiguration> findByGiven(Long proposalId, Long pointTypeId);
    PointsDistributionConfiguration create(PointsDistributionConfiguration pointsDistributionConfiguration);
    PointsDistributionConfiguration get(Long id_) throws NotFoundException;
    PointsDistributionConfiguration getByPlanSectionDefinitionId(Long targetPlanSectionDefinitionId) throws NotFoundException;
    boolean update(PointsDistributionConfiguration pointsDistributionConfiguration);
    int delete(Long id_);
}
