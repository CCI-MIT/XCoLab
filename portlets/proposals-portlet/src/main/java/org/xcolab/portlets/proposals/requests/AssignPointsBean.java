package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.model.PointsDistributionConfiguration;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
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
    //pointTypeId => [UserId -> Percentage]
    //The percentages are from 0 to 100 here, in order to make it easier to display it to the user
    private Map<Long, Map<Long, Double>> assignments;

    private List<User> usersNotInTeam;

    public AssignPointsBean() {
        this.assignments = new HashMap<>();
    }

    public void setupUsers(List<User> teamMembers) throws SystemException {
        List<User> allUsers = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        List<User> usersNotInTeam = new ArrayList<>(allUsers);
        usersNotInTeam.removeAll(teamMembers);
        this.usersNotInTeam = usersNotInTeam;
    }

    public void addAssignment(PointTypeWrapper pointType, List<User> users, List<PointsDistributionConfiguration> existingDistribution) {
        final double percentMultiplicationFactor = pointType.getPercentageOfTotal() * 100;

        Map<Long, Double> entityPercentages = new HashMap<>();
        if (users != null) {
            for (User u : users) {
                Double percentage = (1.0/users.size()) * percentMultiplicationFactor;
                PointsDistributionConfiguration foundElement = null;
                for (PointsDistributionConfiguration distribution : existingDistribution) {
                    if (distribution.getTargetUserId() == u.getUserId()) {
                        percentage = distribution.getPercentage() * percentMultiplicationFactor;
                        foundElement = distribution;
                        break;
                    }
                }
                if (foundElement != null) {
                    existingDistribution.remove(foundElement);
                }
                entityPercentages.put(u.getUserId(), roundToTwoDigits(percentage));
            }
        }

        //add all remaining distributions that were not in users
        for (PointsDistributionConfiguration distribution: existingDistribution) {
            entityPercentages.put(distribution.getTargetUserId(), roundToTwoDigits(distribution.getPercentage() * percentMultiplicationFactor));
        }

        this.assignments.put(pointType.getId(), entityPercentages);
    }

    private static double roundToTwoDigits(double d) {
        DecimalFormat newFormat = new DecimalFormat("#.##");
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator('.');
        newFormat.setDecimalFormatSymbols(dfs);
        return Double.valueOf(newFormat.format(d));
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
