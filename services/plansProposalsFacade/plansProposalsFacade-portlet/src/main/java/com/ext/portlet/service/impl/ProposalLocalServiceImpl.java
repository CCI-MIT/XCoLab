package com.ext.portlet.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.ext.portlet.discussions.DiscussionActions;
import com.ext.portlet.model.DiscussionCategoryGroup;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.base.ProposalLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalAttributePK;
import com.ext.portlet.service.persistence.ProposalVersionPK;
import com.ext.utils.PortalServicesHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.GroupConstants;
import com.liferay.portal.model.ResourceConstants;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.RoleConstants;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.service.ServiceContext;

/**
 * The implementation of the proposal local service.
 * 
 * <p>
 * All custom service methods should be put in this class. Whenever methods are
 * added, rerun ServiceBuilder to copy their definitions into the
 * {@link com.ext.portlet.service.ProposalLocalService} interface.
 * 
 * <p>
 * This is a local service. Methods of this service will not have security
 * checks based on the propagated JAAS credentials because this service can only
 * be accessed from within the same VM.
 * </p>
 * 
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalLocalServiceUtil
 */
public class ProposalLocalServiceImpl extends ProposalLocalServiceBaseImpl {

    private static Log _log = LogFactoryUtil.getLog(ProposalLocalServiceImpl.class);

    /**
     * Default community permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_COMMUNITY_PERMISSIONS = { ActionKeys.VIEW, ActionKeys.SUBSCRIBE,
            ActionKeys.REPLY_TO_MESSAGE, ActionKeys.ADD_MESSAGE };

    /**
     * Default guest permissions for community forum category.
     */
    public static final String[] DEFAULT_CATEGORY_GUEST_PERMISSIONS = { ActionKeys.VIEW, ActionKeys.SUBSCRIBE };

    /**
     * Default description of group working on a plan.
     */
    public static final String DEFAULT_GROUP_DESCRIPTION = "Group working on plan %s";
    
    private PortalServicesHelper portalServicesHelper;
    
    public ProposalLocalServiceImpl(PortalServicesHelper portalServicesHelper) {
        this.portalServicesHelper = portalServicesHelper;
    }

    /**
     * <p>
     * Creates new proposal initialized to version 1 with one attribute "NAME"
     * set to "Untitled proposal - PROPOSAL_ID". All related entities are
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
     * @param authorId
     *            id of proposal author
     * @return created proposal
     * @throws SystemException
     *             in case of a Liferay error
     * @throws PortalException
     *             in case of a Liferay error
     */
    @Transactional
    public Proposal create(long authorId) throws SystemException, PortalException {

        long proposalId = portalServicesHelper.getCounterLocalService().increment(Proposal.class.getName());
        Proposal proposal = createProposal(proposalId);
        proposal.setVisible(true);
        proposal.setAuthorId(authorId);
        proposal.setCreateDate(new Date());

        // create discussions
        DiscussionCategoryGroup proposalDiscussion = discussionCategoryGroupLocalService
                .createDiscussionCategoryGroup("Proposal " + proposalId + " main discussion");
        proposal.setDiscussionId(proposalDiscussion.getId());

        DiscussionCategoryGroup judgesDiscussion = discussionCategoryGroupLocalService
                .createDiscussionCategoryGroup("Proposal " + proposalId + " judges discussion");
        proposal.setJudgeDiscussionId(judgesDiscussion.getId());

        DiscussionCategoryGroup advisorsDiscussion = discussionCategoryGroupLocalService
                .createDiscussionCategoryGroup("Proposal " + proposalId + " judges discussion");
        proposal.setAdvisorDiscussionId(advisorsDiscussion.getId());

        DiscussionCategoryGroup fellowsDiscussion = discussionCategoryGroupLocalService
                .createDiscussionCategoryGroup("Proposal " + proposalId + " judges discussion");
        proposal.setFellowDiscussionId(fellowsDiscussion.getId());

        // create group
        Group group = createGroupAndSetUpPermissions(authorId, proposalId);
        proposal.setGroupId(group.getGroupId());
        
        
        addProposal(proposal);

        return proposal;
    }

