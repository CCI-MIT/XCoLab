package com.ext.portlet.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.PersistedModelLocalService;

/**
 * The interface for the proposal version local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVersionLocalServiceUtil
 * @see com.ext.portlet.service.base.ProposalVersionLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.ProposalVersionLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ProposalVersionLocalService extends PersistedModelLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalVersionLocalServiceUtil} to access the proposal version local service. Add custom service methods to {@link com.ext.portlet.service.impl.ProposalVersionLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the proposal version to the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVersion the proposal version
    * @return the proposal version that was added
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion addProposalVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Creates a new proposal version with the primary key. Does not add the proposal version to the database.
    *
    * @param proposalVersionPK the primary key for the new proposal version
    * @return the new proposal version
    */
    public com.ext.portlet.model.ProposalVersion createProposalVersion(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK);

    /**
    * Deletes the proposal version with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @throws PortalException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public void deleteProposalVersion(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Deletes the proposal version from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVersion the proposal version
    * @throws SystemException if a system exception occurred
    */
    public void deleteProposalVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

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
        int end) throws com.liferay.portal.kernel.exception.SystemException;

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
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.ProposalVersion fetchProposalVersion(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal version with the primary key.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @return the proposal version
    * @throws PortalException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.ProposalVersion getProposalVersion(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal versions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @return the range of proposal versions
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.ProposalVersion> getProposalVersions(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal versions.
    *
    * @return the number of proposal versions
    * @throws SystemException if a system exception occurred
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getProposalVersionsCount()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the proposal version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalVersion the proposal version
    * @return the proposal version that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion updateProposalVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Updates the proposal version in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param proposalVersion the proposal version
    * @param merge whether to merge the proposal version with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the proposal version that was updated
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion updateProposalVersion(
        com.ext.portlet.model.ProposalVersion proposalVersion, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    /**
    * <p>Returns proposal version count for given proposal</p>
    *
    * @param proposalId proposal id
    * @return proposal versions count
    * @throws SystemException
    */
    public long countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * <p>Returns list of proposal versions for given proposal</p>
    *
    * @param proposalId id of a proposal
    * @param start first entity
    * @param end last entity
    * @return list of proposal versions
    * @throws SystemException
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.util.List<com.ext.portlet.model.ProposalVersion> getByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.ext.portlet.model.ProposalVersion getByProposalIdVersion(
        long proposalId, int version)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException;
}
