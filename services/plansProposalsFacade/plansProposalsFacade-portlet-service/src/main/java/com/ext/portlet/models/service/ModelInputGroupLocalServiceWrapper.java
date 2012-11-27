package com.ext.portlet.models.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelInputGroupLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelInputGroupLocalService
 * @generated
 */
public class ModelInputGroupLocalServiceWrapper
    implements ModelInputGroupLocalService,
        ServiceWrapper<ModelInputGroupLocalService> {
    private ModelInputGroupLocalService _modelInputGroupLocalService;

    public ModelInputGroupLocalServiceWrapper(
        ModelInputGroupLocalService modelInputGroupLocalService) {
        _modelInputGroupLocalService = modelInputGroupLocalService;
    }

    /**
    * Adds the model input group to the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroup the model input group
    * @return the model input group that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup addModelInputGroup(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputGroupLocalService.addModelInputGroup(modelInputGroup);
    }

    /**
    * Creates a new model input group with the primary key. Does not add the model input group to the database.
    *
    * @param modelInputGroupPK the primary key for the new model input group
    * @return the new model input group
    */
    public com.ext.portlet.models.model.ModelInputGroup createModelInputGroup(
        java.lang.Long modelInputGroupPK) {
        return _modelInputGroupLocalService.createModelInputGroup(modelInputGroupPK);
    }

    /**
    * Deletes the model input group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @throws PortalException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelInputGroup(java.lang.Long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _modelInputGroupLocalService.deleteModelInputGroup(modelInputGroupPK);
    }

    /**
    * Deletes the model input group from the database. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroup the model input group
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelInputGroup(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelInputGroupLocalService.deleteModelInputGroup(modelInputGroup);
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
        return _modelInputGroupLocalService.dynamicQuery(dynamicQuery);
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
        return _modelInputGroupLocalService.dynamicQuery(dynamicQuery, start,
            end);
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
        return _modelInputGroupLocalService.dynamicQuery(dynamicQuery, start,
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
        return _modelInputGroupLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.models.model.ModelInputGroup fetchModelInputGroup(
        java.lang.Long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputGroupLocalService.fetchModelInputGroup(modelInputGroupPK);
    }

    /**
    * Returns the model input group with the primary key.
    *
    * @param modelInputGroupPK the primary key of the model input group
    * @return the model input group
    * @throws PortalException if a model input group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup getModelInputGroup(
        java.lang.Long modelInputGroupPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelInputGroupLocalService.getModelInputGroup(modelInputGroupPK);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelInputGroupLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model input groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model input groups
    * @param end the upper bound of the range of model input groups (not inclusive)
    * @return the range of model input groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> getModelInputGroups(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputGroupLocalService.getModelInputGroups(start, end);
    }

    /**
    * Returns the number of model input groups.
    *
    * @return the number of model input groups
    * @throws SystemException if a system exception occurred
    */
    public int getModelInputGroupsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputGroupLocalService.getModelInputGroupsCount();
    }

    /**
    * Updates the model input group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroup the model input group
    * @return the model input group that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup updateModelInputGroup(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputGroupLocalService.updateModelInputGroup(modelInputGroup);
    }

    /**
    * Updates the model input group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelInputGroup the model input group
    * @param merge whether to merge the model input group with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model input group that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelInputGroup updateModelInputGroup(
        com.ext.portlet.models.model.ModelInputGroup modelInputGroup,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelInputGroupLocalService.updateModelInputGroup(modelInputGroup,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _modelInputGroupLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelInputGroupLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> getInputGroups(
        edu.mit.cci.simulation.client.Simulation sim) {
        return _modelInputGroupLocalService.getInputGroups(sim);
    }

    public java.util.List<com.ext.portlet.models.model.ModelInputGroup> getChildGroups(
        com.ext.portlet.models.model.ModelInputGroup group) {
        return _modelInputGroupLocalService.getChildGroups(group);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelInputGroupLocalService getWrappedModelInputGroupLocalService() {
        return _modelInputGroupLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelInputGroupLocalService(
        ModelInputGroupLocalService modelInputGroupLocalService) {
        _modelInputGroupLocalService = modelInputGroupLocalService;
    }

    public ModelInputGroupLocalService getWrappedService() {
        return _modelInputGroupLocalService;
    }

    public void setWrappedService(
        ModelInputGroupLocalService modelInputGroupLocalService) {
        _modelInputGroupLocalService = modelInputGroupLocalService;
    }
}
