package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PointDistributionTarget;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the point distribution target service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointDistributionTargetPersistenceImpl
 * @see PointDistributionTargetUtil
 * @generated
 */
public interface PointDistributionTargetPersistence extends BasePersistence<PointDistributionTarget> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PointDistributionTargetUtil} to access the point distribution target persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the point distribution targets where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the point distribution targets where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of point distribution targets
    * @param end the upper bound of the range of point distribution targets (not inclusive)
    * @return the range of matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the point distribution targets where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of point distribution targets
    * @param end the upper bound of the range of point distribution targets (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first point distribution target in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first point distribution target in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last point distribution target in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last point distribution target in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the point distribution targets before and after the current point distribution target in the ordered set where proposalId = &#63;.
    *
    * @param id the primary key of the current point distribution target
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget[] findByProposalId_PrevAndNext(
        long id, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the point distribution targets where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of point distribution targets where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the point distribution targets where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> findByContestId(
        long contestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the point distribution targets where contestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestId the contest ID
    * @param start the lower bound of the range of point distribution targets
    * @param end the upper bound of the range of point distribution targets (not inclusive)
    * @return the range of matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> findByContestId(
        long contestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the point distribution targets where contestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestId the contest ID
    * @param start the lower bound of the range of point distribution targets
    * @param end the upper bound of the range of point distribution targets (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> findByContestId(
        long contestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first point distribution target in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget findByContestId_First(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first point distribution target in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget fetchByContestId_First(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last point distribution target in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget findByContestId_Last(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last point distribution target in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget fetchByContestId_Last(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the point distribution targets before and after the current point distribution target in the ordered set where contestId = &#63;.
    *
    * @param id the primary key of the current point distribution target
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget[] findByContestId_PrevAndNext(
        long id, long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the point distribution targets where contestId = &#63; from the database.
    *
    * @param contestId the contest ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestId(long contestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of point distribution targets where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the number of matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public int countByContestId(long contestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the point distribution target in the entity cache if it is enabled.
    *
    * @param pointDistributionTarget the point distribution target
    */
    public void cacheResult(
        com.ext.portlet.model.PointDistributionTarget pointDistributionTarget);

    /**
    * Caches the point distribution targets in the entity cache if it is enabled.
    *
    * @param pointDistributionTargets the point distribution targets
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.PointDistributionTarget> pointDistributionTargets);

    /**
    * Creates a new point distribution target with the primary key. Does not add the point distribution target to the database.
    *
    * @param id the primary key for the new point distribution target
    * @return the new point distribution target
    */
    public com.ext.portlet.model.PointDistributionTarget create(long id);

    /**
    * Removes the point distribution target with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the point distribution target
    * @return the point distribution target that was removed
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget remove(long id)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.PointDistributionTarget updateImpl(
        com.ext.portlet.model.PointDistributionTarget pointDistributionTarget)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the point distribution target with the primary key or throws a {@link com.ext.portlet.NoSuchPointDistributionTargetException} if it could not be found.
    *
    * @param id the primary key of the point distribution target
    * @return the point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the point distribution target with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the point distribution target
    * @return the point distribution target, or <code>null</code> if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.PointDistributionTarget fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the point distribution targets.
    *
    * @return the point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the point distribution targets.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of point distribution targets
    * @param end the upper bound of the range of point distribution targets (not inclusive)
    * @return the range of point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the point distribution targets.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.PointDistributionTargetModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of point distribution targets
    * @param end the upper bound of the range of point distribution targets (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.PointDistributionTarget> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the point distribution targets from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of point distribution targets.
    *
    * @return the number of point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
