package com.ext.portlet.ontology.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link OntologyTermLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       OntologyTermLocalService
 * @generated
 */
public class OntologyTermLocalServiceWrapper implements OntologyTermLocalService,
    ServiceWrapper<OntologyTermLocalService> {
    private OntologyTermLocalService _ontologyTermLocalService;

    public OntologyTermLocalServiceWrapper(
        OntologyTermLocalService ontologyTermLocalService) {
        _ontologyTermLocalService = ontologyTermLocalService;
    }

    /**
    * Adds the ontology term to the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @return the ontology term that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTerm addOntologyTerm(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.addOntologyTerm(ontologyTerm);
    }

    /**
    * Creates a new ontology term with the primary key. Does not add the ontology term to the database.
    *
    * @param id the primary key for the new ontology term
    * @return the new ontology term
    */
    public com.ext.portlet.ontology.model.OntologyTerm createOntologyTerm(
        long id) {
        return _ontologyTermLocalService.createOntologyTerm(id);
    }

    /**
    * Deletes the ontology term with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the ontology term
    * @throws PortalException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteOntologyTerm(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermLocalService.deleteOntologyTerm(id);
    }

    /**
    * Deletes the ontology term from the database. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @throws SystemException if a system exception occurred
    */
    public void deleteOntologyTerm(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermLocalService.deleteOntologyTerm(ontologyTerm);
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
        return _ontologyTermLocalService.dynamicQuery(dynamicQuery);
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
        return _ontologyTermLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _ontologyTermLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _ontologyTermLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.ontology.model.OntologyTerm fetchOntologyTerm(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.fetchOntologyTerm(id);
    }

    /**
    * Returns the ontology term with the primary key.
    *
    * @param id the primary key of the ontology term
    * @return the ontology term
    * @throws PortalException if a ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTerm getOntologyTerm(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.getOntologyTerm(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ontology terms.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of ontology terms
    * @param end the upper bound of the range of ontology terms (not inclusive)
    * @return the range of ontology terms
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getOntologyTerms(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.getOntologyTerms(start, end);
    }

    /**
    * Returns the number of ontology terms.
    *
    * @return the number of ontology terms
    * @throws SystemException if a system exception occurred
    */
    public int getOntologyTermsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.getOntologyTermsCount();
    }

    /**
    * Updates the ontology term in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @return the ontology term that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTerm updateOntologyTerm(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.updateOntologyTerm(ontologyTerm);
    }

    /**
    * Updates the ontology term in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ontologyTerm the ontology term
    * @param merge whether to merge the ontology term with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the ontology term that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.OntologyTerm updateOntologyTerm(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.updateOntologyTerm(ontologyTerm, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _ontologyTermLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ontologyTermLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentId(
        java.lang.Long parentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.findByParentId(parentId);
    }

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> findByParentIdSpaceId(
        java.lang.Long parentId, java.lang.Long spaceId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.findByParentIdSpaceId(parentId, spaceId);
    }

    public com.ext.portlet.ontology.model.OntologyTerm createTerm(
        java.lang.Long parentId, java.lang.String name, java.lang.Long spaceId,
        java.lang.String descriptionUrl)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.createTerm(parentId, name, spaceId,
            descriptionUrl);
    }

    public int countChildTerms(java.lang.Long parentId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.countChildTerms(parentId);
    }

    public void clearClassTags(java.lang.Class clasz, java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermLocalService.clearClassTags(clasz, id);
    }

    public void store(com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermLocalService.store(ontologyTerm);
    }

    public com.ext.portlet.ontology.model.OntologyTerm getParent(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.getParent(ontologyTerm);
    }

    public int getChildTermsCount(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.getChildTermsCount(ontologyTerm);
    }

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getChildTerms(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.getChildTerms(ontologyTerm);
    }

    public com.ext.portlet.ontology.model.OntologySpace getSpace(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.getSpace(ontologyTerm);
    }

    public void tagClass(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm,
        java.lang.Class clasz, java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        _ontologyTermLocalService.tagClass(ontologyTerm, clasz, id);
    }

    public java.util.List<java.lang.Long> findTagedIdsForClass(
        com.ext.portlet.ontology.model.OntologyTerm ontologyTerm,
        java.lang.Class clasz)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _ontologyTermLocalService.findTagedIdsForClass(ontologyTerm,
            clasz);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public OntologyTermLocalService getWrappedOntologyTermLocalService() {
        return _ontologyTermLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedOntologyTermLocalService(
        OntologyTermLocalService ontologyTermLocalService) {
        _ontologyTermLocalService = ontologyTermLocalService;
    }

    public OntologyTermLocalService getWrappedService() {
        return _ontologyTermLocalService;
    }

    public void setWrappedService(
        OntologyTermLocalService ontologyTermLocalService) {
        _ontologyTermLocalService = ontologyTermLocalService;
    }
}
