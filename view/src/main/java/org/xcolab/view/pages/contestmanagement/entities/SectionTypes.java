package org.xcolab.view.pages.contestmanagement.entities;

public enum SectionTypes {
    TEXT_SECTION("", "Text section"),
    //"IMAGE_SECTOR("IMAGE", "Image section"),
    DROPDOWN_MENU("DROPDOWN_MENU", "Dropdown menu"),
    INLINE_PROPOSAL_REFERENCE("PROPOSAL_LIST_TEXT_REFERENCE", "Inline proposal reference"),
    EXPLICIT_SINGLE_PROPOSAL_REFERENCE("PROPOSAL_REFERENCE", "Explicit single proposal reference"),
    EXPLICIT_MULTI_PROPOSAL_REFERENCE("PROPOSAL_LIST_REFERENCE",
            "Explicit multi proposal reference"),
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
