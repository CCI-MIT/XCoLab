package com.ext.portlet.models.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ModelDiscussionLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ModelDiscussionLocalService
 * @generated
 */
public class ModelDiscussionLocalServiceWrapper
    implements ModelDiscussionLocalService,
        ServiceWrapper<ModelDiscussionLocalService> {
    private ModelDiscussionLocalService _modelDiscussionLocalService;

    public ModelDiscussionLocalServiceWrapper(
        ModelDiscussionLocalService modelDiscussionLocalService) {
        _modelDiscussionLocalService = modelDiscussionLocalService;
    }

    /**
    * Adds the model discussion to the database. Also notifies the appropriate model listeners.
    *
    * @param modelDiscussion the model discussion
    * @return the model discussion that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelDiscussion addModelDiscussion(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelDiscussionLocalService.addModelDiscussion(modelDiscussion);
    }

    /**
    * Creates a new model discussion with the primary key. Does not add the model discussion to the database.
    *
    * @param modelDiscussionId the primary key for the new model discussion
    * @return the new model discussion
    */
    public com.ext.portlet.models.model.ModelDiscussion createModelDiscussion(
        java.lang.Long modelDiscussionId) {
        return _modelDiscussionLocalService.createModelDiscussion(modelDiscussionId);
    }

    /**
    * Deletes the model discussion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelDiscussionId the primary key of the model discussion
    * @throws PortalException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelDiscussion(java.lang.Long modelDiscussionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _modelDiscussionLocalService.deleteModelDiscussion(modelDiscussionId);
    }

    /**
    * Deletes the model discussion from the database. Also notifies the appropriate model listeners.
    *
    * @param modelDiscussion the model discussion
    * @throws SystemException if a system exception occurred
    */
    public void deleteModelDiscussion(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.kernel.exception.SystemException {
        _modelDiscussionLocalService.deleteModelDiscussion(modelDiscussion);
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
        return _modelDiscussionLocalService.dynamicQuery(dynamicQuery);
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
        return _modelDiscussionLocalService.dynamicQuery(dynamicQuery, start,
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
        return _modelDiscussionLocalService.dynamicQuery(dynamicQuery, start,
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
        return _modelDiscussionLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.models.model.ModelDiscussion fetchModelDiscussion(
        java.lang.Long modelDiscussionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelDiscussionLocalService.fetchModelDiscussion(modelDiscussionId);
    }

    /**
    * Returns the model discussion with the primary key.
    *
    * @param modelDiscussionId the primary key of the model discussion
    * @return the model discussion
    * @throws PortalException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelDiscussion getModelDiscussion(
        java.lang.Long modelDiscussionId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelDiscussionLocalService.getModelDiscussion(modelDiscussionId);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _modelDiscussionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the model discussions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @return the range of model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.models.model.ModelDiscussion> getModelDiscussions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelDiscussionLocalService.getModelDiscussions(start, end);
    }

    /**
    * Returns the number of model discussions.
    *
    * @return the number of model discussions
    * @throws SystemException if a system exception occurred
    */
    public int getModelDiscussionsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelDiscussionLocalService.getModelDiscussionsCount();
    }

    /**
    * Updates the model discussion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelDiscussion the model discussion
    * @return the model discussion that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelDiscussion updateModelDiscussion(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelDiscussionLocalService.updateModelDiscussion(modelDiscussion);
    }

    /**
    * Updates the model discussion in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param modelDiscussion the model discussion
    * @param merge whether to merge the model discussion with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the model discussion that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.models.model.ModelDiscussion updateModelDiscussion(
        com.ext.portlet.models.model.ModelDiscussion modelDiscussion,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _modelDiscussionLocalService.updateModelDiscussion(modelDiscussion,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _modelDiscussionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _modelDiscussionLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ModelDiscussionLocalService getWrappedModelDiscussionLocalService() {
        return _modelDiscussionLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedModelDiscussionLocalService(
        ModelDiscussionLocalService modelDiscussionLocalService) {
        _modelDiscussionLocalService = modelDiscussionLocalService;
    }

    public ModelDiscussionLocalService getWrappedService() {
        return _modelDiscussionLocalService;
    }

    public void setWrappedService(
        ModelDiscussionLocalService modelDiscussionLocalService) {
        _modelDiscussionLocalService = modelDiscussionLocalService;
    }
}
