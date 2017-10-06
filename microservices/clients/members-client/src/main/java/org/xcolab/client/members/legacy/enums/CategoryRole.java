package org.xcolab.client.members.legacy.enums;

public enum CategoryRole {

    ADVISOR(9L, MemberRole.ADVISOR, true),
    JUDGE(10L, MemberRole.JUDGE, true),
    FELLOW(14L, MemberRole.FELLOW, true),
    IMPACT_FELLOW(15L, MemberRole.IMPACT_ASSESSMENT_FELLOW, true),
    CATALYST(16L, MemberRole.CATALYST, false),
    EXPERT_ADVISORY(3L, null, false),
    EXPERT_COUNCIL(4L, null, false),
    TEAM(5L, null, false),
    //MemberRole.STAFF has dummy entries, the staff table has all staff
    VENDORS(6L, null, false),
    ADVISORS_STAFF(7L, null, false),
    ALUMNI(8L, null, false),
    RESEARCH_COLAB(17L, null, false);

    private final Long categoryId;
    private final MemberRole memberRole;
    private final Boolean groupByYear;

    CategoryRole(Long categoryId, MemberRole memberRole, Boolean groupByYear) {
        this.categoryId = categoryId;
        this.memberRole = memberRole;
        this.groupByYear = groupByYear;
    }

    public static CategoryRole fromCategoryId(long categoryId) throws NoSuchCategoryRoleException {
        for (CategoryRole categoryRole : CategoryRole.values()) {
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

    public MemberRole getRole() {
        return this.memberRole;
    }

    public static class NoSuchCategoryRoleException extends Exception {

        public NoSuchCategoryRoleException(String message) {
            super(message);
        }
    }
}
