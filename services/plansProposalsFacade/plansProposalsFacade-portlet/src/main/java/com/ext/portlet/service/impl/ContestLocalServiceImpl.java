package com.ext.portlet.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

import com.ext.portlet.contests.ContestStatus;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.StringUtils;
import org.xcolab.enums.ContestPhasePromoteType;
import org.xcolab.enums.ContestPhaseType;
import org.xcolab.enums.ContestTier;
import org.xcolab.enums.MemberRole;
import org.xcolab.utils.emailnotification.ContestVoteQuestionNotification;
import org.xcolab.utils.judging.ProposalRatingWrapper;
import org.xcolab.utils.judging.ProposalReview;
import org.xcolab.utils.judging.ProposalReviewCsvExporter;

import com.ext.portlet.JudgingSystemActions;
import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.discussions.DiscussionActions;

import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestDebate;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.FocusAreaOntologyTerm;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.PlanType;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalRating;
import com.ext.portlet.model.ProposalRatingType;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.models.CollaboratoriumModelingService;
import com.ext.portlet.service.ActivitySubscriptionLocalServiceUtil;
import com.ext.portlet.service.ClpSerializer;
import com.ext.portlet.service.ContestDebateLocalServiceUtil;
import com.ext.portlet.service.ContestLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.ContestPhaseTypeLocalServiceUtil;
import com.ext.portlet.service.ContestTeamMemberLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.OntologyTermLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.PlanTypeLocalServiceUtil;
import com.ext.portlet.service.PlanVoteLocalServiceUtil;
import com.ext.portlet.service.Proposal2PhaseLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalRatingLocalServiceUtil;
import com.ext.portlet.service.base.ContestLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import edu.mit.cci.roma.client.Simulation;


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
    public List<ContestPhase> getAllPhases(Contest contest) {
        try {
            return ContestPhaseLocalServiceUtil.getPhasesForContest(contest);
        } catch (SystemException e) {
            _log.error(e);
            return new ArrayList<ContestPhase>();

        }
    }

    public List<ContestPhase> getVisiblePhases(Contest contest) throws SystemException, PortalException {
        List<ContestPhase> allPhases = getAllPhases(contest);

        List<ContestPhase> visiblePhases = new ArrayList<>();
        for (ContestPhase phase : allPhases) {
            com.ext.portlet.model.ContestPhaseType phaseType = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(phase.getContestPhaseType());
            if (!phaseType.getInvisible()) {
                visiblePhases.add(phase);
            }
        }

        return visiblePhases;
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
        List<ContestPhase> result = getVisiblePhases(contest);
        for (Iterator<ContestPhase> i = result.iterator(); i.hasNext(); ) {
            if (!ContestPhaseLocalServiceUtil.getPhaseActive(i.next())) {
                i.remove();
            }
        }
        return result;
    }

    public ContestPhase getActivePhase(Contest contest) throws SystemException, PortalException {

        for (ContestPhase phase : getVisiblePhases(contest)) {
            if (ContestPhaseLocalServiceUtil.getPhaseActive(phase)) return phase;
        }
        return null;
    }

    public ContestPhase getActiveOrLastPhase(Contest contest) throws SystemException, PortalException {
        ContestPhase lastPhase = null;
        for (ContestPhase phase : getAllPhases(contest)) {
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
            List<ContestPhase> phases = getVisiblePhases(contest);
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

    public long getTotalCommentsCount(Contest contest) throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(getDiscussionCategoryGroup(contest)) + getProposalsCommentsCount(contest);
    }

    public long getCommentsCount(Contest contest) throws PortalException, SystemException {
        return DiscussionCategoryGroupLocalServiceUtil.getCommentsCount(getDiscussionCategoryGroup(contest));
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
        List<Long> proposalIds = new ArrayList<>();
        for (Proposal proposal : proposalLocalService.getProposalsInContest(contest.getContestPK())) {
            proposalIds.add(proposal.getProposalId());
        }

        if (proposalIds.size() == 0) {
            return 0L;
        }

        DynamicQuery votesQuery = DynamicQueryFactoryUtil.forClass(ProposalVote.class);
        votesQuery.add(RestrictionsFactoryUtil.in("proposalId", proposalIds));

        return dynamicQueryCount(votesQuery);
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
        List<Long> ret = new ArrayList<>();
        boolean addedDefault = false;

        if (StringUtils.isNotBlank(contest.getOtherModels())) {
        	for (String modelId:  contest.getOtherModels().split(",")) {
        		long modelIdLong = Long.parseLong(modelId);
        		ret.add(modelIdLong);
        		if (modelIdLong == contest.getDefaultModelId()) {
        			addedDefault = true;
        		}
        	}
        }
        if (! addedDefault) {
        	ret.add(contest.getDefaultModelId());
        }

        return ret;
    }
    
    public Map<Long, String> getModelIdsAndNames(long contestPK) throws SystemException, PortalException {
    	List<Long> modelIds = getModelIds(contestPK);
    	
        Contest contest = getContest(contestPK);

        Map<Long, String> ret = new HashMap<>();
        for (Long modelId: modelIds) {
        	try {
        		Simulation s = CollaboratoriumModelingService.repository().getSimulation(modelId);
                ret.put(s.getId(), s.getName());
        	
        	}
        	catch (IOException e) {
        		throw new PortalException(e);
        	}
        }
        return ret;
    }


    public Long getDefaultModelId(long contestPK) throws PortalException, SystemException {
        Contest contest = getContest(contestPK);
        return contest.getDefaultModelId();
    }

    private void reindex(Contest contest) {
        Indexer indexer = IndexerRegistryUtil.getIndexer(Contest.class);

        String errorMessage = "Can't reindex contest " + contest.getContestPK();
        try {
            indexer.reindex(contest.getContestPK());
        } catch (SearchException e) {
            _log.error(errorMessage, e);
        } catch (NullPointerException e) {
            _log.error(errorMessage, e);
        }
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

    /**
     * Returns all contests matching the specified contest tier.
     * Returns all contests in the case of ContestTier.NONE
     * @param contestTierType   The specified contest tier
     * @return                  A list of all Contests matching the specified contest tier
     * @throws PortalException
     * @throws SystemException
     */
    public List<Contest> getContestsMatchingTier(long contestTierType) throws PortalException, SystemException {
        if (ContestTier.getContestTierByTierType(contestTierType) == ContestTier.NONE) {
            return contestPersistence.findAll();
        }
        return contestPersistence.findByContestTier(contestTierType);
    }

    /**
     * Returns all contests matching the specified contest tier and ontology terms.
     * Returns all contests in the case of ContestTier.NONE
     * @param contestTierType   The specified contest tier
     * @return                  A list of all Contests matching the specified contest tier
     * @throws PortalException
     * @throws SystemException
     */
    public List<Contest> getContestsMatchingOntologyTermsAndTier(List<OntologyTerm> ontologyTerms, long contestTierType) throws PortalException, SystemException{
        List<Contest> matchedOntologyContests = getContestsMatchingOntologyTerms(ontologyTerms);

        // Ignore tier filter if no contest tier has been selected
        if (ContestTier.getContestTierByTierType(contestTierType) == ContestTier.NONE) {
            return matchedOntologyContests;
        }

        List<Contest> matchedTierContests = getContestsMatchingTier(contestTierType);

        // Cut both lists together
        matchedOntologyContests.retainAll(matchedTierContests);
        return matchedOntologyContests;
    }

    /**
     * This method transfers users' supports for proposals in the passed contest to a vote. If a user has only supported
     * one proposal within the passed contest, the support is automatically transferred to a vote and the user is notified
     * about this action. If the user supports more than one proposal in the passed contest, a message is sent to the user
     * which the user to vote for his/her favourite proposal of the list of supported proposals
     * @param contest               The contest object
     * @param serviceContext        The service contest containing the portal base URL
     * @throws SystemException
     * @throws PortalException
     */
    public void transferSupportsToVote(Contest contest, ServiceContext serviceContext) throws SystemException, PortalException {
        ContestPhase lastOrActivePhase = contestLocalService.getActiveOrLastPhase(contestLocalService.getContest(contest.getContestPK()));
        // Vote is only possible in Winner Selection phase
        if (lastOrActivePhase.getContestPhaseType() != ContestPhaseType.SELECTION_OF_WINNERS.getTypeId() &&
                lastOrActivePhase.getContestPhaseType() != ContestPhaseType.WINNERS_SELECTION.getTypeId()) {
            return;
        }
        
        Set<Long> proposalsUserCanVoteOn = new HashSet<Long>();
        for (Proposal proposal: proposalLocalService.getProposalsInContestPhase(lastOrActivePhase.getContestPhasePK())) {
        	proposalsUserCanVoteOn.add(proposal.getProposalId());
        }
        Map<User, List<Proposal>> userToSupportsMap = getContestSupportingUser(contest);
        for (User user : userToSupportsMap.keySet()) {
            // Do nothing if the user has already voted
            if (proposalVoteLocalService.hasUserVoted(lastOrActivePhase.getContestPhasePK(), user.getUserId())) {
                continue;
            }

            List<Proposal> proposals = new ArrayList<Proposal>();
            if (userToSupportsMap.containsKey(user)) {
            	for (Proposal p: userToSupportsMap.get(user))
            		if (proposalsUserCanVoteOn.contains(p.getProposalId())) {
            			proposals.add(p);
            		}
            }
            if (proposals.isEmpty()) {
            	continue;
            }

            /*
            // Directly transfer the support to a vote
            if (proposals.size() == 1) {
                voteForProposal(user.getUserId(), proposals.get(0).getProposalId(), lastOrActivePhase.getContestPhasePK()); votes will not be converted automatically anymore
                new ContestVoteNotification(user, contest, proposals.get(0), serviceContext).sendEmailNotification();
            }
            // Send a notification to the user
            else {
                new ContestVoteQuestionNotification(user, contest, proposals, serviceContext).sendEmailNotification();
            }
            */
            // Always ask the user to upgrade their support to a vote

            
            new ContestVoteQuestionNotification(user, contest, proposals, serviceContext).sendEmailNotification();
        }
    }

    /**
     * This method generates a CSV string of all judge Reviews of all previous contestPhases of a contest.
     * By using the currentPhase parameter, the output is filtered to only include proposals that are in
     * currentPhase.
     *
     *
     * @param contest           The contest for which the review should be created
     * @param currentPhase      The currently active ContestPhase which should be used for proposal filtering
     * @param serviceContext    A serviceContext which must include the Portal's base URL
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public String getProposalJudgeReviewCsv(Contest contest, ContestPhase currentPhase, ServiceContext serviceContext) throws SystemException, PortalException {
        Map<Proposal,List<ProposalReview>> proposalToProposalReviewsMap = new HashMap<>();

        List<Proposal> stillActiveProposals = proposalLocalService.getActiveProposalsInContestPhase(currentPhase.getContestPhasePK());
        Set<ProposalRatingType> occurringRatingTypes = new HashSet<ProposalRatingType>();
        Set<User> occurringJudges = new HashSet<User>();


        for (ContestPhase judgingPhase : getAllPhases(contest)) {
            if(!judgingPhase.getFellowScreeningActive()){
                continue;
            }
            for (Proposal proposal : stillActiveProposals) {
                try {
                    ProposalContestPhaseAttribute fellowActionAttribute = getProposalContestPhaseAttributeLocalService().
                            getProposalContestPhaseAttribute(proposal.getProposalId(), judgingPhase.getContestPhasePK(),
                                    ProposalContestPhaseAttributeKeys.FELLOW_ACTION);
                    JudgingSystemActions.FellowAction fellowAction = JudgingSystemActions.FellowAction.fromInt((int) fellowActionAttribute.getNumericValue());

                    // Ignore proposals that have not been passed to judge
                    if (fellowAction != JudgingSystemActions.FellowAction.PASS_TO_JUDGES && judgingPhase.isFellowScreeningActive()) {
                        continue;
                    }
                } catch (NoSuchProposalContestPhaseAttributeException e) {
                    // Skip when proposal fellow screening is active but not yet done for this proposal
                    if (judgingPhase.isFellowScreeningActive()) {
                        _log.info("Fellow screening enabled proposal with ID " + proposal.getProposalId() + " has not been screened by fellows yet.");
                        continue;
                    }
                }

                final String proposalUrl = serviceContext.getPortalURL() + proposalLocalService.getProposalLinkUrl(contest, proposal, judgingPhase);
                final ProposalReview proposalReview = new ProposalReview(proposal, judgingPhase, proposalUrl);
                proposalReview.setReviewers(ImmutableSet.copyOf(getProposalReviewingJudges(proposal, judgingPhase)));
                List<ProposalRating> ratings = ProposalRatingLocalServiceUtil.getJudgeRatingsForProposal(proposal.getProposalId(), judgingPhase.getContestPhasePK());
                Map<ProposalRatingType, List<Long>> ratingsPerType = new HashMap<ProposalRatingType, List<Long>>();

                for (ProposalRating rating: ratings) {
                    ProposalRatingWrapper wrapper = new ProposalRatingWrapper(rating);
                    if (ratingsPerType.get(wrapper.getRatingType()) == null) {
                        ratingsPerType.put(wrapper.getRatingType(), new ArrayList<Long>());
                    }
                    ratingsPerType.get(wrapper.getRatingType()).add(wrapper.getRatingValue().getValue());

                    proposalReview.addUserRating(wrapper.getUser(),wrapper.getRatingType(),wrapper.getRatingValue().getValue());

                    occurringRatingTypes.add(wrapper.getRatingType());
                    if (rating.isCommentEnabled()) {
                        proposalReview.addReview(wrapper.getUser(), rating.getComment());
                        occurringJudges.add(wrapper.getUser());
                    }
                }

                //take the average for each type
                for (ProposalRatingType key : ratingsPerType.keySet()) {
                    double sum = 0;
                    int count = 0;
                    for (Long val: ratingsPerType.get(key)) {
                        sum += val;
                        count++;
                    }
                    double avg = sum/count;
                    proposalReview.addRatingAverage(key, avg);
                }

                if (Validator.isNull(proposalToProposalReviewsMap.get(proposal))) {
                    proposalToProposalReviewsMap.put(proposal, new ArrayList<ProposalReview>());
                }

                proposalToProposalReviewsMap.get(proposal).add(proposalReview);
            }
        }

        ProposalReviewCsvExporter csvExporter = new ProposalReviewCsvExporter(proposalToProposalReviewsMap, new ArrayList(occurringJudges), new ArrayList(occurringRatingTypes));

        return csvExporter.getCsvString();
    }

    /**
     * Returns all Judge Users that have been selected by Fellows for the proposal review or all
     * Contest Judges if fellow screening is disabled
     *
     * @param proposal
     * @param judgingPhase
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    private List<User> getProposalReviewingJudges(Proposal proposal, ContestPhase judgingPhase) throws SystemException, PortalException {
        List<User> selectedJudges = null;

        if (judgingPhase.isFellowScreeningActive()) {
            try {
                final String judgeIdString = getProposalContestPhaseAttributeLocalService().
                        getProposalContestPhaseAttribute(proposal.getProposalId(), judgingPhase.getContestPhasePK(),
                                ProposalContestPhaseAttributeKeys.SELECTED_JUDGES).getStringValue();

                selectedJudges = new ArrayList<>();

                for (String element : judgeIdString.split(";")) {
                    long userId = GetterUtil.getLong(element);
                    User judge = userLocalService.getUser(userId);
                    selectedJudges.add(judge);
                }
            } catch (NoSuchProposalContestPhaseAttributeException e) {
                _log.error("ContestPhaseAttribute of proposal with ID " + proposal.getProposalId() + " have not been found", e);
            }
        }
        // Choose all judges
        else {
            selectedJudges =  getJudgesForContest(contestLocalService.getContest(judgingPhase.getContestPK()));
        }

        return selectedJudges;
    }

    public List<User> getAdvisorsForContest(Contest contest) throws SystemException, PortalException {
        Map<MemberRole, List<User>> roleToUserMap = getContestTeamMembersByRole(contest);
        if (Validator.isNotNull(roleToUserMap) && Validator.isNotNull(roleToUserMap.get(MemberRole.ADVISOR))) {
            return roleToUserMap.get(MemberRole.ADVISOR);
        }
        return new ArrayList<>();
    }

    public List<User> getJudgesForContest(Contest contest) throws SystemException, PortalException {
        Map<MemberRole, List<User>> roleToUserMap = getContestTeamMembersByRole(contest);
        if (Validator.isNotNull(roleToUserMap) && Validator.isNotNull(roleToUserMap.get(MemberRole.JUDGES))) {
            return roleToUserMap.get(MemberRole.JUDGES);
        }
        return new ArrayList<>();
    }

    public List<User> getFellowsForContest(Contest contest) throws SystemException, PortalException {
        Map<MemberRole, List<User>> roleToUserMap = getContestTeamMembersByRole(contest);
        if (Validator.isNotNull(roleToUserMap) && Validator.isNotNull(roleToUserMap.get(MemberRole.FELLOW))) {
            return roleToUserMap.get(MemberRole.FELLOW);
        }
        return new ArrayList<>();
    }

    public List<User> getContestManagersForContest(Contest contest) throws SystemException, PortalException {
        Map<MemberRole, List<User>> roleToUserMap = getContestTeamMembersByRole(contest);
        if (Validator.isNotNull(roleToUserMap) && Validator.isNotNull(roleToUserMap.get(MemberRole.CONTESTMANAGER))) {
            return roleToUserMap.get(MemberRole.CONTESTMANAGER);
        }
        return new ArrayList<>();
    }

    private List<ContestPhase> getJudgingContestPhases(Contest contest) throws SystemException {
        List<ContestPhase> judgingPhases = new ArrayList<>();

        for (ContestPhase contestPhase : contestPhaseLocalService.getPhasesForContest(contest)) {
            ContestPhasePromoteType promoteType = ContestPhasePromoteType.getPromoteType(contestPhase.getContestPhaseAutopromote());
            if (promoteType == ContestPhasePromoteType.PROMOTE_JUDGED) {
                judgingPhases.add(contestPhase);
            }
        }

        return judgingPhases;
    }
    /**
     * Returns a map object that maps MemberRoles to a list of associated users for the respective MemberRole
     * according to the ContestTeamMember table
     *
     * @param contest           The contest for which the mapping is requested
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    private Map<MemberRole, List<User>> getContestTeamMembersByRole(Contest contest) throws PortalException, SystemException {
        Map<MemberRole, List<User>> teamRoleToUsersMap = new TreeMap<>();
        for (ContestTeamMember ctm : ContestLocalServiceUtil.getTeamMembers(contest)) {
            MemberRole teamRole = MemberRole.getMember(ctm.getRole());
            List<User> roleUsers = teamRoleToUsersMap.get(teamRole);
            if (roleUsers == null) {
                roleUsers = new ArrayList<>();
                teamRoleToUsersMap.put(teamRole, roleUsers);
            }
            roleUsers.add(ContestTeamMemberLocalServiceUtil.getUser(ctm));
        }

        return teamRoleToUsersMap;
    }

    /**
     * Returns the URL link address for the passed contest
     *
     * @param contest   The contest object
     * @return          Contest URL as String
     */
    public String getContestLinkUrl(Contest contest) {
        String link = "/web/guest/plans/-/plans/contestId/%d";
        return String.format(link, contest.getContestPK());
    }

    private Map<User, List<Proposal>> getContestSupportingUser(Contest contest) throws SystemException, PortalException {
        List<Proposal> proposalsInContest = proposalLocalService.getProposalsInContest(contest.getContestPK());

        HashMap<User, List<Proposal>> userToSupportsMap = new HashMap<>();
        // Generate a list of supporting proposals for each user
        for (ProposalSupporter supporter : proposalSupporterLocalService.getProposalSupportersForProposals(proposalsInContest)) {
            User user = userLocalService.getUser(supporter.getUserId());

            List<Proposal> proposals = userToSupportsMap.get(user);
            if (proposals == null) {
                proposals = new ArrayList<Proposal>();
                userToSupportsMap.put(user, proposals);
            }

            proposals.add(proposalLocalService.getProposal(supporter.getProposalId()));
        }

        return userToSupportsMap;
    }

    private void voteForProposal(long userId, long proposalId, long contestPhaseId) throws SystemException, PortalException {
        proposalLocalService.addVote(proposalId, contestPhaseId, userId);
        int analyticsValue = 0;
        int supportedCount = ProposalLocalServiceUtil.getUserVotedProposalsCount(userId);
        if (supportedCount > 0) {
            if (supportedCount == 1) {
                analyticsValue = 1;
            }
            else if ( supportedCount < 5) {
                analyticsValue = 2;
            }
            else {
                analyticsValue = 3;
            }/*
            AnalyticsUtil.publishEvent(request, userId, VOTE_ANALYTICS_KEY + analyticsValue,
                    VOTE_ANALYTICS_CATEGORY,
                    VOTE_ANALYTICS_ACTION,
                    VOTE_ANALYTICS_LABEL,
                    analyticsValue);
        }*/
        }
    }
    
    public boolean hasContestEnded(long contestPK) throws SystemException, PortalException {
    	return hasContestEnded(getContest(contestPK));
    }

    public boolean hasContestEnded(Contest contest) throws SystemException, PortalException {
    	ContestPhase activePhase = getActiveOrLastPhase(contest);
        com.ext.portlet.model.ContestPhaseType type = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(activePhase.getContestPhaseType());
        boolean typeIsClosed =
                ContestStatus.COMPLETED.toString().toUpperCase().equals(type.getStatus().toUpperCase()) ||
                ContestStatus.CLOSED.toString().toUpperCase().equals(type.getStatus().toUpperCase()) ||
                ContestStatus.FINISHED.toString().toUpperCase().equals(type.getStatus().toUpperCase());
        //Either, the active or last phase has no end date (which means the contest ended), or the current date is after it's end date.
    	if (typeIsClosed && (activePhase.getPhaseEndDate() == null || new Date().after(activePhase.getPhaseEndDate()))) {
    		return true;
    	}
    	return false;
    }
    
    
    public Proposal getWinnerProposal(long contestPK) throws SystemException, PortalException {
    	Contest contest = getContest(contestPK);
        //only return a winner if the contest is done
    	if (!hasContestEnded(contest)) {
            return null;
        }
    	ContestPhase lastPhase = getActiveOrLastPhase(contest);
        //this is the winner of combined award such as judges & popular choice award. he will be returned if no one won the popular choice award directly
        Proposal winnerOfCombinedAwards = null;

        for (Proposal proposal : ProposalLocalServiceUtil.getActiveProposalsInContestPhase(lastPhase.getContestPhasePK())) {
            Proposal2Phase p2p = Proposal2PhaseLocalServiceUtil.getByProposalIdContestPhaseId(proposal.getProposalId(), lastPhase.getContestPhasePK());
            try {
            	ProposalContestPhaseAttribute pcpa = 
            			proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttribute(
            					proposal.getProposalId(), 
            					lastPhase.getContestPhasePK(), 
            					ProposalContestPhaseAttributeKeys.RIBBON);
            	if (pcpa.getNumericValue() == 2) {
            		// FIXME - this has to be improved to make sure that we select proper ribbon
            		return proposal;
            	} else if (pcpa.getNumericValue() == 5 || pcpa.getNumericValue() == 7|| pcpa.getNumericValue() == 8|| pcpa.getNumericValue() == 9) {
                    winnerOfCombinedAwards = proposal;
                }
            }
            catch (NoSuchProposalContestPhaseAttributeException e) {
            	// ignore
            }
            
        }
        return winnerOfCombinedAwards;
    	
    }

    public Integer getPointsAccessibleForActivePhaseOfContest(Contest contest) throws SystemException, PortalException {
        //check if the phase, the contest is currently in, allows for editing
        ContestPhase activePhase = ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest);
        if (activePhase != null) {
            com.ext.portlet.model.ContestPhaseType cpType = ContestPhaseTypeLocalServiceUtil.getContestPhaseType(activePhase.getContestPhaseType());
            if (cpType != null) {
                return cpType.getPointsAccessible();
            }
        }
        return null;
    }
}
