package org.xcolab.client.contest.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public abstract class AbstractContest implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long      contestpk;
    private Long      contesttypeid;
    private String    contestname;
    private String    contestshortname;
    private String    contesturlname;
    private Long      contestyear;
    private String    contestdescription;
    private String    contestmodeldescription;
    private String    contestpositionsdescription;
    private Timestamp created;
    private Timestamp updated;
    private Long      authorid;
    private Boolean   contestactive;
    private Long      plantemplateid;
    private Long      contestscheduleid;
    private String    proposalcreationtemplatestring;
    private String    votetemplatestring;
    private String    proposalvotetemplatestring;
    private String    proposalvoteconfirmationtemplatestring;
    private String    votequestiontemplatestring;
    private Long      focusareaid;
    private Long      contesttier;
    private Long      contestlogoid;
    private Boolean   featured_;
    private Boolean   plansopenbydefault;
    private Long      sponsorlogoid;
    private String    sponsortext;
    private String    sponsorlink;
    private Integer   flag;
    private String    flagtext;
    private String    flagtooltip;
    private Long      groupid;
    private Long      discussiongroupid;
    private Integer   weight;
    private String    resourcesurl;
    private Boolean   contestprivate;
    private Boolean   usepermissions;
    private String    contestcreationstatus;
    private Long      defaultmodelid;
    private String    othermodels;
    private String    defaultmodelsettings;
    private Double    points;
    private Long      defaultparentpointtype;
    private String    pointdistributionstrategy;
    private String    emailtemplateurl;
    private Boolean   showInTileView;
    private Boolean   showInListView;
    private Boolean   showInOutlineView;
    private Boolean   hideribbons;
    private Long      resourcearticleid;
    private Boolean   issharedcontest;
    private String    sharedorigin;

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
        this.authorid = value.authorid;
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
        this.sponsortext = value.sponsortext;
        this.sponsorlink = value.sponsorlink;
        this.flag = value.flag;
        this.flagtext = value.flagtext;
        this.flagtooltip = value.flagtooltip;
        this.groupid = value.groupid;
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
        this.issharedcontest = value.issharedcontest;
        this.sharedorigin = value.sharedorigin;
    }

    public AbstractContest(
            Long      contestpk,
            Long      contesttypeid,
            String    contestname,
            String    contestshortname,
            String    contesturlname,
            Long      contestyear,
            String    contestdescription,
            String    contestmodeldescription,
            String    contestpositionsdescription,
            Timestamp created,
            Timestamp updated,
            Long      authorid,
            Boolean   contestactive,
            Long      plantemplateid,
            Long      contestscheduleid,
            String    proposalcreationtemplatestring,
            String    votetemplatestring,
            String    proposalvotetemplatestring,
            String    proposalvoteconfirmationtemplatestring,
            String    votequestiontemplatestring,
            Long      focusareaid,
            Long      contesttier,
            Long      contestlogoid,
            Boolean   featured_,
            Boolean   plansopenbydefault,
            Long      sponsorlogoid,
            String    sponsortext,
            String    sponsorlink,
            Integer   flag,
            String    flagtext,
            String    flagtooltip,
            Long      groupid,
            Long      discussiongroupid,
            Integer   weight,
            String    resourcesurl,
            Boolean   contestprivate,
            Boolean   usepermissions,
            String    contestcreationstatus,
            Long      defaultmodelid,
            String    othermodels,
            String    defaultmodelsettings,
            Double    points,
            Long      defaultparentpointtype,
            String    pointdistributionstrategy,
            String    emailtemplateurl,
            Boolean   showInTileView,
            Boolean   showInListView,
            Boolean   showInOutlineView,
            Boolean   hideribbons,
            Long      resourcearticleid,
            Boolean   issharedcontest,
            String   sharedorigin
    ) {
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
        this.authorid = authorid;
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
        this.groupid = groupid;
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
        this.issharedcontest = issharedcontest;
        this.sharedorigin = sharedorigin;
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

    public Long getAuthorId() {
        return this.authorid;
    }

    public void setAuthorId(Long authorid) {
        this.authorid = authorid;
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

    public void setProposalVoteConfirmationTemplateString(String proposalvoteconfirmationtemplatestring) {
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

    public Boolean getFeatured_() {
        return this.featured_;
    }

    public void setFeatured_(Boolean featured_) {
        this.featured_ = featured_;
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

    public Integer getFlag() {
        return this.flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
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

    public Long getGroupId() {
        return this.groupid;
    }

    public void setGroupId(Long groupid) {
        this.groupid = groupid;
    }

    public Long getDiscussionGroupId() {
        return this.discussiongroupid;
    }

    public void setDiscussionGroupId(Long discussiongroupid) {
        this.discussiongroupid = discussiongroupid;
    }

    public Integer getWeight() {
        return this.weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
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

    public Double getPoints() {
        return this.points;
    }

    public void setPoints(Double points) {
        this.points = points;
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

    public Boolean getIsSharedContest() {
        return this.issharedcontest;
    }

    public void setIsSharedContest(Boolean issharedcontest) {
        this.issharedcontest = issharedcontest;
    }

    public String getSharedOrigin() {
        return this.sharedorigin;
    }

    public void setSharedOrigin(String sharedorigin) {
        this.sharedorigin = sharedorigin;
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
        final AbstractContest other = (AbstractContest) obj;
        if (contestpk == null) {
            if (other.contestpk != null) {
                return false;
            }
        }
        else if (!contestpk.equals(other.contestpk)) {
            return false;
        }
        if (contesttypeid == null) {
            if (other.contesttypeid != null) {
                return false;
            }
        }
        else if (!contesttypeid.equals(other.contesttypeid)) {
            return false;
        }
        if (contestname == null) {
            if (other.contestname != null) {
                return false;
            }
        }
        else if (!contestname.equals(other.contestname)) {
            return false;
        }
        if (contestshortname == null) {
            if (other.contestshortname != null) {
                return false;
            }
        }
        else if (!contestshortname.equals(other.contestshortname)) {
            return false;
        }
        if (contesturlname == null) {
            if (other.contesturlname != null) {
                return false;
            }
        }
        else if (!contesturlname.equals(other.contesturlname)) {
            return false;
        }
        if (contestyear == null) {
            if (other.contestyear != null) {
                return false;
            }
        }
        else if (!contestyear.equals(other.contestyear)) {
            return false;
        }
        if (contestdescription == null) {
            if (other.contestdescription != null) {
                return false;
            }
        }
        else if (!contestdescription.equals(other.contestdescription)) {
            return false;
        }
        if (contestmodeldescription == null) {
            if (other.contestmodeldescription != null) {
                return false;
            }
        }
        else if (!contestmodeldescription.equals(other.contestmodeldescription)) {
            return false;
        }
        if (contestpositionsdescription == null) {
            if (other.contestpositionsdescription != null) {
                return false;
            }
        }
        else if (!contestpositionsdescription.equals(other.contestpositionsdescription)) {
            return false;
        }
        if (created == null) {
            if (other.created != null) {
                return false;
            }
        }
        else if (!created.equals(other.created)) {
            return false;
        }
        if (updated == null) {
            if (other.updated != null) {
                return false;
            }
        }
        else if (!updated.equals(other.updated)) {
            return false;
        }
        if (authorid == null) {
            if (other.authorid != null) {
                return false;
            }
        }
        else if (!authorid.equals(other.authorid)) {
            return false;
        }
        if (contestactive == null) {
            if (other.contestactive != null) {
                return false;
            }
        }
        else if (!contestactive.equals(other.contestactive)) {
            return false;
        }
        if (plantemplateid == null) {
            if (other.plantemplateid != null) {
                return false;
            }
        }
        else if (!plantemplateid.equals(other.plantemplateid)) {
            return false;
        }
        if (contestscheduleid == null) {
            if (other.contestscheduleid != null) {
                return false;
            }
        }
        else if (!contestscheduleid.equals(other.contestscheduleid)) {
            return false;
        }
        if (proposalcreationtemplatestring == null) {
            if (other.proposalcreationtemplatestring != null) {
                return false;
            }
        }
        else if (!proposalcreationtemplatestring.equals(other.proposalcreationtemplatestring)) {
            return false;
        }
        if (votetemplatestring == null) {
            if (other.votetemplatestring != null) {
                return false;
            }
        }
        else if (!votetemplatestring.equals(other.votetemplatestring)) {
            return false;
        }
        if (proposalvotetemplatestring == null) {
            if (other.proposalvotetemplatestring != null) {
                return false;
            }
        }
        else if (!proposalvotetemplatestring.equals(other.proposalvotetemplatestring)) {
            return false;
        }
        if (proposalvoteconfirmationtemplatestring == null) {
            if (other.proposalvoteconfirmationtemplatestring != null) {
                return false;
            }
        }
        else if (!proposalvoteconfirmationtemplatestring.equals(other.proposalvoteconfirmationtemplatestring)) {
            return false;
        }
        if (votequestiontemplatestring == null) {
            if (other.votequestiontemplatestring != null) {
                return false;
            }
        }
        else if (!votequestiontemplatestring.equals(other.votequestiontemplatestring)) {
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
        if (contesttier == null) {
            if (other.contesttier != null) {
                return false;
            }
        }
        else if (!contesttier.equals(other.contesttier)) {
            return false;
        }
        if (contestlogoid == null) {
            if (other.contestlogoid != null) {
                return false;
            }
        }
        else if (!contestlogoid.equals(other.contestlogoid)) {
            return false;
        }
        if (featured_ == null) {
            if (other.featured_ != null) {
                return false;
            }
        }
        else if (!featured_.equals(other.featured_)) {
            return false;
        }
        if (plansopenbydefault == null) {
            if (other.plansopenbydefault != null) {
                return false;
            }
        }
        else if (!plansopenbydefault.equals(other.plansopenbydefault)) {
            return false;
        }
        if (sponsorlogoid == null) {
            if (other.sponsorlogoid != null) {
                return false;
            }
        }
        else if (!sponsorlogoid.equals(other.sponsorlogoid)) {
            return false;
        }
        if (sponsortext == null) {
            if (other.sponsortext != null) {
                return false;
            }
        }
        else if (!sponsortext.equals(other.sponsortext)) {
            return false;
        }
        if (sponsorlink == null) {
            if (other.sponsorlink != null) {
                return false;
            }
        }
        else if (!sponsorlink.equals(other.sponsorlink)) {
            return false;
        }
        if (flag == null) {
            if (other.flag != null) {
                return false;
            }
        }
        else if (!flag.equals(other.flag)) {
            return false;
        }
        if (flagtext == null) {
            if (other.flagtext != null) {
                return false;
            }
        }
        else if (!flagtext.equals(other.flagtext)) {
            return false;
        }
        if (flagtooltip == null) {
            if (other.flagtooltip != null) {
                return false;
            }
        }
        else if (!flagtooltip.equals(other.flagtooltip)) {
            return false;
        }
        if (groupid == null) {
            if (other.groupid != null) {
                return false;
            }
        }
        else if (!groupid.equals(other.groupid)) {
            return false;
        }
        if (discussiongroupid == null) {
            if (other.discussiongroupid != null) {
                return false;
            }
        }
        else if (!discussiongroupid.equals(other.discussiongroupid)) {
            return false;
        }
        if (weight == null) {
            if (other.weight != null) {
                return false;
            }
        }
        else if (!weight.equals(other.weight)) {
            return false;
        }
        if (resourcesurl == null) {
            if (other.resourcesurl != null) {
                return false;
            }
        }
        else if (!resourcesurl.equals(other.resourcesurl)) {
            return false;
        }
        if (contestprivate == null) {
            if (other.contestprivate != null) {
                return false;
            }
        }
        else if (!contestprivate.equals(other.contestprivate)) {
            return false;
        }
        if (usepermissions == null) {
            if (other.usepermissions != null) {
                return false;
            }
        }
        else if (!usepermissions.equals(other.usepermissions)) {
            return false;
        }
        if (contestcreationstatus == null) {
            if (other.contestcreationstatus != null) {
                return false;
            }
        }
        else if (!contestcreationstatus.equals(other.contestcreationstatus)) {
            return false;
        }
        if (defaultmodelid == null) {
            if (other.defaultmodelid != null) {
                return false;
            }
        }
        else if (!defaultmodelid.equals(other.defaultmodelid)) {
            return false;
        }
        if (othermodels == null) {
            if (other.othermodels != null) {
                return false;
            }
        }
        else if (!othermodels.equals(other.othermodels)) {
            return false;
        }
        if (defaultmodelsettings == null) {
            if (other.defaultmodelsettings != null) {
                return false;
            }
        }
        else if (!defaultmodelsettings.equals(other.defaultmodelsettings)) {
            return false;
        }
        if (points == null) {
            if (other.points != null) {
                return false;
            }
        }
        else if (!points.equals(other.points)) {
            return false;
        }
        if (defaultparentpointtype == null) {
            if (other.defaultparentpointtype != null) {
                return false;
            }
        }
        else if (!defaultparentpointtype.equals(other.defaultparentpointtype)) {
            return false;
        }
        if (pointdistributionstrategy == null) {
            if (other.pointdistributionstrategy != null) {
                return false;
            }
        }
        else if (!pointdistributionstrategy.equals(other.pointdistributionstrategy)) {
            return false;
        }
        if (emailtemplateurl == null) {
            if (other.emailtemplateurl != null) {
                return false;
            }
        }
        else if (!emailtemplateurl.equals(other.emailtemplateurl)) {
            return false;
        }
        if (showInTileView == null) {
            if (other.showInTileView != null) {
                return false;
            }
        }
        else if (!showInTileView.equals(other.showInTileView)) {
            return false;
        }
        if (showInListView == null) {
            if (other.showInListView != null) {
                return false;
            }
        }
        else if (!showInListView.equals(other.showInListView)) {
            return false;
        }
        if (showInOutlineView == null) {
            if (other.showInOutlineView != null) {
                return false;
            }
        }
        else if (!showInOutlineView.equals(other.showInOutlineView)) {
            return false;
        }
        if (hideribbons == null) {
            if (other.hideribbons != null) {
                return false;
            }
        }
        else if (!hideribbons.equals(other.hideribbons)) {
            return false;
        }
        if (resourcearticleid == null) {
            if (other.resourcearticleid != null) {
                return false;
            }
        }
        else if (!resourcearticleid.equals(other.resourcearticleid)) {
            return false;
        }
        if (issharedcontest == null) {
            if (other.issharedcontest != null) {
                return false;
            }
        }
        else if (!issharedcontest.equals(other.issharedcontest)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((contestpk == null) ? 0 : contestpk.hashCode());
        result = prime * result + ((contesttypeid == null) ? 0 : contesttypeid.hashCode());
        result = prime * result + ((contestname == null) ? 0 : contestname.hashCode());
        result = prime * result + ((contestshortname == null) ? 0 : contestshortname.hashCode());
        result = prime * result + ((contesturlname == null) ? 0 : contesturlname.hashCode());
        result = prime * result + ((contestyear == null) ? 0 : contestyear.hashCode());
        result = prime * result + ((contestdescription == null) ? 0 : contestdescription.hashCode());
        result = prime * result + ((contestmodeldescription == null) ? 0 : contestmodeldescription.hashCode());
        result = prime * result + ((contestpositionsdescription == null) ? 0 : contestpositionsdescription.hashCode());
        result = prime * result + ((created == null) ? 0 : created.hashCode());
        result = prime * result + ((updated == null) ? 0 : updated.hashCode());
        result = prime * result + ((authorid == null) ? 0 : authorid.hashCode());
        result = prime * result + ((contestactive == null) ? 0 : contestactive.hashCode());
        result = prime * result + ((plantemplateid == null) ? 0 : plantemplateid.hashCode());
        result = prime * result + ((contestscheduleid == null) ? 0 : contestscheduleid.hashCode());
        result = prime * result + ((proposalcreationtemplatestring == null) ? 0 : proposalcreationtemplatestring.hashCode());
        result = prime * result + ((votetemplatestring == null) ? 0 : votetemplatestring.hashCode());
        result = prime * result + ((proposalvotetemplatestring == null) ? 0 : proposalvotetemplatestring.hashCode());
        result = prime * result + ((proposalvoteconfirmationtemplatestring == null) ? 0 : proposalvoteconfirmationtemplatestring.hashCode());
        result = prime * result + ((votequestiontemplatestring == null) ? 0 : votequestiontemplatestring.hashCode());
        result = prime * result + ((focusareaid == null) ? 0 : focusareaid.hashCode());
        result = prime * result + ((contesttier == null) ? 0 : contesttier.hashCode());
        result = prime * result + ((contestlogoid == null) ? 0 : contestlogoid.hashCode());
        result = prime * result + ((featured_ == null) ? 0 : featured_.hashCode());
        result = prime * result + ((plansopenbydefault == null) ? 0 : plansopenbydefault.hashCode());
        result = prime * result + ((sponsorlogoid == null) ? 0 : sponsorlogoid.hashCode());
        result = prime * result + ((sponsortext == null) ? 0 : sponsortext.hashCode());
        result = prime * result + ((sponsorlink == null) ? 0 : sponsorlink.hashCode());
        result = prime * result + ((flag == null) ? 0 : flag.hashCode());
        result = prime * result + ((flagtext == null) ? 0 : flagtext.hashCode());
        result = prime * result + ((flagtooltip == null) ? 0 : flagtooltip.hashCode());
        result = prime * result + ((groupid == null) ? 0 : groupid.hashCode());
        result = prime * result + ((discussiongroupid == null) ? 0 : discussiongroupid.hashCode());
        result = prime * result + ((weight == null) ? 0 : weight.hashCode());
        result = prime * result + ((resourcesurl == null) ? 0 : resourcesurl.hashCode());
        result = prime * result + ((contestprivate == null) ? 0 : contestprivate.hashCode());
        result = prime * result + ((usepermissions == null) ? 0 : usepermissions.hashCode());
        result = prime * result + ((contestcreationstatus == null) ? 0 : contestcreationstatus.hashCode());
        result = prime * result + ((defaultmodelid == null) ? 0 : defaultmodelid.hashCode());
        result = prime * result + ((othermodels == null) ? 0 : othermodels.hashCode());
        result = prime * result + ((defaultmodelsettings == null) ? 0 : defaultmodelsettings.hashCode());
        result = prime * result + ((points == null) ? 0 : points.hashCode());
        result = prime * result + ((defaultparentpointtype == null) ? 0 : defaultparentpointtype.hashCode());
        result = prime * result + ((pointdistributionstrategy == null) ? 0 : pointdistributionstrategy.hashCode());
        result = prime * result + ((emailtemplateurl == null) ? 0 : emailtemplateurl.hashCode());
        result = prime * result + ((showInTileView == null) ? 0 : showInTileView.hashCode());
        result = prime * result + ((showInListView == null) ? 0 : showInListView.hashCode());
        result = prime * result + ((showInOutlineView == null) ? 0 : showInOutlineView.hashCode());
        result = prime * result + ((hideribbons == null) ? 0 : hideribbons.hashCode());
        result = prime * result + ((resourcearticleid == null) ? 0 : resourcearticleid.hashCode());
        result = prime * result + ((issharedcontest == null) ? 0 : issharedcontest.hashCode());
        return result;
    }

    @Override
    public String toString() {

        return "Contest (" + contestpk +
                ", " + contesttypeid +
                ", " + contestname +
                ", " + contestshortname +
                ", " + contesturlname +
                ", " + contestyear +
                ", " + contestdescription +
                ", " + contestmodeldescription +
                ", " + contestpositionsdescription +
                ", " + created +
                ", " + updated +
                ", " + authorid +
                ", " + contestactive +
                ", " + plantemplateid +
                ", " + contestscheduleid +
                ", " + proposalcreationtemplatestring +
                ", " + votetemplatestring +
                ", " + proposalvotetemplatestring +
                ", " + proposalvoteconfirmationtemplatestring +
                ", " + votequestiontemplatestring +
                ", " + focusareaid +
                ", " + contesttier +
                ", " + contestlogoid +
                ", " + featured_ +
                ", " + plansopenbydefault +
                ", " + sponsorlogoid +
                ", " + sponsortext +
                ", " + sponsorlink +
                ", " + flag +
                ", " + flagtext +
                ", " + flagtooltip +
                ", " + groupid +
                ", " + discussiongroupid +
                ", " + weight +
                ", " + resourcesurl +
                ", " + contestprivate +
                ", " + usepermissions +
                ", " + contestcreationstatus +
                ", " + defaultmodelid +
                ", " + othermodels +
                ", " + defaultmodelsettings +
                ", " + points +
                ", " + defaultparentpointtype +
                ", " + pointdistributionstrategy +
                ", " + emailtemplateurl +
                ", " + showInTileView +
                ", " + showInListView +
                ", " + showInOutlineView +
                ", " + hideribbons +
                ", " + resourcearticleid +
                ", " + issharedcontest +
                ")";
    }
}
