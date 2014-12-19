package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelOutputItem;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the model output item service. This utility wraps {@link ModelOutputItemPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputItemPersistence
 * @see ModelOutputItemPersistenceImpl
 * @generated
 */
public class ModelOutputItemUtil {
    private static ModelOutputItemPersistence _persistence;

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
    public static void clearCache(ModelOutputItem modelOutputItem) {
        getPersistence().clearCache(modelOutputItem);
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
    public static List<ModelOutputItem> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ModelOutputItem> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ModelOutputItem> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ModelOutputItem update(ModelOutputItem modelOutputItem)
        throws SystemException {
        return getPersistence().update(modelOutputItem);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ModelOutputItem update(ModelOutputItem modelOutputItem,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(modelOutputItem, serviceContext);
    }

    /**
    * Returns the model output item where modelOutputItemId = &#63; or throws a {@link com.ext.portlet.NoSuchModelOutputItemException} if it could not be found.
    *
    * @param modelOutputItemId the model output item ID
    * @return the matching model output item
    * @throws com.ext.portlet.NoSuchModelOutputItemException if a matching model output item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem findByModelOutputId(
        long modelOutputItemId)
        throws com.ext.portlet.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByModelOutputId(modelOutputItemId);
    }

    /**
    * Returns the model output item where modelOutputItemId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param modelOutputItemId the model output item ID
    * @return the matching model output item, or <code>null</code> if a matching model output item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem fetchByModelOutputId(
        long modelOutputItemId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByModelOutputId(modelOutputItemId);
    }

    /**
    * Returns the model output item where modelOutputItemId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param modelOutputItemId the model output item ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching model output item, or <code>null</code> if a matching model output item could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem fetchByModelOutputId(
        long modelOutputItemId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByModelOutputId(modelOutputItemId, retrieveFromCache);
    }

    /**
    * Removes the model output item where modelOutputItemId = &#63; from the database.
    *
    * @param modelOutputItemId the model output item ID
    * @return the model output item that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem removeByModelOutputId(
        long modelOutputItemId)
        throws com.ext.portlet.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByModelOutputId(modelOutputItemId);
    }

    /**
    * Returns the number of model output items where modelOutputItemId = &#63;.
    *
    * @param modelOutputItemId the model output item ID
    * @return the number of matching model output items
    * @throws SystemException if a system exception occurred
    */
    public static int countByModelOutputId(long modelOutputItemId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByModelOutputId(modelOutputItemId);
    }

    /**
    * Caches the model output item in the entity cache if it is enabled.
    *
    * @param modelOutputItem the model output item
    */
    public static void cacheResult(
        com.ext.portlet.model.ModelOutputItem modelOutputItem) {
        getPersistence().cacheResult(modelOutputItem);
    }

    /**
    * Caches the model output items in the entity cache if it is enabled.
    *
    * @param modelOutputItems the model output items
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ModelOutputItem> modelOutputItems) {
        getPersistence().cacheResult(modelOutputItems);
    }

    /**
    * Creates a new model output item with the primary key. Does not add the model output item to the database.
    *
    * @param modelOutputItemModifierPK the primary key for the new model output item
    * @return the new model output item
    */
    public static com.ext.portlet.model.ModelOutputItem create(
        long modelOutputItemModifierPK) {
        return getPersistence().create(modelOutputItemModifierPK);
    }

    /**
    * Removes the model output item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @return the model output item that was removed
    * @throws com.ext.portlet.NoSuchModelOutputItemException if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem remove(
        long modelOutputItemModifierPK)
        throws com.ext.portlet.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(modelOutputItemModifierPK);
    }

    public static com.ext.portlet.model.ModelOutputItem updateImpl(
        com.ext.portlet.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(modelOutputItem);
    }

    /**
    * Returns the model output item with the primary key or throws a {@link com.ext.portlet.NoSuchModelOutputItemException} if it could not be found.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @return the model output item
    * @throws com.ext.portlet.NoSuchModelOutputItemException if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem findByPrimaryKey(
        long modelOutputItemModifierPK)
        throws com.ext.portlet.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(modelOutputItemModifierPK);
    }

    /**
    * Returns the model output item with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @return the model output item, or <code>null</code> if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ModelOutputItem fetchByPrimaryKey(
        long modelOutputItemModifierPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(modelOutputItemModifierPK);
    }

    /**
    * Returns all the model output items.
    *
    * @return the model output items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelOutputItem> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the model output items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelOutputItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of model output items
    * @param end the upper bound of the range of model output items (not inclusive)
    * @return the range of model output items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelOutputItem> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the model output items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelOutputItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of model output items
    * @param end the upper bound of the range of model output items (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model output items
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ModelOutputItem> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the model output items from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of model output items.
    *
    * @return the number of model output items
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ModelOutputItemPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ModelOutputItemPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ModelOutputItemPersistence.class.getName());

            ReferenceRegistry.registerReference(ModelOutputItemUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ModelOutputItemPersistence persistence) {
    }
}
