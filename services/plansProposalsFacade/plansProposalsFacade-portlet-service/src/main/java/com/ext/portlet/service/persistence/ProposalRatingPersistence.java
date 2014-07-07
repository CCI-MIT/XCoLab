package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalRating;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal rating service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingPersistenceImpl
 * @see ProposalRatingUtil
 * @generated
 */
public interface ProposalRatingPersistence extends BasePersistence<ProposalRating> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalRatingUtil} to access the proposal rating persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the proposal rating in the entity cache if it is enabled.
    *
    * @param proposalRating the proposal rating
    */
    public void cacheResult(com.ext.portlet.model.ProposalRating proposalRating);

    /**
    * Caches the proposal ratings in the entity cache if it is enabled.
    *
    * @param proposalRatings the proposal ratings
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalRating> proposalRatings);

    /**
    * Creates a new proposal rating with the primary key. Does not add the proposal rating to the database.
    *
    * @param id the primary key for the new proposal rating
    * @return the new proposal rating
    */
    public com.ext.portlet.model.ProposalRating create(long id);

    /**
    * Removes the proposal rating with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal rating
    * @return the proposal rating that was removed
    * @throws com.ext.portlet.NoSuchProposalRatingException if a proposal rating with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalRating remove(long id)
        throws com.ext.portlet.NoSuchProposalRatingException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalRating updateImpl(
        com.ext.portlet.model.ProposalRating proposalRating)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal rating with the primary key or throws a {@link com.ext.portlet.NoSuchProposalRatingException} if it could not be found.
    *
    * @param id the primary key of the proposal rating
    * @return the proposal rating
    * @throws com.ext.portlet.NoSuchProposalRatingException if a proposal rating with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalRating findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchProposalRatingException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal rating with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the proposal rating
    * @return the proposal rating, or <code>null</code> if a proposal rating with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalRating fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal ratings.
    *
    * @return the proposal ratings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalRating> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal ratings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal ratings
    * @param end the upper bound of the range of proposal ratings (not inclusive)
    * @return the range of proposal ratings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalRating> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal ratings.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal ratings
    * @param end the upper bound of the range of proposal ratings (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal ratings
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalRating> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal ratings from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal ratings.
    *
    * @return the number of proposal ratings
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
