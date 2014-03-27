package com.ext.portlet.service.persistence;

import com.ext.portlet.model.BalloonLink;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the balloon link service. This utility wraps {@link BalloonLinkPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonLinkPersistence
 * @see BalloonLinkPersistenceImpl
 * @generated
 */
public class BalloonLinkUtil {
    private static BalloonLinkPersistence _persistence;

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
    public static void clearCache(BalloonLink balloonLink) {
        getPersistence().clearCache(balloonLink);
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
    public static List<BalloonLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<BalloonLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<BalloonLink> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static BalloonLink update(BalloonLink balloonLink)
        throws SystemException {
        return getPersistence().update(balloonLink);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static BalloonLink update(BalloonLink balloonLink,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(balloonLink, serviceContext);
    }

    /**
    * Returns all the balloon links where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @return the matching balloon links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonLink> findByBalloonUserUuid(
        java.lang.String balloonUserUuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByBalloonUserUuid(balloonUserUuid);
    }

    /**
    * Returns a range of all the balloon links where balloonUserUuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param balloonUserUuid the balloon user uuid
    * @param start the lower bound of the range of balloon links
    * @param end the upper bound of the range of balloon links (not inclusive)
    * @return the range of matching balloon links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonLink> findByBalloonUserUuid(
        java.lang.String balloonUserUuid, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBalloonUserUuid(balloonUserUuid, start, end);
    }

    /**
    * Returns an ordered range of all the balloon links where balloonUserUuid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param balloonUserUuid the balloon user uuid
    * @param start the lower bound of the range of balloon links
    * @param end the upper bound of the range of balloon links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching balloon links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonLink> findByBalloonUserUuid(
        java.lang.String balloonUserUuid, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBalloonUserUuid(balloonUserUuid, start, end,
            orderByComparator);
    }

    /**
    * Returns the first balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching balloon link
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a matching balloon link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonLink findByBalloonUserUuid_First(
        java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBalloonUserUuid_First(balloonUserUuid,
            orderByComparator);
    }

    /**
    * Returns the first balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching balloon link, or <code>null</code> if a matching balloon link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonLink fetchByBalloonUserUuid_First(
        java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByBalloonUserUuid_First(balloonUserUuid,
            orderByComparator);
    }

    /**
    * Returns the last balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching balloon link
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a matching balloon link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonLink findByBalloonUserUuid_Last(
        java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBalloonUserUuid_Last(balloonUserUuid,
            orderByComparator);
    }

    /**
    * Returns the last balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching balloon link, or <code>null</code> if a matching balloon link could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonLink fetchByBalloonUserUuid_Last(
        java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByBalloonUserUuid_Last(balloonUserUuid,
            orderByComparator);
    }

    /**
    * Returns the balloon links before and after the current balloon link in the ordered set where balloonUserUuid = &#63;.
    *
    * @param uuid the primary key of the current balloon link
    * @param balloonUserUuid the balloon user uuid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next balloon link
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonLink[] findByBalloonUserUuid_PrevAndNext(
        java.lang.String uuid, java.lang.String balloonUserUuid,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByBalloonUserUuid_PrevAndNext(uuid, balloonUserUuid,
            orderByComparator);
    }

    /**
    * Removes all the balloon links where balloonUserUuid = &#63; from the database.
    *
    * @param balloonUserUuid the balloon user uuid
    * @throws SystemException if a system exception occurred
    */
    public static void removeByBalloonUserUuid(java.lang.String balloonUserUuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByBalloonUserUuid(balloonUserUuid);
    }

    /**
    * Returns the number of balloon links where balloonUserUuid = &#63;.
    *
    * @param balloonUserUuid the balloon user uuid
    * @return the number of matching balloon links
    * @throws SystemException if a system exception occurred
    */
    public static int countByBalloonUserUuid(java.lang.String balloonUserUuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByBalloonUserUuid(balloonUserUuid);
    }

    /**
    * Caches the balloon link in the entity cache if it is enabled.
    *
    * @param balloonLink the balloon link
    */
    public static void cacheResult(
        com.ext.portlet.model.BalloonLink balloonLink) {
        getPersistence().cacheResult(balloonLink);
    }

    /**
    * Caches the balloon links in the entity cache if it is enabled.
    *
    * @param balloonLinks the balloon links
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.BalloonLink> balloonLinks) {
        getPersistence().cacheResult(balloonLinks);
    }

    /**
    * Creates a new balloon link with the primary key. Does not add the balloon link to the database.
    *
    * @param uuid the primary key for the new balloon link
    * @return the new balloon link
    */
    public static com.ext.portlet.model.BalloonLink create(
        java.lang.String uuid) {
        return getPersistence().create(uuid);
    }

    /**
    * Removes the balloon link with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param uuid the primary key of the balloon link
    * @return the balloon link that was removed
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonLink remove(
        java.lang.String uuid)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(uuid);
    }

    public static com.ext.portlet.model.BalloonLink updateImpl(
        com.ext.portlet.model.BalloonLink balloonLink)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(balloonLink);
    }

    /**
    * Returns the balloon link with the primary key or throws a {@link com.ext.portlet.NoSuchBalloonLinkException} if it could not be found.
    *
    * @param uuid the primary key of the balloon link
    * @return the balloon link
    * @throws com.ext.portlet.NoSuchBalloonLinkException if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonLink findByPrimaryKey(
        java.lang.String uuid)
        throws com.ext.portlet.NoSuchBalloonLinkException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(uuid);
    }

    /**
    * Returns the balloon link with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param uuid the primary key of the balloon link
    * @return the balloon link, or <code>null</code> if a balloon link with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonLink fetchByPrimaryKey(
        java.lang.String uuid)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(uuid);
    }

    /**
    * Returns all the balloon links.
    *
    * @return the balloon links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonLink> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the balloon links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon links
    * @param end the upper bound of the range of balloon links (not inclusive)
    * @return the range of balloon links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonLink> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the balloon links.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonLinkModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon links
    * @param end the upper bound of the range of balloon links (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of balloon links
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonLink> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the balloon links from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of balloon links.
    *
    * @return the number of balloon links
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static BalloonLinkPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (BalloonLinkPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    BalloonLinkPersistence.class.getName());

            ReferenceRegistry.registerReference(BalloonLinkUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(BalloonLinkPersistence persistence) {
    }
}
