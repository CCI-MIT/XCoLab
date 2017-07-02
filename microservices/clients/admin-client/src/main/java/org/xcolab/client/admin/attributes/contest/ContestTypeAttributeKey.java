package org.xcolab.client.admin.attributes.contest;

import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.i18n.LocalizableAttributeGetter;
import org.xcolab.util.attributes.wrappers.TransformedAttribute;

public final class ContestTypeAttributeKey {

    public static final LocalizableAttributeGetter<String> CONTEST_NAME =
            ContestTypeAttributes.newLocalizedStringAttribute("CONTEST_NAME")
                    .buildLocalizable();

    public static final LocalizableAttributeGetter<String> PROPOSAL_NAME =
            ContestTypeAttributes.newLocalizedStringAttribute("PROPOSAL_NAME")
                    .buildLocalizable();


    public static final LocalizableAttributeGetter<String> CONTEST_NAME_PLURAL =
            ContestTypeAttributes.newLocalizedStringAttribute("CONTEST_NAME_PLURAL")
                    .defaultValue(TransformedAttribute.of(CONTEST_NAME, s -> s + "s"))
                    .buildLocalizable();

    public static final LocalizableAttributeGetter<String> PROPOSAL_NAME_PLURAL =
            ContestTypeAttributes.newLocalizedStringAttribute("PROPOSAL_NAME_PLURAL")
                    .defaultValue(TransformedAttribute.of(PROPOSAL_NAME, s -> s + "s"))
                    .buildLocalizable();

    public static final AttributeGetter<String> CONTEST_BASE_URL =
            ContestTypeAttributes.newStringAttribute("CONTEST_BASE_URL")
                    .defaultValue(TransformedAttribute.of(CONTEST_NAME_PLURAL,
                            s -> "/" + s.toLowerCase()))
                    .build();

    public static final AttributeGetter<String> FRIENDLY_URL_STRING_CONTESTS =
            ContestTypeAttributes.newStringAttribute("FRIENDLY_URL_STRING_CONTESTS")
                    .defaultValue(TransformedAttribute.of(CONTEST_NAME_PLURAL, String::toLowerCase))
                    .build();

    public static final AttributeGetter<String> FRIENDLY_URL_STRING_PROPOSAL =
            ContestTypeAttributes.newStringAttribute("FRIENDLY_URL_STRING_PROPOSAL")
                    .defaultValue(TransformedAttribute.of(PROPOSAL_NAME, String::toLowerCase))
                    .build();

    public static final LocalizableAttributeGetter<String> MENU_ITEM_NAME =
            ContestTypeAttributes.newLocalizedStringAttribute("MENU_ITEM_NAME")
                    .defaultValue(CONTEST_NAME_PLURAL)
                    .buildLocalizable();

    public static final LocalizableAttributeGetter<String> RULES_NAME =
            ContestTypeAttributes.newLocalizedStringAttribute("RULES_NAME")
                    .defaultValue(TransformedAttribute.of(CONTEST_NAME, s -> s + " rules"))
                    .buildLocalizable();

    public static final AttributeGetter<String> RULES_URL =
            ContestTypeAttributes.newStringAttribute("RULES_URL")
                    .defaultValue(
                            TransformedAttribute.of(CONTEST_NAME, s -> "/wiki/" + s + "+rules"))
                    .build();

    public static final AttributeGetter<Boolean> DISCUSSION_IS_ACTIVE =
            ContestTypeAttributes.newBooleanAttribute("DISCUSSION_IS_ACTIVE")
                    .defaultValue(true)
                    .build();

    public static final AttributeGetter<Boolean> SUGGESTIONS_IS_ACTIVE =
            ContestTypeAttributes.newBooleanAttribute("SUGGESTIONS_IS_ACTIVE")
                    .defaultValue(false)
                    .build();

    public static final AttributeGetter<Long> SUGGESTIONS_CONTEST_ID =
            ContestTypeAttributes.newLongAttribute("SUGGESTIONS_CONTEST_ID")
                    .build();

    public static final AttributeGetter<Boolean> RESOURCES_SHOW_LINK =
            ContestTypeAttributes.newBooleanAttribute("RESOURCES_SHOW_LINK")
                    .defaultValue(true)
                    .build();

    public static final AttributeGetter<Boolean> PROPOSALS_SHOW_SUMMARY =
            ContestTypeAttributes.newBooleanAttribute("PROPOSALS_SHOW_SUMMARY")
                    .defaultValue(true)
                    .build();

    public static final AttributeGetter<Boolean> PROPOSALS_SHOW_TEAM_FIELD =
            ContestTypeAttributes.newBooleanAttribute("PROPOSALS_SHOW_TEAM_FIELD")
                    .defaultValue(true)
                    .build();

    public static final LocalizableAttributeGetter<String> PROPOSALS_CREATION_PROMPT =
            ContestTypeAttributes.newLocalizedStringAttribute("PROPOSALS_CREATION_PROMPT")
                    .defaultValue(TransformedAttribute.of(PROPOSAL_NAME, s -> "CREATE " + s))
                    .buildLocalizable();

    public static final LocalizableAttributeGetter<String> PROPOSALS_PITCH_NAME =
            ContestTypeAttributes.newLocalizedStringAttribute("PROPOSALS_PITCH_NAME")
                    .defaultValue("Pitch")
                    .buildLocalizable();

    public static final AttributeGetter<Boolean> IS_ACTIVE =
            ContestTypeAttributes.newBooleanAttribute("IS_ACTIVE")
                    .defaultValue(true)
                    .build();

}
