package com.ext.portlet.models.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model category local service. This utility wraps {@link com.ext.portlet.models.service.impl.ModelCategoryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelCategoryLocalService
 * @see com.ext.portlet.models.service.base.ModelCategoryLocalServiceBaseImpl
 * @see com.ext.portlet.models.service.impl.ModelCategoryLocalServiceImpl
 * @generated
 */
public class ModelCategoryLocalServiceUtil {
    private static ModelCategoryLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.models.service.impl.ModelCategoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the model category to the database. Also notifies the appropriate model listeners.
    *
    * @param modelCategory the model category
    * @return the model category that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelCategory addModelCategory(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addModelCategory(modelCategory);
    }

    /**
    * Creates a new model category with the primary key. Does not add the model category to the database.
    *
    * @param modelCategoryPK the primary key for the new model category
    * @return the new model category
    */
    public static com.ext.portlet.models.model.ModelCategory createModelCategory(
        java.lang.Long modelCategoryPK) {
        return getService().createModelCategory(modelCategoryPK);
    }

    /**
    * Deletes the model category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelCategoryPK the primary key of the model category
    * @throws PortalException if a model category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelCategory(java.lang.Long modelCategoryPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelCategory(modelCategoryPK);
    }

    /**
    * Deletes the model category from the database. Also notifies the appropriate model listeners.
    *
    * @param modelCategory the model category
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelCategory(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelCategory(modelCategory);
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

    public static com.ext.portlet.models.model.ModelCategory fetchModelCategory(
        java.lang.Long modelCategoryPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchModelCategory(modelCategoryPK);
    }

    /**
    * Returns the model category with the primary key.
    *
    * @param modelCategoryPK the primary key of the model category
    * @return the model category
    * @throws PortalException if a model category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelCategory getModelCategory(
        java.lang.Long modelCategoryPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelCategory(modelCategoryPK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model categories
    * @param end the upper bound of the range of model categories (not inclusive)
    * @return the range of model categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelCategory> getModelCategories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelCategories(start, end);
    }

    /**
    * Returns the number of model categories.
    *
    * @return the number of model categories
    * @throws SystemException if a system exception occurred
    */
    public static int getModelCategoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelCategoriesCount();
    }

    /**
    * Updates the model category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelCategory the model category
    * @return the model category that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelCategory updateModelCategory(
        com.ext.portlet.models.model.ModelCategory modelCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelCategory(modelCategory);
    }

    /**
    * Updates the model category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelCategory the model category
    * @param merge whether to merge the model category with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model category that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelCategory updateModelCategory(
        com.ext.portlet.models.model.ModelCategory modelCategory, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelCategory(modelCategory, merge);
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

    public static ModelCategoryLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelCategoryLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelCategoryLocalService.class.getName(),
                    portletClassLoader);

            _service = new ModelCategoryLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelCategoryLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ModelCategoryLocalService.class);
        }

        return _service;
    }

    public void setService(ModelCategoryLocalService service) {
        MethodCache.remove(ModelCategoryLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelCategoryLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ModelCategoryLocalService.class);
    }
}
