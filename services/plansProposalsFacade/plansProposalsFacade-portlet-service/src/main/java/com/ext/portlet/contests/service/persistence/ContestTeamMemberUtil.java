package com.ext.portlet.contests.service.persistence;

import com.ext.portlet.contests.model.ContestTeamMember;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contest team member service. This utility wraps {@link ContestTeamMemberPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberPersistence
 * @see ContestTeamMemberPersistenceImpl
 * @generated
 */
public class ContestTeamMemberUtil {
    private static ContestTeamMemberPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
     */
    public static void clearCache(ContestTeamMember contestTeamMember) {
        getPersistence().clearCache(contestTeamMember);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ContestTeamMember> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContestTeamMember> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContestTeamMember> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static ContestTeamMember update(
        ContestTeamMember contestTeamMember, boolean merge)
        throws SystemException {
        return getPersistence().update(contestTeamMember, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static ContestTeamMember update(
        ContestTeamMember contestTeamMember, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(contestTeamMember, merge, serviceContext);
    }

    /**
    * Caches the contest team member in the entity cache if it is enabled.
    *
    * @param contestTeamMember the contest team member
    */
    public static void cacheResult(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember) {
        getPersistence().cacheResult(contestTeamMember);
    }

    /**
    * Caches the contest team members in the entity cache if it is enabled.
    *
    * @param contestTeamMembers the contest team members
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.contests.model.ContestTeamMember> contestTeamMembers) {
        getPersistence().cacheResult(contestTeamMembers);
    }

    /**
    * Creates a new contest team member with the primary key. Does not add the contest team member to the database.
    *
    * @param id the primary key for the new contest team member
    * @return the new contest team member
    */
    public static com.ext.portlet.contests.model.ContestTeamMember create(
        java.lang.Long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the contest team member with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest team member
    * @return the contest team member that was removed
    * @throws com.ext.portlet.contests.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestTeamMember remove(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember updateImpl(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contestTeamMember, merge);
    }

    /**
    * Returns the contest team member with the primary key or throws a {@link com.ext.portlet.contests.NoSuchContestTeamMemberException} if it could not be found.
    *
    * @param id the primary key of the contest team member
    * @return the contest team member
    * @throws com.ext.portlet.contests.NoSuchContestTeamMemberException if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestTeamMember findByPrimaryKey(
        java.lang.Long id)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the contest team member with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest team member
    * @return the contest team member, or <code>null</code> if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestTeamMember fetchByPrimaryKey(
        java.lang.Long id)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the contest team members where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the matching contest team members
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findByContestId(
        java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestId(contestId);
    }

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
    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findByContestId(
        java.lang.Long contestId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestId(contestId, start, end);
    }

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
    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findByContestId(
        java.lang.Long contestId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId(contestId, start, end, orderByComparator);
    }

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
    public static com.ext.portlet.contests.model.ContestTeamMember findByContestId_First(
        java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId_First(contestId, orderByComparator);
    }

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
    public static com.ext.portlet.contests.model.ContestTeamMember findByContestId_Last(
        java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId_Last(contestId, orderByComparator);
    }

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
    public static com.ext.portlet.contests.model.ContestTeamMember[] findByContestId_PrevAndNext(
        java.lang.Long id, java.lang.Long contestId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.contests.NoSuchContestTeamMemberException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByContestId_PrevAndNext(id, contestId, orderByComparator);
    }

    /**
    * Returns all the contest team members.
    *
    * @return the contest team members
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contest team members where contestId = &#63; from the database.
    *
    * @param contestId the contest ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByContestId(java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByContestId(contestId);
    }

    /**
    * Removes all the contest team members from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contest team members where contestId = &#63;.
    *
    * @param contestId the contest ID
    * @return the number of matching contest team members
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestId(java.lang.Long contestId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestId(contestId);
    }

    /**
    * Returns the number of contest team members.
    *
    * @return the number of contest team members
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContestTeamMemberPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContestTeamMemberPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.contests.service.ClpSerializer.getServletContextName(),
                    ContestTeamMemberPersistence.class.getName());

            ReferenceRegistry.registerReference(ContestTeamMemberUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(ContestTeamMemberPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(ContestTeamMemberUtil.class,
            "_persistence");
    }
}
