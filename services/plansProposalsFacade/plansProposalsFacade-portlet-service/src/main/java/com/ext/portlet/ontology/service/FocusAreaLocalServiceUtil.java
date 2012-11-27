package com.ext.portlet.ontology.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the focus area local service. This utility wraps {@link com.ext.portlet.ontology.service.impl.FocusAreaLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaLocalService
 * @see com.ext.portlet.ontology.service.base.FocusAreaLocalServiceBaseImpl
 * @see com.ext.portlet.ontology.service.impl.FocusAreaLocalServiceImpl
 * @generated
 */
public class FocusAreaLocalServiceUtil {
    private static FocusAreaLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.ontology.service.impl.FocusAreaLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the focus area to the database. Also notifies the appropriate model listeners.
    *
    * @param focusArea the focus area
    * @return the focus area that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.ontology.model.FocusArea addFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addFocusArea(focusArea);
    }

    /**
    * Creates a new focus area with the primary key. Does not add the focus area to the database.
    *
    * @param id the primary key for the new focus area
    * @return the new focus area
    */
    public static com.ext.portlet.ontology.model.FocusArea createFocusArea(
        java.lang.Long id) {
        return getService().createFocusArea(id);
    }

    /**
    * Deletes the focus area with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the focus area
    * @throws PortalException if a focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFocusArea(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFocusArea(id);
    }

    /**
    * Deletes the focus area from the database. Also notifies the appropriate model listeners.
    *
    * @param focusArea the focus area
    * @throws SystemException if a system exception occurred
    */
    public static void deleteFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteFocusArea(focusArea);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
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

    public static com.ext.portlet.ontology.model.FocusArea fetchFocusArea(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchFocusArea(id);
    }

    /**
    * Returns the focus area with the primary key.
    *
    * @param id the primary key of the focus area
    * @return the focus area
    * @throws PortalException if a focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.ontology.model.FocusArea getFocusArea(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getFocusArea(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the focus areas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of focus areas
    * @param end the upper bound of the range of focus areas (not inclusive)
    * @return the range of focus areas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.ontology.model.FocusArea> getFocusAreas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFocusAreas(start, end);
    }

    /**
    * Returns the number of focus areas.
    *
    * @return the number of focus areas
    * @throws SystemException if a system exception occurred
    */
    public static int getFocusAreasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getFocusAreasCount();
    }

    /**
    * Updates the focus area in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param focusArea the focus area
    * @return the focus area that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.ontology.model.FocusArea updateFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateFocusArea(focusArea);
    }

    /**
    * Updates the focus area in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param focusArea the focus area
    * @param merge whether to merge the focus area with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the focus area that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.ontology.model.FocusArea updateFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateFocusArea(focusArea, merge);
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

    public static void clearService() {
        _service = null;
    }

    public static FocusAreaLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    FocusAreaLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    FocusAreaLocalService.class.getName(), portletClassLoader);

            _service = new FocusAreaLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(FocusAreaLocalServiceUtil.class,
                "_service");
            MethodCache.remove(FocusAreaLocalService.class);
        }

        return _service;
    }

    public void setService(FocusAreaLocalService service) {
        MethodCache.remove(FocusAreaLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(FocusAreaLocalServiceUtil.class,
            "_service");
        MethodCache.remove(FocusAreaLocalService.class);
    }
}
