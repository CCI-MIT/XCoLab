package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionCategory;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the discussion category service. This utility wraps {@link DiscussionCategoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionCategoryPersistence
 * @see DiscussionCategoryPersistenceImpl
 * @generated
 */
public class DiscussionCategoryUtil {
    private static DiscussionCategoryPersistence _persistence;

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
    public static void clearCache(DiscussionCategory discussionCategory) {
        getPersistence().clearCache(discussionCategory);
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
    public static List<DiscussionCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<DiscussionCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<DiscussionCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static DiscussionCategory update(
        DiscussionCategory discussionCategory) throws SystemException {
        return getPersistence().update(discussionCategory);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static DiscussionCategory update(
        DiscussionCategory discussionCategory, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(discussionCategory, serviceContext);
    }

    /**
    * Returns all the discussion categories where categoryGroupId = &#63;.
    *
    * @param categoryGroupId the category group ID
    * @return the matching discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategory> findByCategoryGroupId(
        long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns a range of all the discussion categories where categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param categoryGroupId the category group ID
    * @param start the lower bound of the range of discussion categories
    * @param end the upper bound of the range of discussion categories (not inclusive)
    * @return the range of matching discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategory> findByCategoryGroupId(
        long categoryGroupId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryGroupId(categoryGroupId, start, end);
    }

    /**
    * Returns an ordered range of all the discussion categories where categoryGroupId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param categoryGroupId the category group ID
    * @param start the lower bound of the range of discussion categories
    * @param end the upper bound of the range of discussion categories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategory> findByCategoryGroupId(
        long categoryGroupId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryGroupId(categoryGroupId, start, end,
            orderByComparator);
    }

    /**
    * Returns the first discussion category in the ordered set where categoryGroupId = &#63;.
    *
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion category
    * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a matching discussion category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory findByCategoryGroupId_First(
        long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryGroupId_First(categoryGroupId,
            orderByComparator);
    }

    /**
    * Returns the first discussion category in the ordered set where categoryGroupId = &#63;.
    *
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion category, or <code>null</code> if a matching discussion category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory fetchByCategoryGroupId_First(
        long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCategoryGroupId_First(categoryGroupId,
            orderByComparator);
    }

    /**
    * Returns the last discussion category in the ordered set where categoryGroupId = &#63;.
    *
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion category
    * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a matching discussion category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory findByCategoryGroupId_Last(
        long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryGroupId_Last(categoryGroupId,
            orderByComparator);
    }

    /**
    * Returns the last discussion category in the ordered set where categoryGroupId = &#63;.
    *
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion category, or <code>null</code> if a matching discussion category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory fetchByCategoryGroupId_Last(
        long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByCategoryGroupId_Last(categoryGroupId,
            orderByComparator);
    }

    /**
    * Returns the discussion categories before and after the current discussion category in the ordered set where categoryGroupId = &#63;.
    *
    * @param pk the primary key of the current discussion category
    * @param categoryGroupId the category group ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion category
    * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a discussion category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory[] findByCategoryGroupId_PrevAndNext(
        long pk, long categoryGroupId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByCategoryGroupId_PrevAndNext(pk, categoryGroupId,
            orderByComparator);
    }

    /**
    * Removes all the discussion categories where categoryGroupId = &#63; from the database.
    *
    * @param categoryGroupId the category group ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByCategoryGroupId(long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns the number of discussion categories where categoryGroupId = &#63;.
    *
    * @param categoryGroupId the category group ID
    * @return the number of matching discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static int countByCategoryGroupId(long categoryGroupId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCategoryGroupId(categoryGroupId);
    }

    /**
    * Returns the discussion category where categoryId = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionCategoryException} if it could not be found.
    *
    * @param categoryId the category ID
    * @return the matching discussion category
    * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a matching discussion category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory findByCategoryId(
        long categoryId)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByCategoryId(categoryId);
    }

    /**
    * Returns the discussion category where categoryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param categoryId the category ID
    * @return the matching discussion category, or <code>null</code> if a matching discussion category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory fetchByCategoryId(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCategoryId(categoryId);
    }

    /**
    * Returns the discussion category where categoryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param categoryId the category ID
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching discussion category, or <code>null</code> if a matching discussion category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory fetchByCategoryId(
        long categoryId, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByCategoryId(categoryId, retrieveFromCache);
    }

    /**
    * Removes the discussion category where categoryId = &#63; from the database.
    *
    * @param categoryId the category ID
    * @return the discussion category that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory removeByCategoryId(
        long categoryId)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByCategoryId(categoryId);
    }

    /**
    * Returns the number of discussion categories where categoryId = &#63;.
    *
    * @param categoryId the category ID
    * @return the number of matching discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static int countByCategoryId(long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByCategoryId(categoryId);
    }

    /**
    * Caches the discussion category in the entity cache if it is enabled.
    *
    * @param discussionCategory the discussion category
    */
    public static void cacheResult(
        com.ext.portlet.model.DiscussionCategory discussionCategory) {
        getPersistence().cacheResult(discussionCategory);
    }

    /**
    * Caches the discussion categories in the entity cache if it is enabled.
    *
    * @param discussionCategories the discussion categories
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.DiscussionCategory> discussionCategories) {
        getPersistence().cacheResult(discussionCategories);
    }

    /**
    * Creates a new discussion category with the primary key. Does not add the discussion category to the database.
    *
    * @param pk the primary key for the new discussion category
    * @return the new discussion category
    */
    public static com.ext.portlet.model.DiscussionCategory create(long pk) {
        return getPersistence().create(pk);
    }

    /**
    * Removes the discussion category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion category
    * @return the discussion category that was removed
    * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a discussion category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory remove(long pk)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(pk);
    }

    public static com.ext.portlet.model.DiscussionCategory updateImpl(
        com.ext.portlet.model.DiscussionCategory discussionCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(discussionCategory);
    }

    /**
    * Returns the discussion category with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionCategoryException} if it could not be found.
    *
    * @param pk the primary key of the discussion category
    * @return the discussion category
    * @throws com.ext.portlet.NoSuchDiscussionCategoryException if a discussion category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory findByPrimaryKey(
        long pk)
        throws com.ext.portlet.NoSuchDiscussionCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(pk);
    }

    /**
    * Returns the discussion category with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param pk the primary key of the discussion category
    * @return the discussion category, or <code>null</code> if a discussion category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionCategory fetchByPrimaryKey(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(pk);
    }

    /**
    * Returns all the discussion categories.
    *
    * @return the discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the discussion categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion categories
    * @param end the upper bound of the range of discussion categories (not inclusive)
    * @return the range of discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the discussion categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion categories
    * @param end the upper bound of the range of discussion categories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionCategory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the discussion categories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of discussion categories.
    *
    * @return the number of discussion categories
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static DiscussionCategoryPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (DiscussionCategoryPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    DiscussionCategoryPersistence.class.getName());

            ReferenceRegistry.registerReference(DiscussionCategoryUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(DiscussionCategoryPersistence persistence) {
    }
}
