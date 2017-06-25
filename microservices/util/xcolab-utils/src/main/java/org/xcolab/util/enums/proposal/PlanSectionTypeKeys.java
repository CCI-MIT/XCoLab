package org.xcolab.util.enums.proposal;

import java.util.EnumSet;

public enum PlanSectionTypeKeys {
    TEXT,
    DROPDOWN_MENU,
    CHECKBOX_OPTION,
    SECTION_HEADER,
    ONTOLOGY_REFERENCE,
    PROPOSAL_REFERENCE,
    PROPOSAL_LIST_REFERENCE,
    PROPOSAL_LIST_TEXT_REFERENCE,
    IMAGE;

    public static final EnumSet<PlanSectionTypeKeys> PROPOSAL_PICKER_SECTION_TYPES = EnumSet
            .of(PlanSectionTypeKeys.PROPOSAL_REFERENCE,
                    PlanSectionTypeKeys.PROPOSAL_LIST_REFERENCE,
                    PlanSectionTypeKeys.PROPOSAL_LIST_TEXT_REFERENCE);
}
