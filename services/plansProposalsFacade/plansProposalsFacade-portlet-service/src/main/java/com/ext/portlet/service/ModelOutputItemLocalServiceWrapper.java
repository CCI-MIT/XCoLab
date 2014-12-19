package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ModelOutputItemLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ModelOutputItemLocalService
 * @generated
 */
public class ModelOutputItemLocalServiceWrapper
    implements ModelOutputItemLocalService,
        ServiceWrapper<ModelOutputItemLocalService> {
    private ModelOutputItemLocalService _modelOutputItemLocalService;

    public ModelOutputItemLocalServiceWrapper(
        ModelOutputItemLocalService modelOutputItemLocalService) {
        _modelOutputItemLocalService = modelOutputItemLocalService;
    }

    /**
    * Adds the model output item to the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItem the model output item
    * @return the model output item that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelOutputItem addModelOutputItem(
        com.ext.portlet.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.addModelOutputItem(modelOutputItem);
    }

    /**
    * Creates a new model output item with the primary key. Does not add the model output item to the database.
    *
    * @param modelOutputItemModifierPK the primary key for the new model output item
    * @return the new model output item
    */
    @Override
    public com.ext.portlet.model.ModelOutputItem createModelOutputItem(
        long modelOutputItemModifierPK) {
        return _modelOutputItemLocalService.createModelOutputItem(modelOutputItemModifierPK);
    }

    /**
    * Deletes the model output item with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @return the model output item that was removed
    * @throws PortalException if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelOutputItem deleteModelOutputItem(
        long modelOutputItemModifierPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.deleteModelOutputItem(modelOutputItemModifierPK);
    }

    /**
    * Deletes the model output item from the database. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItem the model output item
    * @return the model output item that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelOutputItem deleteModelOutputItem(
        com.ext.portlet.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.deleteModelOutputItem(modelOutputItem);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _modelOutputItemLocalService.dynamicQuery();
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
        return _modelOutputItemLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelOutputItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _modelOutputItemLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ModelOutputItemModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _modelOutputItemLocalService.dynamicQuery(dynamicQuery, start,
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
        return _modelOutputItemLocalService.dynamicQueryCount(dynamicQuery);
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
        return _modelOutputItemLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ModelOutputItem fetchModelOutputItem(
        long modelOutputItemModifierPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.fetchModelOutputItem(modelOutputItemModifierPK);
    }

    /**
    * Returns the model output item with the primary key.
    *
    * @param modelOutputItemModifierPK the primary key of the model output item
    * @return the model output item
    * @throws PortalException if a model output item with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelOutputItem getModelOutputItem(
        long modelOutputItemModifierPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.getModelOutputItem(modelOutputItemModifierPK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.getPersistedModel(primaryKeyObj);
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
    @Override
    public java.util.List<com.ext.portlet.model.ModelOutputItem> getModelOutputItems(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.getModelOutputItems(start, end);
    }

    /**
    * Returns the number of model output items.
    *
    * @return the number of model output items
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getModelOutputItemsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.getModelOutputItemsCount();
    }

    /**
    * Updates the model output item in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelOutputItem the model output item
    * @return the model output item that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ModelOutputItem updateModelOutputItem(
        com.ext.portlet.model.ModelOutputItem modelOutputItem)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.updateModelOutputItem(modelOutputItem);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _modelOutputItemLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelOutputItemLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _modelOutputItemLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    @Override
    public com.ext.portlet.model.ModelOutputItem getOutputItem(
        edu.mit.cci.roma.client.MetaData md)
        throws com.ext.portlet.NoSuchModelOutputItemException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelOutputItemLocalService.getOutputItem(md);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ModelOutputItemLocalService getWrappedModelOutputItemLocalService() {
        return _modelOutputItemLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedModelOutputItemLocalService(
        ModelOutputItemLocalService modelOutputItemLocalService) {
        _modelOutputItemLocalService = modelOutputItemLocalService;
    }

    @Override
    public ModelOutputItemLocalService getWrappedService() {
        return _modelOutputItemLocalService;
    }

    @Override
    public void setWrappedService(
        ModelOutputItemLocalService modelOutputItemLocalService) {
        _modelOutputItemLocalService = modelOutputItemLocalService;
    }
}
