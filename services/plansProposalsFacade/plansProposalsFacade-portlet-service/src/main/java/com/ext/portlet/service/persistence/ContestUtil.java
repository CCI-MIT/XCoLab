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
    * Returns all the contests where ContestYear = &#63;.
    *
    * @param ContestYear the contest year
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestYear(
        long ContestYear)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestYear(ContestYear);
    }

    /**
    * Returns a range of all the contests where ContestYear = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestYear the contest year
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestYear(
        long ContestYear, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestYear(ContestYear, start, end);
    }

    /**
    * Returns an ordered range of all the contests where ContestYear = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestYear the contest year
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestYear(
        long ContestYear, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestYear(ContestYear, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where ContestYear = &#63;.
    *
    * @param ContestYear the contest year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestYear_First(
        long ContestYear,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestYear_First(ContestYear, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where ContestYear = &#63;.
    *
    * @param ContestYear the contest year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestYear_First(
        long ContestYear,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestYear_First(ContestYear, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where ContestYear = &#63;.
    *
    * @param ContestYear the contest year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestYear_Last(
        long ContestYear,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestYear_Last(ContestYear, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where ContestYear = &#63;.
    *
    * @param ContestYear the contest year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestYear_Last(
        long ContestYear,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestYear_Last(ContestYear, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where ContestYear = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param ContestYear the contest year
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByContestYear_PrevAndNext(
        long ContestPK, long ContestYear,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestYear_PrevAndNext(ContestPK, ContestYear,
            orderByComparator);
    }

    /**
    * Removes all the contests where ContestYear = &#63; from the database.
    *
    * @param ContestYear the contest year
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestYear(long ContestYear)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByContestYear(ContestYear);
    }

    /**
    * Returns the number of contests where ContestYear = &#63;.
    *
    * @param ContestYear the contest year
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestYear(long ContestYear)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestYear(ContestYear);
    }

    /**
    * Returns all the contests where ContestUrlName = &#63;.
    *
    * @param ContestUrlName the contest url name
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestUrlName(
        java.lang.String ContestUrlName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestUrlName(ContestUrlName);
    }

    /**
    * Returns a range of all the contests where ContestUrlName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestUrlName the contest url name
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestUrlName(
        java.lang.String ContestUrlName, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestUrlName(ContestUrlName, start, end);
    }

    /**
    * Returns an ordered range of all the contests where ContestUrlName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestUrlName the contest url name
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestUrlName(
        java.lang.String ContestUrlName, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestUrlName(ContestUrlName, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where ContestUrlName = &#63;.
    *
    * @param ContestUrlName the contest url name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestUrlName_First(
        java.lang.String ContestUrlName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestUrlName_First(ContestUrlName, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where ContestUrlName = &#63;.
    *
    * @param ContestUrlName the contest url name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestUrlName_First(
        java.lang.String ContestUrlName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestUrlName_First(ContestUrlName,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where ContestUrlName = &#63;.
    *
    * @param ContestUrlName the contest url name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestUrlName_Last(
        java.lang.String ContestUrlName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestUrlName_Last(ContestUrlName, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where ContestUrlName = &#63;.
    *
    * @param ContestUrlName the contest url name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestUrlName_Last(
        java.lang.String ContestUrlName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestUrlName_Last(ContestUrlName, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where ContestUrlName = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param ContestUrlName the contest url name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByContestUrlName_PrevAndNext(
        long ContestPK, java.lang.String ContestUrlName,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestUrlName_PrevAndNext(ContestPK, ContestUrlName,
            orderByComparator);
    }

    /**
    * Removes all the contests where ContestUrlName = &#63; from the database.
    *
    * @param ContestUrlName the contest url name
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestUrlName(java.lang.String ContestUrlName)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByContestUrlName(ContestUrlName);
    }

    /**
    * Returns the number of contests where ContestUrlName = &#63;.
    *
    * @param ContestUrlName the contest url name
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestUrlName(java.lang.String ContestUrlName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestUrlName(ContestUrlName);
    }

    /**
    * Returns the contest where ContestUrlName = &#63; and ContestYear = &#63; or throws a {@link com.ext.portlet.NoSuchContestException} if it could not be found.
    *
    * @param ContestUrlName the contest url name
    * @param ContestYear the contest year
    * @return the matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestUrlNameContestYear(
        java.lang.String ContestUrlName, long ContestYear)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestUrlNameContestYear(ContestUrlName, ContestYear);
    }

    /**
    * Returns the contest where ContestUrlName = &#63; and ContestYear = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param ContestUrlName the contest url name
    * @param ContestYear the contest year
    * @return the matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestUrlNameContestYear(
        java.lang.String ContestUrlName, long ContestYear)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestUrlNameContestYear(ContestUrlName, ContestYear);
    }

    /**
    * Returns the contest where ContestUrlName = &#63; and ContestYear = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param ContestUrlName the contest url name
    * @param ContestYear the contest year
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestUrlNameContestYear(
        java.lang.String ContestUrlName, long ContestYear,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestUrlNameContestYear(ContestUrlName,
            ContestYear, retrieveFromCache);
    }

    /**
    * Removes the contest where ContestUrlName = &#63; and ContestYear = &#63; from the database.
    *
    * @param ContestUrlName the contest url name
    * @param ContestYear the contest year
    * @return the contest that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest removeByContestUrlNameContestYear(
        java.lang.String ContestUrlName, long ContestYear)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .removeByContestUrlNameContestYear(ContestUrlName,
            ContestYear);
    }

    /**
    * Returns the number of contests where ContestUrlName = &#63; and ContestYear = &#63;.
    *
    * @param ContestUrlName the contest url name
    * @param ContestYear the contest year
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestUrlNameContestYear(
        java.lang.String ContestUrlName, long ContestYear)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByContestUrlNameContestYear(ContestUrlName, ContestYear);
    }

    /**
    * Returns all the contests where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByTier(
        long contestTier)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTier(contestTier);
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
    public static java.util.List<com.ext.portlet.model.Contest> findByTier(
        long contestTier, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTier(contestTier, start, end);
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
    public static java.util.List<com.ext.portlet.model.Contest> findByTier(
        long contestTier, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTier(contestTier, start, end, orderByComparator);
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
    public static com.ext.portlet.model.Contest findByTier_First(
        long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTier_First(contestTier, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByTier_First(
        long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByTier_First(contestTier, orderByComparator);
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
    public static com.ext.portlet.model.Contest findByTier_Last(
        long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTier_Last(contestTier, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByTier_Last(
        long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByTier_Last(contestTier, orderByComparator);
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
    public static com.ext.portlet.model.Contest[] findByTier_PrevAndNext(
        long ContestPK, long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTier_PrevAndNext(ContestPK, contestTier,
            orderByComparator);
    }

    /**
    * Removes all the contests where contestTier = &#63; from the database.
    *
    * @param contestTier the contest tier
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTier(long contestTier)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTier(contestTier);
    }

    /**
    * Returns the number of contests where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByTier(long contestTier)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTier(contestTier);
    }

    /**
    * Returns all the contests where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByTierType(
        long contestTier, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByTierType(contestTier, contestTypeId);
    }

    /**
    * Returns a range of all the contests where contestTier = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByTierType(
        long contestTier, long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTierType(contestTier, contestTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestTier = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByTierType(
        long contestTier, long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTierType(contestTier, contestTypeId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByTierType_First(
        long contestTier, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTierType_First(contestTier, contestTypeId,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByTierType_First(
        long contestTier, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTierType_First(contestTier, contestTypeId,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByTierType_Last(
        long contestTier, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTierType_Last(contestTier, contestTypeId,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByTierType_Last(
        long contestTier, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByTierType_Last(contestTier, contestTypeId,
            orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByTierType_PrevAndNext(
        long ContestPK, long contestTier, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByTierType_PrevAndNext(ContestPK, contestTier,
            contestTypeId, orderByComparator);
    }

    /**
    * Removes all the contests where contestTier = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByTierType(long contestTier, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByTierType(contestTier, contestTypeId);
    }

    /**
    * Returns the number of contests where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByTierType(long contestTier, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByTierType(contestTier, contestTypeId);
    }

    /**
    * Returns all the contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActivePrivate(
        boolean contestActive, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivate(contestActive, contestPrivate);
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActivePrivate(
        boolean contestActive, boolean contestPrivate, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivate(contestActive, contestPrivate, start,
            end);
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActivePrivate(
        boolean contestActive, boolean contestPrivate, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivate(contestActive, contestPrivate, start,
            end, orderByComparator);
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
    public static com.ext.portlet.model.Contest findByActivePrivate_First(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivate_First(contestActive, contestPrivate,
            orderByComparator);
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
    public static com.ext.portlet.model.Contest fetchByActivePrivate_First(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActivePrivate_First(contestActive, contestPrivate,
            orderByComparator);
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
    public static com.ext.portlet.model.Contest findByActivePrivate_Last(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivate_Last(contestActive, contestPrivate,
            orderByComparator);
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
    public static com.ext.portlet.model.Contest fetchByActivePrivate_Last(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActivePrivate_Last(contestActive, contestPrivate,
            orderByComparator);
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
    public static com.ext.portlet.model.Contest[] findByActivePrivate_PrevAndNext(
        long ContestPK, boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivate_PrevAndNext(ContestPK, contestActive,
            contestPrivate, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and contestPrivate = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActivePrivate(boolean contestActive,
        boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByActivePrivate(contestActive, contestPrivate);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActivePrivate(boolean contestActive,
        boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActivePrivate(contestActive, contestPrivate);
    }

    /**
    * Returns all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActivePrivateType(
        boolean contestActive, boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivateType(contestActive, contestPrivate,
            contestTypeId);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActivePrivateType(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivateType(contestActive, contestPrivate,
            contestTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActivePrivateType(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivateType(contestActive, contestPrivate,
            contestTypeId, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActivePrivateType_First(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivateType_First(contestActive,
            contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActivePrivateType_First(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActivePrivateType_First(contestActive,
            contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActivePrivateType_Last(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivateType_Last(contestActive, contestPrivate,
            contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActivePrivateType_Last(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActivePrivateType_Last(contestActive,
            contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActivePrivateType_PrevAndNext(
        long ContestPK, boolean contestActive, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActivePrivateType_PrevAndNext(ContestPK,
            contestActive, contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActivePrivateType(boolean contestActive,
        boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActivePrivateType(contestActive, contestPrivate,
            contestTypeId);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActivePrivateType(boolean contestActive,
        boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActivePrivateType(contestActive, contestPrivate,
            contestTypeId);
    }

    /**
    * Returns all the contests where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActive(
        boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByActive(contestActive);
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActive(
        boolean contestActive, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByActive(contestActive, start, end);
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActive(
        boolean contestActive, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActive(contestActive, start, end, orderByComparator);
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
    public static com.ext.portlet.model.Contest findByActive_First(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActive_First(contestActive, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActive_First(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActive_First(contestActive, orderByComparator);
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
    public static com.ext.portlet.model.Contest findByActive_Last(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActive_Last(contestActive, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActive_Last(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActive_Last(contestActive, orderByComparator);
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
    public static com.ext.portlet.model.Contest[] findByActive_PrevAndNext(
        long ContestPK, boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActive_PrevAndNext(ContestPK, contestActive,
            orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; from the database.
    *
    * @param contestActive the contest active
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActive(boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByActive(contestActive);
    }

    /**
    * Returns the number of contests where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActive(boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByActive(contestActive);
    }

    /**
    * Returns all the contests where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveType(
        boolean contestActive, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByActiveType(contestActive, contestTypeId);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveType(
        boolean contestActive, long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveType(contestActive, contestTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveType(
        boolean contestActive, long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveType(contestActive, contestTypeId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveType_First(
        boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveType_First(contestActive, contestTypeId,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveType_First(
        boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveType_First(contestActive, contestTypeId,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveType_Last(
        boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveType_Last(contestActive, contestTypeId,
            orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveType_Last(
        boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveType_Last(contestActive, contestTypeId,
            orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveType_PrevAndNext(
        long ContestPK, boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveType_PrevAndNext(ContestPK, contestActive,
            contestTypeId, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveType(boolean contestActive,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByActiveType(contestActive, contestTypeId);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveType(boolean contestActive,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByActiveType(contestActive, contestTypeId);
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
    * Returns all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedType(
        boolean contestActive, boolean featured, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedType(contestActive, featured,
            contestTypeId);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedType(
        boolean contestActive, boolean featured, long contestTypeId, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedType(contestActive, featured,
            contestTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedType(
        boolean contestActive, boolean featured, long contestTypeId, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedType(contestActive, featured,
            contestTypeId, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFeaturedType_First(
        boolean contestActive, boolean featured, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedType_First(contestActive, featured,
            contestTypeId, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFeaturedType_First(
        boolean contestActive, boolean featured, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeaturedType_First(contestActive, featured,
            contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFeaturedType_Last(
        boolean contestActive, boolean featured, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedType_Last(contestActive, featured,
            contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFeaturedType_Last(
        boolean contestActive, boolean featured, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeaturedType_Last(contestActive, featured,
            contestTypeId, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFeaturedType_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedType_PrevAndNext(ContestPK,
            contestActive, featured, contestTypeId, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFeaturedType(boolean contestActive,
        boolean featured, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActiveFeaturedType(contestActive, featured, contestTypeId);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFeaturedType(boolean contestActive,
        boolean featured, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActiveFeaturedType(contestActive, featured,
            contestTypeId);
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivate(contestActive, featured,
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivate(contestActive, featured,
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivate(contestActive, featured,
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
    public static com.ext.portlet.model.Contest findByActiveFeaturedPrivate_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivate_First(contestActive, featured,
            contestPrivate, orderByComparator);
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
    public static com.ext.portlet.model.Contest fetchByActiveFeaturedPrivate_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeaturedPrivate_First(contestActive, featured,
            contestPrivate, orderByComparator);
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
    public static com.ext.portlet.model.Contest findByActiveFeaturedPrivate_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivate_Last(contestActive, featured,
            contestPrivate, orderByComparator);
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
    public static com.ext.portlet.model.Contest fetchByActiveFeaturedPrivate_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeaturedPrivate_Last(contestActive, featured,
            contestPrivate, orderByComparator);
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
    public static com.ext.portlet.model.Contest[] findByActiveFeaturedPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivate_PrevAndNext(ContestPK,
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
    public static void removeByActiveFeaturedPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActiveFeaturedPrivate(contestActive, featured,
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
    public static int countByActiveFeaturedPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActiveFeaturedPrivate(contestActive, featured,
            contestPrivate);
    }

    /**
    * Returns all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivateType(contestActive, featured,
            contestPrivate, contestTypeId);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivateType(contestActive, featured,
            contestPrivate, contestTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivateType(contestActive, featured,
            contestPrivate, contestTypeId, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFeaturedPrivateType_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivateType_First(contestActive,
            featured, contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFeaturedPrivateType_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeaturedPrivateType_First(contestActive,
            featured, contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFeaturedPrivateType_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivateType_Last(contestActive,
            featured, contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFeaturedPrivateType_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFeaturedPrivateType_Last(contestActive,
            featured, contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFeaturedPrivateType_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFeaturedPrivateType_PrevAndNext(ContestPK,
            contestActive, featured, contestPrivate, contestTypeId,
            orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActiveFeaturedPrivateType(contestActive, featured,
            contestPrivate, contestTypeId);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFeaturedPrivateType(boolean contestActive,
        boolean featured, boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActiveFeaturedPrivateType(contestActive, featured,
            contestPrivate, contestTypeId);
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
    * Returns all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagType(
        boolean contestActive, int flag, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagType(contestActive, flag, contestTypeId);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagType(
        boolean contestActive, int flag, long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagType(contestActive, flag, contestTypeId,
            start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagType(
        boolean contestActive, int flag, long contestTypeId, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagType(contestActive, flag, contestTypeId,
            start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagType_First(
        boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagType_First(contestActive, flag,
            contestTypeId, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagType_First(
        boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagType_First(contestActive, flag,
            contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagType_Last(
        boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagType_Last(contestActive, flag,
            contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagType_Last(
        boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagType_Last(contestActive, flag,
            contestTypeId, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFlagType_PrevAndNext(
        long ContestPK, boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagType_PrevAndNext(ContestPK, contestActive,
            flag, contestTypeId, orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFlagType(boolean contestActive, int flag,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActiveFlagType(contestActive, flag, contestTypeId);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFlagType(boolean contestActive, int flag,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActiveFlagType(contestActive, flag, contestTypeId);
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivate(
        boolean contestActive, int flag, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivate(contestActive, flag, contestPrivate);
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivate(
        boolean contestActive, int flag, boolean contestPrivate, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivate(contestActive, flag,
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
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivate(
        boolean contestActive, int flag, boolean contestPrivate, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivate(contestActive, flag,
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
    public static com.ext.portlet.model.Contest findByActiveFlagPrivate_First(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivate_First(contestActive, flag,
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
    public static com.ext.portlet.model.Contest fetchByActiveFlagPrivate_First(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagPrivate_First(contestActive, flag,
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
    public static com.ext.portlet.model.Contest findByActiveFlagPrivate_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivate_Last(contestActive, flag,
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
    public static com.ext.portlet.model.Contest fetchByActiveFlagPrivate_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagPrivate_Last(contestActive, flag,
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
    public static com.ext.portlet.model.Contest[] findByActiveFlagPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, int flag,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivate_PrevAndNext(ContestPK,
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
    public static void removeByActiveFlagPrivate(boolean contestActive,
        int flag, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActiveFlagPrivate(contestActive, flag, contestPrivate);
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
    public static int countByActiveFlagPrivate(boolean contestActive, int flag,
        boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActiveFlagPrivate(contestActive, flag, contestPrivate);
    }

    /**
    * Returns all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivateType(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivateType(contestActive, flag,
            contestPrivate, contestTypeId);
    }

    /**
    * Returns a range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivateType(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivateType(contestActive, flag,
            contestPrivate, contestTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivateType(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivateType(contestActive, flag,
            contestPrivate, contestTypeId, start, end, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagPrivateType_First(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivateType_First(contestActive, flag,
            contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagPrivateType_First(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagPrivateType_First(contestActive, flag,
            contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByActiveFlagPrivateType_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivateType_Last(contestActive, flag,
            contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByActiveFlagPrivateType_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByActiveFlagPrivateType_Last(contestActive, flag,
            contestPrivate, contestTypeId, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByActiveFlagPrivateType_PrevAndNext(
        long ContestPK, boolean contestActive, int flag,
        boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByActiveFlagPrivateType_PrevAndNext(ContestPK,
            contestActive, flag, contestPrivate, contestTypeId,
            orderByComparator);
    }

    /**
    * Removes all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByActiveFlagPrivateType(boolean contestActive,
        int flag, boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence()
            .removeByActiveFlagPrivateType(contestActive, flag, contestPrivate,
            contestTypeId);
    }

    /**
    * Returns the number of contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByActiveFlagPrivateType(boolean contestActive,
        int flag, boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByActiveFlagPrivateType(contestActive, flag,
            contestPrivate, contestTypeId);
    }

    /**
    * Returns all the contests where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestType(
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestType(contestTypeId);
    }

    /**
    * Returns a range of all the contests where contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestType(
        long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestType(contestTypeId, start, end);
    }

    /**
    * Returns an ordered range of all the contests where contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.Contest> findByContestType(
        long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestType(contestTypeId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestType_First(
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestType_First(contestTypeId, orderByComparator);
    }

    /**
    * Returns the first contest in the ordered set where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestType_First(
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestType_First(contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest findByContestType_Last(
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestType_Last(contestTypeId, orderByComparator);
    }

    /**
    * Returns the last contest in the ordered set where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest fetchByContestType_Last(
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestType_Last(contestTypeId, orderByComparator);
    }

    /**
    * Returns the contests before and after the current contest in the ordered set where contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.Contest[] findByContestType_PrevAndNext(
        long ContestPK, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestType_PrevAndNext(ContestPK, contestTypeId,
            orderByComparator);
    }

    /**
    * Removes all the contests where contestTypeId = &#63; from the database.
    *
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestType(long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByContestType(contestTypeId);
    }

    /**
    * Returns the number of contests where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestType(long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestType(contestTypeId);
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
