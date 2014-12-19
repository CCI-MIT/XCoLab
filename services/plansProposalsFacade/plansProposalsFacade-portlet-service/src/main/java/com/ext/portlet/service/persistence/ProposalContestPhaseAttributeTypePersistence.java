package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalContestPhaseAttributeType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal contest phase attribute type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalContestPhaseAttributeTypePersistenceImpl
 * @see ProposalContestPhaseAttributeTypeUtil
 * @generated
 */
public interface ProposalContestPhaseAttributeTypePersistence
    extends BasePersistence<ProposalContestPhaseAttributeType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalContestPhaseAttributeTypeUtil} to access the proposal contest phase attribute type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the proposal contest phase attribute type in the entity cache if it is enabled.
    *
    * @param proposalContestPhaseAttributeType the proposal contest phase attribute type
    */
    public void cacheResult(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType);

    /**
    * Caches the proposal contest phase attribute types in the entity cache if it is enabled.
    *
    * @param proposalContestPhaseAttributeTypes the proposal contest phase attribute types
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalContestPhaseAttributeType> proposalContestPhaseAttributeTypes);

    /**
    * Creates a new proposal contest phase attribute type with the primary key. Does not add the proposal contest phase attribute type to the database.
    *
    * @param name the primary key for the new proposal contest phase attribute type
    * @return the new proposal contest phase attribute type
    */
    public com.ext.portlet.model.ProposalContestPhaseAttributeType create(
        java.lang.String name);

    /**
    * Removes the proposal contest phase attribute type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param name the primary key of the proposal contest phase attribute type
    * @return the proposal contest phase attribute type that was removed
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttributeType remove(
        java.lang.String name)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalContestPhaseAttributeType updateImpl(
        com.ext.portlet.model.ProposalContestPhaseAttributeType proposalContestPhaseAttributeType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attribute type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException} if it could not be found.
    *
    * @param name the primary key of the proposal contest phase attribute type
    * @return the proposal contest phase attribute type
    * @throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException if a proposal contest phase attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttributeType findByPrimaryKey(
        java.lang.String name)
        throws com.ext.portlet.NoSuchProposalContestPhaseAttributeTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal contest phase attribute type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param name the primary key of the proposal contest phase attribute type
    * @return the proposal contest phase attribute type, or <code>null</code> if a proposal contest phase attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalContestPhaseAttributeType fetchByPrimaryKey(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal contest phase attribute types.
    *
    * @return the proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttributeType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal contest phase attribute types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attribute types
    * @param end the upper bound of the range of proposal contest phase attribute types (not inclusive)
    * @return the range of proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttributeType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal contest phase attribute types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalContestPhaseAttributeTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal contest phase attribute types
    * @param end the upper bound of the range of proposal contest phase attribute types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalContestPhaseAttributeType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal contest phase attribute types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal contest phase attribute types.
    *
    * @return the number of proposal contest phase attribute types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
