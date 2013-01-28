package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelOutputChartOrder;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the model output chart order service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputChartOrderPersistenceImpl
 * @see ModelOutputChartOrderUtil
 * @generated
 */
public interface ModelOutputChartOrderPersistence extends BasePersistence<ModelOutputChartOrder> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelOutputChartOrderUtil} to access the model output chart order persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the model output chart order in the entity cache if it is enabled.
    *
    * @param modelOutputChartOrder the model output chart order
    */
    public void cacheResult(
        com.ext.portlet.model.ModelOutputChartOrder modelOutputChartOrder);

    /**
    * Caches the model output chart orders in the entity cache if it is enabled.
    *
    * @param modelOutputChartOrders the model output chart orders
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ModelOutputChartOrder> modelOutputChartOrders);

    /**
    * Creates a new model output chart order with the primary key. Does not add the model output chart order to the database.
    *
    * @param modelOutputChartOrderPK the primary key for the new model output chart order
    * @return the new model output chart order
    */
    public com.ext.portlet.model.ModelOutputChartOrder create(
        long modelOutputChartOrderPK);

    /**
    * Removes the model output chart order with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputChartOrderPK the primary key of the model output chart order
    * @return the model output chart order that was removed
    * @throws com.ext.portlet.NoSuchModelOutputChartOrderException if a model output chart order with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelOutputChartOrder remove(
        long modelOutputChartOrderPK)
        throws com.ext.portlet.NoSuchModelOutputChartOrderException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ModelOutputChartOrder updateImpl(
        com.ext.portlet.model.ModelOutputChartOrder modelOutputChartOrder,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output chart order with the primary key or throws a {@link com.ext.portlet.NoSuchModelOutputChartOrderException} if it could not be found.
    *
    * @param modelOutputChartOrderPK the primary key of the model output chart order
    * @return the model output chart order
    * @throws com.ext.portlet.NoSuchModelOutputChartOrderException if a model output chart order with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelOutputChartOrder findByPrimaryKey(
        long modelOutputChartOrderPK)
        throws com.ext.portlet.NoSuchModelOutputChartOrderException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output chart order with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelOutputChartOrderPK the primary key of the model output chart order
    * @return the model output chart order, or <code>null</code> if a model output chart order with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelOutputChartOrder fetchByPrimaryKey(
        long modelOutputChartOrderPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output chart order where modelId = &#63; and modelOutputLabel = &#63; or throws a {@link com.ext.portlet.NoSuchModelOutputChartOrderException} if it could not be found.
    *
    * @param modelId the model ID
    * @param modelOutputLabel the model output label
    * @return the matching model output chart order
    * @throws com.ext.portlet.NoSuchModelOutputChartOrderException if a matching model output chart order could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelOutputChartOrder findByModelIdAndLabel(
        long modelId, java.lang.String modelOutputLabel)
        throws com.ext.portlet.NoSuchModelOutputChartOrderException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output chart order where modelId = &#63; and modelOutputLabel = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param modelId the model ID
    * @param modelOutputLabel the model output label
    * @return the matching model output chart order, or <code>null</code> if a matching model output chart order could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelOutputChartOrder fetchByModelIdAndLabel(
        long modelId, java.lang.String modelOutputLabel)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model output chart order where modelId = &#63; and modelOutputLabel = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param modelId the model ID
    * @param modelOutputLabel the model output label
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching model output chart order, or <code>null</code> if a matching model output chart order could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelOutputChartOrder fetchByModelIdAndLabel(
        long modelId, java.lang.String modelOutputLabel,
        boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model output chart orders.
    *
    * @return the model output chart orders
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelOutputChartOrder> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model output chart orders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model output chart orders
    * @param end the upper bound of the range of model output chart orders (not inclusive)
    * @return the range of model output chart orders
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelOutputChartOrder> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model output chart orders.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model output chart orders
    * @param end the upper bound of the range of model output chart orders (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model output chart orders
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelOutputChartOrder> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the model output chart order where modelId = &#63; and modelOutputLabel = &#63; from the database.
    *
    * @param modelId the model ID
    * @param modelOutputLabel the model output label
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelIdAndLabel(long modelId,
        java.lang.String modelOutputLabel)
        throws com.ext.portlet.NoSuchModelOutputChartOrderException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model output chart orders from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model output chart orders where modelId = &#63; and modelOutputLabel = &#63;.
    *
    * @param modelId the model ID
    * @param modelOutputLabel the model output label
    * @return the number of matching model output chart orders
    * @throws SystemException if a system exception occurred
    */
    public int countByModelIdAndLabel(long modelId,
        java.lang.String modelOutputLabel)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model output chart orders.
    *
    * @return the number of model output chart orders
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
