package org.xcolab.enums;

import com.ext.portlet.model.MemberCategory;
import com.ext.portlet.service.MemberCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang.WordUtils;

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

    public String getPrintName() throws SystemException {
        return WordUtils.capitalizeFully((getMemberCategory().getDisplayName()));
    }

    public String getImageUrl() throws SystemException {
        return getMemberCategory().getImageName();
    }

    public MemberCategory getMemberCategory() throws SystemException {
        final MemberCategory memberCategory = MemberCategoryLocalServiceUtil.fetchMemberCategory(roleId);
        if (memberCategory == null) {
            throw new SystemException(String.format("No member category with roleId %d exists", roleId));
        }
        return memberCategory;
    }

    public String[] getRoleNames() {
        return roleNames;
    }

    public long getRoleId() {
        return roleId;
    }

    public static MemberRole fromRoleId(long roleId) throws NoSuchMemberRoleException {
        for (MemberRole memberRole : MemberRole.values()) {
            if (roleId == memberRole.getRoleId() || memberRole.getOtherRoleIds().contains(roleId)) {
                return memberRole;
            }
        }
        throw new NoSuchMemberRoleException("Unknown role id given: " + roleId);
    }

    public static MemberRole fromRoleName(String roleName) throws NoSuchMemberRoleException {
        for (MemberRole memberRole : MemberRole.values()) {
            if (isStringInList(roleName, memberRole.getRoleNames())) {
                return memberRole;
            }
        }
        throw new NoSuchMemberRoleException("Unknown role name given: " + roleName);
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

    public static class NoSuchMemberRoleException extends SystemException {
        public NoSuchMemberRoleException(String message) {
            super(message);
        }
    }
}
