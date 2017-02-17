package org.xcolab.client.contest.pojo.templates;

import java.io.Serializable;

abstract class AbstractPlanSectionDefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long    id_;
    private String  type_;
    private String  admintitle;
    private String  title;
    private String  defaulttext;
    private String  helptext;
    private Integer characterlimit;
    private Long    focusareaid;
    private Long    tier;
    private String  allowedcontesttypeids;
    private String  allowedvalues;
    private String  additionalids;
    private Boolean locked;
    private Boolean contestintegrationrelevance;

    public AbstractPlanSectionDefinition() {}

    public AbstractPlanSectionDefinition(AbstractPlanSectionDefinition value) {
        this.id_ = value.id_;
        this.type_ = value.type_;
        this.admintitle = value.admintitle;
        this.title = value.title;
        this.defaulttext = value.defaulttext;
        this.helptext = value.helptext;
        this.characterlimit = value.characterlimit;
        this.focusareaid = value.focusareaid;
        this.tier = value.tier;
        this.allowedcontesttypeids = value.allowedcontesttypeids;
        this.allowedvalues = value.allowedvalues;
        this.additionalids = value.additionalids;
        this.locked = value.locked;
        this.contestintegrationrelevance = value.contestintegrationrelevance;
    }

    public AbstractPlanSectionDefinition(
        Long    id_,
        String  type_,
        String  admintitle,
        String  title,
        String  defaulttext,
        String  helptext,
        Integer characterlimit,
        Long    focusareaid,
        Long    tier,
        String  allowedcontesttypeids,
        String  allowedvalues,
        String  additionalids,
        Boolean locked,
        Boolean contestintegrationrelevance
    ) {
        this.id_ = id_;
        this.type_ = type_;
        this.admintitle = admintitle;
        this.title = title;
        this.defaulttext = defaulttext;
        this.helptext = helptext;
        this.characterlimit = characterlimit;
        this.focusareaid = focusareaid;
        this.tier = tier;
        this.allowedcontesttypeids = allowedcontesttypeids;
        this.allowedvalues = allowedvalues;
        this.additionalids = additionalids;
        this.locked = locked;
        this.contestintegrationrelevance = contestintegrationrelevance;
    }

    public Long getId_() {
        return this.id_;
    }

    public void setId_(Long id_) {
        this.id_ = id_;
    }

    public String getType_() {
        return this.type_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public String getAdminTitle() {
        return this.admintitle;
    }

    public void setAdminTitle(String admintitle) {
        this.admintitle = admintitle;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDefaultText() {
        return this.defaulttext;
    }

    public void setDefaultText(String defaulttext) {
        this.defaulttext = defaulttext;
    }

    public String getHelpText() {
        return this.helptext;
    }

    public void setHelpText(String helptext) {
        this.helptext = helptext;
    }

    public Integer getCharacterLimit() {
        return this.characterlimit;
    }

    public void setCharacterLimit(Integer characterlimit) {
        this.characterlimit = characterlimit;
    }

    public Long getFocusAreaId() {
        return this.focusareaid;
    }

    public void setFocusAreaId(Long focusareaid) {
        this.focusareaid = focusareaid;
    }

    public Long getTier() {
        return this.tier;
    }

    public void setTier(Long tier) {
        this.tier = tier;
    }

    public String getAllowedContestTypeIds() {
        return this.allowedcontesttypeids;
    }

    public void setAllowedContestTypeIds(String allowedcontesttypeids) {
        this.allowedcontesttypeids = allowedcontesttypeids;
    }

    public String getAllowedValues() {
        return this.allowedvalues;
    }

    public void setAllowedValues(String allowedvalues) {
        this.allowedvalues = allowedvalues;
    }

    public String getAdditionalIds() {
        return this.additionalids;
    }

    public void setAdditionalIds(String additionalids) {
        this.additionalids = additionalids;
    }

    public Boolean getLocked() {
        return this.locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getContestIntegrationRelevance() {
        return this.contestintegrationrelevance;
    }

    public void setContestIntegrationRelevance(Boolean contestintegrationrelevance) {
        this.contestintegrationrelevance = contestintegrationrelevance;
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
        final AbstractPlanSectionDefinition other = (AbstractPlanSectionDefinition) obj;
        if (id_ == null) {
            if (other.id_ != null) {
                return false;
            }
        }
        else if (!id_.equals(other.id_)) {
            return false;
        }
        if (type_ == null) {
            if (other.type_ != null) {
                return false;
            }
        }
        else if (!type_.equals(other.type_)) {
            return false;
        }
        if (admintitle == null) {
            if (other.admintitle != null) {
                return false;
            }
        }
        else if (!admintitle.equals(other.admintitle)) {
            return false;
        }
        if (title == null) {
            if (other.title != null) {
                return false;
            }
        }
        else if (!title.equals(other.title)) {
            return false;
        }
        if (defaulttext == null) {
            if (other.defaulttext != null) {
                return false;
            }
        }
        else if (!defaulttext.equals(other.defaulttext)) {
            return false;
        }
        if (helptext == null) {
            if (other.helptext != null) {
                return false;
            }
        }
        else if (!helptext.equals(other.helptext)) {
            return false;
        }
        if (characterlimit == null) {
            if (other.characterlimit != null) {
                return false;
            }
        }
        else if (!characterlimit.equals(other.characterlimit)) {
            return false;
        }
        if (focusareaid == null) {
            if (other.focusareaid != null) {
                return false;
            }
        }
        else if (!focusareaid.equals(other.focusareaid)) {
            return false;
        }
        if (tier == null) {
            if (other.tier != null) {
                return false;
            }
        }
        else if (!tier.equals(other.tier)) {
            return false;
        }
        if (allowedcontesttypeids == null) {
            if (other.allowedcontesttypeids != null) {
                return false;
            }
        }
        else if (!allowedcontesttypeids.equals(other.allowedcontesttypeids)) {
            return false;
        }
        if (allowedvalues == null) {
            if (other.allowedvalues != null) {
                return false;
            }
        }
        else if (!allowedvalues.equals(other.allowedvalues)) {
            return false;
        }
        if (additionalids == null) {
            if (other.additionalids != null) {
                return false;
            }
        }
        else if (!additionalids.equals(other.additionalids)) {
            return false;
        }
        if (locked == null) {
            if (other.locked != null) {
                return false;
            }
        }
        else if (!locked.equals(other.locked)) {
            return false;
        }
        if (contestintegrationrelevance == null) {
            if (other.contestintegrationrelevance != null) {
                return false;
            }
        }
        else if (!contestintegrationrelevance.equals(other.contestintegrationrelevance)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id_ == null) ? 0 : id_.hashCode());
        result = prime * result + ((type_ == null) ? 0 : type_.hashCode());
        result = prime * result + ((admintitle == null) ? 0 : admintitle.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((defaulttext == null) ? 0 : defaulttext.hashCode());
        result = prime * result + ((helptext == null) ? 0 : helptext.hashCode());
        result = prime * result + ((characterlimit == null) ? 0 : characterlimit.hashCode());
        result = prime * result + ((focusareaid == null) ? 0 : focusareaid.hashCode());
        result = prime * result + ((tier == null) ? 0 : tier.hashCode());
        result = prime * result + ((allowedcontesttypeids == null) ? 0 : allowedcontesttypeids.hashCode());
        result = prime * result + ((allowedvalues == null) ? 0 : allowedvalues.hashCode());
        result = prime * result + ((additionalids == null) ? 0 : additionalids.hashCode());
        result = prime * result + ((locked == null) ? 0 : locked.hashCode());
        result = prime * result + ((contestintegrationrelevance == null) ? 0 : contestintegrationrelevance.hashCode());
        return result;
    }

    @Override
    public String toString() {

        return "PlanSectionDefinition (" + id_ +
                ", " + type_ +
                ", " + admintitle +
                ", " + title +
                ", " + defaulttext +
                ", " + helptext +
                ", " + characterlimit +
                ", " + focusareaid +
                ", " + tier +
                ", " + allowedcontesttypeids +
                ", " + allowedvalues +
                ", " + additionalids +
                ", " + locked +
                ", " + contestintegrationrelevance +
                ")";
    }
}
