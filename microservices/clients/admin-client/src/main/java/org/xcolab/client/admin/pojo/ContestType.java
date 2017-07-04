package org.xcolab.client.admin.pojo;

import org.xcolab.client.admin.attributes.contest.ContestTypeAttributeKey;
import org.xcolab.util.attributes.AttributeGetter;
import org.xcolab.util.attributes.i18n.LocalizableAttributeGetter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.xcolab.util.attributes.i18n.LocalizableAttributeGetter.*;

public class ContestType implements Serializable {

    private final long id;
    private final String locale;

    private final Map<AttributeGetter, Object> attributeCache = new HashMap<>();

    public ContestType(long id) {
        this.id = id;
        this.locale = null;
    }

    public ContestType(long id, String locale) {
        this.id = id;
        this.locale = locale;
    }

    public ContestType withLocale(String locale) {
        return new ContestType(this.id, locale);
    }

    public long getId() {
        return this.id;
    }

    public String getLocale() {
        return locale;
    }

    private <T> T getAttribute(AttributeGetter<T> getter) {
        //noinspection unchecked
        return (T) attributeCache.computeIfAbsent(getter, key -> {
            if (getter instanceof LocalizableAttributeGetter) {
                return localizable(getter).get(locale, id);
            }
            return getter.get(id);
        });
    }

    public String getContestName() {
        return getAttribute(ContestTypeAttributeKey.CONTEST_NAME);
    }

    public String getContestNameLowercase() {
        return getAttribute(ContestTypeAttributeKey.CONTEST_NAME).toLowerCase();
    }

    public String getContestNamePlural() {
        return getAttribute(ContestTypeAttributeKey.CONTEST_NAME_PLURAL);
    }

    public String getContestNamePluralLowercase() {
        return getAttribute(ContestTypeAttributeKey.CONTEST_NAME_PLURAL).toLowerCase();
    }

    public String getProposalName() {
        return getAttribute(ContestTypeAttributeKey.PROPOSAL_NAME);
    }

    public String getProposalNameLowercase() {
        return getAttribute(ContestTypeAttributeKey.PROPOSAL_NAME).toLowerCase();
    }

    public String getProposalNamePlural() {
        return getAttribute(ContestTypeAttributeKey.PROPOSAL_NAME_PLURAL);
    }

    public String getProposalNamePluralLowercase() {
        return getAttribute(ContestTypeAttributeKey.PROPOSAL_NAME).toLowerCase();
    }

    public String getContestBaseUrl() {
        return getAttribute(ContestTypeAttributeKey.CONTEST_BASE_URL);
    }

    public String getFriendlyUrlStringContests() {
        return getAttribute(ContestTypeAttributeKey.FRIENDLY_URL_STRING_CONTESTS);
    }

    public String getFriendlyUrlStringProposal() {
        return getAttribute(ContestTypeAttributeKey.FRIENDLY_URL_STRING_PROPOSAL);
    }

    public String getMenuItemName() {
        return getAttribute(ContestTypeAttributeKey.MENU_ITEM_NAME);
    }

    public Boolean getHasDiscussion() {
        return getAttribute(ContestTypeAttributeKey.DISCUSSION_IS_ACTIVE);
    }

    public boolean isSuggestionsActive() {
        return getAttribute(ContestTypeAttributeKey.SUGGESTIONS_IS_ACTIVE);
    }

    public Long getSuggestionContestId() {
        return getAttribute(ContestTypeAttributeKey.SUGGESTIONS_CONTEST_ID);
    }

    public String getRulesPageName() {
        return getAttribute(ContestTypeAttributeKey.RULES_NAME);
    }

    public String getRulesPageUrl() {
        return getAttribute(ContestTypeAttributeKey.RULES_URL);
    }

    public boolean isShowProposalSummary() {
        return getAttribute(ContestTypeAttributeKey.PROPOSALS_SHOW_SUMMARY);
    }

    public String getPitchName() {
        return getAttribute(ContestTypeAttributeKey.PROPOSALS_PITCH_NAME);
    }

    public Boolean getShowTeamField() {
        return getAttribute(ContestTypeAttributeKey.PROPOSALS_SHOW_TEAM_FIELD);
    }

    public Boolean getShowResourceLinks() {
        return getAttribute(ContestTypeAttributeKey.RESOURCES_SHOW_LINK);
    }

    public String getLabelName() {
        return String.format("%d - %s with %s",
                this.getId(), this.getContestName(), this.getProposalNamePlural());
    }

    public String getIdentifier() {
        return "ContestType#" + id;
    }

    public String getCreationPrompt() {
        return getAttribute(ContestTypeAttributeKey.PROPOSALS_CREATION_PROMPT);
    }

    public boolean isActive() {
        return getAttribute(ContestTypeAttributeKey.IS_ACTIVE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContestType)) {
            return false;
        }
        ContestType that = (ContestType) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
