package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalLocalService
 * @generated
 */
public class ProposalLocalServiceWrapper implements ProposalLocalService,
    ServiceWrapper<ProposalLocalService> {
    private ProposalLocalService _proposalLocalService;

    public ProposalLocalServiceWrapper(
        ProposalLocalService proposalLocalService) {
        _proposalLocalService = proposalLocalService;
    }

    /**
    * Adds the proposal to the database. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @return the proposal that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal addProposal(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.addProposal(proposal);
    }

    /**
    * Creates a new proposal with the primary key. Does not add the proposal to the database.
    *
    * @param proposalId the primary key for the new proposal
    * @return the new proposal
    */
    public com.ext.portlet.model.Proposal createProposal(long proposalId) {
        return _proposalLocalService.createProposal(proposalId);
    }

    /**
    * Deletes the proposal with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalId the primary key of the proposal
    * @throws PortalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _proposalLocalService.deleteProposal(proposalId);
    }

    /**
    * Deletes the proposal from the database. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @throws SystemException if a system exception occurred
    */
    public void deleteProposal(com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalLocalService.deleteProposal(proposal);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.Proposal fetchProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.fetchProposal(proposalId);
    }

    /**
    * Returns the proposal with the primary key.
    *
    * @param proposalId the primary key of the proposal
    * @return the proposal
    * @throws PortalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal getProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getProposal(proposalId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposals
    * @param end the upper bound of the range of proposals (not inclusive)
    * @return the range of proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal> getProposals(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getProposals(start, end);
    }

    /**
    * Returns the number of proposals.
    *
    * @return the number of proposals
    * @throws SystemException if a system exception occurred
    */
    public int getProposalsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getProposalsCount();
    }

    /**
    * Updates the proposal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @return the proposal that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal updateProposal(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.updateProposal(proposal);
    }

    /**
    * Updates the proposal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @param merge whether to merge the proposal with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the proposal that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal updateProposal(
        com.ext.portlet.model.Proposal proposal, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.updateProposal(proposal, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _proposalLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalLocalService.setBeanIdentifier(beanIdentifier);
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
    id of proposal author
    * @return created proposal
    * @throws SystemException
    in case of a Liferay error
    * @throws PortalException
    in case of a Liferay error
    * @author janusz
    */
    public com.ext.portlet.model.Proposal create(long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.create(authorId);
    }

    /**
    * <p>Sets attribute value and creates new version for a proposal that reflects the change</p>
    * <p>The algorithm for setting an attribute value is as follows:</p>
    * <ol>
    *  <li>new proposal version is created</li>
    *  <li>for each attribute that was already present in the proposal (excluding the one that is currently being set)
    *      it is copied to the new version</li>
    *  <li>for attribute that is being set it's value (if present) isn't copied to the new version as it gets new value</li>
    * </ol>
    *
    * @param authorId id of a change author
    * @param proposalId id of a proposal
    * @param attributeName name of an attribute
    * @param additionalId additional id for an attribute
    * @param stringValue string value for an attribute
    * @param numericValue numeric value for an attribute
    * @param doubleValue double value for an attribute
    * @return ProposalAttribute that represents newly set attribute
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, long additionalId,
        java.lang.String stringValue, long numericValue, double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.setAttribute(authorId, proposalId,
            attributeName, additionalId, stringValue, numericValue, realValue);
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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName,
        java.lang.String stringValue, long numericValue, double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.setAttribute(authorId, proposalId,
            attributeName, stringValue, numericValue, realValue);
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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, long additionalId,
        java.lang.String stringValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.setAttribute(authorId, proposalId,
            attributeName, additionalId, stringValue);
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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName,
        java.lang.String stringValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.setAttribute(authorId, proposalId,
            attributeName, stringValue);
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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, long additionalId,
        long numericValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.setAttribute(authorId, proposalId,
            attributeName, additionalId, numericValue);
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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, long numericValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.setAttribute(authorId, proposalId,
            attributeName, numericValue);
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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, long additionalId,
        double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.setAttribute(authorId, proposalId,
            attributeName, additionalId, realValue);
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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.setAttribute(authorId, proposalId,
            attributeName, realValue);
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
    public java.util.List<com.ext.portlet.model.ProposalAttribute> getAttributes(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getAttributes(proposalId);
    }

    /**
    * <p>Returns all attributes for given version of a proposal.</p>
    *
    * @param proposalId id of a proposal
    * @param version version number of a proposal
    * @return list of proposal attributes for current version of a proposal
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public java.util.List<com.ext.portlet.model.ProposalAttribute> getAttributes(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getAttributes(proposalId, version);
    }

    /**
    * <p>Returns an attribute for current version of a proposal.</p>
    *
    * @param proposalId id of a proposal
    * @param attributeName name of an attribute
    * @param additionalId additionalId of an attribute
    * @return proposal attribute
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public com.ext.portlet.model.ProposalAttribute getAttribute(
        long proposalId, java.lang.String attributeName, long additionalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getAttribute(proposalId, attributeName,
            additionalId);
    }

    /**
    * <p>Returns an attribute for concrete version of a proposal.</p>
    *
    * @param proposalId id of a proposal
    * @param version version of a proposal
    * @param attributeName name of an attribute
    * @param additionalId additionalId of an attribute
    * @return proposal attribute
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public com.ext.portlet.model.ProposalAttribute getAttribute(
        long proposalId, int version, java.lang.String attributeName,
        long additionalId)
        throws com.ext.portlet.NoSuchProposalAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getAttribute(proposalId, version,
            attributeName, additionalId);
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
    public java.util.List<com.ext.portlet.model.ProposalVersion> getProposalVersions(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getProposalVersions(proposalId);
    }

    /**
    * <p>Returns a concrete proposal version descriptor.</p>
    *
    * @param proposalId id of a proposal
    * @param version version of a proposal
    * @return proposal version descriptor
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    public com.ext.portlet.model.ProposalVersion getProposalVersion(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getProposalVersion(proposalId, version);
    }

    /**
    * <p>Returns a list of proposals associated with given contest phase</p>
    *
    * @param contestPhaseId id of a contest phase
    * @return list of proposals from given contest phase
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public java.util.List<com.ext.portlet.model.Proposal> getProposalsInContestPhase(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getProposalsInContestPhase(contestPhaseId);
    }

    /**
    * <p>Returns list of proposal team members</p>
    *
    * @param proposalId proposal id
    * @return list of proposal team members
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public java.util.List<com.liferay.portal.model.User> getMembers(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getMembers(proposalId);
    }

    /**
    * <p>Returns list of proposal supporters</p>
    *
    * @param proposalId proposal id
    * @return list of proposal supporters
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public java.util.List<com.liferay.portal.model.User> getSupporters(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getSupporters(proposalId);
    }

    /**
    * <p>Returns true if user is a proposal supporter, false otherwise.</p>
    *
    * @param proposalId proposal id
    * @return true if user is a proposal supporter, false otherwise
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public boolean isSupporter(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.isSupporter(proposalId, userId);
    }

    /**
    * <p>Adds supporter to a proposal</p>
    *
    * @param proposalId id of a proposal
    * @param userId id of a supported to be added
    * @throws SystemException in case of an LR error
    */
    public void addSupporter(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalLocalService.addSupporter(proposalId, userId);
    }

    /**
    * <p>Retracts support from a proposal</p>
    *
    * @param proposalId id of a proposal
    * @param userId id of a supported to be removed
    * @throws SystemException in case of an LR error
    */
    public void removeSupporter(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalLocalService.removeSupporter(proposalId, userId);
    }

    /**
    * <p>Returns list of users that have voted for a proposal in given contest phase</p>
    *
    * @param proposalId proposal id
    * @param contestPhaseId contest phase id
    * @return list of proposal voters
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public java.util.List<com.liferay.portal.model.User> getVoters(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getVoters(proposalId, contestPhaseId);
    }

    /**
    * <p>Return number of users that have voted for a proposal in given contest phase</p>
    *
    * @param proposalId proposal id
    * @param contestPhaseId contest phase id
    * @return number of votes
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public long getVotesCount(long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.getVotesCount(proposalId, contestPhaseId);
    }

    /**
    * <p>Adds a user vote to a proposal in context of given contest phase. If user has already voted
    * for different proposal in this phase, then that vote is removed first. User has only one vote
    * in one contestPhase.</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a contest phase
    * @param userId id of an user
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public void addVote(long proposalId, long contestPhaseId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _proposalLocalService.addVote(proposalId, contestPhaseId, userId);
    }

    /**
    * <p>Retracts user vote in context of a contest phase.</p>
    *
    * @param contestPhaseId id of a contest phase
    * @param userId id of an user
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public void removeVote(long contestPhaseId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _proposalLocalService.removeVote(contestPhaseId, userId);
    }

    /**
    * <p>Tells if user is a member of a proposal team</p>
    *
    * @param proposalId id of a proposal
    * @param userId id of an user
    * @return true if user is a member of given proposal team, false otherwise
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public boolean isUserAMember(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.isUserAMember(proposalId, userId);
    }

    /**
    * <p>Returns true if proposal is open (so it can be edited by any user).</p>
    *
    * @param proposalId id of proposal
    * @return true if plan is open, false otherwise
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public boolean isOpen(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalLocalService.isOpen(proposalId);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalLocalService getWrappedProposalLocalService() {
        return _proposalLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalLocalService(
        ProposalLocalService proposalLocalService) {
        _proposalLocalService = proposalLocalService;
    }

    public ProposalLocalService getWrappedService() {
        return _proposalLocalService;
    }

    public void setWrappedService(ProposalLocalService proposalLocalService) {
        _proposalLocalService = proposalLocalService;
    }
}
