package com.ext.portlet.contests.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the contest team member local service. This utility wraps {@link com.ext.portlet.contests.service.impl.ContestTeamMemberLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestTeamMemberLocalService
 * @see com.ext.portlet.contests.service.base.ContestTeamMemberLocalServiceBaseImpl
 * @see com.ext.portlet.contests.service.impl.ContestTeamMemberLocalServiceImpl
 * @generated
 */
public class ContestTeamMemberLocalServiceUtil {
    private static ContestTeamMemberLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.contests.service.impl.ContestTeamMemberLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the contest team member to the database. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMember the contest team member
    * @return the contest team member that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestTeamMember addContestTeamMember(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addContestTeamMember(contestTeamMember);
    }

    /**
    * Creates a new contest team member with the primary key. Does not add the contest team member to the database.
    *
    * @param id the primary key for the new contest team member
    * @return the new contest team member
    */
    public static com.ext.portlet.contests.model.ContestTeamMember createContestTeamMember(
        long id) {
        return getService().createContestTeamMember(id);
    }

    /**
    * Deletes the contest team member with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the contest team member
    * @throws PortalException if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestTeamMember(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestTeamMember(id);
    }

    /**
    * Deletes the contest team member from the database. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMember the contest team member
    * @throws SystemException if a system exception occurred
    */
    public static void deleteContestTeamMember(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteContestTeamMember(contestTeamMember);
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember fetchContestTeamMember(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchContestTeamMember(id);
    }

    /**
    * Returns the contest team member with the primary key.
    *
    * @param id the primary key of the contest team member
    * @return the contest team member
    * @throws PortalException if a contest team member with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestTeamMember getContestTeamMember(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestTeamMember(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> getContestTeamMembers(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestTeamMembers(start, end);
    }

    /**
    * Returns the number of contest team members.
    *
    * @return the number of contest team members
    * @throws SystemException if a system exception occurred
    */
    public static int getContestTeamMembersCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getContestTeamMembersCount();
    }

    /**
    * Updates the contest team member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMember the contest team member
    * @return the contest team member that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestTeamMember updateContestTeamMember(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestTeamMember(contestTeamMember);
    }

    /**
    * Updates the contest team member in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param contestTeamMember the contest team member
    * @param merge whether to merge the contest team member with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the contest team member that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.contests.model.ContestTeamMember updateContestTeamMember(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateContestTeamMember(contestTeamMember, merge);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static com.ext.portlet.contests.model.ContestTeamMember addContestTeamMember(
        java.lang.Long userId, java.lang.Long contestPk, java.lang.String role)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addContestTeamMember(userId, contestPk, role);
    }

    public static java.util.List<com.ext.portlet.contests.model.ContestTeamMember> findForContest(
        java.lang.Long contestPk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findForContest(contestPk);
    }

    public static void store(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(contestTeamMember);
    }

    public static void delete(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().delete(contestTeamMember);
    }

    public static com.liferay.portal.model.User getUser(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getUser(contestTeamMember);
    }

    public static com.ext.portlet.contests.model.Contest getContest(
        com.ext.portlet.contests.model.ContestTeamMember contestTeamMember)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getContest(contestTeamMember);
    }

    public static void clearService() {
        _service = null;
    }

    public static ContestTeamMemberLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ContestTeamMemberLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    ContestTeamMemberLocalService.class.getName(),
                    portletClassLoader);

            _service = new ContestTeamMemberLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(ContestTeamMemberLocalServiceUtil.class,
                "_service");
            MethodCache.remove(ContestTeamMemberLocalService.class);
        }

        return _service;
    }

    public void setService(ContestTeamMemberLocalService service) {
        MethodCache.remove(ContestTeamMemberLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(ContestTeamMemberLocalServiceUtil.class,
            "_service");
        MethodCache.remove(ContestTeamMemberLocalService.class);
    }
}
