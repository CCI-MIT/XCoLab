package com.ext.portlet.service.persistence;

import com.ext.portlet.model.SocialActivity;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the social activity service. This utility wraps {@link SocialActivityPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityPersistence
 * @see SocialActivityPersistenceImpl
 * @generated
 */
public class SocialActivityUtil {
    private static SocialActivityPersistence _persistence;

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
    public static void clearCache(SocialActivity socialActivity) {
        getPersistence().clearCache(socialActivity);
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
    public static List<SocialActivity> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<SocialActivity> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<SocialActivity> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static SocialActivity update(SocialActivity socialActivity)
        throws SystemException {
        return getPersistence().update(socialActivity);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static SocialActivity update(SocialActivity socialActivity,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(socialActivity, serviceContext);
    }

    /**
    * Caches the social activity in the entity cache if it is enabled.
    *
    * @param socialActivity the social activity
    */
    public static void cacheResult(
        com.ext.portlet.model.SocialActivity socialActivity) {
        getPersistence().cacheResult(socialActivity);
    }

    /**
    * Caches the social activities in the entity cache if it is enabled.
    *
    * @param socialActivities the social activities
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.SocialActivity> socialActivities) {
        getPersistence().cacheResult(socialActivities);
    }

    /**
    * Creates a new social activity with the primary key. Does not add the social activity to the database.
    *
    * @param activityId the primary key for the new social activity
    * @return the new social activity
    */
    public static com.ext.portlet.model.SocialActivity create(long activityId) {
        return getPersistence().create(activityId);
    }

    /**
    * Removes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param activityId the primary key of the social activity
    * @return the social activity that was removed
    * @throws com.ext.portlet.NoSuchSocialActivityException if a social activity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SocialActivity remove(long activityId)
        throws com.ext.portlet.NoSuchSocialActivityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(activityId);
    }

    public static com.ext.portlet.model.SocialActivity updateImpl(
        com.ext.portlet.model.SocialActivity socialActivity)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(socialActivity);
    }

    /**
    * Returns the social activity with the primary key or throws a {@link com.ext.portlet.NoSuchSocialActivityException} if it could not be found.
    *
    * @param activityId the primary key of the social activity
    * @return the social activity
    * @throws com.ext.portlet.NoSuchSocialActivityException if a social activity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SocialActivity findByPrimaryKey(
        long activityId)
        throws com.ext.portlet.NoSuchSocialActivityException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(activityId);
    }

    /**
    * Returns the social activity with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param activityId the primary key of the social activity
    * @return the social activity, or <code>null</code> if a social activity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.SocialActivity fetchByPrimaryKey(
        long activityId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(activityId);
    }

    /**
    * Returns all the social activities.
    *
    * @return the social activities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.SocialActivity> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the social activities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of social activities
    * @param end the upper bound of the range of social activities (not inclusive)
    * @return the range of social activities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.SocialActivity> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the social activities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of social activities
    * @param end the upper bound of the range of social activities (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of social activities
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.SocialActivity> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the social activities from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of social activities.
    *
    * @return the number of social activities
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static SocialActivityPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (SocialActivityPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    SocialActivityPersistence.class.getName());

            ReferenceRegistry.registerReference(SocialActivityUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(SocialActivityPersistence persistence) {
    }
}
