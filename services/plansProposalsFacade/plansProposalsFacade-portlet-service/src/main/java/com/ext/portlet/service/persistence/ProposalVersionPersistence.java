package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalVersion;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal version service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalVersionPersistenceImpl
 * @see ProposalVersionUtil
 * @generated
 */
public interface ProposalVersionPersistence extends BasePersistence<ProposalVersion> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalVersionUtil} to access the proposal version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the proposal versions where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching proposal versions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVersion> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal versions where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @return the range of matching proposal versions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVersion> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal versions where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal versions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVersion> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal version
    * @throws com.ext.portlet.NoSuchProposalVersionException if a matching proposal version could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal version, or <code>null</code> if a matching proposal version could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal version
    * @throws com.ext.portlet.NoSuchProposalVersionException if a matching proposal version could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal version, or <code>null</code> if a matching proposal version could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal versions before and after the current proposal version in the ordered set where proposalId = &#63;.
    *
    * @param proposalVersionPK the primary key of the current proposal version
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal version
    * @throws com.ext.portlet.NoSuchProposalVersionException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion[] findByProposalId_PrevAndNext(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK,
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal versions where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal versions where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching proposal versions
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the proposal version in the entity cache if it is enabled.
    *
    * @param proposalVersion the proposal version
    */
    public void cacheResult(
        com.ext.portlet.model.ProposalVersion proposalVersion);

    /**
    * Caches the proposal versions in the entity cache if it is enabled.
    *
    * @param proposalVersions the proposal versions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalVersion> proposalVersions);

    /**
    * Creates a new proposal version with the primary key. Does not add the proposal version to the database.
    *
    * @param proposalVersionPK the primary key for the new proposal version
    * @return the new proposal version
    */
    public com.ext.portlet.model.ProposalVersion create(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK);

    /**
    * Removes the proposal version with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @return the proposal version that was removed
    * @throws com.ext.portlet.NoSuchProposalVersionException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion remove(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalVersion updateImpl(
        com.ext.portlet.model.ProposalVersion proposalVersion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal version with the primary key or throws a {@link com.ext.portlet.NoSuchProposalVersionException} if it could not be found.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @return the proposal version
    * @throws com.ext.portlet.NoSuchProposalVersionException if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion findByPrimaryKey(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK)
        throws com.ext.portlet.NoSuchProposalVersionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal version with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposalVersionPK the primary key of the proposal version
    * @return the proposal version, or <code>null</code> if a proposal version with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalVersion fetchByPrimaryKey(
        com.ext.portlet.service.persistence.ProposalVersionPK proposalVersionPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal versions.
    *
    * @return the proposal versions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVersion> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal versions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @return the range of proposal versions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVersion> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal versions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalVersionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal versions
    * @param end the upper bound of the range of proposal versions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal versions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalVersion> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal versions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal versions.
    *
    * @return the number of proposal versions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
