package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.model.ModelInputGroup;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the model input group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputGroupPersistenceImpl
 * @see ModelInputGroupUtil
 * @generated
 */
public interface ModelInputGroupPersistence extends BasePersistence<ModelInputGroup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelInputGroupUtil} to access the model input group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the model input group in the entity cache if it is enabled.
    *
    * @param modelInputGroup the model input group
    */
    public void cacheResult(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup);

    /**
    * Caches the model input groups in the entity cache if it is enabled.
    *
    * @param modelInputGroups the model input groups
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelInputGroup> modelInputGroups);

    /**
    * Creates a new model input group with the primary key. Does not add the model input group to the database.
    *
    * @param modelInputGroupPK the primary key for the new model input group
    * @return the new model input group
    */
    public com.ext.portlet.models.model.ModelInputGroup create(
        java.lang.Long modelInputGroupPK);

    /**
    * Removes the model input group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @return the model input group that was removed
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup remove(
        java.lang.Long modelInputGroupPK)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.models.model.ModelInputGroup updateImpl(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input group with the primary key or throws a {@link com.ext.portlet.models.NoSuchModelInputGroupException} if it could not be found.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @return the model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup findByPrimaryKey(
        java.lang.Long modelInputGroupPK)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @return the model input group, or <code>null</code> if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup fetchByPrimaryKey(
        java.lang.Long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model input groups where parentGroupPK = &#63;.
    *
    * @param parentGroupPK the parent group p k
    * @return the matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByparentModelId(
        java.lang.Long parentGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model input groups where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param parentGroupPK the parent group p k
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @return the range of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByparentModelId(
        java.lang.Long parentGroupPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model input groups where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param parentGroupPK the parent group p k
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByparentModelId(
        java.lang.Long parentGroupPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model input group in the ordered set where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param parentGroupPK the parent group p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a matching model input group could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup findByparentModelId_First(
        java.lang.Long parentGroupPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model input group in the ordered set where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param parentGroupPK the parent group p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a matching model input group could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup findByparentModelId_Last(
        java.lang.Long parentGroupPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input groups before and after the current model input group in the ordered set where parentGroupPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelInputGroupPK the primary key of the current model input group
    * @param parentGroupPK the parent group p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup[] findByparentModelId_PrevAndNext(
        java.lang.Long modelInputGroupPK, java.lang.Long parentGroupPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model input groups where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        java.lang.Long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model input groups where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @return the range of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        java.lang.Long modelId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model input groups where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findByModelId(
        java.lang.Long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model input group in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a matching model input group could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup findByModelId_First(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model input group in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a matching model input group could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup findByModelId_Last(
        java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input groups before and after the current model input group in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelInputGroupPK the primary key of the current model input group
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model input group
    * @throws com.ext.portlet.models.NoSuchModelInputGroupException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup[] findByModelId_PrevAndNext(
        java.lang.Long modelInputGroupPK, java.lang.Long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputGroupException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model input groups.
    *
    * @return the model input groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model input groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model input groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model input groups where parentGroupPK = &#63; from the database.
    *
    * @param parentGroupPK the parent group p k
    * @throws SystemException if a system exception occurred
    */
    public void removeByparentModelId(java.lang.Long parentGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model input groups where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelId(java.lang.Long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model input groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model input groups where parentGroupPK = &#63;.
    *
    * @param parentGroupPK the parent group p k
    * @return the number of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public int countByparentModelId(java.lang.Long parentGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model input groups where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model input groups
    * @throws SystemException if a system exception occurred
    */
    public int countByModelId(java.lang.Long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model input groups.
    *
    * @return the number of model input groups
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
