package org.xcolab.client.admin.pojo;

public class MockContestType extends ContestType {

    public MockContestType(long id) {
        super(id);
    }

    public MockContestType(long id, String lang) {
        super(id, lang);
    }

    @Override
    public String getContestName() {
        return "Contest";
    }

    @Override
    public String getContestNamePlural() {
        return "Contests";
    }

    @Override
    public String getProposalName() {
        return "Proposal";
    }

    @Override
    public String getProposalNameLowercase() {
        return "proposal";
    }

    @Override
    public String getProposalNamePlural() {
        return "Proposals";
    }

    @Override
    public String getContestNameLowercase() {
        return "contest";
    }

    @Override
    public String getContestNamePluralLowercase() {
        return "contests";
    }

    @Override
    public String getProposalNamePluralLowercase() {
        return "proposals";
    }

    @Override
    public String getContestBaseUrl() {
        return "/contests";
    }

    @Override
    public String getFriendlyUrlStringContests() {
        return "contests";
    }

    @Override
    public String getFriendlyUrlStringProposal() {
        return "proposal";
    }

    @Override
    public String getMenuItemName() {
        return "Contests";
    }

    @Override
    public Boolean getHasDiscussion() {
        return true;
    }

    @Override
    public boolean isSuggestionsActive() {
        return false;
    }

    @Override
    public Long getSuggestionContestId() {
        return null;
    }

    @Override
    public String getRulesPageName() {
        return "Contest rules";
    }

    @Override
    public String getRulesPageUrl() {
        return "/wiki/Contest+rules";
    }

    @Override
    public boolean isShowProposalSummary() {
        return true;
    }

    @Override
    public String getPitchName() {
        return "Pitch";
    }

    @Override
    public Boolean getShowTeamField() {
        return true;
    }

    @Override
    public Boolean getShowResourceLinks() {
        return true;
    }

    @Override
    public String getLabelName() {
        return String.format("%d - %s with %s",
                this.getId(), this.getContestName(), this.getProposalNamePlural());
    }

    @Override
    public String getIdentifier() {
        return "ContestType#" + getId();
    }

    @Override
    public String getCreationPrompt() {
        return "CREATE proposal";
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
