package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model output item local service. This utility wraps {@link com.ext.portlet.service.impl.ModelOutputItemLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputItemLocalService
 * @see com.ext.portlet.service.base.ModelOutputItemLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelOutputItemLocalServiceImpl
 * @generated
 */
public class ModelOutputItemLocalServiceUtil {
    private static ModelOutputItemLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelOutputItemLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the model output item to the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItem the model output item
    * @return the model output item that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem addModelOutputItem(
        com.ext.portlet.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addModelOutputItem(modelOutputItem);
    }

    /**
    * Creates a new model output item with the primary key. Does not add the model output item to the database.
    *
    * @param modelOutputItemModifierPK the primary key for the new model output item
    * @return the new model output item
    */
    public static com.ext.portlet.model.ModelOutputItem createModelOutputItem(
        long modelOutputItemModifierPK) {
        return getService().createModelOutputItem(modelOutputItemModifierPK);
    }

    /**
    * Deletes the model output item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @throws PortalException if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelOutputItem(long modelOutputItemModifierPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelOutputItem(modelOutputItemModifierPK);
    }

    /**
    * Deletes the model output item from the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItem the model output item
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelOutputItem(
        com.ext.portlet.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelOutputItem(modelOutputItem);
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

    public static com.ext.portlet.model.ModelOutputItem fetchModelOutputItem(
        long modelOutputItemModifierPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchModelOutputItem(modelOutputItemModifierPK);
    }

    /**
    * Returns the model output item with the primary key.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @return the model output item
    * @throws PortalException if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem getModelOutputItem(
        long modelOutputItemModifierPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelOutputItem(modelOutputItemModifierPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model output items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model output items
    * @param end the upper bound of the range of model output items (not inclusive)
    * @return the range of model output items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelOutputItem> getModelOutputItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelOutputItems(start, end);
    }

    /**
    * Returns the number of model output items.
    *
    * @return the number of model output items
    * @throws SystemException if a system exception occurred
    */
    public static int getModelOutputItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelOutputItemsCount();
    }

    /**
    * Updates the model output item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItem the model output item
    * @return the model output item that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem updateModelOutputItem(
        com.ext.portlet.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelOutputItem(modelOutputItem);
    }

    /**
    * Updates the model output item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItem the model output item
    * @param merge whether to merge the model output item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model output item that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem updateModelOutputItem(
        com.ext.portlet.model.ModelOutputItem modelOutputItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelOutputItem(modelOutputItem, merge);
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

    public static com.ext.portlet.model.ModelOutputItem getOutputItem(
        edu.mit.cci.simulation.client.MetaData md)
        throws com.ext.portlet.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getOutputItem(md);
    }

    public static void clearService() {
        _service = null;
    }

    public static ModelOutputItemLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelOutputItemLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelOutputItemLocalService.class.getName(),
                    portletClassLoader);

            _service = new ModelOutputItemLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelOutputItemLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ModelOutputItemLocalService.class);
        }

        return _service;
    }

    public void setService(ModelOutputItemLocalService service) {
        MethodCache.remove(ModelOutputItemLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelOutputItemLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ModelOutputItemLocalService.class);
    }
}
