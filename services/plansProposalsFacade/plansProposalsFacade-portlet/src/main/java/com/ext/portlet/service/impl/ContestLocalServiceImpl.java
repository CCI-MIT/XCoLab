package com.ext.portlet.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.NoSuchContestPhaseException;
import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestDebate;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.PlanItem;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestDebateLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.PlanItemLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.base.ContestLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.PermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the contest local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ContestLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ContestLocalServiceBaseImpl
 * @see com.ext.portlet.service.ContestLocalServiceUtil
 */
public class ContestLocalServiceImpl extends ContestLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ContestLocalServiceUtil} to access the contest local service.
     */

    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on contest %s";
    private Random rand = new Random();

    private final static Log _log = LogFactoryUtil.getLog(ContestLocalServiceImpl.class);
    
    public Contest getContestByActiveFlag(boolean contestActive) throws NoSuchContestException, SystemException {
        return contestPersistence.findBycontestActive(contestActive);
    }
    
    public Contest createNewContest(Long userId, String name) throws SystemException, PortalException {
        Contest c = ContestLocalServiceUtil.createContest(CounterLocalServiceUtil.increment(Contest.class.getName()));
        

        c.setAuthorId(userId);
        c.setContestName(name);
        c.setContestShortName(name);
        
        setGroupAndDiscussionForContest(c);
        
        store(c);
        return c;
    }
    
    public void updateContestGroupsAndDiscussions() throws SystemException, PortalException {
        for (Contest c: ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
            if (c.getGroupId() <= 0) {
                setGroupAndDiscussionForContest(c);
            }
        }
    }
    
    private void setGroupAndDiscussionForContest(Contest c) throws PortalException, SystemException {

        ServiceContext groupServiceContext = new ServiceContext();
        groupServiceContext.setUserId(c.getAuthorId());

        String groupName = c.getContestName() + "_" + System.currentTimeMillis() + "_" + rand.nextLong();
        Group group = null;
        
        /*
        group = GroupServiceUtil.add.addGroup("CONTEST:  " + c.getContestShortName(), String.format(DEFAULT_GROUP_DESCRIPTION, groupName),
                    GroupConstants.TYPE_COMMUNITY_RESTRICTED, null, true, groupServiceContext);
        */
        group = GroupLocalServiceUtil.addGroup(c.getAuthorId(), null, 0, "CONTEST:  " + c.getContestShortName(), 
                String.format(DEFAULT_GROUP_DESCRIPTION, groupName),  
                GroupConstants.TYPE_SITE_RESTRICTED, null, true, true, groupServiceContext);
        
        DiscussionCategoryGroup categoryGroup = DiscussionCategoryGroupLocalServiceUtil
                .createDiscussionCategoryGroup(c.getContestName() + " discussion");

        categoryGroup.setUrl("/web/guest/plans/-/plans/contestId/" + c.getContestPK() + "/page/discussion");
        
        DiscussionCategoryGroupLocalServiceUtil.store(categoryGroup);
        
        // set up permissions

        Long companyId = group.getCompanyId();
        Role owner = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_OWNER);
        Role admin = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_ADMINISTRATOR);
        Role member = RoleLocalServiceUtil.getRole(companyId, RoleConstants.SITE_MEMBER);
        Role userRole = RoleLocalServiceUtil.getRole(companyId, RoleConstants.USER);
        Role guest = RoleLocalServiceUtil.getRole(companyId, RoleConstants.GUEST);
        Role moderator = RoleLocalServiceUtil.getRole(companyId, "Moderator");

        String[] ownerActions = { DiscussionActions.ADMIN.name(), DiscussionActions.ADD_CATEGORY.name(),
                DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADMIN_CATEGORIES.name(), DiscussionActions.ADMIN_MESSAGES.name(),
                DiscussionActions.ADD_COMMENT.name() };

        String[] adminActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] moderatorActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] memberActions = { DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADD_COMMENT.name() };

        String[] userActions = { DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADD_COMMENT.name() };

        String[] guestActions = {};

        Map<Role, String[]> rolesActionsMap = new HashMap<Role, String[]>();

        rolesActionsMap.put(owner, ownerActions);
        rolesActionsMap.put(admin, adminActions);
        rolesActionsMap.put(member, memberActions);
        rolesActionsMap.put(userRole, userActions);
        rolesActionsMap.put(guest, guestActions);
        rolesActionsMap.put(moderator, moderatorActions);

        for (Role role : rolesActionsMap.keySet()) {
            PermissionLocalServiceUtil.setRolePermissions(role.getRoleId(), companyId,
                    DiscussionCategoryGroup.class.getName(), ResourceConstants.SCOPE_GROUP,
                    String.valueOf(group.getGroupId()), rolesActionsMap.get(role));
        }

        c.setGroupId(group.getGroupId());
        c.setDiscussionGroupId(categoryGroup.getPrimaryKey());
        store(c);
    }
    
    public List<Contest> findByActiveFeatured(boolean active, boolean featured) throws SystemException {
        return contestPersistence.findByActiveFeatured(active, featured);
    }
    
    public List<Contest> findByActiveFlag(boolean active, int flag) throws SystemException {
        return contestPersistence.findByActiveFlag(active, flag);
    }
    public List<Contest> findByActiveFlagText(boolean active, String flagText) throws SystemException {
        return contestPersistence.findByActiveFlagText(active, flagText);
    }
    

    /** Methods from ContestImpl **/
    public List<ContestPhase> getPhases(Contest contest) {
        try {
            return ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
        }
        catch (SystemException e) {
            _log.error(e);
            return new ArrayList<ContestPhase>();
            
        }
    }

    public PlanType getPlanType(Contest contest) throws SystemException, PortalException {
        try {
            return (PlanType) ClpSerializer.translateOutput(PlanTypeLocalServiceUtil.getPlanType(contest.getPlanTypeId()));
        }
        catch (PortalException | SystemException e) {
            _log.error(e);
            return null;
        }
    }

    public List<ContestPhase> getActivePhases(Contest contest) throws SystemException, PortalException {
        List<ContestPhase> result = getPhases(contest);
        for (Iterator<ContestPhase> i=result.iterator();i.hasNext();) {
           if (!ContestPhaseLocalServiceUtil.getPhaseActive(i.next())) {
               i.remove();
           }
        }
        return result;
    }
    
    public ContestPhase getActivePhase(Contest contest) throws NoSuchContestPhaseException, SystemException {
        
        for (ContestPhase phase: getPhases(contest)) {
            if (ContestPhaseLocalServiceUtil.getPhaseActive(phase)) return phase;
        }
        return null;
    }
    
    public ContestPhase getActiveOrLastPhase(Contest contest) throws NoSuchContestPhaseException, SystemException {
        ContestPhase phaseToReturn = null;
        for (ContestPhase phase: getPhases(contest)) {
            phaseToReturn = phase;
            if (ContestPhaseLocalServiceUtil.getPhaseActive(phase)) return phase;
        }
        return phaseToReturn;
    }
    
    public boolean isActive(Contest contest) throws SystemException {
        try {
            ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
            return true;
        }
        catch (NoSuchContestPhaseException e) {
            // ignore
        }
        return false;
    }
    
    public List<Long> getDebatesIds(Contest contest) throws SystemException  {
        List<Long> ret = new ArrayList<Long>();
        for (ContestDebate pos: ContestDebateLocalServiceUtil.getContestDebates(contest.getContestPK())) {
            ret.add(pos.getDebateId());
        }
        return ret;
    }
    
    public Integer getTotalVotes(Contest contest) throws SystemException {
        return PlanVoteLocalServiceUtil.countPlanVotes(contest);
    }
    
    public void updateDefaultPlanDescription(Contest contest, String description) throws SystemException {
        contest.setDefaultPlanDescription(description);
        ContestLocalServiceUtil.updateContest(contest);
    }
    
    public void store(Contest contest) throws SystemException {
        if (contest.isNew()) {
            if (contest.getContestPK() <= 0L) {
                contest.setContestPK(CounterLocalServiceUtil.increment(Contest.class.getName()));
            }
            ContestLocalServiceUtil.addContest(contest);
        }
        else {
            ContestLocalServiceUtil.updateContest(contest);
        }

        reindex(contest);
    }
    
    public PlanTemplate getPlanTemplate(Contest contest) throws PortalException, SystemException {
        if (contest.getPlanTemplateId() > 0) {
            return PlanTemplateLocalServiceUtil.getPlanTemplate(contest.getPlanTemplateId());
        }
        return null;
    }
    
    public FocusArea getFocusArea(Contest contest) throws PortalException, SystemException {
        if (contest.getFocusAreaId() > 0) {
            return FocusAreaLocalServiceUtil.getFocusArea(contest.getFocusAreaId());
        }
        return null;
    }
    
    public Image getLogo(Contest contest) throws PortalException, SystemException {
        return contest.getContestLogoId() > 0 ? 
                ImageLocalServiceUtil.getImage(contest.getContestLogoId()) : 
                null;
    }
    
    public Image getSponsorLogo(Contest contest) throws PortalException, SystemException {
        return contest.getSponsorLogoId() > 0 ? 
                ImageLocalServiceUtil.getImage(contest.getSponsorLogoId()) : 
                null;
    }
    
    public void setLogo(Contest contest, File logoFile) throws IOException, SystemException, PortalException {
//        Image i = ImageLocalServiceUtil.getImage(logoFile);//.getImage(logoFile);   
//        i.setImageId(CounterLocalServiceUtil.increment(Image.class.getName()));
//        
//        ImageLocalServiceUtil.addImage(i);
//        ImageLocalServiceUtil.updateImage(i.getImageId(), i.getTextObj());
//        contest.setContestLogoId(i.getImageId());
    }
    
    public void setSponsorLogo(Contest contest, File logoFile) throws IOException, SystemException, PortalException {
//        Image i = ImageLocalServiceUtil.getImage(logoFile);//.getImage(logoFile);   
//        i.setImageId(CounterLocalServiceUtil.increment(Image.class.getName()));
//        
//        ImageLocalServiceUtil.addImage(i);
//        ImageLocalServiceUtil.updateImage(i.getImageId(), i.getTextObj());
//        contest.setSponsorLogoId(i.getImageId());
    }
    
    public String getLogoPath(Contest contest) throws PortalException, SystemException {
        Image i = getLogo(contest);
        if (i != null) {
            return "/contest?img_id=" + i.getImageId();// + "&t=" + ImageServletTokenUtil.getToken(i.getImageId());
        }
        return "";
    }
    
    public String getSponsorLogoPath(Contest contest) throws PortalException, SystemException {
        Image i = getSponsorLogo(contest);
        if (i != null) {
            return "/contest?img_id=" + i.getImageId();
        }
        return "";
    }
    
    
    public long getProposalsCount(Contest contest) throws PortalException, SystemException {
        // first - get current phase
        ContestPhase activePhase = getActivePhase(contest);
        if (activePhase == null) {
            List<ContestPhase> phases = getPhases(contest);
            if (phases != null && ! phases.isEmpty()) {
                activePhase = phases.get(phases.size() - 1);
            }
        }
        if (activePhase == null) 
            return 0;
        return PlanItemLocalServiceUtil.countPlansByContestPhase(activePhase);
    }
    
    public DiscussionCategoryGroup getDiscussionCategoryGroup(Contest contest) throws PortalException, SystemException {
        DiscussionCategoryGroup dcg = 
            DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(contest.getDiscussionGroupId());
        return dcg;
    }
    
    public long getCommentsCount(Contest contest) throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(getDiscussionCategoryGroup(contest));
    }
    
    public long getProposalsCommentsCount(Contest contest) throws SystemException, PortalException {
        long proposalsCommentsCount = 0;
        for (PlanItem pi: PlanItemLocalServiceUtil.getPlansByContest(contest.getContestPK())) {
            proposalsCommentsCount += PlanItemLocalServiceUtil.getCommentsCount(pi);
        }
        return proposalsCommentsCount;
    }
    
    public long getVotesCount(Contest contest) throws SystemException, PortalException {
        long commentsCount = 0;
        for (PlanItem pi: PlanItemLocalServiceUtil.getPlansByContest(contest.getContestPK())) {
            commentsCount += PlanItemLocalServiceUtil.getVotes(pi);
        }
        
        return commentsCount;
    }
    
    public long getTotalComments(Contest contest) throws PortalException, SystemException {
        return getCommentsCount(contest) + getProposalsCommentsCount(contest);
    }
    
    public List<ContestTeamMember> getTeamMembers(Contest contest) throws SystemException {
        return ContestTeamMemberLocalServiceUtil.findForContest(contest.getContestPK());
    }
    
    /**
     * <p>Returns true if user is subscribed to a contest, false otherwise</p>
     * @param contestPK id of a contest
     * @param userId id of a user
     * @return true if user is subscribed to a contest, false otherwise
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    public boolean isSubscribed(long contestPK, long userId) throws PortalException, SystemException {
        return ActivitySubscriptionLocalServiceUtil.isSubscribed(userId, Contest.class, contestPK, 0, "");
    }
    
    /**
     * <p>Subscribes user to contest</p>
     * @param contestPK id of a contest
     * @param userId id of a user
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Transactional
    public void subscribe(long contestPK, long userId) throws PortalException, SystemException {
        ActivitySubscriptionLocalServiceUtil.addSubscription(Contest.class, contestPK, 0, "", userId);
        Set<Long> proposalsProcessed = new HashSet<Long>();
        // automatically subscribe user to all proposals in the phase but
        for (ContestPhase contestPhase: ContestPhaseLocalServiceUtil.getPhasesForContest(contestPK)) {
            for (Proposal proposal: ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
                if (!proposalsProcessed.contains(proposal.getProposalId())) {
                    ProposalLocalServiceUtil.subscribe(proposal.getProposalId(), userId, true);
                }
                proposalsProcessed.add(proposal.getProposalId());
            }
        }
    }
    
    /**
     * <p>Subscribes user to contest</p>
     * @param contestPK id of a contest
     * @param userId id of a user
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Transactional
    public void unsubscribe(long contestPK, long userId) throws PortalException, SystemException {
        activitySubscriptionLocalService.deleteSubscription(userId, Contest.class, contestPK, 0, "");

        Set<Long> proposalsProcessed = new HashSet<Long>();
        // unsubscribe user from all proposals in the phase to which he was automatically registered  
        for (ContestPhase contestPhase: contestPhaseLocalService.getPhasesForContest(contestPK)) {
            for (Proposal proposal: proposalLocalService.getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
                // remove automatic subscription from proposal
                if (!proposalsProcessed.contains(proposal.getProposalId())) {
                    proposalLocalService.unsubscribe(proposal.getProposalId(), userId, true);
                }
                proposalsProcessed.add(proposal.getProposalId());
            }
        }
    }
    
    private void reindex(Contest contest) {
        Indexer indexer = IndexerRegistryUtil.getIndexer(Contest.class);

        try {
            indexer.reindex(contest.getContestPK());
        } catch (SearchException e) {
            _log.error("Can't reindex contest " + contest.getContestPK(), e);
        }
    }
}
