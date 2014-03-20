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
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
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
import com.ext.portlet.service.ContestPhaseLocalService;
import com.liferay.portal.model.User;
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
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;

import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.FocusAreaOntologyTerm;

import com.ext.portlet.service.base.ContestLocalServiceBaseImpl;

import com.ext.portlet.service.OntologyTermLocalServiceUtil;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
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
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;

/**
 * The implementation of the contest local service.
 * <p/>
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ContestLocalService} interface.
 * <p/>
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

    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";
    public Contest getContestByActiveFlag(boolean contestActive) throws SystemException, NoSuchContestException {
        List<Contest> contests = contestPersistence.findByContestActive(contestActive);
        if (contests.isEmpty()) {
        	throw new NoSuchContestException();
        }
        return contests.get(0);
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
        for (Contest c : ContestLocalServiceUtil.getContests(0, Integer.MAX_VALUE)) {
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
        group = GroupLocalServiceUtil.addGroup(c.getAuthorId(), null, c.getContestPK(), "CONTEST:  " + c.getContestPK(),
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

        String[] ownerActions = {DiscussionActions.ADMIN.name(), DiscussionActions.ADD_CATEGORY.name(),
                DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADMIN_CATEGORIES.name(), DiscussionActions.ADMIN_MESSAGES.name(),
                DiscussionActions.ADD_COMMENT.name()};

        String[] adminActions = {DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name()};

        String[] moderatorActions = {DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADMIN_CATEGORIES.name(),
                DiscussionActions.ADMIN_MESSAGES.name(), DiscussionActions.ADD_COMMENT.name()};

        String[] memberActions = {DiscussionActions.ADD_CATEGORY.name(), DiscussionActions.ADD_MESSAGE.name(),
                DiscussionActions.ADD_THREAD.name(), DiscussionActions.ADD_COMMENT.name()};

        String[] userActions = {DiscussionActions.ADD_MESSAGE.name(), DiscussionActions.ADD_THREAD.name(),
                DiscussionActions.ADD_COMMENT.name()};

        String[] guestActions = {};

        Map<Role, String[]> rolesActionsMap = new HashMap<Role, String[]>();

        rolesActionsMap.put(owner, ownerActions);
        rolesActionsMap.put(admin, adminActions);
        rolesActionsMap.put(member, memberActions);
        rolesActionsMap.put(userRole, userActions);
        rolesActionsMap.put(guest, guestActions);
        rolesActionsMap.put(moderator, moderatorActions);

        for (Role role : rolesActionsMap.keySet()) {
            ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId,
                    DiscussionCategoryGroup.class.getName(), ResourceConstants.SCOPE_GROUP,
                    String.valueOf(group.getGroupId()), role.getRoleId(), rolesActionsMap.get(role));
        }

        c.setGroupId(group.getGroupId());
        c.setDiscussionGroupId(categoryGroup.getPrimaryKey());
        store(c);
    }
    
    public List<Contest> findByActive(boolean active) throws SystemException {
        return contestPersistence.findByContestActive(active);
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


    /**
     * Methods from ContestImpl *
     */
    public List<ContestPhase> getPhases(Contest contest) {
        try {
            return ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
        } catch (SystemException e) {
            _log.error(e);
            return new ArrayList<ContestPhase>();

        }
    }

    public PlanType getPlanType(Contest contest) throws SystemException, PortalException {
        try {
            return (PlanType) ClpSerializer.translateOutput(PlanTypeLocalServiceUtil.getPlanType(contest.getPlanTypeId()));
        } catch (PortalException | SystemException e) {
            _log.error(e);
            return null;
        }
    }

    public List<ContestPhase> getActivePhases(Contest contest) throws SystemException, PortalException {
        List<ContestPhase> result = getPhases(contest);
        for (Iterator<ContestPhase> i = result.iterator(); i.hasNext(); ) {
            if (!ContestPhaseLocalServiceUtil.getPhaseActive(i.next())) {
                i.remove();
            }
        }
        return result;
    }

    public ContestPhase getActivePhase(Contest contest) throws SystemException {

        for (ContestPhase phase : getPhases(contest)) {
            if (ContestPhaseLocalServiceUtil.getPhaseActive(phase)) return phase;
        }
        return null;
    }

    public ContestPhase getActiveOrLastPhase(Contest contest) throws SystemException {
        ContestPhase lastPhase = null;
        for (ContestPhase phase : getPhases(contest)) {
            if (lastPhase == null || lastPhase.getPhaseStartDate().before(phase.getPhaseStartDate())) lastPhase = phase;
            if (ContestPhaseLocalServiceUtil.getPhaseActive(phase)) return phase;
        }
        return lastPhase;
    }

    public boolean isActive(Contest contest) throws SystemException {
        try {
            ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
            return true;
        } catch (Exception e) {
            // ignore
        }
        return false;
    }

    public List<Long> getDebatesIds(Contest contest) throws SystemException {
        List<Long> ret = new ArrayList<Long>();
        for (ContestDebate pos : ContestDebateLocalServiceUtil.getContestDebates(contest.getContestPK())) {
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
        } else {
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
        ContestPhase activePhase = getActiveOrLastPhase(contest);
        if (activePhase == null) {
            List<ContestPhase> phases = getPhases(contest);
            if (phases != null && !phases.isEmpty()) {
                activePhase = phases.get(phases.size() - 1);
            }
        }
        if (activePhase == null)
            return 0;
        
        return proposalLocalService.countProposalsInContestPhase(activePhase.getContestPhasePK());
    }

    public DiscussionCategoryGroup getDiscussionCategoryGroup(Contest contest) throws PortalException, SystemException {
        DiscussionCategoryGroup dcg =
                DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(contest.getDiscussionGroupId());
        return dcg;
    }

    public long getCommentsCount(Contest contest) throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(getDiscussionCategoryGroup(contest)) + getProposalsCommentsCount(contest);
    }

    public long getProposalsCommentsCount(Contest contest) throws SystemException, PortalException {
        long proposalsCommentsCount = 0;
        try {
            ContestPhase activeContestPhase = ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
            for (Proposal proposal: proposalLocalService.getActiveProposalsInContestPhase(activeContestPhase.getContestPhasePK())) {
                proposalsCommentsCount += proposalLocalService.getCommentsCount(proposal.getProposalId());
            }
        } catch (SystemException e) {
            return 0;
        }
        return proposalsCommentsCount;
    }

    public long getVotesCount(Contest contest) throws SystemException, PortalException {
        long votesCount = 0;
        for (PlanItem pi : PlanItemLocalServiceUtil.getPlansByContest(contest.getContestPK())) {
            votesCount += PlanItemLocalServiceUtil.getVotes(pi);
        }

        return votesCount;
    }

    public long getTotalComments(Contest contest) throws PortalException, SystemException {
        return getCommentsCount(contest) + getProposalsCommentsCount(contest);
    }

    public List<ContestTeamMember> getTeamMembers(Contest contest) throws SystemException {
        return ContestTeamMemberLocalServiceUtil.findForContest(contest.getContestPK());
    }

    /**
     * <p>Returns true if user is subscribed to a contest, false otherwise</p>
     *
     * @param contestPK id of a contest
     * @param userId    id of a user
     * @return true if user is subscribed to a contest, false otherwise
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    public boolean isSubscribed(long contestPK, long userId) throws PortalException, SystemException {
        return ActivitySubscriptionLocalServiceUtil.isSubscribed(userId, Contest.class, contestPK, 0, "");
    }

    /**
     * <p>Subscribes user to contest</p>
     *
     * @param contestPK id of a contest
     * @param userId    id of a user
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Transactional
    public void subscribe(long contestPK, long userId) throws PortalException, SystemException {
        ActivitySubscriptionLocalServiceUtil.addSubscription(Contest.class, contestPK, 0, "", userId);
        Set<Long> proposalsProcessed = new HashSet<Long>();
        // automatically subscribe user to all proposals in the phase but
        for (ContestPhase contestPhase : ContestPhaseLocalServiceUtil.getPhasesForContest(contestPK)) {
            for (Proposal proposal : ProposalLocalServiceUtil.getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
                if (!proposalsProcessed.contains(proposal.getProposalId())) {
                    ProposalLocalServiceUtil.subscribe(proposal.getProposalId(), userId, true);
                }
                proposalsProcessed.add(proposal.getProposalId());
            }
        }
    }

    /**
     * <p>Subscribes user to contest</p>
     *
     * @param contestPK id of a contest
     * @param userId    id of a user
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Transactional
    public void unsubscribe(long contestPK, long userId) throws PortalException, SystemException {
        activitySubscriptionLocalService.deleteSubscription(userId, Contest.class, contestPK, 0, "");

        Set<Long> proposalsProcessed = new HashSet<Long>();
        // unsubscribe user from all proposals in the phase to which he was automatically registered  
        for (ContestPhase contestPhase : contestPhaseLocalService.getPhasesForContest(contestPK)) {
            for (Proposal proposal : proposalLocalService.getProposalsInContestPhase(contestPhase.getContestPhasePK())) {
                // remove automatic subscription from proposal
                if (!proposalsProcessed.contains(proposal.getProposalId())) {
                    proposalLocalService.unsubscribe(proposal.getProposalId(), userId, true);
                }
                proposalsProcessed.add(proposal.getProposalId());
            }
        }
    }

    public List<Long> getModelIds(long contestPK) throws SystemException, PortalException {
        Contest contest = getContest(contestPK);
        PlanType planType = planTypeLocalService.getPlanType(contest.getPlanTypeId());

        List<Long> ret = new ArrayList<>();
        for (Simulation s : planTypeLocalService.getAvailableModels(planType)) {
            ret.add(s.getId());
        }

        return ret;
    }
    
    public Map<Long, String> getModelIdsAndNames(long contestPK) throws SystemException, PortalException {
        Contest contest = getContest(contestPK);
        PlanType planType = planTypeLocalService.getPlanType(contest.getPlanTypeId());

        Map<Long, String> ret = new HashMap<>();
        for (Simulation s : planTypeLocalService.getAvailableModels(planType)) {
            ret.put(s.getId(), s.getName());
        }
        return ret;
    }


    public Long getDefaultModelId(long contestPK) throws PortalException, SystemException {
        Contest contest = getContest(contestPK);
        if (contest.getPlanTypeId() > 0) {
        	PlanType planType = planTypeLocalService.getPlanType(contest.getPlanTypeId());
        	return planType.getDefaultModelId();
        }
        return 0L;
    }

    private void reindex(Contest contest) {
        Indexer indexer = IndexerRegistryUtil.getIndexer(Contest.class);

        try {
            indexer.reindex(contest.getContestPK());
        } catch (SearchException e) {
            _log.error("Can't reindex contest " + contest.getContestPK(), e);
        }
    }

    public int getNumberOfProposalsForJudge(User u, Contest c) throws PortalException, SystemException{
        long lastContestPhase = ContestPhaseLocalServiceUtil.getPhasesForContest(c).get(ContestPhaseLocalServiceUtil.getPhasesForContest(c).size()-1).getContestPhasePK();

        List<Proposal> proposals = ProposalLocalServiceUtil.getProposalsInContestPhase(lastContestPhase);
        int counter=0;
        for (Proposal p : proposals){
            String judges = "";
            try{
                judges = ProposalContestPhaseAttributeLocalServiceUtil.getProposalContestPhaseAttribute(p.getProposalId(), lastContestPhase, ProposalContestPhaseAttributeKeys.SELECTED_JUDGES).getStringValue();
            } catch (NoSuchProposalContestPhaseAttributeException e) {  }
            if (StringUtils.containsIgnoreCase(judges,u.getUserId()+"")) counter++;
        }
        return counter;
    }
    
    public List<Contest> getContestsByActivePrivate(boolean active, boolean privateContest) throws SystemException {
    	return contestPersistence.findByContestActivecontestPrivate(active, privateContest);	
    }

    public List<Contest> getContestsMatchingOntologyTerms(List<OntologyTerm> ontologyTerms) throws PortalException, SystemException{
        // remove terms that are root elements
        for (Iterator<OntologyTerm> i = ontologyTerms.iterator(); i.hasNext();){
            OntologyTerm o = i.next();
            if (o.getParentId() == 0) i.remove();
        }
        Long[][] terms = new Long[ontologyTerms.size()][];
        // get all child elements and add id's to array
        int i = 0;
        for (OntologyTerm ot : ontologyTerms){
            List<OntologyTerm> childTerms = OntologyTermLocalServiceUtil.getAllDescendantTerms(ot);
            terms[i] = new Long[childTerms.size() + 1];
            terms[i][0] = ot.getId();
            int k = 1;
            for (OntologyTerm child : childTerms){
                terms[i][k] = child.getId();
                k++;
            }
            i++;
        }
        // for each first dimension execute a query
        ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(
            ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader");
        List<Contest>[] contestsMatchingTerms = new List[terms.length];
        for (int l=0; l<terms.length; l++){
            DynamicQuery dq =  DynamicQueryFactoryUtil.forClass(FocusAreaOntologyTerm.class, portletClassLoader);
            dq.add(PropertyFactoryUtil.forName("primaryKey.ontologyTermId").in(terms[l]));
            dq.setProjection(ProjectionFactoryUtil.distinct(ProjectionFactoryUtil.property("primaryKey.focusAreaId")));

            DynamicQuery contestQuery =  DynamicQueryFactoryUtil.forClass(Contest.class, portletClassLoader);
            contestQuery.add(PropertyFactoryUtil.forName("focusAreaId").in(dq));
            contestsMatchingTerms[l] = ContestLocalServiceUtil.dynamicQuery(contestQuery);
        }

        List<Contest> listOfContests = new ArrayList<>(contestsMatchingTerms[0]);

        // take only contests matching all requirements
        for (int m=1; m<contestsMatchingTerms.length; m++){
            listOfContests.retainAll(contestsMatchingTerms[m]);
        }

        return listOfContests;
    }
}
