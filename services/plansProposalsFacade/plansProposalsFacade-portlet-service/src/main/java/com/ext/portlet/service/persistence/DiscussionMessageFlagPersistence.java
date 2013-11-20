package com.ext.portlet.service.persistence;

import com.ext.portlet.model.DiscussionMessageFlag;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the discussion message flag service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see DiscussionMessageFlagPersistenceImpl
 * @see DiscussionMessageFlagUtil
 * @generated
 */
public interface DiscussionMessageFlagPersistence extends BasePersistence<DiscussionMessageFlag> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DiscussionMessageFlagUtil} to access the discussion message flag persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the discussion message flags where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findByMessageId(
        long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the discussion message flags where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param messageId the message ID
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @return the range of matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findByMessageId(
        long messageId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion message flags where messageId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param messageId the message ID
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findByMessageId(
        long messageId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message flag in the ordered set where messageId = &#63;.
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag findByMessageId_First(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first discussion message flag in the ordered set where messageId = &#63;.
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag fetchByMessageId_First(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message flag in the ordered set where messageId = &#63;.
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag findByMessageId_Last(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last discussion message flag in the ordered set where messageId = &#63;.
    *
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag fetchByMessageId_Last(
        long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message flags before and after the current discussion message flag in the ordered set where messageId = &#63;.
    *
    * @param pk the primary key of the current discussion message flag
    * @param messageId the message ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag[] findByMessageId_PrevAndNext(
        long pk, long messageId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion message flags where messageId = &#63; from the database.
    *
    * @param messageId the message ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion message flags where messageId = &#63;.
    *
    * @param messageId the message ID
    * @return the number of matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public int countByMessageId(long messageId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message flag where messageId = &#63; and flagType = &#63; or throws a {@link com.ext.portlet.NoSuchDiscussionMessageFlagException} if it could not be found.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @return the matching discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag findByMessageIdFlagType(
        long messageId, java.lang.String flagType)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message flag where messageId = &#63; and flagType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @return the matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag fetchByMessageIdFlagType(
        long messageId, java.lang.String flagType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message flag where messageId = &#63; and flagType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching discussion message flag, or <code>null</code> if a matching discussion message flag could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag fetchByMessageIdFlagType(
        long messageId, java.lang.String flagType, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the discussion message flag where messageId = &#63; and flagType = &#63; from the database.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @return the discussion message flag that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag removeByMessageIdFlagType(
        long messageId, java.lang.String flagType)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion message flags where messageId = &#63; and flagType = &#63;.
    *
    * @param messageId the message ID
    * @param flagType the flag type
    * @return the number of matching discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public int countByMessageIdFlagType(long messageId,
        java.lang.String flagType)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the discussion message flag in the entity cache if it is enabled.
    *
    * @param discussionMessageFlag the discussion message flag
    */
    public void cacheResult(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag);

    /**
    * Caches the discussion message flags in the entity cache if it is enabled.
    *
    * @param discussionMessageFlags the discussion message flags
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.DiscussionMessageFlag> discussionMessageFlags);

    /**
    * Creates a new discussion message flag with the primary key. Does not add the discussion message flag to the database.
    *
    * @param pk the primary key for the new discussion message flag
    * @return the new discussion message flag
    */
    public com.ext.portlet.model.DiscussionMessageFlag create(long pk);

    /**
    * Removes the discussion message flag with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag that was removed
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag remove(long pk)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.DiscussionMessageFlag updateImpl(
        com.ext.portlet.model.DiscussionMessageFlag discussionMessageFlag)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message flag with the primary key or throws a {@link com.ext.portlet.NoSuchDiscussionMessageFlagException} if it could not be found.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag
    * @throws com.ext.portlet.NoSuchDiscussionMessageFlagException if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag findByPrimaryKey(long pk)
        throws com.ext.portlet.NoSuchDiscussionMessageFlagException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the discussion message flag with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param pk the primary key of the discussion message flag
    * @return the discussion message flag, or <code>null</code> if a discussion message flag with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.DiscussionMessageFlag fetchByPrimaryKey(
        long pk) throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the discussion message flags.
    *
    * @return the discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the discussion message flags.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.DiscussionMessageFlagModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of discussion message flags
    * @param end the upper bound of the range of discussion message flags (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.DiscussionMessageFlag> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the discussion message flags from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of discussion message flags.
    *
    * @return the number of discussion message flags
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
