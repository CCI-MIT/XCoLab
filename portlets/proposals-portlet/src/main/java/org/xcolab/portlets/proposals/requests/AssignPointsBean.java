package org.xcolab.portlets.proposals.requests;


import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;


import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.proposals.PointsDistributionConfigurationClientUtil;
import org.xcolab.client.proposals.pojo.points.PointsDistributionConfiguration;
import org.xcolab.points.DistributionStrategy;
import org.xcolab.points.ReceiverLimitationStrategy;
import org.xcolab.portlets.proposals.wrappers.PointTypeWrapper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Manuel Thurner on 24/07/14.
 */
public class AssignPointsBean {
    private final long proposalId;
    //pointTypeId => [UserId -> Percentage]
    //The percentages are from 0 to 100 here, in order to make it easier to display it to the user
    private final Map<Long, Map<Long, Double>> assignmentsByUserIdByPointTypeId;

    private List<Member> usersNotInTeam;

    public AssignPointsBean() {
        this.proposalId = 0;
        assignmentsByUserIdByPointTypeId = new HashMap<>();
    }

    public AssignPointsBean(long proposalId) {
        this.proposalId = proposalId;
        assignmentsByUserIdByPointTypeId = new HashMap<>();
    }

    public void addAllAssignments(PointTypeWrapper pointType, List<Member> members) throws SystemException, PortalException {
        if (pointType.getDistributionStrategy().equals(DistributionStrategy.USER_DEFINED)) {

            PointsDistributionConfigurationLocalServiceUtil.verifyDistributionConfigurationsForProposalId(proposalId);

            List<PointsDistributionConfiguration> existingDistributionConfigurations =
                    PointsDistributionConfigurationClientUtil
                            .getPointsDistributionByProposalIdPointTypeId(proposalId, pointType.getId());

            switch(pointType.getReceiverLimitationStrategy().getType()) {
                case USER:
                    List<Member> presetUsers = null;
                    if (pointType.getReceiverLimitationStrategy().equals(ReceiverLimitationStrategy.ANY_TEAM_MEMBER)) {
                        presetUsers = members;
                    }

                    addAssignment(pointType, presetUsers, existingDistributionConfigurations);
                    break;
                case SUB_PROPOSAL:
                    //TODO: subProposals
                    throw new UnsupportedOperationException("The points system does not yet support" +
                            "user defined allocation to sub proposals");
            }
        }
        //follow down the pointType tree
        for (PointTypeWrapper p: pointType.getChildren()) {
            addAllAssignments(p, members);
        }
        initializeUsers(members);
    }

    public void initializeUsers(List<Member> teamMembers) throws SystemException {
        usersNotInTeam = new ArrayList<>(MembersClient.listAllMembers());
        usersNotInTeam.removeAll(teamMembers);
    }

    public void addAssignment(PointTypeWrapper pointType, List<Member> users,
                              List<PointsDistributionConfiguration> existingDistributionConfigurations) {

        final double percentMultiplicationFactor = pointType.getPercentageOfTotal() * 100;

        Map<Long, Double> entityPercentages = new HashMap<>();
        if (users != null) {
            for (Member u : users) {
                double percentage = (1.0/users.size()) * percentMultiplicationFactor;
                PointsDistributionConfiguration foundElement = null;
                for (PointsDistributionConfiguration distribution : existingDistributionConfigurations) {
                    if (distribution.getTargetUserId() == u.getId_()) {
                        percentage = distribution.getPercentage() * percentMultiplicationFactor;
                        foundElement = distribution;
                        break;
                    }
                }
                if (foundElement != null) {
                    existingDistributionConfigurations.remove(foundElement);
                }
                entityPercentages.put(u.getUserId(), percentage);
            }
        }

        //add all remaining distributions that were not in users
        for (PointsDistributionConfiguration distribution: existingDistributionConfigurations) {
            entityPercentages.put(distribution.getTargetUserId(), distribution.getPercentage() * percentMultiplicationFactor);
        }

        this.assignmentsByUserIdByPointTypeId.put(pointType.getId(), entityPercentages);
    }

    private static double roundToTwoDigits(double d) {
        DecimalFormat newFormat = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        newFormat.setDecimalFormatSymbols(dfs);
        return Double.valueOf(newFormat.format(d));
    }

    public Map<Long, Double> getAssignmentsByUserId(Long pointTypeId) throws PortalException {
        final Map<Long, Double> assignmentsByUserId = this.assignmentsByUserIdByPointTypeId.get(pointTypeId);
        if (assignmentsByUserId == null) {
            throw new PortalException("No assignments found for pointTypeId "+pointTypeId);
        }
        return assignmentsByUserId;
    }

    public Set<Long> getUserIds(Long pointTypeId) throws PortalException {
        return getAssignmentsByUserId(pointTypeId).keySet();
    }

    public Map<Long, Map<Long, Double>> getAssignmentsByUserIdByPointTypeId() {
        return assignmentsByUserIdByPointTypeId;
    }

    public List<Member> getUsersNotInTeam() {
        return usersNotInTeam;
    }
}
