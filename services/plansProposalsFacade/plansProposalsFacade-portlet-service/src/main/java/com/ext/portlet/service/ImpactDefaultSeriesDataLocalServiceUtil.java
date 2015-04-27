package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ImpactDefaultSeriesData. This utility wraps
 * {@link com.ext.portlet.service.impl.ImpactDefaultSeriesDataLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactDefaultSeriesDataLocalService
 * @see com.ext.portlet.service.base.ImpactDefaultSeriesDataLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ImpactDefaultSeriesDataLocalServiceImpl
 * @generated
 */
public class ImpactDefaultSeriesDataLocalServiceUtil {
    private static ImpactDefaultSeriesDataLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ImpactDefaultSeriesDataLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the impact default series data to the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesData the impact default series data
    * @return the impact default series data that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData addImpactDefaultSeriesData(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addImpactDefaultSeriesData(impactDefaultSeriesData);
    }

    /**
    * Creates a new impact default series data with the primary key. Does not add the impact default series data to the database.
    *
    * @param impactDefaultSeriesDataPK the primary key for the new impact default series data
    * @return the new impact default series data
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData createImpactDefaultSeriesData(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK) {
        return getService()
                   .createImpactDefaultSeriesData(impactDefaultSeriesDataPK);
    }

    /**
    * Deletes the impact default series data with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data that was removed
    * @throws PortalException if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData deleteImpactDefaultSeriesData(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .deleteImpactDefaultSeriesData(impactDefaultSeriesDataPK);
    }

    /**
    * Deletes the impact default series data from the database. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesData the impact default series data
    * @return the impact default series data that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData deleteImpactDefaultSeriesData(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .deleteImpactDefaultSeriesData(impactDefaultSeriesData);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.ImpactDefaultSeriesData fetchImpactDefaultSeriesData(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .fetchImpactDefaultSeriesData(impactDefaultSeriesDataPK);
    }

    /**
    * Returns the impact default series data with the primary key.
    *
    * @param impactDefaultSeriesDataPK the primary key of the impact default series data
    * @return the impact default series data
    * @throws PortalException if a impact default series data with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData getImpactDefaultSeriesData(
        com.ext.portlet.service.persistence.ImpactDefaultSeriesDataPK impactDefaultSeriesDataPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactDefaultSeriesData(impactDefaultSeriesDataPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the impact default series datas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactDefaultSeriesDataModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact default series datas
    * @param end the upper bound of the range of impact default series datas (not inclusive)
    * @return the range of impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> getImpactDefaultSeriesDatas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactDefaultSeriesDatas(start, end);
    }

    /**
    * Returns the number of impact default series datas.
    *
    * @return the number of impact default series datas
    * @throws SystemException if a system exception occurred
    */
    public static int getImpactDefaultSeriesDatasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactDefaultSeriesDatasCount();
    }

    /**
    * Updates the impact default series data in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param impactDefaultSeriesData the impact default series data
    * @return the impact default series data that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactDefaultSeriesData updateImpactDefaultSeriesData(
        com.ext.portlet.model.ImpactDefaultSeriesData impactDefaultSeriesData)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateImpactDefaultSeriesData(impactDefaultSeriesData);
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

    public static java.util.List<com.ext.portlet.model.ImpactDefaultSeriesData> getDefaultSeriesDataBySeriesId(
        long seriesId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDefaultSeriesDataBySeriesId(seriesId);
    }

    public static com.ext.portlet.model.ImpactDefaultSeriesData getDefaultSeriesDataBySeriesIdAndYear(
        long seriesId, int year)
        throws com.ext.portlet.NoSuchImpactDefaultSeriesDataException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getDefaultSeriesDataBySeriesIdAndYear(seriesId, year);
    }

    public static void clearService() {
        _service = null;
    }

    public static ImpactDefaultSeriesDataLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ImpactDefaultSeriesDataLocalService.class.getName());

            if (invokableLocalService instanceof ImpactDefaultSeriesDataLocalService) {
                _service = (ImpactDefaultSeriesDataLocalService) invokableLocalService;
            } else {
                _service = new ImpactDefaultSeriesDataLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ImpactDefaultSeriesDataLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ImpactDefaultSeriesDataLocalService service) {
    }
}
