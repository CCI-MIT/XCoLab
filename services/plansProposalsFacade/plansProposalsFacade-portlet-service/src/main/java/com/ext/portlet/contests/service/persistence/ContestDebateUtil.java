package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.model.ContestDebate;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contest debate service. This utility wraps {@link ContestDebatePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDebatePersistence
 * @see ContestDebatePersistenceImpl
 * @generated
 */
public class ContestDebateUtil {
    private static ContestDebatePersistence _persistence;

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
    public static void clearCache(ContestDebate contestDebate) {
        getPersistence().clearCache(contestDebate);
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
    public static List<ContestDebate> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContestDebate> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContestDebate> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ContestDebate update(ContestDebate contestDebate,
        boolean merge) throws SystemException {
        return getPersistence().update(contestDebate, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ContestDebate update(ContestDebate contestDebate,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(contestDebate, merge, serviceContext);
    }

    /**
    * Caches the contest debate in the entity cache if it is enabled.
    *
    * @param contestDebate the contest debate
    */
    public static void cacheResult(
        com.ext.portlet.contests.model.ContestDebate contestDebate) {
        getPersistence().cacheResult(contestDebate);
    }

    /**
    * Caches the contest debates in the entity cache if it is enabled.
    *
    * @param contestDebates the contest debates
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestDebate> contestDebates) {
        getPersistence().cacheResult(contestDebates);
    }

    /**
    * Creates a new contest debate with the primary key. Does not add the contest debate to the database.
    *
    * @param id the primary key for the new contest debate
    * @return the new contest debate
    */
    public static com.ext.portlet.contests.model.ContestDebate create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the contest debate with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest debate
    * @return the contest debate that was removed
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.contests.model.ContestDebate updateImpl(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contestDebate, merge);
    }

    /**
    * Returns the contest debate with the primary key or throws a {@link com.ext.portlet.contests.NoSuchContestDebateException} if it could not be found.
    *
    * @param id the primary key of the contest debate
    * @return the contest debate
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the contest debate with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest debate
    * @return the contest debate, or <code>null</code> if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the contest debates where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @return the matching contest debates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        java.lang.Long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestPK(ContestPK);
    }

    /**
    * Returns a range of all the contest debates where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @return the range of matching contest debates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        java.lang.Long ContestPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestPK(ContestPK, start, end);
    }

    /**
    * Returns an ordered range of all the contest debates where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest debates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        java.lang.Long ContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestPK(ContestPK, start, end, orderByComparator);
    }

    /**
    * Returns the first contest debate in the ordered set where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest debate
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a matching contest debate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate findByContestPK_First(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestPK_First(ContestPK, orderByComparator);
    }

    /**
    * Returns the last contest debate in the ordered set where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest debate
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a matching contest debate could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate findByContestPK_Last(
        java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestPK_Last(ContestPK, orderByComparator);
    }

    /**
    * Returns the contest debates before and after the current contest debate in the ordered set where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current contest debate
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest debate
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestDebate[] findByContestPK_PrevAndNext(
        java.lang.Long id, java.lang.Long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestPK_PrevAndNext(id, ContestPK, orderByComparator);
    }

    /**
    * Returns all the contest debates.
    *
    * @return the contest debates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contest debates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @return the range of contest debates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contest debates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest debates
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contest debates where ContestPK = &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestPK(java.lang.Long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByContestPK(ContestPK);
    }

    /**
    * Removes all the contest debates from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contest debates where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @return the number of matching contest debates
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestPK(java.lang.Long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestPK(ContestPK);
    }

    /**
    * Returns the number of contest debates.
    *
    * @return the number of contest debates
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContestDebatePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContestDebatePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.contests.service.ClpSerializer.getServletContextName(),
                    ContestDebatePersistence.class.getName());

            ReferenceRegistry.registerReference(ContestDebateUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ContestDebatePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ContestDebateUtil.class,
            "_persistence");
    }
}
