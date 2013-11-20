package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Plan2Proposal;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the plan2 proposal service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Plan2ProposalPersistenceImpl
 * @see Plan2ProposalUtil
 * @generated
 */
public interface Plan2ProposalPersistence extends BasePersistence<Plan2Proposal> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link Plan2ProposalUtil} to access the plan2 proposal persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the plan2 proposals where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Plan2Proposal> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan2 proposals where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Plan2ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of plan2 proposals
    * @param end the upper bound of the range of plan2 proposals (not inclusive)
    * @return the range of matching plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Plan2Proposal> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan2 proposals where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Plan2ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of plan2 proposals
    * @param end the upper bound of the range of plan2 proposals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Plan2Proposal> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan2 proposal in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan2 proposal
    * @throws com.ext.portlet.NoSuchPlan2ProposalException if a matching plan2 proposal could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Plan2Proposal findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlan2ProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first plan2 proposal in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching plan2 proposal, or <code>null</code> if a matching plan2 proposal could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Plan2Proposal fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan2 proposal in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan2 proposal
    * @throws com.ext.portlet.NoSuchPlan2ProposalException if a matching plan2 proposal could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Plan2Proposal findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlan2ProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last plan2 proposal in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching plan2 proposal, or <code>null</code> if a matching plan2 proposal could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Plan2Proposal fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan2 proposals before and after the current plan2 proposal in the ordered set where proposalId = &#63;.
    *
    * @param planId the primary key of the current plan2 proposal
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next plan2 proposal
    * @throws com.ext.portlet.NoSuchPlan2ProposalException if a plan2 proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Plan2Proposal[] findByProposalId_PrevAndNext(
        long planId, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPlan2ProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan2 proposals where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan2 proposals where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the plan2 proposal in the entity cache if it is enabled.
    *
    * @param plan2Proposal the plan2 proposal
    */
    public void cacheResult(com.ext.portlet.model.Plan2Proposal plan2Proposal);

    /**
    * Caches the plan2 proposals in the entity cache if it is enabled.
    *
    * @param plan2Proposals the plan2 proposals
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.Plan2Proposal> plan2Proposals);

    /**
    * Creates a new plan2 proposal with the primary key. Does not add the plan2 proposal to the database.
    *
    * @param planId the primary key for the new plan2 proposal
    * @return the new plan2 proposal
    */
    public com.ext.portlet.model.Plan2Proposal create(long planId);

    /**
    * Removes the plan2 proposal with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param planId the primary key of the plan2 proposal
    * @return the plan2 proposal that was removed
    * @throws com.ext.portlet.NoSuchPlan2ProposalException if a plan2 proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Plan2Proposal remove(long planId)
        throws com.ext.portlet.NoSuchPlan2ProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.Plan2Proposal updateImpl(
        com.ext.portlet.model.Plan2Proposal plan2Proposal)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan2 proposal with the primary key or throws a {@link com.ext.portlet.NoSuchPlan2ProposalException} if it could not be found.
    *
    * @param planId the primary key of the plan2 proposal
    * @return the plan2 proposal
    * @throws com.ext.portlet.NoSuchPlan2ProposalException if a plan2 proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Plan2Proposal findByPrimaryKey(long planId)
        throws com.ext.portlet.NoSuchPlan2ProposalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the plan2 proposal with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param planId the primary key of the plan2 proposal
    * @return the plan2 proposal, or <code>null</code> if a plan2 proposal with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Plan2Proposal fetchByPrimaryKey(long planId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the plan2 proposals.
    *
    * @return the plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Plan2Proposal> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the plan2 proposals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Plan2ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan2 proposals
    * @param end the upper bound of the range of plan2 proposals (not inclusive)
    * @return the range of plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Plan2Proposal> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the plan2 proposals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Plan2ProposalModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of plan2 proposals
    * @param end the upper bound of the range of plan2 proposals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Plan2Proposal> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the plan2 proposals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of plan2 proposals.
    *
    * @return the number of plan2 proposals
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
