package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestTeamMemberRole;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest team member role service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberRolePersistenceImpl
 * @see ContestTeamMemberRoleUtil
 * @generated
 */
public interface ContestTeamMemberRolePersistence extends BasePersistence<ContestTeamMemberRole> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestTeamMemberRoleUtil} to access the contest team member role persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the contest team member role in the entity cache if it is enabled.
    *
    * @param contestTeamMemberRole the contest team member role
    */
    public void cacheResult(
        com.ext.portlet.model.ContestTeamMemberRole contestTeamMemberRole);

    /**
    * Caches the contest team member roles in the entity cache if it is enabled.
    *
    * @param contestTeamMemberRoles the contest team member roles
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ContestTeamMemberRole> contestTeamMemberRoles);

    /**
    * Creates a new contest team member role with the primary key. Does not add the contest team member role to the database.
    *
    * @param id the primary key for the new contest team member role
    * @return the new contest team member role
    */
    public com.ext.portlet.model.ContestTeamMemberRole create(long id);

    /**
    * Removes the contest team member role with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest team member role
    * @return the contest team member role that was removed
    * @throws com.ext.portlet.NoSuchContestTeamMemberRoleException if a contest team member role with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestTeamMemberRole remove(long id)
        throws com.ext.portlet.NoSuchContestTeamMemberRoleException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ContestTeamMemberRole updateImpl(
        com.ext.portlet.model.ContestTeamMemberRole contestTeamMemberRole)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest team member role with the primary key or throws a {@link com.ext.portlet.NoSuchContestTeamMemberRoleException} if it could not be found.
    *
    * @param id the primary key of the contest team member role
    * @return the contest team member role
    * @throws com.ext.portlet.NoSuchContestTeamMemberRoleException if a contest team member role with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestTeamMemberRole findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchContestTeamMemberRoleException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest team member role with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest team member role
    * @return the contest team member role, or <code>null</code> if a contest team member role with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestTeamMemberRole fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest team member roles.
    *
    * @return the contest team member roles
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestTeamMemberRole> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contest team member roles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest team member roles
    * @param end the upper bound of the range of contest team member roles (not inclusive)
    * @return the range of contest team member roles
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestTeamMemberRole> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contest team member roles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestTeamMemberRoleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest team member roles
    * @param end the upper bound of the range of contest team member roles (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest team member roles
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestTeamMemberRole> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest team member roles from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest team member roles.
    *
    * @return the number of contest team member roles
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
