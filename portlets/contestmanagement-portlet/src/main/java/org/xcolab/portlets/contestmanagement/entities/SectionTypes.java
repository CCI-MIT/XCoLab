package org.xcolab.portlets.contestmanagement.entities;

/**
 * Created by Thomas on 2/17/2015.
 */
public enum SectionTypes {
    TEXT_SECTION("", "Text section"),
    //"IMAGE_SECTOR("IMAGE", "Image section"),
    INLINE_PROPOSAL_REFERENCE("PROPOSAL_REFERENCE", "Inline proposal reference"),
    EXPLICIT_SINGLE_PROPOSAL_REFERENCE("PROPOSAL_LIST_REFERENCE ", "Explicit single proposal reference"),
    PROPOSAL_LIST_TEXT_REFERENCE("PROPOSAL_LIST_TEXT_REFERENCE ", "Explicit multi proposal reference"),
    ONTOLOGY_REFERENCE("ONTOLOGY_REFERENCE", "Ontology reference");

    private final String displayName;
    private final String sectionType;

    private SectionTypes(String sectionType, String displayName) {
        this.sectionType = sectionType;
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getSectionType() {
        return sectionType;
    }
}
