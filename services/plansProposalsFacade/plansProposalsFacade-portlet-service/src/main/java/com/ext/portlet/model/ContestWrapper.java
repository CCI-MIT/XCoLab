package com.ext.portlet.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Contest}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Contest
 * @generated
 */
public class ContestWrapper implements Contest, ModelWrapper<Contest> {
    private Contest _contest;

    public ContestWrapper(Contest contest) {
        _contest = contest;
    }

    @Override
    public Class<?> getModelClass() {
        return Contest.class;
    }

    @Override
    public String getModelClassName() {
        return Contest.class.getName();
    }

    @Override
    public Map<String, Object> getModelAttributes() {
        Map<String, Object> attributes = new HashMap<String, Object>();

        attributes.put("ContestPK", getContestPK());
        attributes.put("ContestName", getContestName());
        attributes.put("ContestShortName", getContestShortName());
        attributes.put("ContestDescription", getContestDescription());
        attributes.put("ContestModelDescription", getContestModelDescription());
        attributes.put("ContestPositionsDescription",
            getContestPositionsDescription());
        attributes.put("defaultPlanDescription", getDefaultPlanDescription());
        attributes.put("PlanTypeId", getPlanTypeId());
        attributes.put("created", getCreated());
        attributes.put("updated", getUpdated());
        attributes.put("authorId", getAuthorId());
        attributes.put("contestActive", getContestActive());
        attributes.put("planTemplateId", getPlanTemplateId());
        attributes.put("contestScheduleId", getContestScheduleId());
        attributes.put("focusAreaId", getFocusAreaId());
        attributes.put("contestTier", getContestTier());
        attributes.put("contestLogoId", getContestLogoId());
        attributes.put("featured", getFeatured());
        attributes.put("plansOpenByDefault", getPlansOpenByDefault());
        attributes.put("sponsorLogoId", getSponsorLogoId());
        attributes.put("sponsorText", getSponsorText());
        attributes.put("sponsorLink", getSponsorLink());
        attributes.put("flag", getFlag());
        attributes.put("flagText", getFlagText());
        attributes.put("flagTooltip", getFlagTooltip());
        attributes.put("groupId", getGroupId());
        attributes.put("discussionGroupId", getDiscussionGroupId());
        attributes.put("weight", getWeight());
        attributes.put("resourcesUrl", getResourcesUrl());
        attributes.put("contestPrivate", getContestPrivate());
        attributes.put("usePermissions", getUsePermissions());
        attributes.put("contestCreationStatus", getContestCreationStatus());
        attributes.put("defaultModelId", getDefaultModelId());
        attributes.put("otherModels", getOtherModels());
        attributes.put("points", getPoints());
        attributes.put("defaultParentPointType", getDefaultParentPointType());
        attributes.put("pointDistributionStrategy",
            getPointDistributionStrategy());
        attributes.put("emailTemplateUrl", getEmailTemplateUrl());
        attributes.put("show_in_tile_view", getShow_in_tile_view());
        attributes.put("show_in_list_view", getShow_in_list_view());
        attributes.put("show_in_outline_view", getShow_in_outline_view());

        return attributes;
    }

