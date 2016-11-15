package com.ext.portlet.service.impl;

import edu.mit.cci.roma.client.Simulation;
import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.NoSuchContestException;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.contests.ContestStatus;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestTeamMember;
import com.ext.portlet.model.ContestTeamMemberRole;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.FocusAreaOntologyTerm;
import com.ext.portlet.model.ImpactIteration;
import com.ext.portlet.model.ImpactTemplateFocusAreaList;
import com.ext.portlet.model.ImpactTemplateMaxFocusArea;
import com.ext.portlet.model.ImpactTemplateSeries;
import com.ext.portlet.model.OntologyTerm;
import com.ext.portlet.model.PlanTemplate;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.FocusAreaOntologyTermLocalServiceUtil;
import com.ext.portlet.service.PlanTemplateLocalServiceUtil;
import com.ext.portlet.service.base.ContestLocalServiceBaseImpl;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.NoSuchModelException;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletClassLoaderUtil;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.Image;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.ImageLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;

import org.xcolab.client.activities.ActivitiesClient;
import org.xcolab.client.activities.ActivitiesClientUtil;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.comment.util.CommentClientUtil;
import org.xcolab.client.comment.util.ThreadClientUtil;
import org.xcolab.client.modeling.RomaClientUtil;
import org.xcolab.enums.ContestPhaseTypeValue;
import org.xcolab.enums.ContestTier;
import org.xcolab.enums.MemberRole;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.exceptions.ReferenceResolutionException;
import org.xcolab.utils.IdListUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;


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
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.contestLocalService} to access the contest local service.
     */

    public static final List<Long> ANY_TERM_IDS = Arrays.asList(1L,2L,3L, 1300601L);
    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on contest %s";
    private final Random rand = new Random();

    private final static Log _log = LogFactoryUtil.getLog(ContestLocalServiceImpl.class);

    private static final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";

    @Override
    public Contest getContestByActiveFlag(boolean contestActive) throws SystemException, NoSuchContestException {
        List<Contest> contests = contestPersistence.findByActive(contestActive);
        if (contests.isEmpty()) {
        	throw new NoSuchContestException();
        }
        return contests.get(0);
    }

    @Override
    public Contest createNewContest(Long userId, String name) throws SystemException, PortalException {
        Contest c = contestLocalService.createContest(CounterLocalServiceUtil.increment(Contest.class.getName()));

        c.setAuthorId(userId);
        c.setContestName(name);
        c.setContestShortName(name);
        c.setContestUrlName(generateContestUrlName(c));

        setGroupAndDiscussionForContest(c);

        store(c);
        return c;
    }

    @Override
    public void updateContestGroupsAndDiscussions() throws SystemException, PortalException {
        for (Contest c : contestLocalService.getContests(0, Integer.MAX_VALUE)) {
            if (c.getGroupId() <= 0) {
                setGroupAndDiscussionForContest(c);
            }
        }
    }

    private void setGroupAndDiscussionForContest(Contest c) throws PortalException, SystemException {

        ServiceContext groupServiceContext = new ServiceContext();
        groupServiceContext.setUserId(c.getAuthorId());

        String groupName = c.getContestName() + "_" + System.currentTimeMillis() + "_" + rand.nextLong();
        Group group = GroupLocalServiceUtil.addGroup(c.getAuthorId(), null, c.getContestPK(), "CONTEST:  " + c.getContestPK(),
                String.format(DEFAULT_GROUP_DESCRIPTION, groupName),
                GroupConstants.TYPE_SITE_RESTRICTED, null, true, true, groupServiceContext);

        CommentThread thread = new CommentThread();
        thread.setTitle(c.getContestName() + " discussion");
        thread.setAuthorId(c.getAuthorId());
        thread.setIsQuiet(false);
        long discussionId = ThreadClientUtil.createThread(thread).getThreadId();
        c.setGroupId(group.getGroupId());
        c.setDiscussionGroupId(discussionId);
        store(c);
    }

    @Override
    public List<Contest> getByContestUrlName(String contestUrlName) throws SystemException {
        return contestPersistence.findByContestUrlName(contestUrlName);
    }

    @Override
    public List<Contest> findByContestYear(long contestYear) throws SystemException {
        return contestPersistence.findByContestYear(contestYear);
    }

    @Override
    public Contest getByContestUrlNameContestYear(String contestUrlName, long year)
            throws SystemException, NoSuchContestException {
        return contestPersistence.findByContestUrlNameContestYear(contestUrlName, year);
    }
    
    @Override
    public List<Contest> findByActive(boolean active) throws SystemException {
        return contestPersistence.findByActive(active);
    }
    
    @Override
    public List<Contest> findByActiveFeatured(boolean active, boolean featured) throws SystemException {
        return contestPersistence.findByActiveFeatured(active, featured);
    }

    @Override
    public List<Contest> findByActiveFlag(boolean active, int flag) throws SystemException {
        return contestPersistence.findByActiveFlag(active, flag);
    }

    /**
     * Methods from ContestImpl *
     */
    @Override
    public List<ContestPhase> getAllPhases(Contest contest) {
        try {
            return contestPhaseLocalService.getPhasesForContest(contest);
        } catch (SystemException e) {
            _log.error(e);
            return new ArrayList<>();

        }
    }

    @Override
    public List<ContestPhase> getVisiblePhases(Contest contest) throws SystemException, PortalException {
        List<ContestPhase> allPhases = getAllPhases(contest);

        List<ContestPhase> visiblePhases = new ArrayList<>();
        for (ContestPhase phase : allPhases) {
            com.ext.portlet.model.ContestPhaseType phaseType = contestPhaseTypeLocalService.getContestPhaseType(phase.getContestPhaseType());
            if (!phaseType.getInvisible()) {
                visiblePhases.add(phase);
            }
        }

        return visiblePhases;
    }

    @Override
    public List<ContestPhase> getActivePhases(Contest contest) throws SystemException, PortalException {
        List<ContestPhase> result = getVisiblePhases(contest);
        for (Iterator<ContestPhase> i = result.iterator(); i.hasNext(); ) {
            if (!contestPhaseLocalService.getPhaseActive(i.next())) {
                i.remove();
            }
        }
        return result;
    }

    @Override
    public ContestPhase getActivePhase(Contest contest) throws SystemException, PortalException {

        for (ContestPhase phase : getVisiblePhases(contest)) {
            if (contestPhaseLocalService.getPhaseActive(phase)) {
                return phase;
            }
        }
        return null;
    }

    @Override
    public ContestPhase getActiveOrLastPhase(Contest contest) throws SystemException, PortalException {
        ContestPhase lastPhase = null;
        for (ContestPhase phase : getAllPhases(contest)) {
            if (lastPhase == null || lastPhase.getPhaseStartDate().before(phase.getPhaseStartDate())) {
                lastPhase = phase;
            }
            if (contestPhaseLocalService.getPhaseActive(phase)) {
                return phase;
            }
        }
        return lastPhase;
    }

    @Override
    public boolean isActive(Contest contest) {
        try {
            contestPhaseLocalService.getActivePhaseForContest(contest);
            return true;
        } catch (SystemException | PortalException ignored) { }
        return false;
    }

    @Override
    public void store(Contest contest) throws SystemException {
        if (contest.isNew()) {
            if (contest.getContestPK() <= 0L) {
                contest.setContestPK(CounterLocalServiceUtil.increment(Contest.class.getName()));
            }
            contestLocalService.addContest(contest);
        } else {
            contestLocalService.updateContest(contest);
        }

        reindex(contest);
    }

    @Override
    public PlanTemplate getPlanTemplate(Contest contest) throws PortalException, SystemException {
        if (contest.getPlanTemplateId() > 0) {
            return PlanTemplateLocalServiceUtil.getPlanTemplate(contest.getPlanTemplateId());
        }
        return null;
    }

    @Override
    public FocusArea getFocusArea(Contest contest) throws PortalException, SystemException {
        if (contest.getFocusAreaId() > 0) {
            return FocusAreaLocalServiceUtil.getFocusArea(contest.getFocusAreaId());
        }
        return null;
    }

    @Override
    public Image getLogo(Contest contest) throws PortalException, SystemException {
        return contest.getContestLogoId() > 0 ?
                ImageLocalServiceUtil.getImage(contest.getContestLogoId()) :
                null;
    }

    @Override
    public Image getSponsorLogo(Contest contest) throws PortalException, SystemException {
        return contest.getSponsorLogoId() > 0 ?
                ImageLocalServiceUtil.getImage(contest.getSponsorLogoId()) :
                null;
    }

    @Override
    public String getLogoPath(Contest contest) throws PortalException, SystemException {
        Image i = getLogo(contest);
        if (i != null) {
            return "/contest?img_id=" + i.getImageId();// + "&t=" + ImageServletTokenUtil.getToken(i.getImageId());
        }
        return "";
    }

    @Override
    public String getSponsorLogoPath(Contest contest) throws PortalException, SystemException {
        Image i = getSponsorLogo(contest);
        if (i != null) {
            return "/contest?img_id=" + i.getImageId();
        }
        return "";
    }

    @Override
    public long getProposalsCount(Contest contest) throws PortalException, SystemException {
        // first - get current phase
        ContestPhase activePhase = getActiveOrLastPhase(contest);
        if (activePhase == null) {
            List<ContestPhase> phases = getVisiblePhases(contest);
            if (phases != null && !phases.isEmpty()) {
                activePhase = phases.get(phases.size() - 1);
            }
        }
        if (activePhase == null) {
            return 0;
        }
        
        return proposalLocalService.countProposalsInContestPhase(activePhase.getContestPhasePK());
    }

    @Override
    public long getTotalCommentsCount(Contest contest) throws PortalException, SystemException {
        return getCommentsCount(contest) + getProposalsCommentsCount(contest);
    }

    @Override
    public long getCommentsCount(Contest contest) {
        return CommentClientUtil.countComments(contest.getDiscussionGroupId());
    }

    @Override
    public long getProposalsCommentsCount(Contest contest) throws SystemException, PortalException {
        long proposalsCommentsCount = 0;
        try {
            ContestPhase activeContestPhase = contestPhaseLocalService.getActivePhaseForContest(contest);
            for (Proposal proposal: proposalLocalService.getActiveProposalsInContestPhase(activeContestPhase.getContestPhasePK())) {
                proposalsCommentsCount += proposalLocalService.getCommentsCount(proposal.getProposalId());
            }
        } catch (SystemException e) {
            return 0;
        }
        return proposalsCommentsCount;
    }

    @Override
    public long getVotesCount(Contest contest) throws SystemException, PortalException {
        ContestPhase contestPhase = contestPhaseLocalService.getActivePhaseForContest(contest);
        List<Long> proposalIds = new ArrayList<>();
        for (Proposal proposal : proposalLocalService.getProposalsInContest(contest.getContestPK())) {
            proposalIds.add(proposal.getProposalId());
        }

        if (proposalIds.isEmpty()) {
            return 0L;
        }

        Long contestPhaseId = contestPhase.getContestPhasePK();
        DynamicQuery votesQuery = DynamicQueryFactoryUtil.forClass(ProposalVote.class);
        votesQuery.add(PropertyFactoryUtil.forName("primaryKey.contestPhaseId").eq(contestPhaseId));
        votesQuery.add(RestrictionsFactoryUtil.in("proposalId", proposalIds));
        return dynamicQueryCount(votesQuery);
    }

    @Override
    public List<ContestTeamMember> getTeamMembers(Contest contest) throws SystemException {
        return contestTeamMemberLocalService.findForContest(contest.getContestPK());
    }

    @Override
    public ContestTeamMemberRole getRoleForMember(ContestTeamMember contestTeamMember) throws SystemException, NoSuchModelException {
        return contestTeamMemberRoleLocalService.findForContestTeamMember(contestTeamMember.getRoleId());
    }

    /**
     * <p>Returns true if user is subscribed to a contest, false otherwise</p>
     *
     * @param contestPK id of a contest
     * @param userId    id of a user
     * @return true if user is subscribed to a contest, false otherwise
     */
    @Override
    public boolean isSubscribed(long contestPK, long userId) {
        //return activitySubscriptionLocalService.isSubscribed(userId, Contest.class, contestPK, 0, "");
        return ActivitiesClientUtil.isSubscribedToActivity(userId,ActivityEntryType.CONTEST.getPrimaryTypeId(),contestPK,0,"");
    }

    /**
     * <p>Subscribes user to contest</p>
     *
     * @param contestPK id of a contest
     * @param userId    id of a user
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override
    @Transactional
    public void subscribe(long contestPK, long userId) throws PortalException, SystemException {
        ActivitiesClientUtil.addSubscription(userId, ActivityEntryType.CONTEST, contestPK, "");
    }

    /**
     * <p>Unsubscribes user from contest</p>
     *
     * @param contestPK id of a contest
     * @param userId    id of a user
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override
    @Transactional
    public void unsubscribe(long contestPK, long userId) throws PortalException, SystemException {
        ActivitiesClientUtil.deleteSubscription(userId, ActivityEntryType.CONTEST, contestPK, "");
    }

    @Override
    public List<Long> getModelIds(long contestPK) throws SystemException, PortalException {
        Contest contest = getContest(contestPK);
        List<Long> modelIds = new ArrayList<>();

        if (StringUtils.isNotBlank(contest.getOtherModels())) {
            modelIds.addAll(IdListUtil.getIdsFromString(contest.getOtherModels()));
        }
        if (!modelIds.contains(contest.getDefaultModelId())) {
        	modelIds.add(contest.getDefaultModelId());
        }

        return modelIds;
    }
    
    @Override
    public Map<Long, String> getModelIdsAndNames(long contestPK) throws SystemException, PortalException {
    	List<Long> modelIds = getModelIds(contestPK);

        Map<Long, String> ret = new HashMap<>();
        for (Long modelId: modelIds) {
        	try {
        		Simulation s = RomaClientUtil.repository().getSimulation(modelId);
                ret.put(s.getId(), s.getName());
        	
        	}
        	catch (IOException e) {
        		throw new PortalException(e);
        	}
        }
        return ret;
    }

    @Override
    public Long getDefaultModelId(long contestPK) throws PortalException, SystemException {
        Contest contest = getContest(contestPK);
        return contest.getDefaultModelId();
    }

    private void reindex(Contest contest) {
        Indexer indexer = IndexerRegistryUtil.getIndexer(Contest.class);

        String errorMessage = "Can't reindex contest " + contest.getContestPK();
        try {
            indexer.reindex(contest.getContestPK());
        } catch (SearchException | NullPointerException e) {
            _log.error(errorMessage, e);
        }
    }
    
    @Override
    public List<Contest> getContestsByActivePrivate(boolean contestActive, boolean contestPrivate) throws SystemException {
    	return contestPersistence.findByActivePrivate(contestActive, contestPrivate);
    }

    @Override
    public List<Contest> getContestsByActivePrivateType(boolean contestActive, boolean contestPrivate, long contestTypeId)
            throws SystemException {
        return contestPersistence.findByActivePrivateType(contestActive, contestPrivate, contestTypeId);
    }

    @Override
    public List<Contest> getContestsMatchingOntologyTerms(List<OntologyTerm> ontologyTerms) throws PortalException, SystemException{

        // remove terms that are root elements
        for (Iterator<OntologyTerm> i = ontologyTerms.iterator(); i.hasNext();){
            OntologyTerm o = i.next();
            if (o.getParentId() == 0 && ANY_TERM_IDS.contains(o.getId())) {
                i.remove();
            }
        }

        if (ontologyTerms.isEmpty()) {
            //if no terms are left (i.e. they were all root elements), return all contests
            return getContests(0, Integer.MAX_VALUE);
        }

        Long[][] terms = new Long[ontologyTerms.size()][];
        // get all child elements and add id's to array
        int i = 0;
        for (OntologyTerm ot : ontologyTerms){
            List<OntologyTerm> childTerms = ontologyTermLocalService.getAllDescendantTerms(ot);
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
            contestsMatchingTerms[l] = contestLocalService.dynamicQuery(contestQuery);
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
    @Override
    public List<Contest> getContestsMatchingTier(long contestTierType) throws PortalException, SystemException {
        if (ContestTier.getContestTierByTierType(contestTierType) == ContestTier.NONE) {
            return contestPersistence.findAll();
        }
        return contestPersistence.findByTier(contestTierType);
    }

    @Override
    public List<Contest> getContestsMatchingTierInType(long contestTierType, long contestTypeId) throws PortalException, SystemException {
        if (ContestTier.getContestTierByTierType(contestTierType) == ContestTier.NONE) {
            return contestPersistence.findByContestType(contestTypeId);
        }
        return contestPersistence.findByTierType(contestTierType, contestTypeId);
    }

    /**
     * Returns all contests matching the specified contest tier and ontology terms.
     * Returns all contests in the case of ContestTier.NONE
     * @param contestTierType   The specified contest tier
     * @return                  A list of all Contests matching the specified contest tier
     * @throws PortalException
     * @throws SystemException
     */
    @Override
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
    @Override
    public void transferSupportsToVote(Contest contest, ServiceContext serviceContext) throws SystemException, PortalException {
        ContestPhase lastOrActivePhase = contestLocalService.getActiveOrLastPhase(contestLocalService.getContest(contest.getContestPK()));
        // Vote is only possible in Winner Selection phase
        if (lastOrActivePhase.getContestPhaseType() != ContestPhaseTypeValue.SELECTION_OF_WINNERS.getTypeId() &&
                lastOrActivePhase.getContestPhaseType() != ContestPhaseTypeValue.WINNERS_SELECTION.getTypeId() &&
                lastOrActivePhase.getContestPhaseType() != ContestPhaseTypeValue.SELECTION_OF_WINNERS_NEW.getTypeId()) {
            return;
        }
        
        Set<Long> proposalsUserCanVoteOn = new HashSet<>();
        for (Proposal proposal: proposalLocalService.getProposalsInContestPhase(lastOrActivePhase.getContestPhasePK())) {
        	proposalsUserCanVoteOn.add(proposal.getProposalId());
        }
        Map<User, List<Proposal>> userToSupportsMap = getContestSupportingUser(contest);
        for (User user : userToSupportsMap.keySet()) {
            // Do nothing if the user has already voted
            if (proposalVoteLocalService.hasUserVoted(lastOrActivePhase.getContestPhasePK(), user.getUserId())) {
                continue;
            }

            List<Proposal> proposals = new ArrayList<>();
            if (userToSupportsMap.containsKey(user)) {
            	for (Proposal p: userToSupportsMap.get(user)) {
                    if (proposalsUserCanVoteOn.contains(p.getProposalId())) {
                        proposals.add(p);
                    }
                }
            }
            if (proposals.isEmpty()) {
            	continue;
            }

            /*
            // Directly transfer the support to a vote
            try {
                Member member = MembersClient.getMember(user.getUserId());
                if (proposals.size() == 1) {
                    voteForProposal(user.getUserId(), proposals.get(0).getProposalId(), lastOrActivePhase.getContestPhasePK());
                    try{
                        org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(contest.getContestPK());
                        new ContestVoteNotification(member, contestMicro, proposals.get(0), serviceContext).sendMessage();
                    }catch (ContestNotFoundException ignored){

                    }

                }
                // Send a notification to the user
                else {
                    try {
                        org.xcolab.client.contest.pojo.Contest contestMicro = ContestClient.getContest(contest.getContestPK());
                        new ContestVoteQuestionNotification(member, contestMicro, proposals, serviceContext).sendMessage();
                    }catch (ContestNotFoundException ignored){

                    }
                }
            } catch (MemberNotFoundException e) {
                //ignore, we know it exists
            }
            */

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
     * @throws SystemException
     * @throws PortalException
     */
    @Override
    public String getProposalJudgeReviewCsv(Contest contest, ContestPhase currentPhase, ServiceContext serviceContext) throws SystemException, PortalException {
    /*
        Map<Proposal,List<ProposalReview>> proposalToProposalReviewsMap = new HashMap<>();

        List<Proposal> stillActiveProposals = proposalLocalService.getActiveProposalsInContestPhase(currentPhase.getContestPhasePK());
        Set<ProposalRatingType> occurringRatingTypes = new HashSet<>();
        Set<User> occurringJudges = new HashSet<>();

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
                List<ProposalRating> ratings = proposalRatingLocalService.getJudgeRatingsForProposal(proposal.getProposalId(), judgingPhase.getContestPhasePK());
                Map<ProposalRatingType, List<Long>> ratingsPerType = new HashMap<>();

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

        ProposalReviewCsvExporter csvExporter = new ProposalReviewCsvExporter(proposalToProposalReviewsMap, new ArrayList<>(occurringRatingTypes));

        return csvExporter.getCsvString();*/
                return "";
    }

    /**
     * Returns all Judge Users that have been selected by Fellows for the proposal review or all
     * Contest Judges if fellow screening is disabled
     *
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

    @Override
    public List<User> getAdvisorsForContest(Contest contest) throws SystemException, PortalException {
        Map<MemberRole, List<User>> roleToUserMap = getContestTeamMembersByRole(contest);
        if (Validator.isNotNull(roleToUserMap) && Validator.isNotNull(roleToUserMap.get(MemberRole.ADVISOR))) {
            return roleToUserMap.get(MemberRole.ADVISOR);
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> getJudgesForContest(Contest contest) throws SystemException, PortalException {
        Map<MemberRole, List<User>> roleToUserMap = getContestTeamMembersByRole(contest);
        if (Validator.isNotNull(roleToUserMap) && Validator.isNotNull(roleToUserMap.get(MemberRole.JUDGE))) {
            return roleToUserMap.get(MemberRole.JUDGE);
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> getFellowsForContest(Contest contest) throws SystemException, PortalException {
        Map<MemberRole, List<User>> roleToUserMap = getContestTeamMembersByRole(contest);
        if (Validator.isNotNull(roleToUserMap) && Validator.isNotNull(roleToUserMap.get(MemberRole.FELLOW))) {
            return roleToUserMap.get(MemberRole.FELLOW);
        }
        return Collections.emptyList();
    }

    @Override
    public List<User> getContestManagersForContest(Contest contest) throws SystemException, PortalException {
        Map<MemberRole, List<User>> roleToUserMap = getContestTeamMembersByRole(contest);
        if (Validator.isNotNull(roleToUserMap) && Validator.isNotNull(roleToUserMap.get(MemberRole.CONTEST_MANAGER))) {
            return roleToUserMap.get(MemberRole.CONTEST_MANAGER);
        }
        return Collections.emptyList();
    }

    /**
     * Returns a map object that maps MemberRoles to a list of associated users for the respective MemberRole
     * according to the ContestTeamMember table
     *
     * @param contest           The contest for which the mapping is requested
     * @throws PortalException
     * @throws SystemException
     */
    private Map<MemberRole, List<User>> getContestTeamMembersByRole(Contest contest) throws PortalException, SystemException {
        Map<MemberRole, List<User>> teamRoleToUsersMap = new TreeMap<>();
        for (ContestTeamMember ctm : getTeamMembers(contest)) {
            try {
                ContestTeamMemberRole role = getRoleForMember(ctm);
                MemberRole memberRole = MemberRole.fromRoleId(role.getPrimaryKey());
                List<User> roleUsers = teamRoleToUsersMap.get(memberRole);

                if (roleUsers == null) {
                    roleUsers = new ArrayList<>();
                    teamRoleToUsersMap.put(memberRole, roleUsers);
                }

                roleUsers.add(contestTeamMemberLocalService.getUser(ctm));
            } catch (NoSuchModelException e) {
                throw ReferenceResolutionException
                        .toObject(ContestTeamMemberRole.class, ctm.getRoleId())
                        .fromObject(ContestTeamMember.class, ctm.getId());
            }
        }
        return teamRoleToUsersMap;
    }

    @Override
    public String getContestLinkUrl(long contestId) {
        try {
            return getContestLinkUrl(getContest(contestId));
        } catch (PortalException | SystemException e) {
            return "/contests";
        }
    }

    /**
     * Returns the URL link address for the passed contest
     *
     * @param contest   The contest object
     * @return          Contest URL as String
     */
    @Override
    public String getContestLinkUrl(Contest contest) {
        String link = "/";
        try {
            link += contestTypeLocalService.getContestType(contest).getFriendlyUrlStringContests();
        } catch (SystemException e) {
            link += "contests";
        }
        link += "/%d/%s";
        return String.format(link, contest.getContestYear(), contest.getContestUrlName());
    }

    private Map<User, List<Proposal>> getContestSupportingUser(Contest contest) throws SystemException, PortalException {
        List<Proposal> proposalsInContest = proposalLocalService.getProposalsInContest(contest.getContestPK());

        HashMap<User, List<Proposal>> userToSupportsMap = new HashMap<>();
        // Generate a list of supporting proposals for each user
        for (ProposalSupporter supporter : proposalSupporterLocalService.getProposalSupportersForProposals(proposalsInContest)) {
            User user = userLocalService.getUser(supporter.getUserId());

            List<Proposal> proposals = userToSupportsMap.get(user);
            if (proposals == null) {
                proposals = new ArrayList<>();
                userToSupportsMap.put(user, proposals);
            }

            proposals.add(proposalLocalService.getProposal(supporter.getProposalId()));
        }

        return userToSupportsMap;
    }

    private void voteForProposal(long userId, long proposalId, long contestPhaseId) throws SystemException, PortalException {
        proposalLocalService.addVote(proposalId, contestPhaseId, userId);
    }
    
    @Override
    public boolean hasContestEnded(long contestPK) throws SystemException, PortalException {
    	return hasContestEnded(getContest(contestPK));
    }

    @Override
    public boolean hasContestEnded(Contest contest) throws SystemException, PortalException {
    	ContestPhase activePhase = getActiveOrLastPhase(contest);
        com.ext.portlet.model.ContestPhaseType type = contestPhaseTypeLocalService.getContestPhaseType(activePhase.getContestPhaseType());
        boolean typeIsClosed =
                ContestStatus.COMPLETED.toString().toUpperCase().equals(type.getStatus().toUpperCase()) ||
                ContestStatus.CLOSED.toString().toUpperCase().equals(type.getStatus().toUpperCase()) ||
                ContestStatus.FINISHED.toString().toUpperCase().equals(type.getStatus().toUpperCase());
        //Either, the active or last phase has no end date (which means the contest ended), or the current date is after it's end date.
        return typeIsClosed && (activePhase.getPhaseEndDate() == null || new Date().after(activePhase.getPhaseEndDate()));
    }
    
    
    @Override
    public Proposal getWinnerProposal(long contestPK) throws SystemException, PortalException {
    	Contest contest = getContest(contestPK);
        //only return a winner if the contest is done
    	if (!hasContestEnded(contest)) {
            return null;
        }
    	ContestPhase lastPhase = getActiveOrLastPhase(contest);

        for (Proposal proposal : proposalLocalService.getActiveProposalsInContestPhase(lastPhase.getContestPhasePK())) {
            try {
            	ProposalContestPhaseAttribute pcpa = 
            			proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttribute(
            					proposal.getProposalId(), 
            					lastPhase.getContestPhasePK(), 
            					ProposalContestPhaseAttributeKeys.RIBBON);
            	if (pcpa.getNumericValue() == 2 || pcpa.getNumericValue() == 5 || pcpa.getNumericValue() == 7
                        || pcpa.getNumericValue() == 8 || pcpa.getNumericValue() == 9) {
            		// FIXME - this has to be improved to make sure that we select proper ribbon
            		return proposal;
            	}
            }
            catch (NoSuchProposalContestPhaseAttributeException ignored) { }
            
        }
        return null;
    }

    @Override
    public Integer getPointsAccessibleForActivePhaseOfContest(Contest contest) throws SystemException, PortalException {
        //check if the phase, the contest is currently in, allows for editing
        ContestPhase activePhase = contestPhaseLocalService.getActivePhaseForContest(contest);
        if (activePhase != null) {
            com.ext.portlet.model.ContestPhaseType cpType = contestPhaseTypeLocalService.getContestPhaseType(activePhase.getContestPhaseType());
            if (cpType != null) {
                return cpType.getPointsAccessible();
            }
        }
        return null;
    }

    // Proposal impact implementation

    @Override
    public ImpactTemplateSeries getContestImpactTemplateSeries(Contest contest) throws SystemException, PortalException {
        PlanTemplate planTemplate = planTemplateLocalService.getPlanTemplate(contest.getPlanTemplateId());
        return impactTemplateSeriesPersistence.findByPrimaryKey(planTemplate.getImpactSeriesTemplateId());
    }

    @Override
    public List<ImpactIteration> getContestImpactIterations(Contest contest) throws PortalException, SystemException {
        ImpactTemplateSeries impactSeries = getContestImpactTemplateSeries(contest);
        return impactIterationPersistence.findByIterationId(impactSeries.getIterationId());
    }

    @Override
    public ImpactTemplateFocusAreaList getContestImpactFocusAreaList(Contest contest) throws SystemException, PortalException {
        PlanTemplate planTemplate = planTemplateLocalService.getPlanTemplate(contest.getPlanTemplateId());
        return impactTemplateFocusAreaListLocalService.getImpactTemplateFocusAreaList(planTemplate.getFocusAreaListTemplateId());
    }

    @Override
    public List<ImpactTemplateMaxFocusArea> getContestImpactFocusAreas(Contest contest) throws PortalException, SystemException {
        ImpactTemplateFocusAreaList focusAreaList = getContestImpactFocusAreaList(contest);
        return impactTemplateMaxFocusAreaPersistence.findByFocusAreaListId(focusAreaList.getFocusAreaListId());
    }

    @Override
    public List<Contest> getContestsByContestType(Long contestTypeId) throws SystemException {
        return contestPersistence.findByContestType(contestTypeId);
    }

    @Override
    public List<Contest> getContestsByContestTypeIds(List<Long> contestTypeIds) throws SystemException {
        List<Contest> contests = new ArrayList<>();
        for (long contestTypeId : contestTypeIds) {
            contests.addAll(getContestsByContestType(contestTypeId));
        }
        return contests;
    }

    @Override
    public int countContestsByContestType(Long contestTypeId) throws SystemException {
        return contestPersistence.countByContestType(contestTypeId);
    }

    @Override
    public List<Contest> getContestsByTierLevelAndOntologyTermIds(Long contestTier, List<Long> focusAreaOntologyTermIds)
            throws SystemException {
        DynamicQuery queryContestsByTierLevelAndOntologyTermIds =
            DynamicQueryFactoryUtil.forClass(Contest.class, PortletClassLoaderUtil.getClassLoader())
                    .add(PropertyFactoryUtil.forName("contestTier").eq(contestTier))
                    .add(PropertyFactoryUtil.forName("focusAreaId").in(focusAreaOntologyTermIds));

        return contestPersistence.findWithDynamicQuery(queryContestsByTierLevelAndOntologyTermIds);
    }

    @Override
    public List<Contest> getContestsByContestScheduleId(Long contestScheduleId) throws SystemException {

        DynamicQuery queryContestsByTierLevelAndOntologyTermIds =
                DynamicQueryFactoryUtil.forClass(Contest.class, PortletClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("contestScheduleId").eq(contestScheduleId))
                        .add(PropertyFactoryUtil.forName("ContestPK").ne(0L));

        return contestPersistence.findWithDynamicQuery(queryContestsByTierLevelAndOntologyTermIds);
    }

    @Override
    public List<Contest> getContestsByPlanTemplateId(Long planTemplateId) throws SystemException {

        DynamicQuery queryContestsByPlanTemplateId =
                DynamicQueryFactoryUtil.forClass(Contest.class, PortletClassLoaderUtil.getClassLoader())
                        .add(PropertyFactoryUtil.forName("planTemplateId").eq(planTemplateId))
                        .add(PropertyFactoryUtil.forName("ContestPK").ne(0L));

        return contestPersistence.findWithDynamicQuery(queryContestsByPlanTemplateId);
    }


    @Override
    public List<Contest> getSubContestsByOntologySpaceId(Contest contest, Long ontologySpaceId)
            throws SystemException, PortalException {
        long focusAreaId = contest.getFocusAreaId();
        long contestTier =  contest.getContestTier();
        long lowerContestTier = contestTier - 1;

        if(lowerContestTier < 1) {
            throw new PortalException("Contest " + contest.getContestPK() + " has no sub-contests!" );
        }

        List<Long> focusAreaOntologyTermIds =
                FocusAreaOntologyTermLocalServiceUtil.getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(focusAreaId, ontologySpaceId);

        return getContestsByTierLevelAndOntologyTermIds(lowerContestTier, focusAreaOntologyTermIds);
    }

    /**
     * This method adds a year suffix to already completed contests. It iterates over all completed contests
     * and automatically adds the year of the completed contest phase as a suffix to the Contest's ShortName, if necessary
     */
    @Override
    public void addContestYearSuffixToCompletedContests() throws SystemException, PortalException {
        for (Contest priorContest : getContestsByActivePrivate(true, false)) {
            addContestYearSuffixToContest(priorContest, true);
        }
    }

    /**
     * Returns a list of contests that have Proposal points enabled (column 'points' and 'defaultParentPointType' set),
     * while optionally ignoring contests which are marked as inactive
     *
     * @param ignoreInactiveContests    A flag indicating whether the method should include inactive contests or not
     * @return                          A list of contests mathing the criteria
     */
    @Override
    public List<Contest> getPointsEnabledContests(boolean ignoreInactiveContests) throws SystemException {
        List<Contest> returnList = new ArrayList<>();
        for (Contest contest : contestLocalService.getContests(QueryUtil.ALL_POS, QueryUtil.ALL_POS)) {
            if (contest.getPoints() > 0 && contest.getDefaultParentPointType() > 0) {
                if (!ignoreInactiveContests || contest.getContestActive()) {
                    returnList.add(contest);
                }
            }
        }

        return returnList;
    }

    /**
     * This method adds a year suffix to the passed contest. By passing the flag checkForCompleted, the method will only
     * add the suffix for contests which latest contest phase is of type COMPLETED
     * It automatically adds the year of the completed contest phase as a suffix to the Contest's ShortName, if necessary
     *
     * @param contest               The contest that should get a year suffix
     * @param checkForCompleted     Indicates whether contests that are not in a COMPLETED active contest phase should be ignored
     */
    @Override
    public void addContestYearSuffixToContest(Contest contest, boolean checkForCompleted) {
        try {
            ContestPhase latestPhase = getActiveOrLastPhase(contest);
            String[] contestNameParts = contest.getContestShortName().split(" ");
            _log.info("addContestYearSuffixToContest: " + contest.getContestPK());
            // Is in completed phase and inactive? - or is flag set to false?
            boolean isCompleted = (ArrayUtil.isNotEmpty(contestNameParts) &&
                    (latestPhase.getContestPhaseType() == ContestPhaseTypeValue.COMPLETED.getTypeId() ||
                            latestPhase.getContestPhaseType() == ContestPhaseTypeValue.WINNERS_AWARDED.getTypeId()));
            if (!checkForCompleted || isCompleted) {
                _log.info("Contest phase type : " + latestPhase.getContestPhaseType());

                String lastNamePart = contestNameParts[contestNameParts.length - 1];
                Integer phaseEndYear = getYearFromDate(latestPhase.getPhaseStartDate());

                String newContestShortName;
                try {
                    final int suffixYear = Integer.parseInt(lastNamePart);

                    // Same year suffix detected - skip contest
                    if (suffixYear == phaseEndYear) {
                        return;
                    }

                    // Unlikely event that a suffix has been created but the phase end date has changed - adapt to new suffix
                    contestNameParts[contestNameParts.length - 1] = phaseEndYear.toString();
                    newContestShortName = StringUtils.join(contestNameParts, " ");
                } catch (NumberFormatException e) {
                    // No year suffix detected - add new one
                    newContestShortName = contest.getContestShortName() + " " + phaseEndYear;
                }
                contest.setContestShortName(newContestShortName);
                contest.persist();
            }
        } catch (SystemException | PortalException e) {
            _log.error("Could not get latest contest phase of contest '" + contest.getContestPK() + "' to add year suffix or persist contest", e);
        }
    }

    private Integer getYearFromDate(Date date) {
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone("US/Eastern"));
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    @Override
    public String generateContestUrlName(Contest contest) {
        String contestUrlName = contest.getContestShortName().toLowerCase();
        return contestUrlName.replaceAll(" ", "-").replaceAll("[^a-z0-9-]", "");
    }
}
