package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OntologySpaceLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OntologySpaceLocalService
 * @generated
 */
public class OntologySpaceLocalServiceWrapper
    implements OntologySpaceLocalService,
        ServiceWrapper<OntologySpaceLocalService> {
    private OntologySpaceLocalService _ontologySpaceLocalService;

    public OntologySpaceLocalServiceWrapper(
        OntologySpaceLocalService ontologySpaceLocalService) {
        _ontologySpaceLocalService = ontologySpaceLocalService;
    }

    /**
    * Adds the ontology space to the database. Also notifies the appropriate model listeners.
    *
    * @param ontologySpace the ontology space
    * @return the ontology space that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologySpace addOntologySpace(
        com.ext.portlet.model.OntologySpace ontologySpace)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.addOntologySpace(ontologySpace);
    }

    /**
    * Creates a new ontology space with the primary key. Does not add the ontology space to the database.
    *
    * @param id the primary key for the new ontology space
    * @return the new ontology space
    */
    public com.ext.portlet.model.OntologySpace createOntologySpace(long id) {
        return _ontologySpaceLocalService.createOntologySpace(id);
    }

    /**
    * Deletes the ontology space with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology space
    * @throws PortalException if a ontology space with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteOntologySpace(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _ontologySpaceLocalService.deleteOntologySpace(id);
    }

    /**
    * Deletes the ontology space from the database. Also notifies the appropriate model listeners.
    *
    * @param ontologySpace the ontology space
    * @throws SystemException if a system exception occurred
    */
    public void deleteOntologySpace(
        com.ext.portlet.model.OntologySpace ontologySpace)
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologySpaceLocalService.deleteOntologySpace(ontologySpace);
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
        return _ontologySpaceLocalService.dynamicQuery(dynamicQuery);
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
        return _ontologySpaceLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _ontologySpaceLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ontologySpaceLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.model.OntologySpace fetchOntologySpace(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.fetchOntologySpace(id);
    }

    /**
    * Returns the ontology space with the primary key.
    *
    * @param id the primary key of the ontology space
    * @return the ontology space
    * @throws PortalException if a ontology space with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologySpace getOntologySpace(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.getOntologySpace(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ontology spaces.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of ontology spaces
    * @param end the upper bound of the range of ontology spaces (not inclusive)
    * @return the range of ontology spaces
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.OntologySpace> getOntologySpaces(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.getOntologySpaces(start, end);
    }

    /**
    * Returns the number of ontology spaces.
    *
    * @return the number of ontology spaces
    * @throws SystemException if a system exception occurred
    */
    public int getOntologySpacesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.getOntologySpacesCount();
    }

    /**
    * Updates the ontology space in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologySpace the ontology space
    * @return the ontology space that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologySpace updateOntologySpace(
        com.ext.portlet.model.OntologySpace ontologySpace)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.updateOntologySpace(ontologySpace);
    }

    /**
    * Updates the ontology space in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologySpace the ontology space
    * @param merge whether to merge the ontology space with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the ontology space that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.OntologySpace updateOntologySpace(
        com.ext.portlet.model.OntologySpace ontologySpace, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.updateOntologySpace(ontologySpace,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _ontologySpaceLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ontologySpaceLocalService.setBeanIdentifier(beanIdentifier);
    }

    public com.ext.portlet.model.OntologySpace createSpace(
        java.lang.String name, java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.createSpace(name, description);
    }

    public void store(com.ext.portlet.model.OntologySpace space)
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologySpaceLocalService.store(space);
    }

    public com.ext.portlet.model.OntologyTerm getTopTerm(
        com.ext.portlet.model.OntologySpace space)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologySpaceLocalService.getTopTerm(space);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public OntologySpaceLocalService getWrappedOntologySpaceLocalService() {
        return _ontologySpaceLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedOntologySpaceLocalService(
        OntologySpaceLocalService ontologySpaceLocalService) {
        _ontologySpaceLocalService = ontologySpaceLocalService;
    }

    public OntologySpaceLocalService getWrappedService() {
        return _ontologySpaceLocalService;
    }

    public void setWrappedService(
        OntologySpaceLocalService ontologySpaceLocalService) {
        _ontologySpaceLocalService = ontologySpaceLocalService;
    }
}
