package org.xcolab.client.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.xcolab.client.user.exceptions.RoleGroupNotFoundException;
import org.xcolab.client.user.permissions.SystemRole;
import org.xcolab.client.user.pojo.IRole;
import org.xcolab.client.user.pojo.IRoleGroup;
import org.xcolab.client.user.pojo.IUser;
import org.xcolab.client.user.pojo.Member;
import org.xcolab.client.user.pojo.Role;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@FeignClient("xcolab-permission-service")

public interface IPermissionClient {

    @RequestMapping(value = "/roleGroups", method = RequestMethod.POST)
    IRoleGroup create(@RequestBody IRoleGroup roleGroup) ;

    @RequestMapping(value = "/roleGroups/{roleGroupId}/roles", method = RequestMethod.GET)
     List<IRole> getRolesInGroup(@PathVariable("roleGroupId") Long roleGroupId) throws RoleGroupNotFoundException;

    @RequestMapping(value = "/roleGroups/{roleGroupId}/roles", method = RequestMethod.POST)
     String addRoleToRoleGroup(@PathVariable Long roleGroupId, @RequestParam Long roleId);

    @RequestMapping(value = "/roleGroups/{roleGroupId}/roles/{roleId}", method = RequestMethod.DELETE)
     String deleteRoleFromRoleGroup(@PathVariable("roleGroupId") Long roleGroupId,
            @PathVariable("roleId") Long roleId) throws RoleGroupNotFoundException;

    default boolean isGuest(Member member) {
        return member != null && isGuest(member.getId());
    }

    default boolean isGuest(long userId) {
        return memberHasRole(userId, SystemRole.GUEST);
    }

    default boolean isMember(long userId) {
        return memberHasRole(userId, SystemRole.MEMBER);
    }

    default boolean canAdminAll(Long userId) {
        return memberHasRole(userId, SystemRole.ADMINISTRATOR);
    }

    default boolean canAdminAll(IUser member) {
        return member != null && canAdminAll(member.getId());
    }

    default boolean canContestManager(Long userId) {
        return memberHasRole(userId, SystemRole.CONTEST_MANAGER);
    }

    default boolean canContestManager(Member member) {
        return member != null && canContestManager(member.getId());
    }

    default boolean canJudge(Long userId, Long contestId) {
        return memberHasRoleInContest(userId, contestId, SystemRole.JUDGE);
    }

    default boolean canFellow(Long userId, Long contestId) {
        return memberHasRoleInContest(userId, contestId, SystemRole.FELLOW);
    }

    default boolean canIAF(Long userId) {
        return memberHasRole(userId, SystemRole.IMPACT_ASSESSMENT_FELLOW);
    }

    default boolean canIAF(Member member) {
        return member != null && canIAF(member.getId());
    }

    default boolean canStaff(Long userId) {
        return memberHasRole(userId, SystemRole.STAFF);
    }

    default boolean hasRoleGroup(long userId, long roleGroupId) {
        try {

            final List<IRole> roles = getRolesInGroup(roleGroupId);
            for (IRole role : roles) {
                if (memberHasRole(userId, role.getId())) {
                    return true;
                }
            }
            return canAdminAll(userId);

        }catch (RoleGroupNotFoundException e){
            return false;
        }

    }



    default boolean memberHasRole(Long userId, long roleIdToTest) {
        return memberHasAnyRole(userId, Collections.singleton(roleIdToTest));
    }

    default boolean memberHasRole(Long userId, SystemRole roleToTest) {
        return memberHasAnyRole(userId, Collections.singleton(roleToTest.getRoleId()));
    }

    default boolean memberHasAnySystemRole(Long userId, Set<SystemRole> rolesToTest) {
        return memberHasAnyRole(userId, rolesToTest.stream().map(SystemRole::getRoleId)
                .collect(Collectors.toSet()));
    }

    default boolean memberHasAnyRole(Long userId, Set<Long> rolesToTest ) {
        if (userId == 0) {
            return false;
        }

        final List<Role> roles = MembersClient.getMemberRoles(userId);
        return roles.stream().map(Role::getId).anyMatch(rolesToTest::contains);
    }

    default boolean memberHasRoleInContest(Long userId, Long contestId, SystemRole roleToTest) {
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
