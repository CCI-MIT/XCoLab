package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalAttributeType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal attribute type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalAttributeTypePersistenceImpl
 * @see ProposalAttributeTypeUtil
 * @generated
 */
public interface ProposalAttributeTypePersistence extends BasePersistence<ProposalAttributeType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalAttributeTypeUtil} to access the proposal attribute type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the proposal attribute type in the entity cache if it is enabled.
    *
    * @param proposalAttributeType the proposal attribute type
    */
    public void cacheResult(
        com.ext.portlet.model.ProposalAttributeType proposalAttributeType);

    /**
    * Caches the proposal attribute types in the entity cache if it is enabled.
    *
    * @param proposalAttributeTypes the proposal attribute types
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalAttributeType> proposalAttributeTypes);

    /**
    * Creates a new proposal attribute type with the primary key. Does not add the proposal attribute type to the database.
    *
    * @param name the primary key for the new proposal attribute type
    * @return the new proposal attribute type
    */
    public com.ext.portlet.model.ProposalAttributeType create(
        java.lang.String name);

    /**
    * Removes the proposal attribute type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param name the primary key of the proposal attribute type
    * @return the proposal attribute type that was removed
    * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalAttributeType remove(
        java.lang.String name)
        throws com.ext.portlet.NoSuchProposalAttributeTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalAttributeType updateImpl(
        com.ext.portlet.model.ProposalAttributeType proposalAttributeType,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal attribute type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalAttributeTypeException} if it could not be found.
    *
    * @param name the primary key of the proposal attribute type
    * @return the proposal attribute type
    * @throws com.ext.portlet.NoSuchProposalAttributeTypeException if a proposal attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalAttributeType findByPrimaryKey(
        java.lang.String name)
        throws com.ext.portlet.NoSuchProposalAttributeTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal attribute type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param name the primary key of the proposal attribute type
    * @return the proposal attribute type, or <code>null</code> if a proposal attribute type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalAttributeType fetchByPrimaryKey(
        java.lang.String name)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal attribute types.
    *
    * @return the proposal attribute types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalAttributeType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal attribute types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal attribute types
    * @param end the upper bound of the range of proposal attribute types (not inclusive)
    * @return the range of proposal attribute types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalAttributeType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal attribute types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of proposal attribute types
    * @param end the upper bound of the range of proposal attribute types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal attribute types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalAttributeType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal attribute types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal attribute types.
    *
    * @return the number of proposal attribute types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
