package com.ext.portlet.service.persistence;

import com.ext.portlet.model.ModelDiscussion;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the model discussion service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ModelDiscussionPersistenceImpl
 * @see ModelDiscussionUtil
 * @generated
 */
public interface ModelDiscussionPersistence extends BasePersistence<ModelDiscussion> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ModelDiscussionUtil} to access the model discussion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the model discussion in the entity cache if it is enabled.
    *
    * @param modelDiscussion the model discussion
    */
    public void cacheResult(
        com.ext.portlet.model.ModelDiscussion modelDiscussion);

    /**
    * Caches the model discussions in the entity cache if it is enabled.
    *
    * @param modelDiscussions the model discussions
    */
    public void cacheResult(
        java.util.List<com.ext.portlet.model.ModelDiscussion> modelDiscussions);

    /**
    * Creates a new model discussion with the primary key. Does not add the model discussion to the database.
    *
    * @param modelDiscussionId the primary key for the new model discussion
    * @return the new model discussion
    */
    public com.ext.portlet.model.ModelDiscussion create(long modelDiscussionId);

    /**
    * Removes the model discussion with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param modelDiscussionId the primary key of the model discussion
    * @return the model discussion that was removed
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelDiscussion remove(long modelDiscussionId)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    public com.ext.portlet.model.ModelDiscussion updateImpl(
        com.ext.portlet.model.ModelDiscussion modelDiscussion, boolean merge)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model discussion with the primary key or throws a {@link com.ext.portlet.NoSuchModelDiscussionException} if it could not be found.
    *
    * @param modelDiscussionId the primary key of the model discussion
    * @return the model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelDiscussion findByPrimaryKey(
        long modelDiscussionId)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model discussion with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param modelDiscussionId the primary key of the model discussion
    * @return the model discussion, or <code>null</code> if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelDiscussion fetchByPrimaryKey(
        long modelDiscussionId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model discussions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelDiscussion> findByModelId(
        long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model discussions where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @return the range of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelDiscussion> findByModelId(
        long modelId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model discussions where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelDiscussion> findByModelId(
        long modelId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model discussion in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a matching model discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelDiscussion findByModelId_First(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model discussion in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a matching model discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelDiscussion findByModelId_Last(
        long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model discussions before and after the current model discussion in the ordered set where modelId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelDiscussionId the primary key of the current model discussion
    * @param modelId the model ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelDiscussion[] findByModelId_PrevAndNext(
        long modelDiscussionId, long modelId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model discussions where categoryId = &#63;.
    *
    * @param categoryId the category ID
    * @return the matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelDiscussion> findByDiscussionId(
        long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model discussions where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @return the range of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelDiscussion> findByDiscussionId(
        long categoryId, int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model discussions where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelDiscussion> findByDiscussionId(
        long categoryId, int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the first model discussion in the ordered set where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a matching model discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelDiscussion findByDiscussionId_First(
        long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the last model discussion in the ordered set where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param categoryId the category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a matching model discussion could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelDiscussion findByDiscussionId_Last(
        long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the model discussions before and after the current model discussion in the ordered set where categoryId = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param modelDiscussionId the primary key of the current model discussion
    * @param categoryId the category ID
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next model discussion
    * @throws com.ext.portlet.NoSuchModelDiscussionException if a model discussion with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.ext.portlet.model.ModelDiscussion[] findByDiscussionId_PrevAndNext(
        long modelDiscussionId, long categoryId,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.ext.portlet.NoSuchModelDiscussionException,
            com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns all the model discussions.
    *
    * @return the model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelDiscussion> findAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the model discussions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @return the range of model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelDiscussion> findAll(
        int start, int end)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the model discussions.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
    * </p>
    *
    * @param start the lower bound of the range of model discussions
    * @param end the upper bound of the range of model discussions (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of model discussions
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.ext.portlet.model.ModelDiscussion> findAll(
        int start, int end,
        com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model discussions where modelId = &#63; from the database.
    *
    * @param modelId the model ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model discussions where categoryId = &#63; from the database.
    *
    * @param categoryId the category ID
    * @throws SystemException if a system exception occurred
    */
    public void removeByDiscussionId(long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Removes all the model discussions from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model discussions where modelId = &#63;.
    *
    * @param modelId the model ID
    * @return the number of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public int countByModelId(long modelId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model discussions where categoryId = &#63;.
    *
    * @param categoryId the category ID
    * @return the number of matching model discussions
    * @throws SystemException if a system exception occurred
    */
    public int countByDiscussionId(long categoryId)
        throws com.liferay.portal.kernel.exception.SystemException;

    /**
    * Returns the number of model discussions.
    *
    * @return the number of model discussions
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.liferay.portal.kernel.exception.SystemException;
}
