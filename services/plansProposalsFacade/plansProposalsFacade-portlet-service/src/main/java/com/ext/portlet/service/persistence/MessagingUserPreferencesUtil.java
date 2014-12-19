package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingUserPreferences;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the messaging user preferences service. This utility wraps {@link MessagingUserPreferencesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingUserPreferencesPersistence
 * @see MessagingUserPreferencesPersistenceImpl
 * @generated
 */
public class MessagingUserPreferencesUtil {
    private static MessagingUserPreferencesPersistence _persistence;

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
        MessagingUserPreferences messagingUserPreferences) {
        getPersistence().clearCache(messagingUserPreferences);
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
    public static List<MessagingUserPreferences> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MessagingUserPreferences> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MessagingUserPreferences> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static MessagingUserPreferences update(
        MessagingUserPreferences messagingUserPreferences)
        throws SystemException {
        return getPersistence().update(messagingUserPreferences);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static MessagingUserPreferences update(
        MessagingUserPreferences messagingUserPreferences,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(messagingUserPreferences, serviceContext);
    }

    /**
    * Returns the messaging user preferences where userId = &#63; or throws a {@link com.ext.portlet.NoSuchMessagingUserPreferencesException} if it could not be found.
    *
    * @param userId the user ID
    * @return the matching messaging user preferences
    * @throws com.ext.portlet.NoSuchMessagingUserPreferencesException if a matching messaging user preferences could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingUserPreferences findBymessagingPreferences(
        long userId)
        throws com.ext.portlet.NoSuchMessagingUserPreferencesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBymessagingPreferences(userId);
    }

    /**
    * Returns the messaging user preferences where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param userId the user ID
    * @return the matching messaging user preferences, or <code>null</code> if a matching messaging user preferences could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingUserPreferences fetchBymessagingPreferences(
        long userId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBymessagingPreferences(userId);
    }

    /**
    * Returns the messaging user preferences where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param userId the user ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching messaging user preferences, or <code>null</code> if a matching messaging user preferences could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingUserPreferences fetchBymessagingPreferences(
        long userId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBymessagingPreferences(userId, retrieveFromCache);
    }

    /**
    * Removes the messaging user preferences where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @return the messaging user preferences that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingUserPreferences removeBymessagingPreferences(
        long userId)
        throws com.ext.portlet.NoSuchMessagingUserPreferencesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeBymessagingPreferences(userId);
    }

    /**
    * Returns the number of messaging user preferenceses where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    public static int countBymessagingPreferences(long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBymessagingPreferences(userId);
    }

    /**
    * Caches the messaging user preferences in the entity cache if it is enabled.
    *
    * @param messagingUserPreferences the messaging user preferences
    */
    public static void cacheResult(
        com.ext.portlet.model.MessagingUserPreferences messagingUserPreferences) {
        getPersistence().cacheResult(messagingUserPreferences);
    }

    /**
    * Caches the messaging user preferenceses in the entity cache if it is enabled.
    *
    * @param messagingUserPreferenceses the messaging user preferenceses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingUserPreferences> messagingUserPreferenceses) {
        getPersistence().cacheResult(messagingUserPreferenceses);
    }

    /**
    * Creates a new messaging user preferences with the primary key. Does not add the messaging user preferences to the database.
    *
    * @param messagingPreferencesId the primary key for the new messaging user preferences
    * @return the new messaging user preferences
    */
    public static com.ext.portlet.model.MessagingUserPreferences create(
        long messagingPreferencesId) {
        return getPersistence().create(messagingPreferencesId);
    }

    /**
    * Removes the messaging user preferences with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param messagingPreferencesId the primary key of the messaging user preferences
    * @return the messaging user preferences that was removed
    * @throws com.ext.portlet.NoSuchMessagingUserPreferencesException if a messaging user preferences with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingUserPreferences remove(
        long messagingPreferencesId)
        throws com.ext.portlet.NoSuchMessagingUserPreferencesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(messagingPreferencesId);
    }

    public static com.ext.portlet.model.MessagingUserPreferences updateImpl(
        com.ext.portlet.model.MessagingUserPreferences messagingUserPreferences)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(messagingUserPreferences);
    }

    /**
    * Returns the messaging user preferences with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingUserPreferencesException} if it could not be found.
    *
    * @param messagingPreferencesId the primary key of the messaging user preferences
    * @return the messaging user preferences
    * @throws com.ext.portlet.NoSuchMessagingUserPreferencesException if a messaging user preferences with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingUserPreferences findByPrimaryKey(
        long messagingPreferencesId)
        throws com.ext.portlet.NoSuchMessagingUserPreferencesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(messagingPreferencesId);
    }

    /**
    * Returns the messaging user preferences with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param messagingPreferencesId the primary key of the messaging user preferences
    * @return the messaging user preferences, or <code>null</code> if a messaging user preferences with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingUserPreferences fetchByPrimaryKey(
        long messagingPreferencesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(messagingPreferencesId);
    }

    /**
    * Returns all the messaging user preferenceses.
    *
    * @return the messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingUserPreferences> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the messaging user preferenceses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingUserPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging user preferenceses
    * @param end the upper bound of the range of messaging user preferenceses (not inclusive)
    * @return the range of messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingUserPreferences> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the messaging user preferenceses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingUserPreferencesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging user preferenceses
    * @param end the upper bound of the range of messaging user preferenceses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingUserPreferences> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the messaging user preferenceses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of messaging user preferenceses.
    *
    * @return the number of messaging user preferenceses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingUserPreferencesPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MessagingUserPreferencesPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    MessagingUserPreferencesPersistence.class.getName());

            ReferenceRegistry.registerReference(MessagingUserPreferencesUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MessagingUserPreferencesPersistence persistence) {
    }
}
