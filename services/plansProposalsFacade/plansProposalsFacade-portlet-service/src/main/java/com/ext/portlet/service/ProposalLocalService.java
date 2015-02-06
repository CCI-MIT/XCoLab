package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * Provides the local service interface for Proposal. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalLocalServiceUtil
 * @see com.ext.portlet.service.base.ProposalLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ProposalLocalService extends BaseLocalService,
    InvokableLocalService, PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalLocalServiceUtil} to access the proposal local service. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the proposal to the database. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @return the proposal that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal addProposal(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new proposal with the primary key. Does not add the proposal to the database.
    *
    * @param proposalId the primary key for the new proposal
    * @return the new proposal
    */
    public com.ext.portlet.model.Proposal createProposal(long proposalId);

    /**
    * Deletes the proposal with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalId the primary key of the proposal
    * @return the proposal that was removed
    * @throws PortalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal deleteProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the proposal from the database. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @return the proposal that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal deleteProposal(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException;

    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery();

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.Proposal fetchProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal with the primary key.
    *
    * @param proposalId the primary key of the proposal
    * @return the proposal
    * @throws PortalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.Proposal getProposal(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposals
    * @param end the upper bound of the range of proposals (not inclusive)
    * @return the range of proposals
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.Proposal> getProposals(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposals.
    *
    * @return the number of proposals
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getProposalsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the proposal in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposal the proposal
    * @return the proposal that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal updateProposal(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

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
    public com.ext.portlet.model.Proposal create(long authorId,
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.Proposal create(long authorId,
        long contestPhaseId, long proposalId, boolean publishActivity)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    public void setVisibility(java.lang.Long proposalId,
        java.lang.Boolean visibility, java.lang.Long authorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, long additionalId,
        java.lang.String stringValue, long numericValue, double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, long additionalId,
        java.lang.String stringValue, long numericValue, double realValue,
        java.util.Date updatedDate, boolean publishActivity)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName,
        java.lang.String stringValue, long numericValue, double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName,
        java.lang.String stringValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, long numericValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
            com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.ProposalAttribute setAttribute(long authorId,
        long proposalId, java.lang.String attributeName, double realValue)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns all attributes for current version of a proposal.</p>
    *
    * @param proposalId id of a proposal
    * @return list of proposal attributes for current version of a proposal
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.ProposalAttribute> getAttributes(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.ProposalAttribute> getAttributes(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.ProposalAttribute getAttribute(
        long proposalId, java.lang.String attributeName, long additionalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.ProposalAttribute getAttribute(
        long proposalId, int version, java.lang.String attributeName,
        long additionalId)
        throws com.ext.portlet.NoSuchProposalAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns a list of all proposal version descriptors.</p>
    *
    * @param proposalId id of a proposal
    * @return list of proposal versions covering entire change history for a proposal
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    * @author janusz
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.ProposalVersion> getProposalVersions(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.ProposalVersion getProposalVersion(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns a list of proposals associated with given contest phase</p>
    *
    * @param contestPhaseId id of a contest phase
    * @return list of proposals from given contest phase
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.Proposal> getProposalsInContestPhase(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.Proposal> getProposalsInContestPhase(
        long contestPhaseId, java.lang.String sortProperty,
        boolean sortAscending, int start, int end)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns a list of proposals associated with the given contest phase which are both generally visible and visible in the given contest phase</p>
    *
    * @param contestPhaseId id of a contest phase
    * @return list of proposals from given contest phase
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.Proposal> getActiveProposalsInContestPhase(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns a list of proposals associated with given contest</p>
    *
    * @param contestId id of a contest phase
    * @return list of proposals from given contest
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.Proposal> getProposalsInContest(
        long contestId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Retrieves all proposals for which a user is either the author or member of the author group (proposals to which a user has contributed)
    *
    * @param userId The userId of the user
    * @return A list of proposals the user has contributed to
    * @throws SystemException
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.Proposal> getUserProposals(
        long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns count of proposals associated with given contest phase</p>
    *
    * @param contestPhaseId id of a contest phase
    * @return count of proposals from given contest phase
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public long countProposalsInContestPhase(long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns list of proposal team members</p>
    *
    * @param proposalId proposal id
    * @return list of proposal team members
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getMembers(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns list of proposal supporters</p>
    *
    * @param proposalId proposal id
    * @return list of proposal supporters
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getSupporters(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns number of proposal supporters</p>
    *
    * @param proposalId proposal id
    * @return number of proposal supporters
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getSupportersCount(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns true if user is a proposal supporter, false otherwise.</p>
    *
    * @param proposalId proposal id
    * @return true if user is a proposal supporter, false otherwise
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isSupporter(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Adds supporter to a proposal</p>
    *
    * @param proposalId id of a proposal
    * @param userId     id of a supported to be added
    * @throws SystemException in case of an LR error
    * @throws PortalException
    */
    public void addSupporter(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Adds supporter to a proposal</p>
    *
    * @param proposalId id of a proposal
    * @param userId     id of a supported to be added
    * @throws SystemException in case of an LR error
    * @throws PortalException
    */
    public void addSupporter(long proposalId, long userId,
        boolean publishActivity)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Retracts support from a proposal</p>
    *
    * @param proposalId id of a proposal
    * @param userId     id of a supported to be removed
    * @throws SystemException in case of an LR error
    * @throws PortalException
    */
    public void removeSupporter(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns list of users that have voted for a proposal in given contest phase</p>
    *
    * @param proposalId     proposal id
    * @param contestPhaseId contest phase id
    * @return list of proposal voters
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.User> getVoters(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Return number of users that have voted for a proposal in given contest phase</p>
    *
    * @param proposalId     proposal id
    * @param contestPhaseId contest phase id
    * @return number of votes
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getVotesCount(long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public void addVote(long proposalId, long contestPhaseId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public void addVote(long proposalId, long contestPhaseId, long userId,
        boolean publishActivity)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Retracts user vote in context of a contest phase.</p>
    *
    * @param contestPhaseId id of a contest phase
    * @param userId         id of an user
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    public void removeVote(long contestPhaseId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns number of comments in discussion associated with this proposal</p>
    *
    * @param proposalId proposal id
    * @return number of comments
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public long getCommentsCount(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Tells if user is a member of a proposal team</p>
    *
    * @param proposalId id of a proposal
    * @param userId     id of an user
    * @return true if user is a member of given proposal team, false otherwise
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isUserAMember(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns true if proposal is open (so it can be edited by any user).</p>
    *
    * @param proposalId id of proposal
    * @return true if plan is open, false otherwise
    * @throws PortalException in case of an LR error
    * @throws SystemException in case of an LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isOpen(long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns all team membership requests for a proposal.</p>
    *
    * @param proposalId proposal id
    * @return list of membership requests
    * @throws SystemException in case of LR error
    * @throws PortalException in case of LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.liferay.portal.model.MembershipRequest> getMembershipRequests(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Sends a request to join proposal team</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @param comment    optional comment
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public com.liferay.portal.model.MembershipRequest addMembershipRequest(
        long proposalId, long userId, java.lang.String comment)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Remove a user from a proposal team</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public void removeUserFromTeam(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Denies user as a member of proposal team</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public void dennyMembershipRequest(long proposalId, long userId,
        long membershipRequestId, java.lang.String reply, long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Approves user as a member of proposal team</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public void approveMembershipRequest(long proposalId,
        java.lang.Long userId,
        com.liferay.portal.model.MembershipRequest request,
        java.lang.String reply, java.lang.Long updateAuthorId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Tells if user has requested membership of given plan</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @return true if user has requested membership, false otherwise
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserRequestedMembership(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Adds user to a proposal team if proposal is open and user is not a member already</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public void joinIfNotAMemberAndProposalIsOpen(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns true if user is subscribed to given proposal</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @return true if user has subscribed to a proposal, false otherwise
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean isSubscribed(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Subscribes user to a proposal</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public void subscribe(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public void subscribe(long proposalId, long userId, boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Unsubscribes user from given proposal</p>
    *
    * @param proposalId proposal id
    * @param userId     user id
    * @throws PortalException in case of LR error
    * @throws SystemException in case of LR error
    */
    public void unsubscribe(long proposalId, long userId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

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
    public void unsubscribe(long proposalId, long userId, boolean automatic)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns true if user has voted for given proposal in context of a contest phase</p>
    *
    * @param proposalId     proposal id
    * @param contestPhaseId contest phase id
    * @param userId         user id
    * @return true if user has voted for proposal in context of a contest phase
    * @throws SystemException
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public boolean hasUserVoted(long proposalId, long contestPhaseId,
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns number of proposals that user supports
    *
    * @param userId
    * @return
    * @throws SystemException
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserSupportedProposalsCount(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns number of proposals that user has given his vote to
    *
    * @param userId
    * @return
    * @throws SystemException
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserVotedProposalsCount(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.Proposal> getModifiedAfter(
        java.util.Date date)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Sends out the judges' review about the proposal's advance decision as a CoLab message notification to all proposal
    * contributers
    *
    * @param proposal      The proposal for which the notification should be sent
    * @param contestPhase  The contestPhase in which the proposal is in
    * @param request       A PortletRequest object to extract the Portal's base URL (may be null - choose default portal URL in that case)
    */
    public void contestPhasePromotionEmailNotifyProposalContributors(
        com.ext.portlet.model.Proposal proposal,
        com.ext.portlet.model.ContestPhase contestPhase,
        javax.portlet.PortletRequest request)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException,
            com.liferay.util.mail.MailEngineException,
            javax.mail.internet.AddressException;

    /**
    * Posts the judges' review about the proposal's advance decision on the proposal's comment thread
    *
    * @param proposal  The proposal for which the notification should be sent
    */
    public void contestPhasePromotionCommentNotifyProposalContributors(
        com.ext.portlet.model.Proposal proposal,
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.Long getDiscussionIdAndGenerateIfNull(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the URL link address for the passed proposal and contest
    *
    * @param contest  The contest object in which the proposal was written
    * @param proposal The proposal object (must not be null)
    * @return Proposal URL as String
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getProposalLinkUrl(
        com.ext.portlet.model.Contest contest,
        com.ext.portlet.model.Proposal proposal);

    /**
    * Returns the URL link address for the passed proposal, contest and contestPhase
    *
    * @param contest       The contest object in which the proposal was written
    * @param proposal      The proposal object
    * @param contestPhase  The associated contestPhase of the proposal
    * @return Proposal URL as String
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getProposalLinkUrl(
        com.ext.portlet.model.Contest contest,
        com.ext.portlet.model.Proposal proposal,
        com.ext.portlet.model.ContestPhase contestPhase);

    /**
    * Returns list of proposals referenced by given proposal
    *
    * @param proposalId      The proposal for which subproposals should be returned
    * @return collection of referenced proposals
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.Proposal> getSubproposals(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns latest contest phase to which proposal was submited
    *
    * @param proposalId id of a proposal
    * @return last contest phase to which proposal was submited
    * @throws PortalException
    * @throws SystemException
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.ContestPhase getLatestProposalContestPhase(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns latest contest to which proposal was submited
    *
    * @param proposalId id of a proposal
    * @return last contest to which proposal was submited
    * @throws PortalException
    * @throws SystemException
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.Contest getLatestProposalContest(
        long proposalId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}
