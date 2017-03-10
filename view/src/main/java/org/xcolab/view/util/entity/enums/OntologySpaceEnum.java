package org.xcolab.view.util.entity.enums;

public enum OntologySpaceEnum {
    WHAT(103L, 2L),
    WHERE(104L, 3L),
    WHO(102L, 1L),
    HOW(105L, 1300601L);

    public long getSpaceId() {
        return spaceId;
    }

    private long spaceId;
    private long anyOntologyTermId;

    OntologySpaceEnum(long spaceId) {
        this.spaceId = spaceId;
    }

    public long getAnyOntologyTermId() {
        return anyOntologyTermId;
    }

    OntologySpaceEnum(long spaceId, long anyOntologyTermID) {
        this.spaceId = spaceId;
        this.anyOntologyTermId = anyOntologyTermID;
    }
}
