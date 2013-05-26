package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhase;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contest phase service. This utility wraps {@link ContestPhasePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhasePersistence
 * @see ContestPhasePersistenceImpl
 * @generated
 */
public class ContestPhaseUtil {
    private static ContestPhasePersistence _persistence;

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
    public static void clearCache(ContestPhase contestPhase) {
        getPersistence().clearCache(contestPhase);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ContestPhase> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContestPhase> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContestPhase> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ContestPhase update(ContestPhase contestPhase, boolean merge)
        throws SystemException {
        return getPersistence().update(contestPhase, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ContestPhase update(ContestPhase contestPhase, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(contestPhase, merge, serviceContext);
    }

    /**
    * Caches the contest phase in the entity cache if it is enabled.
    *
    * @param contestPhase the contest phase
    */
    public static void cacheResult(
        com.ext.portlet.model.ContestPhase contestPhase) {
        getPersistence().cacheResult(contestPhase);
    }

    /**
    * Caches the contest phases in the entity cache if it is enabled.
    *
    * @param contestPhases the contest phases
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ContestPhase> contestPhases) {
        getPersistence().cacheResult(contestPhases);
    }

    /**
    * Creates a new contest phase with the primary key. Does not add the contest phase to the database.
    *
    * @param ContestPhasePK the primary key for the new contest phase
    * @return the new contest phase
    */
    public static com.ext.portlet.model.ContestPhase create(long ContestPhasePK) {
        return getPersistence().create(ContestPhasePK);
    }

    /**
    * Removes the contest phase with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase that was removed
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase remove(long ContestPhasePK)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(ContestPhasePK);
    }

    public static com.ext.portlet.model.ContestPhase updateImpl(
        com.ext.portlet.model.ContestPhase contestPhase, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contestPhase, merge);
    }

    /**
    * Returns the contest phase with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseException} if it could not be found.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByPrimaryKey(
        long ContestPhasePK)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ContestPhasePK);
    }

    /**
    * Returns the contest phase with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase, or <code>null</code> if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase fetchByPrimaryKey(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ContestPhasePK);
    }

    /**
    * Returns the contest phase where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; or throws a {@link com.ext.portlet.NoSuchContestPhaseException} if it could not be found.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @return the matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByContestIdStartEnd(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestIdStartEnd(ContestPK, PhaseStartDate,
            PhaseEndDate);
    }

    /**
    * Returns the contest phase where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @return the matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase fetchByContestIdStartEnd(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestIdStartEnd(ContestPK, PhaseStartDate,
            PhaseEndDate);
    }

    /**
    * Returns the contest phase where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase fetchByContestIdStartEnd(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestIdStartEnd(ContestPK, PhaseStartDate,
            PhaseEndDate, retrieveFromCache);
    }

    /**
    * Returns all the contest phases where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByContestId(
        long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestId(ContestPK);
    }

    /**
    * Returns a range of all the contest phases where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByContestId(
        long ContestPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestId(ContestPK, start, end);
    }

    /**
    * Returns an ordered range of all the contest phases where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByContestId(
        long ContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId(ContestPK, start, end, orderByComparator);
    }

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByContestId_First(
        long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId_First(ContestPK, orderByComparator);
    }

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByContestId_Last(
        long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId_Last(ContestPK, orderByComparator);
    }

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase[] findByContestId_PrevAndNext(
        long ContestPhasePK, long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId_PrevAndNext(ContestPhasePK, ContestPK,
            orderByComparator);
    }

    /**
    * Returns all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseActiveOverride(
        long ContestPK, boolean phaseActiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseActiveOverride(ContestPK, phaseActiveOverride);
    }

    /**
    * Returns a range of all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseActiveOverride(
        long ContestPK, boolean phaseActiveOverride, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseActiveOverride(ContestPK, phaseActiveOverride,
            start, end);
    }

    /**
    * Returns an ordered range of all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseActiveOverride(
        long ContestPK, boolean phaseActiveOverride, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseActiveOverride(ContestPK, phaseActiveOverride,
            start, end, orderByComparator);
    }

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByPhaseActiveOverride_First(
        long ContestPK, boolean phaseActiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseActiveOverride_First(ContestPK,
            phaseActiveOverride, orderByComparator);
    }

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByPhaseActiveOverride_Last(
        long ContestPK, boolean phaseActiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseActiveOverride_Last(ContestPK,
            phaseActiveOverride, orderByComparator);
    }

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase[] findByPhaseActiveOverride_PrevAndNext(
        long ContestPhasePK, long ContestPK, boolean phaseActiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseActiveOverride_PrevAndNext(ContestPhasePK,
            ContestPK, phaseActiveOverride, orderByComparator);
    }

    /**
    * Returns all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseInactiveOverride(
        long ContestPK, boolean phaseInactiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseInactiveOverride(ContestPK, phaseInactiveOverride);
    }

    /**
    * Returns a range of all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseInactiveOverride(
        long ContestPK, boolean phaseInactiveOverride, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseInactiveOverride(ContestPK,
            phaseInactiveOverride, start, end);
    }

    /**
    * Returns an ordered range of all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseInactiveOverride(
        long ContestPK, boolean phaseInactiveOverride, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseInactiveOverride(ContestPK,
            phaseInactiveOverride, start, end, orderByComparator);
    }

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByPhaseInactiveOverride_First(
        long ContestPK, boolean phaseInactiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseInactiveOverride_First(ContestPK,
            phaseInactiveOverride, orderByComparator);
    }

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByPhaseInactiveOverride_Last(
        long ContestPK, boolean phaseInactiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseInactiveOverride_Last(ContestPK,
            phaseInactiveOverride, orderByComparator);
    }

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase[] findByPhaseInactiveOverride_PrevAndNext(
        long ContestPhasePK, long ContestPK, boolean phaseInactiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseInactiveOverride_PrevAndNext(ContestPhasePK,
            ContestPK, phaseInactiveOverride, orderByComparator);
    }

    /**
    * Returns all the contest phases where contestPhaseAutopromote = &#63;.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseAutopromote(
        java.lang.String contestPhaseAutopromote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPhaseAutopromote(contestPhaseAutopromote);
    }

    /**
    * Returns a range of all the contest phases where contestPhaseAutopromote = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseAutopromote(
        java.lang.String contestPhaseAutopromote, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseAutopromote(contestPhaseAutopromote, start, end);
    }

    /**
    * Returns an ordered range of all the contest phases where contestPhaseAutopromote = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseAutopromote(
        java.lang.String contestPhaseAutopromote, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseAutopromote(contestPhaseAutopromote, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contest phase in the ordered set where contestPhaseAutopromote = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByPhaseAutopromote_First(
        java.lang.String contestPhaseAutopromote,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseAutopromote_First(contestPhaseAutopromote,
            orderByComparator);
    }

    /**
    * Returns the last contest phase in the ordered set where contestPhaseAutopromote = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase findByPhaseAutopromote_Last(
        java.lang.String contestPhaseAutopromote,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseAutopromote_Last(contestPhaseAutopromote,
            orderByComparator);
    }

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where contestPhaseAutopromote = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhase[] findByPhaseAutopromote_PrevAndNext(
        long ContestPhasePK, java.lang.String contestPhaseAutopromote,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByPhaseAutopromote_PrevAndNext(ContestPhasePK,
            contestPhaseAutopromote, orderByComparator);
    }

    /**
    * Returns all the contest phases.
    *
    * @return the contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contest phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contest phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest phases
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhase> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes the contest phase where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestIdStartEnd(long ContestPK,
        java.util.Date PhaseStartDate, java.util.Date PhaseEndDate)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByContestIdStartEnd(ContestPK, PhaseStartDate, PhaseEndDate);
    }

    /**
    * Removes all the contest phases where ContestPK = &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestId(long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByContestId(ContestPK);
    }

    /**
    * Removes all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPhaseActiveOverride(long ContestPK,
        boolean phaseActiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByPhaseActiveOverride(ContestPK, phaseActiveOverride);
    }

    /**
    * Removes all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPhaseInactiveOverride(long ContestPK,
        boolean phaseInactiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByPhaseInactiveOverride(ContestPK, phaseInactiveOverride);
    }

    /**
    * Removes all the contest phases where contestPhaseAutopromote = &#63; from the database.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @throws SystemException if a system exception occurred
    */
    public static void removeByPhaseAutopromote(
        java.lang.String contestPhaseAutopromote)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByPhaseAutopromote(contestPhaseAutopromote);
    }

    /**
    * Removes all the contest phases from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestIdStartEnd(long ContestPK,
        java.util.Date PhaseStartDate, java.util.Date PhaseEndDate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByContestIdStartEnd(ContestPK, PhaseStartDate,
            PhaseEndDate);
    }

    /**
    * Returns the number of contest phases where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestId(long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestId(ContestPK);
    }

    /**
    * Returns the number of contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static int countByPhaseActiveOverride(long ContestPK,
        boolean phaseActiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByPhaseActiveOverride(ContestPK, phaseActiveOverride);
    }

    /**
    * Returns the number of contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static int countByPhaseInactiveOverride(long ContestPK,
        boolean phaseInactiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByPhaseInactiveOverride(ContestPK,
            phaseInactiveOverride);
    }

    /**
    * Returns the number of contest phases where contestPhaseAutopromote = &#63;.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public static int countByPhaseAutopromote(
        java.lang.String contestPhaseAutopromote)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByPhaseAutopromote(contestPhaseAutopromote);
    }

    /**
    * Returns the number of contest phases.
    *
    * @return the number of contest phases
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContestPhasePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContestPhasePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ContestPhasePersistence.class.getName());

            ReferenceRegistry.registerReference(ContestPhaseUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ContestPhasePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ContestPhaseUtil.class,
            "_persistence");
    }
}
