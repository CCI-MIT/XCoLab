package org.xcolab.client.user;

import org.xcolab.client.user.permissions.SystemRole;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.user.pojo.Role;
import org.xcolab.util.http.caching.CacheName;
import org.xcolab.util.http.client.RestResource1;
import org.xcolab.util.http.client.RestResource2L;
import org.xcolab.util.http.client.types.TypeProvider;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
        return memberHasRole(userId, SystemRole.GUEST);
    }

    public static boolean isMember(long userId) {
        return memberHasRole(userId, SystemRole.MEMBER);
    }

    public static boolean canAdminAll(Long userId) {
        return memberHasRole(userId, SystemRole.ADMINISTRATOR);
    }

    public static boolean canAdminAll(Member member) {
        return member != null && canAdminAll(member.getId());
    }

    public static boolean canContestManager(Long userId) {
        return memberHasRole(userId, SystemRole.CONTEST_MANAGER);
    }

    public static boolean canContestManager(Member member) {
        return member != null && canContestManager(member.getId());
    }

    public static boolean canJudge(Long userId, Long contestId) {
        return memberHasRoleInContest(userId, contestId, SystemRole.JUDGE);
    }

    public static boolean canFellow(Long userId, Long contestId) {
        return memberHasRoleInContest(userId, contestId, SystemRole.FELLOW);
    }

    public static boolean canIAF(Long userId) {
        return memberHasRole(userId, SystemRole.IMPACT_ASSESSMENT_FELLOW);
    }

    public static boolean canIAF(Member member) {
        return member != null && canIAF(member.getId());
    }

    public static boolean canStaff(Long userId) {
        return memberHasRole(userId, SystemRole.STAFF);
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
        return memberHasAnyRole(userId, Collections.singleton(roleIdToTest));
    }

    public static boolean memberHasRole(Long userId, SystemRole roleToTest) {
        return memberHasAnyRole(userId, Collections.singleton(roleToTest.getRoleId()));
    }

    public static boolean memberHasAnySystemRole(Long userId, Set<SystemRole> rolesToTest) {
        return memberHasAnyRole(userId, rolesToTest.stream().map(SystemRole::getRoleId)
                .collect(Collectors.toSet()));
    }

    public static boolean memberHasAnyRole(Long userId, Set<Long> rolesToTest ) {
        if (userId == 0) {
            return false;
        }

        final List<Role> roles = MembersClient.getMemberRoles(userId);
        return roles.stream().map(Role::getId).anyMatch(rolesToTest::contains);
    }

    private static boolean memberHasRoleInContest(Long userId, Long contestId, SystemRole roleToTest) {
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
