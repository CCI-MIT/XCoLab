package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.model.ModelOutputItem;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the model output item service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputItemPersistenceImpl
 * @see ModelOutputItemUtil
 * @generated
 */
public interface ModelOutputItemPersistence extends BasePersistence<ModelOutputItem> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelOutputItemUtil} to access the model output item persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the model output item in the entity cache if it is enabled.
    *
    * @param modelOutputItem the model output item
    */
    public void cacheResult(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem);

    /**
    * Caches the model output items in the entity cache if it is enabled.
    *
    * @param modelOutputItems the model output items
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelOutputItem> modelOutputItems);

    /**
    * Creates a new model output item with the primary key. Does not add the model output item to the database.
    *
    * @param modelOutputItemModifierPK the primary key for the new model output item
    * @return the new model output item
    */
    public com.ext.portlet.models.model.ModelOutputItem create(
        java.lang.Long modelOutputItemModifierPK);

    /**
    * Removes the model output item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @return the model output item that was removed
    * @throws com.ext.portlet.models.NoSuchModelOutputItemException if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputItem remove(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.models.model.ModelOutputItem updateImpl(
        com.ext.portlet.models.model.ModelOutputItem modelOutputItem,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output item with the primary key or throws a {@link com.ext.portlet.models.NoSuchModelOutputItemException} if it could not be found.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @return the model output item
    * @throws com.ext.portlet.models.NoSuchModelOutputItemException if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputItem findByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output item with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @return the model output item, or <code>null</code> if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputItem fetchByPrimaryKey(
        java.lang.Long modelOutputItemModifierPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output item where modelOutputItemId = &#63; or throws a {@link com.ext.portlet.models.NoSuchModelOutputItemException} if it could not be found.
    *
    * @param modelOutputItemId the model output item ID
    * @return the matching model output item
    * @throws com.ext.portlet.models.NoSuchModelOutputItemException if a matching model output item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputItem findByModelOutputId(
        java.lang.Long modelOutputItemId)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output item where modelOutputItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param modelOutputItemId the model output item ID
    * @return the matching model output item, or <code>null</code> if a matching model output item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputItem fetchByModelOutputId(
        java.lang.Long modelOutputItemId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output item where modelOutputItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param modelOutputItemId the model output item ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching model output item, or <code>null</code> if a matching model output item could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputItem fetchByModelOutputId(
        java.lang.Long modelOutputItemId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model output items.
    *
    * @return the model output items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelOutputItem> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.models.model.ModelOutputItem> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model output items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model output items
    * @param end the upper bound of the range of model output items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model output items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelOutputItem> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the model output item where modelOutputItemId = &#63; from the database.
    *
    * @param modelOutputItemId the model output item ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelOutputId(java.lang.Long modelOutputItemId)
        throws com.ext.portlet.models.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model output items from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model output items where modelOutputItemId = &#63;.
    *
    * @param modelOutputItemId the model output item ID
    * @return the number of matching model output items
    * @throws SystemException if a system exception occurred
    */
    public int countByModelOutputId(java.lang.Long modelOutputItemId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model output items.
    *
    * @return the number of model output items
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
