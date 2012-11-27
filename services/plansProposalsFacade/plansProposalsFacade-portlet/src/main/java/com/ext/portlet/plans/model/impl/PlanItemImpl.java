package com.ext.portlet.plans.model.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ext.portlet.contests.model.Contest;
import com.ext.portlet.contests.model.ContestPhase;
import com.ext.portlet.contests.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.discussions.model.DiscussionCategoryGroup;
import com.ext.portlet.discussions.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.plans.EntityState;
import com.ext.portlet.plans.NoSuchPlanFanException;
import com.ext.portlet.plans.NoSuchPlanItemException;
import com.ext.portlet.plans.NoSuchPlanPositionsException;
import com.ext.portlet.plans.NoSuchPlanTeamHistoryException;
import com.ext.portlet.plans.NoSuchPlanVoteException;
import com.ext.portlet.plans.PlanConstants;
import com.ext.portlet.plans.PlanConstants.Attribute;
import com.ext.portlet.plans.PlanTeamActions;
import com.ext.portlet.plans.PlanUserPermission;
import com.ext.portlet.plans.UpdateType;
import com.ext.portlet.plans.model.PlanAttribute;
import com.ext.portlet.plans.model.PlanDescription;
import com.ext.portlet.plans.model.PlanFan;
import com.ext.portlet.plans.model.PlanItem;
import com.ext.portlet.plans.model.PlanMeta;
import com.ext.portlet.plans.model.PlanModelRun;
import com.ext.portlet.plans.model.PlanPositions;
import com.ext.portlet.plans.model.PlanSection;
import com.ext.portlet.plans.model.PlanSectionDefinition;
import com.ext.portlet.plans.model.PlanTeamHistory;
import com.ext.portlet.plans.model.PlanTemplate;
import com.ext.portlet.plans.model.PlanType;
import com.ext.portlet.plans.model.PlanVote;
import com.ext.portlet.plans.service.PlanAttributeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanDescriptionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanFanLocalServiceUtil;
import com.ext.portlet.plans.service.PlanItemLocalServiceUtil;
import com.ext.portlet.plans.service.PlanMetaLocalServiceUtil;
import com.ext.portlet.plans.service.PlanModelRunLocalServiceUtil;
import com.ext.portlet.plans.service.PlanPositionsLocalServiceUtil;
import com.ext.portlet.plans.service.PlanSectionLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTeamHistoryLocalServiceUtil;
import com.ext.portlet.plans.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.plans.service.PlanVoteLocalServiceUtil;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserGroupRoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;

import edu.mit.cci.simulation.client.Simulation;

/**
 * The extended model implementation for the PlanItem service. Represents a row in the &quot;plansProposalsFacade_PlanItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * Helper methods and all application logic should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.plans.model.PlanItem} interface.
 * </p>
 *
 * @author Brian Wing Shun Chan
 */