    @Override
    public void setModelAttributes(Map<String, Object> attributes) {
        Long ContestPK = (Long) attributes.get("ContestPK");

        if (ContestPK != null) {
            setContestPK(ContestPK);
        }

        String ContestName = (String) attributes.get("ContestName");

        if (ContestName != null) {
            setContestName(ContestName);
        }

        String ContestShortName = (String) attributes.get("ContestShortName");

        if (ContestShortName != null) {
            setContestShortName(ContestShortName);
        }

        String ContestDescription = (String) attributes.get(
                "ContestDescription");

        if (ContestDescription != null) {
            setContestDescription(ContestDescription);
        }

        String ContestModelDescription = (String) attributes.get(
                "ContestModelDescription");

        if (ContestModelDescription != null) {
            setContestModelDescription(ContestModelDescription);
        }

        String ContestPositionsDescription = (String) attributes.get(
                "ContestPositionsDescription");

        if (ContestPositionsDescription != null) {
            setContestPositionsDescription(ContestPositionsDescription);
        }

        String defaultPlanDescription = (String) attributes.get(
                "defaultPlanDescription");

        if (defaultPlanDescription != null) {
            setDefaultPlanDescription(defaultPlanDescription);
        }

        Long PlanTypeId = (Long) attributes.get("PlanTypeId");

        if (PlanTypeId != null) {
            setPlanTypeId(PlanTypeId);
        }

        Date created = (Date) attributes.get("created");

        if (created != null) {
            setCreated(created);
        }

        Date updated = (Date) attributes.get("updated");

        if (updated != null) {
            setUpdated(updated);
        }

        Long authorId = (Long) attributes.get("authorId");

        if (authorId != null) {
            setAuthorId(authorId);
        }

        Boolean contestActive = (Boolean) attributes.get("contestActive");

        if (contestActive != null) {
            setContestActive(contestActive);
        }

        Long planTemplateId = (Long) attributes.get("planTemplateId");

        if (planTemplateId != null) {
            setPlanTemplateId(planTemplateId);
        }

        Long contestScheduleId = (Long) attributes.get("contestScheduleId");

        if (contestScheduleId != null) {
            setContestScheduleId(contestScheduleId);
        }

        Long focusAreaId = (Long) attributes.get("focusAreaId");

        if (focusAreaId != null) {
            setFocusAreaId(focusAreaId);
        }

        Long contestTier = (Long) attributes.get("contestTier");

        if (contestTier != null) {
            setContestTier(contestTier);
        }

        Long contestLogoId = (Long) attributes.get("contestLogoId");

        if (contestLogoId != null) {
            setContestLogoId(contestLogoId);
        }

        Boolean featured = (Boolean) attributes.get("featured");

        if (featured != null) {
            setFeatured(featured);
        }

        Boolean plansOpenByDefault = (Boolean) attributes.get(
                "plansOpenByDefault");

        if (plansOpenByDefault != null) {
            setPlansOpenByDefault(plansOpenByDefault);
        }

        Long sponsorLogoId = (Long) attributes.get("sponsorLogoId");

        if (sponsorLogoId != null) {
            setSponsorLogoId(sponsorLogoId);
        }

        String sponsorText = (String) attributes.get("sponsorText");

        if (sponsorText != null) {
            setSponsorText(sponsorText);
        }

        String sponsorLink = (String) attributes.get("sponsorLink");

        if (sponsorLink != null) {
            setSponsorLink(sponsorLink);
        }

        Integer flag = (Integer) attributes.get("flag");

        if (flag != null) {
            setFlag(flag);
        }

        String flagText = (String) attributes.get("flagText");

        if (flagText != null) {
            setFlagText(flagText);
        }

        String flagTooltip = (String) attributes.get("flagTooltip");

        if (flagTooltip != null) {
            setFlagTooltip(flagTooltip);
        }

        Long groupId = (Long) attributes.get("groupId");

        if (groupId != null) {
            setGroupId(groupId);
        }

        Long discussionGroupId = (Long) attributes.get("discussionGroupId");

        if (discussionGroupId != null) {
            setDiscussionGroupId(discussionGroupId);
        }

        Integer weight = (Integer) attributes.get("weight");

        if (weight != null) {
            setWeight(weight);
        }

        String resourcesUrl = (String) attributes.get("resourcesUrl");

        if (resourcesUrl != null) {
            setResourcesUrl(resourcesUrl);
        }

        Boolean contestPrivate = (Boolean) attributes.get("contestPrivate");

        if (contestPrivate != null) {
            setContestPrivate(contestPrivate);
        }

        Boolean usePermissions = (Boolean) attributes.get("usePermissions");

        if (usePermissions != null) {
            setUsePermissions(usePermissions);
        }

        String contestCreationStatus = (String) attributes.get(
                "contestCreationStatus");

        if (contestCreationStatus != null) {
            setContestCreationStatus(contestCreationStatus);
        }

        Long defaultModelId = (Long) attributes.get("defaultModelId");

        if (defaultModelId != null) {
            setDefaultModelId(defaultModelId);
        }

        String otherModels = (String) attributes.get("otherModels");

        if (otherModels != null) {
            setOtherModels(otherModels);
        }

        Double points = (Double) attributes.get("points");

        if (points != null) {
            setPoints(points);
        }

        Long defaultParentPointType = (Long) attributes.get(
                "defaultParentPointType");

        if (defaultParentPointType != null) {
            setDefaultParentPointType(defaultParentPointType);
        }

        String pointDistributionStrategy = (String) attributes.get(
                "pointDistributionStrategy");

        if (pointDistributionStrategy != null) {
            setPointDistributionStrategy(pointDistributionStrategy);
        }

        String emailTemplateUrl = (String) attributes.get("emailTemplateUrl");

        if (emailTemplateUrl != null) {
            setEmailTemplateUrl(emailTemplateUrl);
        }

        Boolean show_in_tile_view = (Boolean) attributes.get(
                "show_in_tile_view");

        if (show_in_tile_view != null) {
            setShow_in_tile_view(show_in_tile_view);
        }

        Boolean show_in_list_view = (Boolean) attributes.get(
                "show_in_list_view");

        if (show_in_list_view != null) {
            setShow_in_list_view(show_in_list_view);
        }

        Boolean show_in_outline_view = (Boolean) attributes.get(
                "show_in_outline_view");

        if (show_in_outline_view != null) {
            setShow_in_outline_view(show_in_outline_view);
        }
    }

