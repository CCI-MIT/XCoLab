package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.model.ContestTeamMember;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest team member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberPersistenceImpl
 * @see ContestTeamMemberUtil
 * @generated
 */
public interface ContestTeamMemberPersistence extends BasePersistence<ContestTeamMember> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestTeamMemberUtil} to access the contest team member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contest team member in the entity cache if it is enabled.
    *
    * @param contestTeamMember the contest team member
    */
    public void cacheResult(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember);

    /**
    * Caches the contest team members in the entity cache if it is enabled.
    *
    * @param contestTeamMembers the contest team members
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestTeamMember> contestTeamMembers);

    /**
    * Creates a new contest team member with the primary key. Does not add the contest team member to the database.
    *
    * @param id the primary key for the new contest team member
    * @return the new contest team member
    */
    public com.ext.portlet.contests.model.ContestTeamMember create(
        java.lang.Long id);

    /**
    * Removes the contest team member with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest team member
    * @return the contest team member that was removed
    * @throws com.ext.portlet.contests.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestTeamMember remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.contests.model.ContestTeamMember updateImpl(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest team member with the primary key or throws a {@link com.ext.portlet.contests.NoSuchContestTeamMemberException} if it could not be found.
    *
    * @param id the primary key of the contest team member
    * @return the contest team member
    * @throws com.ext.portlet.contests.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestTeamMember findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest team member with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest team member
    * @return the contest team member, or <code>null</code> if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestTeamMember fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest team members where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the matching contest team members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findByContestId(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest team members where contestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestId the contest ID
    * @param start the lower bound of the range of contest team members
    * @param end the upper bound of the range of contest team members (not inclusive)
    * @return the range of matching contest team members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findByContestId(
        java.lang.Long contestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest team members where contestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestId the contest ID
    * @param start the lower bound of the range of contest team members
    * @param end the upper bound of the range of contest team members (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contest team members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findByContestId(
        java.lang.Long contestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first contest team member in the ordered set where contestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contest team member
    * @throws com.ext.portlet.contests.NoSuchContestTeamMemberException if a matching contest team member could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestTeamMember findByContestId_First(
        java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last contest team member in the ordered set where contestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contest team member
    * @throws com.ext.portlet.contests.NoSuchContestTeamMemberException if a matching contest team member could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestTeamMember findByContestId_Last(
        java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest team members before and after the current contest team member in the ordered set where contestId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param id the primary key of the current contest team member
    * @param contestId the contest ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contest team member
    * @throws com.ext.portlet.contests.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.contests.model.ContestTeamMember[] findByContestId_PrevAndNext(
        java.lang.Long id, java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest team members.
    *
    * @return the contest team members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest team members.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest team members
    * @param end the upper bound of the range of contest team members (not inclusive)
    * @return the range of contest team members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest team members.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of contest team members
    * @param end the upper bound of the range of contest team members (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest team members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest team members where contestId = &#63; from the database.
    *
    * @param contestId the contest ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByContestId(java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest team members from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest team members where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the number of matching contest team members
    * @throws SystemException if a system exception occurred
    */
    public int countByContestId(java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest team members.
    *
    * @return the number of contest team members
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
