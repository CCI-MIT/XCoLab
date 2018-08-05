package org.xcolab.client.contest.pojo;

import java.sql.Timestamp;
import java.util.Objects;

public abstract class AbstractContest {

    private Long contestpk;
    private Long contesttypeid;
    private String contestname;
    private String contestshortname;
    private String contesturlname;
    private Long contestyear;
    private String contestdescription;
    private String contestmodeldescription;
    private String contestpositionsdescription;
    private Timestamp created;
    private Timestamp updated;
    private Long authorUserid;
    private Boolean contestactive;
    private Long plantemplateid;
    private Long contestscheduleid;
    private String proposalcreationtemplatestring;
    private String votetemplatestring;
    private String proposalvotetemplatestring;
    private String proposalvoteconfirmationtemplatestring;
    private String votequestiontemplatestring;
    private Long focusareaid;
    private Long contesttier;
    private Long contestlogoid;
    private Boolean featured_;
    private Boolean plansopenbydefault;
    private Long sponsorlogoid;
    private Long defaultproposallogoid;
    private String sponsortext;
    private String sponsorlink;
    private Integer flag;
    private String flagtext;
    private String flagtooltip;
    private Long discussiongroupid;
    private Integer weight;
    private String resourcesurl;
    private Boolean contestprivate;
    private Boolean usepermissions;
    private String contestcreationstatus;
    private Long defaultmodelid;
    private String othermodels;
    private String defaultmodelsettings;
    private Double points;
    private Long defaultparentpointtype;
    private String pointdistributionstrategy;
    private String emailtemplateurl;
    private Boolean showInTileView;
    private Boolean showInListView;
    private Boolean showInOutlineView;
    private Boolean hideribbons;
    private Long resourcearticleid;

    public AbstractContest() {}

    public AbstractContest(AbstractContest value) {
        this.contestpk = value.contestpk;
        this.contesttypeid = value.contesttypeid;
        this.contestname = value.contestname;
        this.contestshortname = value.contestshortname;
        this.contesturlname = value.contesturlname;
        this.contestyear = value.contestyear;
        this.contestdescription = value.contestdescription;
        this.contestmodeldescription = value.contestmodeldescription;
        this.contestpositionsdescription = value.contestpositionsdescription;
        this.created = value.created;
        this.updated = value.updated;
        this.authorUserid = value.authorUserid;
        this.contestactive = value.contestactive;
        this.plantemplateid = value.plantemplateid;
        this.contestscheduleid = value.contestscheduleid;
        this.proposalcreationtemplatestring = value.proposalcreationtemplatestring;
        this.votetemplatestring = value.votetemplatestring;
        this.proposalvotetemplatestring = value.proposalvotetemplatestring;
        this.proposalvoteconfirmationtemplatestring = value.proposalvoteconfirmationtemplatestring;
        this.votequestiontemplatestring = value.votequestiontemplatestring;
        this.focusareaid = value.focusareaid;
        this.contesttier = value.contesttier;
        this.contestlogoid = value.contestlogoid;
        this.featured_ = value.featured_;
        this.plansopenbydefault = value.plansopenbydefault;
        this.sponsorlogoid = value.sponsorlogoid;
        this.defaultproposallogoid = value.defaultproposallogoid;
        this.sponsortext = value.sponsortext;
        this.sponsorlink = value.sponsorlink;
        this.flag = value.flag;
        this.flagtext = value.flagtext;
        this.flagtooltip = value.flagtooltip;
        this.discussiongroupid = value.discussiongroupid;
        this.weight = value.weight;
        this.resourcesurl = value.resourcesurl;
        this.contestprivate = value.contestprivate;
        this.usepermissions = value.usepermissions;
        this.contestcreationstatus = value.contestcreationstatus;
        this.defaultmodelid = value.defaultmodelid;
        this.othermodels = value.othermodels;
        this.defaultmodelsettings = value.defaultmodelsettings;
        this.points = value.points;
        this.defaultparentpointtype = value.defaultparentpointtype;
        this.pointdistributionstrategy = value.pointdistributionstrategy;
        this.emailtemplateurl = value.emailtemplateurl;
        this.showInTileView = value.showInTileView;
        this.showInListView = value.showInListView;
        this.showInOutlineView = value.showInOutlineView;
        this.hideribbons = value.hideribbons;
        this.resourcearticleid = value.resourcearticleid;
    }

