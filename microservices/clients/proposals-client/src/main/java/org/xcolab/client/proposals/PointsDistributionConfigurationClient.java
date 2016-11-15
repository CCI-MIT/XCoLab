package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.client.proposals.pojo.points.PointTypeDto;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfiguration;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfigurationDto;
import org.xcolab.util.http.caching.CacheKeys;
import org.xcolab.util.http.caching.CacheRetention;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestService;
import org.xcolab.util.http.dto.DtoUtil;
import org.xcolab.util.http.exceptions.EntityNotFoundException;

import java.util.List;

public final class PointsDistributionConfigurationClient {
    private static final RestService proposalService = new RestService("proposals-service");

    private static final RestResource1<PointsDistributionConfigurationDto, Long> pointsDistributionConfigurationResource = new RestResource1<>(proposalService,
            "pointsDistributionConfigurations", PointsDistributionConfigurationDto.TYPES);

    private static final RestResource1<PointTypeDto, Long> pointTypeResource = new RestResource1<>(proposalService,
            "pointTypes", PointTypeDto.TYPES);

    public static PointsDistributionConfiguration createPointsDistributionConfiguration(PointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource.create(new PointsDistributionConfigurationDto(pointsDistributionConfiguration)).execute().toPojo(proposalService);
    }

    public static PointsDistributionConfiguration getPointsDistributionConfigurationByTargetPlanSectionDefinitionId(long targetPlanSectionDefinitionId) {
        try {
            return pointsDistributionConfigurationResource
                    .service("getByTargetPlanSectionDefinitionId",
                            PointsDistributionConfiguration.class)
                    .queryParam("targetPlanSectionDefinitionId", targetPlanSectionDefinitionId)
                    .getChecked();
        }catch (EntityNotFoundException ignored){
            return null;
        }

    }

    public static boolean updatePointsDistributionConfiguration(PointsDistributionConfiguration pointsDistributionConfiguration) {
        return pointsDistributionConfigurationResource.update(new PointsDistributionConfigurationDto(pointsDistributionConfiguration), pointsDistributionConfiguration.getId_())
                .execute();
    }

    public static List<PointsDistributionConfiguration> getPointsDistributionByProposalIdPointTypeId(Long proposalId, Long pointTypeId) {
        return DtoUtil.toPojos(pointsDistributionConfigurationResource.list()
                .optionalQueryParam("proposalId", proposalId)
                .optionalQueryParam("pointTypeId", pointTypeId)
                .execute(), proposalService);
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
                .withCache(CacheKeys.of(PointTypeDto.class, Id_), CacheRetention.REQUEST)
                .execute().toPojo(proposalService);

    }

    public static List<PointType> getAllPointTypes() {
        return DtoUtil.toPojos(pointTypeResource.list()
                .execute(), proposalService);
    }

    public static List<PointType> getChildrenOfPointType(Long parentPointTypeId) {
        return DtoUtil.toPojos(pointTypeResource.list()
                .withCache(CacheKeys.withClass(PointTypeDto.class)
                                .withParameter("parentPointTypeId", parentPointTypeId)
                                .asList(),
                        CacheRetention.MEDIUM)
                .optionalQueryParam("parentPointTypeId", parentPointTypeId)
                .execute(), proposalService);
    }
}
