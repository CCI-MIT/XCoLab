package com.ext.portlet.models.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelOutputChartOrderLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelOutputChartOrderLocalService
 * @generated
 */
public class ModelOutputChartOrderLocalServiceWrapper
    implements ModelOutputChartOrderLocalService,
        ServiceWrapper<ModelOutputChartOrderLocalService> {
    private ModelOutputChartOrderLocalService _modelOutputChartOrderLocalService;

    public ModelOutputChartOrderLocalServiceWrapper(
        ModelOutputChartOrderLocalService modelOutputChartOrderLocalService) {
        _modelOutputChartOrderLocalService = modelOutputChartOrderLocalService;
    }

    /**
    * Adds the model output chart order to the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputChartOrder the model output chart order
    * @return the model output chart order that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputChartOrder addModelOutputChartOrder(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.addModelOutputChartOrder(modelOutputChartOrder);
    }

    /**
    * Creates a new model output chart order with the primary key. Does not add the model output chart order to the database.
    *
    * @param modelOutputChartOrderPK the primary key for the new model output chart order
    * @return the new model output chart order
    */
    public com.ext.portlet.models.model.ModelOutputChartOrder createModelOutputChartOrder(
        long modelOutputChartOrderPK) {
        return _modelOutputChartOrderLocalService.createModelOutputChartOrder(modelOutputChartOrderPK);
    }

    /**
    * Deletes the model output chart order with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputChartOrderPK the primary key of the model output chart order
    * @throws PortalException if a model output chart order with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelOutputChartOrder(long modelOutputChartOrderPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _modelOutputChartOrderLocalService.deleteModelOutputChartOrder(modelOutputChartOrderPK);
    }

    /**
    * Deletes the model output chart order from the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputChartOrder the model output chart order
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelOutputChartOrder(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelOutputChartOrderLocalService.deleteModelOutputChartOrder(modelOutputChartOrder);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.dynamicQuery(dynamicQuery);
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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.dynamicQuery(dynamicQuery,
            start, end);
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
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.models.model.ModelOutputChartOrder fetchModelOutputChartOrder(
        long modelOutputChartOrderPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.fetchModelOutputChartOrder(modelOutputChartOrderPK);
    }

    /**
    * Returns the model output chart order with the primary key.
    *
    * @param modelOutputChartOrderPK the primary key of the model output chart order
    * @return the model output chart order
    * @throws PortalException if a model output chart order with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputChartOrder getModelOutputChartOrder(
        long modelOutputChartOrderPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.getModelOutputChartOrder(modelOutputChartOrderPK);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.getPersistedModel(primaryKeyObj);
    }

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
    public java.util.List<com.ext.portlet.models.model.ModelOutputChartOrder> getModelOutputChartOrders(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.getModelOutputChartOrders(start,
            end);
    }

    /**
    * Returns the number of model output chart orders.
    *
    * @return the number of model output chart orders
    * @throws SystemException if a system exception occurred
    */
    public int getModelOutputChartOrdersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.getModelOutputChartOrdersCount();
    }

    /**
    * Updates the model output chart order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelOutputChartOrder the model output chart order
    * @return the model output chart order that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputChartOrder updateModelOutputChartOrder(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.updateModelOutputChartOrder(modelOutputChartOrder);
    }

    /**
    * Updates the model output chart order in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelOutputChartOrder the model output chart order
    * @param merge whether to merge the model output chart order with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model output chart order that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelOutputChartOrder updateModelOutputChartOrder(
        com.ext.portlet.models.model.ModelOutputChartOrder modelOutputChartOrder,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.updateModelOutputChartOrder(modelOutputChartOrder,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _modelOutputChartOrderLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelOutputChartOrderLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.models.model.ModelOutputChartOrder getChartOrder(
        edu.mit.cci.simulation.client.Simulation sim, java.lang.String label)
        throws com.ext.portlet.models.NoSuchModelOutputChartOrderException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputChartOrderLocalService.getChartOrder(sim, label);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelOutputChartOrderLocalService getWrappedModelOutputChartOrderLocalService() {
        return _modelOutputChartOrderLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelOutputChartOrderLocalService(
        ModelOutputChartOrderLocalService modelOutputChartOrderLocalService) {
        _modelOutputChartOrderLocalService = modelOutputChartOrderLocalService;
    }

    public ModelOutputChartOrderLocalService getWrappedService() {
        return _modelOutputChartOrderLocalService;
    }

    public void setWrappedService(
        ModelOutputChartOrderLocalService modelOutputChartOrderLocalService) {
        _modelOutputChartOrderLocalService = modelOutputChartOrderLocalService;
    }
}
