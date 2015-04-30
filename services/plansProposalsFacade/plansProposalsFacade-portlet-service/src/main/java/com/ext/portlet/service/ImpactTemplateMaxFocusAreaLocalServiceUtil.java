package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ImpactTemplateMaxFocusArea. This utility wraps
 * {@link com.ext.portlet.service.impl.ImpactTemplateMaxFocusAreaLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ImpactTemplateMaxFocusAreaLocalService
 * @see com.ext.portlet.service.base.ImpactTemplateMaxFocusAreaLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ImpactTemplateMaxFocusAreaLocalServiceImpl
 * @generated
 */
public class ImpactTemplateMaxFocusAreaLocalServiceUtil {
    private static ImpactTemplateMaxFocusAreaLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ImpactTemplateMaxFocusAreaLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the impact template max focus area to the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateMaxFocusArea the impact template max focus area
    * @return the impact template max focus area that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateMaxFocusArea addImpactTemplateMaxFocusArea(
        com.ext.portlet.model.ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .addImpactTemplateMaxFocusArea(impactTemplateMaxFocusArea);
    }

    /**
    * Creates a new impact template max focus area with the primary key. Does not add the impact template max focus area to the database.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key for the new impact template max focus area
    * @return the new impact template max focus area
    */
    public static com.ext.portlet.model.ImpactTemplateMaxFocusArea createImpactTemplateMaxFocusArea(
        com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK) {
        return getService()
                   .createImpactTemplateMaxFocusArea(impactTemplateMaxFocusAreaPK);
    }

    /**
    * Deletes the impact template max focus area with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
    * @return the impact template max focus area that was removed
    * @throws PortalException if a impact template max focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateMaxFocusArea deleteImpactTemplateMaxFocusArea(
        com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .deleteImpactTemplateMaxFocusArea(impactTemplateMaxFocusAreaPK);
    }

    /**
    * Deletes the impact template max focus area from the database. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateMaxFocusArea the impact template max focus area
    * @return the impact template max focus area that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateMaxFocusArea deleteImpactTemplateMaxFocusArea(
        com.ext.portlet.model.ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .deleteImpactTemplateMaxFocusArea(impactTemplateMaxFocusArea);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.ext.portlet.model.ImpactTemplateMaxFocusArea fetchImpactTemplateMaxFocusArea(
        com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .fetchImpactTemplateMaxFocusArea(impactTemplateMaxFocusAreaPK);
    }

    /**
    * Returns the impact template max focus area with the primary key.
    *
    * @param impactTemplateMaxFocusAreaPK the primary key of the impact template max focus area
    * @return the impact template max focus area
    * @throws PortalException if a impact template max focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateMaxFocusArea getImpactTemplateMaxFocusArea(
        com.ext.portlet.service.persistence.ImpactTemplateMaxFocusAreaPK impactTemplateMaxFocusAreaPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .getImpactTemplateMaxFocusArea(impactTemplateMaxFocusAreaPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the impact template max focus areas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ImpactTemplateMaxFocusAreaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of impact template max focus areas
    * @param end the upper bound of the range of impact template max focus areas (not inclusive)
    * @return the range of impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ImpactTemplateMaxFocusArea> getImpactTemplateMaxFocusAreas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactTemplateMaxFocusAreas(start, end);
    }

    /**
    * Returns the number of impact template max focus areas.
    *
    * @return the number of impact template max focus areas
    * @throws SystemException if a system exception occurred
    */
    public static int getImpactTemplateMaxFocusAreasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getImpactTemplateMaxFocusAreasCount();
    }

    /**
    * Updates the impact template max focus area in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param impactTemplateMaxFocusArea the impact template max focus area
    * @return the impact template max focus area that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ImpactTemplateMaxFocusArea updateImpactTemplateMaxFocusArea(
        com.ext.portlet.model.ImpactTemplateMaxFocusArea impactTemplateMaxFocusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateImpactTemplateMaxFocusArea(impactTemplateMaxFocusArea);
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

    public static void clearService() {
        _service = null;
    }

    public static ImpactTemplateMaxFocusAreaLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ImpactTemplateMaxFocusAreaLocalService.class.getName());

            if (invokableLocalService instanceof ImpactTemplateMaxFocusAreaLocalService) {
                _service = (ImpactTemplateMaxFocusAreaLocalService) invokableLocalService;
            } else {
                _service = new ImpactTemplateMaxFocusAreaLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ImpactTemplateMaxFocusAreaLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ImpactTemplateMaxFocusAreaLocalService service) {
    }
}
