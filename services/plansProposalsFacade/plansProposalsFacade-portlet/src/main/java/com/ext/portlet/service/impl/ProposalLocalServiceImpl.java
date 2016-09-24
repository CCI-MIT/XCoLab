package com.ext.portlet.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.NoSuchProposalException;
import com.ext.portlet.NoSuchProposalSupporterException;
import com.ext.portlet.NoSuchProposalVoteException;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalReference;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.FocusAreaLocalServiceUtil;
import com.ext.portlet.service.PlanSectionDefinitionLocalServiceUtil;
import com.ext.portlet.service.ProposalAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalContestPhaseAttributeLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.ProposalReferenceLocalServiceUtil;
import com.ext.portlet.service.base.ProposalLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
import com.ext.portlet.service.persistence.ProposalSupporterPK;
import com.ext.portlet.service.persistence.ProposalVersionPK;
import com.ext.portlet.service.persistence.ProposalVotePK;
import com.liferay.portal.NoSuchUserException;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.OrderFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;

import org.xcolab.client.activities.ActivitiesClient;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.client.contest.ContestClient;

import org.xcolab.enums.MembershipRequestStatus;
import org.xcolab.mail.EmailToAdminDispatcher;
import org.xcolab.proposals.events.ProposalAssociatedWithContestPhaseEvent;
import org.xcolab.proposals.events.ProposalMemberAddedEvent;
import org.xcolab.proposals.events.ProposalMemberRemovedEvent;
import org.xcolab.proposals.events.ProposalRemovedVoteEvent;
import org.xcolab.proposals.events.ProposalSupporterAddedEvent;
import org.xcolab.proposals.events.ProposalSupporterRemovedEvent;
import org.xcolab.proposals.events.ProposalVotedOnEvent;
import org.xcolab.services.EventBusService;
import org.xcolab.util.enums.activity.ActivityEntryType;
import org.xcolab.util.enums.contest.ProposalContestPhaseAttributeKeys;
import org.xcolab.util.exceptions.DatabaseAccessException;
import org.xcolab.utils.TemplateReplacementUtil;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.mail.internet.AddressException;
import javax.portlet.PortletRequest;

/**
 * The implementation of the proposal local service.
 * <p/>
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.ext.portlet.service.ProposalLocalService} interface.
 * <p/>
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 *
 * @author janusz
 * @see com.ext.portlet.service.base.ProposalLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalLocalServiceUtil
 */
public class ProposalLocalServiceImpl extends ProposalLocalServiceBaseImpl {

    private static final Log _log = LogFactoryUtil.getLog(ProposalLocalServiceImpl.class);

    private static final long ADMINISTRATOR_USER_ID = 10144L;



    /**
     * Default description of group working on a plan.
     */
    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on <proposal/> %s";

    @BeanReference(type = EventBusService.class)
    private EventBusService eventBus;

    @BeanReference(type = GroupService.class)
    private GroupService groupService;

    @BeanReference(type = RoleLocalService.class)
    private RoleLocalService roleLocalService;

    public ProposalLocalServiceImpl() {
    }

    /**
     * <p>
     * Creates new proposal, initializes it and associates it with contest phase. All related entities are
     * created:
     * </p>
     * <ul>
     * <li>liferay group</li>
     * <li>discussion for proposal comments</li>
     * <li>discussion for judges</li>
     * <li>discussion for advisors</li>
     * <li>discussion for</li>
     * </ul>
     * <p>
     * Creation of all entities is wrapped into a transaction.
     * </p>
     *
     * @param authorId       id of proposal author
     * @param contestPhaseId id of a contestPhase
     * @return created proposal
     * @throws SystemException in case of a Liferay error
     * @throws PortalException in case of a Liferay error
     * @author janusz
     */
    @Override
    @Transactional
    public Proposal create(long authorId, long contestPhaseId) throws SystemException, PortalException {
        long proposalId = counterLocalService.increment(Proposal.class.getName());
        return create(authorId, contestPhaseId, proposalId, true);
    }

    /**
     * <p>
     * Creates new proposal, initializes it and associates it with contest phase. All related entities are
     * created:
     * </p>
     * <ul>
     * <li>liferay group</li>
     * <li>discussion for proposal comments</li>
     * <li>discussion for judges</li>
     * <li>discussion for advisors</li>
     * <li>discussion for</li>
     * </ul>
     * <p>
     * Creation of all entities is wrapped into a transaction.
     * </p>
     *
     * @param authorId       id of proposal author
     * @param contestPhaseId id of a contestPhase
     * @return created proposal
     * @throws SystemException in case of a Liferay error
     * @throws PortalException in case of a Liferay error
     * @author janusz
     */
    @Override
    @Transactional
    public Proposal create(long authorId, long contestPhaseId, long proposalId, boolean publishActivity) throws SystemException, PortalException {

        Proposal proposal = createProposal(proposalId);
        proposal.setVisible(true);
        proposal.setAuthorId(authorId);
        proposal.setCreateDate(new Date());

        ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
        final Contest contest = contestLocalService.fetchContest(contestPhase.getContestPK());
        ContestType contestType = contestTypeLocalService.getContestType(contest);

        // create discussions
        final String proposalEntityName = contestType.getProposalName()+" ";


        final CommentThread mainCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " main discussion",
                authorId, false);

        proposal.setDiscussionId(mainCommentThread.getThreadId());


