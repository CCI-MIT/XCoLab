package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestSchedule;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest schedule service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestSchedulePersistenceImpl
 * @see ContestScheduleUtil
 * @generated
 */
public interface ContestSchedulePersistence extends BasePersistence<ContestSchedule> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestScheduleUtil} to access the contest schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contest schedule in the entity cache if it is enabled.
    *
    * @param contestSchedule the contest schedule
    */
    public void cacheResult(
        com.ext.portlet.model.ContestSchedule contestSchedule);

    /**
    * Caches the contest schedules in the entity cache if it is enabled.
    *
    * @param contestSchedules the contest schedules
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ContestSchedule> contestSchedules);

    /**
    * Creates a new contest schedule with the primary key. Does not add the contest schedule to the database.
    *
    * @param id the primary key for the new contest schedule
    * @return the new contest schedule
    */
    public com.ext.portlet.model.ContestSchedule create(long id);

    /**
    * Removes the contest schedule with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest schedule
    * @return the contest schedule that was removed
    * @throws com.ext.portlet.NoSuchContestScheduleException if a contest schedule with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestSchedule remove(long id)
        throws com.ext.portlet.NoSuchContestScheduleException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ContestSchedule updateImpl(
        com.ext.portlet.model.ContestSchedule contestSchedule)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest schedule with the primary key or throws a {@link com.ext.portlet.NoSuchContestScheduleException} if it could not be found.
    *
    * @param id the primary key of the contest schedule
    * @return the contest schedule
    * @throws com.ext.portlet.NoSuchContestScheduleException if a contest schedule with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestSchedule findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchContestScheduleException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest schedule with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest schedule
    * @return the contest schedule, or <code>null</code> if a contest schedule with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestSchedule fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest schedules.
    *
    * @return the contest schedules
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestSchedule> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest schedules.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest schedules
    * @param end the upper bound of the range of contest schedules (not inclusive)
    * @return the range of contest schedules
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestSchedule> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest schedules.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestScheduleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest schedules
    * @param end the upper bound of the range of contest schedules (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest schedules
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestSchedule> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest schedules from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest schedules.
    *
    * @return the number of contest schedules
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