    /**
    * Returns the primary key of this contest.
    *
    * @return the primary key of this contest
    */
    @Override
    public long getPrimaryKey() {
        return _contest.getPrimaryKey();
    }

    /**
    * Sets the primary key of this contest.
    *
    * @param primaryKey the primary key of this contest
    */
    @Override
    public void setPrimaryKey(long primaryKey) {
        _contest.setPrimaryKey(primaryKey);
    }

    /**
    * Returns the contest p k of this contest.
    *
    * @return the contest p k of this contest
    */
    @Override
    public long getContestPK() {
        return _contest.getContestPK();
    }

    /**
    * Sets the contest p k of this contest.
    *
    * @param ContestPK the contest p k of this contest
    */
    @Override
    public void setContestPK(long ContestPK) {
        _contest.setContestPK(ContestPK);
    }

    /**
    * Returns the contest name of this contest.
    *
    * @return the contest name of this contest
    */
    @Override
    public java.lang.String getContestName() {
        return _contest.getContestName();
    }

    /**
    * Sets the contest name of this contest.
    *
    * @param ContestName the contest name of this contest
    */
    @Override
    public void setContestName(java.lang.String ContestName) {
        _contest.setContestName(ContestName);
    }

    /**
    * Returns the contest short name of this contest.
    *
    * @return the contest short name of this contest
    */
    @Override
    public java.lang.String getContestShortName() {
        return _contest.getContestShortName();
    }

    /**
    * Sets the contest short name of this contest.
    *
    * @param ContestShortName the contest short name of this contest
    */
    @Override
    public void setContestShortName(java.lang.String ContestShortName) {
        _contest.setContestShortName(ContestShortName);
    }

    /**
    * Returns the contest description of this contest.
    *
    * @return the contest description of this contest
    */
    @Override
    public java.lang.String getContestDescription() {
        return _contest.getContestDescription();
    }

    /**
    * Sets the contest description of this contest.
    *
    * @param ContestDescription the contest description of this contest
    */
    @Override
    public void setContestDescription(java.lang.String ContestDescription) {
        _contest.setContestDescription(ContestDescription);
    }

    /**
    * Returns the contest model description of this contest.
    *
    * @return the contest model description of this contest
    */
    @Override
    public java.lang.String getContestModelDescription() {
        return _contest.getContestModelDescription();
    }

    /**
    * Sets the contest model description of this contest.
    *
    * @param ContestModelDescription the contest model description of this contest
    */
    @Override
    public void setContestModelDescription(
        java.lang.String ContestModelDescription) {
        _contest.setContestModelDescription(ContestModelDescription);
    }

    /**
    * Returns the contest positions description of this contest.
    *
    * @return the contest positions description of this contest
    */
    @Override
    public java.lang.String getContestPositionsDescription() {
        return _contest.getContestPositionsDescription();
    }

