package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ProposalMoveHistory;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal move history service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProposalMoveHistoryPersistenceImpl
 * @see ProposalMoveHistoryUtil
 * @generated
 */
public interface ProposalMoveHistoryPersistence extends BasePersistence<ProposalMoveHistory> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ProposalMoveHistoryUtil} to access the proposal move history persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the proposal move histories where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalId(
        long sourceProposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal move histories where sourceProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceProposalId the source proposal ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalId(
        long sourceProposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal move histories where sourceProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceProposalId the source proposal ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalId(
        long sourceProposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findBySourceProposalId_First(
        long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchBySourceProposalId_First(
        long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findBySourceProposalId_Last(
        long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchBySourceProposalId_Last(
        long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourceProposalId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param sourceProposalId the source proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory[] findBySourceProposalId_PrevAndNext(
        long id_, long sourceProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal move histories where sourceProposalId = &#63; from the database.
    *
    * @param sourceProposalId the source proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySourceProposalId(long sourceProposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal move histories where sourceProposalId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public int countBySourceProposalId(long sourceProposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal move histories where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceContestId(
        long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal move histories where sourceContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceContestId the source contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceContestId(
        long sourceContestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal move histories where sourceContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceContestId the source contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceContestId(
        long sourceContestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findBySourceContestId_First(
        long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchBySourceContestId_First(
        long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findBySourceContestId_Last(
        long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchBySourceContestId_Last(
        long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourceContestId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory[] findBySourceContestId_PrevAndNext(
        long id_, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal move histories where sourceContestId = &#63; from the database.
    *
    * @param sourceContestId the source contest ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySourceContestId(long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal move histories where sourceContestId = &#63;.
    *
    * @param sourceContestId the source contest ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public int countBySourceContestId(long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal move histories where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourcePhaseId(
        long sourcePhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal move histories where sourcePhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourcePhaseId the source phase ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourcePhaseId(
        long sourcePhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal move histories where sourcePhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourcePhaseId the source phase ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourcePhaseId(
        long sourcePhaseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findBySourcePhaseId_First(
        long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchBySourcePhaseId_First(
        long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findBySourcePhaseId_Last(
        long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchBySourcePhaseId_Last(
        long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourcePhaseId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param sourcePhaseId the source phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory[] findBySourcePhaseId_PrevAndNext(
        long id_, long sourcePhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal move histories where sourcePhaseId = &#63; from the database.
    *
    * @param sourcePhaseId the source phase ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySourcePhaseId(long sourcePhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal move histories where sourcePhaseId = &#63;.
    *
    * @param sourcePhaseId the source phase ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public int countBySourcePhaseId(long sourcePhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal move histories where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetProposalId(
        long targetProposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal move histories where targetProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetProposalId the target proposal ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetProposalId(
        long targetProposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal move histories where targetProposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetProposalId the target proposal ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetProposalId(
        long targetProposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findByTargetProposalId_First(
        long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchByTargetProposalId_First(
        long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findByTargetProposalId_Last(
        long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchByTargetProposalId_Last(
        long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where targetProposalId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param targetProposalId the target proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory[] findByTargetProposalId_PrevAndNext(
        long id_, long targetProposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal move histories where targetProposalId = &#63; from the database.
    *
    * @param targetProposalId the target proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByTargetProposalId(long targetProposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal move histories where targetProposalId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public int countByTargetProposalId(long targetProposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal move histories where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetContestId(
        long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal move histories where targetContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetContestId the target contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetContestId(
        long targetContestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal move histories where targetContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetContestId the target contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetContestId(
        long targetContestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findByTargetContestId_First(
        long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchByTargetContestId_First(
        long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findByTargetContestId_Last(
        long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchByTargetContestId_Last(
        long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where targetContestId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param targetContestId the target contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory[] findByTargetContestId_PrevAndNext(
        long id_, long targetContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal move histories where targetContestId = &#63; from the database.
    *
    * @param targetContestId the target contest ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByTargetContestId(long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal move histories where targetContestId = &#63;.
    *
    * @param targetContestId the target contest ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public int countByTargetContestId(long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal move histories where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetPhaseId(
        long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal move histories where targetPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetPhaseId the target phase ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetPhaseId(
        long targetPhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal move histories where targetPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param targetPhaseId the target phase ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findByTargetPhaseId(
        long targetPhaseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findByTargetPhaseId_First(
        long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchByTargetPhaseId_First(
        long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findByTargetPhaseId_Last(
        long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchByTargetPhaseId_Last(
        long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where targetPhaseId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param targetPhaseId the target phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory[] findByTargetPhaseId_PrevAndNext(
        long id_, long targetPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal move histories where targetPhaseId = &#63; from the database.
    *
    * @param targetPhaseId the target phase ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByTargetPhaseId(long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal move histories where targetPhaseId = &#63;.
    *
    * @param targetPhaseId the target phase ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public int countByTargetPhaseId(long targetPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @return the matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findBySourceProposalIdContestId(
        long sourceProposalId, long sourceContestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findBySourceProposalIdContestId_First(
        long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchBySourceProposalIdContestId_First(
        long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findBySourceProposalIdContestId_Last(
        long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchBySourceProposalIdContestId_Last(
        long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move histories before and after the current proposal move history in the ordered set where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param id_ the primary key of the current proposal move history
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory[] findBySourceProposalIdContestId_PrevAndNext(
        long id_, long sourceProposalId, long sourceContestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63; from the database.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @throws SystemException if a system exception occurred
    */
    public void removeBySourceProposalIdContestId(long sourceProposalId,
        long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal move histories where sourceProposalId = &#63; and sourceContestId = &#63;.
    *
    * @param sourceProposalId the source proposal ID
    * @param sourceContestId the source contest ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public int countBySourceProposalIdContestId(long sourceProposalId,
        long sourceContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move history where targetProposalId = &#63; and targetContestId = &#63; or throws a {@link com.ext.portlet.NoSuchProposalMoveHistoryException} if it could not be found.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @return the matching proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findByTargetProposalIdContestId(
        long targetProposalId, long targetContestId)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move history where targetProposalId = &#63; and targetContestId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @return the matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchByTargetProposalIdContestId(
        long targetProposalId, long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move history where targetProposalId = &#63; and targetContestId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching proposal move history, or <code>null</code> if a matching proposal move history could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchByTargetProposalIdContestId(
        long targetProposalId, long targetContestId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the proposal move history where targetProposalId = &#63; and targetContestId = &#63; from the database.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @return the proposal move history that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory removeByTargetProposalIdContestId(
        long targetProposalId, long targetContestId)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal move histories where targetProposalId = &#63; and targetContestId = &#63;.
    *
    * @param targetProposalId the target proposal ID
    * @param targetContestId the target contest ID
    * @return the number of matching proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public int countByTargetProposalIdContestId(long targetProposalId,
        long targetContestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the proposal move history in the entity cache if it is enabled.
    *
    * @param proposalMoveHistory the proposal move history
    */
    public void cacheResult(
        com.ext.portlet.model.ProposalMoveHistory proposalMoveHistory);

    /**
    * Caches the proposal move histories in the entity cache if it is enabled.
    *
    * @param proposalMoveHistories the proposal move histories
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ProposalMoveHistory> proposalMoveHistories);

    /**
    * Creates a new proposal move history with the primary key. Does not add the proposal move history to the database.
    *
    * @param id_ the primary key for the new proposal move history
    * @return the new proposal move history
    */
    public com.ext.portlet.model.ProposalMoveHistory create(long id_);

    /**
    * Removes the proposal move history with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id_ the primary key of the proposal move history
    * @return the proposal move history that was removed
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory remove(long id_)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ProposalMoveHistory updateImpl(
        com.ext.portlet.model.ProposalMoveHistory proposalMoveHistory)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move history with the primary key or throws a {@link com.ext.portlet.NoSuchProposalMoveHistoryException} if it could not be found.
    *
    * @param id_ the primary key of the proposal move history
    * @return the proposal move history
    * @throws com.ext.portlet.NoSuchProposalMoveHistoryException if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory findByPrimaryKey(long id_)
        throws com.ext.portlet.NoSuchProposalMoveHistoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal move history with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id_ the primary key of the proposal move history
    * @return the proposal move history, or <code>null</code> if a proposal move history with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ProposalMoveHistory fetchByPrimaryKey(long id_)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal move histories.
    *
    * @return the proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal move histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @return the range of proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal move histories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ProposalMoveHistoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal move histories
    * @param end the upper bound of the range of proposal move histories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ProposalMoveHistory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal move histories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal move histories.
    *
    * @return the number of proposal move histories
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
