package com.ext.portlet.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the discussion category group local service. This utility wraps {@link com.ext.portlet.service.impl.DiscussionCategoryGroupLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroupLocalService
 * @see com.ext.portlet.service.base.DiscussionCategoryGroupLocalServiceBaseImpl
 * @see com.ext.portlet.service.impl.DiscussionCategoryGroupLocalServiceImpl
 * @generated
 */
public class DiscussionCategoryGroupLocalServiceUtil {
    private static DiscussionCategoryGroupLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.service.impl.DiscussionCategoryGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the discussion category group to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionCategoryGroup the discussion category group
    * @return the discussion category group that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategoryGroup addDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addDiscussionCategoryGroup(discussionCategoryGroup);
    }

    /**
    * Creates a new discussion category group with the primary key. Does not add the discussion category group to the database.
    *
    * @param id the primary key for the new discussion category group
    * @return the new discussion category group
    */
    public static com.ext.portlet.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        long id) {
        return getService().createDiscussionCategoryGroup(id);
    }

    /**
    * Deletes the discussion category group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the discussion category group
    * @throws PortalException if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteDiscussionCategoryGroup(long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteDiscussionCategoryGroup(id);
    }

    /**
    * Deletes the discussion category group from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionCategoryGroup the discussion category group
    * @throws SystemException if a system exception occurred
    */
    public static void deleteDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteDiscussionCategoryGroup(discussionCategoryGroup);
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

    public static com.ext.portlet.model.DiscussionCategoryGroup fetchDiscussionCategoryGroup(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchDiscussionCategoryGroup(id);
    }

    /**
    * Returns the discussion category group with the primary key.
    *
    * @param id the primary key of the discussion category group
    * @return the discussion category group
    * @throws PortalException if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategoryGroup getDiscussionCategoryGroup(
        long id)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionCategoryGroup(id);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the discussion category groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion category groups
    * @param end the upper bound of the range of discussion category groups (not inclusive)
    * @return the range of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategoryGroup> getDiscussionCategoryGroups(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionCategoryGroups(start, end);
    }

    /**
    * Returns the number of discussion category groups.
    *
    * @return the number of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public static int getDiscussionCategoryGroupsCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionCategoryGroupsCount();
    }

    /**
    * Updates the discussion category group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionCategoryGroup the discussion category group
    * @return the discussion category group that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategoryGroup updateDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateDiscussionCategoryGroup(discussionCategoryGroup);
    }

    /**
    * Updates the discussion category group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionCategoryGroup the discussion category group
    * @param merge whether to merge the discussion category group with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the discussion category group that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategoryGroup updateDiscussionCategoryGroup(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .updateDiscussionCategoryGroup(discussionCategoryGroup, merge);
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

    public static com.ext.portlet.model.DiscussionCategoryGroup createDiscussionCategoryGroup(
        java.lang.String description)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().createDiscussionCategoryGroup(description);
    }

    public static com.ext.portlet.model.DiscussionCategory getCategoryById(
        long categoryId)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCategoryById(categoryId);
    }

    public static com.ext.portlet.model.DiscussionMessage getThreadById(
        long threadId)
        throws com.ext.portlet.NoSuchDiscussionMessageException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getThreadById(threadId);
    }

    public static java.util.List<com.ext.portlet.model.DiscussionCategory> getCategories(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCategories(dcg);
    }

    public static com.ext.portlet.model.DiscussionCategory addCategory(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        java.lang.String name, java.lang.String description,
        com.liferay.portal.model.User creator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addCategory(dcg, name, description, creator);
    }

    public static void store(com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().store(dcg);
    }

    public static com.ext.portlet.model.DiscussionMessage getCommentThread(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCommentThread(dcg);
    }

    public static com.ext.portlet.model.DiscussionMessage addComment(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        java.lang.String title, java.lang.String description,
        com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().addComment(dcg, title, description, author);
    }

    public static int getCommentsCount(
        com.ext.portlet.model.DiscussionCategoryGroup dcg)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getCommentsCount(dcg);
    }

    public static void copyEverything(
        com.ext.portlet.model.DiscussionCategoryGroup dcg,
        com.ext.portlet.model.DiscussionCategoryGroup source)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().copyEverything(dcg, source);
    }

    public static void clearService() {
        _service = null;
    }

    public static DiscussionCategoryGroupLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    DiscussionCategoryGroupLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    DiscussionCategoryGroupLocalService.class.getName(),
                    portletClassLoader);

            _service = new DiscussionCategoryGroupLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(DiscussionCategoryGroupLocalServiceUtil.class,
                "_service");
            MethodCache.remove(DiscussionCategoryGroupLocalService.class);
        }

        return _service;
    }

    public void setService(DiscussionCategoryGroupLocalService service) {
        MethodCache.remove(DiscussionCategoryGroupLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(DiscussionCategoryGroupLocalServiceUtil.class,
            "_service");
        MethodCache.remove(DiscussionCategoryGroupLocalService.class);
    }
}
