package org.xcolab.enums;

/**
 * Created by Klemens Mang on 24/06/15.
 */
public enum OntologySpaceEnum {
    WHAT(103L),
    WHERE(104L),
    WHO(102L),
    HOW(105L);

    public long getSpaceId() {
        return spaceId;
    }

    private long spaceId;

    OntologySpaceEnum(long spaceId) {
        this.spaceId = spaceId;
    }
}
