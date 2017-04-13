package org.xcolab.client.members.legacy.enums;

public enum CategoryRole {

    ADVISOR(9l,MemberRole.ADVISOR,true),
    JUDGE(10l, MemberRole.JUDGE,true),
    FELLOW(14l, MemberRole.FELLOW,true),
    IMPACT_FELLOW(15l,MemberRole.IMPACT_ASSESSMENT_FELLOW,true),
    CATALYST(16l,MemberRole.CATALYST,false),
    EXPERT_ADVISORY(3l,null,false),
    EXPERT_COUNCIL(4l,null,false),
    TEAM(5l,null,false),//MemberRole.STAFF has dummy entries, the staff table has all staff
    VENDORS(6l,null,false),
    ADVISORS_STAFF(7l,null,false),
    ALUMNI(8l,null,false),
    RESARCH_COLAB(17l,null,false);

    private Long categoryId;
    private MemberRole memberRole;
    private Boolean groupByYear;

    CategoryRole(Long categoryId, MemberRole memberRole,Boolean groupByYear){
        this.categoryId = categoryId;
        this.memberRole = memberRole;
        this.groupByYear = groupByYear;
    }

    public Boolean getGroupByYear(){
        return groupByYear;
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
