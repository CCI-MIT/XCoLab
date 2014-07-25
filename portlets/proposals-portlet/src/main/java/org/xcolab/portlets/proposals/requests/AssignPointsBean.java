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
import org.xcolab.portlets.proposals.wrappers.ProposalWrapper;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;

import java.util.*;

/**
 * Created by Manuel Thurner on 24/07/14.
 */
public class AssignPointsBean {
    //pointTypeId => [UserId -> Percentage]
    //The percentages are from 0 to 100 here, in order to make it easier to display it to the user
    private Map<Long, Map<Long, Integer>> assignments;

    private Map<Long, Long> singleUserAssignments;



    private Map<Long, String> singleUserAssignmentNames;

    private List<User> usersNotInTeam;

    public AssignPointsBean() {
        this.assignments = new HashMap<Long, Map<Long, Integer>>();
        this.singleUserAssignments = new HashMap<Long, Long>();
        this.singleUserAssignmentNames = new HashMap<Long, String>();
    }

    public void setupUsers(List<User> teamMembers) throws SystemException {
        List<Long> teamMembersIds = new ArrayList<Long>();
        for (User u: teamMembers) {
            teamMembersIds.add(u.getUserId());
        }

        DynamicQuery userQuery = DynamicQueryFactoryUtil.forClass(User.class, PortalClassLoaderUtil.getClassLoader());
        if (teamMembersIds.size() > 0) {
            userQuery.add(
                    //RestrictionsFactoryUtil.not(
                     RestrictionsFactoryUtil.in("userId", teamMembersIds)
                    // )
                    );
        }
        usersNotInTeam = UserLocalServiceUtil.dynamicQuery(userQuery);
    }

    public void addAssignment(long pointTypeId, List<User> users, List<PointsDistributionConfiguration> existingDistribution) {
        Map<Long, Integer> entityPercentages = new HashMap<Long, Integer>();
        for (User u: users) {
            Integer percentage = 0;
            for (PointsDistributionConfiguration distribution: existingDistribution) {
                if (distribution.getTargetUserId() == u.getUserId()) {
                    percentage = (int)Math.round(distribution.getPercentage()*100);
                }
            }
            entityPercentages.put(u.getUserId(), percentage);
        }
        this.assignments.put(pointTypeId, entityPercentages);
    }

    public void addSingleUserAssignment(long pointTypeId, PointsDistributionConfiguration existingDistribution) throws SystemException {
        Long userId = -1L;
        String userName = "";
        if (existingDistribution != null) {
            userId = existingDistribution.getTargetUserId();
            userName = UserLocalServiceUtil.fetchUser(userId).getScreenName();
        }
        this.singleUserAssignments.put(pointTypeId, userId);
        this.singleUserAssignmentNames.put(pointTypeId, userName);
    }

    public Map<Long, Integer> get(Long pointTypeId) {
        return this.assignments.get(pointTypeId);
    }

    public Map<Long, Map<Long, Integer>> getAssignments() {
        return assignments;
    }

    public void setAssignments(Map<Long, Map<Long, Integer>> assignments) {
        this.assignments = assignments;
    }

    public Map<Long, Long> getSingleUserAssignments() {
        return singleUserAssignments;
    }

    public void setSingleUserAssignments(Map<Long, Long> singleUserAssignments) {
        this.singleUserAssignments = singleUserAssignments;
    }

    public List<User> getUsersNotInTeam() {
        return usersNotInTeam;
    }

    public void setUsersNotInTeam(List<User> usersNotInTeam) {
        this.usersNotInTeam = usersNotInTeam;
    }

    public Map<Long, String> getSingleUserAssignmentNames() {
        return singleUserAssignmentNames;
    }

    public void setSingleUserAssignmentNames(Map<Long, String> singleUserAssignmentNames) {
        this.singleUserAssignmentNames = singleUserAssignmentNames;
    }
}
