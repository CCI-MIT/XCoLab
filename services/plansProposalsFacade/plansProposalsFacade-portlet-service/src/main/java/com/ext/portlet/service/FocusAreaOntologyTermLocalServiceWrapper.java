package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FocusAreaOntologyTermLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see FocusAreaOntologyTermLocalService
 * @generated
 */
public class FocusAreaOntologyTermLocalServiceWrapper
    implements FocusAreaOntologyTermLocalService,
        ServiceWrapper<FocusAreaOntologyTermLocalService> {
    private FocusAreaOntologyTermLocalService _focusAreaOntologyTermLocalService;

    public FocusAreaOntologyTermLocalServiceWrapper(
        FocusAreaOntologyTermLocalService focusAreaOntologyTermLocalService) {
        _focusAreaOntologyTermLocalService = focusAreaOntologyTermLocalService;
    }

    /**
    * Adds the focus area ontology term to the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @return the focus area ontology term that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.FocusAreaOntologyTerm addFocusAreaOntologyTerm(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.addFocusAreaOntologyTerm(focusAreaOntologyTerm);
    }

    /**
    * Creates a new focus area ontology term with the primary key. Does not add the focus area ontology term to the database.
    *
    * @param focusAreaOntologyTermPK the primary key for the new focus area ontology term
    * @return the new focus area ontology term
    */
    @Override
    public com.ext.portlet.model.FocusAreaOntologyTerm createFocusAreaOntologyTerm(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK) {
        return _focusAreaOntologyTermLocalService.createFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    /**
    * Deletes the focus area ontology term with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term that was removed
    * @throws PortalException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.FocusAreaOntologyTerm deleteFocusAreaOntologyTerm(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.deleteFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    /**
    * Deletes the focus area ontology term from the database. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @return the focus area ontology term that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.FocusAreaOntologyTerm deleteFocusAreaOntologyTerm(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.deleteFocusAreaOntologyTerm(focusAreaOntologyTerm);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _focusAreaOntologyTermLocalService.dynamicQuery();
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
        return _focusAreaOntologyTermLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.FocusAreaOntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _focusAreaOntologyTermLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.FocusAreaOntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _focusAreaOntologyTermLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
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
        return _focusAreaOntologyTermLocalService.dynamicQueryCount(dynamicQuery);
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
        return _focusAreaOntologyTermLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.FocusAreaOntologyTerm fetchFocusAreaOntologyTerm(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.fetchFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    /**
    * Returns the focus area ontology term with the primary key.
    *
    * @param focusAreaOntologyTermPK the primary key of the focus area ontology term
    * @return the focus area ontology term
    * @throws PortalException if a focus area ontology term with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.FocusAreaOntologyTerm getFocusAreaOntologyTerm(
        com.ext.portlet.service.persistence.FocusAreaOntologyTermPK focusAreaOntologyTermPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.getFocusAreaOntologyTerm(focusAreaOntologyTermPK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the focus area ontology terms.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.FocusAreaOntologyTermModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of focus area ontology terms
    * @param end the upper bound of the range of focus area ontology terms (not inclusive)
    * @return the range of focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> getFocusAreaOntologyTerms(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.getFocusAreaOntologyTerms(start,
            end);
    }

    /**
    * Returns the number of focus area ontology terms.
    *
    * @return the number of focus area ontology terms
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getFocusAreaOntologyTermsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.getFocusAreaOntologyTermsCount();
    }

    /**
    * Updates the focus area ontology term in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param focusAreaOntologyTerm the focus area ontology term
    * @return the focus area ontology term that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.FocusAreaOntologyTerm updateFocusAreaOntologyTerm(
        com.ext.portlet.model.FocusAreaOntologyTerm focusAreaOntologyTerm)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.updateFocusAreaOntologyTerm(focusAreaOntologyTerm);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _focusAreaOntologyTermLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _focusAreaOntologyTermLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _focusAreaOntologyTermLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.util.List<com.ext.portlet.model.FocusAreaOntologyTerm> findTermsByFocusArea(
        java.lang.Long focusAreaId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.findTermsByFocusArea(focusAreaId);
    }

    @Override
    public void addAreaTerm(java.lang.Long focusAreaId, java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _focusAreaOntologyTermLocalService.addAreaTerm(focusAreaId, termId);
    }

    @Override
    public void removeAreaTerm(java.lang.Long focusAreaId, java.lang.Long termId)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _focusAreaOntologyTermLocalService.removeAreaTerm(focusAreaId, termId);
    }

    @Override
    public void store(com.ext.portlet.model.FocusAreaOntologyTerm faot)
        throws com.liferay.portal.kernel.exception.SystemException {
        _focusAreaOntologyTermLocalService.store(faot);
    }

    @Override
    public com.ext.portlet.model.OntologyTerm getTerm(
        com.ext.portlet.model.FocusAreaOntologyTerm faot)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.getTerm(faot);
    }

    @Override
    public com.ext.portlet.model.FocusArea getArea(
        com.ext.portlet.model.FocusAreaOntologyTerm faot)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _focusAreaOntologyTermLocalService.getArea(faot);
    }

    @Override
    public java.util.List<java.lang.Long> getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(
        long focusAreaId, long ontologySpaceId) throws java.lang.Exception {
        return _focusAreaOntologyTermLocalService.getFocusAreaOntologyTermIdsByFocusAreaAndSpaceId(focusAreaId,
            ontologySpaceId);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public FocusAreaOntologyTermLocalService getWrappedFocusAreaOntologyTermLocalService() {
        return _focusAreaOntologyTermLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedFocusAreaOntologyTermLocalService(
        FocusAreaOntologyTermLocalService focusAreaOntologyTermLocalService) {
        _focusAreaOntologyTermLocalService = focusAreaOntologyTermLocalService;
    }

    @Override
    public FocusAreaOntologyTermLocalService getWrappedService() {
        return _focusAreaOntologyTermLocalService;
    }

    @Override
    public void setWrappedService(
        FocusAreaOntologyTermLocalService focusAreaOntologyTermLocalService) {
        _focusAreaOntologyTermLocalService = focusAreaOntologyTermLocalService;
    }
}
