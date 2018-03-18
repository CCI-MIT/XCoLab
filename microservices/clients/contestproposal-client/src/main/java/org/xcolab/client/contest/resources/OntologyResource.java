package org.xcolab.client.contest.resources;

import org.xcolab.commons.http.client.CoLabService;
import org.xcolab.commons.http.client.enums.ResourceEnum;

public enum OntologyResource implements ResourceEnum {
    FOCUS_AREA("focusAreas"),
    FOCUS_AREA_ONTOLOGY_TERM("focusAreaOntologyTerms"),
    ONTOLOGY_TERM("ontologyTerms"),
    ONTOLOGY_SPACE("ontologySpaces");

    private final String resourceEnum;

    OntologyResource(String resourceEnum) {
        this.resourceEnum = resourceEnum;
    }

    @Override
    public String getResourceName() {
        return resourceEnum;
    }

    @Override
    public CoLabService getCoLabService() {
        return CoLabService.CONTEST;
    }
}
