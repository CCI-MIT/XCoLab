package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhase;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest phase service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhasePersistenceImpl
 * @see ContestPhaseUtil
 * @generated
 */
public interface ContestPhasePersistence extends BasePersistence<ContestPhase> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestPhaseUtil} to access the contest phase persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByContestIdStartEnd(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByContestIdStartEnd(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByContestIdStartEnd(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByContestIdStartEnd_First(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByContestIdStartEnd_First(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByContestIdStartEnd_Last(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByContestIdStartEnd_Last(
        long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase[] findByContestIdStartEnd_PrevAndNext(
        long ContestPhasePK, long ContestPK, java.util.Date PhaseStartDate,
        java.util.Date PhaseEndDate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestIdStartEnd(long ContestPK,
        java.util.Date PhaseStartDate, java.util.Date PhaseEndDate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phases where ContestPK = &#63; and PhaseStartDate &le; &#63; and PhaseEndDate &ge; &#63;.
    *
    * @param ContestPK the contest p k
    * @param PhaseStartDate the phase start date
    * @param PhaseEndDate the phase end date
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public int countByContestIdStartEnd(long ContestPK,
        java.util.Date PhaseStartDate, java.util.Date PhaseEndDate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest phases where contestScheduleId = &#63; and ContestPK = &#63;.
    *
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByContestScheduleId(
        long contestScheduleId, long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phases where contestScheduleId = &#63; and ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByContestScheduleId(
        long contestScheduleId, long ContestPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phases where contestScheduleId = &#63; and ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByContestScheduleId(
        long contestScheduleId, long ContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
    *
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByContestScheduleId_First(
        long contestScheduleId, long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
    *
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByContestScheduleId_First(
        long contestScheduleId, long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
    *
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByContestScheduleId_Last(
        long contestScheduleId, long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
    *
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByContestScheduleId_Last(
        long contestScheduleId, long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where contestScheduleId = &#63; and ContestPK = &#63;.
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase[] findByContestScheduleId_PrevAndNext(
        long ContestPhasePK, long contestScheduleId, long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phases where contestScheduleId = &#63; and ContestPK = &#63; from the database.
    *
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestScheduleId(long contestScheduleId, long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phases where contestScheduleId = &#63; and ContestPK = &#63;.
    *
    * @param contestScheduleId the contest schedule ID
    * @param ContestPK the contest p k
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public int countByContestScheduleId(long contestScheduleId, long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest phases where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByContestId(
        long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phases where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByContestId(
        long ContestPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phases where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByContestId(
        long ContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByContestId_First(
        long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByContestId_First(
        long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByContestId_Last(
        long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByContestId_Last(
        long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63;.
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase[] findByContestId_PrevAndNext(
        long ContestPhasePK, long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phases where ContestPK = &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestId(long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phases where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public int countByContestId(long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseActiveOverride(
        long ContestPK, boolean phaseActiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseActiveOverride(
        long ContestPK, boolean phaseActiveOverride, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseActiveOverride(
        long ContestPK, boolean phaseActiveOverride, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByPhaseActiveOverride_First(
        long ContestPK, boolean phaseActiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByPhaseActiveOverride_First(
        long ContestPK, boolean phaseActiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByPhaseActiveOverride_Last(
        long ContestPK, boolean phaseActiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByPhaseActiveOverride_Last(
        long ContestPK, boolean phaseActiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase[] findByPhaseActiveOverride_PrevAndNext(
        long ContestPhasePK, long ContestPK, boolean phaseActiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phases where ContestPK = &#63; and phaseActiveOverride = &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @throws SystemException if a system exception occurred
    */
    public void removeByPhaseActiveOverride(long ContestPK,
        boolean phaseActiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phases where ContestPK = &#63; and phaseActiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseActiveOverride the phase active override
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public int countByPhaseActiveOverride(long ContestPK,
        boolean phaseActiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseInactiveOverride(
        long ContestPK, boolean phaseInactiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseInactiveOverride(
        long ContestPK, boolean phaseInactiveOverride, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseInactiveOverride(
        long ContestPK, boolean phaseInactiveOverride, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByPhaseInactiveOverride_First(
        long ContestPK, boolean phaseInactiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByPhaseInactiveOverride_First(
        long ContestPK, boolean phaseInactiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByPhaseInactiveOverride_Last(
        long ContestPK, boolean phaseInactiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByPhaseInactiveOverride_Last(
        long ContestPK, boolean phaseInactiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase[] findByPhaseInactiveOverride_PrevAndNext(
        long ContestPhasePK, long ContestPK, boolean phaseInactiveOverride,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @throws SystemException if a system exception occurred
    */
    public void removeByPhaseInactiveOverride(long ContestPK,
        boolean phaseInactiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phases where ContestPK = &#63; and phaseInactiveOverride = &#63;.
    *
    * @param ContestPK the contest p k
    * @param phaseInactiveOverride the phase inactive override
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public int countByPhaseInactiveOverride(long ContestPK,
        boolean phaseInactiveOverride)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest phases where contestPhaseAutopromote = &#63;.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @return the matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseAutopromote(
        java.lang.String contestPhaseAutopromote)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phases where contestPhaseAutopromote = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseAutopromote(
        java.lang.String contestPhaseAutopromote, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phases where contestPhaseAutopromote = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findByPhaseAutopromote(
        java.lang.String contestPhaseAutopromote, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where contestPhaseAutopromote = &#63;.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByPhaseAutopromote_First(
        java.lang.String contestPhaseAutopromote,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase in the ordered set where contestPhaseAutopromote = &#63;.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByPhaseAutopromote_First(
        java.lang.String contestPhaseAutopromote,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where contestPhaseAutopromote = &#63;.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByPhaseAutopromote_Last(
        java.lang.String contestPhaseAutopromote,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase in the ordered set where contestPhaseAutopromote = &#63;.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase, or <code>null</code> if a matching contest phase could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByPhaseAutopromote_Last(
        java.lang.String contestPhaseAutopromote,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phases before and after the current contest phase in the ordered set where contestPhaseAutopromote = &#63;.
    *
    * @param ContestPhasePK the primary key of the current contest phase
    * @param contestPhaseAutopromote the contest phase autopromote
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase[] findByPhaseAutopromote_PrevAndNext(
        long ContestPhasePK, java.lang.String contestPhaseAutopromote,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phases where contestPhaseAutopromote = &#63; from the database.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @throws SystemException if a system exception occurred
    */
    public void removeByPhaseAutopromote(
        java.lang.String contestPhaseAutopromote)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phases where contestPhaseAutopromote = &#63;.
    *
    * @param contestPhaseAutopromote the contest phase autopromote
    * @return the number of matching contest phases
    * @throws SystemException if a system exception occurred
    */
    public int countByPhaseAutopromote(java.lang.String contestPhaseAutopromote)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the contest phase in the entity cache if it is enabled.
    *
    * @param contestPhase the contest phase
    */
    public void cacheResult(com.ext.portlet.model.ContestPhase contestPhase);

    /**
    * Caches the contest phases in the entity cache if it is enabled.
    *
    * @param contestPhases the contest phases
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ContestPhase> contestPhases);

    /**
    * Creates a new contest phase with the primary key. Does not add the contest phase to the database.
    *
    * @param ContestPhasePK the primary key for the new contest phase
    * @return the new contest phase
    */
    public com.ext.portlet.model.ContestPhase create(long ContestPhasePK);

    /**
    * Removes the contest phase with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase that was removed
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase remove(long ContestPhasePK)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ContestPhase updateImpl(
        com.ext.portlet.model.ContestPhase contestPhase)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phase with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseException} if it could not be found.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase
    * @throws com.ext.portlet.NoSuchContestPhaseException if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase findByPrimaryKey(
        long ContestPhasePK)
        throws com.ext.portlet.NoSuchContestPhaseException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phase with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ContestPhasePK the primary key of the contest phase
    * @return the contest phase, or <code>null</code> if a contest phase with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhase fetchByPrimaryKey(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest phases.
    *
    * @return the contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @return the range of contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phases.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phases
    * @param end the upper bound of the range of contest phases (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest phases
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhase> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phases from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phases.
    *
    * @return the number of contest phases
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
