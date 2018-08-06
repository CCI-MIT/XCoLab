package org.xcolab.client.members;

import org.xcolab.client.members.legacy.enums.MemberRole;
import org.xcolab.client.members.pojo.Member;
import org.xcolab.client.members.pojo.Role;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.List;

public final class PermissionsClient {

    private static final RestResource1<Object, Long> roleGroupResource =
            new RestResource1<>(UserResource.ROLE_GROUP, new TypeProvider<>(null, null));

    private static final RestResource2L<Object, Role> roleGroupRoleResource =
            new RestResource2L<>(roleGroupResource, "roles", Role.TYPES);

    private PermissionsClient() {
    }

    public static boolean isGuest(Member member) {
        return member != null && isGuest(member.getId());
    }

    public static boolean isGuest(long userId) {
        return memberHasRole(userId, MemberRole.GUEST.getRoleId());
    }

    public static boolean isMember(long userId) {
        return memberHasRole(userId, MemberRole.MEMBER.getRoleId());
    }

    public static boolean canAdminAll(Long userId) {
        return memberHasRole(userId, MemberRole.ADMINISTRATOR.getRoleId());
    }

    public static boolean canAdminAll(Member member) {
        return member != null && canAdminAll(member.getId());
    }

    public static boolean canJudge(Long userId, Long contestId) {
        return memberHasRoleInContest(userId, contestId, MemberRole.JUDGE);
    }

    public static boolean canFellow(Long userId, Long contestId) {
        return memberHasRoleInContest(userId, contestId, MemberRole.FELLOW);
    }

    public static boolean canIAF(Long userId) {
        return memberHasRole(userId, MemberRole.IMPACT_ASSESSMENT_FELLOW.getRoleId());
    }

    public static boolean canStaff(Long userId) {
        return memberHasRole(userId, MemberRole.STAFF.getRoleId());
    }

    public static boolean hasRoleGroup(long userId, long roleGroupId) {
        final List<Role> roles = getRoleGroupRoles(roleGroupId);
        for (Role role : roles) {
            if (memberHasRole(userId, role.getId())) {
                return true;
            }
        }
        return canAdminAll(userId);
    }

    private static List<Role> getRoleGroupRoles(long roleGroupId) {
        //TODO COLAB-2594: think about structure
        return roleGroupRoleResource.resolveParentId(roleGroupResource.id(roleGroupId))
                .list()
                .withCache(CacheName.CONFIGURATION)
                .execute();
    }

    public static boolean memberHasRole(Long userId, long roleIdToTest) {
        if (userId == 0) {
            return false;
        }
        List<Role> roles = MembersClient.getMemberRoles(userId);
        if (roles != null && !roles.isEmpty()) {
            for (Role role : roles) {
                if (role.getId() == roleIdToTest) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean memberHasRoleInContest(Long userId, Long contestId, MemberRole roleToTest) {
        if (userId == 0) {
            return false;
        }
        List<Role> roles = MembersClient.getMemberRolesInContest(userId, contestId);
        if (roles != null && !roles.isEmpty()) {
            for (Role role : roles) {
                if (role.getId() == roleToTest.getRoleId()) {
                    return true;
                }
            }
        }
        return false;
    }
}
