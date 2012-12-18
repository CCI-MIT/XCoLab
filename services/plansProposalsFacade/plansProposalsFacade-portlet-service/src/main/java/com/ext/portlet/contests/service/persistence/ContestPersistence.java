package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.model.Contest;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestPersistenceImpl
 * @see ContestUtil
 * @generated
 */
public interface ContestPersistence extends BasePersistence<Contest> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestUtil} to access the contest persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contest in the entity cache if it is enabled.
    *
    * @param contest the contest
    */
    public void cacheResult(com.ext.portlet.contests.model.Contest contest);

    /**
    * Caches the contests in the entity cache if it is enabled.
    *
    * @param contests the contests
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.contests.model.Contest> contests);

    /**
    * Creates a new contest with the primary key. Does not add the contest to the database.
    *
    * @param ContestPK the primary key for the new contest
    * @return the new contest
    */
    public com.ext.portlet.contests.model.Contest create(long ContestPK);

    /**
    * Removes the contest with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest that was removed
    * @throws com.ext.portlet.contests.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest remove(long ContestPK)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.contests.model.Contest updateImpl(
        com.ext.portlet.contests.model.Contest contest, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest with the primary key or throws a {@link com.ext.portlet.contests.NoSuchContestException} if it could not be found.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findByPrimaryKey(
        long ContestPK)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest, or <code>null</code> if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest fetchByPrimaryKey(
        long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where PlanTypeId = &#63;.
    *
    * @param PlanTypeId the plan type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByType(
        long PlanTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where PlanTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param PlanTypeId the plan type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByType(
        long PlanTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where PlanTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param PlanTypeId the plan type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByType(
        long PlanTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where PlanTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param PlanTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findByType_First(
        long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where PlanTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param PlanTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findByType_Last(
        long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where PlanTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the primary key of the current contest
    * @param PlanTypeId the plan type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest[] findByType_PrevAndNext(
        long ContestPK, long PlanTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest where contestActive = &#63; or throws a {@link com.ext.portlet.contests.NoSuchContestException} if it could not be found.
    *
    * @param contestActive the contest active
    * @return the matching contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findBycontestActive(
        boolean contestActive)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest where contestActive = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param contestActive the contest active
    * @return the matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest fetchBycontestActive(
        boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest where contestActive = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param contestActive the contest active
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest fetchBycontestActive(
        boolean contestActive, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFeatured(
        boolean contestActive, boolean featured)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and featured = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFeatured(
        boolean contestActive, boolean featured, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFeatured(
        boolean contestActive, boolean featured, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findByActiveFeatured_First(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findByActiveFeatured_Last(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest[] findByActiveFeatured_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFlag(
        boolean contestActive, int flag)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFlag(
        boolean contestActive, int flag, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFlag(
        boolean contestActive, int flag, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findByActiveFlag_First(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findByActiveFlag_Last(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest[] findByActiveFlag_PrevAndNext(
        long ContestPK, boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFlagText(
        boolean contestActive, java.lang.String flagText)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flagText = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFlagText(
        boolean contestActive, java.lang.String flagText, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findByActiveFlagText(
        boolean contestActive, java.lang.String flagText, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findByActiveFlagText_First(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest findByActiveFlagText_Last(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.contests.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.Contest[] findByActiveFlagText_PrevAndNext(
        long ContestPK, boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests.
    *
    * @return the contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.Contest> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where PlanTypeId = &#63; from the database.
    *
    * @param PlanTypeId the plan type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByType(long PlanTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the contest where contestActive = &#63; from the database.
    *
    * @param contestActive the contest active
    * @throws SystemException if a system exception occurred
    */
    public void removeBycontestActive(boolean contestActive)
        throws com.ext.portlet.contests.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and featured = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFeatured(boolean contestActive, boolean featured)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and flag = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFlag(boolean contestActive, int flag)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and flagText = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFlagText(boolean contestActive,
        java.lang.String flagText)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where PlanTypeId = &#63;.
    *
    * @param PlanTypeId the plan type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByType(long PlanTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countBycontestActive(boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFeatured(boolean contestActive, boolean featured)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFlag(boolean contestActive, int flag)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFlagText(boolean contestActive,
        java.lang.String flagText)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests.
    *
    * @return the number of contests
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