    @Transactional
    public void setAttribute(long authorId, long proposalId, String attributeName, long additionalId,
            String stringValue, long numericValue, double realValue) throws PortalException, SystemException {
        Proposal proposal = getProposal(proposalId);

        int currentVersion = proposal.getCurrentVersion();
        int newVersion = currentVersion + 1;

        proposal.setCurrentVersion(newVersion);

        // find attributes for current version of a proposal
        List<ProposalAttribute> currentProposalAttributes = proposalAttributePersistence.findByProposalIdVersion(
                proposalId, currentVersion);

        // for each attribute, if it isn't the one that we are changing, simply
        // update it to the most recent version
        // if it is the one that we are changing then leave old one as it is and
        // create new one with for new proposal version
        for (ProposalAttribute attribute : currentProposalAttributes) {
            if (!attributeName.equals(attribute.getName()) || additionalId != attribute.getAdditionalId()) {
                // update version
                attribute.setVersion(newVersion);
                proposalAttributeLocalService.updateProposalAttribute(attribute);
            }
        }

        // set new value for provided attribute
        setAttributeValue(proposalId, newVersion, attributeName, additionalId, stringValue, numericValue, realValue);

        // create newly created version descriptor
        createPlanVersionDescription(authorId, proposalId, newVersion, attributeName, additionalId);
        updateProposal(proposal);
    }

    @Transactional
    private void setAttributeValue(long proposalId, int version, String attributeName, long additionalId,
            String stringValue, long numericValue, double realValue) throws SystemException {
        ProposalAttribute attribute = proposalAttributeLocalService.createProposalAttribute(new ProposalAttributePK(
                proposalId, version, attributeName, additionalId));

        attribute.setNumericValue(numericValue);
        attribute.setStringValue(stringValue);
        attribute.setRealValue(realValue);

        proposalAttributeLocalService.addProposalAttribute(attribute);
    }

    private Group createGroupAndSetUpPermissions(long authorId, long proposalId) throws PortalException,
            SystemException {

        ServiceContext groupServiceContext = new ServiceContext();
        groupServiceContext.setUserId(authorId);
        String groupName = "Proposal_" + proposalId;

        Group group = portalServicesHelper.getGroupService().addGroup(StringUtils.substring(groupName, 0, 80),
                String.format(DEFAULT_GROUP_DESCRIPTION, StringUtils.substring(groupName, 0, 80)),
                GroupConstants.TYPE_SITE_RESTRICTED, null, true, true, groupServiceContext);

        Long companyId = group.getCompanyId();
        Role owner = portalServicesHelper.getRoleLocalService().getRole(companyId, RoleConstants.SITE_OWNER);
        Role admin = portalServicesHelper.getRoleLocalService().getRole(companyId, RoleConstants.SITE_ADMINISTRATOR);
        Role member = portalServicesHelper.getRoleLocalService().getRole(companyId, RoleConstants.SITE_MEMBER);
        Role userRole = portalServicesHelper.getRoleLocalService().getRole(companyId, RoleConstants.USER);
        Role guest = portalServicesHelper.getRoleLocalService().getRole(companyId, RoleConstants.GUEST);
        Role moderator = portalServicesHelper.getRoleLocalService().getRole(companyId, "Moderator");

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
            portalServicesHelper.getPermissionLocalService().setRolePermissions(role.getRoleId(), companyId,
                    DiscussionCategoryGroup.class.getName(), ResourceConstants.SCOPE_GROUP,
                    String.valueOf(group.getGroupId()), rolesActionsMap.get(role));
        }

        return group;
    }

    @Transactional
    private void createPlanVersionDescription(long authorId, long proposalId, int version, String updateType,
            long additionalId) throws SystemException {

        ProposalVersion proposalVersion = proposalVersionLocalService.createProposalVersion(new ProposalVersionPK(
                proposalId, version));

        proposalVersion.setAuthorId(authorId);
        proposalVersion.setUpdateType(updateType);
        proposalVersion.setUpdateAdditionalId(additionalId);

        proposalVersionLocalService.addProposalVersion(proposalVersion);
    }


}
