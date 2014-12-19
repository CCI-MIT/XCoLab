package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionCategoryGroup;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the discussion category group service. This utility wraps {@link DiscussionCategoryGroupPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryGroupPersistence
 * @see DiscussionCategoryGroupPersistenceImpl
 * @generated
 */
public class DiscussionCategoryGroupUtil {
    private static DiscussionCategoryGroupPersistence _persistence;

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
    public static void clearCache(
        DiscussionCategoryGroup discussionCategoryGroup) {
        getPersistence().clearCache(discussionCategoryGroup);
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
    public static List<DiscussionCategoryGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<DiscussionCategoryGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<DiscussionCategoryGroup> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static DiscussionCategoryGroup update(
        DiscussionCategoryGroup discussionCategoryGroup)
        throws SystemException {
        return getPersistence().update(discussionCategoryGroup);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static DiscussionCategoryGroup update(
        DiscussionCategoryGroup discussionCategoryGroup,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(discussionCategoryGroup, serviceContext);
    }

    /**
    * Caches the discussion category group in the entity cache if it is enabled.
    *
    * @param discussionCategoryGroup the discussion category group
    */
    public static void cacheResult(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup) {
        getPersistence().cacheResult(discussionCategoryGroup);
    }

    /**
    * Caches the discussion category groups in the entity cache if it is enabled.
    *
    * @param discussionCategoryGroups the discussion category groups
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.DiscussionCategoryGroup> discussionCategoryGroups) {
        getPersistence().cacheResult(discussionCategoryGroups);
    }

    /**
    * Creates a new discussion category group with the primary key. Does not add the discussion category group to the database.
    *
    * @param id the primary key for the new discussion category group
    * @return the new discussion category group
    */
    public static com.ext.portlet.model.DiscussionCategoryGroup create(long id) {
        return getPersistence().create(id);
    }

    /**
    * Removes the discussion category group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the discussion category group
    * @return the discussion category group that was removed
    * @throws com.ext.portlet.NoSuchDiscussionCategoryGroupException if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategoryGroup remove(long id)
        throws com.ext.portlet.NoSuchDiscussionCategoryGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(id);
    }

    public static com.ext.portlet.model.DiscussionCategoryGroup updateImpl(
        com.ext.portlet.model.DiscussionCategoryGroup discussionCategoryGroup)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(discussionCategoryGroup);
    }

    /**
    * Returns the discussion category group with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionCategoryGroupException} if it could not be found.
    *
    * @param id the primary key of the discussion category group
    * @return the discussion category group
    * @throws com.ext.portlet.NoSuchDiscussionCategoryGroupException if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategoryGroup findByPrimaryKey(
        long id)
        throws com.ext.portlet.NoSuchDiscussionCategoryGroupException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(id);
    }

    /**
    * Returns the discussion category group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the discussion category group
    * @return the discussion category group, or <code>null</code> if a discussion category group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategoryGroup fetchByPrimaryKey(
        long id) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(id);
    }

    /**
    * Returns all the discussion category groups.
    *
    * @return the discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategoryGroup> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the discussion category groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion category groups
    * @param end the upper bound of the range of discussion category groups (not inclusive)
    * @return the range of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategoryGroup> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the discussion category groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion category groups
    * @param end the upper bound of the range of discussion category groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategoryGroup> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the discussion category groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of discussion category groups.
    *
    * @return the number of discussion category groups
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static DiscussionCategoryGroupPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (DiscussionCategoryGroupPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    DiscussionCategoryGroupPersistence.class.getName());

            ReferenceRegistry.registerReference(DiscussionCategoryGroupUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(DiscussionCategoryGroupPersistence persistence) {
    }
}
