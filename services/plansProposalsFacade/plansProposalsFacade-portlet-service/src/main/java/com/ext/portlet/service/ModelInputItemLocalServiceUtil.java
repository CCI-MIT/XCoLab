package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model input item local service. This utility wraps {@link com.ext.portlet.service.impl.ModelInputItemLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItemLocalService
 * @see com.ext.portlet.service.base.ModelInputItemLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelInputItemLocalServiceImpl
 * @generated
 */
public class ModelInputItemLocalServiceUtil {
    private static ModelInputItemLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelInputItemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the model input item to the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItem the model input item
    * @return the model input item that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelInputItem addModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addModelInputItem(modelInputItem);
    }

    /**
    * Creates a new model input item with the primary key. Does not add the model input item to the database.
    *
    * @param modelInputItemPK the primary key for the new model input item
    * @return the new model input item
    */
    public static com.ext.portlet.model.ModelInputItem createModelInputItem(
        long modelInputItemPK) {
        return getService().createModelInputItem(modelInputItemPK);
    }

    /**
    * Deletes the model input item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @throws PortalException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelInputItem(long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelInputItem(modelInputItemPK);
    }

    /**
    * Deletes the model input item from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItem the model input item
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelInputItem(modelInputItem);
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

    public static com.ext.portlet.model.ModelInputItem fetchModelInputItem(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchModelInputItem(modelInputItemPK);
    }

    /**
    * Returns the model input item with the primary key.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @return the model input item
    * @throws PortalException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelInputItem getModelInputItem(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelInputItem(modelInputItemPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model input items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model input items
    * @param end the upper bound of the range of model input items (not inclusive)
    * @return the range of model input items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelInputItem> getModelInputItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelInputItems(start, end);
    }

    /**
    * Returns the number of model input items.
    *
    * @return the number of model input items
    * @throws SystemException if a system exception occurred
    */
    public static int getModelInputItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelInputItemsCount();
    }

    /**
    * Updates the model input item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelInputItem the model input item
    * @return the model input item that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelInputItem updateModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelInputItem(modelInputItem);
    }

    /**
    * Updates the model input item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelInputItem the model input item
    * @param merge whether to merge the model input item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model input item that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelInputItem updateModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelInputItem(modelInputItem, merge);
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

    public static java.util.List<com.ext.portlet.model.ModelInputItem> getItemsForModel(
        edu.mit.cci.simulation.client.Simulation sim) {
        return getService().getItemsForModel(sim);
    }

    public static com.ext.portlet.model.ModelInputItem getItemForMetaData(
        java.lang.Long modelId, edu.mit.cci.simulation.client.MetaData md) {
        return getService().getItemForMetaData(modelId, md);
    }

    public static java.util.List<com.ext.portlet.model.ModelInputItem> getItemForGroupId(
        java.lang.Long groupid) {
        return getService().getItemForGroupId(groupid);
    }

    public static edu.mit.cci.simulation.client.MetaData getMetaData(
        com.ext.portlet.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return getService().getMetaData(item);
    }

    public static edu.mit.cci.simulation.client.Simulation getModel(
        com.ext.portlet.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return getService().getModel(item);
    }

    public static java.util.Map<java.lang.String, java.lang.String> getPropertyMap(
        com.ext.portlet.model.ModelInputItem item) {
        return getService().getPropertyMap(item);
    }

    public static void saveProperties(
        com.ext.portlet.model.ModelInputItem item,
        java.util.Map<java.lang.String, java.lang.String> props)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().saveProperties(item, props);
    }

    public static void store(com.ext.portlet.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(item);
    }

    public static void clearService() {
        _service = null;
    }

    public static ModelInputItemLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelInputItemLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelInputItemLocalService.class.getName(),
                    portletClassLoader);

            _service = new ModelInputItemLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelInputItemLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ModelInputItemLocalService.class);
        }

        return _service;
    }

    public void setService(ModelInputItemLocalService service) {
        MethodCache.remove(ModelInputItemLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelInputItemLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ModelInputItemLocalService.class);
    }
}
