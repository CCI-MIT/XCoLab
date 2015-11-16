package com.ext.portlet.service.persistence;

import com.ext.portlet.model.MemberCategory;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the member category service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see MemberCategoryPersistenceImpl
 * @see MemberCategoryUtil
 * @generated
 */
public interface MemberCategoryPersistence extends BasePersistence<MemberCategory> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link MemberCategoryUtil} to access the member category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the member category where displayName = &#63; or throws a {@link com.ext.portlet.NoSuchMemberCategoryException} if it could not be found.
    *
    * @param displayName the display name
    * @return the matching member category
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory findBydisplayName(
        java.lang.String displayName)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the member category where displayName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param displayName the display name
    * @return the matching member category, or <code>null</code> if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory fetchBydisplayName(
        java.lang.String displayName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the member category where displayName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param displayName the display name
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching member category, or <code>null</code> if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory fetchBydisplayName(
        java.lang.String displayName, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the member category where displayName = &#63; from the database.
    *
    * @param displayName the display name
    * @return the member category that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory removeBydisplayName(
        java.lang.String displayName)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of member categories where displayName = &#63;.
    *
    * @param displayName the display name
    * @return the number of matching member categories
    * @throws SystemException if a system exception occurred
    */
    public int countBydisplayName(java.lang.String displayName)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the member categories where showInList = &#63;.
    *
    * @param showInList the show in list
    * @return the matching member categories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MemberCategory> findByshowInList(
        boolean showInList)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.MemberCategory> findByshowInList(
        boolean showInList, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.MemberCategory> findByshowInList(
        boolean showInList, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first member category in the ordered set where showInList = &#63;.
    *
    * @param showInList the show in list
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching member category
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory findByshowInList_First(
        boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first member category in the ordered set where showInList = &#63;.
    *
    * @param showInList the show in list
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching member category, or <code>null</code> if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory fetchByshowInList_First(
        boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last member category in the ordered set where showInList = &#63;.
    *
    * @param showInList the show in list
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching member category
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory findByshowInList_Last(
        boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last member category in the ordered set where showInList = &#63;.
    *
    * @param showInList the show in list
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching member category, or <code>null</code> if a matching member category could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory fetchByshowInList_Last(
        boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public com.ext.portlet.model.MemberCategory[] findByshowInList_PrevAndNext(
        long roleId, boolean showInList,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the member categories where showInList = &#63; from the database.
    *
    * @param showInList the show in list
    * @throws SystemException if a system exception occurred
    */
    public void removeByshowInList(boolean showInList)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of member categories where showInList = &#63;.
    *
    * @param showInList the show in list
    * @return the number of matching member categories
    * @throws SystemException if a system exception occurred
    */
    public int countByshowInList(boolean showInList)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the member category in the entity cache if it is enabled.
    *
    * @param memberCategory the member category
    */
    public void cacheResult(com.ext.portlet.model.MemberCategory memberCategory);

    /**
    * Caches the member categories in the entity cache if it is enabled.
    *
    * @param memberCategories the member categories
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.MemberCategory> memberCategories);

    /**
    * Creates a new member category with the primary key. Does not add the member category to the database.
    *
    * @param roleId the primary key for the new member category
    * @return the new member category
    */
    public com.ext.portlet.model.MemberCategory create(long roleId);

    /**
    * Removes the member category with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param roleId the primary key of the member category
    * @return the member category that was removed
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory remove(long roleId)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.MemberCategory updateImpl(
        com.ext.portlet.model.MemberCategory memberCategory)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the member category with the primary key or throws a {@link com.ext.portlet.NoSuchMemberCategoryException} if it could not be found.
    *
    * @param roleId the primary key of the member category
    * @return the member category
    * @throws com.ext.portlet.NoSuchMemberCategoryException if a member category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory findByPrimaryKey(long roleId)
        throws com.ext.portlet.NoSuchMemberCategoryException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the member category with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param roleId the primary key of the member category
    * @return the member category, or <code>null</code> if a member category with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.MemberCategory fetchByPrimaryKey(long roleId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the member categories.
    *
    * @return the member categories
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.MemberCategory> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.MemberCategory> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.MemberCategory> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the member categories from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of member categories.
    *
    * @return the number of member categories
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
