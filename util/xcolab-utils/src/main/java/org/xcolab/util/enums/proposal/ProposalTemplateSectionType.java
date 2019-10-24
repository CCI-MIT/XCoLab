package org.xcolab.util.enums.proposal;

import java.util.EnumSet;

public enum ProposalTemplateSectionType {
    TEXT,
    DROPDOWN_MENU,
    CHECKBOX_OPTION,
    RADIO_TABLE,
    SECTION_HEADER,
    ONTOLOGY_REFERENCE,
    PROPOSAL_REFERENCE,
    PROPOSAL_LIST_REFERENCE,
    PROPOSAL_LIST_TEXT_REFERENCE,
    IMAGE;

    public static final EnumSet<ProposalTemplateSectionType> PROPOSAL_PICKER_SECTION_TYPES = EnumSet
            .of(ProposalTemplateSectionType.PROPOSAL_REFERENCE,
                    ProposalTemplateSectionType.PROPOSAL_LIST_REFERENCE,
                    ProposalTemplateSectionType.PROPOSAL_LIST_TEXT_REFERENCE);
}
