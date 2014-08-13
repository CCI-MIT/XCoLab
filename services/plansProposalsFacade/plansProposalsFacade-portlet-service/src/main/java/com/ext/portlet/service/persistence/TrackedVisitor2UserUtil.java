package com.ext.portlet.service.persistence;

import com.ext.portlet.model.TrackedVisitor2User;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the tracked visitor2 user service. This utility wraps {@link TrackedVisitor2UserPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitor2UserPersistence
 * @see TrackedVisitor2UserPersistenceImpl
 * @generated
 */
public class TrackedVisitor2UserUtil {
    private static TrackedVisitor2UserPersistence _persistence;

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
    public static void clearCache(TrackedVisitor2User trackedVisitor2User) {
        getPersistence().clearCache(trackedVisitor2User);
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
    public static List<TrackedVisitor2User> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<TrackedVisitor2User> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<TrackedVisitor2User> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static TrackedVisitor2User update(
        TrackedVisitor2User trackedVisitor2User) throws SystemException {
        return getPersistence().update(trackedVisitor2User);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static TrackedVisitor2User update(
        TrackedVisitor2User trackedVisitor2User, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(trackedVisitor2User, serviceContext);
    }

    /**
    * Caches the tracked visitor2 user in the entity cache if it is enabled.
    *
    * @param trackedVisitor2User the tracked visitor2 user
    */
    public static void cacheResult(
        com.ext.portlet.model.TrackedVisitor2User trackedVisitor2User) {
        getPersistence().cacheResult(trackedVisitor2User);
    }

    /**
    * Caches the tracked visitor2 users in the entity cache if it is enabled.
    *
    * @param trackedVisitor2Users the tracked visitor2 users
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.TrackedVisitor2User> trackedVisitor2Users) {
        getPersistence().cacheResult(trackedVisitor2Users);
    }

    /**
    * Creates a new tracked visitor2 user with the primary key. Does not add the tracked visitor2 user to the database.
    *
    * @param id the primary key for the new tracked visitor2 user
    * @return the new tracked visitor2 user
    */
    public static com.ext.portlet.model.TrackedVisitor2User create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the tracked visitor2 user with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the tracked visitor2 user
    * @return the tracked visitor2 user that was removed
    * @throws com.ext.portlet.NoSuchTrackedVisitor2UserException if a tracked visitor2 user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.TrackedVisitor2User remove(long id)
        throws com.ext.portlet.NoSuchTrackedVisitor2UserException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.TrackedVisitor2User updateImpl(
        com.ext.portlet.model.TrackedVisitor2User trackedVisitor2User)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(trackedVisitor2User);
    }

    /**
    * Returns the tracked visitor2 user with the primary key or throws a {@link com.ext.portlet.NoSuchTrackedVisitor2UserException} if it could not be found.
    *
    * @param id the primary key of the tracked visitor2 user
    * @return the tracked visitor2 user
    * @throws com.ext.portlet.NoSuchTrackedVisitor2UserException if a tracked visitor2 user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.TrackedVisitor2User findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchTrackedVisitor2UserException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the tracked visitor2 user with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the tracked visitor2 user
    * @return the tracked visitor2 user, or <code>null</code> if a tracked visitor2 user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.TrackedVisitor2User fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the tracked visitor2 users.
    *
    * @return the tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.TrackedVisitor2User> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the tracked visitor2 users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of tracked visitor2 users
    * @param end the upper bound of the range of tracked visitor2 users (not inclusive)
    * @return the range of tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.TrackedVisitor2User> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the tracked visitor2 users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of tracked visitor2 users
    * @param end the upper bound of the range of tracked visitor2 users (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.TrackedVisitor2User> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the tracked visitor2 users from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of tracked visitor2 users.
    *
    * @return the number of tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static TrackedVisitor2UserPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (TrackedVisitor2UserPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    TrackedVisitor2UserPersistence.class.getName());

            ReferenceRegistry.registerReference(TrackedVisitor2UserUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(TrackedVisitor2UserPersistence persistence) {
    }
}
