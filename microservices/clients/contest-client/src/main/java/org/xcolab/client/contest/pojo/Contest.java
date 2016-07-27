package org.xcolab.client.contest.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.core.ParameterizedTypeReference;

import org.xcolab.client.contest.ContestClient;
import org.xcolab.util.http.client.types.TypeProvider;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Contest implements Serializable {
    public static final TypeProvider<Contest> TYPES =
            new TypeProvider<>(Contest.class,
                    new ParameterizedTypeReference<List<Contest>>() {
                    });

    private static final long serialVersionUID = 914529571;

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
    private Long authorid;
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
    private String sponsortext;
    private String sponsorlink;
    private Integer flag;
    private String flagtext;
    private String flagtooltip;
    private Long groupid;
    private Long discussiongroupid;
    private Long fellowdiscussiongroupid;
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

    private Long resourceArticleId;

    public Contest() {
    }

    public Contest(Long contestpk, Long contesttypeid, String contestname, String contestshortname,
                   String contesturlname,
                   Long contestyear, String contestdescription, String contestmodeldescription,
                   String contestpositionsdescription, Timestamp created, Timestamp updated,
                   Long authorid, Boolean contestactive, Long plantemplateid, Long contestscheduleid,
                   String proposalcreationtemplatestring, String votetemplatestring,
                   String proposalvotetemplatestring, String proposalvoteconfirmationtemplatestring,
                   String votequestiontemplatestring, Long focusareaid, Long contesttier,
                   Long contestlogoid, Boolean featured_, Boolean plansopenbydefault,
                   Long sponsorlogoid, String sponsortext, String sponsorlink,
                   Integer flag, String flagtext, String flagtooltip, Long groupid,
                   Long discussiongroupid, Long fellowdiscussiongroupid, Integer weight,
                   String resourcesurl, Boolean contestprivate, Boolean usepermissions,
                   String contestcreationstatus, Long defaultmodelid, String othermodels,
                   String defaultmodelsettings, Double points, Long defaultparentpointtype,
                   String pointdistributionstrategy, String emailtemplateurl, Boolean showInTileView,
                   Boolean showInListView, Boolean showInOutlineView, Boolean hideribbons,
                   Long resourceArticleId) {
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
        this.fellowdiscussiongroupid = fellowdiscussiongroupid;
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
        this.resourceArticleId = resourceArticleId;
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

    public Long getFellowDiscussionGroupId() {
        return this.fellowdiscussiongroupid;
    }

    public void setFellowDiscussionGroupId(Long fellowdiscussiongroupid) {
        this.fellowdiscussiongroupid = fellowdiscussiongroupid;
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
        return resourceArticleId;
    }

    public void setResourceArticleId(Long resourceArticleId) {
        this.resourceArticleId = resourceArticleId;
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
                ", " + fellowdiscussiongroupid +
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
                ", " + resourceArticleId +
                ")";
    }

    public String getContestLinkUrl() {
        String link = "/";
        link += ContestClient.getContestType(this.getContestTypeId()).getFriendlyUrlStringContests();
        link += "/%d/%s";
        return String.format(link, this.getContestYear(), this.getContestUrlName());
    }
    public String getLogoPath(){
        Long i = this.getContestLogoId();
        if (i != null) {
            return "/contest?img_id=" + i;// + "&t=" + ImageServletTokenUtil.getToken(i.getImageId());
        }
        return "";
    }

}
