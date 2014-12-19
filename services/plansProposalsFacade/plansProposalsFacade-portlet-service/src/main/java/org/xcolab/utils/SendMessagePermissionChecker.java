package org.xcolab.utils;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import org.xcolab.enums.MemberRole;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mente on 11/04/14.
 */
public class SendMessagePermissionChecker {
    /**
     * This map holds all the roles that are not accessible from a given <i>MemberRole</i> object. Entries in this map
     * under a <i>MemberRole</i> key indicate that the value-list contains the roles a user cannot send a message to
     */
    private Map<MemberRole, List<MemberRole>> blacklistedRolesMap;
    private User originator;

	/**
	 * IMPORTANT
	 * This flag indicates whether the permission checker is active or not.
	 * Set this flag to true when the feature should be enabled
	 */
	private static final boolean ACTIVATED = false;

    public SendMessagePermissionChecker(User origin) {
        this.originator = origin;

        // Set up blacklist
        List<MemberRole> memberBlacklist = new ArrayList<>();
        memberBlacklist.add(MemberRole.ADVISOR);
        memberBlacklist.add(MemberRole.JUDGES);

        blacklistedRolesMap = new EnumMap<>(MemberRole.class);
        blacklistedRolesMap.put(MemberRole.MEMBER, memberBlacklist);
    }

    /**
     * Returns whether the user is allowed to send a message to the passed user
     * @param user  The recipient user object
     * @return
     * @throws SystemException
     */
    public boolean canSendToUser(User user) throws SystemException {
		if (!ACTIVATED) {
			return true;
		}
        MemberRole destinationRole = getHighestUserRole(user);

        for (Role role : originator.getRoles()) {
            MemberRole currentRole = MemberRole.getMember(role.getName());
            List<MemberRole> blacklist = blacklistedRolesMap.get(currentRole);
            if (blacklist == null || !blacklist.contains(destinationRole)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get all MemberRole objects that are blacklisted for the user
     * @return  A list consisting of <i>MemberRole</i> objects
     * @throws SystemException
     */
    public List<MemberRole> getBlacklistedMemberRoles() throws SystemException {

		if (!ACTIVATED) {
			return new ArrayList<>();
		}

        // Count the number of blacklist entries for each of the users roles
        Map<MemberRole, Integer> blacklistCountMap = new EnumMap<>(MemberRole.class);
        for (Role role : originator.getRoles()) {
            MemberRole currentRole = MemberRole.getMember(role.getName());
            List<MemberRole> blacklist = blacklistedRolesMap.get(currentRole);
            if (blacklist != null) {
                for (MemberRole mr : blacklist) {
                    if (blacklistCountMap.get(mr) == null) {
                        blacklistCountMap.put(mr, 1);
                    } else {
                        blacklistCountMap.put(mr, blacklistCountMap.get(mr) + 1);
                    }
                }
            }
        }

        // Now determine the destination roles which are not accessible for every role a user has
        List<MemberRole> retList = new ArrayList<>();
        for (MemberRole role : blacklistCountMap.keySet()) {
            if (blacklistCountMap.get(role) == originator.getRoles().size()) {
                retList.add(role);
            }
        }

        return retList;
    }

    private MemberRole getHighestUserRole(User user) throws SystemException {
        List<Role> roles = user.getRoles();
        // Determine the highest role of the user (copied from {@link org.xcolab.portlets.members.MemberListItemBean})
        MemberRole currentRole;
        MemberRole role = MemberRole.MEMBER;

        for (Role r: roles) {
            final String roleString = r.getName();

            currentRole = MemberRole.getMember(roleString);
            if (currentRole != null && role != null) {
                if (currentRole.ordinal() > role.ordinal()) {
                    role = currentRole;
                }
            }
        }

        if (role == MemberRole.MODERATOR) role = MemberRole.STAFF;

        return role;
    }
}
