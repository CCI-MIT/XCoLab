package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalRatingType;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal rating type service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingTypePersistenceImpl
 * @see ProposalRatingTypeUtil
 * @generated
 */
public interface ProposalRatingTypePersistence extends BasePersistence<ProposalRatingType> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalRatingTypeUtil} to access the proposal rating type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the proposal rating type in the entity cache if it is enabled.
    *
    * @param proposalRatingType the proposal rating type
    */
    public void cacheResult(
        com.ext.portlet.model.ProposalRatingType proposalRatingType);

    /**
    * Caches the proposal rating types in the entity cache if it is enabled.
    *
    * @param proposalRatingTypes the proposal rating types
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalRatingType> proposalRatingTypes);

    /**
    * Creates a new proposal rating type with the primary key. Does not add the proposal rating type to the database.
    *
    * @param id the primary key for the new proposal rating type
    * @return the new proposal rating type
    */
    public com.ext.portlet.model.ProposalRatingType create(long id);

    /**
    * Removes the proposal rating type with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal rating type
    * @return the proposal rating type that was removed
    * @throws com.ext.portlet.NoSuchProposalRatingTypeException if a proposal rating type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalRatingType remove(long id)
        throws com.ext.portlet.NoSuchProposalRatingTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalRatingType updateImpl(
        com.ext.portlet.model.ProposalRatingType proposalRatingType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal rating type with the primary key or throws a {@link com.ext.portlet.NoSuchProposalRatingTypeException} if it could not be found.
    *
    * @param id the primary key of the proposal rating type
    * @return the proposal rating type
    * @throws com.ext.portlet.NoSuchProposalRatingTypeException if a proposal rating type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalRatingType findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchProposalRatingTypeException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal rating type with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the proposal rating type
    * @return the proposal rating type, or <code>null</code> if a proposal rating type with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalRatingType fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal rating types.
    *
    * @return the proposal rating types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalRatingType> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal rating types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal rating types
    * @param end the upper bound of the range of proposal rating types (not inclusive)
    * @return the range of proposal rating types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalRatingType> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal rating types.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingTypeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal rating types
    * @param end the upper bound of the range of proposal rating types (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal rating types
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalRatingType> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal rating types from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal rating types.
    *
    * @return the number of proposal rating types
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
