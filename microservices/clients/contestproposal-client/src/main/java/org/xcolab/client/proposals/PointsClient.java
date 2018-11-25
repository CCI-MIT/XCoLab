package org.xcolab.client.proposals;

import org.xcolab.client.contest.resources.ContestResource;
import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfiguration;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PointsClient {

    private static final Map<ServiceNamespace, PointsClient> instances = new HashMap<>();

    private final RestResource1<PointsDistributionConfiguration, Long>
            pointsDistributionConfigurationResource;
    private final RestResource1<PointType, Long> pointTypeResource;

    public PointsClient() {
        pointsDistributionConfigurationResource = new RestResource1<>(
                ContestResource.POINTS_DISTRIBUTION_CONFIGURATION,
                PointsDistributionConfiguration.TYPES);
        pointTypeResource = new RestResource1<>(ContestResource.POINT_TYPE, PointType.TYPES);
    }

    public static PointsClient fromNamespace(ServiceNamespace proposalService) {
        return instances.computeIfAbsent(proposalService, serviceNamespace -> new PointsClient());
    }

    public PointsDistributionConfiguration createPointsDistributionConfiguration(
            PointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource
                .create(new PointsDistributionConfiguration(pointsDistributionConfiguration))
                .execute();
    }

    public PointsDistributionConfiguration
    getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(
            long targetSectionDefinitionId) {
        try {
            return pointsDistributionConfigurationResource
                    .collectionService("getByTargetProposalTemplateSectionDefinitionId",
                            PointsDistributionConfiguration.class)
                    .queryParam("targetProposalTemplateSectionDefinitionId",
                            targetSectionDefinitionId)
                    .getChecked();
        } catch (EntityNotFoundException ignored){
            return null;
        }

    }

    public boolean updatePointsDistributionConfiguration(
            PointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource
                .update(new PointsDistributionConfiguration(pointsDistributionConfiguration),
                        pointsDistributionConfiguration.getId())
                .execute();
    }

    public List<PointsDistributionConfiguration> getPointsDistributionByProposalIdPointTypeId(
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

    public PointType getPointType(long Id) {
        return pointTypeResource.get(Id)
                .withCache(CacheKeys.of(PointType.class, Id), CacheName.MISC_REQUEST)
                .execute();

    }

    public List<PointType> getAllPointTypes() {
        return pointTypeResource.list()
                .execute();
    }

    public List<PointType> getChildrenOfPointType(Long parentPointTypeId) {
        return pointTypeResource.list()
                .withCache(CacheKeys.withClass(PointType.class)
                                .withParameter("parentPointTypeId", parentPointTypeId)
                                .asList(),
                        CacheName.MISC_MEDIUM)
                .optionalQueryParam("parentPointTypeId", parentPointTypeId)
                .execute();
    }
}
