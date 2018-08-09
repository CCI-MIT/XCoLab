package org.xcolab.client.members.legacy.utils;

import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.members.pojo.Role;
import org.xcolab.client.members.pojo.Member;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mente on 11/04/14.
 */
public class SendMessagePermissionChecker {
    /**
     * This map holds all the roles that are not accessible from a given <i>MemberRole</i> object.
     * Entries in this map under a <i>MemberRole</i> key indicate that the value-list contains the
     * roles a user cannot send a message to
     */
    private final Map<MemberRole, List<MemberRole>> blacklistedRolesMap;
    private final Member originator;

    /**
     * IMPORTANT This flag indicates whether the permission checker is active or not. Set this flag
     * to true when the feature should be enabled
     */
    //TODO COLAB-2596: when will we activate this feature? Do we still need this?
    private static final boolean ACTIVATED = false;

    public SendMessagePermissionChecker(Member origin) {
        this.originator = origin;

        // Set up blacklist
        List<MemberRole> memberBlacklist = new ArrayList<>();
        memberBlacklist.add(MemberRole.ADVISOR);
        memberBlacklist.add(MemberRole.JUDGE);

        blacklistedRolesMap = new EnumMap<>(MemberRole.class);
        blacklistedRolesMap.put(MemberRole.MEMBER, memberBlacklist);
    }

    /**
     * Returns whether the user is allowed to send a message to the passed user
     *
     * @param user The recipient user object
     */
    public boolean canSendToUser(Member user) throws MemberRole.NoSuchMemberRoleException {
        if (!ACTIVATED) {
            return true;
        }
        MemberRole destinationRole = MemberRole.getHighestRole(user.getRoles());

        for (Role role : originator.getRoles()) {
            MemberRole currentRole = MemberRole.fromRoleName(role.getName());
            List<MemberRole> blacklist = blacklistedRolesMap.get(currentRole);
            if (blacklist == null || !blacklist.contains(destinationRole)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get all MemberRole objects that are blacklisted for the user
     *
     * @return A list consisting of <i>MemberRole</i> objects
     */
    public List<MemberRole> getBlacklistedMemberRoles() {

        if (!ACTIVATED) {
            return new ArrayList<>();
        }

        // Count the number of blacklist entries for each of the users roles
        Map<MemberRole, Integer> blacklistCountMap = new EnumMap<>(MemberRole.class);
        for (Role role : originator.getRoles()) {
            MemberRole currentRole = MemberRole.fromRoleName(role.getName());
            List<MemberRole> blacklist = blacklistedRolesMap.get(currentRole);
            if (blacklist != null) {
                for (MemberRole mr : blacklist) {
                    blacklistCountMap.merge(mr, 1, (a, b) -> a + b);
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
}
