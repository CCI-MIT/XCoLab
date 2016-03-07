package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestTeamMemberRole;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contest team member role service. This utility wraps {@link ContestTeamMemberRolePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberRolePersistence
 * @see ContestTeamMemberRolePersistenceImpl
 * @generated
 */
public class ContestTeamMemberRoleUtil {
    private static ContestTeamMemberRolePersistence _persistence;

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
    public static void clearCache(ContestTeamMemberRole contestTeamMemberRole) {
        getPersistence().clearCache(contestTeamMemberRole);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<ContestTeamMemberRole> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContestTeamMemberRole> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContestTeamMemberRole> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ContestTeamMemberRole update(
        ContestTeamMemberRole contestTeamMemberRole) throws SystemException {
        return getPersistence().update(contestTeamMemberRole);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ContestTeamMemberRole update(
        ContestTeamMemberRole contestTeamMemberRole,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(contestTeamMemberRole, serviceContext);
    }

    /**
    * Caches the contest team member role in the entity cache if it is enabled.
    *
    * @param contestTeamMemberRole the contest team member role
    */
    public static void cacheResult(
        com.ext.portlet.model.ContestTeamMemberRole contestTeamMemberRole) {
        getPersistence().cacheResult(contestTeamMemberRole);
    }

    /**
    * Caches the contest team member roles in the entity cache if it is enabled.
    *
    * @param contestTeamMemberRoles the contest team member roles
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ContestTeamMemberRole> contestTeamMemberRoles) {
        getPersistence().cacheResult(contestTeamMemberRoles);
    }

    /**
    * Creates a new contest team member role with the primary key. Does not add the contest team member role to the database.
    *
    * @param id the primary key for the new contest team member role
    * @return the new contest team member role
    */
    public static com.ext.portlet.model.ContestTeamMemberRole create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the contest team member role with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest team member role
    * @return the contest team member role that was removed
    * @throws com.ext.portlet.NoSuchContestTeamMemberRoleException if a contest team member role with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestTeamMemberRole remove(long id)
        throws com.ext.portlet.NoSuchContestTeamMemberRoleException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.ContestTeamMemberRole updateImpl(
        com.ext.portlet.model.ContestTeamMemberRole contestTeamMemberRole)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contestTeamMemberRole);
    }

    /**
    * Returns the contest team member role with the primary key or throws a {@link com.ext.portlet.NoSuchContestTeamMemberRoleException} if it could not be found.
    *
    * @param id the primary key of the contest team member role
    * @return the contest team member role
    * @throws com.ext.portlet.NoSuchContestTeamMemberRoleException if a contest team member role with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestTeamMemberRole findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchContestTeamMemberRoleException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the contest team member role with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the contest team member role
    * @return the contest team member role, or <code>null</code> if a contest team member role with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestTeamMemberRole fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the contest team member roles.
    *
    * @return the contest team member roles
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestTeamMemberRole> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.ext.portlet.model.ContestTeamMemberRole> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.ext.portlet.model.ContestTeamMemberRole> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contest team member roles from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contest team member roles.
    *
    * @return the number of contest team member roles
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContestTeamMemberRolePersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContestTeamMemberRolePersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ContestTeamMemberRolePersistence.class.getName());

            ReferenceRegistry.registerReference(ContestTeamMemberRoleUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ContestTeamMemberRolePersistence persistence) {
    }
}
