package org.xcolab.client.members.legacy.enums;

public enum CategoryRole {

    ADVISOR(9l,MemberRole.ADVISOR),
    JUDGE(10l, MemberRole.JUDGE),
    FELLOW(14l, MemberRole.FELLOW),
    IMPACT_FELLOW(15l,MemberRole.IMPACT_ASSESSMENT_FELLOW),
    EXPERT_ADVISORY(3l,null),
    EXPERT_COUNCIL(4l,null),
    TEAM(5l,MemberRole.STAFF),
    VENDORS(6l,null),
    ADVISORS_STAFF(7l,null),
    ALUMI(8l,null);

    private Long categoryId;
    private MemberRole memberRole;

    CategoryRole(Long categoryId, MemberRole memberRole){
        this.categoryId = categoryId;
        this.memberRole = memberRole;
    }

    public MemberRole getRole() {
        return this.memberRole;
    }

    public Long getCategoryId() {
        return this.categoryId;
    }

    public static CategoryRole fromCategoryId(long categoryId) throws NoSuchCategoryRoleException {
        for (CategoryRole categoryRole : CategoryRole.values()) {
            if (categoryId == categoryRole.getCategoryId()) {
                return categoryRole;
            }
        }
        throw new NoSuchCategoryRoleException("Unknown role id given: " + categoryId);
    }
    public static class NoSuchCategoryRoleException extends Exception {
        public NoSuchCategoryRoleException(String message) {
            super(message);
        }
    }
}
