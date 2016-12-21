package org.xcolab.portlets.users.utils;




public enum MembersListColumns {
    ACTIVITY("activities", SortType.LONG_TYPE.getValue()),
    MEMBER_CATEGORY("memberCategory", SortType.STRING_TYPE.getValue()),
    REAL_NAME("realName", SortType.STRING_TYPE.getValue()),
    MEMBER_SINCE("joinDate", SortType.STRING_TYPE.getValue());
    
    private final String field;
    private final int type;
    
    MembersListColumns(String field, int type) {
        this.field = field;
        this.type = type;
    }

    public String getField() {
        return field;
    }

    public int getType() {
        return type;
    }
}
enum SortType{
    LONG_TYPE(6),
    STRING_TYPE(3);
    private final int value;
    SortType(int i){
        this.value = i;
    }
    public int getValue(){
        return this.value;
    }
}

