package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelInputItemLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelInputItemLocalService
 * @generated
 */
public class ModelInputItemLocalServiceWrapper
    implements ModelInputItemLocalService,
        ServiceWrapper<ModelInputItemLocalService> {
    private ModelInputItemLocalService _modelInputItemLocalService;

    public ModelInputItemLocalServiceWrapper(
        ModelInputItemLocalService modelInputItemLocalService) {
        _modelInputItemLocalService = modelInputItemLocalService;
    }

    /**
    * Adds the model input item to the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItem the model input item
    * @return the model input item that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelInputItem addModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.addModelInputItem(modelInputItem);
    }

    /**
    * Creates a new model input item with the primary key. Does not add the model input item to the database.
    *
    * @param modelInputItemPK the primary key for the new model input item
    * @return the new model input item
    */
    @Override
    public com.ext.portlet.model.ModelInputItem createModelInputItem(
        long modelInputItemPK) {
        return _modelInputItemLocalService.createModelInputItem(modelInputItemPK);
    }

    /**
    * Deletes the model input item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @return the model input item that was removed
    * @throws PortalException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelInputItem deleteModelInputItem(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.deleteModelInputItem(modelInputItemPK);
    }

    /**
    * Deletes the model input item from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItem the model input item
    * @return the model input item that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelInputItem deleteModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.deleteModelInputItem(modelInputItem);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _modelInputItemLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelInputItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelInputItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ModelInputItem fetchModelInputItem(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.fetchModelInputItem(modelInputItemPK);
    }

    /**
    * Returns the model input item with the primary key.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @return the model input item
    * @throws PortalException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelInputItem getModelInputItem(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.getModelInputItem(modelInputItemPK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model input items.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelInputItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of model input items
    * @param end the upper bound of the range of model input items (not inclusive)
    * @return the range of model input items
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ModelInputItem> getModelInputItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.getModelInputItems(start, end);
    }

    /**
    * Returns the number of model input items.
    *
    * @return the number of model input items
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getModelInputItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.getModelInputItemsCount();
    }

    /**
    * Updates the model input item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelInputItem the model input item
    * @return the model input item that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelInputItem updateModelInputItem(
        com.ext.portlet.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.updateModelInputItem(modelInputItem);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelInputItemLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelInputItemLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelInputItemLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ModelInputItem> getItemsForModel(
        edu.mit.cci.roma.client.Simulation sim) {
        return _modelInputItemLocalService.getItemsForModel(sim);
    }

    @Override
    public com.ext.portlet.model.ModelInputItem getItemForMetaData(
        java.lang.Long modelId, edu.mit.cci.roma.client.MetaData md) {
        return _modelInputItemLocalService.getItemForMetaData(modelId, md);
    }

    @Override
    public java.util.List<com.ext.portlet.model.ModelInputItem> getItemForGroupId(
        java.lang.Long groupid) {
        return _modelInputItemLocalService.getItemForGroupId(groupid);
    }

    @Override
    public edu.mit.cci.roma.client.MetaData getMetaData(
        com.ext.portlet.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return _modelInputItemLocalService.getMetaData(item);
    }

    @Override
    public edu.mit.cci.roma.client.Simulation getModel(
        com.ext.portlet.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return _modelInputItemLocalService.getModel(item);
    }

    @Override
    public java.util.Map<java.lang.String, java.lang.String> getPropertyMap(
        com.ext.portlet.model.ModelInputItem item) {
        return _modelInputItemLocalService.getPropertyMap(item);
    }

    @Override
    public void saveProperties(com.ext.portlet.model.ModelInputItem item,
        java.util.Map<java.lang.String, java.lang.String> props)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputItemLocalService.saveProperties(item, props);
    }

    @Override
    public void store(com.ext.portlet.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputItemLocalService.store(item);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelInputItemLocalService getWrappedModelInputItemLocalService() {
        return _modelInputItemLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelInputItemLocalService(
        ModelInputItemLocalService modelInputItemLocalService) {
        _modelInputItemLocalService = modelInputItemLocalService;
    }

    @Override
    public ModelInputItemLocalService getWrappedService() {
        return _modelInputItemLocalService;
    }

    @Override
    public void setWrappedService(
        ModelInputItemLocalService modelInputItemLocalService) {
        _modelInputItemLocalService = modelInputItemLocalService;
    }
}
