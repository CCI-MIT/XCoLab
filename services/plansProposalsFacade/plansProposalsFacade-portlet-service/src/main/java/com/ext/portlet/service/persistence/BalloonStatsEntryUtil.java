package com.ext.portlet.service.persistence;

import com.ext.portlet.model.BalloonStatsEntry;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the balloon stats entry service. This utility wraps {@link BalloonStatsEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BalloonStatsEntryPersistence
 * @see BalloonStatsEntryPersistenceImpl
 * @generated
 */
public class BalloonStatsEntryUtil {
    private static BalloonStatsEntryPersistence _persistence;

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
    public static void clearCache(BalloonStatsEntry balloonStatsEntry) {
        getPersistence().clearCache(balloonStatsEntry);
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
    public static List<BalloonStatsEntry> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<BalloonStatsEntry> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<BalloonStatsEntry> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static BalloonStatsEntry update(BalloonStatsEntry balloonStatsEntry)
        throws SystemException {
        return getPersistence().update(balloonStatsEntry);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static BalloonStatsEntry update(
        BalloonStatsEntry balloonStatsEntry, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(balloonStatsEntry, serviceContext);
    }

    /**
    * Caches the balloon stats entry in the entity cache if it is enabled.
    *
    * @param balloonStatsEntry the balloon stats entry
    */
    public static void cacheResult(
        com.ext.portlet.model.BalloonStatsEntry balloonStatsEntry) {
        getPersistence().cacheResult(balloonStatsEntry);
    }

    /**
    * Caches the balloon stats entries in the entity cache if it is enabled.
    *
    * @param balloonStatsEntries the balloon stats entries
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.BalloonStatsEntry> balloonStatsEntries) {
        getPersistence().cacheResult(balloonStatsEntries);
    }

    /**
    * Creates a new balloon stats entry with the primary key. Does not add the balloon stats entry to the database.
    *
    * @param id the primary key for the new balloon stats entry
    * @return the new balloon stats entry
    */
    public static com.ext.portlet.model.BalloonStatsEntry create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the balloon stats entry with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the balloon stats entry
    * @return the balloon stats entry that was removed
    * @throws com.ext.portlet.NoSuchBalloonStatsEntryException if a balloon stats entry with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonStatsEntry remove(long id)
        throws com.ext.portlet.NoSuchBalloonStatsEntryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.BalloonStatsEntry updateImpl(
        com.ext.portlet.model.BalloonStatsEntry balloonStatsEntry)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(balloonStatsEntry);
    }

    /**
    * Returns the balloon stats entry with the primary key or throws a {@link com.ext.portlet.NoSuchBalloonStatsEntryException} if it could not be found.
    *
    * @param id the primary key of the balloon stats entry
    * @return the balloon stats entry
    * @throws com.ext.portlet.NoSuchBalloonStatsEntryException if a balloon stats entry with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonStatsEntry findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchBalloonStatsEntryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the balloon stats entry with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the balloon stats entry
    * @return the balloon stats entry, or <code>null</code> if a balloon stats entry with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.BalloonStatsEntry fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the balloon stats entries.
    *
    * @return the balloon stats entries
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonStatsEntry> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the balloon stats entries.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonStatsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon stats entries
    * @param end the upper bound of the range of balloon stats entries (not inclusive)
    * @return the range of balloon stats entries
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonStatsEntry> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the balloon stats entries.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.BalloonStatsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of balloon stats entries
    * @param end the upper bound of the range of balloon stats entries (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of balloon stats entries
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.BalloonStatsEntry> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the balloon stats entries from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of balloon stats entries.
    *
    * @return the number of balloon stats entries
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static BalloonStatsEntryPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (BalloonStatsEntryPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    BalloonStatsEntryPersistence.class.getName());

            ReferenceRegistry.registerReference(BalloonStatsEntryUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(BalloonStatsEntryPersistence persistence) {
    }
}
