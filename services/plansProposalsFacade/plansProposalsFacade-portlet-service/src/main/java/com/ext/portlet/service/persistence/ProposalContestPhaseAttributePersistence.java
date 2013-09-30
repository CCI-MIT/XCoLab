package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalContestPhaseAttribute;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal contest phase attribute service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributePersistenceImpl
 * @see ProposalContestPhaseAttributeUtil
 * @generated
 */
public interface ProposalContestPhaseAttributePersistence
    extends BasePersistence<ProposalContestPhaseAttribute> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalContestPhaseAttributeUtil} to access the proposal contest phase attribute persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the proposal contest phase attribute in the entity cache if it is enabled.
    *
    * @param proposalContestPhaseAttribute the proposal contest phase attribute
    */
    public void cacheResult(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute);

    /**
    * Caches the proposal contest phase attributes in the entity cache if it is enabled.
    *
    * @param proposalContestPhaseAttributes the proposal contest phase attributes
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> proposalContestPhaseAttributes);

    /**
    * Creates a new proposal contest phase attribute with the primary key. Does not add the proposal contest phase attribute to the database.
    *
    * @param id the primary key for the new proposal contest phase attribute
    * @return the new proposal contest phase attribute
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute create(long id);

    /**
    * Removes the proposal contest phase attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal contest phase attribute
    * @return the proposal contest phase attribute that was removed
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute remove(long id)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalContestPhaseAttribute updateImpl(
        com.ext.portlet.model.ProposalContestPhaseAttribute proposalContestPhaseAttribute,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attribute with the primary key or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeException} if it could not be found.
    *
    * @param id the primary key of the proposal contest phase attribute
    * @return the proposal contest phase attribute
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attribute with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the proposal contest phase attribute
    * @return the proposal contest phase attribute, or <code>null</code> if a proposal contest phase attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttribute fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal contest phase attributes.
    *
    * @return the proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal contest phase attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attributes
    * @param end the upper bound of the range of proposal contest phase attributes (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttribute> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal contest phase attributes from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal contest phase attributes.
    *
    * @return the number of proposal contest phase attributes
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
