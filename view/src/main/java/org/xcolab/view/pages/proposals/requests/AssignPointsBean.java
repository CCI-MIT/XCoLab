package org.xcolab.view.pages.proposals.requests;


import org.xcolab.client.contest.pojo.IPointsDistributionConfiguration;
import org.xcolab.client.contest.pojo.wrapper.PointTypeWrapper;
import org.xcolab.client.contest.proposals.StaticProposalContext;
import org.xcolab.client.contest.proposals.enums.points.DistributionStrategy;
import org.xcolab.client.contest.proposals.enums.points.ReceiverLimitationStrategy;
import org.xcolab.client.user.StaticUserContext;
import org.xcolab.client.user.pojo.wrapper.UserWrapper;
import org.xcolab.commons.exceptions.InternalException;

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



    public AssignPointsBean() {
        this.proposalId = 0;
        assignmentsByUserIdByPointTypeId = new HashMap<>();
    }

    public AssignPointsBean(long proposalId) {
        this.proposalId = proposalId;
        assignmentsByUserIdByPointTypeId = new HashMap<>();
    }

    public void addAllAssignments(PointTypeWrapper pointType, List<UserWrapper> members) {
        if (pointType.getDistributionStrategyz().name().equals(DistributionStrategy.USER_DEFINED.name())) {

            StaticProposalContext.getPointsClient()
                    .verifyDistributionConfigurationsForProposalId(proposalId);

            List<IPointsDistributionConfiguration> existingDistributionConfigurations =
                    StaticProposalContext.getPointsClient()
                            .getPointsDistributionByProposalIdPointTypeId(proposalId, pointType.getId());

            switch(pointType.getReceiverLimitationStrategyz().getType()) {
                case USER:
                    List<UserWrapper> presetUsers = null;
                    if (pointType.getReceiverLimitationStrategyz().name().equals(
                            ReceiverLimitationStrategy.ANY_TEAM_MEMBER.name())) {
                        presetUsers = members;
                    }

                    addAssignment(pointType, presetUsers, existingDistributionConfigurations);
                    break;
                case SUB_PROPOSAL:
                    //TODO COLAB-2582: subProposals
                    throw new UnsupportedOperationException("The points system does not yet support" +
                            "user defined allocation to sub proposals");
                default:
            }
        }
        //follow down the pointType tree
        List<PointTypeWrapper> list = pointType.getChildren();
        if(list!=null) {
            for (PointTypeWrapper p : list) {
                addAllAssignments(p, members);
            }
        }

    }



    public void addAssignment(PointTypeWrapper pointType, List<UserWrapper> users,
                              List<IPointsDistributionConfiguration> existingDistributionConfigurations) {

        final double percentMultiplicationFactor = pointType.getPercentageOfTotal() * 100;

        Map<Long, Double> entityPercentages = new HashMap<>();
        if (users != null) {
            for (UserWrapper u : users) {
                double percentage = (1.0/users.size()) * percentMultiplicationFactor;
                IPointsDistributionConfiguration foundElement = null;
                for (IPointsDistributionConfiguration distribution : existingDistributionConfigurations) {
                    if (distribution.getTargetUserId() == u.getId()) {
                        percentage = distribution.getPercentage() * percentMultiplicationFactor;
                        foundElement = distribution;
                        break;
                    }
                }
                if (foundElement != null) {
                    existingDistributionConfigurations.remove(foundElement);
                }
                entityPercentages.put(u.getId(), percentage);
            }
        }

        //add all remaining distributions that were not in users
        for (IPointsDistributionConfiguration distribution: existingDistributionConfigurations) {
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

    public Map<Long, Double> getAssignmentsByUserId(Long pointTypeId) {
        final Map<Long, Double> assignmentsByUserId = this.assignmentsByUserIdByPointTypeId.get(pointTypeId);
        if (assignmentsByUserId == null) {
            throw new InternalException("No assignments found for pointTypeId " + pointTypeId);
        }
        return assignmentsByUserId;
    }

    public Set<Long> getUserIds(Long pointTypeId) {
        return getAssignmentsByUserId(pointTypeId).keySet();
    }
    public ArrayList<UserWrapper> getMembers(Long pointTypeId) {
        ArrayList<UserWrapper> members = new ArrayList<>();
        for(Long userId: getAssignmentsByUserId(pointTypeId).keySet()){
            UserWrapper m = StaticUserContext.getUserClient().getMemberUnchecked(userId);
            if(m!=null){
                members.add(m);
            }
        }
        return members;
    }

    public Map<Long, Map<Long, Double>> getAssignmentsByUserIdByPointTypeId() {
        return assignmentsByUserIdByPointTypeId;
    }

}