        final CommentThread resultsCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId +" results discussion",
                authorId, true);

        proposal.setResultsDiscussionId(resultsCommentThread.getThreadId());



        final CommentThread judgeCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId +" judges discussion",
                authorId, true);

        proposal.setJudgeDiscussionId(judgeCommentThread.getThreadId());



        final CommentThread advisorsCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " advisors discussion",
                authorId, true);

        proposal.setAdvisorDiscussionId(advisorsCommentThread.getThreadId());


        final CommentThread fellowsCommentThread = createCommentThreadForProposal(proposalEntityName + proposalId + " fellows discussion",
                authorId, true);

        proposal.setFellowDiscussionId(fellowsCommentThread.getThreadId());

        // create group
        Group group = createGroupAndSetUpPermissions(authorId, proposalId, contest);
        proposal.setGroupId(group.getGroupId());


        addProposal(proposal);

        if (contestPhaseId > 0) {
            // associate proposal with phase
            Proposal2Phase p2p = proposal2PhaseLocalService.createProposal2Phase(new Proposal2PhasePK(proposalId, contestPhaseId));
            p2p.setVersionFrom(proposal.getCurrentVersion());
            p2p.setVersionTo(-1);
            proposal2PhaseLocalService.addProposal2Phase(p2p);
            if (publishActivity) {
                eventBus.post(new ProposalAssociatedWithContestPhaseEvent(proposal,
                        contestPhaseLocalService.getContestPhase(contestPhaseId), UserLocalServiceUtil.getUser(authorId)));


            }
        }

        // Automatically subscribe author to own proposal
        subscribe(proposalId, authorId);

        return proposal;
    }

    private CommentThread createCommentThreadForProposal(String title, Long authorId, boolean isQuiet){
        CommentThread commentThread = new CommentThread();
        commentThread.setAuthorId(authorId);
        commentThread.setCategoryId(null);
        commentThread.setTitle(title);
        commentThread.setIsQuiet(isQuiet);
        commentThread = CommentClient.createThread(commentThread);
        return commentThread;
    }

    @Override
    @Transactional
    public void setVisibility(Long proposalId, Boolean visibility, Long authorId) throws SystemException, PortalException {
        Proposal proposal = ProposalLocalServiceUtil.getProposal(proposalId);
        proposal.setVisible(visibility);
        ProposalLocalServiceUtil.updateProposal(proposal);

        proposalAttributeLocalService.setAttribute(authorId, proposalId, ProposalAttributeKeys.VISIBLE, (visibility) ? 1L : 0L);

    }

    /**
     * <p>Returns a list of all proposal version descriptors.</p>
     *
     * @param proposalId id of a proposal
     * @return list of proposal versions covering entire change history for a proposal
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     * @author janusz
     */
    @Override
    public List<ProposalVersion> getProposalVersions(long proposalId) throws PortalException, SystemException {
        return proposalVersionPersistence.findByProposalId(proposalId);
    }

    /**
     * <p>Returns a concrete proposal version descriptor.</p>
     *
     * @param proposalId id of a proposal
     * @param version    version of a proposal
     * @return proposal version descriptor
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     * @author janusz
     */
    @Override
    public ProposalVersion getProposalVersion(long proposalId, int version) throws PortalException, SystemException {
        return proposalVersionLocalService.getProposalVersion(new ProposalVersionPK(proposalId, version));
    }

    /**
     * <p>Returns a list of proposals associated with given contest phase</p>
     *
     * @param contestPhaseId id of a contest phase
     * @return list of proposals from given contest phase
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public List<Proposal> getProposalsInContestPhase(long contestPhaseId) throws PortalException, SystemException {
        List<Proposal> proposals = new ArrayList<>();

        for (Proposal2Phase proposal2Phase : proposal2PhasePersistence.findByContestPhaseId(contestPhaseId)) {
            Proposal proposal = getProposal(proposal2Phase.getProposalId());

            // set proper version for proposal to reflect max version that proposal has reached at given phase
            if (proposal2Phase.getVersionTo() > 0) {
                proposal.setCurrentVersion(proposal2Phase.getVersionTo());
            }
            proposals.add(proposal);
        }
        return proposals;
    }

    @Override
    public List<Proposal> getProposalsInContestPhase(long contestPhaseId, String sortProperty, boolean sortAscending, int start, int end)
            throws SystemException, NoSuchProposalException {

        List<Proposal> proposals = new ArrayList<>();

        for (Proposal2Phase proposal2Phase : proposal2PhasePersistence.findByContestPhaseId(contestPhaseId)) {
            proposals.add(proposalPersistence.findByPrimaryKey(proposal2Phase.getProposalId()));
        }
        return proposals;
    }

    /**
     * <p>Returns a list of proposals associated with the given contest phase which are both generally visible and visible in the given contest phase</p>
     *
     * @param contestPhaseId id of a contest phase
     * @return list of proposals from given contest phase
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public List<Proposal> getActiveProposalsInContestPhase(long contestPhaseId)
            throws PortalException, SystemException {

        final DynamicQuery phaseProposals = DynamicQueryFactoryUtil.forClass(Proposal2Phase.class, "phaseProposalIds");
        phaseProposals.setProjection(ProjectionFactoryUtil.property("phaseProposalIds.primaryKey.proposalId"));
        phaseProposals.add(PropertyFactoryUtil.forName("phaseProposalIds.primaryKey.contestPhaseId").eq(contestPhaseId));

        final DynamicQuery phaseInvisibleProposals = DynamicQueryFactoryUtil.forClass(ProposalContestPhaseAttribute.class, "proposalContestPhaseAttributes");
        phaseInvisibleProposals.setProjection(ProjectionFactoryUtil.property("proposalContestPhaseAttributes.proposalId"));
        phaseInvisibleProposals.add(PropertyFactoryUtil.forName("contestPhaseId").eq(contestPhaseId));
        phaseInvisibleProposals.add(PropertyFactoryUtil.forName("proposalContestPhaseAttributes.name").eq(ProposalContestPhaseAttributeKeys.VISIBLE));
        phaseInvisibleProposals.add(PropertyFactoryUtil.forName("proposalContestPhaseAttributes.numericValue").eq(0L));

        final DynamicQuery proposalsInPhaseNotDeleted = DynamicQueryFactoryUtil.forClass(Proposal.class, "proposal");
        proposalsInPhaseNotDeleted.add(PropertyFactoryUtil.forName("proposal.proposalId").in(phaseProposals))
                .add(PropertyFactoryUtil.forName("proposal.visible").eq(true))
                .add(PropertyFactoryUtil.forName("proposal.proposalId").notIn(phaseInvisibleProposals));

        return dynamicQuery(proposalsInPhaseNotDeleted);
    }

    /**
     * <p>Returns a list of proposals associated with given contest</p>
     *
     * @param contestId id of a contest phase
     * @return list of proposals from given contest
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public List<Proposal> getProposalsInContest(long contestId) throws PortalException, SystemException {
        List<Proposal> proposals = new ArrayList<>();

        ContestPhase lastOrActivePhase = contestLocalService.getActiveOrLastPhase(contestLocalService.getContest(contestId));
        if (lastOrActivePhase == null) {
            return proposals;
        }

        for (Proposal2Phase proposal2Phase : proposal2PhasePersistence.findByContestPhaseId(lastOrActivePhase.getContestPhasePK())) {
            Proposal proposal = getProposal(proposal2Phase.getProposalId());

            // set proper version for proposal to reflect max version that proposal has reached at given phase
            if (proposal2Phase.getVersionTo() > 0) {
                proposal.setCurrentVersion(proposal2Phase.getVersionTo());
            }
            proposals.add(proposal);
        }
        return proposals;
    }

    /**
     * Retrieves all proposals for which a user is either the author or member of the author group (proposals to which a user has contributed)
     *
     * @param userId The userId of the user
     * @return A list of proposals the user has contributed to
     * @throws SystemException
     */
    @Override
    public List<Proposal> getUserProposals(long userId) throws SystemException, PortalException {
        // Get all groups the user is in
        List<Long> groupIds = new ArrayList<>();
        User user = userLocalService.getUser(userId);
        List<Group> groups = user.getGroups();

        for (Group group : groups) {
            groupIds.add(group.getGroupId());
        }

        Criterion criterion = RestrictionsFactoryUtil.eq("authorId", userId);
        criterion = RestrictionsFactoryUtil.or(criterion, RestrictionsFactoryUtil.in("groupId", groupIds));

        final String ENTITY_CLASS_LOADER_CONTEXT = "plansProposalsFacade-portlet";
        final DynamicQuery query = DynamicQueryFactoryUtil.forClass(Proposal.class, (ClassLoader) PortletBeanLocatorUtil.locate(
                ENTITY_CLASS_LOADER_CONTEXT, "portletClassLoader"))
                .add(criterion)
                .add(PropertyFactoryUtil.forName("visible").eq(true))
                .addOrder(OrderFactoryUtil.desc("createDate"));
        //make an editable copy so we can remove proposals
        List<Proposal> proposals = new ArrayList<>(proposalLocalService.dynamicQuery(query));

        for (Iterator<Proposal> iterator = proposals.iterator(); iterator.hasNext(); ) {
            Proposal proposal = iterator.next();
            if (isDeleted(proposal)) {
                iterator.remove();
            }
        }

        return proposals;
    }

    /**
     * <p>Returns count of proposals associated with given contest phase</p>
     *
     * @param contestPhaseId id of a contest phase
     * @return count of proposals from given contest phase
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public long countProposalsInContestPhase(long contestPhaseId) throws PortalException, SystemException {

        List<Proposal> activeProposals = getActiveProposalsInContestPhase(contestPhaseId);
        return activeProposals.size();
    }

    /**
     * <p>Returns list of proposal team members</p>
     *
     * @param proposalId proposal id
     * @return list of proposal team members
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public List<User> getMembers(long proposalId) throws SystemException, PortalException {
        Proposal proposal = ProposalLocalServiceUtil.getProposal(proposalId);

        return UserLocalServiceUtil.getGroupUsers(proposal.getGroupId());
    }

    /**
     * <p>Returns list of proposal supporters</p>
     *
     * @param proposalId proposal id
     * @return list of proposal supporters
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public List<User> getSupporters(long proposalId) throws SystemException, PortalException {
        List<User> ret = new ArrayList<>();
        for (ProposalSupporter supporter : proposalSupporterPersistence.findByProposalId(proposalId)) {
            try {
                ret.add(UserLocalServiceUtil.getUser(supporter.getUserId()));
            } catch(NoSuchUserException e){
                _log.warn("Could not add proposal supporter", e);
            }
        }
        return ret;
    }

    /**
     * <p>Returns number of proposal supporters</p>
     *
     * @param proposalId proposal id
     * @return number of proposal supporters
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public int getSupportersCount(long proposalId) throws SystemException, PortalException {
        return proposalSupporterPersistence.countByProposalId(proposalId);
    }

    /**
     * <p>Returns true if user is a proposal supporter, false otherwise.</p>
     *
     * @param proposalId proposal id
     * @return true if user is a proposal supporter, false otherwise
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public boolean isSupporter(long proposalId, long userId) throws SystemException, PortalException {
        try {
            proposalSupporterPersistence.findByPrimaryKey(new ProposalSupporterPK(proposalId, userId));
            return true;
        } catch (NoSuchProposalSupporterException e) {
            return false;
        }
    }

    /**
     * <p>Adds supporter to a proposal</p>
     *
     * @param proposalId id of a proposal
     * @param userId     id of a supported to be added
     * @throws SystemException in case of an LR error
     * @throws PortalException
     */
    @Override
    @Transactional
    public void addSupporter(long proposalId, long userId) throws SystemException, PortalException {
        addSupporter(proposalId, userId, true);
    }

    /**
     * <p>Adds supporter to a proposal</p>
     *
     * @param proposalId id of a proposal
     * @param userId     id of a supported to be added
     * @throws SystemException in case of an LR error
     * @throws PortalException
     */
    @Override
    @Transactional
    public void addSupporter(long proposalId, long userId, boolean publishActivity) throws SystemException, PortalException {
        ProposalSupporter supporter =
                proposalSupporterLocalService.createProposalSupporter(new ProposalSupporterPK(proposalId, userId));

        supporter.setCreateDate(new Date());
        proposalSupporterLocalService.addProposalSupporter(supporter);

        if (publishActivity) {
            eventBus.post(new ProposalSupporterAddedEvent(getProposal(proposalId), userLocalService.getUser(userId)));

           /* ActivityEntryHelper.createActivityEntry(userId,proposalId,null,
                    new ProposalSupporterAddedActivityEntry());*/
        }
    }

    /**
     * <p>Retracts support from a proposal</p>
     *
     * @param proposalId id of a proposal
     * @param userId     id of a supported to be removed
     * @throws SystemException in case of an LR error
     * @throws PortalException
     */
    @Override
    @Transactional
    public void removeSupporter(long proposalId, long userId) throws SystemException, PortalException {
        ProposalSupporter supporter =
                proposalSupporterLocalService.createProposalSupporter(new ProposalSupporterPK(proposalId, userId));

        proposalSupporterLocalService.deleteProposalSupporter(supporter);
        eventBus.post(new ProposalSupporterRemovedEvent(getProposal(proposalId), userLocalService.getUser(userId)));

        /*ActivityEntryHelper.createActivityEntry(userId,proposalId,null,
                 new ProposalSupporterRemovedActivityEntry());*/
    }

    /**
     * <p>Returns list of users that have voted for a proposal in given contest phase</p>
     *
     * @param proposalId     proposal id
     * @param contestPhaseId contest phase id
     * @return list of proposal voters
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public List<User> getVoters(long proposalId, long contestPhaseId) throws SystemException, PortalException {
        List<User> ret = new ArrayList<>();
        for (ProposalVote proposalVote : proposalVotePersistence.findByProposalIdContestPhaseId(proposalId, contestPhaseId)) {
            ret.add(UserLocalServiceUtil.getUser(proposalVote.getUserId()));
        }
        return ret;
    }

    /**
     * <p>Return number of users that have voted for a proposal in given contest phase</p>
     *
     * @param proposalId     proposal id
     * @param contestPhaseId contest phase id
     * @return number of votes
     * @throws SystemException in case of an LR error
     */
    @Override
    public long getVotesCount(long proposalId, long contestPhaseId) throws SystemException {
        return proposalVotePersistence.countByProposalIdContestPhaseId(proposalId, contestPhaseId);
    }

    /**
     * <p>Adds a user vote to a proposal in context of given contest phase. If user has already voted
     * for different proposal in this phase, then that vote is removed first. User has only one vote
     * in one contestPhase.</p>
     *
     * @param proposalId     id of a proposal
     * @param contestPhaseId id of a contest phase
     * @param userId         id of an user
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    @Transactional
    public void addVote(long proposalId, long contestPhaseId, long userId)
            throws SystemException, PortalException {
        addVote(proposalId, contestPhaseId, userId, true);
    }

    /**
     * <p>Adds a user vote to a proposal in context of given contest phase. If user has already voted
     * for different proposal in this phase, then that vote is removed first. User has only one vote
     * in one contestPhase.</p>
     *
     * @param proposalId     id of a proposal
     * @param contestPhaseId id of a contest phase
     * @param userId         id of an user
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    @Transactional
    public void addVote(long proposalId, long contestPhaseId, long userId, boolean publishActivity)
            throws SystemException, PortalException {

        // retract any vote that user has given to any proposal in context of provided phase
        boolean voted = hasUserVoted(proposalId, contestPhaseId, userId);
        if (voted) {
            removeVote(contestPhaseId, userId);
        }

        // add vote to a proposal
        ProposalVote vote = proposalVoteLocalService.createProposalVote(new ProposalVotePK(contestPhaseId, userId));
        vote.setCreateDate(new Date());
        vote.setProposalId(proposalId);
        vote.setIsValid(true);

        proposalVoteLocalService.addProposalVote(vote);
        if (publishActivity) {
            eventBus.post(new ProposalVotedOnEvent(getProposal(proposalId), userLocalService.getUser(userId), voted));

            if(!voted) {
                /*ActivityEntryHelper.createActivityEntry(userId, proposalId, null,
                         ActivityProvidersType.ProposalVoteActivityEntry);*/
            }else{
                /*ActivityEntryHelper.createActivityEntry(userId, proposalId, null,
                        ActivityProvidersType.ProposalVoteSwitchActivityEntry);*/
            }
        }
    }

    /**
     * <p>Retracts user vote in context of a contest phase.</p>
     *
     * @param contestPhaseId id of a contest phase
     * @param userId         id of an user
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    @Transactional
    public void removeVote(long contestPhaseId, long userId) throws SystemException, PortalException {
        try {
            ProposalVote proposalVote = proposalVoteLocalService.findByProposalIdContestPhaseIdUserId(contestPhaseId, userId);
            proposalVoteLocalService.deleteProposalVote(proposalVote);
            eventBus.post(new ProposalRemovedVoteEvent(getProposal(proposalVote.getProposalId()), userLocalService.getUser(userId)));

            /*ActivityEntryHelper.createActivityEntry(userId,proposalVote.getProposalId(),null,
                    new ProposalVoteRetractActivityEntry());*/

        } catch (NoSuchProposalVoteException ignored) { }
    }

    /**
     * <p>Returns number of comments in discussion associated with this proposal</p>
     *
     * @param proposalId proposal id
     * @return number of comments
     */
    @Override
    public long getCommentsCount(long proposalId) {
        try {
            Proposal proposal = getProposal(proposalId);
            final long discussionId = proposal.getDiscussionId();
            if (discussionId > 0) {
                return CommentClient.countComments(discussionId);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new IllegalArgumentException("Proposal " + proposalId + " does not exist");
        }
        return 0;
    }

    /**
     * <p>Returns number of fellow review comments in discussion associated with this proposal</p>
     *
     * @param proposalId proposal id
     * @return number of comments
     */
    @Override
    public long getFellowReviewCommentsCount(long proposalId) {
        try {
            Proposal proposal = getProposal(proposalId);
            final long fellowDiscussionId = proposal.getFellowDiscussionId();
            if (fellowDiscussionId > 0) {
                return CommentClient.countComments(fellowDiscussionId);
            }
        } catch (SystemException e) {
            throw new DatabaseAccessException(e);
        } catch (PortalException e) {
            throw new IllegalArgumentException("Proposal " + proposalId + " does not exist");
        }
        return 0;
    }

    /**
     * <p>Tells if user is a member of a proposal team</p>
     *
     * @param proposalId id of a proposal
     * @param userId     id of an user
     * @return true if user is a member of given proposal team, false otherwise
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public boolean isUserAMember(long proposalId, long userId) throws SystemException, PortalException {
        Proposal proposal = getProposal(proposalId);
        return GroupLocalServiceUtil.hasUserGroup(userId, proposal.getGroupId());
    }

    /**
     * <p>Returns true if proposal is open (so it can be edited by any user).</p>
     *
     * @param proposalId id of proposal
     * @return true if plan is open, false otherwise
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Override
    public boolean isOpen(long proposalId) throws PortalException, SystemException {
        try {
            ProposalAttribute attribute = proposalAttributeLocalService.getAttribute(proposalId, ProposalAttributeKeys.OPEN, 0);
            return attribute.getNumericValue() > 0;

        } catch (NoSuchProposalAttributeException e) {
            // ignore
        }
        return false;
    }

    /**
     * <p>Returns all team membership requests for a proposal.</p>
     *
     * @param proposalId proposal id
     * @return list of membership requests
     * @throws SystemException in case of LR error
     * @throws PortalException in case of LR error
     */
    @Override
    public List<MembershipRequest> getMembershipRequests(long proposalId) throws SystemException, PortalException {
        Proposal proposal = getProposal(proposalId);
        List<MembershipRequest>  invited = MembershipRequestLocalServiceUtil.search(proposal.getGroupId(),
                MembershipRequestStatus.STATUS_PENDING_INVITED, 0, Integer.MAX_VALUE);
        List<MembershipRequest>  requested = MembershipRequestLocalServiceUtil.search(proposal.getGroupId(),
                MembershipRequestStatus.STATUS_PENDING_REQUESTED, 0, Integer.MAX_VALUE);
        List<MembershipRequest>  olderRequests = MembershipRequestLocalServiceUtil.search(proposal.getGroupId(),
                MembershipRequestConstants.STATUS_PENDING, 0, Integer.MAX_VALUE);
        List<MembershipRequest> combined = new ArrayList<>();
        if(invited!=null&&invited.size()> 0){combined.addAll(invited);}
        if(requested!=null&&requested.size()> 0){combined.addAll(requested);}
        if(olderRequests!=null&&olderRequests.size()> 0){combined.addAll(olderRequests);}

        return combined;
    }

    /**
     * <p>Sends a request to join proposal team</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @param comment    optional comment
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override
    public MembershipRequest addMembershipRequest(long proposalId, long userId, String comment) throws PortalException, SystemException {
        Proposal proposal = getProposal(proposalId);

        return MembershipRequestLocalServiceUtil.addMembershipRequest(userId, proposal.getGroupId(), comment == null? StringPool.BLANK : comment, null);
    }

    /**
     * <p>Sends a request to join proposal team</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @param comment    optional comment
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override
    public MembershipRequest addRequestedMembershipRequest(long proposalId, long userId, String comment) throws PortalException, SystemException {
        Proposal proposal = getProposal(proposalId);
        MembershipRequest membershipRequest = addMembershipRequest(proposalId,userId,comment);
        MembershipRequestLocalServiceUtil.updateStatus(0l,membershipRequest.getMembershipRequestId(),null,
                MembershipRequestStatus.STATUS_PENDING_REQUESTED,false,null);
        return membershipRequest;
    }

    /**
     * <p>Sends a request to join proposal team</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @param comment    optional comment
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override
    public MembershipRequest addInvitedMembershipRequest(long proposalId, long userId, String comment) throws PortalException, SystemException {
        Proposal proposal = getProposal(proposalId);
        MembershipRequest membershipRequest = addMembershipRequest(proposalId,userId,comment);
        MembershipRequestLocalServiceUtil.updateStatus(0l,membershipRequest.getMembershipRequestId(),null,
                MembershipRequestStatus.STATUS_PENDING_INVITED,false,null);
        return membershipRequest;
    }

    /**
     * <p>Remove a user from a proposal team</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override
    public void removeUserFromTeam(long proposalId, long userId) throws PortalException, SystemException {
        Proposal proposal = getProposal(proposalId);
        if (userId == proposal.getAuthorId()) {
            final String errorMessage = String.format("Cannot delete user %d from proposal %d: userId == proposal.getAuthorId()", userId, proposalId);
            try {
                throw new PortalException(errorMessage);
            } catch(PortalException e) {
                //TODO: remove debug email
                StringBuilder stringBuilder = new StringBuilder();
                User deletedUser = UserLocalServiceUtil.fetchUser(userId);
                stringBuilder.append(String.format("Deleted member: %s, %d <br/>", deletedUser.getScreenName(), deletedUser.getUserId()));
                stringBuilder.append(String.format("Deleted from group: %d <br/>", proposal.getGroupId()));
                stringBuilder.append(String.format("Proposal: %d <br/>",  proposalId));
                stringBuilder.append("<br/>Stack trace: <br/>");
                stringBuilder.append(ExceptionUtils.getStackTrace(e));
                new EmailToAdminDispatcher(errorMessage, stringBuilder.toString()).sendMessage();
                throw e;
            }
        }
        GroupLocalServiceUtil.unsetUserGroups(userId, new long[]{proposal.getGroupId()});

        eventBus.post(new ProposalMemberRemovedEvent(proposal, userLocalService.getUser(userId)));

        /*
        ActivityEntryHelper.createActivityEntry(userId,proposalId,null,
                new ProposalMemberRemovedActivityEntry());*/
    }

    /**
     * <p>Denies user as a member of proposal team</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override
    public void dennyMembershipRequest(long proposalId, long userId, long membershipRequestId, String reply, long updateAuthorId)
            throws PortalException, SystemException {
        if (hasUserRequestedMembership(proposalId, userId)) {
            MembershipRequestLocalServiceUtil.updateStatus(userId, membershipRequestId, reply,
                    MembershipRequestConstants.STATUS_DENIED, false, null);
        }
    }

    /**
     * <p>Approves user as a member of proposal team</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override

    public void approveMembershipRequest(long proposalId, Long userId, MembershipRequest request, String reply, Long updateAuthorId)
            throws PortalException, SystemException {

        if (hasUserRequestedMembership(proposalId, userId)) {
            MembershipRequestLocalServiceUtil.updateStatus(userId, request.getMembershipRequestId(), reply,
                    MembershipRequestConstants.STATUS_APPROVED, true, null);
            eventBus.post(new ProposalMemberAddedEvent(getProposal(proposalId), userLocalService.getUser(userId)));

            /*ActivityEntryHelper.createActivityEntry(userId,proposalId,null,
                    new ProposalMemberAddedActivityEntry());
                    */

            if (!isSubscribed(proposalId, userId)) {
                subscribe(proposalId, userId);
            }
        }
    }

    /**
     * <p>Tells if user has requested membership of given plan</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @return true if user has requested membership, false otherwise
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override
    public boolean hasUserRequestedMembership(long proposalId, long userId) throws PortalException, SystemException {
        Proposal proposal = ProposalLocalServiceUtil.getProposal(proposalId);
        boolean hasRequested = !MembershipRequestLocalServiceUtil.getMembershipRequests(userId, proposal.getGroupId(),
                MembershipRequestStatus.STATUS_PENDING_REQUESTED).isEmpty();
        boolean hasBeenInvited = !MembershipRequestLocalServiceUtil.getMembershipRequests(userId, proposal.getGroupId(),
                MembershipRequestStatus.STATUS_PENDING_INVITED).isEmpty();
        return hasRequested||hasBeenInvited;

    }

    /**
     * <p>Adds user to a proposal team if proposal is open and user is not a member already</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    @Override
    public void joinIfNotAMemberAndProposalIsOpen(long proposalId, long userId) throws PortalException, SystemException {
        Proposal proposal = ProposalLocalServiceUtil.getProposal(proposalId);
        if (isOpen(proposalId) && !isUserAMember(proposalId, userId)) {
            GroupLocalServiceUtil.addUserGroups(userId, new long[]{proposal.getGroupId()});
        }
    }

    /**
     * <p>Returns true if user is subscribed to given proposal</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @return true if user has subscribed to a proposal, false otherwise
     */
    @Override
    public boolean isSubscribed(long proposalId, long userId) {
        return ActivitiesClient.isSubscribedToActivity(userId,
                ActivityEntryType.PROPOSAL.getPrimaryTypeId(), proposalId, 0, "");
    }

    /**
     * <p>Subscribes user to a proposal</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     */
    @Override
    public void subscribe(long proposalId, long userId) {
        subscribe(proposalId, userId, false);
    }

    /**
     * <p>Subscribes user to a proposal (supports manual and automatic subscriptions).
     * Automatic subscription is created when user is being subscribed indirectly
     * (ie. when new proposal is created in a contest to which user is subscribed). </p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @param automatic  if this is an automatic subscription
     */
    @Override
    public void subscribe(long proposalId, long userId, boolean automatic) {
        ActivitiesClient.addSubscription(userId, ActivityEntryType.PROPOSAL, proposalId, null);
    }

    /**
     * <p>Unsubscribes user from given proposal</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     */
    @Override
    public void unsubscribe(long proposalId, long userId) {
        unsubscribe(proposalId, userId, false);
    }

    /**
     * <p>Unsubscribes user from given proposal (supports removal of automatic subscriptions).
     * If user is unsubscribing manually then subscription is removed without any conditions,
     * but if this is removal of an automatic subscription then a "automaticSubscriptionCounter"
     * is decreased by 1 for this subscription and if it reaches 0 then subscription is removed. </p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @param automatic  if this is an automatic subscription
     */
    @Override
    public void unsubscribe(long proposalId, long userId, boolean automatic) {
        ActivitiesClient.deleteSubscription(userId, ActivityEntryType.PROPOSAL, proposalId, null);
    }

    /**
     * <p>Returns true if user has voted for given proposal in context of a contest phase</p>
     *
     * @param proposalId     proposal id
     * @param contestPhaseId contest phase id
     * @param userId         user id
     * @return true if user has voted for proposal in context of a contest phase
     * @throws SystemException
     */
    @Override
    public boolean hasUserVoted(long proposalId, long contestPhaseId, long userId) throws SystemException {
        try {
            ProposalVote proposalVote = proposalVotePersistence.findByPrimaryKey(new ProposalVotePK(contestPhaseId, userId));
            return proposalVote.getProposalId() == proposalId;
        } catch (NoSuchProposalVoteException e) {
            return false;
        }
    }

    /**
     * Returns number of proposals that user supports
     *
     * @throws SystemException
     */
    @Override
    public int getUserSupportedProposalsCount(long userId) throws SystemException {
        return proposalSupporterPersistence.countByUserId(userId);
    }

    /**
     * Returns number of proposals that user has given his vote to
     *
     * @throws SystemException
     */
    @Override
    public int getUserVotedProposalsCount(long userId) throws SystemException {
        return proposalVotePersistence.countByUserId(userId);
    }

    @Override
    public List<Proposal> getModifiedAfter(Date date) throws SystemException {
        return proposalPersistence.findByModifiedAfter(date);
    }

    /**
     * Sends out the judges' review about the proposal's advance decision as a CoLab message notification to all proposal
     * contributers
     * @param proposal      The proposal for which the notification should be sent
     * @param contestPhase  The contestPhase in which the proposal is in
     * @param request       A PortletRequest object to extract the Portal's base URL (may be null - choose default portal URL in that case)
     */
    @Override
    public void contestPhasePromotionEmailNotifyProposalContributors(Proposal proposal, ContestPhase contestPhase, PortletRequest request)
            throws PortalException, SystemException, AddressException, MailEngineException, UnsupportedEncodingException {

        String subject = "Judging Results on your Proposal " + proposalAttributeLocalService.getAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();

        /*ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        String messageBody = reviewContentHelper.getPromotionComment(true);
        if (Validator.isNotNull(messageBody)) {
            MessagingClient
                    .sendMessage(subject, messageBody, ADMINISTRATOR_USER_ID, ADMINISTRATOR_USER_ID, getMemberUserIds(proposal));
        }*/
    }

    private List<Long> getMemberUserIds(Proposal proposal) throws PortalException, SystemException {
        List<Long> recipientIds = new ArrayList<>();

        for (User contributor : getMembers(proposal.getProposalId())) {
            recipientIds.add(contributor.getUserId());
        }

        return recipientIds;
    }

    /**
     * <p>Creates a Liferay group for proposal and sets up proper permissions on it.</p>
     *
     * @param authorId   id of a proposal author
     * @param proposalId id of a proposal
     * @param contest
     * @return newly created group
     * @throws PortalException in case on LR error
     * @throws SystemException in case on LR error
     * @author janusz
     */
    @Transactional
    private Group createGroupAndSetUpPermissions(long authorId, long proposalId, Contest contest) throws PortalException,
            SystemException {

        // create new group
        ServiceContext groupServiceContext = new ServiceContext();
        groupServiceContext.setUserId(authorId);
        final ContestType contestType = contestTypeLocalService.getContestType(contest);

        String groupName = contestType.getProposalName() + "_" + proposalId + "_" + new Date().getTime();

        final org.xcolab.client.contest.pojo.ContestType contestTypeForLiferay = ContestClient.getContestType(contestType.getId());
        final String groupDescription = TemplateReplacementUtil.replaceContestTypeStrings(DEFAULT_GROUP_DESCRIPTION, contestTypeForLiferay);

        return groupService.addGroup(StringUtils.substring(groupName, 0, 80),
                String.format(groupDescription, StringUtils.substring(groupName, 0, 80)),
                GroupConstants.TYPE_SITE_RESTRICTED, null, true, true, groupServiceContext);
    }

    /**
     * Returns the URL link address for the passed proposal in the latest contest
     *
     * @param proposalId The proposal id
     * @return Proposal URL as String
     */
    @Override
    public String getProposalLinkUrl(Long proposalId) throws SystemException, PortalException {
        return getProposalLinkUrl(proposal2PhaseLocalService.getCurrentContestForProposal(proposalId), proposalId, 0L);
    }

    /**
     * Returns the URL link address for the passed proposal and contest
     *
     * @param contest  The contest object in which the proposal was written
     * @param proposal The proposal object (must not be null)
     * @return Proposal URL as String
     */
    @Override
    public String getProposalLinkUrl(Contest contest, Proposal proposal) {
        return getProposalLinkUrl(contest, proposal.getProposalId(), 0L);
    }

    /**
     * Returns the URL link address for the passed proposal, contest and contestPhase
     *
     * @param contest       The contest object in which the proposal was written
     * @param proposal      The proposal object
     * @param contestPhase  The associated contestPhase of the proposal
     * @return Proposal URL as String
     */
    @Override
    public String getProposalLinkUrl(Contest contest, Proposal proposal, ContestPhase contestPhase) {
        return getProposalLinkUrl(contest, proposal.getProposalId(), contestPhase.getContestPhasePK());
    }

    @Override
    public String getProposalLinkUrl(Contest contest, long proposalId, long contestPhaseId) {
        String link = "/";
        String friendlyUrlStringProposal;
        try {
            final ContestType contestType = contestTypeLocalService.getContestType(contest);
            link += contestType.getFriendlyUrlStringContests();
            friendlyUrlStringProposal = contestType.getFriendlyUrlStringProposal();
        } catch (SystemException e) {
            link += "contests";
            friendlyUrlStringProposal = "proposal";
        }

        if (contestPhaseId > 0) {
            try {
                long activePhaseId = ContestPhaseLocalServiceUtil.getActivePhaseForContest(contest).getContestPhasePK();
                if (activePhaseId == contestPhaseId) {
                    link += "/%d/%s/c/" + friendlyUrlStringProposal + "/%d";
                    return String.format(link, contest.getContestYear(), contest.getContestUrlName(), proposalId);
                }
            } catch (PortalException | SystemException ignored) { }
            link += "/%d/%s/phase/%d/" + friendlyUrlStringProposal + "/%d";
            return String.format(link, contest.getContestYear(), contest.getContestUrlName(),
                    contestPhaseId, proposalId);
        }

        link += "/%d/%s/c/" + friendlyUrlStringProposal + "/%d";
        return String.format(link, contest.getContestYear(), contest.getContestUrlName(), proposalId);
    }

    /**
     * Returns list of proposals referenced by given proposal that are relevant for the ingtegration contests
     * @param proposalId                        The proposal for which subproposals should be returned
     * @return collection of referenced proposals
     */
    @Override
    public List<Proposal> getContestIntegrationRelevantSubproposals(long proposalId) throws SystemException, PortalException {
        final boolean onlyWithContestIntegrationRelevance = true;
        final boolean includeProposalsInSameContest = false;
        return getSubproposals(proposalId, includeProposalsInSameContest, onlyWithContestIntegrationRelevance);
    }

    /**
     * Returns list of proposals referenced by given proposal
     * @param proposalId                        The proposal for which subproposals should be returned
     * @param includeProposalsInSameContest     Specifies whether linked proposals in the same contest as the passed proposal
     *                                          should be included in the result or not
     * @return collection of referenced proposals
     */
    @Override
    public List<Proposal> getSubproposals(long proposalId, boolean includeProposalsInSameContest) throws SystemException, PortalException {
        final boolean onlyWithContestIntegrationRelevance = false;
        return getSubproposals(proposalId, includeProposalsInSameContest, onlyWithContestIntegrationRelevance);
    }

    /**
     * Returns list of proposals referenced by given proposal
     * @param proposalId                        The proposal for which subproposals should be returned
     * @param includeProposalsInSameContest     Specifies whether linked proposals in the same contest as the passed proposal
     *                                          should be included in the result or not
     * @param onlyWithContestIntegrationRelevance Specifies whether only proposal with relevance for integration should be included
     *
     * @return collection of referenced proposals
     */
    @Override
    public List<Proposal> getSubproposals(long proposalId, boolean includeProposalsInSameContest, boolean onlyWithContestIntegrationRelevance)
            throws SystemException, PortalException {
        List<ProposalReference> proposalReferences = ProposalReferenceLocalServiceUtil.getByProposalId(proposalId);

        List<Proposal> proposals = new ArrayList<>();
        for (ProposalReference proposalReference : proposalReferences) {
            if (onlyWithContestIntegrationRelevance) {
                ProposalAttribute attribute = ProposalAttributeLocalServiceUtil.fetchProposalAttribute(proposalReference.getSectionAttributeId());
                PlanSectionDefinition psd = PlanSectionDefinitionLocalServiceUtil.fetchPlanSectionDefinition(attribute.getAdditionalId());
                if (!psd.getContestIntegrationRelevance()) {
                    continue;
                }
            }
            final long subProposalId = proposalReference.getSubProposalId();
            Proposal p = getProposal(subProposalId);
            if (p != null) {
                if (!includeProposalsInSameContest) {
                    if (getLatestProposalContest(proposalId).equals(getLatestProposalContest(subProposalId))) {
                        continue;
                    }
                }
                if (p.getProposalId() != proposalId) {
                    proposals.add(p);
                }
            }
        }
        return proposals;
    }

    /**
     * Returns latest contest phase to which proposal was submitted
     *
     * @param proposalId id of a proposal
     * @return last contest phase to which proposal was submitted
     * @throws PortalException
     * @throws SystemException
     */
    @Override
    public ContestPhase getLatestProposalContestPhase(long proposalId) throws PortalException, SystemException {
        Proposal2Phase latestP2p = null;
        for (Proposal2Phase p2p: proposal2PhaseLocalService.getByProposalId(proposalId)) {

            if (proposal2PhaseLocalService.isContestPhaseOfProposal2PhaseValidInContest(p2p)){
                // This is always the most current phase
                if (p2p.getVersionTo() == -1) {
                    latestP2p = p2p;
                    break;
                }

                if ((latestP2p == null || p2p.getVersionTo() == 0 || latestP2p.getVersionTo() < p2p.getVersionTo())) {
                    latestP2p = p2p;
                }
            }
        }
        if (latestP2p != null) {
            return contestPhaseLocalService.getContestPhase(latestP2p.getContestPhaseId());
        }
        return null;
    }

    /**
     * Returns latest contest to which proposal was submitted
     * 
     * @param proposalId id of a proposal
     * @return last contest to which proposal was submitted
     * @throws PortalException
     * @throws SystemException
     */
    @Override
    public Contest getLatestProposalContest(long proposalId) throws PortalException, SystemException {
    	return contestLocalService.getContest(this.getLatestProposalContestPhase(proposalId).getContestPK());
    }

    /**
     * Returns all focus areas, for which entered proposal impact data is available
     *
     */
    @Override
    public List<FocusArea> getImpactProposalFocusAreas(Proposal proposal) throws SystemException, PortalException {
        Set<Long> focusAreaIdSet = new HashSet<>();
        List<FocusArea> impactSeriesFocusAreas = new ArrayList<>();

        for (ProposalAttribute attribute : proposalAttributeLocalService.getImpactProposalAttributes(proposal)) {
            if (!focusAreaIdSet.contains(attribute.getAdditionalId())) {
                focusAreaIdSet.add(attribute.getAdditionalId());
                impactSeriesFocusAreas.add(FocusAreaLocalServiceUtil.getFocusArea(attribute.getAdditionalId()));
            }
        }

        return impactSeriesFocusAreas;
    }

    @Override
    public boolean isDeleted(Proposal proposal) throws SystemException, PortalException {
        final ContestPhase contestPhase = getLatestProposalContestPhase(proposal.getProposalId());
        long visibleAttributeValue = 1;
        if (contestPhase != null) {
            visibleAttributeValue = ProposalContestPhaseAttributeLocalServiceUtil.getAttributeLongValue(proposal.getProposalId(),
                    contestPhase.getContestPhasePK(), ProposalContestPhaseAttributeKeys.VISIBLE, 0, 1);
        }
        return !proposal.isVisible() || visibleAttributeValue == 0;
    }

    @Override
    public boolean isVisibleInContest(Proposal proposal, long contestId) throws PortalException, SystemException {
        final Contest currentContest = proposal2PhaseLocalService.getCurrentContestForProposal(proposal.getProposalId());
        return !isDeleted(proposal) && currentContest.getContestPK() == contestId;
    }
}
