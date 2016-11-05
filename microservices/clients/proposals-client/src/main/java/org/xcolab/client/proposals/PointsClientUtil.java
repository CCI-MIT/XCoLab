package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfiguration;
import org.xcolab.util.http.client.RestService;

import java.util.List;

public final class PointsClientUtil {
    private static final RestService proposalService = new RestService("proposals-service");

    private static final PointsClient client
            = PointsClient.fromService(proposalService);

    public static PointsClient getClient() {
        return client;
    }

    public static PointsDistributionConfiguration createPointsDistributionConfiguration(
            PointsDistributionConfiguration pointsDistributionConfiguration) {
        return client.createPointsDistributionConfiguration(pointsDistributionConfiguration);
    }

    public static PointsDistributionConfiguration
    getPointsDistributionConfigurationByTargetPlanSectionDefinitionId(
            long targetPlanSectionDefinitionId) {
        return client.getPointsDistributionConfigurationByTargetPlanSectionDefinitionId(
                targetPlanSectionDefinitionId);
    }

    public static boolean updatePointsDistributionConfiguration(
            PointsDistributionConfiguration pointsDistributionConfiguration) {
        return client.updatePointsDistributionConfiguration(pointsDistributionConfiguration);
    }

    public static List<PointsDistributionConfiguration>
    getPointsDistributionByProposalIdPointTypeId(
            Long proposalId, Long pointTypeId) {
        return client.getPointsDistributionByProposalIdPointTypeId(proposalId, pointTypeId);
    }

    public static Boolean deletePointsDistributionConfiguration(
            Long pointsDistributionConfigurationId) {
        return client.deletePointsDistributionConfiguration(pointsDistributionConfigurationId);
    }

    public static Boolean deletePointsDistributionConfigurationByProposalId(Long proposalId) {
        return client.deletePointsDistributionConfigurationByProposalId(proposalId);
    }
    public static void verifyDistributionConfigurationsForProposalId(Long proposalId){
         client.verifyDistributionConfigurationsForProposalId(proposalId);
    }

    public static PointType getPointType(long Id_) {
        return client.getPointType(Id_);
    }

    public static List<PointType> getAllPointTypes() {
        return client.getAllPointTypes();
    }

    public static List<PointType> getChildrenOfPointType(
            Long parentPointTypeId) {
        return client.getChildrenOfPointType(parentPointTypeId);
    }
}