    public AbstractContest(Long contestpk, Long contesttypeid, String contestname,
            String contestshortname, String contesturlname, Long contestyear,
            String contestdescription, String contestmodeldescription,
            String contestpositionsdescription, Timestamp created, Timestamp updated, Long authorUserid,
            Boolean contestactive, Long plantemplateid, Long contestscheduleid,
            String proposalcreationtemplatestring, String votetemplatestring,
            String proposalvotetemplatestring, String proposalvoteconfirmationtemplatestring,
            String votequestiontemplatestring, Long focusareaid, Long contesttier,
            Long contestlogoid, Boolean featured_, Boolean plansopenbydefault, Long sponsorlogoid,
            String sponsortext, String sponsorlink, Integer flag, String flagtext,
            String flagtooltip, Long discussiongroupid, Integer weight, String resourcesurl,
            Boolean contestprivate, Boolean usepermissions, String contestcreationstatus,
            Long defaultmodelid, String othermodels, String defaultmodelsettings, Double points,
            Long defaultparentpointtype, String pointdistributionstrategy, String emailtemplateurl,
            Boolean showInTileView, Boolean showInListView, Boolean showInOutlineView,
            Boolean hideribbons, Long resourcearticleid) {
        this.contestpk = contestpk;
        this.contesttypeid = contesttypeid;
        this.contestname = contestname;
        this.contestshortname = contestshortname;
        this.contesturlname = contesturlname;
        this.contestyear = contestyear;
        this.contestdescription = contestdescription;
        this.contestmodeldescription = contestmodeldescription;
        this.contestpositionsdescription = contestpositionsdescription;
        this.created = created;
        this.updated = updated;
        this.authorUserid = authorUserid;
        this.contestactive = contestactive;
        this.plantemplateid = plantemplateid;
        this.contestscheduleid = contestscheduleid;
        this.proposalcreationtemplatestring = proposalcreationtemplatestring;
        this.votetemplatestring = votetemplatestring;
        this.proposalvotetemplatestring = proposalvotetemplatestring;
        this.proposalvoteconfirmationtemplatestring = proposalvoteconfirmationtemplatestring;
        this.votequestiontemplatestring = votequestiontemplatestring;
        this.focusareaid = focusareaid;
        this.contesttier = contesttier;
        this.contestlogoid = contestlogoid;
        this.featured_ = featured_;
        this.plansopenbydefault = plansopenbydefault;
        this.sponsorlogoid = sponsorlogoid;
        this.sponsortext = sponsortext;
        this.sponsorlink = sponsorlink;
        this.flag = flag;
        this.flagtext = flagtext;
        this.flagtooltip = flagtooltip;
        this.discussiongroupid = discussiongroupid;
        this.weight = weight;
        this.resourcesurl = resourcesurl;
        this.contestprivate = contestprivate;
        this.usepermissions = usepermissions;
        this.contestcreationstatus = contestcreationstatus;
        this.defaultmodelid = defaultmodelid;
        this.othermodels = othermodels;
        this.defaultmodelsettings = defaultmodelsettings;
        this.points = points;
        this.defaultparentpointtype = defaultparentpointtype;
        this.pointdistributionstrategy = pointdistributionstrategy;
        this.emailtemplateurl = emailtemplateurl;
        this.showInTileView = showInTileView;
        this.showInListView = showInListView;
        this.showInOutlineView = showInOutlineView;
        this.hideribbons = hideribbons;
        this.resourcearticleid = resourcearticleid;
    }

    public Long getContestPK() {
        return this.contestpk;
    }

    public void setContestPK(Long contestpk) {
        this.contestpk = contestpk;
    }

    public Long getContestTypeId() {
        return this.contesttypeid;
    }

    public void setContestTypeId(Long contesttypeid) {
        this.contesttypeid = contesttypeid;
    }

    public String getContestName() {
        return this.contestname;
    }

    public void setContestName(String contestname) {
        this.contestname = contestname;
    }

    public String getContestShortName() {
        return this.contestshortname;
    }

    public void setContestShortName(String contestshortname) {
        this.contestshortname = contestshortname;
    }

    public String getContestUrlName() {
        return this.contesturlname;
    }

    public void setContestUrlName(String contesturlname) {
        this.contesturlname = contesturlname;
    }

    public Long getContestYear() {
        return this.contestyear;
    }

    public void setContestYear(Long contestyear) {
        this.contestyear = contestyear;
    }

    public String getContestDescription() {
        return this.contestdescription;
    }

    public void setContestDescription(String contestdescription) {
        this.contestdescription = contestdescription;
    }

    public String getContestModelDescription() {
        return this.contestmodeldescription;
    }

    public void setContestModelDescription(String contestmodeldescription) {
        this.contestmodeldescription = contestmodeldescription;
    }

