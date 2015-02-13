package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ContestDiscussion;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contest discussion service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ContestDiscussionPersistenceImpl
 * @see ContestDiscussionUtil
 * @generated
 */
public interface ContestDiscussionPersistence extends BasePersistence<ContestDiscussion> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContestDiscussionUtil} to access the contest discussion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns the contest discussion where ContestId = &#63; and Tab = &#63; or throws a {@link com.ext.portlet.NoSuchContestDiscussionException} if it could not be found.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @return the matching contest discussion
    * @throws com.ext.portlet.NoSuchContestDiscussionException if a matching contest discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestDiscussion findByContestIdAndTab(
        long ContestId, java.lang.String Tab)
        throws com.ext.portlet.NoSuchContestDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest discussion where ContestId = &#63; and Tab = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @return the matching contest discussion, or <code>null</code> if a matching contest discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestDiscussion fetchByContestIdAndTab(
        long ContestId, java.lang.String Tab)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest discussion where ContestId = &#63; and Tab = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @param retrieveFromCache whether to use the finder cache
    * @return the matching contest discussion, or <code>null</code> if a matching contest discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestDiscussion fetchByContestIdAndTab(
        long ContestId, java.lang.String Tab, boolean retrieveFromCache)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes the contest discussion where ContestId = &#63; and Tab = &#63; from the database.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @return the contest discussion that was removed
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestDiscussion removeByContestIdAndTab(
        long ContestId, java.lang.String Tab)
        throws com.ext.portlet.NoSuchContestDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest discussions where ContestId = &#63; and Tab = &#63;.
    *
    * @param ContestId the contest ID
    * @param Tab the tab
    * @return the number of matching contest discussions
    * @throws SystemException if a system exception occurred
    */
    public int countByContestIdAndTab(long ContestId, java.lang.String Tab)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Caches the contest discussion in the entity cache if it is enabled.
    *
    * @param contestDiscussion the contest discussion
    */
    public void cacheResult(
        com.ext.portlet.model.ContestDiscussion contestDiscussion);

    /**
    * Caches the contest discussions in the entity cache if it is enabled.
    *
    * @param contestDiscussions the contest discussions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ContestDiscussion> contestDiscussions);

    /**
    * Creates a new contest discussion with the primary key. Does not add the contest discussion to the database.
    *
    * @param DiscussionId the primary key for the new contest discussion
    * @return the new contest discussion
    */
    public com.ext.portlet.model.ContestDiscussion create(long DiscussionId);

    /**
    * Removes the contest discussion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param DiscussionId the primary key of the contest discussion
    * @return the contest discussion that was removed
    * @throws com.ext.portlet.NoSuchContestDiscussionException if a contest discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestDiscussion remove(long DiscussionId)
        throws com.ext.portlet.NoSuchContestDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ContestDiscussion updateImpl(
        com.ext.portlet.model.ContestDiscussion contestDiscussion)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest discussion with the primary key or throws a {@link com.ext.portlet.NoSuchContestDiscussionException} if it could not be found.
    *
    * @param DiscussionId the primary key of the contest discussion
    * @return the contest discussion
    * @throws com.ext.portlet.NoSuchContestDiscussionException if a contest discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestDiscussion findByPrimaryKey(
        long DiscussionId)
        throws com.ext.portlet.NoSuchContestDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the contest discussion with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param DiscussionId the primary key of the contest discussion
    * @return the contest discussion, or <code>null</code> if a contest discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ContestDiscussion fetchByPrimaryKey(
        long DiscussionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the contest discussions.
    *
    * @return the contest discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ContestDiscussion> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ContestDiscussion> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

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
    public java.util.List<com.ext.portlet.model.ContestDiscussion> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the contest discussions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contest discussions.
    *
    * @return the number of contest discussions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
