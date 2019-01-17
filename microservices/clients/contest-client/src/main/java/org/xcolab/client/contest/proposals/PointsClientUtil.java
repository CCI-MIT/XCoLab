package org.xcolab.client.contest.proposals;

import org.xcolab.client.contest.pojo.PointTypeWrapper;
import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;

import java.util.List;

public final class PointsClientUtil {

    private static final PointsClient client = new PointsClient();

    public static PointsClient getClient() {
        return client;
    }

    public static IPointsDistributionConfiguration createPointsDistributionConfiguration(
            IPointsDistributionConfiguration pointsDistributionConfiguration) {
        return client.createPointsDistributionConfiguration(pointsDistributionConfiguration);
    }

    public static IPointsDistributionConfiguration
    getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(
            long targetProposalTemplateSectionDefinitionId) {
        return client.getPointsDistributionConfigurationByTargetProposalTemplateSectionDefinitionId(
                targetProposalTemplateSectionDefinitionId);
    }

    public static boolean updatePointsDistributionConfiguration(
            IPointsDistributionConfiguration pointsDistributionConfiguration) {
        return client.updatePointsDistributionConfiguration(pointsDistributionConfiguration);
    }

    public static List<IPointsDistributionConfiguration>
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

    public static PointTypeWrapper getPointType(long Id) {
        return client.getPointType(Id);
    }

    public static List<PointTypeWrapper> getAllPointTypes() {
        return client.getAllPointTypes();
    }

    public static List<PointTypeWrapper> getChildrenOfPointType(
            Long parentPointTypeId) {
        return client.getChildrenOfPointType(parentPointTypeId);
    }
}
