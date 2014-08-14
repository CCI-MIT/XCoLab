package com.ext.portlet.service.persistence;

import com.ext.portlet.model.TrackedVisitor2User;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the tracked visitor2 user service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TrackedVisitor2UserPersistenceImpl
 * @see TrackedVisitor2UserUtil
 * @generated
 */
public interface TrackedVisitor2UserPersistence extends BasePersistence<TrackedVisitor2User> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link TrackedVisitor2UserUtil} to access the tracked visitor2 user persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the tracked visitor2 user in the entity cache if it is enabled.
    *
    * @param trackedVisitor2User the tracked visitor2 user
    */
    public void cacheResult(
        com.ext.portlet.model.TrackedVisitor2User trackedVisitor2User);

    /**
    * Caches the tracked visitor2 users in the entity cache if it is enabled.
    *
    * @param trackedVisitor2Users the tracked visitor2 users
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.TrackedVisitor2User> trackedVisitor2Users);

    /**
    * Creates a new tracked visitor2 user with the primary key. Does not add the tracked visitor2 user to the database.
    *
    * @param id the primary key for the new tracked visitor2 user
    * @return the new tracked visitor2 user
    */
    public com.ext.portlet.model.TrackedVisitor2User create(long id);

    /**
    * Removes the tracked visitor2 user with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param id the primary key of the tracked visitor2 user
    * @return the tracked visitor2 user that was removed
    * @throws com.ext.portlet.NoSuchTrackedVisitor2UserException if a tracked visitor2 user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisitor2User remove(long id)
        throws com.ext.portlet.NoSuchTrackedVisitor2UserException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.TrackedVisitor2User updateImpl(
        com.ext.portlet.model.TrackedVisitor2User trackedVisitor2User)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the tracked visitor2 user with the primary key or throws a {@link com.ext.portlet.NoSuchTrackedVisitor2UserException} if it could not be found.
    *
    * @param id the primary key of the tracked visitor2 user
    * @return the tracked visitor2 user
    * @throws com.ext.portlet.NoSuchTrackedVisitor2UserException if a tracked visitor2 user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisitor2User findByPrimaryKey(long id)
        throws com.ext.portlet.NoSuchTrackedVisitor2UserException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the tracked visitor2 user with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param id the primary key of the tracked visitor2 user
    * @return the tracked visitor2 user, or <code>null</code> if a tracked visitor2 user with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.TrackedVisitor2User fetchByPrimaryKey(long id)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the tracked visitor2 users.
    *
    * @return the tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.TrackedVisitor2User> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the tracked visitor2 users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of tracked visitor2 users
    * @param end the upper bound of the range of tracked visitor2 users (not inclusive)
    * @return the range of tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.TrackedVisitor2User> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the tracked visitor2 users.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.TrackedVisitor2UserModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of tracked visitor2 users
    * @param end the upper bound of the range of tracked visitor2 users (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.TrackedVisitor2User> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the tracked visitor2 users from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of tracked visitor2 users.
    *
    * @return the number of tracked visitor2 users
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