    public String getContestPositionsDescription() {
        return this.contestpositionsdescription;
    }

    public void setContestPositionsDescription(String contestpositionsdescription) {
        this.contestpositionsdescription = contestpositionsdescription;
    }

    public Long getauthorUserid() {
        return this.authorUserid;
    }

    public void setauthorUserid(Long authorUserid) {
        this.authorUserid = authorUserid;
    }

    public Boolean getContestActive() {
        return this.contestactive;
    }

    public void setContestActive(Boolean contestactive) {
        this.contestactive = contestactive;
    }

    public Long getPlanTemplateId() {
        return this.plantemplateid;
    }

    public void setPlanTemplateId(Long plantemplateid) {
        this.plantemplateid = plantemplateid;
    }

    public Long getContestScheduleId() {
        return this.contestscheduleid;
    }

    public void setContestScheduleId(Long contestscheduleid) {
        this.contestscheduleid = contestscheduleid;
    }

    public String getProposalCreationTemplateString() {
        return this.proposalcreationtemplatestring;
    }

    public void setProposalCreationTemplateString(String proposalcreationtemplatestring) {
        this.proposalcreationtemplatestring = proposalcreationtemplatestring;
    }

    public String getVoteTemplateString() {
        return this.votetemplatestring;
    }

    public void setVoteTemplateString(String votetemplatestring) {
        this.votetemplatestring = votetemplatestring;
    }

    public String getProposalVoteTemplateString() {
        return this.proposalvotetemplatestring;
    }

    public void setProposalVoteTemplateString(String proposalvotetemplatestring) {
        this.proposalvotetemplatestring = proposalvotetemplatestring;
    }

    public String getProposalVoteConfirmationTemplateString() {
        return this.proposalvoteconfirmationtemplatestring;
    }

    public void setProposalVoteConfirmationTemplateString(
            String proposalvoteconfirmationtemplatestring) {
        this.proposalvoteconfirmationtemplatestring = proposalvoteconfirmationtemplatestring;
    }

    public String getVoteQuestionTemplateString() {
        return this.votequestiontemplatestring;
    }

    public void setVoteQuestionTemplateString(String votequestiontemplatestring) {
        this.votequestiontemplatestring = votequestiontemplatestring;
    }

    public Long getFocusAreaId() {
        return this.focusareaid;
    }

    public void setFocusAreaId(Long focusareaid) {
        this.focusareaid = focusareaid;
    }

    public Long getContestTier() {
        return this.contesttier;
    }

    public void setContestTier(Long contesttier) {
        this.contesttier = contesttier;
    }

    public Long getContestLogoId() {
        return this.contestlogoid;
    }

    public void setContestLogoId(Long contestlogoid) {
        this.contestlogoid = contestlogoid;
    }

    public Boolean getPlansOpenByDefault() {
        return this.plansopenbydefault;
    }

    public void setPlansOpenByDefault(Boolean plansopenbydefault) {
        this.plansopenbydefault = plansopenbydefault;
    }

    public Long getSponsorLogoId() {
        return this.sponsorlogoid;
    }

    public void setSponsorLogoId(Long sponsorlogoid) {
        this.sponsorlogoid = sponsorlogoid;
    }

    public Long getDefaultProposalLogoId() {
        return this.defaultproposallogoid;
    }

    public void setDefaultProposalLogoId(Long defaultproposallogoid) {
        this.defaultproposallogoid = defaultproposallogoid;
    }

    public String getSponsorText() {
        return this.sponsortext;
    }

    public void setSponsorText(String sponsortext) {
        this.sponsortext = sponsortext;
    }

    public String getSponsorLink() {
        return this.sponsorlink;
    }

    public void setSponsorLink(String sponsorlink) {
        this.sponsorlink = sponsorlink;
    }

    public String getFlagText() {
        return this.flagtext;
    }

    public void setFlagText(String flagtext) {
        this.flagtext = flagtext;
    }

    public String getFlagTooltip() {
        return this.flagtooltip;
    }

    public void setFlagTooltip(String flagtooltip) {
        this.flagtooltip = flagtooltip;
    }

    public Long getDiscussionGroupId() {
        return this.discussiongroupid;
    }

    public void setDiscussionGroupId(Long discussiongroupid) {
        this.discussiongroupid = discussiongroupid;
    }

    public String getResourcesUrl() {
        return this.resourcesurl;
    }

    public void setResourcesUrl(String resourcesurl) {
        this.resourcesurl = resourcesurl;
    }

