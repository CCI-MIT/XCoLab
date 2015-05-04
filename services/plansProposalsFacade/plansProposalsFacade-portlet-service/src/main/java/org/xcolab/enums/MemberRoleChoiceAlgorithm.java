package org.xcolab.enums;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This interface is used for member role choice algorithm implementations to choose the highest member role depending on the context
 * (i.e. highest role in context of members-portlet or for the proposal impact tab)
 *
 * Created by kmang on 03/05/15.
 */
public abstract class MemberRoleChoiceAlgorithm {
    private static Log _log = LogFactoryUtil.getLog(MemberRoleChoiceAlgorithm.class);

    public final static MemberRoleChoiceAlgorithm proposalImpactTabAlgorithm = new MemberRoleChoiceAlgorithm() {

        @Override
        public MemberRole getHighestMemberRoleForUser(User user) throws SystemException {
            Map<Long, Boolean> roleIdMap = getRoleIdMap(user);

            for (MemberRole memberRole : getPrioritizedMemberRoles()) {
                if (Validator.isNotNull(roleIdMap.get(memberRole.getRoleId()))) {
                    return memberRole;
                }
            }

            _log.error("No member role could be found for proposal impact tab member selection algorithm for user with ID " + user.getUserId() + "! Returning Member...");
            return MemberRole.MEMBER;
        }

        @Override
        public MemberRole[] getPrioritizedMemberRoles() {
            MemberRole[] roles = {MemberRole.IMPACT_ASSESSMENT_FELLOW, MemberRole.STAFF, MemberRole.FELLOW, MemberRole.MEMBER};
            return roles;
        }

        @Override
        public String getMemberRoleDescription(MemberRole memberRole) {
            String memberDescription = getMemberRoleToMemberDescriptionMap().get(memberRole);
            if (Validator.isNotNull(memberDescription)) {
                return memberDescription;
            }

            // Return the first default description
            return memberRole.getRoleNames()[0];
        }

        @Override
        public Map<MemberRole, String> getMemberRoleToMemberDescriptionMap() {
            Map<MemberRole, String> memberRoleToMemberDescriptionMap = new HashMap<>();
            memberRoleToMemberDescriptionMap.put(MemberRole.MEMBER, "Proposal author");

            return memberRoleToMemberDescriptionMap;
        }
    };

    /**
     * Returns the role of the user with highest priority, where the priority is determined by the algorithm's implementation
     * @param user      The user in question
     * @return          A member role object of type with the highest priority
     * @throws SystemException
     */
    public abstract MemberRole getHighestMemberRoleForUser(User user) throws SystemException;

    public abstract MemberRole[] getPrioritizedMemberRoles();

    /**
     * Returns the preferred description string for a given MemberRole. Returns the first general description
     * (according to MemberRole class) of the member role if none specific is present
     *
     * @param memberRole
     * @return
     */
    public abstract String getMemberRoleDescription(MemberRole memberRole);
    public abstract Map<MemberRole, String> getMemberRoleToMemberDescriptionMap();

    // Helper methods
    public Map<Long, Boolean> getRoleIdMap(User user) throws SystemException {
        List<Role> userRoles = RoleLocalServiceUtil.getUserRoles(user.getUserId());
        Map<Long, Boolean> roleIdMap = new HashMap<>(userRoles.size());
        for (Role role : userRoles) {
            roleIdMap.put(role.getRoleId(), true);
        }

        return roleIdMap;
    }

    public String getUserRoleDescription(User user) throws SystemException {
        return getMemberRoleDescription(getHighestMemberRoleForUser(user));
    }
}
