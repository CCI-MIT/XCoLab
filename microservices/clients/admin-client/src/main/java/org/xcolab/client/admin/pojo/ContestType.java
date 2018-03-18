package org.xcolab.client.admin.pojo;

import org.xcolab.client.admin.attributes.contest.ContestTypeAttributeKey;
import org.xcolab.commons.attributes.AttributeGetter;
import org.xcolab.commons.attributes.i18n.LocalizableAttributeGetter;
import org.xcolab.util.i18n.I18nUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.xcolab.commons.attributes.i18n.LocalizableAttributeGetter.localizable;

public class ContestType implements Serializable {

    private static final String PROPOSAL_PLACEHOLDER = "<proposal/>";
    private static final String CONTEST_PLACEHOLDER = "<contest/>";
    private static final String PROPOSALS_PLACEHOLDER = "<proposals/>";
    private static final String CONTESTS_PLACEHOLDER = "<contests/>";
    private static final String PROPOSAL_LC_PLACEHOLDER = "<proposal-lc/>";
    private static final String CONTEST_LC_PLACEHOLDER = "<contest-lc/>";
    private static final String PROPOSALS_LC_PLACEHOLDER = "<proposals-lc/>";
    private static final String CONTESTS_LC_PLACEHOLDER = "<contests-lc/>";

    private final long id;
    private final String language;

    private final Map<AttributeGetter, Object> attributeCache = new HashMap<>();

    public ContestType(long id) {
        this.id = id;
        this.language = null;
    }

    public ContestType(long id, String language) {
        this.id = id;
        this.language = language;
    }

    public ContestType withLocale(String locale) {
        return new ContestType(this.id, locale);
    }

    public long getId() {
        return this.id;
    }

    public String getLanguage() {
        return language;
    }

    private <T> T getAttribute(AttributeGetter<T> getter) {
        //noinspection unchecked
        return (T) attributeCache.computeIfAbsent(getter, key -> {
            if (getter instanceof LocalizableAttributeGetter) {
                return localizable(getter).get(language, id);
            }
            return getter.get(id);
        });
    }

    public String getContestName() {
        return getAttribute(ContestTypeAttributeKey.CONTEST_NAME);
    }

    public String getContestNameLowercase() {
        final String attribute = getAttribute(ContestTypeAttributeKey.CONTEST_NAME);
        if (I18nUtils.hasCapitalNouns(language)) {
            return attribute;
        }
        return attribute.toLowerCase();
    }

    public String getContestNamePlural() {
        return getAttribute(ContestTypeAttributeKey.CONTEST_NAME_PLURAL);
    }

    public String getContestNamePluralLowercase() {
        final String attribute = getAttribute(ContestTypeAttributeKey.CONTEST_NAME_PLURAL);
        if (I18nUtils.hasCapitalNouns(language)) {
            return attribute;
        }
        return attribute.toLowerCase();
    }

    public String getProposalName() {
        return getAttribute(ContestTypeAttributeKey.PROPOSAL_NAME);
    }

    public String getProposalNameLowercase() {
        final String attribute = getAttribute(ContestTypeAttributeKey.PROPOSAL_NAME);
        if (I18nUtils.hasCapitalNouns(language)) {
            return attribute;
        }
        return attribute.toLowerCase();
    }

    public String getProposalNamePlural() {
        return getAttribute(ContestTypeAttributeKey.PROPOSAL_NAME_PLURAL);
    }

    public String getProposalNamePluralLowercase() {
        final String attribute = getAttribute(ContestTypeAttributeKey.PROPOSAL_NAME_PLURAL);
        if (I18nUtils.hasCapitalNouns(language)) {
            return attribute;
        }
        return attribute.toLowerCase();
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

    /**
     * Formats the given String by replacing references to contest or proposal names.
     * The following substitutions are made:
     * <proposal/> -> Proposal
     * <contest/> -> Contest
     * <proposals/> -> Proposals
     * <contests/> -> Contests
     *
     * Lowercase versions of all elements are available by using the -lc suffix, e.g. <proposal-lc/>.
     * @param text The text to be formatted.
     * @return A formatted String with proposal and contest names inserted.
     */
    public String format(String text) {
        return text.replaceAll(PROPOSAL_PLACEHOLDER, getProposalName())
                .replaceAll(PROPOSALS_PLACEHOLDER, getProposalNamePlural())
                .replaceAll(CONTEST_PLACEHOLDER, getContestName())
                .replaceAll(CONTESTS_PLACEHOLDER, getContestNamePlural())
                .replaceAll(PROPOSAL_LC_PLACEHOLDER, getProposalNameLowercase())
                .replaceAll(PROPOSALS_LC_PLACEHOLDER, getProposalNamePluralLowercase())
                .replaceAll(CONTEST_LC_PLACEHOLDER, getContestNameLowercase())
                .replaceAll(CONTESTS_LC_PLACEHOLDER, getContestNamePluralLowercase());
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
