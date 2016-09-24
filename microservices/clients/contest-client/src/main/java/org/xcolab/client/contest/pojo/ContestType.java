package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContestType implements Serializable {

    public static final TypeProvider<ContestType> TYPES = new TypeProvider<>(ContestType.class,
            new ParameterizedTypeReference<List<ContestType>>() {
            });

    private static final long serialVersionUID = -816986355;

    private Long id_;
    private String contestName;
    private String contestNamePlural;
    private String proposalName;
    private String proposalNamePlural;
    private String portletName;
    private String portleturl;
    private String friendlyUrlStringContests;
    private String friendlyUrlStringProposal;
    private String menuItemName;
    private Boolean hasDiscussion;
    private Long suggestionContestId;
    private String rulesPageName;
    private String rulesPageUrl;

    public ContestType() {
    }

    public ContestType(ContestType value) {
        this.id_ = value.id_;
        this.contestName = value.contestName;
        this.contestNamePlural = value.contestNamePlural;
        this.proposalName = value.proposalName;
        this.proposalNamePlural = value.proposalNamePlural;
        this.portletName = value.portletName;
        this.portleturl = value.portleturl;
        this.friendlyUrlStringContests = value.friendlyUrlStringContests;
        this.friendlyUrlStringProposal = value.friendlyUrlStringProposal;
        this.menuItemName = value.menuItemName;
        this.hasDiscussion = value.hasDiscussion;
        this.suggestionContestId = value.suggestionContestId;
        this.rulesPageName = value.rulesPageName;
        this.rulesPageUrl = value.rulesPageUrl;
    }

    public ContestType(Long id_, String contestName, String contestNamePlural, String proposalName,
            String proposalNamePlural, String portletName, String portleturl,
            String friendlyUrlStringContests, String friendlyUrlStringProposal,
            String menuItemName, Boolean hasDiscussion, Long suggestionContestId,
            String rulesPageName, String rulesPageUrl) {
        this.id_ = id_;
        this.contestName = contestName;
        this.contestNamePlural = contestNamePlural;
        this.proposalName = proposalName;
        this.proposalNamePlural = proposalNamePlural;
        this.portletName = portletName;
        this.portleturl = portleturl;
        this.friendlyUrlStringContests = friendlyUrlStringContests;
        this.friendlyUrlStringProposal = friendlyUrlStringProposal;
        this.menuItemName = menuItemName;
        this.hasDiscussion = hasDiscussion;
        this.suggestionContestId = suggestionContestId;
        this.rulesPageName = rulesPageName;
        this.rulesPageUrl = rulesPageUrl;
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
        return this.portleturl;
    }

    public void setPortletUrl(String portleturl) {
        this.portleturl = portleturl;
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContestType other = (ContestType) obj;
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        } else if (!id_.equals(other.id_)) {
            return false;
        }
        if (contestName == null) {
            if (other.contestName != null) {
                return false;
            }
        } else if (!contestName.equals(other.contestName)) {
            return false;
        }
        if (contestNamePlural == null) {
            if (other.contestNamePlural != null) {
                return false;
            }
        } else if (!contestNamePlural.equals(other.contestNamePlural)) {
            return false;
        }
        if (proposalName == null) {
            if (other.proposalName != null) {
                return false;
            }
        } else if (!proposalName.equals(other.proposalName)) {
            return false;
        }
        if (proposalNamePlural == null) {
            if (other.proposalNamePlural != null) {
                return false;
            }
        } else if (!proposalNamePlural.equals(other.proposalNamePlural)) {
            return false;
        }
        if (portletName == null) {
            if (other.portletName != null) {
                return false;
            }
        } else if (!portletName.equals(other.portletName)) {
            return false;
        }
        if (portleturl == null) {
            if (other.portleturl != null) {
                return false;
            }
        } else if (!portleturl.equals(other.portleturl)) {
            return false;
        }
        if (friendlyUrlStringContests == null) {
            if (other.friendlyUrlStringContests != null) {
                return false;
            }
        } else if (!friendlyUrlStringContests.equals(other.friendlyUrlStringContests)) {
            return false;
        }
        if (friendlyUrlStringProposal == null) {
            if (other.friendlyUrlStringProposal != null) {
                return false;
            }
        } else if (!friendlyUrlStringProposal.equals(other.friendlyUrlStringProposal)) {
            return false;
        }
        if (menuItemName == null) {
            if (other.menuItemName != null) {
                return false;
            }
        } else if (!menuItemName.equals(other.menuItemName)) {
            return false;
        }
        if (hasDiscussion == null) {
            if (other.hasDiscussion != null) {
                return false;
            }
        } else if (!hasDiscussion.equals(other.hasDiscussion)) {
            return false;
        }
        if (suggestionContestId == null) {
            if (other.suggestionContestId != null) {
                return false;
            }
        } else if (!suggestionContestId.equals(other.suggestionContestId)) {
            return false;
        }
        if (rulesPageName == null) {
            if (other.rulesPageName != null) {
                return false;
            }
        } else if (!rulesPageName.equals(other.rulesPageName)) {
            return false;
        }
        if (rulesPageUrl == null) {
            if (other.rulesPageUrl != null) {
                return false;
            }
        } else if (!rulesPageUrl.equals(other.rulesPageUrl)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((contestName == null) ? 0 : contestName.hashCode());
        result = prime * result + ((contestNamePlural == null) ? 0 : contestNamePlural.hashCode());
        result = prime * result + ((proposalName == null) ? 0 : proposalName.hashCode());
        result =
                prime * result + ((proposalNamePlural == null) ? 0 : proposalNamePlural.hashCode());
        result = prime * result + ((portletName == null) ? 0 : portletName.hashCode());
        result = prime * result + ((portleturl == null) ? 0 : portleturl.hashCode());
        result = prime * result + ((friendlyUrlStringContests == null) ? 0
                : friendlyUrlStringContests.hashCode());
        result = prime * result + ((friendlyUrlStringProposal == null) ? 0
                : friendlyUrlStringProposal.hashCode());
        result = prime * result + ((menuItemName == null) ? 0 : menuItemName.hashCode());
        result = prime * result + ((hasDiscussion == null) ? 0 : hasDiscussion.hashCode());
        result = prime * result + ((suggestionContestId == null) ? 0
                : suggestionContestId.hashCode());
        result = prime * result + ((rulesPageName == null) ? 0 : rulesPageName.hashCode());
        result = prime * result + ((rulesPageUrl == null) ? 0 : rulesPageUrl.hashCode());
        return result;
    }

    @Override
    public String toString() {

        return "ContestType (" + id_ +
                ", " + contestName +
                ", " + contestNamePlural +
                ", " + proposalName +
                ", " + proposalNamePlural +
                ", " + portletName +
                ", " + portleturl +
                ", " + friendlyUrlStringContests +
                ", " + friendlyUrlStringProposal +
                ", " + menuItemName +
                ", " + hasDiscussion +
                ", " + suggestionContestId +
                ", " + rulesPageName +
                ", " + rulesPageUrl +
                ")";
    }
    @JsonIgnore
    public String getLabelName() {
        return String.format("%d - %s with %s",
                this.getId_(), this.getContestName(), this.getProposalNamePlural());
    }
}
