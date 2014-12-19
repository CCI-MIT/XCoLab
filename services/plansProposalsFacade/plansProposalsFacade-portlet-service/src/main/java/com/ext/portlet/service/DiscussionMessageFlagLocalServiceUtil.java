package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for DiscussionMessageFlag. This utility wraps
 * {@link com.ext.portlet.service.impl.DiscussionMessageFlagLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlagLocalService
 * @see com.ext.portlet.service.base.DiscussionMessageFlagLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.DiscussionMessageFlagLocalServiceImpl
 * @generated
 */
public class DiscussionMessageFlagLocalServiceUtil {
    private static DiscussionMessageFlagLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.DiscussionMessageFlagLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the discussion message flag to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @return the discussion message flag that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag addDiscussionMessageFlag(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addDiscussionMessageFlag(discussionMessageFlag);
    }

    /**
    * Creates a new discussion message flag with the primary key. Does not add the discussion message flag to the database.
    *
    * @param pk the primary key for the new discussion message flag
    * @return the new discussion message flag
    */
    public static com.ext.portlet.model.DiscussionMessageFlag createDiscussionMessageFlag(
        long pk) {
        return getService().createDiscussionMessageFlag(pk);
    }

    /**
    * Deletes the discussion message flag with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag that was removed
    * @throws PortalException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag deleteDiscussionMessageFlag(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteDiscussionMessageFlag(pk);
    }

    /**
    * Deletes the discussion message flag from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @return the discussion message flag that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag deleteDiscussionMessageFlag(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().deleteDiscussionMessageFlag(discussionMessageFlag);
    }

    public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.liferay.portal.kernel.dao.orm.Projection projection)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.ext.portlet.model.DiscussionMessageFlag fetchDiscussionMessageFlag(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchDiscussionMessageFlag(pk);
    }

    /**
    * Returns the discussion message flag with the primary key.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag
    * @throws PortalException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag getDiscussionMessageFlag(
        long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionMessageFlag(pk);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the discussion message flags.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @return the range of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessageFlag> getDiscussionMessageFlags(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionMessageFlags(start, end);
    }

    /**
    * Returns the number of discussion message flags.
    *
    * @return the number of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static int getDiscussionMessageFlagsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionMessageFlagsCount();
    }

    /**
    * Updates the discussion message flag in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionMessageFlag the discussion message flag
    * @return the discussion message flag that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag updateDiscussionMessageFlag(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateDiscussionMessageFlag(discussionMessageFlag);
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

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findMessageFlags(
        java.lang.Long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().findMessageFlags(messageId);
    }

    public static com.ext.portlet.model.DiscussionMessageFlag createFlag(
        java.lang.Long messageId, java.lang.String flagType,
        java.lang.String data, java.lang.Long userId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createFlag(messageId, flagType, data, userId);
    }

    public static void clearService() {
        _service = null;
    }

    public static DiscussionMessageFlagLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    DiscussionMessageFlagLocalService.class.getName());

            if (invokableLocalService instanceof DiscussionMessageFlagLocalService) {
                _service = (DiscussionMessageFlagLocalService) invokableLocalService;
            } else {
                _service = new DiscussionMessageFlagLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(DiscussionMessageFlagLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(DiscussionMessageFlagLocalService service) {
    }
}
