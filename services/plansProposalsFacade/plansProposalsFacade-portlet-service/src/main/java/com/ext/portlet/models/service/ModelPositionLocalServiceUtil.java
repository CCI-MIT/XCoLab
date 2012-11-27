package com.ext.portlet.models.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model position local service. This utility wraps {@link com.ext.portlet.models.service.impl.ModelPositionLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelPositionLocalService
 * @see com.ext.portlet.models.service.base.ModelPositionLocalServiceBaseImpl
 * @see com.ext.portlet.models.service.impl.ModelPositionLocalServiceImpl
 * @generated
 */
public class ModelPositionLocalServiceUtil {
    private static ModelPositionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.models.service.impl.ModelPositionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the model position to the database. Also notifies the appropriate model listeners.
    *
    * @param modelPosition the model position
    * @return the model position that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelPosition addModelPosition(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addModelPosition(modelPosition);
    }

    /**
    * Creates a new model position with the primary key. Does not add the model position to the database.
    *
    * @param id the primary key for the new model position
    * @return the new model position
    */
    public static com.ext.portlet.models.model.ModelPosition createModelPosition(
        java.lang.Long id) {
        return getService().createModelPosition(id);
    }

    /**
    * Deletes the model position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the model position
    * @throws PortalException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelPosition(java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelPosition(id);
    }

    /**
    * Deletes the model position from the database. Also notifies the appropriate model listeners.
    *
    * @param modelPosition the model position
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelPosition(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelPosition(modelPosition);
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

    public static com.ext.portlet.models.model.ModelPosition fetchModelPosition(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchModelPosition(id);
    }

    /**
    * Returns the model position with the primary key.
    *
    * @param id the primary key of the model position
    * @return the model position
    * @throws PortalException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelPosition getModelPosition(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelPosition(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @return the range of model positions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelPosition> getModelPositions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelPositions(start, end);
    }

    /**
    * Returns the number of model positions.
    *
    * @return the number of model positions
    * @throws SystemException if a system exception occurred
    */
    public static int getModelPositionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelPositionsCount();
    }

    /**
    * Updates the model position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelPosition the model position
    * @return the model position that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelPosition updateModelPosition(
        com.ext.portlet.models.model.ModelPosition modelPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelPosition(modelPosition);
    }

    /**
    * Updates the model position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelPosition the model position
    * @param merge whether to merge the model position with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model position that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelPosition updateModelPosition(
        com.ext.portlet.models.model.ModelPosition modelPosition, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelPosition(modelPosition, merge);
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

    public static java.util.List<com.ext.portlet.models.model.ModelPosition> getModelPositionsByModelId(
        java.lang.Long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelPositionsByModelId(modelId);
    }

    public static void setModelPositions(java.lang.Long modelId,
        java.util.List<java.lang.Long> positionIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setModelPositions(modelId, positionIds);
    }

    public static void clearService() {
        _service = null;
    }

    public static ModelPositionLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelPositionLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelPositionLocalService.class.getName(),
                    portletClassLoader);

            _service = new ModelPositionLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelPositionLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ModelPositionLocalService.class);
        }

        return _service;
    }

    public void setService(ModelPositionLocalService service) {
        MethodCache.remove(ModelPositionLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelPositionLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ModelPositionLocalService.class);
    }
}