public class PlanItemImpl extends PlanItemBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this class directly. All methods that expect a plan item model instance should use the {@link com.ext.portlet.plans.model.PlanItem} interface instead.
     */
    private final static Log _log = LogFactoryUtil.getLog(PlanItemImpl.class);

    public static List<PlanItem> getPlans() throws SystemException {
        return PlanItemLocalServiceUtil.getPlans();
    }

    public PlanItemImpl() {
    }

    /* Description related stuff */
    public String getDescription() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getForPlan(this).getDescription();
    }

    public String getName() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getForPlan(this).getName();
    }
    
    public Long getImageId() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getForPlan(this).getImage();
    }
    
    public String getPitch() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getForPlan(this).getPitch();
    }
    
    
    public Image getImage() throws SystemException, PortalException {
        Long imageId = getImageId();
        if (imageId != null) {
            return ImageLocalServiceUtil.getImage(imageId);
        }
        return null;
    }

    public void setDescription(String description, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.DESCRIPTION_UPDATED, updateAuthorId);

        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(this);
        planDescription.setDescription(description);
        planDescription.store();
        updateAttribute(Attribute.DESCRIPTION);
        
       //joinIfNotAMember(updateAuthorId);
        updateSearchIndex();
    }

    public void setName(String name, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.NAME_UPDATED, updateAuthorId);
        // update plan name attribute

        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(this);
        planDescription.setName(name);
        planDescription.store();
        updateAttribute(Attribute.NAME);
        //joinIfNotAMember(updateAuthorId);
        updateSearchIndex();
        // update referenced discussion category group
        DiscussionCategoryGroup dcg = getDiscussionCategoryGroup();
        dcg.setDescription(name + " discussion");
        dcg.store();
    }
    
    public void setImage(Long imageId, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.IMAGE_UPDATED, updateAuthorId);
        // update plan name attribute

        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(this);
        planDescription.setImage(imageId);
        planDescription.store();
        updateAttribute(Attribute.IMAGE);
        //joinIfNotAMember(updateAuthorId);
        updateSearchIndex();
    }
    
    public void setPitch(String pitch, Long updateAuthorId) throws SystemException, SearchException {
        newVersion(UpdateType.PITCH_UPDATED, updateAuthorId);
        
        PlanDescription planDescription = PlanDescriptionLocalServiceUtil.createNewVersionForPlan(this);
        planDescription.setPitch(pitch);
        planDescription.store();
        updateAttribute(Attribute.ABSTRACT);
        updateSearchIndex();
    }

    public List<PlanDescription> getAllDescriptionVersions() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getAllForPlan(this);
    }

    /**
     * List of all versions of PlanDescription objects related to given plan
     * 
     * @see com.ext.portlet.plans.model.PlanItem#getPlanDescriptions()
     */
    public List<PlanDescription> getPlanDescriptions() throws SystemException {
        return PlanDescriptionLocalServiceUtil.getAllForPlan(this);
    }

    /*
     * 
     * Scenarios
     */
    public Long getScenarioId() throws SystemException {
        return PlanModelRunLocalServiceUtil.getCurrentForPlan(this).getScenarioId();
    }

    public void setScenarioId(Long scenarioId, Long authorId) throws SystemException, PortalException {
        newVersion(UpdateType.SCENARIO_UPDATED, authorId);

        PlanModelRun planModelRun = PlanModelRunLocalServiceUtil.createNewVersionForPlan(this);
        planModelRun.setScenarioId(scenarioId);
        planModelRun.store();

        // update plan attributes to reflect values from new scenario
        PlanType planType = getPlanType();

        for (PlanConstants.Attribute attribute : PlanConstants.Attribute.getPlanTypeAttributes(planType)) {
            updateAttribute(attribute);
        }
        
        //joinIfNotAMember(authorId);
    }

    public void setModelId(Long simulationId, Long authorId) throws SystemException, PortalException {

        PlanType planType = getPlanType();
        List<Simulation> sims = planType.getAvailableModels();
        boolean found = false;
        for (Simulation sim : sims) {
            if (simulationId.equals(sim.getId())) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new SystemException("Model id " + simulationId + " not valid for planType");
        }

        newVersion(UpdateType.MODEL_UPDATED, authorId);
        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setModelId(simulationId);
        planMeta.store();

        setScenarioId(null, authorId);
        //joinIfNotAMember(authorId);
    }

    public List<PlanModelRun> getAllPlanModelRuns() throws SystemException {
        return PlanModelRunLocalServiceUtil.getAllForPlan(this);
    }

    /*
     * Plan meta informations.
     */

    public PlanMeta getPlanMeta() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this);
    }

    public List<PlanMeta> getAllPlanMetas() throws SystemException {
        return PlanMetaLocalServiceUtil.getAllForPlan(this);
    }

    public Long getPlanTypeId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getPlanTypeId();
    }

    public PlanType getPlanType() throws SystemException, PortalException {
        return PlanTypeLocalServiceUtil.getPlanType(getPlanTypeId());
    }

    public Contest getContest() throws SystemException, PortalException {
       ContestPhase phase = getContestPhase();
        return phase == null?null:phase.getContest();
    }

    public ContestPhase getContestPhase() throws SystemException, PortalException {
        Long phase =  getPlanMeta().getContestPhase();
        return phase == null?null:ContestPhaseLocalServiceUtil.getContestPhase(phase);
    }

    public void setContestPhase(ContestPhase phase, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_TYPE_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setContestPhase(phase.getContestPhasePK());
        planMeta.store();

        //joinIfNotAMember(updateAuthorId);
    }

    public void setPlanTypeId(Long planTypeId, Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.PLAN_TYPE_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setPlanTypeId(planTypeId);
        planMeta.store();
        
        //joinIfNotAMember(updateAuthorId);
    }

    public Long getMBCategoryId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getMbCategoryId();
    }

    public void setMBCategoryId(Long mbCategoryId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.MB_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setMbCategoryId(mbCategoryId);
        planMeta.store();
    }

    public Long getCategoryGroupId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getCategoryGroupId();
    }

    public void setCategoryGroupId(Long categoryGroupId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.MB_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setCategoryGroupId(categoryGroupId);
        planMeta.store();
    }

    public Long getPlanGroupId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getPlanGroupId();
    }

    public void setPlanGroupId(Long groupId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setPlanGroupId(groupId);
        planMeta.store();
    }

    public Long getAuthorId() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getAuthorId();
    }

    public User getAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getAuthorId());
    }

    public void setAuthorId(Long authorId, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_GROUP_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setAuthorId(authorId);
        planMeta.store();
    }

    public Date getCreateDate() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getCreated();
    }

    public Date getPublishDate() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getCreated();
    }

    public String getCreator() throws PortalException, SystemException {
        return getAuthor().getScreenName();

    }

    public Integer getVotes() throws SystemException {
        //return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getVotes();
        return PlanVoteLocalServiceUtil.countPlanVotesByPlanId(this.getPlanId());
    }

    public boolean getOpen() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getOpen();
    }

    public void setOpen(boolean open, Long updateAuthorId) throws SystemException {
        if (open) {
            newVersion(UpdateType.PLAN_OPENED, updateAuthorId);
        }
        else {
            newVersion(UpdateType.PLAN_CLOSED, updateAuthorId);
            
        }

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setOpen(open);
        planMeta.store();
        updateAttribute(Attribute.IS_PLAN_OPEN);

    }
    
    public void setOpen(boolean open) throws SystemException {
        PlanMeta planMeta = getPlanMeta();
        planMeta.setOpen(open);
        planMeta.store();
        updateAttribute(Attribute.IS_PLAN_OPEN);

    }

    public String getStatus() throws SystemException {
        return PlanMetaLocalServiceUtil.getCurrentForPlan(this).getStatus();
    }

    public void setStatus(String status, Long updateAuthorId) throws SystemException {
        newVersion(UpdateType.PLAN_STATUS_UPDATED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setStatus(status);
        planMeta.store();
        updateAttribute(PlanConstants.Attribute.STATUS);

    }

    /*
     * POSITIONS
     */

    public PlanPositions getPlanPositions() throws NoSuchPlanPositionsException, SystemException {
        return PlanPositionsLocalServiceUtil.getCurrentForPlan(this);
    }

    public List<Long> getPositionsIds() throws SystemException, NoSuchPlanPositionsException {
        PlanPositions x = PlanPositionsLocalServiceUtil.getCurrentForPlan(this);
        return x.getPositionsIds();
    }

    public Long[] getPositionsIdsArray() throws SystemException, NoSuchPlanPositionsException {
        List<Long> idsList = getPositionsIds();
        Long[] ret = new Long[idsList.size()];
        return idsList.toArray(ret);
    }

    public void setPositions(List<Long> positionsIds, Long updateAuthorId) throws PortalException, SystemException {
        newVersion(UpdateType.PLAN_POSITIONS_UPDATED, updateAuthorId);

        PlanPositions planPositions = PlanPositionsLocalServiceUtil.createNewVersionForPlan(this);
        planPositions.store();
        planPositions.setPositionsIds(positionsIds);
        updateAttribute(Attribute.POSITIONS);
        
        //joinIfNotAMember(updateAuthorId);
    }

    public List<PlanPositions> getAllPositionsVersions() throws SystemException {
        return PlanPositionsLocalServiceUtil.getAllForPlan(this);
    }

    /*
     * VOTES
     */
    public boolean hasUserVoted(Long userId) throws PortalException, SystemException {
        try {
            PlanVote vote = PlanVoteLocalServiceUtil.getPlanVote(userId, getContest().getContestPK());
            return vote.getPlanId().equals(getPlanId());
        } catch (NoSuchPlanVoteException e) {
            // ignore
        }
        return false;
    }

    public void vote(Long userId) throws PortalException, SystemException {
        if (PlanVoteLocalServiceUtil.voteForPlan(getPlanId(), userId)) {
            PlanMetaLocalServiceUtil.getCurrentForPlan(this).vote();
        }
        updateAttribute(Attribute.VOTES);
    }

    public void unvote(Long userId) throws PortalException, SystemException {
        if (PlanVoteLocalServiceUtil.unvote(userId, this.getContest().getContestPK())) {
            PlanMetaLocalServiceUtil.getCurrentForPlan(this).unvote();
        }
        updateAttribute(Attribute.VOTES);
    }

    public List<PlanItem> getAllVersions() throws SystemException {
        return PlanItemLocalServiceUtil.getAllVersions(this);
    }

    private PlanItem newVersion(UpdateType updateType, Long updateAuthorId) throws SystemException {
        PlanItem latestVersion = this;
        try {
            /*
             *
             // i don't think that this is a problem
            if (!latestVersion.equals()) {
                throw new SystemException(
                        "Can only create a new version of a plan from the latest version in existence");
            }
            */
            latestVersion = PlanItemLocalServiceUtil.getPlan(this.getPlanId());
        } catch (NoSuchPlanItemException e) {
            // ignore
        }
        PlanItem oldVersion = (PlanItem) latestVersion.clone();
        //newVersion.setId();
        oldVersion.store();

        this.setId(CounterLocalServiceUtil.increment(PlanItem.class.getName()));
        this.setVersion(Math.max(getVersion(), latestVersion.getVersion()) + 1);
        this.setUpdated(new Date());
        this.setUpdateType(updateType.name());
        this.setUpdateAuthorId(updateAuthorId);
        this.setNew(true);
        this.store();
        
        setAttribute(PlanConstants.Attribute.LAST_MOD_DATE, String.valueOf(this.getUpdated()));

        return this;
    }

    public void store() throws SystemException {
        if (this.isNew()) {
            PlanItemLocalServiceUtil.addPlanItem(this);
        } else {
            PlanItemLocalServiceUtil.updatePlanItem(this);
        }
    }

    /**
     * Updates values of all available attributes.
     * 
     * @throws SystemException
     */

    public void updateAllAttributes() throws SystemException {
        for (Attribute attribute : Attribute.values()) {
            updateAttribute(attribute);
        }
    }

    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attributeName
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    public void updateAttribute(String attributeName) throws SystemException {
        updateAttribute(Attribute.valueOf(attributeName));
    }

    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attribute
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    private void updateAttribute(Attribute attribute) throws SystemException {
        String value = attribute.calculateValue(this).toString();
        PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(getPlanId(), attribute.name());
        if (att != null) {
            if (! att.getAttributeValue().equals(value)) {
                att.setAttributeValue(value);
                PlanAttributeLocalServiceUtil.updatePlanAttribute(att);
            }
        } else {
            PlanAttributeLocalServiceUtil.addPlanAttribute(getPlanId(), attribute.name(), value);
        }
    }
    
    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attribute
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    private void setAttribute(Attribute attribute, String value) throws SystemException {
        PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(getPlanId(), attribute.name());
        if (att != null) {
            att.setAttributeValue(value);
            PlanAttributeLocalServiceUtil.updatePlanAttribute(att);
        }
        else {
            PlanAttributeLocalServiceUtil.addPlanAttribute(getPlanId(), attribute.name(), value);
        }
    }
    
    /**
     * Updates value of a given attribute, should be used only for property
     * attributes.
     * 
     * @param attribute
     *            attribute which value should be updated
     * @throws SystemException
     *             in case of any error
     */
    private String getAttribute(Attribute attribute) throws SystemException {
        PlanAttribute att = PlanAttributeLocalServiceUtil.findPlanAttribute(getPlanId(), attribute.name());
        if (att != null) {
            return att.getAttributeValue();
        }
        return null;
    }
    
    

    public List<PlanAttribute> getPlanAttributes() throws SystemException {
        return PlanItemLocalServiceUtil.getPlanAttributes(this);
    }

    public PlanAttribute getPlanAttribute(String name) throws SystemException {
        return PlanItemLocalServiceUtil.getPlanAttribute(this, name);
    }

    /*
     * Plan membership related stuff
     */

    /**
     * Returns list of plan members.
     */
    public List<User> getMembers() throws SystemException {
        return UserLocalServiceUtil.getGroupUsers(getPlanGroupId());
    }

    public List<MembershipRequest> getMembershipRequests() throws SystemException {
        return MembershipRequestLocalServiceUtil.search(getPlanGroupId(), MembershipRequestConstants.STATUS_PENDING, 0,
                Integer.MAX_VALUE);
    }

    public void addMembershipRequest(Long userId, String comments) throws PortalException, SystemException {
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId,
                PlanTeamActions.MEMBERSHIP_REQUESTED.name(), userId);
        MembershipRequestLocalServiceUtil.addMembershipRequest(userId, getPlanGroupId(), comments, null);
    }

    public void dennyMembershipRequest(Long userId, MembershipRequest request, String reply, Long updateAuthorId)
            throws PortalException, SystemException {
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId,
                PlanTeamActions.MEMBERSHIP_DECLEINED.name(), updateAuthorId);
        MembershipRequestLocalServiceUtil.updateStatus(userId, request.getMembershipRequestId(), reply,
                MembershipRequestConstants.STATUS_DENIED, false, null);
    }

    public void approveMembershipRequest(Long userId, MembershipRequest request, String reply, Long updateAuthorId)
            throws PortalException, SystemException {
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId,
                PlanTeamActions.MEMBERSHIP_APPROVED.name(), updateAuthorId);
        MembershipRequestLocalServiceUtil.updateStatus(userId, request.getMembershipRequestId(), reply,
                MembershipRequestConstants.STATUS_APPROVED, true, null);
    }

    /*
     * Plan actions, publishing/deleting
     */
    public void publish(Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.PLAN_PUBLISHED, updateAuthorId);

        PlanMeta planMeta = PlanMetaLocalServiceUtil.createNewVersionForPlan(this);
        planMeta.setPlanTypeId(getPlanType().getPublishedCounterpartId());
        planMeta.store();
    }

    public void delete(Long updateAuthorId) throws SystemException, PortalException {
        newVersion(UpdateType.PLAN_DELETED, updateAuthorId);
        this.setState(EntityState.DELETED.name());
        this.store();
        PlanItemLocalServiceUtil.planDeleted(this);
        /*
        try {
            Indexer.deleteEntry(10112L, getPlanId());
        } catch (SearchException e) {
            _log.error("can't remove plan " + getPlanId() + " from search index", e);
        }
        */
    }

    public User getUpdateAuthor() throws PortalException, SystemException {
        return UserLocalServiceUtil.getUser(getUpdateAuthorId());
    }

    public List<PlanFan> getFans() throws SystemException {
        return PlanFanLocalServiceUtil.getPlanFansForPlan(this.getPlanId());
    }

    public PlanFan addFan(Long userId) throws SystemException {
        if (!isUserAFan(userId)) {
            PlanFan planFan = PlanFanLocalServiceUtil.addFan(this.getPlanId(), userId);
            setAttribute(Attribute.SUPPORTERS, String.valueOf(PlanFanLocalServiceUtil.countPlanFansForPlan(getPlanId())));
            return planFan;
        }
        return null;
    }

    public void removeFan(Long userId) throws SystemException {
        if (isUserAFan(userId)) {
            PlanFanLocalServiceUtil.removePlanFan(this.getPlanId(), userId);
            setAttribute(Attribute.SUPPORTERS, String.valueOf(PlanFanLocalServiceUtil.countPlanFansForPlan(getPlanId())));
        }
    }

    public boolean isUserAFan(Long userId) throws SystemException {
        try {
            return PlanFanLocalServiceUtil.getPlanFanByPlanIdUserId(this.getPlanId(), userId) != null;
        } catch (NoSuchPlanFanException e) {
            return false;
        }
    }

    public boolean isUserAMember(Long userId) throws SystemException {
        return GroupLocalServiceUtil.hasUserGroup(userId, getPlanGroupId());
    }

    public boolean hasUserRequestedMembership(Long userId) throws SystemException {
        try {
            PlanTeamHistory action = PlanTeamHistoryLocalServiceUtil.getLastUserActionInPlan(getPlanId(), userId);
            if (action.getAction().equals(PlanTeamActions.MEMBERSHIP_REQUESTED.name())) {
                return true;
            }
        } catch (NoSuchPlanTeamHistoryException e) {
            // ignore
        }
        return false;
    }

    public boolean isAdmin(Long userId) throws PortalException, SystemException {
        return UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, getPlanGroupId(),
                RoleConstants.SITE_ADMINISTRATOR)
                || isOwner(userId);
    }

    public boolean isOwner(Long userId) throws PortalException, SystemException {
        return UserGroupRoleLocalServiceUtil.hasUserGroupRole(userId, getPlanGroupId(), RoleConstants.SITE_OWNER);
    }

    public void setUserPermission(Long userId, String userPermission, Long updateAuthorId) throws SystemException,
            PortalException {
        PlanUserPermission userPerm = PlanUserPermission.valueOf(userPermission);

        Group group = GroupLocalServiceUtil.getGroup(getPlanGroupId());
        UserGroupRoleLocalServiceUtil.deleteUserGroupRoles(userId, new long[] { getPlanGroupId() });
        String roleName = RoleConstants.SITE_MEMBER;

        if (userPerm == PlanUserPermission.OWNER) {
            roleName = RoleConstants.SITE_OWNER;
        } else if (userPerm == PlanUserPermission.ADMIN) {
            roleName = RoleConstants.SITE_ADMINISTRATOR;
        }

        Role role = RoleLocalServiceUtil.getRole(group.getCompanyId(), roleName);
        UserGroupRoleLocalServiceUtil.addUserGroupRoles(userId, getPlanGroupId(), new long[] { role.getRoleId() });
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId, PlanTeamActions.PERMISSIONS_CHANGED.name(),
                userPermission, updateAuthorId);
    }

    public void removeMember(Long userId, Long updateAuthorId) throws SystemException, PortalException {
        UserLocalServiceUtil.unsetGroupUsers(getPlanGroupId(), new long[] { userId }, null);
        PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId, PlanTeamActions.MEMBER_REMOVED.name(),
                updateAuthorId);
    }
    

    public void joinIfNotAMember(Long userId) throws SystemException, PortalException {
        if (! isUserAMember(userId)) {
            GroupLocalServiceUtil.addUserGroups(userId, new long[] {getPlanGroupId()});
            PlanTeamHistoryLocalServiceUtil.newHistoryItem(getPlanId(), userId, PlanTeamActions.MEMBERSHIP_APPROVED.name(),
                    userId);
        }
        
    }
    
    public void setSeekingAssistance(boolean seekingAssistance) throws SystemException {
        setAttribute(Attribute.SEEKING_ASSISTANCE, String.valueOf(seekingAssistance));
    }
    
    public boolean isSeekingAssistance() throws SystemException {
        Object value = Attribute.SEEKING_ASSISTANCE.getValue(this);
        if ("true".equals(value)) {
            return true;
        }
        return false;
    }
    

    private void updateSearchIndex() throws SearchException, SystemException {
        //Indexer.updateEntry(10112L, this);
        
    }
    
    public DiscussionCategoryGroup getDiscussionCategoryGroup() throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(getCategoryGroupId());
        
    }
    
    public PlanItem promote(User user) throws SystemException, PortalException {
        ContestPhase contestPhase = getContestPhase().getNextContestPhase();
        
        PlanMeta planMeta = PlanMetaLocalServiceUtil.getCurrentForPlan(this);
        planMeta.setPromoted(true);
        planMeta.setPreviousContestPhase(planMeta.getContestPhase());
        planMeta.setContestPhase(contestPhase.getContestPhasePK());
        
        planMeta.store();
        setAttribute(Attribute.PLAN_PLACE, String.valueOf(1));

        return this;
    }
    
    
    
    public boolean getPromoted() throws SystemException {
        Boolean promoted = PlanMetaLocalServiceUtil.getCurrentForPlan(this).getPromoted();
        return promoted != null ? promoted : false;
        
    }
    
    public int getCommentsCount() throws PortalException, SystemException {
        return getDiscussionCategoryGroup().getCommentsCount();
    }
    
    public void setPlace(int place) throws SystemException {
        setAttribute(Attribute.PLAN_PLACE, String.valueOf(place));
    }
    
    public void removePlace() throws SystemException {
        setAttribute(Attribute.PLAN_PLACE, String.valueOf(-1));
    }
    
    public List<PlanVote> getPlanVotes() throws SystemException {
        return PlanVoteLocalServiceUtil.getPlanVotes(this.getPlanId());
    }
    
    public void setRibbon(Integer ribbon) throws SystemException {
        setAttribute(Attribute.PLAN_RIBBON, String.valueOf(ribbon));
    }
    

    public void setRibbonText(String ribbonText) throws SystemException {
        setAttribute(Attribute.PLAN_RIBBON_TEXT, String.valueOf(ribbonText));
    }
    
    
    public void setAttribute(String attributeName, String value) throws SystemException {
        setAttribute(Attribute.valueOf(attributeName), value);
    }
    
    public void removeAttribute(String attributeName) throws SystemException {
        PlanAttribute attr = PlanAttributeLocalServiceUtil.findPlanAttribute(getPlanId(), attributeName);
        
        if (attr != null) {
            PlanAttributeLocalServiceUtil.deletePlanAttribute(attr);
        }
    }
    
    public PlanTemplate getPlanTemplate() throws PortalException, SystemException {
        return getContest().getPlanTemplate();
    }
    
    public List<PlanSection> getPlanSections() throws PortalException, SystemException {
        PlanTemplate tmpl = getPlanTemplate();
        if (tmpl != null) {
            List<PlanSection> ret = new ArrayList<PlanSection>();
            
            for (PlanSectionDefinition psd: tmpl.getSections()) {
                ret.add(PlanSectionLocalServiceUtil.getForPlanSectionDef(this, psd));
            }
            return ret;
        }
        
        return null;
    }
    
    public void setSectionContent(PlanSectionDefinition psd, String content, List<Long> referencedPlans, Long updateAuthorId) 
    throws SystemException, PortalException {
        newVersion(UpdateType.PLAN_SECTION_UPDATED, updateAuthorId);
        PlanSection ps = PlanSectionLocalServiceUtil.createNewVersionForPlanSectionDefinition(this, psd, false);
        ps.setUpdateAuthorId(updateAuthorId);
        ps.setContent(content);
        
        for (Long planId: referencedPlans) {
            ps.addPlanReference(planId);
        }
        
        ps.store();
        
    }
    
    public List<PlanSection> getAllPlanSections(PlanSectionDefinition psd) throws SystemException {
        return PlanSectionLocalServiceUtil.getAllForPlanDefinition(this, psd);
    }

    public Integer getRibbon() throws SystemException {
        PlanAttribute attr = getPlanAttribute(PlanConstants.Attribute.PLAN_RIBBON.name());
        try {
            return attr != null && attr.getAttributeValue() != null && attr.getAttributeValue().trim().length() > 0 ? 
                    Integer.parseInt(attr.getAttributeValue()) : null;
        }
        catch (NumberFormatException e) {
            return null;
        }
    }
    
    public void setTeam(String team) throws SystemException {
        setAttribute(PlanConstants.Attribute.TEAM, team);
    }
    
    public String getTeam() throws SystemException {
        String team = getAttribute(Attribute.TEAM);
        if (team == null || team.trim().length() == 0) {
            return null;
        }
        return team;
    }
    
    public void revertTo(Long updateAuthorId) throws SystemException, PortalException {
        
        
        PlanDescription oldDesc = PlanDescriptionLocalServiceUtil.getForPlan(this);
        PlanModelRun oldAi = PlanModelRunLocalServiceUtil.getForPlan(this);
        List<PlanSection> oldSections = getPlanSections();
        
        newVersion(UpdateType.PLAN_REVERTED, updateAuthorId);
        
        PlanDescriptionLocalServiceUtil.createNewVersionForPlanFrom(this, oldDesc, true);
        PlanModelRunLocalServiceUtil.createNewVersionForPlanFrom(this, oldAi, true);
        
        PlanTemplate tmpl = getPlanTemplate();
        for (PlanSection section: oldSections) {
            PlanSectionLocalServiceUtil.createForPlanFrom(this, section, true);
        }
        
        updateAttribute(Attribute.ABSTRACT);
        updateAttribute(Attribute.DESCRIPTION);
        updateAttribute(Attribute.NAME);
        updateAttribute(Attribute.IMAGE);        
        
        PlanType planType = getPlanType();

        for (PlanConstants.Attribute attribute : PlanConstants.Attribute.getPlanTypeAttributes(planType)) {
            updateAttribute(attribute);
        }
    }
    
    public String getTags() throws SystemException {
        String tags = getAttribute(Attribute.TAGS);
        if (tags == null || tags.trim().length() == 0) {
            return null;
        }
        return tags;
    }
    
    public void setTags(String tags) throws SystemException {
        setAttribute(Attribute.TAGS, tags);
    }
    
    public String getTagsHover() throws SystemException {
        String tagsHover = getAttribute(Attribute.TAGS_HOVER);
        if (tagsHover == null || tagsHover.trim().length() == 0) {
            return null;
        }
        return tagsHover;
    }
    
    public void setTagsHover(String tagsHover) throws SystemException {
        setAttribute(Attribute.TAGS_HOVER, tagsHover);
    }
    
    public Integer getTagsOrder() throws SystemException {
        String tagsOrderStr = getAttribute(Attribute.TAGS_ORDER);
        int ret = 0;
        if (tagsOrderStr != null && tagsOrderStr.trim().length() > 0) {
            try {
                ret = Integer.parseInt(tagsOrderStr);
                
            }
            catch (NumberFormatException e) {
                // ignore
            }
        }
        return ret;
    }
    
    public void setTagsOrder(int tagsOrder) throws SystemException {
        setAttribute(Attribute.TAGS_ORDER, String.valueOf(tagsOrder));
    }

}