    public Boolean getContestPrivate() {
        return this.contestprivate;
    }

    public void setContestPrivate(Boolean contestprivate) {
        this.contestprivate = contestprivate;
    }

    public Boolean getUsePermissions() {
        return this.usepermissions;
    }

    public void setUsePermissions(Boolean usepermissions) {
        this.usepermissions = usepermissions;
    }

    public String getContestCreationStatus() {
        return this.contestcreationstatus;
    }

    public void setContestCreationStatus(String contestcreationstatus) {
        this.contestcreationstatus = contestcreationstatus;
    }

    public Long getDefaultModelId() {
        return this.defaultmodelid;
    }

    public void setDefaultModelId(Long defaultmodelid) {
        this.defaultmodelid = defaultmodelid;
    }

    public String getOtherModels() {
        return this.othermodels;
    }

    public void setOtherModels(String othermodels) {
        this.othermodels = othermodels;
    }

    public String getDefaultModelSettings() {
        return this.defaultmodelsettings;
    }

    public void setDefaultModelSettings(String defaultmodelsettings) {
        this.defaultmodelsettings = defaultmodelsettings;
    }

    public Long getDefaultParentPointType() {
        return this.defaultparentpointtype;
    }

    public void setDefaultParentPointType(Long defaultparentpointtype) {
        this.defaultparentpointtype = defaultparentpointtype;
    }

    public String getPointDistributionStrategy() {
        return this.pointdistributionstrategy;
    }

    public void setPointDistributionStrategy(String pointdistributionstrategy) {
        this.pointdistributionstrategy = pointdistributionstrategy;
    }

    public String getEmailTemplateUrl() {
        return this.emailtemplateurl;
    }

    public void setEmailTemplateUrl(String emailtemplateurl) {
        this.emailtemplateurl = emailtemplateurl;
    }

    public Boolean getShow_in_tile_view() {
        return this.showInTileView;
    }

    public void setShow_in_tile_view(Boolean showInTileView) {
        this.showInTileView = showInTileView;
    }

    public Boolean getShow_in_list_view() {
        return this.showInListView;
    }

    public void setShow_in_list_view(Boolean showInListView) {
        this.showInListView = showInListView;
    }

    public Boolean getShow_in_outline_view() {
        return this.showInOutlineView;
    }

    public void setShow_in_outline_view(Boolean showInOutlineView) {
        this.showInOutlineView = showInOutlineView;
    }

    public Boolean getHideRibbons() {
        return this.hideribbons;
    }

    public void setHideRibbons(Boolean hideribbons) {
        this.hideribbons = hideribbons;
    }

    public Long getResourceArticleId() {
        return this.resourcearticleid;
    }

