package com.ext.portlet.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.portlet.PortletRequest;

import org.apache.commons.lang3.StringUtils;
import org.xcolab.proposals.events.ProposalAssociatedWithContestPhaseEvent;
import org.xcolab.proposals.events.ProposalAttributeUpdatedEvent;
import org.xcolab.proposals.events.ProposalMemberAddedEvent;
import org.xcolab.proposals.events.ProposalMemberRemovedEvent;
import org.xcolab.proposals.events.ProposalRemovedVoteEvent;
import org.xcolab.proposals.events.ProposalSupporterAddedEvent;
import org.xcolab.proposals.events.ProposalSupporterRemovedEvent;
import org.xcolab.proposals.events.ProposalVotedOnEvent;
import org.xcolab.services.EventBusService;
import org.xcolab.utils.UrlBuilder;
import org.xcolab.utils.judging.ProposalJudgingCommentHelper;

import com.ext.portlet.NoSuchProposalAttributeException;
import com.ext.portlet.NoSuchProposalContestPhaseAttributeException;
import com.ext.portlet.NoSuchProposalSupporterException;
import com.ext.portlet.NoSuchProposalVoteException;
import com.ext.portlet.PlanSectionTypeKeys;
import com.ext.portlet.ProposalAttributeKeys;
import com.ext.portlet.ProposalContestPhaseAttributeKeys;
import com.ext.portlet.Activity.DiscussionActivityKeys;
import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.messaging.MessageUtil;
import com.ext.portlet.model.Contest;
import com.ext.portlet.model.ContestPhase;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.PlanSectionDefinition;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.Proposal2Phase;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalContestPhaseAttribute;
import com.ext.portlet.model.ProposalSupporter;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.model.ProposalVote;
import com.ext.portlet.service.ContestPhaseLocalServiceUtil;
import com.ext.portlet.service.DiscussionCategoryGroupLocalServiceUtil;
import com.ext.portlet.service.ProposalLocalServiceUtil;
import com.ext.portlet.service.base.ProposalLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.Proposal2PhasePK;
import com.ext.portlet.service.persistence.ProposalSupporterPK;
import com.ext.portlet.service.persistence.ProposalVersionPK;
import com.ext.portlet.service.persistence.ProposalVotePK;
import com.liferay.counter.service.CounterLocalServiceUtil;
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
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.MembershipRequest;
import com.liferay.portal.model.MembershipRequestConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.model.User;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.GroupService;
import com.liferay.portal.service.MembershipRequestLocalServiceUtil;
import com.liferay.portal.service.ResourcePermissionLocalServiceUtil;
import com.liferay.portal.service.RoleLocalService;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.util.mail.MailEngineException;

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

    private static Log _log = LogFactoryUtil.getLog(ProposalLocalServiceImpl.class);

    private static final long ADMINISTRATOR_USER_ID = 10144L;

    /**
     * Default community permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_COMMUNITY_PERMISSIONS = {ActionKeys.VIEW, ActionKeys.SUBSCRIBE,
            ActionKeys.REPLY_TO_MESSAGE, ActionKeys.ADD_MESSAGE};

    /**
     * Default guest permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_GUEST_PERMISSIONS = {ActionKeys.VIEW, ActionKeys.SUBSCRIBE};

    /**
     * Default description of group working on a plan.
     */
    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on plan %s";

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
    @Transactional
    public Proposal create(long authorId, long contestPhaseId, long proposalId, boolean publishActivity) throws SystemException, PortalException {

        Proposal proposal = createProposal(proposalId);
        proposal.setVisible(true);
        proposal.setAuthorId(authorId);
        proposal.setCreateDate(new Date());

        ContestPhase contestPhase = ContestPhaseLocalServiceUtil.getContestPhase(contestPhaseId);
        // create discussions
        DiscussionCategoryGroup proposalDiscussion = discussionCategoryGroupLocalService
                .createDiscussionCategoryGroup("Proposal " + proposalId + " main discussion");

        proposalDiscussion.setUrl(UrlBuilder.getProposalCommentsUrl(contestPhase.getContestPK(), proposalId));
        discussionCategoryGroupLocalService.updateDiscussionCategoryGroup(proposalDiscussion);

        proposal.setDiscussionId(proposalDiscussion.getId());

        DiscussionCategoryGroup judgesDiscussion = discussionCategoryGroupLocalService
                .createDiscussionCategoryGroup("Proposal " + proposalId + " judges discussion");
        judgesDiscussion.setIsQuiet(true);
        discussionCategoryGroupLocalService.updateDiscussionCategoryGroup(judgesDiscussion);
        proposal.setJudgeDiscussionId(judgesDiscussion.getId());

        DiscussionCategoryGroup advisorsDiscussion = discussionCategoryGroupLocalService
                .createDiscussionCategoryGroup("Proposal " + proposalId + " advisors discussion");
        advisorsDiscussion.setIsQuiet(true);
        discussionCategoryGroupLocalService.updateDiscussionCategoryGroup(advisorsDiscussion);
        proposal.setAdvisorDiscussionId(advisorsDiscussion.getId());

        DiscussionCategoryGroup fellowsDiscussion = discussionCategoryGroupLocalService
                .createDiscussionCategoryGroup("Proposal " + proposalId + " fellows discussion");
        fellowsDiscussion.setIsQuiet(true);
        discussionCategoryGroupLocalService.updateDiscussionCategoryGroup(fellowsDiscussion);
        proposal.setFellowDiscussionId(fellowsDiscussion.getId());

        // create group
        Group group = createGroupAndSetUpPermissions(authorId, proposalId);
        proposal.setGroupId(group.getGroupId());


        addProposal(proposal);

        if (contestPhaseId > 0) {
            // associate proposal with phase
            Proposal2Phase p2p = proposal2PhaseLocalService.createProposal2Phase(new Proposal2PhasePK(proposalId, contestPhaseId));
            p2p.setVersionFrom(proposal.getCurrentVersion());
            p2p.setVersionTo(-1);
            proposal2PhaseLocalService.addProposal2Phase(p2p);
            if (publishActivity) eventBus.post(new ProposalAssociatedWithContestPhaseEvent(proposal,
                    contestPhaseLocalService.getContestPhase(contestPhaseId), UserLocalServiceUtil.getUser(authorId)));
        }

        // Automatically subscribe author to own proposal
        subscribe(proposalId, authorId);

        return proposal;
    }

    @Transactional
    public void setVisibility(Long proposalId, Boolean visibility, Long authorId) throws SystemException, PortalException {
        Proposal proposal = ProposalLocalServiceUtil.getProposal(proposalId);
        proposal.setVisible(visibility);
        ProposalLocalServiceUtil.updateProposal(proposal);

        setAttribute(authorId, proposalId, ProposalAttributeKeys.VISIBLE, (visibility) ? 1l : 0l);

    }

    /**
     * <p>Sets attribute value and creates new version for a proposal that reflects the change</p>
     * <p>The algorithm for setting an attribute value is as follows:</p>
     * <ol>
     * <li>new proposal version is created</li>
     * <li>for each attribute that was already present in the proposal (excluding the one that is currently being set)
     * it is copied to the new version</li>
     * <li>for attribute that is being set it's value (if present) isn't copied to the new version as it gets new value</li>
     * </ol>
     *
     * @param authorId      id of a change author
     * @param proposalId    id of a proposal
     * @param attributeName name of an attribute
     * @param additionalId  additional id for an attribute
     * @param stringValue   string value for an attribute
     * @param numericValue  numeric value for an attribute
     * @param realValue     double value for an attribute
     * @return ProposalAttribute that represents newly set attribute
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     * @author janusz
     */
    @Transactional
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long additionalId,
                                          String stringValue, long numericValue, double realValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, additionalId, stringValue, numericValue, realValue, new Date(), true);
    }

    /**
     * <p>Sets attribute value and creates new version for a proposal that reflects the change</p>
     * <p>The algorithm for setting an attribute value is as follows:</p>
     * <ol>
     * <li>new proposal version is created</li>
     * <li>for each attribute that was already present in the proposal (excluding the one that is currently being set)
     * it is copied to the new version</li>
     * <li>for attribute that is being set it's value (if present) isn't copied to the new version as it gets new val`ue</li>
     * </ol>
     *
     * @param authorId      id of a change author
     * @param proposalId    id of a proposal
     * @param attributeName name of an attribute
     * @param additionalId  additional id for an attribute
     * @param stringValue   string value for an attribute
     * @param numericValue  numeric value for an attribute
     * @param realValue     double value for an attribute
     * @param updatedDate   date of update
     * @return ProposalAttribute that represents newly set attribute
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     * @author patrickhiesel
     */
    @Transactional
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long additionalId,
                                          String stringValue, long numericValue, double realValue, Date updatedDate, boolean publishActivity) throws PortalException, SystemException {
        Proposal proposal = getProposal(proposalId);
        ProposalAttribute oldAttribute = null;

        int currentVersion = proposal.getCurrentVersion();
        int newVersion = currentVersion + 1;

        // find attributes for current version of a proposal
        List<ProposalAttribute> currentProposalAttributes = proposalAttributePersistence.findByProposalIdVersion(
                proposalId, currentVersion);

        // for each attribute, if it isn't the one that we are changing, simply
        // update it to the most recent version
        // if it is the one that we are changing then leave old one as it is and
        // create new one for new proposal version
        for (ProposalAttribute attribute : currentProposalAttributes) {
            if (!attributeName.equals(attribute.getName()) || additionalId != attribute.getAdditionalId()) {
                // clone the attribute and set its version to the new value
                attribute.setVersion(newVersion);
                proposalAttributeLocalService.updateProposalAttribute(attribute);
            } else {
                oldAttribute = attribute;
            }
        }

        // set new value for provided attribute
        ProposalAttribute attribute = setAttributeValue(proposalId, newVersion, attributeName, additionalId, stringValue, numericValue, realValue);

        proposal.setCurrentVersion(newVersion);
        proposal.setUpdatedDate(updatedDate);

        // create newly created version descriptor
        createPlanVersionDescription(authorId, proposalId, newVersion, attributeName, additionalId, updatedDate);
        updateProposal(proposal);

        // Update the proposal name in the discussion category
        if (attributeName.equals(ProposalAttributeKeys.NAME)) {
            DiscussionCategoryGroup dcg = discussionCategoryGroupLocalService.getDiscussionCategoryGroup(proposal.getDiscussionId());
            dcg.setDescription(String.format(DiscussionActivityKeys.PROPOSAL_DISCUSSION_FORMAT_STRING, stringValue));
            discussionCategoryGroupLocalService.updateDiscussionCategoryGroup(dcg);
        }

        if (publishActivity)
            eventBus.post(new ProposalAttributeUpdatedEvent(proposal, userLocalService.getUser(authorId),
                    attributeName, oldAttribute, attribute));

        return attribute;
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     *
     * @param authorId
     * @param proposalId
     * @param attributeName
     * @param stringValue
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName,
                                          String stringValue, long numericValue, double realValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, 0L, stringValue, numericValue, realValue);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     *
     * @param authorId
     * @param proposalId
     * @param attributeName
     * @param additionalId
     * @param stringValue
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long additionalId, String stringValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, additionalId, stringValue, 0, 0);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     *
     * @param authorId
     * @param proposalId
     * @param attributeName
     * @param stringValue
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, String stringValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, 0, stringValue, 0, 0);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     *
     * @param authorId
     * @param proposalId
     * @param attributeName
     * @param additionalId
     * @param numericValue
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long additionalId, long numericValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, additionalId, null, numericValue, 0);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     *
     * @param authorId
     * @param proposalId
     * @param attributeName
     * @param numericValue
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long numericValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, 0, null, numericValue, 0);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     *
     * @param authorId
     * @param proposalId
     * @param attributeName
     * @param additionalId
     * @param realValue
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long additionalId, double realValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, additionalId, null, 0, realValue);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     *
     * @param authorId
     * @param proposalId
     * @param attributeName
     * @param realValue
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, double realValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, 0, null, 0, realValue);
    }

    /**
     * <p>Returns all attributes for current version of a proposal.</p>
     *
     * @param proposalId id of a proposal
     * @return list of proposal attributes for current version of a proposal
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     * @author janusz
     */
    public List<ProposalAttribute> getAttributes(long proposalId) throws SystemException, PortalException {
        Proposal proposal = getProposal(proposalId);

        return getAttributes(proposalId, proposal.getCurrentVersion());
    }

    /**
     * <p>Returns all attributes for given version of a proposal.</p>
     *
     * @param proposalId id of a proposal
     * @param version    version number of a proposal
     * @return list of proposal attributes for current version of a proposal
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     * @author janusz
     */
    public List<ProposalAttribute> getAttributes(long proposalId, int version) throws SystemException, PortalException {
        return proposalAttributePersistence.findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(proposalId, version, version);
    }

    /**
     * <p>Returns an attribute for current version of a proposal.</p>
     *
     * @param proposalId    id of a proposal
     * @param attributeName name of an attribute
     * @param additionalId  additionalId of an attribute
     * @return proposal attribute
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     * @author janusz
     */
    public ProposalAttribute getAttribute(long proposalId, String attributeName, long additionalId) throws PortalException, SystemException {
        Proposal proposal = getProposal(proposalId);
        return getAttribute(proposalId, proposal.getCurrentVersion(), attributeName, additionalId);
    }

    /**
     * <p>Returns an attribute for concrete version of a proposal.</p>
     *
     * @param proposalId    id of a proposal
     * @param version       version of a proposal
     * @param attributeName name of an attribute
     * @param additionalId  additionalId of an attribute
     * @return proposal attribute
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     * @author janusz
     */
    public ProposalAttribute getAttribute(long proposalId, int version, String attributeName, long additionalId) throws NoSuchProposalAttributeException, SystemException {
        List<ProposalAttribute> attribute = proposalAttributePersistence.
                findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(
                        proposalId, version, version, attributeName, additionalId);

        if (attribute.isEmpty()) throw new NoSuchProposalAttributeException("Can't find attribute [" +
                "proposalId: " + proposalId + ", " +
                "version: " + version + ", " +
                "attributeName: " + attributeName + ", " +
                "additionalId: " + additionalId + "]");

        return attribute.get(0);
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

    public List<Proposal> getProposalsInContestPhase(long contestPhaseId, String sortProperty, boolean sortAscending, int start, int end)
            throws PortalException, SystemException {

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
    public List<Proposal> getProposalsInContest(long contestId) throws PortalException, SystemException {
        List<Proposal> proposals = new ArrayList<>();

        ContestPhase lastOrActivePhase = contestLocalService.getActiveOrLastPhase(contestLocalService.getContest(contestId));
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
        List<Proposal> proposals = proposalLocalService.dynamicQuery(query);

        // Filter out "deleted" proposals
        List<Proposal> returnList = new ArrayList<>();
        for (Proposal proposal : proposals) {
            List<Proposal2Phase> p2Phases = proposal2PhaseLocalService.getByProposalId(proposal.getProposalId());

            // Count number of invisible attributes
            int invisibleCount = 0;
            int overallCount = 0;
            for (Proposal2Phase phase : p2Phases) {
                overallCount++;

                // Try to get
                try {
                    final ProposalContestPhaseAttribute visibleAttribute = proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttribute(
                            phase.getProposalId(), phase.getContestPhaseId(), ProposalContestPhaseAttributeKeys.VISIBLE);

                    if (visibleAttribute.getNumericValue() == 0) {
                        invisibleCount++;
                    }

                } catch (NoSuchProposalContestPhaseAttributeException e) {
                    // We ignore the exception here since it does not have an impact
                }

            }

            // Either we don't have an invisible entry in the attributes table or there is at least one visible
            if ((overallCount == 0) || (overallCount != invisibleCount)) {
                returnList.add(proposal);
            }
        }

        return returnList;
    }

    /**
     * <p>Returns count of proposals associated with given contest phase</p>
     *
     * @param contestPhaseId id of a contest phase
     * @return count of proposals from given contest phase
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
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
    public List<User> getSupporters(long proposalId) throws SystemException, PortalException {
        List<User> ret = new ArrayList<>();
        for (ProposalSupporter supporter : proposalSupporterPersistence.findByProposalId(proposalId)) {
            ret.add(UserLocalServiceUtil.getUser(supporter.getUserId()));
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
    @Transactional
    public void addSupporter(long proposalId, long userId, boolean publishActivity) throws SystemException, PortalException {
        ProposalSupporter supporter =
                proposalSupporterLocalService.createProposalSupporter(new ProposalSupporterPK(proposalId, userId));

        supporter.setCreateDate(new Date());
        proposalSupporterLocalService.addProposalSupporter(supporter);

        if (publishActivity)
            eventBus.post(new ProposalSupporterAddedEvent(getProposal(proposalId), userLocalService.getUser(userId)));
    }

    /**
     * <p>Retracts support from a proposal</p>
     *
     * @param proposalId id of a proposal
     * @param userId     id of a supported to be removed
     * @throws SystemException in case of an LR error
     * @throws PortalException
     */
    @Transactional
    public void removeSupporter(long proposalId, long userId) throws SystemException, PortalException {
        ProposalSupporter supporter =
                proposalSupporterLocalService.createProposalSupporter(new ProposalSupporterPK(proposalId, userId));

        proposalSupporterLocalService.deleteProposalSupporter(supporter);
        eventBus.post(new ProposalSupporterRemovedEvent(getProposal(proposalId), userLocalService.getUser(userId)));
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
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
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

        proposalVoteLocalService.addProposalVote(vote);
        if (publishActivity)
            eventBus.post(new ProposalVotedOnEvent(getProposal(proposalId), userLocalService.getUser(userId), voted));
    }

    /**
     * <p>Retracts user vote in context of a contest phase.</p>
     *
     * @param contestPhaseId id of a contest phase
     * @param userId         id of an user
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    @Transactional
    public void removeVote(long contestPhaseId, long userId) throws SystemException, PortalException {
        try {
            ProposalVote proposalVote = proposalVoteLocalService.findByProposalIdContestPhaseIdUserId(contestPhaseId, userId);
            proposalVoteLocalService.deleteProposalVote(proposalVote);
            eventBus.post(new ProposalRemovedVoteEvent(getProposal(proposalVote.getProposalId()), userLocalService.getUser(userId)));

        } catch (NoSuchProposalVoteException e) {
            // ignore
            return;
        }

    }

    /**
     * <p>Returns number of comments in discussion associated with this proposal</p>
     *
     * @param proposalId proposal id
     * @return number of comments
     * @throws PortalException in case of an LR error
     * @throws SystemException in case of an LR error
     */
    public long getCommentsCount(long proposalId) throws SystemException, PortalException {
        Proposal proposal = getProposal(proposalId);
        return discussionCategoryGroupLocalService.getCommentsCount(proposal.getDiscussionId());
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
    public boolean isOpen(long proposalId) throws PortalException, SystemException {
        try {
            ProposalAttribute attribute = getAttribute(proposalId, ProposalAttributeKeys.OPEN, 0);
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
    public List<MembershipRequest> getMembershipRequests(long proposalId) throws SystemException, PortalException {
        Proposal proposal = getProposal(proposalId);
        return MembershipRequestLocalServiceUtil.search(proposal.getGroupId(),
                MembershipRequestConstants.STATUS_PENDING, 0, Integer.MAX_VALUE);
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
    public MembershipRequest addMembershipRequest(long proposalId, long userId, String comment) throws PortalException, SystemException {
        Proposal proposal = getProposal(proposalId);

        return MembershipRequestLocalServiceUtil.addMembershipRequest(userId, proposal.getGroupId(), comment, null);
    }


    /**
     * <p>Remove a user from a proposal team</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    public void removeUserFromTeam(long proposalId, long userId) throws PortalException, SystemException {
        Proposal proposal = getProposal(proposalId);
        GroupLocalServiceUtil.unsetUserGroups(userId, new long[]{proposal.getGroupId()});

        eventBus.post(new ProposalMemberRemovedEvent(proposal, userLocalService.getUser(userId)));
    }


    /**
     * <p>Denies user as a member of proposal team</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
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
    public void approveMembershipRequest(long proposalId, Long userId, MembershipRequest request, String reply, Long updateAuthorId)
            throws PortalException, SystemException {

        if (hasUserRequestedMembership(proposalId, userId)) {
            MembershipRequestLocalServiceUtil.updateStatus(userId, request.getMembershipRequestId(), reply,
                    MembershipRequestConstants.STATUS_APPROVED, true, null);
            eventBus.post(new ProposalMemberAddedEvent(getProposal(proposalId), userLocalService.getUser(userId)));

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
    public boolean hasUserRequestedMembership(long proposalId, long userId) throws PortalException, SystemException {
        Proposal proposal = ProposalLocalServiceUtil.getProposal(proposalId);
        return !MembershipRequestLocalServiceUtil.getMembershipRequests(userId, proposal.getGroupId(), MembershipRequestConstants.STATUS_PENDING).isEmpty();

    }

    /**
     * <p>Adds user to a proposal team if proposal is open and user is not a member already</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
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
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    public boolean isSubscribed(long proposalId, long userId) throws PortalException, SystemException {
        return activitySubscriptionLocalService.isSubscribed(userId, Proposal.class, proposalId, 0, "");
    }

    /**
     * <p>Subscribes user to a proposal</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    public void subscribe(long proposalId, long userId) throws PortalException, SystemException {
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
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    public void subscribe(long proposalId, long userId, boolean automatic) throws PortalException, SystemException {
        activitySubscriptionLocalService.addSubscription(Proposal.class, proposalId, 0, "", userId, automatic);

        Proposal proposal = getProposal(proposalId);
        DiscussionCategoryGroup dcg = discussionCategoryGroupLocalService.getDiscussionCategoryGroup(proposal.getDiscussionId());
        activitySubscriptionLocalService.addSubscription(DiscussionCategoryGroup.class, dcg.getPrimaryKey(), 0, "", userId, automatic);
    }


    /**
     * <p>Unsubscribes user from given proposal</p>
     *
     * @param proposalId proposal id
     * @param userId     user id
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    public void unsubscribe(long proposalId, long userId) throws PortalException, SystemException {
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
     * @throws PortalException in case of LR error
     * @throws SystemException in case of LR error
     */
    public void unsubscribe(long proposalId, long userId, boolean automatic) throws PortalException, SystemException {
        activitySubscriptionLocalService.deleteSubscription(userId, Proposal.class, proposalId, 0, "", automatic);

        Proposal proposal = getProposal(proposalId);
        DiscussionCategoryGroup dcg = discussionCategoryGroupLocalService.getDiscussionCategoryGroup(proposal.getDiscussionId());
        activitySubscriptionLocalService.deleteSubscription(userId, DiscussionCategoryGroup.class, dcg.getPrimaryKey(), 0, "", automatic);
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
     * @param userId
     * @return
     * @throws SystemException
     */
    public int getUserSupportedProposalsCount(long userId) throws SystemException {
        return proposalSupporterPersistence.countByUserId(userId);
    }

    /**
     * Returns number of proposals that user has given his vote to
     *
     * @param userId
     * @return
     * @throws SystemException
     */
    public int getUserVotedProposalsCount(long userId) throws SystemException {
        return proposalVotePersistence.countByUserId(userId);
    }

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
    public void contestPhasePromotionEmailNotifyProposalContributors(Proposal proposal, ContestPhase contestPhase, PortletRequest request)
            throws PortalException, SystemException, AddressException, MailEngineException {

        String subject = "Judging Results on your Proposal " + ProposalLocalServiceUtil.getAttribute(proposal.getProposalId(), ProposalAttributeKeys.NAME, 0).getStringValue();

        ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        String messageBody = reviewContentHelper.getPromotionComment(true);
        if (Validator.isNotNull(messageBody)) {
            MessageUtil.sendMessage(subject, messageBody, ADMINISTRATOR_USER_ID, ADMINISTRATOR_USER_ID, getMemberUserIds(proposal), request);
        }
    }

    /**
     * Posts the judges' review about the proposal's advance decision on the proposal's comment thread
     * @param proposal  The proposal for which the notification should be sent
     */
    public void contestPhasePromotionCommentNotifyProposalContributors(Proposal proposal, ContestPhase contestPhase) throws PortalException, SystemException {
        ProposalJudgingCommentHelper reviewContentHelper = new ProposalJudgingCommentHelper(proposal, contestPhase);
        String commentBody = reviewContentHelper.getPromotionComment(false);
        //only post comment if it is not empty.
        if (commentBody != null && !commentBody.trim().equals("")) {
            DiscussionCategoryGroup discussionGroup = DiscussionCategoryGroupLocalServiceUtil.getDiscussionCategoryGroup(proposal.getDiscussionId());
            DiscussionCategoryGroupLocalServiceUtil.addComment(discussionGroup, "", commentBody, UserLocalServiceUtil.getUser(ADMINISTRATOR_USER_ID));
        }
    }

    private List<Long> getMemberUserIds(Proposal proposal) throws PortalException, SystemException {
        List<Long> recipientIds = new ArrayList<>();

        for (User contributor : getMembers(proposal.getProposalId())) {
            recipientIds.add(contributor.getUserId());
        }

        return recipientIds;
    }
    /**
     * <p>Helper method that sets an attribute value by creating a new attribute and setting all values according to passed parameters. This method doesn't care about other attributes.</p>
     *
     * @param proposalId    id of a proposal
     * @param version       proposal version
     * @param attributeName name of an attribute
     * @param additionalId  additional id for an attribute
     * @param stringValue   string value for an attribute
     * @param numericValue  numeric value for an attribute
     * @param realValue     real value for an attribute
     * @return newly created proposal attribute
     * @throws SystemException in case of a LR error
     * @author janusz
     */
    @Transactional
    private ProposalAttribute setAttributeValue(long proposalId, int version, String attributeName, long additionalId,
                                                String stringValue, long numericValue, double realValue) throws SystemException {
        ProposalAttribute attribute = proposalAttributeLocalService.createProposalAttribute(
                CounterLocalServiceUtil.increment(ProposalAttribute.class.getName()));

        attribute.setName(attributeName);
        attribute.setProposalId(proposalId);
        attribute.setVersion(version);
        attribute.setVersionWhenCreated(version);
        attribute.setAdditionalId(additionalId);

        attribute.setNumericValue(numericValue);
        attribute.setStringValue(stringValue);
        attribute.setRealValue(realValue);

        proposalAttributeLocalService.addProposalAttribute(attribute);

        return attribute;
    }

    /**
     * <p>Creates a Liferay group for proposal and sets up proper permissions on it.</p>
     *
     * @param authorId   id of a proposal author
     * @param proposalId id of a proposal
     * @return newly created group
     * @throws PortalException in case on LR error
     * @throws SystemException in case on LR error
     * @author janusz
     */
    @Transactional
    private Group createGroupAndSetUpPermissions(long authorId, long proposalId) throws PortalException,
            SystemException {

        // create new gropu
        ServiceContext groupServiceContext = new ServiceContext();
        groupServiceContext.setUserId(authorId);
        String groupName = "Proposal_" + proposalId + "_" + new Date().getTime();

        Group group = groupService.addGroup(StringUtils.substring(groupName, 0, 80),
                String.format(DEFAULT_GROUP_DESCRIPTION, StringUtils.substring(groupName, 0, 80)),
                GroupConstants.TYPE_SITE_RESTRICTED, null, true, true, groupServiceContext);

        // set up permissions
        Long companyId = group.getCompanyId();
        Role owner = roleLocalService.getRole(companyId, RoleConstants.SITE_OWNER);
        Role admin = roleLocalService.getRole(companyId, RoleConstants.SITE_ADMINISTRATOR);
        Role member = roleLocalService.getRole(companyId, RoleConstants.SITE_MEMBER);
        Role userRole = roleLocalService.getRole(companyId, RoleConstants.USER);
        Role guest = roleLocalService.getRole(companyId, RoleConstants.GUEST);
        Role moderator = roleLocalService.getRole(companyId, "Moderator");

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

        Map<Long, String[]> rolesActionsMap = new HashMap<Long, String[]>();

        rolesActionsMap.put(owner.getRoleId(), ownerActions);
        rolesActionsMap.put(admin.getRoleId(), adminActions);
        rolesActionsMap.put(member.getRoleId(), memberActions);
        rolesActionsMap.put(userRole.getRoleId(), userActions);
        rolesActionsMap.put(guest.getRoleId(), guestActions);
        rolesActionsMap.put(moderator.getRoleId(), moderatorActions);


        ResourcePermissionLocalServiceUtil.setResourcePermissions(companyId,
                DiscussionCategoryGroup.class.getName(), ResourceConstants.SCOPE_GROUP,
                String.valueOf(group.getGroupId()), rolesActionsMap);


        return group;
    }

    /**
     * <p>Creates new plan version descriptor</p>
     *
     * @param authorId     id of a change author
     * @param proposalId   id of a proposal
     * @param version      proposal version
     * @param updateType   name of updated attribute
     * @param additionalId additional id of an updated attribute
     * @throws SystemException
     */
    @Transactional
    private void createPlanVersionDescription(long authorId, long proposalId, int version, String updateType,
                                              long additionalId) throws SystemException {
        createPlanVersionDescription(authorId, proposalId, version, updateType, additionalId, new Date());
    }


    /**
     * <p>Creates new plan version descriptor</p>
     *
     * @param authorId     id of a change author
     * @param proposalId   id of a proposal
     * @param version      proposal version
     * @param updateType   name of updated attribute
     * @param additionalId additional id of an updated attribute
     * @param updatedDate  date when this version has been created
     * @throws SystemException
     */
    @Transactional
    private void createPlanVersionDescription(long authorId, long proposalId, int version, String updateType,
                                              long additionalId, Date updatedDate) throws SystemException {

        ProposalVersion proposalVersion = proposalVersionLocalService.createProposalVersion(new ProposalVersionPK(
                proposalId, version));

        proposalVersion.setAuthorId(authorId);
        proposalVersion.setUpdateType(updateType);
        proposalVersion.setUpdateAdditionalId(additionalId);
        proposalVersion.setCreateDate(updatedDate);

        proposalVersionLocalService.addProposalVersion(proposalVersion);
    }

    /**
     * Returns the URL link address for the passed proposal and contest
     *
     * @param contest  The contest object in which the proposal was written
     * @param proposal The proposal object (must not be null)
     * @return Proposal URL as String
     */
    public String getProposalLinkUrl(Contest contest, Proposal proposal) {
        String link = "/web/guest/plans/-/plans/contestId/%d/planId/%d";
        return String.format(link, contest.getContestPK(), proposal.getProposalId());
    }

    /**
     * Returns the URL link address for the passed proposal, contest and contestPhase
     *
     * @param contest       The contest object in which the proposal was written
     * @param proposal      The proposal object
     * @param contestPhase  The associated contestPhase of the proposal
     * @return Proposal URL as String
     */
    public String getProposalLinkUrl(Contest contest, Proposal proposal, ContestPhase contestPhase) {
        String link = "/web/guest/plans/-/plans/contestId/%d/phaseId/%d/planId/%d";
        return String.format(link, contest.getContestPK(), contestPhase.getContestPhasePK(), proposal.getProposalId());
    }
    
    /**
     * Returns list of proposals referenced by given proposal
     * @param proposalId      The proposal for which subproposals should be returned
     * @return collection of referenced proposals
     */
    public List<Proposal> getSubproposals(long proposalId) throws SystemException, PortalException {
    	Set<Long> detectedIds = new HashSet<Long>();
    	
    	
    	for (ProposalAttribute attribute: getAttributes(proposalId)) {
    		
    		if (attribute.getName().equals(ProposalAttributeKeys.SECTION)) {
    			PlanSectionDefinition psd = planSectionDefinitionLocalService.getPlanSectionDefinition(attribute.getAdditionalId());
    			
    			if (StringUtils.isBlank(psd.getType())) {
    				continue;
    			}
    		
    			PlanSectionTypeKeys type = PlanSectionTypeKeys.valueOf(psd.getType());
    			switch (type) {
    			case PROPOSAL_REFERENCE:
        			detectedIds.add(attribute.getNumericValue());
        			break;
    			case PROPOSAL_LIST_REFERENCE:
                    String[] referencedProposals = attribute.getStringValue().split(",");
                    for (int i = 0; i < referencedProposals.length; i++) {
                    	detectedIds.add(Long.parseLong(referencedProposals[i]));
                    }
                    break;
    			case PROPOSAL_LIST_TEXT_REFERENCE:
    				Pattern proposalLinkPattern = Pattern.compile("href=.*/plans/-/plans/contestId/(\\d*)/planId/(\\d*).*");
    				Matcher m = proposalLinkPattern.matcher(attribute.getStringValue());
    				while (m.find()) {
    					detectedIds.add(Long.parseLong(m.group(2)));
    				}
    				break;
    			}
    		}
    	}

    	List<Proposal> proposals = new ArrayList<Proposal>();
    	for (Long subProposalId: detectedIds) {
    		proposals.add(getProposal(subProposalId));
    	}
    	return proposals;
    }
    
    /**
     * Returns latest contest to which proposal was submited
     * 
     * @param proposalId id of a proposal
     * @return last contest to which proposal was submited
     * @throws PortalException
     * @throws SystemException
     */
    public Contest getLatestProposalContest(long proposalId) throws PortalException, SystemException {
    	Proposal2Phase latestP2p = null;
    	for (Proposal2Phase p2p: proposal2PhaseLocalService.getByProposalId(proposalId)) {
    		if (latestP2p == null || p2p.getVersionTo() == 0 || latestP2p.getVersionTo() < p2p.getVersionTo()) {
    			latestP2p = p2p;
    		}
    	}
    	
    	return contestLocalService.getContest(contestPhaseLocalService.getContestPhase(latestP2p.getContestPhaseId()).getContestPK());
    }
}
