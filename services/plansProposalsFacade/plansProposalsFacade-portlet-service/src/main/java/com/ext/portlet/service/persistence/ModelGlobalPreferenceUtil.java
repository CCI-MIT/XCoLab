package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelGlobalPreference;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the model global preference service. This utility wraps {@link ModelGlobalPreferencePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelGlobalPreferencePersistence
 * @see ModelGlobalPreferencePersistenceImpl
 * @generated
 */
public class ModelGlobalPreferenceUtil {
    private static ModelGlobalPreferencePersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(ModelGlobalPreference modelGlobalPreference) {
        getPersistence().clearCache(modelGlobalPreference);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ModelGlobalPreference> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ModelGlobalPreference> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ModelGlobalPreference> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ModelGlobalPreference update(
        ModelGlobalPreference modelGlobalPreference) throws SystemException {
        return getPersistence().update(modelGlobalPreference);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ModelGlobalPreference update(
        ModelGlobalPreference modelGlobalPreference,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(modelGlobalPreference, serviceContext);
    }

    /**
    * Returns the model global preference where modelId = &#63; or throws a {@link com.ext.portlet.NoSuchModelGlobalPreferenceException} if it could not be found.
    *
    * @param modelId the model ID
    * @return the matching model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference findByModelId(
        long modelId)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId(modelId);
    }

    /**
    * Returns the model global preference where modelId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param modelId the model ID
    * @return the matching model global preference, or <code>null</code> if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference fetchByModelId(
        long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByModelId(modelId);
    }

    /**
    * Returns the model global preference where modelId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param modelId the model ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching model global preference, or <code>null</code> if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference fetchByModelId(
        long modelId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByModelId(modelId, retrieveFromCache);
    }

    /**
    * Removes the model global preference where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @return the model global preference that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference removeByModelId(
        long modelId)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByModelId(modelId);
    }

    /**
    * Returns the number of model global preferences where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByModelId(modelId);
    }

    /**
    * Returns all the model global preferences where modelCategoryId = &#63;.
    *
    * @param modelCategoryId the model category ID
    * @return the matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelGlobalPreference> findByModelCategoryId(
        long modelCategoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelCategoryId(modelCategoryId);
    }

    /**
    * Returns a range of all the model global preferences where modelCategoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelGlobalPreferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param modelCategoryId the model category ID
    * @param start the lower bound of the range of model global preferences
    * @param end the upper bound of the range of model global preferences (not inclusive)
    * @return the range of matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelGlobalPreference> findByModelCategoryId(
        long modelCategoryId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelCategoryId(modelCategoryId, start, end);
    }

    /**
    * Returns an ordered range of all the model global preferences where modelCategoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelGlobalPreferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param modelCategoryId the model category ID
    * @param start the lower bound of the range of model global preferences
    * @param end the upper bound of the range of model global preferences (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelGlobalPreference> findByModelCategoryId(
        long modelCategoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelCategoryId(modelCategoryId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first model global preference in the ordered set where modelCategoryId = &#63;.
    *
    * @param modelCategoryId the model category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference findByModelCategoryId_First(
        long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelCategoryId_First(modelCategoryId,
            orderByComparator);
    }

    /**
    * Returns the first model global preference in the ordered set where modelCategoryId = &#63;.
    *
    * @param modelCategoryId the model category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model global preference, or <code>null</code> if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference fetchByModelCategoryId_First(
        long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByModelCategoryId_First(modelCategoryId,
            orderByComparator);
    }

    /**
    * Returns the last model global preference in the ordered set where modelCategoryId = &#63;.
    *
    * @param modelCategoryId the model category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference findByModelCategoryId_Last(
        long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelCategoryId_Last(modelCategoryId,
            orderByComparator);
    }

    /**
    * Returns the last model global preference in the ordered set where modelCategoryId = &#63;.
    *
    * @param modelCategoryId the model category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model global preference, or <code>null</code> if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference fetchByModelCategoryId_Last(
        long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByModelCategoryId_Last(modelCategoryId,
            orderByComparator);
    }

    /**
    * Returns the model global preferences before and after the current model global preference in the ordered set where modelCategoryId = &#63;.
    *
    * @param modelGlobalPreferencePK the primary key of the current model global preference
    * @param modelCategoryId the model category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference[] findByModelCategoryId_PrevAndNext(
        long modelGlobalPreferencePK, long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelCategoryId_PrevAndNext(modelGlobalPreferencePK,
            modelCategoryId, orderByComparator);
    }

    /**
    * Removes all the model global preferences where modelCategoryId = &#63; from the database.
    *
    * @param modelCategoryId the model category ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByModelCategoryId(long modelCategoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByModelCategoryId(modelCategoryId);
    }

    /**
    * Returns the number of model global preferences where modelCategoryId = &#63;.
    *
    * @param modelCategoryId the model category ID
    * @return the number of matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelCategoryId(long modelCategoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByModelCategoryId(modelCategoryId);
    }

    /**
    * Caches the model global preference in the entity cache if it is enabled.
    *
    * @param modelGlobalPreference the model global preference
    */
    public static void cacheResult(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference) {
        getPersistence().cacheResult(modelGlobalPreference);
    }

    /**
    * Caches the model global preferences in the entity cache if it is enabled.
    *
    * @param modelGlobalPreferences the model global preferences
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ModelGlobalPreference> modelGlobalPreferences) {
        getPersistence().cacheResult(modelGlobalPreferences);
    }

    /**
    * Creates a new model global preference with the primary key. Does not add the model global preference to the database.
    *
    * @param modelGlobalPreferencePK the primary key for the new model global preference
    * @return the new model global preference
    */
    public static com.ext.portlet.model.ModelGlobalPreference create(
        long modelGlobalPreferencePK) {
        return getPersistence().create(modelGlobalPreferencePK);
    }

    /**
    * Removes the model global preference with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelGlobalPreferencePK the primary key of the model global preference
    * @return the model global preference that was removed
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference remove(
        long modelGlobalPreferencePK)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(modelGlobalPreferencePK);
    }

    public static com.ext.portlet.model.ModelGlobalPreference updateImpl(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(modelGlobalPreference);
    }

    /**
    * Returns the model global preference with the primary key or throws a {@link com.ext.portlet.NoSuchModelGlobalPreferenceException} if it could not be found.
    *
    * @param modelGlobalPreferencePK the primary key of the model global preference
    * @return the model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference findByPrimaryKey(
        long modelGlobalPreferencePK)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(modelGlobalPreferencePK);
    }

    /**
    * Returns the model global preference with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelGlobalPreferencePK the primary key of the model global preference
    * @return the model global preference, or <code>null</code> if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelGlobalPreference fetchByPrimaryKey(
        long modelGlobalPreferencePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(modelGlobalPreferencePK);
    }

    /**
    * Returns all the model global preferences.
    *
    * @return the model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelGlobalPreference> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the model global preferences.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelGlobalPreferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of model global preferences
    * @param end the upper bound of the range of model global preferences (not inclusive)
    * @return the range of model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelGlobalPreference> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the model global preferences.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelGlobalPreferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of model global preferences
    * @param end the upper bound of the range of model global preferences (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelGlobalPreference> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the model global preferences from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of model global preferences.
    *
    * @return the number of model global preferences
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ModelGlobalPreferencePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ModelGlobalPreferencePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ModelGlobalPreferencePersistence.class.getName());

            ReferenceRegistry.registerReference(ModelGlobalPreferenceUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ModelGlobalPreferencePersistence persistence) {
    }
}