    /**
    * Sets the contest positions description of this contest.
    *
    * @param ContestPositionsDescription the contest positions description of this contest
    */
    @Override
    public void setContestPositionsDescription(
        java.lang.String ContestPositionsDescription) {
        _contest.setContestPositionsDescription(ContestPositionsDescription);
    }

    /**
    * Returns the default plan description of this contest.
    *
    * @return the default plan description of this contest
    */
    @Override
    public java.lang.String getDefaultPlanDescription() {
        return _contest.getDefaultPlanDescription();
    }

    /**
    * Sets the default plan description of this contest.
    *
    * @param defaultPlanDescription the default plan description of this contest
    */
    @Override
    public void setDefaultPlanDescription(
        java.lang.String defaultPlanDescription) {
        _contest.setDefaultPlanDescription(defaultPlanDescription);
    }

    /**
    * Returns the plan type ID of this contest.
    *
    * @return the plan type ID of this contest
    */
    @Override
    public long getPlanTypeId() {
        return _contest.getPlanTypeId();
    }

    /**
    * Sets the plan type ID of this contest.
    *
    * @param PlanTypeId the plan type ID of this contest
    */
    @Override
    public void setPlanTypeId(long PlanTypeId) {
        _contest.setPlanTypeId(PlanTypeId);
    }

    /**
    * Returns the created of this contest.
    *
    * @return the created of this contest
    */
    @Override
    public java.util.Date getCreated() {
        return _contest.getCreated();
    }

    /**
    * Sets the created of this contest.
    *
    * @param created the created of this contest
    */
    @Override
    public void setCreated(java.util.Date created) {
        _contest.setCreated(created);
    }

    /**
    * Returns the updated of this contest.
    *
    * @return the updated of this contest
    */
    @Override
    public java.util.Date getUpdated() {
        return _contest.getUpdated();
    }

    /**
    * Sets the updated of this contest.
    *
    * @param updated the updated of this contest
    */
    @Override
    public void setUpdated(java.util.Date updated) {
        _contest.setUpdated(updated);
    }

    /**
    * Returns the author ID of this contest.
    *
    * @return the author ID of this contest
    */
    @Override
    public long getAuthorId() {
        return _contest.getAuthorId();
    }

    /**
    * Sets the author ID of this contest.
    *
    * @param authorId the author ID of this contest
    */
    @Override
    public void setAuthorId(long authorId) {
        _contest.setAuthorId(authorId);
    }

    /**
    * Returns the contest active of this contest.
    *
    * @return the contest active of this contest
    */
    @Override
    public boolean getContestActive() {
        return _contest.getContestActive();
    }

    /**
    * Returns <code>true</code> if this contest is contest active.
    *
    * @return <code>true</code> if this contest is contest active; <code>false</code> otherwise
    */
    @Override
    public boolean isContestActive() {
        return _contest.isContestActive();
    }

    /**
    * Sets whether this contest is contest active.
    *
    * @param contestActive the contest active of this contest
    */
    @Override
    public void setContestActive(boolean contestActive) {
        _contest.setContestActive(contestActive);
    }

    /**
    * Returns the plan template ID of this contest.
    *
    * @return the plan template ID of this contest
    */
    @Override
    public long getPlanTemplateId() {
        return _contest.getPlanTemplateId();
    }

    /**
    * Sets the plan template ID of this contest.
    *
    * @param planTemplateId the plan template ID of this contest
    */
    @Override
    public void setPlanTemplateId(long planTemplateId) {
        _contest.setPlanTemplateId(planTemplateId);
    }

    /**
    * Returns the contest schedule ID of this contest.
    *
    * @return the contest schedule ID of this contest
    */
    @Override
    public long getContestScheduleId() {
        return _contest.getContestScheduleId();
    }

    /**
    * Sets the contest schedule ID of this contest.
    *
    * @param contestScheduleId the contest schedule ID of this contest
    */
    @Override
    public void setContestScheduleId(long contestScheduleId) {
        _contest.setContestScheduleId(contestScheduleId);
    }

    /**
    * Returns the focus area ID of this contest.
    *
    * @return the focus area ID of this contest
    */
    @Override
    public long getFocusAreaId() {
        return _contest.getFocusAreaId();
    }

