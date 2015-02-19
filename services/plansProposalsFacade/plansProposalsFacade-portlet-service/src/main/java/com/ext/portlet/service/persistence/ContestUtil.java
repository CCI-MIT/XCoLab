package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Contest;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contest service. This utility wraps {@link ContestPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPersistence
 * @see ContestPersistenceImpl
 * @generated
 */
public class ContestUtil {
    private static ContestPersistence _persistence;

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
    public static void clearCache(Contest contest) {
        getPersistence().clearCache(contest);
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
    public static List<Contest> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<Contest> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<Contest> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static Contest update(Contest contest) throws SystemException {
        return getPersistence().update(contest);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static Contest update(Contest contest, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(contest, serviceContext);
    }

    /**
    * Returns all the contests where PlanTypeId = &#63;.
    *
    * @param PlanTypeId the plan type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByType(
        long PlanTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByType(PlanTypeId);
    }

    /**
    * Returns a range of all the contests where PlanTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param PlanTypeId the plan type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByType(
        long PlanTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByType(PlanTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the contests where PlanTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param PlanTypeId the plan type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByType(
        long PlanTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByType(PlanTypeId, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where PlanTypeId = &#63;.
    *
    * @param PlanTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByType_First(
        long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByType_First(PlanTypeId, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where PlanTypeId = &#63;.
    *
    * @param PlanTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByType_First(
        long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByType_First(PlanTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where PlanTypeId = &#63;.
    *
    * @param PlanTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByType_Last(
        long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByType_Last(PlanTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where PlanTypeId = &#63;.
    *
    * @param PlanTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByType_Last(
        long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByType_Last(PlanTypeId, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where PlanTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param PlanTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByType_PrevAndNext(
        long ContestPK, long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByType_PrevAndNext(ContestPK, PlanTypeId,
            orderByComparator);
    }

    /**
    * Removes all the contests where PlanTypeId = &#63; from the database.
    *
    * @param PlanTypeId the plan type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByType(long PlanTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByType(PlanTypeId);
    }

    /**
    * Returns the number of contests where PlanTypeId = &#63;.
    *
    * @param PlanTypeId the plan type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByType(long PlanTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByType(PlanTypeId);
    }

    /**
    * Returns all the contests where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestTier(
        long contestTier)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestTier(contestTier);
    }

    /**
    * Returns a range of all the contests where contestTier = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTier the contest tier
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestTier(
        long contestTier, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestTier(contestTier, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestTier = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTier the contest tier
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestTier(
        long contestTier, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestTier(contestTier, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestTier_First(
        long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestTier_First(contestTier, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestTier_First(
        long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestTier_First(contestTier, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestTier_Last(
        long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestTier_Last(contestTier, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestTier_Last(
        long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestTier_Last(contestTier, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestTier = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByContestTier_PrevAndNext(
        long ContestPK, long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestTier_PrevAndNext(ContestPK, contestTier,
            orderByComparator);
    }

    /**
    * Removes all the contests where contestTier = &#63; from the database.
    *
    * @param contestTier the contest tier
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestTier(long contestTier)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByContestTier(contestTier);
    }

    /**
    * Returns the number of contests where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestTier(long contestTier)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestTier(contestTier);
    }

    /**
    * Returns all the contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestActivecontestPrivate(
        boolean contestActive, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActivecontestPrivate(contestActive,
            contestPrivate);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestActivecontestPrivate(
        boolean contestActive, boolean contestPrivate, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActivecontestPrivate(contestActive,
            contestPrivate, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestActivecontestPrivate(
        boolean contestActive, boolean contestPrivate, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActivecontestPrivate(contestActive,
            contestPrivate, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestActivecontestPrivate_First(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActivecontestPrivate_First(contestActive,
            contestPrivate, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestActivecontestPrivate_First(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestActivecontestPrivate_First(contestActive,
            contestPrivate, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestActivecontestPrivate_Last(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActivecontestPrivate_Last(contestActive,
            contestPrivate, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestActivecontestPrivate_Last(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestActivecontestPrivate_Last(contestActive,
            contestPrivate, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByContestActivecontestPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActivecontestPrivate_PrevAndNext(ContestPK,
            contestActive, contestPrivate, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and contestPrivate = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestActivecontestPrivate(
        boolean contestActive, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByContestActivecontestPrivate(contestActive, contestPrivate);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestActivecontestPrivate(
        boolean contestActive, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByContestActivecontestPrivate(contestActive,
            contestPrivate);
    }

    /**
    * Returns all the contests where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestActive(
        boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestActive(contestActive);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestActive(
        boolean contestActive, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestActive(contestActive, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestActive(
        boolean contestActive, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActive(contestActive, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestActive_First(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActive_First(contestActive, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestActive_First(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestActive_First(contestActive, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestActive_Last(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActive_Last(contestActive, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestActive_Last(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestActive_Last(contestActive, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByContestActive_PrevAndNext(
        long ContestPK, boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestActive_PrevAndNext(ContestPK, contestActive,
            orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; from the database.
    *
    * @param contestActive the contest active
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestActive(boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByContestActive(contestActive);
    }

    /**
    * Returns the number of contests where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestActive(boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestActive(contestActive);
    }

    /**
    * Returns all the contests where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeatured(
        boolean contestActive, boolean featured)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByActiveFeatured(contestActive, featured);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and featured = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeatured(
        boolean contestActive, boolean featured, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeatured(contestActive, featured, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeatured(
        boolean contestActive, boolean featured, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeatured(contestActive, featured, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFeatured_First(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeatured_First(contestActive, featured,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFeatured_First(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeatured_First(contestActive, featured,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFeatured_Last(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeatured_Last(contestActive, featured,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFeatured_Last(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeatured_Last(contestActive, featured,
            orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFeatured_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeatured_PrevAndNext(ContestPK, contestActive,
            featured, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and featured = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFeatured(boolean contestActive,
        boolean featured)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByActiveFeatured(contestActive, featured);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFeatured(boolean contestActive,
        boolean featured)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByActiveFeatured(contestActive, featured);
    }

    /**
    * Returns all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedcontestPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedcontestPrivate(contestActive, featured,
            contestPrivate);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedcontestPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedcontestPrivate(contestActive, featured,
            contestPrivate, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedcontestPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedcontestPrivate(contestActive, featured,
            contestPrivate, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFeaturedcontestPrivate_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedcontestPrivate_First(contestActive,
            featured, contestPrivate, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFeaturedcontestPrivate_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeaturedcontestPrivate_First(contestActive,
            featured, contestPrivate, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFeaturedcontestPrivate_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedcontestPrivate_Last(contestActive,
            featured, contestPrivate, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFeaturedcontestPrivate_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeaturedcontestPrivate_Last(contestActive,
            featured, contestPrivate, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFeaturedcontestPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedcontestPrivate_PrevAndNext(ContestPK,
            contestActive, featured, contestPrivate, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFeaturedcontestPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActiveFeaturedcontestPrivate(contestActive, featured,
            contestPrivate);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFeaturedcontestPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActiveFeaturedcontestPrivate(contestActive,
            featured, contestPrivate);
    }

    /**
    * Returns all the contests where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlag(
        boolean contestActive, int flag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByActiveFlag(contestActive, flag);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and flag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlag(
        boolean contestActive, int flag, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByActiveFlag(contestActive, flag, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlag(
        boolean contestActive, int flag, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlag(contestActive, flag, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlag_First(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlag_First(contestActive, flag,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlag_First(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlag_First(contestActive, flag,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlag_Last(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlag_Last(contestActive, flag, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlag_Last(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlag_Last(contestActive, flag,
            orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFlag_PrevAndNext(
        long ContestPK, boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlag_PrevAndNext(ContestPK, contestActive,
            flag, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and flag = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFlag(boolean contestActive, int flag)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByActiveFlag(contestActive, flag);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFlag(boolean contestActive, int flag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByActiveFlag(contestActive, flag);
    }

    /**
    * Returns all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagContest(
        boolean contestActive, int flag, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagContest(contestActive, flag, contestPrivate);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagContest(
        boolean contestActive, int flag, boolean contestPrivate, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagContest(contestActive, flag,
            contestPrivate, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagContest(
        boolean contestActive, int flag, boolean contestPrivate, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagContest(contestActive, flag,
            contestPrivate, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagContest_First(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagContest_First(contestActive, flag,
            contestPrivate, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagContest_First(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagContest_First(contestActive, flag,
            contestPrivate, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagContest_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagContest_Last(contestActive, flag,
            contestPrivate, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagContest_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagContest_Last(contestActive, flag,
            contestPrivate, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFlagContest_PrevAndNext(
        long ContestPK, boolean contestActive, int flag,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagContest_PrevAndNext(ContestPK,
            contestActive, flag, contestPrivate, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFlagContest(boolean contestActive,
        int flag, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActiveFlagContest(contestActive, flag, contestPrivate);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFlagContest(boolean contestActive, int flag,
        boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActiveFlagContest(contestActive, flag, contestPrivate);
    }

    /**
    * Returns all the contests where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagText(
        boolean contestActive, java.lang.String flagText)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByActiveFlagText(contestActive, flagText);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and flagText = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagText(
        boolean contestActive, java.lang.String flagText, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagText(contestActive, flagText, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagText(
        boolean contestActive, java.lang.String flagText, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagText(contestActive, flagText, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagText_First(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagText_First(contestActive, flagText,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagText_First(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagText_First(contestActive, flagText,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagText_Last(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagText_Last(contestActive, flagText,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagText_Last(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagText_Last(contestActive, flagText,
            orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFlagText_PrevAndNext(
        long ContestPK, boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagText_PrevAndNext(ContestPK, contestActive,
            flagText, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and flagText = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFlagText(boolean contestActive,
        java.lang.String flagText)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByActiveFlagText(contestActive, flagText);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFlagText(boolean contestActive,
        java.lang.String flagText)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByActiveFlagText(contestActive, flagText);
    }

    /**
    * Returns all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextcontestPrivate(
        boolean contestActive, java.lang.String flagText, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagTextcontestPrivate(contestActive, flagText,
            contestPrivate);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextcontestPrivate(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagTextcontestPrivate(contestActive, flagText,
            contestPrivate, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextcontestPrivate(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagTextcontestPrivate(contestActive, flagText,
            contestPrivate, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagTextcontestPrivate_First(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagTextcontestPrivate_First(contestActive,
            flagText, contestPrivate, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagTextcontestPrivate_First(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagTextcontestPrivate_First(contestActive,
            flagText, contestPrivate, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagTextcontestPrivate_Last(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagTextcontestPrivate_Last(contestActive,
            flagText, contestPrivate, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagTextcontestPrivate_Last(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagTextcontestPrivate_Last(contestActive,
            flagText, contestPrivate, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFlagTextcontestPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagTextcontestPrivate_PrevAndNext(ContestPK,
            contestActive, flagText, contestPrivate, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFlagTextcontestPrivate(
        boolean contestActive, java.lang.String flagText, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActiveFlagTextcontestPrivate(contestActive, flagText,
            contestPrivate);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFlagTextcontestPrivate(
        boolean contestActive, java.lang.String flagText, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActiveFlagTextcontestPrivate(contestActive,
            flagText, contestPrivate);
    }

    /**
    * Caches the contest in the entity cache if it is enabled.
    *
    * @param contest the contest
    */
    public static void cacheResult(com.ext.portlet.model.Contest contest) {
        getPersistence().cacheResult(contest);
    }

    /**
    * Caches the contests in the entity cache if it is enabled.
    *
    * @param contests the contests
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.Contest> contests) {
        getPersistence().cacheResult(contests);
    }

    /**
    * Creates a new contest with the primary key. Does not add the contest to the database.
    *
    * @param ContestPK the primary key for the new contest
    * @return the new contest
    */
    public static com.ext.portlet.model.Contest create(long ContestPK) {
        return getPersistence().create(ContestPK);
    }

    /**
    * Removes the contest with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest that was removed
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest remove(long ContestPK)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(ContestPK);
    }

    public static com.ext.portlet.model.Contest updateImpl(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contest);
    }

    /**
    * Returns the contest with the primary key or throws a {@link com.ext.portlet.NoSuchContestException} if it could not be found.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByPrimaryKey(long ContestPK)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ContestPK);
    }

    /**
    * Returns the contest with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest, or <code>null</code> if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByPrimaryKey(
        long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ContestPK);
    }

    /**
    * Returns all the contests.
    *
    * @return the contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contests.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contests.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contests from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contests.
    *
    * @return the number of contests
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContestPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContestPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ContestPersistence.class.getName());

            ReferenceRegistry.registerReference(ContestUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ContestPersistence persistence) {
    }
}
