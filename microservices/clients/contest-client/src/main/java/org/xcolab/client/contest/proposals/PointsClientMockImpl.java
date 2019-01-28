package org.xcolab.client.contest.proposals;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;

import java.util.Collections;
import java.util.List;

@Component
@Profile("test")
public class PointsClientMockImpl implements IPointsClient {

    @Override
    public IPointsDistributionConfiguration createPointsDistributionConfiguration(
            IPointsDistributionConfiguration pointsDistributionConfiguration) {
        return null;
    }

    @Override
    public IPointsDistributionConfiguration getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(
            Long targetProposalTemplateSectionDefinitionId) {
        return null;
    }

    @Override
    public boolean updatePointsDistributionConfiguration(
            IPointsDistributionConfiguration pointsDistributionConfiguration) {
        return false;
    }

    @Override
    public List<IPointsDistributionConfiguration> getPointsDistributionByProposalIdPointTypeId(
            Long proposalId, Long pointTypeId) {
        return Collections.emptyList();
    }

    @Override
    public boolean deletePointsDistributionConfiguration(Long id) {
        return false;
    }

    @Override
    public boolean verifyDistributionConfigurationsForProposalId(Long proposalId) {
        return false;
    }

    @Override
    public boolean deletePointsDistributionConfigurationByProposalId(Long proposalId) {
        return false;
    }

    @Override
    public PointTypeWrapper getPointType(Long pointTypeId) {
        return null;
    }

    @Override
    public List<PointTypeWrapper> getPointTypes(Long parentPointTypeId) {
        return Collections.emptyList();
    }
}
