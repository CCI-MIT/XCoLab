package org.xcolab.client.contest.proposals;

import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class PointsClient {

    private final RestResource1<IPointsDistributionConfiguration, Long>
            pointsDistributionConfigurationResource = null; // pointsDistributionConfigurations
    private final RestResource1<PointTypeWrapper, Long> pointTypeResource = null; // pointTypes

    public IPointsDistributionConfiguration createPointsDistributionConfiguration(
            IPointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource
                .create(pointsDistributionConfiguration)
                .execute();
    }

    public IPointsDistributionConfiguration
    getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(
            long targetSectionDefinitionId) {
        try {
            return pointsDistributionConfigurationResource
                    .collectionService("getByTargetProposalTemplateSectionDefinitionId",
                            IPointsDistributionConfiguration.class)
                    .queryParam("targetProposalTemplateSectionDefinitionId",
                            targetSectionDefinitionId)
                    .getChecked();
        } catch (EntityNotFoundException ignored){
            return null;
        }

    }

    public boolean updatePointsDistributionConfiguration(
            IPointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource
                .update(pointsDistributionConfiguration,
                        pointsDistributionConfiguration.getId())
                .execute();
    }

    public List<IPointsDistributionConfiguration> getPointsDistributionByProposalIdPointTypeId(
            Long proposalId, Long pointTypeId) {
        return pointsDistributionConfigurationResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("pointTypeId", pointTypeId)
                .execute();
    }

    public Boolean deletePointsDistributionConfiguration(Long pointsDistributionConfigurationId) {
        return pointsDistributionConfigurationResource.delete(pointsDistributionConfigurationId)
                .execute();
    }

    public void verifyDistributionConfigurationsForProposalId(Long proposalId) {
         pointsDistributionConfigurationResource.collectionService("verifyDistributionConfigurationsForProposalId", String.class)
                .queryParam("proposalId", proposalId).execute();
    }

    public Boolean deletePointsDistributionConfigurationByProposalId(Long proposalId) {
        return pointsDistributionConfigurationResource.collectionService("removeByProposalId", Boolean.class)
                .queryParam("proposalId", proposalId).delete();
    }

    public PointTypeWrapper getPointType(long Id) {
        return pointTypeResource.get(Id)
                .withCache(CacheKeys.of(PointTypeWrapper.class, Id), CacheName.MISC_REQUEST)
                .execute();

    }

    public List<PointTypeWrapper> getAllPointTypes() {
        return pointTypeResource.list()
                .execute();
    }

    public List<PointTypeWrapper> getChildrenOfPointType(Long parentPointTypeId) {
        return pointTypeResource.list()
                .withCache(CacheKeys.withClass(PointTypeWrapper.class)
                                .withParameter("parentPointTypeId", parentPointTypeId)
                                .asList(),
                        CacheName.MISC_MEDIUM)
                .optionalQueryParam("parentPointTypeId", parentPointTypeId)
                .execute();
    }
}