    public void setResourceArticleId(Long resourcearticleid) {
        this.resourcearticleid = resourcearticleid;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getUpdated() {
        return this.updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public Boolean getFeatured_() {
        return this.featured_;
    }

    public void setFeatured_(Boolean featured_) {
        this.featured_ = featured_;
    }

    public Integer getFlag() {
        return this.flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getPoints() {
        return this.points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    @Override
    public int hashCode() {
        return Objects.hash(contestpk, contesttypeid, contestname, contestshortname, contesturlname,
                contestyear, contestdescription, contestmodeldescription,
                contestpositionsdescription, getCreated(), getUpdated(), authorUserid, contestactive,
                plantemplateid, contestscheduleid, proposalcreationtemplatestring,
                votetemplatestring, proposalvotetemplatestring,
                proposalvoteconfirmationtemplatestring, votequestiontemplatestring, focusareaid,
                contesttier, contestlogoid, getFeatured_(), plansopenbydefault, sponsorlogoid,
                defaultproposallogoid, sponsortext, sponsorlink, getFlag(), flagtext, flagtooltip,
                discussiongroupid, getWeight(), resourcesurl, contestprivate,
                usepermissions, contestcreationstatus, defaultmodelid, othermodels,
                defaultmodelsettings, getPoints(), defaultparentpointtype,
                pointdistributionstrategy, emailtemplateurl, showInTileView, showInListView,
                showInOutlineView, hideribbons, resourcearticleid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractContest)) {
            return false;
        }
        AbstractContest that = (AbstractContest) o;
        return Objects.equals(contestpk, that.contestpk) && Objects
                .equals(contesttypeid, that.contesttypeid) && Objects
                .equals(contestname, that.contestname) && Objects
                .equals(contestshortname, that.contestshortname) && Objects
                .equals(contesturlname, that.contesturlname) && Objects
                .equals(contestyear, that.contestyear) && Objects
                .equals(contestdescription, that.contestdescription) && Objects
                .equals(contestmodeldescription, that.contestmodeldescription) && Objects
                .equals(contestpositionsdescription, that.contestpositionsdescription) && Objects
                .equals(getCreated(), that.getCreated()) && Objects
                .equals(getUpdated(), that.getUpdated()) && Objects.equals(authorUserid, that.authorUserid)
                && Objects.equals(contestactive, that.contestactive) && Objects
                .equals(plantemplateid, that.plantemplateid) && Objects
                .equals(contestscheduleid, that.contestscheduleid) && Objects
                .equals(proposalcreationtemplatestring, that.proposalcreationtemplatestring)
                && Objects.equals(votetemplatestring, that.votetemplatestring) && Objects
                .equals(proposalvotetemplatestring, that.proposalvotetemplatestring) && Objects
                .equals(proposalvoteconfirmationtemplatestring,
                        that.proposalvoteconfirmationtemplatestring) && Objects
                .equals(votequestiontemplatestring, that.votequestiontemplatestring) && Objects
                .equals(focusareaid, that.focusareaid) && Objects
                .equals(contesttier, that.contesttier) && Objects
                .equals(contestlogoid, that.contestlogoid) && Objects
                .equals(getFeatured_(), that.getFeatured_()) && Objects
                .equals(plansopenbydefault, that.plansopenbydefault) && Objects
                .equals(sponsorlogoid, that.sponsorlogoid) && Objects
                .equals(defaultproposallogoid, that.defaultproposallogoid) && Objects
                .equals(sponsortext, that.sponsortext) && Objects
                .equals(sponsorlink, that.sponsorlink) && Objects.equals(getFlag(), that.getFlag())
                && Objects.equals(flagtext, that.flagtext) && Objects
                .equals(flagtooltip, that.flagtooltip)
                && Objects.equals(discussiongroupid, that.discussiongroupid) && Objects
                .equals(getWeight(), that.getWeight()) && Objects
                .equals(resourcesurl, that.resourcesurl) && Objects
                .equals(contestprivate, that.contestprivate) && Objects
                .equals(usepermissions, that.usepermissions) && Objects
                .equals(contestcreationstatus, that.contestcreationstatus) && Objects
                .equals(defaultmodelid, that.defaultmodelid) && Objects
                .equals(othermodels, that.othermodels) && Objects
                .equals(defaultmodelsettings, that.defaultmodelsettings) && Objects
                .equals(getPoints(), that.getPoints()) && Objects
                .equals(defaultparentpointtype, that.defaultparentpointtype) && Objects
                .equals(pointdistributionstrategy, that.pointdistributionstrategy) && Objects
                .equals(emailtemplateurl, that.emailtemplateurl) && Objects
                .equals(showInTileView, that.showInTileView) && Objects
                .equals(showInListView, that.showInListView) && Objects
                .equals(showInOutlineView, that.showInOutlineView) && Objects
                .equals(hideribbons, that.hideribbons) && Objects
                .equals(resourcearticleid, that.resourcearticleid);
    }

    @Override
    public String toString() {

        return "Contest (" + contestpk + ", " + contesttypeid + ", " + contestname + ", "
                + contestshortname + ", " + contesturlname + ", " + contestyear + ", "
                + contestdescription + ", " + contestmodeldescription + ", "
                + contestpositionsdescription + ", " + created + ", " + updated + ", " + authorUserid
                + ", " + contestactive + ", " + plantemplateid + ", " + contestscheduleid + ", "
                + proposalcreationtemplatestring + ", " + votetemplatestring + ", "
                + proposalvotetemplatestring + ", " + proposalvoteconfirmationtemplatestring + ", "
                + votequestiontemplatestring + ", " + focusareaid + ", " + contesttier + ", "
                + contestlogoid + ", " + featured_ + ", " + plansopenbydefault + ", "
                + sponsorlogoid + ", " + sponsortext + ", " + sponsorlink + ", " + flag + ", "
                + flagtext + ", " + flagtooltip + ", " + discussiongroupid + ", " + weight + ", " + resourcesurl + ", " + contestprivate + ", " + usepermissions
                + ", " + contestcreationstatus + ", " + defaultmodelid + ", " + othermodels + ", "
                + defaultmodelsettings + ", " + points + ", " + defaultparentpointtype + ", "
                + pointdistributionstrategy + ", " + emailtemplateurl + ", " + showInTileView + ", "
                + showInListView + ", " + showInOutlineView + ", " + hideribbons + ", "
                + resourcearticleid + ")";
    }
}
