package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MemberCategory;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the member category service. This utility wraps {@link MemberCategoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberCategoryPersistence
 * @see MemberCategoryPersistenceImpl
 * @generated
 */
public class MemberCategoryUtil {
    private static MemberCategoryPersistence _persistence;

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
    public static void clearCache(MemberCategory memberCategory) {
        getPersistence().clearCache(memberCategory);
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
    public static List<MemberCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MemberCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MemberCategory> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static MemberCategory update(MemberCategory memberCategory)
        throws SystemException {
        return getPersistence().update(memberCategory);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static MemberCategory update(MemberCategory memberCategory,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(memberCategory, serviceContext);
    }

    /**
    * Returns the member category where displayName = &#63; or throws a {@link com.ext.portlet.NoSuchMemberCategoryException} if it could not be found.
    *
    * @param displayName the display name
    * @return the matching member category
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory findBydisplayName(
        java.lang.String displayName)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findBydisplayName(displayName);
    }

    /**
    * Returns the member category where displayName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param displayName the display name
    * @return the matching member category, or <code>null</code> if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory fetchBydisplayName(
        java.lang.String displayName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchBydisplayName(displayName);
    }

    /**
    * Returns the member category where displayName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param displayName the display name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching member category, or <code>null</code> if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory fetchBydisplayName(
        java.lang.String displayName, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchBydisplayName(displayName, retrieveFromCache);
    }

    /**
    * Removes the member category where displayName = &#63; from the database.
    *
    * @param displayName the display name
    * @return the member category that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory removeBydisplayName(
        java.lang.String displayName)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeBydisplayName(displayName);
    }

    /**
    * Returns the number of member categories where displayName = &#63;.
    *
    * @param displayName the display name
    * @return the number of matching member categories
    * @throws SystemException if a system exception occurred
    */
    public static int countBydisplayName(java.lang.String displayName)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countBydisplayName(displayName);
    }

    /**
    * Returns all the member categories where showInList = &#63;.
    *
    * @param showInList the show in list
    * @return the matching member categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MemberCategory> findByshowInList(
        boolean showInList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByshowInList(showInList);
    }

    /**
    * Returns a range of all the member categories where showInList = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param showInList the show in list
    * @param start the lower bound of the range of member categories
    * @param end the upper bound of the range of member categories (not inclusive)
    * @return the range of matching member categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MemberCategory> findByshowInList(
        boolean showInList, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByshowInList(showInList, start, end);
    }

    /**
    * Returns an ordered range of all the member categories where showInList = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param showInList the show in list
    * @param start the lower bound of the range of member categories
    * @param end the upper bound of the range of member categories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching member categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MemberCategory> findByshowInList(
        boolean showInList, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByshowInList(showInList, start, end, orderByComparator);
    }

    /**
    * Returns the first member category in the ordered set where showInList = &#63;.
    *
    * @param showInList the show in list
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching member category
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory findByshowInList_First(
        boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByshowInList_First(showInList, orderByComparator);
    }

    /**
    * Returns the first member category in the ordered set where showInList = &#63;.
    *
    * @param showInList the show in list
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching member category, or <code>null</code> if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory fetchByshowInList_First(
        boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByshowInList_First(showInList, orderByComparator);
    }

    /**
    * Returns the last member category in the ordered set where showInList = &#63;.
    *
    * @param showInList the show in list
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching member category
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory findByshowInList_Last(
        boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByshowInList_Last(showInList, orderByComparator);
    }

    /**
    * Returns the last member category in the ordered set where showInList = &#63;.
    *
    * @param showInList the show in list
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching member category, or <code>null</code> if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory fetchByshowInList_Last(
        boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByshowInList_Last(showInList, orderByComparator);
    }

    /**
    * Returns the member categories before and after the current member category in the ordered set where showInList = &#63;.
    *
    * @param roleId the primary key of the current member category
    * @param showInList the show in list
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next member category
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory[] findByshowInList_PrevAndNext(
        long roleId, boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByshowInList_PrevAndNext(roleId, showInList,
            orderByComparator);
    }

    /**
    * Removes all the member categories where showInList = &#63; from the database.
    *
    * @param showInList the show in list
    * @throws SystemException if a system exception occurred
    */
    public static void removeByshowInList(boolean showInList)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByshowInList(showInList);
    }

    /**
    * Returns the number of member categories where showInList = &#63;.
    *
    * @param showInList the show in list
    * @return the number of matching member categories
    * @throws SystemException if a system exception occurred
    */
    public static int countByshowInList(boolean showInList)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByshowInList(showInList);
    }

    /**
    * Caches the member category in the entity cache if it is enabled.
    *
    * @param memberCategory the member category
    */
    public static void cacheResult(
        com.ext.portlet.model.MemberCategory memberCategory) {
        getPersistence().cacheResult(memberCategory);
    }

    /**
    * Caches the member categories in the entity cache if it is enabled.
    *
    * @param memberCategories the member categories
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.MemberCategory> memberCategories) {
        getPersistence().cacheResult(memberCategories);
    }

    /**
    * Creates a new member category with the primary key. Does not add the member category to the database.
    *
    * @param roleId the primary key for the new member category
    * @return the new member category
    */
    public static com.ext.portlet.model.MemberCategory create(long roleId) {
        return getPersistence().create(roleId);
    }

    /**
    * Removes the member category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param roleId the primary key of the member category
    * @return the member category that was removed
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory remove(long roleId)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(roleId);
    }

    public static com.ext.portlet.model.MemberCategory updateImpl(
        com.ext.portlet.model.MemberCategory memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(memberCategory);
    }

    /**
    * Returns the member category with the primary key or throws a {@link com.ext.portlet.NoSuchMemberCategoryException} if it could not be found.
    *
    * @param roleId the primary key of the member category
    * @return the member category
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory findByPrimaryKey(
        long roleId)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(roleId);
    }

    /**
    * Returns the member category with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param roleId the primary key of the member category
    * @return the member category, or <code>null</code> if a member category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.MemberCategory fetchByPrimaryKey(
        long roleId) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(roleId);
    }

    /**
    * Returns all the member categories.
    *
    * @return the member categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MemberCategory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the member categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of member categories
    * @param end the upper bound of the range of member categories (not inclusive)
    * @return the range of member categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MemberCategory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the member categories.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.MemberCategoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of member categories
    * @param end the upper bound of the range of member categories (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of member categories
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.MemberCategory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the member categories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of member categories.
    *
    * @return the number of member categories
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MemberCategoryPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MemberCategoryPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    MemberCategoryPersistence.class.getName());

            ReferenceRegistry.registerReference(MemberCategoryUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MemberCategoryPersistence persistence) {
    }
}
