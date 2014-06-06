package com.ext.portlet.service.persistence;

import com.ext.portlet.model.StaffMember;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the staff member service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see StaffMemberPersistenceImpl
 * @see StaffMemberUtil
 * @generated
 */
public interface StaffMemberPersistence extends BasePersistence<StaffMember> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StaffMemberUtil} to access the staff member persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the staff members where userId = &#63;.
    *
    * @param userId the user ID
    * @return the matching staff members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.StaffMember> findByUserId(
        long userId) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the staff members where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of staff members
    * @param end the upper bound of the range of staff members (not inclusive)
    * @return the range of matching staff members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.StaffMember> findByUserId(
        long userId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the staff members where userId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param userId the user ID
    * @param start the lower bound of the range of staff members
    * @param end the upper bound of the range of staff members (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching staff members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.StaffMember> findByUserId(
        long userId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first staff member in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching staff member
    * @throws com.ext.portlet.NoSuchStaffMemberException if a matching staff member could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.StaffMember findByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchStaffMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first staff member in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching staff member, or <code>null</code> if a matching staff member could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.StaffMember fetchByUserId_First(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last staff member in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching staff member
    * @throws com.ext.portlet.NoSuchStaffMemberException if a matching staff member could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.StaffMember findByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchStaffMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last staff member in the ordered set where userId = &#63;.
    *
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching staff member, or <code>null</code> if a matching staff member could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.StaffMember fetchByUserId_Last(long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the staff members before and after the current staff member in the ordered set where userId = &#63;.
    *
    * @param id the primary key of the current staff member
    * @param userId the user ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next staff member
    * @throws com.ext.portlet.NoSuchStaffMemberException if a staff member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.StaffMember[] findByUserId_PrevAndNext(
        long id, long userId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchStaffMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the staff members where userId = &#63; from the database.
    *
    * @param userId the user ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of staff members where userId = &#63;.
    *
    * @param userId the user ID
    * @return the number of matching staff members
    * @throws SystemException if a system exception occurred
    */
    public int countByUserId(long userId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the staff member in the entity cache if it is enabled.
    *
    * @param staffMember the staff member
    */
    public void cacheResult(com.ext.portlet.model.StaffMember staffMember);

    /**
    * Caches the staff members in the entity cache if it is enabled.
    *
    * @param staffMembers the staff members
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.StaffMember> staffMembers);

    /**
    * Creates a new staff member with the primary key. Does not add the staff member to the database.
    *
    * @param id the primary key for the new staff member
    * @return the new staff member
    */
    public com.ext.portlet.model.StaffMember create(long id);

    /**
    * Removes the staff member with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the staff member
    * @return the staff member that was removed
    * @throws com.ext.portlet.NoSuchStaffMemberException if a staff member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.StaffMember remove(long id)
        throws com.ext.portlet.NoSuchStaffMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.StaffMember updateImpl(
        com.ext.portlet.model.StaffMember staffMember)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the staff member with the primary key or throws a {@link com.ext.portlet.NoSuchStaffMemberException} if it could not be found.
    *
    * @param id the primary key of the staff member
    * @return the staff member
    * @throws com.ext.portlet.NoSuchStaffMemberException if a staff member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.StaffMember findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchStaffMemberException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the staff member with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the staff member
    * @return the staff member, or <code>null</code> if a staff member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.StaffMember fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the staff members.
    *
    * @return the staff members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.StaffMember> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the staff members.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of staff members
    * @param end the upper bound of the range of staff members (not inclusive)
    * @return the range of staff members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.StaffMember> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the staff members.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.StaffMemberModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of staff members
    * @param end the upper bound of the range of staff members (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of staff members
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.StaffMember> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the staff members from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of staff members.
    *
    * @return the number of staff members
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
