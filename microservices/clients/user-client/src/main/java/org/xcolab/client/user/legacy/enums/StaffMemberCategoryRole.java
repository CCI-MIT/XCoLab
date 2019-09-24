package org.xcolab.client.user.legacy.enums;

import org.xcolab.client.user.permissions.SystemRole;

public enum StaffMemberCategoryRole {

    ADVISOR(9L, SystemRole.ADVISOR, true),
    JUDGE(10L, SystemRole.JUDGE, true),
    FELLOW(14L, SystemRole.FELLOW, true),
    IMPACT_FELLOW(15L, SystemRole.IMPACT_ASSESSMENT_FELLOW, true),
    CATALYST(16L, SystemRole.CATALYST, false),
    EXPERT_ADVISORY(3L, null, false),
    EXPERT_COUNCIL(4L, null, false),
    TEAM(5L, null, false),
    //MemberRole.STAFF has dummy entries, the staff table has all staff
    VENDORS(6L, null, false),
    ADVISORS_STAFF(7L, null, false),
    ALUMNI(8L, null, false),
    RESEARCH_COLAB(17L, null, false);

    private final Long categoryId;
    private final SystemRole memberRole;
    private final Boolean groupByYear;

    StaffMemberCategoryRole(Long categoryId, SystemRole memberRole, Boolean groupByYear) {
        this.categoryId = categoryId;
        this.memberRole = memberRole;
        this.groupByYear = groupByYear;
    }

    public static StaffMemberCategoryRole fromCategoryId(long categoryId) throws NoSuchCategoryRoleException {
        for (StaffMemberCategoryRole categoryRole : StaffMemberCategoryRole.values()) {
            if (categoryId == categoryRole.getCategoryId()) {
                return categoryRole;
            }
        }
        throw new NoSuchCategoryRoleException("Unknown role id given: " + categoryId);
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public Boolean getGroupByYear() {
        return groupByYear;
    }

    public SystemRole getRole() {
        return this.memberRole;
    }

    public static class NoSuchCategoryRoleException extends Exception {

        public NoSuchCategoryRoleException(String message) {
            super(message);
        }
    }
}
