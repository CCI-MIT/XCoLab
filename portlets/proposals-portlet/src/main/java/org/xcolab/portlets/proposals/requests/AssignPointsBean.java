package org.xcolab.portlets.proposals.requests;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.model.PointsDistributionConfiguration;
import com.ext.portlet.service.ProposalRatingTypeLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import org.hibernate.validator.constraints.NotBlank;
import org.xcolab.portlets.proposals.wrappers.PointTypeWrapper;
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created by Manuel Thurner on 24/07/14.
 */
public class AssignPointsBean {
    //pointTypeId => [UserId -> Percentage]
    //The percentages are from 0 to 100 here, in order to make it easier to display it to the user
    private Map<Long, Map<Long, Double>> assignments;

    private List<User> usersNotInTeam;

    public AssignPointsBean() {
        this.assignments = new HashMap<Long, Map<Long, Double>>();
    }

    public void setupUsers(List<User> teamMembers) throws SystemException {
        List<Long> teamMembersIds = new ArrayList<Long>();
        for (User u: teamMembers) {
            teamMembersIds.add(u.getUserId());
        }

        DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class, PortalClassLoaderUtil.getClassLoader());
        if (teamMembersIds.size() > 0) {
            userQuery.add(
                RestrictionsFactoryUtil.not(
                    RestrictionsFactoryUtil.in("userId", teamMembersIds)
                )
            );
        }
        usersNotInTeam = UserLocalServiceUtil.dynamicQuery(userQuery);
    }

    public void addAssignment(PointTypeWrapper pointType, List<User> users, List<PointsDistributionConfiguration> existingDistribution) {
        final double percentMultiplicationFactor = pointType.getPercentageOfTotal() * 100;

        Map<Long, Double> entityPercentages = new HashMap<Long, Double>();
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
