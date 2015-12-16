package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.service.PointsDistributionConfigurationLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.xcolab.points.DistributionStrategy;
import org.xcolab.points.ReceiverLimitationStrategy;
import org.xcolab.portlets.proposals.wrappers.PointTypeWrapper;

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
    private Map<Long, Map<Long, Double>> assignments;

    private List<User> usersNotInTeam;

    public AssignPointsBean(long proposalId) {
        this.proposalId = proposalId;
        this.assignments = new HashMap<>();
    }

    public void addAllAssignments(PointTypeWrapper pointType, List<User> members) throws SystemException, PortalException {
        if (pointType.getDistributionStrategy().equals(DistributionStrategy.USER_DEFINED)) {

            PointsDistributionConfigurationLocalServiceUtil.verifyDistributionConfigurationsForProposalId(proposalId);

            List<PointsDistributionConfiguration> existingDistributionConfigurations =
                    PointsDistributionConfigurationLocalServiceUtil.findByProposalIdPointTypeId(proposalId, pointType.getId());

            switch(pointType.getReceiverLimitationStrategy().getType()) {
                case USER:
                    List<User> presetUsers = null;
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

    public void initializeUsers(List<User> teamMembers) throws SystemException {
        usersNotInTeam = new ArrayList<>(UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS));
        usersNotInTeam.removeAll(teamMembers);
    }

    public void addAssignment(PointTypeWrapper pointType, List<User> users,
                              List<PointsDistributionConfiguration> existingDistributionConfigurations) {

        final double percentMultiplicationFactor = pointType.getPercentageOfTotal() * 100;

        Map<Long, Double> entityPercentages = new HashMap<>();
        if (users != null) {
            for (User u : users) {
                double percentage = (1.0/users.size()) * percentMultiplicationFactor;
                PointsDistributionConfiguration foundElement = null;
                for (PointsDistributionConfiguration distribution : existingDistributionConfigurations) {
                    if (distribution.getTargetUserId() == u.getUserId()) {
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

        this.assignments.put(pointType.getId(), entityPercentages);
    }

    public Map<Long, Double> get(Long pointTypeId) {
        return this.assignments.get(pointTypeId);
    }

    public Set<Long> getUserIds(Long pointTypeId) {
        return this.assignments.get(pointTypeId).keySet();
    }

    public Map<Long, Map<Long, Double>> getAssignments() {
        return assignments;
    }

    public void setAssignments(Map<Long, Map<Long, Double>> assignments) {
        this.assignments = assignments;
    }

    public List<User> getUsersNotInTeam() {
        return usersNotInTeam;
    }

    public void setUsersNotInTeam(List<User> usersNotInTeam) {
        this.usersNotInTeam = usersNotInTeam;
    }
}
