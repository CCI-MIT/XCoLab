package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalSupporter;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal supporter service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalSupporterPersistenceImpl
 * @see ProposalSupporterUtil
 * @generated
 */
public interface ProposalSupporterPersistence extends BasePersistence<ProposalSupporter> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalSupporterUtil} to access the proposal supporter persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the proposal supporters where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalSupporter> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal supporters where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @return the range of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalSupporter> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal supporters where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalSupporter> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal supporter, or <code>null</code> if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal supporter, or <code>null</code> if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal supporters before and after the current proposal supporter in the ordered set where proposalId = &#63;.
    *
    * @param proposalSupporterPK the primary key of the current proposal supporter
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter[] findByProposalId_PrevAndNext(
        ProposalSupporterPK proposalSupporterPK, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal supporters where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal supporters where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal supporters where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalSupporter> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal supporters where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @return the range of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalSupporter> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal supporters where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalSupporter> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal supporter in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter findByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal supporter in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal supporter, or <code>null</code> if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter fetchByUserId_First(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal supporter in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter findByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal supporter in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal supporter, or <code>null</code> if a matching proposal supporter could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter fetchByUserId_Last(
        long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal supporters before and after the current proposal supporter in the ordered set where userId = &#63;.
    *
    * @param proposalSupporterPK the primary key of the current proposal supporter
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter[] findByUserId_PrevAndNext(
        ProposalSupporterPK proposalSupporterPK, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal supporters where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal supporters where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the proposal supporter in the entity cache if it is enabled.
    *
    * @param proposalSupporter the proposal supporter
    */
    public void cacheResult(
        com.ext.portlet.model.ProposalSupporter proposalSupporter);

    /**
    * Caches the proposal supporters in the entity cache if it is enabled.
    *
    * @param proposalSupporters the proposal supporters
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalSupporter> proposalSupporters);

    /**
    * Creates a new proposal supporter with the primary key. Does not add the proposal supporter to the database.
    *
    * @param proposalSupporterPK the primary key for the new proposal supporter
    * @return the new proposal supporter
    */
    public com.ext.portlet.model.ProposalSupporter create(
        ProposalSupporterPK proposalSupporterPK);

    /**
    * Removes the proposal supporter with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalSupporterPK the primary key of the proposal supporter
    * @return the proposal supporter that was removed
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter remove(
        ProposalSupporterPK proposalSupporterPK)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalSupporter updateImpl(
        com.ext.portlet.model.ProposalSupporter proposalSupporter)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal supporter with the primary key or throws a {@link com.ext.portlet.NoSuchProposalSupporterException} if it could not be found.
    *
    * @param proposalSupporterPK the primary key of the proposal supporter
    * @return the proposal supporter
    * @throws com.ext.portlet.NoSuchProposalSupporterException if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter findByPrimaryKey(
        ProposalSupporterPK proposalSupporterPK)
        throws com.ext.portlet.NoSuchProposalSupporterException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal supporter with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposalSupporterPK the primary key of the proposal supporter
    * @return the proposal supporter, or <code>null</code> if a proposal supporter with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalSupporter fetchByPrimaryKey(
        ProposalSupporterPK proposalSupporterPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal supporters.
    *
    * @return the proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalSupporter> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal supporters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @return the range of proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalSupporter> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal supporters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalSupporterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal supporters
    * @param end the upper bound of the range of proposal supporters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalSupporter> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal supporters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal supporters.
    *
    * @return the number of proposal supporters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
