package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Proposal;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalPersistenceImpl
 * @see ProposalUtil
 * @generated
 */
public interface ProposalPersistence extends BasePersistence<Proposal> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalUtil} to access the proposal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the proposals where updatedDate &ge; &#63;.
    *
    * @param updatedDate the updated date
    * @return the matching proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal> findByModifiedAfter(
        java.util.Date updatedDate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposals where updatedDate &ge; &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param updatedDate the updated date
    * @param start the lower bound of the range of proposals
    * @param end the upper bound of the range of proposals (not inclusive)
    * @return the range of matching proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal> findByModifiedAfter(
        java.util.Date updatedDate, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposals where updatedDate &ge; &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param updatedDate the updated date
    * @param start the lower bound of the range of proposals
    * @param end the upper bound of the range of proposals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal> findByModifiedAfter(
        java.util.Date updatedDate, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal in the ordered set where updatedDate &ge; &#63;.
    *
    * @param updatedDate the updated date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal
    * @throws com.ext.portlet.NoSuchProposalException if a matching proposal could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal findByModifiedAfter_First(
        java.util.Date updatedDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal in the ordered set where updatedDate &ge; &#63;.
    *
    * @param updatedDate the updated date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal, or <code>null</code> if a matching proposal could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal fetchByModifiedAfter_First(
        java.util.Date updatedDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal in the ordered set where updatedDate &ge; &#63;.
    *
    * @param updatedDate the updated date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal
    * @throws com.ext.portlet.NoSuchProposalException if a matching proposal could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal findByModifiedAfter_Last(
        java.util.Date updatedDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal in the ordered set where updatedDate &ge; &#63;.
    *
    * @param updatedDate the updated date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal, or <code>null</code> if a matching proposal could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal fetchByModifiedAfter_Last(
        java.util.Date updatedDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposals before and after the current proposal in the ordered set where updatedDate &ge; &#63;.
    *
    * @param proposalId the primary key of the current proposal
    * @param updatedDate the updated date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal
    * @throws com.ext.portlet.NoSuchProposalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal[] findByModifiedAfter_PrevAndNext(
        long proposalId, java.util.Date updatedDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposals where updatedDate &ge; &#63; from the database.
    *
    * @param updatedDate the updated date
    * @throws SystemException if a system exception occurred
    */
    public void removeByModifiedAfter(java.util.Date updatedDate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposals where updatedDate &ge; &#63;.
    *
    * @param updatedDate the updated date
    * @return the number of matching proposals
    * @throws SystemException if a system exception occurred
    */
    public int countByModifiedAfter(java.util.Date updatedDate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the proposal in the entity cache if it is enabled.
    *
    * @param proposal the proposal
    */
    public void cacheResult(com.ext.portlet.model.Proposal proposal);

    /**
    * Caches the proposals in the entity cache if it is enabled.
    *
    * @param proposals the proposals
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.Proposal> proposals);

    /**
    * Creates a new proposal with the primary key. Does not add the proposal to the database.
    *
    * @param proposalId the primary key for the new proposal
    * @return the new proposal
    */
    public com.ext.portlet.model.Proposal create(long proposalId);

    /**
    * Removes the proposal with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalId the primary key of the proposal
    * @return the proposal that was removed
    * @throws com.ext.portlet.NoSuchProposalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal remove(long proposalId)
        throws com.ext.portlet.NoSuchProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.Proposal updateImpl(
        com.ext.portlet.model.Proposal proposal)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal with the primary key or throws a {@link com.ext.portlet.NoSuchProposalException} if it could not be found.
    *
    * @param proposalId the primary key of the proposal
    * @return the proposal
    * @throws com.ext.portlet.NoSuchProposalException if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal findByPrimaryKey(long proposalId)
        throws com.ext.portlet.NoSuchProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposalId the primary key of the proposal
    * @return the proposal, or <code>null</code> if a proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal fetchByPrimaryKey(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposals.
    *
    * @return the proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.Proposal> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposals
    * @param end the upper bound of the range of proposals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposals.
    *
    * @return the number of proposals
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
