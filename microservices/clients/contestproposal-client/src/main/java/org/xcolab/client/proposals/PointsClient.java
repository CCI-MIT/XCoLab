package org.xcolab.client.proposals;

import org.xcolab.client.contest.resources.ContestResource;
import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.client.proposals.pojo.points.PointTypeDto;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfiguration;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfigurationDto;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.enums.ServiceNamespace;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PointsClient {

    private static final Map<ServiceNamespace, PointsClient> instances = new HashMap<>();

    private final ServiceNamespace serviceNamespace;

    private final RestResource1<PointsDistributionConfigurationDto, Long>
            pointsDistributionConfigurationResource;
    private final RestResource1<PointTypeDto, Long> pointTypeResource;

    private PointsClient(ServiceNamespace serviceNamespace) {
        pointsDistributionConfigurationResource = new RestResource1<>(
                ContestResource.POINTS_DISTRIBUTION_CONFIGURATION,
                PointsDistributionConfigurationDto.TYPES, serviceNamespace);
        pointTypeResource = new RestResource1<>(ContestResource.POINT_TYPE, PointTypeDto.TYPES,
                serviceNamespace);
        this.serviceNamespace = serviceNamespace;
    }

    public static PointsClient fromNamespace(ServiceNamespace proposalService) {
        return instances.computeIfAbsent(proposalService, PointsClient::new);
    }

    public PointsDistributionConfiguration createPointsDistributionConfiguration(
            PointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource
                .create(new PointsDistributionConfigurationDto(pointsDistributionConfiguration))
                .execute().toPojo(serviceNamespace);
    }

    public PointsDistributionConfiguration
    getPointsDistributionConfigurationByTargetPlanSectionDefinitionId(
            long targetSectionDefinitionId) {
        try {
            return pointsDistributionConfigurationResource
                    .collectionService("getByTargetProposalTemplateSectionDefinitionId",
                            PointsDistributionConfigurationDto.class)
                    .queryParam("targetProposalTemplateSectionDefinitionId",
                            targetSectionDefinitionId)
                    .getChecked().toPojo(serviceNamespace);
        } catch (EntityNotFoundException ignored){
            return null;
        }

    }

    public boolean updatePointsDistributionConfiguration(
            PointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource
                .update(new PointsDistributionConfigurationDto(pointsDistributionConfiguration),
                        pointsDistributionConfiguration.getId())
                .execute();
    }

    public List<PointsDistributionConfiguration> getPointsDistributionByProposalIdPointTypeId(
            Long proposalId, Long pointTypeId) {
        return DtoUtil.toPojos(pointsDistributionConfigurationResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("pointTypeId", pointTypeId)
                .execute(), serviceNamespace);
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
                .withCache(CacheKeys.of(PointTypeDto.class, Id), CacheName.MISC_REQUEST)
                .execute().toPojo(serviceNamespace);

    }

    public List<PointType> getAllPointTypes() {
        return DtoUtil.toPojos(pointTypeResource.list()
                .execute(), serviceNamespace);
    }

    public List<PointType> getChildrenOfPointType(Long parentPointTypeId) {
        return DtoUtil.toPojos(pointTypeResource.list()
                .withCache(CacheKeys.withClass(PointTypeDto.class)
                                .withParameter("parentPointTypeId", parentPointTypeId)
                                .asList(),
                        CacheName.MISC_MEDIUM)
                .optionalQueryParam("parentPointTypeId", parentPointTypeId)
                .execute(), serviceNamespace);
    }
}
