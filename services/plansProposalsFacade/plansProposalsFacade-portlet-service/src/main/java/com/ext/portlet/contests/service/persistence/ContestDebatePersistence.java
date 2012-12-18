package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.model.ContestDebate;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest debate service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDebatePersistenceImpl
 * @see ContestDebateUtil
 * @generated
 */
public interface ContestDebatePersistence extends BasePersistence<ContestDebate> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestDebateUtil} to access the contest debate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contest debate in the entity cache if it is enabled.
    *
    * @param contestDebate the contest debate
    */
    public void cacheResult(
        com.ext.portlet.contests.model.ContestDebate contestDebate);

    /**
    * Caches the contest debates in the entity cache if it is enabled.
    *
    * @param contestDebates the contest debates
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestDebate> contestDebates);

    /**
    * Creates a new contest debate with the primary key. Does not add the contest debate to the database.
    *
    * @param id the primary key for the new contest debate
    * @return the new contest debate
    */
    public com.ext.portlet.contests.model.ContestDebate create(long id);

    /**
    * Removes the contest debate with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest debate
    * @return the contest debate that was removed
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate remove(long id)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.contests.model.ContestDebate updateImpl(
        com.ext.portlet.contests.model.ContestDebate contestDebate,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest debate with the primary key or throws a {@link com.ext.portlet.contests.NoSuchContestDebateException} if it could not be found.
    *
    * @param id the primary key of the contest debate
    * @return the contest debate
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate findByPrimaryKey(
        long id)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest debate with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest debate
    * @return the contest debate, or <code>null</code> if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest debates where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @return the matching contest debates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest debates where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @return the range of matching contest debates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        long ContestPK, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest debates where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest debates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findByContestPK(
        long ContestPK, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest debate in the ordered set where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest debate
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a matching contest debate could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate findByContestPK_First(
        long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest debate in the ordered set where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest debate
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a matching contest debate could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate findByContestPK_Last(
        long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest debates before and after the current contest debate in the ordered set where ContestPK = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current contest debate
    * @param ContestPK the contest p k
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest debate
    * @throws com.ext.portlet.contests.NoSuchContestDebateException if a contest debate with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestDebate[] findByContestPK_PrevAndNext(
        long id, long ContestPK,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestDebateException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest debates.
    *
    * @return the contest debates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest debates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @return the range of contest debates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest debates.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest debates
    * @param end the upper bound of the range of contest debates (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest debates
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestDebate> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest debates where ContestPK = &#63; from the database.
    *
    * @param ContestPK the contest p k
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestPK(long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest debates from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest debates where ContestPK = &#63;.
    *
    * @param ContestPK the contest p k
    * @return the number of matching contest debates
    * @throws SystemException if a system exception occurred
    */
    public int countByContestPK(long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest debates.
    *
    * @return the number of contest debates
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
