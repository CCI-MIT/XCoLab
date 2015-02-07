package org.xcolab.portlets.users.utils;

import com.liferay.portal.kernel.search.Sort;

public enum MembersListColumns {
    ACTIVITY("activities", Sort.LONG_TYPE),
    MEMBER_CATEGORY("memberCategory", Sort.STRING_TYPE),
    REAL_NAME("realName", Sort.STRING_TYPE),
    MEMBER_SINCE("joinDate", Sort.STRING_TYPE);
    
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
