package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractContestType {

    private Long id_;
    private String contestName;
    private String contestNamePlural;
    private String proposalName;
    private String proposalNamePlural;
    private String portletName;
    private String portletUrl;
    private String friendlyUrlStringContests;
    private String friendlyUrlStringProposal;
    private String menuItemName;
    private Boolean hasDiscussion;
    private Long suggestionContestId;
    private String rulesPageName;
    private String rulesPageUrl;
    private boolean showProposalSummary;

    public AbstractContestType() {
    }

    public AbstractContestType(AbstractContestType value) {
        this.id_ = value.id_;
        this.contestName = value.contestName;
        this.contestNamePlural = value.contestNamePlural;
        this.proposalName = value.proposalName;
        this.proposalNamePlural = value.proposalNamePlural;
        this.portletName = value.portletName;
        this.portletUrl = value.portletUrl;
        this.friendlyUrlStringContests = value.friendlyUrlStringContests;
        this.friendlyUrlStringProposal = value.friendlyUrlStringProposal;
        this.menuItemName = value.menuItemName;
        this.hasDiscussion = value.hasDiscussion;
        this.suggestionContestId = value.suggestionContestId;
        this.rulesPageName = value.rulesPageName;
        this.rulesPageUrl = value.rulesPageUrl;
        this.showProposalSummary = value.showProposalSummary;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getContestName() {
        return this.contestName;
    }

    @JsonIgnore
    public String getContestNameLowercase() {
        return this.contestName.toLowerCase();
    }

    public void setContestName(String contestName) {
        this.contestName = contestName;
    }

    public String getContestNamePlural() {
        return this.contestNamePlural;
    }

    @JsonIgnore
    public String getContestNamePluralLowercase() {
        return this.contestNamePlural.toLowerCase();
    }

    public void setContestNamePlural(String contestNamePlural) {
        this.contestNamePlural = contestNamePlural;
    }

    public String getProposalName() {
        return this.proposalName;
    }

    @JsonIgnore
    public String getProposalNameLowercase() {
        return this.proposalName.toLowerCase();
    }

    public void setProposalName(String proposalName) {
        this.proposalName = proposalName;
    }

    public String getProposalNamePlural() {
        return this.proposalNamePlural;
    }

    @JsonIgnore
    public String getProposalNamePluralLowercase() {
        return this.proposalNamePlural.toLowerCase();
    }

    public void setProposalNamePlural(String proposalNamePlural) {
        this.proposalNamePlural = proposalNamePlural;
    }

    public String getPortletName() {
        return this.portletName;
    }

    public void setPortletName(String portletName) {
        this.portletName = portletName;
    }

    public String getPortletUrl() {
        return this.portletUrl;
    }

    public void setPortletUrl(String portletUrl) {
        this.portletUrl = portletUrl;
    }

    public String getFriendlyUrlStringContests() {
        return this.friendlyUrlStringContests;
    }

    public void setFriendlyUrlStringContests(String friendlyUrlStringContests) {
        this.friendlyUrlStringContests = friendlyUrlStringContests;
    }

    public String getFriendlyUrlStringProposal() {
        return this.friendlyUrlStringProposal;
    }

    public void setFriendlyUrlStringProposal(String friendlyUrlStringProposal) {
        this.friendlyUrlStringProposal = friendlyUrlStringProposal;
    }

    public String getMenuItemName() {
        return this.menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public Boolean getHasDiscussion() {
        return this.hasDiscussion;
    }

    public void setHasDiscussion(Boolean hasDiscussion) {
        this.hasDiscussion = hasDiscussion;
    }

    public Long getSuggestionContestId() {
        return this.suggestionContestId;
    }

    public void setSuggestionContestId(Long suggestionContestId) {
        this.suggestionContestId = suggestionContestId;
    }

    public String getRulesPageName() {
        return this.rulesPageName;
    }

    public void setRulesPageName(String rulesPageName) {
        this.rulesPageName = rulesPageName;
    }

    public String getRulesPageUrl() {
        return this.rulesPageUrl;
    }

    public void setRulesPageUrl(String rulesPageUrl) {
        this.rulesPageUrl = rulesPageUrl;
    }

    public boolean isShowProposalSummary() {
        return showProposalSummary;
    }

    public void setShowProposalSummary(boolean showProposalSummary) {
        this.showProposalSummary = showProposalSummary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractContestType)) {
            return false;
        }

        AbstractContestType that = (AbstractContestType) o;

        if (showProposalSummary != that.showProposalSummary) {
            return false;
        }
        if (id_ != null ? !id_.equals(that.id_) : that.id_ != null) {
            return false;
        }
        if (contestName != null ? !contestName.equals(that.contestName)
                : that.contestName != null) {
            return false;
        }
        if (contestNamePlural != null ? !contestNamePlural.equals(that.contestNamePlural)
                : that.contestNamePlural != null) {
            return false;
        }
        if (proposalName != null ? !proposalName.equals(that.proposalName)
                : that.proposalName != null) {
            return false;
        }
        if (proposalNamePlural != null ? !proposalNamePlural.equals(that.proposalNamePlural)
                : that.proposalNamePlural != null) {
            return false;
        }
        if (portletName != null ? !portletName.equals(that.portletName)
                : that.portletName != null) {
            return false;
        }
        if (portletUrl != null ? !portletUrl.equals(that.portletUrl) : that.portletUrl != null) {
            return false;
        }
        if (friendlyUrlStringContests != null ? !friendlyUrlStringContests
                .equals(that.friendlyUrlStringContests) : that.friendlyUrlStringContests != null) {
            return false;
        }
        if (friendlyUrlStringProposal != null ? !friendlyUrlStringProposal
                .equals(that.friendlyUrlStringProposal) : that.friendlyUrlStringProposal != null) {
            return false;
        }
        if (menuItemName != null ? !menuItemName.equals(that.menuItemName)
                : that.menuItemName != null) {
            return false;
        }
        if (hasDiscussion != null ? !hasDiscussion.equals(that.hasDiscussion)
                : that.hasDiscussion != null) {
            return false;
        }
        if (suggestionContestId != null ? !suggestionContestId.equals(that.suggestionContestId)
                : that.suggestionContestId != null) {
            return false;
        }
        if (rulesPageName != null ? !rulesPageName.equals(that.rulesPageName)
                : that.rulesPageName != null) {
            return false;
        }
        return rulesPageUrl != null ? rulesPageUrl.equals(that.rulesPageUrl)
                : that.rulesPageUrl == null;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = id_ != null ? id_.hashCode() : 0;
        result = prime * result + (contestName != null ? contestName.hashCode() : 0);
        result = prime * result + (contestNamePlural != null ? contestNamePlural.hashCode() : 0);
        result = prime * result + (proposalName != null ? proposalName.hashCode() : 0);
        result = prime * result + (proposalNamePlural != null ? proposalNamePlural.hashCode() : 0);
        result = prime * result + (portletName != null ? portletName.hashCode() : 0);
        result = prime * result + (portletUrl != null ? portletUrl.hashCode() : 0);
        result = prime * result + (friendlyUrlStringContests != null ? friendlyUrlStringContests
                .hashCode()
                : 0);
        result = prime * result + (friendlyUrlStringProposal != null ? friendlyUrlStringProposal
                .hashCode()
                : 0);
        result = prime * result + (menuItemName != null ? menuItemName.hashCode() : 0);
        result = prime * result + (hasDiscussion != null ? hasDiscussion.hashCode() : 0);
        result = prime * result + (suggestionContestId != null ? suggestionContestId.hashCode() : 0);
        result = prime * result + (rulesPageName != null ? rulesPageName.hashCode() : 0);
        result = prime * result + (rulesPageUrl != null ? rulesPageUrl.hashCode() : 0);
        result = prime * result + (showProposalSummary ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "AbstractContestType{" +
                "id_=" + id_ +
                ", contestName='" + contestName + '\'' +
                ", contestNamePlural='" + contestNamePlural + '\'' +
                ", proposalName='" + proposalName + '\'' +
                ", proposalNamePlural='" + proposalNamePlural + '\'' +
                ", portletName='" + portletName + '\'' +
                ", portletUrl='" + portletUrl + '\'' +
                ", friendlyUrlStringContests='" + friendlyUrlStringContests + '\'' +
                ", friendlyUrlStringProposal='" + friendlyUrlStringProposal + '\'' +
                ", menuItemName='" + menuItemName + '\'' +
                ", hasDiscussion=" + hasDiscussion + ", suggestionContestId=" + suggestionContestId +
                ", rulesPageName='" + rulesPageName + '\'' +
                ", rulesPageUrl='" + rulesPageUrl + '\'' +
                ", showProposalSummary=" + showProposalSummary +
                '}';
    }
}
