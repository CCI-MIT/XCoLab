package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelGlobalPreference;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the model global preference service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelGlobalPreferencePersistenceImpl
 * @see ModelGlobalPreferenceUtil
 * @generated
 */
public interface ModelGlobalPreferencePersistence extends BasePersistence<ModelGlobalPreference> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelGlobalPreferenceUtil} to access the model global preference persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the model global preference in the entity cache if it is enabled.
    *
    * @param modelGlobalPreference the model global preference
    */
    public void cacheResult(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference);

    /**
    * Caches the model global preferences in the entity cache if it is enabled.
    *
    * @param modelGlobalPreferences the model global preferences
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ModelGlobalPreference> modelGlobalPreferences);

    /**
    * Creates a new model global preference with the primary key. Does not add the model global preference to the database.
    *
    * @param modelGlobalPreferencePK the primary key for the new model global preference
    * @return the new model global preference
    */
    public com.ext.portlet.model.ModelGlobalPreference create(
        long modelGlobalPreferencePK);

    /**
    * Removes the model global preference with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelGlobalPreferencePK the primary key of the model global preference
    * @return the model global preference that was removed
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelGlobalPreference remove(
        long modelGlobalPreferencePK)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ModelGlobalPreference updateImpl(
        com.ext.portlet.model.ModelGlobalPreference modelGlobalPreference,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model global preference with the primary key or throws a {@link com.ext.portlet.NoSuchModelGlobalPreferenceException} if it could not be found.
    *
    * @param modelGlobalPreferencePK the primary key of the model global preference
    * @return the model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelGlobalPreference findByPrimaryKey(
        long modelGlobalPreferencePK)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model global preference with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelGlobalPreferencePK the primary key of the model global preference
    * @return the model global preference, or <code>null</code> if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelGlobalPreference fetchByPrimaryKey(
        long modelGlobalPreferencePK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model global preference where modelId = &#63; or throws a {@link com.ext.portlet.NoSuchModelGlobalPreferenceException} if it could not be found.
    *
    * @param modelId the model ID
    * @return the matching model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelGlobalPreference findByModelId(
        long modelId)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model global preference where modelId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param modelId the model ID
    * @return the matching model global preference, or <code>null</code> if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelGlobalPreference fetchByModelId(
        long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model global preference where modelId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param modelId the model ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching model global preference, or <code>null</code> if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelGlobalPreference fetchByModelId(
        long modelId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model global preferences where modelCategoryId = &#63;.
    *
    * @param modelCategoryId the model category ID
    * @return the matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelGlobalPreference> findByModelCategoryId(
        long modelCategoryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model global preferences where modelCategoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelCategoryId the model category ID
    * @param start the lower bound of the range of model global preferences
    * @param end the upper bound of the range of model global preferences (not inclusive)
    * @return the range of matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelGlobalPreference> findByModelCategoryId(
        long modelCategoryId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model global preferences where modelCategoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelCategoryId the model category ID
    * @param start the lower bound of the range of model global preferences
    * @param end the upper bound of the range of model global preferences (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelGlobalPreference> findByModelCategoryId(
        long modelCategoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model global preference in the ordered set where modelCategoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelCategoryId the model category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelGlobalPreference findByModelCategoryId_First(
        long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model global preference in the ordered set where modelCategoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelCategoryId the model category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a matching model global preference could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelGlobalPreference findByModelCategoryId_Last(
        long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model global preferences before and after the current model global preference in the ordered set where modelCategoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelGlobalPreferencePK the primary key of the current model global preference
    * @param modelCategoryId the model category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model global preference
    * @throws com.ext.portlet.NoSuchModelGlobalPreferenceException if a model global preference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelGlobalPreference[] findByModelCategoryId_PrevAndNext(
        long modelGlobalPreferencePK, long modelCategoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model global preferences.
    *
    * @return the model global preferences
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelGlobalPreference> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ModelGlobalPreference> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model global preferences.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model global preferences
    * @param end the upper bound of the range of model global preferences (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model global preferences
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelGlobalPreference> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the model global preference where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelId(long modelId)
        throws com.ext.portlet.NoSuchModelGlobalPreferenceException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model global preferences where modelCategoryId = &#63; from the database.
    *
    * @param modelCategoryId the model category ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelCategoryId(long modelCategoryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model global preferences from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model global preferences where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public int countByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model global preferences where modelCategoryId = &#63;.
    *
    * @param modelCategoryId the model category ID
    * @return the number of matching model global preferences
    * @throws SystemException if a system exception occurred
    */
    public int countByModelCategoryId(long modelCategoryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model global preferences.
    *
    * @return the number of model global preferences
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
