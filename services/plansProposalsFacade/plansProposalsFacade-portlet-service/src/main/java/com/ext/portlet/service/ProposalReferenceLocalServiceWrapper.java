package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalReferenceLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalReferenceLocalService
 * @generated
 */
public class ProposalReferenceLocalServiceWrapper
    implements ProposalReferenceLocalService,
        ServiceWrapper<ProposalReferenceLocalService> {
    private ProposalReferenceLocalService _proposalReferenceLocalService;

    public ProposalReferenceLocalServiceWrapper(
        ProposalReferenceLocalService proposalReferenceLocalService) {
        _proposalReferenceLocalService = proposalReferenceLocalService;
    }

    /**
    * Adds the proposal reference to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalReference the proposal reference
    * @return the proposal reference that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalReference addProposalReference(
        com.ext.portlet.model.ProposalReference proposalReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalReferenceLocalService.addProposalReference(proposalReference);
    }

    /**
    * Creates a new proposal reference with the primary key. Does not add the proposal reference to the database.
    *
    * @param proposalReferencePK the primary key for the new proposal reference
    * @return the new proposal reference
    */
    @Override
    public com.ext.portlet.model.ProposalReference createProposalReference(
        com.ext.portlet.service.persistence.ProposalReferencePK proposalReferencePK) {
        return _proposalReferenceLocalService.createProposalReference(proposalReferencePK);
    }

    /**
    * Deletes the proposal reference with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalReferencePK the primary key of the proposal reference
    * @return the proposal reference that was removed
    * @throws PortalException if a proposal reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalReference deleteProposalReference(
        com.ext.portlet.service.persistence.ProposalReferencePK proposalReferencePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalReferenceLocalService.deleteProposalReference(proposalReferencePK);
    }

    /**
    * Deletes the proposal reference from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalReference the proposal reference
    * @return the proposal reference that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalReference deleteProposalReference(
        com.ext.portlet.model.ProposalReference proposalReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalReferenceLocalService.deleteProposalReference(proposalReference);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _proposalReferenceLocalService.dynamicQuery();
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
        return _proposalReferenceLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalReferenceLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalReferenceLocalService.dynamicQuery(dynamicQuery, start,
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
        return _proposalReferenceLocalService.dynamicQueryCount(dynamicQuery);
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
        return _proposalReferenceLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ProposalReference fetchProposalReference(
        com.ext.portlet.service.persistence.ProposalReferencePK proposalReferencePK)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalReferenceLocalService.fetchProposalReference(proposalReferencePK);
    }

    /**
    * Returns the proposal reference with the primary key.
    *
    * @param proposalReferencePK the primary key of the proposal reference
    * @return the proposal reference
    * @throws PortalException if a proposal reference with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalReference getProposalReference(
        com.ext.portlet.service.persistence.ProposalReferencePK proposalReferencePK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalReferenceLocalService.getProposalReference(proposalReferencePK);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalReferenceLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal references.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalReferenceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal references
    * @param end the upper bound of the range of proposal references (not inclusive)
    * @return the range of proposal references
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ProposalReference> getProposalReferences(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalReferenceLocalService.getProposalReferences(start, end);
    }

    /**
    * Returns the number of proposal references.
    *
    * @return the number of proposal references
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProposalReferencesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalReferenceLocalService.getProposalReferencesCount();
    }

    /**
    * Updates the proposal reference in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalReference the proposal reference
    * @return the proposal reference that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalReference updateProposalReference(
        com.ext.portlet.model.ProposalReference proposalReference)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalReferenceLocalService.updateProposalReference(proposalReference);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalReferenceLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalReferenceLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalReferenceLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalReferenceLocalService getWrappedProposalReferenceLocalService() {
        return _proposalReferenceLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalReferenceLocalService(
        ProposalReferenceLocalService proposalReferenceLocalService) {
        _proposalReferenceLocalService = proposalReferenceLocalService;
    }

    @Override
    public ProposalReferenceLocalService getWrappedService() {
        return _proposalReferenceLocalService;
    }

    @Override
    public void setWrappedService(
        ProposalReferenceLocalService proposalReferenceLocalService) {
        _proposalReferenceLocalService = proposalReferenceLocalService;
    }
}
