package com.ext.portlet.ontology.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link FocusAreaLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FocusAreaLocalService
 * @generated
 */
public class FocusAreaLocalServiceWrapper implements FocusAreaLocalService,
    ServiceWrapper<FocusAreaLocalService> {
    private FocusAreaLocalService _focusAreaLocalService;

    public FocusAreaLocalServiceWrapper(
        FocusAreaLocalService focusAreaLocalService) {
        _focusAreaLocalService = focusAreaLocalService;
    }

    /**
    * Adds the focus area to the database. Also notifies the appropriate model listeners.
    *
    * @param focusArea the focus area
    * @return the focus area that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea addFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaLocalService.addFocusArea(focusArea);
    }

    /**
    * Creates a new focus area with the primary key. Does not add the focus area to the database.
    *
    * @param id the primary key for the new focus area
    * @return the new focus area
    */
    public com.ext.portlet.ontology.model.FocusArea createFocusArea(long id) {
        return _focusAreaLocalService.createFocusArea(id);
    }

    /**
    * Deletes the focus area with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the focus area
    * @throws PortalException if a focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteFocusArea(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _focusAreaLocalService.deleteFocusArea(id);
    }

    /**
    * Deletes the focus area from the database. Also notifies the appropriate model listeners.
    *
    * @param focusArea the focus area
    * @throws SystemException if a system exception occurred
    */
    public void deleteFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        _focusAreaLocalService.deleteFocusArea(focusArea);
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
        return _focusAreaLocalService.dynamicQuery(dynamicQuery);
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
        return _focusAreaLocalService.dynamicQuery(dynamicQuery, start, end);
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
        return _focusAreaLocalService.dynamicQuery(dynamicQuery, start, end,
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
        return _focusAreaLocalService.dynamicQueryCount(dynamicQuery);
    }

    public com.ext.portlet.ontology.model.FocusArea fetchFocusArea(long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaLocalService.fetchFocusArea(id);
    }

    /**
    * Returns the focus area with the primary key.
    *
    * @param id the primary key of the focus area
    * @return the focus area
    * @throws PortalException if a focus area with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea getFocusArea(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaLocalService.getFocusArea(id);
    }

    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the focus areas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of focus areas
    * @param end the upper bound of the range of focus areas (not inclusive)
    * @return the range of focus areas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.ontology.model.FocusArea> getFocusAreas(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaLocalService.getFocusAreas(start, end);
    }

    /**
    * Returns the number of focus areas.
    *
    * @return the number of focus areas
    * @throws SystemException if a system exception occurred
    */
    public int getFocusAreasCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaLocalService.getFocusAreasCount();
    }

    /**
    * Updates the focus area in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param focusArea the focus area
    * @return the focus area that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea updateFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaLocalService.updateFocusArea(focusArea);
    }

    /**
    * Updates the focus area in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param focusArea the focus area
    * @param merge whether to merge the focus area with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the focus area that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.ontology.model.FocusArea updateFocusArea(
        com.ext.portlet.ontology.model.FocusArea focusArea, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaLocalService.updateFocusArea(focusArea, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _focusAreaLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _focusAreaLocalService.setBeanIdentifier(beanIdentifier);
    }

    public void store(com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.SystemException {
        _focusAreaLocalService.store(focusArea);
    }

    public java.util.List<com.ext.portlet.ontology.model.OntologyTerm> getTerms(
        com.ext.portlet.ontology.model.FocusArea focusArea)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaLocalService.getTerms(focusArea);
    }

    public void removeTerm(com.ext.portlet.ontology.model.FocusArea focusArea,
        java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _focusAreaLocalService.removeTerm(focusArea, termId);
    }

    public void addTerm(com.ext.portlet.ontology.model.FocusArea focusArea,
        java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _focusAreaLocalService.addTerm(focusArea, termId);
    }

    public void tagClass(com.ext.portlet.ontology.model.FocusArea focusArea,
        java.lang.Class clasz, java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _focusAreaLocalService.tagClass(focusArea, clasz, pk);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public FocusAreaLocalService getWrappedFocusAreaLocalService() {
        return _focusAreaLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedFocusAreaLocalService(
        FocusAreaLocalService focusAreaLocalService) {
        _focusAreaLocalService = focusAreaLocalService;
    }

    public FocusAreaLocalService getWrappedService() {
        return _focusAreaLocalService;
    }

    public void setWrappedService(FocusAreaLocalService focusAreaLocalService) {
        _focusAreaLocalService = focusAreaLocalService;
    }
}