    /**
    * Sets the focus area ID of this contest.
    *
    * @param focusAreaId the focus area ID of this contest
    */
    @Override
    public void setFocusAreaId(long focusAreaId) {
        _contest.setFocusAreaId(focusAreaId);
    }

    /**
    * Returns the contest tier of this contest.
    *
    * @return the contest tier of this contest
    */
    @Override
    public long getContestTier() {
        return _contest.getContestTier();
    }

    /**
    * Sets the contest tier of this contest.
    *
    * @param contestTier the contest tier of this contest
    */
    @Override
    public void setContestTier(long contestTier) {
        _contest.setContestTier(contestTier);
    }

    /**
    * Returns the contest logo ID of this contest.
    *
    * @return the contest logo ID of this contest
    */
    @Override
    public long getContestLogoId() {
        return _contest.getContestLogoId();
    }

    /**
    * Sets the contest logo ID of this contest.
    *
    * @param contestLogoId the contest logo ID of this contest
    */
    @Override
    public void setContestLogoId(long contestLogoId) {
        _contest.setContestLogoId(contestLogoId);
    }

    /**
    * Returns the featured of this contest.
    *
    * @return the featured of this contest
    */
    @Override
    public boolean getFeatured() {
        return _contest.getFeatured();
    }

    /**
    * Returns <code>true</code> if this contest is featured.
    *
    * @return <code>true</code> if this contest is featured; <code>false</code> otherwise
    */
    @Override
    public boolean isFeatured() {
        return _contest.isFeatured();
    }

    /**
    * Sets whether this contest is featured.
    *
    * @param featured the featured of this contest
    */
    @Override
    public void setFeatured(boolean featured) {
        _contest.setFeatured(featured);
    }

    /**
    * Returns the plans open by default of this contest.
    *
    * @return the plans open by default of this contest
    */
    @Override
    public boolean getPlansOpenByDefault() {
        return _contest.getPlansOpenByDefault();
    }

    /**
    * Returns <code>true</code> if this contest is plans open by default.
    *
    * @return <code>true</code> if this contest is plans open by default; <code>false</code> otherwise
    */
    @Override
    public boolean isPlansOpenByDefault() {
        return _contest.isPlansOpenByDefault();
    }

    /**
    * Sets whether this contest is plans open by default.
    *
    * @param plansOpenByDefault the plans open by default of this contest
    */
    @Override
    public void setPlansOpenByDefault(boolean plansOpenByDefault) {
        _contest.setPlansOpenByDefault(plansOpenByDefault);
    }

    /**
    * Returns the sponsor logo ID of this contest.
    *
    * @return the sponsor logo ID of this contest
    */
    @Override
    public long getSponsorLogoId() {
        return _contest.getSponsorLogoId();
    }

    /**
    * Sets the sponsor logo ID of this contest.
    *
    * @param sponsorLogoId the sponsor logo ID of this contest
    */
    @Override
    public void setSponsorLogoId(long sponsorLogoId) {
        _contest.setSponsorLogoId(sponsorLogoId);
    }

    /**
    * Returns the sponsor text of this contest.
    *
    * @return the sponsor text of this contest
    */
    @Override
    public java.lang.String getSponsorText() {
        return _contest.getSponsorText();
    }

    /**
    * Sets the sponsor text of this contest.
    *
    * @param sponsorText the sponsor text of this contest
    */
    @Override
    public void setSponsorText(java.lang.String sponsorText) {
        _contest.setSponsorText(sponsorText);
    }

    /**
    * Returns the sponsor link of this contest.
    *
    * @return the sponsor link of this contest
    */
    @Override
    public java.lang.String getSponsorLink() {
        return _contest.getSponsorLink();
    }

    /**
    * Sets the sponsor link of this contest.
    *
    * @param sponsorLink the sponsor link of this contest
    */
    @Override
    public void setSponsorLink(java.lang.String sponsorLink) {
        _contest.setSponsorLink(sponsorLink);
    }

    /**
    * Returns the flag of this contest.
    *
    * @return the flag of this contest
    */
    @Override
    public int getFlag() {
        return _contest.getFlag();
    }

