package com.ext.portlet.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.ext.portlet.service.http.ContestServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.http.ContestServiceSoap
 * @generated
 */
public class ContestSoap implements Serializable {
    private long _ContestPK;
    private String _ContestName;
    private String _ContestShortName;
    private String _ContestDescription;
    private String _ContestModelDescription;
    private String _ContestPositionsDescription;
    private String _defaultPlanDescription;
    private long _PlanTypeId;
    private Date _created;
    private Date _updated;
    private long _authorId;
    private boolean _contestActive;
    private long _planTemplateId;
    private long _contestScheduleId;
    private long _focusAreaId;
    private long _contestTier;
    private long _contestLogoId;
    private boolean _featured;
    private boolean _plansOpenByDefault;
    private long _sponsorLogoId;
    private String _sponsorText;
    private String _sponsorLink;
    private int _flag;
    private String _flagText;
    private String _flagTooltip;
    private long _groupId;
    private long _discussionGroupId;
    private int _weight;
    private String _resourcesUrl;
    private boolean _contestPrivate;
    private boolean _usePermissions;
    private String _contestCreationStatus;
    private long _defaultModelId;
    private String _otherModels;
    private double _points;
    private long _defaultParentPointType;
    private String _pointDistributionStrategy;
    private String _emailTemplateUrl;
    private boolean _show_in_tile_view;
    private boolean _show_in_list_view;
    private boolean _show_in_outline_view;

    public ContestSoap() {
    }

    public static ContestSoap toSoapModel(Contest model) {
        ContestSoap soapModel = new ContestSoap();

        soapModel.setContestPK(model.getContestPK());
        soapModel.setContestName(model.getContestName());
        soapModel.setContestShortName(model.getContestShortName());
        soapModel.setContestDescription(model.getContestDescription());
        soapModel.setContestModelDescription(model.getContestModelDescription());
        soapModel.setContestPositionsDescription(model.getContestPositionsDescription());
        soapModel.setDefaultPlanDescription(model.getDefaultPlanDescription());
        soapModel.setPlanTypeId(model.getPlanTypeId());
        soapModel.setCreated(model.getCreated());
        soapModel.setUpdated(model.getUpdated());
        soapModel.setAuthorId(model.getAuthorId());
        soapModel.setContestActive(model.getContestActive());
        soapModel.setPlanTemplateId(model.getPlanTemplateId());
        soapModel.setContestScheduleId(model.getContestScheduleId());
        soapModel.setFocusAreaId(model.getFocusAreaId());
        soapModel.setContestTier(model.getContestTier());
        soapModel.setContestLogoId(model.getContestLogoId());
        soapModel.setFeatured(model.getFeatured());
        soapModel.setPlansOpenByDefault(model.getPlansOpenByDefault());
        soapModel.setSponsorLogoId(model.getSponsorLogoId());
        soapModel.setSponsorText(model.getSponsorText());
        soapModel.setSponsorLink(model.getSponsorLink());
        soapModel.setFlag(model.getFlag());
        soapModel.setFlagText(model.getFlagText());
        soapModel.setFlagTooltip(model.getFlagTooltip());
        soapModel.setGroupId(model.getGroupId());
        soapModel.setDiscussionGroupId(model.getDiscussionGroupId());
        soapModel.setWeight(model.getWeight());
        soapModel.setResourcesUrl(model.getResourcesUrl());
        soapModel.setContestPrivate(model.getContestPrivate());
        soapModel.setUsePermissions(model.getUsePermissions());
        soapModel.setContestCreationStatus(model.getContestCreationStatus());
        soapModel.setDefaultModelId(model.getDefaultModelId());
        soapModel.setOtherModels(model.getOtherModels());
        soapModel.setPoints(model.getPoints());
        soapModel.setDefaultParentPointType(model.getDefaultParentPointType());
        soapModel.setPointDistributionStrategy(model.getPointDistributionStrategy());
        soapModel.setEmailTemplateUrl(model.getEmailTemplateUrl());
        soapModel.setShow_in_tile_view(model.getShow_in_tile_view());
        soapModel.setShow_in_list_view(model.getShow_in_list_view());
        soapModel.setShow_in_outline_view(model.getShow_in_outline_view());

        return soapModel;
    }

    public static ContestSoap[] toSoapModels(Contest[] models) {
        ContestSoap[] soapModels = new ContestSoap[models.length];

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModel(models[i]);
        }

