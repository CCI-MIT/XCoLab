package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the model global preference local service. This utility wraps {@link com.ext.portlet.service.impl.ModelGlobalPreferenceLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelGlobalPreferenceLocalService
 * @see com.ext.portlet.service.base.ModelGlobalPreferenceLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ModelGlobalPreferenceLocalServiceImpl
 * @generated
 */
public class ModelGlobalPreferenceLocalServiceUtil {
    private static ModelGlobalPreferenceLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.ModelGlobalPreferenceLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the model global preference to the database. Also notifies the appropriate model listeners.
    *
    * @param modelGlobalPreference the model global preference
    * @return the model global preference that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference addModelGlobalPreference(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addModelGlobalPreference(modelGlobalPreference);
    }

    /**
    * Creates a new model global preference with the primary key. Does not add the model global preference to the database.
    *
    * @param modelGlobalPreferencePK the primary key for the new model global preference
    * @return the new model global preference
    */
    public static com.ext.portlet.model.ModelGlobalPreference createModelGlobalPreference(
        long modelGlobalPreferencePK) {
        return getService().createModelGlobalPreference(modelGlobalPreferencePK);
    }

    /**
    * Deletes the model global preference with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelGlobalPreferencePK the primary key of the model global preference
    * @throws PortalException if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelGlobalPreference(long modelGlobalPreferencePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelGlobalPreference(modelGlobalPreferencePK);
    }

    /**
    * Deletes the model global preference from the database. Also notifies the appropriate model listeners.
    *
    * @param modelGlobalPreference the model global preference
    * @throws SystemException if a system exception occurred
    */
    public static void deleteModelGlobalPreference(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteModelGlobalPreference(modelGlobalPreference);
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

    public static com.ext.portlet.model.ModelGlobalPreference fetchModelGlobalPreference(
        long modelGlobalPreferencePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchModelGlobalPreference(modelGlobalPreferencePK);
    }

    /**
    * Returns the model global preference with the primary key.
    *
    * @param modelGlobalPreferencePK the primary key of the model global preference
    * @return the model global preference
    * @throws PortalException if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference getModelGlobalPreference(
        long modelGlobalPreferencePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelGlobalPreference(modelGlobalPreferencePK);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model global preferences.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model global preferences
    * @param end the upper bound of the range of model global preferences (not inclusive)
    * @return the range of model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelGlobalPreference> getModelGlobalPreferences(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelGlobalPreferences(start, end);
    }

    /**
    * Returns the number of model global preferences.
    *
    * @return the number of model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static int getModelGlobalPreferencesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getModelGlobalPreferencesCount();
    }

    /**
    * Updates the model global preference in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelGlobalPreference the model global preference
    * @return the model global preference that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference updateModelGlobalPreference(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateModelGlobalPreference(modelGlobalPreference);
    }

    /**
    * Updates the model global preference in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelGlobalPreference the model global preference
    * @param merge whether to merge the model global preference with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model global preference that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference updateModelGlobalPreference(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateModelGlobalPreference(modelGlobalPreference, merge);
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

    public static boolean isVisible(edu.mit.cci.simulation.client.Simulation s)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().isVisible(s);
    }

    public static void setVisible(edu.mit.cci.simulation.client.Simulation s,
        boolean visible)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setVisible(s, visible);
    }

    public static int getWeight(edu.mit.cci.simulation.client.Simulation s)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getWeight(s);
    }

    public static void setWeight(edu.mit.cci.simulation.client.Simulation s,
        int weight) throws com.liferay.portal.kernel.exception.SystemException {
        getService().setWeight(s, weight);
    }

    public static java.lang.Long getExpertEvaluationPageId(
        edu.mit.cci.simulation.client.Simulation s)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getExpertEvaluationPageId(s);
    }

    public static void setExpertEvaluationPageId(
        edu.mit.cci.simulation.client.Simulation s, java.lang.Long pageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().setExpertEvaluationPageId(s, pageId);
    }

    public static java.util.List<com.ext.portlet.model.ModelGlobalPreference> findByCategory(
        com.ext.portlet.model.ModelCategory category)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findByCategory(category);
    }

    public static com.ext.portlet.model.ModelCategory getCategory(
        edu.mit.cci.simulation.client.Simulation sim)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCategory(sim);
    }

    public static void updateModelCategory(
        com.ext.portlet.model.ModelCategory cat,
        edu.mit.cci.simulation.client.Simulation sim)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().updateModelCategory(cat, sim);
    }

    public static void clearService() {
        _service = null;
    }

    public static ModelGlobalPreferenceLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ModelGlobalPreferenceLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ModelGlobalPreferenceLocalService.class.getName(),
                    portletClassLoader);

            _service = new ModelGlobalPreferenceLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ModelGlobalPreferenceLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ModelGlobalPreferenceLocalService.class);
        }

        return _service;
    }

    public void setService(ModelGlobalPreferenceLocalService service) {
        MethodCache.remove(ModelGlobalPreferenceLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ModelGlobalPreferenceLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ModelGlobalPreferenceLocalService.class);
    }
}
