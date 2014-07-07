package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalRatingValue;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal rating value service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalRatingValuePersistenceImpl
 * @see ProposalRatingValueUtil
 * @generated
 */
public interface ProposalRatingValuePersistence extends BasePersistence<ProposalRatingValue> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalRatingValueUtil} to access the proposal rating value persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the proposal rating value in the entity cache if it is enabled.
    *
    * @param proposalRatingValue the proposal rating value
    */
    public void cacheResult(
        com.ext.portlet.model.ProposalRatingValue proposalRatingValue);

    /**
    * Caches the proposal rating values in the entity cache if it is enabled.
    *
    * @param proposalRatingValues the proposal rating values
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalRatingValue> proposalRatingValues);

    /**
    * Creates a new proposal rating value with the primary key. Does not add the proposal rating value to the database.
    *
    * @param id the primary key for the new proposal rating value
    * @return the new proposal rating value
    */
    public com.ext.portlet.model.ProposalRatingValue create(long id);

    /**
    * Removes the proposal rating value with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the proposal rating value
    * @return the proposal rating value that was removed
    * @throws com.ext.portlet.NoSuchProposalRatingValueException if a proposal rating value with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalRatingValue remove(long id)
        throws com.ext.portlet.NoSuchProposalRatingValueException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalRatingValue updateImpl(
        com.ext.portlet.model.ProposalRatingValue proposalRatingValue)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal rating value with the primary key or throws a {@link com.ext.portlet.NoSuchProposalRatingValueException} if it could not be found.
    *
    * @param id the primary key of the proposal rating value
    * @return the proposal rating value
    * @throws com.ext.portlet.NoSuchProposalRatingValueException if a proposal rating value with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalRatingValue findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchProposalRatingValueException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal rating value with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the proposal rating value
    * @return the proposal rating value, or <code>null</code> if a proposal rating value with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalRatingValue fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal rating values.
    *
    * @return the proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalRatingValue> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal rating values.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal rating values
    * @param end the upper bound of the range of proposal rating values (not inclusive)
    * @return the range of proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalRatingValue> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal rating values.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalRatingValueModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal rating values
    * @param end the upper bound of the range of proposal rating values (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalRatingValue> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal rating values from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal rating values.
    *
    * @return the number of proposal rating values
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