        return soapModels;
    }

    public static ContestSoap[][] toSoapModels(Contest[][] models) {
        ContestSoap[][] soapModels = null;

        if (models.length > 0) {
            soapModels = new ContestSoap[models.length][models[0].length];
        } else {
            soapModels = new ContestSoap[0][0];
        }

        for (int i = 0; i < models.length; i++) {
            soapModels[i] = toSoapModels(models[i]);
        }

        return soapModels;
    }

    public static ContestSoap[] toSoapModels(List<Contest> models) {
        List<ContestSoap> soapModels = new ArrayList<ContestSoap>(models.size());

        for (Contest model : models) {
            soapModels.add(toSoapModel(model));
        }

        return soapModels.toArray(new ContestSoap[soapModels.size()]);
    }

    public long getPrimaryKey() {
        return _ContestPK;
    }

    public void setPrimaryKey(long pk) {
        setContestPK(pk);
    }

    public long getContestPK() {
        return _ContestPK;
    }

    public void setContestPK(long ContestPK) {
        _ContestPK = ContestPK;
    }

    public String getContestName() {
        return _ContestName;
    }

    public void setContestName(String ContestName) {
        _ContestName = ContestName;
    }

    public String getContestShortName() {
        return _ContestShortName;
    }

    public void setContestShortName(String ContestShortName) {
        _ContestShortName = ContestShortName;
    }

    public String getContestDescription() {
        return _ContestDescription;
    }

    public void setContestDescription(String ContestDescription) {
        _ContestDescription = ContestDescription;
    }

    public String getContestModelDescription() {
        return _ContestModelDescription;
    }

    public void setContestModelDescription(String ContestModelDescription) {
        _ContestModelDescription = ContestModelDescription;
    }

    public String getContestPositionsDescription() {
        return _ContestPositionsDescription;
    }

    public void setContestPositionsDescription(
        String ContestPositionsDescription) {
        _ContestPositionsDescription = ContestPositionsDescription;
    }

    public String getDefaultPlanDescription() {
        return _defaultPlanDescription;
    }

    public void setDefaultPlanDescription(String defaultPlanDescription) {
        _defaultPlanDescription = defaultPlanDescription;
    }

    public long getPlanTypeId() {
        return _PlanTypeId;
    }

    public void setPlanTypeId(long PlanTypeId) {
        _PlanTypeId = PlanTypeId;
    }

    public Date getCreated() {
        return _created;
    }

    public void setCreated(Date created) {
        _created = created;
    }

    public Date getUpdated() {
        return _updated;
    }

    public void setUpdated(Date updated) {
        _updated = updated;
    }

    public long getAuthorId() {
        return _authorId;
    }

    public void setAuthorId(long authorId) {
        _authorId = authorId;
    }

    public boolean getContestActive() {
        return _contestActive;
    }

    public boolean isContestActive() {
        return _contestActive;
    }

    public void setContestActive(boolean contestActive) {
        _contestActive = contestActive;
    }

    public long getPlanTemplateId() {
        return _planTemplateId;
    }

    public void setPlanTemplateId(long planTemplateId) {
        _planTemplateId = planTemplateId;
    }

    public long getContestScheduleId() {
        return _contestScheduleId;
    }

    public void setContestScheduleId(long contestScheduleId) {
        _contestScheduleId = contestScheduleId;
    }

    public long getFocusAreaId() {
        return _focusAreaId;
    }

    public void setFocusAreaId(long focusAreaId) {
        _focusAreaId = focusAreaId;
    }

    public long getContestTier() {
        return _contestTier;
    }

    public void setContestTier(long contestTier) {
        _contestTier = contestTier;
    }

    public long getContestLogoId() {
        return _contestLogoId;
    }

    public void setContestLogoId(long contestLogoId) {
        _contestLogoId = contestLogoId;
    }

    public boolean getFeatured() {
        return _featured;
    }

    public boolean isFeatured() {
        return _featured;
    }

    public void setFeatured(boolean featured) {
        _featured = featured;
    }

    public boolean getPlansOpenByDefault() {
        return _plansOpenByDefault;
    }

    public boolean isPlansOpenByDefault() {
        return _plansOpenByDefault;
    }

    public void setPlansOpenByDefault(boolean plansOpenByDefault) {
        _plansOpenByDefault = plansOpenByDefault;
    }

    public long getSponsorLogoId() {
        return _sponsorLogoId;
    }

    public void setSponsorLogoId(long sponsorLogoId) {
        _sponsorLogoId = sponsorLogoId;
    }

    public String getSponsorText() {
        return _sponsorText;
    }

    public void setSponsorText(String sponsorText) {
        _sponsorText = sponsorText;
    }

    public String getSponsorLink() {
        return _sponsorLink;
    }

    public void setSponsorLink(String sponsorLink) {
        _sponsorLink = sponsorLink;
    }

    public int getFlag() {
        return _flag;
    }

    public void setFlag(int flag) {
        _flag = flag;
    }

    public String getFlagText() {
        return _flagText;
    }

    public void setFlagText(String flagText) {
        _flagText = flagText;
    }

    public String getFlagTooltip() {
        return _flagTooltip;
    }

    public void setFlagTooltip(String flagTooltip) {
        _flagTooltip = flagTooltip;
    }

    public long getGroupId() {
        return _groupId;
    }

    public void setGroupId(long groupId) {
        _groupId = groupId;
    }

    public long getDiscussionGroupId() {
        return _discussionGroupId;
    }

    public void setDiscussionGroupId(long discussionGroupId) {
        _discussionGroupId = discussionGroupId;
    }

    public int getWeight() {
        return _weight;
    }

    public void setWeight(int weight) {
        _weight = weight;
    }

    public String getResourcesUrl() {
        return _resourcesUrl;
    }

    public void setResourcesUrl(String resourcesUrl) {
        _resourcesUrl = resourcesUrl;
    }

    public boolean getContestPrivate() {
        return _contestPrivate;
    }

    public boolean isContestPrivate() {
        return _contestPrivate;
    }

    public void setContestPrivate(boolean contestPrivate) {
        _contestPrivate = contestPrivate;
    }

    public boolean getUsePermissions() {
        return _usePermissions;
    }

    public boolean isUsePermissions() {
        return _usePermissions;
    }

    public void setUsePermissions(boolean usePermissions) {
        _usePermissions = usePermissions;
    }

    public String getContestCreationStatus() {
        return _contestCreationStatus;
    }

    public void setContestCreationStatus(String contestCreationStatus) {
        _contestCreationStatus = contestCreationStatus;
    }

    public long getDefaultModelId() {
        return _defaultModelId;
    }

    public void setDefaultModelId(long defaultModelId) {
        _defaultModelId = defaultModelId;
    }

    public String getOtherModels() {
        return _otherModels;
    }

    public void setOtherModels(String otherModels) {
        _otherModels = otherModels;
    }

    public double getPoints() {
        return _points;
    }

    public void setPoints(double points) {
        _points = points;
    }

    public long getDefaultParentPointType() {
        return _defaultParentPointType;
    }

    public void setDefaultParentPointType(long defaultParentPointType) {
        _defaultParentPointType = defaultParentPointType;
    }

    public String getPointDistributionStrategy() {
        return _pointDistributionStrategy;
    }

    public void setPointDistributionStrategy(String pointDistributionStrategy) {
        _pointDistributionStrategy = pointDistributionStrategy;
    }

    public String getEmailTemplateUrl() {
        return _emailTemplateUrl;
    }

    public void setEmailTemplateUrl(String emailTemplateUrl) {
        _emailTemplateUrl = emailTemplateUrl;
    }

    public boolean getShow_in_tile_view() {
        return _show_in_tile_view;
    }

    public boolean isShow_in_tile_view() {
        return _show_in_tile_view;
    }

    public void setShow_in_tile_view(boolean show_in_tile_view) {
        _show_in_tile_view = show_in_tile_view;
    }

    public boolean getShow_in_list_view() {
        return _show_in_list_view;
    }

    public boolean isShow_in_list_view() {
        return _show_in_list_view;
    }

    public void setShow_in_list_view(boolean show_in_list_view) {
        _show_in_list_view = show_in_list_view;
    }

    public boolean getShow_in_outline_view() {
        return _show_in_outline_view;
    }

    public boolean isShow_in_outline_view() {
        return _show_in_outline_view;
    }

    public void setShow_in_outline_view(boolean show_in_outline_view) {
        _show_in_outline_view = show_in_outline_view;
    }
}
