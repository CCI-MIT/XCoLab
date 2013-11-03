package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionMessageFlag;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the discussion message flag service. This utility wraps {@link DiscussionMessageFlagPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlagPersistence
 * @see DiscussionMessageFlagPersistenceImpl
 * @generated
 */
public class DiscussionMessageFlagUtil {
    private static DiscussionMessageFlagPersistence _persistence;

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
    public static void clearCache(DiscussionMessageFlag discussionMessageFlag) {
        getPersistence().clearCache(discussionMessageFlag);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<DiscussionMessageFlag> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<DiscussionMessageFlag> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<DiscussionMessageFlag> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
     */
    public static DiscussionMessageFlag update(
        DiscussionMessageFlag discussionMessageFlag, boolean merge)
        throws SystemException {
        return getPersistence().update(discussionMessageFlag, merge);
    }

    /**
     * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
     */
    public static DiscussionMessageFlag update(
        DiscussionMessageFlag discussionMessageFlag, boolean merge,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(discussionMessageFlag, merge, serviceContext);
    }

    /**
    * Caches the discussion message flag in the entity cache if it is enabled.
    *
    * @param discussionMessageFlag the discussion message flag
    */
    public static void cacheResult(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag) {
        getPersistence().cacheResult(discussionMessageFlag);
    }

    /**
    * Caches the discussion message flags in the entity cache if it is enabled.
    *
    * @param discussionMessageFlags the discussion message flags
    */
    public static void cacheResult(
        java.util.List<com.ext.portlet.model.DiscussionMessageFlag> discussionMessageFlags) {
        getPersistence().cacheResult(discussionMessageFlags);
    }

    /**
    * Creates a new discussion message flag with the primary key. Does not add the discussion message flag to the database.
    *
    * @param pk the primary key for the new discussion message flag
    * @return the new discussion message flag
    */
    public static com.ext.portlet.model.DiscussionMessageFlag create(long pk) {
        return getPersistence().create(pk);
    }

    /**
    * Removes the discussion message flag with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag that was removed
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag remove(long pk)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().remove(pk);
    }

    public static com.ext.portlet.model.DiscussionMessageFlag updateImpl(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag,
        boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(discussionMessageFlag, merge);
    }

    /**
    * Returns the discussion message flag with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionMessageFlagException} if it could not be found.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag findByPrimaryKey(
        long pk)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(pk);
    }

    /**
    * Returns the discussion message flag with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag, or <code>null</code> if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag fetchByPrimaryKey(
        long pk) throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(pk);
    }

    /**
    * Returns all the discussion message flags where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findByMessageId(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByMessageId(messageId);
    }

    /**
    * Returns a range of all the discussion message flags where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @return the range of matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findByMessageId(
        long messageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByMessageId(messageId, start, end);
    }

    /**
    * Returns an ordered range of all the discussion message flags where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findByMessageId(
        long messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMessageId(messageId, start, end, orderByComparator);
    }

    /**
    * Returns the first discussion message flag in the ordered set where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag findByMessageId_First(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMessageId_First(messageId, orderByComparator);
    }

    /**
    * Returns the last discussion message flag in the ordered set where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag findByMessageId_Last(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMessageId_Last(messageId, orderByComparator);
    }

    /**
    * Returns the discussion message flags before and after the current discussion message flag in the ordered set where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param pk the primary key of the current discussion message flag
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag[] findByMessageId_PrevAndNext(
        long pk, long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .findByMessageId_PrevAndNext(pk, messageId, orderByComparator);
    }

    /**
    * Returns the discussion message flag where messageId = &#63; and flagType = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionMessageFlagException} if it could not be found.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @return the matching discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag findByMessageIdFlagType(
        long messageId, java.lang.String flagType)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findByMessageIdFlagType(messageId, flagType);
    }

    /**
    * Returns the discussion message flag where messageId = &#63; and flagType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @return the matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag fetchByMessageIdFlagType(
        long messageId, java.lang.String flagType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().fetchByMessageIdFlagType(messageId, flagType);
    }

    /**
    * Returns the discussion message flag where messageId = &#63; and flagType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.ext.portlet.model.DiscussionMessageFlag fetchByMessageIdFlagType(
        long messageId, java.lang.String flagType, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence()
                   .fetchByMessageIdFlagType(messageId, flagType,
            retrieveFromCache);
    }

    /**
    * Returns all the discussion message flags.
    *
    * @return the discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the discussion message flags.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @return the range of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the discussion message flags.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the discussion message flags where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByMessageId(messageId);
    }

    /**
    * Removes the discussion message flag where messageId = &#63; and flagType = &#63; from the database.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @throws SystemException if a system exception occurred
    */
    public static void removeByMessageIdFlagType(long messageId,
        java.lang.String flagType)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeByMessageIdFlagType(messageId, flagType);
    }

    /**
    * Removes all the discussion message flags from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of discussion message flags where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static int countByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByMessageId(messageId);
    }

    /**
    * Returns the number of discussion message flags where messageId = &#63; and flagType = &#63;.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @return the number of matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static int countByMessageIdFlagType(long messageId,
        java.lang.String flagType)
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countByMessageIdFlagType(messageId, flagType);
    }

    /**
    * Returns the number of discussion message flags.
    *
    * @return the number of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.liferay.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static DiscussionMessageFlagPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (DiscussionMessageFlagPersistence) PortletBeanLocatorUtil.locate(com.ext.portlet.service.ClpSerializer.getServletContextName(),
                    DiscussionMessageFlagPersistence.class.getName());

            ReferenceRegistry.registerReference(DiscussionMessageFlagUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    public void setPersistence(DiscussionMessageFlagPersistence persistence) {
        _persistence = persistence;

        ReferenceRegistry.registerReference(DiscussionMessageFlagUtil.class,
            "_persistence");
    }
}
