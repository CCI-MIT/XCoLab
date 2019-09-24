package org.xcolab.service.contest.proposal.domain.pointsdistributionconfiguration;

import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.service.contest.exceptions.NotFoundException;

import java.util.List;

public interface PointsDistributionConfigurationDao {
    int deleteByProposalId(Long proposalId);
    List<IPointsDistributionConfiguration> findByGiven(Long proposalId, Long pointTypeId);
    IPointsDistributionConfiguration create(
            IPointsDistributionConfiguration pointsDistributionConfiguration);
    IPointsDistributionConfiguration get(Long id) throws NotFoundException;
    IPointsDistributionConfiguration getByProposalTemplateSectionDefinitionId(Long targetProposalTemplateSectionDefinitionId) throws NotFoundException;
    boolean update(IPointsDistributionConfiguration pointsDistributionConfiguration);
    int delete(Long id);
}