    /**
    * Sets the flag of this contest.
    *
    * @param flag the flag of this contest
    */
    @Override
    public void setFlag(int flag) {
        _contest.setFlag(flag);
    }

    /**
    * Returns the flag text of this contest.
    *
    * @return the flag text of this contest
    */
    @Override
    public java.lang.String getFlagText() {
        return _contest.getFlagText();
    }

    /**
    * Sets the flag text of this contest.
    *
    * @param flagText the flag text of this contest
    */
    @Override
    public void setFlagText(java.lang.String flagText) {
        _contest.setFlagText(flagText);
    }

    /**
    * Returns the flag tooltip of this contest.
    *
    * @return the flag tooltip of this contest
    */
    @Override
    public java.lang.String getFlagTooltip() {
        return _contest.getFlagTooltip();
    }

    /**
    * Sets the flag tooltip of this contest.
    *
    * @param flagTooltip the flag tooltip of this contest
    */
    @Override
    public void setFlagTooltip(java.lang.String flagTooltip) {
        _contest.setFlagTooltip(flagTooltip);
    }

    /**
    * Returns the group ID of this contest.
    *
    * @return the group ID of this contest
    */
    @Override
    public long getGroupId() {
        return _contest.getGroupId();
    }

    /**
    * Sets the group ID of this contest.
    *
    * @param groupId the group ID of this contest
    */
    @Override
    public void setGroupId(long groupId) {
        _contest.setGroupId(groupId);
    }

    /**
    * Returns the discussion group ID of this contest.
    *
    * @return the discussion group ID of this contest
    */
    @Override
    public long getDiscussionGroupId() {
        return _contest.getDiscussionGroupId();
    }

    /**
    * Sets the discussion group ID of this contest.
    *
    * @param discussionGroupId the discussion group ID of this contest
    */
    @Override
    public void setDiscussionGroupId(long discussionGroupId) {
        _contest.setDiscussionGroupId(discussionGroupId);
    }

    /**
    * Returns the weight of this contest.
    *
    * @return the weight of this contest
    */
    @Override
    public int getWeight() {
        return _contest.getWeight();
    }

    /**
    * Sets the weight of this contest.
    *
    * @param weight the weight of this contest
    */
    @Override
    public void setWeight(int weight) {
        _contest.setWeight(weight);
    }

    /**
    * Returns the resources url of this contest.
    *
    * @return the resources url of this contest
    */
    @Override
    public java.lang.String getResourcesUrl() {
        return _contest.getResourcesUrl();
    }

    /**
    * Sets the resources url of this contest.
    *
    * @param resourcesUrl the resources url of this contest
    */
    @Override
    public void setResourcesUrl(java.lang.String resourcesUrl) {
        _contest.setResourcesUrl(resourcesUrl);
    }

    /**
    * Returns the contest private of this contest.
    *
    * @return the contest private of this contest
    */
    @Override
    public boolean getContestPrivate() {
        return _contest.getContestPrivate();
    }

    /**
    * Returns <code>true</code> if this contest is contest private.
    *
    * @return <code>true</code> if this contest is contest private; <code>false</code> otherwise
    */
    @Override
    public boolean isContestPrivate() {
        return _contest.isContestPrivate();
    }

    /**
    * Sets whether this contest is contest private.
    *
    * @param contestPrivate the contest private of this contest
    */
    @Override
    public void setContestPrivate(boolean contestPrivate) {
        _contest.setContestPrivate(contestPrivate);
    }

    /**
    * Returns the use permissions of this contest.
    *
    * @return the use permissions of this contest
    */
    @Override
    public boolean getUsePermissions() {
        return _contest.getUsePermissions();
    }

    /**
    * Returns <code>true</code> if this contest is use permissions.
    *
    * @return <code>true</code> if this contest is use permissions; <code>false</code> otherwise
    */
    @Override
    public boolean isUsePermissions() {
        return _contest.isUsePermissions();
    }

    /**
    * Sets whether this contest is use permissions.
    *
    * @param usePermissions the use permissions of this contest
    */
    @Override
    public void setUsePermissions(boolean usePermissions) {
        _contest.setUsePermissions(usePermissions);
    }

