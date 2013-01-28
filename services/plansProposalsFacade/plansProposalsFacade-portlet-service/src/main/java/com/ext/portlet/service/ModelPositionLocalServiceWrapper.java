package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelPositionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelPositionLocalService
 * @generated
 */
public class ModelPositionLocalServiceWrapper
    implements ModelPositionLocalService,
        ServiceWrapper<ModelPositionLocalService> {
    private ModelPositionLocalService _modelPositionLocalService;

    public ModelPositionLocalServiceWrapper(
        ModelPositionLocalService modelPositionLocalService) {
        _modelPositionLocalService = modelPositionLocalService;
    }

    /**
    * Adds the model position to the database. Also notifies the appropriate model listeners.
    *
    * @param modelPosition the model position
    * @return the model position that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition addModelPosition(
        com.ext.portlet.model.ModelPosition modelPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelPositionLocalService.addModelPosition(modelPosition);
    }

    /**
    * Creates a new model position with the primary key. Does not add the model position to the database.
    *
    * @param id the primary key for the new model position
    * @return the new model position
    */
    public com.ext.portlet.model.ModelPosition createModelPosition(long id) {
        return _modelPositionLocalService.createModelPosition(id);
    }

    /**
    * Deletes the model position with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the model position
    * @throws PortalException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelPosition(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _modelPositionLocalService.deleteModelPosition(id);
    }

    /**
    * Deletes the model position from the database. Also notifies the appropriate model listeners.
    *
    * @param modelPosition the model position
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelPosition(
        com.ext.portlet.model.ModelPosition modelPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelPositionLocalService.deleteModelPosition(modelPosition);
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
        return _modelPositionLocalService.dynamicQuery(dynamicQuery);
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
        return _modelPositionLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _modelPositionLocalService.dynamicQuery(dynamicQuery, start,
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
        return _modelPositionLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.ModelPosition fetchModelPosition(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelPositionLocalService.fetchModelPosition(id);
    }

    /**
    * Returns the model position with the primary key.
    *
    * @param id the primary key of the model position
    * @return the model position
    * @throws PortalException if a model position with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition getModelPosition(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelPositionLocalService.getModelPosition(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelPositionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model positions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model positions
    * @param end the upper bound of the range of model positions (not inclusive)
    * @return the range of model positions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelPosition> getModelPositions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelPositionLocalService.getModelPositions(start, end);
    }

    /**
    * Returns the number of model positions.
    *
    * @return the number of model positions
    * @throws SystemException if a system exception occurred
    */
    public int getModelPositionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelPositionLocalService.getModelPositionsCount();
    }

    /**
    * Updates the model position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelPosition the model position
    * @return the model position that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition updateModelPosition(
        com.ext.portlet.model.ModelPosition modelPosition)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelPositionLocalService.updateModelPosition(modelPosition);
    }

    /**
    * Updates the model position in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelPosition the model position
    * @param merge whether to merge the model position with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model position that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelPosition updateModelPosition(
        com.ext.portlet.model.ModelPosition modelPosition, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelPositionLocalService.updateModelPosition(modelPosition,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _modelPositionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelPositionLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<com.ext.portlet.model.ModelPosition> getModelPositionsByModelId(
        java.lang.Long modelId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelPositionLocalService.getModelPositionsByModelId(modelId);
    }

    public void setModelPositions(java.lang.Long modelId,
        java.util.List<java.lang.Long> positionIds)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelPositionLocalService.setModelPositions(modelId, positionIds);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelPositionLocalService getWrappedModelPositionLocalService() {
        return _modelPositionLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelPositionLocalService(
        ModelPositionLocalService modelPositionLocalService) {
        _modelPositionLocalService = modelPositionLocalService;
    }

    public ModelPositionLocalService getWrappedService() {
        return _modelPositionLocalService;
    }

    public void setWrappedService(
        ModelPositionLocalService modelPositionLocalService) {
        _modelPositionLocalService = modelPositionLocalService;
    }
}
