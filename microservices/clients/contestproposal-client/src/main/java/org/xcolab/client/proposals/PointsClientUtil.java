package org.xcolab.client.proposals;

import org.xcolab.client.proposals.pojo.points.PointType;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfiguration;

import java.util.List;

public final class PointsClientUtil {

    private static final PointsClient client = new PointsClient();

    public static PointsClient getClient() {
        return client;
    }

    public static PointsDistributionConfiguration createPointsDistributionConfiguration(
            PointsDistributionConfiguration pointsDistributionConfiguration) {
        return client.createPointsDistributionConfiguration(pointsDistributionConfiguration);
    }

    public static PointsDistributionConfiguration
    getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(
            long targetProposalTemplateSectionDefinitionId) {
        return client.getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(
                targetProposalTemplateSectionDefinitionId);
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

    public static PointType getPointType(long Id) {
        return client.getPointType(Id);
    }

    public static List<PointType> getAllPointTypes() {
        return client.getAllPointTypes();
    }

    public static List<PointType> getChildrenOfPointType(
            Long parentPointTypeId) {
        return client.getChildrenOfPointType(parentPointTypeId);
    }
}
