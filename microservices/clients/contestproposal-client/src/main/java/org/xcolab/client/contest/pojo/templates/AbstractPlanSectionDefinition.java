package org.xcolab.client.contest.pojo.templates;

import java.io.Serializable;
import java.util.Objects;

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
    private Boolean includeInJudgingReport;

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
        this.includeInJudgingReport = value.includeInJudgingReport;
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

    public Boolean getIncludeInJudgingReport() {
        return this.includeInJudgingReport;
    }

    public void setIncludeInJudgingReport(Boolean includeInJudgingReport) {
        this.includeInJudgingReport = includeInJudgingReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractPlanSectionDefinition)) {
            return false;
        }
        AbstractPlanSectionDefinition that = (AbstractPlanSectionDefinition) o;
        return Objects.equals(getId_(), that.getId_())
                && Objects.equals(getType_(), that.getType_())
                && Objects.equals(admintitle, that.admintitle)
                && Objects.equals(getTitle(), that.getTitle())
                && Objects.equals(defaulttext, that.defaulttext)
                && Objects.equals(helptext, that.helptext)
                && Objects.equals(characterlimit, that.characterlimit)
                && Objects.equals(focusareaid, that.focusareaid)
                && Objects.equals(getTier(), that.getTier())
                && Objects.equals(allowedcontesttypeids, that.allowedcontesttypeids)
                && Objects.equals(allowedvalues, that.allowedvalues)
                && Objects.equals(additionalids, that.additionalids)
                && Objects.equals(getLocked(), that.getLocked())
                && Objects.equals(contestintegrationrelevance, that.contestintegrationrelevance)
                && Objects.equals(getIncludeInJudgingReport(), that.getIncludeInJudgingReport());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId_(), getType_(), admintitle, getTitle(), defaulttext, helptext,
                characterlimit, focusareaid, getTier(), allowedcontesttypeids, allowedvalues,
                additionalids, getLocked(), contestintegrationrelevance,
                getIncludeInJudgingReport());
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
