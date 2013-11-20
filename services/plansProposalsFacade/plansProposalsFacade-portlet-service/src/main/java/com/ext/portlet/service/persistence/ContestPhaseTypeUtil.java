package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhaseType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contest phase type service. This utility wraps {@link ContestPhaseTypePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseTypePersistence
 * @see ContestPhaseTypePersistenceImpl
 * @generated
 */
public class ContestPhaseTypeUtil {
    private static ContestPhaseTypePersistence _persistence;

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
    public static void clearCache(ContestPhaseType contestPhaseType) {
        getPersistence().clearCache(contestPhaseType);
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
    public static List<ContestPhaseType> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContestPhaseType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContestPhaseType> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ContestPhaseType update(ContestPhaseType contestPhaseType)
        throws SystemException {
        return getPersistence().update(contestPhaseType);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ContestPhaseType update(ContestPhaseType contestPhaseType,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(contestPhaseType, serviceContext);
    }

    /**
    * Caches the contest phase type in the entity cache if it is enabled.
    *
    * @param contestPhaseType the contest phase type
    */
    public static void cacheResult(
        com.ext.portlet.model.ContestPhaseType contestPhaseType) {
        getPersistence().cacheResult(contestPhaseType);
    }

    /**
    * Caches the contest phase types in the entity cache if it is enabled.
    *
    * @param contestPhaseTypes the contest phase types
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ContestPhaseType> contestPhaseTypes) {
        getPersistence().cacheResult(contestPhaseTypes);
    }

    /**
    * Creates a new contest phase type with the primary key. Does not add the contest phase type to the database.
    *
    * @param id the primary key for the new contest phase type
    * @return the new contest phase type
    */
    public static com.ext.portlet.model.ContestPhaseType create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the contest phase type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase type
    * @return the contest phase type that was removed
    * @throws com.ext.portlet.NoSuchContestPhaseTypeException if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseType remove(long id)
        throws com.ext.portlet.NoSuchContestPhaseTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.ContestPhaseType updateImpl(
        com.ext.portlet.model.ContestPhaseType contestPhaseType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contestPhaseType);
    }

    /**
    * Returns the contest phase type with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseTypeException} if it could not be found.
    *
    * @param id the primary key of the contest phase type
    * @return the contest phase type
    * @throws com.ext.portlet.NoSuchContestPhaseTypeException if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseType findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchContestPhaseTypeException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the contest phase type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest phase type
    * @return the contest phase type, or <code>null</code> if a contest phase type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestPhaseType fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the contest phase types.
    *
    * @return the contest phase types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhaseType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contest phase types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phase types
    * @param end the upper bound of the range of contest phase types (not inclusive)
    * @return the range of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhaseType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contest phase types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phase types
    * @param end the upper bound of the range of contest phase types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestPhaseType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contest phase types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contest phase types.
    *
    * @return the number of contest phase types
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContestPhaseTypePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContestPhaseTypePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ContestPhaseTypePersistence.class.getName());

            ReferenceRegistry.registerReference(ContestPhaseTypeUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ContestPhaseTypePersistence persistence) {
    }
}