    /**
    * Returns the contest creation status of this contest.
    *
    * @return the contest creation status of this contest
    */
    @Override
    public java.lang.String getContestCreationStatus() {
        return _contest.getContestCreationStatus();
    }

    /**
    * Sets the contest creation status of this contest.
    *
    * @param contestCreationStatus the contest creation status of this contest
    */
    @Override
    public void setContestCreationStatus(java.lang.String contestCreationStatus) {
        _contest.setContestCreationStatus(contestCreationStatus);
    }

    /**
    * Returns the default model ID of this contest.
    *
    * @return the default model ID of this contest
    */
    @Override
    public long getDefaultModelId() {
        return _contest.getDefaultModelId();
    }

    /**
    * Sets the default model ID of this contest.
    *
    * @param defaultModelId the default model ID of this contest
    */
    @Override
    public void setDefaultModelId(long defaultModelId) {
        _contest.setDefaultModelId(defaultModelId);
    }

    /**
    * Returns the other models of this contest.
    *
    * @return the other models of this contest
    */
    @Override
    public java.lang.String getOtherModels() {
        return _contest.getOtherModels();
    }

    /**
    * Sets the other models of this contest.
    *
    * @param otherModels the other models of this contest
    */
    @Override
    public void setOtherModels(java.lang.String otherModels) {
        _contest.setOtherModels(otherModels);
    }

    /**
    * Returns the points of this contest.
    *
    * @return the points of this contest
    */
    @Override
    public double getPoints() {
        return _contest.getPoints();
    }

    /**
    * Sets the points of this contest.
    *
    * @param points the points of this contest
    */
    @Override
    public void setPoints(double points) {
        _contest.setPoints(points);
    }

    /**
    * Returns the default parent point type of this contest.
    *
    * @return the default parent point type of this contest
    */
    @Override
    public long getDefaultParentPointType() {
        return _contest.getDefaultParentPointType();
    }

    /**
    * Sets the default parent point type of this contest.
    *
    * @param defaultParentPointType the default parent point type of this contest
    */
    @Override
    public void setDefaultParentPointType(long defaultParentPointType) {
        _contest.setDefaultParentPointType(defaultParentPointType);
    }

    /**
    * Returns the point distribution strategy of this contest.
    *
    * @return the point distribution strategy of this contest
    */
    @Override
    public java.lang.String getPointDistributionStrategy() {
        return _contest.getPointDistributionStrategy();
    }

    /**
    * Sets the point distribution strategy of this contest.
    *
    * @param pointDistributionStrategy the point distribution strategy of this contest
    */
    @Override
    public void setPointDistributionStrategy(
        java.lang.String pointDistributionStrategy) {
        _contest.setPointDistributionStrategy(pointDistributionStrategy);
    }

    /**
    * Returns the email template url of this contest.
    *
    * @return the email template url of this contest
    */
    @Override
    public java.lang.String getEmailTemplateUrl() {
        return _contest.getEmailTemplateUrl();
    }

    /**
    * Sets the email template url of this contest.
    *
    * @param emailTemplateUrl the email template url of this contest
    */
    @Override
    public void setEmailTemplateUrl(java.lang.String emailTemplateUrl) {
        _contest.setEmailTemplateUrl(emailTemplateUrl);
    }

    /**
    * Returns the show_in_tile_view of this contest.
    *
    * @return the show_in_tile_view of this contest
    */
    @Override
    public boolean getShow_in_tile_view() {
        return _contest.getShow_in_tile_view();
    }

    /**
    * Returns <code>true</code> if this contest is show_in_tile_view.
    *
    * @return <code>true</code> if this contest is show_in_tile_view; <code>false</code> otherwise
    */
    @Override
    public boolean isShow_in_tile_view() {
        return _contest.isShow_in_tile_view();
    }

    /**
    * Sets whether this contest is show_in_tile_view.
    *
    * @param show_in_tile_view the show_in_tile_view of this contest
    */
    @Override
    public void setShow_in_tile_view(boolean show_in_tile_view) {
        _contest.setShow_in_tile_view(show_in_tile_view);
    }

