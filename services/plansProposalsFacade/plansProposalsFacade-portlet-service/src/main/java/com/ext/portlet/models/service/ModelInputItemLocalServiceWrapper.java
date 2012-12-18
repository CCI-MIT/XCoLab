package com.ext.portlet.models.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelInputItemLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelInputItemLocalService
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
    public com.ext.portlet.models.model.ModelInputItem addModelInputItem(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.addModelInputItem(modelInputItem);
    }

    /**
    * Creates a new model input item with the primary key. Does not add the model input item to the database.
    *
    * @param modelInputItemPK the primary key for the new model input item
    * @return the new model input item
    */
    public com.ext.portlet.models.model.ModelInputItem createModelInputItem(
        long modelInputItemPK) {
        return _modelInputItemLocalService.createModelInputItem(modelInputItemPK);
    }

    /**
    * Deletes the model input item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItemPK the primary key of the model input item
    * @throws PortalException if a model input item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelInputItem(long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _modelInputItemLocalService.deleteModelInputItem(modelInputItemPK);
    }

    /**
    * Deletes the model input item from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputItem the model input item
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelInputItem(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputItemLocalService.deleteModelInputItem(modelInputItem);
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
        return _modelInputItemLocalService.dynamicQuery(dynamicQuery);
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
        return _modelInputItemLocalService.dynamicQuery(dynamicQuery, start, end);
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
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.models.model.ModelInputItem fetchModelInputItem(
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
    public com.ext.portlet.models.model.ModelInputItem getModelInputItem(
        long modelInputItemPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.getModelInputItem(modelInputItemPK);
    }

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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model input items
    * @param end the upper bound of the range of model input items (not inclusive)
    * @return the range of model input items
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputItem> getModelInputItems(
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
    public com.ext.portlet.models.model.ModelInputItem updateModelInputItem(
        com.ext.portlet.models.model.ModelInputItem modelInputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.updateModelInputItem(modelInputItem);
    }

    /**
    * Updates the model input item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelInputItem the model input item
    * @param merge whether to merge the model input item with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model input item that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputItem updateModelInputItem(
        com.ext.portlet.models.model.ModelInputItem modelInputItem,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputItemLocalService.updateModelInputItem(modelInputItem,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _modelInputItemLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelInputItemLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> getItemsForModel(
        edu.mit.cci.simulation.client.Simulation sim) {
        return _modelInputItemLocalService.getItemsForModel(sim);
    }

    public com.ext.portlet.models.model.ModelInputItem getItemForMetaData(
        java.lang.Long modelId, edu.mit.cci.simulation.client.MetaData md) {
        return _modelInputItemLocalService.getItemForMetaData(modelId, md);
    }

    public java.util.List<com.ext.portlet.models.model.ModelInputItem> getItemForGroupId(
        java.lang.Long groupid) {
        return _modelInputItemLocalService.getItemForGroupId(groupid);
    }

    public edu.mit.cci.simulation.client.MetaData getMetaData(
        com.ext.portlet.models.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return _modelInputItemLocalService.getMetaData(item);
    }

    public edu.mit.cci.simulation.client.Simulation getModel(
        com.ext.portlet.models.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException,
            java.io.IOException {
        return _modelInputItemLocalService.getModel(item);
    }

    public java.util.Map<java.lang.String, java.lang.String> getPropertyMap(
        com.ext.portlet.models.model.ModelInputItem item) {
        return _modelInputItemLocalService.getPropertyMap(item);
    }

    public void saveProperties(
        com.ext.portlet.models.model.ModelInputItem item,
        java.util.Map<java.lang.String, java.lang.String> props)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputItemLocalService.saveProperties(item, props);
    }

    public void store(com.ext.portlet.models.model.ModelInputItem item)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputItemLocalService.store(item);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelInputItemLocalService getWrappedModelInputItemLocalService() {
        return _modelInputItemLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelInputItemLocalService(
        ModelInputItemLocalService modelInputItemLocalService) {
        _modelInputItemLocalService = modelInputItemLocalService;
    }

    public ModelInputItemLocalService getWrappedService() {
        return _modelInputItemLocalService;
    }

    public void setWrappedService(
        ModelInputItemLocalService modelInputItemLocalService) {
        _modelInputItemLocalService = modelInputItemLocalService;
    }
}
