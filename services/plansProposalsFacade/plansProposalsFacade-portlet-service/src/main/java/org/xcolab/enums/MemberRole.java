package org.xcolab.enums;

import com.ext.portlet.NoSuchMemberCategoryException;
import com.ext.portlet.model.MemberCategory;
import com.ext.portlet.service.MemberCategoryLocalServiceUtil;
import com.liferay.portal.kernel.exception.SystemException;
import org.apache.commons.lang.WordUtils;

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
    STAFF(31704L, "Staff", "Moderator", "Administrator"),
    MODERATOR(31213L, "Staff"),
    CATALYST(1430078L, "Catalyst"),
    CONTEST_MANAGER(1950101L, "Contest Manager"),
    IMPACT_ASSESSMENT_FELLOW(1975251L, "Impact Assessment Fellow");

    private final String[] roleNames;
    private final long roleId;

    MemberRole(Long roleId, String... roleNames) {
        this.roleId = roleId;
        this.roleNames = roleNames;
    } 

    public String getPrintName() throws SystemException {
        return WordUtils.capitalizeFully((getMemberCategory().getDisplayName()));
    }

    public String getImageUrl() throws SystemException {
        return getMemberCategory().getImageName();
    }

    public MemberCategory getMemberCategory() throws SystemException {
        try {
            return MemberCategoryLocalServiceUtil.fetchMemberCategory(roleId);
        } catch (SystemException e) {
            throw new SystemException("Could not get member category for MemberRole enum: roleIds might be out of sync", e);
        }
    }

    public String[] getRoleNames() {
        return roleNames;
    }

    public long getRoleId() {
        return roleId;
    }

    public static MemberRole fromRoleId(long roleId) throws SystemException {
        for (MemberRole memberRole : MemberRole.values()) {
            if (roleId == memberRole.getRoleId()) {
                return memberRole;
            }
        }
        throw new SystemException("Unknown role id given: " + roleId);
    }

    public static MemberRole fromRoleName(String roleName) throws SystemException {
        for (MemberRole memberRole : MemberRole.values()) {
            if (isStringInList(roleName, memberRole.getRoleNames())) {
                return memberRole;
            }
        }
        throw new SystemException("Unknown role name given: " + roleName);
    }

    private static boolean isStringInList(String name, String[] names) {
        for (String n : names) {
            if (name.equalsIgnoreCase(n)) {
                return true;
            }
        }
        return false;
    }
}
