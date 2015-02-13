package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestDiscussion;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the contest discussion service. This utility wraps {@link ContestDiscussionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDiscussionPersistence
 * @see ContestDiscussionPersistenceImpl
 * @generated
 */
public class ContestDiscussionUtil {
    private static ContestDiscussionPersistence _persistence;

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
    public static void clearCache(ContestDiscussion contestDiscussion) {
        getPersistence().clearCache(contestDiscussion);
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
    public static List<ContestDiscussion> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ContestDiscussion> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ContestDiscussion> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
     */
    public static ContestDiscussion update(ContestDiscussion contestDiscussion)
        throws SystemException {
        return getPersistence().update(contestDiscussion);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
     */
    public static ContestDiscussion update(
        ContestDiscussion contestDiscussion, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(contestDiscussion, serviceContext);
    }

    /**
    * Returns the contest discussion where ContestId = &#63; and Tab = &#63; or throws a {@link com.ext.portlet.NoSuchContestDiscussionException} if it could not be found.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @return the matching contest discussion
    * @throws com.ext.portlet.NoSuchContestDiscussionException if a matching contest discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestDiscussion findByContestIdAndTab(
        long ContestId, java.lang.String Tab)
        throws com.ext.portlet.NoSuchContestDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByContestIdAndTab(ContestId, Tab);
    }

    /**
    * Returns the contest discussion where ContestId = &#63; and Tab = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @return the matching contest discussion, or <code>null</code> if a matching contest discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestDiscussion fetchByContestIdAndTab(
        long ContestId, java.lang.String Tab)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByContestIdAndTab(ContestId, Tab);
    }

    /**
    * Returns the contest discussion where ContestId = &#63; and Tab = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching contest discussion, or <code>null</code> if a matching contest discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestDiscussion fetchByContestIdAndTab(
        long ContestId, java.lang.String Tab, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByContestIdAndTab(ContestId, Tab, retrieveFromCache);
    }

    /**
    * Removes the contest discussion where ContestId = &#63; and Tab = &#63; from the database.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @return the contest discussion that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestDiscussion removeByContestIdAndTab(
        long ContestId, java.lang.String Tab)
        throws com.ext.portlet.NoSuchContestDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().removeByContestIdAndTab(ContestId, Tab);
    }

    /**
    * Returns the number of contest discussions where ContestId = &#63; and Tab = &#63;.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @return the number of matching contest discussions
    * @throws SystemException if a system exception occurred
    */
    public static int countByContestIdAndTab(long ContestId,
        java.lang.String Tab)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByContestIdAndTab(ContestId, Tab);
    }

    /**
    * Caches the contest discussion in the entity cache if it is enabled.
    *
    * @param contestDiscussion the contest discussion
    */
    public static void cacheResult(
        com.ext.portlet.model.ContestDiscussion contestDiscussion) {
        getPersistence().cacheResult(contestDiscussion);
    }

    /**
    * Caches the contest discussions in the entity cache if it is enabled.
    *
    * @param contestDiscussions the contest discussions
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.ContestDiscussion> contestDiscussions) {
        getPersistence().cacheResult(contestDiscussions);
    }

    /**
    * Creates a new contest discussion with the primary key. Does not add the contest discussion to the database.
    *
    * @param DiscussionId the primary key for the new contest discussion
    * @return the new contest discussion
    */
    public static com.ext.portlet.model.ContestDiscussion create(
        long DiscussionId) {
        return getPersistence().create(DiscussionId);
    }

    /**
    * Removes the contest discussion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param DiscussionId the primary key of the contest discussion
    * @return the contest discussion that was removed
    * @throws com.ext.portlet.NoSuchContestDiscussionException if a contest discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestDiscussion remove(
        long DiscussionId)
        throws com.ext.portlet.NoSuchContestDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(DiscussionId);
    }

    public static com.ext.portlet.model.ContestDiscussion updateImpl(
        com.ext.portlet.model.ContestDiscussion contestDiscussion)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(contestDiscussion);
    }

    /**
    * Returns the contest discussion with the primary key or throws a {@link com.ext.portlet.NoSuchContestDiscussionException} if it could not be found.
    *
    * @param DiscussionId the primary key of the contest discussion
    * @return the contest discussion
    * @throws com.ext.portlet.NoSuchContestDiscussionException if a contest discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestDiscussion findByPrimaryKey(
        long DiscussionId)
        throws com.ext.portlet.NoSuchContestDiscussionException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(DiscussionId);
    }

    /**
    * Returns the contest discussion with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param DiscussionId the primary key of the contest discussion
    * @return the contest discussion, or <code>null</code> if a contest discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.ContestDiscussion fetchByPrimaryKey(
        long DiscussionId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(DiscussionId);
    }

    /**
    * Returns all the contest discussions.
    *
    * @return the contest discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestDiscussion> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the contest discussions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest discussions
    * @param end the upper bound of the range of contest discussions (not inclusive)
    * @return the range of contest discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestDiscussion> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the contest discussions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.ContestDiscussionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contest discussions
    * @param end the upper bound of the range of contest discussions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contest discussions
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.ContestDiscussion> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the contest discussions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of contest discussions.
    *
    * @return the number of contest discussions
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ContestDiscussionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ContestDiscussionPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    ContestDiscussionPersistence.class.getName());

            ReferenceRegistry.registerReference(ContestDiscussionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ContestDiscussionPersistence persistence) {
    }
}
