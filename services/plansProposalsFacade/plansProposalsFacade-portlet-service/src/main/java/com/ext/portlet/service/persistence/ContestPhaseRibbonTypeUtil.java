package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhaseRibbonType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contest phase ribbon type service. This utility wraps {@link ContestPhaseRibbonTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseRibbonTypePersistence
 * @see ContestPhaseRibbonTypePersistenceImpl
 * @generated
 */
public class ContestPhaseRibbonTypeUtil {
    private static ContestPhaseRibbonTypePersistence _persistence;

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
    public static void clearCache(ContestPhaseRibbonType contestPhaseRibbonType) {
        getPersistence().clearCache(contestPhaseRibbonType);
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
    public static List<ContestPhaseRibbonType> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContestPhaseRibbonType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContestPhaseRibbonType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ContestPhaseRibbonType update(
        ContestPhaseRibbonType contestPhaseRibbonType, boolean merge)
        throws SystemException {
        return getPersistence().update(contestPhaseRibbonType, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ContestPhaseRibbonType update(
        ContestPhaseRibbonType contestPhaseRibbonType, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(contestPhaseRibbonType, merge, serviceContext);
    }

    /**
    * Caches the contest phase ribbon type in the entity cache if it is enabled.
    *
    * @param contestPhaseRibbonType the contest phase ribbon type
    */
    public static void cacheResult(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType) {
        getPersistence().cacheResult(contestPhaseRibbonType);
    }

    /**
    * Caches the contest phase ribbon types in the entity cache if it is enabled.
    *
    * @param contestPhaseRibbonTypes the contest phase ribbon types
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> contestPhaseRibbonTypes) {
        getPersistence().cacheResult(contestPhaseRibbonTypes);
    }

    /**
    * Creates a new contest phase ribbon type with the primary key. Does not add the contest phase ribbon type to the database.
    *
    * @param id the primary key for the new contest phase ribbon type
    * @return the new contest phase ribbon type
    */
    public static com.ext.portlet.model.ContestPhaseRibbonType create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the contest phase ribbon type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase ribbon type
    * @return the contest phase ribbon type that was removed
    * @throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseRibbonType remove(long id)
        throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.ContestPhaseRibbonType updateImpl(
        com.ext.portlet.model.ContestPhaseRibbonType contestPhaseRibbonType,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contestPhaseRibbonType, merge);
    }

    /**
    * Returns the contest phase ribbon type with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseRibbonTypeException} if it could not be found.
    *
    * @param id the primary key of the contest phase ribbon type
    * @return the contest phase ribbon type
    * @throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseRibbonType findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchContestPhaseRibbonTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the contest phase ribbon type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest phase ribbon type
    * @return the contest phase ribbon type, or <code>null</code> if a contest phase ribbon type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseRibbonType fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the contest phase ribbon types.
    *
    * @return the contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contest phase ribbon types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phase ribbon types
    * @param end the upper bound of the range of contest phase ribbon types (not inclusive)
    * @return the range of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contest phase ribbon types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest phase ribbon types
    * @param end the upper bound of the range of contest phase ribbon types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhaseRibbonType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contest phase ribbon types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contest phase ribbon types.
    *
    * @return the number of contest phase ribbon types
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContestPhaseRibbonTypePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContestPhaseRibbonTypePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ContestPhaseRibbonTypePersistence.class.getName());

            ReferenceRegistry.registerReference(ContestPhaseRibbonTypeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ContestPhaseRibbonTypePersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ContestPhaseRibbonTypeUtil.class,
            "_persistence");
    }
}
