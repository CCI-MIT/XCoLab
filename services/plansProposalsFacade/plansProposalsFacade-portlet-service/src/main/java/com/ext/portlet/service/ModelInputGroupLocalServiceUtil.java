package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model input group local service. This utility wraps {@link com.ext.portlet.service.impl.ModelInputGroupLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputGroupLocalService
 * @see com.ext.portlet.service.base.ModelInputGroupLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelInputGroupLocalServiceImpl
 * @generated
 */
public class ModelInputGroupLocalServiceUtil {
    private static ModelInputGroupLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelInputGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the model input group to the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroup the model input group
    * @return the model input group that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelInputGroup addModelInputGroup(
        com.ext.portlet.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addModelInputGroup(modelInputGroup);
    }

    /**
    * Creates a new model input group with the primary key. Does not add the model input group to the database.
    *
    * @param modelInputGroupPK the primary key for the new model input group
    * @return the new model input group
    */
    public static com.ext.portlet.model.ModelInputGroup createModelInputGroup(
        long modelInputGroupPK) {
        return getService().createModelInputGroup(modelInputGroupPK);
    }

    /**
    * Deletes the model input group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @throws PortalException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelInputGroup(long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelInputGroup(modelInputGroupPK);
    }

    /**
    * Deletes the model input group from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroup the model input group
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelInputGroup(
        com.ext.portlet.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelInputGroup(modelInputGroup);
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

    public static com.ext.portlet.model.ModelInputGroup fetchModelInputGroup(
        long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchModelInputGroup(modelInputGroupPK);
    }

    /**
    * Returns the model input group with the primary key.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @return the model input group
    * @throws PortalException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelInputGroup getModelInputGroup(
        long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelInputGroup(modelInputGroupPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model input groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @return the range of model input groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelInputGroup> getModelInputGroups(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelInputGroups(start, end);
    }

    /**
    * Returns the number of model input groups.
    *
    * @return the number of model input groups
    * @throws SystemException if a system exception occurred
    */
    public static int getModelInputGroupsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelInputGroupsCount();
    }

    /**
    * Updates the model input group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroup the model input group
    * @return the model input group that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelInputGroup updateModelInputGroup(
        com.ext.portlet.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelInputGroup(modelInputGroup);
    }

    /**
    * Updates the model input group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroup the model input group
    * @param merge whether to merge the model input group with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model input group that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelInputGroup updateModelInputGroup(
        com.ext.portlet.model.ModelInputGroup modelInputGroup, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelInputGroup(modelInputGroup, merge);
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

    public static java.util.List<com.ext.portlet.model.ModelInputGroup> getInputGroups(
        edu.mit.cci.roma.client.Simulation sim) {
        return getService().getInputGroups(sim);
    }

    public static java.util.List<com.ext.portlet.model.ModelInputGroup> getChildGroups(
        com.ext.portlet.model.ModelInputGroup group) {
        return getService().getChildGroups(group);
    }

    public static java.util.List<com.ext.portlet.model.ModelInputItem> getInputItems(
        com.ext.portlet.model.ModelInputGroup group) {
        return getService().getInputItems(group);
    }

    public static com.ext.portlet.model.ModelInputGroup getParent(
        com.ext.portlet.model.ModelInputGroup group) {
        return getService().getParent(group);
    }

    public static edu.mit.cci.roma.client.Simulation getModel(
        com.ext.portlet.model.ModelInputGroup group)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return getService().getModel(group);
    }

    public static edu.mit.cci.roma.client.MetaData getMetaData(
        com.ext.portlet.model.ModelInputGroup group)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return getService().getMetaData(group);
    }

    public static void clearService() {
        _service = null;
    }

    public static ModelInputGroupLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelInputGroupLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelInputGroupLocalService.class.getName(),
                    portletClassLoader);

            _service = new ModelInputGroupLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelInputGroupLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ModelInputGroupLocalService.class);
        }

        return _service;
    }

    public void setService(ModelInputGroupLocalService service) {
        MethodCache.remove(ModelInputGroupLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelInputGroupLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ModelInputGroupLocalService.class);
    }
}