    /**
    * Returns the show_in_list_view of this contest.
    *
    * @return the show_in_list_view of this contest
    */
    @Override
    public boolean getShow_in_list_view() {
        return _contest.getShow_in_list_view();
    }

    /**
    * Returns <code>true</code> if this contest is show_in_list_view.
    *
    * @return <code>true</code> if this contest is show_in_list_view; <code>false</code> otherwise
    */
    @Override
    public boolean isShow_in_list_view() {
        return _contest.isShow_in_list_view();
    }

    /**
    * Sets whether this contest is show_in_list_view.
    *
    * @param show_in_list_view the show_in_list_view of this contest
    */
    @Override
    public void setShow_in_list_view(boolean show_in_list_view) {
        _contest.setShow_in_list_view(show_in_list_view);
    }

    /**
    * Returns the show_in_outline_view of this contest.
    *
    * @return the show_in_outline_view of this contest
    */
    @Override
    public boolean getShow_in_outline_view() {
        return _contest.getShow_in_outline_view();
    }

    /**
    * Returns <code>true</code> if this contest is show_in_outline_view.
    *
    * @return <code>true</code> if this contest is show_in_outline_view; <code>false</code> otherwise
    */
    @Override
    public boolean isShow_in_outline_view() {
        return _contest.isShow_in_outline_view();
    }

    /**
    * Sets whether this contest is show_in_outline_view.
    *
    * @param show_in_outline_view the show_in_outline_view of this contest
    */
    @Override
    public void setShow_in_outline_view(boolean show_in_outline_view) {
        _contest.setShow_in_outline_view(show_in_outline_view);
    }

    @Override
    public boolean isNew() {
        return _contest.isNew();
    }

    @Override
    public void setNew(boolean n) {
        _contest.setNew(n);
    }

    @Override
    public boolean isCachedModel() {
        return _contest.isCachedModel();
    }

    @Override
    public void setCachedModel(boolean cachedModel) {
        _contest.setCachedModel(cachedModel);
    }

    @Override
    public boolean isEscapedModel() {
        return _contest.isEscapedModel();
    }

    @Override
    public java.io.Serializable getPrimaryKeyObj() {
        return _contest.getPrimaryKeyObj();
    }

    @Override
    public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
        _contest.setPrimaryKeyObj(primaryKeyObj);
    }

    @Override
    public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
        return _contest.getExpandoBridge();
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.model.BaseModel<?> baseModel) {
        _contest.setExpandoBridgeAttributes(baseModel);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
        _contest.setExpandoBridgeAttributes(expandoBridge);
    }

    @Override
    public void setExpandoBridgeAttributes(
        com.liferay.portal.service.ServiceContext serviceContext) {
        _contest.setExpandoBridgeAttributes(serviceContext);
    }

    @Override
    public java.lang.Object clone() {
        return new ContestWrapper((Contest) _contest.clone());
    }

    @Override
    public int compareTo(Contest contest) {
        return _contest.compareTo(contest);
    }

    @Override
    public int hashCode() {
        return _contest.hashCode();
    }

    @Override
    public com.liferay.portal.model.CacheModel<Contest> toCacheModel() {
        return _contest.toCacheModel();
    }

    @Override
    public Contest toEscapedModel() {
        return new ContestWrapper(_contest.toEscapedModel());
    }

    @Override
    public Contest toUnescapedModel() {
        return new ContestWrapper(_contest.toUnescapedModel());
    }

    @Override
    public java.lang.String toString() {
        return _contest.toString();
    }

    @Override
    public java.lang.String toXmlString() {
        return _contest.toXmlString();
    }

    @Override
    public void persist()
        throws com.liferay.portal.kernel.exception.SystemException {
        _contest.persist();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ContestWrapper)) {
            return false;
        }

        ContestWrapper contestWrapper = (ContestWrapper) obj;

        if (Validator.equals(_contest, contestWrapper._contest)) {
            return true;
        }

        return false;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
     */
    public Contest getWrappedContest() {
        return _contest;
    }

    @Override
    public Contest getWrappedModel() {
        return _contest;
    }

    @Override
    public void resetOriginalValues() {
        _contest.resetOriginalValues();
    }
}
