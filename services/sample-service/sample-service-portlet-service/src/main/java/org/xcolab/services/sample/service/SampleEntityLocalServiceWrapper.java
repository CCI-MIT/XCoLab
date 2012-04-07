package org.xcolab.services.sample.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link SampleEntityLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SampleEntityLocalService
 * @generated
 */
public class SampleEntityLocalServiceWrapper implements SampleEntityLocalService,
    ServiceWrapper<SampleEntityLocalService> {
    private SampleEntityLocalService _sampleEntityLocalService;

    public SampleEntityLocalServiceWrapper(
        SampleEntityLocalService sampleEntityLocalService) {
        _sampleEntityLocalService = sampleEntityLocalService;
    }

    /**
    * Adds the sample entity to the database. Also notifies the appropriate model listeners.
    *
    * @param sampleEntity the sample entity
    * @return the sample entity that was added
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.sample.model.SampleEntity addSampleEntity(
        org.xcolab.services.sample.model.SampleEntity sampleEntity)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _sampleEntityLocalService.addSampleEntity(sampleEntity);
    }

    /**
    * Creates a new sample entity with the primary key. Does not add the sample entity to the database.
    *
    * @param id the primary key for the new sample entity
    * @return the new sample entity
    */
    public org.xcolab.services.sample.model.SampleEntity createSampleEntity(
        long id) {
        return _sampleEntityLocalService.createSampleEntity(id);
    }

    /**
    * Deletes the sample entity with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the sample entity
    * @throws PortalException if a sample entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteSampleEntity(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _sampleEntityLocalService.deleteSampleEntity(id);
    }

    /**
    * Deletes the sample entity from the database. Also notifies the appropriate model listeners.
    *
    * @param sampleEntity the sample entity
    * @throws SystemException if a system exception occurred
    */
    public void deleteSampleEntity(
        org.xcolab.services.sample.model.SampleEntity sampleEntity)
        throws com.liferay.portal.kernel.exception.SystemException {
        _sampleEntityLocalService.deleteSampleEntity(sampleEntity);
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
        return _sampleEntityLocalService.dynamicQuery(dynamicQuery);
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
        return _sampleEntityLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _sampleEntityLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _sampleEntityLocalService.dynamicQueryCount(dynamicQuery);
    }

    public org.xcolab.services.sample.model.SampleEntity fetchSampleEntity(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _sampleEntityLocalService.fetchSampleEntity(id);
    }

    /**
    * Returns the sample entity with the primary key.
    *
    * @param id the primary key of the sample entity
    * @return the sample entity
    * @throws PortalException if a sample entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.sample.model.SampleEntity getSampleEntity(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _sampleEntityLocalService.getSampleEntity(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _sampleEntityLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the sample entities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of sample entities
    * @param end the upper bound of the range of sample entities (not inclusive)
    * @return the range of sample entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<org.xcolab.services.sample.model.SampleEntity> getSampleEntities(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _sampleEntityLocalService.getSampleEntities(start, end);
    }

    /**
    * Returns the number of sample entities.
    *
    * @return the number of sample entities
    * @throws SystemException if a system exception occurred
    */
    public int getSampleEntitiesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _sampleEntityLocalService.getSampleEntitiesCount();
    }

    /**
    * Updates the sample entity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param sampleEntity the sample entity
    * @return the sample entity that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.sample.model.SampleEntity updateSampleEntity(
        org.xcolab.services.sample.model.SampleEntity sampleEntity)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _sampleEntityLocalService.updateSampleEntity(sampleEntity);
    }

    /**
    * Updates the sample entity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param sampleEntity the sample entity
    * @param merge whether to merge the sample entity with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the sample entity that was updated
    * @throws SystemException if a system exception occurred
    */
    public org.xcolab.services.sample.model.SampleEntity updateSampleEntity(
        org.xcolab.services.sample.model.SampleEntity sampleEntity,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _sampleEntityLocalService.updateSampleEntity(sampleEntity, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _sampleEntityLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _sampleEntityLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public SampleEntityLocalService getWrappedSampleEntityLocalService() {
        return _sampleEntityLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedSampleEntityLocalService(
        SampleEntityLocalService sampleEntityLocalService) {
        _sampleEntityLocalService = sampleEntityLocalService;
    }

    public SampleEntityLocalService getWrappedService() {
        return _sampleEntityLocalService;
    }

    public void setWrappedService(
        SampleEntityLocalService sampleEntityLocalService) {
        _sampleEntityLocalService = sampleEntityLocalService;
    }
}
