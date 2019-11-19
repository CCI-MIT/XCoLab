package org.xcolab.view.pages.contestmanagement.entities;

public enum SectionTypes {
    TEXT_SECTION("", "Text section"),
    //"IMAGE_SECTOR("IMAGE", "Image section"),
    DROPDOWN_MENU("DROPDOWN_MENU", "Dropdown menu"),
    RADIO_TABLE("RADIO_TABLE", "Radio table"),
    CHECKBOX_OPTION("CHECKBOX_OPTION","Checkbox option"),
    SECTION_HEADER("SECTION_HEADER","Section Header"),
    INLINE_PROPOSAL_REFERENCE("PROPOSAL_LIST_TEXT_REFERENCE", "Inline proposal reference"),
    EXPLICIT_SINGLE_PROPOSAL_REFERENCE("PROPOSAL_REFERENCE", "Explicit single proposal reference"),
    EXPLICIT_MULTI_PROPOSAL_REFERENCE("PROPOSAL_LIST_REFERENCE",
            "Explicit multi proposal reference"),
    ONTOLOGY_REFERENCE("ONTOLOGY_REFERENCE", "Ontology reference");

    private final String displayName;
    private final String sectionType;

    SectionTypes(String sectionType, String displayName) {
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
