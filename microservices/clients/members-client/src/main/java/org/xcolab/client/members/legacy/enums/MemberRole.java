package org.xcolab.client.members.legacy.enums;

import org.apache.commons.text.WordUtils;

import org.xcolab.client.members.MembersClient;
import org.xcolab.client.members.pojo.MemberCategory;
import org.xcolab.client.members.pojo.Role_;

import java.util.Arrays;
import java.util.List;

public enum MemberRole {
    /**
     * Important:
     * Whenever these roles are modified (which should never happen) these Ids should be updated as well
     */
    DEFAULT(0L, "Default"),
	ALL(0L, "All"),
    GUEST(10119L, "Guest"),
    MEMBER(10122L, "User", "Member"),
    FELLOW(193261L, "Fellow"),
    ADVISOR(193260L, "Advisor"),
    EXPERT(44201L, "Experts"),
    JUDGE(1251483L, "Judges", "Judge"),
    STAFF(31704L, new Long[]{10118L}, "Staff", "Moderator", "Administrator"),
    ADMINISTRATOR(10118L, "Administrator"),
    MODERATOR(31213L, "Staff"),
    CATALYST(1430078L, "Catalyst"),
    CONTEST_MANAGER(1958405L, "Contest Manager"),
    IMPACT_ASSESSMENT_FELLOW(1975251L, "Impact Assessment Fellow");

    private final String[] roleNames;
    private final long roleId;
    private final List<Long> otherRoleIds;

    MemberRole(Long roleId, String... roleNames) {
        this(roleId, new Long[0], roleNames);
    }

    MemberRole(Long roleId, Long[] otherRoleIds, String... roleNames) {
        this.roleNames = roleNames;
        this.roleId = roleId;
        this.otherRoleIds = Arrays.asList(otherRoleIds);
    }

    public String getPrintName() throws NoSuchMemberRoleException {
        return WordUtils.capitalize((getMemberCategory().getDisplayName()));
    }

    public String getImageUrl() throws NoSuchMemberRoleException {
        return getMemberCategory().getImageName();
    }

    public MemberCategory getMemberCategory() {
        final MemberCategory memberCategory = MembersClient.getMemberCategory(roleId);
        if (memberCategory == null) {
            throw new IllegalArgumentException("No member category with roleId %d exists" + roleId);
        }
        return memberCategory;
    }

    public String[] getRoleNames() {
        return roleNames.clone();
    }

    public long getRoleId() {
        return roleId;
    }

    public static MemberRole fromRoleId(long roleId) {
        for (MemberRole memberRole : MemberRole.values()) {
            if (roleId == memberRole.getRoleId() || memberRole.getOtherRoleIds().contains(roleId)) {
                return memberRole;
            }
        }
        return null;
    }

    public static MemberRole fromRoleName(String roleName)  {
        for (MemberRole memberRole : MemberRole.values()) {
            if (isStringInList(roleName, memberRole.getRoleNames())) {
                return memberRole;
            }
        }
        return null;
    }

    public static MemberRole getHighestRole(List<Role_> roles) {
        MemberRole role = MemberRole.MEMBER;

        for (Role_ r: roles) {
            MemberRole currentRole = MemberRole.fromRoleId(r.getRoleId());
            if (currentRole != null && currentRole.getMemberCategory().getShowInList()) {
                if (currentRole.getMemberCategory().getSortOrder() > role.getMemberCategory().getSortOrder()) {
                    role = currentRole;
                }
            }
        }

        return role;
    }

    private static boolean isStringInList(String name, String[] names) {
        for (String n : names) {
            if (name.equalsIgnoreCase(n)) {
                return true;
            }
        }
        return false;
    }

    public List<Long> getOtherRoleIds() {
        return otherRoleIds;
    }

    public static class NoSuchMemberRoleException extends Exception {
        public NoSuchMemberRoleException(String message) {
            super(message);
        }
    }
}
