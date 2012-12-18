package com.ext.portlet.ontology.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OntologyTermEntityLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OntologyTermEntityLocalService
 * @generated
 */
public class OntologyTermEntityLocalServiceWrapper
    implements OntologyTermEntityLocalService,
        ServiceWrapper<OntologyTermEntityLocalService> {
    private OntologyTermEntityLocalService _ontologyTermEntityLocalService;

    public OntologyTermEntityLocalServiceWrapper(
        OntologyTermEntityLocalService ontologyTermEntityLocalService) {
        _ontologyTermEntityLocalService = ontologyTermEntityLocalService;
    }

    /**
    * Adds the ontology term entity to the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTermEntity the ontology term entity
    * @return the ontology term entity that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity addOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermEntityLocalService.addOntologyTermEntity(ontologyTermEntity);
    }

    /**
    * Creates a new ontology term entity with the primary key. Does not add the ontology term entity to the database.
    *
    * @param id the primary key for the new ontology term entity
    * @return the new ontology term entity
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity createOntologyTermEntity(
        long id) {
        return _ontologyTermEntityLocalService.createOntologyTermEntity(id);
    }

    /**
    * Deletes the ontology term entity with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology term entity
    * @throws PortalException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteOntologyTermEntity(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermEntityLocalService.deleteOntologyTermEntity(id);
    }

    /**
    * Deletes the ontology term entity from the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTermEntity the ontology term entity
    * @throws SystemException if a system exception occurred
    */
    public void deleteOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermEntityLocalService.deleteOntologyTermEntity(ontologyTermEntity);
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
        return _ontologyTermEntityLocalService.dynamicQuery(dynamicQuery);
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
        return _ontologyTermEntityLocalService.dynamicQuery(dynamicQuery,
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
        return _ontologyTermEntityLocalService.dynamicQuery(dynamicQuery,
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
        return _ontologyTermEntityLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.ontology.model.OntologyTermEntity fetchOntologyTermEntity(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermEntityLocalService.fetchOntologyTermEntity(id);
    }

    /**
    * Returns the ontology term entity with the primary key.
    *
    * @param id the primary key of the ontology term entity
    * @return the ontology term entity
    * @throws PortalException if a ontology term entity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity getOntologyTermEntity(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermEntityLocalService.getOntologyTermEntity(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermEntityLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ontology term entities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of ontology term entities
    * @param end the upper bound of the range of ontology term entities (not inclusive)
    * @return the range of ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTermEntity> getOntologyTermEntities(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermEntityLocalService.getOntologyTermEntities(start,
            end);
    }

    /**
    * Returns the number of ontology term entities.
    *
    * @return the number of ontology term entities
    * @throws SystemException if a system exception occurred
    */
    public int getOntologyTermEntitiesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermEntityLocalService.getOntologyTermEntitiesCount();
    }

    /**
    * Updates the ontology term entity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologyTermEntity the ontology term entity
    * @return the ontology term entity that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity updateOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermEntityLocalService.updateOntologyTermEntity(ontologyTermEntity);
    }

    /**
    * Updates the ontology term entity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologyTermEntity the ontology term entity
    * @param merge whether to merge the ontology term entity with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the ontology term entity that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTermEntity updateOntologyTermEntity(
        com.ext.portlet.ontology.model.OntologyTermEntity ontologyTermEntity,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermEntityLocalService.updateOntologyTermEntity(ontologyTermEntity,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _ontologyTermEntityLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ontologyTermEntityLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<java.lang.Long> findTagedIdsForClass(
        java.lang.Long termId, java.lang.Class clasz)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermEntityLocalService.findTagedIdsForClass(termId,
            clasz);
    }

    public void store(com.ext.portlet.ontology.model.OntologyTermEntity ote)
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermEntityLocalService.store(ote);
    }

    public void remove(com.ext.portlet.ontology.model.OntologyTermEntity ote)
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermEntityLocalService.remove(ote);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public OntologyTermEntityLocalService getWrappedOntologyTermEntityLocalService() {
        return _ontologyTermEntityLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedOntologyTermEntityLocalService(
        OntologyTermEntityLocalService ontologyTermEntityLocalService) {
        _ontologyTermEntityLocalService = ontologyTermEntityLocalService;
    }

    public OntologyTermEntityLocalService getWrappedService() {
        return _ontologyTermEntityLocalService;
    }

    public void setWrappedService(
        OntologyTermEntityLocalService ontologyTermEntityLocalService) {
        _ontologyTermEntityLocalService = ontologyTermEntityLocalService;
    }
}
