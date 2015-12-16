package com.ext.portlet.service.impl.mock;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portlet.social.model.SocialActivity;
import com.liferay.portlet.social.service.SocialActivityLocalService;

import java.util.Date;
import java.util.List;

/**
 * <p>A mock class that realizes a counter contract.</p>
 * 
 * @author janusz
 *
 */
public class SocialActivityLocalServiceMock implements SocialActivityLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link SocialActivityLocalServiceUtil} to access the social activity local service. Add custom service methods to {@link impl.SocialActivityLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Adds the social activity to the database. Also notifies the appropriate model listeners.
    *
    * @param socialActivity the social activity
    * @return the social activity that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public SocialActivity addSocialActivity(SocialActivity socialActivity)
        throws SystemException {
        return null;
    }

    /**
    * Creates a new social activity with the primary key. Does not add the social activity to the database.
    *
    * @param activityId the primary key for the new social activity
    * @return the new social activity
    */
    @Override
    public SocialActivity createSocialActivity(
        long activityId) {
        return null;
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return null;
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(
        DynamicQuery dynamicQuery, int start,
        int end) throws SystemException {
        return null;
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public List dynamicQuery(
        DynamicQuery dynamicQuery, int start,
        int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        DynamicQuery dynamicQuery)
        throws SystemException {
        return 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public SocialActivity fetchSocialActivity(
        long activityId)
        throws SystemException {
        return null;
    }

    /**
    * Returns the social activity with the primary key.
    *
    * @param activityId the primary key of the social activity
    * @return the social activity
    * @throws PortalException if a social activity with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public SocialActivity getSocialActivity(
        long activityId)
        throws PortalException,
            SystemException {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public com.liferay.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws PortalException,
            SystemException {
        return null;
    }

    /**
    * Returns a range of all the social activities.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of social activities
    * @param end the upper bound of the range of social activities (not inclusive)
    * @return the range of social activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getSocialActivities(
        int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of social activities.
    *
    * @return the number of social activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getSocialActivitiesCount()
        throws SystemException {
        return 0;
    }

    /**
    * Updates the social activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param socialActivity the social activity
    * @return the social activity that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public SocialActivity updateSocialActivity(
        SocialActivity socialActivity)
        throws SystemException {
        return null;
    }

    /**
    * Updates the social activity in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param socialActivity the social activity
    * @param merge whether to merge the social activity with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
    * @return the social activity that was updated
    * @throws SystemException if a system exception occurred
    */
    public SocialActivity updateSocialActivity(
        SocialActivity socialActivity,
        boolean merge)
        throws SystemException {
        return null;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return null;
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
    }

    /**
    * Records an activity with the given time in the database.
    *
    * <p>
    * This method records a social activity done on an asset, identified by its
    * class name and class primary key, in the database. Additional information
    * (such as the original message ID for a reply to a forum post) is passed
    * in via the <code>extraData</code> in JSON format. For activities
    * affecting another user, a mirror activity is generated that describes the
    * action from the user's point of view. The target user's ID is passed in
    * via the <code>receiverUserId</code>.
    * </p>
    *
    * <p>
    * Example for a mirrored activity:<br> When a user replies to a message
    * boards post, the reply action is stored in the database with the
    * <code>receiverUserId</code> being the ID of the author of the original
    * message. The <code>extraData</code> contains the ID of the original
    * message in JSON format. A mirror activity is generated with the values of
    * the <code>userId</code> and the <code>receiverUserId</code> swapped. This
    * mirror activity basically describes a "replied to" event.
    * </p>
    *
    * <p>
    * Mirror activities are most often used in relation to friend requests and
    * activities.
    * </p>
    *
    * @param userId the primary key of the acting user
    * @param groupId the primary key of the group
    * @param createDate the activity's date
    * @param className the target asset's class name
    * @param classPK the primary key of the target asset
    * @param type the activity's type
    * @param extraData any extra data regarding the activity
    * @param receiverUserId the primary key of the receiving user
    * @throws PortalException if the user or group could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addActivity(long userId, long groupId,
                            Date createDate, java.lang.String className, long classPK,
                            int type, java.lang.String extraData, long receiverUserId)
        throws PortalException,
            SystemException {
    }

    /**
    * Records an activity in the database, using a time based on the current
    * time in an attempt to make the activity's time unique.
    *
    * @param userId the primary key of the acting user
    * @param groupId the primary key of the group
    * @param className the target asset's class name
    * @param classPK the primary key of the target asset
    * @param type the activity's type
    * @param extraData any extra data regarding the activity
    * @param receiverUserId the primary key of the receiving user
    * @throws PortalException if the user or group could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addActivity(long userId, long groupId,
                            java.lang.String className, long classPK, int type,
                            java.lang.String extraData, long receiverUserId)
        throws PortalException,
            SystemException {
    }

    @Override
    public void addActivity(
        SocialActivity activity,
        SocialActivity mirrorActivity)
        throws PortalException,
            SystemException {
    }

    /**
    * Records an activity in the database, but only if there isn't already an
    * activity with the same parameters.
    *
    * <p>
    * For the main functionality see {@link #addActivity(long, long, Date,
    * String, long, int, String, long)}
    * </p>
    *
    * @param userId the primary key of the acting user
    * @param groupId the primary key of the group
    * @param createDate the activity's date
    * @param className the target asset's class name
    * @param classPK the primary key of the target asset
    * @param type the activity's type
    * @param extraData any extra data regarding the activity
    * @param receiverUserId the primary key of the receiving user
    * @throws PortalException if the user or group could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addUniqueActivity(long userId, long groupId,
                                  Date createDate, java.lang.String className, long classPK,
                                  int type, java.lang.String extraData, long receiverUserId)
        throws PortalException,
            SystemException {
    }

    /**
    * Records an activity with the current time in the database, but only if
    * there isn't one with the same parameters.
    *
    * <p>
    * For the main functionality see {@link #addActivity(long, long, Date,
    * String, long, int, String, long)}
    * </p>
    *
    * @param userId the primary key of the acting user
    * @param groupId the primary key of the group
    * @param className the target asset's class name
    * @param classPK the primary key of the target asset
    * @param type the activity's type
    * @param extraData any extra data regarding the activity
    * @param receiverUserId the primary key of the receiving user
    * @throws PortalException if the user or group could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void addUniqueActivity(long userId, long groupId,
                                  java.lang.String className, long classPK, int type,
                                  java.lang.String extraData, long receiverUserId)
        throws PortalException,
            SystemException {
    }

    /**
    * Removes stored activities for the asset identified by the class name ID
    * and class primary key.
    *
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteActivities(
        com.liferay.portlet.asset.model.AssetEntry assetEntry)
        throws PortalException,
            SystemException {
    }

    /**
    * Removes stored activities for the asset identified by the class name and
    * class primary key.
    *
    * @param className the target asset's class name
    * @param classPK the primary key of the target asset
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteActivities(java.lang.String className, long classPK)
        throws SystemException {
    }

    /**
    * Removes the stored activity from the database.
    *
    * @param activityId the primary key of the stored activity
    * @throws PortalException if the activity could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteActivity(long activityId)
        throws PortalException,
            SystemException {
    }

    /**
    * Removes the stored activity and its mirror activity from the database.
    *
    * @param activity the activity to be removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteActivity(
        SocialActivity activity)
        throws SystemException {
    }

    /**
    * Removes the user's stored activities from the database.
    *
    * <p>
    * This method removes all activities where the user is either the actor or
    * the receiver.
    * </p>
    *
    * @param userId the primary key of the user
    * @throws SystemException if a system exception occurred
    */
    @Override
    public void deleteUserActivities(long userId)
        throws SystemException {
    }

    /**
    * Returns a range of all the activities done on assets identified by the
    * class name ID.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param classNameId the target asset's class name ID
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getActivities(
        long classNameId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns a range of all the activities done on the asset identified by the
    * class name ID and class primary key that are mirrors of the activity
    * identified by the mirror activity ID.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param mirrorActivityId the primary key of the mirror activity
    * @param classNameId the target asset's class name ID
    * @param classPK the primary key of the target asset
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getActivities(
        long mirrorActivityId, long classNameId, long classPK, int start,
        int end) throws SystemException {
        return null;
    }

    /**
    * Returns a range of all the activities done on the asset identified by the
    * class name and the class primary key that are mirrors of the activity
    * identified by the mirror activity ID.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param mirrorActivityId the primary key of the mirror activity
    * @param className the target asset's class name
    * @param classPK the primary key of the target asset
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getActivities(
        long mirrorActivityId, java.lang.String className, long classPK,
        int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns a range of all the activities done on assets identified by the
    * class name.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param className the target asset's class name
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getActivities(
        java.lang.String className, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done on assets identified by the class
    * name ID.
    *
    * @param classNameId the target asset's class name ID
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getActivitiesCount(long classNameId)
        throws SystemException {
        return 0;
    }

    /**
    * Returns the number of activities done on the asset identified by the
    * class name ID and class primary key that are mirrors of the activity
    * identified by the mirror activity ID.
    *
    * @param mirrorActivityId the primary key of the mirror activity
    * @param classNameId the target asset's class name ID
    * @param classPK the primary key of the target asset
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getActivitiesCount(long mirrorActivityId, long classNameId,
        long classPK)
        throws SystemException {
        return 0;
    }

    /**
    * Returns the number of activities done on the asset identified by the
    * class name and class primary key that are mirrors of the activity
    * identified by the mirror activity ID.
    *
    * @param mirrorActivityId the primary key of the mirror activity
    * @param className the target asset's class name
    * @param classPK the primary key of the target asset
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getActivitiesCount(long mirrorActivityId,
        java.lang.String className, long classPK)
        throws SystemException {
        return 0;
    }

    /**
    * Returns the number of activities done on assets identified by class name.
    *
    * @param className the target asset's class name
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getActivitiesCount(java.lang.String className)
        throws SystemException {
        return 0;
    }

    /**
    * Returns the activity identified by its primary key.
    *
    * @param activityId the primary key of the activity
    * @return Returns the activity
    * @throws PortalException if the activity could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public SocialActivity getActivity(
        long activityId)
        throws PortalException,
            SystemException {
        return null;
    }

    /**
    * Returns a range of all the activities done in the group.
    *
    * <p>
    * This method only finds activities without mirrors.
    * </p>
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param groupId the primary key of the group
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getGroupActivities(
        long groupId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done in the group.
    *
    * <p>
    * This method only counts activities without mirrors.
    * </p>
    *
    * @param groupId the primary key of the group
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getGroupActivitiesCount(long groupId)
        throws SystemException {
        return 0;
    }

    /**
    * Returns a range of activities done by users that are members of the
    * group.
    *
    * <p>
    * This method only finds activities without mirrors.
    * </p>
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param groupId the primary key of the group
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getGroupUsersActivities(
        long groupId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done by users that are members of the
    * group.
    *
    * <p>
    * This method only counts activities without mirrors.
    * </p>
    *
    * @param groupId the primary key of the group
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getGroupUsersActivitiesCount(long groupId)
        throws SystemException {
        return 0;
    }

    /**
    * Returns the activity that has the mirror activity.
    *
    * @param mirrorActivityId the primary key of the mirror activity
    * @return Returns the mirror activity
    * @throws PortalException if the mirror activity could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public SocialActivity getMirrorActivity(
        long mirrorActivityId)
        throws PortalException,
            SystemException {
        return null;
    }

    /**
    * Returns a range of all the activities done in the organization. This
    * method only finds activities without mirrors.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param organizationId the primary key of the organization
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getOrganizationActivities(
        long organizationId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done in the organization. This method
    * only counts activities without mirrors.
    *
    * @param organizationId the primary key of the organization
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getOrganizationActivitiesCount(long organizationId)
        throws SystemException {
        return 0;
    }

    /**
    * Returns a range of all the activities done by users of the organization.
    * This method only finds activities without mirrors.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param organizationId the primary key of the organization
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getOrganizationUsersActivities(
        long organizationId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done by users of the organization. This
    * method only counts activities without mirrors.
    *
    * @param organizationId the primary key of the organization
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getOrganizationUsersActivitiesCount(long organizationId)
        throws SystemException {
        return 0;
    }

    /**
    * Returns a range of all the activities done by users in a relationship
    * with the user identified by the user ID.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <>0</code> refers
    * to the first result in the set. Setting both <code>start</code> and
    * <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId the primary key of the user
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getRelationActivities(
        long userId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns a range of all the activities done by users in a relationship of
    * type <code>type</code> with the user identified by <code>userId</code>.
    * This method only finds activities without mirrors.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId the primary key of the user
    * @param type the relationship type
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getRelationActivities(
        long userId, int type, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done by users in a relationship with the
    * user identified by userId.
    *
    * @param userId the primary key of the user
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getRelationActivitiesCount(long userId)
        throws SystemException {
        return 0;
    }

    /**
    * Returns the number of activities done by users in a relationship of type
    * <code>type</code> with the user identified by <code>userId</code>. This
    * method only counts activities without mirrors.
    *
    * @param userId the primary key of the user
    * @param type the relationship type
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getRelationActivitiesCount(long userId, int type)
        throws SystemException {
        return 0;
    }

    /**
    * Returns a range of all the activities done by the user.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId the primary key of the user
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getUserActivities(
        long userId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done by the user.
    *
    * @param userId the primary key of the user
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserActivitiesCount(long userId)
        throws SystemException {
        return 0;
    }

    /**
    * Returns a range of all the activities done in the user's groups. This
    * method only finds activities without mirrors.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId the primary key of the user
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getUserGroupsActivities(
        long userId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done in user's groups. This method only
    * counts activities without mirrors.
    *
    * @param userId the primary key of the user
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserGroupsActivitiesCount(long userId)
        throws SystemException {
        return 0;
    }

    /**
    * Returns a range of all the activities done in the user's groups and
    * organizations. This method only finds activities without mirrors.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId the primary key of the user
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getUserGroupsAndOrganizationsActivities(
        long userId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done in user's groups and organizations.
    * This method only counts activities without mirrors.
    *
    * @param userId the primary key of the user
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserGroupsAndOrganizationsActivitiesCount(long userId)
        throws SystemException {
        return 0;
    }

    /**
    * Returns a range of all activities done in the user's organizations. This
    * method only finds activities without mirrors.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end -
    * start</code> instances. <code>start</code> and <code>end</code> are not
    * primary keys, they are indexes in the result set. Thus, <code>0</code>
    * refers to the first result in the set. Setting both <code>start</code>
    * and <code>end</code> to {@link
    * com.liferay.portal.kernel.dao.orm.Queryutil#ALL_POS} will return the full
    * result set.
    * </p>
    *
    * @param userId the primary key of the user
    * @param start the lower bound of the range of results
    * @param end the upper bound of the range of results (not inclusive)
    * @return the range of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<SocialActivity> getUserOrganizationsActivities(
        long userId, int start, int end)
        throws SystemException {
        return null;
    }

    /**
    * Returns the number of activities done in the user's organizations. This
    * method only counts activities without mirrors.
    *
    * @param userId the primary key of the user
    * @return the number of matching activities
    * @throws SystemException if a system exception occurred
    */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int getUserOrganizationsActivitiesCount(long userId)
        throws SystemException {
        return 0;
    }

	@Override
	public DynamicQuery dynamicQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteActivities(long groupId) throws SystemException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long dynamicQueryCount(DynamicQuery dynamicQuery,
			Projection projection) throws SystemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public SocialActivity fetchFirstActivity(String className, long classPK,
			int type) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SocialActivity> getActivitySetActivities(long activitySetId,
			int start, int end) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SocialActivity deleteSocialActivity(long activityId)
			throws PortalException, SystemException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SocialActivity deleteSocialActivity(SocialActivity socialActivity)
			throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}

}