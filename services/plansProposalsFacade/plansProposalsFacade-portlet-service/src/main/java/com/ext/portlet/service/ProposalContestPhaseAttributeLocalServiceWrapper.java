package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ProposalContestPhaseAttributeLocalService}.
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeLocalService
 * @generated
 */
public class ProposalContestPhaseAttributeLocalServiceWrapper
    implements ProposalContestPhaseAttributeLocalService,
        ServiceWrapper<ProposalContestPhaseAttributeLocalService> {
    private ProposalContestPhaseAttributeLocalService _proposalContestPhaseAttributeLocalService;

    public ProposalContestPhaseAttributeLocalServiceWrapper(
        ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService) {
        _proposalContestPhaseAttributeLocalService = proposalContestPhaseAttributeLocalService;
    }

    /**
    * Adds the proposal contest phase attribute to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalContestPhaseAttribute the proposal contest phase attribute
    * @return the proposal contest phase attribute that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute addProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.addProposalContestPhaseAttribute(proposalContestPhaseAttribute);
    }

    /**
    * Creates a new proposal contest phase attribute with the primary key. Does not add the proposal contest phase attribute to the database.
    *
    * @param id the primary key for the new proposal contest phase attribute
    * @return the new proposal contest phase attribute
    */
    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute createProposalContestPhaseAttribute(
        long id) {
        return _proposalContestPhaseAttributeLocalService.createProposalContestPhaseAttribute(id);
    }

    /**
    * Deletes the proposal contest phase attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal contest phase attribute
    * @return the proposal contest phase attribute that was removed
    * @throws PortalException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute deleteProposalContestPhaseAttribute(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.deleteProposalContestPhaseAttribute(id);
    }

    /**
    * Deletes the proposal contest phase attribute from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalContestPhaseAttribute the proposal contest phase attribute
    * @return the proposal contest phase attribute that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute deleteProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.deleteProposalContestPhaseAttribute(proposalContestPhaseAttribute);
    }

    @Override
    public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _proposalContestPhaseAttributeLocalService.dynamicQuery();
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
        return _proposalContestPhaseAttributeLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalContestPhaseAttributeLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _proposalContestPhaseAttributeLocalService.dynamicQuery(dynamicQuery,
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
        return _proposalContestPhaseAttributeLocalService.dynamicQueryCount(dynamicQuery);
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
        return _proposalContestPhaseAttributeLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchProposalContestPhaseAttribute(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.fetchProposalContestPhaseAttribute(id);
    }

    /**
    * Returns the proposal contest phase attribute with the primary key.
    *
    * @param id the primary key of the proposal contest phase attribute
    * @return the proposal contest phase attribute
    * @throws PortalException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute getProposalContestPhaseAttribute(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttribute(id);
    }

    @Override
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the proposal contest phase attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @return the range of proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttributes(start,
            end);
    }

    /**
    * Returns the number of proposal contest phase attributes.
    *
    * @return the number of proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getProposalContestPhaseAttributesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttributesCount();
    }

    /**
    * Updates the proposal contest phase attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalContestPhaseAttribute the proposal contest phase attribute
    * @return the proposal contest phase attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute updateProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.updateProposalContestPhaseAttribute(proposalContestPhaseAttribute);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _proposalContestPhaseAttributeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalContestPhaseAttributeLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _proposalContestPhaseAttributeLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public boolean isAttributeSetAndTrue(long proposalId, long contestPhaseId,
        java.lang.String attributeName, long additionalId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.isAttributeSetAndTrue(proposalId,
            contestPhaseId, attributeName, additionalId);
    }

    @Override
    public boolean persistAttribute(long proposalId, long contestPhaseId,
        java.lang.String attributeName, long additionalId, long numericValue) {
        return _proposalContestPhaseAttributeLocalService.persistAttribute(proposalId,
            contestPhaseId, attributeName, additionalId, numericValue);
    }

    @Override
    public boolean persistAttribute(long proposalId, long contestPhaseId,
        java.lang.String attributeName, long additionalId,
        java.lang.String stringValue) {
        return _proposalContestPhaseAttributeLocalService.persistAttribute(proposalId,
            contestPhaseId, attributeName, additionalId, stringValue);
    }

    @Override
    public boolean persistSelectedJudgesAttribute(long proposalId,
        long contestPhaseId, java.util.List<java.lang.Long> selectedJudges) {
        return _proposalContestPhaseAttributeLocalService.persistSelectedJudgesAttribute(proposalId,
            contestPhaseId, selectedJudges);
    }

    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute getOrCreateAttribute(
        long proposalId, long contestPhaseId, java.lang.String attributeName,
        long additionalId) {
        return _proposalContestPhaseAttributeLocalService.getOrCreateAttribute(proposalId,
            contestPhaseId, attributeName, additionalId);
    }

    /**
    * <p>Returns list of proposal phase attributes associated with given proposal in context of a phase</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a phase
    * @return list of proposal phase attributes
    * @throws SystemException in case of LR error
    */
    @Override
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> getProposalContestPhaseAttributes(
        long proposalId, long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttributes(proposalId,
            contestPhaseId);
    }

    /**
    * <p>Returns a proposal phase attribute by attributeName associated with given proposal in context of a phase and additionalId</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a phase
    * @param attributeName name of attribute
    * @param additionalId additional id to find attribute
    * @return proposal phase attribute
    * @throws SystemException in case of LR error
    */
    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute getProposalContestPhaseAttribute(
        long proposalId, long contestPhaseId, java.lang.String attributeName,
        long additionalId)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttribute(proposalId,
            contestPhaseId, attributeName, additionalId);
    }

    /**
    * <p>Returns proposal phase attribute (if exists)</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a phase
    * @param attributeName name of an attribute
    * @return proposal phase attribute
    * @throws NoSuchProposalContestPhaseAttributeException if there is no attribute with given name
    * @throws SystemException in case of lr error
    */
    @Override
    public com.ext.portlet.model.ProposalContestPhaseAttribute getProposalContestPhaseAttribute(
        long proposalId, long contestPhaseId, java.lang.String attributeName)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttribute(proposalId,
            contestPhaseId, attributeName);
    }

    /**
    * <p>Returns all contest phase attributes</p>
    *
    * @param contestPhaseId id of a phase
    * @return proposal phase attributes
    * @throws NoSuchProposalContestPhaseAttributeException if there is no attribute with given name
    * @throws SystemException in case of lr error
    */
    @Override
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> getAllContestPhaseAttributes(
        long contestPhaseId)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getAllContestPhaseAttributes(contestPhaseId);
    }

    /**
    * <p>Returns all contest phase attributes</p>
    *
    * @param contestPhaseId id of a phase
    * @return proposal phase attributes
    * @throws NoSuchProposalContestPhaseAttributeException if there is no attribute with given name
    * @throws SystemException in case of lr error
    */
    @Override
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> getAllContestPhaseProposalAttributes(
        long contestPhaseId, long proposalId)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getAllContestPhaseProposalAttributes(contestPhaseId,
            proposalId);
    }

    /**
    * <p>Sets numeric value for contest phase attribute, sets default values to other "values" - 0 and null where applicable</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a contest phase
    * @param attributeName name of an attribute
    * @param value value to be set
    * @throws SystemException in case of LR error
    */
    @Override
    public void setProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName, long value)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeLocalService.setProposalContestPhaseAttribute(proposalId,
            contestPhaseId, attributeName, value);
    }

    /**
    * <p>Sets string value for contest phase attribute, sets default values to other "values" - 0 and null where applicable</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a contest phase
    * @param attributeName name of an attribute
    * @param value value to be set
    * @throws SystemException in case of LR error
    */
    @Override
    public void setProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName,
        java.lang.String value)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeLocalService.setProposalContestPhaseAttribute(proposalId,
            contestPhaseId, attributeName, value);
    }

    /**
    * <p>Sets real value for contest phase attribute, sets default values to other "values" - 0 and null where applicable</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a contest phase
    * @param attributeName name of an attribute
    * @param value value to be set
    * @throws SystemException in case of LR error
    */
    @Override
    public void setProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName, double value)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeLocalService.setProposalContestPhaseAttribute(proposalId,
            contestPhaseId, attributeName, value);
    }

    /**
    * <p>Sets values contest phase attribute</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a contest phase
    * @param attributeName name of an attribute
    * @throws SystemException in case of LR error
    */
    @Override
    public void setProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName, long longValue,
        java.lang.String stringValue, double realValue)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeLocalService.setProposalContestPhaseAttribute(proposalId,
            contestPhaseId, attributeName, longValue, stringValue, realValue);
    }

    /**
    * <p>Removes proposal phase attribute with given name</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a contest phase
    * @param attributeName name of an attribute
    * @throws SystemException in case of LR error
    */
    @Override
    public void deleteProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeLocalService.deleteProposalContestPhaseAttribute(proposalId,
            contestPhaseId, attributeName);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ProposalContestPhaseAttributeLocalService getWrappedProposalContestPhaseAttributeLocalService() {
        return _proposalContestPhaseAttributeLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedProposalContestPhaseAttributeLocalService(
        ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService) {
        _proposalContestPhaseAttributeLocalService = proposalContestPhaseAttributeLocalService;
    }

    @Override
    public ProposalContestPhaseAttributeLocalService getWrappedService() {
        return _proposalContestPhaseAttributeLocalService;
    }

    @Override
    public void setWrappedService(
        ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService) {
        _proposalContestPhaseAttributeLocalService = proposalContestPhaseAttributeLocalService;
    }
}
