package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ImpactDefaultSeries. This utility wraps
 * {@link com.ext.portlet.service.impl.ImpactDefaultSeriesLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesLocalService
 * @see com.ext.portlet.service.base.ImpactDefaultSeriesLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ImpactDefaultSeriesLocalServiceImpl
 * @generated
 */
public class ImpactDefaultSeriesLocalServiceUtil {
    private static ImpactDefaultSeriesLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ImpactDefaultSeriesLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the impact default series to the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeries the impact default series
    * @return the impact default series that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries addImpactDefaultSeries(
        com.ext.portlet.model.ImpactDefaultSeries impactDefaultSeries)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addImpactDefaultSeries(impactDefaultSeries);
    }

    /**
    * Creates a new impact default series with the primary key. Does not add the impact default series to the database.
    *
    * @param impactDefaultSeriesPK the primary key for the new impact default series
    * @return the new impact default series
    */
    public static com.ext.portlet.model.ImpactDefaultSeries createImpactDefaultSeries(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesPK impactDefaultSeriesPK) {
        return getService().createImpactDefaultSeries(impactDefaultSeriesPK);
    }

    /**
    * Deletes the impact default series with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesPK the primary key of the impact default series
    * @return the impact default series that was removed
    * @throws PortalException if a impact default series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries deleteImpactDefaultSeries(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesPK impactDefaultSeriesPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteImpactDefaultSeries(impactDefaultSeriesPK);
    }

    /**
    * Deletes the impact default series from the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeries the impact default series
    * @return the impact default series that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries deleteImpactDefaultSeries(
        com.ext.portlet.model.ImpactDefaultSeries impactDefaultSeries)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteImpactDefaultSeries(impactDefaultSeries);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.ImpactDefaultSeries fetchImpactDefaultSeries(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesPK impactDefaultSeriesPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchImpactDefaultSeries(impactDefaultSeriesPK);
    }

    /**
    * Returns the impact default series with the primary key.
    *
    * @param impactDefaultSeriesPK the primary key of the impact default series
    * @return the impact default series
    * @throws PortalException if a impact default series with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries getImpactDefaultSeries(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesPK impactDefaultSeriesPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactDefaultSeries(impactDefaultSeriesPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the impact default serieses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact default serieses
    * @param end the upper bound of the range of impact default serieses (not inclusive)
    * @return the range of impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> getImpactDefaultSerieses(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactDefaultSerieses(start, end);
    }

    /**
    * Returns the number of impact default serieses.
    *
    * @return the number of impact default serieses
    * @throws SystemException if a system exception occurred
    */
    public static int getImpactDefaultSeriesesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactDefaultSeriesesCount();
    }

    /**
    * Updates the impact default series in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeries the impact default series
    * @return the impact default series that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeries updateImpactDefaultSeries(
        com.ext.portlet.model.ImpactDefaultSeries impactDefaultSeries)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateImpactDefaultSeries(impactDefaultSeries);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeries> getAllImpactDefaultSeriesWithFocusArea(
        com.ext.portlet.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getAllImpactDefaultSeriesWithFocusArea(focusArea);
    }

    public static com.ext.portlet.model.ImpactDefaultSeries getImpactDefaultSeriesWithFocusAreaAndName(
        com.ext.portlet.model.FocusArea focusArea, java.lang.String name)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getImpactDefaultSeriesWithFocusAreaAndName(focusArea, name);
    }

    public static void clearService() {
        _service = null;
    }

    public static ImpactDefaultSeriesLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ImpactDefaultSeriesLocalService.class.getName());

            if (invokableLocalService instanceof ImpactDefaultSeriesLocalService) {
                _service = (ImpactDefaultSeriesLocalService) invokableLocalService;
            } else {
                _service = new ImpactDefaultSeriesLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ImpactDefaultSeriesLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ImpactDefaultSeriesLocalService service) {
    }
}
