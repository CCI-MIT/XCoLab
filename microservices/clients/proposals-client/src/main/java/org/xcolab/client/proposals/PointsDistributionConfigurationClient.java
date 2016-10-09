package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.PointType;
import org.xcolab.client.proposals.pojo.PointsDistributionConfiguration;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class PointsDistributionConfigurationClient {
    private static final RestService proposalService = new RestService("proposals-service");

    private static final RestResource1<PointsDistributionConfiguration, Long> pointsDistributionConfigurationResource = new RestResource1<>(proposalService,
            "pointsDistributionConfigurations", PointsDistributionConfiguration.TYPES);

    private static final RestResource1<PointType, Long> pointTypeResource = new RestResource1<>(proposalService,
            "pointTypes", PointType.TYPES);

    public static PointsDistributionConfiguration createPointsDistributionConfiguration(PointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource.create(pointsDistributionConfiguration).execute();
    }

    public static PointsDistributionConfiguration getPointsDistributionConfigurationByTargetPlanSectionDefinitionId(long targetPlanSectionDefinitionId) {
        return pointsDistributionConfigurationResource.service("getByTargetPlanSectionDefinitionId", PointsDistributionConfiguration.class)
                .queryParam("targetPlanSectionDefinitionId", targetPlanSectionDefinitionId)
                .get();

    }

    public static boolean updatePointsDistributionConfiguration(PointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource.update(pointsDistributionConfiguration, pointsDistributionConfiguration.getId_())
                .execute();
    }

    public static List<PointsDistributionConfiguration> getPointsDistributionByProposalIdPointTypeId(Long proposalId, Long pointTypeId) {
        return pointsDistributionConfigurationResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("pointTypeId", pointTypeId)
                .execute();
    }

    public static Boolean deletePointsDistributionConfiguration(Long pointsDistributionConfigurationId) {
        return pointsDistributionConfigurationResource.delete(pointsDistributionConfigurationId).execute();
    }

    public static Boolean deletePointsDistributionConfigurationByProposalId(Long proposalId) {
        return pointsDistributionConfigurationResource.service("removeByProposalId", Boolean.class)
                .queryParam("proposalId", proposalId).execute();
    }

    public static PointType getPointType(long Id_) {
        return pointTypeResource.get(Id_)
                .withCache(CacheKeys.of(PointType.class, Id_), CacheRetention.REQUEST)
                .execute();

    }

    public static List<PointType> getAllPointTypes() {
        return pointTypeResource.list()
                .execute();
    }

    public static List<PointType> getChildrenOfPointType(Long parentPointTypeId) {
        return pointTypeResource.list()
                .withCache(CacheKeys.withClass(PointType.class)
                                .withParameter("parentPointTypeId", parentPointTypeId)
                                .asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("parentPointTypeId", parentPointTypeId)
                .execute();
    }
}
