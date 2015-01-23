package com.ext.portlet.service.persistence;

import com.ext.portlet.model.SocialActivity;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the social activity service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SocialActivityPersistenceImpl
 * @see SocialActivityUtil
 * @generated
 */
public interface SocialActivityPersistence extends BasePersistence<SocialActivity> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link SocialActivityUtil} to access the social activity persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the social activity in the entity cache if it is enabled.
    *
    * @param socialActivity the social activity
    */
    public void cacheResult(com.ext.portlet.model.SocialActivity socialActivity);

    /**
    * Caches the social activities in the entity cache if it is enabled.
    *
    * @param socialActivities the social activities
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.SocialActivity> socialActivities);

    /**
    * Creates a new social activity with the primary key. Does not add the social activity to the database.
    *
    * @param activityId the primary key for the new social activity
    * @return the new social activity
    */
    public com.ext.portlet.model.SocialActivity create(long activityId);

    /**
    * Removes the social activity with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param activityId the primary key of the social activity
    * @return the social activity that was removed
    * @throws com.ext.portlet.NoSuchSocialActivityException if a social activity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SocialActivity remove(long activityId)
        throws com.ext.portlet.NoSuchSocialActivityException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.SocialActivity updateImpl(
        com.ext.portlet.model.SocialActivity socialActivity)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the social activity with the primary key or throws a {@link com.ext.portlet.NoSuchSocialActivityException} if it could not be found.
    *
    * @param activityId the primary key of the social activity
    * @return the social activity
    * @throws com.ext.portlet.NoSuchSocialActivityException if a social activity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SocialActivity findByPrimaryKey(
        long activityId)
        throws com.ext.portlet.NoSuchSocialActivityException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the social activity with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param activityId the primary key of the social activity
    * @return the social activity, or <code>null</code> if a social activity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.SocialActivity fetchByPrimaryKey(
        long activityId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the social activities.
    *
    * @return the social activities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SocialActivity> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the social activities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of social activities
    * @param end the upper bound of the range of social activities (not inclusive)
    * @return the range of social activities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SocialActivity> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the social activities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.ext.portlet.model.impl.SocialActivityModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of social activities
    * @param end the upper bound of the range of social activities (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of social activities
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.SocialActivity> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the social activities from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of social activities.
    *
    * @return the number of social activities
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
