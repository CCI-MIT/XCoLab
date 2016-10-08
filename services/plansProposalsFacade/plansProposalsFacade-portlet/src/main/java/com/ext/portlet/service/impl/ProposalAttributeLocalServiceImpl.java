package com.ext.portlet.service.impl;

import com.ext.portlet.NoSuchProposalAttributeException;
import org.xcolab.client.proposals.enums.ProposalAttributeKeys;
import com.ext.portlet.model.ContestType;
import com.ext.portlet.model.FocusArea;
import com.ext.portlet.model.Proposal;
import com.ext.portlet.model.ProposalAttribute;
import com.ext.portlet.model.ProposalVersion;
import com.ext.portlet.service.base.ProposalAttributeLocalServiceBaseImpl;
import com.ext.portlet.service.persistence.ProposalVersionPK;
import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Transactional;

import org.xcolab.client.comment.CommentClient;
import org.xcolab.client.comment.exceptions.ThreadNotFoundException;
import org.xcolab.client.comment.pojo.CommentThread;
import org.xcolab.proposals.events.ProposalAttributeRemovedEvent;
import org.xcolab.proposals.events.ProposalAttributeUpdatedEvent;
import org.xcolab.services.EventBusService;
import org.xcolab.utils.ProposalAttributeDetectUpdateAlgorithm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The implementation of the proposal attribute local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.ext.portlet.service.ProposalAttributeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.ext.portlet.service.base.ProposalAttributeLocalServiceBaseImpl
 * @see com.ext.portlet.service.ProposalAttributeLocalServiceUtil
 */
