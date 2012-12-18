package com.ext.portlet.models.service.persistence;

import com.ext.portlet.models.model.ModelInputItem;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the model input item service. This utility wraps {@link ModelInputItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItemPersistence
 * @see ModelInputItemPersistenceImpl
 * @generated
 */
public class ModelInputItemUtil {
    private static ModelInputItemPersistence _persistence;

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
    public static void clearCache(ModelInputItem modelInputItem) {
        getPersistence().clearCache(modelInputItem);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ModelInputItem> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ModelInputItem> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ModelInputItem> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ModelInputItem update(ModelInputItem modelInputItem,
        boolean merge) throws SystemException {
        return getPersistence().update(modelInputItem, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ModelInputItem update(ModelInputItem modelInputItem,
        boolean merge, ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(modelInputItem, merge, serviceContext);
    }

    /**
    * Caches the model input item in the entity cache if it is enabled.
    *
    * @param modelInputItem the model input item
    */
    public static void cacheResult(
        com.ext.portlet.models.model.ModelInputItem modelInputItem) {
        getPersistence().cacheResult(modelInputItem);
    }

    /**
    * Caches the model input items in the entity cache if it is enabled.
    *
    * @param modelInputItems the model input items
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.models.model.ModelInputItem> modelInputItems) {
        getPersistence().cacheResult(modelInputItems);
    }

    /**
    * Creates a new model input item with the primary key. Does not add the model input item to the database.
    *
    * @param modelInputItemPK the primary key for the new model input item
    * @return the new model input item
    */
    public static com.ext.portlet.models.model.ModelInputItem create(
        long modelInputItemPK) {
        return getPersistence().create(modelInputItemPK);
    }

    /**
    * Removes the model input item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @return the model input item that was removed
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem remove(
        long modelInputItemPK)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(modelInputItemPK);
    }

    public static com.ext.portlet.models.model.ModelInputItem updateImpl(
        com.ext.portlet.models.model.ModelInputItem modelInputItem,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(modelInputItem, merge);
    }

    /**
    * Returns the model input item with the primary key or throws a {@link com.ext.portlet.models.NoSuchModelInputItemException} if it could not be found.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @return the model input item
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem findByPrimaryKey(
        long modelInputItemPK)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(modelInputItemPK);
    }

    /**
    * Returns the model input item with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @return the model input item, or <code>null</code> if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem fetchByPrimaryKey(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(modelInputItemPK);
    }

    /**
    * Returns all the model input items where modelGroupId = &#63;.
    *
    * @param modelGroupId the model group ID
    * @return the matching model input items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelGroupId(
        long modelGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelGroupId(modelGroupId);
    }

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
    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelGroupId(
        long modelGroupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelGroupId(modelGroupId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelGroupId(
        long modelGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelGroupId(modelGroupId, start, end,
            orderByComparator);
    }

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
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem findByModelGroupId_First(
        long modelGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelGroupId_First(modelGroupId, orderByComparator);
    }

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
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem findByModelGroupId_Last(
        long modelGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelGroupId_Last(modelGroupId, orderByComparator);
    }

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
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem[] findByModelGroupId_PrevAndNext(
        long modelInputItemPK, long modelGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelGroupId_PrevAndNext(modelInputItemPK,
            modelGroupId, orderByComparator);
    }

    /**
    * Returns the model input item where modelInputItemID = &#63; or throws a {@link com.ext.portlet.models.NoSuchModelInputItemException} if it could not be found.
    *
    * @param modelInputItemID the model input item i d
    * @return the matching model input item
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem findByModelInputId(
        long modelInputItemID)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelInputId(modelInputItemID);
    }

    /**
    * Returns the model input item where modelInputItemID = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param modelInputItemID the model input item i d
    * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem fetchByModelInputId(
        long modelInputItemID)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByModelInputId(modelInputItemID);
    }

    /**
    * Returns the model input item where modelInputItemID = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param modelInputItemID the model input item i d
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem fetchByModelInputId(
        long modelInputItemID, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByModelInputId(modelInputItemID, retrieveFromCache);
    }

    /**
    * Returns all the model input items where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the matching model input items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelId(
        long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId(modelId);
    }

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
    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelId(
        long modelId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId(modelId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findByModelId(
        long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelId(modelId, start, end, orderByComparator);
    }

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
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem findByModelId_First(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId_First(modelId, orderByComparator);
    }

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
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem findByModelId_Last(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelId_Last(modelId, orderByComparator);
    }

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
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem[] findByModelId_PrevAndNext(
        long modelInputItemPK, long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelId_PrevAndNext(modelInputItemPK, modelId,
            orderByComparator);
    }

    /**
    * Returns the model input item where modelId = &#63; and modelInputItemID = &#63; or throws a {@link com.ext.portlet.models.NoSuchModelInputItemException} if it could not be found.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @return the matching model input item
    * @throws com.ext.portlet.models.NoSuchModelInputItemException if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem findByModelIdModelInputId(
        long modelId, long modelInputItemID)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByModelIdModelInputId(modelId, modelInputItemID);
    }

    /**
    * Returns the model input item where modelId = &#63; and modelInputItemID = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem fetchByModelIdModelInputId(
        long modelId, long modelInputItemID)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByModelIdModelInputId(modelId, modelInputItemID);
    }

    /**
    * Returns the model input item where modelId = &#63; and modelInputItemID = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching model input item, or <code>null</code> if a matching model input item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.models.model.ModelInputItem fetchByModelIdModelInputId(
        long modelId, long modelInputItemID, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByModelIdModelInputId(modelId, modelInputItemID,
            retrieveFromCache);
    }

    /**
    * Returns all the model input items.
    *
    * @return the model input items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
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
    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.models.model.ModelInputItem> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the model input items where modelGroupId = &#63; from the database.
    *
    * @param modelGroupId the model group ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByModelGroupId(long modelGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByModelGroupId(modelGroupId);
    }

    /**
    * Removes the model input item where modelInputItemID = &#63; from the database.
    *
    * @param modelInputItemID the model input item i d
    * @throws SystemException if a system exception occurred
    */
    public static void removeByModelInputId(long modelInputItemID)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByModelInputId(modelInputItemID);
    }

    /**
    * Removes all the model input items where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByModelId(modelId);
    }

    /**
    * Removes the model input item where modelId = &#63; and modelInputItemID = &#63; from the database.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @throws SystemException if a system exception occurred
    */
    public static void removeByModelIdModelInputId(long modelId,
        long modelInputItemID)
        throws com.ext.portlet.models.NoSuchModelInputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByModelIdModelInputId(modelId, modelInputItemID);
    }

    /**
    * Removes all the model input items from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of model input items where modelGroupId = &#63;.
    *
    * @param modelGroupId the model group ID
    * @return the number of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelGroupId(long modelGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByModelGroupId(modelGroupId);
    }

    /**
    * Returns the number of model input items where modelInputItemID = &#63;.
    *
    * @param modelInputItemID the model input item i d
    * @return the number of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelInputId(long modelInputItemID)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByModelInputId(modelInputItemID);
    }

    /**
    * Returns the number of model input items where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByModelId(modelId);
    }

    /**
    * Returns the number of model input items where modelId = &#63; and modelInputItemID = &#63;.
    *
    * @param modelId the model ID
    * @param modelInputItemID the model input item i d
    * @return the number of matching model input items
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelIdModelInputId(long modelId,
        long modelInputItemID)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .countByModelIdModelInputId(modelId, modelInputItemID);
    }

    /**
    * Returns the number of model input items.
    *
    * @return the number of model input items
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ModelInputItemPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ModelInputItemPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.models.service.ClpSerializer.getServletContextName(),
                    ModelInputItemPersistence.class.getName());

            ReferenceRegistry.registerReference(ModelInputItemUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ModelInputItemPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ModelInputItemUtil.class,
            "_persistence");
    }
}
