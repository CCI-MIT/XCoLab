package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Proposal2Phase;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the proposal2 phase service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see Proposal2PhasePersistenceImpl
 * @see Proposal2PhaseUtil
 * @generated
 */
public interface Proposal2PhasePersistence extends BasePersistence<Proposal2Phase> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link Proposal2PhaseUtil} to access the proposal2 phase persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the proposal2 phases where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the matching proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal2Phase> findByProposalId(
        long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal2 phases where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal2 phases
    * @param end the upper bound of the range of proposal2 phases (not inclusive)
    * @return the range of matching proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal2Phase> findByProposalId(
        long proposalId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal2 phases where proposalId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param proposalId the proposal ID
    * @param start the lower bound of the range of proposal2 phases
    * @param end the upper bound of the range of proposal2 phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal2Phase> findByProposalId(
        long proposalId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal2 phase in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal2 phase
    * @throws com.ext.portlet.NoSuchProposal2PhaseException if a matching proposal2 phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase findByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposal2PhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal2 phase in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal2 phase, or <code>null</code> if a matching proposal2 phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase fetchByProposalId_First(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal2 phase in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal2 phase
    * @throws com.ext.portlet.NoSuchProposal2PhaseException if a matching proposal2 phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase findByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposal2PhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal2 phase in the ordered set where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal2 phase, or <code>null</code> if a matching proposal2 phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase fetchByProposalId_Last(
        long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal2 phases before and after the current proposal2 phase in the ordered set where proposalId = &#63;.
    *
    * @param proposal2PhasePK the primary key of the current proposal2 phase
    * @param proposalId the proposal ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal2 phase
    * @throws com.ext.portlet.NoSuchProposal2PhaseException if a proposal2 phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase[] findByProposalId_PrevAndNext(
        Proposal2PhasePK proposal2PhasePK, long proposalId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposal2PhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal2 phases where proposalId = &#63; from the database.
    *
    * @param proposalId the proposal ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal2 phases where proposalId = &#63;.
    *
    * @param proposalId the proposal ID
    * @return the number of matching proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public int countByProposalId(long proposalId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal2 phases where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @return the matching proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal2Phase> findByContestPhaseId(
        long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal2 phases where contestPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestPhaseId the contest phase ID
    * @param start the lower bound of the range of proposal2 phases
    * @param end the upper bound of the range of proposal2 phases (not inclusive)
    * @return the range of matching proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal2Phase> findByContestPhaseId(
        long contestPhaseId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal2 phases where contestPhaseId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestPhaseId the contest phase ID
    * @param start the lower bound of the range of proposal2 phases
    * @param end the upper bound of the range of proposal2 phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal2Phase> findByContestPhaseId(
        long contestPhaseId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal2 phase in the ordered set where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal2 phase
    * @throws com.ext.portlet.NoSuchProposal2PhaseException if a matching proposal2 phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase findByContestPhaseId_First(
        long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposal2PhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first proposal2 phase in the ordered set where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching proposal2 phase, or <code>null</code> if a matching proposal2 phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase fetchByContestPhaseId_First(
        long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal2 phase in the ordered set where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal2 phase
    * @throws com.ext.portlet.NoSuchProposal2PhaseException if a matching proposal2 phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase findByContestPhaseId_Last(
        long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposal2PhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last proposal2 phase in the ordered set where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching proposal2 phase, or <code>null</code> if a matching proposal2 phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase fetchByContestPhaseId_Last(
        long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal2 phases before and after the current proposal2 phase in the ordered set where contestPhaseId = &#63;.
    *
    * @param proposal2PhasePK the primary key of the current proposal2 phase
    * @param contestPhaseId the contest phase ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next proposal2 phase
    * @throws com.ext.portlet.NoSuchProposal2PhaseException if a proposal2 phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase[] findByContestPhaseId_PrevAndNext(
        Proposal2PhasePK proposal2PhasePK, long contestPhaseId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchProposal2PhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal2 phases where contestPhaseId = &#63; from the database.
    *
    * @param contestPhaseId the contest phase ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestPhaseId(long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal2 phases where contestPhaseId = &#63;.
    *
    * @param contestPhaseId the contest phase ID
    * @return the number of matching proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public int countByContestPhaseId(long contestPhaseId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the proposal2 phase in the entity cache if it is enabled.
    *
    * @param proposal2Phase the proposal2 phase
    */
    public void cacheResult(com.ext.portlet.model.Proposal2Phase proposal2Phase);

    /**
    * Caches the proposal2 phases in the entity cache if it is enabled.
    *
    * @param proposal2Phases the proposal2 phases
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.Proposal2Phase> proposal2Phases);

    /**
    * Creates a new proposal2 phase with the primary key. Does not add the proposal2 phase to the database.
    *
    * @param proposal2PhasePK the primary key for the new proposal2 phase
    * @return the new proposal2 phase
    */
    public com.ext.portlet.model.Proposal2Phase create(
        Proposal2PhasePK proposal2PhasePK);

    /**
    * Removes the proposal2 phase with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param proposal2PhasePK the primary key of the proposal2 phase
    * @return the proposal2 phase that was removed
    * @throws com.ext.portlet.NoSuchProposal2PhaseException if a proposal2 phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase remove(
        Proposal2PhasePK proposal2PhasePK)
        throws com.ext.portlet.NoSuchProposal2PhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.Proposal2Phase updateImpl(
        com.ext.portlet.model.Proposal2Phase proposal2Phase)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal2 phase with the primary key or throws a {@link com.ext.portlet.NoSuchProposal2PhaseException} if it could not be found.
    *
    * @param proposal2PhasePK the primary key of the proposal2 phase
    * @return the proposal2 phase
    * @throws com.ext.portlet.NoSuchProposal2PhaseException if a proposal2 phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase findByPrimaryKey(
        Proposal2PhasePK proposal2PhasePK)
        throws com.ext.portlet.NoSuchProposal2PhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the proposal2 phase with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param proposal2PhasePK the primary key of the proposal2 phase
    * @return the proposal2 phase, or <code>null</code> if a proposal2 phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Proposal2Phase fetchByPrimaryKey(
        Proposal2PhasePK proposal2PhasePK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the proposal2 phases.
    *
    * @return the proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal2Phase> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the proposal2 phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal2 phases
    * @param end the upper bound of the range of proposal2 phases (not inclusive)
    * @return the range of proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal2Phase> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the proposal2 phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.Proposal2PhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of proposal2 phases
    * @param end the upper bound of the range of proposal2 phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Proposal2Phase> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the proposal2 phases from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of proposal2 phases.
    *
    * @return the number of proposal2 phases
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
