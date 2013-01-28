package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelInputItem;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the model input item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItemPersistenceImpl
 * @see ModelInputItemUtil
 * @generated
 */
public interface ModelInputItemPersistence extends BasePersistence<ModelInputItem> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelInputItemUtil} to access the model input item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the model input item in the entity cache if it is enabled.
    *
    * @param modelInputItem the model input item
    */
    public void cacheResult(com.ext.portlet.model.ModelInputItem modelInputItem);

    /**
    * Caches the model input items in the entity cache if it is enabled.
    *
    * @param modelInputItems the model input items
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ModelInputItem> modelInputItems);

    /**
    * Creates a new model input item with the primary key. Does not add the model input item to the database.
    *
    * @param modelInputItemPK the primary key for the new model input item
    * @return the new model input item
    */
    public com.ext.portlet.model.ModelInputItem create(long modelInputItemPK);

    /**
    * Removes the model input item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @return the model input item that was removed
    * @throws com.ext.portlet.NoSuchModelInputItemException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem remove(long modelInputItemPK)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ModelInputItem updateImpl(
        com.ext.portlet.model.ModelInputItem modelInputItem, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input item with the primary key or throws a {@link com.ext.portlet.NoSuchModelInputItemException} if it could not be found.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @return the model input item
    * @throws com.ext.portlet.NoSuchModelInputItemException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem findByPrimaryKey(
        long modelInputItemPK)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input item with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @return the model input item, or <code>null</code> if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem fetchByPrimaryKey(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model input items where modelGroupId = &#63;.
    *
    * @param modelGroupId the model group ID
    * @return the matching model input items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelInputItem> findByModelGroupId(
        long modelGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model input items where modelGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelGroupId the model group ID
    * @param start the lower bound of the range of model input items
    * @param end the upper bound of the range of model input items (not inclusive)
    * @return the range of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelInputItem> findByModelGroupId(
        long modelGroupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model input items where modelGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelGroupId the model group ID
    * @param start the lower bound of the range of model input items
    * @param end the upper bound of the range of model input items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelInputItem> findByModelGroupId(
        long modelGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model input item in the ordered set where modelGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelGroupId the model group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model input item
    * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem findByModelGroupId_First(
        long modelGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model input item in the ordered set where modelGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelGroupId the model group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model input item
    * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem findByModelGroupId_Last(
        long modelGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input items before and after the current model input item in the ordered set where modelGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelInputItemPK the primary key of the current model input item
    * @param modelGroupId the model group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model input item
    * @throws com.ext.portlet.NoSuchModelInputItemException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem[] findByModelGroupId_PrevAndNext(
        long modelInputItemPK, long modelGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input item where modelInputItemID = &#63; or throws a {@link com.ext.portlet.NoSuchModelInputItemException} if it could not be found.
    *
    * @param modelInputItemID the model input item i d
    * @return the matching model input item
    * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem findByModelInputId(
        long modelInputItemID)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input item where modelInputItemID = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param modelInputItemID the model input item i d
    * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem fetchByModelInputId(
        long modelInputItemID)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input item where modelInputItemID = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param modelInputItemID the model input item i d
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem fetchByModelInputId(
        long modelInputItemID, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model input items where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the matching model input items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelInputItem> findByModelId(
        long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model input items where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model input items
    * @param end the upper bound of the range of model input items (not inclusive)
    * @return the range of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelInputItem> findByModelId(
        long modelId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model input items where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model input items
    * @param end the upper bound of the range of model input items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelInputItem> findByModelId(
        long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model input item in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model input item
    * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem findByModelId_First(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model input item in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model input item
    * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem findByModelId_Last(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input items before and after the current model input item in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelInputItemPK the primary key of the current model input item
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model input item
    * @throws com.ext.portlet.NoSuchModelInputItemException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem[] findByModelId_PrevAndNext(
        long modelInputItemPK, long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input item where modelId = &#63; and modelInputItemID = &#63; or throws a {@link com.ext.portlet.NoSuchModelInputItemException} if it could not be found.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @return the matching model input item
    * @throws com.ext.portlet.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem findByModelIdModelInputId(
        long modelId, long modelInputItemID)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input item where modelId = &#63; and modelInputItemID = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem fetchByModelIdModelInputId(
        long modelId, long modelInputItemID)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model input item where modelId = &#63; and modelInputItemID = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelInputItem fetchByModelIdModelInputId(
        long modelId, long modelInputItemID, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model input items.
    *
    * @return the model input items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelInputItem> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ModelInputItem> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model input items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model input items
    * @param end the upper bound of the range of model input items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model input items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelInputItem> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model input items where modelGroupId = &#63; from the database.
    *
    * @param modelGroupId the model group ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelGroupId(long modelGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the model input item where modelInputItemID = &#63; from the database.
    *
    * @param modelInputItemID the model input item i d
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelInputId(long modelInputItemID)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model input items where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the model input item where modelId = &#63; and modelInputItemID = &#63; from the database.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelIdModelInputId(long modelId, long modelInputItemID)
        throws com.ext.portlet.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model input items from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model input items where modelGroupId = &#63;.
    *
    * @param modelGroupId the model group ID
    * @return the number of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public int countByModelGroupId(long modelGroupId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model input items where modelInputItemID = &#63;.
    *
    * @param modelInputItemID the model input item i d
    * @return the number of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public int countByModelInputId(long modelInputItemID)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model input items where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public int countByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model input items where modelId = &#63; and modelInputItemID = &#63;.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @return the number of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public int countByModelIdModelInputId(long modelId, long modelInputItemID)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model input items.
    *
    * @return the number of model input items
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
