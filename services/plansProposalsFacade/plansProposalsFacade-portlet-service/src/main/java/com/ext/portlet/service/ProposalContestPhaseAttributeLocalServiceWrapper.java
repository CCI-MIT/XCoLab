package com.ext.portlet.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ProposalContestPhaseAttributeLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProposalContestPhaseAttributeLocalService
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
    public com.ext.portlet.model.ProposalContestPhaseAttribute createProposalContestPhaseAttribute(
        long id) {
        return _proposalContestPhaseAttributeLocalService.createProposalContestPhaseAttribute(id);
    }

    /**
    * Deletes the proposal contest phase attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal contest phase attribute
    * @throws PortalException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteProposalContestPhaseAttribute(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeLocalService.deleteProposalContestPhaseAttribute(id);
    }

    /**
    * Deletes the proposal contest phase attribute from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalContestPhaseAttribute the proposal contest phase attribute
    * @throws SystemException if a system exception occurred
    */
    public void deleteProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeLocalService.deleteProposalContestPhaseAttribute(proposalContestPhaseAttribute);
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
        return _proposalContestPhaseAttributeLocalService.dynamicQuery(dynamicQuery);
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
        return _proposalContestPhaseAttributeLocalService.dynamicQuery(dynamicQuery,
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
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.dynamicQueryCount(dynamicQuery);
    }

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
    public com.ext.portlet.model.ProposalContestPhaseAttribute getProposalContestPhaseAttribute(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttribute(id);
    }

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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @return the range of proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
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
    public com.ext.portlet.model.ProposalContestPhaseAttribute updateProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.updateProposalContestPhaseAttribute(proposalContestPhaseAttribute);
    }

    /**
    * Updates the proposal contest phase attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalContestPhaseAttribute the proposal contest phase attribute
    * @param merge whether to merge the proposal contest phase attribute with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the proposal contest phase attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute updateProposalContestPhaseAttribute(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.updateProposalContestPhaseAttribute(proposalContestPhaseAttribute,
            merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _proposalContestPhaseAttributeLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _proposalContestPhaseAttributeLocalService.setBeanIdentifier(beanIdentifier);
    }

    /**
    * <p>Returns list of proposal phase attributes associated with given proposal in context of a phase</p>
    *
    * @param proposalId id of a proposal
    * @param contestPhaseId id of a phase
    * @return list of proposal phase attributes
    * @throws SystemException in case of LR error
    */
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
    * @return list of proposal phase attributes
    * @throws SystemException in case of LR error
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute getProposalContestPhaseAttributes(
        long proposalId, long contestPhaseId, java.lang.String attributeName,
        long additionalId)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttributes(proposalId,
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
    public com.ext.portlet.model.ProposalContestPhaseAttribute getProposalContestPhaseAttribute(
        long proposalId, long contestPhaseId, java.lang.String attributeName)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException {
        return _proposalContestPhaseAttributeLocalService.getProposalContestPhaseAttribute(proposalId,
            contestPhaseId, attributeName);
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
    * @param value value to be set
    * @throws SystemException in case of LR error
    */
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
    * @param value value to be set
    * @throws SystemException in case of LR error
    */
    public void deleteProposalContestPhaseAttribute(long proposalId,
        long contestPhaseId, java.lang.String attributeName)
        throws com.liferay.portal.kernel.exception.SystemException {
        _proposalContestPhaseAttributeLocalService.deleteProposalContestPhaseAttribute(proposalId,
            contestPhaseId, attributeName);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ProposalContestPhaseAttributeLocalService getWrappedProposalContestPhaseAttributeLocalService() {
        return _proposalContestPhaseAttributeLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedProposalContestPhaseAttributeLocalService(
        ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService) {
        _proposalContestPhaseAttributeLocalService = proposalContestPhaseAttributeLocalService;
    }

    public ProposalContestPhaseAttributeLocalService getWrappedService() {
        return _proposalContestPhaseAttributeLocalService;
    }

    public void setWrappedService(
        ProposalContestPhaseAttributeLocalService proposalContestPhaseAttributeLocalService) {
        _proposalContestPhaseAttributeLocalService = proposalContestPhaseAttributeLocalService;
    }
}