public class ProposalAttributeLocalServiceImpl
    extends ProposalAttributeLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link com.ext.portlet.service.ProposalAttributeLocalServiceUtil} to access the proposal attribute local service.
     */

    @BeanReference(type = EventBusService.class)
    private EventBusService eventBus;

    @Override
    public ProposalAttribute getAttribute(long proposalId, int version, String attributeName, long additionalId)
            throws NoSuchProposalAttributeException, SystemException {
        List<ProposalAttribute> attribute = proposalAttributePersistence.
                findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual_NameAdditionalId(
                        proposalId, version, version, attributeName, additionalId);

        if (attribute.isEmpty()) {
            throw new NoSuchProposalAttributeException("Can't find attribute [" +
                    "proposalId: " + proposalId + ", " +
                    "version: " + version + ", " +
                    "attributeName: " + attributeName + ", " +
                    "additionalId: " + additionalId + "]");
        }

        return attribute.get(0);
    }

    /**
     * <p>Returns an attribute for current version of a proposal.</p>
     */
    @Override
    public ProposalAttribute getAttribute(long proposalId, String attributeName, long additionalId) throws SystemException, NoSuchProposalAttributeException {
        Proposal proposal = proposalPersistence.fetchByPrimaryKey(proposalId);
        return getAttribute(proposalId, proposal.getCurrentVersion(), attributeName, additionalId);
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
     * @return ProposalAttribute that represents newly set attribute
     */
    @Override
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
     * @return ProposalAttribute that represents newly set attribute
     */
    @Override
    @Transactional
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long additionalId,
                                          String stringValue, long numericValue, double realValue, Date updatedDate, boolean publishActivity)
            throws PortalException, SystemException {
        Proposal proposal = proposalPersistence.fetchByPrimaryKey(proposalId);
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
            ProposalAttributeDetectUpdateAlgorithm updateAlgorithm = new ProposalAttributeDetectUpdateAlgorithm(attribute);
            if (!updateAlgorithm.hasBeenUpdated(attributeName, additionalId, numericValue, realValue)) {
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
        createProposalVersionDescription(authorId, proposalId, newVersion, attributeName, additionalId, updatedDate);
        proposalLocalService.updateProposal(proposal);

        // Update the proposal name in the discussion category
        if (attributeName.equals(ProposalAttributeKeys.NAME)) {
            try {
                CommentThread thread = CommentClient.getThread(proposal.getDiscussionId());
                ContestType contestType = contestTypeLocalService.getContestTypeFromProposalId(proposalId);
                thread.setTitle(String.format("%s %s", contestType.getProposalName(), stringValue));
                CommentClient.updateThread(thread);
            } catch (ThreadNotFoundException ignored) {
            }
        }

        if (publishActivity) {
            eventBus.post(new ProposalAttributeUpdatedEvent(proposal, userLocalService.getUser(authorId),
                    attributeName, oldAttribute, attribute));
        }

        return attribute;
    }

    /**
     * <p>Helper method that sets an attribute value by creating a new attribute and setting all values according to passed parameters. This method doesn't care about other attributes.</p>
     * @return newly created proposal attribute
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
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     */
    @Override
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName,
                                          String stringValue, long numericValue, double realValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, 0L, stringValue, numericValue, realValue);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     */
    @Override
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long additionalId, String stringValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, additionalId, stringValue, 0, 0);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     */
    @Override
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, String stringValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, 0, stringValue, 0, 0);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     */
    @Override
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long additionalId, long numericValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, additionalId, null, numericValue, 0);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     */
    @Override
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long numericValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, 0, null, numericValue, 0);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     */
    @Override
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, long additionalId, double realValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, additionalId, null, 0, realValue);
    }

    /**
     * <p>Sets an attribute for a proposal. See  {@link #setAttribute(long, long, String, long, String, long, double)}
     * it uses nulls/zeros for unspecified values</p>
     */
    @Override
    public ProposalAttribute setAttribute(long authorId, long proposalId, String attributeName, double realValue) throws PortalException, SystemException {
        return setAttribute(authorId, proposalId, attributeName, 0, null, 0, realValue);
    }

    /**
     * <p>Returns all attributes for current version of a proposal.</p>
     *
     */
    @Override
    public List<ProposalAttribute> getAttributes(long proposalId) throws SystemException {
        Proposal proposal = proposalPersistence.fetchByPrimaryKey(proposalId);
        return getAttributes(proposalId, proposal.getCurrentVersion());
    }

    /**
     * <p>Returns all attributes for given version of a proposal.</p>
     */
    @Override
    public List<ProposalAttribute> getAttributes(long proposalId, int version) throws SystemException {
        return proposalAttributePersistence.findByProposalId_VersionGreaterEqual_VersionWhenCreatedLesserEqual(proposalId, version, version);
    }

    /**
     * <p>Removes a proposal attribute. All other proposal attributes in the current version are being promoted to the next version.</p>
     */
    @Override
    public void removeAttribute(long authorId, ProposalAttribute attributeToDelete, boolean publishActivity)
            throws SystemException, PortalException {
        Proposal proposal = proposalPersistence.fetchByPrimaryKey(attributeToDelete.getProposalId());

        int currentVersion = proposal.getCurrentVersion();
        int newVersion = currentVersion + 1;

        // find attributes for current version of a proposal
        List<ProposalAttribute> currentProposalAttributes = proposalAttributePersistence.findByProposalIdVersion(
                proposal.getProposalId(), currentVersion);

        // for each attribute, if it isn't the one that we are deleting, simply
        // update it to the most recent version
        for (ProposalAttribute attribute : currentProposalAttributes) {
            if (attribute.getId() != attributeToDelete.getId()) {
                // clone the attribute and set its version to the new value
                attribute.setVersion(newVersion);
                proposalAttributeLocalService.updateProposalAttribute(attribute);
            }
        }

        Date now = new Date();
        proposal.setCurrentVersion(newVersion);
        proposal.setUpdatedDate(now);

        // create newly created version descriptor
        createProposalVersionDescription(authorId, attributeToDelete.getProposalId(), newVersion, attributeToDelete.getName(), attributeToDelete.getAdditionalId(), now);
        proposalLocalService.updateProposal(proposal);

        if (publishActivity) {
            eventBus.post(new ProposalAttributeRemovedEvent(proposal, userLocalService.getUser(authorId),
                    attributeToDelete.getName(), attributeToDelete));
            /*
            ActivityEntryHelper.createActivityEntry(authorId,proposal.getProposalId(),attributeToDelete.getName(),
                    new ProposalAttributeRemoveActivityEntry());*/
        }
    }

    /**
     * <p>Removes a proposal attribute. This method is currently only used for the Proposal impact feature to delete already saved proposal impact serieses.</p>
     */
    @Override
    public void removeAttribute(long authorId, ProposalAttribute attributeToDelete) throws PortalException, SystemException {
        removeAttribute(authorId, attributeToDelete, true);
    }

    /**
     * <p>Creates new plan version descriptor</p>
     */
    @Transactional
    private void createProposalVersionDescription(long authorId, long proposalId, int version, String updateType,
                                                  long additionalId, Date updatedDate) throws SystemException {

        ProposalVersion proposalVersion = proposalVersionLocalService.createProposalVersion(new ProposalVersionPK(
                proposalId, version));

        proposalVersion.setAuthorId(authorId);
        proposalVersion.setUpdateType(updateType);
        proposalVersion.setUpdateAdditionalId(additionalId);
        proposalVersion.setCreateDate(updatedDate);

        proposalVersionLocalService.addProposalVersion(proposalVersion);
    }

    @Override
    public List<ProposalAttribute> getImpactProposalAttributes(Proposal proposal) throws SystemException {
        return proposalAttributeFinder.findByProposalIdVersionGreaterThanVersionWhenCreatedLessThanNameLikeImpact(proposal.getProposalId(),
                proposal.getCurrentVersion(), proposal.getCurrentVersion());
    }

    @Override
    public List<ProposalAttribute> getImpactProposalAttributes(Proposal proposal, FocusArea focusArea) throws SystemException {
        List<ProposalAttribute> filteredProposalAttributes = new ArrayList<>();
        for (ProposalAttribute attribute : getImpactProposalAttributes(proposal)) {
            if (attribute.getAdditionalId() == focusArea.getId()) {
                filteredProposalAttributes.add(attribute);
            }
        }
        return filteredProposalAttributes;
    }
}
