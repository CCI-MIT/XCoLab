package com.ext.portlet.service.persistence;

import com.ext.portlet.model.Contest;

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
    * Returns all the contests where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByTier(
        long contestTier)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestTier = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTier the contest tier
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByTier(
        long contestTier, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestTier = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTier the contest tier
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByTier(
        long contestTier, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByTier_First(long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByTier_First(long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByTier_Last(long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByTier_Last(long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestTier = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestTier the contest tier
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByTier_PrevAndNext(
        long ContestPK, long contestTier,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestTier = &#63; from the database.
    *
    * @param contestTier the contest tier
    * @throws SystemException if a system exception occurred
    */
    public void removeByTier(long contestTier)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestTier = &#63;.
    *
    * @param contestTier the contest tier
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByTier(long contestTier)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByTierType(
        long contestTier, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestTier = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByTierType(
        long contestTier, long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestTier = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByTierType(
        long contestTier, long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByTierType_First(
        long contestTier, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByTierType_First(
        long contestTier, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByTierType_Last(long contestTier,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByTierType_Last(
        long contestTier, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByTierType_PrevAndNext(
        long ContestPK, long contestTier, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestTier = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByTierType(long contestTier, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestTier = &#63; and contestTypeId = &#63;.
    *
    * @param contestTier the contest tier
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByTierType(long contestTier, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActivePrivate(
        boolean contestActive, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActivePrivate(
        boolean contestActive, boolean contestPrivate, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActivePrivate(
        boolean contestActive, boolean contestPrivate, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActivePrivate_First(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActivePrivate_First(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActivePrivate_Last(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActivePrivate_Last(
        boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActivePrivate_PrevAndNext(
        long ContestPK, boolean contestActive, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and contestPrivate = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @throws SystemException if a system exception occurred
    */
    public void removeByActivePrivate(boolean contestActive,
        boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActivePrivate(boolean contestActive,
        boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActivePrivateType(
        boolean contestActive, boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActivePrivateType(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActivePrivateType(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActivePrivateType_First(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActivePrivateType_First(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActivePrivateType_Last(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActivePrivateType_Last(
        boolean contestActive, boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActivePrivateType_PrevAndNext(
        long ContestPK, boolean contestActive, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActivePrivateType(boolean contestActive,
        boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActivePrivateType(boolean contestActive,
        boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActive(
        boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActive(
        boolean contestActive, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActive(
        boolean contestActive, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActive_First(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActive_First(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActive_Last(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActive_Last(
        boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActive_PrevAndNext(
        long ContestPK, boolean contestActive,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; from the database.
    *
    * @param contestActive the contest active
    * @throws SystemException if a system exception occurred
    */
    public void removeByActive(boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63;.
    *
    * @param contestActive the contest active
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActive(boolean contestActive)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveType(
        boolean contestActive, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveType(
        boolean contestActive, long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveType(
        boolean contestActive, long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveType_First(
        boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveType_First(
        boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveType_Last(
        boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveType_Last(
        boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveType_PrevAndNext(
        long ContestPK, boolean contestActive, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveType(boolean contestActive, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveType(boolean contestActive, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeatured(
        boolean contestActive, boolean featured)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and featured = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeatured(
        boolean contestActive, boolean featured, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeatured(
        boolean contestActive, boolean featured, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFeatured_First(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFeatured_First(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFeatured_Last(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFeatured_Last(
        boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param featured the featured
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFeatured_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
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
    * Returns all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedType(
        boolean contestActive, boolean featured, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedType(
        boolean contestActive, boolean featured, long contestTypeId, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedType(
        boolean contestActive, boolean featured, long contestTypeId, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFeaturedType_First(
        boolean contestActive, boolean featured, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFeaturedType_First(
        boolean contestActive, boolean featured, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFeaturedType_Last(
        boolean contestActive, boolean featured, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFeaturedType_Last(
        boolean contestActive, boolean featured, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFeaturedType_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFeaturedType(boolean contestActive,
        boolean featured, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and featured = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFeaturedType(boolean contestActive,
        boolean featured, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivate(
        boolean contestActive, boolean featured, boolean contestPrivate,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFeaturedPrivate_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFeaturedPrivate_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFeaturedPrivate_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFeaturedPrivate_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFeaturedPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFeaturedPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFeaturedPrivate(boolean contestActive,
        boolean featured, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFeaturedPrivateType(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFeaturedPrivateType_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFeaturedPrivateType_First(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFeaturedPrivateType_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFeaturedPrivateType_Last(
        boolean contestActive, boolean featured, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFeaturedPrivateType_PrevAndNext(
        long ContestPK, boolean contestActive, boolean featured,
        boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFeaturedPrivateType(boolean contestActive,
        boolean featured, boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and featured = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param featured the featured
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFeaturedPrivateType(boolean contestActive,
        boolean featured, boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlag(
        boolean contestActive, int flag)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlag(
        boolean contestActive, int flag, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlag(
        boolean contestActive, int flag, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlag_First(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlag_First(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlag_Last(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlag_Last(
        boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flag the flag
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFlag_PrevAndNext(
        long ContestPK, boolean contestActive, int flag,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

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
    * Returns all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagType(
        boolean contestActive, int flag, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagType(
        boolean contestActive, int flag, long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagType(
        boolean contestActive, int flag, long contestTypeId, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagType_First(
        boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagType_First(
        boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagType_Last(
        boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagType_Last(
        boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFlagType_PrevAndNext(
        long ContestPK, boolean contestActive, int flag, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFlagType(boolean contestActive, int flag,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and flag = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFlagType(boolean contestActive, int flag,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivate(
        boolean contestActive, int flag, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivate(
        boolean contestActive, int flag, boolean contestPrivate, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivate(
        boolean contestActive, int flag, boolean contestPrivate, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagPrivate_First(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagPrivate_First(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagPrivate_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagPrivate_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFlagPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, int flag,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFlagPrivate(boolean contestActive, int flag,
        boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFlagPrivate(boolean contestActive, int flag,
        boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivateType(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivateType(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagPrivateType(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagPrivateType_First(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagPrivateType_First(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagPrivateType_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagPrivateType_Last(
        boolean contestActive, int flag, boolean contestPrivate,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFlagPrivateType_PrevAndNext(
        long ContestPK, boolean contestActive, int flag,
        boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFlagPrivateType(boolean contestActive, int flag,
        boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and flag = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flag the flag
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFlagPrivateType(boolean contestActive, int flag,
        boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagText(
        boolean contestActive, java.lang.String flagText)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flagText = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagText(
        boolean contestActive, java.lang.String flagText, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagText(
        boolean contestActive, java.lang.String flagText, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagText_First(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagText_First(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagText_Last(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagText_Last(
        boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFlagText_PrevAndNext(
        long ContestPK, boolean contestActive, java.lang.String flagText,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

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
    * Returns all the contests where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextType(
        boolean contestActive, java.lang.String flagText, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextType(
        boolean contestActive, java.lang.String flagText, long contestTypeId,
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextType(
        boolean contestActive, java.lang.String flagText, long contestTypeId,
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagTextType_First(
        boolean contestActive, java.lang.String flagText, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagTextType_First(
        boolean contestActive, java.lang.String flagText, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagTextType_Last(
        boolean contestActive, java.lang.String flagText, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagTextType_Last(
        boolean contestActive, java.lang.String flagText, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFlagTextType_PrevAndNext(
        long ContestPK, boolean contestActive, java.lang.String flagText,
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFlagTextType(boolean contestActive,
        java.lang.String flagText, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and flagText = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFlagTextType(boolean contestActive,
        java.lang.String flagText, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextPrivate(
        boolean contestActive, java.lang.String flagText, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextPrivate(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextPrivate(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagTextPrivate_First(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagTextPrivate_First(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagTextPrivate_Last(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagTextPrivate_Last(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFlagTextPrivate_PrevAndNext(
        long ContestPK, boolean contestActive, java.lang.String flagText,
        boolean contestPrivate,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFlagTextPrivate(boolean contestActive,
        java.lang.String flagText, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFlagTextPrivate(boolean contestActive,
        java.lang.String flagText, boolean contestPrivate)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextPrivateType(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextPrivateType(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByActiveFlagTextPrivateType(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagTextPrivateType_First(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagTextPrivateType_First(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByActiveFlagTextPrivateType_Last(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByActiveFlagTextPrivateType_Last(
        boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByActiveFlagTextPrivateType_PrevAndNext(
        long ContestPK, boolean contestActive, java.lang.String flagText,
        boolean contestPrivate, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63; from the database.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByActiveFlagTextPrivateType(boolean contestActive,
        java.lang.String flagText, boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestActive = &#63; and flagText = &#63; and contestPrivate = &#63; and contestTypeId = &#63;.
    *
    * @param contestActive the contest active
    * @param flagText the flag text
    * @param contestPrivate the contest private
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByActiveFlagTextPrivateType(boolean contestActive,
        java.lang.String flagText, boolean contestPrivate, long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @return the matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByContestType(
        long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests where contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByContestType(
        long contestTypeId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests where contestTypeId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contestTypeId the contest type ID
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findByContestType(
        long contestTypeId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByContestType_First(
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest in the ordered set where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByContestType_First(
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest
    * @throws com.ext.portlet.NoSuchContestException if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByContestType_Last(
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest in the ordered set where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest, or <code>null</code> if a matching contest could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByContestType_Last(
        long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contests before and after the current contest in the ordered set where contestTypeId = &#63;.
    *
    * @param ContestPK the primary key of the current contest
    * @param contestTypeId the contest type ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest[] findByContestType_PrevAndNext(
        long ContestPK, long contestTypeId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests where contestTypeId = &#63; from the database.
    *
    * @param contestTypeId the contest type ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestType(long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contests where contestTypeId = &#63;.
    *
    * @param contestTypeId the contest type ID
    * @return the number of matching contests
    * @throws SystemException if a system exception occurred
    */
    public int countByContestType(long contestTypeId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the contest in the entity cache if it is enabled.
    *
    * @param contest the contest
    */
    public void cacheResult(com.ext.portlet.model.Contest contest);

    /**
    * Caches the contests in the entity cache if it is enabled.
    *
    * @param contests the contests
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.Contest> contests);

    /**
    * Creates a new contest with the primary key. Does not add the contest to the database.
    *
    * @param ContestPK the primary key for the new contest
    * @return the new contest
    */
    public com.ext.portlet.model.Contest create(long ContestPK);

    /**
    * Removes the contest with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest that was removed
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest remove(long ContestPK)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.Contest updateImpl(
        com.ext.portlet.model.Contest contest)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest with the primary key or throws a {@link com.ext.portlet.NoSuchContestException} if it could not be found.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest
    * @throws com.ext.portlet.NoSuchContestException if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest findByPrimaryKey(long ContestPK)
        throws com.ext.portlet.NoSuchContestException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ContestPK the primary key of the contest
    * @return the contest, or <code>null</code> if a contest with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.Contest fetchByPrimaryKey(long ContestPK)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contests.
    *
    * @return the contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contests.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @return the range of contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findAll(int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contests.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contests
    * @param end the upper bound of the range of contests (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contests
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.Contest> findAll(int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contests from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
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
