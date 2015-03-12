package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ImpactTemplateSeries;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the impact template series service. This utility wraps {@link ImpactTemplateSeriesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateSeriesPersistence
 * @see ImpactTemplateSeriesPersistenceImpl
 * @generated
 */
public class ImpactTemplateSeriesUtil {
    private static ImpactTemplateSeriesPersistence _persistence;

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
    public static void clearCache(ImpactTemplateSeries impactTemplateSeries) {
        getPersistence().clearCache(impactTemplateSeries);
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
    public static List<ImpactTemplateSeries> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImpactTemplateSeries> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImpactTemplateSeries> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ImpactTemplateSeries update(
        ImpactTemplateSeries impactTemplateSeries) throws SystemException {
        return getPersistence().update(impactTemplateSeries);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ImpactTemplateSeries update(
        ImpactTemplateSeries impactTemplateSeries, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(impactTemplateSeries, serviceContext);
    }

    /**
    * Caches the impact template series in the entity cache if it is enabled.
    *
    * @param impactTemplateSeries the impact template series
    */
    public static void cacheResult(
        com.ext.portlet.model.ImpactTemplateSeries impactTemplateSeries) {
        getPersistence().cacheResult(impactTemplateSeries);
    }

    /**
    * Caches the impact template serieses in the entity cache if it is enabled.
    *
    * @param impactTemplateSerieses the impact template serieses
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ImpactTemplateSeries> impactTemplateSerieses) {
        getPersistence().cacheResult(impactTemplateSerieses);
    }

    /**
    * Creates a new impact template series with the primary key. Does not add the impact template series to the database.
    *
    * @param seriesId the primary key for the new impact template series
    * @return the new impact template series
    */
    public static com.ext.portlet.model.ImpactTemplateSeries create(
        long seriesId) {
        return getPersistence().create(seriesId);
    }

    /**
    * Removes the impact template series with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param seriesId the primary key of the impact template series
    * @return the impact template series that was removed
    * @throws com.ext.portlet.NoSuchImpactTemplateSeriesException if a impact template series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateSeries remove(
        long seriesId)
        throws com.ext.portlet.NoSuchImpactTemplateSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(seriesId);
    }

    public static com.ext.portlet.model.ImpactTemplateSeries updateImpl(
        com.ext.portlet.model.ImpactTemplateSeries impactTemplateSeries)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(impactTemplateSeries);
    }

    /**
    * Returns the impact template series with the primary key or throws a {@link com.ext.portlet.NoSuchImpactTemplateSeriesException} if it could not be found.
    *
    * @param seriesId the primary key of the impact template series
    * @return the impact template series
    * @throws com.ext.portlet.NoSuchImpactTemplateSeriesException if a impact template series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateSeries findByPrimaryKey(
        long seriesId)
        throws com.ext.portlet.NoSuchImpactTemplateSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(seriesId);
    }

    /**
    * Returns the impact template series with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param seriesId the primary key of the impact template series
    * @return the impact template series, or <code>null</code> if a impact template series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateSeries fetchByPrimaryKey(
        long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(seriesId);
    }

    /**
    * Returns all the impact template serieses.
    *
    * @return the impact template serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactTemplateSeries> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the impact template serieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template serieses
    * @param end the upper bound of the range of impact template serieses (not inclusive)
    * @return the range of impact template serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactTemplateSeries> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the impact template serieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template serieses
    * @param end the upper bound of the range of impact template serieses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of impact template serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactTemplateSeries> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the impact template serieses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of impact template serieses.
    *
    * @return the number of impact template serieses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImpactTemplateSeriesPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImpactTemplateSeriesPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ImpactTemplateSeriesPersistence.class.getName());

            ReferenceRegistry.registerReference(ImpactTemplateSeriesUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImpactTemplateSeriesPersistence persistence) {
    }
}
