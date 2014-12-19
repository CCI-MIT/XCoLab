package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestPhaseColumn;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest phase column service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPhaseColumnPersistenceImpl
 * @see ContestPhaseColumnUtil
 * @generated
 */
public interface ContestPhaseColumnPersistence extends BasePersistence<ContestPhaseColumn> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestPhaseColumnUtil} to access the contest phase column persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the contest phase columns where ContestPhasePK = &#63;.
    *
    * @param ContestPhasePK the contest phase p k
    * @return the matching contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseColumn> findByContestPhasePK(
        long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phase columns where ContestPhasePK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPhasePK the contest phase p k
    * @param start the lower bound of the range of contest phase columns
    * @param end the upper bound of the range of contest phase columns (not inclusive)
    * @return the range of matching contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseColumn> findByContestPhasePK(
        long ContestPhasePK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phase columns where ContestPhasePK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param ContestPhasePK the contest phase p k
    * @param start the lower bound of the range of contest phase columns
    * @param end the upper bound of the range of contest phase columns (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseColumn> findByContestPhasePK(
        long ContestPhasePK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase column in the ordered set where ContestPhasePK = &#63;.
    *
    * @param ContestPhasePK the contest phase p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase column
    * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a matching contest phase column could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseColumn findByContestPhasePK_First(
        long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseColumnException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest phase column in the ordered set where ContestPhasePK = &#63;.
    *
    * @param ContestPhasePK the contest phase p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest phase column, or <code>null</code> if a matching contest phase column could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseColumn fetchByContestPhasePK_First(
        long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase column in the ordered set where ContestPhasePK = &#63;.
    *
    * @param ContestPhasePK the contest phase p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase column
    * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a matching contest phase column could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseColumn findByContestPhasePK_Last(
        long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseColumnException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest phase column in the ordered set where ContestPhasePK = &#63;.
    *
    * @param ContestPhasePK the contest phase p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest phase column, or <code>null</code> if a matching contest phase column could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseColumn fetchByContestPhasePK_Last(
        long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phase columns before and after the current contest phase column in the ordered set where ContestPhasePK = &#63;.
    *
    * @param id the primary key of the current contest phase column
    * @param ContestPhasePK the contest phase p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest phase column
    * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a contest phase column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseColumn[] findByContestPhasePK_PrevAndNext(
        long id, long ContestPhasePK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestPhaseColumnException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phase columns where ContestPhasePK = &#63; from the database.
    *
    * @param ContestPhasePK the contest phase p k
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestPhasePK(long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phase columns where ContestPhasePK = &#63;.
    *
    * @param ContestPhasePK the contest phase p k
    * @return the number of matching contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public int countByContestPhasePK(long ContestPhasePK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the contest phase column in the entity cache if it is enabled.
    *
    * @param contestPhaseColumn the contest phase column
    */
    public void cacheResult(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn);

    /**
    * Caches the contest phase columns in the entity cache if it is enabled.
    *
    * @param contestPhaseColumns the contest phase columns
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ContestPhaseColumn> contestPhaseColumns);

    /**
    * Creates a new contest phase column with the primary key. Does not add the contest phase column to the database.
    *
    * @param id the primary key for the new contest phase column
    * @return the new contest phase column
    */
    public com.ext.portlet.model.ContestPhaseColumn create(long id);

    /**
    * Removes the contest phase column with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest phase column
    * @return the contest phase column that was removed
    * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a contest phase column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseColumn remove(long id)
        throws com.ext.portlet.NoSuchContestPhaseColumnException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ContestPhaseColumn updateImpl(
        com.ext.portlet.model.ContestPhaseColumn contestPhaseColumn)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phase column with the primary key or throws a {@link com.ext.portlet.NoSuchContestPhaseColumnException} if it could not be found.
    *
    * @param id the primary key of the contest phase column
    * @return the contest phase column
    * @throws com.ext.portlet.NoSuchContestPhaseColumnException if a contest phase column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseColumn findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchContestPhaseColumnException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest phase column with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest phase column
    * @return the contest phase column, or <code>null</code> if a contest phase column with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestPhaseColumn fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest phase columns.
    *
    * @return the contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseColumn> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest phase columns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phase columns
    * @param end the upper bound of the range of contest phase columns (not inclusive)
    * @return the range of contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseColumn> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest phase columns.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestPhaseColumnModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest phase columns
    * @param end the upper bound of the range of contest phase columns (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestPhaseColumn> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest phase columns from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest phase columns.
    *
    * @return the number of contest phase columns
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
