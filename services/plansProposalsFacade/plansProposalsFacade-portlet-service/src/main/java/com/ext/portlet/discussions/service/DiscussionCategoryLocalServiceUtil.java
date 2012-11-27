package com.ext.portlet.discussions.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the discussion category local service. This utility wraps {@link com.ext.portlet.discussions.service.impl.DiscussionCategoryLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryLocalService
 * @see com.ext.portlet.discussions.service.base.DiscussionCategoryLocalServiceBaseImpl
 * @see com.ext.portlet.discussions.service.impl.DiscussionCategoryLocalServiceImpl
 * @generated
 */
public class DiscussionCategoryLocalServiceUtil {
    private static DiscussionCategoryLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.ext.portlet.discussions.service.impl.DiscussionCategoryLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the discussion category to the database. Also notifies the appropriate model listeners.
    *
    * @param discussionCategory the discussion category
    * @return the discussion category that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.discussions.model.DiscussionCategory addDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().addDiscussionCategory(discussionCategory);
    }

    /**
    * Creates a new discussion category with the primary key. Does not add the discussion category to the database.
    *
    * @param pk the primary key for the new discussion category
    * @return the new discussion category
    */
    public static com.ext.portlet.discussions.model.DiscussionCategory createDiscussionCategory(
        java.lang.Long pk) {
        return getService().createDiscussionCategory(pk);
    }

    /**
    * Deletes the discussion category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion category
    * @throws PortalException if a discussion category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static void deleteDiscussionCategory(java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        getService().deleteDiscussionCategory(pk);
    }

    /**
    * Deletes the discussion category from the database. Also notifies the appropriate model listeners.
    *
    * @param discussionCategory the discussion category
    * @throws SystemException if a system exception occurred
    */
    public static void deleteDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        getService().deleteDiscussionCategory(discussionCategory);
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

    public static com.ext.portlet.discussions.model.DiscussionCategory fetchDiscussionCategory(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().fetchDiscussionCategory(pk);
    }

    /**
    * Returns the discussion category with the primary key.
    *
    * @param pk the primary key of the discussion category
    * @return the discussion category
    * @throws PortalException if a discussion category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.discussions.model.DiscussionCategory getDiscussionCategory(
        java.lang.Long pk)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionCategory(pk);
    }

    public static com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.liferay.portal.kernel.exception.PortalException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the discussion categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion categories
    * @param end the upper bound of the range of discussion categories (not inclusive)
    * @return the range of discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getDiscussionCategories(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionCategories(start, end);
    }

    /**
    * Returns the number of discussion categories.
    *
    * @return the number of discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static int getDiscussionCategoriesCount()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionCategoriesCount();
    }

    /**
    * Updates the discussion category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionCategory the discussion category
    * @return the discussion category that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.discussions.model.DiscussionCategory updateDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateDiscussionCategory(discussionCategory);
    }

    /**
    * Updates the discussion category in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param discussionCategory the discussion category
    * @param merge whether to merge the discussion category with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the discussion category that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.discussions.model.DiscussionCategory updateDiscussionCategory(
        com.ext.portlet.discussions.model.DiscussionCategory discussionCategory,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().updateDiscussionCategory(discussionCategory, merge);
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

    public static java.util.List<com.ext.portlet.discussions.model.DiscussionCategory> getCategoriesByCategoryGroupId(
        java.lang.Long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService().getCategoriesByCategoryGroupId(categoryGroupId);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory getDiscussionCategoryById(
        java.lang.Long categoryId)
        throws com.ext.portlet.discussions.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getService().getDiscussionCategoryById(categoryId);
    }

    public static com.ext.portlet.discussions.model.DiscussionCategory createDebateCategory(
        java.lang.Long categoryGroupId, java.lang.String name,
        java.lang.String description, com.liferay.portal.model.User author)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getService()
                   .createDebateCategory(categoryGroupId, name, description,
            author);
    }

    public static void clearService() {
        _service = null;
    }

    public static DiscussionCategoryLocalService getService() {
        if (_service == null) {
            Object object = PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    DiscussionCategoryLocalService.class.getName());
            ClassLoader portletClassLoader = (ClassLoader) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    "portletClassLoader");

            ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(object,
                    DiscussionCategoryLocalService.class.getName(),
                    portletClassLoader);

            _service = new DiscussionCategoryLocalServiceClp(classLoaderProxy);

            ClpSerializer.setClassLoader(portletClassLoader);

            ReferenceRegistry.registerReference(DiscussionCategoryLocalServiceUtil.class,
                "_service");
            MethodCache.remove(DiscussionCategoryLocalService.class);
        }

        return _service;
    }

    public void setService(DiscussionCategoryLocalService service) {
        MethodCache.remove(DiscussionCategoryLocalService.class);

        _service = service;

        ReferenceRegistry.registerReference(DiscussionCategoryLocalServiceUtil.class,
            "_service");
        MethodCache.remove(DiscussionCategoryLocalService.class);
    }
}
