package com.ext.portlet.service.persistence;

import com.ext.portlet.model.PointDistributionTarget;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the point distribution target service. This utility wraps {@link PointDistributionTargetPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see PointDistributionTargetPersistence
 * @see PointDistributionTargetPersistenceImpl
 * @generated
 */
public class PointDistributionTargetUtil {
    private static PointDistributionTargetPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(
        PointDistributionTarget pointDistributionTarget) {
        getPersistence().clearCache(pointDistributionTarget);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<PointDistributionTarget> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<PointDistributionTarget> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<PointDistributionTarget> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static PointDistributionTarget update(
        PointDistributionTarget pointDistributionTarget)
        throws SystemException {
        return getPersistence().update(pointDistributionTarget);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static PointDistributionTarget update(
        PointDistributionTarget pointDistributionTarget,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(pointDistributionTarget, serviceContext);
    }

    /**
    * Returns all the point distribution targets where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointDistributionTarget> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId);
    }

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
    public static java.util.List<com.ext.portlet.model.PointDistributionTarget> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByProposalId(proposalId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.PointDistributionTarget> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId(proposalId, start, end, orderByComparator);
    }

    /**
    * Returns the first point distribution target in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the first point distribution target in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_First(proposalId, orderByComparator);
    }

    /**
    * Returns the last point distribution target in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_Last(proposalId, orderByComparator);
    }

    /**
    * Returns the last point distribution target in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByProposalId_Last(proposalId, orderByComparator);
    }

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
    public static com.ext.portlet.model.PointDistributionTarget[] findByProposalId_PrevAndNext(
        long id, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByProposalId_PrevAndNext(id, proposalId,
            orderByComparator);
    }

    /**
    * Removes all the point distribution targets where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByProposalId(proposalId);
    }

    /**
    * Returns the number of point distribution targets where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public static int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByProposalId(proposalId);
    }

    /**
    * Returns all the point distribution targets where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointDistributionTarget> findByContestId(
        long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestId(contestId);
    }

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
    public static java.util.List<com.ext.portlet.model.PointDistributionTarget> findByContestId(
        long contestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestId(contestId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.PointDistributionTarget> findByContestId(
        long contestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId(contestId, start, end, orderByComparator);
    }

    /**
    * Returns the first point distribution target in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget findByContestId_First(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId_First(contestId, orderByComparator);
    }

    /**
    * Returns the first point distribution target in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget fetchByContestId_First(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestId_First(contestId, orderByComparator);
    }

    /**
    * Returns the last point distribution target in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget findByContestId_Last(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId_Last(contestId, orderByComparator);
    }

    /**
    * Returns the last point distribution target in the ordered set where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching point distribution target, or <code>null</code> if a matching point distribution target could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget fetchByContestId_Last(
        long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestId_Last(contestId, orderByComparator);
    }

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
    public static com.ext.portlet.model.PointDistributionTarget[] findByContestId_PrevAndNext(
        long id, long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId_PrevAndNext(id, contestId, orderByComparator);
    }

    /**
    * Removes all the point distribution targets where contestId = &#63; from the database.
    *
    * @param contestId the contest ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestId(long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByContestId(contestId);
    }

    /**
    * Returns the number of point distribution targets where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the number of matching point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestId(long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestId(contestId);
    }

    /**
    * Caches the point distribution target in the entity cache if it is enabled.
    *
    * @param pointDistributionTarget the point distribution target
    */
    public static void cacheResult(
        com.ext.portlet.model.PointDistributionTarget pointDistributionTarget) {
        getPersistence().cacheResult(pointDistributionTarget);
    }

    /**
    * Caches the point distribution targets in the entity cache if it is enabled.
    *
    * @param pointDistributionTargets the point distribution targets
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.PointDistributionTarget> pointDistributionTargets) {
        getPersistence().cacheResult(pointDistributionTargets);
    }

    /**
    * Creates a new point distribution target with the primary key. Does not add the point distribution target to the database.
    *
    * @param id the primary key for the new point distribution target
    * @return the new point distribution target
    */
    public static com.ext.portlet.model.PointDistributionTarget create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the point distribution target with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the point distribution target
    * @return the point distribution target that was removed
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget remove(long id)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.PointDistributionTarget updateImpl(
        com.ext.portlet.model.PointDistributionTarget pointDistributionTarget)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(pointDistributionTarget);
    }

    /**
    * Returns the point distribution target with the primary key or throws a {@link com.ext.portlet.NoSuchPointDistributionTargetException} if it could not be found.
    *
    * @param id the primary key of the point distribution target
    * @return the point distribution target
    * @throws com.ext.portlet.NoSuchPointDistributionTargetException if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchPointDistributionTargetException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the point distribution target with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the point distribution target
    * @return the point distribution target, or <code>null</code> if a point distribution target with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.PointDistributionTarget fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the point distribution targets.
    *
    * @return the point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.PointDistributionTarget> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.ext.portlet.model.PointDistributionTarget> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.PointDistributionTarget> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the point distribution targets from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of point distribution targets.
    *
    * @return the number of point distribution targets
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static PointDistributionTargetPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (PointDistributionTargetPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    PointDistributionTargetPersistence.class.getName());

            ReferenceRegistry.registerReference(PointDistributionTargetUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(PointDistributionTargetPersistence persistence) {
    }
}
