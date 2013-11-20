package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MessagingRedirectLink;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the messaging redirect link service. This utility wraps {@link MessagingRedirectLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MessagingRedirectLinkPersistence
 * @see MessagingRedirectLinkPersistenceImpl
 * @generated
 */
public class MessagingRedirectLinkUtil {
    private static MessagingRedirectLinkPersistence _persistence;

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
    public static void clearCache(MessagingRedirectLink messagingRedirectLink) {
        getPersistence().clearCache(messagingRedirectLink);
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
    public static List<MessagingRedirectLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MessagingRedirectLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MessagingRedirectLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static MessagingRedirectLink update(
        MessagingRedirectLink messagingRedirectLink) throws SystemException {
        return getPersistence().update(messagingRedirectLink);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static MessagingRedirectLink update(
        MessagingRedirectLink messagingRedirectLink,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(messagingRedirectLink, serviceContext);
    }

    /**
    * Caches the messaging redirect link in the entity cache if it is enabled.
    *
    * @param messagingRedirectLink the messaging redirect link
    */
    public static void cacheResult(
        com.ext.portlet.model.MessagingRedirectLink messagingRedirectLink) {
        getPersistence().cacheResult(messagingRedirectLink);
    }

    /**
    * Caches the messaging redirect links in the entity cache if it is enabled.
    *
    * @param messagingRedirectLinks the messaging redirect links
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.MessagingRedirectLink> messagingRedirectLinks) {
        getPersistence().cacheResult(messagingRedirectLinks);
    }

    /**
    * Creates a new messaging redirect link with the primary key. Does not add the messaging redirect link to the database.
    *
    * @param redirectId the primary key for the new messaging redirect link
    * @return the new messaging redirect link
    */
    public static com.ext.portlet.model.MessagingRedirectLink create(
        long redirectId) {
        return getPersistence().create(redirectId);
    }

    /**
    * Removes the messaging redirect link with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param redirectId the primary key of the messaging redirect link
    * @return the messaging redirect link that was removed
    * @throws com.ext.portlet.NoSuchMessagingRedirectLinkException if a messaging redirect link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingRedirectLink remove(
        long redirectId)
        throws com.ext.portlet.NoSuchMessagingRedirectLinkException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(redirectId);
    }

    public static com.ext.portlet.model.MessagingRedirectLink updateImpl(
        com.ext.portlet.model.MessagingRedirectLink messagingRedirectLink)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(messagingRedirectLink);
    }

    /**
    * Returns the messaging redirect link with the primary key or throws a {@link com.ext.portlet.NoSuchMessagingRedirectLinkException} if it could not be found.
    *
    * @param redirectId the primary key of the messaging redirect link
    * @return the messaging redirect link
    * @throws com.ext.portlet.NoSuchMessagingRedirectLinkException if a messaging redirect link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingRedirectLink findByPrimaryKey(
        long redirectId)
        throws com.ext.portlet.NoSuchMessagingRedirectLinkException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(redirectId);
    }

    /**
    * Returns the messaging redirect link with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param redirectId the primary key of the messaging redirect link
    * @return the messaging redirect link, or <code>null</code> if a messaging redirect link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MessagingRedirectLink fetchByPrimaryKey(
        long redirectId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(redirectId);
    }

    /**
    * Returns all the messaging redirect links.
    *
    * @return the messaging redirect links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingRedirectLink> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the messaging redirect links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingRedirectLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging redirect links
    * @param end the upper bound of the range of messaging redirect links (not inclusive)
    * @return the range of messaging redirect links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingRedirectLink> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the messaging redirect links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MessagingRedirectLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of messaging redirect links
    * @param end the upper bound of the range of messaging redirect links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of messaging redirect links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MessagingRedirectLink> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the messaging redirect links from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of messaging redirect links.
    *
    * @return the number of messaging redirect links
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MessagingRedirectLinkPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MessagingRedirectLinkPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    MessagingRedirectLinkPersistence.class.getName());

            ReferenceRegistry.registerReference(MessagingRedirectLinkUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MessagingRedirectLinkPersistence persistence) {
    }
}
