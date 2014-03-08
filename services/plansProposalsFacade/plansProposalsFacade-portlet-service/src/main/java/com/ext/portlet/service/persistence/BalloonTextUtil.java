package com.ext.portlet.service.persistence;

import com.ext.portlet.model.BalloonText;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the balloon text service. This utility wraps {@link BalloonTextPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonTextPersistence
 * @see BalloonTextPersistenceImpl
 * @generated
 */
public class BalloonTextUtil {
    private static BalloonTextPersistence _persistence;

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
    public static void clearCache(BalloonText balloonText) {
        getPersistence().clearCache(balloonText);
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
    public static List<BalloonText> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<BalloonText> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<BalloonText> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static BalloonText update(BalloonText balloonText)
        throws SystemException {
        return getPersistence().update(balloonText);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static BalloonText update(BalloonText balloonText,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(balloonText, serviceContext);
    }

    /**
    * Caches the balloon text in the entity cache if it is enabled.
    *
    * @param balloonText the balloon text
    */
    public static void cacheResult(
        com.ext.portlet.model.BalloonText balloonText) {
        getPersistence().cacheResult(balloonText);
    }

    /**
    * Caches the balloon texts in the entity cache if it is enabled.
    *
    * @param balloonTexts the balloon texts
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.BalloonText> balloonTexts) {
        getPersistence().cacheResult(balloonTexts);
    }

    /**
    * Creates a new balloon text with the primary key. Does not add the balloon text to the database.
    *
    * @param id the primary key for the new balloon text
    * @return the new balloon text
    */
    public static com.ext.portlet.model.BalloonText create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the balloon text with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the balloon text
    * @return the balloon text that was removed
    * @throws com.ext.portlet.NoSuchBalloonTextException if a balloon text with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonText remove(long id)
        throws com.ext.portlet.NoSuchBalloonTextException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.BalloonText updateImpl(
        com.ext.portlet.model.BalloonText balloonText)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(balloonText);
    }

    /**
    * Returns the balloon text with the primary key or throws a {@link com.ext.portlet.NoSuchBalloonTextException} if it could not be found.
    *
    * @param id the primary key of the balloon text
    * @return the balloon text
    * @throws com.ext.portlet.NoSuchBalloonTextException if a balloon text with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonText findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchBalloonTextException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the balloon text with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the balloon text
    * @return the balloon text, or <code>null</code> if a balloon text with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonText fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the balloon texts.
    *
    * @return the balloon texts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonText> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the balloon texts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonTextModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon texts
    * @param end the upper bound of the range of balloon texts (not inclusive)
    * @return the range of balloon texts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonText> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the balloon texts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonTextModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon texts
    * @param end the upper bound of the range of balloon texts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of balloon texts
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonText> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the balloon texts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of balloon texts.
    *
    * @return the number of balloon texts
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static BalloonTextPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (BalloonTextPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    BalloonTextPersistence.class.getName());

            ReferenceRegistry.registerReference(BalloonTextUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(BalloonTextPersistence persistence) {
    }
}
